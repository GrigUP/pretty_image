package org.tpu.servlets.image_servlets;

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

@WebServlet(name = "DeleteImageServlet", urlPatterns = "/image/delete")
public class DeleteImageServlet extends HttpServlet {

    private final Logger logger = new Logger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("account");
        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        int imageId = Integer.parseInt(req.getParameter("imageForDeleteId"));
        DBFactory dbFactory = new MysqlDBFactory();
        ImageService imageService = new ImageService(dbFactory);
        imageService.deleteById(imageId);
        dbFactory.close();

        logger.writeToLogFile(String.format("%s %s delete image with id %d", account.getFname(), account.getLname(), imageId));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
