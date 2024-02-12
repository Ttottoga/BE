package com.umc.ttg.domain.member.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.umc.ttg.domain.member.entity.CustomOAuth2Member;
import com.umc.ttg.domain.member.entity.Member;
import com.umc.ttg.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2MemberServiceImpl extends DefaultOAuth2UserService {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String oauthClientName = request.getClientRegistration().getClientName();

//        System.out.println("accesstoken: " + request.getAccessToken().getTokenValue());

        Member member = null;
        String userId = null;
        String nickname = null;
        String profile_img = null;

        if(oauthClientName.equals("kakao")) {
            Map<String, Object> properties = (Map<String, Object>) oAuth2User.getAttributes().get("properties");

            userId = "kakao_" + oAuth2User.getAttributes().get("id");
            nickname = (String) properties.get("nickname");
            profile_img = (String) properties.get("profile_image");

            member = new Member(userId,  nickname, profile_img, "kakao");
        }

        if (oauthClientName.equals("naver")) {

        }

        if(memberService.findMemberByUsername(member.getName()) == null) {
            memberRepository.save(member);
        }
        else {
//            memberService.update();
        }
        System.out.println("Saving member");

        return new CustomOAuth2Member(userId);
//        return oAuth2User;
    }

}
