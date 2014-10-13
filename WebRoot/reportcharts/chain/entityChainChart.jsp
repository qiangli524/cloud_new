<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>虚拟机报表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
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
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
	var vmIdsParam = '';
	
	$(function() {
		$("#trid").hide();
		$("#dots").hide();
		$("#dotNum").hide();
		$("#memType").hide();
		$("#memTD").hide();
		
		//插入站位报表
		var chartXml = '{}';
		var chart1 = new FusionCharts(
				"FusionCharts/MSLine.swf", "ChartId5", "100%",
				"300", "0", "0");
		chart1.setJSONData(chartXml);
		chart1.render("chartarea");
		
		$("#query").click(function() {
			initData(vmIdsParam);
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

	function initData(vmIds) {
		if (vmIds == '') {
			return;
		}
		vmIdsParam = vmIds;
		$("#tab tr:not(:first)").remove();
		mask('正在查询,请稍后....','0.5','0px');
		var vmName = $("#vmName").val();
		//var vmIds = 'VCENTER.m0_vm-109,VCENTER.m0_vm-154,VCENTER.m0_vm-116';
		var top_line ='line';
		var cpu_mem = $("#cpu_mem").val();
		var cpuUsageType = $("#cpuUsageType").val();
		var cpuStartUsage = $("#cpuStartUsage").val();
		var cpuEndUsage = $("#cpuEndUsage").val();
		var memUsageType = $("#memUsageType").val();
		var memStartUsage = $("#memStartUsage").val();
		var memEndUsage = $("#memEndUsage").val();
		var startDate = $("#startDateId").val();
		var endDate = $("#endDateId").val();
		var d = new Date();
		var dots = $("#fingdots").val();
		//查询CPU
		if (cpu_mem == "cpu") {
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
						+ '&vf.startDate=' + startDate + '&vf.endDate='
						+ endDate + '&vf.cpu_mem=' + cpu_mem
						+ '&vf.top_line='
						+ top_line + '&vf.dots=' + dots,
				dataType : 'json',
				success : function(msg) {
					var chartXml = msg.chartXml;
					var chart1 = new FusionCharts(
							"FusionCharts/MSLine.swf", "ChartId3", "100%",
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
		} else if (cpu_mem == "mem") {	//查询内存
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
						+ '&vf.startDate=' + startDate + '&vf.endDate='
						+ endDate + '&vf.cpu_mem=' + cpu_mem
						+ '&vf.top_line='
						+ top_line + '&vf.dots=' + dots,
				dataType : 'json',
				success : function(msg) {
					var chartXml = msg.chartXml;
					var chart1 = new FusionCharts(
							"FusionCharts/MSLine.swf", "ChartId4", "100%",
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
        var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vDisabled == true);
        //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效 
        var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea";
        //判断 
        if (flag2 || flag1) return false;
    }
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    
    //弹出实体选择界面
    function gotoEntityTreePage(type) {
    	$.dialog({
			id : 'add',
			title : '添加虚拟机窗口',
			width : '800px',
			height : '504px',
			max : true,
			lock :true,
			content : 'url:entitychart_intoEntityTreePage.do?flag='+type
		});
    }
    
    window.onload = function(){
    	var _mframe = window.parent.document.getElementById("mainIframe");
    	_mframe.height = '1000px';
    }
</script>
</head>
<body style="height: 1000px">
	<div class="mainbody">
		<s:form action="vmReportFormChart_showVmDetailInfo.do" method="post"
			cssClass="theForm" id="theForm">
			<div class="pd-20 bgcolor-1">
				<div class="box on">
					<h2 class="utt-1">性能报表</h2>
					<div class="bord-1 pd-10">
					<table width="100%" class="blue-table sorttable" border="0">
								<tr >
									<td>
										呈现点数:
									</td>
									<td colspan="5">
										<select id="fingdots"
											width="100%" class="select-1">
											<option>100</option>
											<option selected="selected">150</option>
											<option>200</option>
											<option>300</option>
										</select>
									</td>
									<td id="dataCenter">
										指标:
									</td>
									<td>
										<select id="cpu_mem"
											onchange="sel();" class="select-1">
											<option value="cpu" selected="selected">CPU</option>
											<option value="mem">内存</option>
										</select>
									</td>
									<td>CPU:</td>
									<td>
										<select id="cpuUsageType"
											class="select-1">
											<option value="cpuMax" selected="selected">CPU最大值</option>
											<option value="cpuAvg">CPU平均值</option>
											<option value="cpuMin">CPU最小值</option>
										</select>
									</td>
									<td >
										 <select id="top_line" Class="select-1 fl" onchange="selChart();" width="100%">
											<option value="top" selected="selected">Top图</option>
											<option value="line">走势图</option>
										 </select>
									</td>
									<td id="dotNum">内存:</td>
									<td id="dots" >
									 <select id="memUsageType"
										class="select-1">
										<option value="memMax" selected="selected">内存最大值</option>
										<option value="memAvg">内存平均值</option>
										<option value="memMin">内存最小值</option>
									</select>
									</td>
								</tr>
								<tr>
									<td>
										选择虚拟机
									</td>
									<td id="cpuType" colspan="5">
									<a href="#"	onclick="gotoEntityTreePage('vm')"><img src="<%=request.getContextPath()%>/graph/editors/images/zoom.gif"
											style="width: 20px;height: 20px;"> </a>
									</td>
									<td>时间: </td>
									<td colspan="5" align="left">
										<input id="startDateId"
											type="text" name="vf.startDate" size="20" class="Wdate"
											value="${vf.startDate }"
											style="height:26px;border:solid 1px #c3c3c3;"
											onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
										~ <input id="endDateId" type="text" name="vf.endDate" size="20"
											class="Wdate" value="${vf.endDate }"
											style="height:26px;border:solid 1px #c3c3c3;"
											onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</td>
									<td colspan="5" class="btns">
											<span class="ubtn-1 mgl-10"><input type="button"
									 value="查询" id="query" />
									</span> <span class="ubtn-1 mgl-10"><input type="button"
										value="重置" id="resert" />
									</span>
									</td>
								</tr>
							</table>
						<div class="utt-2 mgt-20"></div>
						<div id="chartarea" align="center"></div>

						<table id="tab" width="100%" class="blue-table sorttable"
							border="0" cellspacing="0">
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
