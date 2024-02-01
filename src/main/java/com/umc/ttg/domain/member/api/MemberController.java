package com.umc.ttg.domain.member.api;

import com.umc.ttg.domain.member.application.MemberQueryService;
import com.umc.ttg.domain.member.dto.MyPageResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberService;
    @GetMapping
    public BaseResponseDto<MyPageResponseDto> getMyPage() {

        return null;
    }
}
