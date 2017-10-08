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
<title>添加宠物</title>
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
			<form action="PetServlet?m=add" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<td>主人姓名</td>
						<td><input type="text" name="cname" disabled="disabled"
							value="<%=request.getParameter("cname")%>" />
							<input type="hidden" name="cid" value="<%=request.getParameter("cid")%>"/>
							</td>
					</tr>
					<tr>
						<td>宠物姓名</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>出生日期</td>
						<td><input type="text" name="birthdate" /></td>
					</tr>
					<tr>
						<td>宠物照片</td>
						<td><input type="file" name="photo" /></td>
					</tr>


					<tr class="cols2">
						<td colspan="2"><input type="submit" value="添加" /><input
							type="reset" value="重置 " /></td>
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