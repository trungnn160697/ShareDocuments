function readURL(input) {

	if (input.files && input.files[0]) {
		var reader = new FileReader();

		reader.onload = function(e) {
			$('#user_image').attr('src', e.target.result);
		}

		reader.readAsDataURL(input.files[0]);
	}
}
	$("#image").change(function() {
		readURL(this);
});


$(document).ready(function(){
	
	$("#button_change_password").click(function(){
		var formData = new FormData();
		formData.append("id",$("#id-user-login").val());
		formData.append("currentPassword",$("#current_password").val());
		formData.append("newPassword",$("#new_password").val());
		formData.append("confirmNewPassword",$("#confirm_new_password").val());
		$.ajax({
			type : 'POST',
			url : "/change-password",
			data : formData,
			processData : false,
			contentType : false,
			success : function(result) {
				var message = result.message.message;
				var code = result.message.code;
				if(code == 200){
					swal("Thay đổi mật khẩu thành công.", "" , "success");
					$('#current_password').val('');
					$('#new_password').val('');
					$('#confirm_new-password').val('');
				}
				else{
					swal("Thay đổi mật khẩu thất bại",message,"error")
				} 
			},
			error : function() {
				swal({
					  title: "Lỗi",
					  text: "Không thể thay đổi mật khẩu",
					  icon: "error",
					  buttons: true,
					  dangerMode: true,
					})
					
			}
		});
	});
	
	$("#button_update_info_user").click(function(){
		var formData = new FormData();
		formData.append("id",$("#id_user_login").val());
		formData.append("fullName",$("#fullName").val());
		formData.append("email",$("#email").val());
		formData.append("phone",$("#phone").val());
		formData.append("image",document.getElementById("image").files[0]);
		$.ajax({
			type : 'POST',
			url : "/change-info",
			data : formData,
			processData : false,
			contentType : false,
			success : function(result) {
				var message = result.message.message;
				var code = result.message.code;
				if(code == 200){
					swal("Cập nhật thông tin thành công.", "" , "success")
					.then((value) => {
						$('#avarta-user').attr({
							'src': 'http://localhost:8888/display?'+result.userDto.image,
							});
						setTimeout(function() {
							window.location.href = '/';
							}, 1500);
					});
				}
				else{
					swal("Cập nhật thông tin thất bại",message,"error")
				} 
			},
			error : function() {
				swal({
					  title: "Lỗi",
					  text: "Không thể thay đổi thông tin",
					  icon: "error",
					  buttons: true,
					  dangerMode: true,
					})
					
			}
		});
	});
	
	
});
