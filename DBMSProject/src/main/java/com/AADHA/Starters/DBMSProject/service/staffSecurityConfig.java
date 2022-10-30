package com.AADHA.Starters.DBMSProject.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Order(1)
public class staffSecurityConfig {

    @Bean
    public UserDetailsService staffDetailsService(){
        return new staffUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder1(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider1(){
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(staffDetailsService());
        p.setPasswordEncoder(passwordEncoder1());
        return p;
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity http)throws Exception {
        http.authenticationProvider(authenticationProvider1());
        http.authorizeRequests().antMatchers("/").permitAll();

        http.antMatcher("/staff/**").authorizeRequests().anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/staffLogin")
                .usernameParameter("UID")
                .loginProcessingUrl("/staff/login")
                .defaultSuccessUrl("/home")
                .permitAll()
                .and()
                .logout().logoutUrl("/staff/logout")
                .logoutSuccessUrl("/");
        return http.build();
    }
}
