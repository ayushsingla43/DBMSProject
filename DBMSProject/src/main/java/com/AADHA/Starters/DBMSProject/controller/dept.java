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
    public ModelAndView deptProfile(@PathVariable("dept_name") String dept_name){
        ModelAndView mv=new ModelAndView("dept.html");
        deptdao depart=new deptdao(j);
        staffdao stfd=new staffdao(j);
        workindao wrkd=new workindao(j);
        department dpt=depart.findbyDeptname(dept_name);
        List<Map<String,Object>> staffs=wrkd.curr(dept_name);
        staff stf=stfd.info(dpt.getHead());
        mv.addObject("dept", dpt);
        mv.addObject("staffs", staffs);
        mv.addObject("incharge", stf);
        return mv;
    }

    @PostMapping("/staff/dept/{dept_name}")
    public ModelAndView deptProfile(@PathVariable("dept_name") String dept_name,String show,String message){
        ModelAndView mv=new ModelAndView("dept.html");
        deptdao depart=new deptdao(j);
        staffdao stfd=new staffdao(j);
        workindao wrkd=new workindao(j);
        department dpt=depart.findbyDeptname(dept_name);
        staff stf=stfd.info(dpt.getHead());
        if (show.equals("2")) mv.addObject("staffs", wrkd.allemp(dept_name));
        else mv.addObject("staffs", wrkd.curr(dept_name));
        mv.addObject("message", message);
        mv.addObject("dept", dpt);
        mv.addObject("incharge", stf);
        return mv;
    }

}
