<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   
   <title>文件上传</title>
	<!--[if lt IE 9]>
	    <meta http-equiv="refresh" content="0;ie.html" />
	<![endif]-->

    <link href="${pageContext.request.contextPath }/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/static/hadmin/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/hadmin/js/layer/skin/layer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/index.css" type="text/css">

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
				<!-- <h1 class="page-title">文件上传</h1> -->
				<div class="content clearfix">
				    <!-- 左侧表单 -->
				    <div class="left-box col-sm-6">
				        <h3>点击下方上传文件</h3>
					    <form class="upload" action="${pageContext.request.contextPath }/upload/upload.do"
							method="post" enctype="multipart/form-data">
						<!-- 	<textarea rows="2" cols="4" name="title"></textarea> -->
							<input type="text" value="" name="title" class="send-title" placeholder="请输入公告标题">
							<input id="up_img" name="upload" type="file"> 
							<div class="send com-btn">
						        <button id="submit" onclick="return uplaod()">提交</button>
						   </div>
							<!-- <input
								type="submit" value="提交" onclick="return uplaod()"> -->
						</form>
					</div>
					<!-- 右侧可上传的图标 -->
					<div class="right-box col-sm-6">
					   <h3>可上传的文件类型</h3>
					   <span class="circle1">Word</span>
					   <span class="circle2">Excel</span>
					   <span class="circle3">ppt</span>
					</div>
					<div class="bottom-box">
						<img src="${pageContext.request.contextPath }/static/css/timg.jpg">
					</div>
				</div>
				
			</body>
				
		    </div>
        </div>
        

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath }/static/jquery/jquery-1.9.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/metisMenu/jquery.metisMenu.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath }/static/hadmin/js/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath }/static/hadmin/js/hAdmin.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/paging.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/index.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/show.js"></script>
    <c:choose>
			<c:when test="${stute=='1' }">
				<script>
					alert("上传成功！");
					//layer.msg('上传成功！');
					location.href = "/phoneShow/page/show.do";
				</script>
			</c:when>
			<c:when test="${stute=='2' }">
				<script>
					alert("上传失败！");
					//layer.msg('上传失败！');
				</script>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
</body>
</html>

<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<c:choose>
		<c:when test="${stute=='1' }">
			<script>
				alert("上传成功！");
				location.href = "/phoneShow/page/show.do";
			</script>
		</c:when>
		<c:when test="${stute=='2' }">
			<script>
				alert("上传失败！");
			</script>
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
		<input id="up_img" name="upload" type="file"> <input
			type="submit" value="提交" onclick="return uplaod()">
	</form>
</body>
</html> --%>