<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%--<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>--%>
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
	
	$(function() {
		
	});
</script>
</head>
<body>
<s:form action="treetask_listTask" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap nobtask" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th width="40px" onclick="sort(theTable,1,'string')">
							实例编号
						</th>
						<th width="40px" onclick="sort(theTable,2,'string')">
							是否成功
						</th>
						<th width="40px" onclick="sort(theTable,3,'string')">
							是否完成
						</th>
						<th width="400px" onclick="sort(theTable,4,'string')">
							日志描述
						</th>
						<th width="100px" onclick="sort(theTable,5,'date')">
							插入时间
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="logList" id="theBean">
	                  <tr>
						<td>
							<s:property value="#theBean.example_id"/>						
						</td>
						<td>
							<s:if test="#theBean.isSuccess== 1 && #theBean.isComplete==1">
								成功
							</s:if>
							<s:elseif test="#theBean.isSuccess==0 && #theBean.isComplete==0">
								失败
							</s:elseif>
							<s:else>-</s:else>
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
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
