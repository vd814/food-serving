<!-- Header -->
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="header.jsp"></jsp:include>

<!-- Side Menu -->
<jsp:include page="sideMenu.jsp"></jsp:include>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->

<div class="content-page">

	<form action="createNeedFoodRequest" method="post" id="createRequest">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="content">
	
			<!-- Start Content-->
			<div class="container-fluid">
	
				<!-- start page title -->
				<div class="row">
					<div class="col-12">	
						<div class="page-title-box">
							<h4 class="page-title">Create Need Food Request</h4>
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
								<h4 class="header-title text-uppercase bg-light p-2 mt-0 mb-3">
									<i class="fa fa-info-circle" aria-hidden="true"></i>
									Person Detail
								</h4>
								<input type="hidden" id="requestType" name="requestType" value="<%=Constant.FOOD_NEED%>">
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-lable">No. Of Person</label>
											<input type="text" class="form-control" 
												id="noOfPerson" name="noOfPerson" placeholder="No. Of Person">						
										</div>
									</div>
								</div>
								
								<h4 class="header-title text-uppercase bg-light p-2 mt-2 mb-3">
									<i class="fa fa-info-circle" aria-hidden="true"></i>
									Other Detail
								</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Description</label> 
											<textarea class="form-control" id="description" name="description" placeholder="Add Description"></textarea>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Address</label> 
											<textarea class="form-control" id="address" name="address" placeholder="Add Address"></textarea>
										</div>
									</div>
									<input type="hidden" name="longitude" id="inputLongitude" >
									<input type="hidden" name="latitude" id="inputLatitude" >
								</div>
								<div class="text-center">
									<a  href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL%>requestList"
										class="btn btn-danger waves-effect waves-light"> <i
										class="fa fa-window-close mr-1"></i> Close
									</a>
									<button type="submit"
										class="btn btn-success waves-effect waves-light">
										<i class="fa fa-plus-circle"></i> Create
									</button>
								</div>
							</div>
							<!-- end card body-->
						</div>
						<!-- end card -->
					</div>
					<!-- end col-->
				</div>
				<!-- End Page Content -->
			</div>
		</div>
	</form>
				
</div>
<!-- End Container -->

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/admin/adminNeedFoodRequest.js"></script>