<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<c:import url="/view/headers/header-guest.jsp"/>

<div class="container">
    <h2 class="my-sm-3">
        <fmt:message key="authorization" bundle="${ rb }"/>
    </h2>

    <form name="userInfo" action="${pageContext.request.contextPath}/job/login"
          method="post">

        <input type="hidden" name="page" value="${pageContext.request.servletPath}">

        <c:if test='${loginError!=null}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="loginError" bundle="${ rb }"/>
            </p>
        </c:if>


<%--        <c:if test='${invalidEmail==true}'>--%>
<%--            <p class="alert alert-danger my-sm-3 " role="alert">--%>
<%--                <fmt:message key="invalidEmail" bundle="${ rb }"/>--%>
<%--            </p>--%>
<%--        </c:if>--%>


<%--        <div class="alert alert-danger my-sm-3 " role="alert" id="emailError"></div>--%>

        <div class="form-group">
            <label for="email"><fmt:message key="email" bundle="${ rb }"/></label>
            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email"
                   placeholder=<fmt:message key="email" bundle="${ rb }"/>
                           required>
        </div>


<%--        <c:if test='${invalidPassword==true}'>--%>
<%--            <p class="alert alert-danger my-sm-3 " role="alert">--%>
<%--                <fmt:message key="invalidPassword" bundle="${ rb }"/>--%>
<%--            </p>--%>
<%--        </c:if>--%>

<%--        <p class="alert alert-danger my-sm-3 " role="alert" id="passwordError"></p>--%>

        <div class="form-group">
            <label for="password"><fmt:message key="password" bundle="${ rb }"/></label>
            <input type="password" class="form-control" id="password" placeholder=<fmt:message key="password" bundle="${ rb }"/> name="password"
                   required>
        </div>

        <button type="submit" class="btn btn-success"><fmt:message key="login" bundle="${ rb }"/></button>
    </form>

</div>

<script src=<c:url value="/js/language.js"/>>
</script>

<%--<script src=<c:url value="/js/user-form-validation.js"/>>--%>
<%--</script>--%>

</body>
</html>
