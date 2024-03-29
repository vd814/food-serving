<!DOCTYPE html>
<%@page import="com.swing.foodserving.constant.Constant"%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Coming Soon | UBold - Responsive Admin Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="A fully featured admin theme which can be used to build CRM, CMS, etc." name="description" />
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="">

		<!-- App css -->
		<link href="<%=Constant.BASEURL%>/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" id="bs-default-stylesheet" />
		<link href="<%=Constant.BASEURL%>/assets/css/app.min.css" rel="stylesheet" type="text/css" id="app-default-stylesheet" />

		
		<!-- icons -->
		<link href="<%=Constant.BASEURL%>/assets/css/icons.min.css" rel="stylesheet" type="text/css" />

    </head>

    <body class="loading authentication-bg authentication-bg-pattern">

        <div class="mt-5 mb-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-12">

                        <div class="text-center">
                           <div class="auth-logo">
                               <a href="" >
									<h2 class="text-white">
										Food Serving
									</h2>
								</a>
                          </div>

                            
                            <h3 class="mt-4 text-white">Stay tunned, we're launching very soon</h3>
                            <p class="text-white-50">We're making the system more awesome.</p>

                            <div class="row mt-5 justify-content-center text-white">
                                <div class="col-md-8">
                                    <div data-countdown="2021/02/13" class="counter-number"></div>
                                </div> <!-- end col-->
                            </div> <!-- end row-->
                        </div> <!-- end /.text-center-->

                    </div> <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- end container -->
        </div>
        <!-- end page -->

        <footer class="footer footer-alt">
           2020 &copy; <a href="" class="text-white-50">Food Serving</a> 
        </footer>


        <!-- Vendor js -->
        <script src="<%=Constant.BASEURL%>/assets/js/vendor.min.js"></script>

        <!-- Plugins js-->
        <script src="<%=Constant.BASEURL%>/assets/libs/jquery-countdown/jquery.countdown.min.js"></script>

        <!-- Countdown js -->
        <script src="<%=Constant.BASEURL%>/assets/js/pages/coming-soon.init.js"></script>

        <!-- App js -->
        <script src="<%=Constant.BASEURL%>/assets/js/app.min.js"></script>
        
    </body>
</html>