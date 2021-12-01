<!-- Header -->
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="commonHeader.jsp"></jsp:include>

<!-- Side Menu -->
<jsp:include page="commonSideMenu.jsp"></jsp:include>

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
						<h4 class="page-title">REQUEST DECLINE INFORMATION</h4>
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
							<div class="table-responsive">
								<table class="table table-centered table-striped"
									id="requestDeclineDataTable">
									<thead>
										<tr>
											<th>#</th>
											<th>Request Type</th>
											<th>Decline By</th>
											<th>Reason</th>
											<th>Request Detail</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
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
		<!-- End Container -->
		
		<!-- Add Request Decline Reason Modal Start -->
		<a href="#requestDeclineReason" id="forRequestDeclineReason" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="requestDeclineReason" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title"></i>Edit Request Decline Reason</h4>
			<div class="custom-modal-text text-left">
				<form action="vendorEditDeclineRequest" method="post" id="declineRequest">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="requestDeclineID" id="requestDeclineID" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">Reason</label> 
								<textarea class="form-control" id="declineReason" name="declineReason" placeholder="Give Reason For Decline Request"></textarea>
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fe-edit"></i> Decline Request</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Add Request Decline Reason Modal End -->
		
		<!-- Need Food Request Detail Modal Start -->
		<a href="#needFoodRequestDetail" id="forNeedFoodRequestDetail" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="needFoodRequestDetail" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title"> Need Food Request Detail </h4>
			<div class="custom-modal-text text-left">
				<div class="table-responsive">
					<table class="table table-centered table-borderless table-striped mb-0">
						<tbody id="needFoodRequestDetailBody">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- Need Food Request Detail Modal End -->
		
		<!-- Extra Food Request Detail Modal Start -->
		<a href="#extraFoodRequestDetail" id="forExtraFoodRequestDetail" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="extraFoodRequestDetail" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title"> Extra Food Request Detail </h4>
			<div class="custom-modal-text text-left">
				<h4 class="header-title text-uppercase bg-light p-2 mt-0 mb-3">
					<i class="fa fa-info-circle" aria-hidden="true"></i>
					Food Item Detail
				</h4>
				<div class="table-responsive">
					<table class="table table-centered table-borderless table-striped mb-0">
						<thead>
							<tr>
								<th>Food Item</th>
								<th>Item Quantity</th>
							</tr>
						</thead>
						<tbody id="foodItemDetail">
							
						</tbody>
					</table>
				</div>
				<br>
				<h4 class="header-title text-uppercase bg-light p-2 mt-0 mb-3">
					<i class="fa fa-info-circle" aria-hidden="true"></i>
					Other Detail
				</h4>
				<div class="table-responsive">
					<table class="table table-centered table-borderless table-striped mb-0">
						<tbody id="otherDetail">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- Extra Food Request Detail Modal End -->
	</div>
</div>

<!-- Footer -->
<jsp:include page="commonFooter.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/common/userRequestDeclineList.js"></script>