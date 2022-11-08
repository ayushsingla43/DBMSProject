package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.alumnidao;
import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.resultsdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
public class insert {

    @Autowired
    JdbcTemplate j;
    @GetMapping("/staff/add")
    public ModelAndView insertTeacher(HttpSession session){
        if((int)session.getAttribute("authority")<3) return new ModelAndView("error/405.html");
        ModelAndView mv = new ModelAndView("staffInsert.html");
        return mv;
    }

    @PostMapping("/staff/add")
    public ModelAndView insertTeacher(String stf_name, String stf_gender, int stf_exp, int stf_pin, int stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar, String stf_pan, String stf_photo){
        ModelAndView mv = new ModelAndView("staffInsert.html");
        staffdao stfdao = new staffdao(j);
        staff st=stfdao.getStaffByAttribute("phone_1", stf_phone1);
        if(st!=null){
            mv.addObject("message", "Phone1 already exists");
            return mv;
        }
        st = stfdao.getStaffByAttribute("email", stf_email);
        if(st!=null){
            mv.addObject("message", "Email Address already exists");
            return mv;
        }
        st = stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar);
        if(st!=null){
            mv.addObject("message", "Aadhar No should be unique");
            return mv;
        }
        st = stfdao.getStaffByAttribute("PAN_no", stf_pan);
        if(st!=null){
            mv.addObject("message", "PAN No should be unique");
            return mv;
        }
        Random random = new Random();
        int leftLimit = '0', rightLimit = '9';
        int length = 8;
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedint = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedint);
        }
        String password = buffer.toString();
        stfdao.insertTeacher(stf_name, stf_gender, stf_exp, stf_pin,stf_salary,stf_dob, stf_email, stf_bg, stf_phone1, stf_phone2, stf_street, stf_city, stf_state,stf_aadhar, stf_pan, stf_photo, password
        );
        mv.setViewName("staffShowUID.html");
        mv.addObject("addedStaff", stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar));
        return mv;
    }

    @GetMapping("/student/add")
    public ModelAndView tryAddStudent(HttpSession session){
        if((int)session.getAttribute("authority")<3) return new ModelAndView("error/405.html");
        ModelAndView mv =  new ModelAndView("studentInsert.html");
        classdao cls = new classdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        return mv;
    }

    @PostMapping("/student/add")
    public ModelAndView insertStudent(String std_name, String std_gender, String std_date, Integer std_pin,String std_mother, String std_guardian, String std_class, String std_section,String std_dob, String std_email, String std_bg, String std_phone1, String std_phone2, String std_street, String std_city,String std_state, String std_aadhar, String std_photo)
    {
        ModelAndView mv = new ModelAndView("studentInsert.html");
        studentdao stddao = new studentdao(j);
        Integer Std_class = Integer.valueOf(std_class);
        Integer Std_section = Integer.valueOf(std_section);
        Random random = new Random();
        int leftLimit = '0', rightLimit = '9';
        int length = 8;
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedint = leftLimit + (int)(random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedint);
        }
        String password = buffer.toString();
        student stu=stddao.getStudentByAttribute("Aadhar_no", std_aadhar);
        if(stu!=null){
            mv.addObject("message", "Aadhar Number should be unique!");
            return mv;
        }
        stddao.insertStudent(std_name,std_gender,std_date,std_pin,std_mother,std_guardian,Std_class,Std_section,std_dob,std_email,std_bg,std_phone1,std_phone2,std_street,std_city,std_state,std_aadhar,std_photo,password);
        mv.setViewName("studentShowUID.html");
        mv.addObject("addedStudent", stddao.getStudentByAttribute("Aadhar_no", std_aadhar));
        return mv;
    }

    @PostMapping("/staff/insert/session")
    public ModelAndView create_session(RedirectAttributes redirectAttributes,String message,HttpSession session){
        if((int)session.getAttribute("authority")<4){
            return new ModelAndView("error/405.html");
        }
        ModelAndView mv=new ModelAndView();
        mv.setViewName("redirect:/staff/results");
        resultsdao res=new resultsdao(j);
        List<Map<String,Object>> students=res.emptyresult();
        if (students.size()==0){
            System.out.println("no null values");
            String curr=res.currsession();
            String newss=String.valueOf(Integer.valueOf(curr)+1);
            redirectAttributes.addFlashAttribute("message","Session "+newss+" created succesfully");
            coursesdao crs=new coursesdao(j);
            Map<String,List<String>> allcourses=crs.allclasscourses();
            System.out.println("reached");
            studentdao std=new studentdao(j);
            List<Map<String,Object>> crss=crs.allcs(curr);
            for(Map<String,Object> x: crss){
                crs.addcourse(String.valueOf(x.get("class_no")),String.valueOf(x.get("section_no")),String.valueOf(x.get("emp_id")),newss,String.valueOf(x.get("dept_name")));
            }
            List<Map<String,Object>> allstud=std.allstud(curr);
            for(Map<String,Object> stud: allstud){
                boolean pass=res.pass_fail(String.valueOf(stud.get("SRN")),curr);
                if (pass){
                    if (String.valueOf(stud.get("class_no")).equals("12")){
                        student stu=std.getStudentByUID(String.valueOf(stud.get("UID")));
                        std.deleteStudent(String.valueOf(stud.get("UID")));
                        alumnidao adao=new alumnidao(j);
                        adao.addAlumini(stu.getSRN(), stu.getClass_no(), Integer.parseInt(curr), stu.getName(), stu.getAdmission_date(),stu.getEmail(), stu.getPhone_1(), stu.getPhone_2(), stu.getPhoto(), stu.getGender(), stu.getAadhar_no());

                    }
                    else{
                        String class_no=String.valueOf(Integer.valueOf(String.valueOf(stud.get("class_no")))+1);
                        
                        std.update_class(String.valueOf(stud.get("SRN")),class_no);

                        res.addprev_class(String.valueOf(stud.get("SRN")),newss,String.valueOf(stud.get("class_no")),String.valueOf(stud.get("section_no")));

                        List<String> subj=allcourses.get(String.valueOf(stud.get("class_no"))+'-'+String.valueOf(stud.get("section_no")));

                        for (String dept_name : subj ){
                            res.addresult(String.valueOf(stud.get("SRN")),newss, dept_name);
                        }
                    }
                }
                else{
                    res.addprev_class(String.valueOf(stud.get("SRN")),newss,String.valueOf(stud.get("class_no")),String.valueOf(stud.get("section_no")));

                    List<String> subj=allcourses.get(String.valueOf(stud.get("class_no"))+'-'+String.valueOf(stud.get("section_no")));

                    for (String dept_name : subj ){
                        res.addresult(String.valueOf(stud.get("SRN")),newss, dept_name);
                    }
                }
            }

            staffdao stf=new staffdao(j);
            stf.increasexp();

        }
        else{
            System.out.println("null values");
            mv.setViewName("staffResults.html");
            Map<String,Object> filter = new HashMap<String,Object>() {{
                put("emp_id","");
                put("SRN","");
                put("class_","");
                put("section","");
                put("course","");
                put("session","");
                put("limit","");
            }};
            mv.addObject("filter", filter);
            if(message==null) mv.addObject("message","Some Student's results is not inserted");
            else mv.addObject("message",message);
            mv.addObject("students",students);
        }
        return mv;
    }
}
