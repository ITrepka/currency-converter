<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="currency.app.service.CurrencyService" %>
<%@ page import="currency.app.model.Currency" %><%--
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" type="text/css">
</head>
<body>

<%
    CurrencyService currencyService = new CurrencyService();
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
%>
<h1>Currency Converter</h1>
<form class="form" method="get" action="converter.jsp">
    <b>Kwota:</b>
    <input name="amount" type="number" <%if (amount != null) {%> value="<%=amount%>" <%}%>>
    <b>Waluta podanej kwoty:</b>
    <select name="currency1">
        <%
            for (Currency currency : CurrencyService.dailyCurrencyList) { %>
        <option value="<%=currency.getCode().toUpperCase()%>"
                <%if (request.getParameter("currency1") != null && currency1.equals(currency.getCode())) {%>selected="selected"
                <%}%>><%=currency.getCode().toUpperCase()%>
        </option>
        <%
            }
        %>
    </select>
    <b>Waluta na jaką chcesz przeliczyć:</b>
    <select name="currency2">
        <%
            for (Currency currency : CurrencyService.dailyCurrencyList) { %>
        <option value="<%=currency.getCode().toUpperCase()%>"
                <%if (request.getParameter("currency2") != null && currency2.equals(currency.getCode())) {%>selected="selected"
                <%}%>><%=currency.getCode().toUpperCase()%>
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
</body>
</html>
