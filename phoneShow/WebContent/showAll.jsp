<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/jquery/paging.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/static/css/alloffice.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/static/js/showAll.js"></script>
</head>
<body>
	<h1>文档管理</h1>
	类型：
	<select id="type"><option value="">请选择</option>
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option></select> 标题：
	<input type="text" id="title"> 开始时间：
	<input type="date" id="start"> 结束时间：
	<input type="date" id="end">
	<button id="seach">查询</button>
	<button id="clearbtn">刷新</button>
	<table class="table table-hover">
		<tr>
			<th>编号</th>
			<th>标题</th>
			<th>类型</th>
			<th>浏览</th>
			<th>时间</th>
		</tr>
		<tbody id="officeTable"></tbody>
	</table>
	<select id="pagesize" onchange="pageSizeChonge();"><option
			value="10">10</option>
		<option value="5">5</option></select>
	<div value="1 0"></div>
	<div id="page" class="page_div"></div>
</body>
</html>