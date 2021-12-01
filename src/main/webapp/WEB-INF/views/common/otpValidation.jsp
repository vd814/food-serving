<!DOCTYPE html>
<%@page import="com.swing.foodserving.constant.Constant"%>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Forgot Password</title>
    <!-- App favicon -->
    <link rel="shortcut icon" href="">

	<!-- App css -->
	<link href="<%=Constant.BASEURL%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" id="bs-default-stylesheet" />
	<link href="<%=Constant.BASEURL%>/assets/css/app.min.css" rel="stylesheet" type="text/css" id="app-default-stylesheet" />
	
	
	
	<!-- icons -->
	<link href="<%=Constant.BASEURL%>/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
		body.authentication-bg-pattern {
			background-image: none !important;
			background-color: #fff !important;
		}
		.card-body{
			box-shadow: 0px 0px 1px 1px #b9bcbf;
		}
		.error{
			color:red;
			width: 100%;
		} 
	</style>
	
	
</head>

<body class="loading authentication-bg authentication-bg-pattern">

    <div class="account-pages mt-5 mb-5">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-pattern">

                        <div class="card-body p-4">
                            
                            <div class="text-center w-75 m-auto">
                                <div class="auth-logo">
                                     <a href="">
										<h2>
											Food Serving
										</h2>
									</a>
                                </div>
                                
                            </div>

                            <form action="#" id="forgotPassword" method="post">

                                <div class="form-group mb-3">
                                    <label for="emailaddress">Email OTP</label>
                                    <input class="form-control"  id="otp"  placeholder="Enter OTP" name="otp">
                                </div>
                                
                                <input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" id="_csrf"/>

                                <div class="form-group mb-0 text-center">
                                    <button class="btn btn-primary btn-block" type="submit"> Reset Password </button>
                                </div>

                            </form>

                        </div> <!-- end card-body -->
                </div>
                <!-- end card -->

                <div class="row mt-3">
                    <div class="col-12 text-center">
                        <p class="">Back to <a href="signIn" class=" ml-1"><b>Log in</b></a></p>
                    </div> <!-- end col -->
                </div>
                <!-- end row -->

            </div> <!-- end col -->
        </div>
        <!-- end row -->
    </div>
    <!-- end container -->
</div>
<!-- end page -->


<footer class="footer footer-alt">
    2020 &copy; <a href="" class="">Food Serving</a> 
</footer>

<!-- Vendor js -->
<script src="<%=Constant.BASEURL%>/assets/js/vendor.min.js"></script>

<!-- App js -->
<script src="<%=Constant.BASEURL%>/assets/js/app.min.js"></script>

<script src="<%=Constant.BASEURL%>assets/js/jquery.validate.min.js"></script>
<script type="text/javascript">
$("#forgotPassword").validate({
	rules: {
		otp:{
			required : true,
			number : true,
			maxlength: 6,
			minlength: 6,
			remote : {
				url: "",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "otp" : function () { return $("#otp").val() },
                } 
			},
		},
	},
	messages: {
		otp:{
			required:"Please enter OTP",
			number : "Enter Number Only",
			maxlength: "Enter 6 Digit OTP",
			minlength: "Enter 6 Digit OTP",
			remote :"OTP Doesn't Match",
 
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});
</script>
</body>
</html>