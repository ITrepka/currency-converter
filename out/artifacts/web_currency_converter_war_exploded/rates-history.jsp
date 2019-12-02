<%@ page import="currency.app.service.CurrencyService" %>
<%@ page import="currency.app.model.Currency" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.11.2019
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>rates-history</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
</head>
<body>
<h1>RATES HISTORY</h1>
<table>
    <tr>
        <th>Currency</th>
        <th>Code</th>
        <th>Bid</th>
        <th>Ask</th>
        <th>Trading Date</th>
        <th>Effective Date</th>
    </tr>
    <%
        for (Currency currency : CurrencyService.allHistory) { %>
            <tr>
                <th><%=currency.getCurrency()%></th>
                <th><%=currency.getCode()%></th>
                <th><%=currency.getBid()%></th>
                <th><%=currency.getAsk()%></th>
                <th><%=currency.getTradingDate()%></th>
                <th><%=currency.getEffectiveDate()%></th>
            </tr>
    <% }
    %>
</table>
</body>
</html>
