<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>add user</title>
</head>
<body>
<form method="post" action="Registration/controller/StudentServlet">
		<table 
			style="width:70%;background-color: skyblue;margin-top:200px;margin-left:100px;margin-right:200px;">

			<tr>
				<td>
					<h3 style="color:brown">add user</h3>
				</td>
			</tr>
	        <tr>
				<td>Enter User Name :</td>
				<td><input type="text" name="userName"></td>
			</tr>
			
			<tr>
				<td><input type="text" name="type" style="display:none" value="post"></td>
			</tr>
		
			 <tr>
				<td>Enter Password :</td>
				<td><input type="password" name="password"></td>
			</tr>
			 <tr>
				<td>Enter Address :</td>
				<td><input type="text" name="address"></td>
			</tr>
			 <tr>
				<td>Enter Phone Number :</td>
				<td><input type="text" name="phoneNum"></td>
			</tr>
			 <tr>
				<td>Enter type :</td>
				<td><input type="number" name="role"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="Login" value="Submit"></td>
			
			</tr>
		</table>
	</form>
</body>
</html>