<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Vacancy error</title>

    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<c:import url="/view/headers/header-employer.jsp"/>

<div class="container">
    <p  class="font-weight-bold vacancy-error">
        <fmt:message key="vacancyError" bundle="${ rb }"/>
        <a href="${pageContext.request.contextPath}/job/employer/changeInfo">
            <fmt:message key="fillCompanyInfo" bundle="${ rb }"/>
        </a>
    </p>
</div>

</body>
</html>
