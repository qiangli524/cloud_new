<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@    taglib prefix="s" uri="/struts-tags" %>
<head>
    <title>资源使用情况</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/temp/pub-ui/css/skin1/all.css"></link>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/default.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/publicCloud/pub-ui/css/vmdetails.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/images/nresources/ziyuan-css.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/common.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/all-min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/temp/pub-ui/js/plugin/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/ui.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/publicCloud/pub-ui/js/order.js"></script>

    <script type="text/javascript">
        var vmCode, connectCode, hyId, kpi, motionName;
        $(document).ready(function () {
            vmCode = $("#vmCode").val();
            connectCode = $("#connectCode").val();
            hyId = connectCode + '_' + vmCode;

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
<s:hidden name="obj.VH_UUID" id="vmCode"></s:hidden>
<s:hidden name="obj.connectId" id="connectCode"></s:hidden>
<!--container star-->
<div class="container">
<!--col-c7 star-->
<div class="col-c7">
<!--left star-->
<jsp:include page="/publicCloud/pub-page/order.jsp"/>
<!--left end-->
<!--main-c1 star-->
<div class="main-c1 fr">
<!--标题 star-->
<h2 class="title-common6">
    <a href="#" class="home"></a><img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/title-img2.gif"
                                      width="65" height="65"/>
    云主机详情
</h2>
<!--标题 start-->
<div class="box on">
    <p class="title-common8">基本信息</p>

    <div class="border-2 pd-10 clearfix">
        <table class="ecs-vm-detail" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">ID：</label>
                        <span class="col-value">${obj.VH_ID}&nbsp;</span>
                    </p>
                </td>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">主机名称：</label>
                        <span class="col-value">${obj.VH_NAME}&nbsp;</span>
                    </p>
                </td>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">状态：</label>
						        			<span class="col-value">
							        			<s:if test="#obj.VH_STAT==1">
                                                    <img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/run.gif"
                                                         alt="正在运行"/>
                                                </s:if>
		                            			<s:else>
                                                    <img src="<%=request.getContextPath()%>/publicCloud/pub-ui/images/stoprun.gif"
                                                         alt="已停止"/>
                                                </s:else>
	                            			</span>
                    </p>
                </td>
                <%--<td class="vm-detail-action">
                </td>
            --%></tr>
        </table>
    </div>
</div>
<!--标题 end-->
<!--标题 start-->
<div class="box on">
    <p class="title-common8">配置信息</p>

    <div class="border-2 pd-10 clearfix">
        <table class="ecs-vm-detail" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">CPU：</label>
						        			<span class="col-value">
							        			${obj.VH_CPU}核&nbsp;
						        			</span>
                    </p>

                    <p>
                        <label class="text-muted">操作系统：</label>
                        <span class="col-value">${obj.VH_SYSTEM}&nbsp;</span>
                    </p>

                    <p>
                        <label class="text-muted">带宽计费方式：</label>
                        <span class="col-value">-</span>
                    </p>


                </td>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">内存：</label>
						        			<span class="col-value">
												${obj.VH_MEM} G&nbsp;
                            				</span>
                    </p>

                    <p>
                        <label class="text-muted">公网IP：</label>
						        			<span class="col-value">
						        				${obj.public_ip}&nbsp;
                            				</span>
                    </p>

                    <p>
                        <label class="text-muted">内网IP：</label>
						        			<span class="col-value">
						        				${obj.VH_IP}  &nbsp;	
											</span>
                    </p>

                </td>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">存储：</label>
						        			<span class="col-value">
						        				${obj.VH_STORAGE} G&nbsp;	
											</span>
                    </p>

                    <p>
                        <label class="text-muted">地域：</label>
						        			<span class="col-value">
							        			<s:if test="#session.areaid == '1'">
                                                    北京东部(EAST)
                                                </s:if>
							        			<s:else>
                                                    北京西部(WEST)
                                                </s:else>
				        					</span>
                    </p>

                    <p>&nbsp;
                    </p>
                </td>

                <%--<td class="vm-detail-action"></td>
            --%></tr>
        </table>
    </div>
</div>
<!--标题 end-->
<!--标题 start-->
<div class="box on">
    <p class="title-common8">付费信息</p>

    <div class="border-2 pd-10 clearfix">
        <table class="ecs-vm-detail" cellpadding="0" cellspacing="0" width="100%">
            <tr>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">付费方式：</label>
                        <span class="col-value">${obj.VH_STATUS} &nbsp;</span>
                    </p>

                    <p>
                        <label class="text-muted">上周消费金额：</label>
                        <span class="col-value">-</span>
                    </p>
                </td>
                <td class="vm-detail-col">
                    <p>
                        <label class="text-muted">创建时间：</label>
                        <span class="col-value">${obj.VH_STARTED} &nbsp;</span>
                    </p>

                    <p>
                        <label class="text-muted">到期时间：</label>
                        <span class="col-value">${obj.VH_COMPLETED} &nbsp;</span>
                    </p>
                </td>
                <td class="vm-detail-action">
                    <div>
						        			<span>
						        				<a href="#" class="btn-link" target="_blank">续费</a>
						        			</span> 
						        			<span>
						        				<a href="#" class="btn-link" target="_blank">购买相同配置</a>
						        			</span>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>
<!--标题 end-->
<!--标题 start-->
<div class="box on">
    <p class="title-common8">监控信息</p>

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

<!--tab-content star-->
<div class="tab-content border-2 pdtb-10">
    <!--box star-->
    <div class="box" style="display: block;">
        <!-- 监控start -->
        <div class="pdt-10 pdb-10 pdl-10 pdr-10">
            <div class="ued-grid mgb-10">
                <div class="ued-grid ued-gl">
                    <div class="ued-gbox">
                        <div class="pdr-5 ued-panel-4" data-role="ued-panel">
                            <h3>
                                <a class="font-gray1" style="cursor:default;text-decoration:none">CPU使用情况</a>
											<span> <!--<a class="tool max"> </a>--> <a class="tool collapse">
                                            </a> <a class="tool close"> </a> </span>
                            </h3>

                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                                 style="height:350px">
                                <div id="cpu_dayChartContainer"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ued-grid mgb-10">
                <div class="ued-grid ued-gl">
                    <div class="ued-gbox">
                        <div class="pdr-5 ued-panel-4 mgt-10" data-role="ued-panel">
                            <h3>
                                <a class="font-gray1" style="cursor:default;text-decoration:none">内存使用情况</a>
											<span> <!--<a class="tool max"> </a>--> <a class="tool collapse">
                                            </a> <a class="tool close"> </a> </span>
                            </h3>

                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                                 style="height:350px">
                                <div id="mem_dayChartContainer"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ued-grid mgb-10">
                <div class="ued-grid ued-gl">
                    <div class="ued-gbox">
                        <div class="pdr-5 ued-panel-4 mgt-10" data-role="ued-panel">
                            <h3>
                                <a class="font-gray1" style="cursor:default;text-decoration:none">存储使用情况</a>
											<span> <!--<a class="tool max"> </a>--> <a class="tool collapse">
                                            </a> <a class="tool close"> </a> </span>
                            </h3>

                            <div class="ued-content pdt-5 pdb-5 pdr-5 pdl-5"
                                 style="height:350px">
                                <div id="disk_usage_dayChartContainer"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="ued-grid mgb-10">
                <div class="ued-grid ued-gl">
                    <div class="ued-gbox">
                        <div class="pdr-5 ued-panel-4 mgt-10" data-role="ued-panel">
                            <h3>
                                <a class="font-gray1" style="cursor:default;text-decoration:none"> 磁盘读写速率</a>
											<span> <!--<a class="tool max"> </a>--> <a class="tool collapse">
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
        <div class="clear"></div>
        <!-- 监控end -->
    </div>
</div>
</div>
<!--main-c1 end-->
<div class="clear"></div>
</div>
<!--col-c7 end-->
</div>
<!--container end-->
<!--版权 star-->
<div class="copy">
    <div class="copy-con ac">
        <p class="pdt-30">©2012中国电信云计算分公司版权所有 京ICP备 12022551号 增值电信业务经营许可证A2.B1.B2-20090001</p>
    </div>
</div>
<!--版权 end-->
</body>
</html>
