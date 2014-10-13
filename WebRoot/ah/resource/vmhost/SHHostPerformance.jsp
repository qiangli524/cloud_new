<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/sort/jquery.tablesorter.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>

<title></title>
	<style type="text/css">
		div.hidden{
		width:112px;
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
	$(function(){
        $("#theTable").tablesorter(); 
		
		$("#query").click(function(){
			mask('正在查询,请稍后....','0.5','0px');
			$("#theForm").submit();
		});
		
		$("#resert").click(function(){
			   $("input[flag='text']").val("");
			   $(".select-1").find("option[value='1']").attr("selected",true);
			   $("[dis]").attr("disabled","disabled");
			});
		
	})
      function chanageRq(obj){
      	$("#dateBoxId").hide();
      	if(obj.value == 'selfDate'){
      		$("#dateBoxId").show();
      	}
      }
	
	function chanagesUsage(obj){
		if(obj.value==1){
			$(obj).parent().children("[dis]").attr("disabled","disabled");
		}else{
			$(obj).parent().children("[dis]").removeAttr("disabled");
		}
	}
	
   
    function showHyMonitorPerformationTabs(eqId){
    	var startDate = $("#startDateId").val();
    	var endDate = $("#endDateId").val();
    	$.dialog({
    		id:'showHyMonitorPerformationTabs',
    		title:'主机性能明细',
			width: '800px',
			height: '500px',
			lock:true,
			//content: 'url:ah-hyMonitor_hyMonitorPerformationTabs.do?hyId='+hyId+'&startDate='+startDate+'&endDate='+endDate
		    content:'url:hostMonitor_highChartsTabs.do?id='+eqId+'&startDate='+startDate+'&endDate='+endDate+'&flag=report'
		});
    }
    function listExp(){
    	mask('正在查询,请稍后....','0.5','0px');
    	var url = "vmReportForm_sh_hostPerformanceExport.do?"+$("#theForm").serialize()
		exportForm.action = url;
		exportForm.submit();
		removeMask();
    }
    
    $(function(){
    	var isHide=$("#isHide").val();
    	if(isHide==1){
    		$("[name='hiddend']").show();
    	}else{
    		$("[name='hiddend']").hide();
    	}
    });
    
</script>
</head>
<body  class="pop-body scrollbody">
<div class="mainbody" >
<s:form action="vmReportForm_SHQueryHostPerformance.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="isHide" id="isHide"></s:hidden>
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">宿主机报表</h2>
        <div class="bord-1 pd-10" >
                <table  width="100%" class="blue-table sorttable" border="0" >
                  <tr>
	                  <td width="15%">
						数据中心:
					  </td>
					  <td align="left"  colspan="3">
						<s:select list="vf.dataCenterList" id="center_uuidd" headerKey="" headerValue="---所有---" listKey="uuid" listValue="name"
							name="vf.center_uuid" value="vf.center_uuid" cssClass="select-1"></s:select>
					  </td>
				  </tr>
				  
				  <tr>
                    <td width="15%" >主机名称:</td>
                    <td align="left" colspan="3">
                    	<s:textfield name="vf.vmName" cssStyle="width:150px;"  flag="text"></s:textfield>
                    </td>
                  </tr>
<%--                  <tr>--%>
<%--                   <td width="15%">CPU性能：</td>--%>
<%--                    <td align="left">--%>
<%--                     <select onchange="chanagesUsage(this)" name="vf.cpuUsageType" class="select-1">--%>
<%--                        <option value="1" selected="selected">无限制</option>--%>
<%--					    <option value="cpuMax">CPU最大值</option>--%>
<%--			  			<option value="cpuAvg">CPU平均值</option>--%>
<%--			  			<option value="cpuMin">CPU最小值</option>--%>
<%--			  		 </select>--%>
<%--                      <s:textfield name="vf.cpuStartUsage" cssStyle="width:70px;" dis="cpu" ></s:textfield>--%>
<%--			  		  ~--%>
<%--			  		  <s:textfield name="vf.cpuEndUsage" cssStyle="width:70px;" dis="cpu"></s:textfield>--%>
<%--			  		</td>--%>
<%--			  		<td width="15%">内存性能：</td>--%>
<%--			  		<td align="left">--%>
<%--                     <select onchange="chanagesUsage(this)" name="vf.memUsageType" class="select-1">--%>
<%--                       <option value="1" selected="selected">无限制</option>--%>
<%--					    <option value="memMax">内存最大值</option>--%>
<%--			  			<option value="memAvg">内存平均值</option>--%>
<%--			  			<option value="memMin">内存最小值</option>--%>
<%--			  		 </select>--%>
<%--			  		  <s:textfield  name="vf.memStartUsage" cssStyle="width:70px;" dis="mem"></s:textfield>--%>
<%--			  		  ~--%>
<%--			  		  <s:textfield  name="vf.memEndUsage" cssStyle="width:70px;" dis="mem"></s:textfield>--%>
<%--			  		</td>--%>
<%--			  		</tr>--%>
<%--			  		<tr>--%>
<%--			  		<td width="15%">磁盘kbps：</td>--%>
<%--			  		<td align="left">--%>
<%--                     <select onchange="chanagesUsage(this)" name="vf.diskUsageType" class="select-1">--%>
<%--                        <option value="1" selected="selected">无限制</option>--%>
<%--					    <option value="diskRMax">磁盘读最大值</option>--%>
<%--			  			<option value="diskRMin">磁盘读最小值</option>--%>
<%--			  			<option value="diskWMax">磁盘写最大值</option>--%>
<%--			  			<option value="diskWMin">磁盘写最小值</option>--%>
<%--			  		 </select>--%>
<%--			  		  <s:textfield  name="vf.diskStartUsage" cssStyle="width:70px;" dis="disk"></s:textfield>--%>
<%--			  		  ~--%>
<%--			  		  <s:textfield  name="vf.diskEndUsage" cssStyle="width:70px;" dis="disk"></s:textfield>--%>
<%--			  		</td>--%>
<%--			  		<td width="15%">网络kbps：</td>--%>
<%--			  		<td align="left">--%>
<%--                     <select onchange="chanagesUsage(this)" name="vf.networkUsageType" class="select-1">--%>
<%--                        <option value="1" selected="selected">无限制</option>--%>
<%--					    <option value="networkUpMax">网络上行最大值</option>--%>
<%--			  			<option value="networkUpMin">网络上行最小值</option>--%>
<%--			  			<option value="networkDownMax">网络下行最大值</option>--%>
<%--			  			<option value="networkDownMin">网络下行最小值</option>--%>
<%--			  		 </select>--%>
<%--			  		  <s:textfield  name="vf.networkStartUsage" cssStyle="width:70px;" dis="network"></s:textfield>--%>
<%--			  		  ~--%>
<%--			  		  <s:textfield  name="vf.networkEndUsage" cssStyle="width:70px;" dis="network"></s:textfield>--%>
<%--			  		</td>--%>
<%--			  	   </tr>--%>
                   <tr>
			  		<td width="15%">统计周期：</td>
			  		<td align="left" colspan="3">
			  		<input id="startDateId" type="text" name="vf.startDate" size="30"  class="Wdate" value="${vf.startDate }"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		/>
						   		~
					<input id="endDateId" type="text"  name="vf.endDate" size="30"  class="Wdate" value="${vf.endDate }"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		/>
					</td>
                  </tr>
                  <tr>
                    <td colspan="10" class="btns">
                        <div align="center">
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="query"/></span>
							<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resert"/></span>
                        </div>
                    </td>
                  </tr>
                </table>
                <div class="utt-2"  name="hiddend">
                	<a class="" href="javascript:void(0)" onclick = "listExp();"  >导出</a>
                </div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0"  name="hiddend">
				  <thead>
					  <tr>
							<th>主机名称</th>
<%--							<th>业务子系统</th>--%>
<%--							<th>承载业务</th>--%>
						<!-- 	<th>网络域</th> -->
							<th>IP</th>
							<th>主机类型</th>
							<th>
								cpu配置					
							</th>
							<th>
								cpu Max					
							</th>
							<!-- <th>
								cpu平均值					
							</th>
							<th>
								cpu最小值						
							</th> -->
							<th>
								内存配置					
							</th>
							<th>
								内存 Max						
							</th>
							<th>
							            磁盘读 Max					
							</th>
							<th>
								磁盘写 Max					
							</th>
							<th>
								网络上行Max					
							</th>
							<th>
								网络下行Max				
							</th>
							<!-- <th>
								内存平均值						
							</th>
							<th>
								内存最小值						
							</th> -->
<%--							<th>--%>
<%--								存储总量(G)						--%>
<%--							</th>--%>
<%--							<th>--%>
<%--								存储使用量峰值(G)					--%>
<%--							</th>--%>
<%--							<th>--%>
<%--								存储使用率						--%>
<%--							</th>--%>
							<th>
								操作						
							</th>
				    </tr>
				</thead>
				<tbody>
				  <s:iterator value="vmReportFormList" id="theBean">
				  	<tr>
				  		<td> 
				  			<div class="hidden" title='<s:property value="#theBean.hostName"/>'>
								<s:property value="#theBean.hostName"/>
							</div>
				  		</td>
<%--				  		<td>--%>
<%--				  		    <s:property value="#theBean.busiParent" default="-"/>--%>
<%--	                    </td>--%>
<%--				  		<td>--%>
<%--				  		    <s:property value="#theBean.busi" default="-"/>--%>
<%--	                    </td>--%>
				  		<%-- <td>
				  		    <s:property value="#theBean.network" default="-"/>
	                    </td> --%>
				  		<td>
				  			<s:property value="#theBean.ip" default="-"/>
				  		</td>
				  		<td> 
			  				<s:property value="#theBean.hostType" default="-"/>
				  		</td>
				  		<td>
	                        <s:property value="#theBean.cpuAll" default="-"/>
						</td>
				  		<td>
	                        <s:property value="#theBean.cpu_maxKpiValue" default="-"/>
						</td>
						<%-- <td>
	                        <s:property value="#theBean.cpu_avgKpiValue" default="-"/>
						</td>
						<td>
	                        <s:property value="#theBean.cpu_minKpiValue" default="-"/>
						</td> --%>
				  		<td>
	                        <s:property value="#theBean.memAll" default="-"/>
						</td>
				  		<td>
	                        <s:property value="#theBean.mem_maxKpiValue" default="-"/>
						</td>
						<%-- <td>
	                        <s:property value="#theBean.mem_avgKpiValue" default="-"/>
						</td>
						<td>
	                        <s:property value="#theBean.mem_minKpiValue" default="-"/>
						</td> --%>
<%--						<td>--%>
<%--	                        <s:property value="#theBean.storeAll" default="-"/>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--	                        <s:property value="#theBean.storeUsed_maxKpiValue" default="-"/>--%>
<%--						</td>--%>
						<td>
						     <s:property value="#theBean.disk_ioReadMaxValue" default="-"/>
						</td>
						<td>
						     <s:property value="#theBean.disk_ioWriteMaxValue" default="-"/>
						</td>
						<td>
						     <s:property value="#theBean.network_ioUpMaxValue" default="-"/>
						</td>
						<td>
						     <s:property value="#theBean.network_ioDownMaxValue" default="-"/>
						</td>
						<td>
							<a href="javascript:showHyMonitorPerformationTabs('<s:property value="#theBean.eqId"/>')">明细</a>
						</td>
				  	 </tr>
				  	</s:iterator>
				 </tbody>
				</table>
				<div class="pages mgb-10" name="hiddend"><!-- 分页 -->
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>
			</div>
			<div style="height:10px;"></div>
			<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
	</div>
</s:form>
</div>
<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
