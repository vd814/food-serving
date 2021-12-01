/**
 * 
 */
$("#addQuery").validate({
	rules: {
		email:{
			required:true,
		},
		queryType:{
			required:true,
		},
		query:{
			required:true,
		},
	},
	messages: {
		email:{
			required:"Please enter email !",
		},
		queryType:{
			required:"Please select query type !",
		},
		query:{
			required:"Please enter query !",
		},
	},
	submitHandler: function(form) {
		form.submit();
	}
});