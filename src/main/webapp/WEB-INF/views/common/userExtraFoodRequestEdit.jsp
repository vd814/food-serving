<!-- Header -->
<%@page import="net.sf.json.JSONObject"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="com.swing.foodserving.entity.Request"%>
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="commonHeader.jsp"></jsp:include>

<!-- Side Menu -->
<jsp:include page="commonSideMenu.jsp"></jsp:include>

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->
<% Request foodRequest = (Request)request.getAttribute("request"); %>
<div class="content-page">

	<form action="<%=Constant.AFTER_LOGIN_BASEURL %>editExtraFoodRequest" method="post" id="createRequest">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="content">
	
			<!-- Start Content-->
			<div class="container-fluid">
	
				<!-- start page title -->
				<div class="row">
					<div class="col-12">	
						<div class="page-title-box">
							<h4 class="page-title">Edit Extra Food Request</h4>
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
									Food Item Detail
								</h4>
								<input type="hidden" id="foodItemData" name="foodItemData" value="<%=foodRequest.getRequestData() %>">
								<input type="hidden" id="requestID" name="requestID" value="<%=foodRequest.getrID() %>">
								<div class="table-responsive" id="foodItemTable">
									<table class="table table-borderless table-striped mb-0">
										<thead>
											<tr>
												<th>Food Item</th>
												<th>Item Quantity</th>
												<th>Item Unit</th>
												<th style="width: 20%"></th>
											</tr>
										</thead>
										<tbody id="foodItem">
											
												
												<% JSONArray itemList = JSONArray.fromObject(foodRequest.getRequestData()); 
													int itemCount = 0;	
												%>
												<% for(int i = 0; i < itemList.size(); i++){ 
														JSONObject item = JSONObject.fromObject(itemList.get(i));
												%>
												<tr class="item">
													<td>
														<input class="form-control" type="text" name="foodItem<%=itemCount %>" id="foodItem<%=itemCount %>" value="<%=item.get("itemName") %>">
													</td>
													<td>
														<input class="form-control" type="text" name="itemQuantity<%=itemCount %>" id="itemQuantity<%=itemCount %>" value="<%=item.get("itemQuantity") %>">
													</td>
													<td>
														<select class="form-control" name="itemUnit<%=itemCount %>" id="itemUnit<%=itemCount %>">
															<option value="" selected="selected">Select Item Unit</option>
															<% if(item.get("itemUnit").equals("Kg")){ %>
															<option value="Kg" selected="selected">Kg</option>
															<option value="pcs" >pcs</option>
															<option value="ltr" >ltr</option>
															<%}else if(item.get("itemUnit").equals("pcs")) {%>
															<option value="Kg">Kg</option>
															<option value="pcs" selected="selected">pcs</option>
															<option value="ltr" >ltr</option>
															<%}else{ %>
															<option value="Kg">Kg</option>
															<option value="pcs" >pcs</option>
															<option value="ltr" selected="selected">ltr</option>
															<%} %>
														</select>
													</td>
												</tr>											
												<%itemCount++;} %>
												
												
										</tbody>
									</table>
								</div>
								
								<h4 class="header-title text-uppercase bg-light p-2 mt-2 mb-3">
									<i class="fa fa-info-circle" aria-hidden="true"></i>
									Other Detail
								</h4>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Description</label> 
											<textarea class="form-control" id="description" name="description" placeholder="Add Descriptio"><%=foodRequest.getRequestDiscription() %></textarea>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="control-label">Address</label> 
											<textarea class="form-control" id="address" name="address" placeholder="Add Address"><%=foodRequest.getRequestAddress() %></textarea>
										</div>
									</div>
								</div>
								<div class="text-center">
									<a  href="<%=Constant.AFTER_LOGIN_BASEURL%>userRequestList"
										class="btn btn-danger waves-effect waves-light"> <i
										class="fa fa-window-close mr-1"></i> Close
									</a>
									<button type="submit"
										class="btn btn-success waves-effect waves-light">
										<i class="fe-edit"></i> Edit
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
<jsp:include page="commonFooter.jsp"></jsp:include>
<script src = "https://maps.googleapis.com/maps/api/js"></script>
<script>
	var count = <%=itemCount %>
	if (count == 1) {
		var td = '<td>'
			+'<div class="editable-buttons text-center" id="button0">'
				+'<span class="btn btn-success" onclick="addFoodItem(this)">'
					+'<span class="btn-lable">'
						+'<i class="fa fa-plus-circle"></i>'
					+'</span>'
				+'</span>'
			+'</div>'
		+'</td>';
		$("#foodItem").find("tr:first").append(td);
	}else {
		var td1 = '<td>'
			+'<div class="editable-buttons text-center d-none" id="button0">'
				+'<span class="btn btn-success" onclick="addFoodItem(this)">'
					+'<span class="btn-lable">'
						+'<i class="fa fa-plus-circle"></i>'
					+'</span>'
				+'</span>'
			+'</div>'
		+'</td>';
		$("#foodItem").find("tr:first").append(td1);
		var td2 = '<td>'
					+'<div class="editable-buttons text-center" id="button0">'
						+'<span class="btn btn-success  mr-2" onclick="addFoodItem(this)">'
							+'<span class="btn-lable">'
								+'<i class="fa fa-plus-circle"></i>'
							+'</span>'
						+'</span>'
						+'<span class="btn btn-danger" onclick="removeFoodItem(this)">'
							+'<span class="btn-lable">'
								+'<i class="fa fa-window-close"></i>'
							+'</span>'
						+'</span>'
					+'</div>'

				+'</td>';
		$("#foodItem").find("tr:last").append(td2);	
	}

</script>
<script src="<%=Constant.BASEURL%>assets/js/custom/common/userExtraFoodRequestEdit.js"></script>