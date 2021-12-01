<!-- Header -->
<%@page import="com.swing.foodserving.utility.UserUtil"%>
<%@page import="com.swing.foodserving.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="header.jsp"></jsp:include>

<!-- Side Menu -->
<jsp:include page="sideMenu.jsp"></jsp:include>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->
<% List<User> vendorList = (List<User>)request.getAttribute("vemdorList"); %>
<div class="content-page">

	<div class="content">

		<!-- Start Content-->
		<div class="container-fluid">

			<!-- start page title -->
			<div class="row">
				<div class="col-12">
					<div class="page-title-box">
						<div class="page-title-right">
							<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>adminExtraFoodRequest"
								class="btn btn-success waves-effect waves-light financial-year-non-updateble"> 
								<i class="mdi mdi-plus-circle mr-1"></i> Extra Food Request
							</a>
							<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>adminNeedFoodRequest"
								class="btn btn-success waves-effect waves-light financial-year-non-updateble"> 
								<i class="mdi mdi-plus-circle mr-1"></i> Need Food Request
							</a>
						</div>
						<h4 class="page-title">REQUEST INFORMATION</h4>
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
									id="requestDataTable">
									<thead>
										<tr>
											<th>#</th>
											<th>Request Type</th>
											<th>User</th>
											<th>Vendor</th>
											<th>Status</th>
											<th>Assign Request</th>
											<th>Cancel Request</th>
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
		
		<!-- Request Assign Modal Start -->
		<a href="#assignRequestModal" id="assignRequest" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="assignRequestModal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title"><i class="fe-plus-circle"></i> Assign Request</h4>
			<div class="custom-modal-text text-left">
				<form action="assignRequest" method="post" id="addUser">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="eMail" class="control-label">Vendor List</label> 
								<select
								class="form-control" id="vendorID"
								name="vendorID">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<%for(User user : vendorList){ %>
										<option value="<%=user.getUserId() %>"><%=user.getName() %></option>
									<%} %>
								</select>
							</div>
						</div>
					</div>
					
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="requestID" id="requestID" />
					
					<div class="text-right">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fe-save"></i> Assign</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Request Assign Modal End  -->
		
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
		
		<!-- Add Request Cancel Reason Modal Start -->
		<a href="#requestCancelReason" id="forRequestCancelReason" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="requestCancelReason" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Give Reason For Cancel Request</h4>
			<div class="custom-modal-text text-left">
				<form action="requestCancel" method="post" id="cancelRequest">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="requestID" id="requestIdForCancelRequest" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label class="control-label">Reason</label> 
								<textarea class="form-control" id="reason" name="reason" placeholder="Give Reason For Cancel Request"></textarea>
							</div>
						</div>
					</div>
					<div class="text-center">
						<button type="submit"
							class="btn btn-danger waves-effect waves-light "><i class="fa fa-times"></i> Cancel Request</button>
					</div>
				</form>
			</div>
		</div>
		<!-- Add Request Cancel Reason Modal End -->
		
		<!-- Request Cancel Detail Modal Start -->
		<a href="#requestCancelDetail" id="forRequestCancelDetail" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="requestCancelDetail" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Request Cancel Detail</h4>
			<div class="custom-modal-text text-left">
				<div class="table-responsive">
					<table class="table table-centered table-borderless table-striped mb-0">
						<tbody id="requestCancelDetailTable">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- Request Cancel Detail Modal End -->
	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
<script>
	var userRole = <%=UserUtil.getUser(request.getSession()).getRole() %>
	var admin = <%=Constant.ROLE_ADMIN %>
	var vendor = <%=Constant.ROLE_VENDOR %>
	var user = <%=Constant.ROLE_PUBLIC %>
</script>
<script src="<%=Constant.BASEURL%>assets/js/custom/admin/requestList.js"></script>