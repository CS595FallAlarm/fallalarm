<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send an e-mail</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
	<div
		style="background-image:url(${pageContext.request.contextPath}/resources/image/Senior.png); margin-left:80px; width:1200px;height:200px;">
		<div style="margin-left: 50px; margin-top: 10px;">
			<br />
			<br />
			<h1>Send Email</h1>
		</div>
		<div style="margin-left: 120px; margin-top: 100px;">
			<ul>
				<li><a href="jsp/index595.html">Home</a></li>
				<li><a href="patientList">View All Patient</a></li>
				<li><a href="html/NewPatient.html">Add New Patient</a></li>
				<li><a href="jsp/Send.jsp">Send Email</a></li>
			</ul>
		</div>
	</div>
	<hr class="style-one">
	<div style="background-color: white; margin-left: 300px; width: 700px;">
		<form action="EmailSendingServlet" method="post">
			<table border="0" width="35%" align="center">
				<caption>
					<h2>Send E-mail</h2>
				</caption>
				<tr>
					<td width="50%">Recipient address</td>
					<td><input type="email" name="recipient" size="50" /></td>
				</tr>
				<tr>
					<td>Subject</td>
					<td><input type="text" name="subject" size="50" /></td>
				</tr>
				<tr>
					<td>Content</td>
					<td><textarea rows="10" cols="39" name="content"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Send" /></td>
				</tr>
			</table>

		</form>
	</div>
</body>
</html>