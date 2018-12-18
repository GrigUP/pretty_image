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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Account account = (Account) req.getSession().getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        DBFactory bd = new MysqlDBFactory();
        ImageService imageService = new ImageService(bd);
        AccountsDAO accountsDAO = new AccountsDAO(bd.connect());
        List<Image> imageList = imageService.readAll(account);
        List<Account> accountList = accountsDAO.readAllAccount();
        bd.close();

        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/admin.jsp");

        req.setAttribute("accountList", accountList);
        req.setAttribute("imagesList", imageList);
        if(requestDispatcher != null) requestDispatcher.forward(req, resp);
        resetErrorMessage(req.getSession());
    }

    private void resetErrorMessage(HttpSession session) {
        session.setAttribute("uploadErrorMsg", null);
    }

    private List<Integer> parseLinkList(int totalElement, int elementOnPage) {
        List<Integer> result = new ArrayList<>();

        int totalPage = totalElement/elementOnPage;

        if (totalElement%elementOnPage != 0) {
            totalPage++;
        }

        for (int i = 1; i <= totalPage; i++) {
            result.add(i);
        }

        return result;
    }
}
