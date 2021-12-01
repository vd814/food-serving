/**
 * 
 */
var userDatatable=$('#reviewRatingDatatable').DataTable( {
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
		"url": "getAllCompetedRequestWithDatatable",
		"data": function ( data ) {
		}
	},
	"aoColumnDefs": [
		{ 'bSortable': false,'aTargets': [0,3,4,5,6,7] }
		],
		"order": [[ 0, "desc" ]],
		"fnServerParams": function ( aoData ) {

		}

});

function giveReviewRating(requestID) {
	$("#requestID").val(requestID);
	$("#forAddReviewRatingModal").trigger('click');
}
$("#addReviewRating").validate({
	rules: {
		review:{
			required:true,
		},
		rating:{
			required:true,
			range: [0, 5]
		}
	},
	messages: {
		review:{
			required:"Please Enter Review",
		},
		rating:{
			required:"Please Enter Rating",
			range : "Please Enter Rating Between 0 To 5"
		}
	},
	submitHandler: function(form) {
		form.submit();
	}
});

function editReviewRating(reviewID) {
	var review = getReviewByID(reviewID);
	$("#reviewID").val(review.id);
	$("#editReview").val(review.review);
	$("#editRating").val(review.rating);
	$("#forEditReviewRatingModal").trigger('click');
}

function getReviewByID(reviewID){
	var data = '';
	$.ajax({
		url:'getReviewByID',
		type:'post',
		dataType:'json',
		data:{_csrf:token,reviewID:reviewID},
		async:false,
		success:function(res){
			data = res;
		}
	});
	return data;
}

$("#editReviewRating").validate({
	rules: {
		editReview:{
			required:true,
		},
		editRating:{
			required:true,
			range: [0, 5]
		}
	},
	messages: {
		editReview:{
			required:"Please Enter Review",
		},
		editRating:{
			required:"Please Enter Rating",
			range : "Please Enter Rating Between 0 To 5"
		}
	},
	submitHandler: function(form) {
		form.submit();
	}
});

function doComplain(requestIdForComplain) {
	$("#requestIdForComplain").val(requestIdForComplain);
	$("#forAddComplainModal").trigger('click');
}

$("#addComplain").validate({
	rules: {
		complain:{
			required:true,
		},
	},
	messages: {
		complain:{
			required:"Please Enter Complain Detail",
		},
	},
	submitHandler: function(form) {
		form.submit();
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