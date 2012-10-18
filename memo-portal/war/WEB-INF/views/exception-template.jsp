<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tls" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<fmt:setBundle basename="memo.web.portal.server.messages" scope="session" />
	<c:set value="${pageContext.request.contextPath}" var="contextPath" scope="session" />
	<head>
		<title>
			<fmt:message key="error.title" />
		</title>
	</head>
	<body>
		<tls:insertAttribute name="body" />
	</body>
</html>
