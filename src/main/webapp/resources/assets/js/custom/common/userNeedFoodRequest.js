/**
 * 
 */
$("#createRequest").validate({
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

getLocation();
function getLocation() {
	  if (navigator.geolocation) {
		  console.log(navigator);
	    navigator.geolocation.getCurrentPosition(showPosition, showError);
	  } else {
	    alert("Geolocation is not supported by this browser.");
	  }
	}

function showPosition(position) {
	$("#inputLongitude").val(position.coords.longitude);
	$("#inputLatitude").val(position.coords.latitude);
		
}
function showError(error) {
	  var errorMsg="";
	  switch(error.code) {
	    case error.PERMISSION_DENIED:
	    	errorMsg="User denied the request for Geolocation.";
	      break;
	    case error.POSITION_UNAVAILABLE:
	    	errorMsg="Location information is unavailable.";
	      break;
	    case error.TIMEOUT:
	    	errorMsg="The request to get user location timed out.";
	      break;
	    case error.UNKNOWN_ERROR:
	    	errorMsg="An unknown error occurred.";
	      break;
	  }
	  swal({
			title: "Permission Denied !",
			text: errorMsg,
			type: "warning",
			confirmButtonClass: "btn btn-confirm mt-2"
		}).then(function(){
			window.history.back();				
		})	 
	  
}