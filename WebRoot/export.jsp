<%@ page language="java" import="java.util.*,java.io.*"
	pageEncoding="utf-8"%>
<jsp:directive.page import="javax.swing.JOptionPane" />
<jsp:directive.page import="com.jspsmart.upload.SmartUpload"/>
<jsp:directive.page import="com.util.ExportExcel"/>
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

		<title>My JSP 'export.jsp' starting page</title>

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
			List all = (List)request.getAttribute("all");

			ExportExcel<Object> ex = new ExportExcel<Object>();
			String[] headers = {"编号", "支出名称", "支出类型","支出金额","记帐日期"};
			try {
				OutputStream output = new FileOutputStream("c://pay_data.xls");
				ex.exportExcel(headers, all, output);
				output.close();
				
			} catch (Exception e) {

				e.printStackTrace();
			}

			SmartUpload su = new SmartUpload();
			// 初始化
			su.initialize(pageContext);

			su.setContentDisposition(null);

			su.downloadFile("c://pay_data.xls");
			out.clear();
			out = pageContext.pushBody();
		%>
		<script>
		   alert("数据导出成功!");
		</script>
		
	</body>
</html>
