<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Vacancy</title>
    <%--    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">--%>
    <link rel="stylesheet" href="<c:url value="/css/vacancy.css"/>" type="text/css">
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

<div class="job_details_area">
    <div class="vacancy-container">
        <div class="row">
            <div class="col-lg-8">
                <div class="job_details_header">
                    <div class="single_jobs d-flex justify-content-between">
                        <div class="jobs_left d-flex align-items-center">

                            <div class="jobs_conetent">
                                <h4>${vacancy.position}</h4>
                                <div class="links_locat d-flex align-items-center">
                                    <div class="location">
                                        <p><i class="fa fa-map-marker"></i>${vacancy.city}
                                        </p>
                                    </div>
                                    <div class="location">
                                        <p><i class="fa fa-clock-o"></i>
                                            <c:set var="sch" value="${vacancy.schedule}"/>
                                            <fmt:message key="${fn:toLowerCase(sch)}" bundle="${ rb }"/>
                                        </p>
                                    </div>
                                    <div class="location">
                                        <p><i class="fas fa-building"></i>
                                            ${vacancy.employer.companyName}
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${sessionScope.role eq 'EMPLOYEE' and respond!=true}">
                            <div class="jobs_right">
                                <div class="apply_now">
                                    <form action="${pageContext.request.contextPath}/job/employee/respondVacancy" method="post">
                                        <input type="hidden" name="vacancyId" value="${vacancy.id}">
                                        <button type="submit" class="btn btn-success mr-1">
                                            <fmt:message key="respond" bundle="${ rb }"/>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="descript_wrap">
                    <div class="single_wrap">
                        <h4><fmt:message key="requirements" bundle="${ rb }"/></h4>
                        <p>${vacancy.requirements}</p>
                    </div>
                    <div class="single_wrap">
                        <h4><fmt:message key="duties" bundle="${ rb }"/></h4>
                        <p>${vacancy.duties}</p>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="job_sumary">
                    <div class="summery_header">
                        <h3><fmt:message key="jobSummary" bundle="${ rb }"/></h3>
                    </div>
                    <div class="job_content">
                        <ul>
                            <li><strong><fmt:message key="salary" bundle="${ rb }"/></strong>
                                <span>${vacancy.salary} ${vacancy.currency}</span></li>
                            <li><strong><fmt:message key="location" bundle="${ rb }"/></strong>
                                <span>${vacancy.city}</span></li>
                            <li>
                                <strong><fmt:message key="schedule" bundle="${ rb }"/></strong>
                                <span>
                                <c:set var="sch" value="${vacancy.schedule}"/>
                                            <fmt:message key="${fn:toLowerCase(sch)}" bundle="${ rb }"/>
                                 </span></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<script src="https://kit.fontawesome.com/8350b1f3e1.js" crossorigin="anonymous"></script>

</body>
</html>
