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
        <h2>
            Add a car
        </h2>
        <h3>
            ${message}
        </h3>
        <form method="post" action="<%=request.getContextPath()%>/secure/carCreate" class="form-horizontal">
            <div class="form-group form-group-lg">
                <label for="name" class="col-sm-2 control-label">Name:</label>
                <div class="col-sm-10">
                    <input type="text" name="name" id="name" class="form-control" >
                </div>
            </div>
            <div class="form-group form-group-lg">
                <label for="brand" class="col-sm-2 control-label">Brand:</label>
                <div class="col-sm-10">
                    <input type="text" id="brand" class="form-control" name="brand"  >
                </div>
            </div>
           <div class="form-group form-group-lg">
               <label for="year" class="col-sm-2 control-label">Year:</label>
               <div class="col-sm-10">
                   <input type="number" id="year" class="form-control" name="year"  >
               </div>
           </div>
           <div class="form-group form-group-lg">
              <label for="color" class="col-sm-2 control-label">Color:</label>
              <div class="col-sm-10">
                  <input type="text" id="color" class="form-control" name="color"  >
              </div>
          </div>

            <div style="float:right">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>



    </div>

         <br><br>

          <a href="<%=request.getContextPath()%>/secure">Main Page</a>


</body>
</html>