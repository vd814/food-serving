<!-- Header -->
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="header.jsp"></jsp:include>

<!-- Side Menu -->
<jsp:include page="sideMenu.jsp"></jsp:include>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->

<div class="content-page">

	<div class="content">

		<!-- Start Content-->
		<div class="container-fluid">

			<!-- start page title -->
			<div class="row">
				<div class="col-12">
					<div class="page-title-box">
						<div class="page-title-right">
							<a href="#add-taluka-modal"
								class="btn btn-success waves-effect waves-light financial-year-non-updateble"
								data-animation="fadein" data-plugin="custommodal"
								data-overlaycolor="#38414a"> <i
								class="mdi mdi-plus-circle mr-1"></i> Button
							</a>
						</div>
						<h4 class="page-title">Title Goes Here</h4>
					</div>
				</div>
			</div>
			<!-- end page title -->

			<!-- Start Page Content -->
			<div class="row">
				<!-- Start col-->
				<div class="col-12">
					<!-- Start card -->
					<div class="card">
						<!-- Start card body-->
						<div class="card-body">
						
						</div>
						<!-- end card body-->
					</div>
					<!-- end card -->
				</div>
				<!-- end col-->
			</div>
			<!-- End Page Content -->
				
		</div>
		<!-- End Container -->

	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/admin/reviewRatingList.js"></script>