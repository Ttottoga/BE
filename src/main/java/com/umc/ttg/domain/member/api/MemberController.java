package com.umc.ttg.domain.member.api;

import com.umc.ttg.domain.member.application.MemberCommandService;
import com.umc.ttg.domain.member.application.MemberQueryService;
import com.umc.ttg.domain.member.dto.MemberImageRequestDTO;
import com.umc.ttg.domain.member.dto.MemberImageResponseDTO;
import com.umc.ttg.domain.member.dto.MyPageAllResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members/profile")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    @GetMapping
    public BaseResponseDto<MyPageAllResponseDto> getMyPage() {

        return memberQueryService.myPageLookUp();
    }

    @PostMapping("/image")
    public BaseResponseDto<MemberImageResponseDTO> modifyProfileImage
            (@RequestBody @Valid MemberImageRequestDTO memberImageRequestDTO) {

        return memberCommandService.modifyImage(memberImageRequestDTO);
    }
}
