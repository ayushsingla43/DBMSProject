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
            stf.setName(row.getString("name"));
            stf.setPhoto(row.getString("photo"));
            stf.setGender(row.getString("gender"));
            stf.setPhone_1(row.getString("phone_1"));
            stf.setPhone_2(row.getString("phone_2"));
            stf.setDOB(row.getString("DOB"));
            stf.setEmail(row.getString("email"));
            stf.setBlood_grp(row.getString("blood_grp"));
            stf.setExp_years(row.getInt("exp_years"));
            stf.setSalary(row.getInt("salary"));
            stf.setPIN(row.getInt("PIN"));
            stf.setStreet(row.getString("street"));
            stf.setCity(row.getString("city"));
            stf.setState(row.getString("state"));
            stf.setAadhar_no(row.getString("Aadhar_no"));
            stf.setPAN_no(row.getString("PAN_no"));
            stf.setCurr(row.getInt("curr"));
            stf.setUID(row.getString("UID"));
            stf.setPassword(row.getString("password"));
            return stf;
        }
    };

    public static RowMapper<department> rmdepartment = new RowMapper<department>(){
        @Override
        public department mapRow(ResultSet row, int ind) throws SQLException {
            department dept = new department();
            dept.setDept_name(row.getString("Dept_name"));
            dept.setContact(row.getString("contact"));
            dept.setEmail(row.getString("email"));
            dept.setFloor_no(row.getInt("floor_no"));
            dept.setRoom_no(row.getInt("room_no"));
            dept.setHead(row.getInt("head"));
            return dept;
        }
    };

    public static RowMapper<works_in> rmworks_in = new RowMapper<works_in>(){
        @Override
        public works_in mapRow(ResultSet row, int ind) throws SQLException {
            works_in wrk = new works_in();
            wrk.setEmp_id(row.getInt("Emp_id"));
            wrk.setDept_name(row.getString("dept_name"));
            wrk.setJoining_date(row.getString("joining_date"));
            wrk.setLeaving_date(row.getString("leaving_date"));
            return wrk;
        }
    };

    public static RowMapper<class_> rmclass = new RowMapper<class_>(){
        @Override
        public class_ mapRow(ResultSet row, int ind) throws SQLException {
            class_ cls = new class_();
            cls.setClass_no(row.getInt("class_no"));
            cls.setSection_no(row.getInt("section_no"));
            cls.setFloor_no(row.getInt("floor_no"));
            cls.setRoom_no(row.getInt("room_no"));
            cls.setIncharge(row.getInt("incharge"));
            return cls;
        }
    };

    public static RowMapper<student> rmstudent = new RowMapper<student>(){
        @Override
        public student mapRow(ResultSet row, int ind) throws SQLException {
            student stu = new student();
            stu.setSRN(row.getInt("SRN"));
            stu.setName(row.getString("name"));
            stu.setPhoto(row.getString("photo"));
            stu.setGender(row.getString("gender"));
            stu.setPhone_1(row.getString("phone_1"));
            stu.setPhone_2(row.getString("phone_2"));
            stu.setDOB(row.getString("DOB"));
            stu.setAdmission_date(row.getString("admission_date"));
            stu.setEmail(row.getString("email"));
            stu.setBlood_grp(row.getString("blood_grp"));
            stu.setGuardian(row.getString("guardian"));
            stu.setMother(row.getString("mother"));
            stu.setPIN(row.getInt("PIN"));
            stu.setStreet(row.getString("street"));
            stu.setCity(row.getString("city"));
            stu.setState(row.getString("state"));
            stu.setAadhar_no(row.getString("Aadhar_no"));
            stu.setClass_no(row.getInt("class_no"));
            stu.setSection_no(row.getInt("section_no"));
            stu.setPassword(row.getString("password"));
            stu.setUID(row.getString("UID"));
            return stu;
        }
    };

    public static RowMapper<courses> rmcourses = new RowMapper<courses>(){
        @Override
        public courses mapRow(ResultSet row, int ind) throws SQLException {
            courses crs = new courses();
            crs.setClass_no(row.getInt("class_no"));
            crs.setSection_no(row.getInt("section_no"));
            crs.setDept_name(row.getString("dept_name"));
            crs.setEmp_id(row.getInt("emp_id"));
            crs.setSession_no(row.getInt("session_no"));
            return crs;
        }
    };

    public static RowMapper<results> rmresults = new RowMapper<results>(){
        @Override
        public results mapRow(ResultSet row, int ind) throws SQLException {
            results res = new results();
            res.setSRN(row.getInt("SRN"));
            res.setSession_no(row.getInt("Session_no"));
            res.setCourse(row.getString("Course"));
            res.setMarks(row.getInt("marks"));
            return res;
        }
    };

    public static RowMapper<prev_class> rmprev_class = new RowMapper<prev_class>(){
        @Override
        public prev_class mapRow(ResultSet row, int ind) throws SQLException {
            prev_class pre = new prev_class();
            pre.setSRN(row.getInt("SRN"));
            pre.setSession_no(row.getInt("session_no"));
            pre.setClass_no(row.getInt("class_no"));
            pre.setSection_no(row.getInt("section_no"));
            return pre;
        }
    };

    public static RowMapper<alumni> rmalumni = new RowMapper<alumni>(){
        @Override
        public alumni mapRow(ResultSet row, int ind) throws SQLException {
            alumni alu = new alumni();
            alu.setSRN(row.getInt("SRN"));
            alu.setName(row.getString("name"));
            alu.setPhoto(row.getString("photo"));
            alu.setGender(row.getString("gender"));
            alu.setPhone_1(row.getString("phone_1"));
            alu.setPhone_2(row.getString("phone_2"));
            alu.setAdmission_date(row.getString("admission_date"));
            alu.setEmail(row.getString("email"));
            alu.setAadhar_no(row.getString("Aadhar_no"));
            alu.setLast_class(row.getInt("last_class"));
            alu.setLeave_year(row.getInt("leave_year"));
            return alu;
        }
    };

}
