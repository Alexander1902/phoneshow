<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <link href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/hadmin/js/layer/skin/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/index.css" type="text/css">
    <style>
    body{background:#B4C7D4;}
       .login-content{
          text-align:center;
          margin-top:100px;
       }
        .login-content>div{
          margin-bottom:20px;
        }
       h1{
          line-height:50px;
          color:#3E4B7D;
          margin-bottom:20px;
       }
       label{width:70px;text-align:right;font-size:18px;}
       input{
            width:250px;
			height:45px;
			line-height:45px;
			border:1px solid #3E4B7D;
			border-radius:4px;
			padding-left:10px;
		}
       button{
		width:100px;
		height:40px;
		border:none;
		background:#3E4B7D;
		border-radius:4px;
		color:#fff;
		margin-left:50px;
		margin-bottom:20px;
	}
	 button:hover{
		border:1px solid #3E4B7D;
		background:#fff;
		color:#3E4B7D;
	}
    </style>
</head>
<body>
<div class="login-content">
    <h1>用户登录</h1>
	<div><label>用户名:</label> <input type="text" id="username"></div>
	<div><label>密码：</label> <input type="password" id="password"></div>
	
	<button id="login">登录</button><br>
	
	
	 <a style="margin-left:35px;font-size:16px;" href="${pageContext.request.contextPath }/showAll.jsp">
	                      <i class="fa fa-table"></i>
	                     <span class="nav-label">进入首页</span>
	                 </a> 
	    <a style="margin-left:35px;font-size:16px;" href="${pageContext.request.contextPath }/office/app/ceshi.apk">
	                      <i class="fa fa-table"></i>
	                     <span class="nav-label">下载app</span>
	                 </a> 
                 
 </div>
<!-- 全局js -->
    <script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/layer/layer.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			var username=$("#username").val();
			var password=$("#password").val();
			var usernameencode=encodeURI(username);
			var json={"username":usernameencode,"password":password};
			var _param={
					url: "/phoneShow/page/login.do",
					type: "POST",
					datatype: 'json',
					data:json,
					scriptCharset:'UTF-8',
					beforeSend:function(XMLHttpRequest){ 
			              //alert('远程调用开始...'); 
						  layer.load(2);
			        }, 
					success: function(data){
						layer.closeAll('loading');
						if(data.stute==1){
							location.href="${pageContext.request.contextPath }/page/alloffice.do";
						}else{
							alert('用户名或密码错误，请重新输入');
						}
					}
				};
				
				$.ajax(_param);
		})
	});
</script>
</body>
</html>