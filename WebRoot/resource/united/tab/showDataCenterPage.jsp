<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
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
	//	var centerid = $("#centerid").val();
	//	var connect_id = $("#connid").val();
		var clusterList = $("#clusterList").val();
		var unalloclusterList = $("#unalloclusterList").val();
		var vtype = $("#vtype").val();
		var htype = "";
		var xtype = "";
		if( vtype=="1" ){ htype=4;xtype=1; }
		if( vtype=="2" ){ htype=3;xtype=3;}
		if( vtype=="7" ){ htype=0; }
		
		$("#seriousAllocated").click(function(){
			$.dialog({
				id:'viewSeriousAll',
				title:'查看异常主机（已分配）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousHost.do?allo=1&clusterList='+clusterList+'&vtype='+htype
		//		content:'url:resourcestatistics_viewSeriousHost.do?allo=1&centerid='+centerid+'&connect_id='+connect_id
			});
		});
		
		$("#seriousUnAllocated").click(function(){
			$.dialog({
				id:'viewSeriousUnAllo',
				title:'查看异常主机（未分配）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousHost.do?allo=0&unalloclusterList='+unalloclusterList
		//		content:'url:resourcestatistics_viewSeriousHost.do?allo=0&centerid='+centerid+'&connect_id='+connect_id
			});
		});
		
		$("#vmserious").click(function(){
			$.dialog({
				id:'viewVmwareSerious',
				title:'查看异常虚拟机（vmware）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousVM.do?vtype=1&clusterList='+clusterList
			//	content:'url:resourcestatistics_viewSeriousVM.do?vtype=1&centerid='+centerid+'&connect_id='+connect_id
			});
		});
		
		$("#xenserious").click(function(){
			$.dialog({
				id:'viewXenSerious',
				title:'查看异常虚拟机（xen）',
				width:'1100px',
				height:'530px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousVM.do?vtype=3&clusterList='+clusterList
			//	content:'url:resourcestatistics_viewSeriousVM.do?vtype=3&centerid='+centerid+'&connect_id='+connect_id
			});
		});
		


		$("#viewVmwareCluster").click(function(){
			$.dialog({
				id:'viewVmwareCluster',
				title:'查看VMWARE集群',
				width:'800px',
				height:'450px',
				lock:true,
				content:'url:resourcestatistics_viewCluster.do?vtype=1&clusterList='+clusterList
			});
		});
		
		$("#viewXenCluster").click(function(){
			$.dialog({
				id:'viewXenCluster',
				title:'查看XEN集群',
				width:'800px',
				height:'550px',
				lock:true,
				content:'url:resourcestatistics_viewCluster.do?vtype=3&clusterList='+clusterList
			});
		});
		
		$("#viewOtherCluster").click(function(){
			$.dialog({
				id:'viewXenSerious',
				title:'查看其他集群',
				width:'800px',
				height:'550px',
				lock:true,
				content:'url:resourcestatistics_viewClusterUnallo.do?vtype=7&unalloclusterList='+unalloclusterList
			});
		});
		
		$("#viewHost").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'1100px',
				height:'530px',
				lock:true,
		//		zIndex:1975,
	//			content:'url:resourcestatistics_viewSeriousHost.do?allo=0&unalloclusterList='+unalloclusterList    content:'url:resourcestatistics_viewSeriousHost.do?allo=1&clusterList='+clusterList
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&vtype='+htype
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
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&vtype='+xtype
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
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&status=1'+'&vtype='+htype
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
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&status=2'+'&vtype='+htype
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
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&eq_type=4'+'&vtype='+htype
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
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&eq_type=5'+'&vtype='+htype
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
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&vtype=1'
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
				content:'url:resourcestatistics_viewVM.do?vtype=1&clusterList='+clusterList+'&status=1'
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
				content:'url:resourcestatistics_viewVM.do?vtype=1&clusterList='+clusterList+'&status=0'
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
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&vtype=3'
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
				content:'url:resourcestatistics_viewVM.do?vtype=3&clusterList='+clusterList+'&status=1'
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
				content:'url:resourcestatistics_viewVM.do?vtype=3&clusterList='+clusterList+'&status=0'
			});
		});
	});
