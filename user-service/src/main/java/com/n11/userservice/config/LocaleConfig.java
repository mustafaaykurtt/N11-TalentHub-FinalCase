package com.n11.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * Created By Mustafa Aykurt
 * Date:11.03.2024
 * Time:22:53
 */

@Configuration
public class LocaleConfig {

    @Bean
    public String changedLanguage() {
        Locale.setDefault(Locale.ENGLISH);
        return "Success";
    }
}
