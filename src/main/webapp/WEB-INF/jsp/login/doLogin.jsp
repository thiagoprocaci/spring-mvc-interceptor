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
            Login
        </h2>

                <c:if test="${message != null}">
                        <h3>
                            <c:out value="${message}" />
                        </h3>

                </c:if>


                <form method="post" action="<%=request.getContextPath()%>/login/doLogin" class="form-horizontal">
                    <div class="form-group form-group-lg">
                        <label for="username" class="col-sm-2 control-label">Name:</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" id="username" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group form-group-lg">
                        <label for="password" class="col-sm-2 control-label">Password:</label>
                        <div class="col-sm-10">
                            <input type="password" id="password" class="form-control" name="password"  >
                        </div>
                    </div>

                    <div style="float:right">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>

                 <br ><br >
                 <a href="<%=request.getContextPath()%>/user/create">Create your account</a>

    </div>


    <!-- Latest compiled and minified JavaScript -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>
</body>
</html>