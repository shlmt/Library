<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import = "java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loan book</title>
</head>
<body>

<h1>all books</h1>
<table style="width:70%;background-color: skyblue;margin-top:200px;margin-left:100px;margin-right:200px;">
<tr>
<td width = "119"  ><b>title</b></td>
<td width = "168"  ><b>description</b></td>
</tr>

<% Iterator itr;%>
<% List data = (List)request.getAttribute("books");
   for(itr=data.iterator();itr.hasNext();)   
   {
%>
<tr>
<td width = "119"><%=itr.next() %></td>
<td width = "168"><%=itr.next() %></td>
</tr>
<%} %>
</table>

<form method="post" action="../../Registration/controller/BookServlet">
	<table 
		style="width:70%;background-color: pink;margin-top:200px;margin-left:100px;margin-right:200px;">

		<tr>
			<td>
				<h3 style="color:brown">loan book</h3>
			</td>
		</tr>
        <tr>
			<td><input type="text" name="title">book title</td>
		</tr>
		<tr>
			<td><input type="text" name="name" style="display:none" value=<%= request.getAttribute("name") %>></td>
			<td><input type="text" name="type" style="display:none" value="loan"></td>
		</tr>
		<tr>
			<td><input type="submit" value="submit"></td>
		</tr>
	</table>

</form>

</body>
</html>
</html>