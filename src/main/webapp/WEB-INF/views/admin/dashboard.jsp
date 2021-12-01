<%@page import="com.swing.foodserving.entity.User"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="sideMenu.jsp"></jsp:include>
<style>
.card-box {
    padding: 0.8rem !important;
}  
</style>
<% 
User user=(User)session.getAttribute("user");
%>

<!-- Left Sidebar End -->

<!-- ============================================================== -->
<!-- Start Page Content here -->
<!-- ============================================================== -->
            
            
<% Map<String, Integer> dashboardData = (Map<String, Integer>) request.getAttribute("dashboardData"); %>
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
                 <div class="col-md-4 col-lg-4 col-xl-4" title="Totla User">
                     <div class="widget-rounded-circle card-box">
                         <div class="row">
                             <div class="col-6">
                                 <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                     <i class="fas fa-user font-22 avatar-title text-success"></i>
                                 </div>
                             </div>
                             <div class="col-6">
                                 <div class="text-right">
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("totalUser") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Total User</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
                 </div> 
                 
                 <!-- end col-->

                 <div class="col-md-4 col-lg-4 col-xl-4" title="Total Vendor">
                     <div class="widget-rounded-circle card-box">
                         <div class="row">
                             <div class="col-6">
                                 <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                     <i class="fa fa-user-circle font-22 avatar-title text-success"></i>
                                 </div>
                             </div>
                             <div class="col-6">
                                 <div class="text-right">
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("vendorUser") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Total Vendor</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
                 </div> <!-- end col-->
                 
                 <div class="col-md-4 col-lg-4 col-xl-4" title="Public User">
                     <div class="widget-rounded-circle card-box">
                         <div class="row">
                             <div class="col-6">
                                 <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                     <i class="fa fa-user-circle font-22 avatar-title text-success"></i>
                                 </div>
                             </div>
                             <div class="col-6">
                                 <div class="text-right">
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("publicUser") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Public User</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
                 </div> <!-- end col-->

                 <div class="col-md-4 col-lg-4 col-xl-4" title="Total Request">
                     <div class="widget-rounded-circle card-box">
                         <div class="row">
                             <div class="col-6">
                                 <div class="avatar-lg rounded-circle bg-soft-info border-info border">
                                     <i class="fa fa-paper-plane font-22 avatar-title text-info"></i>
                                 </div>
                             </div>
                             <div class="col-6">
                                 <div class="text-right">
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("totalRequest") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Total Request</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
                 </div> <!-- end col-->

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
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("pendingRequest") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Pending Request</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
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
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
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
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("acceptedRequest") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Accepted Request</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
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
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("completedRequest") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Completed Request</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
                 </div>
                 
                 <div class="col-md-4 col-lg-4 col-xl-4" title="Deleted Request">
                     <div class="widget-rounded-circle card-box">
                         <div class="row">
                             <div class="col-6">
                                 <div class="avatar-lg rounded-circle bg-soft-success border-success border">
                                     <i class="fa fa-paper-plane font-22 avatar-title text-success"></i>
                                 </div>
                             </div>
                             <div class="col-6">
                                 <div class="text-right">
                                     <h3 class="text-dark mt-1"><span data-plugin="counterup"><%=dashboardData.get("cancelRequest") %></span></h3>
                                     <p class="text-muted mb-1 text-truncate">Cancel Request</p>
                                 </div>
                             </div>
                         </div> <!-- end row-->
                     </div> <!-- end widget-rounded-circle-->
                 </div>

             </div>
             <!-- end row-->

         </div> <!-- container -->

     </div> <!-- content -->
</div>
<jsp:include page="footer.jsp"></jsp:include>
