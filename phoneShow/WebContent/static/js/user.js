$(function(){//初始化页面应该调getAllUser()，测试调showAllUser(data)
	/*var data=['{"id":12,"username":"张珊","password":"123456"}',
	          '{"id":13,"username":"张四","password":"123456"}',
	          '{"id":13,"username":"张3","password":"123456"}',
	          '{"id":13,"username":"张4","password":"123456"}',
	          '{"id":14,"username":"张5","password":"123456"}'];
	var json=new Array(5);
	for(var o=0;o<data.length;o++){
		var obj=eval('(' + data[o] + ')');
		json[o]=obj;
	}
	showAllUser(json);*/
	getAllUser();
});
function getAllUser(){
	var _param={
			url: "/phoneShow/page/getUserInfo.do",
			type: "GET",
			datatype: 'json',
			scriptCharset:'UTF-8',
			beforeSend:function(XMLHttpRequest){ 
				  layer.load(2);
	        },
			success: function(data){//调用showAllUser(data)
				layer.closeAll('loading');
				showAllUser(data);
			}
		};
		$.ajax(_param);
}

function showAllUser(data){
	for(var i=0;i<data.length;i++){
		var html="<tr>";
		html+="<td>"+(i+1);
		html+="</td>";
		html+="<td>"+data[i].username;
		html+="</td>";
		html+="<td><span id=\""+data[i].id+"_span\">"+data[i].password;
		html+="</span><div id=\""+data[i].id+"_div\"  style=\"display:none;\"><input id=\""+data[i].id+"_input\" value=\""+data[i].password+"\"><button onclick=\"update('"+data[i].id+"','"+data[i].username+"')\">保存</button></div</td>";
		html+="<td><button onclick=\"deleteById('"+data[i].username+"','"+data[i].id+"')\">删除</button>&nbsp;&nbsp;" +
				"<button onclick=\"modifyPass("+data[i].id+")\">修改密码</button>";
		html+="</td>";
		html+="</tr>";
		$("#userTable").append(html);
	}
}
function deleteById(name,id){//删除后调用getAllUser()
	var cf=confirm("删除用户"+name+"吗？");
	if(cf){
		/*var json={"id":id};
		var _param={
				url: "/phoneShow/page/*.do",
				type: "GET",
				datatype: 'json',
				data:json,
				scriptCharset:'UTF-8',
				beforeSend:function(XMLHttpRequest){ 
					  layer.load(2);
		        }, 
				success: function(data){//返回stute 数字1成功，2失败
					if(data.stute==1){
						layer.msg("删除成功！");
						getAllUser();
					}else{
						layer.msg("删除失败！");
					}
				}
			};
			$.ajax(_param);*/
	}
}
function modifyPass(id){
	$("#"+id+"_span").hide();
	$("#"+id+"_div").show();
	$("#"+id+"_input").focus();
}
function update(id,name){
	var newpass=trim($("#"+id+"_input").val());
	var oldpass=trim($("#"+id+"_span").html());
	console.log("新密码："+newpass+",老密码："+oldpass);
	if(newpass!=null&&newpass!=""&&oldpass!=newpass){
		if(!check(newpass)){
			alert("密码请输入数字");
			$("#"+id+"_input").focus();
			return;
		}
		if(newpass.length<6){
			alert("请输入6位以上数字");
			$("#"+id+"_input").focus();
			return;
		}
		var cf=confirm("确认修改"+name+"密码吗？");
		if(cf){
			alert("修改了密码");
			$("#"+id+"_span").html(newpass);
			/*var json={"id":id,"newpass":newpass};
			var _param={
					url: "/phoneShow/page/*.do",
					type: "GET",
					datatype: 'json',
					data:json,
					scriptCharset:'UTF-8',
					beforeSend:function(XMLHttpRequest){ 
						  layer.load(2);
			        }, 
					success: function(data){//返回stute 数字1成功，2失败
						if(data.stute==1){
							layer.msg("修改成功！");
						}else{
							layer.msg("修改失败！");
						}
						$("#"+id+"_span").show();
						$("#"+id+"_input").hide();
					}
				};
				$.ajax(_param);*/
		}else{}
	}
	$("#"+id+"_span").show();
	$("#"+id+"_div").hide();
}
function addHtml(){
	layer.open({
		  title: '添加用户',
	      type: 1,
	      area: ['450px', '160px'],
	      shadeClose: true, //点击遮罩关闭
	      content: '\<\div style="padding:20px;">用户名：<input id="newusername" type="text">密码：<input id="newpassword" type="text"><button onclick="adduser()">添加</button>\<\/div>'
	    });
}
function adduser(){
	var username=trim($("#newusername").val());
	var newpassword=trim($("#newpassword").val());
	console.log("用户名："+username+",密码："+newpassword);
	if(username==null||username==""||newpassword==null||newpassword==""){
		alert("用户名或密码不能为空！");
		return;
	}else{
		if(!check(newpassword)){
			alert("密码请输入数字");
			return;
		}
		if(newpassword.length<6){
			alert("请输入6位以上数字");
			return;
		}
		//username需要转字符集，防止乱码，后台解字符集 var titleencode=encodeURI(title);
		var usernameencode=encodeURI(username);
		var json={"username":usernameencode,"password":newpassword};
		var _param={
				url: "/phoneShow/page/userInfo.do",
				type: "POST",
				datatype: 'json',
				data:json,
				scriptCharset:'UTF-8',
				beforeSend:function(XMLHttpRequest){ 
					  layer.load(2);
		        }, 
				success: function(data){
					layer.closeAll('loading');
					
						if(data.stute==1){
							alert("添加成功！");
						}else{
							alert("添加失败！");
						}
						layer.close(layer.index);
				}
			};
			$.ajax(_param);
	}
	layer.close(layer.index);
}
function trim(value){
	return value.replace(/(^\s*)|(\s*$)/g,"");
}
function check(obj){
	var reg = new RegExp("^[0-9]*$");
	 return reg.test(obj);
}