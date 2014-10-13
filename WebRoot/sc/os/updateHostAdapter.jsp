<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var api = frameElement.api;
		w = api.opener;
		
		$("#updateButton").click(function() {
			var _ip_address = $("#ip_address").val();
			if (_ip_address == null|| _ip_address == '') {
				alert('IP地址是必填项');
				$("#ip_address").focus();
				return false;
			}else if(!isIP(_ip_address)){
				alert('IP地址格式错误');
				$("#ip_address").focus();
				return false;
			}
			
			var _subnet_mask = $("#subnet_mask").val();
			if (_subnet_mask == null|| _subnet_mask == '') {
				alert('子网掩码是必填项');
				$("#subnet_mask").focus();
				return false;
			}else if(!isIP(_subnet_mask)){
				alert('子网掩码格式错误');
				$("#subnet_mask").focus();
				return false;
			}
			
			var _default_gateway = $("#default_gateway").val();
			if (_default_gateway == null|| _default_gateway == '') {
				alert('默认网关是必填项');
				$("#default_gateway").focus();
				return false;
			}else if(!isIP(_default_gateway)){
				alert('默认网管格式错误');
				$("#default_gateway").focus();
				return false;
			}
			w._update_button_click_event($("#theForm").serialize());
		});
		
		$("#clearButton").click(function(){
			$("#ip_address").val("");
			$("#subnet_mask").val("");
			$("#default_gateway").val("");
			w._update_button_click_event($("#theForm").serialize());
		});
		
		$("#resetButton").click(function (){
			//根据当前业务，nic_order和mac，不允许修改。
			//资源入库的时候确认。 Marked By JamTau
			$("#ip_address").val("");
			$("#subnet_mask").val(""); 
			$("#default_gateway").val("");  
		});	
	});
	
	
	function isIP(strIP) { 
		if (isNull(strIP)) return false; 
		var re= /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g;
		if(re.test(strIP)){ 
			if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)
				return true; 
		} 
		return false; 
	}   
	function isNull(str){
		return str == null || str == '';
	}
			
	function resetForm(theForm){
		theForm.ip_address.value = "";
		theForm.subnet_mask.value = "";
		theForm.default_gateway.value = "";
		theForm.purpose.value = "";
	}
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">修改网络配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="hostAdapter_modifyHostAdapter.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id"></s:hidden>
				<s:hidden name="theForm.os_host_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								网卡 <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.nic_order" id="nic_order" readonly="true" maxlength="30" cssClass="inpt-2"/>            
							</td>
							<td class="til">
								MAC<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.mac" id="mac" readonly="true" maxlength="30" cssClass="inpt-2"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								IP <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.ip_address" id="ip_address" maxlength="30" cssClass="inpt-2"/>            
							</td>
							<td class="til">
								子网掩码<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.subnet_mask" id="subnet_mask" maxlength="30" cssClass="inpt-2"/>
							</td>
						</tr>						
						<tr>
							<td class="til">
								默认网关<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.default_gateway" id="default_gateway" maxlength="30" cssClass="inpt-2"/>
							</td>
							<td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.purpose" id="purpose" maxlength="30" cssClass="textarea-1"/>
							</td>
						</tr>
						<tr>
							<td colspan="6" class="btnCenter">
								<span class="ubtn-green mgl-20"><input type="button" id="updateButton" value="确定" /></span>
								<span class="ubtn-green mgl-20"><input type="button" id="clearButton" value="清空" /></span>
								<span class="ubtn-orange mgl-20"><input type="button" id="resetButton" value="重置" /></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
