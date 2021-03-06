#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@page import="com.kidbear.${artifactId}.manager.tec.TecMgr"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>科技数据</title>
<!-- <style type="text/css">
.player {
	padding: 50px;
}

.btn {
	width: 100%;
	height: 50px;
	line-height: 50px;
	background-color: lightblue;
}

.btn:hover {
	opacity: 0.8;
}
</style> -->
<script type="text/javascript">
function updateTec(userID,tecId) {
	var userID = userID;
	var id = tecId;
	var level = ${symbol_dollar}("${symbol_pound}level"+id+"").val();
//	var isOpen = ${symbol_dollar}("${symbol_pound}isOpen"+id+"").val();
	if(level==''){
		alert('请输入科技等级');
		return false;
	}
	var datas = {
		"userID":userID,
		"id":id,
		"level":level
	//	"isOpen":isOpen
	};;;;;;
	${symbol_dollar}.ajax({
		type : "post",
		dataType : "text",
		data :datas,
		url : "../datas/saveTecHandle",
		success : function(data) {
			if (data == "success") {
				skip("../datas/teclist?userID=" + userID);
				alert('修改科技成功');
			} else {
				alert('修改科技失败');

			}
		}
	});
}
</script>
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h2>
						<i class="fa fa-table red"></i><span class="break"></span><strong>科技信息(UID:
							<fmt:formatNumber type='number' value='${symbol_dollar}{userID }'></fmt:formatNumber>
							<input type="hidden" id="userID" value="${symbol_dollar}{userID }" />)
						</strong>
					</h2>
					<div class="panel-actions">
						<a href="${symbol_pound}" class="btn-close"
							onclick="skip('../datas/teclist?userID=${symbol_dollar}{userID}')"><i
							class="fa fa-refresh"></i></a> <a href="${symbol_pound}" class="btn-close"
							onclick="skip('../datas/player?userID=${symbol_dollar}{userID}')"><i
							class="fa fa-reply"></i></a>
					</div>
				</div>
				<div class="panel-body">
					<table
						class="table table-bordered table-striped table-condensed table-hover">
						<thead>
							<tr>
								<th>科技编号</th>
								<th>科技名字</th>
								<th>科技等级</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${symbol_dollar}{tecs}" var="tec">
								<tr>
									<td><input id="tec${symbol_dollar}{tec.id }" type="number"
										class="form-control" value="${symbol_dollar}{tec.tec }" disabled="disabled" /></td>
									<c:set var="kejiOpenMap"
										value="<%=TecMgr.getInstance().kejiOpenMap%>" />
									<td>${symbol_dollar}{kejiOpenMap[tec.tec].scienceOpen}</td>
									<td><input id="level${symbol_dollar}{tec.id }" type="number"
										class="form-control" value="${symbol_dollar}{tec.level }"></td>
									<td><button class="btn btn-primary"
											onclick="updateTec('${symbol_dollar}{userID }','${symbol_dollar}{tec.id }')">修改</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!--/col-->
	</div>
	<!--/row-->
</body>
</html>