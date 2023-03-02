package com.haji;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUser extends HttpServlet {
 @Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	 resp.setContentType("text/html");
	 String uname=req.getParameter("uname");
	 String pass=req.getParameter("pass");
	 PrintWriter pw= resp.getWriter();
	 if(uname.isEmpty()||pass.isEmpty()) {
		 RequestDispatcher rd = req.getRequestDispatcher("Aduser.html");
		 
		 rd.include(req, resp);
		 pw.print("<h2 style=\"text-align:center; color: black;\">empty credentials</h2>");
		 
	 }
	 else {
		 try {
			 String url="jdbc:mysql://localhost:3306/auth_sys";
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url,"root","system");
			Statement st = con.createStatement();
			String q= "SELECT * FROM ee";
			ResultSet r=st.executeQuery(q);
			boolean flag= true;
			while(r.next()) {
				if(r.getString(1).equals(uname)) {

					 RequestDispatcher rd = req.getRequestDispatcher("Aduser.html");
					 flag=false;
					 rd.include(req, resp);
					 pw.print("<h2 style=\"text-align:center; color: black;\">UserName exists</h2>");
					break;
				}
				
			}
			if(flag) {
			 String pq="insert into ee values(?,?,?);"; PreparedStatement ps=
			  con.prepareStatement(pq); ps.setString(1,uname); ps.setString(2, pass);
			  ps.setString(3,"lodfa"); ps.executeUpdate();
			  pw.print("<h2 style=\"text-align:center; color: black;\">UserADDED</h2>");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }

 }
}
