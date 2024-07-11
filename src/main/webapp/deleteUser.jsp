<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>delete user</title>
</head>
<body>
<form method="post" action="Registration/controller/StudentServlet">
		<table 
			style="width:70%;background-color: pink;margin-top:200px;margin-left:100px;margin-right:200px;">

			<tr>
				<td>
					<h3 style="color:brown">delete user</h3>
				</td>
			</tr>
	        <tr>
				<td>Enter user Name</td>
				<td><input type="text" name="userName" ></td>
			</tr>
			<tr>
				<td><input type="text" name="type" style="display:none" value="delete"></td>
			</tr>
			<tr>
				<td><input type="submit" name="Login" value="submit"></td>
			</tr>
		</table>

	</form>
</body>
</html>