package ua.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ua.example.controller.MainController;
import ua.example.model.services.AddEmployeeService;
import ua.example.model.services.DeleteEmployeeService;
import ua.example.model.services.InfoEmployeeService;
import ua.example.view.InfoEmployeeView;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class Main {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        File xmlPath = new File("src" + File.separator
                + "main" + File.separator
                + "resources" + File.separator
                + "datasource.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlPath);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

        String sourceName;
        String connectionUrl;
        String driverClass;
        String username;
        String password;

        try {
            sourceName = xpath.evaluate("/datasource/source-name", doc);
            connectionUrl = xpath.evaluate("/datasource/connection-url", doc);
            driverClass = xpath.evaluate("/datasource/driver-class", doc);
            username = xpath.evaluate("/datasource/user-name", doc);
            password = xpath.evaluate("/datasource/password", doc);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }

        MainController mainController = new MainController();

        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Класс JDBC-драйвера не найден!", e);
        }

        mainController.setInfoEmployeeService(new InfoEmployeeService());
        mainController.setInfoEmployeeView(new InfoEmployeeView());
        mainController.setAddEmployeeService(new AddEmployeeService());
        mainController.setDeleteEmployeeService(new DeleteEmployeeService());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             Connection connection = DriverManager.getConnection(connectionUrl + sourceName, username, password)) {
            mainController.doWork(reader, connection);
        } catch (SQLException | IOException e) {
            LOGGER.error("Connection problem", e);
        }
    }
}
