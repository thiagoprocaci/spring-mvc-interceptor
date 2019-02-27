<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import ="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
      <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">
</head>
<body>

<div class="container">

<center>
    <h1>
        Car List
    </h1>


    <table class="table">
     <tr>
        <th>
            Name
        </th>
         <th>
            Brand
        </th>
        <th>
            Color
        </th>
        <th>
            Year
        </th>

      </tr>
      <c:forEach var="car" items="${carList}">
       <tr>
            <td>
                ${car.name}
            </td>
            <td>
                ${car.brand}
            </td>
            <td>
                ${car.color}
            </td>
            <td>
                ${car.year}
            </td>

        </tr>
      </c:forEach>
     </table>

      <br><br>




</center>

</div>
      <br><br>
      <a href="<%=request.getContextPath()%>/secure">Main Page</a>
</body>
</html>