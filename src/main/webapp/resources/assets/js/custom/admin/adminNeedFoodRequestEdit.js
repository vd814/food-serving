/**
 * 
 */
$("#editRequest").validate({
	rules: {
		noOfPerson:{
			required:true,
		},
		address:{
			required:true,
		}
	},
	messages: {
		noOfPerson:{
			required:"Enter No. Of Person",
		},
		address:{
			required:"Please Add Address",
		}
	},
	submitHandler: function(form) {
		previewFoodItem();
		form.submit();
	}
});