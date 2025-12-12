package members;


import java.sql.*;

public class DBConfig {
 
 protected static final String URL = "jdbc:mysql://localhost:3306/gym_db"; 
 protected static final String USER = "root"; 
 protected static final String PASS = ""; 
 

 public Connection getConnection() throws SQLException {
     return DriverManager.getConnection(URL, USER, PASS);
 }
}