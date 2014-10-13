<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%> 
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
    var w = api.opener;

    api.button({
		id:'OkAnd',
		name: '确定',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	});
    function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(){ 
		var name= document.getElementById("POOL_NAME").value;
	    if(name.length ==0){
	     	alert("主机池名称不能为空！");
	     	return false  ;
	    }
	    var type= document.getElementById("POOL_TYPE").value;
	    if(type.length ==0){
	     	alert("主机池类型不能为空！");
	     	return false;
	    }
	    var url = "resourcepool_saveHostPool.do?"+$("#theForm").serialize();
		w.saveHostPool(url);
	}
	

</script>
</head>
<body onLoad="self.focus();document.theForm.POOL_NAME.focus()" class="pop-body scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						主机池名称 <font color="red">*</font>
					</td>
					<td>
					<s:textfield name="theForm.NAME" id="POOL_NAME" cssClass="txt" ></s:textfield>       
					</td> 
				</tr>
				<tr>
				     <td class="til">
						主机池类型 <font color="red">*</font>
					</td>
					<td>
					    <s:if test="theForm.flag==0">
						    <s:select list="#{'':'-请选择-','1':'物理池','2':'业务池'}"
							  name="theForm.POOL_TYPE" id="POOL_TYPE"></s:select>
					    </s:if>
						<s:else>
							<s:select list="#{'':'-请选择-','1':'物理池','2':'业务池'}"
							  name="theForm.POOL_TYPE" id="POOL_TYPE" readonly="true" disabled="true"></s:select>
						</s:else>
					</td>
				</tr>
				<tr>
				    <td class="til">
						主机池描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.POOL_DESC" id="DESC" cssClass="txt"  style="width:300px;height:100px"></s:textarea>
					</td>
				</tr>
			</table>
	</s:form>
</body>