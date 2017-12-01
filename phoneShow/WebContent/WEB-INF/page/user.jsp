<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/hadmin/js/layer/skin/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/index.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/alloffice.css" type="text/css">
</head>
<body>
<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
         <%@ include file="comLeftNav.jsp" %>
         <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
					<div class="show-table">
					    <div class="add com-btn">
					        <button onclick="addHtml()">添加用户</button>
					     </div>
						<table class="table table-hover">
							<thead>
								<td>编号</td>
								<td>用户名</td>
								<td>密码</td>
								<td>操作</td></thead>
							<tbody id="userTable"></tbody>
						</table>
					</div>	
				 </div>
		    </div>
        </div>

<!-- 全局js -->
    <script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/datapicker/bootstrap-datetimepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/layer/layer.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/user.js"></script>
</body>
</html>