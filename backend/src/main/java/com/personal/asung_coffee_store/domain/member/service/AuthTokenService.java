package com.personal.asung_coffee_store.domain.member.service;

import com.personal.asung_coffee_store.domain.member.entity.Member;
import com.personal.asung_coffee_store.global.standard.util.Ut;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    @Value("${custom.jwt.secret-key}")
    private String secretKey;

    @Value("${custom.jwt.expire-seconds}")
    private int expire_seconds;

    public String generateAccessToken(Member member) {
        return Ut.Jwt.createToken(
                secretKey,
                expire_seconds,
                Map.of(
                        "id", member.getId(),
                        "username", member.getUsername(),
                        "nickname", member.getNickname(),
                        "role", member.getRole()
                ));
    }
}
