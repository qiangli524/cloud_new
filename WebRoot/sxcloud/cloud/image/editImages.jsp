<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../../common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../common/link.jsp"%>
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
	    if(thisForm.FUNCID.value.length ==0){
	     alert("����Ų���Ϊ�գ�");
	     thisForm.FUNCID.focus;
	     return false  ;
	    }
	    var bool1 = isnumber(thisForm.FUNCID.value);
	    if(!bool1)
		{
			alert("����Ų�����Ҫ��");
			return false;
		}
	    if(thisForm.FUNNAME.value.length ==0){
	     alert("�������Ʋ���Ϊ�գ�");
	     thisForm.FUNNAME.focus;
	     return false  ;
	    }
	    if(thisForm.FUNCREQUEST.value.length ==0){
	     alert("���ӵ�ַ����Ϊ�գ�");
	     thisForm.FUNCREQUEST.focus;
	     return false  ;
	    }
	    thisForm.submit();
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

</script>
</head>
<body class="pop-body scrollbody">
	<html:form action="saveImageInfo" method="post" styleId="theForm">
		<bean:define id="theForm" name="imageForm" />
		<input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
		<html:hidden name="theForm" property="IM_ID" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						ӳ������ <font color="red">*</font>
					</td>
					<td>
					    <html:text property="FUNCID" styleClass="txt" /><font color="red" size="1"> ע:ֻ����������</font>                 
					</td>
					<td class="til">
						ӳ������ <font color="red">*</font>
					</td>
					<td>
					    <html:text property="FUNNAME" styleClass="txt" />                 
					</td>
				</tr>
				<tr>
				    <td class="til">
						״̬<font color="red">*</font>
					</td>
					<td>
                      <html:select property="STATUS" >
                          <html:option value="0">ʧЧ</html:option>
                          <html:option value="1">��Ч</html:option>
					    </html:select>
					</td>
				    <td class="til">
						���� <font color="red">*</font>
					</td>
					<td >
						<html:select property="TYPE" >
					      <html:option value="1">�˵�����</html:option>
					      <html:option value="2">��ť����</html:option>
					    </html:select>
					</td>
				</tr>
				<tr>
				    <td class="til">
						���ӵ�ַ <font color="red">*</font>
					</td>
					<td>
					    <html:text property="FUNCREQUEST" styleClass="txt" />                 
					</td>
					<td class="til">
						�����Ƿ�ˢ�� <font color="red">*</font>
					</td>
					<td>
					    <html:select property="ISREFRESH" >
					      <html:option value="1">ˢ��</html:option>
					      <html:option value="0">��ˢ��</html:option>
					    </html:select>                 
					</td>
				</tr>
				
				<tr>
				    <td class="til">
						��ע
					</td>
					<td colspan="3">
						<html:textarea property="REMARK" cols="77" rows="5"></html:textarea>
					</td>		
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="ȷ��"
							onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="����"
							onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
					</td>
				</tr>
			</table>
	</html:form>
</body>

</html:html>
