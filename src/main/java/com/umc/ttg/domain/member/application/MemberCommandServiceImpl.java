package com.umc.ttg.domain.member.application;

import com.umc.ttg.domain.member.dto.MemberImageRequestDTO;
import com.umc.ttg.domain.member.dto.MemberImageResponseDTO;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.exception.handler.MemberHandler;
import com.umc.ttg.domain.member.repository.MemberRepository;
import com.umc.ttg.global.common.AwsS3;
import com.umc.ttg.global.common.BaseResponseDto;
import com.umc.ttg.global.common.ResponseCode;
import com.umc.ttg.global.error.handler.AwsS3Handler;
import com.umc.ttg.global.util.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {

    private final MemberRepository memberRepository;
    private final AwsS3Service awsS3Service;

    @Override
    public BaseResponseDto<MemberImageResponseDTO> updateImage(MemberImageRequestDTO memberImageRequestDTO) throws IOException {

        // 로그인 구현되면 시큐리티에서 member 가져올 예정
        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ResponseCode.MEMBER_NOT_FOUND));

        if (!CheckFileExtension(memberImageRequestDTO)) {
            throw new AwsS3Handler(ResponseCode.S3_UPLOAD_FAIL);
        }

        member.setProfileImage(getS3ImageLink(memberImageRequestDTO.getProfileImage()));

        memberRepository.save(member);

        MemberImageResponseDTO memberImageResponseDTO = new MemberImageResponseDTO(member.getId());

        return BaseResponseDto.onSuccess(memberImageResponseDTO, ResponseCode.OK);
    }

    private String getS3ImageLink(MultipartFile multipartFile) throws IOException {

        AwsS3 memberImage = awsS3Service.upload(multipartFile, "memberImage");

        return memberImage.getPath();

    }

    private boolean CheckFileExtension(MemberImageRequestDTO memberImageRequestDTO) {

        // 지원하지 않는 파일일 경우
        String fileName = String.valueOf(memberImageRequestDTO.getProfileImage().getOriginalFilename());
        int lastIndex = fileName.lastIndexOf(".");
        String fileExtensionName = fileName.substring(lastIndex + 1);

        if (!fileExtensionName.equals("jpg") && !fileExtensionName.equals("png") && !fileExtensionName.equals("jpeg"))
            return false;

        return true;
    }
}
