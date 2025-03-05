package com.personal.asung_coffee_store.domain.member.service;

import com.personal.asung_coffee_store.domain.member.dto.request.SignupForm;
import com.personal.asung_coffee_store.domain.member.dto.response.MemberResponse;
import com.personal.asung_coffee_store.domain.member.entity.Member;
import com.personal.asung_coffee_store.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberValidator memberValidator;

    public MemberResponse createMember(SignupForm signupForm) {

        // 입력 폼 검증
        memberValidator.validateSignupForm(signupForm);

        Member member = Member.builder()
                .username(signupForm.username())
                .password(passwordEncoder.encode(signupForm.password()))
                .name(signupForm.name())
                .email(signupForm.email())
                .address(signupForm.address())
                .phoneNumber(signupForm.phoneNumber())
                .zipcode(signupForm.zipcode())
                .build();

        memberRepository.save(member);

        return MemberResponse.fromEntity(member);
    }


}
