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
        validateDuplicateMember(signupForm.username(), signupForm.email(), signupForm.nickname());

        Member member = Member.builder()
                .username(signupForm.username())
                .password(passwordEncoder.encode(signupForm.password()))
                .nickname(signupForm.nickname())
                .name(signupForm.name())
                .email(signupForm.email())
                .address(signupForm.address())
                .phoneNumber(signupForm.phoneNumber())
                .zipcode(signupForm.zipcode())
                .build();

        memberRepository.save(member);

        return MemberResponse.fromEntity(member);
    }

    private void validateDuplicateMember(String username, String email,String nickname) {
        if (memberRepository.existsByUsername(username)) {
            throw new ServiceException("409-1", "이미 존재하는 아이디입니다.");
        }

        if (memberRepository.existsByEmail(email)) {
            throw new ServiceException("409-2", "이미 존재하는 이메일입니다.");
        }

        if (memberRepository.existsByNickname(nickname)) {
            throw new ServiceException("409-3", "이미 존재하는 닉네임입니다.");
        }
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new ServiceException("400-1", "아이디가 존재하지 않습니다.")
        );
    }

    public Member login(String username, String password) {
        Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new ServiceException("400-1", "잘못된 아이디입니다.")
        );

        if (!passwordEncoder.matches(member.getPassword(), password)) {
            throw new ServiceException("400-2", "비밀번호가 일치하지 않습니다.");
        }

        return member;
    }
}
