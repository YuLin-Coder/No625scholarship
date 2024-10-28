<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧导航menu</title>
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/js/sdmenu.js"></script>
<script type="text/javascript">
	// <![CDATA[
	var myMenu;
	window.onload = function() {
		myMenu = new SDMenu("my_menu");
		myMenu.init();
	};
	// ]]>
</script>
<style type=text/css>
html{ SCROLLBAR-FACE-COLOR: #538ec6; SCROLLBAR-HIGHLIGHT-COLOR: #dce5f0; SCROLLBAR-SHADOW-COLOR: #2c6daa; SCROLLBAR-3DLIGHT-COLOR: #dce5f0; SCROLLBAR-ARROW-COLOR: #2c6daa;  SCROLLBAR-TRACK-COLOR: #dce5f0;  SCROLLBAR-DARKSHADOW-COLOR: #dce5f0; overflow-x:hidden;}
body{overflow-x:hidden; background:url(<%=path%>/images/main/leftbg.jpg) left top repeat-y #f2f0f5; width:194px;}
</style>
</head>
<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
<div id="left-top">
	<div><img src="<%=path%>/images/main/member.gif" width="44" height="44" /></div>
    <span>用户：${loginUser[2]}<br>角色:<c:choose>
     <c:when test="${(role eq '学生') or (role eq '管理员') }">${role}</c:when>
     <c:otherwise>${role}[${loginUser[3] }]</c:otherwise>
    </c:choose></span>
</div>
    <div style="float: left" id="my_menu" class="sdmenu">
      <div class="collapsed">
        <span>学生信息管理</span>
          <c:if test="${role!='学生' }">
           <a href="<%=path %>/stu/add.jsp" target="mainFrame" onFocus="this.blur()">学生添加</a>
           <a href="<%=path %>/stuServlet" target="mainFrame" onFocus="this.blur()">学生信息</a>
           </c:if>
            <c:if test="${role eq '学生' }">
                  <a href="<%=path %>/stuServlet" target="mainFrame" onFocus="this.blur()">我的信息</a>
           </c:if>
      </div>
        <c:if test="${role!='学生' }">
      <div>
        <span>教师信息管理</span>
        <c:if test="${role eq '管理员' }">
           <a href="<%=path %>/teacher/add.jsp" target="mainFrame" onFocus="this.blur()">教师添加</a>
           <a href="<%=path %>/teacherServlet" target="mainFrame" onFocus="this.blur()">教师信息</a>
        </c:if>
        <c:if test="${role eq '教师' }">
           <a href="<%=path %>/teacherServlet" target="mainFrame" onFocus="this.blur()">我的信息</a>
        </c:if>
      </div>
      </c:if>
      
      
       <div>
        <span>通知管理</span>
        <c:if test="${role!='学生' }">
           <a href="<%=path %>/notice/add.jsp" target="mainFrame" onFocus="this.blur()">发布通知</a>
        </c:if>
        <a href="<%=path %>/noticeServlet" target="mainFrame" onFocus="this.blur()">通知信息</a>
      </div>
      
       <div>
        <span>奖学金申请</span>
        <c:if test="${role eq '学生' }">
        <a href="<%=path %>/apply/add.jsp" target="mainFrame" onFocus="this.blur()">提交申请</a>
        </c:if>
        <a href="<%=path %>/applyServlet" target="mainFrame" onFocus="this.blur()">奖学金申请信息</a>
      </div>
      
      <c:if test="${role!='学生' }">
       <div>
        <span>奖学金审核</span>
        <a href="<%=path %>/auditServlet?operate=load"  target="mainFrame"  onFocus="this.blur()">学院审核</a>
        <c:if test="${(role eq '管理员') or (loginUser[3] eq '学工办') }">
        <a href="<%=path %>/auditServlet?operate=school" target="mainFrame" onFocus="this.blur()">学校审核</a>
        </c:if>
      </div>
      
       <div>
        <span>奖学金公示</span>
        <a href="<%=path %>/auditServlet?operate=loadTip" target="mainFrame" onFocus="this.blur()">学院公示</a>
         <c:if test="${(role eq '管理员') or (loginUser[3] eq '学工办') }">
        <a href="<%=path %>/auditServlet?operate=schoolTip" target="mainFrame" onFocus="this.blur()">学校公示</a>
        </c:if>
      </div>
      </c:if>
    </div>
</body>
</html>