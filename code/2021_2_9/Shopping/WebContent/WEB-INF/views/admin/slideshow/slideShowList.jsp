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
	<div class="small-header transition">
		<div class="content">
		    <div class="hpanel">
		        <div class="panel-body">
					<div class="row">
							<div class="hpanel">
								<div id="error_div" class="text-left"></div>
									<form id="form_productCategory" name="form_productCategory" action="" method="POST">
										<div class="form-group col-lg-6">
											<label>幻灯片</label>
												<input type="text" id="name" name="name" class="form-control" maxlength="50" placeholder="幻灯片"/>	
										</div>
										<div class="form-group col-lg-6">
											<label>描述</label>
											<input type="text" id="description" name="description" class="form-control" maxlength="50" placeholder="描述"/>	
										</div>
										<div class="text-right m-t-xs">
											<button id="search" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" type="button">
												<strong>查询</strong>
											</button>
											<button id="reset" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" style="margin-right: 20px;" type="reset">
												<strong>重置</strong>
											</button>
											<button id="add" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" type="button">
												<strong>添加幻灯</strong>
											</button>
											<button id="delBtn" class="btn btn-success" style="margin-right: 5px;margin-top: 5px" type="button">
												<strong>删除幻灯</strong>
											</button>
										</div>
									</form>
								</div>
						</div>
					</div>
				</div>
				
			</div>
			
		</div>
		<div class="content">	
		<div class="row">
			<div class="col-lg-12">
				<div class="hpanel">
					<div class="panel-body">
						<table id="slideshowList" style="width: 100%;" class="table table-striped table-bordered table-hover"></table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	
<form id="forwordform" action='<c:url value="/admin/slideshow/edit?${_csrf.parameterName}=${_csrf.token}" />' method='POST'>
	<input type="hidden" name="id" id="id"/>
</form>
<script type="text/javascript">
$('#leftMenuProduct').addClass('active');
var oTable;
var header = $("meta[name='_csrf_header']").attr("content");  
var token = $("meta[name='_csrf']").attr("content"); 
var contextPath = '${pageContext.request.contextPath}';
$(function() {
	$('.date').datepicker();
	$(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
	slideshowListData();
});

$('#add').click(function() {
	window.location.href =contextPath+'/admin/slideshow/add';
});

$('#search').click(function() {
	productCategoryListData();
});

$('#delBtn').click(function() {
	var ids = document.getElementsByName("ids");
	var strIds = new Array();
	var idTemp = "";
	for(var i=0;i<ids.length;i++){
		if(ids[i].checked){
			strIds[strIds.length]=ids[i].value;
			idTemp += ids[i].value + "-";
		}
	}
	if(strIds.length == 0){
		swal({
            title: "提示",
            text: "请选择需要删除的商品分类！"
   	    });
		return false;
	}else{
		swal({
			title : "确定删除吗?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#62cb31",
			confirmButtonText : "删除",
			cancelButtonText : '取消',
		}, function() {
			validationProduct(idTemp);
		});
	}
});





function delshow(idTemp){
	swal({
		title : "确定删除吗?",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#62cb31",
		confirmButtonText : "删除",
		cancelButtonText : '取消',
	}, function() {
		delSlideShow(idTemp)
	});
}



function delSlideShow(id){
	$.ajax({
		type : "POST",
		contentType : "application/json;charset=utf-8", 
		url : contextPath + '/admin/slideshow/del',
		data :  JSON.stringify(id),
		dataType : "json", 
		async : false,
		success : function(json) {
			if(json.error == '0'){
				window.location.reload();
			}else{
				alert("商品分类删除失败！");
			}
		},
		error : function(json) {
			existFlag = true;
		}
	});
}

function checkedAll(){
	if($("#checkedAll").is(':checked')==true){
		//全选
		$("input[name='ids']:checkbox").prop('checked',true);
	}else{
		//取消全选
		$("input[name='ids']").removeAttr("checked");
	}
}

function slideshowListData() {
	
	if (typeof oTable == 'undefined') {
		// dataTable load
		oTable = $('#slideshowList')
				.dataTable(
						{
							'bPaginate' : true, // 翻页功能
							'sServerMethod' : 'POST', // 提交方式
							'bServerSide' : true,
							'bProcessing' : true,
							'bFilter' : false,
							"bSort" : false,
							'sAjaxSource' : contextPath + '/admin/slideShowinfoDataTable',
							"fnServerParams" : function(aoData) { // 查询条件
								aoData.push({
												"name" : "name",
												"value" : $('[name=name]').val().trim()
											},{
												"name" : "description",
												"value" : $('[name=description]').val().trim()
											});
							},aoColumns : [
								{
									'mData' : 'id',
									'sTitle' : '<input type="checkbox" id="checkedAll" name="checkedAll" onclick="checkedAll()">',
									'sWidth' : '2%',
									'mRender' : function(data){
				                        var sRendor = '<input type="checkbox" name="ids" value="'+ data +'">';
				                        return sRendor;
				                    }
								},
								{
									'sTitle':'幻灯片',
									'mData' : 'name'
								},
								{
									'sTitle':'描述',
									'mData' : 'introduction'
								},
								{
									'sTitle':'缩略图',
									'mData' : 'image',
									'sWidth' : '25%',
									'mRender' : function(data,
											type, full) {
										var returnValue = '<img src="/Shopping'+data+'" style="width:300px;height:150px">';
										return returnValue;
									}
								},
								{
									'sTitle':'更新者',
									'mData':'adder'
								},{
									'sTitle' : '操作',
									'mData' : 'id',
									'sWidth' : '9%',
									'mRender' : function(data) {
										var returnValue = '<button id="update" class="btn btn-default btn-sm" onclick="editSlideShow('+data+')"><i class="fa fa-pencil"></i>编辑</button>&nbsp&nbsp'
										+ '<button id="update" class="btn btn-default btn-sm" onclick="delshow('+data+')"><i class="fa fa-pencil"></i>删除</button>';
										return returnValue;
									}
								}
								] 

						});
	} else {
		// oTable.fnClearTable(0);
		var oSettings = oTable.fnSettings();
		oSettings._iDisplayLength = parseInt($(
				'[name=slideShowList_length] option:selected').val());
		$('.dataTables_length select').val(
				$('[name=slideShowList_length] option:selected').val());
		oSettings._iDisplayStart = 0;
		oTable.fnDraw();
	}
}

function editSlideShow(id){
	console.log(id);
	$("#id").val(id);
	$("#forwordform").submit();
}

</script>
</body>
</html>