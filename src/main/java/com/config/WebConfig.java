package com.config;

import com.filters.ValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(validationFilter());
        registration.addUrlPatterns("/priceHistory");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("validationFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean(name = "validationFilter")
    public Filter validationFilter() {
        return new ValidationFilter();
    }
}