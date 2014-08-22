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
import com.fallalarm.web.dao.MessageDAO;
import com.fallalarm.web.data.Message;

/**
 * Servlet implementation for Patient Messages
 */
@WebServlet("/patient/messages")
public class PatientMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBConn newdbConn = new DBConn();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientMessageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nurseId = request.getParameter("nurseId");
		MessageDAO messageDAO = new MessageDAO();
		List<Message> messages = messageDAO.getMessages(Integer.parseInt(nurseId));
		ObjectMapper mapper = new ObjectMapper();
		String messagesJson = mapper.writeValueAsString(messages);
		response.getWriter().write(messagesJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String patientId = request.getParameter("patientId"); // number
		String message = request.getParameter("message"); // message
		
		Message m = new Message();
		m.setPatientId(Integer.parseInt(patientId));
		m.setContent(message);
		MessageDAO messageDAO = new MessageDAO();
		messageDAO.saveMessage(m);
	}

}
