<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
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
    			content: 'url:alarm_listMonitorAlarm.do'
    			});
              });
            
	});
	//展示全部虚拟机信息
	function showVMAll(){
		 $.dialog({
			id:'showVMAll',
			title:'虚拟机信息',
			height:'500px',
			width:'1000px',
			lock:true,
			content:'url:unitedOutline_showVMAll.do'
		});
	}
	//展示每个虚拟机信息
	function showVMNews(projectId){
		 $.dialog({
			id:'shownvm',
			title:'虚拟机信息',
			height:'500px',
			width:'1000px',
			lock:true,
			content:'url:unitedOutline_showVMNews.do?vmObj.PROJECT_ID='+projectId
		});
	
	}
	
</script>
</head>



<body class="pop-body scrollbody">

<s:form action="function_saveFunctions" method="post"  id="theForm">
<div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
  	<td width="25"></td>
    <td align="left" valign="top" class="panel-datacenter">
       <h2 class="zy"></h2>
            <div class="box on">
                <dl class="single5">
              	<dt>
              			总量:<s:property value='userAllObj.CPU_ALL_COUNT'/> 核 &nbsp;
              			已用:<s:property value='userUsedObj.CPU_USED_COUNT'/> 核 &nbsp;
              			可用:<s:property value='userAllObj.CPU_ALL_COUNT-userUsedObj.CPU_USED_COUNT'/> 核
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/cpu.gif" width="40" height="23" /></td>
                        <td width="30"><span>CPU</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.CPU_USED_COUNT*100*100/userAllObj.CPU_ALL_COUNT)/100.0"/>%'></b></div></td>
                        <td ><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(userUsedObj.CPU_USED_COUNT * 100 * 100 / userAllObj.CPU_ALL_COUNT ) / 100.0"/> %
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single5">
              	<dt>
              			总量:<s:property value='@java.lang.Math@round(userAllObj.MEM_ALL_MB /1024*100) / 100.0'/>G &nbsp;
              			已用:<s:property value='@java.lang.Math@round(userUsedObj.MEM_USED_MB /1024*100) / 100.0'/>G &nbsp;
              			可用:<s:property value='@java.lang.Math@round((userAllObj.MEM_ALL_MB-userUsedObj.MEM_USED_MB)/1024*100)/ 100.0'/>G
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/mem.gif" width="40" height="23" /></td>
                        <td width="30"><span>内存</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.MEM_USED_MB*100*100/userAllObj.MEM_ALL_MB)/100.0"/>%'></b></div></td>
                        <td ><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(userUsedObj.MEM_USED_MB * 100 * 100 /userAllObj.MEM_ALL_MB ) / 100.0"/> %
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single5">
              	<dt>
              			总量:<s:property value='@java.lang.Math@round(userAllObj.STORAGE_ALL_MB /1024*100) / 100.0'/>G &nbsp;
              			已用:<s:property value='@java.lang.Math@round(userUsedObj.STORAGE_USED_MB /1024*100) / 100.0'/>G &nbsp;
              			可用:<s:property value='@java.lang.Math@round((userAllObj.STORAGE_ALL_MB-userUsedObj.STORAGE_USED_MB)/1024*100)/ 100.0'/>G
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/storage.png" width="25" height="23" /></td>
                         <td width="30"><span>存储</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.STORAGE_USED_MB*100*100/userAllObj.STORAGE_ALL_MB)/100.0"/>%'></b></div></td>
                        <td ><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(userUsedObj.STORAGE_USED_MB * 100 * 100 / userAllObj.STORAGE_ALL_MB ) / 100.0"/> %
                       </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <dl class="single5">
              	<dt>
              			总量:<s:property value='userAllObj.IP_ALL_COUNT'/> 
              			已用:<s:property value='userUsedObj.IP_USED_COUNT'/> 
              			可用:<s:property value='userAllObj.IP_ALL_COUNT-userUsedObj.IP_USED_COUNT'/>
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/ip.png" width="20" height="20" /></td>
                        <td width="30"><span>IP</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(userUsedObj.IP_USED_COUNT*100*100/userAllObj.IP_ALL_COUNT)/100.0"/>%'></b></div></td>
                        <td ><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(userUsedObj.IP_USED_COUNT * 100 * 100 / userAllObj.IP_ALL_COUNT ) / 100.0"/> %
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl>
               <%-- <dl class="single5">
              	<dt>
              			总量:<s:property value='map.cpu_all'/> 
              			已用:<s:property value='map.used_cpu'/> 
              			可用:<s:property value='map.cpu_all-map.used_cpu'/>
              	</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/net.gif" width="23" height="23" /></td>
                        <td width="30"><span>网络</span></td>
                        <td ><div class="percentage"><b style='width:<s:property value="@java.lang.Math@round(#vmware.usedVcpu*100*100/#vmware.allVcpu)/100.0"/>%'></b></div></td>
                        <td ><!-- 60%  -->
                        	<s:property value="@java.lang.Math@round(map.used_cpu * 100 * 100 / map.used_cpu ) / 100.0"/> %
                        </td>
                      </tr>
                    </table>
                </dd>
              </dl> --%>
               <dl class="single5">
              	<dt>&nbsp;</dt>
                <dd>
                	<table width="280" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="30"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/vm.png" width="23" height="23" /></td>
                        <td ><span>虚拟机个数:&nbsp;</span>  
                          <a href="javascript:;" onclick="showVMAll();"><font size="2" color="orange"><s:property value="userAllObj.VM_ALL_COUNT"/>&nbsp;个</font></a></td>
                      </tr>
                    </table>
                </dd>
              </dl>
            </div>
    </td>
    <td align="left" valign="top" class="panel-gj">
    	<!--告警 start-->
    	<%-- <h2 class="gj"></h2>
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
        </table>        --%>
        <!--告警 end-->
       <!--  <div class="clr" style=" height: 57px;"></div>
        工单 start
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
        </table> -->
		<!--工单 end-->
    </td>
  </tr>
