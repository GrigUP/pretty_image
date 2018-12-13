package org.tpu.servlets.aunth_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String login = req.getParameter("email");
        String password = req.getParameter("password");

        DBFactory db = new MysqlDBFactory();
        AccountsDAO dao = new AccountsDAO(db.connect());
        Account account = dao.readAccount(login);
        db.close();

        if (account != null) {
            if (!account.getPassword().equals(password)) {
                account = null;
            } else {
                req.getSession().setAttribute("account", account);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
