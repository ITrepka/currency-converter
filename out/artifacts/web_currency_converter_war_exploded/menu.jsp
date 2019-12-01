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
    <style>
        .menu-list {
            text-align: center;
            padding: 10px;
            list-style-type: none;
            margin-top: 100px;
        }
        .menu-list li {
            margin-top: 30px;
            font-size: 25px;
            background-color: #068de9;
            border: 3px solid #2270ff;
            border-radius: 20px;
            width: 400px;
            margin-left: auto;
            margin-right: auto;
            color: white;
            padding: 50px;
        }

        .menu-list li:hover {
            border-color: white;
        }

        .menu-header{
            font-size: 58px;
        }

        .container-menu {
            padding: 20px;
            background-color: transparent;
            color: black;
            border: none;
        }

        #rates-history-button {
            background-color: #ff4930;
            border: red 3px solid;
        }

        #rates-history-button:hover {
            border-color: white;
        }


    </style>
</head>
<body>
<div class="container-menu">
    <h1 class="menu-header">MENU</h1>
    <ul class="menu-list">
        <li id="rates-history-button">CURRENCY RATES HISTORY</li>
        <li id="converter-button"><a href="converter.jsp>">CURRENCY CONVERTER</a></li>
    </ul>
</div>
</body>
</html>
