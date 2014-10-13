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
		var unalloclusterList = $("#unalloclusterList").val();
		var clusterList = $("#clusterList").val();
		$("#seriousAllocated").click(function(){
			$.dialog({
				id:'viewSeriousAll',
				title:'查看异常主机（已分配）',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousHost.do?allo=1&clusterList='+clusterList
			});
		});
		
		$("#seriousUnAllocated").click(function(){
			$.dialog({
				id:'viewSeriousUnAllo',
				title:'查看异常主机（未分配）',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousHost.do?allo=0&unalloclusterList='+unalloclusterList
			});
		});
		
		$("#vmserious").click(function(){
			$.dialog({
				id:'viewVmwareSerious',
				title:'查看异常虚拟机（vmware）',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousVM.do?vtype=1&clusterList='+clusterList
			});
		});
		
		$("#xenserious").click(function(){
			$.dialog({
				id:'viewXenSerious',
				title:'查看异常虚拟机（xen）',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewSeriousVM.do?vtype=3&clusterList='+clusterList
			});
		});
		
		$("#viewVmwareCluster").click(function(){
			$.dialog({
				id:'viewVmwareCluster',
				title:'查看VMWARE集群',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewCluster.do?vtype=1&clusterList='+clusterList
			});
		});
		
		$("#viewXenCluster").click(function(){
			$.dialog({
				id:'viewXenCluster',
				title:'查看XEN集群',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewCluster.do?vtype=3&clusterList='+clusterList
			});
		});
		
		$("#viewOtherCluster").click(function(){
			$.dialog({
				id:'viewXenSerious',
				title:'查看其他集群',
				width:'800px',
				height:'750px',
				lock:true,
				content:'url:resourcestatistics_viewClusterUnallo.do?vtype=7&unalloclusterList='+unalloclusterList
			});
		});
		
		$("#viewHost").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'800px',
				height:'750px',
				lock:true,
				zIndex:1975,
	//			content:'url:resourcestatistics_viewSeriousHost.do?allo=0&unalloclusterList='+unalloclusterList    content:'url:resourcestatistics_viewSeriousHost.do?allo=1&clusterList='+clusterList
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList
			});
		});
		
		$("#viewVMHost").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'750px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList
			});
		});
		
		$("#viewHostRun").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'800px',
				height:'750px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&status=1'
			});
		});
		
		$("#viewHostStop").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'800px',
				height:'750px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&status=2'
			});
		});
		
		$("#viewX86Host").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'800px',
				height:'750px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&eq_type=4'
			});
		});
		
		$("#viewFrameHost").click(function(){
			$.dialog({
				id:'viewHost',
				title:'主机',
				width:'800px',
				height:'750px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewHost.do?clusterList='+clusterList+'&eq_type=5'
			});
		});
		
		$("#viewVmwareVM").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'550px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&vtype=1'
			});
		});
		
		$("#viewVmwareRunVM").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'550px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&status=1'
			});
		});
		
		$("#viewVmwareStop").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'550px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&status=0'
			});
		});
		
		$("#viewXenVM").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'550px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&vtype=3'
			});
		});
		
		$("#viewXenRun").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'550px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&status=1'
			});
		});
		
		$("#viewXenStop").click(function(){
			$.dialog({
				id:'viesVMs',
				title:'虚拟机',
				width:'800px',
				height:'550px',
				lock:true,
				zIndex:1975,
				content:'url:resourcestatistics_viewVM.do?clusterList='+clusterList+'&status=0'
			});
		});
	});
</script>
</head>
<body>
<s:form action="" method="post"  id="theForm">
<s:hidden name="unalloclusterList" id="unalloclusterList"></s:hidden>
<s:hidden name="clusterList" id="clusterList"></s:hidden>
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-datacenter">
       <h2 class="datacenter"><span><font color="orange" size="5"><s:property value="centercount" /></font>&nbsp;个</span></h2>
        <div class="clr"></div>
            <div class="box on">
              <dl class="single">
              	<dt>
              			CPU：总量：<fmt:formatNumber value="${(cpuallcount)}" pattern="#,###" type="number"/> 核
              			已分配: <fmt:formatNumber value="${(cpuallocount)}" pattern="#,###" type="number"/> 核
              			</br>
              			已使用: <fmt:formatNumber value="${(cpuusedcount)}" pattern="#,###" type="number"/> 核
              	</dt>
              	<dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
