package com.personal.asung_coffee_store.domain.member.dto.response;

public record LoginResponse(MemberResponse memberResponse, String accessToken) {
}
