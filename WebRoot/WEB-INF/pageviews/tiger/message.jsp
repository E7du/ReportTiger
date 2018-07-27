<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
	String cxt = application.getContextPath();
	String message = (String)request.getAttribute("message"); 
	System.out.println(message);
%>
<script type="text/javascript">
$(function(){
	alert("${message}");
	<%
	if(message.contains("成功")){
		%>
		window.location="<%=cxt%>/table";
		<%
	}
	%>
});
</script>
</body>
</html>