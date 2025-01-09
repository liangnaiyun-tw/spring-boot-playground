package com.example.blogserver.configs;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

@Configuration
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {
    List<Locale> LOCALES = List.of(
            new Locale("en"),
            new Locale("zh"),
            new Locale("ja")
    );

    @Override
    public Locale resolveLocale(HttpServletRequest request){
        String headerLang = request.getHeader("Accept-Language");
        return headerLang == null || headerLang.isEmpty() ? Locale.getDefault() : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
    }

    @Bean
    public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("message");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }
}
