<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function deployRequest(theForm){
	if(theForm.PROJECT_ID.value ==0){
	alert();
	    	alert("请选择项目！");
	    	thisForm.PROJECT_ID.focus;
	    	return false  ;
	    }
	    if(thisForm.VH_CPU.value <1 ){
	    alert("CPU个数不能小于一个，并且必须为整数值");
	    thisForm.VH_CPU.focus;
	    	return false  ;
	    }
	    if(theForm.VH_CPU.value >128){
	    alert("CPU个数不能大于128，并且必须为整数值");
	    thisForm.VH_CPU.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MEM.value < 256){
	    alert("内存不能小于128，并且必须为整数值");
	    thisForm.VH_MEM.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MEM.value > 123136){
	    alert("内存不能大于123136，并且必须为整数值");
	    thisForm.VH_MEM.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MIN_PROCESS_UNIT.value < 0.1){
	    alert("最小处理单元数不能小于0.1，可以是一位小数，例如：2.1");
	    thisForm.VH_MIN_PROCESS_UNIT.focus;
	    	return false  ;
	    }
	     if(theForm.VH_MIN_PROCESS_UNIT.value > 10){
	    alert("最小处理单元数不能大于10，可以是一位小数，例如：2.1");
	    thisForm.VH_MIN_PROCESS_UNIT.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MAX_PROCESS_UNIT.value <1){
	    alert("最大处理单元数不能小于1，可以是一位小数，例如：2.1");
	    thisForm.VH_MAX_PROCESS_UNIT.focus;
	    	return false  ;
	    }
	    if(theForm.VH_MAX_PROCESS_UNIT.value >128){
	    alert("最大处理单元数不能大于128，可以是一位小数，例如：2.1");
	    thisForm.VH_MAX_PROCESS_UNIT.focus;
	    	return false  ;
	    }
	    if(theForm.VH_PROCESS_UNIT.value <0.1){
	    alert("处理单元数不能小于0.1，可以是一位小数，例如：2.1");
	    thisForm.VH_PROCESS_UNIT.focus;
	    	return false  ;
	    }
	    if(theForm.VH_PROCESS_UNIT.value >12.8){
	    alert("处理单元数不能大于12.8，可以是一位小数，例如：2.1");
	    thisForm.VH_PROCESS_UNIT.focus;
	    	return false  ;
	    }
		theForm.action = 'advancedDeployImage.do'
		theForm.submit();
	}
	function saveRequest(thisForm){
		theForm.action = 'saveImageInfo.do'
		theForm.submit();
	}
	function cpuModelSelect(){
		var str = document.theForm.SETTINGS.value;
		if(str=='1'){
			document.getElementById('dedicatedModel').style.display="";
			document.getElementById('sharedModel1').style.display="none";
			document.getElementById('sharedModel2').style.display="none";
			document.getElementById('sharedModel3').style.display="none";
			document.getElementById('priority').style.display="none";
		}else
		if(str=='0'){
			document.getElementById('dedicatedModel').style.display="none";
			document.getElementById('sharedModel1').style.display="";
			document.getElementById('sharedModel2').style.display="";
			document.getElementById('sharedModel3').style.display="";
		}
	}
	function prioritySelect(){
		var str= document.theForm.MODEL.value;
		if(str=='0'){
			document.getElementById('priority').style.display="";
			
		}else
		if(str=='1'){
			document.getElementById('priority').style.display="none";
		}
	}
