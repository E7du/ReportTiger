<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("cxt", request.getContextPath());
%>
<jsp:include page="../common/header.jsp">
    <jsp:param value="添加统计Code" name="title"/>
</jsp:include>
<jsp:include page="../common/menu.jsp">
	<jsp:param value="2" name="active"/>
</jsp:include>
<jsp:include page="content.jsp"></jsp:include>
<script type="text/javascript">
	
	function regCode() {
		//validate
		$(".validate-form").validate();
		if ($(".validate-form").valid()) {
			var regcodeAjax = $.ajax({
				url : '${cxt}/tiger/regCode',
				type : "post",
				data : $(".validate-form").serialize()
			});
			//request
			$.when(regcodeAjax).done(function(data) {
				$("#ret").html("<h3>处理结果</h3><br/>" + data);
				$("#ret").addClass('alert alert-info');
				$(".validate-form")[0].reset();
			}).fail(function(data) {
				$("#ret").html("<h3>处理失败，请稍后重试</h3><br/>" + data);
			});
		}
	}

	function previewDDL() {
		//validate
		$(".validate-form").validate();
		if ($(".validate-form").valid()) {
			var previewAjax = $.ajax({
				url : '${cxt}/tiger/preview',
				type : "post",
				data : $(".validate-form").serialize()
			});
			//request
			$.when(previewAjax).done(function(data) {
				$("#ret").html("<h3>处理结果</h3><br/>" + data);
				$("#ret").addClass('alert alert-info');
			}).fail(function(data) {
				$("#ret").html("<h3>处理失败，请稍后重试!</h3><br/>" + data);
			});
		}
	}
</script>
<%@ include file="../common/footer.jsp" %>