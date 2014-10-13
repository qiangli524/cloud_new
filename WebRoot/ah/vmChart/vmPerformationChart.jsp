<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>虚拟机报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script language="javascript" type="text/javascript"
	src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>

<style type="text/css">
.font-more {
	width: 180px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
#vmName{
	color:#808080; 
}
#top{
	margin-right: 10px;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#trid").hide();
		$("#dots").hide();
		$("#dotNum").hide();
		$("#memType").hide();
		$("#memTD").hide();
		initData();
		$("#query").click(function() {
			initData();
		});
		$("#resert").click(function() {
		// 重置
		$("#vmName").val("请选择虚拟机..");
		$("input[flag='text']").val("");
		$("#startDateId").val("");
		$("#endDateId").val("");
		$("#vmIds").attr("name","a");
		
		});
	});

	function initData() {
		$("#tab tr:not(:first)").remove();
		mask('正在查询,请稍后....','0.5','0px');
		var vmName = $("#vmName").val();
		var vmIds = $("#vmIds").attr("name");
		var busi = $("#busi").val();
		var top_line = $("#top_line").val();
		var cpu_mem = $("#cpu_mem").val();
		var cpuUsageType = $("#cpuUsageType").val();
		var cpuStartUsage = $("#cpuStartUsage").val();
		var cpuEndUsage = $("#cpuEndUsage").val();
		var memUsageType = $("#memUsageType").val();
		var memStartUsage = $("#memStartUsage").val();
		var memEndUsage = $("#memEndUsage").val();
		var startDate = $("#startDateId").val();
		var endDate = $("#endDateId").val();
		var center_uuid = $("#center_uuidd").val();
		var top_num = $("#topnum").val();
		var d = new Date();
		var dots = $("#fingdots").val();
		//查询CPU
		if (cpu_mem == "cpu") {
			//查top
			if (top_line == "top") {
				if(vmIds == "a"){
					vmIds="";
				}
				$.ajax({
							type : 'post',
							url : 'vmReportFormChart_showVmDetailInfo.do?dd='
									+ d + '&vf.vmId='
									+  vmIds
									+ '&vf.busi=' + encodeURI(encodeURI(busi))
									+ '&vf.cpuUsageType=' + cpuUsageType
									+ '&vf.cpuStartUsage=' + cpuStartUsage
									+ '&vf.cpuEndUsage=' + cpuEndUsage
									+ '&vf.startDate=' + startDate
									+ '&vf.endDate=' + endDate + '&vf.cpu_mem='
									+ cpu_mem + '&vf.top_num=' + top_num
									+ '&vf.top_line=' + top_line
									+ '&vf.center_uuid='+center_uuid,
							dataType : 'json',
							success : function(msg) {
								var chartXml = msg.chartXml;
								var chart1 = new FusionCharts(
										"FusionCharts/MSColumn3D.swf",
										"ChartId1", "100%", "400", "0", "0");
								chart1.setJSONData(chartXml);
								chart1.render("chartarea");
								var resultList = msg.resultList;
								if (resultList != null) {
									for ( var i = 0; i < resultList.length; i++) {
										$("<tr><td align=\"center\">"
														+ resultList[i].vmName
														+ "</td><td>"
														+ resultList[i].busiParent
														+ "</td><td>"
														+ resultList[i].ip
														+ "</td><td>"
														+ resultList[i].vmType
														+ "</td><td>"
														+ resultList[i].cpu_maxKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_avgKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_minKpiValue
														+ "</td><td>"
														+ resultList[i].mem_maxKpiValue
														+ "</td><td>"
														+ resultList[i].mem_avgKpiValue
														+ "</td><td>"
														+ resultList[i].mem_minKpiValue
														+ "</td><td>"
														+ resultList[i].storeAll
														+ "</td><td>"
														+ resultList[i].storeUsed_maxKpiValue
														+ "</td></tr>")
												.insertAfter(
														$("#tab tr:eq(" + i
																+ ")"));
									}
								}
								removeMask();
							}
						});
			} else {
				//折线图
				if(vmIds=="a"){
					removeMask();
					alert("请您填写虚拟机！");
					return false;
				}
				$.ajax({
					type : 'post',
					url : 'vmReportFormChart_showVmDetailInfo.do?dd=' + d
							+ '&vf.vmId=' + vmIds
							+ '&vf.busi=' + encodeURI(encodeURI(busi))
							+ '&vf.startDate=' + startDate + '&vf.endDate='
							+ endDate + '&vf.cpu_mem=' + cpu_mem
							+ '&vf.top_num=' + top_num + '&vf.top_line='
							+ top_line + '&vf.dots=' + dots,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId3", "100%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
						var resultList = msg.resultList;
								if (resultList != null) {
									for ( var i = 0; i < resultList.length; i++) {
										$("<tr><td align=\"center\">"
														+ resultList[i].vmName
														+ "</td><td>"
														+ resultList[i].busiParent
														+ "</td><td>"
														+ resultList[i].ip
														+ "</td><td>"
														+ resultList[i].vmType
														+ "</td><td>"
														+ resultList[i].cpu_maxKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_avgKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_minKpiValue
														+ "</td><td>"
														+ resultList[i].mem_maxKpiValue
														+ "</td><td>"
														+ resultList[i].mem_avgKpiValue
														+ "</td><td>"
														+ resultList[i].mem_minKpiValue
														+ "</td><td>"
														+ resultList[i].storeAll
														+ "</td><td>"
														+ resultList[i].storeUsed_maxKpiValue
														+ "</td></tr>")
												.insertAfter(
														$("#tab tr:eq(" + i
																+ ")"));
									}
								}
								removeMask();
					}
				});
			}
		}
		//查询内存
		if (cpu_mem == "mem") {
			//查top
			if (top_line == "top") {
				if(vmIds == "a"){
					vmIds="";
				}
				$.ajax({
							type : 'post',
							url : 'vmReportFormChart_showVmDetailInfo.do?dd='
									+ d + '&vf.vmId='
									+ vmIds
									+ '&vf.busi=' + encodeURI(encodeURI(busi))
									+ '&vf.memUsageType=' + memUsageType
									+ '&vf.memStartUsage=' + memStartUsage
									+ '&vf.memEndUsage=' + memEndUsage
									+ '&vf.startDate=' + startDate
									+ '&vf.endDate=' + endDate + '&vf.cpu_mem='
									+ cpu_mem + '&vf.top_num=' + top_num+'&vf.top_line='
									+ top_line + '&vf.dots=' + dots
									+'&vf.center_uuid='+center_uuid,
							dataType : 'json',
							success : function(msg) {
								//var i = 1;
								var chartXml = msg.chartXml;
								var chart1 = new FusionCharts(
										"FusionCharts/MSColumn3D.swf",
										"ChartId2", "100%", "300", "0", "0");
								chart1.setJSONData(chartXml);
								chart1.render("chartarea");
								var resultList = msg.resultList;
								if (resultList != null && chartXml!=null) {
									for ( var i = 0; i < resultList.length; i++) {
										$(
												"<tr><td align=\"center\">"
														+ resultList[i].vmName
														+ "</td><td>"
														+ resultList[i].busiParent
														+ "</td><td>"
														+ resultList[i].ip
														+ "</td><td>"
														+ resultList[i].vmType
														+ "</td><td>"
														+ resultList[i].cpu_maxKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_avgKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_minKpiValue
														+ "</td><td>"
														+ resultList[i].mem_maxKpiValue
														+ "</td><td>"
														+ resultList[i].mem_avgKpiValue
														+ "</td><td>"
														+ resultList[i].mem_minKpiValue
														+ "</td><td>"
														+ resultList[i].storeAll
														+ "</td><td>"
														+ resultList[i].storeUsed_maxKpiValue
														+ "</td></tr>")
												.insertAfter(
														$("#tab tr:eq(" + i
																+ ")"));
									}
								}
								removeMask();
							}
						});
			} else {
				//折线图
				if(vmIds=="a"){
					alert("请您填写虚拟机！");
					removeMask();
					return false;
				}
				$.ajax({
					type : 'post',
					url : 'vmReportFormChart_showVmDetailInfo.do?dd=' + d
							+ '&vf.vmId=' + vmIds
							+ '&vf.busi=' + encodeURI(encodeURI(busi))
							+ '&vf.startDate=' + startDate + '&vf.endDate='
							+ endDate + '&vf.cpu_mem=' + cpu_mem
							+ '&vf.top_num=' + top_num + '&vf.top_line='
							+ top_line + '&vf.dots=' + dots,
					dataType : 'json',
					success : function(msg) {
						var chartXml = msg.chartXml;
						var chart1 = new FusionCharts(
								"FusionCharts/ZoomLine.swf", "ChartId4", "100%",
								"300", "0", "0");
						chart1.setJSONData(chartXml);
						chart1.render("chartarea");
						var resultList = msg.resultList;
								if (resultList != null) {
									for ( var i = 0; i < resultList.length; i++) {
										$("<tr><td align=\"center\">"
														+ resultList[i].vmName
														+ "</td><td>"
														+ resultList[i].busiParent
														+ "</td><td>"
														+ resultList[i].ip
														+ "</td><td>"
														+ resultList[i].vmType
														+ "</td><td>"
														+ resultList[i].cpu_maxKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_avgKpiValue
														+ "</td><td>"
														+ resultList[i].cpu_minKpiValue
														+ "</td><td>"
														+ resultList[i].mem_maxKpiValue
														+ "</td><td>"
														+ resultList[i].mem_avgKpiValue
														+ "</td><td>"
														+ resultList[i].mem_minKpiValue
														+ "</td><td>"
														+ resultList[i].storeAll
														+ "</td><td>"
														+ resultList[i].storeUsed_maxKpiValue
														+ "</td></tr>")
												.insertAfter(
														$("#tab tr:eq(" + i
																+ ")"));
									}
								}
								removeMask();
					}
				});
			}

		}
		
	}
	function sel() {
		var cpu_mem = $("#cpu_mem").val();
		var top_line = $("#top_line").val();
		if (cpu_mem == "cpu" & top_line == "top") {
			$("#memType").hide();
			$("#memTD").hide();
			$("#cpuType").show();
			$("#cpuTD").show();
		} 
		if(cpu_mem == "mem" & top_line == "top"){
			$("#cpuType").hide();
			$("#cpuTD").hide();
			$("#memType").show();
			$("#memTD").show();
		}
	}

	function selChart() {
		
		var top_line = $("#top_line").val();
		if (top_line == "line") {
			//$("#top").fadeOut(1000);//淡出
			$("#top").slideUp(800);//向上淡出
			$("#dots").show();
			$("#dotNum").show();
			$("#memType").hide();
			$("#memTD").hide();
			$("#cpuType").hide();
			$("#cpuTD").hide();
			$("#center_uuidd").attr("disabled",true);
			$("#dataCenter").css("color", "#778899");
		} else {
			//$("#top").fadeIn(1000);//淡入
			$("#top").slideDown(800);//向下淡入
			$("#dots").hide();
			$("#dotNum").hide();
			$("#center_uuidd").attr("disabled",false);
			$("#dataCenter").css("color","#000000");
			
			var cm=$("#cpu_mem").val();
			if(cm == "cpu"){
				$("#cpuType").show();
				$("#cpuTD").show();
			}else{
				$("#memType").show();
				$("#memTD").show();
			}
		}

	}

	function addVm() {
		$.dialog({
			id : 'add',
			title : '添加虚拟机窗口',
			width : '900px',
			height : '704px',
			max : true,
			min : true,
			lock:true,
			content : 'url:vmReportFormChart_showVmDetailAdd.do'
		});
	}

	function addValue(vmNames2,vmIds2) {
		$("#vmName").val("");//先清空
		$("#vmName").val(vmNames2);
		$("#vmIds").val("");//先清空
		$("#vmIds").attr("name",vmIds2);
		//alert($("#vmIds").attr("name"));
	}
	//处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function forbidBackSpace(e) {
            var ev = e || window.event; //获取event对象 
            var obj = ev.target || ev.srcElement; //获取事件源 
            var t = obj.type || obj.getAttribute('type'); //获取事件源类型 
            //获取作为判断条件的事件类型 
            var vReadOnly = obj.readOnly;
            var vDisabled = obj.disabled;
            //处理undefined值情况 
            vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
            vDisabled = (vDisabled == undefined) ? true : vDisabled;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的， 
            //并且readOnly属性为true或disabled属性为true的，则退格键失效 
            var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == 

