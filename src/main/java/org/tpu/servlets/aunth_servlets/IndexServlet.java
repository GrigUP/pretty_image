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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "IndexServlet", urlPatterns = "")
public class IndexServlet extends HttpServlet {

    private final Integer imageOnPage = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().setAttribute("imageOnPage", imageOnPage);

        Account sessionAccount = (Account) req.getSession().getAttribute("account");
        if (sessionAccount != null) {
            switch (sessionAccount.getAccountType()) {
                case user:
                    resp.sendRedirect(req.getContextPath() + "/user");
                    break;
                case administration:
                    resp.sendRedirect(req.getContextPath() + "/admin");
            }
        } else {
            DBFactory bd = new MysqlDBFactory();
            ImageService imageService = new ImageService(bd);

            List<Image> imageList = imageService.readAll(null, 0,9);
            bd.close();

            req.setAttribute("imagesList", imageList);
            resp.setContentType("text/html");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/index.jsp");
            if (requestDispatcher != null) requestDispatcher.forward(req, resp);

            resetErrorMessage(req.getSession());


        }
    }

    private void resetErrorMessage(HttpSession session) {
        session.setAttribute("accountError", null);
        session.setAttribute("loginError", null);
        session.setAttribute("uploadErrorMsg", null);
    }
}
