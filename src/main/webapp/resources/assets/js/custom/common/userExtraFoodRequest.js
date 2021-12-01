/**
 * 
 */
var count = 1;
function addFoodItem(e) {
	$(e).parent().addClass("d-none");
	
	var tr = '<tr class="item">'
				+'<td>'
					+'<input class="form-control" type="text" name="foodItem'+count+'" id="foodItem'+count+'">'
				+'</td>'
				+'<td>'
					+'<input class="form-control" type="text" name="itemQuantity'+count+'" id="itemQuantity'+count+'">'
				+'</td>'
				+'<td>'
					+'<select class="form-control" name="itemUnit'+count+'" id="itemUnit'+count+'">'
						+'<option value="" selected="selected">Select Item Unit</option>'
						+'<option value="Kg" >Kg</option>'
						+'<option value="pcs" >pcs</option>'
						+'<option value="ltr" >ltr</option>'
					+'</select>'	
				+'</td>'
				+'<td>'
					+'<div class="editable-buttons text-center" id="button'+count+'">'
						+'<span class="btn btn-success mr-2" onclick="addFoodItem(this)">'
							+'<span class="btn-lable">'
								+'<i class="fa fa-plus-circle"></i>'
							+'</span>'
						+'</span>'
						+'<span class="btn btn-danger" onclick="removeFoodItem(this)">'
							+'<span class="btn-lable">'
								+'<i class="fa fa-window-close"></i>'
							+'</span>'
						+'</span>'
					'</div>'
				+'</td>'
			+'</tr>';
	$("#foodItem").append(tr);
	count++;
}

function removeFoodItem(e) {
	count--;
	var index = $(e).parent().parent().parent().index();
	$("#foodItem tr").eq(index-1).find("td:last").find("div").removeClass("d-none");
	$(e).parent().parent().parent().remove();
}

function previewFoodItem(){
	var foodItemDataList = [];
	$.each($(".item"),function(index,tr){
		var itemName = $(tr).find("#foodItem"+index).val();
		var itemQuantity = $(tr).find("#itemQuantity"+index).val();
		var itemUnit = $(tr).find("#itemUnit"+index).val();
		var itemDetail = {
				"itemName" : itemName,
				"itemQuantity" : itemQuantity,
				"itemUnit" : itemUnit
		};
		foodItemDataList.push(itemDetail);
	});
	var myJSON = JSON.stringify(foodItemDataList);
	$("#foodItemData").val(myJSON);
}


$("#createRequest").validate({
	rules: {
		foodItem0:{
			required:true,
		},
		address:{
			required:true,
		}
	},
	messages: {
		foodItem0:{
			required:"Add atlist one Food Item Name !",
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