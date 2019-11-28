<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="currency.app.service.CurrencyService" %><%--
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
            font-size: 16px;
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

        .add-currency {
            width: 15%;
            position: absolute;
            top: 50px;
            left: 50px;
            padding: 10px;
        }

        h2 {
            margin-top: 0;
        }
    </style>
</head>
<body>

<%
    Double amount = null;
    String currency1 = null;
    String currency2 = null;
    if (request.getParameter("amount") != null &&
            request.getParameter("amount").length() > 0 &&
            request.getParameter("currency1") != null &&
            request.getParameter("currency2") != null) {
        amount = Double.valueOf(request.getParameter("amount"));
        currency1 = request.getParameter("currency1");
        currency2 = request.getParameter("currency2");
    }
    String currencyName = null;
    Double currencyValue = null;
    if (request.getParameter("currencyName") != null && request.getParameter("currencyValue") != null &&
            request.getParameter("currencyName").length() > 0 && request.getParameter("currencyValue").length() > 0) {
        currencyName = request.getParameter("currencyName");
        currencyValue = Double.valueOf(request.getParameter("currencyValue"));
        CurrencyService.currencies.put(currencyName, currencyValue);
    }
%>
<h1>Currency Converter</h1>
<form class="form" method="get" action="converter.jsp">
    <b>Kwota:</b>
    <input name="amount" type="number" <%if (amount != null) {%> value="<%=amount%>" <%}%>>
    <b>Waluta podanej kwoty:</b>
    <select name="currency1">
        <%
            for (Map.Entry<String, Double> entry : CurrencyService.currencies.entrySet()) { %>
        <option value="<%=entry.getKey()%>"
                <%if (request.getParameter("currency1") != null && currency1.equals(entry.getKey())) {%>selected="selected"
                <%}%>><%=entry.getKey().toUpperCase()%>
        </option>
        <%
            }
        %>
    </select>
    <b>Waluta na jaką chcesz przeliczyć:</b>
    <select name="currency2">
        <%
            for (Map.Entry<String, Double> entry : CurrencyService.currencies.entrySet()) { %>
        <option value="<%=entry.getKey()%>"
                <%if (request.getParameter("currency2") != null && currency2.equals(entry.getKey())) {%>selected="selected"
                <%}%>><%=entry.getKey().toUpperCase()%>
        </option>
        <%
            }
        %>
    </select>
    <input value="convert" type="submit">
</form>
    <%if (amount != null) {%>
<div class="result">
    <%=
    amount + " " + currency1 + " = "
    %>
    <%=String.format("%.2f", CurrencyService.convert(amount, currency1, currency2))%>
    <%=currency2%>
</div>
    <%}%>

<form method="get" action="converter.jsp" class="add-currency">
    <h2>Add New Currency</h2>
    <b>Currency Name</b>
    <input type="text" name="currencyName" <%if (request.getParameter("currencyName") != null) {%> value="<%=currencyName%>" <%}%>>
    <b>Currency Value</b>
    <input type="number" name="currencyValue" <%if (request.getParameter("currencyValue") != null) {%> value="<%=currencyValue%>" <%}%>>
    <input type="submit" value="Add Currency">
</form>
</body>
</html>
