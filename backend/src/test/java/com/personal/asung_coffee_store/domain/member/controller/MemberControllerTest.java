package com.personal.asung_coffee_store.domain.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.asung_coffee_store.domain.member.dto.request.SignupForm;
import com.personal.asung_coffee_store.domain.member.entity.Member;
import com.personal.asung_coffee_store.domain.member.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MemberService memberService;

    private ResultActions signupRequest(SignupForm signupForm) throws Exception {
        String requestBody = objectMapper.writeValueAsString(signupForm);

        return mvc.perform(
                        post("/api/members/signup")
                                .contentType("application/json")
                                .content(requestBody)
                )
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup1() throws Exception {
        SignupForm signupForm = new SignupForm(
                "asung123",
                "Passw0rd!",
                "김아성",
                "asung@example.com",
                "010-1234-5678",
                "서울특별시 강남구",
                "12345"
        );

        ResultActions resultActions = signupRequest(signupForm);

        Member savedMember = memberService.findByUsername("asung123");
        Assertions.assertThat(savedMember.getUsername()).isEqualTo("asung123");

        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().methodName("createMember"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("회원가입이 완료되었습니다."));

    }

    @Test
    @DisplayName("회원가입 실패 - 중복 아이디")
    void signup2() throws Exception {
        SignupForm signupForm = new SignupForm(
                "asung123",
                "Passw0rd!",
                "김아성",
                "asung@example.com",
                "010-1234-5678",
                "서울특별시 강남구",
                "12345"
        );

        ResultActions resultActions = signupRequest(signupForm);

        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().methodName("createMember"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("회원가입이 완료되었습니다."));

        ResultActions sameRequestResult = signupRequest(signupForm);

        sameRequestResult
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("409-1"))
                .andExpect(jsonPath("$.message").value("이미 존재하는 아이디입니다."));

    }

    @Test
    @DisplayName("회원가입 실패 - 중복 이메일")
    void signup3() throws Exception {
        SignupForm signupForm1 = new SignupForm(
                "asung123",
                "Passw0rd!",
                "김아성",
                "asung@example.com",
                "010-1234-5678",
                "서울특별시 강남구",
                "12345"
        );

        ResultActions resultActions = signupRequest(signupForm1);

        resultActions
                .andExpect(status().isOk())
                .andExpect(handler().methodName("createMember"))
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("회원가입이 완료되었습니다."));

        SignupForm signupForm2 = new SignupForm(
                "aaaabbbb",
                "Passw0rd!",
                "김아성",
                "asung@example.com",
                "010-1234-5678",
                "서울특별시 강남구",
                "12345"
        );

        ResultActions sameRequestResult = signupRequest(signupForm2);

        sameRequestResult
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.code").value("409-2"))
                .andExpect(jsonPath("$.message").value("이미 존재하는 이메일입니다."));

    }

}