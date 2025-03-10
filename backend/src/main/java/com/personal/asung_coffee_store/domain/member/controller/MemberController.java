package com.personal.asung_coffee_store.domain.member.controller;

import com.personal.asung_coffee_store.domain.member.dto.request.LoginForm;
import com.personal.asung_coffee_store.domain.member.dto.request.SignupForm;
import com.personal.asung_coffee_store.domain.member.dto.response.LoginResponse;
import com.personal.asung_coffee_store.domain.member.dto.response.MemberResponse;
import com.personal.asung_coffee_store.domain.member.entity.Member;
import com.personal.asung_coffee_store.domain.member.service.AuthTokenService;
import com.personal.asung_coffee_store.domain.member.service.MemberService;
import com.personal.asung_coffee_store.global.dto.RsData;
import com.personal.asung_coffee_store.global.standard.request.Rq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final Rq rq;
    private final AuthTokenService authTokenService;
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


    @PostMapping("/login")
    public RsData<LoginResponse> login(@Valid @RequestBody LoginForm loginForm) {
        Member member = memberService.login(loginForm.username(), loginForm.password());

        // accessToken 발급 및 쿠키 추가
        String accessToken = authTokenService.generateAccessToken(member);
        rq.addCookie("accessToken", accessToken);

        return new RsData<>(
                "200",
                "로그인을 성공하였습니다.",
                new LoginResponse(MemberResponse.fromEntity(member), accessToken));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public RsData<MemberResponse> getMyInfo() {
        // 지금 누가 접속해있는지 체크
        Member actor = rq.getActor();
        Member realActor = rq.getRealActor(actor);

        return new RsData<>(
                "200",
                "내 정보 조회가 완료되었습니다.",
                MemberResponse.fromEntity(realActor));

    }
}
