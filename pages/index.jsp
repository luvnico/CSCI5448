<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
<title>Index Page</title>
</head>
<body>

<p><a href="<%= response.encodeUrl(request.getContextPath() + "/Controller?action=login") %>">Click here to login</a></p>
<p><a href="<%= response.encodeUrl(request.getContextPath() + "/Controller?action=createaccount") %>">Click here to create a new account</a></p>


</body>
</html>