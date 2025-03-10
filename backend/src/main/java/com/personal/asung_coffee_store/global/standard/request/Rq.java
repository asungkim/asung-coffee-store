package com.personal.asung_coffee_store.global.standard.request;

import com.personal.asung_coffee_store.domain.member.entity.Member;
import com.personal.asung_coffee_store.domain.member.service.MemberService;
import com.personal.asung_coffee_store.domain.member.service.SecurityUser;
import com.personal.asung_coffee_store.global.exception.ServiceException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletResponse response;
    private final HttpServletRequest request;
    private final MemberService memberService;

    public void addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);

        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setAttribute("SameSite", "Strict");

        response.addCookie(cookie);
    }

    public Member getActor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new ServiceException("401-1", "로그인이 필요합니다.");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof SecurityUser)) {
            throw new ServiceException("401-2", "잘못된 인증 정보입니다.");
        }

        SecurityUser member = (SecurityUser) principal;

        return Member.builder()
                .id(member.getId())
                .username(member.getUsername())
                .role(member.getRole())
                .build();
    }

    public Member getRealActor(Member actor) {
        return memberService.findByUsername(actor.getUsername());
    }
}