</table>
<div class="clr" style="height: 10px;"></div>
<!--ip start-->

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
<%--   <s:iterator id="theBean" value="netList">
  	<li title="总量:<s:property value="@java.lang.Integer@parseInt(#theBean.USEDCOUNT)+@java.lang.Integer@parseInt(#theBean.FREECOUNT)"/> 已用:<s:property value="#theBean.USEDCOUNT"/> 可用:<s:property value="#theBean.FREECOUNT"/>">
	<p><s:property value="@java.lang.Math@round(#theBean.USEDCOUNT * 100 * 100 / (@java.lang.Integer@parseInt(#theBean.USEDCOUNT)+@java.lang.Integer@parseInt(#theBean.FREECOUNT)) ) / 100.0"/> %</p>
	<p class="percentage2"><b style='height:<s:property value="100-(@java.lang.Math@round(#theBean.USEDCOUNT * 100 * 100 / (@java.lang.Integer@parseInt(#theBean.USEDCOUNT)+@java.lang.Integer@parseInt(#theBean.FREECOUNT)) )/100.0)"/>%'></b></p>
	<p><s:property value="#theBean.NAME"/></p>
    </li>
    </s:iterator> --%>
    <div>
	<div class="box on">
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,1,'string')">项目编号</th>
				   <th onclick="sort(theTable,2,'string')">项目名称</th>
				   <th onclick="sort(theTable,3,'int')">CPU预分配个数</th>                
                   <th onclick="sort(theTable,4,'int')">内存预分配大小</th>
                   <th onclick="sort(theTable,5,'int')">存储预分配大小</th>
                   <th onclick="sort(theTable,6,'int')">IP预分配个数</th>
                   <th onclick="sort(theTable,7,'string')">项目负责人</th>
                   <th onclick="sort(theTable,10,'string')">网络域</th>
                   <th onclick="sort(theTable,11,'string')">虚拟机个数</th>
                   <!-- <th onclick="sort(theTable,11,'date')">创建时间</th>
                   <th onclick="sort(theTable,12,'string')">创建人</th>
                   <th onclick="sort(theTable,13,'date')">修改时间</th>
                   <th onclick="sort(theTable,14,'string')">修改人</th> -->
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="userProjectList" id="theBean">
				<tr>
					<td><s:property value="#theBean.PROJECT_NO" /></td>
					<td><s:property value="#theBean.PROJECT_NAME" /></td>
					<td><s:property value="#theBean.CPU_COUNT" />核</td>
					<td><s:property value="@java.lang.Math@round(#theBean.MEMORY_SIZE /1024*100) / 100.0"/>G</td>
					<td><s:property value="@java.lang.Math@round(#theBean.STORAGE_SIZE /1024*100) / 100.0"/>G</td>
					<td><s:property value="#theBean.IP_COUNT" /></td> 
					<td><s:property value="#theBean.PROJECT_LEADER" /></td>
					<td><s:property value="#theBean.NETWORK_DOMAIN" /></td>
					<td>
					<s:if test="#theBean.vmCount != 0">
						<a href="javascript:;" onclick="showVMNews('<s:property value="#theBean.ID"/>');"><s:property value="#theBean.vmCount"/>个</a>
					</s:if><s:else>
						0个
					</s:else>
					</td>
					
					<!-- <td><s:property value="#theBean.CREATED_TIME" /></td>
					<td><s:property value="#theBean.CREATED_USER" /></td>
					<td><s:property value="#theBean.UPDATE_TIME" /></td>
					<td><s:property value="#theBean.UPDATE_USER" /></td> -->
				</tr>
			   </s:iterator>
			  </tbody>
			</table>
		</div>
       	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
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
