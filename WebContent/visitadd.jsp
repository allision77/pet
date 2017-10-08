<%@page import="entity.Vet"%>
<%@page import="java.util.List"%>
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
<title>添加宠物病历</title>
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
			<form action="VisitServlet" method="post">
				<table>
					<tr>
						<td>宠物姓名</td>
						<td><input type="text" name="cname" disabled="disabled"
							value="<%=request.getParameter("petname")%>" /> <input
							type="hidden" name="petId"
							value="<%=request.getParameter("petId")%>" /> <input
							type="hidden" name="cid"
							value="<%=request.getParameter("cid")%>" /></td>
					</tr>
					<tr>
						<td>主治医生</td>
						<td><select name="vetId">
								<%
									List<Vet> vets = (List<Vet>) request.getAttribute("vets");
									for (Vet v : vets) {
								%>
								<option value="<%=v.getId()%>">
									<%=v.getName()%>
								</option>
								<%
									}
								%>
						</select></td>
					</tr>
					<tr>
						<td>病情描述</td>
						<td><textarea rows="4" name="description"></textarea></td>
					</tr>
					<tr>
						<td>治疗方案</td>
						<td><textarea rows="4" name="treatment"></textarea></td>
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