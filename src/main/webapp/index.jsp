<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@page import="org.apache.shiro.subject.Subject,org.apache.shiro.SecurityUtils"%>
<!DOCTYPE html>
<html>
<head>
	<title>java-shiro-cas-client-demo</title>
	<meta charset="UTF-8" />
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="active"><a href="/index.jsp">You are on the /index.jsp page</a></li>
			<li><a href="/protected/index.jsp">Call the /protected/index.jsp page</a></li>
			<!-- #### change with your own CAS server and your host name #### -->
			<li><a href="https://localhost:8888/cas/logout?service=http://localhost:8080">Call the CAS logout</a></li>
		</ul>
		<% Subject subject = SecurityUtils.getSubject(); %>
		<h3>
			<p>principals : <%=subject.getPrincipals()%></p>
			<p>isAuthenticated : <%=subject.isAuthenticated()%></p>
		</h3>
	</div>
</body>
</html>
