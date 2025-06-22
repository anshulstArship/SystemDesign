package com.main.userservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
public class SpringSecurity {
    @Bean
    public SecurityFilterChain filteringCriterion(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.cors().disable();
        http.authorizeHttpRequests(authorize-> authorize.anyRequest().permitAll());
        return http.build();
    }
    // spring boot will create object during startup,telling spring to consider this as a special class, class is already there but we want it's object
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
