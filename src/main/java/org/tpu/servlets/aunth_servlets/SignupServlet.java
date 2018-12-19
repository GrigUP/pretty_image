package org.tpu.servlets.aunth_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.database.models.AccountType;
import org.tpu.services.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "SignupServlet", urlPatterns = "/signup")
public class SignupServlet extends HttpServlet {

    private final Logger logger = new Logger();
    private final String errorMessage = "Аккаунт существует";
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

        Account account = dao.readAccount(req.getParameter("email").toLowerCase());
        if (account == null) {
            account = new Account();
            account.setFname(new String(req.getParameter("fname").getBytes(StandardCharsets.ISO_8859_1), "UTF-8"));
            account.setLname(new String(req.getParameter("lname").getBytes(StandardCharsets.ISO_8859_1), "UTF-8"));
            account.setEmail(req.getParameter("email").toLowerCase());
            account.setPassword(req.getParameter("password"));
            account.setAccountType(defaultType);

            dao.createAccount(account);
            logger.writeToLogFile(String.format("%s %s is signed up", account.getFname(), account.getLname()));

            account = dao.readAccount(req.getParameter("email").toLowerCase());
            db.close();
        } else {
            req.getSession().setAttribute("accountError", errorMessage);
            account = null;
        }

        if (account != null) {
            req.getSession().setAttribute("account", account);
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