<%--                        <td><div style="float:left;" class="cpu"></div></td>--%>
                        <td>
                        	<s:if test="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0 >= 75">
	                        	<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0"/>%'></b></div>
                        	</s:if>
                        	<s:elseif test="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0 >= 50">
	                        	<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0"/>%'></b></div>
                        	</s:elseif>
                        	<s:else>
	                        	<div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0"/>%'></b></div>
                        	</s:else>
                        </td>
                        <td>
                        	<s:property value="@java.lang.Math@round(cpuallocount*100*100/cpuallcount)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <div class="clr" style=" height: 10px;"></div>
              <dl class="single">
              	<dt>
              		内存：总量：<fmt:formatNumber value="${(memallcount)}" pattern="#,###.##" type="number"/> G
              		已分配: <fmt:formatNumber value="${(memallocount)}" pattern="#,###.##" type="number"/> G
              		</br>
              		已使用: <fmt:formatNumber value="${(memusedcount)}" pattern="#,###.##" type="number"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><div style="float:left;" class="mem"></div></td>
                        <td>
                        	<s:if test="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0 >= 75">
                        		<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0"/>%'></b></div>
                        	</s:if>
                        	<s:elseif test="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0 >= 50">
                        		<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0"/>%'></b></div>
                        	</s:elseif>
                        	<s:else>
                        		<div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0"/>%'></b></div>
                        	</s:else>
                        </td>
                        <td>
                        	<s:property value="@java.lang.Math@round(memallocount*100*100/memallcount)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <div class="clr" style=" height: 10px;"></div>
              <dl class="single">
              	<dt>
              		存储：总量：<fmt:formatNumber value="${(srallcount)}" pattern="#,###.##" type="number"/> T
              		已分配: <fmt:formatNumber value="${(srallocount)}" pattern="#,###.##" type="number"/> T
              		</br>
              		已使用：<fmt:formatNumber value="${(srusedcount)}" pattern="#,###.##" type="number"/> T
              	</dt> 
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><div style="float:left;" class="storage"></div></td>
                        <td>
                        	<s:if test="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0 >= 75">
	                        	<div class="percentage"><b class="red" style='width:<s:property value="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0"/>%'></b></div>
                        	</s:if>
                        	<s:elseif test="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0 >= 50">
	                        	<div class="percentage"><b class="ora" style='width:<s:property value="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0"/>%'></b></div>
                        	</s:elseif>
                        	<s:else>
	                        	<div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0"/>%'></b></div>
                        	</s:else>
                        </td>
                        <td>
                        	<s:property value="@java.lang.Math@round(srallocount*100*100/srallcount)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
    </td>
    <td align="left" valign="top" class="panel-resource">
        	<h2 class="clustertitle dc-tt"><span class="txt">集群</span><span class="other"><span class="num"><s:property value="clucount" /></span>&nbsp;个</span></h2>
<%--    	<h2 class="clustertitle"><span><font color="orange" size="5"><s:property value="clucount" /></font>&nbsp;个</span></h2>--%>
        <table width="450" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
   			<tr>
   				<td align="left" width="38%">
   					<div style="float:left;" class="vmware"></div><div>vmware:<a href="javascript:;" id="viewVmwareCluster"><font color="blue" size="4"><s:property value="vmwareclucount"/></font></a>&nbsp;个</div>
   				</td>
   				<td align="left"  width="38%">
   					<div style="float:left;" class="xen"></div><div>xen:<a href="javascript:;" id="viewXenCluster"><font color="blue" size="4"><s:property value="xenclucount"/></font></a>&nbsp;个 </div>
   				</td>
   				<td align="left">
   					<div style="float:left;" class="cluster"></div><div>未分配:<a href="javascript:;" id="viewOtherCluster"><font color="blue" size="4"><s:property value="unalloclucount"/></font></a>&nbsp;个  </div>
   				</td>
   			</tr>
   			    <tr>
      				<td align="left">
      					<div style="float:left;" class="zhuji"></div><div>主机总数:<a href="javascript:;" id="viewHost"><font color="blue" size="4"><s:property value="hostcount"/></font></a>&nbsp;个</div> 
      				</td>
      				<td align="left">
      					<div style="float:left;" class="vm"></div><div>虚拟机总数:<a href="javascript:;" id="viewVMHost"><font color="blue" size="4"><s:property value="vmcount"/></font></a>&nbsp;个 </div>
          			</td>
      			</tr>
        </table>  
        <div class="clr" style=" height: 8px;"></div>
        <h2 class="host dc-tt mgt-15"><span class="txt">已分配主机</span><span class="other"><span class="num"><s:property value="hostallo" /></span>&nbsp;个</span></h2>
<%--        <h2 class="host"><span><font color="orange" size="5"><s:property value="hostallo" /></font>&nbsp;个</span></h2>--%>
	        <table width="450" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
				<tr>
					<td align="left"  width="38%">
						<div style="float:left;" class="running"></div><div>正在运行:<a href="javascript:;" id="viewHostRun"><font color="blue" size="4"><s:property value="hostruncount"/></font></a>&nbsp;个 </div>
					</td>
					<td align="left"  width="38%">
						<div style="float:left;" class="stop"></div><div>已停止:<a href="javascript:;" id="viewHostStop"><font color="blue" size="4"><s:property value="hoststopcount"/></font></a>&nbsp;个  </div>
     				</td>
     				<td align="left">
     					<div style="float:left;" class="alert"></div><div>异常:
     						<a href="javascript:;" id="seriousAllocated">
     							<u><font color="blue" size="4"><s:property value="hostseriouscount"/></font></u>
     						</a>&nbsp;个
     					</div>
     				</td>
				</tr>
				<tr>
   					<td align="left">
   						<div style="float:left;" class="x86"></div><div>X86刀片:<a href="javascript:;" id="viewX86Host"><font color="blue" size="4"><s:property value="x86hostcount"/></font></a>&nbsp;个 </div>
       				</td>
      				<td align="left">
      					<div style="float:left;" class="jijia"></div><div>机架式服务器:<a href="javascript:;" name="viewFrameHost"><font color="blue" size="4"><s:property value="framehostcount"/></font></a>&nbsp;个 </div>
       				</td>
   				</tr>
	        </table> 
	    <div class="clr" style=" height: 8px;"></div>
	    <h2 class="vmtitle dc-tt mgt-15"><span class="txt">虚拟机</span><span class="other"><span class="num"><s:property value="vmcount" /></span>&nbsp;个</span></h2>
