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
	     alert("�û���Ų���Ϊ�գ�");
	     theForm.USER_ID.focus;
	     return false  ;
	    }
	    var bool1 = isnumber(theForm.USER_ID.value);
	    if(!bool1)
		{
			alert("�û���Ų�����Ҫ��");
			return false;
		}
		theForm.action="vuser_saveVuserManage.do";
	    theForm.submit();
	}
	
	
	/* ����ַ����Ƿ�Ϊ���ֻ�����ĸ */
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
						�û���� <font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="theForm.USER_ID" styleClass="txt" readonly="true" id="USER_ID"/>                
					</td>
					<td class="til">
						�û��� <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.USER_NAME" styleClass="txt" readonly="true" id="USER_NAME"/>
					    	<input type="button" class="thickbox btn-style02-100"
							value="ѡ���û���Ϣ"
							onclick="javascript:openNewWindows();" />   
					</td>
					<td class="til">
						���� <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.PASSWORD" styleClass="txt" readonly="true" id="PASSWORD"/>                 
					</td>
				</tr>
				<tr>
					<td class="til">
						���� <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.NAME" styleClass="txt" readonly="true" id="NAME"/>                 
					</td>
					<td class="til">
						���� <font color="red"></font>
					</td>
					<td>
					    <s:textfield name="theForm.EMAIL" styleClass="txt" readonly="true" id="EMAIL"/>                 
					</td>
					 <td class="til">
						<%--��ɫ <font color="red"></font>--%>&nbsp;
					</td>
					<td>
					    <%--<html:text property="ROLE" styleClass="txt" />   --%>  &nbsp;            
					</td>
				</tr>
				<tr>	
					<td class="til">
						�ʼ�֪ͨ <font color="red"></font>
					</td>
					<td>
                      <s:select name="theForm.EMAILNOTIFICATIONS"  list="#{'0':'��','1':'��'}" id="EMAILNOTIFICATIONS">
                      </s:select>
					</td>
					<td class="til">
						�Ƿ������ <font color="red"></font>
					</td>
					<td>
                      <s:select name="theForm.ISAPPROVER" list="#{'0':'��','1':'��'}" id="ISAPPROVER">
                      </s:select>
					</td>
					<td class="til">
						�Ƿ����Ա<font color="red"></font>
					</td>
					<td>
                      <s:select name="theForm.ISADMIN" id="ISADMIN" list="#{'0':'��','1':'��'}">
                      </s:select>
					</td>
				</tr>
				<tr>
					<font color="red" size="2">ע:�û����ֻ���������֣�������ѡ���û���Ϣ��ťѡ���û��������롢���֡��������Ϣ</font>  
				</tr>
				
				<tr>
					<td colspan="6" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="ȷ��"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="����"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
						<input type="button" class="thickbox btn-style02" value="����"
							onclick="window.history.back()"/>
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
