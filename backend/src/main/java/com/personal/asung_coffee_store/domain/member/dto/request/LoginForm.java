package com.personal.asung_coffee_store.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(@NotBlank(message = "아이디를 입력 해주세요.") String username,
                        @NotBlank(message = "비밀번호를 입력 해주세요.") String password) {
}
