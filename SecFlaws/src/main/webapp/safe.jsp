<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="org.owasp.esapi.ESAPI"%>
<!DOCTYPE html>
<html>
	<head><title>SecFlaws Project</title></head>
	<body>
		<h1>SecFlaws Project</h1>
		<p>[<%=ESAPI.encoder().encodeForHTML(request.getParameter("input")) %>]
		<p><a href="index.jsp">Back</a>
	</body>
</html>