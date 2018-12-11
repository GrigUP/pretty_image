package org.tpu.servlets.aunth_servlets;

import org.tpu.database.models.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", urlPatterns = "")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account sessionAccount = (Account) req.getSession().getAttribute("account");
        if (sessionAccount != null) {
            switch (sessionAccount.getAccountType()) {
                case user:
                    resp.sendRedirect(req.getContextPath() + "/user");
                    break;
                case moderator:
                    resp.sendRedirect(req.getContextPath() + "/admin");
                    break;
                case administration:
                    resp.sendRedirect(req.getContextPath() + "/admin");
            }
        } else {
            resp.setContentType("text/html");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/index.jsp");
            if (requestDispatcher != null) requestDispatcher.forward(req, resp);
        }
    }
}
