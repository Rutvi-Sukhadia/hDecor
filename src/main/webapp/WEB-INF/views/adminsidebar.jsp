<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.hdecor.model.Admin"%>

<!DOCTYPE html>
<html>
<body>
<% if(session.getAttribute("admin")==null){
	response.sendRedirect("error");}%>
<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar" style="background:#222d32">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
				<% Admin admin = (Admin)session.getAttribute("admin"); %> 
					<div class="pull-left image">
						<img src="/hdecorAdmin/resources/images/profilePicture/${admin.profilePicture}" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						 <center><label style="color:white;font-size:15px;">
		                         <% out.println(admin.getFname()); %>  
		                         <% out.print(admin.getLname()); %>
		                         </label></center>
						<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>
				<!-- search form -->
				<!-- <form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form> -->
				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header"><center><h4>MAIN NAVIGATION</h4></center></li>
					
					<li><a href="/hdecorAdmin/admin/home"><i
							class="fa fa-home"></i><span>Home Page</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					<!-- Manage  profile-->
					<li class="active treeview menu-open"><a href="#"> <i
							class="fa fa-user"></i> <span>Manage Profile</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
					 <ul class="treeview-menu">
					<li><a href="/hdecorAdmin/admin/editProfile"><i class="fa fa-circle-o"></i>Edit Profile</a></li>
					<li><a href="/hdecorAdmin/admin/changePassword"><i class="fa fa-circle-o"></i>Change Password</a></li>
							
					</ul></li>
					<!-- Manage Admin -->
					<li><a href="/hdecorAdmin/admin/adminList"><i
							class="fa fa-users"></i><span>Manage Admins</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					<!-- Manage Users -->
					<li><a href="/hdecorAdmin/user/userList"> <i class="fa fa-users"></i>
							<span>Manage Users</span> <span class="pull-right-container">
						</span>
					</a></li>				
					
					<!-- Manage Vehicles -->
					<li><a href="/hdecorAdmin/product/productList"> <i
							class="fa fa-bed"></i> <span>Manage Products</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					<!-- Manage Drivers -->
					<li><a href="/hdecorAdmin/review/reviewList"> <i
							class="fa fa-users"></i> <span>Manage Reviews</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					<!-- Manage Filters -->
					<li class="treeview"><a href="#"> <i
							class="fa fa-filter"></i> <span>Manage Filters</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="/hdecorAdmin/category/categoryList"><i
									class="fa fa-circle-o"></i> Manage Categories</a></li>
							<li><a href="/hdecorAdmin/subcategory/subcategoryList"><i
									class="fa fa-circle-o"></i> Manage Sub-Categories</a></li>
							<li><a href="/hdecorAdmin/color/colorlist"><i
									class="fa fa-circle-o"></i> Manage Colors</a></li>
							<li><a href="/hdecorAdmin/material/materiallist"><i
									class="fa fa-circle-o"></i> Manage Materials</a></li>
							
						</ul></li>
						
					
					<!-- Manage Locations -->
					<li class="treeview"><a href="#"> <i
							class="fa fa-map-marker "></i> <span>Manage Locations</span> <span
							class="pull-right-container"> <i
								class="fa fa-angle-left pull-right"></i>
						</span>
					</a>
						<ul class="treeview-menu">
						
							<li><a href="/hdecorAdmin/state/statelist"><i
									class="fa fa-circle-o"></i> Manage States</a></li>
							<li><a href="/hdecorAdmin/city/cityList"><i
									class="fa fa-circle-o"></i> Manage Cities</a></li>
							<li><a href="/hdecorAdmin/area/areaList"><i
									class="fa fa-circle-o"></i> Manage Areas</a></li>
						</ul></li>
					<!--   View Orders-->
					<li><a href="/hdecorAdmin/order/orderList"> <i
							class="fa fa-shopping-cart"></i> <span>View Orders</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					<!-- Manage Feedback -->
					<li><a href="/hdecorAdmin/feedback/feedbackList"> <i
							class="fa fa-edit"></i> <span>Manage Feedbacks</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					<!-- Generate Report -->
					
					
					
					<!-- Log Out -->
					<li><a href="/hdecorAdmin/admin/logout"> <i
							class="fa fa-power-off"></i> <span>Log Out</span> <span
							class="pull-right-container">
						</span>
					</a></li>
					
					
			</section>
			<!-- /.sidebar -->
		</aside>

</body>
</html>