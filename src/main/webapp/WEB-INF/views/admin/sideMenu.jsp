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
            	<%if(user.getRole() == Constant.ROLE_ADMIN){ %>
            	Admin
            	<%}%>
            </p>
        </div>
        
		<!--- Sidemenu -->
		<div id="sidebar-menu">

			<ul class="metismenu" id="side-menu">
				<li>
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>dashboard">
						<i class="fas fa-tachometer-alt"></i><span>DashBoard</span>
					</a>
				</li>
				
				<li>
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>userList">
						<i class="fa fa-user"></i><span>Manage User</span>
					</a>
				</li>
				<li>
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>requestList">
						<i class="fa fa-paper-plane"></i><span>Manage Request</span>
					</a>
				</li>
				<li>
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>requestDeclineList">
						<i class="fa fa-ban"></i><span>Request Decline Detail</span>
					</a>
				</li>
				<li>
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>reviewRatingList">
						<i class="fa fa-star"></i><span>Review & Rating</span>
					</a>
				</li>
				<li>
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>complainList">
						<i class="fa fa-comments"></i><span>Complain Detail</span>
					</a>
				</li>
				<li class="d-none">
					<a href="<%=Constant.AFTER_LOGIN_ADMIN_BASEURL %>queryList">
						<i class="fa fa-question-circle"></i><span>Query Detail</span>
					</a>
				</li>
				
			</ul>

		</div>
		<!-- End Sidebar -->
		
	</div>
	<!-- Sidebar -left -->

</div>
