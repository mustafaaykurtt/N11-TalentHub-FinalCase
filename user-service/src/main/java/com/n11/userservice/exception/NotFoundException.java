package com.n11.userservice.exception;

import com.n11.userservice.general.GeneralMessages;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Created By Mustafa Aykurt
 * Date:10.03.2024
 * Time:01:06
 */

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id) {
        super(GeneralMessages.getMessageForLocale("n11.user.not.found", LocaleContextHolder.getLocale(), id));
    }

}
