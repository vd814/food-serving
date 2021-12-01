/**
 * 
 */
var userDatatable=$('#requestDeclineDataTable').DataTable( {
	responsive: true,
	"language": {
		"info": ' Showing _START_ to _END_ of _TOTAL_ Decline Request Detail',
		"processing": "<i class='fa fa-refresh fa-spin'></i>",
		"paginate": {
			previous: "<i class='mdi mdi-chevron-left'>",
			next: "<i class='mdi mdi-chevron-right'>"
		}
	},
	"drawCallback": function() {
		$(".dataTables_paginate > .pagination").addClass("pagination-rounded");
		initiliztionSwitch();
	},
	"initComplete": function(settings, json) {
		//initiliztionSwitch();
	},
	"processing": false,
	"serverSide": true,
	"pageLength": 10,
	"bSort":true,
	"ajax": {
		"url": "getAllDeclineRequestWithDataTable",
		"data": function ( data ) {
		}
	},
	"aoColumnDefs": [
		{ 'bSortable': false,'aTargets': [0,2,3,4,5] }
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