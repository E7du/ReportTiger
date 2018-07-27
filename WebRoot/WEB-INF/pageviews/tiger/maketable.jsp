<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String cxt = application.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生成表结构</title>
<script src="<%=cxt %>/js/jquery-1.11.0.min.js"></script>
<style type="text/css">
	#preview{
		background-color: lightgray;
		margin-top:30px;
		width:50%;
	}
</style>
</head>
<body>
<div>
<form action="<%=cxt %>/tiger/regcode" name="myform" id="formInfo" method="post">
	<label>数据助记码:</label><input name="code"/><br/>
	<label>数据描述:</label><input name="codecomment" /><br/>
	 选择App:<select name ="app">
	 	<option>选择app</option>
		<c:forEach items="${apps}" var="app">
		<option value="${app.id}">${app.packagename}</option>
		</c:forEach>
	</select><br/>
	数据列：<input  name="colcount" type="text" id="count" size="3" maxlength="2"  >
		<input type="button" onClick="javascript:addColumns();" name="button" id="button" value="确定">
	<table border="0" cellpadding="0"  cellspacing="1" id="tid">
	<tr>
		<td >
		列名：
		</td>
		<td >
		列类型：
		</td>
		<td >
		列大小：
		</td>
		<td >
		默认值
		</td>
		<td >
		注释：
		</td>
		<td >
		PK：
		</td>
		<td >
		UK：
		</td>
		<td >
		操作
		</td>
	</tr>
</table>
<input type="button" onclick="javascript:preview()"  value="点击预览">
<input type="submit" value="提交">
</form>
</div>
<div id="preview">
</div>
<div id="message">
apperr : ${apperr==null?"":apperr}<br/>
codeerr: ${codeerr==null?"":codeerr}<br/>
codecommenterr : ${codecommenterr==null?"":codecommenterr}<br/>
colcounterr: ${colcounterr==null?"":colcounterr}<br/>
colnameerr: ${colnameerr==null?"":colnameerr}<br/>
coltypeerr: ${coltypeerr==null?"":coltypeerr}<br/>
colsizeerr: ${colsizeerr==null?"":colsizeerr}<br/>
colinitvalueerr: ${colinitvalueerr==null?"":colinitvalueerr}<br/>
colcommenterr: ${colcommenterr==null?"":colcommenterr}<br/>
colpkerr: ${colpkerr==null?"":colpkerr}<br/>
colukerr: ${colukerr==null?"":colukerr}<br/>
</div>
<script type="text/javascript">

no=0;
function addColumns(){
	var trid;
	var rowsNo = document.myform.colcount.value;
	if(rowsNo=="" || isNaN(rowsNo)){
		alert("请输入行数！");
		document.myform.zl.focus();
		return;
	}
	var tb = document.getElementById("tid");
	for(var i=0;i<rowsNo;i++){
		var tr = tb.insertRow();
		no++;
		tr.id='tr'+no;
		var td1 = tr.insertCell();
		var td2 = tr.insertCell();
		var td3 = tr.insertCell();
		var td4 = tr.insertCell();
		var td5 = tr.insertCell();
		var td6 = tr.insertCell();
		var td7 = tr.insertCell();
		var td8 = tr.insertCell();
		tr.height='20';
		tr.bgColor="#ffffff";
		// 列名
		td1.innerHTML='<td ><input name="colname" type="text" ></td>';
		// 列类型
		td2.innerHTML='<td ><select name="coltype">'
						+'<c:forEach items="${types}" var="type">'
						+'<option value="${type.id}">${type.name}</option>'
						+'</c:forEach>'
						+'</select>'
						+'</td>';
		// 列大小
		td3.innerHTML='<td ><input name="colsize" type="text" onchange="javascript:number(this.value)"></td>';
		// 列默认值
		td4.innerHTML='<td ><input name="colinitvalue" type="text"></td>';
		// 列注释
		td5.innerHTML='<td ><input name="colcomment" type="text"></td>';
		// 是否主键
		td6.innerHTML='<td ><select name="colpk">'
						+'<option value="0">否</option>'
						+'<option value="1">是</option>'
						+'</select>'
						+'</td>';
		// 是否 unique
		td7.innerHTML='<td ><select name="coluk">'
						+'<option value="0">否</option>'
						+'<option value="1">是</option>'
						+'</select>'
						+'</td>';
		td8.innerHTML='<td ><a href="javascript:deleteTr('+no+')">删除</a></td>';
	}
}

function deleteTr(obj){
	var tb = document.getElementById("tid");
	var tr = document.getElementById("tr"+obj);
	tr.parentNode.removeChild(tr);
}

function preview(){
	var url = "<%=cxt %>/tiger/preview";
	var data = $("#formInfo").serialize();
	$.ajax({
		url:url,
		type:"post",
		data:data,
		success:function(data){
			$("#preview").html(data);
		}
	});
}

function number(no){
	if(!isNaN(no)){
		}else{
		   alert("请输入数字");
		}
}

</script>
</body>
</html>