package org.tpu.servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.dao.ImageDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.*;
import org.tpu.services.ImageService;
import org.tpu.services.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        if (account == null) {
            System.out.println("null");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        resp.setContentType("text/http");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/admin.jsp");

        DBFactory bd = new MysqlDBFactory();
        ImageService imageService = new ImageService(bd);

        AccountsDAO accountsDAO = new AccountsDAO(bd.connect());
        List<Account> accountList = accountsDAO.readAllAccount();
        List<Image> imageList = imageService.readAll(account);
        bd.close();

        req.setAttribute("accountList", accountList);
        req.setAttribute("imagesList", imageList);
        if(requestDispatcher != null) requestDispatcher.forward(req, resp);
        resetErrorMessage(req.getSession());
    }

    private void resetErrorMessage(HttpSession session) {
        session.setAttribute("uploadErrorMsg", null);
    }
}
