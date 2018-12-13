package org.tpu.servlets.aunth_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.database.models.Image;
import org.tpu.services.ImageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            DBFactory bd = new MysqlDBFactory();
            ImageService imageService = new ImageService(bd);

            AccountsDAO accountsDAO = new AccountsDAO(bd.connect());
            List<Account> accountList = accountsDAO.readAllAccount();
            List<Image> imageList = imageService.readAll();
            bd.close();

            req.setAttribute("imagesList", imageList);
            resp.setContentType("text/html");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/index.jsp");
            if (requestDispatcher != null) requestDispatcher.forward(req, resp);
        }
    }
}
