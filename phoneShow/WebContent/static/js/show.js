function uplaod(){
			//alert($("#up_img").val());
			var name = $("#up_img").val();
			var expan = name.substring(name.lastIndexOf(".")+1);
			if(expan=="doc"||expan=="DOC"||expan=="XLS"||expan=="xls"){
				return true;
			}else{
				alert("请上传doc或xls文件格式");
				return false;
			}
		}