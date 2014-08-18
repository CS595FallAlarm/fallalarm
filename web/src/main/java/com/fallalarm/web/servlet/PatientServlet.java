package com.fallalarm.web.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fallalarm.web.dao.DBConn;
import com.fallalarm.web.data.Patient;

/**
 * Servlet implementation class patientArrayList
 */
@WebServlet("/patientList")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConn newdbConn=new DBConn();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ArrayList <Patient> arrayList=new ArrayList<Patient>(); 
		    try {
				arrayList=newdbConn.ListPatient();
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    request.setAttribute("patientArrayList",arrayList);
			RequestDispatcher view =request.getRequestDispatcher("/jsp/viewAllPatients.jsp");
			view.forward(request, response);  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
