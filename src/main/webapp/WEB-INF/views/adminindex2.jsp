<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

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
<!-- DataTables -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<!-- jvectormap -->
<link rel="stylesheet"
	href="/hdecorAdmin/resources/bower_components/jvectormap/jquery-jvectormap.css">
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
<style>
.wrapper, .skin-blue .main-sidebar, .skin-blue .left-side {
	background-color: white;
}
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<%
	if (session.getAttribute("admin") == null)
		response.sendRedirect("error");
	%>
	<div class="wrapper">
		<%@include file="header.jsp"%>
		<%@include file="adminsidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<center>
				<img src="/hdecorAdmin/resources/images/hdecor_logo/logo-01.png"
					style="width: 350px; height: 150px;" />
			</center>

			<!-- Content Header (Page header) -->
			<section class="content-header">

				<h1>Dashboard</h1>

			</section>

			<!-- Main content -->
			<section class="content">
				<!-- Info boxes -->
				<div class="row">
					<div class="col-md-17 col-sm-12 col-xs-16">
						<div class="info-box">
							<span class="info-box-icon bg-aqua"><i
								class="ion ion-ios-people-outline"></i></span>

							<div class="info-box-content" style="width: 190px;">
								<span class="info-box-text">Registered Users</span> <span
									class="info-box-number">${users}</span> <a
									href="/hdecorAdmin/user/userList">View All Users</a>
							</div>

						</div>
						<!-- /.info-box -->
					</div>

					<!-- /.col -->

					<!-- /.col -->

					<!-- fix for small devices only -->
					<div class="clearfix visible-sm-block"></div>

					<div class="col-md-17 col-sm-12 col-xs-16">
						<div class="info-box">
							<span class="info-box-icon bg-green"><i
								class="icon ion-ios-cart-outline"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">Registered Products</span> <span
									class="info-box-number">${products}</span> <a
									href="/hdecorAdmin/product/productList">View All Products</a>

							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
					</div>
					<div class="col-md-17 col-sm-12 col-xs-16">
						<div class="info-box">
							<span class="info-box-icon bg-orange"><i
								class="icon ion-ios-person-outline"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">Registered Admins</span> <span
									class="info-box-number">${admins}</span> <a
									href="/hdecorAdmin/admin/adminList">View All Admins</a>
							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
					</div>

					<div class="col-md-17 col-sm-12 col-xs-16">
						<div class="info-box">
							<span class="info-box-icon bg-red"><i
								class="ion ion-ios-chatboxes-outline"></i></span>

							<div class="info-box-content">
								<span class="info-box-text">Received Feedbacks</span> <span
									class="info-box-number">${feedbacks}</span> <a
									href="/hdecorAdmin/feedback/feedbackList">View All
									Feedbacks</a>
							</div>
							<!-- /.info-box-content -->
						</div>
						<!-- /.info-box -->
					</div>
					<!-- /.col -->

					<!-- /.col -->
				</div>
				<!-- /.row -->
		</div>

		<%@include file="adminfooter.jsp"%>



	</div>
	<!-- ./wrapper -->

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
	<!-- Sparkline -->
	<script
		src="/hdecorAdmin/resources/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
	<!-- jvectormap  -->
	<script src="plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<!-- SlimScroll -->
	<script
		src="/hdecorAdmin/resources/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<!-- ChartJS -->
	<script src="/hdecorAdmin/resources/bower_components/chart.js/Chart.js"></script>
	<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	<script src="/hdecorAdmin/resources/dist/js/pages/dashboard2.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="/hdecorAdmin/resources/dist/js/demo.js"></script>
</body>
</html>