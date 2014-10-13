<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
	function submitRequest(theForm){  
	    if(theForm.USER_ID.value.length ==0){
	     alert("用户编号不能为空！");
	     theForm.USER_ID.focus;
	     return false  ;
	    }
	    var bool1 = isnumber(theForm.USER_ID.value);
	    if(!bool1)
		{
			alert("用户编号不符合要求");
			return false;
		}
		theForm.action="vuser_saveVuserManage.do";
	    theForm.submit();
	}
	
	
	/* 检测字符串是否为数字或者字母 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890";
	    var i;
	    for (i=0;i<str.length;i++)
	      {
	          if (number_chars.indexOf(str.charAt(i))==-1) return false;
	      }
	      return true;
	 }
	function openNewWindows(){
		window.open("vuser_listUserInfoWindow.do","listUserInfoWindow",'width=800, height=600, resizable=yes');
 	}

</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="vuser_saveVuserManage.do" method="post" id="theForm">
		<s:hidden name="theForm.flag" id="flag" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				  <td class="til">
						用户编号 <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.USER_ID" styleClass="txt" readonly="true" id="USER_ID"/>                
					</td>
					<td class="til">
						用户名 <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.USER_NAME" styleClass="txt" readonly="true" id="USER_NAME"/>
					    	<input type="button" class="thickbox btn-style02-100"
							value="选择用户信息"
							onclick="javascript:openNewWindows();" />   
					</td>
					<td class="til">
						密码 <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.PASSWORD" styleClass="txt" readonly="true" id="PASSWORD"/>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						名字 <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.NAME" styleClass="txt" readonly="true" id="NAME"/>                 
					</td>
					<td class="til">
						邮箱 <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.EMAIL" styleClass="txt" readonly="true" id="EMAIL"/>                 
					</td>
					 <td class="til">
						<%--角色 <font color="red"></font>--%>&nbsp;
					</td>
					<td>
					    <%--<html:text property="ROLE" styleClass="txt" />   --%>  &nbsp;            
					</td>
				</tr>
				<tr>	
					<td class="til">
						邮件通知 <font color="red"></font>
					</td>
					<td>
                      <s:select name="theForm.EMAILNOTIFICATIONS"  list="#{'0':'否','1':'是'}" id="EMAILNOTIFICATIONS">
                      </s:select>
					</td>
					<td class="til">
						是否审核人 <font color="red"></font>
					</td>
					<td>
                      <s:select name="theForm.ISAPPROVER" list="#{'0':'否','1':'是'}" id="ISAPPROVER">
                      </s:select>
					</td>
					<td class="til">
						是否管理员<font color="red"></font>
					</td>
					<td>
                      <s:select name="theForm.ISADMIN" id="ISADMIN" list="#{'0':'否','1':'是'}">
                      </s:select>
					</td>
				</tr>
				<tr>
					<font color="red" size="2">注:用户编号只能输入数字，另请点击选择用户信息按钮选择用户名、密码、名字、邮箱的信息</font>  
				</tr>
				
				<tr>
					<td colspan="6" class="btnCenter">
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
