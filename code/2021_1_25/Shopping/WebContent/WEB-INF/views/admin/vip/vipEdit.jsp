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
							'sAjaxSource' : contextPath + '/admin/userInfoDataTable',
							"fnServerParams" : function(aoData) { // 查询条件
								aoData.push({
												"name" : "userName",
												"value" : $('[name=userName]').val()
											},{
												"name" : "enabled",
												"value" : $('[name=enabled]').val()
											},{
												"name" : "role",
												"value" : $('[name=role]').val()
											},{
												"name" : "registerTimeFrom",
												"value" : $('[name=registerTimeFrom]').val()
											},{
												"name" : "registerTimeTo",
												"value" : $('[name=registerTimeTo]').val()
											},{
												"name" : "lastLoginTimeFrom",
												"value" : $('[name=lastLoginTimeFrom]:checked').val()
											},{
												"name" : "lastLoginTimeTo",
												"value" : $('[name=lastLoginTimeTo]').val()
											});
							},aoColumns : [{
										'mData' : 'id',
										'sTitle' : '会员编号',
										'visible' : false
									},{
										'sTitle' : '会员名',
										'mData' : 'userName'
									},{
										'sTitle' : '昵称',
										'mData' : 'nickname'
									},{
										'sTitle' : '邮箱',
										'mData' : 'email'
									},{
										'sTitle' : '会员状态',
										'mData' : 'enabled',
										'mRender' : function(data, type, full) {
											var returnValue = "";
											if (data == "1") {
												returnValue = "激活会员";
											} else {
												returnValue = "冻结会员";
											}
											return returnValue;
										}
									},{
										'sTitle' : '角色',
										'mData' : 'role',
										'mRender' : function(data, type, full) {
											var returnValue = "";
											if (data == "ROLE_USER") {
												returnValue = "普通会员";
											} else {
												returnValue = "管理员";
											}
											return returnValue;
										}
									},{
										'sTitle' : '最后登陆时间',
										'mData' : 'lastLoginTime',
										'mRender' : function(data, type, full) {
											return data;
										}
									},{
										'sTitle' : '注册时间',
										'mData' : 'registerTime',
										'mRender' : function(data) {
											return data;
										}
									},{
										'sTitle' : '操作',
										'mData' : 'id',
										'sWidth' : '5%',
										'mRender' : function(data, type, full) {
											//alert(full.role);
											var returnValue = '<button id="update" class="btn btn-default btn-sm" onclick="editUser('+data+',\''+full.role+'\')"><i class="fa fa-pencil"></i>添加VIP</button>';
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
function editUser(userId,roleStr){
	var addVo={};
	addVo.userId=userId;
	if(roleStr=="ROLE_ADMIN"){
		alert("管理员用户无法添加VIP")
	}else{
		$.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/admin/addVip",
            data: JSON.stringify(addVo),
            contentType:"application/json;charset=utf-8",
            dataType: "json",
            success: function(data){
            	if(data.errorCode==0){
            		swal({
                        title: "成功",
                        text:data.message
                    });
            	}else{
            		swal({
                        title: "失败",
                        text:data.message
                    });
            	}
            	userInfoListData();
       		}
        });
		console.log(userId,roleStr)
	}
}

</script>
</body>
</html>