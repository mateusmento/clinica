<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ERRO</title>
</head>
<body>
    <h1>ERRO</h1>
    <p><%= request.getAttribute("errorMsg")%></p> 
</body>
</html>
