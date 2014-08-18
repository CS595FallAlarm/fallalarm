<%@ page import="javax.servlet.ServletRequest.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>index.html</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>
  
    <div align="center">
      <div style="margin-top: 30px; margin-left:auto;font-size:50px;font-family:'Times New Roman';">
          <img src="${pageContext.request.contextPath}/resources/image/logo.png" alt="logo" title="ILG"; width="50" height="50">
        CS535 Capstone FallArm Project, NPU
      </div>  

       <!-- <ul>
  <li><a href="default.asp" style="color:blue;">Home</a></li>
  <li><a href="news.asp" style="color:blue;">News</a></li>
  <li><a href="contact.asp" style="color:blue;">Contact</a></li>
  <li><a href="about.asp" style="color:blue;">About</a></li>
</ul> -->
       <hr class="style-one">


        <div>

             <img src="${pageContext.request.contextPath}/resources/image/Senior-Image.jpg" alt="senior" title="senior";>
        </div>
        <div style ="margin-top:100px; font-size:25px; ">
		
		<form action="adminlogin" method="post">
			<input type='hidden' name='submitted' id='submitted' value='1'/>
        		Username: 	<input type="text" name="username" class="scion-input" value=""> <br />
        		Password :	<input type="password" name="password"  class="scion-input">   <br />  <br />  			
        			<input type="submit" value="Login" class="scion-button">
        		</form>
        </div>
       
   
    </div>
    <div align="center"  style =" margin-bottom:auto;">

        <p>
            Irene Yarui Zhang 9975,    Leo Ninad Thepade 10369,     Mario Ryolzanych 9936,<br />
Peter Sun 10042,            Prakash Ramsuami 9521,         Rice Ruimin Li 10121  <br />

Prepared under the guidance of Dr. Henry Chang and Mr. Michael Wang
Summer, 2014 <br />
Northwestern Polytechnic University, 47671 Westinghouse Dr., Fremont, CA 94539 <br />

        </p>

    </div>
</body>
</html>
