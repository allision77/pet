<%@page import="java.net.URLEncoder"%>
<%@page import="entity.Pet"%>
<%@page import="entity.User"%>
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
<title>客户详细信息</title>
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
			<%
				User user = (User) request.getAttribute("user");
			%>
			<table>
				<tr>
					<td>客户姓名</td>
					<td><input type="text" name="name" disabled="disabled"
						value="<%=user.getName()%>" /></td>
				</tr>
				<tr>
					<td>联系电话</td>
					<td><input type="text" name="tel" disabled="disabled"
						value="<%=user.getTel()%>" /></td>
				</tr>
				<tr>
					<td>家庭地址</td>
					<td><input type="text" name="address" disabled="disabled"
						value="<%=user.getAddress()%>" /></td>
				</tr>
				<tr class="cols2">
					<td colspan="2" class="info"><a
						href="PetServlet?m=toAdd&cid=<%=user.getId()%>&cname=<%=URLEncoder.encode(user.getName(), "utf-8")%>">添加新宠物</a></td>
				</tr>
				<tr class="cols2">
					<td colspan="2" class="info"><%=request.getAttribute("msg") == null ? "" : request
					.getAttribute("msg")%></td>
				</tr>
			</table>
			<hr>
			<table class="wide">
				<thead>
					<tr>
						<td colspan="2">宠物信息</td>
						<td>操作</td>
					</tr>
				</thead>
				<%
					for (Pet pet : user.getPets()) {
				%>
				<tr>
					<td><img alt="" src="<%=pet.getPhoto()%>" height="64"
						width="64"></td>
					<td>姓名:<%=pet.getName()%>,生日:<%=pet.getBirthdate()%></td>
					<td><a
						href="PetServlet?m=delete&petId=<%=pet.getId()%>&cid=<%=pet.getOwnerId()%>">删除</a>|<a
						href="VisitServlet?m=showHistory&petId=<%=pet.getId()%>">病历</a>|
						<a
						href="VisitServlet?m=toAdd&petId=<%=pet.getId()%>&cid=<%=pet.getOwnerId()%>&petname=<%=URLEncoder.encode(pet.getName(), "utf-8")%>">添加病历</a></td>
				</tr>

				<%
					}
				%>



			</table>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>