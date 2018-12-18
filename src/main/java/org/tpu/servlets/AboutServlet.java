package org.tpu.servlets;

import org.tpu.services.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/about")
public class AboutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Logger log = new Logger();
        log.writeToLogFile("Page About is selected");
        RequestDispatcher dispatcher = req.getRequestDispatcher("pages/about.jsp");
        dispatcher.forward(req, resp);
    }
}