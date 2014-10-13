<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <title>资源使用情况</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/temp/pub-ui/css/skin1/all.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/common.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <link href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css" rel="stylesheet"
          type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/all-min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/My97DatePicker/WdatePicker.js">

    </script>
    <script type="text/javascript">
        var vmCode = '<s:property value="obj.vmCode"/>';
        var connectCode = '<s:property value="obj.connectCode"/>';
        var hyId = connectCode + '_' + vmCode;
        var kpi;
        var motionName;
        $(document).ready(function () {
            insertFusionChartData();

            $("#cpu").click(function () {
                $("#cpu_dayChartContainer").slideToggle("slow");
            });

            $("#mem").click(function () {
                $("#mem_dayChartContainer").slideToggle("slow");
            });

            $("#disk").click(function () {
                $("#disk_dayChartContainer").slideToggle("slow");
            });

            $("#disk_usage").click(function () {
                $("#disk_usage_dayChartContainer").slideToggle("slow");
            });


        });

        function updateFusionChartData() {
            mask('正在加载数据,请稍后...', '0.5', '0px');

            var cycle_time = $("#time option:selected").val();
            var pointCount = $("#pointCount option:selected").val();
            var startDate = $("#startDateId").val();
            var endDate = $("#endDateId").val();
            //cpu使用率查询
            kpi = 'PM-V-01-010-11';
            motionName = 'CPU';
            $("#cpu_dayChartContainer").updateFusionCharts({
                dataSource: "hyMonitor_queryHyCMData_f.do?cycle_time=" + cycle_time + "&startDate=" + startDate + "&endDate=" + endDate + "&pointCount=" + pointCount
                        + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName
            });
            //内存使用率查询
            kpi = 'PM-V-01-010-12';
            motionName = 'MEMORY';
            $("#mem_dayChartContainer").updateFusionCharts({
                dataSource: "hyMonitor_queryHyCMData_f.do?cycle_time=" + cycle_time + "&startDate=" + startDate + "&endDate=" + endDate + "&pointCount=" + pointCount
                        + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName
            });
            //存储使用率查询
            kpi = 'PM-H-01-010-20';
            motionName = 'STORAGE';
            $("#disk_usage_dayChartContainer").updateFusionCharts({
                dataSource: "hyMonitor_queryHyCMData_f.do?cycle_time=" + cycle_time + "&startDate=" + startDate + "&endDate=" + endDate + "&pointCount=" + pointCount
                        + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName
            });
            //磁盘磁盘读写速率查询
            motionName = 'DISK';
            kpi = '';

            $("#disk_dayChartContainer").updateFusionCharts({
                dataSource: "hyMonitor_queryHyDNData_fusion.do?cycle_time=" + cycle_time + "&startDate=" + startDate + "&endDate=" + endDate + "&pointCount=" + pointCount
                        + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName
            });
            removeMask();
        }

        function insertFusionChartData() {
            //cpu使用率查询
            kpi = 'PM-V-01-010-11';
            motionName = 'CPU';
            $("#cpu_dayChartContainer").insertFusionCharts({
                swfUrl: "/cloud/FusionCharts/ZoomLine.swf",
                dataSource: "hyMonitor_queryHyCMData_f.do?cycle_time=oneHour" + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName,
                dataFormat: "jsonurl",
                //width: "730",
                height: "350",
                id: "cpu_dayChart"
            });
            //内存使用率查询
            kpi = 'PM-V-01-010-12';
            motionName = 'MEMORY';
            $("#mem_dayChartContainer").insertFusionCharts({
                swfUrl: "/cloud/FusionCharts/ZoomLine.swf",
                dataSource: "hyMonitor_queryHyCMData_f.do?cycle_time=oneHour" + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName,
                dataFormat: "jsonurl",
                //width: "730",
                height: "350",
                id: "mem_dayChart"
            });
            //磁盘使用率查询
            kpi = 'PM-H-01-010-20';
            motionName = 'STORAGE';
            $("#disk_usage_dayChartContainer").insertFusionCharts({
                swfUrl: "/cloud/FusionCharts/ZoomLine.swf",
                dataSource: "hyMonitor_queryHyCMData_f.do?cycle_time=oneHour" + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName,
                dataFormat: "jsonurl",
                //width: "730",
                height: "350",
                id: "disk_usage_dayChart"
            });
            //磁盘读写速率查询
            motionName = 'DISK';
            kpi = '';
            $("#disk_dayChartContainer").insertFusionCharts({
                swfUrl: "/cloud/FusionCharts/ZoomLine.swf",
                dataSource: "hyMonitor_queryHyDNData_fusion.do?cycle_time=oneHour" + "&hyId=" + hyId + "&kpi=" + kpi + "&motionName=" + motionName,
                dataFormat: "jsonurl",
                //width: "730",
                height: "350",
                id: "disk_dayChart"
            });


        }

        function chanageRq(obj) {
            $("#dateBoxId").hide();
            if (obj.value == 'selfDate') {
                $("#dateBoxId").show();
            }
        }
    </script>
