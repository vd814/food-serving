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
						<h4 class="page-title">Complain Detail</h4>
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
									id="complainDatatable">
									<thead>
										<tr>
											<th>#</th>
											<th>Request Type</th>
											<th>Complain By</th>
											<th>Complain</th>
											<th>Your Replay</th>
											<th>Admin Replay</th>
											<th>Action</th>
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
		
		<!--add Replay Modal Start-->
		<a href="#addReplayModal" id="forAddReplayModal" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="addReplayModal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Add Your Replay</h4>
			<div class="custom-modal-text text-left">
				<form action="addReplayByVendor" method="post" id="addReplayByVendor">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="complainID" id="complainID" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Replay</label> <input
									type="text" class="form-control" id="replay" name="replay"
									placeholder="Add Your Replay">
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fa fa-plus-circle"></i> Add Replay</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--add Replay Modal End  -->
		
		<!--edit Replay Modal Start-->
		<a href="#editReplayModal" id="forEditReplayModal" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="editReplayModal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Edit Your Replay</h4>
			<div class="custom-modal-text text-left">
				<form action="editReplayByVendor" method="post" id="editReplayByVendor">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="editComplainID" id="editComplainID" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Replay</label> <input
									type="text" class="form-control" id="editReplay" name="editReplay"
									placeholder="Add Your Replay">
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fa fa-edit"></i> Edit Replay</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--edit Replay Modal End  -->
	</div>
</div>

<!-- Footer -->
<jsp:include page="commonFooter.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/common/vendorComplainList.js"></script>