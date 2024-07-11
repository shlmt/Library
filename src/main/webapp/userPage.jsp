<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="windows-1255">
<title>user page</title>
</head>
<body>
<h1>hello <%= request.getAttribute("name") %></h1>
<h2>your books are:</h2>

<table style="width:70%;background-color: skyblue;margin:50px">

<tr>
<td width = "119"  ><b>title</b></td>
<td width = "168"  ><b>תאריך החזרה מיועד</b></td>
</tr>

<% Iterator itr;%>
<% List data = (List)request.getAttribute("booksById"); 
   for(itr=data.iterator();itr.hasNext();)
   {	
%>
<tr>
<td width = "119"><%=itr.next() %></td>
<td width = "168"><%=itr.next() %></td>
</tr>
<%} %>
</table>



<form method="get" action="StudentServlet">
	<input type="text" name="type" style="display:none" value="history">
	<input type="text" name="name" style="display:none" value=<%= request.getAttribute("name") %>>
	<input type="submit" name="Login" value="history">
</form>
<br/>
<form method="get" action="StudentServlet">
	<input type="text" name="type" style="display:none" value="loan">
	<input type="text" name="name" style="display:none" value=<%= request.getAttribute("name") %>>
	<input type="submit" value="loan book" placeholder="title">
</form>
<br/>
<form method="post" action="BookServlet">
	<input type="text" name="type" style="display:none" value="return">
	<input type="text" name="name" style="display:none" value=<%= request.getAttribute("name") %>>
	<input type="text" name="title" placeholder="insert title">
	<input type="submit" value="return book">
</form>
<br/>
<form method="post" action="StudentServlet">
	<input type="text" name="type" style="display:none" value="extend">
	<input type="text" name="name" style="display:none" value=<%= request.getAttribute("name") %>>
	<input type="text" name="book" placeholder="insert title">
	<input type="submit" value="extend">
</form>
<br/>
<form method="post" action="MsgServlet">
	<input type="text" name="senderName" style="display:none" value=<%= request.getAttribute("name") %>>
	<input type="text" name="booksById" style="display:none" value=<%= (List)request.getAttribute("booksById") %>>
	<input type="text" name="text" placeholder="your msg">
	<input type="submit" value="send message">
</form>
<br/>
<form method="get" action="BookServlet">
	<input type="text" name="type" style="display:none" value="search">
	<input type="text" name="title" placeholder="insert book title">
	<input type="submit" value="search">
</form>
</body>
</html>

