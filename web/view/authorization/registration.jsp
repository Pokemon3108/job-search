<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
    <link rel="script" href="<c:url value="/js/user-form-validation.js"/>">

</head>
<body onload="checkRadioButton(${role});">

<c:import url="/view/headers/header-guest.jsp"/>


<div class="container">
    <h2 class="my-sm-3">
        <fmt:message key="registration_title" bundle="${ rb }"/>
    </h2>

    <form name="userInfo" action="${pageContext.request.contextPath}/job/registration" onsubmit="return validate(this);"
          method="post">

<%--        <input type="hidden" name="page" value="${pageContext.request.servletPath}">--%>

        <c:if test='${invalidEmail==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="invalid_email" bundle="${ rb }"/>
            </p>
        </c:if>

        <c:if test='${repeatedEmail==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="repeated_email" bundle="${ rb }"/>
            </p>
        </c:if>


        <div class="alert alert-danger my-sm-3 " role="alert" id="emailError"></div>

        <div class="form-group">
            <label for="email"><fmt:message key="email" bundle="${ rb }"/></label>
            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email" value="${email}"
                   placeholder=
                   <fmt:message key="email" bundle="${ rb }"/>
                           required>
        </div>


        <c:if test='${invalidPassword==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="invali_password" bundle="${ rb }"/>
            </p>
        </c:if>

        <p class="alert alert-danger my-sm-3 " role="alert" id="passwordError"></p>

        <div class="form-group">
            <label for="password"><fmt:message key="password" bundle="${ rb }"/></label>
            <input type="password" class="form-control" id="password" name="password" placeholder=
            <fmt:message key="password" bundle="${ rb }"/>
                    required>
        </div>

        <div class="form-group">
<%--            <c:set var="roles" value="${roles}"/>--%>
            <c:forEach items="${roles}" var="roleArr">
                <div class="custom-radio custom-control-inline">
                    <label>
                        <input type="radio" name="role" value="${roleArr}">
                        <c:set var="r" value="${roleArr}" />
                        <fmt:message key="${fn:toLowerCase(r)}" bundle="${ rb }"/>
                    </label>
                </div>
            </c:forEach>
        </div>

        <button type="submit" class="btn btn-success"><fmt:message key="create_account" bundle="${ rb }"/></button>
    </form>

</div>
</body>

<script src=<c:url value="/js/user-form-validation.js"/>>
</script>

<script src=<c:url value="/js/language.js"/>>
</script>

</html>
