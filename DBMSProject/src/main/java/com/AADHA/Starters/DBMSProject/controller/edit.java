package com.AADHA.Starters.DBMSProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.dao.workindao;
import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;

@Controller
public class edit {

    @Autowired
    JdbcTemplate j;
    
    @GetMapping("/staff/edit/{UID}")
    public ModelAndView staffEdit(@PathVariable("UID") String UID){
        ModelAndView mv = new ModelAndView("staffEdit.html");
        staffdao stfd = new staffdao(j);
        staff stf = stfd.getStaffByUID(UID);
        mv.addObject("stf", stf);
        return mv;
    }

    @PostMapping("/staff/edit/{UID}")
    public ModelAndView staffEdit(@PathVariable("UID") String UID, String stf_name, String stf_gender, int stf_exp, int stf_pin, int stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar, String stf_pan, String stf_photo)
    {
        ModelAndView mv = new ModelAndView("staffEdit.html");
        staffdao stfdao = new staffdao(j);
        staff st = stfdao.getStaffByAttribute("phone_1", stf_phone1);
        if(st!=null && !st.getPhone_1().equals(stf_phone1)){
            mv.addObject("message", "Phone1 already exists");
            return mv;
        }
        st = stfdao.getStaffByAttribute("email", stf_email);
        if(st!=null && !st.getEmail().equals(stf_email)){
            mv.addObject("message", "Email Address already exists");
            return mv;
        }
        st = stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar);
        if(st!=null && !st.getAadhar_no().equals(stf_aadhar)){
            mv.addObject("message", "Aadhar No should be unique");
            return mv;
        }
        st = stfdao.getStaffByAttribute("PAN_no", stf_pan);
        if(st!=null && !st.getPAN_no().equals(stf_pan)){
            mv.addObject("message", "PAN No should be unique");
            return mv;
        }
        
        stfdao.editTeacher(UID,stf_name, stf_gender, stf_exp, stf_pin,stf_salary,stf_dob, stf_email, stf_bg, stf_phone1, stf_phone2, stf_street, stf_city, stf_state,stf_aadhar, stf_pan, stf_photo);
        System.out.println("vn");
        st=stfdao.getStaffByUID(UID);
        mv.addObject("stf", st);
        mv.addObject("message", "edited successfully");
        return mv;
    }

    @GetMapping("/student/edit/{UID}")
    public ModelAndView studentEdit(@PathVariable("UID") String UID){
        ModelAndView mv =  new ModelAndView("studentEdit.html");
        classdao cls = new classdao(j);
        studentdao stud = new studentdao(j);
        student stu = stud.getStudentByUID(UID);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        mv.addObject("stu", stu);
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        return mv;
    }

    @PostMapping("/student/edit/{UID}")
    public ModelAndView insertStudent(@PathVariable("UID") String UID, String std_name, String std_gender, String std_date, Integer std_pin,String std_mother, String std_guardian, String std_class, String std_section,String std_dob, String std_email, String std_bg, String std_phone1, String std_phone2, String std_street, String std_city,String std_state, String std_aadhar, String std_photo)
    {
        studentdao stddao = new studentdao(j);
        ModelAndView mv = new ModelAndView("studentInsert.html");
        Integer Std_class = Integer.valueOf(std_class);
        Integer Std_section = Integer.valueOf(std_section);
        student stu=stddao.getStudentByAttribute("Aadhar_no", std_aadhar);
        if(stu!=null && !stu.getAadhar_no().equals(std_aadhar)){
            mv.addObject("message", "Aadhar Number should be unique!");
            return mv;
        }
        stddao.editStudent(UID,std_name,std_gender,std_date,std_pin,std_mother,std_guardian,Std_class,Std_section,std_dob,std_email,std_bg,std_phone1,std_phone2,std_street,std_city,std_state,std_aadhar,std_photo);
        classdao cls = new classdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        stu=stddao.getStudentByUID(UID);
        mv.addObject("stu", stu);
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("stu", stu);
        mv.addObject("message", "edited successfully");
        return mv;
    }
    
    @PostMapping("/staff/assign/change")
    public ModelAndView staffassignedit(String emp_id,String class_no,String section_no,String dept_name,String name){
        ModelAndView mv=new ModelAndView("staffassignedit.html");
        // System.out.println(emp_id);
        // System.out.println(class_no);
        // System.out.println(section_no);
        // System.out.println(dept_name);
        Map<String,Object>asn=new HashMap<String,Object>();
        asn.put("emp_id",emp_id);
        asn.put("class_no",class_no);
        asn.put("section_no",section_no);
        asn.put("dept_name",dept_name);
        asn.put("name",name);
        workindao wkn=new workindao(j);
        List<Map<String,Object>> staff=wkn.allemp(dept_name);
        mv.addObject("staff",staff);
        mv.addObject("asn",asn);
        return mv;
    }

    @PostMapping("/staff/assign/change2")
    public String redirect(String emp_id,String class_no,String section_no,String dept_name,String new_emp){
        coursesdao crs=new coursesdao(j);
        crs.updateassign(class_no, section_no, dept_name, new_emp);
        return "redirect:/staff/assign";
    }
}
