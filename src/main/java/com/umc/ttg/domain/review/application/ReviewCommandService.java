package com.umc.ttg.domain.review.application;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.review.dto.ReviewRegisterRequestDTO;
import com.umc.ttg.domain.review.dto.ReviewRegisterResponseDTO;
import com.umc.ttg.global.common.BaseResponseDto;

import java.io.IOException;

public interface ReviewCommandService {
    BaseResponseDto<ReviewRegisterResponseDTO> save(Long storeId, ReviewRegisterRequestDTO reviewRegisterRequestDTO) throws IOException, WriterException;
}
