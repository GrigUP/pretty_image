package org.tpu.servlets.account_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.services.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountDeleteServlet", urlPatterns = "/accounts/delete")
public class AccountDeleteServlet extends HttpServlet {

    private final Logger logger = new Logger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idForDelete = Integer.parseInt(req.getParameter("idForDelete"));

        DBFactory db = new MysqlDBFactory();
        AccountsDAO dao = new AccountsDAO(db.connect());
        dao.deleteAccountById(idForDelete);
        db.close();

        Account account = (Account) req.getSession().getAttribute("account");
        logger.writeToLogFile(String.format("%s %s delete account with id %d", account.getFname(), account.getLname(), idForDelete));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
