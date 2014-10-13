<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<script type="text/javascript">
		$(function(){
			$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
			$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		})
		function submitForm(theForm) {
		
				//var NAME_EN =<%=request.getAttribute("NAME_EN")%>;
				//var hostIP =<%=request.getAttribute("hostIP")%>;
				var NAME_EN = theForm.NAME_EN.value;
				var hostIP = theForm.hostIP.value;
				var cpu = theForm.CPU.value;
				var memory = theForm.MEMORY.value;
			if (confirm('确认调整虚拟机吗?')) {
				 $.getJSON("yvm_adjustKVMVirtualInfo.do?cpu="+cpu+"&memory="+memory+"&hostIP="+hostIP+"&NAME_EN="+NAME_EN,{'time':new Date().toString()},function(data){
				 	if(data==null) {
				 		alert('调整成功！');
				 	} else {
				 		alert('调整失败！');
				 	}
				 	window.location.reload();
				 });
			 }
		}
	</script>
</head>
<body>
<s:form action="yvm_adjustKVMVirtualInfo.do" method="post" id="theForm">
<s:hidden name="theForm.NAME_EN" id="NAME_EN"></s:hidden>
<s:hidden name="theForm.hostIP" id="hostIP"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
				   <td class="til">
						所属主机IP
					</td>
					<td>
						<s:property value="theForm.hostIP"  />
					</td>
			 	</tr>
			  	<tr>
				   <td class="til">
						虚拟机名称 
					</td>
					<td>
					    <s:property value="theForm.NAME_ZH"/>               
					</td>
			 	</tr>
			 	<tr>
				   <td class="til">
						映像类型
					</td>
					<td>
					   <s:property value="theForm.IM_TYPE"/>               
					</td>
				</tr>
				
                    <tr>              
                  <td class="til">
						状态
					</td>
					<td>
					    <s:property value="theForm.STATE"/>               
					</td>
                   </tr>
                   <tr>
                   <td class="til">
						IP地址
					</td>
					<td>
					     <s:property value="theForm.IP"/>               
					</td>
                   </tr>
				<%if(((String)request.getAttribute("oper")).equals("0")) { %>
				   <tr>
				    <td class="til">
						CPU(单位：个)
					</td>
					<td>
					    <s:property value="theForm.CPU"/>                
					</td>
				   </tr>
				   <tr>              
                  <td class="til">
						内存(单位：MB)
					</td>
					<td>
					    <s:property value="theForm.MEMORY"/>                 
					</td>
                   </tr>
                   <%} else if (((String)request.getAttribute("oper")).equals("1")) {%>
                   <tr>
				    <td class="til">
						CPU(单位：个)
					</td>
					<td>
					     <s:textfield name="theForm.CPU" id="CPU" cssClass="txt notnull posInt"></s:textfield>
					</td>
				   </tr>
				   <tr>              
                  <td class="til">
						内存(单位：MB)
					</td>
					<td>
					     <s:textfield name="theForm.MEMORY" id="MEMORY" cssClass="txt notnull posInt"></s:textfield>               
					</td>
                   </tr>
                   <tr>
                   <td colspan="4" class="btnCenter">

					<input type="button" class="thickbox btn-style02" value="提交"
						onclick="javascript:submitForm(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
				</tr>
                   <%} %>
                   
			</table>
		</div>
</s:form>
</body>
