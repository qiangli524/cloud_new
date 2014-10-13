<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/view.jsp" %>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<title></title>
	<style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
	</style>

<script type="text/javascript">
		var task_ids = '<%=request.getAttribute("task_ids")%>';
		var api = frameElement.api;
		w = api.wer;
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectTask,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	function selectTask(){
		var task_ids = '';
		$(":checkbox:checked").each(function(){
       			task_ids +=$(this).attr("taskid")+","; 
        	 });
        api.get("add").addTask(task_ids);
	}
	
	//把已经选中的task勾选上
	$(function(){
    	   if(task_ids!=null&&task_ids!=""){
    	        var array=task_ids.split(",");
    	        for(var i=0;i<array.length;i++){
    	            $("[taskid='"+array[i]+"']").attr("checked",true);
    	        }
    	    }
       });
</script>
</head>
<body>
<s:form action="" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap nobtask">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th>
							选择
						</th>
						<th onclick="sort(theTable,1,'string')">
							任务编号
						</th>
						<th onclick="sort(theTable,2,'string')">
							任务状态
						</th>
						<th onclick="sort(theTable,3,'string')">
							任务类型
						</th>
						<th onclick="sort(theTable,4,'string')">
							任务报告
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
	                  <tr>
					    <td>
							<input type="checkbox" name="checkboxid" taskid='<s:property value="#theBean.ID"/>'/>
						</td>
						<td>
							<s:property value="#theBean.TASK_ID" default="-"/>
						</td>
						<td>
<%--	                    0草稿，1待处理，2处理中，3处理成功，4处理失败					--%>
						    <s:if test="#theBean.STATUS==1">待处理</s:if>
						    <s:elseif test="#theBean.STATUS==2">处理中</s:elseif>
						    <s:elseif test="#theBean.STATUS==0">草稿</s:elseif>
						    <s:elseif test="#theBean.STATUS==3">处理成功</s:elseif>
						    <s:elseif test="#theBean.STATUS==4">处理失败</s:elseif>
						</td>
						<td>
<%--						0部署任务，1升级任务，2启动任务，3停止任务，4重启任务，5、恢复任务，6卸载任务，7捕获任务--%>
						    <s:if test="#theBean.STATUS==1">升级任务</s:if>
						    <s:elseif test="#theBean.STATUS==2">启动任务</s:elseif>
						    <s:elseif test="#theBean.STATUS==0">部署任务</s:elseif>
						    <s:elseif test="#theBean.STATUS==3">停止任务</s:elseif>
						    <s:elseif test="#theBean.STATUS==4">重启任务</s:elseif>
						    <s:elseif test="#theBean.STATUS==5">恢复任务</s:elseif>
						    <s:elseif test="#theBean.STATUS==6">卸载任务</s:elseif>
						    <s:elseif test="#theBean.STATUS==7">捕获任务</s:elseif>
						</td>
						<td>
						    <a href="javascript:;" style="color: blue;" id="look">查看</a>&nbsp;<a href="javascript:;" style="color: blue;" id="down">下载</a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
