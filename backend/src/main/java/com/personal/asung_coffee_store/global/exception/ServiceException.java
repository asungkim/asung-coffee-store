package com.personal.asung_coffee_store.global.exception;

import com.personal.asung_coffee_store.global.dto.RsData;

public class ServiceException extends RuntimeException {
    private final RsData<?> rsData;

	public ServiceException(String code, String message) {
		super(message);
		rsData = new RsData<>(code, message);
	}

	public String getCode() {
		return rsData.getCode();
	}

	public String getMsg() {
		return rsData.getMessage();
	}

	public int getStatusCode() {
		return rsData.getStatusCode();
	}
}
