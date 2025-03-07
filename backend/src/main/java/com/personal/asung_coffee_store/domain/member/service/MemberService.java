package com.personal.asung_coffee_store.domain.member.service;

import com.personal.asung_coffee_store.domain.member.dto.request.SignupForm;
import com.personal.asung_coffee_store.domain.member.dto.response.MemberResponse;
import com.personal.asung_coffee_store.domain.member.entity.Member;
import com.personal.asung_coffee_store.domain.member.repository.MemberRepository;
import com.personal.asung_coffee_store.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponse createMember(SignupForm signupForm) {

        // 중복 데이터만 DB 검증
        validateDuplicateMember(signupForm.username(), signupForm.email());

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

    private void validateDuplicateMember(String username, String email) {
        if (memberRepository.existsByUsername(username)) {
            throw new ServiceException("409-1", "이미 존재하는 아이디입니다.");
        }

        if (memberRepository.existsByEmail(email)) {
            throw new ServiceException("409-2", "이미 존재하는 이메일입니다.");
        }
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
