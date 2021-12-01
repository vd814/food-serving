/**
 * 
 */
var currentUsername;
var currentEmail;
var currentMobile;

var userDatatable=$('#userDatatable').DataTable( {
	responsive: true,
	"language": {
		"info": ' Showing _START_ to _END_ of _TOTAL_ User',
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
		"url": "getAllUserWithDatatable",
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

$("#addUser").validate({
	rules: {
		name:{
			required:true,
		},
		userName:{
			required:true,
			remote : {
				url: "userNameExist",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "userName" : function () { return $("#userName").val() },
                    "currentUserName" : ""
                } 
			},
		},
		password:{
			required:true,
		},
		mail:{
			required:true,
			remote : {
				url: "emailExist",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "email" : function () { return $("#mail").val() },
                    "currentEmail" : ""
                } 
			},
		},
		gender:{
			required:true,
		},
		role:{
			required:true,
		},
		mobile:{
			required:true,
			remote : {
				url: "mobileExist",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "mobile" : function () { return $("#mobile").val() },
                    "currentMobile" : ""
                } 
			},
		}
	},
	messages: {
		name:{
			required:"Please enter name !",
		},
		userName:{
			required:"Please enter username !",
			remote:"username Already Exist !",
		},
		password:{
			required:"Please enter password !",
		},
		mail:{
			required:"Please enter e-mail !",
			remote:"Email ID Already Exist !",
		},
		gender:{
			required:"Please select gender !",
		},
		role:{
			required:"Please select role !",
		},mobile:{
			required:"Please Enter Mobile No",
			remote:"Mobile No Already Exist !"
		}
	},
	submitHandler: function(form) {
		form.submit();
	}
});	

function editUser(userID) {
	var user = getUserByUserID(userID);
	
	$("#userID").val(userID);
	$("#editName").val(user.name);
	$("#editUserName").val(user.username);
	$("#editMail").val(user.email);
	$("#editEmailVerified").val(user.isEmailVerifiyed);
	$("#editMobile").val(user.mobile);
	$("#editMobileVerified").val(user.isMobileVerifiyed);
	$("#editGender").val(user.gender);
	$("#editRole").val(user.role);
	currentUsername = user.username;
	currentEmail = user.email;
	currentMobile = user.mobile;
	$("#referanceHrefForEdit").trigger('click');
}

function getUserByUserID(userID) {
	var data = '';
	$.ajax({
		url:'getUserByUserID',
		type:'post',
		dataType:'json',
		data:{_csrf:token,userID:userID},
		async:false,
		success:function(res){
			data = res;
		}
	});
	return data;
}

$("#editUser").validate({
	rules: {
		editName:{
			required:true,
		},
		editUserName:{
			required:true,
			remote : {
				url: "userNameExist",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "userName" : function () { return $("#editUserName").val() },
                    "currentUserName" : function () { return  currentUsername},
                } 
			},
		},
		editMail:{
			required:true,
			remote : {
				url: "emailExist",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "email" : function () { return $("#editMail").val() },
                    "currentEmail" : function () { return  currentEmail},
                } 
			},
		},
		editEmailVerified:{
			required:true,
		},
		editMobile:{
			required:true,
			remote : {
				url: "mobileExist",
                type: "POST",
                data: {
                	"_csrf": function(){
						return $("#_csrf").val();
					},
                    "mobile" : function () { return $("#editMobile").val() },
                    "currentMobile" : function () { return currentMobile}
                } 
			},
		},
		editMobileVerified:{
			required:true,
		},
		editGender:{
			required:true,
		},
		editGender:{
			required:true,
		},
		editRole:{
			required:true,
		}
	},
	messages: {
		editName:{
			required:"Please enter name !",
		},
		editUserName:{
			required:"Please enter username !",
			remote:"username Already Exist !"
		},
		editMail:{
			required:"Please enter Mail !",
			remote:"Email ID Already Exist !"
		},
		editEmailVerified:{
			required:"Please Confirm Email Is Verified !",
		},
		editMobile:{
			required:"Please Enter Mobile No !",
			remote:"Mobiel No Already Exist !"
		},
		editMobileVerified:{
			required:"Please Confirm Mobile No Is Verified !",
		},
		editGender:{
			required:"Please Select Gender",
		},
		editRole:{
			required:"Please Select Role",
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});	

function changeActiveStatus(status,userID) {
	var data='';
	$.ajax({
		url:'changeUserStatus',
		type:'post',
		dataType:'text',
		data:{status:status,userID:userID,_csrf:token},
		async:false,
		success:function(res){
			data=res;
		}
	});
	notify("success","Success!",data);
}

function deleteUser(userID) {
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
				url:'deleteUser',
				type:'post',
				dataType:'json',
				data:{userID:userID,_csrf:token},
				async:false,
				success:function(res){
					data=res;
				}
			});

			if(data){
				swal({
					title: "Deleted !",
					text: "User has been deleted",
					type: "success",
					confirmButtonClass: "btn btn-confirm mt-2"
				}).then(function(){
					$("#userDatatable").DataTable().ajax.reload();	
				})	


			}else{
				swal({
					title: "Error !",
					text: "There is an error so the User cannot be deleted",
					type: "error",
					confirmButtonClass: "btn btn-confirm mt-2"
				}).then(function(){
									
				})
				
			}
		}
	});
}