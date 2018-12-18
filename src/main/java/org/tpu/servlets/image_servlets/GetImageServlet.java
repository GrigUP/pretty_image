package org.tpu.servlets.image_servlets;

import org.tpu.database.dao.AccountsDAO;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.database.models.Image;
import org.tpu.services.ImageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetImageServlet", urlPatterns = "/getListImage")
public class GetImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("getImage");
        Account account = (Account) req.getSession().getAttribute("account");

        if (account == null) {
            System.out.println("null");
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }

        Integer imageOnPage = (Integer) req.getServletContext().getAttribute("imageOnPage");
        Integer linkValue = (Integer) req.getSession().getAttribute("LinkValueForImageServlet");

        System.out.println(linkValue);
        DBFactory bd = new MysqlDBFactory();
        ImageService imageService = new ImageService(bd);
        List<Image> imageList = imageService.readAll(account, (linkValue-1)*imageOnPage, imageOnPage);
        bd.close();

        req.getSession().setAttribute("imageBatch", imageList);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
