package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DBUtil;




public class LoginServices extends HttpServlet{
	
	 static Connection conn = DBUtil.getMyConnection();
	
	public void doPost( HttpServletRequest req,  HttpServletResponse res) throws IOException, ServletException{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		java.util.Date date=null;
		java.sql.Date sDate = null;
		SimpleDateFormat formatter;
		
		
		String dob = req.getParameter("dob");
		
		
		
		
		
		formatter = new SimpleDateFormat("dd/MM/yyyy");  
		
        try {

                date =  formatter.parse(dob);

                sDate = new java.sql.Date(date.getTime());

        } catch (ParseException e) {

                // TODO Auto-generated catch block

                e.printStackTrace();

        } 


		
		//Date dob1=(Date)Formatter. req.getParameter("dob");
		 
		
			try {
			
				CallableStatement cs = conn.prepareCall("{call register_16050(?,?,?,?,?,?,?,?,?)}");
				cs.setString(1, req.getParameter("fname"));
				cs.setString(2, req.getParameter("mname"));
				cs.setString(3, req.getParameter("lname"));
				cs.setString(4, req.getParameter("email"));
				cs.setString(5, req.getParameter("pass"));
				cs.setDate(6, sDate);
				cs.setInt(7, Integer.parseInt(req.getParameter("amount")));
				cs.setString(8, req.getParameter("address"));
				cs.setString(9, req.getParameter("phone"));
			
				int n = cs.executeUpdate();
				
				
				if(n>0){
				
					
					out.println("<h1><font color = 'white'> register  successfully!!!</font></h1>");
					RequestDispatcher rd = req.getRequestDispatcher("Login.html");
					rd.include(req, res);
				}
				
				
				else{
			
					out.println("<h1><font color = 'white'>register unsuccessfully!!!</font</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
					rd.include(req, res);
				
				}
				
				
				
				
			} catch (SQLException e) {
				out.println("<h1><font color = 'white'>register unsuccessfully!!!</font</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("Register.jsp");
				rd.include(req, res);
				//e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
		
	}
	
	}
