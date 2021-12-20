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
			<br> <br>
			<div class="col-md-12">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">
												Edit Profile
							</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<div class="text-center">
									<%if(request.getParameter("errorMsg") != null){ %>
                                <div style="color: red;"><%= request.getParameter("errorMsg") %></div>
                                <%} %></div>
					<form:form role="form" modelAttribute="admin"
						enctype="multipart/form-data" method="post">
						<div class="box-body">
							<div class="form-group">
								<form:input type="hidden" class="form-control" id="adminId"
								 path="adminId" />
							</div>
							<div class="form-group">
								<label for="exampleInputFile">Profile Picture</label> 
								<div id="dvPreview">
												<img alt="" src="/hdecorAdmin/resources/images/profilePicture/${admin.profilePicture}" style="height:100px;width: 100px;">
											</div>
											<input type="file" id="profilePicture" name="profilePicture"
												path="profilePicture"  />
										
							</div>
							
							<div class="form-group">
								<label for="fname">First Name</label>
								<form:input type="text" class="form-control" id="fname"
									placeholder="First Name" path="fname" />
							</div>
							<div class="form-group">
								<label for="lname">Last Name</label>
								<form:input type="text" class="form-control" id="lname"
									placeholder="Last Name" path="lname" />
							</div>
							<div class="form-group">
								<label for="email">Email address</label>
								<form:input type="email" class="form-control" id="emailId"
									placeholder="Enter email" path="emailId" />
							</div>

							
						</div>
						<!-- /.box-body -->

						<div class="box-footer">
							<center>
									<input class="btn btn-primary" type="submit" value="Update"
										formaction="/hdecorAdmin/admin/updateProfile" />
									<input class="btn btn-primary" type="submit" value="Cancel"
										formaction="/hdecorAdmin/admin/home" />
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
		<script language="javascript" type="text/javascript">
		$(function() {
			$("#profilePicture")
					.change(
							function() {
								if (typeof (FileReader) != "undefined") {
									var dvPreview = $("#dvPreview");
									dvPreview.html("");
									var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.jpg|.jpeg|.gif|.png|.bmp)$/;
									$($(this)[0].files)
											.each(
													function() {
														var file = $(this);
														if (regex
																.test(file[0].name
																		.toLowerCase())) {
															var reader = new FileReader();
															reader.onload = function(
																	e) {
																var img = $("<img />");
																img
																		.attr(
																				"style",
																				"height:100px;width: 100px");
																img
																		.attr(
																				"src",
																				e.target.result);
																dvPreview
																		.append(img);
															}
															reader
																	.readAsDataURL(file[0]);
														} else {
															alert(file[0].name
																	+ " is not a valid image file.");
															dvPreview.html("");
															return false;
														}
													});
								} else {
									alert("This browser does not support HTML5 FileReader.");
								}
							});
		});
	</script>
		
		<%@ include file="adminfooter.jsp"%>
</body>
</html>
