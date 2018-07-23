 var ctx = "http://"+$(location).attr('host');
$(document).ready(function(){
	
	var table =  $('#tableUser').DataTable({
    	"processing":true,
    	"serverSide":true,
    	"bSortable": true,
    	 "sort": "position",
	        // Default: Page display length
	        "iDisplayLength": 10,
	        "iDisplayStart": 0,
	       "sAjaxSource":ctx+"/listuser",
	   "columns": [
		  {"data":"id"},
		  {"data":"username"},
		  {"data":"fullName"},
		  {"data":"image",render:function(data,type,row){
			  return '<img src="'+ctx+'/display?'+row.image+'" class="img-responsive" alt="Image" style="width:50px;height: 50px">';
		  }},
		  {"data":"email"},
		  {"data":"phone"},
		  {"data":"roleName"},
		  { "data": "action",
	            	render: function (data, type, row) {
	            		if(row.roleName=='Khách'&&row.roleName!='Quản trị viên'){
	            			return '<a  class="btn btn-primary  btn-upgrade button_action" href="javascript:;" id="'+row.id+'"  title="Nâng cấp tài khoản"><i class="fa fa-level-up"></i></a> <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            		}
	            		else if(row.roleName!='Quản trị viên'){
	            			return '<a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            		}
	            		else{
	            			return '';
	            		}
	            		
	            }},  
	      ]
   	
	});
	
	 $('#tableUser').on("click",'.btn-delete',function(){
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
			    	url:"/user/delete/"+id,
			    	success: function(){ 
                       $('#tableUser').DataTable().ajax.reload(); 
                       swal("Đã xóa!", "Trở lại trang quản lí nhân viên", "success"); 
                   },
                   error: function(){
                       swal("Error", "Could not be deleted! :)", "error");   
                   }
			    })
			   
			  }
		});
	 });
	 
	 $('#tableUser').on("click",'.btn-upgrade',function(){
		  var formData = new FormData();
		  var id = $(this).attr('id');
		  swal({
			  title: "Bạn chắc chắn muốn nâng cấp",
			  icon: "warning",
			  buttons: true,
			  dangerMode: true,
			})
			.then((willDelete) => {
			  if (willDelete) {
			    $.ajax({
			    	type:"PUT",
			    	processData : false,
					contentType : false,
			    	url:"/user/upgrade/"+id,
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
	 
});