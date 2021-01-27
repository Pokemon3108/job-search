<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Vacancies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

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
    <form action="${pageContext.request.contextPath}/job/filterVacancies" method="get" id="formFilter">
    <div class="row">

                <div class="col-lg-4 mr-5">
                    <h3 class="filter"><fmt:message key="filter" bundle="${ rb }"/></h3>
                <input type="hidden" name="currentPage" value="1">

                <div class="card-body col mb-4 border my-sm-3">
                    <div class="form-group">
                        <input type="text" class="form-control" id="position"
                               name="position" maxlength="255" minlength="3" value="${vacancyParams.position}"
                               placeholder=
                               <fmt:message key="desiredPosition" bundle="${ rb }"/>>
                    </div>

                    <select class="form-select mb-2" name="country">
                        <option class="default-selector" selected><fmt:message key="country" bundle="${ rb }"/></option>
                        <c:forEach items="${countries}" var="countryArr">
                            <c:if test="${vacancyParams!=null  and vacancyParams.countryId==countryArr.id}">
                                <option name="country" value="${countryArr.id}" selected>${countryArr.name}</option>
                            </c:if>
                            <c:if test="${vacancyParams!=null  and vacancyParams.countryId!=countryArr.id}">
                                <option name="country" value="${countryArr.id}">${countryArr.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>


                    <select class="form-select mb-2" name="specialization">
                        <option class="default-selector" selected><fmt:message key="specialization"
                                                                               bundle="${ rb }"/></option>
                        <c:forEach items="${specializations}" var="specArr">
                            <c:if test="${vacancyParams!=null  and vacancyParams.specializationId==specArr.id}">
                                <option name="specialization" value="${specArr.id}" selected>
                                        ${specArr.name}
                                </option>
                            </c:if>
                            <c:if test="${vacancyParams!=null  and vacancyParams.specializationId!=specArr.id}">
                                <option name="specialization" value="${specArr.id}">
                                        ${specArr.name}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <div>
                        <input type="submit" class="btn btn-success" value=
                                <fmt:message key="search" bundle="${ rb }"/> />
                    </div>

                </div>
        </div>

        <div class="col-lg-7">
            <h3 class="vacancy"><fmt:message key="vacancies" bundle="${ rb }"/></h3>

            <c:forEach var="vacancy" items="${vacancies}">
                <div class="card-body col mb-4 border my-sm-3">

                    <a style="color: darkblue"
                       href="${pageContext.servletContext.contextPath}/job/vacancy?id=${vacancy.id}">
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

                </div>
            </c:forEach>

            <c:if test="${action=='show'}">
            <c:set var="paginationPath" scope="session" value="/job/showAllVacancies?currentPage=" />
            </c:if>

            <c:if test="${action=='filter'}">
                <c:set var="paginationPath" scope="session" value="/job/filterVacancies?currentPage=" />
            </c:if>

            <c:import url="/view/headers/pagination.jsp"/>
            </form>
        </div>

    </div>
</div>


</body>
</html>
