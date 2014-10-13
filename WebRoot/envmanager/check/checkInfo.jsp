<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
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
	function submitRequest(){ 
		var ip = document.getElementById('ip').value;
		var name = document.getElementById('name').value;
		var shell = document.getElementById('shell').value;
		var keyword = document.getElementById('keyword').value;
		if(ip.length==0){
			alert("脚本存放地址不能为空");
			return false;
		}
		if (!ipFormat(ip)) {
			alert("IP地址输入不合法,只能为.和数字!");
			return false;
				}
		if(name.length==0){
			alert("脚本执行用户不能为空");
			return false;
		}
		if(shell.length==0){
			alert("脚本执行命令不能为空");
			return false;
		}
		if(keyword.length==0){
			alert("执行用户密码不能为空");
			return false;
		}
		theForm.submit();
		}
	 function init(){
			if('${msg }'!=''){alert('${msg }');}
			}
  
	
</script>
</head>
<body class="pop-body scrollbody"  onload="init()">
	<s:form action="performance_startCheckInfo.do" method="post" id="theForm" >
		<!-- <s:hidden name="theForm.AREA_ID" id="AREA_ID" />
		<s:hidden name="theForm.flag" id="flag"></s:hidden> -->
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						脚本存放地址：<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="ip"  id="ip" maxlength="12" style="width:260px;"/>
					</td>
					
				</tr>

				<tr>
					<td class="til">
						脚本执行用户：<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="name" id="name" maxlength="12" style="width:260px;"/>
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						脚本执行命令：<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="shell" id="shell" maxlength="100" style="width:260px;"/>
					</td>
				</tr>
				<tr>
				  
					<td class="til">
						执行用户密码：<font color="red">*</font>
					</td>
					<td>
                        <s:password name="keyword" id="keyword" maxlength="12" style="width:260px;"/>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" class="btnCenter">

						<input type="submit" class="thickbox btn-style02-100" value="检查MAC地址"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						
					</td>
				</tr>

			</table>
	</s:form>
</body>


