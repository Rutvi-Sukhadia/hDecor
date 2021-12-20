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
						<h3 ><center>
								<font size="8">${product.name}</font>
							</center></h3><br>
					
	
                                    <c:forTokens var="token" items="${product.productPicture}" delims="|">
           
                                    <div class="div1" style="float: left; margin-left: 30px;">
                                    <img width="350px" height="400px" src="/hdecorAdmin/resources/images/productPicture/${token}" alt="Product Picture" /> </div>
                                    </c:forTokens>
                                    
						
						<div class="div2" style="float:left;clear:left;background: white; width:350px; ">
							<br><br>
							<p>
								&nbsp;&nbsp;<b>Category:</b>&nbsp; ${product.subcategoryName}, ${product.categoryName}
								&nbsp; &nbsp; <br>
								<br> &nbsp;&nbsp;<b>Color:</b> &nbsp; ${product.colorName} <br>
								<br> &nbsp;&nbsp;<b>Material:</b>&nbsp;  ${product.materialName}
								&nbsp; &nbsp; &nbsp; &nbsp; <br>
								<br>&nbsp;&nbsp;<b>Rent:</b>&nbsp; ${product.rent}<br>
								<br> &nbsp;&nbsp;<b>Status:</b> &nbsp; ${product.status}<br>
								<br> &nbsp;&nbsp;<b>Description:</b>&nbsp; ${product.description}<br>
								<br>&nbsp;&nbsp;<b>Lender Name:</b><a href="/hdecorAdmin/user/viewUser/${product.userId}"> &nbsp; ${product.username} </a><br>
									
								<br>
							</p>
						</div>
							
								
		
					</div>
			<div class="box-footer box-comments">
										<h3>Reviews</h3>
			<c:forEach var="r" items="${reviewList}">
              <div class="box-comment">
                <!-- User image -->
                <img class="img-circle img-sm" src="/hdecorAdmin/resources/images/userProfilePicture/${r.userProfilePicture}" alt="User Image">

                <div class="comment-text">
                      <span class="username">
                        ${r.userName}
                        <span class="text-muted pull-right">${r.reviewDate}</span>
                      </span><!-- /.username -->
                 ${r.reviewDesc}</div>
                <!-- /.comment-text -->
              </div>
              <!-- /.box-comment -->
              </c:forEach>
                </div>
				</div>
			</div>

			<!-- /.box-header -->
			



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
<%@ include file="adminfooter.jsp"%>
</html>
