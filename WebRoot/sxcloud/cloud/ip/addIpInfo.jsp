<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
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
	function submitRequest(thisForm){
		if(document.getElementById('singleIp').style.display==""){
			if(thisForm.IPADDRESS.value.length==0){
				alert("IP地址不能为空！");
		 		thisForm.IPADDRESS.focus;
		 		return false  ;
			 }
	 	}
		if(document.getElementById('multiIp').style.display==""){
			if(thisForm.STARTIPADDRESS.value.length0=0 || thisForm.ENDIPADDRESS.value.length==0){
				alert("IP地址不能为空！");
	 			thisForm.STARTIPADDRESS.focus;
	 			return false  ;
		 	}
 		}
	    
		if(document.getElementById('singleIp').style.display==""){
			if(!ipFormat(thisForm.IPADDRESS.value)){
				alert("IP地址格式不正确");
				return false;
			}
    	}
	    
		if(document.getElementById('multiIp').style.display==""){
			if(!ipFormat(thisForm.STARTIPADDRESS.value)){
				alert("起始IP地址格式不正确");
				return false;
			}
			if(!ipFormat(thisForm.ENDIPADDRESS.value)){
				alert("终止IP地址格式不正确");
				return false;
			}
		}
	  
		var choose = $("#choose").val();
		var creatType = "";
		var ipAddr = "";
		var ipStart = "";
		var ipEnd = "";
		if(choose==0){
			creatType =  "single";
			ipAddr = $("#IPADDRESS").val();
		}else{
			creatType = "multi";
			ipStart = $("#STARTIPADDRESS").val();
			ipEnd = $("#ENDIPADDRESS").val();
			var maSta = ipStart.substring(0, ipStart.lastIndexOf("."));
			var maEnd = ipEnd.substring(0, ipEnd.lastIndexOf("."));
			if(maSta!=maEnd){
				alert("添加多个IP地址时，起止IP的前三位必须相同 ！");
				return false;
			}
		}
		var netWork = $("#netWorkName").attr("value");
		if(netWork == 0){
			alert("请选择所属网络");
			return false;
		}
		var url = "";
		if(creatType == "single"){
			url = "ip_validateIpInfo.do?creatType="+creatType + "&ipAddr=" + ipAddr;
		}else{
			url = "ip_validateIpInfo.do?creatType="+creatType + "&ipStart=" + ipStart + "&ipEnd="+ipEnd;
		}
		$.getJSON(url,{"time":new Date().toString()},function(date){
			if(date=="true"){
				theForm.submit();
			}else{
				alert(date);
			}
		});
	}
	
	
	/* 检测字符串是否为IP地址 */
	function ipFormat(str)	{
		var re=/^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	  	if (str.match(re)== null){
      		return false;
    	}else{
    		var stn = str.split(".");
    		var re1=/^0\d|0\d\d|00\d$/;
			for(var i=0;i<4;i++){
				if(stn[i].match(re1)!= null){
				//alert("is 0");
				return false;
				break;
				}
			}
		}
    	return true;
    }
 	function backup() {
 		theForm.IPADDRESS.value='';
 	    theForm.action = 'ip_listIpInfo.do' 
		theForm.submit();
 	}
 	function ipModelSelect(){
		var str = document.theForm.choose.value;
		if(str=='0'){
			document.getElementById('singleIp').style.display="";
			document.getElementById('multiIp').style.display="none";
		}else
		if(str=='1'){
			document.getElementById('singleIp').style.display="none";
			document.getElementById('multiIp').style.display="";
		}
	}
</script>
</head>
<body class="pop-body scrollbody" onload=ipModelSelect()>
	<div class="pd-20 bgcolor-1">
	<h2 class="utt-1">IP管理</h2>
	<div class="bord-1 pd-10">
	<s:form action="ip_saveIpInfo.do" method="post" cssStyle="theForm" id="theForm">
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til">
						选择IP地址增加方式 <font style="color:#FF3401">*</font>
					</td>
					<td >
						<s:select list="#{'0':'添加单个IP地址','1':'添加多个IP地址'}" name="theForm.choose" id="choose" onclick="ipModelSelect();return false;" cssClass="select-1"></s:select>
					</td>
				</tr>
				<tr id="singleIp" style="display:">
					<td class="til">
						IP地址 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.IPADDRESS" cssStyle="txt" id="IPADDRESS" cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
				<tr id="multiIp" style="display:">
					<td class="til">
						IP起止地址 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.STARTIPADDRESS" cssStyle="txt" id="STARTIPADDRESS" cssClass="inpt-2"></s:textfield>
						<s:text name="">-</s:text>
						<s:textfield name="theForm.ENDIPADDRESS" cssStyle="txt" id="ENDIPADDRESS" cssClass="inpt-2"></s:textfield>
						<s:text name="">注意：起止IP的前三位必须相同</s:text>
					</td>
				</tr>
				<tr>
					<td class="til">
						IP地址类型 <font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'1':'公网IP','2':'内网IP'}" name="theForm.IP_TYPE" id="IP_TYPE" cssClass="select-1"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						所属网络名称 <font color="red">*</font>
					</td>
					<td>
						<s:select list="theForm.netList" headerKey="0" headerValue="-请选择-" listKey="NET_ID" listValue="NAME" name="theForm.NET_ID" id="netWorkName" cssClass="select-1"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<span class="ubtn-green"><input type="button" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						</span>
						<span class="ubtn-orange mgl-20"><input type="button" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						</span>
						<span class="ubtn-blue mgl-20"><input type="button" value="返回"
				 			onclick="javascript:backup();return false;" />
				 		</span>
					</td>
				</tr>
			</table>
	</s:form>
        </div>
    </div>
</body>

</html:html>
