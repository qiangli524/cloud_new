<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<style>
.path {
	width: 337px;
	height: 20px;
	vertical-align: middle;
	line-height: 20px;
	padding: 0px;
	border: none;
	border: 1px solid #9DBCD9;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
    //创建配置文件
	$(function(){
	   	 var oper =$("#oper").val();
		 var api = frameElement.api;
		 var w = api.opener;
			if("edit"==oper){	
			 api.button({
			     id:'OkAnd',
			     name: '修改',
			     callback:addprocess,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
			}else{
				 api.button({
				     id:'OkAnd',
				     name: '添加',
				     callback:addprocess,
				     focus: true
				 },
				 {
				     id:'cancle',
				     name: '取消'
				 });
			}
	
	    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
		}

		 function addprocess(){
			var kpiId=$("#kpiId").val();
		    if(kpiId==null||Trim(kpiId)==""){
		       alert("指标名称不能为空！");
		       return false  ;
		     }
			var kpiDesc=$("#kpiDesc").val();
		    if(kpiDesc==null||Trim(kpiDesc)==""){
		       alert("指标名称不能为空！");
		       return false  ;
			 }
		    w.saveHadoopKpi($("#theForm").serialize());
		 }
		 
	});
	
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
	<s:hidden name="oper" id="oper"></s:hidden>
	<s:hidden name="kpiObj.id" id="kpiUuid"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
				       监控指标:<font color="red">*</font>
				    </td>
				    <td colspan="5">
				   	 <s:textfield name="kpiObj.kpi_id" cssClass="txt" id="kpiId"></s:textfield>
				    </td>		
				</tr>
				<tr>
				    <td class="til">
				       指标描述:<font color="red">*</font>
				    </td>
				    <td colspan="5">
				   	 <s:textfield name="kpiObj.description" cssClass="txt" id="kpiDesc"></s:textfield>
				    </td>		
				</tr>
				<tr>
				    <td class="til">
				        指标单位:
				    </td>
				    <td>
				    <s:textfield name="kpiObj.unit" cssClass="txt" id="kpiUnit"></s:textfield>
				    </td>		
				</tr>
				<tr>
				    <td class="til">
				        告警阈值:
				    </td>
				    <td>
				    <s:textfield name="kpiObj.threshold" cssClass="txt" id="threshold"></s:textfield> <s:select list="#{'1':'百分比','2':'数值'}" name="kpiObj.threshold_type" ></s:select>
				    </td>		
				</tr>
				<tr align="left">
					<td class="til">指标图例</td>
					<td>
						<s:select list="#{'1':'线图','2':'面图'}" name="kpiObj.shape" id="shape" ></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">是否有效</td>
					<td>
						<s:select list="#{'1':'有效','2':'无效'}" name="kpiObj.isEffect" id="isEffect" ></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">级别</td>
					<td>
						<s:select list="#{'0':'高','1':'中','2':'低'}" name="kpiObj.level" id="level" ></s:select>
					</td>
				</tr> 
			</table>
	</s:form>
</body>

</html:html>
