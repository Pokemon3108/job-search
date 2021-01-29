<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Employer home</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>
<c:import url="/view/imports/header-employer.jsp"/>

<div class="container">
    <div class="card-deck">
        <div class="card-body border my-sm-3">
            <h3 class="card-title">
                <fmt:message key="persInfo" bundle="${ rb }"/>
            </h3>


                <c:if test="${employer.companyName!=null}">
                    <p>
                        <strong><fmt:message key="companyName" bundle="${ rb }"/>: </strong>
                        <span>${employer.companyName}</span>
                    </p>
                </c:if>


                <c:if test="${employer.country.id!=null}">
                    <p>
                        <strong><fmt:message key="country" bundle="${ rb }"/>: </strong>
                        <span>${employer.country.name}</span>
                    </p>
                </c:if>

                <c:if test="${employer.city!=null}">
                    <p>
                        <strong><fmt:message key="city" bundle="${ rb }"/>: </strong>
                        <span>${employer.city}</span>
                    </p>
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
            <ctg:contact contact="${employer.contact}"/>
            <a href="${pageContext.request.contextPath}/job/employer/changeContact" class="btn btn-success">
                <fmt:message key="edit" bundle="${ rb }"/>
            </a>
        </div>
    </div>
</div>
<c:import url="/view/imports/footer.jsp"/>

</body>
</html>
