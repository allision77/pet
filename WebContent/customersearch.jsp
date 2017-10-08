<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<link rel="stylesheet" href="styles.css">
<title>客户查询</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<a id="quit" href="QuitServlet">退出</a>
			<h1>社区宠物诊所</h1>
			<ul id="menu">
				<li><a href="vetsearch.jsp">医生管理</a></li>
				<li><a href="customersearch.jsp">客户管理</a></li>
			</ul>
		</div>
		<div id="content">
			<form action="CustomerServlet?m=search" method="post">
				<table>
					<tr>
						<td>客户姓名</td>
						<td><input type="text" name="customerName"/></td>
					</tr>

					<tr class="cols2">
						<td colspan="2"><input type="submit" value="查询" /><input
							type="reset" value="重置" /></td>
					</tr>
					<tr class="cols2">
						<td colspan="2"><a href="CustomerServlet?m=toAdd">添加客户</a></td>
					</tr>
					<tr class="cols2">
						<td colspan="2" class="info"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></td>
					</tr>
				</table>
			</form>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>