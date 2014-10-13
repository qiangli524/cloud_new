<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<style type="text/css">
		div.hidden{
		width:100px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
</style>
	<script type="text/javascript">
	    
		function resetForm(){
			hostForm.eq_name.value = "";
			hostForm.eq_ip.value = "";
		//	hostForm.hasvertual.value = "-1";
			hostForm.typec.value="-1";
		}
		function searchRequest(){
		    hostForm.action = "hadoopHost_queryHostInfoByNode.do";
 		 	hostForm.submit();
		} 
		$(function(){
            $("[name='showIndex']").click(function(){
		         currentEdit=$(this);
		         var id = $(this).attr("indexId");
	    		 $.dialog({
	    			id:'index',
	    			title:'性能',
	    			width: '920px',
	    			height: '620px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
					content: 'url:hadoopHostServer_listHadoopOnlyHostServer.do?entityId='+id+"&isHideKpiList=1"
	    			});
		     });
		});
	</script>
</head>
<body>
	<div class="mainbody">
	<s:form action="" method="post" cssClass="hostForm" id="hostForm">
	<s:hidden name="entityId" id="entityId"></s:hidden>
	<s:hidden name="nodeId" id="nodeId"></s:hidden>
	<s:hidden name="type" id="typer"></s:hidden>
				<div class="pd-20 bgcolor-1">
				<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="vm">主机名称：</label>
							<s:textfield name="hostForm.host_name" cssClass="inpt-1 vm"  maxlength="30"  id="eq_name"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="vm">IP地址：</label>
							<s:textfield name="hostForm.ip"  cssClass="inpt-1 vm"  maxlength="30"  id="eq_ip"></s:textfield>
						</div>
						<div class="filtrate-field">
							<span class="ubtn-1 mgl-20"><input type="button"
								onclick="javascript:searchRequest()" value="查询" />
							</span> <span class="ubtn-2 mgl-20"><input type="button"
								onclick="javascript:resetForm(document.getElementById('hostForm'))"
								value="重置" />
							</span>
						</div>
					</div>
				<div class="mgt-20"></div>
				
						<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
							<thead>
								<tr>
									<th width="10%">
										主机名称
									</th>
									<th width="5%">所属集群</th>
									<th width="5%">
										主机类型
									</th>
<!-- 									<th width="5%">服务类型</th> -->
									<th width="10%">操作系统</th>
									<th width="10%">IP地址</th>
									<th width="5%">CPU(核)</th>
									<th width="5%">内存(G)</th>
									<th width="5%">SWAP(G)</th>
									<th width="5%">本地磁盘(G)</th>
<!-- 									<th width="5%">服务器类型</th> -->
									
									<th width="5%">性能</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultlist" id="theBean">
									<tr>
										<td>
											<s:property value="#theBean.host_name" />
										</td>
										<td>
											<s:if test="#theBean.cluster_name != '' && #theBean.cluster_name != null">
												<s:property value="#theBean.cluster_name"/>
											</s:if>
											<s:else>
												-
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.host_type == 0">
												物理机
											</s:if>
											<s:elseif test="#theBean.host_type == 2">
												虚拟机
											</s:elseif>
											<s:else>
												--
											</s:else>
										</td>
<!-- 										<td> -->
<!-- <%--										0namenode，1datanode，2hmaser，3regionserver，4reducedemager，5nodemanager，6hive--%> -->
<!-- 									     	<%-- <div class="hidden" title='<s:property value="#theBean.service_type"/>'> -->
<!-- 												<s:property value="#theBean.service_type"/> -->
<!-- 					  						</div> --%> -->
<!-- 									     	<s:if test="#theBean.service == 0"> -->
<!-- 												NAMENODE -->
<!-- 											</s:if> -->
<!-- 											<s:elseif test="#theBean.service == 1"> -->
<!-- 												DATANODE -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service == 2"> -->
<!-- 											    HMASTER -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service == 3"> -->
<!-- 												REGIONSERVER -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service == 4"> -->
<!-- 												REDUCEDEMAGER -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service == 5"> -->
<!-- 												NODEMANAGER -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service == 6"> -->
<!-- 												HIVE -->
<!-- 											</s:elseif> -->
<!-- 											<s:else> -->
<!-- 												-- -->
<!-- 											</s:else> -->
<!-- 										</td> -->
										<td>
											<div class="hidden" title='<s:property value="os"/>'>
												<s:property value = "#theBean.os"/>
					  						</div>
										</td>
										<td>
											<s:property value="#theBean.ip"/>
										</td>
										<td>
											<fmt:formatNumber type="currency" pattern="0.00" value="${theBean.cpu_ghz}"></fmt:formatNumber>
<!-- 											<s:property value="#theBean.cpu_ghz"/> -->
										</td>
										<td>
											<fmt:formatNumber type="currency" pattern="0.00" value="${theBean.mem_size/1024}"></fmt:formatNumber>
										<%-- 	<s:property value="#theBean.mem_size"/> --%>
										</td>
										<td>
											<fmt:formatNumber type="currency" pattern="0.00" value="${theBean.swap_size/1024/1024}"></fmt:formatNumber>
											<%-- <s:property value="#theBean.swap_size"/> --%>
										</td>
										<td>
											<fmt:formatNumber type="currency" pattern="0.00" value="${theBean.local_disk_size}"></fmt:formatNumber>
<!-- 											<s:property value="#theBean.local_disk_size"/> -->
										</td>
<!-- 										<td> -->
<!-- <%--										（0x86服务器、1机架服务器、2vmware虚拟机、3xen虚拟机、4、kvm虚拟机、5其它）--%> -->
<!-- 											<s:if test="#theBean.service_type == 0"> -->
<!-- 												x86服务器 -->
<!-- 											</s:if> -->
<!-- 											<s:elseif test="#theBean.service_type==1"> -->
<!-- 												机架服务器	 -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service_type==2"> -->
<!-- 												VMWARE	 -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service_type==3"> -->
<!-- 												XENSERVER -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service_type==4"> -->
<!-- 												KVM -->
<!-- 											</s:elseif> -->
<!-- 											<s:else> -->
<!-- 												其它 -->
<!-- 											</s:else> -->
<!-- 										</td> -->
										<td>
											<a href="javascript:;" name="showIndex" indexId='<s:property value="#theBean.id"/>' >性能</a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pages mgb-10">
						<!-- 分页 -->
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
		</div>
	</s:form>
</body>
