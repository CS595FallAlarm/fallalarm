package com.fallalarm.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fallalarm.web.dao.DBConn;
import com.fallalarm.web.data.Admin;

@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
	public Admin curAdmin = new Admin();
    public	DBConn newdbConn=new DBConn();
	private static final long serialVersionUID = 1L;

    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String fname =request.getParameter("username");
		String password =request.getParameter("password");
		String name =fname;
		
		try {
			curAdmin=newdbConn.AdminLogin(fname, password);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		request.setAttribute("name", name);
		request.setAttribute("Admin",curAdmin);
		System.out.println(curAdmin.getFname()+ " " +curAdmin.getLname()); 
		RequestDispatcher view =request.getRequestDispatcher("/jsp/admin2.jsp");
		view.forward(request, response);
	}

}
