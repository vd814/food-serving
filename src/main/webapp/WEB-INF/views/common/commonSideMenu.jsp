
<%@page import="com.swing.foodserving.utility.UserUtil"%>
<%@page import="com.swing.foodserving.entity.User"%>
<%@page import="com.swing.foodserving.constant.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- ========== Left Sidebar Start ========== -->
<% User user=UserUtil.getUser(session); %>
<div class="left-side-menu">
	<div class="slimscroll-menu">
		
		<!-- User info box -->
		<div class="user-box text-center">
            <img src="<%=Constant.BASEURL %>assets/images/avatar.jpg" alt="user-img" title="Mat Helme"
                class="rounded-circle avatar-md">
                
            <span class="text-dark h5 mt-2 mb-1 d-block"><%=user.getName() %></span>  
            <p class="text-muted">
            	<%if(user.getRole() == Constant.ROLE_VENDOR){ %>
            	Vendor
            	<%}else{%>
            	Public
            	<%} %>
            </p>
        </div>
		
		
			<!--- Start Sidemenu -->
			<div id="sidebar-menu">
	
				<ul class="metismenu" id="side-menu">
					<% if(user.getRole() == Constant.ROLE_PUBLIC){ %>
					
					<!-- Menu Item For Public User -->
					
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>userDashboard">
								<i class="fas fa-tachometer-alt"></i><span>Dashboard</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>userRequestList">
								<i class="fa fa-paper-plane"></i><span>Manage Request</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>userRequestDeclineList">
								<i class="fa fa-ban"></i><span>Request Decline Detail</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>userReviewRatingList">
								<i class="fa fa-star"></i><span>Review & Rating</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>userComplainList">
								<i class="fa fa-comments"></i><span>Complain Detail</span>
							</a>
						</li>
					<%}else{ %>
					
					<!-- Menu Item For Vendor -->
					
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>vendorDashbord">
								<i class="fas fa-tachometer-alt"></i></i><span>Dashbord</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>vendorRequestList">
								<i class="fa fa-paper-plane"></i><span>Food Request</span>
							</a>
						</li>
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>vendorRequestDeclineList">
								<i class="fa fa-ban"></i><span>Request Decline History</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>vendorReviewRatingList">
								<i class="fa fa-star"></i><span>Review & Rating</span>
							</a>
						</li>
						
						<li>
							<a href="<%=Constant.AFTER_LOGIN_BASEURL %>vendorComplainList">
								<i class="fa fa-comments"></i><span>Complain Detail</span>
							</a>
						</li>
					<%} %>
				</ul>
	
			</div>
			<!-- End SideMenu -->
		
			
		

		<div class="clearfix"></div>

	</div>
	<!-- Sidebar -left -->

</div>
