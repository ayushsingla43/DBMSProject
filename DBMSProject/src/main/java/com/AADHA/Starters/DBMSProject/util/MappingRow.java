package com.AADHA.Starters.DBMSProject.util;

import com.AADHA.Starters.DBMSProject.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class MappingRow {

    public static RowMapper<staff> rmstaff = new RowMapper<staff>(){
        @Override
        public staff mapRow(ResultSet row, int ind) throws SQLException {
            staff stf = new staff();
            stf.setEmp_id(row.getInt("Emp_id"));
            return stf;
        }
    };

    public static RowMapper<department> rmdepartment = new RowMapper<department>(){
        @Override
        public department mapRow(ResultSet row, int ind) throws SQLException {
            department dept = new department();
            dept.setDept_name(row.getString("Dept_name"));
            return dept;
        }
    };

    public static RowMapper<works_in> rmworks_in = new RowMapper<works_in>(){
        @Override
        public works_in mapRow(ResultSet row, int ind) throws SQLException {
            works_in wrk = new works_in();
            wrk.setEmp_id(row.getInt("Emp_id"));
            wrk.setDept_name(row.getString("dept_name"));
            return wrk;
        }
    };

    public static RowMapper<class_> rmclass = new RowMapper<class_>(){
        @Override
        public class_ mapRow(ResultSet row, int ind) throws SQLException {
            class_ cls = new class_();
            cls.setClass_no(row.getInt("class_no"));
            cls.getSection_no(row.getInt("section_no"));
            return cls;
        }
    };

    public static RowMapper<student> rmstudent = new RowMapper<student>(){
        @Override
        public student mapRow(ResultSet row, int ind) throws SQLException {
            student stu = new student();
            stu.setSRN(row.getInt("SRN"));
            return stu;
        }
    };

    public static RowMapper<courses> rmcourses = new RowMapper<courses>(){
        @Override
        public courses mapRow(ResultSet row, int ind) throws SQLException {
            courses crs = new courses();
            crs.setClass_no(row.getInt("class_no"));
            crs.getSection_no(row.getInt("section_no"));
            crs.setDept_name(row.getString("dept_name"));
            return crs;
        }
    };

    public static RowMapper<results> rmresults = new RowMapper<results>(){
        @Override
        public results mapRow(ResultSet row, int ind) throws SQLException {
            results res = new results();
            res.setSRN(row.getInt("SRN"));
            res.getSession_no(row.getInt("Session_no"));
            res.setCourse(row.getString("Course"));
            return res;
        }
    };

    public static RowMapper<prev_class> rmprev_class = new RowMapper<prev_class>(){
        @Override
        public prev_class mapRow(ResultSet row, int ind) throws SQLException {
            prev_class pre = new prev_class();
            pre.setSRN(row.getInt("SRN"));
            pre.getSession_no(row.getInt("session_no"));
            return pre;
        }
    };

    public static RowMapper<books> rmbooks = new RowMapper<books>(){
        @Override
        public books mapRow(ResultSet row, int ind) throws SQLException {
            books bks = new books();
            bks.setBook_id(row.getInt("book_id"));
            return bks;
        }
    };

    public static RowMapper<alumni> rmalumni = new RowMapper<alumni>(){
        @Override
        public alumni mapRow(ResultSet row, int ind) throws SQLException {
            alumni alu = new alumni();
            alu.setSRN(row.getInt("SRN"));
            return alu;
        }
    };

}
