package com.cg.minitest1module4.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}123456").roles("USER")
                .and()
                .withUser("admin").password("{noop}123455").roles("ADMIN");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/" ).permitAll() //cho phep ng dung truy cap toi cac trang web, do uu tien cao hon .anyRequest().authenticated()
                .requestMatchers("/user**").hasRole("USER")
                .requestMatchers("/admin**").hasRole("ADMIN")
                .anyRequest().authenticated() // chan k cho ng dung truy cap vao cac trang web khac truoc khi dang nhap
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }
}
