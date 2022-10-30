package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class staffProfile {

    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/profile")
    public ModelAndView profile(){
        staffdao stfd = new staffdao(j);
        staff stf = stfd.info(1010);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("staffProfile.html");
        mv.addObject("stf",stf);
        return mv;
    }
}
