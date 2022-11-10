package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.alumnidao;
import com.AADHA.Starters.DBMSProject.model.alumni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class alumnii {

    @Autowired
    JdbcTemplate j;
    @GetMapping("/student/alumni")
    public ModelAndView showAlumni(HttpServletRequest request){
        alumnidao adao = new alumnidao(j);
        List<Map<String,Object>> li = adao.getMyAlumni();
        List<Object> z = new ArrayList<>();
        for(Map<String,Object> m: li){
            String s = String.valueOf(m.get("SRN"));
            alumni a = adao.getBySRN(Integer.parseInt(s));
            z.add(a);
        }
        ModelAndView mv = new ModelAndView("Alumni.html");
        Map<String,?> inputFlashMap = RequestContextUtils.getInputFlashMap(request);
        if(inputFlashMap!=null){
            mv.addObject("messsage", String.valueOf(inputFlashMap.get("message")));
        }
        mv.addObject("Alumnis", z);
        return mv;
    }

    @PostMapping("/staff/alumni/edit")
    public String editAlumni(Integer del_SRN, int add_SRN, RedirectAttributes redirectAttributes, HttpSession session){
        int x = (int)session.getAttribute("authority");
        if(x<3){
            return "error/405.html";
        }
        alumnidao adao = new alumnidao(j);
        alumni a = adao.getBySRN(add_SRN);
        if(a==null){
            redirectAttributes.addFlashAttribute("message", "Alumni does not exist!");
            return "redirect:/student/alumni";
        }
        adao.updateShowAlumni(del_SRN, add_SRN);
        return "redirect:/student/alumni";
    }
}
