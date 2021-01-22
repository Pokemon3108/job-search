<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Employer vacancies</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css">
</head>
<body>

<c:import url="/view/headers/header-employer.jsp"/>

<div class="container">
    <h3 class="vacancy"><fmt:message key="vacancies" bundle="${ rb }"/></h3>

    <c:forEach var="vacancy" items="${vacancies}">
        <div class="card-body col mb-4 border my-sm-3">
            <form action="${pageContext.request.contextPath}/job/employer/editVacancy" method="get">

                <h3 class="card-title">${vacancy.position}</h3>

                <p>
                    <strong><fmt:message key="city" bundle="${ rb }"/>: </strong>
                    <span>${vacancy.city}</span>
                </p>

                <c:if test="${vacancy.salary!=null}">
                    <p>
                        <strong><fmt:message key="salary" bundle="${ rb }"/>: </strong>
                        <span>${vacancy.salary} ${vacancy.currency}</span>
                    </p>
                </c:if>

                <p>
                    <strong><fmt:message key="schedule" bundle="${ rb }"/>: </strong>
                    <c:set var="sch" value="${vacancy.schedule}"></c:set>
                    <span><fmt:message key="${fn:toLowerCase(sch)}" bundle="${ rb }"/></span>
                </p>
                <p>
                    <strong><fmt:message key="duties" bundle="${ rb }"/>: </strong>
                    <span>${vacancy.duties}</span>
                </p>
                <p>
                    <strong><fmt:message key="requirements" bundle="${ rb }"/>: </strong>
                    <span>${vacancy.requirements}</span>
                </p>

                <input type="hidden" name="vacancyId" value="${vacancy.id}">

                <div>
                    <button type="submit" class="btn btn-success">
                        <fmt:message key="edit" bundle="${ rb }"/>
                    </button>
                </div>

            </form>


            <button type="button" class="btn btn-danger mr-1" data-toggle="modal" data-target="#deleteVacancy">
                <fmt:message key="deleteVacancy" bundle="${ rb }"/>
            </button>

            <div class="modal fade" id="deleteVacancy" tabindex="-1" role="dialog" aria-labelledby="deleteVacancyLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteVacancyLabel">
                                <fmt:message key="sure" bundle="${ rb }"/></h5>
                        </div>



                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                <fmt:message key="close" bundle="${ rb }"/></button>


                            <form action="${pageContext.request.contextPath}/job/employer/deleteVacancy" method="post">
                                <input type="hidden" name="vacancyId" value="${vacancy.id}">
                                <button type="submit" class="btn btn-danger" id="deleteVac">
                                    <fmt:message key="deleteVacancy" bundle="${ rb }"/></button>
                            </form>
                        </div>


                    </div>
                </div>
            </div>

        </div>

    </c:forEach>

</div>

</body>


</html>
