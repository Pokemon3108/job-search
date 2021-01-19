<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Employer home</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<c:import url="/view/headers/header-employer.jsp"/>

<div class="container">
    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="pers_info" bundle="${ rb }"/>
            </h3>
            <c:if test="${resume.personalInfo!=null}">

                <c:if test="${resume.personalInfo.name!=null}">
                    <p>
                        <strong><fmt:message key="fullname" bundle="${ rb }"/>: </strong>
                        <span>${resume.personalInfo.name} ${resume.personalInfo.surname}</span>
                    </p>
                </c:if>


                <c:if test="${resume.personalInfo.gender!=null}">
                    <p>
                        <strong><fmt:message key="gender" bundle="${ rb }"/>: </strong>
                        <span>
                            <c:set var="gender" value="${resume.personalInfo.gender}"/>
                         <fmt:message key="${fn:toLowerCase(gender)}" bundle="${ rb }"/>
                        </span>
                    </p>
                </c:if>

                <c:if test="${age!=null}">
                    <p>
                        <strong><fmt:message key="age" bundle="${ rb }"/>: </strong>
                        <span>${age} <fmt:message key="years" bundle="${ rb }"/></span>
                    </p>
                </c:if>


                <c:if test="${resume.personalInfo.country!=null}">
                    <p>
                        <strong><fmt:message key="living_place" bundle="${ rb }"/>: </strong>
                        <span>${resume.personalInfo.country.name}</span>
                        <c:if test="${resume.personalInfo.city!=null}">
                            <span>, ${resume.personalInfo.city}</span>
                        </c:if>
                    </p>
                </c:if>
            </c:if>

            <a href="${pageContext.request.contextPath}/job/employer/changeInfo"
               class="btn btn-success"> <fmt:message key="edit" bundle="${ rb }"/></a>
        </div>
    </div>

    <div class="card-deck">

        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="contact" bundle="${ rb }"/>
            </h3>
            <c:if test="${resume.contact!=null}">

                <c:if test="${resume.contact.telephone!=null}">
                    <p>
                        <strong><fmt:message key="phone" bundle="${ rb }"/>: </strong>
                        <span>${resume.contact.telephone}</span>
                    </p>
                </c:if>

                <c:if test="${resume.contact.email!=null}">
                    <p>
                        <strong><fmt:message key="email" bundle="${ rb }"/>: </strong>
                        <span>${resume.contact.email}</span>
                    </p>
                </c:if>

                <c:if test="${resume.contact.email!=null}">
                    <p>
                        <strong>Skype: </strong>
                        <span>${resume.contact.skype}</span>
                    </p>
                </c:if>


            </c:if>
            <a href="${pageContext.request.contextPath}/job/employer/changeContact" class="btn btn-success">
                <fmt:message key="edit" bundle="${ rb }"/>
            </a>
        </div>
    </div>
</div>


</body>
</html>
