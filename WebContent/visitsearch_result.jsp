<%@page import="entity.Visit"%>
<%@page import="java.util.ArrayList"%>
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
<title>病历查询结果</title>
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
			<table class="wide">
				<thead>
					<tr>
						<td width="120">医师</td>
						<td width="120">时间</td>
						<td>病情描述</td>
						<td>治疗方案</td>
					</tr>
				</thead>
				<%
					List<Visit> visits = (List<Visit>)request.getAttribute("visits");
					for (Visit v : visits) {
				%>
				<tr class="result">
					<td width="120">
						<%=v.getVetName() %>
					</td>
					<td width="120">
						<%=v.getVisitdate() %>
					</td>
					<td>
						<%=v.getDescription() %>
					</td>
					<td>
						<%=v.getTreatment() %>
					</td>
				</tr>
				<%
					}
				%>





				<tr class="cols4">
					<td colspan="4"><input type="button" value="返回"
						onclick="history.back(-1);" /></td>
				</tr>
				<tr class="cols4">
					<td colspan="4" class="info"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></td>
				</tr>
			</table>
		</div>
		<div id="footer"></div>
	</div>
</body>
</html>