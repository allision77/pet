<%@page import="entity.Speciality"%>
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
<title>医生查询结果</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<a id="quit" href="QuitServlet">退出</a>
			<h1>社区宠物诊所</h1>
			<ul id="menu">
				<li><a href="#">医生管理</a></li>
				<li><a href="#">客户管理</a></li>
			</ul>
		</div>
		<div id="content">
			<table>
				<tr>
					<td>医生姓名</td>
					<td>专业特长</td>
				</tr>
				<%
					List<Vet> vets = (List<Vet>) request.getAttribute("vets");
					for (Vet vet : vets) {
				%>
				<tr class="result">
					<td><%=vet.getName() %></td>
					<td>
					<%
						for(Speciality spec:vet.getSpecs()){
							out.print(spec.getName()+"&nbsp;");
						}
					%>
					</td>
				</tr>

				<%
					}
				%>
				<tr class="cols2">
					<td colspan="2"><input type="button" value="返回" onclick="history.back(-1);" /></td>
				</tr>
				<tr class="cols2">
					<td colspan="2" class="info"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></td>
				</tr>
			</table>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>