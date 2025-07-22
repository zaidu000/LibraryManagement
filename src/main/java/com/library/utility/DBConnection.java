package com.library.utility;

import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library?useSSL=false&serverTimezone=UTC",
                "root",                                
                "root"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
