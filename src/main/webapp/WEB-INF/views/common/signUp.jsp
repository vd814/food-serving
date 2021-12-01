<!DOCTYPE html>
<%@page import="com.swing.foodserving.constant.Constant"%>
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

<!-- App css -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/app.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/css/pnotify.custom.min.css" rel="stylesheet" type="text/css" />
<link href="<%=Constant.BASEURL%>assets/libs/flatpickr/flatpickr.min.css" rel="stylesheet" type="text/css" /> 
<style type="text/css">
body.authentication-bg-pattern {
	background-image: none !important;
	background-color: #fff !important;
}

.custom-checkbox .custom-control-input:checked ~.custom-control-label::before
	{
	background-color: #1abc9c;
}

.custom-control-input:checked ~.custom-control-label::before {
	color: #1abc9c;
	background-color: #1abc9c;
}
.error{
	color:red;
	width: 100%;
}  

element.style {
}
.ml-1, .mx-1 {
    margin-left: .375rem!important;
}
a {
    color: #1abc9c;
}
</style>
</head>

<body class="authentication-bg authentication-bg-pattern">
	<div class="account-pages mt-3 mb-3">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-8 col-lg-6 col-xl-5">
					<div class="card bg-pattern">

						<div class="card-body p-4"
							style="box-shadow: 0px 0px 1px 1px #b9bcbf;">

							<div class="text-center w-75 m-auto">
								<a href="">
									<h2>
										Food Serving
									</h2>
								</a>
								<p class="text-muted mb-4 mt-3">Create Your Account</p>
							</div>
											
								<%	
								Object sucessMsg = request.getAttribute("sucessMsg");
								if (sucessMsg != null) {
									%>
									  <div class="alert alert-success"  >
		  								<strong>Success! </strong><%=sucessMsg%>
									  </div>
									<%
								}
								%>

								
                                <%
                                	if(request.getParameter("failed")!= null){
                                	String errMsg=(String)session.getAttribute("errorMsg");
                                	session.removeAttribute("errorMsg");
                                %>
                                <div class="alert alert-danger" role="alert">
                                	<i class="mdi mdi-block-helper mr-2"></i> <%=errMsg%>
                                </div>
                                <%}%>

							<form action="registerUser" method="post" id="signInNow">

								<div class="form-group mb-3">
									<label for="roleFound">Join As</label>
									<select name="role" class="form-control" id="role">
										<option value="" selected="selected">Join As</option>
										<option value="2">Join As Vendor</option>
										<option value="3">Join As User</option>
									</select> 
								</div>
								
								<div class="form-group mb-3">
									<label for="emailaddress">Email address</label> <input
										class="form-control" type="text" id="email"
										name="email" placeholder="Enter your email">
								</div>
								
								<div class="form-group mb-3">
									<label for="userName">UserName</label> <input
										class="form-control" type="text" id="userName"
										name="userName" placeholder="Enter your UserName">
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="password">Password</label> <input
												class="form-control" type="password" id="password"
												name="password" placeholder="Enter your password">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group mb-3">
											<label for="password">Re-Enter Password</label> <input
												class="form-control" type="password" id="rePassword"
												name="rePassword" placeholder="Enter your password Again">
										</div>
									</div>
								</div>
								
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" id="_csrf"/>
								

								<div class="form-group mb-0 text-center">
									<button class="btn btn-success btn-block" type="submit">
										Register</button>
								</div>
								
							</form>
							<div class="col-12 text-center mt-3">
								<div class="row">
									<div class="col-md-12">
										If You have Account<a href="<%=Constant.BASEURL %>signIn" class=" ml-1"> Click here</a>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 mt-2">
										Back To Home<a href="<%=Constant.BASEURL %>aboutUs" class=" ml-1"> Click here</a>
									</div>
								</div>
							</div>
						</div>
						
						<!-- end card-body -->
					</div>
					<!-- end card -->

					
					<!-- end row -->

				</div>
				<!-- end col -->
			</div>
			<!-- end row -->
		</div>
		<!-- end container -->
	</div>
	<!-- end page -->


	<!-- <footer class="footer footer-alt">
		Copyright &copy; 2020 
	</footer> -->

	<!-- Vendor js -->
	<script src="assets/js/vendor.min.js"></script>

	<!-- App js -->
	<script src="assets/js/app.min.js"></script>

</body>
<script src="<%=Constant.BASEURL%>assets/js/vendor.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/js/jquery.validate.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/js/jquery.cookie.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/flatpickr/flatpickr.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/js/common/signUp.js"></script>
<script src="<%=Constant.BASEURL%>assets/js/pnotify.custom.min.js"></script>

<script>
var msgStatus = "<%= session.getAttribute("msgStatus")%>";
var msg = "<%= session.getAttribute("msg")%>";
<%
session.removeAttribute("msg");
session.removeAttribute("msgStatus");
%>

function notify(type,title,msg){
	new PNotify({
	    title: title,
	    text: msg,
	    type: type,
	    delay: 3000,
	});
}

console.log(msg);
console.log(msgStatus);
if(msgStatus=="null"){
	
	if(msg != "null"){
		notify("success","Success!",msg);
	}
}
else{
	if(msgStatus==0)
		{
			notify("success","Success!",msg);
		}
	else if(msgStatus==1)
		{
			notify("error","Oh No!",msg);
		}
	
}


</script>

</html>