package com.personal.asung_coffee_store.domain.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignupForm(

        @NotBlank(message = "아이디는 필수로 입력하여야 합니다.")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문과 숫자만 사용할 수 있습니다.")
        @Size(min = 8, max = 20, message = "아이디는 8~20자 사이여야 합니다.")
        String username,

        @NotBlank(message = "비밀번호는 필수로 입력하여야 합니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다.")
        String password,

        @NotBlank(message = "이름은 필수로 입력하여야 합니다.")
        String name,

        @NotBlank(message = "이메일은 필수로 입력하여야 합니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        String email,

        @NotBlank(message = "번호는 필수로 입력하여야 합니다.")
        String phoneNumber,

        @NotBlank(message = "주소는 필수로 입력하여야 합니다.")
        String address,

        @NotBlank(message = "우편번호는 필수로 입력하여야 합니다.")
        String zipcode
) {
}
