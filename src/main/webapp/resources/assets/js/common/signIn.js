
$("#signInNow").validate({
		rules: {
			email:{
				required:true,
			},
			password:{required:true,maxlength:50},
			
		
		},
		messages: {
			email:{
				required:"Please enter Username OR Mobile No. !",
	 
			},
			password:{
				required:"Please enter password !",
				//minlength:"Password should be minimum 6 character long !",
				maxlength:"Password should not be more then 50 character !"
			}
		},
		submitHandler: function(form) {
			form.submit();
		}
	});

var uname=$.cookie("email");
var pwd=$.cookie("password");	
console.log(uname);
console.log(pwd);
if((uname!=undefined && pwd!=undefined))
{
	if((uname!="" && pwd!="")){
		$('#email').val(uname);
		$('#password').val(pwd);
		$('#checkbox-signin').prop("checked","checked");
	}
}