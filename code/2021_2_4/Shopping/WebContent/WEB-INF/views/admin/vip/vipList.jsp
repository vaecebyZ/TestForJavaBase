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
	<div class="small-header transition animated fadeIn">
		<div class="content animate-panel">
		    <div class="hpanel">
		        <div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="hpanel">
								<div id="error_div" class="text-left"></div>
									<form id="form_userInfo" name="form_userInfo" action="userInfo_save.do" method="POST">
										<div class="form-group col-lg-6">
											<label>VIP注册时间</label>
					                        <div class="input-group col-sm-12 date">
					                            <input name="registerTimeFrom" type="text" class="form-control">
					                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					                        </div>
				                        </div>
										<div class="form-group col-lg-6">
											<label>至</label>
					                        <div class="input-group col-sm-12 date">
					                            <input name="registerTimeTo" type="text" class="form-control">
					                            <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
					                        </div>
										</div>	
										<div class="form-group col-lg-6">
											<label>VIP名称</label>
												<input type="text" id="userName" name="userName"
													class="form-control" maxlength="50" placeholder="会员名"/>
										</div>
										<div class="form-group col-lg-6">
											<div class="text-right m-t-xs">
													<button id="search" class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
														type="button">
														<strong>查询</strong>
													</button>
												    <button id="reset" class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
													style="margin-right: 20px;" type="reset">
													<strong>重置</strong>
												   </button>
												   	<button id="add" class="btn btn-success" style="margin-right: 5px;margin-top: 5px"
														type="button">
														<strong>添加VIP</strong>
													</button>
											</div>
										</div>			
									</form>
								</div>
							</div>
						</div>
					</div>
			 </div>
		</div>
	</div>
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
$('#leftMenuUser').addClass('active');
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

$('#add').click(function() {
	window.location.href=contextPath+"/admin/vipAddDrand?${_csrf.parameterName}=${_csrf.token}"
});

$('#search').click(function() {
	userInfoListData();
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
							'sAjaxSource' : contextPath + '/admin/receiveGetVip',
							"fnServerParams" : function(aoData) { // 查询条件
								aoData.push({
												"name" : "userName",
												"value" : $('[name=userName]').val()
											},{
												"name" : "registerTimeFrom",
												"value" : $('[name=registerTimeFrom]').val()
											},{
												"name" : "registerTimeTo",
												"value" : $('[name=registerTimeTo]').val()
											});
							},aoColumns : [{
										'mData' : 'id',
										'sTitle' : 'VIPid',
										'visible' : false
									},{
										'sTitle' : 'VIP昵称',
										'mData' : 'nickname'
									},{
										'sTitle' : '用户账号',
										'mData' : 'userName'
									},{
										'sTitle' : '邮箱',
										'mData' : 'email'
									},{
										'sTitle' : '角色名称',
										'mData' : 'vipName'
									},{
										'sTitle' : 'VIP注册时间',
										'mData' : 'createTime',
										'mRender' : function(data) {
											return data;
										}
									},{
										'sTitle' : '操作',
										'mData' : 'id',
										'sWidth' : '5%',
										'mRender' : function(data, type, full) {
											//alert(full.role);
											var returnValue = '<button id="update" class="btn btn-default btn-sm" onclick="removeVip('+data+')"><i class="fa fa-pencil"></i>删除VIP</button>';
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
function removeVip(data){	
	    swal({
	        title: "确定要删除该条记录?",
	        type: "warning",
	        showCancelButton: true,
	        confirmButtonColor: "#DD6B55",
	        confirmButtonText: "是",
	        cancelButtonText: "否",
	        closeOnConfirm: false,
	        closeOnCancel: true },
	    function (isConfirm) {
	        if (isConfirm) {
	        	var delVo={};
	        	delVo.id=data;
	        	$.ajax({
	                type: "POST",
	                url: "${pageContext.request.contextPath}/admin/removeVip",
	                data: JSON.stringify(delVo),
	                contentType:"application/json;charset=utf-8",
	                dataType: "json",
	                success: function(data){
	                	showcommon(data);
	                	userInfoListData();
	           		}
	            });
	        }
	    });
}
function showcommon(data){
	var error="";
	if(data.error=="-1"){
		error="删除失败";
	}else{
		error="删除成功";
	}
	var title="";
	if(data.error=="0"){
		title="成功";
	}else if(data.error=="-1"){
		title="失败";
	}
	swal({
        title: title,
        text:error,
        type: data.type
    });
}
</script>
</body>
</html>