package com.umc.ttg.domain.member.api;

import com.umc.ttg.domain.member.application.MemberCommandService;
import com.umc.ttg.domain.member.application.MemberQueryService;
import com.umc.ttg.domain.member.application.MemberService;
import com.umc.ttg.domain.member.dto.MemberImageRequestDTO;
import com.umc.ttg.domain.member.dto.MemberImageResponseDTO;
import com.umc.ttg.domain.member.dto.MyPageAllResponseDto;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.global.common.BaseResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;
    private final MemberService memberService;

    @GetMapping("/profile")
    public BaseResponseDto<MyPageAllResponseDto> getMyPage() {

        return memberQueryService.myPageLookUp();
    }

    @PostMapping("/profile/image")
    public BaseResponseDto<MemberImageResponseDTO> modifyProfileImage
            (@ModelAttribute @Valid MemberImageRequestDTO memberImageRequestDTO) throws IOException {

        return memberCommandService.updateImage(memberImageRequestDTO);
    }

    @GetMapping
    public List<Member> checkAuthorized() {
        return memberService.findAll();
    }
}
