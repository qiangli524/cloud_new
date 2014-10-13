<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
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
		var ID = <%=request.getAttribute("ID")%>;
		var TYPE=<%=request.getAttribute("TYPE")%>
		var ip = theForm.IP.value;
		var name = theForm.NAME_ZH.value
		var username = theForm.USERNAME.value;   
		var password = theForm.PASSWORD.value;
		if(ip==null){
			alert("IP地址不能为空，例如：1.2.3.4");
		}
		if(name==null){
			alert("主机名称不能为空");
		}
		if(username==null){
			alert("用户名称不能为空");
		}
		if(password==null){
			alert("密码不能为空");
		}
		theForm.action='yvm_saveHost.do?ID='+ID+'&TYPE='+TYPE;
	    theForm.submit();
	}
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="yvm_saveHost.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					集群名称：
				</td>
				<td>
					<s:text name="theForm.NAME" />
				</td>
			</tr>
			<tr>
				<td class="til">
					IP地址：
				</td>
				<td>
					<s:textfield name="theForm.IP" id="IP"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					主机名称:
				</td>
				<td>
					<s:textfield name="theForm.NAME_ZH" id="NAME_ZH"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					主机用户名：
				</td>
				<td>
					<s:textfield name="theForm.USERNAME" id="USERNAME"/>
				</td>
			</tr>
			
			<tr>
				<td class="til">
					密码：
				</td>
				<td>
					<s:password name="theForm.PASSWORD" id="PASSWORD"/>
				</td>
			</tr>
			
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>

</html:html>
