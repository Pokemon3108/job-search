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

<div class="modal fade" id="deleteModel" tabindex="-1" role="dialog" aria-labelledby="deleteModelLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteModelLabel"><fmt:message key="sure" bundle="${ rb }"/></h5>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <fmt:message key="close" bundle="${ rb }"/></button>


                <form action="${pageContext.request.contextPath}/job/auth/delete" method="post">
                    <button type="submit" class="btn btn-danger" id="delete_acc">
                        <fmt:message key="deleteAcc" bundle="${ rb }"/></button>
                </form>
            </div>


        </div>
    </div>
</div>
</body>
</html>

