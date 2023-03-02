package com.haji;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowTable
 */
@WebServlet("/ShowTable")
public class ShowTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	PrintWriter pw =resp.getWriter();
	//resp.setContentType("text/html");
	  try {
		 String url="jdbc:mysql://localhost:3306/auth_sys";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,"root","system");
			Statement st = con.createStatement();
			String q= "SELECT * FROM ee";
			ResultSet rs=st.executeQuery(q);
			
			while(rs.next()) {
				pw.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"<br>");
				
			}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	  
  	}

}
