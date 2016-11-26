<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Posting</title>

<style  type="text/css">

#content { position: relative;}

#login {
	position: relative;
	top: 80px;
}

.align-right {
	text-align: right;
}

table {
	border: 1px solid gray;
	padding: 20px;
	background-color: #CCCCFF;
}

.message {
	font-size: 16px;
	font-weight: bold;
	font-color: red;
}


</style>

</head>
<body>

<center>

<div id="createposting">

<h3>New Posting</h3>

<form method="post" action="<%= response.encodeUrl(request.getContextPath() + "/Controller?action=createposting") %>">

<input type="hidden" name="action" value="createposting" />

<table>

<tr><td class="align-right">Title: </td><td><input type="text" name="title" value=""/></td></tr>
<tr><td class="align-right">type: </td><td><input type="text" name="type" value=""/></td></tr>
<tr><td class="align-right">Description: </td><td><input type="text" name="description" value=""/></td></tr>
<tr><td class="align-right" colspan="2"><input type="submit" value="create posting"/></td></tr>

</table>


</form>

</div>


</center>
<p class="message"><%= session.getAttribute("loginEmail") %></p>
<p class="message"><%= session.getAttribute("createEmail") %></p>


</body>
</html>