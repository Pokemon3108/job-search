<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Job preference</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
    <link rel="script" href="<c:url value="/js/job-preference-validation.js"/>">

</head>
<body onload="selectCurrency('${preference.currency}');
        selectSchedule('${preference.schedule}')">

<c:import url="/view/headers/header-employee.jsp"/>

<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="job_preference" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${pageContext.request.contextPath}/job/employee/changeJobPreference" method="post"
                      onsubmit="return validate(this);">

                    <div class="alert alert-danger my-sm-3 " role="alert" id="positionError"></div>

                    <c:if test='${invalidPosition==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalid_position" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="position"> <fmt:message key="desired_position" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="position" value="${preference.position}"
                               name="position"
                               required>
                    </div>


                    <fmt:message key="specialization" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="specialization">
                        <c:forEach items="${specializations}" var="specArr">
                            <c:if test="${preference.specialization.id==specArr.id}">
                            <option name="specialization" value="${specArr.id}" selected>
                                    ${specArr.name}
                            </option>
                            </c:if>
                            <c:if test="${preference.specialization.id!=specArr.id}">
                                <option name="specialization" value="${specArr.id}">
                                        ${specArr.name}
                                </option>
                            </c:if>
                        </c:forEach>
                    </select>

                    <c:if test='${invalidSalary==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalid_salary" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <fmt:message key="salary" bundle="${ rb }"/>
                    <div class="row form-group ">
                        <div class="col">
                            <input type="number" class="form-control" id="salary" min="0" value="${preference.salary}"
                                   name="salary">
                        </div>
                        <div class="col">
                            <select class="form-select" name="currency">
                                <c:forEach items="${currencies}" var="currencyArr">
                                    <option name="currency" value="${currencyArr}">
                                            ${currencyArr}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <fmt:message key="schedule" bundle="${ rb }"/>
                    <select class="form-select mb-2" name="schedule">
                        <c:forEach items="${schedules}" var="scheduleArr">
                            <option name="schedule" value="${scheduleArr}">
                                <c:set var="sch" value="${scheduleArr}"/>
                                <fmt:message key="${fn:toLowerCase(sch)}" bundle="${ rb }"/>
                            </option>
                        </c:forEach>
                    </select>

                    <c:if test='${invalidExperience==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalid_exper" bundle="${ rb }"/>
                        </p>
                    </c:if>


                    <div class="form-group">
                        <label class="form-label" for="typeNumber">
                            <fmt:message key="experience" bundle="${ rb }"/>
                        </label>
                        <input type="number" name="experience" value="${preference.experience}" id="typeNumber"
                               class="form-control" min="0" max="40"/>
                    </div>


                    <input type="hidden" name="id" value="${preference.id}">

                    <div>
                        <input type="submit" class="btn btn-success" value=
                                <fmt:message key="save_change"
                                             bundle="${ rb }"/>/>
                    </div>

                </form>

                <button class="btn btn-secondary mt-1"
                        onclick="window.location.href = '${pageContext.request.contextPath}/job/employee/resume';">
                    <fmt:message key="cancel" bundle="${ rb }"/>
                </button>


            </div>
        </div>
    </div>
</div>

</body>

<script src=<c:url value="/js/job-preference-validation.js"/>>
</script>

</html>
