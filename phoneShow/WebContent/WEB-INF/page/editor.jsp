<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发布公告</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/js/editor.js"></script>
<style type="text/css">
div {
	width: 100%;
}
</style>
</head>
<body>
	<h1>信息发布</h1>
	<textarea rows="5" cols="50" id="title"></textarea><button id="submit">提交</button>
	<script id="editor" type="text/plain"
		style="width:1024px;height:500px;"></script>
</body>
</html>