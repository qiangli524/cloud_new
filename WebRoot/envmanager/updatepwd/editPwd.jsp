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
	 var flag = document.getElementById("flag").value;
	 var PWD_USER = document.getElementById("PWD_USER").value;
	 var IPADDRESS = document.getElementById("IPADDRESS").value;
	 if(flag==1){
	 	var PWD_NEWT = document.getElementById("PWD_NEWT").value;
	 }
	 else{
	 	var PWD_NEW = document.getElementById("PWD_NEW").value;
	 }
	 if (PWD_USER.length == 0) {
			alert("用户名不能为空！");
			return false;
		}
	 if (IPADDRESS.length == 0) {
			alert("IP地址不能为空！");
			return false;
		}
	 if (!ipFormat(IPADDRESS)) {
				alert("IP输入不合法,只能为.和数字!");
				return false;
			}
	 if(flag==0){
		 if (PWD_NEW.length == 0) {
				alert("用户密码不能为空！");
				return false;
			}
		}
	 else{
		 if (PWD_NEWT.length == 0) {
					alert("用户新密码不能为空！");
					return false;
				}
	}
	theForm.submit();
	}
	
  
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="pwdobj_savePwdObj.do" method="post" id="theForm" >
	<s:hidden name="theForm.DEVICE_ID" id="DEVICE_ID" />
		<s:hidden name="theForm.pwd_id" id="pwd_id" />
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						用户名： <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.PWD_USER"  id="PWD_USER"/>
					</td>
					
					<td class="til">
						IP地址:<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.IPADDRESS" id="IPADDRESS" maxlength="12"/>
					</td>
					
					
				</tr>

				<tr>
				<s:if test="theForm.flag==1">
				  <td class="til">
						用户旧密码：<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.PWD_NEW" id="PWD_NEW" maxlength="16" readonly="true"/>
					</td>
					<td class="til">
						用户新密码：<font color="red">*</font>
					</td>
					<td >
						<s:textfield name="theForm.PWD_NEWT" id="PWD_NEWT" maxlength="16"/>
					</td>
					</s:if>
					<s:else>
					<td class="til">
						用户密码：<font color="red">*</font>
					</td>
					<td>
                        <s:textfield name="theForm.PWD_NEW" id="PWD_NEW" maxlength="16"/>
					</td>
					<td class="til">
						
					</td>
					<td >
						
					</td>
					
					</s:else>
				</tr>
				
				<tr>
					<td colspan="4" class="btnCenter">

						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>

			</table>
	</s:form>
</body>
</html:html>
