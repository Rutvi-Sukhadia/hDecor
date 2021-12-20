<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>hDecor Admin</title>
  <link rel="shortcut icon" href="/hdecorAdmin/resources/images/hdecor_logo/favicon-01.png"">
  <!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/dist/css/AdminLTE.min.css">
<!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/dist/css/skins/_all-skins.min.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

<!-- Google Font -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%@ include file="header.jsp"%>
		<%@ include file="adminsidebar.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<br>
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title"></h3>
						<div class="div1" style="float: left; margin-left: 30px;">
							<img src="/hdecorAdmin/resources/images/userProfilePicture/${user.profilePicture}" id="pic1" style="height: 400px; width:400px;">
						</div>
						<div class="div2" style="background: white; height: 400px;">
							<center>
								<font size="10">User Profile</font>
							</center>
							<br>
				<div class="text-center">
									<%if(request.getParameter("errorMsg") != null){ %>
                                <div style="color: red;"><%= request.getParameter("errorMsg") %></div>
                                <%} %></div>
							<p>
								&nbsp;&nbsp;<b>Name:</b>&nbsp; ${user.fname} ${user.lname} &nbsp; &nbsp; &nbsp;
								&nbsp; <br>
								<br> &nbsp;&nbsp;<b>Email:</b>&nbsp; ${user.emailId }<br>
								<br> &nbsp;&nbsp;<b>Mobile Number:</b>&nbsp; ${user.mobileNo}<br>
								<br> &nbsp;&nbsp;<b>Date Of Birth:</b>&nbsp; ${user.dob}<br>
								<br> &nbsp;&nbsp;<b>Gender:</b>&nbsp; ${user.gender}<br>
								<br> &nbsp;&nbsp;<b>Address:</b>&nbsp; ${user.address}, ${user.areaName}, ${user.cityName}, ${user.stateName}<br>
							
							</p>
							<div style="float: left; margin-left: 150px;">
								&nbsp;&nbsp;
								<center>
									<a href="/hdecorAdmin/user/deleteUser/${user.userId}"><button style="width: 140px; height: 50px;">Delete
											user</button></a>
								</center>
							</div>
							<div style="float: left; margin-left: 150px;">
								&nbsp;&nbsp;
								<center>
									<a href="/hdecorAdmin/product/usersProducts/${user.userId}"><button style="width: 140px; height: 50px;">Listed products</button></a>
								</center>
							</div>
							
						</div>
					</div>
					</div>
				</div>
			</div>
	
				<!-- /.box-header -->
				<!-- Profile Image -->



	<!-- jQuery 3 -->
	<script
		src="/hdecorAdmin/resources/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="/hdecorAdmin/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- FastClick -->
	<script
		src="/hdecorAdmin/resources/bower_components/fastclick/lib/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="/hdecorAdmin/resources/dist/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="/hdecorAdmin/resources/dist/js/demo.js"></script>
	<%@ include file="adminfooter.jsp"%>
</body>
</html>
