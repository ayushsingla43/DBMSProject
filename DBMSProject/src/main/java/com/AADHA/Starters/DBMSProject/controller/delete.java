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
import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;

import javax.servlet.http.HttpSession;

@Controller
public class delete {
    
    @Autowired
    JdbcTemplate j;

    @RequestMapping("/staff/student/delete/{UID}")
    public ModelAndView getMethodName(@PathVariable("UID") String UID, HttpSession session) {
        if((int)session.getAttribute("authority")<3){
            return new ModelAndView("error/405.html");
        }
        ModelAndView mv = new ModelAndView("studentList.html");
        studentdao sdao = new studentdao(j);
        coursesdao cdao = new coursesdao(j);
        String session_no = cdao.currentsession();
        student stu = sdao.getStudentByUID(UID);
        sdao.deleteStudent(UID);
        alumnidao adao = new alumnidao(j);
        adao.addAlumini(stu.getSRN(), stu.getClass_no(), Integer.parseInt(session_no), stu.getName(), stu.getAdmission_date(),
        stu.getEmail(), stu.getPhone_1(), stu.getPhone_2(), stu.getPhoto(), stu.getGender(), stu.getAadhar_no());
        adao.updateShowAlumni(null,Integer.parseInt(UID.substring(3)));
        mv.addObject("message","Student "+stu.getSRN()+" transferred succesfully");
        return mv;
    }

    @RequestMapping("/staff/delete/{UID}")
    public ModelAndView deletestaff(@PathVariable String UID, HttpSession session){
        if((int)session.getAttribute("authority")<3){
            return new ModelAndView("error/405.html");
        }
        ModelAndView mv=new ModelAndView("dummy/deptRedirect.html");
        staffdao stfd=new staffdao(j);
        staff stf = stfd.getStaffByUID(UID);
        List<Map<String,Object>> check=stfd.checkdept(String.valueOf(stf.getEmp_id()));
        if (check!=null){
            for(Map<String,Object> x : check){
                mv.addObject("switch", 1);
                mv.addObject("message","you have to delete employee "+String.valueOf(stf.getEmp_id())+ " from this department first");
                mv.addObject("dept", String.valueOf(x.get("dept_name")));
            }
        }
        else{
            mv.addObject("switch", 0);
            mv.addObject("message", "employee "+String.valueOf(stf.getEmp_id())+" is deleted");
            stfd.deletestaff(String.valueOf(stf.getEmp_id()));
        }
        return mv;
    }

    @RequestMapping("/staff/dept/{dept_name}/delete/{UID}")
    public ModelAndView deletestaff(@PathVariable String UID,@PathVariable String dept_name, HttpSession session){
        if((int)session.getAttribute("authority")<3){
            return new ModelAndView("error/405.html");
        }
        ModelAndView mv=new ModelAndView("dummy/staffListRedirect.html");
        coursesdao crs=new coursesdao(j);
        workindao wkn=new workindao(j);
        staffdao stfd = new staffdao(j);
        staff stf = stfd.getStaffByUID(UID);
        boolean check=crs.checktech(String.valueOf(stf.getEmp_id()), dept_name);
        if (check){
            mv.addObject("dept_name", dept_name);
            mv.addObject("emp_id", String.valueOf(stf.getEmp_id()));
            mv.addObject("switch", 1);
            mv.addObject("message", "Reassign these classes before deleting employee "+String.valueOf(stf.getEmp_id()));
            return mv;
        }
        else{
            wkn.removestaff(String.valueOf(stf.getEmp_id()),dept_name);
            mv.addObject("message","Employee "+String.valueOf(stf.getEmp_id())+" deleted successfully");
            mv.addObject("dept",dept_name);
            mv.addObject("switch", 0);
            return mv;
        }
    }
}
