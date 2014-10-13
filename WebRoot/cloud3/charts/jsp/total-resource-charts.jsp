<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title>资源总量趋势图</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
        <script type="text/javascript">
            var type = '<s:property value="type"/>',rownum = '<s:property value="rownum"/>';
            var resourceName='';
            if (type == 'vmware') {
                resourceName='VMware';
            } else  if (type == 'xen') {
                resourceName='Xen';
            } else  if (type == 'all') {
                resourceName='所有';
            }
            $(function () {
                    window.chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container',
                            type: 'spline',
                            zoomType : 'x'
                        },
                        credits : {
                            href:'http://www.si-tech.com.cn',
                            text:'www.si-tech.com.cn'
                        },
                        title: {
                            text: resourceName + '资源使用率曲线图'
                        },
                        subtitle: {
                            text: ''
                        },
                        plotOptions: {
                            spline: {
                                lineWidth: 1,
                                //开启点上的label
                                dataLabels: {
                                    enabled: false
                                },
                                states: {
                                    hover: {
                                        lineWidth: 1
                                    }
                                },
                                marker: {
                                    enabled: false,
                                    states: {
                                        hover: {
                                            enabled: true,
                                            symbol: 'circle',
                                            radius: 5,
                                            lineWidth: 1
                                        }
                                    }
                                }
                            }
                        },
                        xAxis: {
                            type: 'datetime',
                            dateTimeLabelFormats: { // don't display the dummy year
                                month: '%e. %b',
                                year: '%b'
                            }
                        },
                        yAxis: {
                            title: {
                                text: '资源使用百分比'
                            },
                            min: 0
                        },
                        tooltip: {
                            formatter: function() {
                                return '<b>'+ this.series.name +'</b><br/>'+
                                    Highcharts.dateFormat('%Y-%m-%e,%H:%M:%S', this.x) + '  ' +  this.series.name + '使用率:'+ this.y + '%';
                            }
                        },
            
                        series: [{
                                name: 'CPU'
                            }, {
                                name: '内存'
                            }, {
                                name: '存储'
                            }]
                    });
                    
                    chart.showLoading('Loading data from server...');
                    var param = {
                    	type:type,
                    	rownum:rownum,
                    	time:new Date().toString()
                    };
                    var url = '<s:url action="homePage_getResourceData"/>';
                    $.getJSON(url,param,function(data) {
                        chart.series[0].setData(data.cpu);
                        chart.series[1].setData(data.mem);
                        chart.series[2].setData(data.storage);
                        chart.hideLoading();
                    });
            });
            /**获取Url参数*/
            <%--
            function getQueryString(name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]); return null;
            }
            --%>
        </script>
    </head>

    <body>
        <div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
    </body>
</html>
