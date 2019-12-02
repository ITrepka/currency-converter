package currency.app.servlets;

import currency.app.service.CurrencyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ratesHistory = req.getParameter("ratesHistory");
        String converter = req.getParameter("converter");
        if (ratesHistory != null || converter != null) {
            CurrencyService currencyService = new CurrencyService();
            currencyService.saveCurrenciesToDatabase();
        }
        if ("1".equals(ratesHistory)) {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/rates-history.jsp"));
        }else if ("1".equals(converter)) {
            resp.sendRedirect(resp.encodeRedirectURL(req.getContextPath() + "/converter.jsp"));
        }
    }
}
