package com.AADHA.Starters.DBMSProject.service;

import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class staffUserDetailsService implements UserDetailsService {
    @Autowired
    staffdao sdao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        staff stf =  sdao.getStaffByUID(username);
        if(stf==null) throw new UsernameNotFoundException("No User Found");
        return new staffUserDetails(stf);
    }
}