"textarea") && (vReadOnly == true || vDisabled == true);
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
            var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != 
"textarea";
            //判断 
            if (flag2 || flag1) return false;
        }
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        
        window.onload = function(){
        	var _mframe = window.parent.document.getElementById("mainIframe");
        	_mframe.height = '1000px';
        }
        
</script>
</head>
<body >
 <div class="scrollbody" style="height: 680px;" >
	<s:form action="vmReportFormChart_showVmDetailInfo.do" method="post" cssClass="theForm" id="theForm">
				<div class="pd-20 bgcolor-1">
					<h2 class="utt-1">虚拟机报表</h2>
       	 			<div class="bord-1 pd-10">
							<table width="100%" class="blue-table sorttable" border="0">
								<tr >
									<td>
										虚拟机名称:
									</td>
									<td colspan="15">
										<input id="vmName" style="height:26px;border:solid 1px #c3c3c3;width:80%;" class='fl' disabled="true" value="请选择虚拟机..">
										<input type="hidden" id="vmIds" name="a" Class="inpt-1" />
										<a href="#"	onclick="addVm();"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
											style="width: 20px;height: 20px;"> </a>
									</td>
								</tr>
								<tr>
									<td id="dataCenter">
										数据中心:
									</td>
									<td>
										<s:select list="vf.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
											name="vf.center_uuid" value="vf.center_uuid" cssClass="select-1 fl"></s:select>
									</td>
									<td>业务名称:</td>
									<td>
										<input id="busi"  style="height:26px;border:solid 1px #c3c3c3;width:90px;"
											flag="text"/>
									</td>
									<td >
										 <select id="top_line" Class="select-1 fl" onchange="selChart();" width="100%">
											<option value="top" selected="selected">Top图</option>
											<option value="line">走势图</option>
										 </select>
									</td>
									<td id="dotNum">呈现点数:</td>
									<td id="dots" ><select id="fingdots" width="100%" Class="select-1 fl">
											<option>100</option>
											<option selected="selected">150</option>
											<option>200</option>
											<option>300</option>
									</select>
									</td>
									<td ><select id="cpu_mem" Class="select-1 fl" onchange="sel();">
											<option value="cpu" selected="selected">CPU</option>
											<option value="mem">内存</option>
									</select>
									</td>
									<td id="cpuType"><select id="cpuUsageType" Class="select-1 fl">
											<option value="cpuMax" selected="selected">CPU最大值</option>
											<option value="cpuAvg">CPU平均值</option>
											<option value="cpuMin">CPU最小值</option>
									</select> ：</td>
									<td id="cpuTD"><input id="cpuStartUsage" style="height:26px;border:solid 1px #c3c3c3;width:30px;"
											value="0" flag="text"/> ~
										<input id="cpuEndUsage" style="height:26px;border:solid 1px #c3c3c3;width:30px;"
											value="100" flag="text"/>
									</td>
									<td id="memType"><select id="memUsageType" Class="select-1 fl">
											<option value="memMax" selected="selected">内存最大值</option>
											<option value="memAvg">内存平均值</option>
											<option value="memMin">内存最小值</option>
									</select> ：</td>
									<td id="memTD"><s:textfield id="memStartUsage"
											cssStyle="width:30px;" value="0" flag="text"></s:textfield> ~
										<s:textfield id="memEndUsage" cssStyle="width:30px;"
											value="100" flag="text"></s:textfield>
									</td>
								
								</tr>
								<tr>
									<td>时间: </td>
									<td colspan="10" align="left">
										<input id="startDateId" 
										name="vf.startDate" class="Wdate" style="height:26px;border:solid 1px #c3c3c3;"
										onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
										- <input id="endDateId"  name="vf.endDate"
										 class="Wdate"  style="height:26px;border:solid 1px #c3c3c3;"
										onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</td>
								</tr>
								<tr>
									<td colspan="10" class="btns">
											<span class="ubtn-1 mgl-20"><input type="button"  value="查询" id="query" /></span>
										    <span class="ubtn-2 mgl-20"><input type="button"  value="重置" id="resert" /></span>
									</td>
								</tr>
							</table>
						<div class="utt-2 mgt-20"></div>
						<div align="right" id="top">
							<font size="4">Top</font><input type="text" Class="inpt-1"
									style="height:26px;width:30px;color: red" id="topnum" value="10" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div id="chartarea" align="center"></div>
						<table id="tab" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
								<thead>
									<tr>
										<th>虚拟机名称</th>
										<th>业务子系统</th>
										<th>IP</th>
										<th>虚拟化类型</th>
										<th>cpu最大值</th>
										<th>cpu平均值</th>
										<th>cpu最小值</th>
										<th>内存最大值</th>
										<th>内存平均值</th>
										<th>内存最小值</th>
										<th>存储总量(G)</th>
										<th>存储使用量峰值(G)</th>	
									</tr>		
								</thead>
								<tbody>
								</tbody>	
						</table>		
					</div>
				</div>
		</s:form>
</div>
</body>
</html>
