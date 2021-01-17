<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Resume</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>


<c:import url="/view/headers/header-employee.jsp"/>


<div class="container">
    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="pers_info" bundle="${ rb }"/>
            </h3>
            <div>
                <p>
                    <span></span>
                </p>
                <p>
                    <strong>Level: </strong>
                    <span></span>
                </p>
            </div>
            <a href="${pageContext.request.contextPath}/job/employee/changePersonalInfo"
               class="btn btn-success">Edit</a>
        </div>
        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="contact" bundle="${ rb }"/>
            </h3>
            <div>
                <p>
                    <span></span>
                </p>
                <p>
                    <strong>Level: </strong>
                    <span></span>
                </p>
            </div>
            <a href="${pageContext.request.contextPath}/job/employee/changeContact" class="btn btn-success">Edit</a>
        </div>
    </div>

    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title"><fmt:message key="job_preference" bundle="${ rb }"/></h3>

            <c:if test='${resume.jobPreference.position!=""}'>
            <p>
                <strong><fmt:message key="desired_position" bundle="${ rb }"/>: </strong>
                <span>${resume.jobPreference.position}</span>
            </p>
            </c:if>

            <c:if test='${resume.jobPreference.specialization.name!=""}'>
                <p>
                    <strong><fmt:message key="specialization" bundle="${ rb }"/>: </strong>
                    <span>${resume.jobPreference.specialization.name}</span>
                </p>
            </c:if>


            <a href="${pageContext.request.contextPath}/job/employee/changeJobPreference"
               class="btn btn-success">Edit</a>
        </div>

        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="foreign_language" bundle="${ rb }"/>
            </h3>
            <div>
                <p>
                    <span></span>
                </p>
                <p>
                    <strong>Level: </strong>
                    <span th:text="${lang.level}"></span>
                </p>
            </div>
            <a href="${pageContext.request.contextPath}/job/employee/changeLanguage" class="btn btn-success">Edit</a>
        </div>
    </div>

    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="skills" bundle="${ rb }"/>
            </h3>
            <div>
                <p>
                    <span></span>
                </p>
                <p>
                    <strong>Level: </strong>
                    <span></span>
                </p>
            </div>
            <a href="${pageContext.request.contextPath}/job/employee/changeSkills" class="btn btn-success">Edit</a>
        </div>
    </div>
</div>
</body>
</html>
