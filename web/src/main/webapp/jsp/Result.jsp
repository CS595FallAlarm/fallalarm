<%@ page import="javax.servlet.ServletRequest.*,java.util.*"
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div
		style="background-image:url(${pageContext.request.contextPath}/resources/image/Senior.png); margin-left:80px; width:1200px;height:200px;">
		<div style="margin-left: 50px; margin-top: 10px;">
			<br />
			<br />
			<h1>Send Email Result</h1>
		</div>
		<div style="margin-left: 120px; margin-top: 100px;">
			<ul>
				<li><a href="jsp/index595.jsp">Home</a></li>
				<li><a href="patientList">View All Patient</a></li>
				<li><a href="html/NewPatient.html">Add New Patient</a></li>
				<li><a href="jsp/Send.jsp">Send Email</a></li>

			</ul>
		</div>
	</div>
	<h3><%=request.getAttribute("Message")%></h3>

</body>
</html>
