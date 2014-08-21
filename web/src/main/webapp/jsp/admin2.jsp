<%@ page import="javax.servlet.ServletRequest.*,java.util.*,com.fallalarm.web.data.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>admin home</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body >
<div style="background-image:url(${pageContext.request.contextPath}/resources/image/Senior.png); margin-left:80px; width:1200px;height:200px;" >
<div style="margin-left:50px; margin-top:10px;">
<br/><br/>
<h1>Add New Patient </h1>
</div>
<div style="margin-left:120px; margin-top:100px; float: left;">
<ul> <li><a href="/jsp/index595.jsp">Home</a></li>
<li><a href="patientList">View All Patient</a></li>
<li><a href="html/NewPatient.html">Add New Patient</a></li>
<li><a href="jsp/Send.jsp">Send Email</a></li>
<li><a href="html/index.html">Home</a></li>
</ul>
</div>
</div>
<hr class="style-one">
<br/>
<br/>
<table class="title" style="float:none;">
  <tr><th>Administrator Home</th></tr>
</table>
<h1>Hello
<%=request.getAttribute("name") %>
</h1> <br><br>
<% Admin newadmin=(Admin)request.getAttribute("Admin"); %>
<%if (newadmin.getFname()==""){ %>
This name is not in Administrator list.
<a href="index595.html">Go back to administrator log in page!</a><br/><br/>
<%} else {%>

<br><br>
<fieldset style="margin-left:200px; width:500px;">
<legend><h3>Administrator Information</h3></legend>
<br/>
Administrator First Name: <%=newadmin.getFname()%>  <br>
Administrator Last Name : <%=newadmin.getLname() %><br>
Administrator ID        : <%=newadmin.getPassword() %><br>
Administrator Role      : <%=newadmin.getRole() %><br>

</fieldset>
<%} %>
</body>
</html>