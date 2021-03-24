package com.creations.funds;

import com.creations.funds.exceptions.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

public class Preconditions {

    public static void validateNotNull(Object field) {
        if (field == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateIsNotEmpty(String field) {
        if (StringUtils.isEmpty(field)) {
            throw new IllegalArgumentException();
        }
    }

    public static void validatePhoneNumber(String phone) {
        if (!phone.matches("[0-9]{10}")) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateEmail(String email) {
        if (!email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")) {
            throw new IllegalArgumentException();
        }
    }

    public static void validateIsTrue(boolean expression, String message) {
        if (!expression) {
            throw new ServiceException(message, HttpStatus.BAD_REQUEST);
        }
    }
}
