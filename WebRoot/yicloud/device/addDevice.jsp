<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
   function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	/* 检测输入的字符串是否符合要求 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	   {
	            if (number_chars.indexOf(str.charAt(i))==-1) return false;
	        }
	        return true;
	}
//是否是ip地址
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
	
	function submitRequest(theForm) {
		if (theForm.DEVICE_NAME.value == '') {
			alert("设备中文名称不能为空！");
			return false;
		}
		if (theForm.DEVICE_NAME_EN.value == '') {
			alert("设备英文名称不能为空！");
			return false;
		}
		if (theForm.DEVICE_TYPE.value == '') {
			alert("设备类型不能为空！");
			return false;
		}
		var type = $("#DEVICE_TYPE").find("option:selected").val();
		if (type == 1) {
			if (theForm.eq_name.value == '') {
				alert("服务器名称不能为空！");
				return false;
			}
			if($(":input#eq_type").find("option:selected").val()==''){
				alert("请选择服务器类型！");
				return false;
			}
			if (theForm.eq_ip.value == '') {
				alert("服务器IP地址不能为空！");
				return false;
			}
			var ip = theForm.eq_ip.value;
			if (!ipFormat(ip)) {
				alert("服务器IP输入不合法,只能为.和数字!");
				return false;
			}
			if($(":input#eq_hostname").val()==''){
				alert("服务器主机名称不能为空！");
				return false;
			}
			if($(":input#hasvertual").find("option:selected").val()==''){
				alert("请选择是否支持虚拟化！");
				return false;
			}
			if($(":input#cq_id").find("option:selected").val()=='0'){
				alert("请选择所属机柜！");
				return false;
			}
			if($(":input#theForm_theForm_PROTOCOL").find("option:selected").val()==''){
				alert("请选择监控方式！");
				return false;
			}
			if($(":input#theForm_theForm_control").find("option:selected").val()==''){
				alert("请选择云平台能否管控！");
				return false;
			}
		}
		var flagTemp=true;
		$(function(){
			$("[nameId='meterNumber']").each(function(data){
	              var str=$(this).val();
	              var temp=isnumber(str);
	              if(!temp){
	                  var name=$(this).attr("nameTemp");
	                  alert(name+" 必须为数字!");
	                  flagTemp=false;
	                  return false;
	               }
				});
			if(flagTemp){
				$("[nameId='ip']").each(function(data){
		            var str=$(this).val();
		            if(null==Trim(str)||''==Trim(str)){
                         return true;
			        }
		            var temp=ipFormat(str);
		            if(!temp){
		                var name=$(this).attr("nameTemp");
		                alert(name+" 格式不正确!");
		                flagTemp=false;
		                return false;
		             }
					});
				}
			});
		if(!flagTemp){
			return false;
		}
		theForm.action = "deviceinfo_saveDevice.do";
		theForm.submit();
	}

	function loadDeviceInfo() {
		var type = $("#DEVICE_TYPE").find("option:selected").val();
		if (type == 1) {
			document.getElementById('trans').style.display = "none";
			document.getElementById('host').style.display = "";
			document.getElementById('raid').style.display = "none";
		} else if (type == 2 || type == 3) {
			document.getElementById('trans').style.display = "";
			document.getElementById('host').style.display = "none";
			document.getElementById('raid').style.display = "none";
		} else if (type == 4) {
			document.getElementById('trans').style.display = "none";
			document.getElementById('host').style.display = "none";
			document.getElementById('raid').style.display = "";
		}
	}
</script>
</head>
<body class="pop-body scrollbody">

<s:form action="deviceinfo_saveDevice.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<div class="tit-zzi">
			<div id="zi">
			设备基本信息
			</div>
			<div id="zi"></div>
		</div>
			<tr>
				<td class="til" width="20%">
					设备中文名：*
				</td>
				<td>
				    <s:textfield name="theForm.DEVICE_NAME" cssClass="txt" id="DEVICE_NAME"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					设备英文名:*
				</td>
				<td>
					<s:textfield name="theForm.DEVICE_NAME_EN" cssClass="txt" id="DEVICE_NAME_EN"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					设备编码：
				</td>
				<td>
					<s:textfield name="theForm.DEVICE_CODE" cssClass="txt" id="DEVICE_CODE"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					设备描述：
				</td>
				<td>
					<s:textfield name="theForm.DEVICE_DESC" cssClass="txt" id="DEVICE_DESC"></s:textfield>
					
				</td>
			</tr>
			<tr>
				<td class="til">
					设备类型:*
				</td>
				<td>
					<s:select list="#{'1':'主机','2':'交换机','3':'路由器','4':'磁盘阵列'}" headerKey="" headerValue="-请选择-" name="theForm.DEVICE_TYPE" id="DEVICE_TYPE" onchange="loadDeviceInfo();"></s:select>
				</td>
			</tr>
			</table>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<div class="tit-zzi">
				<div id="zi">
					设备详细信息
				</div>
				<div id="zi"></div>
			<tr>
				<td id="host" style="display: none;">
					<jsp:include page="editHost.jsp"></jsp:include>
				</td>
				<td id="trans" style="display: none;">
					<jsp:include page="editTrans.jsp"></jsp:include>
				</td>
				<td id="raid" style="display: none;">
					<jsp:include page="editRaid.jsp"></jsp:include>
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
	</s:form>
</body>