</script>
</head>
<body>
<s:form action="" method="post"  id="theForm">
<%--<s:hidden name="centerid" id="centerid"></s:hidden>
<s:hidden name="connect_id" id="connid"></s:hidden>
--%>
<s:hidden name="vtype" id="vtype"></s:hidden>
<s:hidden name="clusterList" id="clusterList"></s:hidden>
<s:hidden name="unalloclusterList" id="unalloclusterList"></s:hidden>
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
    <tbody>
    <tr>
        <td align="left" valign="top" class="panel-datacenter">
        <h2 class="datacenter dc-tt"><span class="txt">数据中心</span></h2>
        <div class="clr"></div>
            <div class="box on">
                <dl class="single dc-dl">
                    <dt>
                    <div class="progress-percent pgpercent-orange js_progressPer">
                        <label>CPU</label>
                        <s:if test="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                        <span class="percent"><s:property value="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0"/>%</span>
                    </div>
                    </dt>
                    <dd>
                        总量：<fmt:formatNumber value="${(cpuallcount)}" pattern="#,###" type="number"/> 核&nbsp;&nbsp;
                        已分配：<fmt:formatNumber value="${(cpuallocount)}" pattern="#,###" type="number"/> 核&nbsp;&nbsp;
                        已使用：<fmt:formatNumber value="${(cpuallocount)}" pattern="#,###" type="number"/>  核
                    </dd>
              </dl>
              <dl class="single dc-dl">
                  <dt>
                  <div class="progress-percent pgpercent-blue js_progressPer">
                      <label>内存</label>
                      <s:if test="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                      <span class="percent"><s:property value="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0"/>%</span>
                  </div>
                  </dt>
                  <dd>
                      总量：<fmt:formatNumber value="${(memallcount)}" pattern="#,###.##" type="number"/>  G&nbsp;&nbsp;
                      已分配：<fmt:formatNumber value="${(memallocount)}" pattern="#,###.##" type="number"/> G&nbsp;&nbsp;
                      已使用：<fmt:formatNumber value="${(memusedcount)}" pattern="#,###.##" type="number"/> G
                  </dd>
              </dl>
              <dl class="single dc-dl">
                  <dt>
                  <div class="progress-percent pgpercent-purple js_progressPer">
                      <label>存储</label>
                      <span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0"/>%'><b></b></span></span></span>
                      <span class="percent"><s:property value="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0"/>%</span>
                  </div>
                  </dt>
                  <dd>
                      总量：：<fmt:formatNumber value="${(srallcount)}" pattern="#,###.##" type="number"/>T&nbsp;
                      已分配: <fmt:formatNumber value="${(srallocount)}" pattern="#,###.##" type="number"/> T&nbsp;
                      已使用：<fmt:formatNumber value="${(srusedcount)}" pattern="#,###.##" type="number"/> T&nbsp;
                  </dd>
              </dl>
            </div>
    </td>
    <td align="left" valign="top" class="panel-resource">
    	<h2 class="clustertitle dc-tt"><span class="txt">集群</span><span class="other"><span class="num"><s:property value="clucount" /></span>&nbsp;个</span></h2>
        <ul class="dc-list">
       		<s:if test="vtype == 1"><%--VMware虚拟化--%>
            <li>
                <i class="vmware"></i><span class="txt">vmware</span><s:if test="vmwareclucount== 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareCluster"><s:property value="vmwareclucount"/></a></s:else>
            </li>
            </s:if>
            <s:if test="vtype == 2"><%--Xen虚拟化--%>
            <li>
                <i class="xen"></i><span class="txt">xen</span><s:if test="xenclucount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenCluster"><s:property value="xenclucount"/></a></s:else>
            </li>
            </s:if>
             <s:if test="vtype == 7"><%--预留--%>
            <li>
                <i class="cluster"></i><span class="txt">未分配</span><s:if test="clucount-xenclucount-vmwareclucount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewOtherCluster"><s:property value="clucount-xenclucount-vmwareclucount"/></a></s:else>
            </li>
            </s:if>
            <li>
                <i class="zhuji"></i><span class="txt">主机总数</span><s:if test="hostcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewHost"><s:property value="hostcount"/></a></s:else>
            </li>
            <li>
                <i class="vm"></i><span class="txt">虚拟机总数</span><s:if test="vmcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVMHost"><s:property value="vmcount"/></a></s:else>
            </li>
        </ul>




    <%--    	<h2 class="clustertitle"><span><font color="orange" size="5"><s:property value="clucount" /></font>&nbsp;个</span></h2>--%>
