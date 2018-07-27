<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("cxt", application.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加App</title>
</head>
<body>
${cxt}
<form action="${cxt}/tiger/addapp" method="POST">
	<label>App包名</label><input type="text" name="packagename" value="${packagename}"/>(区分大小写)<br/>
	<input type="submit" value="提交"/>
</form>
</body>
</html>