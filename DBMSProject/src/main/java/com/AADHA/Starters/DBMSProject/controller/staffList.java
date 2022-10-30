package com.AADHA.Starters.DBMSProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;

@Controller
public class staffList {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/list")
    public ModelAndView teachersearch(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("staffList.html");
        coursesdao cls =new coursesdao(j);
        List<String>ssns=cls.sessions();
        mv.addObject("ssns",ssns);
        return mv;
    }

    @PostMapping("/staff/list")
    public ModelAndView teacherlist(String emp_id,String name,String session,String dept,String class_,String section,String limit){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("staffList.html");
        coursesdao cls =new coursesdao(j);
        List<String>ssns=cls.sessions();
        mv.addObject("ssns",ssns);
        staffdao staf=new staffdao(j);
        List<Map<String,Object>> staffs= staf.listquery(emp_id,name,session,dept,class_,section,limit);
        for(Map<String,Object> stf: staffs){
            System.out.print(stf.get("emp_id"));
            System.out.print(stf.get("name"));
            System.out.print(stf.get("phone_1"));
            System.out.print(stf.get("salary"));
            System.out.print(stf.get("email"));
            System.out.println();
        }
        mv.addObject("staffs",staffs);
        return mv;
    }
}
