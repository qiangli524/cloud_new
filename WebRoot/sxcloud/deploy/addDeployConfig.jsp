<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp"%>
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
	 
	    if(thisForm.HOSTID.value.length ==0){
	     alert("������������Ϊ�գ�");
	     thisForm.HOSTID.focus;
	     return false  ;
	    }
	    if(thisForm.APPID.value.length ==0){
	     alert("����Ӧ�ò���Ϊ�գ�");
	     thisForm.APPID.focus;
	     return false  ;
	    }
	    if(thisForm.REMARK.value.length ==0){
	     alert("��ע����Ϊ�գ�");
	     thisForm.REMARK.focus;
	     return false  ;
	    }
	    thisForm.submit();
	}
	



</script>
</head>
<body class="pop-body scrollbody">
	<html:form action="saveDeployConfig" method="post" styleId="theForm">
	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>">
		<bean:define id="theForm" name="DeployConfigForm" />
					   
		<html:hidden name="theForm" property="ID" />
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				    <td class="til">
						��������<font color="red">*</font>
					</td>
					<td>
                            <html:select name="theForm" property="HOSTID" >
					        <html:option value="">-��ѡ��-</html:option>
					        <html:optionsCollection name="theForm" property="hostList" label="HOSTNAME" value="HOSTID"/>
					        </html:select>
					</td>
				    <td class="til">
						����Ӧ�� <font color="red">*</font>
					</td>
					<td >
						    <html:select name="theForm" property="APPID" >
					        <html:option value="">-��ѡ��-</html:option>
					        <html:optionsCollection name="theForm" property="appList" label="APPNAME" value="APPID"/>
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
