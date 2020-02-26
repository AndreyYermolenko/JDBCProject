package ua.example.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {
    private int empno;
    private String ename;
    private String job;
    private Integer mgr;
    private LocalDate hiredate;
    private float sal;
    private Float comm;
    private int deptno;
    private String dname;
    private String loc;
    private int grade;
}
