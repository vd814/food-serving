<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<jsp:include page="commonHeader.jsp"></jsp:include>
<jsp:include page="commonSideMenu.jsp"></jsp:include>
<style>
.card-box {
    padding: 0.8rem !important;
}  
</style>

<!-- Left Sidebar End -->

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->
            
            
<%Map<String, Float> dashboardData = (Map<String, Float>) request.getAttribute("dashboardData"); %>
<div class="content-page">
    <div class="content">

        <!-- Start Content-->
        <div class="container-fluid">
            
            <!-- start page title -->
            <div class="row">
                <div class="col-12">
                    <div class="page-title-box">
                        <h4 class="page-title">Dashboard</h4>
                    </div>
                </div>
            </div>     
            <!-- end page title --> 
         

            <div class="row">

                <div class="col-md-4 col-lg-4 col-xl-4" title="Total Request">
                    <div class="widget-rounded-circle card-box">
                        <div class="row">
                            <div class="col-6">
                                <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                    <i class="fa fa-star font-22 avatar-title text-success"></i>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="text-right">
                                    <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=request.getAttribute("rating") %></span></h3>
                                    <p class="text-muted mb-1 text-truncate">Rating</p> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 col-lg-4 col-xl-4" title="Total Request">
                    <div class="widget-rounded-circle card-box">
                        <div class="row">
                            <div class="col-6">
                                <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                    <i class="fa fa-paper-plane font-22 avatar-title text-success"></i>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="text-right">
                                    <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("totalRequest") %></span></h3>
                                    <p class="text-muted mb-1 text-truncate">Total Request</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 col-lg-4 col-xl-4" title="Request Completed">
                    <div class="widget-rounded-circle card-box">
                        <div class="row">
                            <div class="col-6">
                                <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                    <i class="fa fa-paper-plane font-22 avatar-title text-success"></i>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="text-right">
                                    <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("completedRequest") %></span></h3>
                                    <p class="text-muted mb-1 text-truncate">Request Completed</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 col-lg-4 col-xl-4" title="Pending Request">
                    <div class="widget-rounded-circle card-box">
                        <div class="row">
                            <div class="col-6">
                                <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                    <i class="fa fa-paper-plane font-22 avatar-title text-success"></i>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="text-right">
                                    <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("assignedRequest") %></span></h3>
                                    <p class="text-muted mb-1 text-truncate">Assigned Request</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="col-md-4 col-lg-4 col-xl-4" title="Pending Request">
                    <div class="widget-rounded-circle card-box">
                        <div class="row">
                            <div class="col-6">
                                <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                    <i class="fa fa-paper-plane font-22 avatar-title text-success"></i>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="text-right">
                                    <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("canceledRequest") %></span></h3>
                                    <p class="text-muted mb-1 text-truncate">Canceled Request</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4 col-lg-4 col-xl-4" title="Deleted Request">
                    <div class="widget-rounded-circle card-box">
                        <div class="row">
                            <div class="col-6">
                                <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                    <i class="fa fa-comments font-22 avatar-title text-success"></i>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="text-right">
                                    <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("complain") %></span></h3>
                                    <p class="text-muted mb-1 text-truncate">Complain</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!-- end row-->

        </div> <!-- container -->

    </div> <!-- content -->
</div>
<jsp:include page="commonFooter.jsp"></jsp:include>