</script>
</head>
<body class="pop-body scrollbody" onload=cpuModelSelect()>
	<s:form action="image_advancedDeploy" method="post" id="theForm">
	<s:hidden name="theForm.workloadInfoId" id="workloadInfoId"></s:hidden>	
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				<td class="til">
						名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.IM_NAME" cssClass="txt" id="IM_NAME"></s:textfield>          
					</td>
					  <td class="til">
						项目 
					</td>
					<td>
						<s:select list="theForm.projectList" listKey="PROJECT_ID" listValue="NAME" headerKey="0" headerValue="--请选择--" name="theForm.PROJECT_ID"></s:select>
						
					</td>
				</tr>
				<tr>
				    <td class="til">
						配置描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.IM_DESC" id="IM_DESC" cols="50" rows="3"></s:textarea>
					</td>		
				</tr>
				<tr>
       		 	<td class="til">
						处理器设置 
					</td>
				<td>
					<s:select list="#{'0':'共享设置','1':'专用设置'}" name="theForm.SETTINGS" id="SETTINGS" onclick="cpuModelSelect();return false;"></s:select>
					
				</td>
				</tr>	
				<tr id=dedicatedModel style="display:">
					<td class="til">
						专用处理器设置 
					</td>
					<td>
				    	<s:textfield name="theForm.VH_MIN_CPU" id="VH_MIN_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.VH_CPU" id="VH_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.VH_MAX_CPU" id="VH_MAX_CPU" cssClass="txt"></s:textfield>
					</td>
				</tr>
				<tr id=sharedModel1 style="display:">
					<td class="til">
						共享虚拟处理器
					</td>
					<td>
				    	<s:textfield name="theForm.VH_MIN_CPU" id="VH_MIN_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.VH_CPU" id="VH_CPU" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.VH_MAX_CPU" id="VH_MAX_CPU" cssClass="txt"></s:textfield>             
					</td>
				</tr>
				<tr id=sharedModel2 style="display:">
				    <td class="til">
						共享处理单元 
					</td>
					<td>
				    	<s:textfield name="theForm.VH_MIN_PROCESS_UNIT" id="VH_MIN_PROCESS_UNIT" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.VH_PROCESS_UNIT" id="VH_PROCESS_UNIT" cssClass="txt"></s:textfield>&le;
				    	<s:textfield name="theForm.VH_MAX_PROCESS_UNIT" id="VH_MAX_PROCESS_UNIT" cssClass="txt"></s:textfield>              
					</td>
				</tr>
				<tr id=sharedModel3 style="display:">
					<td class="til">
						虚拟服务器共享处理单元模式
					</td>
					<td>
						<s:select list="#{'0':'未覆盖','1':'已覆盖'}" name="theForm.MODEL" id="MODEL" onclick="prioritySelect();return false;"></s:select>
					</td>
				</tr>
				<tr id=priority style="display:">
					<td class="til">
						虚拟服务器使用共享处理器池中的可用处理单元的优先级:
					</td>
					<td>
						<s:textfield name="theForm.PRIORITY" cssClass="txt" id="PRIORITY"></s:textfield>
						
					</td>
				</tr>
			<tr>
				<td class="til">
					内存设置
				</td>
				<td>
				    <s:textfield name="theForm.VH_MIN_MEM" id="VH_MIN_MEM" cssClass="txt"></s:textfield>&le;
				    <s:textfield name="theForm.VH_MEM" id="VH_MEM" cssClass="txt"></s:textfield>&le;
				    <s:textfield name="theForm.VH_MAX_MEM" id="VH_MAX_MEM" cssClass="txt"></s:textfield>             
				</td>
			</tr>
			
			<tr>
				<td class="til">
					系统
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td class="til">
					网络配置
				</td>
				<td class="til">
					
					<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" name="theForm.NET_ID" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<%--
			<tr>
				<td class="til">
					系统主机名
				</td>	
				<td>
				    <html:text property="HOSTNAMESYS" styleClass="txt" />
				</td>
			</tr>
			<tr>
				<td class="til">
					系统主机域名
				</td>
				<td>
				    <html:text property="DOMAINSYS" styleClass="txt" />
				</td>
			</tr>
			<tr>
				<td class="til">
					 <font size="2"><strong>适配器1</strong></font>
				</td>
			</tr>
			 
			<tr>
				<td class="til">
					网络配置 
				</td>
				<td class="til">
					<html:select name="theForm" property="NET_ID">
						<html:option value="0">-请选择-</html:option>
					 	<html:optionsCollection name="theForm" property="netList" label="NAME" value="NET_ID"/>
					</html:select>
				</td>
			</tr>
			
			<tr>
				<td class="til">
					静态IP地址：
				</td>
				<td>
				   	<html:text property="FREECOUNT" styleClass="txt" />                
				</td>
				<td class="til">
					主机名：
				</td>
				<td>
				   	<html:text property="HOSTNAME" styleClass="txt" />                
				</td>
			</tr>
			<tr>	
				<td class="til">
					默认网关： 
				</td>
				<td>
				   	<html:text property="GATEWAY1" styleClass="txt" />                
				</td>
				<td class="til">
					子网掩码：
				</td>
				<td>
				   	<html:text property="SUBNET" styleClass="txt" />                
				</td>
			</tr>	
			<tr>
				<td class="til">
					主DNS： 
				</td>
				<td>
				   	<html:text property="DNS1" styleClass="txt" />                
				</td>
				<td class="til">
					辅助DNS：
				</td>
				<td>
				   	<html:text property="DNS2" styleClass="txt" />                
				</td>
			</tr>
			<tr>	
				<td class="til">
					域名：
				</td>
				<td>
				   	<html:text property="DOMAIN" styleClass="txt" />                
				</td>
			</tr>
			<tr>
				<td class="til">
					 <font size="2"><strong>适配器2</strong></font>
				</td>
			</tr>
			--%>
			<tr>
				<td class="til">
					适配器1
				</td>
				<td>&nbsp;
				</td>
			</tr>
			<tr>
				<td class="til">
					网络配置 
				</td>
				<td class="til">
					<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" name="theForm.NAME" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					适配器2
				</td>
				<td>t
				</td>
			</tr>
			<tr>
				<td class="til">
					网络配置 
				</td>
				<td class="til">
					<s:select list="theForm.netList" listKey="NET_ID" listValue="NAME" name="theForm.NET_ID" headerKey="0" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<%--
			<tr>
				<td class="til">
					静态IP地址： 
				</td>
				<td>
				   	<html:text property="FREECOUNT" styleClass="txt" />                
				</td>
				<td class="til">
					主机名： 
				</td>
				<td>
				   	<html:text property="HOSTNAME" styleClass="txt" />                
				</td>
			</tr>
			<tr>	
				<td class="til">
					默认网关： 
				</td>
				<td>
				   	<html:text property="GATEWAY1" styleClass="txt" />                
				</td>
				<td class="til">
					子网掩码：
				</td>
				<td>
				   	<html:text property="SUBNET" styleClass="txt" />                
				</td>
			</tr>	
			<tr>
				<td class="til">
					主DNS： 
				</td>
				<td>
				   	<html:text property="DNS1" styleClass="txt" />                
				</td>
				<td class="til">
					辅助DNS： 
				</td>
				<td>
				   	<html:text property="DNS2" styleClass="txt" />                
				</td>
			</tr>
			<tr>	
				<td class="til">
					域名： 
				</td>
				<td>
				   	<html:text property="DOMAIN" styleClass="txt" />                
				</td>
				
			</tr>
			--%>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="部署"
							onclick="javascript:deployRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:saveRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="取消"
							onclick="window.history.back()" />
							
					</td>
				</tr>
			</table>
	</s:form>
</body>
