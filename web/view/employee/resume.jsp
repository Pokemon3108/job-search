<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            <c:if test="${resume.personalInfo!=null}">

                <p>
                    <strong><fmt:message key="fullname" bundle="${ rb }"/>: </strong>
                    <span>${resume.personalInfo.name} ${resume.personalInfo.surname}</span>
                </p>


                <c:if test="${resume.personalInfo.gender!=''}">
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


                <c:if test="${resume.personalInfo.country!=''}">
                    <p>
                        <strong><fmt:message key="living_place" bundle="${ rb }"/>: </strong>
                        <span>${resume.personalInfo.country.name}</span>
                        <c:if test="${resume.personalInfo.city!=''}">
                            <span>, ${resume.personalInfo.city}</span>
                        </c:if>
                    </p>
                </c:if>
            </c:if>

            <a href="${pageContext.request.contextPath}/job/employee/changePersonalInfo"
               class="btn btn-success">Edit</a>
        </div>


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
            <a href="${pageContext.request.contextPath}/job/employee/changeContact" class="btn btn-success">Edit</a>
        </div>
    </div>

    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title"><fmt:message key="job_preference" bundle="${ rb }"/></h3>

            <c:if test="${resume.jobPreference!=null}">

                <c:if test='${resume.jobPreference.position!=""}'>
                    <p>
                        <strong><fmt:message key="desired_position" bundle="${ rb }"/>: </strong>
                        <span> ${resume.jobPreference.position} </span>
                    </p>
                </c:if>

                <c:if test='${resume.jobPreference.specialization.name!=""}'>
                    <p>
                        <strong><fmt:message key="specialization" bundle="${ rb }"/>: </strong>
                        <span>${resume.jobPreference.specialization.name}</span>
                    </p>
                </c:if>

                <c:if test='${resume.jobPreference.salary!=null}'>
                    <p>
                        <strong><fmt:message key="salary" bundle="${ rb }"/>: </strong>
                        <span>
                                ${resume.jobPreference.salary} ${resume.jobPreference.currency}
                        </span>
                    </p>
                </c:if>

                <p>
                    <strong><fmt:message key="schedule" bundle="${ rb }"/>: </strong>
                    <span>
                        <c:set var="sch" value="${resume.jobPreference.schedule}"/>
                                <fmt:message key="${fn:toLowerCase(sch)}" bundle="${ rb }"/>
                    </span>
                </p>


                <c:if test='${resume.jobPreference.experience!=null}'>
                    <p>
                        <strong><fmt:message key="experience" bundle="${ rb }"/>: </strong>
                        <span>
                                ${resume.jobPreference.experience}
                        </span>
                    </p>
                </c:if>

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
                    <span></span>
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
            <c:if test='${resume.skills!=null}'>
                <p>
                    <strong><fmt:message key="skills" bundle="${ rb }"/>: </strong>
                    <span>${resume.skills}</span>
                </p>
            </c:if>
            <a href="${pageContext.request.contextPath}/job/employee/changeSkills" class="btn btn-success">Edit</a>
        </div>
    </div>

</div>
</body>
</html>
