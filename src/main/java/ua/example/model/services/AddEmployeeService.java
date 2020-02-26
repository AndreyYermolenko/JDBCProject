package ua.example.model.services;

import ua.example.model.dto.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeService {
    public boolean doWork(Connection connection, Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO emp\n" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setInt(1, employee.getEmpno());
            preparedStatement.setString(2, employee.getEname());
            preparedStatement.setString(3, employee.getJob());
            preparedStatement.setObject(4, employee.getMgr());
            preparedStatement.setDate(5, java.sql.Date.valueOf(employee.getHiredate()));
            preparedStatement.setFloat(6, employee.getSal());
            preparedStatement.setObject(7, employee.getComm());
            preparedStatement.setInt(8, employee.getDeptno());

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException("EmployeeAdd request failed.", e);
        }
    }
}
