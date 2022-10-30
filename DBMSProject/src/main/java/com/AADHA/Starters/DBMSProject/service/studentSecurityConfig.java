package com.AADHA.Starters.DBMSProject.service;

import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@Order(2)
public class studentSecurityConfig {


    @Bean
    public UserDetailsService studentDetailsService(){
        return new studentUserDetailsService();
    }
    @Bean
    public PasswordEncoder passwordEncoder2(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider2(){
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(studentDetailsService());
        p.setPasswordEncoder(passwordEncoder2());
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity http)throws Exception{
        http.authenticationProvider(authenticationProvider2());
        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/student/**").authorizeRequests().anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/studentLogin")
                .usernameParameter("UID")
                .loginProcessingUrl("/student/login")
                .defaultSuccessUrl("/student/home")
                .failureUrl("/student/login")
                .permitAll()
                .and()
                .logout().logoutUrl("/student/logout")
                .logoutSuccessUrl("/");
        return http.build();
    }
}
