package com.project.shopapp.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@RequiredArgsConstructor
public class LocalizationUtils {
    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public String getLocalizedMessage(String messageKey, HttpServletRequest request){
        Locale locale = localeResolver.resolveLocale(request);
        return messageSource.getMessage(messageKey, null, locale);
    }
}
