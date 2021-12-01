<%@page import="com.swing.foodserving.constant.Constant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- Footer Start -->

<footer class="footer">
	<div class="container-fluid text-center">
		<div class="row">
			<div class="col-md-12">2020 &copy; FOOD SERVING</div>
		</div>
	</div>
</footer>
<!-- end Footer -->

</div>

<!-- ============================================================== -->
<!-- End Page content -->
<!-- ============================================================== -->


</div>

<script src="<%=Constant.BASEURL%>assets/js/vendor.min.js"></script>


<%
	if(request.getAttribute("javax.servlet.forward.request_uri").toString().contains("dashboard")){
%>

<script src="<%=Constant.BASEURL%>assets/libs/jquery-knob/jquery.knob.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/jquery-sparkline/jquery.sparkline.min.js"></script>
<%-- <script src="<%=Constant.BASEURL%>assets/libs/flot-charts/jquery.flot.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/flot-charts/jquery.flot.time.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/flot-charts/jquery.flot.tooltip.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/flot-charts/jquery.flot.selection.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/flot-charts/jquery.flot.crosshair.js"></script>
 --%>
<!-- Dashboar 1 init js-->
<script src="<%=Constant.BASEURL%>assets/js/pages/dashboard-1.init.js"></script>
<%
}
%>
<!-- loader js -->
<script src="<%=Constant.BASEURL%>assets/js/loader.js"></script>
<!-- App js-->
<script src="<%=Constant.BASEURL%>assets/js/app.min.js"></script>

<!-- Switch active/deactive -->
<script src="<%=Constant.BASEURL%>assets/libs/switchery/switchery.min.js"></script>

<!-- Datatable -->
<script src="<%=Constant.BASEURL%>assets/libs/datatables/jquery.dataTables.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/dataTables.bootstrap4.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/dataTables.responsive.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/responsive.bootstrap4.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/dataTables.buttons.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/buttons.bootstrap4.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/buttons.html5.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/buttons.flash.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/buttons.print.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/dataTables.keyTable.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/datatables/dataTables.select.min.js"></script>


<%-- <script src="<%=Constant.BASEURL%>assets/libs/pdfmake/pdfmake.min.js"></script> --%>

<!-- Pnotify -->
<script src="<%=Constant.BASEURL%>assets/js/pnotify.custom.min.js"></script>

<!-- jquery validation -->
<script src="<%=Constant.BASEURL%>assets/js/jquery.validate.min.js"></script>

<!-- Plugins js-->
<script src="<%=Constant.BASEURL%>assets/libs/flatpickr/flatpickr.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/bootstrap-colorpicker/bootstrap-colorpicker.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/libs/clockpicker/bootstrap-clockpicker.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/js/pages/form-pickers.init.js"></script>

<!-- Sweet Alerts js -->
<script src="<%=Constant.BASEURL%>assets/libs/sweetalert2/sweetalert2.min.js"></script>



<!-- Modal-Effect -->
<script src="<%=Constant.BASEURL%>assets/libs/custombox/custombox.min.js"></script>

<!-- MAsking --> 
<script src="<%=Constant.BASEURL%>assets/js/jquery.maskMoney.min.js"></script>  

<!-- datatable responsive -->
<script src="<%=Constant.BASEURL%>assets/js/dataTables.bootstrap4.min.js"></script>
<script src="<%=Constant.BASEURL%>assets/js/dataTables.responsive.min.js"></script> 
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.maskedinput/1.4.1/jquery.maskedinput.js"></script> 

<script>
var successColor = "#1abc9c";
var dangerColor = "#f1556c";
var token=$("#_csrf").val();

var msgStatus = "<%= session.getAttribute("msgStatus")%>";
var msg = "<%= session.getAttribute("msg")%>";
<%
session.removeAttribute("msg");
session.removeAttribute("msgStatus");
%>
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
</body>
</html>