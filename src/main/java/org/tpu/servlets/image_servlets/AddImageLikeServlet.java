package org.tpu.servlets.image_servlets;

import org.tpu.database.dao.ImageAccountLikesDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.services.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddImageLikeServlet", urlPatterns = "/image/like")
public class AddImageLikeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        int imageId = Integer.parseInt(req.getParameter("imageId"));
        int accountId = account.getId();

        System.out.println(imageId + " " + accountId);

        DBFactory factory = new MysqlDBFactory();
        ImageService imageService = new ImageService(factory);
        String result = imageService.changeLike(accountId, imageId);
        resp.getWriter().write(result);
    }
}
