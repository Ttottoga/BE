package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.member.dto.MyPageResponseDto;
import com.umc.ttg.global.common.BaseResponseDto;

public interface MemberQueryService {
    BaseResponseDto<MyPageResponseDto> lookUpMyPage();
}
