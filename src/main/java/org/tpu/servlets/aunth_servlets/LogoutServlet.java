package org.tpu.servlets.aunth_servlets;

import org.tpu.database.models.Account;
import org.tpu.services.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private final Logger logger = new Logger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account) req.getSession().getAttribute("account");
        logger.writeToLogFile(String.format("%s %s is logged out", account.getFname(), account.getLname()));
        req.getSession().setAttribute("account", null);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
