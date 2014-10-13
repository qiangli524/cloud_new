<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
            
	});
	
	
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
       <h2 class="zy"><span><a href="#" class="blue mx" name="detail">资源明细</a><a href="javascript:;" class="blue zst" name="trend">资源走势图</a></span></h2>
       <table width="600" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;">
          <tr>
            <td width="32"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-cpu.gif" width="26" height="21" /></td>
            <td width="74">CPU总量：</td>
            <td width="164" class="orange-16"><s:property value="(#vmware.allCPU+#xen.allCPU+#power.allCPU+#other.allCPU)/1000"/>GHz<span class="font-gray">已用<s:property value="(#vmware.usedCPU+#xen.usedCPU+#power.usedCPU+#other.usedCPU)/1000"/>GHz</span></td>
            <td width="32"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-nc.gif" width="26" height="21" /></td>
<%--            <td width="74">内存总量：</td> <td width="164" class="orange-20"><s:property value="@java.lang.Math@round(#vmware.allMem/1024/1024+#xen.allMem/1024/1024+#power.allMem/1024/1024+#other.allMem/1024/1024)"/>--%>
<%--            <span class="font-gray">已用<s:property value="@java.lang.Math@round(#vmware.usedMem/1024/1024+#xen.usedMem+#power.usedMem+#other.usedMem)"/>M</span></td>--%>
			<td width="74">内存总量：</td> <td width="164" class="orange-16"><s:property value="@java.lang.Math@round(#vmware.allMem/1024+#xen.allMem/1024+#power.allMem/1024+#other.allMem/1024)"/>G
            <span class="font-gray">已用<s:property value="@java.lang.Math@round(#vmware.usedMem+#xen.usedMem+#power.usedMem+#other.usedMem)/1024"/>G</span></td>
          </tr>
          <tr>
            <td width="32" height="50"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-yp.gif" width="26" height="21" /></td>
            <td width="74">存储总量：</td>
            <td width="250" class="orange-16">
            	<s:property value="@java.lang.Math@round(#vmware.allStor/1024+#xen.allStor+#power.allStor/1024+#other.allStor/1024)"/>G
            <span class="font-gray">已用<s:property value="@java.lang.Math@round(#vmware.usedStor/1024.0+#xen.usedStor+#power.usedStor/1024.0+#other.usedStor/1024.0)"/>G</span></td>
          <!--   <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/icon-jhj.gif" width="26" height="21" /></td>
            <td>交换机端口:</td>
            <td width="160" class="orange-20">&nbsp;&nbsp;&nbsp;&nbsp;96<span class="font-gray">&nbsp;&nbsp;已用48个</span></td>
            --> 
          </tr>
        </table>
        <div class="clr"></div>
		<!--tab start-->
      <div class="tabShow" style="height: 60px;">
        <div class="tabCaption tab-caption-02" >
              <ul>
                <li class="on"><span>vmware</span></li>
                <li><span>xen</span></li>
                <li><span>power vm</span></li>
                <li><span>kvm</span></li>
              </ul>
          </div>
          <div class="tabContent tab-content-02 pd-10-15">
            <div class="box on">
              <dl class="single">
              	<dt><!-- 总量：50核   已用：20核   剩余：30核  -->
              			总量：<s:property value="#vmware.allVcpu"/> 核
              			已用: <s:property value="#vmware.usedVcpu"/> 核
              			剩余：<s:property value="#vmware.freeVcpu"/> 核
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#vmware.usedVcpu*100*100/#vmware.allVcpu)/100.0"/>%'></b></div></td>
                        <td><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(#vmware.usedVcpu * 100 * 100 / #vmware.allVcpu ) / 100.0"/> %
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><%-- 总量：<s:property value="theForm.v_mem"/>M  已用：<s:property value="theForm.v_mem_use"/>  剩余：<s:property value="theForm.v_mem_free"/>M --%>
              		总量：<s:property value="@java.lang.Math@round(#vmware.allMem / 1024 * 100) /100.0 "/> G
              		已用: <s:property value="@java.lang.Math@round(#vmware.usedMem /1024 * 100)/100.0 "/> G
              		剩余：<s:property value="@java.lang.Math@round(#vmware.freeMem / 1024*100)/100.0 "/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#vmware.usedMem*100*100/#vmware.allMem)/100.0"/>%'></b></div></td>
                        <td><%-- <s:property value="theForm.v_mem_per"/>% --%>
                        	<s:property value="@java.lang.Math@round(#vmware.usedMem* 100*100/#vmware.allMem ) / 100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><%-- 总量：<s:property value="theForm.v_store"/>G  已用：<s:property value="theForm.v_store_use"/>   剩余：<s:property value="theForm.v_store_free"/>G --%>
              		总量：<s:property value="@java.lang.Math@round(#vmware.allStor*100/1024) / 100.0"/> G
              		已用: <s:property value="@java.lang.Math@round(#vmware.usedStor*100/1024) / 100.0"/> G
              		剩余：<s:property value="@java.lang.Math@round(#vmware.freeStor*100/1024) / 100.0"/> G
              	</dt> 
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#vmware.usedStor*100*100/#vmware.allStor)/100.0"/>%'></b></div></td>
                        <td><%-- <s:property value="theForm.v_store_per"/>% --%>
                        	<s:property value="@java.lang.Math@round(#vmware.usedStor*100*100/#vmware.allStor)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single" style="height: 67px;">
              	<a href="javascript:;" name="v_trend"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/zyzst.gif"  title="资源走势图" /></a>
              </dl>
            </div>
            <DIV class="box">
              <dl class="single">
              	<dt><!-- 总量：30核   已用：20核   剩余：10核  -->
              			总量：<s:property value="#xen.allVcpu"/> 核
              			已用: <s:property value="#xen.usedVcpu"/> 核
              			剩余：<s:property value="#xen.freeVcpu"/> 核
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-cpu2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#xen.usedVcpu*100*100/#xen.allVcpu)/100.0"/>%'></b></div></td>
                        <td><!-- 67  -->
                        	<s:property value="@java.lang.Math@round(#xen.usedVcpu*100*100/#xen.allVcpu)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><%-- 总量：<s:property value="theForm.x_mem"/>M   已用：<s:property value="theForm.x_mem_use"/>剩余：<s:property value="theForm.x_mem_free"/>M --%>
<%--              		 总量：<s:property value="@java.lang.Math@round(#xen.allMem/1024/1024*100) / 100.0"/> M--%>
<%--              		已用: <s:property value="@java.lang.Math@round(#xen.usedMem/1024/1024*100) / 100.0"/> M--%>
<%--              		剩余：<s:property value="@java.lang.Math@round(#xen.freeMem/1024/1024*100) / 100.0"/> M --%>
					总量：<s:property value="@java.lang.Math@round(#xen.allMem / 1024*100)/100.0 "/> G
              		已用: <s:property value="@java.lang.Math@round(#xen.usedMem / 1024*100)/100.0 "/> G
              		剩余：<s:property value="@java.lang.Math@round(#xen.freeMem / 1024*100)/100.0 "/> G
              	</dt> 
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-nc2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#xen.usedMem*100*100/#xen.allMem)/100.0"/>%'></b></div></td>
                        <td>
                        	<%-- 
                        	<s:if test="theForm.x_mem==0">0%</s:if>
                        	<s:else> <s:property value="theForm.x_mem_per"/>%</s:else>
                        	--%>
                        	<s:property value="@java.lang.Math@round(#xen.usedMem*100*100/#xen.allMem)/100.0"/>%
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
              <dl class="single">
              	<dt><%-- 总量：<s:property value="theForm.x_store"/>G   已用：<s:property value="theForm.x_store_use"/>   剩余：<s:property value="theForm.x_store_free"/>G --%>
              		总量：<s:property value="@java.lang.Math@round(#xen.allStor*100) / 100.0"/> G
              		已用: <s:property value="@java.lang.Math@round(#xen.usedStor*100) / 100.0"/> G
              		剩余：<s:property value="@java.lang.Math@round(#xen.freeStor*100) / 100.0"/> G
              	</dt>
                <dd>
                	<table width="230" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/tit-yp2.gif" width="38" height="23" /></td>
                        <td><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#xen.usedStor*100*100/#xen.allStor)/100.0"/>%'></b></div></td>
                        <td><%-- <s:property value="theForm.x_store_per"/>% --%>
                        	<s:property value="@java.lang.Math@round(#xen.usedStor*100*100/#xen.allStor)/100.0"/>%
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
                <dl class="single" style="height: 67px;">
              	<a href="javascript:;" name="x_trend"><img src="<%=request.getContextPath()%>/sxcloud/images/nresources/zyzst.gif"  title="资源走势图" /></a>
              </dl>
            </DIV>
            <DIV class="box">
               <dl class="single">
              	<dt><!-- 总量：100核   已用：60核   剩余：40核  -->
              			总量：<s:property value="#power.allCPU"/> 核
              			已用: <s:property value="#power.usedCPU"/> 核
              			剩余：<s:property value="#power.freeCPU"/> 核
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
              	<dt><!-- 总量：4000M   已用：2000M  剩余：2000M  -->
              		总量：<s:property value="@java.lang.Math@round(#power.allMem/1024/1024/1024*100) / 100.0"/> G
              		已用: <s:property value="@java.lang.Math@round(#power.usedMem/1024/1024/1024*100) / 100.0"/> G
              		剩余：<s:property value="@java.lang.Math@round(#power.freeMem/1024/1024/1024*100) / 100.0"/> G
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
              	<dt><!-- 总量：500G   已用：300G  剩余：200G  -->
              		总量：<s:property value="@java.lang.Math@round(#power.allStor/1024/1024*100) / 100.0"/> G
              		已用: <s:property value="@java.lang.Math@round(#power.usedStor/1024/1024*100) / 100.0"/> G
              		剩余：<s:property value="@java.lang.Math@round(#power.freeStor/1024/1024*100) / 100.0"/> G
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
              	<dt><!-- 总量：100核   已用：60核   剩余：40核  -->
              			总量：<s:property value="#other.allCPU"/> 核
              			已用: <s:property value="#other.usedCPU"/> 核
              			剩余：<s:property value="#other.freeCPU"/> 核
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
              	<dt><!-- 总量：4000M   已用：2000M剩余：2000M  -->
              		总量：<s:property value="@java.lang.Math@round(#other.allMem/1024/1024/1024*100) / 100.0"/> G
              		已用: <s:property value="@java.lang.Math@round(#other.usedMem/1024/1024/1024*100) / 100.0"/> G
              		剩余：<s:property value="@java.lang.Math@round(#other.freeMem/1024/1024/1024*100) / 100.0"/> G
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
              	<dt><!-- 总量：500G   已用：300G  剩余：200G  -->
              		总量：<s:property value="@java.lang.Math@round(#other.allStor/1024/1024*100) / 100.0"/> G
              		已用: <s:property value="@java.lang.Math@round(#other.usedStor/1024/1024*100) / 100.0"/> G
              		剩余：<s:property value="@java.lang.Math@round(#other.freeStor/1024/1024*100) / 100.0"/> G
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
    	<!--告警 start-->
    	<h2 class="gj"></h2>
        <table width="330" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
           <tr>
            <td width="50%"><img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm1.png" width="16" height="16" state="1" />
            &nbsp;严重告警: 
             <s:if test="map['0'] != null && '' != map['0']">
            	<a href="javascript:;" class="orange-16" name="serious"><s:property value="map['0']"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
            </td>
            <td><img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm2.png" width="16" height="16" state="1" />
            &nbsp;主要告警:  
             <s:if test="map['1'] != null && '' != map['1']">
            	<a href="javascript:;" class="orange-16" name="serious1"><s:property value="map['1']"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
            </td>
          </tr>
          <tr>
            <td><img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm3.png" width="16" height="16" state="1" />
            &nbsp;次要告警: 
              <s:if test="map['2'] != null && '' != map['2']">
            	<a href="javascript:;" class="orange-16" name="serious2"><s:property value="map['2']"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
             </td>
            <td width="50%"><img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm4.png" width="16" height="16" state="1" />
            &nbsp;不确定告警: 
              <s:if test="map['3'] != null && '' != map['3']">
            	<a href="javascript:;" class="orange-16" name="serious3"><s:property value="map['3']"/></a>
            </s:if><s:else>
            	<a href="#" class="orange-16">0</a>
            </s:else>
             </td>
          </tr>
        </table>       
        <!--告警 end-->
        <div class="clr" style=" height: 57px;"></div>
        <!--工单 start-->
        <h2 class="gd"></h2>
        <table width="330" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10">
          <tr>
            <td width="50%">工单总数: <a class="orange-16" href="javascript:;">0</a></td>
            <td>已处理工单: <a href="http://10.110.28.72:8081/itil/getAllDealedList.do?FORM_ID=finished" class="orange-16">0</a></td>
          </tr>
          <tr>
            <td>待办工单: <a href="http://10.110.28.72:8081/itil/itsm/MyWorkBench.jsp" class="orange-16">0</a></td>
            <td width="50%">驳回工单: <a href="javascript:;" class="orange-16">0</a></td>
          </tr>
        </table>
		<!--工单 end-->
    </td>
  </tr>
</table>
<div class="clr" style="height: 10px;"></div>
<!--ip start-->
<h2 class="ip"></h2>
<ul class="panel-ip">
<!-- 
	<s:iterator id="theBean" value="theForm.netList">
	<li>
    	<p><s:property value="#theBean.real"/>%</p>
    	<p class="percentage2"><b style="height:<s:property value="#theBean.per"/>%"></b></p>
        <p><s:property value="#theBean.name"/></p>
    </li>
    </s:iterator>
  -->
  	<s:iterator id="theBean" value="theForm.alarmResultList">
  	<li title="总量:<s:property value="#theBean.allCount"/> 已用:<s:property value="#theBean.usedCount"/> 可用:<s:property value="#theBean.freeCount"/>">
		    	<p><s:property value="@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount ) / 100.0"/> %</p>
		    	<p class="percentage2"><b style='height:<s:property value="100-(@java.lang.Math@round(#theBean.usedCount * 100 * 100 / #theBean.allCount )/100.0)"/>%'></b></p>
		    	<p><s:property value="#theBean.netName"/></p>
    </li>
    </s:iterator>
    <%-- 
    <li>
    	<p>20%</p>
    	<p class="percentage2"><b style="height:80%"></b></p>
    	<p>网管域</p>
    </li>
    <li>
    	<p>30%</p>
    	<p class="percentage2"><b style="height:70%"></b></p>
    	<p>BOSS域</p>
    </li>
     <li>
    	<p>57%</p>
    	<p class="percentage2"><b style="height:43%"></b></p>
    	<p>NG域</p>
    </li>
     <li>
    	<p>85%</p>
    	<p class="percentage2"><b style="height:15%"></b></p>
    	<p>核心业务域</p>
    </li>
    --%>
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
		   })

</script>
</div>
</s:form>
</body>
