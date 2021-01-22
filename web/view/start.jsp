<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Welcome!</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<c:import url="/view/headers/header-guest.jsp"/>


<div class="container">
    <div class="row align-items-center">
        <div class="col-12 col-md-5 col-lg-6 order-md-2">
            <img style="max-width: 150%" src="<c:url value="/img/start.png"/>"
                 class="mw-md-150 mw-lg-130 mb-6 mb-md-0 aos-init aos-animate"
                 data-aos="fade-up" data-aos-delay="100">
        </div>
        <div class="col-12 col-md-7 col-lg-6 order-md-1 aos-init aos-animate" data-aos="fade-up">
            <h1 class="display-4 text-center text-md-left">
                <fmt:message key="welcome" bundle="${ rb }"/>
                <fmt:message key="toThe" bundle="${ rb }"/>
                Negotium!</h1>
            <p style="font-weight: 400;" class="lead text-center text-md-left mb-6 mb-lg-8">
                <fmt:message key="startInfo" bundle="${ rb }"/>
            </p>

            <div class="text-center text-md-left">
                <a href="${pageContext.request.contextPath}/job/registration" class="btn btn-success mr-1">
                    <fmt:message key="register" bundle="${ rb }"/>
                </a>
                <a href="${pageContext.request.contextPath}/job/login" class="btn btn-outline-success">
                    <fmt:message key="login" bundle="${ rb }"/>
                </a>
            </div>
        </div>
    </div>
</div>

<script src=<c:url value="/js/language.js"/>>
</script>

</body>
</html>
