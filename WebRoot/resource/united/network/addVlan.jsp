<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
	
	<script type="text/javascript">
	
	 var api = frameElement.api;
	 var w = api.opener;

	 api.button({
	     id:'Ok',
	     name: '添加',
	     callback:addVlan,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });	
	function addVlan(){    
		var parentId = $("#parent_id").val();
	    var vtype = $("#vtype").val();
	    var type = $("#vtype").val();
	    var subNet = $("#SUBNET").val();
	    var name = $("#NAME").val();
	    var description = $("#DESCRIPTION").val();
	    var DNS1 = $("#DNS1").val();
	    var netDomId = $("#netDomId").val();
	    var subNetId = $("#subNetId").val();
	     var pattern =  /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
	    var radio = $(":radio:checked").val();
	     if(name==""){
		     alert("网络名称不能为空！");
		     $("#NAME").focus();
		     return false  ;
	    }
	    if(netDomId==0){
	    	alert("请选择网络域！");
	    	$("#netDomId").select();
         	$("#netDomId").focus();
         	return false;
	    }
	     if(subNetId==''){
	    	alert("请选择子网络域！");
	    	$("#subNetId").select();
         	$("#subNetId").focus();
         	return false;
	    }
	    if(radio==undefined || radio==1){
	         if(subNet==""){
           		  alert("子网掩码不能为空！");
           		   $("#SUBNET").focus();
           		  return false;
	         }
	         if(!pattern.exec(subNet)){
					$("#SUBNET").attr("class","netMask");
					$("#SUBNET").select();
					$("#SUBNET").focus();
					return false;
				}
	          if(DNS1==""){
			     alert("主DNS不能为空！");
			     $("#DNS1").focus();
			     return false  ;
	   		 }
			var url = "unitedNetwork_saveVlan.do?"+$("#netObj").serialize()+"&name="+encodeURI(encodeURI(name))+"&description="+encodeURI(encodeURI(description));
			w.saveVlan(url,parentId);
	    }else if(radio==0){
	    	var url = "unitedNetwork_saveVlan.do?"+$("#netObj").serialize();
			w.saveVlan(url,parentId);
	    }
	}
	
	
 	function ipSelect1(){
 		$("#manual1").hide();
 		$("#manual2").hide();
 		$("#manual3").hide();
 		//$("#manual4").hide();
 		//$("#manual5").hide();
	}
	function ipSelect2(){
 		$("#manual1").show();
 		$("#manual2").show();
 		$("#manual3").show();
 		//$("#manual4").show();
 		//$("#manual5").show();
	}   
	function radioCheck(){
		 var radio = $(":radio:checked").val();
	     if(radio==undefined || radio==1){
	     	ipSelect2();
	     }else{
	     	ipSelect1();
	     }
	}
	function querySubNet(){
		var id = $("#netDomId").val();
		if(id==0){
			$("#subNetId").html("");
			$("#subNetId").append("<option value=''>-请选择-</option>");
			return false;
		}
		var url ="unitedNetwork_querySubNet.do?obj.parent_id="+id;
		$.ajax({
			type:"POST",
			url : url,
			dataType : "xml",
			cache:false,
		    processData: false,
		    async:false,
		    success : function(data){	//将后台返回数据添加到下拉列表
		    $("#subNetId").html("");
			$("#subNetId").append("<option value=''>-请选择-</option>");
	    	  $(data).find("subDom").each(
					function(){
						var listKey = $(this).attr("value");
						var listValue = $(this).attr("text");
						$("#subNetId").append("<option value =" + listKey + ">" + listValue + "</option>");
			  });
	       }
		});
	}
	//通过网络域查询子网络域之后 做判断 选择相应的子网络域
	$(function(){
		var netDomId = $("#netDomId").val();
		var subID = $("#subID").val();
		if (netDomId.length > 0) {
			$.ajax({
				type:'post',
				dataType:'json',
				url:'unitedNetwork_selectSub.do?netDomId='+netDomId,
				async:false,
				success:function(msg){
					for ( var i = 0; i < msg.length; i++) {
						if (msg[i].id == subID) {
							$("<option value="+msg[i].id+" selected='selected'>"+msg[i].name+"</option>").appendTo("#subNetId");
						} else{
							$("<option value="+msg[i].id+">"+msg[i].name+"</option>").appendTo("#subNetId");
						}
					}
				}
			});
		}
	});
