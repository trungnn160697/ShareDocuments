 var ctx = "http://"+$(location).attr('host');
$(document).ready(function(){
	
	var table =  $('#tableMyDocument').DataTable({
    	"processing":true,
    	"serverSide":true,
    	"bSortable": true,
    	 "sort": "position",
	        // Default: Page display length
	        "iDisplayLength": 10,
	        "iDisplayStart": 0,
	       "sAjaxSource":ctx+"/listMyDocument",
	   "columns": [
		  {"data":"id"},
		  {"data":"name"},
		  {"data":"nameOfSubject",
			 render: function(data,type,row){
				 return '<a href="/document/subject-document/'+row.subjectId+'" >'+row.nameOfSubject+'</a>';
			 }
		  },
		  {"data":"date"},
		  {"data":"numberOfDownload"},
		  {"data":"rate"},
		  { "data": "action",
	            	render: function (data, type, row) {
	            			return '<a  class="btn btn-primary  btn-update button_action" data-toggle="modal" href="#modal_id_update_document" id="'+row.id+'"  title="Sửa">'
	            			+'<i class="fa fa-pencil-square-o"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            		}
	            },  
	      ]
   	
	});
	
	 $('#tableMyDocument').on("click",'.btn-delete',function(){
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
			    	url:"/document/delete/"+id,
			    	success: function(){ 
                       $('#tableMyDocument').DataTable().ajax.reload(); 
                       swal("Đã xóa!", "Trở lại trang quản lí tài liệu của bạn", "success"); 
                   },
                   error: function(){
                       swal("Error", "Could not be deleted! :)", "error");   
                   }
			    })
			   
			  }
		});
	 });
	 
	$('#tableMyDocument').on("click",'.btn-update',function(){
		  var id = $(this).attr('id');

		  $.ajax({
	    	type:"get",
	    	processData : false,
			contentType : false,
	    	url:"/document/view/"+id,
	    	success: function(result){ 
	    		$("#update_id").val(result.documentDto.id);
	    		$("#update_name").val(result.documentDto.name);
	    		$("#update_description").text(result.documentDto.description);
	    		$('#update_subject option[value='+result.documentDto.subjectId+']').attr('selected','selected');
	        },
	          error: function(){
	              swal("Error", "Could not be deleted! :)", "error");   
	          }
		  });
	});	  
	 
	 $('#button_add_document').click(function(){
		  var formData = new FormData();
		  formData.append("name",$('#add_name').val());
		  formData.append("idSubject",$('#add_subject').val());
		  formData.append("description",$('#add_description').val());
		  formData.append("image",document.getElementById("add_image").files[0]);
		  formData.append("linkDocument",document.getElementById("add_document").files[0]);
		  $.ajax({
		    	type:"POST",
		    	processData : false,
				contentType : false,
		    	url:"/document/add-document",
		    	data:formData,
		    	success: function(result){ 
			    	if(result.message.code==200){	
			    		swal("Thêm mới thành công!", "Trở lại trang quản tài liệu", "success"); 
			    		$('#add_name').val('');
			    		$('#add_subject').val('0');
			    		$('#add_description').val('');
			    		$('#modal_id_add_document').modal('hide');
			    		$('#tableMyDocument').DataTable().ajax.reload();
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
	 
	 
	 $('#button_update_document').click(function(){
		  var formData = new FormData();
		  formData.append("id",$('#update_id').val());
		  formData.append("name",$('#update_name').val());
		  formData.append("idSubject",$('#update_subject').val());
		  formData.append("description",$('#update_description').val());
		  formData.append("image",document.getElementById("update_image").files[0]);
		  formData.append("linkDocument",document.getElementById("update_document").files[0]);
		  $.ajax({
		    	type:"post",
		    	processData : false,
				contentType : false,
		    	url:"/document/update-document",
		    	data:formData,
		    	success: function(result){ 
			    	if(result.message.code==200){	
			    		swal("Thay đổi thành công!", "Trở lại trang quản tài liệu của tôi", "success");
			    		$('#tableMyDocument').DataTable().ajax.reload();
			    		$('#modal_id_update_document').modal('hide');
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
	 
});