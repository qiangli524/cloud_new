<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	$(function(){
		$("#seriousAllocated").click(function(){
			$.dialog({
				id:'viewSeriousAll',
				title:'查看异常主机（已分配）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousHost.do?allo=1'
			});
		});
		
		$("#seriousUnAllocated").click(function(){
			$.dialog({
				id:'viewSeriousUnAllo',
				title:'查看异常主机（未分配）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousHost.do?allo=0'
			});
		});
		
		$("#vmserious").click(function(){
			$.dialog({
				id:'viewVmwareSerious',
				title:'查看异常虚拟机（vmware）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousVM.do?vtype=1'
			});
		});
		
		$("#xenserious").click(function(){
			$.dialog({
				id:'viewXenSerious',
				title:'查看异常虚拟机（xen）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousVM.do?vtype=3'
			});
		});
		
		$("#viewVmwareCluster").click(function(){
			$.dialog({
				id:'viewVmwareCluster',
				title:'查看VMWARE集群',
				width:'800px',
				height:'450px',
				lock:true,
				content:'url:resourcestatistics_viewCluster.do?vtype=1'
			});
		});
		
		$("#viewXenCluster").click(function(){
			$.dialog({
				id:'viewXenCluster',
				title:'查看XEN集群',
				width:'800px',
				height:'450px',
				lock:true,
				content:'url:resourcestatistics_viewCluster.do?vtype=2'
			});
		});
		
		$("#viewOtherCluster").click(function(){
			$.dialog({
				id:'viewXenSerious',
				title:'查看其他集群',
				width:'800px',
				height:'450px',
				lock:true,
				content:'url:resourcestatistics_viewClusterUnallo.do?vtype=7'
			});
		});
		
		$("#viewHost").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
	//			content:'url:resourcestatistics_viewSeriousHost.do?allo=0&unalloclusterList='+unalloclusterList    content:'url:resourcestatistics_viewSeriousHost.do?allo=1&clusterList='+clusterList
				content:'url:resourcestatistics_viewHost.do'
			});
		});
		
		$("#viewVMHost").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do'
			});
		});
		
		$("#viewHostRun").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?status=1'
			});
		});
		
		$("#viewHostStop").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?status=2'
			});
		});
		
		$("#viewX86Host").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?eq_type=4'
			});
		});
		
		$("#viewFrameHost").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?eq_type=5'
			});
		});
		
		$("#viewVmwareVM").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?vtype=1'
			});
		});
		
		$("#viewVmwareRunVM").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?status=1&vtype=1'
			});
		});
		
		$("#viewVmwareStop").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?status=0&vtype=1'
			});
		});
		
		$("#viewXenVM").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?vtype=3'
			});
		});
		
		$("#viewXenRun").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?status=1&vtype=3'
			});
		});
		
		$("#viewXenStop").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'1100px',
				height:'530px',
				lock:true,
			//	zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?status=0&vtype=3'
			});
		});
	});
