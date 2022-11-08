package com.AADHA.Starters.DBMSProject.dao;

import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.works_in;
import com.AADHA.Starters.DBMSProject.util.MappingRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class getAuthority {

    @Autowired
    private final JdbcTemplate jdbc;

    public getAuthority(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public int Authority(staff stf){
        String sql = "Select * from works_in where emp_id=? and dept_name=?";
        works_in wi;
        try {
            wi = jdbc.queryForObject(sql, MappingRow.rmworks_in, stf.getEmp_id(), "Principal");
            return 4;
        }
        catch (EmptyResultDataAccessException e){
            try{
                wi = jdbc.queryForObject(sql, MappingRow.rmworks_in, stf.getEmp_id(), "Office Worker");
                return 3;
            }
            catch(EmptyResultDataAccessException E){
                return 2;
            }
        }
    }
}
