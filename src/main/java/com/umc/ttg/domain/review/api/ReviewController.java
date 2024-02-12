package com.umc.ttg.domain.review.api;

import com.google.zxing.WriterException;
import com.umc.ttg.domain.review.application.ReviewCommandService;
import com.umc.ttg.domain.review.dto.ReviewRegisterRequestDTO;
import com.umc.ttg.domain.review.dto.ReviewRegisterResponseDTO;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewCommandService reviewService;

    @PostMapping
    public BaseResponseDto<ReviewRegisterResponseDTO> registerReview(
            @PathVariable("storeId") Long storeId,
            @ModelAttribute @Valid ReviewRegisterRequestDTO reviewRegisterRequestDTO) throws IOException, WriterException {

        return reviewService.save(storeId, reviewRegisterRequestDTO);
    }
}
