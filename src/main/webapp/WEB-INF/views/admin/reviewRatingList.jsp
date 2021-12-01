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
						<h4 class="page-title">Review & Rating</h4>
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
									id="reviewRatingDatatable">
									<thead>
										<tr>
											<th>#</th>
											<th>Request Type</th>
											<th>Review By</th>
											<th>Vendor</th>
											<th>Review</th>
											<th>Rating</th>
											<th>action</th>
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
		
		<!--add review & rating Modal Start-->
		<a href="#addReviewRatingModal" id="forAddReviewRatingModal" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="addReviewRatingModal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Give Review & Rating</h4>
			<div class="custom-modal-text text-left">
				<form action="addReviewRating" method="post" id="addReviewRating">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="requestID" id="requestID" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Review</label> <input
									type="text" class="form-control" id="review" name="review"
									placeholder="Review">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Rating</label> <input
									type="number" class="form-control" id="rating" name="rating"
									placeholder="Rating">
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fa fa-plus-circle"></i> Add</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--add review & rating Modal End  -->
		
		<!--edit review & rating Modal Start-->
		<a href="#editReviewRatingModal" id="forEditReviewRatingModal" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="editReviewRatingModal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Give Review & Rating</h4>
			<div class="custom-modal-text text-left">
				<form action="editReviewRating" method="post" id="editReviewRating">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="reviewID" id="reviewID" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Review</label> <input
									type="text" class="form-control" id="editReview" name="editReview"
									placeholder="Review">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Rating</label> <input
									type="number" class="form-control" id="editRating" name="editRating"
									placeholder="Rating">
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fe-edit"></i>Edit</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--edit review & rating Modal End  -->
		
		<!--add Complain Modal Start-->
		<a href="#addComplainModal" id="forAddComplainModal" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="addComplainModal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Add Complain Detail</h4>
			<div class="custom-modal-text text-left">
				<form action="addComplain" method="post" id="addComplain">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="requestIdForComplain" id="requestIdForComplain" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Complain</label> <input
									type="text" class="form-control" id="complain" name="complain"
									placeholder="Complain">
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fa fa-plus-circle"></i>Add Complain</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--add Complain Modal End  -->
		
	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/admin/reviewRatingList.js"></script>