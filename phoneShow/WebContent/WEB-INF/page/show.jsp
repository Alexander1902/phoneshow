<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<c:choose>
	<c:when test="${stute=='1' }">
		<script>alert("上传成功！");
			location.href="/phoneShow/page/show.do";
		</script>
	</c:when>
	<c:when test="${stute=='2' }">
		<script>alert("上传失败！");</script>
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>
	<h1>你好，世界！</h1>
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="true">
			Dropdown <span class="caret"></span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
			<li><a href="#">Action</a></li>
			<li><a href="#">Another action</a></li>
			<li><a href="#">Something else here</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Separated link</a></li>
		</ul>
	</div>
	<hr>
	<h1>文件上传</h1>
	<form action="${pageContext.request.contextPath }/upload/upload.do"
		method="post" enctype="multipart/form-data">
		<textarea rows="2" cols="4" name="title"></textarea>
		<input id="up_img" name="upload" type="file">
		<input type="submit" value="提交">
	</form>
</body>
</html>