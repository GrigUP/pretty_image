package org.tpu.servlets.image_servlets;

import org.tpu.database.dao.ImageAccountLikesDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.services.ImageService;
import org.tpu.services.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddImageLikeServlet", urlPatterns = "/image/like")
public class AddImageLikeServlet extends HttpServlet {

    private final Logger logger = new Logger();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");

        int imageId = Integer.parseInt(req.getParameter("imageId"));
        int accountId = account.getId();

        DBFactory factory = new MysqlDBFactory();
        ImageService imageService = new ImageService(factory);
        String result = imageService.changeLike(accountId, imageId);

        if (result.equals("added")) {
            logger.writeToLogFile(String.format("%s %s like image with id %d", account.getFname(), account.getLname(), imageId));
        } else {
            logger.writeToLogFile(String.format("%s %s remove like from image with id %d", account.getFname(), account.getLname(), imageId));
        }

        resp.getWriter().write(result);
    }
}
