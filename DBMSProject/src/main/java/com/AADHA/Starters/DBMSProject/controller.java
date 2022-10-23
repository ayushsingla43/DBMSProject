package com.AADHA.Starters.DBMSProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @Autowired
    JdbcTemplate jdbc;

    @RequestMapping("/insert")
    public String index(){
        jdbc.execute("insert into Student(name, id, age) values('ABC', 1, 20) ");
        return "Succesful!";
    }
}
