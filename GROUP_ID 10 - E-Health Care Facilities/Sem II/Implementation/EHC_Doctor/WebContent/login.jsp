<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="Assets/CSS/style.css">
 	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
 	 	
 	<!--  link rel="stylesheet" href="libs/bootstrap.min.css">-->
 	<script type="text/javascript" src="libs/jquery.min.js"></script>
    <script type="text/javascript" src="libs/underscore-min.js"></script>
    <script type="text/javascript" src="libs//backbone-min.js"></script>

	<script type="text/javascript" src = "Views/loginview.js" > </script>
	<script type="text/javascript" src = "Views/regview.js" > </script>
	<script type="text/javascript" src = "Views/homeview.js" > </script>
	<script type="text/javascript" src = "Views/patientRecview.js" > </script>
	<script type="text/javascript" src = "Views/updateview.js" > </script>
	<script type="text/javascript" src = "Views/appointmentview.js" > </script>
	
	<script type="text/javascript" src = "Models/usermodel.js" > </script>
	<script type="text/javascript" src = "Models/prescriptionmodel.js" > </script>
	<script type="text/javascript" src = "Models/doctor.js" > </script> 
	<script type="text/javascript" src = "Models/updateDoctorModel.js" > </script>
	<script type="text/javascript" src = "Models/patientmodel.js" > </script>
	<script type="text/javascript" src = "Models/appointment.js" > </script>
	<script type="text/javascript" src = "Models/ReportModel.js" > </script>
	
	<script type="text/javascript" src = "JS/app.js" > </script> 
</head>
<body>

<div id = "main">

	
</div>
</body>

	<jsp:include page="Templates/regtempl.html" />
	<jsp:include page="Templates/home.html" />
	<jsp:include page="Templates/updateTemp.html" />
	<jsp:include page="Templates/Appointment.html" />
	<jsp:include page="Templates/PatientRec.html" />
	<jsp:include page="Templates/loginTemp.html" />
	<jsp:include page="Templates/NavTemp.html" />
</html>

