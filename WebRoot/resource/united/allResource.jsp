<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="<%=request.getContextPath()%>/sxcloud/images/virtual/unitedtree-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/hadoop/statistics/css/statistics.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<style>
</style>
<script type="text/javascript">
	$(function(){
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
              
              
            $("[name='serious']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'严重告警',
    			width: '1150px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=0'
    			});
              });
            $("[name='serious1']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'主要告警',
        			width: '1150px',
        			height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=1'
        			});
                  });
            $("[name='serious2']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'次要告警',
        			width: '1150px',
        			height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=2'
        			});
                  });
            $("[name='serious3']").unbind().live("click",function(){
            	currentEdit=$(this);
        		$.dialog({
        			id:'vdi',
        			title:'不确定告警',
        			width: '1150px',
        			height: '500px',
        			max: true,
        		    min: true,
        		    lock:true,
        			content: 'url:alarm_forResourcePage.do?EVENT_LEVEL=3'
        			});
                  });
              
              
            $("[name='alarm']").unbind().live("click",function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'告警',
    			width: '1000px',
    			height: '500px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:alarm_listMonitorAlarm.do'
    			});
              });
              
              
              
               $("[name='allWO']").unbind().live("click",function(){
		        	currentEdit=$(this);
		    		$.dialog({
		    			id:'vdi',
		    			title:'工单总数',
		    			width: '1150px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:workorder/workorder_listWOByResource.do'
		    			});
		              });
           	  $("[name='unDealWO']").unbind().live("click",function(){
		        	currentEdit=$(this);
		    		$.dialog({
		    			id:'vdi',
		    			title:'未处理工单',
		    			width: '1150px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:workorder/workorder_listWOByResource.do?workOrderObj.WSTAT=0'
		    			});
		              });
            	 $("[name='BOMCWO']").unbind().live("click",function(){
		        	currentEdit=$(this);
		    		$.dialog({
		    			id:'vdi',
		    			title:'BOMC工单',
		    			width: '1150px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:workorder/workorder_listWOByResource.do?workOrderObj.CAMEFROM=0'
		    			});
		              });
		          $("[name='unDealBOMCWO']").unbind().live("click",function(){
		        	currentEdit=$(this);
		    		$.dialog({
		    			id:'vdi',
		    			title:'BOMC未处理工单',
		    			width: '1150px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:workorder/workorder_listWOByResource.do?workOrderObj.CAMEFROM=0&workOrderObj.WSTAT=0'
		    			});
		              });
		         $("[name='cloudWO']").unbind().live("click",function(){
		        	currentEdit=$(this);
		    		$.dialog({
		    			id:'vdi',
		    			title:'云平台工单',
		    			width: '1150px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:workorder/workorder_listWOByResource.do?workOrderObj.CAMEFROM=1'
		    			});
		              });
		         $("[name='unDealCloudWO']").unbind().live("click",function(){
		        	currentEdit=$(this);
		    		$.dialog({
		    			id:'vdi',
		    			title:'云平台未处理工单',
		    			width: '1150px',
		    			height: '500px',
		    			max: true,
		    		    min: true,
		    		    lock:true,
		    			content: 'url:workorder/workorder_listWOByResource.do?workOrderObj.CAMEFROM=1&workOrderObj.WSTAT=0'
		    			});
		              });
	});
	
	
</script>
</head>



<body>

