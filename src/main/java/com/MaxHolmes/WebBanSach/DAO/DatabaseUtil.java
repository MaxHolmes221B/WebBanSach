package com.MaxHolmes.WebBanSach.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
	   private static final String DB_URL = "jdbc:mysql://localhost:3306/webbansach";
	   private static final String USER = "root";
	   private static final String PASSWORD = "03012009.";
	   public static Connection getConnection() throws SQLException, ClassNotFoundException {
	      Class.forName("com.mysql.cj.jdbc.Driver");
	      return DriverManager.getConnection(DB_URL, USER, PASSWORD);
	   }
}