<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!--左侧导航开始-->
 <nav class="navbar-default navbar-static-side" role="navigation">
     <div class="nav-close"><i class="fa fa-times-circle"></i>
     </div>
     <div class="sidebar-collapse">
         <ul class="nav" id="side-menu">
             <li class="nav-header">
                 <div class="dropdown profile-element">
                     <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                         <span class="clear">
                             <span class="block m-t-xs" style="font-size:20px;">
                                 <strong class="font-bold">Logo</strong>
                             </span>
                         </span>
                     </a>
                 </div>
                 <div class="logo-element">Logo
                 </div>
             </li>
             <li>
                 <a class="J_menuItem" href="${pageContext.request.contextPath }/page/show.do">
                      <i class="fa fa-edit"></i>
                     <span class="nav-label">上传文件</span>
                 </a>
             </li>
             <li>
                 <a href="${pageContext.request.contextPath }/page/alloffice.do">
                     <i class="fa fa-table"></i>
                     <span class="nav-label">文档管理</span>
                 </a>
             </li>
              <li>
                 <a href="${pageContext.request.contextPath }/page/editor.do">
                      <i class="fa fa-envelope"></i>
                     <span class="nav-label">信息发布</span>
                 </a>
             </li>
             <li>
                 <a href="${pageContext.request.contextPath }/page/user.do">
                      <i class="fa fa-envelope"></i>
                     <span class="nav-label">用户管理</span>
                 </a>
             </li>
         </ul>
     </div>
 </nav>