<%--        <s:if test="vtype==7">--%>
<%--	        <h2 class="host2"><span><font color="orange" size="5"><s:property value="hostcount" /></font>&nbsp;个</span></h2>--%>
<%--        </s:if>--%>
<%--        <s:else>--%>
<%--	        <h2 class="host"><span><font color="orange" size="5"><s:property value="hostcount" /></font>&nbsp;个</span></h2>--%>
<%--        </s:else>--%>
        
        <h2 class="host dc-tt mgt-15"><span class="txt">已分配主机</span><span class="other"><span class="num"><s:property value="hostruncount"/></span>&nbsp;个</span></h2>
        <ul class="dc-list">
            <li>
                <i class="running"></i><span class="txt">正在运行</span><s:if test="hostruncount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewHostRun"><s:property value="hostruncount"/></a></s:else>
            </li>
            <li>
                <i class="stop"></i><span class="txt">已停止</span><s:if test="hoststopcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewHostStop"><s:property value="hoststopcount"/></a></s:else>
            </li>
            <li>
                <i class="alert"></i><span class="txt">异常</span>
                <s:if test="vtype==7">
                            <s:property value="hostcount-hostruncount-hoststopcount"/>
                </s:if>
                <s:else>
                	<s:if test="hostseriouscount== 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="seriousAllocated">
                        	<s:property value="hostseriouscount"/></a></s:else>
                </s:else>
            </li>
            </br>
            <li>
                <i class="x86"></i><span class="txt">X86刀片</span><s:if test="x86hostcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewX86Host"><s:property value="x86hostcount"/></a></s:else>
            </li>
            <li>
                <i class="jijia"></i><span class="txt">X86PC服务器</span><s:if test="framehostcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" name="viewFrameHost" id="viewFrameHost"><s:property value="framehostcount"/></a></s:else>
            </li>
        </ul>


	    <h2 class="vmtitle dc-tt mgt-15"><span class="txt">虚拟机</span><span class="other"><span class="num"><s:property value="vmcount" /></span>&nbsp;个</span></h2>
        <ul class="dc-list">
          <s:if test="vtype == 1"><%--VMware虚拟化--%>
            <li>
                <i class="vmware"></i><span class="txt">vmware</span><s:if test="vmwarecount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareVM"><s:property value="vmwarecount"/></a></s:else>
            </li>
            <li>
                <i class="running"></i><span class="txt">正常</span><s:if test="vmwareruncount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareRunVM"><s:property value="vmwareruncount"/></a></s:else>
            </li>
            <li>
                <i class="stop"></i><span class="txt">停止</span><s:if test="vmwarestopcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareStop"><s:property value="vmwarestopcount"/></a></s:else>
            </li>
            </br>
             </s:if>
             <s:if test="vtype == 2"><%--Xen虚拟化--%>
            <li>
                <i class="xen"></i><span class="txt">xen</span><s:if test="xencount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenVM"><s:property value="xencount"/></a></s:else>
            </li>
            <li>
                <i class="running"></i><span class="txt">正常</span><s:if test="xenruncount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenRun"><s:property value="xenruncount"/></a></s:else>
            </li>
            <li>
                <i class="stop"></i><span class="txt">停止</span><s:if test="xenstopcount == 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenStop"><s:property value="xenstopcount"/></a></s:else>
            </li>
            </br>
            </s:if>
            <s:if test="vtype == 1"><%--VMware虚拟化--%>
            <li>
                <i class="vmware-unusual"></i><span class="txt"> 
                <s:if test="vmwareseriouscount == 0">vmware异常
                	<span class="num_zero">0</span>
                </s:if> 
			  <s:else>vmware异常
               		<a class="num" href="javascript:;" id="vmserious"><s:property value="vmwareseriouscount"/></a>
			  </s:else>
			  </span>
            </li>
            </s:if>
            <s:if test="vtype == 2"><%--Xen虚拟化--%>
            <li>
                <i class="xen-unusual"></i><span class="txt">
                <s:if test="xenseriouscount == 0">xen异常
                	<span class="num_zero">0</span>
                </s:if> 
				<s:else>xen异常
					<a href="javascript:;" id="xenserious">
               			<a class="num" href="javascript:;" id="xenserious"><s:property value="xenseriouscount"/></a>
               		</a>
				</s:else>
            </li>
            </s:if>
        </ul>
	    
<%--        <h2 class="vmtitle"><span><font color="orange" size="5"><s:property value="vmcount" /></font>&nbsp;个</span></h2>--%>
    </td>
  </tr>
</tbody></table>
</div>
</s:form>
</body>