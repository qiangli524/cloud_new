<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
    <head>
        <title>虚拟机监控视图</title>
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript">
         	var kpi = getQueryString('kpi');
            var hyid = getQueryString('hyid');
            //time-单位毫秒
            var time = getQueryString('time');
            //虚拟化类型
            //var resourcetype = getQueryString('resourcetype');
            $(function() {
                    // create the chart
                    window.chart = new Highcharts.StockChart({
                        chart : {
                            renderTo : 'container',
                            zoomType: 'x'
                        },
                        navigator : {
                            adaptToUpdatedData: false,
                            series : {
                                data : []
                            }
                        },
                        title: {
                            text: '虚拟机监控视图'
                        },
                        credits : {
                            href:'http://www.si-tech.com.cn',
                            text:'www.si-tech.com.cn'
                        },
                        subtitle: {
                            text: ''
                        },
                        rangeSelector : {
                        	//enabled : false,
                            /*buttons: [{
                                    type: 'hour',
                                    count: 1,
                                    text: '1h'
                                }, {
                                    type: 'day',
                                    count: 1,
                                    text: '1d'
                                }, {
                                    type: 'month',
                                    count: 1,
                                    text: '1m'
                                }, {
                                    type: 'year',
                                    count: 1,
                                    text: '1y'
                                }, {
                                    type: 'all',
                                    text: 'All'
                                }],*/
                            buttons: [{
                                    type: 'all',
                                    text: '所有'
                             }],
                            inputEnabled: false, // it supports only days
                            selected : 0 // all
                        },
                        xAxis : {
                            events : {
                               // afterSetExtremes : afterSetExtremes
                            },
                            minRange: 1000 // one hour
                        },
                        series : [{
                                data : []
                            }]
                    });
                    chart.showLoading('Loading data from server...');
                    $.getJSON('charts_getVMMonitorData.do?kpi='+kpi+'&hyid='+hyid+'&time='+time,{'time':new Date().toString()},function(data) {
						data = eval(data);
						chart.series[0].setData(data);
						chart.hideLoading();
					});
            });

            /**
             * Load new data depending on the selected min and max
             */
            function afterSetExtremes(e) {
                var url,
                currentExtremes = this.getExtremes(),
                range = e.max - e.min;
                chart.showLoading('Loading data from server...');
                $.getJSON('charts_getVMMonitorData.do?kpi='+kpi+'&hyid='+hyid+'&time='+range, function(data) {
                	data = eval(data);
                    chart.series[0].setData(data);
                    chart.hideLoading();
                });
            }
            /**获取Url参数*/
            function getQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]); return null;
            }
        </script>
    </head>

    <body>
        <script type="text/javascript" src="../highstock/highstock.js"></script>
        <script type="text/javascript" src="../highstock/modules/exporting.js"></script>
        <div id="container"></div>
    </body>
</html>
