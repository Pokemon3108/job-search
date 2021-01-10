<%@ page import="by.daryazalevskaya.finalproject.model.type.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<div class="container">
    <h2 class="my-sm-3">Registration</h2>

<%--    ${pageContext.request.servletPath}--%>

    <form action="${pageContext.request.contextPath}/registration" method="post">

        <input type="hidden" name="page" value="${pageContext.request.servletPath}">

        <c:if test='${not empty invalidEmail}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                    ${invalidEmail}
            </p>
        </c:if>

        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="username"
                   placeholder="Email"
                   required>
        </div>

        <c:if test='${not empty invalidPassword}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                    ${invalidPassword}
            </p>
        </c:if>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password" name="password"
                   required>
        </div>

        <div class="form-group">
            <c:set var="roles" value="<%=Role.values()%>"/>
            <c:forEach items="${roles}" var="role">
                <div class="custom-radio custom-control-inline">
                    <label>
                        <input type="radio" name="role" value="${role}" checked>
                            ${role}
                    </label>
                </div>
            </c:forEach>
        </div>


        <button type="submit" class="btn  btn-success">Create account</button>
    </form>
</div>
</body>
</html>
