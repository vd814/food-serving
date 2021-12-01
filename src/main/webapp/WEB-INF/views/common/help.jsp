<!-- Header -->
<%@page import="com.swing.foodserving.constant.Constant"%>
<jsp:include page="beforeLoginHeader.jsp"></jsp:include>

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
							<a href="#add-query-modal"
								class="btn btn-success waves-effect waves-light financial-year-non-updateble"
								data-animation="fadein" data-plugin="custommodal"
								data-overlaycolor="#38414a">
								<i class="mdi mdi-email-outline mr-1"></i> Send Us Your Query
							</a>
						</div>
						<h4 class="page-title">Help</h4>
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
							<div class="row">
	                            <div class="col-12">
	                                <div class="text-center">
	                                    <i class="h1 mdi mdi-comment-multiple-outline text-muted"></i>
	                                    <h3 class="mb-3">Frequently Asked Questions</h3>
	                                </div>
	                            </div><!-- end col -->
	                        </div><!-- end row -->
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
		<div id="add-query-modal" class="modal-demo">
			<button type="button" class="close"
				onclick="Custombox.modal.close();">
				<span>&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="custom-modal-title">Add Your Query</h4>
			<div class="custom-modal-text text-left">
				<form action="addQuery" method="post" id="addQuery">
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="groupName" class="control-label">Email</label> <input
									type="text" class="form-control" id="email" name="email"
									placeholder="Email">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="eMail" class="control-label">Query Type</label> 
								<select
								class="form-control" id="queryType"
								name="queryType">
									<option value="-1" disabled="disabled" selected="selected" >---Select---</option>
									<option value="1">Login / Signup related</option>
									<option value="2">Other</option>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<label for="mobiel" class="control-label">Query</label> <input
									type="text" class="form-control" id="query" name="query"
									placeholder="Query">
							</div>
						</div>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					
					<div class="text-right">
						<button type="submit"
							class="btn btn-success waves-effect waves-light "><i class="fe-save"></i> Send</button>
						<button type="button"
							class="btn btn-danger waves-effect waves-light m-l-10"
							onclick="Custombox.modal.close();"><i class="fa fa-times"></i> Cancel</button>
					</div>
				</form>
			</div>
		</div>
		<!--add User Modal End  -->
		
	</div>
</div>

<!-- Footer -->
<jsp:include page="beforeLoginFooter.jsp"></jsp:include>
<script src="<%=Constant.BASEURL%>assets/js/custom/common/help.js"></script>
<script>
var msgStatus = "<%= request.getAttribute("msgStatus")%>";

var msg = "<%= request.getAttribute("msg")%>";
if(msgStatus=="null"){
	
	if(msg != "null"){
		notify("success","Success!",msg);
	}
}
else{
	if(msgStatus==0)
		{
			notify("success","Success!",msg);
		}
	else if(msgStatus==1)
		{
			notify("error","Oh No!",msg);
		}
	
}

function notify(type,title,msg){
	new PNotify({
	    title: title,
	    text: msg,
	    type: type,
	    delay: 3000,
	});
}

</script>