package ua.example.model.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.example.model.dto.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ua.example.view.PrintMessage.printErrorSqlRequest;

public class Converter {
    private final static Logger LOGGER = LogManager.getLogger(Converter.class);

    public static Employee convertToEmployee(ResultSet resultSet) {
        Employee employee = new Employee();

        try {
            resultSet.next();
            employee.setEmpno(resultSet.getInt("empno"));
            employee.setEname(resultSet.getString("ename"));
            employee.setJob(resultSet.getString("job"));
            employee.setMgr(resultSet.getInt("mgr"));
            employee.setHiredate(resultSet.getDate("hiredate").toLocalDate());
            employee.setSal(resultSet.getFloat("sal"));
            employee.setComm(resultSet.getFloat("comm"));
            employee.setDeptno(resultSet.getInt("deptno"));
            employee.setDname(resultSet.getString("dname"));
            employee.setLoc(resultSet.getString("loc"));
            employee.setGrade(resultSet.getInt("grade"));
        } catch (SQLException e) {
            LOGGER.error("Convert To Employee problem", e);
            printErrorSqlRequest();
        }

        return employee;
    }

    public static LocalDate stringToDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }

    public static Integer stringToInteger(String number) {
        if ("".equals(number)) {
            return null;
        } else {
            return Integer.parseInt(number);
        }
    }

    public static Float stringToFloat(String number) {
        if ("".equals(number)) {
            return null;
        } else {
            return Float.parseFloat(number);
        }
    }
}
