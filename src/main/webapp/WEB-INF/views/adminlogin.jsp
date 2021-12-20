<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>hDecor Admin</title>
<link rel="shortcut icon"
	href="/hdecorAdmin/resources/images/hdecor_logo/favicon-01.png">
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
<body class="hold-transition login-page">
	<div class="login-box">
		<img src="/hdecorAdmin/resources/images/hdecor_logo/logo-01.png"
			style="width: 350px; height: 150px;" />
		<!--  <div class="login-logo">
    <a href="#"><b>hDecor</b> hDECOR</a>
  </div>
  /.login-logo
  -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in to start your session</p>
			<%
			if (request.getParameter("errorMsg") != null) {
			%>
			<div style="color: red;"><%=request.getParameter("errorMsg")%></div>
			<%
			}
			%>
			<form method="post" action="/hdecorAdmin/admin/loggingIn"
				modelAttribute="log">


				<div class="form-group has-feedback">
					<input type="email" class="form-control" placeholder="Email ID"
						name="emailId" id="emailId" path="emailId" /> <span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" placeholder="Password"
						name="password" path="password" id="password" /> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<!-- <div class="checkbox icheck">
            <label>
              <input type="checkbox" path="rememberme" id="rememberme">Remember Me
            </label>
          </div>  -->
				<div class="row">
					<div class="col-xs-8"></div>
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">Login</button>
					</div>
					<!-- /.col -->
				</div>
			</form>


			<!-- <a href="/hdecorAdmin/admin/forgotPassword">I forgot my password</a><br> -->


		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

	<!-- jQuery 3 -->
	<script
		src="/hdecorAdmin/resources/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap 3.3.7 -->
	<script
		src="/hdecorAdmin/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- iCheck -->
	<script src="/hdecorAdmin/resources/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' /* optional */
			});
		});
	</script>
	<!-- Content Wrapper. Contains page content -->
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
</body>


</html>
