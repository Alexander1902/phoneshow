<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>发布公告</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
         <%@ include file="comLeftNav.jsp" %>
         <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                    </div>
                </nav>
                <div class="row J_mainContent" id="content-main">
                   <h1>信息发布</h1>
					<textarea rows="5" cols="50" id="title"></textarea><button id="submit">提交</button>
					<script id="editor" type="text/plain"
						style="width:1024px;height:500px;"></script>
                </div>
            </div>
    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath }/static/hadmin/js/hAdmin.js"></script>
    <script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/ueditor/ueditor.all.js"> </script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath }/static/js/editor.js"></script>
</body>

</html>