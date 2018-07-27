<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("cxt", request.getContextPath());
%>
<jsp:include page="../common/header.jsp">
    	<jsp:param value="Dashboard" name="title"/>
</jsp:include>
<jsp:include page="../common/menu.jsp"></jsp:include>
<jsp:include page="content.jsp"></jsp:include>
<%@ include file="../common/footer.jsp" %>