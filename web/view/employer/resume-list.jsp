<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>All resumes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<c:import url="/view/imports/header-employer.jsp"/>

<div class="container">
    <div class="row">

        <h3 class="mt-2 text-center"><fmt:message key="all_resume" bundle="${ rb }"/></h3>
        <c:forEach var="resume" items="${resumeList}">
            <div class="mt-3 mr-1 card-body col-md-6 col-lg-3 center">
                <a style="color: darkblue"
                   href="${pageContext.servletContext.contextPath}/job/employer/employeeResume?id=${resume.id}">
                    <h3 style="color: darkblue">${resume.personalInfo.name}
                        <c:if test="${resume.personalInfo.surname!=''}">
                            ${resume.personalInfo.surname}
                        </c:if></h3>
                </a>

                    <p>${resume.jobPreference.position}</p>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
