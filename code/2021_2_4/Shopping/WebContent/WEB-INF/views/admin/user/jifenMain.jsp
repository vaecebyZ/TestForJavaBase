<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>商城后台</title>
	<jsp:include page="../commonCss.jsp"/> 
</head>
<body class="blank">
<jsp:include page="../main/top.jsp"/> 
<jsp:include page="../main/left.jsp"/>
<jsp:include page="../commonJs.jsp"/> 
<div id="wrapper">

	<div class="content animate-panel">	
		<div class="row">
			<div class="col-lg-12">
				<div class="hpanel">
					<div class="panel-body">
						<table id="userInfoList" style="width: 100%;" class="table table-striped table-bordered table-hover"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<form id="forwordform" action='<c:url value="/admin/user/edit?${_csrf.parameterName}=${_csrf.token}" />' method='POST'>
	<input type="hidden" name="userId" id="userId"/>
	<input type="hidden" name="roleStr" id="roleStr"/>
</form>

<script type="text/javascript">
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';
$(function() {
	$('.date').datepicker();
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	userInfoListData()
});


function userInfoListData() {
	if (typeof oTable == 'undefined') {
		// dataTable load
		oTable = $('#userInfoList')
				.dataTable(
						{
							'bPaginate' : true, // 翻页功能
							'sServerMethod' : 'POST', // 提交方式
							'bServerSide' : true,
							'bProcessing' : true,
							'bFilter' : false,
							"bSort" : false,
							'sAjaxSource' : contextPath + '/admin/userjiFenInfoDataTable',
							"fnServerParams" : function(aoData) { // 查询条件
								aoData.push();
							},aoColumns : [{
										'mData' : 'id',
										'sTitle' : '用户编号',
										'visible' : false
									},{
										'sTitle' : '用户名',
										'mData' : 'userName'
									},{
										'sTitle' : '昵称',
										'mData' : 'nickname'
									},{
										'sTitle' : '邮箱',
										'mData' : 'email'
									},{
										'sTitle' : '注册时间',
										'mData' : 'registerTime',
										'mRender' : function(data) {
											return data;
										}
									},{
										'sTitle' : '积分',
										'mData' : 'jifen',
										'mRender' : function(data) {
											if(data == null){
												return  0
											}
											return data;
										}
									},{
										'sTitle' : '操作',
										'mData' : 'id',
										'sWidth' : '5%',
										'mRender' : function(data, type, full) {
											//alert(full.role);
											var returnValue = '<button id="update" class="btn btn-default btn-sm" onclick="editUser('+data+',\''+full.role+'\')"><i class="fa fa-pencil"></i>编辑</button>';
											return returnValue;
										}
									}] 

						});
	} else {
		// oTable.fnClearTable(0);
		var oSettings = oTable.fnSettings();
		oSettings._iDisplayLength = parseInt($(
				'[name=userInfoList_length] option:selected').val());
		$('.dataTables_length select').val(
				$('[name=userInfoList_length] option:selected').val());
		oSettings._iDisplayStart = 0;
		oTable.fnDraw();
	}
}


</script>
</body>
</html>