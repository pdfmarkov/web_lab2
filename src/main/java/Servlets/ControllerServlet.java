package Servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("coordinateX") == null || req.getParameter("coordinateY") == null
                || req.getParameter("radius") == null || req.getParameter("flag") == null) {
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else req.getServletContext().getRequestDispatcher("/check_area").forward(req, resp);
    }

}
