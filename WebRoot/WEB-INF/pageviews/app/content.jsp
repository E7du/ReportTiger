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
					<i class='icon-comments'></i> <span>添加App</span>
				</h1>
			</div>
			<div class='alert alert-info'>
				添加一个 App, 用于管理待统计的 App 数据,iOS的 bundleid,Android 的包名
			</div>
			<div class='row-fluid'>
				<div class='span12 box'>
					<div class='box-header blue-background'>
						<div class='title'>待添加App的信息</div>
					</div>
					<div class='box-content'>
						<form class='form form-horizontal validate-form'
							accept-charset="UTF-8"
							style='margin-bottom: 0;'>
							<div class='control-group'>
								<label class='control-label' for='validation_packagename'>包名</label>
								<div class="controls">
                                      <input data-rule-required="true" id="validation_packagename"
                                       placeholder='*' name="packagename" type="text" 
                                       onkeyup="checkApp();" maxlength="10"/>
                                       <small class="muted" id="validation_packagename_ret">(${packname})</small>
                                 </div>
                            </div>
							<div class='form-actions' style='margin-bottom: 0'>
								<button class='btn btn-primary' type="button" onclick="addApp();">
									<i class='icon-save'></i> 添加
								</button>
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

