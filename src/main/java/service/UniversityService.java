package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UniversityService {

    private String dbUrl = "jdbc:postgresql://localhost:5432/spring_academy_project";
    private String username = "postgres";
    private String password = "123";

    Connection connection = DriverManager.getConnection(dbUrl, username, password);


    public UniversityService() throws SQLException {
    }

    public boolean addUniversity(String name, String phoneNumber) throws SQLException {

        String sql = "{call add_university(?,?)}";
        CallableStatement callableStatement = connection.prepareCall(sql);

        callableStatement.setString(1, name);
        callableStatement.setString(2, phoneNumber);
        callableStatement.executeUpdate();
        return true;
    }
}
