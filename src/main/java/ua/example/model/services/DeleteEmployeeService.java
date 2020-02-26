package ua.example.model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteEmployeeService {
    public boolean doWork(Connection connection, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM emp\n" +
                    "WHERE empno = ?");

            preparedStatement.setInt(1, id);

            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            throw new RuntimeException("EmployeeAdd request failed.", e);
        }
    }
}
