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
.content-page{
	margin-left: 0px;
}
.footer{
	position: inherit;
}
.topnav-menu{
	font-size: 25px;
}
</style>
</head>

<body style="background-color: #F5FAF2;">
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

				<li class="d-lg-inline-block">
                    <a class="nav-link" href="home1">
                        <span>Home</span>
                    </a>
                </li>
				<li class="d-lg-inline-block">
                    <a class="nav-link" href="aboutUs">
                        <span>About Us</span>
                    </a>
                </li>
				<li class="d-lg-inline-block">
                    <a class="nav-link" href="help">
                        <span>Help</span>
                    </a>
                </li>
				<li class="d-lg-inline-block">
                    <a class="nav-link" href="signIn">
                        <span>Login</span>
                    </a>
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
				</a>
			</div>

			
		</div>
		
		<!-- Topbar End -->
		
