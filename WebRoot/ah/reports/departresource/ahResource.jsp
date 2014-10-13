<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript">
	$(function(){
			insertFusionChartData();
		
		 $("[name='detail']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'资源明细',
    			width: '890px',
    			height: '480px',
    			max: true,
    		    min: true,
    		    lock:true,
<%--    			content: 'url:showresource_showResourceDetail.do'--%>
<%--    			统一树调整后连接--%>
    		content: 'url:showresource_showResourceDetail_united_tree.do'
    			});
              });
         
         
         $("[name='trend']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'资源总量趋势图',
    			width: '1000px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content:'url:homePage_totalResource.do?type=all&rownum=200'
    			});
              });
              
              
          $("[name='v_trend']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'vmware资源趋势图',
    			width: '1000px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content:'url:homePage_totalResource.do?type=vmware&rownum=200'
    			});
              });
              
           $("[name='x_trend']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'xen资源趋势图',
    			width: '1000px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content:'url:homePage_totalResource.do?type=xen&rownum=200'
    			});
              });
              
	});
	
	function insertFusionChartData(){
		$("#chartarea").insertFusionCharts({
          //  swfUrl: "FusionCharts/FCF_MSLine.swf",
          	swfUrl: "FusionCharts/ScrollCombi2D.swf", 
            dataSource: "departresource_showDepartResourceTrend.do",
            dataFormat: "jsonurl", 
            width: "400", 
            height: "300", 
            id: "dayChart"
      }); 
	}
</script>
</head>



<body>

