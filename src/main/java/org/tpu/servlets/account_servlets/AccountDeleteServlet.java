package org.tpu.servlets.account_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AccountDeleteServlet", urlPatterns = "/accounts/delete")
public class AccountDeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int idForDelete = Integer.parseInt(req.getParameter("idForDelete"));

        DBFactory db = new MysqlDBFactory();
        AccountsDAO dao = new AccountsDAO(db.connect());
        dao.deleteAccountById(idForDelete);
        db.close();

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
