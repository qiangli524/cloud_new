<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
		var entity_id =<%=request.getAttribute("entity_id")%>;
		var pool_uuid ='<%=request.getAttribute("pool_uuid")%>';
		var host_uuid ='<%=request.getAttribute("host_uuid")%>';
	    var vm_uuid ='<%=request.getAttribute("vm_uuid")%>';
		function submitForm(theForm) {
				var cpuMax = theForm.CPU.value;
				var	cpu = theForm.CURRENTCPU.value;
				var dynamicMax = theForm.dynamicMax.value;
				var dynamicMin = theForm.dynamicMin.value;
				var staticMax = theForm.staticMax.value;
				var staticMin = theForm.staticMin.value;
				var storage = 0;
				if(cpu>cpuMax){
					alert("cpu的使用个数不能大于最大个数");
					return false;
				}
				if(storage=='' ||storage==null){
					storage='';
				}
			if (confirm('确认调整虚拟机吗?')) {
				 $.getJSON("xen_adjustVM.do?cpu="+cpu+"&cpuMax="+cpuMax+"&dynamicMax="+dynamicMax
				 +"&dynamicMin="+dynamicMin+"&staticMax="+staticMax+"&staticMin="+staticMin+"&storage="+storage
				 +"&entity_id="+entity_id+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid,{'time':new Date().toString()},function(data){
				 var result = data.responseCode;
				 	if(result==1) {
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
<s:form action="xen_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="20%" align="left"> 
						虚拟机名称
					</td>
					<td  align="left">
						<s:property value="theForm.NAME_EN" />
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						描述
					</td>
					<td align="left">
						<s:if test="theForm.DESC==null||theForm.DESC==''">暂无</s:if>
						<s:else><s:property value="theForm.DESC" /></s:else>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						操作系统
					</td>
					<td align="left">
						<s:if test="theForm.osType==null ||theForm.osType==''">暂无</s:if>
						<s:else>
						<s:property value="theForm.osType"/>
						</s:else>
					</td>
				</tr> 
					<tr>
					<td class="til" align="left">
						cpu最大个数
					</td>
					<td align="left">
					
						<s:textfield name="theForm.CPU" id="CPU" cssStyle="width:70px;   height:15px;"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						cpu实际使用个数
					</td>
					<td align="left">
					
						<s:textfield name="theForm.CURRENTCPU" id="CURRENTCPU" cssStyle="width:70px;   height:15px;"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						内存(单位:M)
					</td>
					<td align="left">
						<s:textfield name="theForm.staticMin" id="staticMin" cssStyle="width:50px;   height:15px;"/>(静态最小)
						<=<s:textfield name="theForm.dynamicMin" id="dynamicMin"  cssStyle="width:50px;   height:15px;"/>(动态最小)
						<=<s:textfield name="theForm.dynamicMax" id="dynamicMax"  cssStyle="width:50px;   height:15px;"/>(动态最大)
						<=<s:textfield name="theForm.staticMax" id="staticMax"  cssStyle="width:50px;   height:15px;"/>(静态最大)
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
				
			</table>
		</div>
</s:form>
</body>
