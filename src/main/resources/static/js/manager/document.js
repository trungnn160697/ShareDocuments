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
		  {"data":"nameOfSubject",
			 render: function(data,type,row){
				 return '<a href="/document/subject-document/'+row.subjectId+'" >'+row.nameOfSubject+'</a>';
			 }
		  },
		  { "data": "usernameUser",
          		render: function (data,type,row) {
          			return '<a href="/document/user-document/'+row.idUser+'" >'+row.usernameUser+'</a>';
          		}
          },  
		  {"data":"date"},
		  {"data":"numberOfDownload"},
		  {"data":"rate"},
		  { "data": "action",
	            	render: function (data, type, row) {
	            			return '<a  class="btn btn-primary  btn-view button_action" data-toggle="modal" href="#modal_id_view_document" id="'+row.id+'"  title="Xem chi tiết"><i class="fa fa-eye"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
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
	 
	$('#tableDocument').on("click",'.btn-view',function(){
		  var id = $(this).attr('id');

		  $.ajax({
	    	type:"get",
	    	processData : false,
			contentType : false,
	    	url:"/document/view/"+id,
	    	success: function(result){ 
	    		$("#view_name_document").text(result.documentDto.name);
	    		$("#view_name_subject").text(result.documentDto.nameOfSubject);
	    		$("#view_description_document").text(result.documentDto.description);
	    		$("#view_username").html('<a class="view-username" href="/document/user-document/'+result.documentDto.idUser+'">'+result.documentDto.usernameUser+'</a>');
	    		$("#view_rate").text(result.documentDto.rate);
	    		$("#view_number_of_download").text(result.documentDto.numberOfDownload);
	    		$("#row_button_download").html('<a href="/document/download/'+result.documentDto.id+'" class="btn btn-primary" id="button_download">Down load</a>');
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
			    		$('#tableDocument').DataTable().ajax.reload();
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
	 
	 
	 
	 var table =  $('#tableDocumentUser').DataTable({
	    	"processing":true,
	    	"serverSide":true,
	    	"bSortable": true,
	    	 "sort": "position",
		        // Default: Page display length
		        "iDisplayLength": 10,
		        "iDisplayStart": 0,
		       "sAjaxSource":ctx+"/listDocumentByUser",
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
		            			return '<a  class="btn btn-primary  btn-view button_action" data-toggle="modal" href="#modal_id_view_document" id="'+row.id+'"  title="Nâng cấp tài khoản"><i class="fa fa-eye"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
		            		}
		            },  
		      ]
	   	
		});
	 
	 $('#tableDocumentUser').on("click",'.btn-view',function(){
		  var id = $(this).attr('id');

		  $.ajax({
	    	type:"get",
	    	processData : false,
			contentType : false,
	    	url:"/document/view/"+id,
	    	success: function(result){ 
	    		$("#view_name_document").text(result.documentDto.name);
	    		$("#view_name_subject").text(result.documentDto.nameOfSubject);
	    		$("#view_description_document").text(result.documentDto.description);
	    		$("#view_username").html('<a class="view-username" href="/document/user-document/'+result.documentDto.idUser+'">'+result.documentDto.usernameUser+'</a>');
	    		$("#view_rate").text(result.documentDto.rate);
	    		$("#view_number_of_download").text(result.documentDto.numberOfDownload);
	    		$("#row_button_download").html('<a href="/document/download/'+result.documentDto.id+'" class="btn btn-primary" id="button_download">Down load</a>');
	        },
	          error: function(){
	              swal("Error", "Could not be deleted! :)", "error");   
	          }
		  });
	});	  
	 
	 
	 var table =  $('#tableDocumentSubject').DataTable({
	    	"processing":true,
	    	"serverSide":true,
	    	"bSortable": true,
	    	 "sort": "position",
		        // Default: Page display length
		        "iDisplayLength": 10,
		        "iDisplayStart": 0,
		       "sAjaxSource":ctx+"/listDocumentBySubject",
		   "columns": [
			  {"data":"id"},
			  {"data":"name"},
			  { "data": "usernameUser",
	          		render: function (data,type,row) {
	          			return '<a href="/document/user-document/'+row.idUser+'" >'+row.usernameUser+'</a>';
	          		}
	          },  
			  {"data":"date"},
			  {"data":"numberOfDownload"},
			  {"data":"rate"},
			  { "data": "action",
		            	render: function (data, type, row) {
		            			return '<a  class="btn btn-primary  btn-view button_action" data-toggle="modal" href="#modal_id_view_document" id="'+row.id+'"  title="Nâng cấp tài khoản"><i class="fa fa-eye"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
		            		}
		            },  
		      ]
	   	
		});
	 
 });

	$('#tableDocumentSubject').on("click",'.btn-view',function(){
		  var id = $(this).attr('id');
		  $.ajax({
		  	type:"get",
		  	processData : false,
				contentType : false,
		  	url:"/document/view/"+id,
		  	success: function(result){ 
		  		$("#view_name_document").text(result.documentDto.name);
		  		$("#view_name_subject").text(result.documentDto.nameOfSubject);
		  		$("#view_description_document").text(result.documentDto.description);
		  		$("#view_username").html('<a class="view-username" href="/document/user-document/'+result.documentDto.idUser+'">'+result.documentDto.usernameUser+'</a>');
		  		$("#view_rate").text(result.documentDto.rate);
		  		$("#view_number_of_download").text(result.documentDto.numberOfDownload);
		  		$("#row_button_download").html('<a href="/document/download/'+result.documentDto.id+'" class="btn btn-primary" id="button_download">Down load</a>');
		      },
	        error: function(){
	            swal("Error", "Could not be deleted! :)", "error");   
	        }
		  });

		  
		  $('#tableDocumentSubject').on("click",'.btn-delete',function(){
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
	                       $('#tableDocumentSubject').DataTable().ajax.reload(); 
	                       swal("Đã xóa!", "Trở lại trang quản lí tài liệu", "success"); 
	                   },
	                   error: function(){
	                       swal("Error", "Could not be deleted! :)", "error");   
	                   }
				    })
				   
				  }
			});
		 });
		  
		  $('#tableDocumentUser').on("click",'.btn-delete',function(){
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
	                       $('#tableDocumentSubject').DataTable().ajax.reload(); 
	                       swal("Đã xóa!", "Trở lại trang quản lí tài liệu", "success"); 
	                   },
	                   error: function(){
	                       swal("Error", "Could not be deleted! :)", "error");   
	                   }
				    })
				   
				  }
			});
		 });
});	  
