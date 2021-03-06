<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${cookie.lang.value}" scope="session"/>
<fmt:setBundle basename="property.pagecontent" var="rb"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="selectLan" bundle="${ rb }"/></h5>
            </div>

                <div class="modal-body">

                    <div class="form-group">
                        <c:set var="lang" value="${languages}"/>
                        <c:forEach items="${languages}" var="lang">
                            <div class="custom-radio custom-control-inline">
                                <label>
                                    <input type="radio" name="lang" value="${lang}" id="lang" checked>
                                    <fmt:message key="${lang.viewName}" bundle="${ rb }"/>
                                </label>
                            </div>
                        </c:forEach>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="close" bundle="${ rb }"/></button>
                    <button type="button" class="btn btn-success" id="change_lan"><fmt:message key="saveChange" bundle="${ rb }"/></button>
                </div>

        </div>
    </div>
</div>

</body>
</html>
