<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/hadmin/js/layer/skin/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/index.css" type="text/css">
</head>
<body>
用户名：<input type="text" id="username">
密码：<input type="password" id="password"><button id="login">登录</button>
<a href="${pageContext.request.contextPath }/page/alloffice.do">
                      <i class="fa fa-table"></i>
                     <span class="nav-label">文档管理</span>
                 </a>
<!-- 全局js -->
    <script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/layer/layer.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#login").click(function(){
			alert("测试");
			var _param={
					url: "/phoneShow/page/",
					type: "POST",
					datatype: 'json',
					data:json,
					scriptCharset:'UTF-8',
					beforeSend:function(XMLHttpRequest){ 
			              //alert('远程调用开始...'); 
						  layer.load(2);
			        }, 
					success: function(data){
						if(data.stute==1){
							location.href="${pageContext.request.contextPath }/page/alloffice.do";
						}else{
							layer.msg('用户名或密码错误，请重新输入');
						}
					}
				};
				
				$.ajax(_param);
		})
	});
</script>
</body>
</html>