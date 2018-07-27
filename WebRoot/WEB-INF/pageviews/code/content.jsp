<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	request.setAttribute("cxt", request.getContextPath());
%>
<div class='container-fluid'>
	<div class='row-fluid' id='content-wrapper'>
		<div class='span12'>
			<div class='page-header'>
				<h1 class='pull-left'>
					<i class='icon-comments'></i><span>添加Code</span>
				</h1>
			</div>
			<div class='alert alert-info'>添加一个统计code 项</div>
			<div class='row-fluid'>
				<div class='span12 box'>
					<div class='box-header blue-background'>
						<div class='title'>Code信息</div>
					</div>
					<div class='box-content'>
						<form class='form form-horizontal validate-form'
							accept-charset="UTF-8" style='margin-bottom: 0;'>
							<div class="control-group">
								<label class="control-label" for="validation_code">Code</label>
								<div class="controls">
									<input data-rule-required="true" id="validation_code"
										name="code" placeholder="数据统计Code" type="text">
								</div>
							</div>
							<div class='control-group'>
								<label class='control-label' for='validation_intro'>描述</label>
								<div class="controls">
									<input data-rule-required="true" id="validation_intro"
										placeholder='描述' name="codecomment" type="text" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="validation_app">选择App</label>
								<div class="controls">
									<select data-rule-required="true" id="validation_app"
										name="app">
										<option></option>
										<c:forEach items="${apps}" var="app">
											<option value="${app.id}">${app.packageName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<!-- data -->
							<div class="control-group">
								<label class="control-label" for="validation_fields">数据列[JSONArray]</label>
								<div class="controls">
									<textarea class="input-block-level" data-rule-required='true'
										id="validation_fields" name="fields"
										placeholder="数据列[JSONArray]" 
										rows="5"></textarea>
								<small class="muted">
									示例： ["name:2:20:'':名字:0:0","address:2:50:'':地址:0:0"]<br/>
									说明： 数据位 jsonarry，每一个元素代表每一个字段。<br/>
										  每个字段含7个属性，依次为字段名，字段类型，字段大小，默认值，注释，PK，UK；<br/>
										  字段类型：1:int;2:varchar;3:text;4:bigint;5:tinyint;<br/>
										  Pk、UK：0:否,1:是;<br/>
								</small>
								</div>
							</div>
							<!-- data end -->
							<div class='form-actions' style='margin-bottom: 0'>
								<button class='btn btn-primary' type="button"
									onclick="regCode();">
									<i class='icon-save'></i> 添加
								</button>
								<button class='btn btn-primary' type="button"
									onclick="previewDDL();">预览</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- 处理结果 -->
			<div id="ret"></div>
		</div>
	</div>
</div>

