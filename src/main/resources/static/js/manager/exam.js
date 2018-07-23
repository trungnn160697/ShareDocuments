 var ctx = "http://"+$(location).attr('host');
$(document).ready(function(){
	
	var table =  $('#tableExam').DataTable({
    	"processing":true,
    	"serverSide":true,
    	"bSortable": true,
    	 "sort": "position",
	        // Default: Page display length
	        "iDisplayLength": 10,
	        "iDisplayStart": 0,
	       "sAjaxSource":ctx+"/listExam",
	   "columns": [
		  {"data":"id"},
		  {"data":"name"},
		  {"data":"nameSubject"},
		  {"data":"quantityQuestion"},
		  { "data": "action",
	            	render: function (data, type, row) {	
	            		if(row.quantityQuestion<50){
	            			return ' <a  data-toggle="modal" class="btn btn-success  btn-add-question button_action" href="#modal_add_question" id="'+row.id+'"  title="Thêm câu hỏi"><i class="fa fa-plus" aria-hidden="true"></i></a>' 
	            				+' <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            		}
	            		return ' <a id="'+row.id+'"  class="btn btn-danger btn-delete button_action" href="javascript:;" title="Xóa" ><i id="deleteUnit" class="fa fa-trash-o" aria-hidden="true"></i></a>';
	            	
	            	}},  
	      ]
   	
	});
	
	 $('#tableExam').on("click",'.btn-delete',function(){
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
			    	url:"/exam/delete/"+id,
			    	success: function(){ 
                       $('#tableExam').DataTable().ajax.reload(); 
                       swal("Đã xóa!", "Trở lại trang quản lí đề thi", "success"); 
                   },
                   error: function(){
                       swal("Error", "Could not be deleted! :)", "error");   
                   }
			    })
			   
			  }
		});
	 });
	 
	 $('#tableExam').on("click",'.btn-add-question',function(){
		  var id = $(this).attr('id');
		  $('#exam_id_text').text(id);
		  $("#exam_id").val(id);
	 });
	 
	 $('#button_add_question').click(function(){
		  var formData = new FormData();
		  formData.append("examId",$('#exam_id').val());
		  formData.append("contentQuestion",$('#content_question').val());
		  formData.append("answerA",$('#answer_a').val());
		  formData.append("answerB",$('#answer_b').val());
		  formData.append("answerC",$('#answer_c').val());
		  formData.append("answerD",$('#answer_d').val());
		  formData.append("answerTrue",$('#answer_true').val());
		  formData.append("image",document.getElementById("image_question").files[0]);
		  $.ajax({
		    	type:"POST",
		    	processData : false,
				contentType : false,
		    	url:"/question/add",
		    	data:formData,
		    	success: function(result){ 
		    		$('#modal_add_question').modal('hide');
		    		$('#tableExam').DataTable().ajax.reload();
		    		$('#content_question').val();
		    		$('#answer_a').val('');
		    		$('#answer_b').val('');
		    		$('#answer_c').val('');
		    		$('#answer_d').val('');
		    		$('#answer_true').val('0');
		    		swal("Thành công!", "Trở lại trang quản lí đề thi", "success"); 
		
		    },
             error: function(){
                 swal("Lỗi", "Không thể thêm mới câu hỏi", "error");   
             }
		    }) ;  
	 });
	 
	 $('#button_add_exam').click(function(){
		  var formData = new FormData();
		  formData.append("name",$('#name_exam').val());
		  formData.append("subject",$('#name_subject').val());
		  formData.append("numberQuestion",$('#number_question').val());
		  $.ajax({
		    	type:"POST",
		    	processData : false,
				contentType : false,
		    	url:"/exam/add-exam",
		    	data:formData,
		    	success: function(result){ 
		    		$('#modal_id_add').modal('hide');
		    		$('#tableExam').DataTable().ajax.reload();
		    		if(result.message.code==200){
		    			swal("Thêm mới thành công!", "Trở lại trang quản lí môn học", "success"); 
			    		$('#name_exam').val('');
			    		$('#name_subject').val('0');
			    		$('#numberQuestion').val('0');
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