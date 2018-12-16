package org.tpu.servlets;

import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.database.models.Image;
import org.tpu.services.ImageService;
import org.tpu.services.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        DBFactory bd = new MysqlDBFactory();
        ImageService imageService = new ImageService(bd);
        List<Image> imageList = imageService.readAll(account);
        bd.close();

        req.setAttribute("imagesList", imageList);

        resp.setContentType("text/html");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/user.jsp");
        if (requestDispatcher != null) requestDispatcher.forward(req, resp);
    }
}
