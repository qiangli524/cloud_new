<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
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

		$("#console1").click(function(){
			window.parent.virtual_console2(pool_uuid,host_uuid,entity_id,0);
			});
		$(function(){
			$("#xenTool").click(function(){
				mask('正在装载XenTool工具....','0.5','50px');
				$.getJSON("xen_loadToolForDVD.do?poolUuid="+pool_uuid+"&vmUuid="+vm_uuid,{'time':new Date().toString()}, function(data){
	    			if(data.responseCode==1){
	    			     removeMask();
	    			     alert("XenTool已经装载进光驱，请手动安装XenTool!");
	    			}else{
	    				removeMask();
	    				alert("装载失败!");
	    			}
	    		});
				});
			});
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
				<s:if test="theForm.TYPE=='27'">
				<tr>
					<td class="til" align="left">
						虚拟机状态
					</td>
					<td align="left">
						<s:if test="theForm.STATE=='Halted'">已关闭</s:if>
						<s:elseif test="theForm.STATE==Paused">暂停</s:elseif>
						<s:elseif test="theForm.STATE=='Running'">正在运行</s:elseif>
						<s:elseif test="theForm.STATE=='Suspended'">已挂起</s:elseif>
					</td>
				</tr> 
				</s:if>
				<s:if test="theForm.STATE=='Running'">
				<tr id="virtual_state">
					<td class="til" align="left">
						虚拟化状态
					</td>
					<td align="left">
						<s:if test="theForm.virtual_state=='true' && theForm.STATE=='Running'">已优化</s:if>
						<s:elseif test="theForm.STATE=='Running' && theForm.virtual_state=='false'"><s:a href="javascript:;" style="color: red;" id="xenTool">未安装XenServer Tools</s:a></s:elseif>
					</td>
				</tr> 
				</s:if>
			<%if(((String)request.getAttribute("oper")).equals("0")){ %>
			<s:if test="theForm.TYPE=='27'">
				<tr id="virtual_console">
					<td class="til" align="left">
						控制台
					</td>
					<td align="left">
						<s:if test="theForm.STATE=='Running'" ><a href="javascript:;" id="console1"  style="color: black;font-weight: normal;">启动控制台</a></s:if>
						<s:else>请先开启虚拟机</s:else>
					</td>
				</tr> 
			</s:if>
				<tr>
					<td class="til" align="left">
						cpu个数
					</td>
					<td align="left">
					
						<s:property value="theForm.CPU" />
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						内存(单位:M)
					</td>
					<td align="left">
						<s:property value="theForm.MEMORY"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left"> 
						存储(单位:G) 
					</td>
					<td align="left">
						<s:property value="theForm.STORAGE"/>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						IP 地址
					</td>
					<td align="left">
					<s:if test="theForm.IP==null ||theForm.IP==''">无</s:if>
					<s:else><s:property value="theForm.IP"/></s:else>
					</td>
				</tr> 
				<%} else{%>
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
				<% }%> 
				
			</table>
		</div>
</s:form>
</body>
