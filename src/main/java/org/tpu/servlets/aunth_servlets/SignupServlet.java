package org.tpu.servlets.aunth_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.database.models.AccountType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignupServlet", urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {

    private final AccountType defaultType = AccountType.user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ((Account) req.getSession().getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        DBFactory db = new MysqlDBFactory();
        AccountsDAO dao = new AccountsDAO(db.connect());

        Account account = dao.readAccount(req.getParameter("email"));
        if (account == null) {
            account = new Account();
            account.setFname(req.getParameter("fname"));
            account.setLname(req.getParameter("lname"));
            account.setEmail(req.getParameter("email"));
            account.setPassword(req.getParameter("password"));
            account.setAccountType(defaultType);

            dao.createAccount(account);

            account = dao.readAccount(req.getParameter("email"));
            db.close();
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        if (account != null) {
            req.getSession().setAttribute("account", account);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