<s:form action="function_saveFunctions" method="post"  id="theForm">
<s:set name="vmware" value="theForm.vmwareStatistics"></s:set>
<s:set name="xen" value="theForm.xenStatistics"></s:set>
<s:set name="power" value="theForm.powerStatistics"></s:set>
<s:set name="other" value="theForm.otherStatistics"></s:set>
<div style="padding-left:1%">

    <h2 class="datacenter dc-tt mgt-15"><span class="txt">资源概述</span></h2>
	<div class="mainbox">
		<div class="item" style="height:200px">
			<div class="top">
				<div class="ml5"><font color="orange" size="3" >资源总量</font></div>
			</div>
			<div>
				<div class="right">
				<ul class="dc-list" >
					<li style="width:40%">
            			<img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-cpu.gif" width="20" height="15" /><span class="txt">CPU总量</span><span class="num blue-num"><font style="font-weight:bold;"><fmt:formatNumber value="${(map.vcpu_all_count)}" pattern="#,###.##" type="number"/>核</font></span>
            		</li>		
        			<li style="height:20%">
            			<i class="running"></i><span class="txt">已分配</span><span class="num blue-num"><font style="font-weight:bold;"><fmt:formatNumber value="${(map.vcpu_allo_count)}" pattern="#,###.##" type="number"/>核</span></font></span>
        			</li>
        			<li style="height:20%">
           				<i class="alert"></i><span class="txt">分配率</span><span class="num red-num"><font style="font-weight:bold;"><fmt:formatNumber value="${((map.vcpu_allo_count) * 100 * 100 /( map.vcpu_all_count )) / 100.0}" pattern="#,###.##" type="number"/>%</font></span>
         			</li>
     			</ul> 
     			<ul class="dc-list">
					<li style="width:40%">
            			<img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-nc.gif" width="20" height="15" /><span class="txt">内存总量</span><span class="num blue-num"><font style="font-weight:bold;"><fmt:formatNumber value="${(map.mem_all_mb/ 1024 * 100)/100.0}" pattern="#,###.##" type="number"/>G</font></span>
            		</li>		
        			<li style="height:20%">
            			<i class="running"></i><span class="txt">已分配</span><span class="num blue-num"><font style="font-weight:bold;"><fmt:formatNumber value="${(map.mem_allo_mb/ 1024 * 100)/100.0}" pattern="#,###.##" type="number"/>G</span></font></span>
        			</li>
        			<li style="height:20%">
           				<i class="alert"></i><span class="txt">分配率</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="@java.lang.Math@round(map.mem_allo_mb * 100 * 100 / map.mem_all_mb) / 100.0"/>%</font></span>
         			</li>
     			</ul>
     			<ul class="dc-list">
					<li style="height:40%">
            			<i class="zhuji"></i><span class="txt">存储有效量</span><span class="num blue-num"><fmt:formatNumber value="${(map.storage_valid_mb/ 1024/1024 * 100)/100.0}" pattern="#,###.##" type="number"/>T(已接入：<fmt:formatNumber value="${(map.storage_mount_mb/ 1024/1024* 100)/100.0}" pattern="#,###.##" type="number"/>T)</font></span>
            		</li>		
        			<li style="height:20%">
            			<i class="running"></i><span class="txt">已分配</span><span class="num blue-num"><font style="font-weight:bold;"><fmt:formatNumber value="${(map.storage_allo_mb/ 1024/1024* 100)/100.0}" pattern="#,###.##" type="number"/>T</span></font></span>
        			</li>
        			<li style="height:20%">
           				<i class="alert"></i><span class="txt">分配率</span><span class="num red-num"><font style="font-weight:bold;"><s:property value="@java.lang.Math@round(map.storage_allo_mb * 100 * 100 / map.storage_mount_mb) / 100.0"/>%</font></span>
         			</li>
     			</ul> 
					</div>
				</div>
			</div>
			
			<div class="item" style="height:200px">
				<div class="top">
					<div class="ml5"><font color="orange" size="3">告警</font></div>
				</div>
				<br />
				<div>
					<div class="right">
					<ul class="dc-list">
					<li style="height:30px">
            			<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm1.png" width="16" height="16" state="1" /><span class="txt">严重告警</span>
            			<span class="num red-num">
            			<font style="font-weight:bold;color: white;">
            				<s:if test="map['0'] != null && '' != map['0']">
				            	<a href="javascript:;"  name="serious"><s:property value="map['0']"/></a>
				            </s:if><s:else>
				            	<a href="#" >0</a>
				            </s:else>
            			</font></span>
            		</li>	
        			<li style="height:30px">
            			<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm2.png" width="16" height="16" state="1" /><span class="txt">主要告警</span><span class="num red-num"><font style="font-weight:bold;">
            				<s:if test="map['1'] != null && '' != map['1']">
				            	<a href="javascript:;"  name="serious1"><s:property value="map['1']"/></a>
				            </s:if><s:else>
				            	<a href="#" >0</a>
				            </s:else>
            			</span></font></span>
        			</li>
     			</ul>  
				<ul class="dc-list">
					<li style="height:30px">
            			<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm3.png" width="16" height="16" state="1" /><span class="txt">次要告警</span><span class="num red-num"><font style="font-weight:bold;">
            			<s:if test="map['2'] != null && '' != map['2']">
			            	<a href="javascript:;"  name="serious2"><s:property value="map['2']"/></a>
			            </s:if><s:else>
			            	<a href="#" >0</a>
			            </s:else>
            			</font></span>
            		</li>		
        			<li style="height:30px">
            			<img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm4.png" width="16" height="16" state="1" /><span class="txt">不确定告警</span><span class="num red-num"><font style="font-weight:bold;">
            			 <s:if test="map['3'] != null && '' != map['3']">
			            	<a href="javascript:;" name="serious3"><s:property value="map['3']"/></a>
			            </s:if><s:else>
			            	<a href="#" >0</a>
			            </s:else>
            			</span></font></span>
        			</li>
     			</ul>  	
					</div>
				</div>
			</div>	
			
			<div class="item" style="height:500px;">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" >虚拟化资源</font></div>
				</div>
				<div class="right">
				<div class="tabShow" style="height: 60px;;margin-top: 20px">
        	<div class="tabCaption tab-caption-02" >
              <ul>
                <li class="on"><span>vmware</span></li>
                <li><span>xen</span></li>
              </ul>
          </div>
          <div class="tabContent tab-content-02 pd-10-15">
