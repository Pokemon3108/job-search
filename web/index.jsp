<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <c:set var="pgTitle" value="Welcome" scope="request" />
  <c:set var="pgTitle" value="w" scope="application" />

  ${pgTitle}

  </body>
</html>
