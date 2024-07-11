<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>login</title>
</head>
<body>
<form method="get" action="Registration/controller/WorkerServlet">

<table 
			style="width:70%;background-color: pink;margin-top:200px;margin-left:100px;margin-right:200px;">

			<tr>
				<td>
					<h3 style="color:brown">workers login</h3>
				</td>
			</tr>
	        <tr>
				<td>Enter Name :</td>
				<td><input type="text" name="name"></td>
			</tr>
			 <tr>
				<td>Enter Password :</td>
				<td><input type="password" name="password"></td>
			</tr> 
			<tr>
				<td></td>
				<td><input type="submit" name="Login" value="login"></td>
			
			</tr>
		</table>
</form>
</body>
</html>