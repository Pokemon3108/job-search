<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Vacancies</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<c:import url="/view/headers/header-employee.jsp"/>

<div class="container">
    <h3 class="vacancy"><fmt:message key="vacancies" bundle="${ rb }"/></h3>

    <c:forEach var="vacancy" items="${vacancies}">
        <div class="card-body col mb-4 border my-sm-3">

            <a style="color: darkblue" href="${pageContext.servletContext.contextPath}/job/vacancy?id=${vacancy.id}">
                <h3>${vacancy.position}</h3>
            </a>

            <c:if test="${vacancy.salary!=null}">
                <p>
                    <strong><fmt:message key="salary" bundle="${ rb }"/>: </strong>
                    <span>${vacancy.salary} ${vacancy.currency}</span>
                </p>
            </c:if>
            <c:if test="${vacancy.salary==null}">
                <strong><fmt:message key="noSalary" bundle="${ rb }"/>: </strong>
            </c:if>

            <p>
                <strong><fmt:message key="companyName" bundle="${ rb }"/>: </strong>
                <span>${vacancy.employer.companyName}</span>
            </p>

            <p>
                <strong><fmt:message key="city" bundle="${ rb }"/>: </strong>
                <span>${vacancy.city}</span>
            </p>

            <form action="${pageContext.request.contextPath}/job/employee/deleteRespondedVacancy" method="post">
                <input type="hidden" name="vacancyId" value="${vacancy.id}">
                <button type="submit" class="btn btn-danger mr-1">
                    <fmt:message key="deleteVacancy" bundle="${ rb }"/>
                </button>
            </form>
        </div>

    </c:forEach>

</div>

</body>
</html>
