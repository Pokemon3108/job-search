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

<c:choose>
    <c:when test="${sessionScope.role eq 'EMPLOYEE'}">
        <c:import url="/view/headers/header-employee.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role eq 'EMPLOYER'}">
        <c:import url="/view/headers/header-employer.jsp"/>
    </c:when>
    <c:when test="${sessionScope.role==null}">
        <c:import url="/view/headers/header-guest.jsp"/>
    </c:when>
</c:choose>

<div class="container">
    <h3 class="vacancy"><fmt:message key="vacancies" bundle="${ rb }"/></h3>

    <c:forEach var="vacancy" items="${vacancies}">
        <div class="card-body col mb-4 border my-sm-3">

            <a style="color: darkblue" href="${pageContext.servletContext.contextPath}/vacancy?id=${vacancy.id}">
                <h3>${vacancy.position}</h3>
            </a>

            <c:if test="${vacancy.salary!=null}">
                <p>
                    <strong><fmt:message key="salary" bundle="${ rb }"/>: </strong>
                    <span>${vacancy.salary} ${vacancy.currency}</span>
                </p>
            </c:if>
            <c:if test="${vacancy.salary==null}">
                <strong><fmt:message key="no_salary" bundle="${ rb }"/>: </strong>
            </c:if>

            <p>
                <strong><fmt:message key="company_name" bundle="${ rb }"/>: </strong>
                <span>${vacancy.employer.companyName}</span>
            </p>

            <p>
                <strong><fmt:message key="city" bundle="${ rb }"/>: </strong>
                <span>${vacancy.city}</span>
            </p>

            <c:if test="sessionScope.role eq 'EMPLOYEE'">
                <div>
                    <button type="submit" class="btn btn-success">
                        <fmt:message key="respond" bundle="${ rb }"/>
                    </button>
                </div>
            </c:if>
            </form>
        </div>
    </c:forEach>

</div>

<%--<div class="container">--%>
<%--    <div class="pagination p1">--%>
<%--        <ul>--%>
<%--            <a href="#"><li><</li></a>--%>
<%--            <a class="is-active" href="#"><li>1</li></a>--%>
<%--            <a href="${pageContext.request.contextPath}/job/showAllVacancies?page=${}"><li>2</li></a>--%>
<%--            <a href="#"><li>3</li></a>--%>
<%--            <a href="#"><li>></li></a>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</div>--%>

<c:import url="/view/headers/pagination.jsp"/>

</body>
</html>
