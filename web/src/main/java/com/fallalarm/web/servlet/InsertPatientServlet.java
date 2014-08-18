package com.fallalarm.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fallalarm.web.dao.DBConn;
import com.fallalarm.web.servlet.util.ServletUtilities;

/**
 * Servlet implementation class insertPatient
 */
@WebServlet("/insertPatient")
public class InsertPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPatientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConn newdbConn=new DBConn();
		
		int pid=Integer.parseInt(request.getParameter("pID"));
		String fName=request.getParameter("fName");
		String lName=request.getParameter("lName");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		int deviceid=Integer.parseInt(request.getParameter("deviceid"));
		String nfName=request.getParameter("nfName");
		String nlName=request.getParameter("nlName");
		
			try {
				
				newdbConn.insertNewPatient(pid,fName, lName,address, phone, deviceid,nfName, nlName);
				
				
				
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = "Add new patient";
	    
	    out.println
	      (ServletUtilities.headWithTitle(title) +
	       "<body bgcolor=\"#fdf5e6\">\n" +
	       "<h1>" + title + "</h1>\n" + 
	       "<p>Add sucessfully.</p>\n" + lName + "   " +fName + "  " + address + "  "+phone+"  "
	       +deviceid+"<br/>" +"<a href=\"patientArrayList\">View All Patient</a>" +
	       "</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