</head>

<body>

<!--标题 start-->
<div class="box on" style="display: block;margin: 10px">
    <p class="title-common8">资源使用情况查看</p>

    <div class="border-2 pd-10 clearfix">
        选择时间：
        <select onchange="chanageRq(this)" id="time">
            <option value="oneHour" selected="selected">1小时</option>
            <option value="fiveHour">5小时</option>
            <option value="thisMonth">本月</option>
            <option value="lastMonth">上月</option>
            <option value="selfDate">自定义</option>
        </select>
		
			
		<span id="dateBoxId" style="display:none;">
				<input id="startDateId" style="txt" type="text" name="startDate" size="22" class="Wdate"
                       onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
   		~
		<input id="endDateId" style="txt" type="text" name="endDate" size="22" class="Wdate"
               onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		</span>
        <a id="search-btn" style="cursor:pointer" onclick="javascript:updateFusionChartData()"
           class="btn-w4"><span>查看</span></a>
    </div>
</div>
<!--标题 end-->
<div class="pdt-10 pdb-10 pdl-10 pdr-10">
    <div class="ued-grid mgb-10">
        <div class="ued-g50 ued-gl">
            <div class="ued-gbox">
                <div class="pdr-5 ued-panel-4" data-role="ued-panel">
                    <h3>
                        <a class="font-gray1" style="cursor:default;text-decoration:none">CPU使用情况</a>
							<span> <a class="tool max"> </a> <a class="tool collapse">
                            </a> <a class="tool close"> </a> </span>
                    </h3>

                    <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                         style="height:350px">
                        <div id="cpu_dayChartContainer"></div>
                    </div>
                </div>
                <div class="pdr-5 ued-panel-4 mgt-10" data-role="ued-panel">
                    <h3>
                        <a class="font-gray1" style="cursor:default;text-decoration:none">内存使用情况</a>
							<span> <a class="tool max"> </a> <a class="tool collapse">
                            </a> <a class="tool close"> </a> </span>
                    </h3>

                    <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                         style="height:350px">
                        <div id="mem_dayChartContainer"></div>
                    </div>
                </div>
            </div>
        </div>
        <div class="ued-g50 ued-gr">
            <div class="ued-gbox">
                <div class="ued-panel-4 pdl-5" data-role="ued-panel">
                    <h3>
                        <a class="font-gray1" style="cursor:default;text-decoration:none">存储使用情况</a>
							<span> <a class="tool max"> </a> <a class="tool collapse">
                            </a> <a class="tool close"> </a> </span>
                    </h3>

                    <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                         style="height:350px">
                        <div id="disk_usage_dayChartContainer"></div>
                    </div>
                </div>
                <div class="ued-panel-4 pdl-5 mgb-10 mgt-10" data-role="ued-panel">
                    <h3>
                        <a class="font-gray1" style="cursor:default;text-decoration:none"> 磁盘读写速率</a>
							<span> <a class="tool max"> </a> <a class="tool collapse">
                            </a> <a class="tool close"> </a> </span>
                    </h3>

                    <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                         style="height:350px">
                        <div id="disk_dayChartContainer"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>