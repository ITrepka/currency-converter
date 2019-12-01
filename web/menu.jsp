<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.11.2019
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu-Converter</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
</head>
<body>
<div class="container-menu">
    <h1 class="menu-header">MENU</h1>
    <ul class="menu-list">
        <a href="controllerServlet?ratesHistory=1>"><li id="rates-history-button">CURRENCY RATES HISTORY</li></a>
        <a href="controllerServlet?converter=1>"><li id="converter-button">CURRENCY CONVERTER</li></a>
    </ul>
</div>
</body>
</html>
