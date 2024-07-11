<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>return book</title>
</head>
<body>
<form method="post" action="Registration/controller/BookServlet">
		<table 
			style="width:70%;background-color: pink;margin-top:200px;margin-left:100px;margin-right:200px;">
			<tr>
				<td>
					<h3 style="color:brown">return book</h3>
				</td>
			</tr>
	        <tr>
				<td><input type="text" name="title">book title</td>
			</tr>
			<tr>
				<td><input type="text" name="type" style="display:none" value="return"></td>
			</tr>
			<tr>
				<td><input type="submit" name="Login" value="submit"></td>
			</tr>
		</table>
</form>
</body>
</html>