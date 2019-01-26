<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
           <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css" integrity="sha384-PmY9l28YgO4JwMKbTvgaS7XNZJ30MK9FAZjjzXtlqyZCqBY6X6bXIkM++IkyinN+" crossorigin="anonymous">

            <!-- Optional theme -->
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap-theme.min.css" integrity="sha384-jzngWsPS6op3fgRCDTESqrEJwRKck+CILhJVO5VvaAZCq8JYf8HsR/HPpBOOPZfR" crossorigin="anonymous">

    </head>
    <body>
    <div class="container">
        <br> <br>

        <p class="text-success">
            Welcome ${user.username}!
        </p>
        <br> <br>


         <a href="<%=request.getContextPath()%>/login/logout">Logout</a>

         <br> <br>

         <p class="bg-info">
             <c:if test="${user.profile == 'Ordinary'}">
                Ordinary Content
             </c:if>
             <c:if test="${user.profile == 'Moderator'}">
                 Moderator Content
              </c:if>
          </p>

     </div>

     <!-- Latest compiled and minified JavaScript -->
     <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js" integrity="sha384-vhJnz1OVIdLktyixHY4Uk3OHEwdQqPppqYR8+5mjsauETgLOcEynD9oPHhhz18Nw" crossorigin="anonymous"></script>

    </body>
</html>