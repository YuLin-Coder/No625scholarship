<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主要内容区main</title>
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
<link href="<%=path%>/css/main.css" type="text/css" rel="stylesheet" />
<link rel="shortcut icon" href="<%=path%>/images/main/favicon.ico" />
<style>
body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
#searchmain{ font-size:12px;}
#search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF; float:left}
#search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
#search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
#search form input.text-but{height:24px; line-height:24px; width:55px; background:url(<%=path%>/images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
#search a.add{ background:url(<%=path%>/images/main/add.jpg) no-repeat -3px 7px #548fc9; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF; float:right}
#search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
#main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
#main-tab th{ font-size:12px; background:url(<%=path%>/images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;}
#main-tab td{ font-size:12px; line-height:40px;}
#main-tab td a{ font-size:12px; color:#548fc9;}
#main-tab td a:hover{color:#565656; text-decoration:underline;}
.bordertop{ border-top:1px solid #ebebeb}
.borderright{ border-right:1px solid #ebebeb}
.borderbottom{ border-bottom:1px solid #ebebeb}
.borderleft{ border-left:1px solid #ebebeb}
.gray{ color:#dbdbdb;}
td.fenye{ padding:10px 0 0 0; text-align:right;}
.bggray{ background:#f9f9f9}
</style>
</head>
<body>
<!--main_top-->
<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
  <tr>
    <td align="left" valign="top">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
  		<tr>
   		 <td width="90%" align="left" valign="middle">
         </td>
  		</tr>
	</table>
    </td>
  </tr>
  <tr>
    <td align="left" valign="top">
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
      <tr>
        <th align="center" valign="middle" class="borderright">学号</th>
        <th align="center" valign="middle" class="borderright">姓名</th>
        <th align="center" valign="middle" class="borderright">学院</th>
        <th align="center" valign="middle" class="borderright">班级</th>
        <th align="center" valign="middle" class="borderright">申请表</th>
        <th align="center" valign="middle" class="borderright">审核状态</th>
        <th align="center" valign="middle" class="borderright">提交日期</th>
        <th align="center" valign="middle">操作</th>
      </tr>
      <c:forEach var="data" items="${all }">
      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
        <td align="center" valign="middle" class="borderright borderbottom">${data[1] }</td>
        <td align="center" valign="middle" class="borderright borderbottom">${data[2] }</td>
        <td align="center" valign="middle" class="borderright borderbottom">${data[3] }</td>
        <td align="center" valign="middle" class="borderright borderbottom">${data[4] }</td>
        <td align="center" valign="middle" class="borderright borderbottom">
             <a href="<%=path %>/upload/${data[5] }">${data[5] }</a>
        </td>
         <td align="center" valign="middle" class="borderright borderbottom">${data[6] }</td>
         <td align="center" valign="middle" class="borderright borderbottom"><fmt:formatDate value="${data[7] }" pattern="yyyy-MM-dd"/></td>
        <td align="center" valign="middle" class="borderbottom">
          <c:if test="${data[6] eq '学院公示通过'}">
           <a href="<%=path %>/auditServlet?operate=schoolyes&id=${data[0]}" target="mainFrame" onFocus="this.blur()" class="add">通过</a><span class="gray">&nbsp;|&nbsp;</span>
           <a href="<%=path %>/auditServlet?operate=schoolno&id=${data[0]}" target="mainFrame" onFocus="this.blur()" class="add">未通过</a>
           </c:if>
    
        </td>
      </tr>
      </c:forEach>
    </table>
    </td>
    </tr>
</table>
</body>
</html>