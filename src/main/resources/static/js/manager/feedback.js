 var ctx = "http://"+$(location).attr('host');
$(document).ready(function(){
	var table =  $('#tableFeedback').DataTable({
    	"processing":true,
    	"serverSide":true,
    	"bSortable": true,
    	 "sort": "position",
	        // Default: Page display length
	        "iDisplayLength": 10,
	        "iDisplayStart": 0,
	       "sAjaxSource":ctx+"/listFeedback",
	   "columns": [
		  {"data":"id"},
		  {"data":"name"},
		  {"data":"email"},
		  {"data":"content"},
		  { "data": "action",
	            	render: function (data, type, row) {	            		
	            		return ' <a  class="btn btn-primary  btn-edit button_action" href="javascript:;" data-toggle="modal" id="'+row.id+'"  title="Phản hồi"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            }},  
	      ]
   	
	});
	
	$('#tableFeedback').on("click",'.btn-delete',function(){
		  var id = $(this).attr('id');
		  swal({
			  title: "Bạn chắc chắn muốn xóa",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
			    $.ajax({
			    	type:"DELETE",
			    	processData : false,
					contentType : false,
			    	url:"/feedback/delete/"+id,
			    	success: function(){ 
                     $('#tableFeedback').DataTable().ajax.reload(); 
                     swal("Đã xóa!", "Trở lại trang quản lí phản hồi", "success"); 
                 },
                 error: function(){
                     swal("Error", "Could not be deleted! :)", "error");   
                 }
			    })
			   
			  }
		});
	 });
	
	 $('#tableFeedback').on("click",'.btn-edit',function(){
		  var id = $(this).attr('id');
		  $.ajax({
		    	type:"GET",
		    	processData : false,
				contentType : false,
		    	url:"/feedback/"+id,
		    	success: function(result){ 
		    		$('#modal_id_repply').modal('show');
		    		$('#email').val(result.feedbackDto.email);
             },
             error: function(){
                 swal("Error", "Could not be edit! :)", "error");   
             }
		    }) ;  
	 });
	
	
	$("#button_repply").click(function(){
		var content = CKEDITOR.instances["content"].getData();
		var json = {"email":$("#email").val(),"title":$("#title").val(),"content":content};
		$.ajax({
			type:"POST",
	    	contentType: "application/json",
	    	url:"/send-repply",
	    	data:JSON.stringify(json),
	    	success: function(){ 
              swal("Thành công!", "Gửi phản hồi thành công", "success"); 
              $("#email").val('');
              $("#title").val('');
              $("#content").val('');
              $('#modal_id_repply').modal('hide');
	    	},
	    	error: function(){
              swal("Error", "error");   
	    	}
		});
	});
	 
});