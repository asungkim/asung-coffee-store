package com.personal.asung_coffee_store.global.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.lang.NonNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RsData<T> {

    @NonNull
    private String code;

    @NonNull
    private String message;

    @NonNull
    private T data;

    public RsData(String code, String message) {
        this(code, message, (T) new Empty());
    }

    @JsonIgnore
    public int getStatusCode() {
        String statusCodeStr = code.split("-")[0];
        return Integer.parseInt(statusCodeStr);
    }
}
