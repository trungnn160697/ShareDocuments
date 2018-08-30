$(document).ready(
		function() {

			$("#rate_1").click(
					function() {
						var id = $("#document_id").val();
						var data = new FormData();
						data.append("rate", 1);
						$.ajax({
							type : "post",
							url : "/rate/" + id,
							data : data,
							processData : false,
							contentType : false,
							success : function(result) {
								$("#rate_1 span").addClass("checked");
								$("#rate_2 span").removeClass("checked");
								$("#rate_3 span").removeClass("checked");
								$("#rate_4 span").removeClass("checked");
								$("#rate_5 span").removeClass("checked");
								$("#rate").text(result);
								swal("Đánh giá thành công!",
										"Cảm ơn đánh giá của bạn!", "success");
							},
							error : function() {
								swal({
									title : "Lỗi",
									icon : "error",
									buttons : true,
									dangerMode : true,
								})

							}
						});

					});

			$("#rate_2").click(
					function() {
						var id = $("#document_id").val();
						var data = new FormData();
						data.append("rate", 2);
						$.ajax({
							type : "post",
							url : "/rate/" + id,
							data : data,
							processData : false,
							contentType : false,
							success : function(result) {
								$("#rate_1 span").addClass("checked");
								$("#rate_2 span").addClass("checked");
								$("#rate_3 span").removeClass("checked");
								$("#rate_3 span").removeClass("checked");
								$("#rate_4 span").removeClass("checked");
								$("#rate_5 span").removeClass("checked");
								$("#rate").text(result);
								swal("Đánh giá thành công!",
										"Cảm ơn đánh giá của bạn!", "success");
							},
							error : function() {
								swal({
									title : "Lỗi",
									icon : "error",
									buttons : true,
									dangerMode : true,
								})

							}
						});

					});

			$("#rate_3").click(
					function() {
						var id = $("#document_id").val();
						var data = new FormData();
						data.append("rate", 3);
						$.ajax({
							type : "post",
							url : "/rate/" + id,
							data : data,
							processData : false,
							contentType : false,
							success : function(result) {
								$("#rate_1 span").addClass("checked");
								$("#rate_2 span").addClass("checked");
								$("#rate_3 span").addClass("checked");
								$("#rate_3 span").removeClass("checked");
								$("#rate_4 span").removeClass("checked");
								$("#rate_5 span").removeClass("checked");
								$("#rate").text(result);
								swal("Đánh giá thành công!",
										"Cảm ơn đánh giá của bạn!", "success");
							},
							error : function() {
								swal({
									title : "Lỗi",
									icon : "error",
									buttons : true,
									dangerMode : true,
								})

							}
						});

					});

			$("#rate_4").click(
					function() {
						var id = $("#document_id").val();
						var data = new FormData();
						data.append("rate", 4);
						$.ajax({
							type : "post",
							url : "/rate/" + id,
							data : data,
							processData : false,
							contentType : false,
							success : function(result) {
								$("#rate_1 span").addClass("checked");
								$("#rate_2 span").addClass("checked");
								$("#rate_3 span").addClass("checked");
								$("#rate_4 span").addClass("checked");
								$("#rate_5 span").removeClass("checked");
								$("#rate").text(result);
								swal("Đánh giá thành công!",
										"Cảm ơn đánh giá của bạn!", "success");
							},
							error : function() {
								swal({
									title : "Lỗi",
									icon : "error",
									buttons : true,
									dangerMode : true,
								})

							}
						});

					});

			$("#rate_5").click(
					function() {
						var id = $("#document_id").val();
						var data = new FormData();
						data.append("rate", 5);
						$.ajax({
							type : "post",
							url : "/rate/" + id,
							data : data,
							processData : false,
							contentType : false,
							success : function(result) {
								$("#rate_1 span").addClass("checked");
								$("#rate_2 span").addClass("checked");
								$("#rate_3 span").addClass("checked");
								$("#rate_4 span").addClass("checked");
								$("#rate_5 span").addClass("checked");
								$("#rate").text(result);
								swal("Đánh giá thành công!",
										"Cảm ơn đánh giá của bạn!", "success");
							},
							error : function() {
								swal({
									title : "Lỗi",
									icon : "error",
									buttons : true,
									dangerMode : true,
								})

							}
						});

					});
		});