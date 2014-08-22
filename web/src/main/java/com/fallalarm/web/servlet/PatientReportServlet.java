package com.fallalarm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.fallalarm.web.dao.DBConn;
import com.fallalarm.web.dao.PatientDAO;
import com.fallalarm.web.data.PatientActivity;

/**
 * Servlet implementation for Patient Messages
 */
@WebServlet("/patient/report")
public class PatientReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConn newdbConn = new DBConn();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String patientId = request.getParameter("patientId");
		PatientDAO patientDAO = new PatientDAO();
		List<PatientActivity> activities = patientDAO.getPatientActivities(Integer.parseInt(patientId));
		ObjectMapper mapper = new ObjectMapper();
		String activitiesJson = mapper.writeValueAsString(activities);
		response.getWriter().write(activitiesJson);
	}

}
