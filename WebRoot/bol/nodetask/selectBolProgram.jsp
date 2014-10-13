<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common_bol.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework_bol.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	api.button({
	    id:'Ok',
	    name: '确定',
	    callback:selectProgram,
	    focus: true
	},
	{
	    id:'cancle',
	    name: '取消'
	});
	
	function selectProgram(){
		var programName = '';
		var couterNum = 0;
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids!=null&&checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1 ;
					programName += "~" + checkboxids[i].value;
				}
			}
		}
		programName = programName.substring(1);
		if(couterNum==0){
			alert("请勾选需要更新的应用程序！");
			return false ;
		}
		api.get("upgradeRequest").updateProgramName(programName);
	}
</script>
<style type="text/css">
div{height:220px;overflow:auto}
</style>
</head>
<body>
<s:form action="bolprogram_listBolProgram.do" id="theForm" method="post" cssClass="theForm">

	<div class="table-ct">
	<table width="100%" class="blue-table sorttable" border="0" id="theTable"
		cellspacing="0" >
		<thead>
			<tr>
				<th>选择</th>
				<th>应用标识</th>
				<th>应用程序名</th>
				<th>应用状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="programList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.name'/>"/>
					</td>
					<td><s:property value="#theBean.id" /></td>
					<td><s:property value="#theBean.name" /></td>
					<td>
						<s:if test="#theBean.status==1">
							正常
						</s:if>
						<s:else>
							<font color="red">异常</font>
						</s:else>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>

</s:form>
</body>
