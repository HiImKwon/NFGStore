/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bui Quan
 */
public class DBUtilities {

    public static Connection makeConnection() 
            throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment_PRX301;instanceName=SQLEXPRESS2008";
        Connection con = DriverManager.getConnection(url, "sa", "12345678");
        if (con!=null) {
            System.out.println("connect successful");
        }
        return con;
    }
}
