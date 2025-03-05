package com.personal.asung_coffee_store.domain.member.controller;

import com.personal.asung_coffee_store.domain.member.dto.request.SignupForm;
import com.personal.asung_coffee_store.domain.member.dto.response.MemberResponse;
import com.personal.asung_coffee_store.domain.member.service.MemberService;
import com.personal.asung_coffee_store.global.dto.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    // TODO : 회원가입, 로그인, 내 정보 조회, 로그아웃

    @PostMapping("/signup")
    public RsData<MemberResponse> createMember(@Valid @RequestBody SignupForm signupForm) {
        MemberResponse memberResponse = memberService.createMember(signupForm);

        return new RsData<>(
                "200",
                "회원가입이 완료되었습니다.",
                memberResponse
        );
    }
}
