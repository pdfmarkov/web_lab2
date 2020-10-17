<%@ page import="Model.Cell" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="table" scope="session" class="Model.Table"/>
<% List<Cell> finalTable = table.getResult(); %>
<!DOCTYPE html>
<html>
<head>
  <title>Марков Петр, Лаб. №2</title>
  <link rel="shortcut icon" href="pictures/favicon.ico" type="image/x-icon">
  <style>
    <%@include file='css/stylesheet.css' %>
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/scripts/sender.js"></script>
  <script src="${pageContext.request.contextPath}/scripts/validator.js"></script>

  <script>
    $(document).ready(function () {
      <% for(Cell cell: finalTable) { %>
      point_draw(<%= cell.getX() %>, <%= cell.getY() %>, <%= cell.getR() %>, <%= cell.isRes() %>);
      <%}  %>
    });
  </script>

</head>
<body>
<header class="shaded animated">
  <h1>Веб-программирование, Лаб. 2, Вариант № 10732</h1>
  <div id="credit">
    <img src="pictures/avatar.png" alt="Аватар разработчика" />
    <a href="https://github.com/pdfmarkov" class="illuminated animated" title="Перейти к профилю Github разработчика">Марков Петр Денисович, P3210</a>
  </div>
</header>
<main>
  <div class="container task">
    <canvas id='area' height='300' width='300' onclick="show_coords(event)"></canvas>
    <script src="${pageContext.request.contextPath}/scripts/image.js"> </script>
  </div>
  <p></p>
  <div class="container form">
    <form id="form" method="GET">
      <table class="radio_btn">
        <tr>
          <td></td>
          <td><label for="0"></label><input type="radio" id="0" name="X" value="-2" onclick="pressX(this.id)" >-2</td>
          <td><label for="1"></label><input type="radio" id="1" name="X" value="-1.5" onclick="pressX(this.id)" >-1.5</td>
          <td><label for="2"></label><input type="radio" id="2" name="X" value="-1" onclick="pressX(this.id)" >-1</td>
        </tr>
        <tr>
          <td> X = </td>
          <td><label for="3"></label><input type="radio" id="3" name="X" value="-0.5" onclick="pressX(this.id)" >-0.5</td>
          <td><label for="4"></label><input type="radio" id="4" name="X" value="0" onclick="pressX(this.id)" >0</td>
          <td><label for="5"></label><input type="radio" id="5" name="X" value="0.5" onclick="pressX(this.id)" >0.5</td>
        </tr>
        <tr>
          <td></td>
          <td><label for="6"></label><input type="radio" id="6" name="X" value="1" onclick="pressX(this.id)" >1</td>
          <td><label for="7"></label><input type="radio" id="7" name="X" value="1.5" onclick="pressX(this.id)" >1.5</td>
          <td><label for="8"></label><input type="radio" id="8" name="X" value="2" onclick="pressX(this.id)" >2</td>
        </tr>
      </table>
      <p></p>
      <label> Y = </label>
      <input class="input_Y" maxlength="9" id="Y" type="text" name="Y" placeholder="(-3 .. 5)"><br>
      <p></p>
      <section class="check-values r">
        <label> R = </label>
        <input type="checkbox" id="1c" name="r" value="1" class="check-box" onclick="pressR(this.id)">1
        <input type="checkbox" id="2c" name="r" value="2" class="check-box" onclick="pressR(this.id)">2
        <input type="checkbox" id="3c" name="r" value="3" class="check-box" onclick="pressR(this.id)">3
        <input type="checkbox" id="4c" name="r" value="4" class="check-box" onclick="pressR(this.id)">4
        <input type="checkbox" id="5c" name="r" value="5" class="check-box" onclick="pressR(this.id)">5
      </section>
      <p></p>
      <button id="button_submit" type="sumbit"> Поiхали </button>
    </form>
  </div>
  <p></p>
  <div id="container frame">
    <table class="table-data" id="resultTable">
      <thead>
      <tr>
        <td>X</td>
        <td>Y</td>
        <td>R</td>
        <td>Время</td>
        <td>Результат</td>
      </tr>
      </thead>
      <tbody>
      <% for (Cell cell : finalTable) {%>
      <tr>
        <td><%= cell.getX()%>
        </td>
        <td><%= cell.getY()%>
        </td>
        <td><%= cell.getR()%>
        </td>
        <td><%= new SimpleDateFormat("HH:mm:ss").format(cell.getDate())%>
        </td>
        <td><%= cell.isRes()%>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>
  </div>
</main>
<footer class="shaded animated">ИТМО<br>2020</footer>
</body>
</html>
