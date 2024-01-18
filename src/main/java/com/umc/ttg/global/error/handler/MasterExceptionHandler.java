package com.umc.ttg.global.error.handler;

import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import com.umc.ttg.global.error.GeneralException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(annotations = {RestController.class})
public class MasterExceptionHandler extends ResponseEntityExceptionHandler {
    Logger logger = LoggerFactory.getLogger(MasterExceptionHandler.class);


    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        return handleExceptionInternal(e, ResponseCode._UNAUTHORIZED, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> general(GeneralException e, WebRequest request) {
        return handleExceptionInternal(e, e.getErrorCode(), request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        e.printStackTrace();
        return handleExceptionInternalFalse(e, ResponseCode._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ResponseCode._INTERNAL_SERVER_ERROR.getHttpStatus(),request);
    }


    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("At exception handler");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) request;
        HttpServletRequest servletRequest = requestAttributes.getRequest();

        String contentType = request.getHeader("Content-Type");
        logger.info("Content-Type : {}", contentType);
        logger.error("발생한 에러의 로그 :", ex);
        return handleExceptionInternal(ex, ResponseCode.valueOf(status), headers, status, request);
    }


    private ResponseEntity<Object> handleExceptionInternal(Exception e, ResponseCode errorCode,
                                                           WebRequest request) {
        return handleExceptionInternal(e, errorCode, HttpHeaders.EMPTY, errorCode.getHttpStatus(),
                request);
    }


    private ResponseEntity<Object> handleExceptionInternal(Exception e, ResponseCode errorCode,
                                                           HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponseDto<Object> body = BaseResponseDto.onFailure(errorCode, null);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }

    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ResponseCode errorCode,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request) {
        BaseResponseDto<Object> body = BaseResponseDto.onFailure(errorCode, null);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }
}
