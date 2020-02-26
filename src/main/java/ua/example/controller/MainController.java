package ua.example.controller;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.example.model.dto.Employee;
import ua.example.model.services.AddEmployeeService;
import ua.example.model.services.DeleteEmployeeService;
import ua.example.model.services.InfoEmployeeService;
import ua.example.view.InfoEmployeeView;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static ua.example.model.tools.Converter.*;
import static ua.example.view.PrintMessage.*;

@Data
public class MainController {
    private final static Logger LOGGER = LogManager.getLogger(MainController.class);

    private InfoEmployeeService infoEmployeeService;
    private InfoEmployeeView infoEmployeeView;

    private AddEmployeeService addEmployeeService;
    private DeleteEmployeeService deleteEmployeeService;

    public void doWork(BufferedReader reader, Connection connection) {
        while(true) {
            try {
                printMainMenu();

                String input = reader.readLine();

                if ("".equals(input)) {
                    break;
                }
                if (!input.matches("[1-3]")) {
                    printToCorrectInput();
                    continue;
                }
                int number = Integer.parseInt(input);
                choiceCase(reader, connection, number);

            } catch (IOException e) {
                LOGGER.error("IOException", e);
            }
        }
    }

    private void choiceCase(BufferedReader reader, Connection connection, int number) {
        switch (number) {
            case 1:
                employeeInfo(reader, connection);
                break;
            case 2:
                addEmployee(reader, connection);
                break;
            case 3:
                deleteEmployee(reader, connection);
                break;
        }
    }

    private void employeeInfo(BufferedReader reader, Connection connection) {
        printInfoEmployeeMenu();
        try {
            String input = reader.readLine();
            int id = Integer.parseInt(input);
            Employee employee = infoEmployeeService.doWork(connection, id);
            infoEmployeeView.doWork(employee);

            printToContinue();
            reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } catch (NumberFormatException e) {
            printToCorrectInput();
        }
    }

    private void addEmployee(BufferedReader reader, Connection connection) {
        printAddEmployeeMenu();
        try {
            int empno = Integer.parseInt(reader.readLine());
            String ename = reader.readLine();
            String job = reader.readLine();
            Integer mgr = stringToInteger(reader.readLine());
            LocalDate hiredate = stringToDate(reader.readLine());
            float sal = Float.parseFloat(reader.readLine());
            Float comm = stringToFloat(reader.readLine());
            int deptno = Integer.parseInt(reader.readLine());

            Employee employee = new Employee();
            employee.setEmpno(empno);
            employee.setEname(ename);
            employee.setJob(job);
            employee.setMgr(mgr);
            employee.setHiredate(hiredate);
            employee.setSal(sal);
            employee.setComm(comm);
            employee.setDeptno(deptno);

            boolean isSuccessful = addEmployeeService.doWork(connection, employee);
            if (isSuccessful) {
                printAddEmployeeSuccessful();
            }
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } catch (NumberFormatException | DateTimeParseException e) {
            printToCorrectInput();
        }


    }

    private void deleteEmployee(BufferedReader reader, Connection connection) {
        printInfoEmployeeMenu();
        try {
            String input = reader.readLine();
            int id = Integer.parseInt(input);

            boolean isSuccessful = deleteEmployeeService.doWork(connection, id);
            if (isSuccessful) {
                printDeleteEmployeeSuccessful();
            } else {
                printToCorrectInput();
            }

            printToContinue();
            reader.readLine();
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } catch (NumberFormatException e) {
            printToCorrectInput();
        }
    }
}
