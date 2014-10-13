<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
</head>
<script type="text/javascript">
		function showHost(log){
			var id = $("#id").val();
			var parent_id = $("#parent_id").val();
			var kpi_id = "";
			if(log == "error"){
				kpi_id = "jvm.JvmMetrics.LogError";
			}else{
				kpi_id = "jvm.JvmMetrics.LogFatal";
			}
			$.dialog({
				id:'showHost',
				title:'主机列表',
    			width: '1000px',
    			height: '500px',
    			min:true,
    			max:true,
    			lock:true,
    			content:'url:hadoopJvm_showHostList.do?obj.id='+id+'&obj.parent_id='+parent_id+'&obj.kpi_id='+kpi_id
			});
		}
</script>
<body style="overflow-y:auto;">
<s:hidden name="obj.id" id="id"></s:hidden>
<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
	<s:form action="" method="post" id="obj" cssStyle="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<tr style="height: 40px;">
						<td class="til" align="left" width="25%">
							Log中输出ERROR的主机:
						</td>
						<td>
							<a href="javascript:;" onclick="showHost('error')"><s:property value="errorCount"/> 个</a>
						</td>
	  				</tr>
					<tr style="height: 40px;">
						<td class="til" align="left">
							Log中输出FATAL的主机:
						</td>
						<td>
							<a href="javascript:;" onclick="showHost('fatal')"><s:property value="fatalCount"/> 个</a>
						</td>
					</tr>
			</table>
		</div>
	</s:form>
</body>