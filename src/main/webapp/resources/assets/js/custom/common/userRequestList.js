/**
 * 
 */
var userDatatable=$('#requestDataTable').DataTable( {
	responsive: true,
	"language": {
		"info": ' Showing _START_ to _END_ of _TOTAL_ Request',
		"processing": "<i class='fa fa-refresh fa-spin'></i>",
		"paginate": {
			previous: "<i class='mdi mdi-chevron-left'>",
			next: "<i class='mdi mdi-chevron-right'>"
		}
	},
	"drawCallback": function() {
		$(".dataTables_paginate > .pagination").addClass("pagination-rounded");
	},
	"initComplete": function(settings, json) {
	},
	"processing": false,
	"serverSide": true,
	"pageLength": 10,
	"bSort":true,
	"ajax": {
		"url": "getUserRequestWithDataTable",
		"data": function ( data ) {
		}
	},
	"aoColumnDefs": [
		{ 'bSortable': false,'aTargets': [0,2,4,5] }
		],
		"order": [[ 0, "desc" ]],
		"fnServerParams": function ( aoData ) {

		}

});

function viewRequestDetail(requestID,requestType) {
	var request = getRequestByRequestID(requestID);
	if (requestType == 1) {
		$("#foodItemDetail tr").remove();
		$("#otherDetail tr").remove();
		var foodItemList = $.parseJSON(request.requestData);
		var foodItemDetailTr = '';
		console.log(foodItemList);
		$.each(foodItemList, function(key, item) 
		{
			foodItemDetailTr += '<tr>'
									+'<td>'+item.itemName+'</td>'
									+'<td>'+item.itemQuantity+'('+item.itemUnit+')</td>'
								+'</tr>';
		   
		});
		$("#foodItemDetail").append(foodItemDetailTr);
		var otherDeatilTr = '<tr>'
								+'<td>Description</td>'
								+'<td>'+request.requestDiscription+'</td>'
							+'</tr>'
							+'<tr>'
								+'<td>Address</td>'
								+'<td>'+request.requestAddress+'</td>'
							+'</tr>';
		$("#otherDetail").append(otherDeatilTr);
		$("#forExtraFoodRequestDetail").trigger('click');
	}else {
		$("#needFoodRequestDetailBody tr").remove();
		var tr = '<tr>'
					+'<td>No. Of Person</td>'
					+'<td>'+request.noOfPerson+'</td>'
				+'</tr>'
				+'<tr>'
					+'<td>Description</td>'
					+'<td>'+request.requestDiscription+'</td>'
				+'</tr>'
				+'<tr>'
					+'<td>Address</td>'
					+'<td>'+request.requestAddress+'</td>'
				+'</tr>';
		$("#needFoodRequestDetailBody").append(tr);
		$("#forNeedFoodRequestDetail").trigger('click');
	}
}

function getRequestByRequestID(requestID) {
	var data = '';
	$.ajax({
		url:'getRequestByRequestID',
		type:'post',
		dataType:'json',
		data:{_csrf:token,requestID:requestID},
		async:false,
		success:function(res){
			data = res;
		}
	});
	return data;
}

function cancelRequest(requestID){
	$("#requestID").val(requestID)
	$("#forRequestCancelReason").trigger('click');
}

$("#cancelRequest").validate({
	rules: {
		reason:{
			required:true,
		},
	},
	messages: {
		reason:{
			required:"Please Enter Reason !",
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});

function requestCancelReason(requestID) {
	var requestCancel = getRequestCancelByRequestId(requestID);
	
	$("#requestCancelDetailTable tr").remove();
	var role = '';
	if (requestCancel[0][1].role == user) {
		role = 'Self';
	}else if (requestCancel[0][1].role == admin) {
		role = 'Admin';
	}else {
		role = 'Vendor';
	}
	var tr = '<tr>'
				+'<td>Cancle By</td>'
				+'<td>'+requestCancel[0][1].name+'('+role+')</td>'
			+'</tr>'
			+'<tr>'
				+'<td>Reason</td>'
				+'<td>'+requestCancel[0][0].cancleReason+'</td>'
			+'</tr>';
	$("#requestCancelDetailTable").append(tr);
	
	$("#forRequestCancelDetail").trigger('click');
	
}

function getRequestCancelByRequestId(requestID) {
	var data = '';
	$.ajax({
		url:'getRequestCancelByRequestId',
		type:'post',
		dataType:'json',
		data:{_csrf:token,requestID:requestID},
		async:false,
		success:function(res){
			data = res;
		}
	});
	return data;
}