package com.royel.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setup connection variables
		String user = "springstudent";
		String password = "springstudent";

		String jdbcurl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.jdbc.Driver";

		// get connection to database

		try {
			PrintWriter out = response.getWriter();

			Class.forName(driver);
			Connection connection = DriverManager.getConnection(jdbcurl, user, password);

			out.println(connection);
			out.println("successfully connected.");

		} catch (Exception e) {
			e.printStackTrace();

			throw new ServletException();
		}

	}

}
