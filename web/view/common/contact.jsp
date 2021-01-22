<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Contact</title>
    <link rel="script" href="<c:url value="/js/contact-form-validation.js"/>">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<c:choose>
    <c:when test="${sessionScope.role eq 'EMPLOYEE'}">
        <c:import url="/view/headers/header-employee.jsp"/>
        <c:set var="post_path" value="${pageContext.request.contextPath}/job/employee/changeContact"/>
        <c:set var="back_path" value="${pageContext.request.contextPath}/job/employee/resume"/>
    </c:when>
    <c:when test="${sessionScope.role eq 'EMPLOYER'}">
        <c:import url="/view/headers/header-employer.jsp"/>
        <c:set var="post_path" value="${pageContext.request.contextPath}/job/employer/changeContact"/>
        <c:set var="back_path" value="${pageContext.request.contextPath}/job/employer/home"/>
    </c:when>
</c:choose>


<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="contact" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${post_path}" method="post" onsubmit="return validate(this);">

                    <%--                    <input type="hidden" name="page" value="${pageContext.request.servletPath}">--%>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="contactError"></div>

                    <c:if test='${isInvalidTelephone==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidPhone" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="number"> <fmt:message key="phone" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="number" value="${contact.telephone}" name="number">
                    </div>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="emailError"></div>

                    <c:if test='${invalidEmail==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidEmail" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="email"><fmt:message key="email" bundle="${ rb }"/></label>
                        <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                               value="${contact.email}"
                               name="email"
                               placeholder=
                               <fmt:message key="email" bundle="${ rb }"/>
                                       required>
                    </div>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="skypeError"></div>

                    <div class="form-group">
                        <label for="skype">Skype</label>
                        <input type="text" class="form-control" id="skype" value="${contact.skype}" name="skype">
                    </div>

                    <input type="hidden" name="id" value="${contact.id}">

                    <div>
                        <input type="submit" class="btn btn-success" value=" <fmt:message key="saveChange" bundle="${ rb }"/>"/>
                    </div>
                </form>

                <form action="${back_path}">
                    <button type="submit" class="btn btn-secondary">
                        <fmt:message key="cancel" bundle="${ rb }"/>
                    </button>
                </form>

            </div>
        </div>
    </div>
</div>

</body>

<script src=<c:url value="/js/contact-form-validation.js"/>>
</script>

</html>