</script>
</head>
<body class="pop-body scrollbody" onload=radioCheck()>
	<s:form action="" method="post" cssStyle="netObj" id="netObj">
		<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
		<s:hidden name="netObj.NET_ID" id="NET_ID"></s:hidden>
		<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
		<s:hidden name="obj.vtype" id="vtype"></s:hidden>
		<s:hidden name="obj.type" id="type"></s:hidden>
		<s:hidden name="subID" id="subID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						网络名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="netObj.NAME" cssStyle="txt" id="NAME"></s:textfield>
					</td>
					<td class="til">
						网络描述 
					</td>
					<td>
						<s:textfield name="netObj.DESCRIPTION" cssStyle="txt" id="DESCRIPTION"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						使用方式
					</td>
				    <td colspan="10">
				    	<s:radio list="#{'0':'DHCP'}" name="netObj.USEDHCP" onclick="ipSelect1();" ></s:radio>
				    	<s:radio list="#{'1':'IP地址池'}" name="netObj.USEDHCP" onclick="ipSelect2();"></s:radio>
					</td>
				</tr>
				
				<%-- <tr>
				    <td class="til">
						网络类型 
					</td>
					<td>
						<s:select list="#{'':'-请选择-','2':'XEN','1':'Vmware'}"
									name="netObj.NET_TYPE" id="NET_TYPE"></s:select>
					</td>
					<td class="til">
						是否缺省
					</td>
					<td>
						<s:select list="#{'':'-请选择-','0':'缺省网络','1':'非缺省网络'}"
									name="netObj.ISDEFAULT" id="ISDEFAULT"></s:select>
					</td>
				</tr> --%>
				
				<tr id="manual1" style="display:">
				    <td class="til">
						子网掩码 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="netObj.SUBNET" cssStyle="txt" id="SUBNET" cssClass="netMask"></s:textfield>
					</td>
					 <td class="til">
						网关地址 
					</td>
					<td>
						<s:textfield name="netObj.GATEWAY1" cssStyle="txt" id="GATEWAY1"></s:textfield>
					</td>
				</tr>
				<tr id="manual2" style="display:">
					<td class="til">
						备用网关地址
					</td>
					<td>
						<s:textfield name="netObj.GATEWAY2" cssStyle="txt" id="GATEWAY2"></s:textfield>
					</td>
					<td class="til">
						VLAN
					</td>
					<td>
						<s:textfield name="netObj.VLAN_ID" cssStyle="txt" id="VLAN_ID"></s:textfield>
					</td>
				</tr>
				<tr id="manual3" style="display:">
				    <td class="til">
						主DNS <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="netObj.DNS1" cssStyle="txt" id="DNS1"></s:textfield>
					</td>
					 <td class="til">
						辅助DNS 
					</td>
					<td>
						<s:textfield name="netObj.DNS2" cssStyle="txt" id="DNS2"></s:textfield>
					</td>
					</tr>
				<%-- <tr id="manual4" style="display:">
				  <td class="til">
						域名后缀
					</td>
					<td>
						<s:textfield name="netObj.DOMAINSUFFIXES" cssStyle="txt" id="DOMAINSUFFIXES"></s:textfield>
					</td>
					<td class="til">
						域名
					</td>
					<td>
						<s:textfield name="netObj.DOMAIN" cssStyle="txt" id="DOMAIN"></s:textfield>
					</td>
				</tr>
				<tr>
				    <td class="til">
						主机名前缀 
					</td>
					<td>
						<s:textfield name="netObj.HOSTNAMEPREFIX" cssStyle="txt" id="HOSTNAMEPREFIX"></s:textfield>
					</td>
					 <td class="til">
						计算机名前缀
					</td>
					<td>
						<s:textfield name="netObj.COMPUTERNAMEPREFIX" cssStyle="txt" id="COMPUTERNAMEPREFIX"></s:textfield>
					</td>
					</tr>
				<tr>
				  <td class="til">
						工作组
					</td>
					<td colspan="10">
						<s:textfield name="netObj.WORKGROUP" cssStyle="txt" id="WORKGROUP"></s:textfield>
					</td>					
				</tr>
				<tr id="manual5" style="display:">
					<td class="til">
						主要WINS地址	
					</td>
					<td>
					<s:textfield name="netObj.WINS1" cssStyle="txt" id="WINS1"></s:textfield>
					</td>
				    <td class="til">
						辅助WINS地址
					</td>
					<td>
					<s:textfield name="netObj.WINS2" cssStyle="txt" id="WINS2"></s:textfield>
					</td>
				</tr> --%>
				<tr>
				   <td class="til">
						网络域<font color="red">*</font>
					</td>
					<td>
                       <s:select list="obj.netDomList" headerKey="0" headerValue="-请选择-" listKey="id" listValue="name" name="netDomId" id="netDomId" onchange="querySubNet()">
	                   </s:select>
					</td>
					<td class="til">
						子网络域<font color="red">*</font>
					</td>
					<td>
                        <s:select list="#{'':'-请选择-'}" id="subNetId" name="obj.pratentId">
	                   </s:select>
					</td>
				</tr>
			</table>
	</s:form>
</body>
</html:html>
