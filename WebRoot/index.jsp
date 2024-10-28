<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="images/favicon.ico" />
<link href="<%=path%>/css/css.css" type="text/css" rel="stylesheet" />
</head>
<frameset rows="95,*,30" cols="*" frameborder="no" border="0"
	framespacing="0">
	<frame src="<%=path%>/top.jsp" name="topframe" scrolling="no" noresize
		id="topframe" title="topframe" />
	<frameset id="attachucp" framespacing="0" border="0" frameborder="no"
		cols="194,12,*" rows="*">
		<frame scrolling="auto" noresize="" frameborder="no" name="leftFrame"
			src="<%=path%>/menu.jsp"></frame>
		<frame id="leftbar" scrolling="no" noresize="" name="switchFrame"
			src="<%=path%>/swich.html"></frame>
		<frame scrolling="auto" noresize="" border="0" name="mainFrame"
			src="<%=path%>/main.jsp"></frame>
	</frameset>
	<frame src="<%=path%>/bottom.jsp" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" title="bottomFrame" />
</frameset>
<noframes></noframes>

</html>