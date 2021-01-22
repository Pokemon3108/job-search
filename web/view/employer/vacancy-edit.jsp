<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Vacancy</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
    <link rel="script" href="<c:url value="/js/vacancy-form-validation.js"/>">
</head>
<body>

<c:import url="/view/headers/header-employer.jsp"/>

<div class="container">
    <h1 class="text-center my-sm-3"><fmt:message key="vacancy" bundle="${ rb }"/></h1>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <form action="${pageContext.request.contextPath}/job/employer/saveVacancyChanges" method="post"
                onsubmit="return validateForm(this);">

                    <c:if test='${invalidPosition==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidPosition" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="position"> <fmt:message key="desiredPosition" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="position" value="${vacancy.position}"
                               name="position" maxlength="255" minlength="3"
                               required>
                    </div>

                    <div class="alert alert-danger my-sm-3 " role="alert" id="cityError"></div>


                    <c:if test='${invalidCity==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidCity" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="city"> <fmt:message key="city" bundle="${ rb }"/></label>
                        <input type="text" class="form-control" id="city" value="${vacancy.city}"
                               name="city" maxlength="100" required>
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


                    <fmt:message key="salary" bundle="${ rb }"/>
                    <div class="row form-group">
                        <div class="col">
                            <input type="number" class="form-control" id="salary" min="0" value="${vacancy.salary}"
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

                    <c:if test='${invalidRequirements==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidRequirements" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="requirements"><fmt:message key="requirements" bundle="${ rb }"/></label>
                        <textarea class="form-control" id="requirements" rows="3" name="requirements" maxlength="2000" required>${vacancy.requirements}</textarea>
                    </div>

                    <c:if test='${invalidDuties==true}'>
                        <p class="alert alert-danger my-sm-3 " role="alert">
                            <fmt:message key="invalidDuties" bundle="${ rb }"/>
                        </p>
                    </c:if>

                    <div class="form-group">
                        <label for="duties"><fmt:message key="duties" bundle="${ rb }"/></label>
                        <textarea class="form-control" id="duties" rows="3" name="duties" maxlength="2000" required>${vacancy.duties}</textarea>
                    </div>


                    <input type="hidden" name="id" value="${vacancy.id}">
                    <input type="hidden" name="action" value="${action}">

                    <div>
                        <input type="submit" class="btn btn-success" value=
                                <fmt:message key="saveChange"
                                             bundle="${ rb }"/>/>
                    </div>

                </form>

                <button class="btn btn-secondary mt-1"
                        onclick="window.location.href = '${pageContext.request.contextPath}/job/employer/home';">
                    <fmt:message key="cancel" bundle="${ rb }"/>
                </button>


            </div>
        </div>
    </div>
</div>

<script src=<c:url value="/js/vacancy-form-validation.js"/>>
</script>

</body>
</html>