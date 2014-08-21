<%@ page
	import="javax.servlet.ServletRequest.*,java.util.*, com.fallalarm.web.data.*"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	int id;
	String firstName;
	String lastName;
	String address;
	String phone;
	int device_id;

	ArrayList<Patient> patientList = (ArrayList<Patient>) request
			.getAttribute("patientArrayList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>view all patients</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div
		style="background-image:url(${pageContext.request.contextPath}/resources/image/Senior.png); margin-left:80px; width:1200px;height:200px;">
		<div style="margin-left: 50px; margin-top: 10px;">
			<br />
			<br />
			<h1>Add New Patient</h1>
		</div>
		<div style="margin-left: 120px; margin-top: 100px; float: left">
			<ul>
				<li><a href="/jsp/index595.jsp">Home</a></li>
				<li><a href="patientList">View All Patient</a></li>
				<li><a href="/html/NewPatient.html">Add New Patient</a></li>
				<li><a href="/jsp/Send.jsp">Send Email</a></li>
			</ul>
		</div>
	</div>
	<hr class="style-one">
	</br>
	</br>
	<div style="background-color: white; margin-left: 80px; width: 1200px;">
		<br /> <br />
		<fieldset style="margin-left: 150px; width: 900px;">
			<legend style="font-size: 20px;">List of all patients:</legend>

			<%
				if (patientList == null || patientList.size() == 0) {
			%>
			No courses available at this time
			<%
				} else {
			%>




			<table border="1"
				style="background-color: #F1F1FF; color: DarkBlue; font-size: 15px">

				<tr style="background-color: #B6B6CC">
					<th>Patient ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Phone Number</th>
					<th>Device ID</th>
				</tr>
				<%
					for (Patient curPatient : patientList) {
							id = curPatient.getID();
							firstName = curPatient.getFirstName();
							lastName = curPatient.getLastName();
							address = curPatient.getAddress();
							phone = curPatient.getPhone();
							device_id = curPatient.getDeviceID();
				%>
				<tr>
					<td><%=id%></td>
					<td><%=firstName%></td>
					<td><%=lastName%></td>
					<td><%=address%></td>
					<td><%=phone%></td>
					<td><%=device_id%></td>

					<%
						}
					%>
					<%
						}
					%>
				</tr>
			</table>
		</fieldset>
	</div>
	<div style="margin-left: 50%;">
		<h4>Servlet JSP JDBC HTML CSS JavaScript</h4>


	</div>
</body>
</html>