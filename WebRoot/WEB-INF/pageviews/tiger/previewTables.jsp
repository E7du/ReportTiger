<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<textarea name="t1" cols="120" rows="30">
	<c:forEach var="sql" items="${sqls}" >
	<c:out value="${sql}"></c:out>
	</c:forEach>
	</textarea>
	<input type="button" value="确认创建" id="creatTable" onclick="javascript:creatTable()" >
	
</body>
</html>