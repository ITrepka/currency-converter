<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="service.CurrencyService" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.11.2019
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background-color: gainsboro;
        }

        h1 {
            text-align: center;
            margin-top: 40px;
            margin-bottom: 40px;
        }

        form, div {
            background-color: #068de9;
            border: 5px solid #2270ff;
            margin: auto;
            color: white;
            margin-bottom: 20px;
            border-radius: 10px;
            text-align: center;
        }

        .form {
            width: 200px;
            height: auto;
            font-size: 14px;
            padding: 10px;
            margin-bottom: 40px;
        }

        .result {
            width: 60%;
            font-size: 30px;
            padding: 20px;
        }

        select {
            display: block;
            margin-bottom: 30px;
            width: 100%;
        }

        input {
            width: 100%;
            margin-bottom: 30px;
        }

        b {
            margin-bottom: 30px;
        }

    </style>
</head>
<body>

<%
    Double amount = null;
    String currency1 = null;
    String currency2 = null;
    if (request.getParameter("amount") != null &&
            request.getParameter("currency1") != null &&
            request.getParameter("currency2") != null) {
        amount = Double.valueOf(request.getParameter("amount"));
        currency1 = request.getParameter("currency1");
        currency2 = request.getParameter("currency2");
    }
%>
<h1>Currency Converter</h1>
<form class="form" method="get" action="converter.jsp">
    <b>Kwota:</b>
    <input name="amount" type="number">
    <b>Waluta podanej kwoty:</b>
    <select name="currency1">
        <option value="eur" selected="selected">EURO</option>
        <option value="pln">PLN</option>
        <option value="gbp">GBP</option>
        <option value="usd">USD</option>
    </select>
    <b>Waluta na jaką chcesz przeliczyć:</b>
    <select name="currency2">
        <option value="eur" selected="selected">EURO</option>
        <option value="pln">PLN</option>
        <option value="gbp">GPG</option>
        <option value="usd">USD</option>
    </select>
    <input value="convert" type="submit">
</form>
<div class="result">
    <%if (amount != null) {%>
    <%=
        amount + " " + currency1 + " = "
    %>
    <%=String.format("%.2f", CurrencyService.convert(amount, currency1, currency2))%>
    <%=currency2%>
    <%}%>
</div>
</body>
</html>
