<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客服 我的工单</title>
    
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/fenye4bpm.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workflow.constant.js"></script>
<style type="text/css">
#ray_line {border-top:1px dashed #cccccc;height: 1px;overflow:hidden;}
</style>
  </head>
  <script type="text/javascript">
	 var statusType =  "${attr.statusType}"; 
 	 function searchRequest(page){
		$('#contentTr').html("");
		var queryType = qtype.wait;
		if(statusType==1){
			queryType = qtype.join;
		}
		
		var size = $("#PGSZIE").val();
		if(size == null || size == ""){
			size = 10;//默认每页显示十条记录
		}
		var search = {
				"obj.queryType":queryType,
				"obj.resourceName":"workorder",
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
    		url : '<%=request.getContextPath()%>/bpm/workorder_getWorkFlowList.action',
   			data:search,
   			dataType: 'json',
   			success : function(data){
   				if(data.length!=0){   							
	   				$(data.list).each(function(i,e){
	   					var html = '<tr>'+
	   								'<td class="tacenter"><input type="checkbox" name="entryId" value="'+e.entryId+'">'+e.entryId+'</td>'+
	   								'<td class="tacenter"><a href="#"></a>'+e.dataMap.content+'</td>'+
	   								'<td class="tacenter">'+e.dataMap.type+'</td>'+
	   								'<td class="tacenter">'+orderStatus[e.resourceName+e.stepId]+'</td>'+
	   								'<td class="tacenter">'+e.submitTime+'</td>'+
	   								'<td class="tacenter">'+e.orderLevel+'</td>'+
	   							    '<td style="color:blue" class="tacenter"><a href="<%=path%>'+serviceUrl[e.resourceName+e.stepId]+'?obj.entryId='+e.entryId+'">查看</a></td>'+
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
 	function addWorkOrder(){
 	    window.location.href="<%=path%>/bpm/workorder_edit.action?obj.resourceName=${obj.resourceName}&position=remy&obj.account=${obj.account}";
 	}
 	function schedule(){
 		var inputObj = $('input:radio[name="entryId"]:checked');		
 	    console.log(inputObj);
 		if(inputObj.length!=0){
 			$(inputObj).each(function(i,e){
 			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=request.getContextPath()%>/bpm/workorder_schedule.action',
       			data:{"obj.entryId":$(e).val(),"obj.resourceName":inputObj.attr("resourceName")},
       			dataType: 'json',
       			success : function(data){
       				window.open(data.url);			
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});			
       		});
 		}else{
 			alert("请选择一条");
 		}
 	} 
 	function history(){
 		var inputObj = $('input:radio[name="entryId"]:checked');
 		if(inputObj.length!=0){
 			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=request.getContextPath()%>/bpm/workorder_history.action',
       			data:{"obj.entryId":inputObj.val()},
       			dataType: 'json',
       			success : function(data){
       				var html ='';
       				$(data).each(function(i,e){
       					if(e.finishDate.length!=0){
       						if(i==0){
       							html+=e.finishDate+' '+e.executor+'新建了故障单'+'\n';
       						}else{
       							if(e.proxyUser.length==0){
       								html+=e.finishDate+' '+e.executor+'审核了'+e.stepName+'\n';
       							}else{
       								html+=e.finishDate+' '+e.executor+'转发给了'+e.proxyUser+'\n';       							
       							}
       						}
       					}       					
       				});
       				alert(html);
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});			
 		}else{
 			alert("请选择一条");
 		}
 	}

 	$(document).ready(function(){
 		renderUser();
 		$('#statusType'+statusType).addClass("ui-state-default ui-corner-top ui-tabs-selected ui-state-active");
		searchRequest(1);
		
		$('.configType').click(function(){
				var type = $('input:radio[name="config.exeType"]:checked').val();
				if(type==0){
					$("#select_user").show();
					$(".selectGroup").show();
				}else if(type==1){
					$("#select_user").hide();
					$(".selectGroup").show();
				}
			});	
		//根据组改变事件
		$('#groupName').change(function(){
			renderUser();
		});
 	});
 	
 	//加载人员
 	function renderUser(){
			$('#userName').html("");
			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=request.getContextPath()%>/bpm/config_getUsersList.do',
       			data:{"groupId":$('#groupName').val()},
       			dataType: 'json',
       			success : function(data){
       				$(data).each(function(i,e){
       					$('#userName').append('<option value="'+e.account+'">'+e.userName+'</option>');
       				});
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});	
		}
 	
/*
 * 转发
 */
function forword(){
	var inputObj = $('input:checkbox[name="entryId"]:checked');
	if(inputObj.length!=0){
		$(inputObj).each(function(i,e){
			var param = {};
			param['proxy.entryId']=$(e).val();
			param['obj.entryId']=$(e).val();
			param['step.objData.Custom_isOver']="0";
			
			var type = $('input:radio[name="config.exeType"]:checked').val();
			if(type==0){
				param['proxy.userName'] = $('#userName').val();
			}else if(type==1){
				param['proxy.groupName']= $('#groupName').val();
			}
			
			param['proxy.callType']= 0;
			param['obj.account']= '${obj.account}';
			$.ajax({
	        		type : 'post',
	        		async : false,
	        		url : '<%=request.getContextPath()%>/bpm/workorder_ajaxSave.action',
	       			data: param,
	       			dataType: 'json',
	       			success : function(data){
	       				if(data.result=='success'){
	       					alert("派发成功");
	       				}
	       			},
	       			error : function(data,textStatus){
	       				console.log('error:' + data);
	       			}
	       	});				
		});
		
	}else{
		alert("请选择一工单");
	}
}
  </script>
  <body>
<div class="mainbody">
<s:hidden name="theForm.ID" id="ID"/>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">我的工单</h2>
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
	                         <a id="statusType0" class="ui-state-default ui-corner-top" href="workorder_service_my.do?statusType=0">待处理工单</a>
	                     </li>
	                     <li>
	                         <a id="statusType1" class="ui-state-default ui-corner-top" href="workorder_service_my.do?statusType=1">已处理工单</a>
	                     </li>
					</ul>		
			</div>
		 </div>
<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
		  <thead>
		  <tr>
                  <th onclick="sort(theTable,1,'string')">工单编号</th>
                  <th onclick="sort(theTable,2,'string')">问题内容</th>
                  <th onclick="sort(theTable,3,'string')">问题类型</th>
                  <th onclick="sort(theTable,4,'string')">状态</th>
                  <th onclick="sort(theTable,5,'string')">提交时间</th>
                  <th onclick="sort(theTable,6,'string')">客户级别</th>
                  <th onclick="sort(theTable,7,'string')">操作</th>
		  </tr>
		  </thead>
		  <tbody id="contentTr"></tbody>
		</table>
<s:if test="%{statusType==0}">
<!-- paifa start -->
<div class="tc mgt-20 mgb-20">
		<form id="search1" method="get" action="">
			<input type="hidden" name="statusType" value="0"> 
			<table width="80%">
				<tr>
					<td class="tacenter">
					<label class="vm">派发类型：</label> 
					<span >
					<input type="radio" class="configType" name="config.exeType"   value="1" />执行组</span>
					<span >
					<input type="radio" class="configType" name="config.exeType" checked value="0" />执行人</span> 
					</td>
					<td class="selectGroup selectUser tacenter">
						<label class="vm mgl=20">选择执组：</label> 
						<select id="groupName" name="config.groupName" class="select-1">
							<s:iterator value="groupList" id="group">
								<option value="${group.groupId }">${group.groupName}</option>
							</s:iterator>
						</seletc>
					</td>
		
					<td class="selectUser tacenter" id="select_user">
						<label class="vm">选择执行人：</label> 
						<select id="userName" name="config.userName" class="select-1">
						</seletc>
					</td> 
					<td class="tacenter">
						<span class="ubtn-1 mgl-20">
		                      	<input type="button" value="派发" onclick="javascript:forword()"> </input>
						</span>
					</td>
				</tr>
			</table>
		</form>
</div>
<!-- paifa end -->
				  <div id="ray_line"></div>
</s:if>			 
				  <!-- ajax分页start -->
				  <div class="pages" id="ajaxpage"></div>
				  <!-- ajax分页end -->
          </div>
      </div>
  </body>
</html>
