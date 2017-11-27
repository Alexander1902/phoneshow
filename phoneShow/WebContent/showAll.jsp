<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
   <title>文档管理</title>
	<!--[if lt IE 9]>
	    <meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->

    <link href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <%--   <link href="${pageContext.request.contextPath }/static/hadmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/style.css" rel="stylesheet"> --%>
    <link href="${pageContext.request.contextPath }/static/hadmin/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/hadmin/js/layer/skin/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/index.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/alloffice.css" type="text/css">

</head>
<body>
<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
         
            <div class="row J_mainContent" id="content-main">
		            <h1 class="page-title">文档管理</h1>
		            <div class="qry-group clearfix">
			            <div class="select-type col-sm-2">
			                <label>类型：</label>
			                <select id="type">
							     <option value="">请选择</option>
								<option value="1">Word</option>
								<option value="2">Excel</option>
								<option value="3">公告</option>
								<option value="4">PPT</option>
					        </select>
			            </div>
						<div class="col-sm-2">
						    <label> 标题：</label>
						    <input type="text" id="title">
						</div>
						
						 <div class="time col-sm-3">
						     <label> 开始时间：</label>
							<input type="text" value="" id="start">
						     <!--<input type="date" id="start">  -->
						 </div>
						<div class="time col-sm-3">
						    <label> 结束时间：</label>
						    <input type="text" value="" id="end">
						   <!--<input type="date" id="end">  -->
						</div>
						<div class="com-btn col-sm-2">
						   <button id="seach" >查询</button>
						   <button id="clearbtn">刷新</button>
						</div>
						
					</div>
					<div class="show-table">
						<table class="table table-hover">
							<thead>
								<td>编号</td>
								<td>标题</td>
								<td>类型</td>
								<td>浏览</td>
								<td>时间</td>
								<td>操作</td>
							<tbody id="officeTable"></tbody>
						</table>
						<div id="page" class="page_div"></div>
					</div>
				 </div>
		    </div>
        </div>
        

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.zh-CN.js"></script>
<%--     <script src="${pageContext.request.contextPath }/static/hadmin/js/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/slimscroll/jquery.slimscroll.min.js"></script> --%>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <!-- <script src="${pageContext.request.contextPath }/static/hadmin/js/hAdmin.js"></script> -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/paging.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/showAll.js"></script>
</body>
</html>