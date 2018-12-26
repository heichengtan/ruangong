<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
	setTimeout(function() { top.location.reload(); }, 1000);
</script>
</head>
<body>
<%
	String message = (String)request.getAttribute("Message");
%>
<p><%=message %></p>
</body>
</html>