<s:form action="function_saveFunctions" method="post"  id="theForm">
<s:set name="vmware" value="theForm.vmwareStatistics"></s:set>
<s:set name="xen" value="theForm.xenStatistics"></s:set>
<s:set name="power" value="theForm.powerStatistics"></s:set>
<s:set name="other" value="theForm.otherStatistics"></s:set>
<div>
<table width="98%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="left" valign="top" class="panel-zy">
       <!--资源 start-->
       <h2 class="zy"><span style="text-align: right"><a href="#"  class="blue mx" name="detail">资源明细</a></span></h2>
       <table width="630" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;">
           <tr>
            <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-cpu.gif" width="26" height="21" /></td>
            <td width="330" colspan="2">CPU总量：<span class="orange-16"><fmt:formatNumber value="${(map.vcpu_all_count)}" pattern="#,###.##" type="number"/>核</span></td>
            <td  width="135"><span class="font-gray">已分配: <fmt:formatNumber value="${(map.vcpu_allo_count)}" pattern="#,###.##" type="number"/>核</span></td>
            <td><span class="font-gray">分配率: <fmt:formatNumber value="${((map.vcpu_allo_count) * 100 * 100 /( map.vcpu_all_count )) / 100.0}" pattern="#,###.##" type="number"/>%</span></td>
          </tr>
          <tr>
          	<td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-nc.gif" width="26" height="21" /></td>
			<td  width="330" colspan="2">内存总量：<span class="orange-16"><fmt:formatNumber value="${(map.mem_all_mb/ 1024 * 100)/100.0}" pattern="#,###.##" type="number"/>G</span></td>
            <td  width="135"><span class="font-gray">已分配: <fmt:formatNumber value="${(map.mem_allo_mb/ 1024 * 100)/100.0}" pattern="#,###.##" type="number"/>G</span></td>
            <td><span class="font-gray">分配率: <s:property value="@java.lang.Math@round(map.mem_allo_mb * 100 * 100 / map.mem_all_mb) / 100.0"/>%</span></td>
          </tr>
          <tr>
            <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-yp.gif" width="26" height="21" /></td>
            <td  width="170">存储有效量：<span class="orange-16"><fmt:formatNumber value="${(map.storage_valid_mb/ 1024/1024 * 100)/100.0}" pattern="#,###.##" type="number"/>T</span></td>
            <td  width="160"><span class="font-gray">已接入: <fmt:formatNumber value="${(map.storage_mount_mb/ 1024/1024* 100)/100.0}" pattern="#,###.##" type="number"/>T</span></td>
            <td  width="135"><span class="font-gray">已分配: <fmt:formatNumber value="${(map.storage_allo_mb/ 1024/1024* 100)/100.0}" pattern="#,###.##" type="number"/>T</span></td>
            <td><span class="font-gray">分配率: <s:property value="@java.lang.Math@round(map.storage_allo_mb * 100 * 100 / map.storage_mount_mb) / 100.0"/>%</span></td>
          </tr>
        </table>
        <div class="clr"></div>
		<!--tab start-->
      <div class="tabShow" style="height: 60px;">
        <div class="tabCaption tab-caption-02" >
              <ul>
                <li class="on"><span>vmware</span></li>
                <li><span>xen</span></li>
                <!--  
                <li><span>power vm</span></li>
                <li><span>kvm</span></li>
                -->
              </ul>
          </div>
          <div class="tabContent tab-content-02 pd-10-15">
            <div class="box on">
              <dl class="single">
              	<dt><!-- 总量：50核   已分配：20核   未分配：30核  -->
              			总量:<s:property value="map.vmware_cpu_count"/> 核 &nbsp;
              			已分配:<s:property value="map.vmware_cpu_allo_count"/> 核  &nbsp;
              			未分配:<s:property value="map.vmware_cpu_count-map.vmware_cpu_allo_count"/> 核
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.vmware_cpu_allo_count*100*100/map.vmware_cpu_count)/100.0"/>%'></b></div></td>
                        <td><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(map.vmware_cpu_allo_count * 100 * 100 / map.vmware_cpu_count ) / 100.0"/> %
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><%-- 总量：<s:property value="theForm.v_mem"/>M  已分配：<s:property value="theForm.v_mem_use"/>  未分配：<s:property value="theForm.v_mem_free"/>M --%>
              		<%-- 总量:<s:property value="@java.lang.Math@round(map.vmware_mem_count / 1024 ) "/> G  &nbsp;
              		已分配:<s:property value="@java.lang.Math@round(map.vmware_mem_allo_count /1024 ) "/> G  &nbsp;
              		未分配:<s:property value="@java.lang.Math@round((map.vmware_mem_count-map.vmware_mem_allo_count) / 1024) "/> G --%>
              		总量:<fmt:formatNumber value="${(map.vmware_mem_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		已分配:<fmt:formatNumber value="${(map.vmware_mem_allo_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		未分配:<fmt:formatNumber value="${((map.vmware_mem_count-map.vmware_mem_allo_count)/ 1024)}" pattern="#,###.##" type="number"/>G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.vmware_mem_allo_count*100*100/map.vmware_mem_count)/100.0"/>%'></b></div></td>
                        <td>
                        	<s:property value="@java.lang.Math@round(map.vmware_mem_allo_count* 100*100/map.vmware_mem_count ) / 100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		<%-- 总量:<s:property value="@java.lang.Math@round(map.vmware_store_count/1024)"/> G &nbsp;
              		已分配:<s:property value="@java.lang.Math@round(map.vmware_store_allo_count/1024)"/> G &nbsp;
              		未分配:<s:property value="@java.lang.Math@round((map.vmware_store_count-map.vmware_store_allo_count)/1024)"/> G --%>
              		总量:<fmt:formatNumber value="${(map.vmware_store_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		已分配:<fmt:formatNumber value="${(map.vmware_store_allo_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		未分配:<fmt:formatNumber value="${((map.vmware_store_count-map.vmware_store_allo_count)/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		
              	</dt> 
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.vmware_store_allo_count*100*100/map.vmware_store_count)/100.0"/>%'></b></div></td>
                        <td>
                        	<s:property value="@java.lang.Math@round(map.vmware_store_allo_count*100*100/map.vmware_store_count)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
             <DIV class="box">
              <dl class="single">
              	<dt>
              			<%-- 总量:<s:property value="map.xen_cpu_count"/> 核 &nbsp;
              			已分配:<s:property value="map.xen_cpu_used_count"/> 核 &nbsp;
              			未分配:<s:property value="(map.xen_cpu_count-map.xen_cpu_used_count)"/> 核 --%>
              			总量:<fmt:formatNumber value="${(map.xen_cpu_count)}" pattern="#,###" type="number"/> 核 &nbsp;
              			已分配:<fmt:formatNumber value="${(map.xen_cpu_allo_count)}" pattern="#,###" type="number"/> 核  &nbsp;
              			未分配:<fmt:formatNumber value="${(map.xen_cpu_count-map.xen_cpu_allo_count)}" pattern="#,###" type="number"/> 核
              			
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.xen_cpu_allo_count*100*100/map.xen_cpu_count)/100.0"/>%'></b></div></td>
                        <td><!-- 67  -->
                        	<s:property value="@java.lang.Math@round(map.xen_cpu_allo_count*100*100/map.xen_cpu_count)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
					<%-- 总量:<s:property value="@java.lang.Math@round(map.xen_mem_count / 1024) "/> G &nbsp;
              		已分配:<s:property value="@java.lang.Math@round(map.xen_mem_used_count / 1024) "/> G &nbsp;
              		未分配:<s:property value="@java.lang.Math@round((map.xen_mem_count-map.xen_mem_used_count)/ 1024) "/> G --%>
              		总量:<fmt:formatNumber value="${(map.xen_mem_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		已分配:<fmt:formatNumber value="${(map.xen_mem_allo_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		未分配:<fmt:formatNumber value="${((map.xen_mem_count-map.xen_mem_allo_count)/ 1024)}" pattern="#,###.##" type="number"/>G
              	</dt> 
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.xen_mem_allo_count*100*100/map.xen_mem_count)/100.0"/>%'></b></div></td>
                        <td>
                        	<s:property value="@java.lang.Math@round(map.xen_mem_allo_count*100*100/map.xen_mem_count)/100.0"/>%
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		<%-- 总量：<s:property value="@java.lang.Math@round(map.xen_store_count /1024) "/> G &nbsp;
              		已分配: <s:property value="@java.lang.Math@round(map.xen_store_used_count  /1024)"/> G &nbsp;
              		未分配：<s:property value="@java.lang.Math@round((map.xen_store_count-map.xen_store_used_count) /1024)"/> G --%>
              		总量:<fmt:formatNumber value="${(map.xen_store_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		已分配:<fmt:formatNumber value="${(map.xen_store_allo_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		未分配:<fmt:formatNumber value="${((map.xen_store_count-map.xen_store_allo_count)/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.xen_store_allo_count*100*100/map.xen_store_count)/100.0"/>%'></b></div></td>
                        <td><%-- <s:property value="theForm.x_store_per"/>% --%>
                        	<s:property value="@java.lang.Math@round(map.xen_store_allo_count*100*100/map.xen_store_count)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
<%--                <dl class="single" style="height: 67px;">--%>
<%--              	<a href="javascript:;" name="x_trend"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/zyzst.gif"  title="资源走势图" /></a>--%>
<%--              </dl>--%>
            </DIV>
            <DIV class="box">
               <dl class="single">
              	<dt><!-- 总量：100核   已分配：60核   未分配：40核  -->
              			总量：<s:property value="#power.allCPU"/> 核
              			已分配: <s:property value="#power.usedCPU"/> 核
              			未分配：<s:property value="#power.freeCPU"/> 核
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="38" height="23" /></td>
                        <td><div class="percentage">
                        	<s:if test="#power.allCPU == 0">
                       		 	<b style='width:0.0%'></b>
                       		 </s:if>
                       		 <s:else>
                       		 	<b style="width:<s:property value="@java.lang.Math@round(#power.usedCPU*100*100/#power.allCPU)/100.0"/>%"></b>
                       		 </s:else>
                        </div></td>
                        <td><!-- 60%  -->
                        	<s:if test="#power.allCPU == 0">
                       		 	0.0
                       		 </s:if>
                       		 <s:else>
                       		 	<s:property value="@java.lang.Math@round(#power.usedCPU*100*100/#power.allCPU)/100.0"/>
                       		 </s:else>
                       		 	%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><!-- 总量：4000M   已分配：2000M  未分配：2000M  -->
              		总量：<s:property value="@java.lang.Math@round(#power.allMem/1024/1024/1024*100) / 100.0"/> G
              		已分配: <s:property value="@java.lang.Math@round(#power.usedMem/1024/1024/1024*100) / 100.0"/> G
              		未分配：<s:property value="@java.lang.Math@round(#power.freeMem/1024/1024/1024*100) / 100.0"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#power.usedMem*100*100/#power.allMem)/100.0"/>%'></b></div></td>
                        <td><!-- 50%  -->
                        	<s:property value="@java.lang.Math@round(#power.usedMem*100*100/#power.allMem)/100.0"/>%
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><!-- 总量：500G   已分配：300G  未分配：200G  -->
              		总量：<s:property value="@java.lang.Math@round(#power.allStor/1024/1024*100) / 100.0"/> G
              		已分配: <s:property value="@java.lang.Math@round(#power.usedStor/1024/1024*100) / 100.0"/> G
              		未分配：<s:property value="@java.lang.Math@round(#power.freeStor/1024/1024*100) / 100.0"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style="width:<s:property value="@java.lang.Math@round(#power.usedStor*100*100/#power.allStor)/100.0"/>%"></b></div></td>
                        <td><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(#power.usedStor*100*100/#power.allStor)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single" style="height: 67px;">
              	<a href="javascript:;" ><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/zyzst.gif"  title="资源走势图" /></a>
              </dl>
            </DIV>
            <DIV class="box">
             <dl class="single">
              	<dt><!-- 总量：100核   已分配：60核   未分配：40核  -->
              			总量：<s:property value="#other.allCPU"/> 核
              			已分配: <s:property value="#other.usedCPU"/> 核
              			未分配：<s:property value="#other.freeCPU"/> 核
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="38" height="23" /></td>
                        <td><div class="percentage">
                       		 <s:if test="#other.allCPU == 0">
                       		 	<b style="width:0.0%" class="red"></b>
                       		 </s:if>
                       		 <s:else>
                       		 	<b style="width:<s:property value="@java.lang.Math@round(#other.usedCPU*100*100/#other.allCPU)/100.0"/>%"></b>
                       		 </s:else>
                        </div></td>
                        <td><!-- 60%  -->
                        	 <s:if test="#other.allCPU == 0">
                       		 	0.0
                       		 </s:if>
                       		 <s:else>
                       		 	<s:property value="@java.lang.Math@round(#other.usedCPU*100*100/#other.allCPU)/100.0"/>
                       		 </s:else>
                        		%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><!-- 总量：4000M   已分配：2000M未分配：2000M  -->
              		总量：<s:property value="@java.lang.Math@round(#other.allMem/1024/1024/1024*100) / 100.0"/> G
              		已分配: <s:property value="@java.lang.Math@round(#other.usedMem/1024/1024/1024*100) / 100.0"/> G
              		未分配：<s:property value="@java.lang.Math@round(#other.freeMem/1024/1024/1024*100) / 100.0"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style="width:<s:property value="@java.lang.Math@round(#other.usedMem*100*100/#other.allMem)/100.0"/>%"></b></div></td>
                        <td><!-- 50%  -->
                        	<s:property value="@java.lang.Math@round(#other.usedMem*100*100/#other.allMem)/100.0"/>%
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><!-- 总量：500G   已分配：300G  未分配：200G  -->
              		总量：<s:property value="@java.lang.Math@round(#other.allStor/1024/1024*100) / 100.0"/> G
              		已分配: <s:property value="@java.lang.Math@round(#other.usedStor/1024/1024*100) / 100.0"/> G
              		未分配：<s:property value="@java.lang.Math@round(#other.freeStor/1024/1024*100) / 100.0"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style="width:<s:property value="@java.lang.Math@round(#other.usedStor*100*100/#other.allStor)/100.0"/>%"></b></div></td>
                        <td><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(#other.usedStor*100*100/#other.allStor)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single" style="height: 67px;">
              	<a href="javascript:;" ><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/zyzst.gif"  title="资源走势图" /></a>
              </dl>
            </DIV>
          </div>
        </div>
        <!--tab end-->
       <!--资源 end-->
    </td>
    
    <td align="left" valign="top" class="panel-gj">
    	<!-- 部门预算分配走势 -->
    	<h2 class="resTrend"></h2>
    		<a href="javascript:;" name="resTrend" >
	    		<div id="chartarea" align="center">
	    			FusionCharts
	    		</div>
	    		<div id="aaa">详细信息</div>
    		</a>
    </td>
  </tr>
</table>
<div class="clr" style="height: 10px;"></div>
<!--ip start-->
<h2 class="ip"></h2>
<ul class="panel-ip">
  	<s:iterator id="theBean" value="netList">
  	<li title="总量:<s:property value="#theBean.allCount"/> 已分配:<s:property value="#theBean.usedCount"/> 可用:<s:property value="#theBean.allCount-#theBean.usedCount"/>">
		    	<p><s:property value="@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount ) / 100.0"/> %</p>
		    	<p class="percentage2"><b style='height:<s:property value="100-(@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount )/100.0)"/>%'></b></p>
		    	<p><s:property value="#theBean.netName"/></p>
    </li>
    </s:iterator>
</ul>

<!--ip end-->
<script type=text/javascript>
$(function(){
	$(".tabShow .tabCaption li").click(
		function(){
			$(this).addClass("on").siblings().removeClass("on");
			var index=$(this).index()
			$(this).parents(".tabShow").find(".tabContent").children(".box").eq(index).addClass("on").siblings().removeClass("on");
			})	   

	$("#aaa").die().live("click",function(){
		$.dialog({
			id:'showDetail',
			title:'部门资源历史走势图',
			width:'1000px',
			height:'550px',
			max:true,
			min:true,
			content:'url:departresource_showDetail.do'
		});
	});	


})

</script>
</div>
</s:form>
</body>
