<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
welcome
<form name="logoutForm"
	action="<%=request.getContextPath()%>/logout" method="POST">

	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
</form>

<!-- item-->
<a href="javascript: document.logoutForm.submit()"><span>Logout</span>
</a>
</body>
</html>