<%--        <h2 class="vmtitle"><span><font color="orange" size="5"><s:property value="vmcount" /></font>&nbsp;个</span></h2>--%>
	        <table width="450" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
		      			<tr>
		      				<td align="left"  width="38%"><div style="float:left;" class="vmware"></div><div>vmware:<a href="javascript:;" id="viewVmwareVM"><font color="blue" size="4"><s:property value="vmwarecount"/></font></a>&nbsp;个 </div>
				            </td>
				            <td align="left"  width="38%"><div style="float:left;" class="running"></div><div>正常:<a href="javascript:;" id="viewVmwareRunVM"><font color="blue" size="4"><s:property value="vmwareruncount"/></font></a>&nbsp;个  </div>
				           	</td>
				            <td align="left"><div style="float:left;" class="stop"></div><div>停止:<a href="javascript:;" id="viewVmwareStop"><font color="blue" size="4"><s:property value="vmwarestopcount"/></font></a>&nbsp;个 </div>
				            </td>
		      			</tr>
		      			<tr>
	          				<td align="left"><div style="float:left;" class="xen"></div><div>xen:<a href="javascript:;" id="viewXenVM"><font color="blue" size="4"><s:property value="xencount"/></font></a>&nbsp;个 </div>
				            </td>
				            <td align="left"><div style="float:left;" class="running"></div><div>正常:<a href="javascript:;" id="viewXenRun"><font color="blue" size="4"><s:property value="xenruncount"/></font></a>&nbsp;个 </div>
				             </td>
				            <td align="left"><div style="float:left;" class="stop"></div><div>停止:<a href="javascript:;" id="viewXenStop"><font color="blue" size="4"><s:property value="xenstopcount"/></font></a>&nbsp;个 </div>
				             </td>
	          			</tr>
	          			<tr>
					            <td><div style="float:left;" class="cancle"></div><div>
					            	vmware异常: <a href="javascript:;" id="vmserious">
					            		<u><font color="blue" size="4"><s:property value="vmwareseriouscount"/></font></u>
					            	</a>
		      						&nbsp;个</div>
					             </td>
					            <td><div style="float:left;" class="cancle"></div><div>
					            	xen异常: 
					            	<a href="javascript:;" id="xenserious">
					            		<u><font color="blue" size="4"><s:property value="xenseriouscount"/></font></u>
					            	</a>
		      						&nbsp;个
					            	</div>
					            </td>
	          				</tr>
	        </table> 
<%--	         <div class="clr" style=" height: 8px;"></div>--%>
<%--         <h2 class="host2"><span><font color="orange" size="5"><s:property value="hostunallo" /></font>&nbsp;个</span></h2>--%>
<%--	        <table width="450" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">--%>
<%--	      				<tr>--%>
<%--		      					<td align="left"  width="38%">--%>
<%--		      						<div style="float:left;" class="running"></div><div>正在运行:<font color="blue" size="4"><s:property value="hostunalloruncount"/></font>&nbsp;个 </div>--%>
<%--		      					</td>--%>
<%--		      					<td align="left" width="38%">--%>
<%--		      						<div style="float:left;" class="stop"></div><div>已停止: <font color="blue" size="4"><s:property value="hostunallostopcount"/></font>&nbsp;个 </div>--%>
<%--	            				</td>--%>
<%----%>
<%--	            				<td align="left">--%>
<%--	            					<div style="float:left;" class="alert"></div><div>异常:--%>
<%--	            						<a href="javascript:;" id="seriousUnAllocated">--%>
<%--	            							<u><font color="blue" size="4"><s:property value="hostunalloseriouscount"/></font></u>--%>
<%--	            						</a>&nbsp;个--%>
<%--	            					 </div>--%>
<%--	            				</td>--%>
<%--		      				</tr>--%>
<%--		      				<tr>--%>
<%--	          					<td align="left">--%>
<%--	          						<div style="float:left;" class="x86"></div><div>X86刀片: <font color="blue" size="4"><s:property value="x86hostunallocount"/></font>&nbsp;个</div>--%>
<%--	              				</td>--%>
<%--	             				<td align="left">--%>
<%--	             					<div style="float:left;" class="jijia"></div><div>机架式服务器: <font color="blue" size="4"><s:property value="framehostunallocount"/></font>&nbsp;个</div>--%>
<%--	              				</td>--%>
<%--	          				</tr>--%>
<%--	        </table> --%>
    </td>
  </tr>
</table>
</div>
</s:form>
</body>