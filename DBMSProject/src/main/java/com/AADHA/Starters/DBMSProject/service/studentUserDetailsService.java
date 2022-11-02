package com.AADHA.Starters.DBMSProject.service;

import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class studentUserDetailsService implements UserDetailsService {
    @Autowired private studentdao sdao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        student stu = sdao.getStudentByUID(username);
        if(stu == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found");
        }
        return new studentUserDetails(stu);
    }
}
