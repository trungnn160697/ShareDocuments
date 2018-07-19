 var ctx = "http://"+$(location).attr('host');
$(document).ready(function(){
	
	var table =  $('#tableSubject').DataTable({
    	"processing":true,
    	"serverSide":true,
    	"bSortable": true,
    	 "sort": "position",
	        // Default: Page display length
	        "iDisplayLength": 10,
	        "iDisplayStart": 0,
	       "sAjaxSource":ctx+"/listsubject",
	   "columns": [
		  {"data":"id"},
		  {"data":"name"},
		  {"data":"code"},
		  {"data":"description"},
		  { "data": "action",
	            	render: function (data, type, row) {	            		
	            		return ' <a  class="btn btn-primary  btn-edit button_action" href="javascript:;" id="'+row.id+'"  title="Sửa"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            }},  
	      ]
   	
	});
	
	 $('#tableSubject').on("click",'.btn-delete',function(){
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
			    	url:"/subject/delete/"+id,
			    	success: function(){ 
                       $('#tableSubject').DataTable().ajax.reload(); 
                       swal("Đã xóa!", "Trở lại trang quản lí môn học", "success"); 
                   },
                   error: function(){
                       swal("Error", "Could not be deleted! :)", "error");   
                   }
			    })
			   
			  }
		});
	 });
	 
	 $('#tableSubject').on("click",'.btn-edit',function(){
		  var formData = new FormData();
		  formData.append("id",$(this).attr('id'));
		  $.ajax({
		    	type:"POST",
		    	processData : false,
				contentType : false,
		    	url:"/subject/info-subject",
		    	data:formData,
		    	success: function(result){ 
		    		$('#modal_id').modal('show');
		    		$('#id').val(result.subjectDto.id);
		    		$('#update_name').val(result.subjectDto.name);
		    		$('#update_code').val(result.subjectDto.code);
		    		$('#update_description').val(result.subjectDto.description);
              },
              error: function(){
                  swal("Error", "Could not be edit! :)", "error");   
              }
		    }) ;  
	 });
	 
	 $('#button_update_subject').click(function(){
		  var formData = new FormData();
		  formData.append("id",$('#id').val());
		  formData.append("name",$('#update_name').val());
		  formData.append("code",$('#update_code').val());
		  formData.append("description",$('#update_description').val());
		  $.ajax({
		    	type:"POST",
		    	processData : false,
				contentType : false,
		    	url:"/subject/update-subject",
		    	data:formData,
		    	success: function(result){ 
		    		$('#modal_id').modal('hide');
		    		$('#tableSubject').DataTable().ajax.reload();
		    		if(result.message.code==200){
		    			swal("Sửa thành công!", "Trở lại trang quản lí môn học", "success"); 
		    		}
		    		else{
		    			swal("Thay đổi thất bại!", result.message.message, "error"); 
		    		}
             },
             error: function(){
                 swal("Error", "Could not be edit! :)", "error");   
             }
		    }) ;  
	 });
	 
	 $('#button_add_subject').click(function(){
		  var formData = new FormData();
		  formData.append("name",$('#add_name').val());
		  formData.append("code",$('#add_code').val());
		  formData.append("description",$('#add_description').val());
		  $.ajax({
		    	type:"POST",
		    	processData : false,
				contentType : false,
		    	url:"/subject/add-subject",
		    	data:formData,
		    	success: function(result){ 
		    		$('#modal_id_add').modal('hide');
		    		$('#tableSubject').DataTable().ajax.reload();
		    		if(result.message.code==200){
		    			swal("Thêm mới thành công!", "Trở lại trang quản lí môn học", "success"); 
			    		$('#add_name').val('');
			    		$('#add_code').val('');
			    		$('#add_description').val('');
		    		}
		    		else{
		    			swal("Thêm mới thất bại!", result.message.message, "error"); 
		    		}
            },
            error: function(){
                swal("Error", "Could not be edit! :)", "error");   
            }
		    }) ;  
	 });
});