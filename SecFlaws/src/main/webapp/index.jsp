<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head><title>SecFlaws Project</title></head>
	<body>
		<h1>SecFlaws Project</h1>
		<br>
		<h2>Unsafe</h2>
		<form name="form1" method="post" action="unsafe.jsp">
			<fieldset>
				<legend>Unsafe JSP</legend>
				<label for="input1" title="Name">Input</label>
				<input type="text" id="input1" name="input">
				<input type="submit" id="submit1" value="Submit">
			</fieldset>
		</form>
		<br>
		<form name="form2" method="post" action="UnsafeServlet">
			<fieldset>
				<legend>Unsafe Servlet</legend>
				<label for="input2" title="Name">Input</label>
				<input type="text" id="input2" name="input">
				<input type="submit" id="submit2" value="Submit">
			</fieldset>
		</form>
		<br>
		<h2>Safe</h2>
		<form name="form1" method="post" action="safe.jsp">
			<fieldset>
				<legend>Safe JSP</legend>
				<label for="input1" title="Name">Input</label>
				<input type="text" id="input1" name="input">
				<input type="submit" id="submit1" value="Submit">
			</fieldset>
		</form>
		<br>
		<form name="form2" method="post" action="SafeServlet">
			<fieldset>
				<legend>Safe Servlet</legend>
				<label for="input2" title="Name">Input</label>
				<input type="text" id="input2" name="input">
				<input type="submit" id="submit2" value="Submit">
			</fieldset>
		</form>

	</body>
</html>