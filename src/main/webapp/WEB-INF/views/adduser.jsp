<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>hDecor Admin</title>
<link rel="shortcut icon"
	href="/hdecorAdmin/resources/images/hdecor_logo/favicon-01.png"">
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
			<br> <br>
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">User Information</h3>
					</div>
					<!-- /.box-header -->
					<div class="text-center">
						<%
						if (request.getParameter("errorMsg") != null) {
						%>
						<div style="color: red;"><%=request.getParameter("errorMsg")%></div>
						<%
						}
						%>
					</div>
					<!-- form start -->
					<form:form role="form" modelAttribute="user"
						enctype="multipart/form-data" method="post">
						<div class="box-body">
							<div class="form-group">
								<form:input type="hidden" class="form-control" id="id"
									placeholder="" path="userId" />
							</div>

							<div class="row">
								<div class="col-lg-6 col-md-12">
									<div class="form-group user">
										<label>First Name</label>
										<form:input type="text" class="form-control bg-gray"
											placeholder="Enter First Name" name="fname" path="fname"
											id="fname" />
										<span id="fname-error"></span>
									</div>
								</div>



								<div class="col-lg-6 col-md-12">
									<div class="form-group user">
										<label>Last Name</label>
										<form:input type="text" class="form-control bg-gray"
											placeholder="Enter Last Name" name="lname" path="lname" />
										<span id="lname-error"></span>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-6 col-md-12">

									<div class="form-group phone">
										<label>Mobile Number</label>
										<form:input type="text" class="form-control bg-gray"
											placeholder="Enter Mobile Number" name="mobileNo"
											path="mobileNo" />
										<span id="mobile-error"></span>
									</div>
								</div>

								<div class="col-lg-6 col-md-12">

									<div class="form-group calender">
										<label>Date Of Birth</label> <br>
										<form:input type="date" class="form-control bg-gray"
											name="dob" path="dob" />
										<span id="dob-error"></span>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label>Gender</label><br>
										<form:radiobutton name="gender" value="Male" path="gender"
											id="male" />
										Male &nbsp; &nbsp; &nbsp;
										<form:radiobutton id="female" name="gender" value="Female"
											path="gender" />
										Female &nbsp; &nbsp; &nbsp;
										<form:radiobutton id="other" name="gender" value="Other"
											path="gender" />
										Other<br> <span id="gender-error"></span>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-lg-6 col-md-12">
									<div class="form-group address">
										<label>Residential Address</label>
										<form:textarea class="form-control bg-gray"
											placeholder="Enter Your Address" name="address"
											path="address" />
										<span id="address-error"></span>
									</div>
								</div>

								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label>State</label>

										<form:select class="form-control bg-gray" path="stateId"
											id="stateId">
											<option value="-1" label="Select State">
												<c:forEach var="s" items="${liststate}">
													<form:option value="${s.stateId}" label="${s.stateName}" />
												</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label>City</label>
										<form:select class="form-control bg-gray" path="cityId"
											id="cityId">
											<c:forEach var="c" items="${listcity}">
												<form:option value="${c.cityId}" label="${c.cityName}" />
											</c:forEach>
										</form:select>
									</div>
								</div>

								<div class="col-lg-6 col-md-12">
									<div class="form-group">
										<label>Area</label>
										<form:select class="form-control bg-gray" path="areaId"
											id="areaId">

											<c:forEach var="a" items="${listarea}">
												<form:option value="${a.areaId}" label="${a.areaName}" />
											</c:forEach>
										</form:select>
									</div>
								</div>

							</div>
							<div class="form-group phone">
								<label for="photo">Upload Photo</label> <input type="file"
									class="form-input" name="profileImage" id="profileImage">
							</div>
							<div class="form-group email">
								<label for="exampleInputEmail2">Email address</label>
								<form:input id="exampleInputEmail2" type="email" name="emailId"
									path="emailId" class="form-control bg-gray"
									placeholder="Enter Email Address" />
								<span id="email-error"></span>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<center>
									<input class="btn btn-primary" type="submit" value="Submit"
										formaction="/hdecorAdmin/user/saveUser" /> <input
										class="btn btn-primary" type="submit" value="Cancel"
										formaction="/hdecorAdmin/user/userList" />
								</center>
							</div>
					</form:form>
				</div>

			</div>
		</div>


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
