 var ctx = "http://"+$(location).attr('host');
$(document).ready(function(){
	
	var table =  $('#tableDocument').DataTable({
    	"processing":true,
    	"serverSide":true,
    	"bSortable": true,
    	 "sort": "position",
	        // Default: Page display length
	        "iDisplayLength": 10,
	        "iDisplayStart": 0,
	       "sAjaxSource":ctx+"/listdocument",
	   "columns": [
		  {"data":"id"},
		  {"data":"name"},
		  {"data":"nameOfSubject"},
		  {"data":"usernameUser"},
		  {"data":"date"},
		  {"data":"numberOfDownload"},
		  {"data":"rate"},
		  { "data": "action",
	            	render: function (data, type, row) {
	            			return '<a  class="btn btn-primary  btn-upgrade button_action" href="javascript:;" id="'+row.id+'"  title="Nâng cấp tài khoản"><i class="fa fa-level-up"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            		}
	            },  
	      ]
   	
	});
	
	 $('#tableDocument').on("click",'.btn-delete',function(){
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
                       $('#tableDocument').DataTable().ajax.reload(); 
                       swal("Đã xóa!", "Trở lại trang quản lí tài liệu", "success"); 
                   },
                   error: function(){
                       swal("Error", "Could not be deleted! :)", "error");   
                   }
			    })
			   
			  }
		});
	 });
	 
	 $('#tableDocument').on("click",'.btn-upgrade',function(){
		  var formData = new FormData();
		  formData.append("id",$(this).attr('id'));
		  swal({
			  title: "Bạn chắc chắn muốn nâng cấp",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
			    $.ajax({
			    	type:"Post",
			    	processData : false,
					contentType : false,
			    	url:"/user/upgrade",
			    	data:formData,
			    	success: function(){ 
                      $('#tableUser').DataTable().ajax.reload(); 
                      swal("Đã nâng cấp!", "Trở lại trang quản lí nhân viên", "success"); 
                  },
                  error: function(){
                      swal("Error", "Could not be deleted! :)", "error");   
                  }
			    })
			   
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
			    		$('#modal_id_add').modal('hide');
			    		$('#tableDocument').DataTable().ajax.reload();	
			    		swal("Thêm mới thành công!", "Trở lại trang quản tài liệu", "success"); 
			    		$('#add_name').val('');
			    		$('#add_subject').val('0');
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