</script>
</head>
<body>
<s:form action="" method="post"  id="theForm">
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <%-- <tr>
    <td align="left" valign="top" class="panel-datacenter">
       <h2 class="datacenter"><span><font color="orange" size="5"><s:property value="map.dc_all_count" /></font>&nbsp;个</span></h2>
       --%>
  <tbody>
     <tr>
        <td align="left" valign="top" class="panel-datacenter">
       	<h2 class="datacenter dc-tt"><span class="txt">数据中心</span><span class="other"><span class="num"><s:property value="map.dc_all_count" /></span>&nbsp;个</span></h2>
        <div class="clr"></div>
            <div class="box on">
              <dl class="single dc-dl">
              	<dt>
              	
              		<div class="progress-percent pgpercent-orange js_progressPer">
                    	<label>CPU
                    	</label>
                    	<s:if test="@java.lang.Math@round(map.vcpu_allo_count*100*100/map.vcpu_all_count)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.vcpu_allo_count*100*100/map.vcpu_all_count)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                   		<span class="percent"><s:property value="@java.lang.Math@round(map.vcpu_allo_count*100*100/map.vcpu_all_count)/100.0"/>%</span>
                    </div>
              	</dt>
              	<dd>
                                                 总量：<fmt:formatNumber value="${(map.vcpu_all_count)}" pattern="#,###" type="number"/> 核&nbsp;&nbsp;
                                                 已分配：<fmt:formatNumber value="${(map.vcpu_allo_count)}" pattern="#,###" type="number"/> 核&nbsp;&nbsp;
                                                 已使用：<fmt:formatNumber value="${(map.vcpu_used_count)}" pattern="#,###" type="number"/> 核
                </dd>
              	
             </dl>
              <dl class="single dc-dl">
              	<dt>
                	<div class="progress-percent pgpercent-blue js_progressPer">
                		<label>内存</label>
                		<s:if test="@java.lang.Math@round(map.mem_allo_mb*100*100/map.mem_all_mb)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.mem_allo_mb*100*100/map.mem_all_mb)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                        <span class="percent"><s:property value="@java.lang.Math@round(map.mem_allo_mb*100*100/map.mem_all_mb)/100.0"/>%</span>
                    </div>
               	</dt>
               	<dd>
                                                     总量：<fmt:formatNumber value="${(map.mem_all_mb)}" pattern="#,###.##" type="number"/> G&nbsp;&nbsp;
                                                     已分配：<fmt:formatNumber value="${(map.mem_allo_mb)}" pattern="#,###.##" type="number"/> G&nbsp;&nbsp;
                                                     已使用：<fmt:formatNumber value="${(map.mem_used_mb)}" pattern="#,###.##" type="number"/> G
                </dd>
              </dl>
              <dl class="single dc-dl">
              		<dt>
                    	<div class="progress-percent pgpercent-purple js_progressPer">
                        	<label>存储</label>
                        	<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.storage_allo_mb*100*100/map.storage_mount_mb)/100.0"/>%'><b></b></span></span></span>
                        	<span class="percent"><s:property value="@java.lang.Math@round(map.storage_allo_mb*100*100/map.storage_mount_mb)/100.0"/>%</span>
                        </div>
                   	</dt>
              		<dd>
                         	 有效：<fmt:formatNumber value="${(map.storage_valid_mb)}" pattern="#,###.##" type="number"/>T&nbsp;
                         	 已接入: <fmt:formatNumber value="${(map.storage_mount_mb)}" pattern="#,###.##" type="number"/>T&nbsp;
                         	 已分配：<fmt:formatNumber value="${(map.storage_allo_mb)}" pattern="#,###.##" type="number"/>T&nbsp;
                         	 已使用：<fmt:formatNumber value="${(map.storage_used_mb)}" pattern="#,###.##" type="number"/>T
                   	</dd>
              </dl>
              <dl class="single dc-dl">
              	<dt>
                	<div class="progress-percent pgpercent-purple js_progressPer">
                		<label>IP</label>
                        	<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.ip_used_count*100*100/map.ip_all_count)/100.0"/>%'><b></b></span></span></span>
                            <span class="percent"><s:property value="@java.lang.Math@round(map.ip_used_count*100*100/map.ip_all_count)/100.0"/>%</span>
                    </div>
                </dt>
                <dd>
                      	总量：<s:property value="map.ip_all_count"/> 个&nbsp;&nbsp;
                      	已分配：<s:property value="map.ip_used_count"/> 个&nbsp;&nbsp;
                      	未分配：<s:property value="map.ip_all_count-map.ip_used_count"/> 个
                </dd>
              </dl>
              <dl class="single dc-dl">
                <dd>
                	VLAN &nbsp;&nbsp;&nbsp;&nbsp;
                      	总量：<s:property value="map.net_all_count"/> 个&nbsp;&nbsp;
                </dd>
              
              </dl>
            </div>
    </td>
    <td align="left" valign="top" class="panel-resource">
    	<h2 class="clustertitle dc-tt"><span class="txt">集群</span><span class="other"><span class="num"><s:property value="map.cluster_all_count" /></span>&nbsp;个</span></h2>
		 <ul class="dc-list">
         	<li>
            	<i class="vmware"></i><span class="txt">vmware</span><s:if test="map.cluster_vmware_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareCluster"><s:property value="map.cluster_vmware_count"/></a></s:else>
            </li>
            <li>
            	<i class="xen"></i><span class="txt">xen</span><s:if test="map.cluster_xen_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenCluster"><s:property value="map.cluster_xen_count"/></a></s:else>
            </li>
            <li>
            	<i class="cluster"></i><span class="txt">未分配</span><s:if test="map.map.cluster_all_count-map.cluster_xen_count-map.cluster_vmware_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewOtherCluster"><s:property value="map.cluster_all_count-map.cluster_xen_count-map.cluster_vmware_count"/></a></s:else>
           	</li>
           	</br>
            <li>
            	<i class="zhuji"></i><span class="txt">主机总数</span><s:if test="map.host_all_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewHost"><s:property value="map.host_all_count"/></a></s:else>
            </li>
            <li>
            	<i class="vm"></i><span class="txt">虚拟机总数</span><s:if test="map.vm_all_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVMHost"><s:property value="map.vm_all_count"/></a></s:else>
            </li>
         </ul>   
   		
        <h2 class="host dc-tt mgt-15"><span class="txt">已分配主机</span><span class="other"><span class="num"><s:property value="map.host_allocated_count" /></span>&nbsp;个</span></h2>
      	<ul class="dc-list">
        	<li>
            	<i class="running"></i><span class="txt">正在运行</span><s:if test="map.host_run_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewHostRun"><s:property value="map.host_run_count"/></a></s:else>
        	</li>
        	<li>
            	<i class="stop"></i><span class="txt">已停止</span><s:if test="map.host_stop_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewHostStop"><s:property value="map.host_stop_count"/></a></s:else>
        	</li>
        	<li>
           		<i class="alert"></i><span class="txt">异常</span><s:if test="map.host_serious_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="seriousAllocated"><s:property value="map.host_serious_count"/></a></s:else>
         	</li>
         	</br>
        	<li>
              	<i class="x86"></i><span class="txt">X86刀片</span><s:if test="map.host_x86_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewX86Host"><s:property value="map.host_x86_count"/></a></s:else>
         	</li>
         	<li>
               	<i class="jijia"></i><span class="txt">X86PC服务器</span><s:if test="map.host_frame_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewFrameHost"><s:property value="map.host_frame_count"/></a></s:else>
         	</li>
     	</ul>  
	    <h2 class="vmtitle dc-tt mgt-15"><span class="txt">虚拟机</span><span class="other"><span class="num"><s:property value="map.vm_all_count" /></span>&nbsp;个</span></h2>
     	<ul class="dc-list">
           	<li>
              	<i class="vmware"></i><span class="txt">vmware</span><s:if test="map.vm_vmware_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareVM"><s:property value="map.vm_vmware_count"/></a></s:else>
          	</li>
          	<li>
             	<i class="running"></i><span class="txt">正常</span><s:if test="map.vm_vmware_run_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareRunVM"><s:property value="map.vm_vmware_run_count"/></a></s:else>
          	</li>
         	<li>
              	<i class="stop"></i><span class="txt">停止</span><s:if test="map.vm_vmware_stop_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareStop"><s:property value="map.vm_vmware_stop_count"/></a></s:else>
           	</li>
           	</br>
         	<li>
          		<i class="xen"></i><span class="txt">xen</span><s:if test="map.vm_xen_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenVM"><s:property value="map.vm_xen_count"/></a></s:else>
       		</li>
           	<li>
              	<i class="running"></i><span class="txt">正常</span><s:if test="map.vm_xen_run_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenRun"><s:property value="map.vm_xen_run_count"/></a></s:else>
           	</li>
          	<li>
             	<i class="stop"></i><span class="txt">停止</span><s:if test="map.vm_xen_stop_count == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenStop"><s:property value="map.vm_xen_stop_count"/></a></s:else>
         	</li>
         	</br>
           	<li>
               	<i class="vmware-unusual"></i><span class="txt">
               	<s:if test="map.vmware_serious_count == 0">vmware异常<span class="num_zero">0</span></s:if> 
			  <s:else>
				vmware异常
               			<a class="num" href="javascript:;" id="vmserious"><s:property value="map.vmware_serious_count"/></a>
			  </s:else>
					</span>
           	</li>
			<li><i class="xen-unusual"></i>
			<span class="txt"><s:if test="map.xen_serious_count == 0">xen异常<span class="num_zero">0</span></s:if> 
			<s:else>
				xen异常
					<a class="num" href="javascript:;" id="xenserious">
							<s:property	value="map.xen_serious_count" />
					</a>
			</s:else>
			</span> </li>
							</ul>
