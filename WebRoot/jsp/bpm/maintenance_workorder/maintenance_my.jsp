<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workflow.constant.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/fenye4bpm.js"></script>
	
	<style type="text/css">
	</style>
</head>
  <script type="text/javascript">	
	var statusType =  "${attr.statusType}";
	//alert(statusType);
 	 function searchRequest(page){
		$('#contentTr').html("");
		var queryType = qtype.wait;
		if(statusType=='1'){
			queryType = qtype.join;
		}

		var size = $("#PGSZIE").val();
		if(size == null || size == ""){
			size = 10;//默认每页显示十条记录
		}
		var search = {
				"obj.queryType":queryType,
				"obj.resourceName":"maintenance_workorder",
				"obj.orderNo":$('#search_orderNo').val(),
				"obj.startDate":$('#search_starttime').val(),
				"obj.endDate":$('#search_endtime').val(),
				"obj.formData":$('#search_formData').val(),
				"PAGINATION_CURRENT_PAGE_NO":page, //分页用
				"PGSZIE":size,                     //分页用
				"PAGINATOR_FLAG":"true"		       //分页用
		}
 		$.ajax({
    		type : 'post',
    		async : false,
    		url : '<%=path%>/bpm/workorder_getWorkFlowList.action',
   			data:search,
   			dataType: 'json',
   			success : function(data){
   				if(data.length!=0){   					
	   				$(data.list).each(function(i,e){
	   					var html = '';
	   					html = '<tr>'+
	   								'<td >'+e.entryId+'</td>'+
	   								'<td >'+e.dataMap.type+'</td>'+
	   								'<td ><a href="#"></a>'+e.dataMap.content+'</td>'+
	   								'<td >'+orderStatus[e.resourceName+e.stepId]+'</td>'+
	   								'<td >'+e.submitTime+'</td>'+
	   								'<td >'+e.orderLevel+'</td>'+
	   							    '<td style="color:blue" ><a href="<%=path%>'+maintenanceUrl[e.resourceName+e.stepId]+'?obj.entryId='+e.entryId+'">查看</a></td>'+
	   							    '</tr>';
	   					$('#contentTr').append(html);
	   				});	
	   				//添加分页
	   				ajaxFenye(data.page);
   				}
   			},
   			error : function(data,textStatus){
   				console.log('error:' + data);
   			}
   		});			
	 } 
 	 
 	//分页fucntion开始 
  	//pageSizeChange
  	function changePageSize(size){
  		document.getElementById("PGSZIE").value = size;
  		if( null != size && ""!= size) {
  			searchRequest(1);
  		}
  	}
  	 
  	//跳到指定页面
  	function gotoPage(page){
  		if( null != page && ""!= page) {
  			searchRequest(page);
  		}
  	}
  	//分页fucntion结束

 	$(document).ready(function(){
		$('#statusType'+statusType).addClass("ui-state-default ui-corner-top ui-tabs-selected ui-state-active");
		searchRequest();
		setTimeout("searchRequest()",1000*60*3);//没三分钟刷新一次
 	})
  </script>
  <body>
<body>
  <div class="mainbody">
<s:form action="function_listFunctions" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"/>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">运维工单</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
			  <div class="clearfix filtrate-area">
				 <form id="search" method="get" action="">
                        <input type="hidden" name="statusType" value="0">
                        <label class="vm">工单编号：</label>
                        <input class="inpt-1 vm" id="search_orderNo" name="obj.orderNo" type="text" size="8" value="">

                        <label class="vm">关键字：</label>
                        <input class="inpt-1 vm" id="search_formData" name="obj.formData" type="text" size="15" value="">

                        <label class="vm">时间范围：</label>
						<input id="search_starttime" style="txt" type="text" name="obj.startDate" size="22" class="Wdate"
                                       onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
				   		~
						<input id="search_endtime" style="txt" type="text" name="obj.endDate" size="22" class="Wdate"
                               onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                        <span class="ubtn-1 mgl-20">
                        	<input type="button" value="查询" id="searchForm" onclick="javascript:searchRequest()" /> 
						</span>                       
                    </form>
               </div>
			</div>
			
			<div class="workorder">
				<div id="tabs" class="ui-tabs ui-widget ui-widget-content">
					<ul id="ul_tabs" class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header">
						 <li>
	                         <a id="statusType0" class="ui-state-default ui-corner-top" href="maintenanceWorkorder_maintenance.do?statusType=0">待处理工单</a>
	                     </li>
	                     <li>
	                         <a id="statusType1" class="ui-state-default ui-corner-top" href="maintenanceWorkorder_maintenance.do?statusType=1">已处理工单</a>
	                     </li>
					</ul>		
			</div>
		 </div>
		 <table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
		  <thead>
		  <tr>
                  <th onclick="sort(theTable,1,'string')">工单编号</th>
                  <th onclick="sort(theTable,2,'string')">问题类型</th>
                  <th onclick="sort(theTable,3,'string')">解决建议</th>
                  <th onclick="sort(theTable,4,'string')">状态</th>
                  <th onclick="sort(theTable,5,'string')">提交时间</th>
                  <th onclick="sort(theTable,6,'string')">客户级别</th>
                  <th onclick="sort(theTable,7,'string')">操作</th>
		  </tr>
		  </thead>
		  <tbody id="contentTr"></tbody>
		</table> 
		 <!-- ajax分页start -->
		  <div class="pages" id="ajaxpage"></div>
		 <!-- ajax分页end -->	
		 
</s:form>
</div>
</html>