<%--          vmware--%>
            <div class="box on">
              <dl class="single">
              	<dt><!-- 总量：50核   已分配：20核   未分配：30核  -->
              			总量:<fmt:formatNumber value="${(map.vmware_cpu_count)}" pattern="#,###.##" type="number"/> 核 &nbsp;
              			已分配:<fmt:formatNumber value="${(map.vmware_cpu_allo_count)}" pattern="#,###.##" type="number"/> 核  &nbsp;
              			未分配:<fmt:formatNumber value="${(map.vmware_cpu_count-map.vmware_cpu_allo_count)}" pattern="#,###.##" type="number"/> 核
              	</dt>
                <dd>
                	<table width="200" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="40" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.vmware_cpu_allo_count*100*100/map.vmware_cpu_count)/100.0"/>%'></b></div></td>
                        <td><!-- 60%  -->
                        	<fmt:formatNumber value="${(map.vmware_cpu_allo_count * 100 * 100 / map.vmware_cpu_count) / 100.0}" pattern="#" type="number"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              		总量:<fmt:formatNumber value="${(map.vmware_mem_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		已分配:<fmt:formatNumber value="${(map.vmware_mem_allo_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		未分配:<fmt:formatNumber value="${((map.vmware_mem_count-map.vmware_mem_allo_count)/ 1024)}" pattern="#,###.##" type="number"/>G
              	</dt>
                <dd>
                	<table width="200" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="40" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.vmware_mem_allo_count*100*100/map.vmware_mem_count)/100.0"/>%'></b></div></td>
                        <td>
                          <fmt:formatNumber value="${(map.vmware_mem_allo_count* 100*100/map.vmware_mem_count) / 100.0}" pattern="#" type="number"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		总量:<fmt:formatNumber value="${(map.vmware_store_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		已分配:<fmt:formatNumber value="${(map.vmware_store_allo_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		未分配:<fmt:formatNumber value="${((map.vmware_store_count-map.vmware_store_allo_count)/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              	</dt> 
                <dd>
                	<table width="200" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="40" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.vmware_store_allo_count*100*100/map.vmware_store_count)/100.0"/>%'></b></div></td>
                        <td>
                           <fmt:formatNumber value="${(map.vmware_store_allo_count*100*100/map.vmware_store_count) / 100.0}" pattern="#" type="number"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
<%--            xen--%>
             <DIV class="box">
              <dl class="single">
              	<dt>
              			总量:<fmt:formatNumber value="${(map.xen_cpu_count)}" pattern="#,###" type="number"/> 核 &nbsp;
              			已分配:<fmt:formatNumber value="${(map.xen_cpu_allo_count)}" pattern="#,###" type="number"/> 核  &nbsp;
              			未分配:<fmt:formatNumber value="${(map.xen_cpu_count-map.xen_cpu_allo_count)}" pattern="#,###" type="number"/> 核
              			
              	</dt>
                <dd>
                	<table width="200" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="40" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.xen_cpu_allo_count*100*100/map.xen_cpu_count)/100.0"/>%'></b></div></td>
                        <td><!-- 67  -->
                          <fmt:formatNumber value="${(map.xen_cpu_allo_count*100*100/map.xen_cpu_count) / 100.0}" pattern="#" type="number"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		总量:<fmt:formatNumber value="${(map.xen_mem_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		已分配:<fmt:formatNumber value="${(map.xen_mem_allo_count/ 1024)}" pattern="#,###.##" type="number"/> G  &nbsp;
              		未分配:<fmt:formatNumber value="${((map.xen_mem_count-map.xen_mem_allo_count)/ 1024)}" pattern="#,###.##" type="number"/>G
              	</dt> 
                <dd>
                	<table width="200" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="40" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.xen_mem_allo_count*100*100/map.xen_mem_count)/100.0"/>%'></b></div></td>
                        <td>
                           <fmt:formatNumber value="${(map.xen_mem_allo_count*100*100/map.xen_mem_count) / 100.0}" pattern="#" type="number"/>%
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt>
              		总量:<fmt:formatNumber value="${(map.xen_store_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		已分配:<fmt:formatNumber value="${(map.xen_store_allo_count/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              		未分配:<fmt:formatNumber value="${((map.xen_store_count-map.xen_store_allo_count)/ 1024/1024)}" pattern="#,###.##" type="number"/>T
              	</dt>
                <dd>
                	<table width="200" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="40" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(map.xen_store_allo_count*100*100/map.xen_store_count)/100.0"/>%'></b></div></td>
                        <td><%-- <s:property value="theForm.x_store_per"/>% --%>
                          <fmt:formatNumber value="${(map.xen_store_allo_count*100*100/map.xen_store_count) / 100.0}" pattern="#" type="number"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </DIV>
          </div>
        </div>
					
				</div>
			</div>
			
			<div class="item" style="height:150px">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" >工单</font></div>
				</div>
				<br />
				<div class="right">
					<table width="330" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
          <tr>
            <td>工单总数: 
             <s:if test="woObj.allWO != null && '' != woObj.allWO">
            	<a href="javascript:;" class="orange-16" name="allWO"><s:property value="woObj.allWO"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
            </td>
            <td>未处理工单:  
             <s:if test="woObj.unDealWO != null && '' != woObj.unDealWO">
            	<a href="javascript:;" class="orange-16" name="unDealWO"><s:property value="woObj.unDealWO"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
            </td>
          </tr>
          <tr>
            <td>BOMC总数:
              <s:if test="woObj.BOMCWO != null && '' != woObj.BOMCWO">
            	<a href="javascript:;" class="orange-16" name="BOMCWO"><s:property value="woObj.BOMCWO"/></a>
              </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
             </td>
            <td>BOMC未处理工单:  
              <s:if test="woObj.unDealBOMCWO != null && '' != woObj.unDealBOMCWO">
            	<a href="javascript:;" class="orange-16" name="unDealBOMCWO"><s:property value="woObj.unDealBOMCWO"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
             </td>
          </tr>
          <tr>
            <td>云平台总数:
              <s:if test="woObj.cloudWO != null && '' != woObj.cloudWO">
            	<a href="javascript:;" class="orange-16" name="cloudWO"><s:property value="woObj.cloudWO"/></a>
              </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
             </td>
            <td>云平台未处理工单:  
              <s:if test="woObj.unDealCloudWO != null && '' != woObj.unDealCloudWO">
            	<a href="javascript:;" class="orange-16" name="unDealCloudWO"><s:property value="woObj.unDealCloudWO"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
             </td>
          </tr>
        </table>
				</div>
			</div>
			
			<div class="item" style="height:200px">
				<div class="top">
					<div class="ml5"><font color="orange" size="3" >IP</font></div>
				</div>
				<br />
				<div class="right">
					<s:iterator id="theBean" value="netList">
				  	<li title="总量:<s:property value="#theBean.allCount"/> 已分配:<s:property value="#theBean.usedCount"/> 可用:<s:property value="#theBean.allCount-#theBean.usedCount"/>">
						    	<p><s:property value="@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount ) / 100.0"/> %</p>
						    	<p class="percentage2"><b style='height:<s:property value="100-(@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount )/100.0)"/>%'></b></p>
						    	<p><s:property value="#theBean.netName"/></p>
				    </li>
				    </s:iterator>
					
				</div>
			</div>
			
	</div>
	

<!--ip end-->
<script type=text/javascript>
$(function(){
	$(".tabShow .tabCaption li").click(
		function(){
			$(this).addClass("on").siblings().removeClass("on");
			var index=$(this).index()
			$(this).parents(".tabShow").find(".tabContent").children(".box").eq(index).addClass("on").siblings().removeClass("on");
			})	   
		   })

</script>
</div>
</div>
</s:form>
</body>
