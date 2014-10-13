<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.SELECT_APP_ID.value = 0;
		
		theForm.SELECT_BIND_DEVICE.value =0;
		theForm.ISUSE.value = '';
		theForm.MAX_BANDWIDTH.value = '';
		theForm.TELECOM_OPERATOR.value = '';
		theForm.MIDDLEWARE.value = '';
		theForm.PORT_TYPE.value = '';
		theForm.IP_TYPE.value = '';
		theForm.ISIPOPEN.value = '';
		theForm.PORT_MAPPING.value = '';
		theForm.LOAD_VIRTUAL_IP.value = '';
		theForm.ISLOADBALANCE.value = '';
	}

 	function checkSelect(){
		theForm.SELECT_APP_ID.value;
		theForm.SELECT_BIND_DEVICE.value;
	}
	function submitRequest(theForm){  
	    theForm.submit();
	}
</script>
</head>
<body>
<s:form action="yiip_saveIp.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
	
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
             <tr>
                    <td class="til">业务应用ID:</td>
                    <td>
						<s:select list="theForm.appIdList" listKey="ID" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_APP_ID" id="SELECT_APP_ID"></s:select>
                    </td>
                    </tr>
                    <tr>
                    <td class="til">绑定设备主机ID:</td>
                    <td>
						<s:select list="theForm.bindDeviceList" listKey="ID" listValue="NAME_ZH" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_BIND_DEVICE" id="SELECT_BIND_DEVICE"></s:select>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">是否使用:</td>
                    <td>
                    	<s:select list="theForm.isUseList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_ISUSE" id="SELECT_ISUSE"></s:select>
					</td>
					</tr>
					<tr>
					 <td class="til">分配最大带宽:</td>
					 <td>
						<s:textfield name="theForm.MAX_BANDWIDTH" cssClass="txt" id="MAX_BANDWIDTH"></s:textfield>
					</td>
					</tr>
					<tr>
					<td class="til">运营商:</td>
					 <td>
                    	<s:select list="theForm.operatorList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_TELECOM_OPERATOR" id="SELECT_TELECOM_OPERATOR"></s:select>
					</td>
					</tr>
					<tr>
                    	<td class="til">中间件:</td>
					 <td>
						<s:textfield name="theForm.MIDDLEWARE" cssClass="txt" id="MIDDLEWARE"></s:textfield>
					</td>
					</tr>
					<tr>
					<td class="til">端口类型:</td>
					 <td>
                    	<s:select list="theForm.portTypeList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_PORT_TYPE" id="SELECT_PORT_TYPE"></s:select>
					</td>
					</tr>
					<tr>
					<td class="til">公网IP类型:</td>
					 <td>
                    	<s:select list="theForm.ipTypeList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_IP_TYPE" id="SELECT_IP_TYPE"></s:select>
					</td>
                  </tr>
                  <tr>
                  <td class="til">公网是否开放:</td>
					 <td>
                    	<s:select list="theForm.isOpenList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_ISIPOPEN" id="SELECT_ISIPOPEN"></s:select>
					</td>
				  </tr>
					<tr>
					<td class="til">公网映射端口:</td>
					 <td>
						<s:textfield name="theForm.PORT_MAPPING" cssClass="txt" id="PORT_MAPPING"></s:textfield>
					</td>
					</tr>
					<tr>
					<td class="til">负载虚拟IP:</td>
					 <td>
						<s:textfield name="theForm.LOAD_VIRTUAL_IP" cssClass="txt" id="LOAD_VIRTUAL_IP"></s:textfield>
					</td>
					</tr>
					<tr>
					<td class="til">是否负载均衡:</td>
					 <td>
                    	<s:select list="theForm.isBalanceList" listKey="NAME" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_ISLOADBALANCE" id="SELECT_ISLOADBALANCE"></s:select>
					</td>
                  </tr>
             <tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
				 			onclick="window.history.back()" />
							
					</td>
				</tr>
			</table>
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
