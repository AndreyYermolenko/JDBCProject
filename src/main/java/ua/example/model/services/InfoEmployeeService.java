package ua.example.model.services;

import ua.example.model.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ua.example.model.tools.Converter.convertToEmployee;

public class InfoEmployeeService {
    public Employee doWork(Connection connection, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT t1.*,\n" +
                    "t2.loc,\n" +
                    "t2.dname,\n" +
                    "t3.grade\n" +
                    "FROM emp t1\n" +
                    "LEFT JOIN dept t2 ON t1.deptno = t2.deptno\n" +
                    "LEFT JOIN salgrade t3 ON t1.sal >= t3.minsal AND t1.sal <= t3. hisal\n" +
                    "WHERE t1.empno = ?\n");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            return convertToEmployee(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("EmployeeInfo request failed.", e);
        }
    }
}
