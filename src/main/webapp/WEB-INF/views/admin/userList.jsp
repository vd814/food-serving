<!-- Header -->
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="header.jsp"></jsp:include>

<!-- Side Menu -->
<jsp:include page="sideMenu.jsp"></jsp:include>

<style>
<!--
.error{
	color: red;
}
-->
</style>
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
							<a href="#add-user-modal"
								class="btn btn-success waves-effect waves-light financial-year-non-updateble"
								data-animation="fadein" data-plugin="custommodal"
								data-overlaycolor="#38414a"> <i
								class="mdi mdi-plus-circle mr-1"></i> Add User
							</a>
						</div>
						<h4 class="page-title">USER INFORMATION</h4>
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
									id="userDatatable">
									<thead>
										<tr>
											<th>#</th>
											<th>Name</th>
											<th>User Name</th>
											<th>Email</th>
											<th>Mobile No</th>
											<th>Role</th>
											<th>Status</th>
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
		
		<!-- add User Modal Start-->
		<div id="add-user-modal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title"></i> Add New User</h4>
			<div class="custom-modal-text text-left">
				<form action="addUser" method="post" id="addUser">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Name</label> <input
									type="text" class="form-control" id="name" name="name"
									placeholder="Name">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="groupName" class="control-label">User Name</label> <input
									type="text" class="form-control" id="userName" name="userName"
									placeholder="username">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="password" class="control-label">Password</label> <input
									type="password" class="form-control" id="password" name="password"
									placeholder="Password">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="eMail" class="control-label">E-Mail</label> <input
									type="text" class="form-control" id="mail" name="mail"
									placeholder="E-Mail">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="eMail" class="control-label">Is E-Mail Verified</label> 
								<select
								class="form-control" id="emailVerified"
								name="emailVerified">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Verified</option>
									<option value="2">Unverified</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="mobiel" class="control-label">Mobile No</label> <input
									type="text" class="form-control" id="mobile" name="mobile"
									placeholder="Mobile No">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="mobiel" class="control-label">Is Mobile No Verified</label> 
								<select
								class="form-control" id="mobileVerified"
								name="mobileVerified">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Verified</option>
									<option value="2">Unverified</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="gender" class="control-label">Gender</label> 
								<select
								class="form-control" id="gender"
								name="gender">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Male</option>
									<option value="2">Female</option>
									<option value="3">Other</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="role" class="control-label">Role</label> 
								<select
								class="form-control" id="role"
								name="role">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Admin</option>
									<option value="2">Vendor</option>
									<option value="3">User</option>
								</select>
							</div>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					
					<div class="text-right">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fe-save"></i> Save</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--add User Modal End  -->
		
		<!--edit User Modal Start-->
		<a href="#edit-user-modal" id="referanceHrefForEdit" class="hide"
			data-animation="fadein" data-plugin="custommodal"
			data-overlaycolor="#38414a"> </a>
		<div id="edit-user-modal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Edit User</h4>
			<div class="custom-modal-text text-left">
				<form action="editUser" method="post" id="editUser">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Name</label> <input
									type="text" class="form-control" id="editName" name="editName"
									placeholder="Name">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">User Name</label> <input
									type="text" class="form-control" id="editUserName" name="editUserName"
									placeholder="username">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="eMail" class="control-label">E-Mail</label> <input
									type="text" class="form-control" id="editMail" name="editMail"
									placeholder="E-Mail">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="eMail" class="control-label">Is E-Mail Verified</label> 
								<select
								class="form-control" id="editEmailVerified"
								name="editEmailVerified">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Verified</option>
									<option value="2">Unverified</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<div class="form-group">
								<label for="mobiel" class="control-label">Mobile No</label> <input
									type="text" class="form-control" id="editMobile" name="editMobile"
									placeholder="Mobile No">
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-group">
								<label for="mobiel" class="control-label">Is Mobile No Verified</label> 
								<select
								class="form-control" id="editMobileVerified"
								name="editMobileVerified">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Verified</option>
									<option value="2">Unverified</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="gender" class="control-label">Gender</label> 
								<select
								class="form-control" id="editGender"
								name="editGender">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Male</option>
									<option value="2">Female</option>
									<option value="3">Other</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="role" class="control-label">Role</label> 
								<select
								class="form-control" id="editRole"
								name="editRole">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Admin</option>
									<option value="2">Vendor</option>
									<option value="3">User</option>
								</select>
							</div>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="userID" id="userID" />
					
					<div class="text-right">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fe-save"></i> Save</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--edit User Modal End  -->
	</div>
</div>

<!-- Footer -->
<jsp:include page="footer.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/admin/userList.js"></script>