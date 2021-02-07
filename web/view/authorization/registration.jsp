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

<c:choose>
    <c:when test="${sessionScope.role eq 'EMPLOYEE'}">
        <c:import url="/view/imports/header-employee.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role eq 'EMPLOYER'}">
        <c:import url="/view/imports/header-employer.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role==null}">
        <c:import url="/view/imports/header-guest.jsp"/>
    </c:when>
</c:choose>


<div class="container">
    <h2 class="my-sm-3">
        <fmt:message key="registrationTitle" bundle="${ rb }"/>
    </h2>

    <form name="userInfo" action="${pageContext.request.contextPath}/job/registration" onsubmit="return validate(this);"
          method="post">

        <p class="requiredField"><fmt:message key="required_field" bundle="${ rb }"/></p>

        <c:if test='${invalidEmail==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="invalidEmail" bundle="${ rb }"/>
            </p>
        </c:if>

        <c:if test='${repeatedEmail==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="repeatedEmail" bundle="${ rb }"/>
            </p>
        </c:if>


        <div class="alert alert-danger my-sm-3 " role="alert" id="emailError"></div>

        <div class="form-group">
            <label for="email"><fmt:message key="email" bundle="${ rb }"/> *</label>
            <input type="email" class="form-control" id="email" aria-describedby="emailHelp" name="email"
                   value="${email}"
                   placeholder=
                   <fmt:message key="email" bundle="${ rb }"/>
                           required>
        </div>


        <c:if test='${invalidPassword==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="invaliPassword" bundle="${ rb }"/>
            </p>
        </c:if>

        <c:if test='${differentPassword==true}'>
            <p class="alert alert-danger my-sm-3 " role="alert">
                <fmt:message key="differentPassword" bundle="${ rb }"/>
            </p>
        </c:if>

        <p class="alert alert-danger my-sm-3 " role="alert" id="passwordError"></p>
        <p class="alert alert-danger my-sm-3 " role="alert" id="differentPassword"></p>

        <div class="form-group">
            <div class="password">
                <label for="password"><fmt:message key="password" bundle="${ rb }"/> *</label>
                <input type="password" class="form-control" id="password" name="password" placeholder=
                <fmt:message key="password" bundle="${ rb }"/>  required>
                <a href="#" class="password-control" onclick="return show_hide_password(this, 'password');"></a>
            </div>
        </div>

        <div class="form-group">
            <div class="password">
                <label for="confirm-password"><fmt:message key="confirm_password" bundle="${ rb }"/> *</label>
                <input type="password" class="form-control" id="confirm-password" name="confirm-password" placeholder=
                <fmt:message key="confirm_password" bundle="${ rb }"/>  required>
                <a href="#" class="password-control" onclick="return show_hide_password(this, 'confirm-password');"></a>
            </div>
        </div>

        <div class="form-group">
            <c:forEach items="${roles}" var="roleArr">
                <div class="custom-radio custom-control-inline">
                    <label>
                        <c:if test="${roleArr==role}">
                            <input type="radio" name="role" value="${roleArr}" checked>
                        </c:if>
                        <c:if test="${roleArr!=role}">
                            <input type="radio" name="role" value="${roleArr}">
                        </c:if>
                        <c:set var="r" value="${roleArr}"/>
                        <fmt:message key="${fn:toLowerCase(r)}" bundle="${ rb }"/>
                    </label>
                </div>
            </c:forEach>
        </div>

        <button type="submit" class="btn btn-success"><fmt:message key="createAccount" bundle="${ rb }"/></button>
    </form>

</div>

<c:import url="/view/imports/footer.jsp"/>
</body>

<script src=<c:url value="/js/user-form-validation.js"/>>
</script>

<script src=<c:url value="/js/language.js"/>>
</script>

</html>
