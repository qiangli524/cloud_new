<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
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
	//var connect_id = $("#connid").val();
	var parentHostList = $("#parentHostList").val();
	$("#vmserious").click(function(){
	//	var hostid = $("#hostid").val();
		$.dialog({
			id:'viewVmwareSerious',
			title:'查看异常虚拟机（vmware）',
			width:'1100px',
			height:'530px',
			lock:true,
			content:'url:resourcestatistics_viewSeriousVM.do?vtype=1&parentHostList='+parentHostList
			//content:'url:resourcestatistics_viewSeriousVM.do?vtype=1&hostid='+hostid+'&connect_id='+connect_id
		});
	});

	$("#xenserious").click(function(){
		//var hostid = $("#hostid").val();
		$.dialog({
			id:'viewXenSerious',
			title:'查看异常虚拟机（xen）',
			width:'1100px',
			height:'530px',
			lock:true,
			content:'url:resourcestatistics_viewSeriousVM.do?vtype=3&parentHostList='+parentHostList
			//content:'url:resourcestatistics_viewSeriousVM.do?vtype=3&hostid='+hostid+'&connect_id='+connect_id
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
			content:'url:resourcestatistics_viewVM.do?parentHostList='+parentHostList+'&vtype=1'
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
			content:'url:resourcestatistics_viewVM.do?vtype=1&parentHostList='+parentHostList+'&status=1'
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
			content:'url:resourcestatistics_viewVM.do?vtype=1&parentHostList='+parentHostList+'&status=0'
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
			content:'url:resourcestatistics_viewVM.do?parentHostList='+parentHostList+'&vtype=3'
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
			content:'url:resourcestatistics_viewVM.do?vtype=3&parentHostList='+parentHostList+'&status=1'
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
			content:'url:resourcestatistics_viewVM.do?vtype=3&parentHostList='+parentHostList+'&status=0'
		});
	});
});

</script>
</head>
<body>
<s:form action="" method="post"  id="theForm">
<%--<s:hidden name="hostid" id="hostid"></s:hidden>
<s:hidden name="connect_id" id="connid"></s:hidden>
--%>
<s:hidden name="parentHostList" id="parentHostList"></s:hidden>
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
<tbody>
    <td align="left" valign="top" class="panel-datacenter">
    <h2 class="datacenter dc-tt"><span class="txt">主机</span></h2>
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
                        已使用：<fmt:formatNumber value="${(cpuusedcount)}" pattern="#,###" type="number"/> 核
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
                        总量：：<fmt:formatNumber value="${(srallcount)}" pattern="#,###.##" type="number"/>  T&nbsp;
                        已分配: <fmt:formatNumber value="${(srallocount)}" pattern="#,###.##" type="number"/>  T&nbsp;
                        已使用：<fmt:formatNumber value="${(srusedcount)}" pattern="#,###.##" type="number"/>  T&nbsp;
                    </dd>
                </dl>
         </div>
    </td>
    <td align="left" valign="top" class="panel-resource">
    		<s:if test="vtype == 1"><%--Vmware虚拟化--%>
            <h2 class="vmtitle dc-tt"><span class="txt">VMWARE虚拟机</span><span class="other"><span class="num"><s:property value="vmwarecount" /></span>&nbsp;个</span></h2>
                <ul class="dc-list">
                    <li>
                        <i class="running"></i><span class="txt">正在运行</span><s:if test="vmwareruncount== 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareRunVM"><s:property value="vmwareruncount"/></a></s:else>
                    </li>
                    <li>
                        <i class="stop"></i><span class="txt">已停止</span><s:if test="vmwarestopcount== 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewVmwareStop"><s:property value="vmwarestopcount"/></a></s:else>
                    </li>
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
                </ul>
             </s:if>
             <s:if test="vtype == 2"><%--Xen虚拟化--%>
            <h2 class="vmtitle dc-tt mgt-15"><span class="txt">XEN虚拟机</span><span class="other"><span class="num"><s:property value="xencount" /></span>&nbsp;个</span></h2>
                <ul class="dc-list">
                    <li>
                        <i class="running"></i><span class="txt">正在运行</span><s:if test="xenruncount== 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenRun"><s:property value="xenruncount"/></a></s:else>
                    </li>
                    <li>
                        <i class="stop"></i><span class="txt">已停止</span><s:if test="xenstopcount== 0"><span class="num_zero">0</span></s:if><s:else><a class="num" href="javascript:;" id="viewXenStop"><s:property value="xenstopcount"/></a></s:else>
                    </li>
                    <li>
                         <i class="xen-unusual"></i><span class="txt">
			                <s:if test="xenseriouscount == 0">xen异常
			               		<span class="num_zero">0</span>
			                </s:if> 
							<s:else>xen异常
								<a href="javascript:;" id="xenserious">
				               			<a class="num" href="javascript:;"><s:property value="xenseriouscount"/></a>
			               		</a>
							</s:else>
                    </li>
                </ul>
                </s:if>
    </td>
  </tr>
</tbody></table>
</div>
</s:form>
</body>