package com.AADHA.Starters.DBMSProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.deptdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.workindao;
import com.AADHA.Starters.DBMSProject.model.department;
import com.AADHA.Starters.DBMSProject.model.staff;

@Controller
public class dept {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/dept/{dept_name}")
    public ModelAndView dept(@PathVariable("dept_name") String dept_name){
        System.out.println("get one!!!!!!!!!!!!!1");
        ModelAndView mv=new ModelAndView();
        mv.setViewName("dept.html");
        deptdao depart=new deptdao(j);

        department temp=depart.findbyDeptname(dept_name);
        mv.addObject("dept", temp);
        staffdao inc=new staffdao(j);

        staff stf=inc.info(temp.getHead());

        workindao staff_filter=new workindao(j);
        List<Map<String,Object>> staffs=staff_filter.curr(dept_name);

        for (Map<String,Object>val : staffs){
            System.out.println(val.get("emp_id"));
            System.out.println(val.get("name"));
            System.out.println(val.get("phone_1"));
            System.out.println(val.get("email"));
            System.out.println(val.get("salary"));
        }
        mv.addObject("staffs", staffs);
        mv.addObject("incharge", stf);
        return mv;
    }

    @PostMapping("/staff/dept/{dept_name}")
    public ModelAndView dept(@PathVariable("dept_name") String dept_name,String show){
        System.out.println("post one!!!!!!!!!!!!!1");

        ModelAndView mv=new ModelAndView();
        deptdao depart=new deptdao(j);

        department temp=depart.findbyDeptname(dept_name);
        staffdao inc=new staffdao(j);
        System.out.println("reached");
        staff stf=inc.info(temp.getHead());

        workindao staff_filter=new workindao(j);
        System.out.println(show);
        System.out.println(show.equals("2"));
        if (show.equals("2"))
            mv.addObject("staffs", staff_filter.allemp(dept_name));
        else mv.addObject("staffs", staff_filter.curr(dept_name));
        System.out.println("Reached");
        mv.addObject("dept", temp);
        mv.addObject("incharge", stf);
        mv.setViewName("dept.html");
        return mv;
    }

}
