<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>

    </head>
    <body>
        Welcome ${user.username}!
        <br> <br>
         <a href="<%=request.getContextPath()%>/login/logout">Logout</a>
         <br> <br>
         <c:if test="${user.profile == 'Ordinary'}">
            Ordinary Content
         </c:if>
         <c:if test="${user.profile == 'Moderator'}">
             Moderator Content
          </c:if>

    </body>
</html>