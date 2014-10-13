<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	$(function(){
		$("[name='viewhis']").unbind().live("click",function(){
			var $td = $(this).parent();
			var kpiId = $td.attr("kpiId");
			var uuid = $("#uuid").val();
			$.dialog({
				id:'viewhistory',
				title:'查看历史值',
				width:'700px',
				height:'550px',
				lock:true,
				content:'url:hadoopmonitor_goHisPage.do?obj.id='+uuid+'&obj.kpiId='+kpiId
			});
		});
	});
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="obj.id" id="uuid"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<s:iterator value="resultList" id="theBean">
					<tr>
						<td class="til" align="left">
							<s:property value="#theBean.description"/>
						</td>
						<td kpiId='<s:property value="#theBean.kpi_id"/>' >
							<a href="javascript:;" name="viewhis">
								<s:property value="#theBean.kpi_value"/>
							</a>
							<s:property value="#theBean.unit"/>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
	</s:form>
</body>