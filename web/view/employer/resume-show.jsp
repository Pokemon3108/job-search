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
    <link rel="stylesheet" href="<c:url value="/css/resume.css"/>" type="text/css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>

<c:import url="/view/imports/header-employer.jsp"/>
<div class="wrapper">
    <div class="sidebar-wrapper">
        <div class="profile-container">
            <h1 class="name">
                <span>${resume.personalInfo.name} ${resume.personalInfo.surname}</span>
            </h1>
            <h3 class="tagline">${resume.jobPreference.position}</h3>
        </div>

        <div class="contact-container container-block">
            <ul class="list-unstyled contact-list">
                <li class=""><i class="fa fa-city">
                    <c:if test="${resume.personalInfo.country!=null}">
                        <span>${resume.personalInfo.country.name}</span>
                        <c:if test="${resume.personalInfo.city!=null and resume.personalInfo.city!=''}">
                            <span>, ${resume.personalInfo.city}</span>
                        </c:if>
                    </c:if>
                </i>
                </li>

                <c:if test="${resume.contact!=null}">
                    <li class="email"><i class="fa fa-envelope"></i><a
                            href="mailto: ${resume.contact.email}">${resume.contact.email}</a>
                    </li>
                    <li class="phone"><i class="fa fa-phone"></i><a
                            href="tel:${resume.contact.telephone}">${resume.contact.telephone}</a></li>
                    <c:if test="${resume.contact.skype!=null}">
                        <li class="skype"><i class="fa fa-skype"></i>${resume.contact.skype}</li>
                    </c:if>
                </c:if>
            </ul>
        </div>

        <c:if test="${resume.language!=null}">
            <div class="languages-container container-block">
                <h2 class="container-block-title"><fmt:message key="foreignLanguage" bundle="${ rb }"/></h2>
                <ul class="list-unstyled interests-list">
                    <li>${resume.language.name.name} <span class="lang-desc">(${resume.language.level})</span></li>
                </ul>
            </div>
        </c:if>
    </div>

    <div class="main-wrapper">

        <section class="section summary-section">
            <h2 class="section-title"><i class="fa fa-user"></i><fmt:message key="persInfo" bundle="${ rb }"/></h2>
            <div class="summary">
                <c:if test="${resume.personalInfo.gender!=null}">
                    <c:set var="gender" value="${resume.personalInfo.gender}"/>
                    <strong> <fmt:message key="gender" bundle="${ rb }"/></strong>;
                    <fmt:message key="${fn:toLowerCase(gender)}" bundle="${ rb }"/>
                </c:if>
                <ctg:age birthday="${resume.personalInfo.birthday}"/>
            </div>
        </section>

        <section class="section summary-section">
            <h2 class="section-title"><i class="fa fa-user"></i><fmt:message key="jobPreference" bundle="${ rb }"/></h2>
            <div class="summary">
                <ul>
                    <li>
                        <strong><fmt:message key="specialization" bundle="${ rb }"/> </strong>
                        ${resume.jobPreference.specialization.name}</li>
                    <li>
                        <c:if test='${resume.jobPreference.salary!=null}'>
                            <strong><fmt:message key="salary" bundle="${ rb }"/>:</strong>
                            ${resume.jobPreference.salary} ${resume.jobPreference.currency}
                        </c:if>
                    </li>
                    <li>
                        <c:if test='${resume.jobPreference.experience!=null}'>
                            <strong> <fmt:message key="experience" bundle="${ rb }"/>: </strong>
                            ${resume.jobPreference.experience}
                        </c:if>
                    </li>
                </ul>
            </div>
        </section>

        <section class="section summary-section">
            <h2 class="section-title"><i class="fas fa-hammer mr-2"></i>
                <fmt:message key="skills" bundle="${ rb }"/></h2>
            <div class="summary">
                <p>${resume.skills}</p>
            </div>
        </section>


    </div>
</div>
<c:import url="/view/imports/footer.jsp"/>
</body>

<script src="https://kit.fontawesome.com/8350b1f3e1.js" crossorigin="anonymous"></script>

</html>
