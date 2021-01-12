<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="ru_RU" scope="session" />
<fmt:setBundle basename="property.pagecontent" var="rb" />
<html><head>
  <title>Registration</title>
</head>
<body>
<fmt:message key="welcome" bundle="${ rb }" />
<hr/>

</body></html>
