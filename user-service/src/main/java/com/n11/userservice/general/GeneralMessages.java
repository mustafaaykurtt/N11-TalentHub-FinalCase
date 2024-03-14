package com.n11.userservice.general;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created By Mustafa Aykurt
 * Date:26.02.2024
 * Time:21:04
 */

public class GeneralMessages {
    public static String getMessageForLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle("messages", locale).getString(messageKey);
    }

    public static String getMessageForLocale(String messageKey, Locale locale, Object... arguments) {
        String message = getMessageForLocale(messageKey, locale);
        return MessageFormat.format(message, arguments);
    }
}
