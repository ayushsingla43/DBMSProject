package com.AADHA.Starters.DBMSProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.alumnidao;
import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.dao.workindao;
import com.AADHA.Starters.DBMSProject.model.student;

@Controller
public class delete {
    
    @Autowired
    JdbcTemplate j;

    @RequestMapping(value="/staff/student/delete/{UID}")
    public ModelAndView getMethodName(@PathVariable("UID") String UID) {
        System.out.println(UID);
        studentdao sdao = new studentdao(j);
        coursesdao cdao = new coursesdao(j);
        String session_no = cdao.currentsession();
        student stu = sdao.getStudentByUID(UID);
        sdao.deleteStudent(UID);
        alumnidao adao = new alumnidao(j);
        adao.addAlumini(stu.getSRN(), stu.getClass_no(), Integer.parseInt(session_no), stu.getName(), stu.getAdmission_date(),
        stu.getEmail(), stu.getPhone_1(), stu.getPhone_2(), stu.getPhoto(), stu.getGender(), stu.getAadhar_no());
        ModelAndView mv = new ModelAndView("studentList.html");
        return mv;
    }

    @RequestMapping("/staff/delete/{emp_id}")
    public String deletestaff(@PathVariable String emp_id){
        staffdao stf=new staffdao(j);
        List<Map<String,Object>> check=stf.checkdept(emp_id);
        if (check!=null){
            for(Map<String,Object> x : check){
                return "redirect:/staff/dept/"+String.valueOf(x.get("dept_name"));
            }
        }
        else{
            stf.deletestaff(emp_id);
            return "redirect:/staff/list";
        }
        return "";
    }

    @RequestMapping("/staff/delete2/{emp_id}/{dept_name}")
    public ModelAndView deletestaff(@PathVariable String emp_id,@PathVariable String dept_name){
        ModelAndView mv=new ModelAndView();
        coursesdao stf=new coursesdao(j);
        workindao wkn=new workindao(j);
        boolean check=stf.checktech(emp_id, dept_name);
        if (check){
            mv.setViewName("autosubmit.html");
            mv.addObject("dept_name", dept_name);
            mv.addObject("emp_id", emp_id);
            return mv;
        }
        else{
            wkn.removestaff(emp_id,dept_name);
            mv.setViewName("redirect:/staff/list");
            return mv;
        }
    }
}
