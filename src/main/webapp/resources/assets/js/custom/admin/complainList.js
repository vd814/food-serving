/**
 * 
 */
var userDatatable=$('#complainDatatable').DataTable( {
	responsive: true,
	"language": {
		"info": ' Showing _START_ to _END_ of _TOTAL_ Review & Rating Detail',
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
		"url": "getAllComplainForAdminWithDatatable",
		"data": function ( data ) {
		}
	},
	"aoColumnDefs": [
		{ 'bSortable': false,'aTargets': [0,4,5,6,7] }
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

function editComplain(complainID) {
	var complain = getComplainByID(complainID);
	$("#editComplainID").val(complain.id);
	$("#editComplain").val(complain.complain);
	$("#forEditComplainModal").trigger('click');
}

function getComplainByID(complainID) {
	var data = '';
	$.ajax({
		url:'getComplainByID',
		type:'post',
		dataType:'json',
		data:{_csrf:token,complainID:complainID},
		async:false,
		success:function(res){
			data = res;
		}
	});
	return data;
}

$("#editComplainForm").validate({
	rules: {
		editComplain:{
			required:true,
		},
	},
	messages: {
		editComplain:{
			required:"Please Enter Complain Detail !",
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});

function deleteComplain(complainID) {
	swal({
		title: "Are you sure?",
		text: "You won't be able to revert this!",
		type: "warning",
		showCancelButton: !0,
		confirmButtonText: "Yes, delete it!",
		cancelButtonText: "No, cancel!",
		confirmButtonClass: "btn btn-success mt-2",
		cancelButtonClass: "btn btn-danger ml-2 mt-2",
		buttonsStyling: !1
	}).then(function(isConfirm) {
		var data=false;
		if (isConfirm.value) {
			$.ajax({
				url:'deleteComplain',
				type:'post',
				dataType:'json',
				data:{complainID:complainID,_csrf:token},
				async:false,
				success:function(res){
					data=res;
				}
			});

			if(data){
				swal({
					title: "Deleted !",
					text: "Complain has been deleted",
					type: "success",
					confirmButtonClass: "btn btn-confirm mt-2"
				}).then(function(){
					$("#complainDatatable").DataTable().ajax.reload();	
				})	


			}else{
				swal({
					title: "Error !",
					text: "There is an error so Complain cannot be deleted",
					type: "error",
					confirmButtonClass: "btn btn-confirm mt-2"
				}).then(function(){
									
				})
				
			}
		}
	});
}

function addReplay(complainID) {
	$("#complainID").val(complainID);
	$("#forAddReplayModal").trigger('click');
}

$("#addReplayByAdmin").validate({
	rules: {
		replay:{
			required:true,
		},
	},
	messages: {
		replay:{
			required:"Please Enter Your Replay !",
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});

function editReplay(complainID) {
	var complain = getComplainByID(complainID);
	$("#editComplainIdforReplay").val(complain.id);
	$("#editReplay").val(complain.adminReplay);
	$("#forEditReplayModal").trigger('click');
}


$("#editReplayByAdmin").validate({
	rules: {
		editReplay:{
			required:true,
		},
	},
	messages: {
		editReplay:{
			required:"Please Enter Your Replay !",
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});