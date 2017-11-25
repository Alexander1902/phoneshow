<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
 *{
     margin:0;
     padding:0;
 }
  body{
     padding:20px;
     background:#B4C7D4;
     font-size:14px;
  }
  #title{
     font-size:18px;
     font-weight:bold;
     color:#256996;
     margin:20px 0;
  }
  #content{
    padding-left:30px;
  }
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/showNotice.js"></script>
</head>
<body>
<input type="hidden" value="${param.id }" id="ID">
	<h2 id="title"></h2>
	日期：<span id="date"></span><br>
	内容：<div id="content"></div>
</body>
</html>