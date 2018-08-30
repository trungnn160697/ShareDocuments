
$(document).ready(function(){
	
	$("#button_feedback").click(function(){
			var json = {"name":$("#name_feedback").val(),"email":$("#email_feedback").val(),"content":$("#content_feedback").val()};
		$.ajax({
			type:"POST",
	    	contentType: "application/json",
	    	url:"/send-feedback",
	    	data:JSON.stringify(json),
	    	success: function(){ 
              swal("Thành công!", "Cảm ơn ý kiến của bạn", "success"); 
              $("#name_feedback").val('');
              $("#email_feedback").val('');
              $("#content_feedback").val('');
	    	},
	    	error: function(){
              swal("Error", "error");   
	    	}
		});
	});
	
	
	
});