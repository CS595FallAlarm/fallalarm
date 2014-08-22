package com.fallalarm.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.fallalarm.web.dao.DBConn;
import com.fallalarm.web.dao.PatientDAO;
import com.fallalarm.web.data.PatientMedication;

/**
 * Servlet implementation for Patient Medication
 */
@WebServlet("/patient/medication")
public class PatientMedicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConn newdbConn = new DBConn();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientMedicationServlet() {
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
		PatientMedication patientMed = patientDAO.getPatientMedication(Integer
				.parseInt(patientId));
		ObjectMapper mapper = new ObjectMapper();
		String patientMedJson = mapper.writeValueAsString(patientMed);
		response.getWriter().write(patientMedJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String patientId = request.getParameter("patientId"); // number
		String medication = request.getParameter("medication"); // Yes or No
		PatientDAO patientDAO = new PatientDAO();
		PatientMedication patientMed = new PatientMedication();
		patientMed.setPatientId(Integer.parseInt(patientId));
		patientMed.setMedication(medication.equalsIgnoreCase("YES") ? true
				: false);
		patientDAO.updatePatientMedication(patientMed);
	}

}
