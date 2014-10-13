<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<s:form action="" method="post"  id="theForm">
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-datacenter">
            <div class="box on">
              <dl class="single dc-dl">
              	<dt>
              		<div class="progress-percent pgpercent-orange js_progressPer">
                    	<label>CPU
                    	</label>
                    	<s:if test="@java.lang.Math@round(map.vcpu_used_count*100*100/map.vcpu_all_count)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.vcpu_used_count*100*100/map.vcpu_all_count)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                   		<span class="percent"><s:property value="@java.lang.Math@round(map.vcpu_used_count*100*100/map.vcpu_all_count)/100.0"/>%</span>
                    </div>
              	</dt>
              	<dd>
              		 总量：<fmt:formatNumber value="${(map.cpu_cl)}" pattern="#,###" type="number"/> 核&nbsp;&nbsp;
                </dd>
              </dl>
              
              <dl class="single dc-dl">
              	<dt>
              		<div class="progress-percent pgpercent-orange js_progressPer">
                    	<label>内存
                    	</label>
                    	<s:if test="@java.lang.Math@round(map.mem_used_mb*100*100/map.mem_all_mb)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.mem_used_mb*100*100/map.mem_all_mb)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                   		<span class="percent"><s:property value="@java.lang.Math@round(map.mem_used_mb*100*100/map.mem_all_mb)/100.0"/>%</span>
                    </div>
              	</dt>
              	<dd>
              		 总量：<fmt:formatNumber value="${(map.memory/1024)}" pattern="#,###" type="number"/> GB&nbsp;&nbsp;
                </dd>
              </dl>
              
              <dl class="single dc-dl">
              	<dt>
              		<div class="progress-percent pgpercent-orange js_progressPer">
                    	<label>存储
                    	</label>
                    	<s:if test="@java.lang.Math@round(map.storage_used_mb*100*100/map.storage_all_mb)/100.0 > 100">
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:103%'><b></b></span></span></span>
                      	</s:if>
                      	<s:else>
                      		<span class="progress-bar"><span class="bar-inner"><span class="current" style='width:<s:property value="@java.lang.Math@round(map.storage_used_mb*100*100/map.storage_all_mb)/100.0"/>%'><b></b></span></span></span>
                      	</s:else>
                   		<span class="percent"><s:property value="@java.lang.Math@round(map.storage_used_mb*100*100/map.storage_all_mb)/100.0"/>%</span>
                    </div>
              	</dt>
              	<dd>
              		 总量：<fmt:formatNumber value="${(map.store/1024)}" pattern="#,###" type="number"/> GB&nbsp;&nbsp;
                </dd>
              </dl>
            </div>
    </td>
    <td align="left" valign="top" class="panel-resource">
    	<%-- <h2 class="cluster"><span><font color="orange" size="5"><s:property value="map.cluster_all_count" /></font>&nbsp;个</span></h2>
        <table width="330" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
	      <tr>
            <td width="50%"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/vmware.gif" width="16" height="16" />&nbsp;vmware集群: 
            	<font color="blue" size="4"><s:property value="map.cluster_vmware_count"/></font>&nbsp;个
            </td>
            <td><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/xen.jpg" width="16" height="16" />&nbsp;xen集群:  
            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4"><s:property value="map.cluster_xen_count"/></font>&nbsp;个
            </td>
          </tr>
          <tr>
            <td><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/zhuji.png" width="16" height="16" />&nbsp;主机总数: 
              	&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4"><s:property value="map.host_all_count"/></font>&nbsp;个
             </td>
            <td width="50%"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/vm.png" width="16" height="16" />&nbsp;虚拟机总数: 
              	<font color="blue" size="4"><s:property value="map.vm_all_count"/></font>&nbsp;个
             </td>
          </tr>
        </table>   --%>
        <!-- <div class="clr" style=" height: 30px;"></div> -->
        
        <h2 class="host dc-tt mgt-15"><span class="txt">物理主机</span><span class="other"><span class="num"><s:property value="map.count" /></span>&nbsp;个</span></h2>
      	<ul class="dc-list">
        	<li>
            	<i class="running"></i><span class="txt">正在运行</span><span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" id="viewHostRun"><s:property value="map.useable"/></a></font></span>
        	</li>
        	<li>
            	<i class="stop"></i><span class="txt">已停止</span><span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" id="viewHostStop"><s:property value="@java.lang.Math@round(map.count - map.useable)"/></a></font></span>
        	</li>
        	<li>
              	<i class="x86"></i><span class="txt">X86刀片</span><span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" id="viewX86Host"><s:property value="map.blade"/></a></font></span>
         	</li>
         	<li>
               	<i class="jijia"></i><span class="txt">机架式服务器</span><span class="num blue-num"><font style="font-weight:bold;"><a href="javascript:;" name="viewFrameHost"><s:property value="map.dl"/></a></font></span>
         	</li>
     	</ul>  
<%-- 	    <div class="clr" style=" height: 30px;"></div>
        <h2 class="vm"><span><font color="orange" size="5"><s:property value="map.vm_all_count" /></font>&nbsp;个</span></h2>
	        <table width="330" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
		      <tr>
	            <td width="40%"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/vmware.gif" width="16" height="16" />&nbsp;vmware: 
	            	<font color="blue" size="4"><s:property value="map.vm_vmware_count"/></font>&nbsp;个
	            </td>
	            <td width="30%">正常:  
	            	<font color="blue" size="4"><s:property value="map.vm_vmware_run_count"/></font>&nbsp;个
	            </td>
	            <td>停止:  
	            	<font color="blue" size="4"><s:property value="map.vm_vmware_stop_count"/></font>&nbsp;个
	            </td>
	          </tr>
	          <tr>
	            <td width="40%"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/xen.jpg" width="16" height="16" />&nbsp;xen: 
	              	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue" size="4"><s:property value="map.vm_xen_count"/></font>&nbsp;个
	             </td>
	            <td width="30%">正常: 
	              	<font color="blue" size="4"><s:property value="map.vm_xen_run_count"/></font>&nbsp;个
	             </td>
	            <td>停止: 
	              	<font color="blue" size="4"><s:property value="map.vm_xen_stop_count"/></font>&nbsp;个
	             </td>
	          </tr>
	        </table> 
 --%>    </td>
  </tr>
</table>
</div>
</s:form>
</body>