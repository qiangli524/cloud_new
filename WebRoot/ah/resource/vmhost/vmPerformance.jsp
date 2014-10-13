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
<script type="text/javascript">
	$(function(){
        $("#theTable").tablesorter(); 
		
		$("#query").click(function(){
			mask('正在查询,请稍后....','0.5','0px');
			$("#theForm").submit();
		});
		
		$("#resert").click(function(){
			   $("input[flag='text']").val("");
			});
		
	})
      function chanageRq(obj){
      	$("#dateBoxId").hide();
      	if(obj.value == 'selfDate'){
      		$("#dateBoxId").show();
      	}
      }
	
   
    function showHyMonitorPerformationTabs(hyId){
    	var startDate = $("#startDateId").val();
    	var endDate = $("#endDateId").val();
    	$.dialog({
    		id:'showHyMonitorPerformationTabs',
    		title:'虚拟机性能明细',
			min:false,
			width: '800px',
			height: '500px',
			lock:true,
			content: 'url:ah-hyMonitor_hyMonitorPerformationTabs.do?hyId='+hyId+'&startDate='+startDate+'&endDate='+endDate
		});
    }
    function listExp(){
    	var url = "vmReportForm_vmPerformanceExport.do?"+$("#theForm").serialize()
		exportForm.action = url;
		exportForm.submit();
    }
    
   
    
    
</script>
</head>
<body  class="pop-body scrollbody">
<div class="mainbody">
<s:form action="vmReportForm_vmPerformance.do" method="post" cssClass="theForm" id="theForm">
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">虚拟机报表</h2>
        <div class="bord-1 pd-10" >
                <table  width="100%" class="blue-table sorttable" border="0" >
                  <tr>
                  	<td align="right">
								数据中心:
					</td>
					<td>
						<s:select list="vf.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
							name="vf.center_uuid" value="vf.center_uuid" cssClass="select-1"></s:select>
					</td>
                    <td align="right">虚拟机名称:</td>
                    <td>
                    	<s:textfield name="vf.vmName" cssStyle="width:100px;"  flag="text"></s:textfield>
                    </td>
                    <td align="right">业务名称:</td>
                    <td>
                    	<s:textfield name="vf.busi" cssStyle="width:100px;" flag="text"></s:textfield>
                    </td>
                   
                    <td align="right">
                     <select onchange="chanagesUsage(this)" name="vf.cpuUsageType" class="select-1">
					    <option value="cpuMax" selected="selected">CPU最大值</option>
			  			<option value="cpuAvg">CPU平均值</option>
			  			<option value="cpuMin">CPU最小值</option>
			  		 </select>
			  		：
                    </td>
                    <td id="cpuTD" >
                      <s:textfield name="vf.cpuStartUsage" cssStyle="width:30px;"></s:textfield>
			  		  ~
			  		  <s:textfield name="vf.cpuEndUsage" cssStyle="width:30px;"></s:textfield>
			  		</td>
			  		<td align="right">
                     <select onchange="chanagesUsage(this)" name="vf.memUsageType" class="select-1">
					    <option value="memMax" selected="selected">内存最大值</option>
			  			<option value="memAvg">内存平均值</option>
			  			<option value="memMin">内存最小值</option>
			  		 </select>
			  		：
                    </td>
			  		<td id="memTD">
			  		  <s:textfield  name="vf.memStartUsage" cssStyle="width:30px;"></s:textfield>
			  		  ~
			  		  <s:textfield  name="vf.memEndUsage" cssStyle="width:30px;"></s:textfield>
			  		</td>
			  	   </tr>
                   <tr>
			  		<td align="right">时间：</td>
			  		<td colspan="10" align="left">
			  		<input id="startDateId" type="text" name="vf.startDate" size="20"  class="Wdate" value="${vf.startDate }"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		/>
						   		~
					<input id="endDateId" type="text"  name="vf.endDate" size="20"  class="Wdate" value="${vf.endDate }"
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
                <div class="utt-2">
                	<a class="" href="javascript:void(0)" onclick = "listExp();" >导出</a>
                </div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
				  <thead>
					  <tr>
							<th>虚拟机名称</th>
							<th>业务子系统</th>
							<th>承载业务</th>
						<!-- 	<th>网络域</th> -->
							<th>IP</th>
							<th>虚拟化类型</th>
							<th>
								cpu配置					
							</th>
							<th>
								cpu最大值					
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
								内存最大值						
							</th>
							<!-- <th>
								内存平均值						
							</th>
							<th>
								内存最小值						
							</th> -->
							<th>
								存储总量(G)						
							</th>
							<th>
								存储使用量峰值(G)					
							</th>
							<th>
								存储使用率						
							</th>
							<th>
								操作						
							</th>
				    </tr>
				</thead>
				<tbody>
				  <s:iterator value="vmReportFormList" id="theBean">
				  	<tr>
				  		<td> 
				  			<s:property value="#theBean.vmName" default="-"/>
				  		</td>
				  		<td>
				  		    <s:property value="#theBean.busiParent" default="-"/>
	                    </td>
				  		<td>
				  		    <s:property value="#theBean.busi" default="-"/>
	                    </td>
				  		<%-- <td>
				  		    <s:property value="#theBean.network" default="-"/>
	                    </td> --%>
				  		<td>
				  			<s:property value="#theBean.ip" default="-"/>
				  		</td>
				  		<td> 
			  				<s:property value="#theBean.vmType" default="-"/>
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
						<td>
	                        <s:property value="#theBean.storeAll" default="-"/>
						</td>
						<td>
	                        <s:property value="#theBean.storeUsed_maxKpiValue" default="-"/>
						</td>
						<td><br /></td>
						<td>
							<a href="javascript:showHyMonitorPerformationTabs('<s:property value="#theBean.vmId"/>')">明细</a>
						</td>
				  	 </tr>
				  	</s:iterator>
				 </tbody>
				</table>
				<div class="pages mgb-10"><!-- 分页 -->
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
