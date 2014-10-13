<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript">
	
	$(function(){
		var div = window.parent.document.getElementById("deployAlarm");
		$(div).attr("style","height: 260px");
	});
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})

	function resetForm(theForm){
		theForm.service_name.value = '';
		theForm.tabale_space.value = '';
		theForm.ipaddr.value = '';
		theForm.port.value = '';
		theForm.usrname.value = '';
		theForm.password.value = '';
	}

	function submitRequest(theForm){  
	    theForm.submit();
	}
</script>
</head>
<body>
<s:form action="busitree_saveDataBase.do" id="theForm" method="post" cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.id" id="id"></s:hidden>
<div class="scrollbody">
	<div class="box on">
	
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
            	<tr>
            		<td class="til">服务名:</td>
                 	<td>
						<s:textfield name="theForm.service_name" cssClass="txt" id="service_name"></s:textfield>
                 	</td>
             	</tr>
<%--             	<tr>
	             	<td class="til">命名空间:</td>
	                <td>
						<s:textfield name="theForm.tabale_space" cssClass="txt" id="tabale_space"></s:textfield>
	                </td>
	             </tr>--%>
	             <tr>
	             	<td class="til">IP地址:</td>
	                <td>
	                   <s:textfield name="theForm.ipaddr" cssClass="txt" id="ipaddr"></s:textfield>
					</td>
				 </tr>
				 <tr>
					 <td class="til">端口:</td>
					 <td>
						<s:textfield name="theForm.port" cssClass="txt" id="port"></s:textfield>
					</td>
				 </tr>
				 <tr>
					<td class="til">用户名:</td>
					 <td>
                    	<s:textfield name="theForm.usrname" cssClass="txt" id="usrname"></s:textfield>
					</td>
				</tr>
				<tr>
                    <td class="til">密码:</td>
					<td>
						<input type="password" id="password" name="theForm.password" value='<s:property value="theForm.password"/>' class="txt"/>
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
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
