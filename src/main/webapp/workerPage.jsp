<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>worker page</title>
</head>
<body>
<h1>hello worker!</h1>
<h2>add</h2>

<button><a href="../../registrationUser.jsp" style="text-decoration: none; color:black">add user</a></button>
<button><a href="../../registrationWorker.jsp" style="text-decoration: none; color:black">add worker</a></button>
<button><a href="../../registrationBook.jsp" style="text-decoration: none; color:black">add book</a></button>

<h2>delete</h2>
<button><a href="../../deleteBook.jsp" style="text-decoration: none; color:black">delete book</a></button>
<button><a href="../../deleteUser.jsp" style="text-decoration: none; color:black">delete user</a></button>

<h2>users have book at home</h2>
<form method="post" action="WorkerServlet">
	<input type="submit" name="type" value="to see click here!"></td>
</form>

<h2>messages from users</h2>
<form method="get" action="MsgServlet">
	<input type="submit" name="type" value="to see click here!"></td>
</form>

</body>
</html>