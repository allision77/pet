<%@page import="entity.Speciality"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>添加医生信息</title>
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
			<form action="VetServlet?m=add" method="post">
				<table>
					<tr>
						<td>医生姓名</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>专业特长</td>
						<td><select size="5" multiple="multiple" name="specId">
								<option disabled="disabled">请选择至少一项</option>
								<%
									List<Speciality> specs = (List<Speciality>) request
											.getAttribute("specs");
									for (Speciality s : specs) {
								%>
								<option value="<%=s.getId()%>"><%=s.getName() %></option>
								<%
									}
								%>
						</select></td>
					</tr>

					<tr class="cols2">
						<td colspan="2"><input type="submit" value="保存" /><input
							type="reset" value="重置" /></td>
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