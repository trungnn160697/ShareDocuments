$(document).ready(function() {

	$('#register').click(function() {
		$("#form-login").hide();
		$("#register-form").show();
		$("#form-forgot-password").hide();
	});

	$('.login').click(function() {
		$("#form-login").show();
		$("#register-form").hide();
		$("#form-forgot-password").hide();
	});

	$('#forgot').click(function() {
		$("#form-login").hide();
		$("#register-form").hide();
		$("#form-forgot-password").show();
	});
	
	$('#submit-forgot').click(function(){
		var formData = new FormData();
		formData.append("email",$("#email-forgot").val());
		$.ajax({
			type : 'POST',
			url : "/forgotPassword",
			data : formData,
			processData : false,
			contentType : false,
			success : function(result) {
				var message = result.message.message;
				var code = result.message.code;
				if(code==200){
					swal("Thay đổi mật khẩu thành công.", message , "success");
				}
				else{
					swal("Thất bại",message,"error")
				} 
			},
			error : function() {
				swal({
					  title: "Lỗi",
					  text: "Không thể lấy lại mật khẩu",
					  icon: "error",
					  buttons: true,
					  dangerMode: true,
					})
					
			}
		});
	});
	
	$('#submit-register').click(function() {
		var formData = new FormData();
		formData.append("username", $('#username').val());
		formData.append("password", $('#password').val());
		formData.append("fullName", $('#fullName').val());
		formData.append("email", $('#email').val());
		formData.append("phone", $('#phone').val());
		formData.append("image",document.getElementById("image").files[0]);
		$.ajax({
			type : 'POST',
			url : "/register",
			data : formData,
			processData : false,
			contentType : false,
			success : function(result) {
				var message = result.message.message;
				var code = result.message.code;
				if (code == 200) {		
					swal({
						  title: "Thêm mới thành công",
						  text: "Đăng nhập ngay",
						  icon: "success",
						  buttons: true,
						  dangerMode: true,
						})
						.then((willDelete) => {
							window.location.href = "/login";
						});
					
				} else {
					swal("Thất bại!", message, "error");
				}
			},
			error : function() {
				swal({
					  title: "Lỗi",
					  text: "Không thể tạo tài khoản",
					  icon: "error",
					  buttons: true,
					  dangerMode: true,
					})
					.then((willDelete) => {
						
					});
			}
		});
	});

})