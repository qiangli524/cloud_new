<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<title></title>
<script type="text/javascript">
	
	//查询
	function searchRequest() {
		taskLogObj.action="treetask_taskReportList.do";
		taskLogObj.submit();
	}
	//重置
	function resetForm(){
		$("#exampleID").attr("value","");
		$("#exampleName").attr("value","");
		$("#IP").attr("value","");
	}
	//导出
	function exportExcel(){
		taskLogObj.action="treetask_exportExcel.do";
		taskLogObj.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody" >
<s:form action="" method="post" id="taskLogObj">
<s:hidden id="task_id" name="taskLogObj.task_id"></s:hidden>
<s:hidden id="order_id" name="taskLogObj.order_id"></s:hidden>
	<div class="pd-20 bgcolor-1">
		<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">实例编号：</label>
				<s:textfield name="taskLogObj.exampleID" id="exampleID" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">实例名称：</label>
				<s:textfield name="taskLogObj.exampleName" id="exampleName" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">IP地址：</label>
				<s:textfield name="taskLogObj.IP" id="IP"cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="exportExcel()">下载</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th width="40px" onclick="sort(theTable,1,'string')">
							订单编号
						</th>
						<th width="40px" onclick="sort(theTable,2,'string')">
							任务编号
						</th>
						<th width="40px" onclick="sort(theTable,3,'string')">
							实例编号
						</th>
						<th width="40px" onclick="sort(theTable,4,'string')">
							IP地址
						</th>
						<th width="40px" onclick="sort(theTable,5,'string')">
							实例名称
						</th>
						<th width="40px" onclick="sort(theTable,6,'string')">
							是否成功
						</th>
						<th width="40px" onclick="sort(theTable,7,'string')">
							是否完成
						</th>
						<th width="200px" onclick="sort(theTable,8,'string')">
							描述
						</th>
						<th width="100px" onclick="sort(theTable,9,'date')">
							插入时间
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="logList" id="theBean">
	                  <tr>
						<td><s:property value="#theBean.order_id"/></td>
						<td><s:property value="#theBean.task_id"/></td>
						<td><s:property value="#theBean.exampleID"/></td>
						<td><s:property value="#theBean.IP"/></td>
						<td><s:property value="#theBean.exampleName"/></td>
						<td>
							<s:if test="#theBean.isSuccess== 1">
								成功
							</s:if>
							<s:elseif test="#theBean.isSuccess==0 && #theBean.isComplete==1">
								失败
							</s:elseif>
							<s:else>
								-
							</s:else>
						</td>
						<td>
							<s:if test="#theBean.isSuccess== 1 && #theBean.isComplete==1">
								完成
							</s:if>
							<s:elseif test="#theBean.isSuccess==0 && #theBean.isComplete==0 ">
								未完成
							</s:elseif>
							<s:else>-</s:else>
						</td>
						<td align="left">
						    <s:property value="#theBean.example_log" default="-"/>
						</td>
						<td>
						    <s:property value="#theBean.insertTime" default="-"/>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		<div class="pages mgb-10"><!-- 分页 -->
			<jsp:include page="../../../sxcloud/inc/Pagination.jsp?formId=taskLogObj" />
		</div>
		        	</div>		
		
</div>
</s:form>
</body>
