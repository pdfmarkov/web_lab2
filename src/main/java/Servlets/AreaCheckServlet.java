package Servlets;

import Model.Cell;
import Model.Table;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;


public class AreaCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("application/json");
        Table table = (Table)session.getAttribute("table");
        if (table == null) {
            table = new Table();
        }
        session.setAttribute("table", table);
        try {
            double x = Double.parseDouble(req.getParameter("coordinateX"));
            double y = Double.parseDouble(req.getParameter("coordinateY"));
            double r = Double.parseDouble(req.getParameter("radius"));
            boolean flag = (!req.getParameter("flag").equals("button"));
            boolean valid = flag?(rIsValid(r)):(xIsValid(x) && yIsValid(y) && rIsValid(r));
            if (valid) {
                Cell cell = createCell(x, y, r);
                table.addCell(cell);
                try(PrintWriter printWriter = resp.getWriter())
                {
                    printWriter.println(new Gson().toJson(cell));
                }
            } else resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean checkArea(double x, double y, double r) {
        boolean sector = (x * x + y * y <= r * r) && y >= 0 && x >= 0;
        boolean rect = (y >= -r) && (x >= -r/2) && x <=0 && y <= 0;
        boolean triangle = (y <= 2*x+
                r) && x <= 0 && y >= 0;
        return rect || sector || triangle;
    }

    private Cell createCell(double x, double y, double r) {
        boolean res = checkArea(x, y, r);
        Date date = new Date();
        return new Cell(x, y, r, date, res);
    }

    private boolean xIsValid(double x) {
        Double[] xLists = {-2d, -1.5d, -1d, -0.5d, 0d, 0.5d, 1d, 1.5d, 2d};
        return Arrays.asList(xLists).contains(x);
    }

    private boolean yIsValid(double y) {
        return y < 5 && y > -3;
    }

    private boolean rIsValid(double r) {
        Integer[] rLists = {1, 2, 3, 4, 5};
        return Arrays.asList(rLists).contains((int) r);
    }
}