<%--	    <h2 class="host2 dc-tt mgt-15"><span class="txt">已分配主机</span><span class="other"><span class="num"><s:property value="map.host_unallocated_count" /></span>&nbsp;个</span></h2>    --%>
<%--         <ul class="dc-list">--%>
<%--        	<li>--%>
<%--              	<i class="running"></i><span class="txt">正在运行</span><span class="num purple-num"><font style="font-weight:bold;"><s:property value="map.host_not_allocated_run_count"/></font></span>--%>
<%--           	</li>--%>
<%--           	<li>--%>
<%--              	<i class="stop"></i><span class="txt">已停止</span><span class="num purple-num"><font style="font-weight:bold;"><s:property value="map.host_not_allocated_stop_count"/></font></span>--%>
<%--          	</li>--%>
<%--          	<li>--%>
<%--              	<i class="alert"></i><span class="txt"><a href="javascript:;" id="seriousUnAllocated">异常</a></span>--%>
<%--              	<span class="num purple-num">--%>
<%--              		<font style="font-weight:bold;"><s:property value="map.host_unallocated_count-map.host_not_allocated_run_count-map.host_not_allocated_stop_count"/></font>--%>
<%--              	</span>--%>
<%--          	</li>--%>
<%--          	<li>--%>
<%--              	<i class="x86"></i><span class="txt">X86刀片</span><span class="num purple-num"><font style="font-weight:bold;"><s:property value="map.host_not_allocated_x86_count"/></font></span>--%>
<%--        	</li>--%>
<%--         	<li>--%>
<%--              	<i class="jijia"></i><span class="txt">机架式服务器</span><span class="num purple-num"><font style="font-weight:bold;"><s:property value="map.host_not_allocated_frame_count"/></font></span>--%>
<%--           	</li>--%>
<%--         </ul> --%>
    </td>
  </tr>
</tbody></table>
</div>
</s:form>
</body>