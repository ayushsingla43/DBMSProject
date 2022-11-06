package com.AADHA.Starters.DBMSProject.service;

import com.AADHA.Starters.DBMSProject.model.student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class studentUserDetails implements UserDetails {
    private student stu;

    public studentUserDetails(student stu) {
        this.stu = stu;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return stu.getPassword();
    }

    @Override
    public String getUsername() {
        return stu.getUID();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
