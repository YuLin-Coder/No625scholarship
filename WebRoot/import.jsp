<%@page import="com.dao.BaseDAO"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:directive.page import="com.util.ReadExcel" />
<jsp:directive.page import="com.jspsmart.upload.SmartUpload" />
<jsp:directive.page import="java.io.File" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'chengji_import.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<% 
		SmartUpload su = new SmartUpload(); 
		su.initialize(pageContext); 
		su.upload(); 
 
		String fileName = su.getFiles().getFile(0).getFileName(); 
 
		String dest = application.getRealPath("/upload"); 
		su.save(dest); 
 
		File file = new File(dest, fileName); 
		String[][] result = ReadExcel.getData(file, 1); 
 
		BaseDAO dao = new BaseDAO(); 
		for (int i = 0; i<result.length; i++) { 
			dao.operate("nsert into pay values(null,?,?,?,?,?)",new Object[]{result[i][0], result[i][1], result[i][2],result[i][3], null}); 
		} 
	%>
	<script>
       alert('数据导入成功!');
       window.leocation.href="<%=path%>/index.jsp";
	</script>
</body>
</html>
