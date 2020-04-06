package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public static Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String DB_URL = "jdbc:mysql://localhost:3306/kontrolinis";
        String USER = "root";
        String PASS = "root";
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void disconnect(Connection conn) throws Exception {
        conn.close();
    }
}