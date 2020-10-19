package Servlets;

import Model.Cell;
import Model.Table;

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
        resp.setContentType("text/html");
        Table table = (Table) session.getAttribute("table");
        if (table == null) {
            table = new Table();
        }
        session.setAttribute("table", table);
        try {
            boolean flag = true;
            double x = Double.parseDouble(req.getParameter("coordinateX"));
            double y = Double.parseDouble(req.getParameter("coordinateY"));
            double r = Double.parseDouble(req.getParameter("radius"));
            if(req.getParameter("flag") != null) flag = (!req.getParameter("flag").equals("button"));
            boolean valid = flag?(rIsValid(r)):(xIsValid(x) && yIsValid(y) && rIsValid(r));
            if (valid) {
                Cell cell = createCell(x, y, r);
                table.addCell(cell);
                try(PrintWriter printWriter = resp.getWriter())
                {
                    goToAreaCheckTable(printWriter,String.valueOf(x),String.valueOf(y),String.valueOf(r),String.valueOf(cell.isRes()));

                }
            } else resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public void goToAreaCheckTable(PrintWriter writer, String x, String y, String r, String res) throws IOException {

        String answer = "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"utf-8\" /> " +
                "<style> " +
                "body {background-color: #1c222a;}" +
                ".check-table {\n" +
                "    border: solid 1px #edb021;\n" +
                "    border-collapse: collapse;\n" +
                "    border-spacing: 0;\n" +
                "    font: normal 13px Arial, sans-serif #edb021;\n" +
                "}\n" +
                ".check-table thead th {\n" +
                "    border: solid 1px #edb021;\n" +
                "    color: #edb021;\n" +
                "    padding: 10px;\n" +
                "    text-align: left;\n" +
                "}\n" +
                ".check-table tbody td {\n" +
                "    border: solid 1px #edb021;\n" +
                "    color: #edb021;\n" +
                "    padding: 10px;\n" +
                "}\n" +
                "a {\n" +
                "text-align: center;\n" +
                "}\n" +
                "a:link{ color: #edb021; }\n" +
                "a:visited {\n" +
                "  color: #edb021;\n" +
                "}" +
                "a:active {\n" +
                "  background: #4159E1;\n" +
                "}" +
                "</style>"+
                " </head>" +
                "<body>" +
                "<table class=\"check-table\">" +
                " <thead>\n" +
                "        <tr>\n" +
                "            <th>X</th>\n" +
                "            <th>Y</th>\n" +
                "            <th>R</th>\n" +
                "            <th>Result</th>\n" +
                "        </tr>\n" +
                "    </thead>\n" +
                "    <tbody>\n" +
                "        <tr>\n" +
                "            <td>" + x + "</td>\n" +
                "            <td>" + y + "</td>\n" +
                "            <td>" + r + "</td>\n" +
                "            <td>" + res + "</td>\n" +
                "        </tr>\n" +
                "    </tbody>" +
                "</table>"+
                "<a href = \"/hype_Web_exploded/\" > BACK TO MAIN PAGE </a>" +
                "</body></html>";
        writer.write(answer);
        writer.close();
    }

    private boolean checkArea(double x, double y, double r) {
        boolean sector = (x * x + y * y <= r * r) && y >= 0 && x >= 0;
        boolean rect = (y >= -r) && (x >= -r/2) && x <=0 && y <= 0;
        boolean triangle = (y <= 2*x+r) && x <= 0 && y >= 0;
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
