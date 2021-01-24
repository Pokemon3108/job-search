<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="customtags" %>
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
                <fmt:message key="persInfo" bundle="${ rb }"/>
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


                <ctg:age birthday="${resume.personalInfo.birthday}"/>


                <c:if test="${resume.personalInfo.country!=null}">
                    <p>
                        <strong><fmt:message key="livingPlace" bundle="${ rb }"/>: </strong>
                        <span>${resume.personalInfo.country.name}</span>
                        <c:if test="${resume.personalInfo.city!=null}">
                            <span>, ${resume.personalInfo.city}</span>
                        </c:if>
                    </p>
                </c:if>
            </c:if>

            <a href="${pageContext.request.contextPath}/job/employee/changePersonalInfo"
               class="btn btn-success"><fmt:message key="edit" bundle="${ rb }"/></a>
        </div>


        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="contact" bundle="${ rb }"/>
            </h3>
            <ctg:contact contact="${resume.contact}"/>
            <a href="${pageContext.request.contextPath}/job/employee/changeContact" class="btn btn-success">
                <fmt:message key="edit" bundle="${ rb }"/>
            </a>
        </div>
    </div>

    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title"><fmt:message key="jobPreference" bundle="${ rb }"/></h3>

            <c:if test="${resume.jobPreference!=null}">

                <c:if test='${resume.jobPreference.position!=null}'>
                    <p>
                        <strong><fmt:message key="desiredPosition" bundle="${ rb }"/>: </strong>
                        <span> ${resume.jobPreference.position} </span>
                    </p>
                </c:if>

                <c:if test='${resume.jobPreference.specialization.name!=null}'>
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

                <c:if test='${resume.jobPreference.schedule!=null}'>
                    <p>
                        <strong><fmt:message key="schedule" bundle="${ rb }"/>: </strong>
                        <span>
                        <c:set var="sch" value="${resume.jobPreference.schedule}"/>
                                <fmt:message key="${fn:toLowerCase(sch)}" bundle="${ rb }"/>
                    </span>
                    </p>
                </c:if>


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
               class="btn btn-success"><fmt:message key="edit" bundle="${ rb }"/></a>
        </div>

        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="foreignLanguage" bundle="${ rb }"/>
            </h3>
            <div>
                <%--                <i  class="fas fa-language"></i>--%>
                <img class="lang" src="<c:url value="/img/translate.png"/>">
                <span>${resume.language.name.name}, ${resume.language.level}</span>
            </div>
            <a href="${pageContext.request.contextPath}/job/employee/changeLanguage" class="btn btn-success">
                <fmt:message key="edit" bundle="${ rb }"/>
            </a>
        </div>
    </div>

    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="skills" bundle="${ rb }"/>
            </h3>
            <c:if test='${resume.skills!=null}'>
                <p>
                    <span>${resume.skills}</span>
                </p>
            </c:if>
            <a href="${pageContext.request.contextPath}/job/employee/changeSkills" class="btn btn-success">
                <fmt:message key="edit" bundle="${ rb }"/>
            </a>
        </div>
    </div>

</div>
</body>
<script src="https://kit.fontawesome.com/8350b1f3e1.js" crossorigin="anonymous"></script>
</html>
