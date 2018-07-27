<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("cxt", request.getContextPath());
%>
<jsp:include page="../common/header.jsp">
    <jsp:param value="创建App" name="title"/>
</jsp:include>
<jsp:include page="../common/menu.jsp">
	<jsp:param value="1" name="active"/>
</jsp:include>
<jsp:include page="content.jsp"></jsp:include>
<script type="text/javascript">
	function addApp() {
		$(".validate-form").validate();
			if ($(".validate-form").valid()) {
				var addAppAjax = $.ajax({
					url : '${cxt}/tiger/addApp',
					type : "post",
					data : $(".validate-form").serialize()
				});
				//request
				$.when(addAppAjax).done(function(data) {
					$("#ret").html("<h3>处理结果</h3><br/>" + data);
					$("#ret").addClass('alert alert-info');
					$(".validate-form")[0].reset();
				}).fail(function(data) {
					$("#ret").html("<h3>处理失败，请重试</h3><br/>" + data);
				});
		}
	}

	function checkApp(){
		//reset
		$("#ret").html("");
		$("#ret").removeClass('alert alert-info');
		$("#validation_packagename_ret").html("(${packname}"+$("#validation_packagename").val()+")");
		//validate
		$(".validate-form").validate();
		if ($(".validate-form").valid()) {
			var checkAppAjax = $.ajax({
				url : '${cxt}/tiger/validatePackage',
				type : "post",
				data : $(".validate-form").serialize()
			});
			//request
			$.when(checkAppAjax).done(function(data){
				$("#ret").html("<h3>处理结果</h3><br/>" + data);
				$("#ret").addClass('alert alert-info');
			}).fail(function(){
				$("#ret").html("<h3>处理失败，请重试</h3><br/>" + data);
			});
			
		}
	}
</script>
<%@ include file="../common/footer.jsp" %>