<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
     api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:updateConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
    function updateConfig(){
    	var name =$("#switch_name").val();   //名称
		var type =$("#switch_type").val();  //型号
		var ip =$("#switch_ip").val();  //ip
		var status = $("#switch_status").val(); //状态
		var oem = $("#switch_oem").val();   //厂商
		var vision = $("#switch_vision").val();   //版本
		var des = $("#switch_des").val();   //描述
		if(name==null || name==''){
			alert('请填写交换机名称');
				return false;
		}
		if(type==null || type ==''){
			alert('请填写交换机型号');
				return false;
		}
		if(!isIp(ip.toString())){
			alert('ip地址有误，请重新输入');
			return false;
		}
		if(oem==null || oem ==''){
			alert('请填写交换机厂商');
				return false;
		}
		if(vision==null || vision ==''){
			alert('请填写交换机版本');
				return false;
		}
		if(des==null || des ==''){
			alert('请填写交换机描述');
				return false;
		}
		var id = $("#id").val();
		if (id == null || id == ''){
			$.ajax({
				type:"POST",
	            url:"switchAction_insert.do",
	            data:$("#theForm").serialize()+"&"+$("#snmp").serialize()+"&"+$("#protocol").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
			});
		}else{
			$.ajax({
				type:"POST",
	            url:"switchAction_update.do",
	            data:$("#theForm").serialize()+"&"+$("#snmp").serialize()+"&"+$("#protocol").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
			});
		}
		w.searchRequest();
	}
	</script>
	</script>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="" method="post" name="theForm" id="theForm" cssStyle="theForm">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<input type="hidden" id="id" name="switchId" value="<s:property value='switchId'/>"/>
		  	<div class="tit-zzi">
				<div id="zi">
					基本信息
				</div>
				<tr>
					<td class="til" width="20%" align="left">
						交换机名称<span style="color:red">*</span>
					</td>
					<td>
						<s:textfield name="theForm.switch_name" id="switch_name" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						型号<span style="color:red">*</span>
					</td>
					<td>
						<s:textfield name="theForm.switch_type" id="switch_type" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						状态<span style="color:red">*</span>
					</td>
					<td>
					<s:select name="theForm.switch_status" list="#{'0':'unlock','1':'lock'}"  id="switch_status" ></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						管理IP<span style="color:red">*</span>
					</td>
					<td>
						<s:textfield name="theForm.switch_ip" id="switch_ip"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						交换机厂商<span style="color:red">*</span>
					</td>
					<td>
						<s:textfield name="theForm.switch_oem"  id="switch_oem" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						交换机版本<span style="color:red">*</span>
					</td>
					<td>
						<s:textfield name="theForm.switch_vision" id="switch_vision" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						交换机描述<span style="color:red">*</span>
					</td>
					<td>
						<s:textarea name="theForm.switch_des" id="switch_des" cols="50" rows="5" >
						</s:textarea>
					</td>
				</tr>
			</div>
		</table>
	</s:form>
	<s:form action="resource_saveHost" method="post" id="snmp" >
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<div class="tit-zzi">
					<div id="zi">
						SNMP接入参数
					</div>
						<tr>
						    <td class="til">
								版本<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.version" id="version" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								IP <font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.ip" id="ip"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								端口<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.port" id="port" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								超时时间<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.time_out" id="time_out"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								重试次数<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.retry_nums" id="retry_nums"/>
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								SMM1 IP地址<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.smm1_ip" id="smm1_ip"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								SMM2 IP地址<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.smm2_ip" id="smm2_ip" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								共同体名<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.community" id="community"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								安全模式<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.security_mode" id="security_mode" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								安全名称<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.security_name" id="security_name"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								鉴权算法<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.indentity_metic" id="indentity_metic" />
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								鉴权密码<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.indentity_password" id="indentity_password"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								加密算法<font color="red">*</font>
							</td>
							<td >
								<s:textfield name="snmp.encrypt_metic" id="encrypt_metic"/>
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								加密密码<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="snmp.encrypt_password" id="encrypt_password"/>
							</td>
						</tr>
					</div>
				</table>
			</s:form>
			<s:form action="resource_saveHost" method="post" id="protocol" >
				<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
					<div class="tit-zzi">
						<div id="zi">
							管理协议
						</div>
						 <tr>
						    <td class="til">
								配置管理协议<font color="red">*</font>
							</td>
							<td >
								<s:radio name="protocol.isOpen" id="isOpen" list="#{'0':'否','1':'是'}"/>
							</td>
							<td class="til">
								版本<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="protocol.version" id="version"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								IP<font color="red">*</font>
							</td>
							<td >
								 <s:textfield name="protocol.ip" id="ip"/>
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								端口<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="protocol.port" id="port"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								用户名<font color="red">*</font>
							</td>
							<td >
								 <s:textfield name="protocol.username" id="username"/>
								<span style="color:RED" id="span_key"/>    
							</td>
							<td class="til">
								密码<font color="red">*</font>
							</td>
							<td>
		                        <s:textfield name="protocol.password" id="password"/>
							</td>
						</tr>
					</div>
				</table>
		</s:form>	
  </body>
</html>
