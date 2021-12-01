/**
 * 
 */
$("#signInNow").validate({
		rules: {
			role:{
				required:true
			},
			email:{
				required:true,
				email : true,
				remote : {
					url: "emailExist",
	                type: "POST",
	                data: {
	                	"_csrf": function(){
							return $("#_csrf").val();
						},
	                    "email" : function () { return $("#email").val() },
	                } 
				},
			},
			password:{
				required:true
			},
			rePassword:{
				required:true,
				equalTo: "#password"
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
	                } 
				},
			}
			
		
		},
		messages: {
			role:{
				required:"Please Select Join As"
			},
			email:{
				required:"Please enter Email ID",
				email : "Please enter valid email",
				remote :"Email ID Already Exist",
	 
			},
			password:{
				required:"Please enter password !",
				equalTo : "Please Enter Same Password"
			},
			rePassword:{
				required:"Plaease Enter Password Again",
				equalTo : "Please Enter Same Password"
			},
			userName:{
				required:"Please Enter username",
				remote :"username Already Exist",
			}
		},
		submitHandler: function(form) {
			form.submit();
		}
});
