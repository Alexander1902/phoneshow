<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
	<span id="title"></span><br>
	日期：<span id="date"></span><br>
	内容：<span id="content"></span>
</body>
</html>