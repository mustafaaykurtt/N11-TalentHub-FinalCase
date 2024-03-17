package com.n11.userservice.exception;

import com.n11.userservice.general.GeneralMessages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

/**
 * Created By Mustafa Aykurt
 * Date:10.03.2024
 * Time:03:32
 */

public class NotUniqueException extends RuntimeException {

    public NotUniqueException() {
        super(GeneralMessages.getMessageForLocale("n11.error.validation", LocaleContextHolder.getLocale()));
    }

    public Map<String, String> getValidationErrors() {
        return Collections.singletonMap("email", GeneralMessages.getMessageForLocale("n11.constraint.email.notUnique", LocaleContextHolder.getLocale()));
    }
}
