package com.example;

import com.example.entities.Category;
import com.example.repositories.CategoryRepository;
import com.example.services.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("[App] is started successfully.");
        final String DB_URL = "jdbc:postgresql://localhost:5432/timekeeping?user=postgres&password=root";
        try (Connection connection = DriverManager.getConnection(DB_URL)) {
            System.out.println("Connection to database was successful!");

           // CategoryService categoryServicenew = new CategoryService(new CategoryRepository(connection));
           // categoryServicenew.create("Games");

            logger.info("Action performing is finished.");
        } catch (SQLException e) {
            logger.error("Error occurred, see: " + e);
            throw new RuntimeException(e);
        }
        logger.info("[App] is finished successfully.");
    }
}
