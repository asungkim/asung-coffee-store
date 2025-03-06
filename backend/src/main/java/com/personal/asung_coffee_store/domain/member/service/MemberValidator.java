package com.personal.asung_coffee_store.domain.member.service;

import com.personal.asung_coffee_store.domain.member.dto.request.SignupForm;
import com.personal.asung_coffee_store.domain.member.repository.MemberRepository;
import com.personal.asung_coffee_store.global.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberValidator {
    private final MemberRepository memberRepository;

    // TODO : 비밀번호, 이메일 형식, 데이터 누락 검증 (향후 리펙토링)

    public void validateSignupForm(SignupForm signupForm) {
        validateUsername(signupForm.username());
        validateEmail(signupForm.email());
    }

    private void validateUsername(String username) {
        boolean isUsernameExist = memberRepository.existsByUsername(username);
        if (isUsernameExist) {
            throw new ServiceException("409-1", "이미 존재하는 아이디입니다.");
        }

    }

    private void validateEmail(String email) {
        boolean isEmailExist = memberRepository.existsByEmail(email);
        if (isEmailExist) {
            throw new ServiceException("409-2", "이미 존재하는 이메일입니다.");
        }
    }
}
