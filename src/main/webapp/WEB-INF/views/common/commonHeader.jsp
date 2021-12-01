<%@page import="com.swing.foodserving.utility.UserUtil"%>
<%@page import="com.swing.foodserving.entity.User"%>
<%@page import="com.swing.foodserving.constant.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Food Serving</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta
	content="A fully featured admin theme which can be used to build CRM, CMS, etc."
	name="description" />
<meta content="Coderthemes" name="author" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- App favicon -->
<link rel="icon" href="" type="image/png">

<!-- Plugins css -->
<link href="<%=Constant.BASEURL%>assets/libs/flatpickr/flatpickr.min.css" rel="stylesheet" type="text/css" />

<!-- App css -->
<link href="<%=Constant.BASEURL%>assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/css/icons.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/css/app.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/libs/switchery/switchery.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/css/dataTables.bootstrap4.min.css" rel="stylesheet">
<link href="<%=Constant.BASEURL%>assets/css/responsive.bootstrap4.min.css" rel="stylesheet">
<link href="<%=Constant.BASEURL%>assets/css/pnotify.custom.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/libs/custombox/custombox.min.css" rel="stylesheet">
<link href="<%=Constant.BASEURL%>assets/libs/sweetalert2/sweetalert2.min.css"rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/libs/flatpickr/flatpickr.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/css/m.css" rel="stylesheet" type="text/css" />

<style type="text/css">
.error{
	color: red;
}
</style>
</head>

<body style="background-color: #F5FAF2;">
<% User user=UserUtil.getUser(session); %>
	<script>
	var afterLoginBaseUrl="<%=Constant.AFTER_LOGIN_BASEURL%>";
	</script>
	
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" id="_csrf" />
		
	<!-- Begin page -->
	<div id="wrapper">

		<!-- Topbar Start -->
		
		<div class="navbar-custom">
		
			<ul class="list-unstyled topnav-menu float-right mb-0">

				<li class="dropdown notification-list">
					<a class="nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light"
						data-toggle="dropdown" href="#" role="button" aria-haspopup="false"
						aria-expanded="false"> <img
							src="<%=Constant.BASEURL %>assets/images/avatar.jpg" alt="user-image"
							class="rounded-circle"> <span class="pro-user-name ml-1">
								<%=user.getName() %> <i class="mdi mdi-chevron-down"></i>
						</span>
					</a>
					<div class="dropdown-menu dropdown-menu-right profile-dropdown ">
						<!-- item-->
						<div class="dropdown-header noti-title">
							<h6 class="text-overflow m-0">Welcome !</h6>
						</div>

						<!-- item-->
						<a href="" class="dropdown-item notify-item">
							<i class="fa fa-user"></i> <span>My Profile</span>
						</a>
						
						<a href="" class="dropdown-item notify-item">
							<i class="fa fa-lock"></i> <span>Change Password</span>
						</a>

						<div class="dropdown-divider"></div>


						<form name="logoutForm"
							action="<%=request.getContextPath()%>/logout" method="POST">

							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
						</form>

						<!-- item-->
						<a href="javascript: document.logoutForm.submit()"
							class="dropdown-item notify-item"> <i class="fe-log-out"></i>
							<span>Logout</span>
						</a>

					</div>
				</li>

				


			</ul>
			
			<!-- LOGO -->
			<div class="logo-box">
				<a href="" class="logo text-center">
					<span class="logo-lg"> 
						<strong>
							<span style="color: #1abc9c; font-size: 25px;">Food Serving</span>
						</strong> 
					</span> 
					<span class="logo-sm">
						<strong>
							<span style="color: #1abc9c; font-size: 25px;">FS</span>
						</strong>
					</span>
				</a>
			</div>

			<ul class="list-unstyled topnav-menu topnav-menu-left m-0">
				<li>
					<button class="button-menu-mobile waves-effect waves-light">
						<i class="fe-menu"></i>
					</button>
				</li>
			</ul>
		</div>
		
		<!-- Topbar End -->
		
