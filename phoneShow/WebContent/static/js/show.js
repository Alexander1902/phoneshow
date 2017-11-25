function uplaod(){
			//alert($("#up_img").val());
			var name = $("#up_img").val();
			var expan = name.substring(name.lastIndexOf(".")+1);
			if(expan=="doc"||expan=="DOC"||expan=="XLS"||expan=="xls"||expan=="PPT"||expan=="ppt"){
				return true;
			}else{
				//alert("请上传doc、xls或ppt文件格式");
				layer.msg("请上传doc、xls或ppt文件格式");
				return false;
			}
		}