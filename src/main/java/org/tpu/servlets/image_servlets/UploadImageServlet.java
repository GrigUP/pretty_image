package org.tpu.servlets.image_servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.tpu.database.implementations.MysqlDBFactory;
import org.tpu.database.interfaces.DBFactory;
import org.tpu.database.models.Account;
import org.tpu.database.models.Image;
import org.tpu.services.ImageMeta;
import org.tpu.services.ImageService;
import org.tpu.services.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.util.List;

@WebServlet(name = "UploadImageServlet", urlPatterns = "/image/upload")
public class UploadImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account sessionAccount = (Account) req.getSession().getAttribute("account");
        if (sessionAccount == null) {
            resp.sendRedirect(req.getContextPath() + "/");
        }

        String accountName = sessionAccount.getFname() + " " + sessionAccount.getLname();
        String imageRoot =  "/Users/ilyagrigorisin/Desktop/tomcat/upload/";
        String imageWebRoot = "/upload/";

        List<FileItem> items = getFileItemListFromRequest(req, resp);
        ImageMeta meta = new ImageMeta(items, imageRoot, imageWebRoot);

        DBFactory dbFactory = new MysqlDBFactory();
        ImageService imageService = new ImageService(dbFactory);

        Image image = imageService.createOnFileSystem(meta);
        image.setAccountName(accountName);
        System.out.println(image);
        imageService.createOnDB(image);

        resp.sendRedirect(req.getContextPath() + "/");
    }

    private List<FileItem> getFileItemListFromRequest(HttpServletRequest req, HttpServletResponse resp) {
        List<FileItem> items = null;

        try {
            if (!ServletFileUpload.isMultipartContent(req)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return items;
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(10 * 1024 * 1024);
            File tempDir = (File) getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(tempDir);

            ServletFileUpload upload = new ServletFileUpload(factory);

            upload.setSizeMax(10 * 1024 * 1024);

            items = upload.parseRequest(req);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return items;
    }
}
