<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Vacancy error</title>
</head>
<body>
<c:import url="/view/headers/header-employer.jsp"/>

<div class="container">
    <p style="font-size: 16px" class="font-weight-bold">
        <fmt:message key="vacancy-error" bundle="${ rb }"/>
        <a href="${pageContext.request.contextPath}/job/employer/changeInfo">
            <fmt:message key="fill_company_info" bundle="${ rb }"/>
        </a>
    </p>
</div>

</body>
</html>
