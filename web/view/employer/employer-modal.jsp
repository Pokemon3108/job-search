<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Employer</title>
</head>
<body>
<div class="modal fade" id="companyModal" tabindex="-1" role="dialog" aria-labelledby="companyModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="companyModalLabel">${vacancy.employer.companyName}</h5>
            </div>

            <div class="row align-items-start">
                <div class="col">
                    <i class="fas fa-city ml-2"></i>
                    <span>${vacancy.employer.country.name}</span>
                    <c:if test="${vacancy.employer.city!=null and vacancy.employer.city!=''}">
                        <span>, ${vacancy.employer.city}</span>
                    </c:if>

                </div>
                <div class="col">
                    <i class="fas fa-at"></i>
                    <span>${vacancy.employer.contact.email}</span>
                </div>
            </div>

            <div class="row align-items-center">
                <div class="col">
                    <i class="fas fa-phone-square-alt ml-2"></i>
                    <span>${vacancy.employer.contact.telephone}</span>
                </div>
                <c:if test="${vacancy.employer.contact.skype!=null and vacancy.employer.contact.skype!=''}">
                    <div class="col">
                        <i class="fab fa-skype"></i>
                        <span>${vacancy.employer.contact.skype}</span>
                    </div>
                </c:if>

            </div>


            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <fmt:message key="close" bundle="${ rb }"/></button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
