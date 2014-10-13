<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
    <head>
      <!-- <title>资源余量图</title> -->
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <script type="text/javascript" src="../jquery/jquery-1.8.2-min.js"></script>
        <script type="text/javascript" src="../highcharts/highcharts.js"></script>
        <script type="text/javascript">
            $(function () {
                    // define the options
                     window.chart = new Highcharts.Chart({
                        chart: {
                            renderTo: 'container',
                            plotBackgroundColor: null,
                            plotBorderWidth: null,
                            plotShadow: false
                        },
                        title: {
                           text: ''
                        },
                        credits : {
                          href:'www.si-tech.com.cn',
                          text:'www.si-tech.com.cn'
                        },
                        tooltip: {
                            pointFormat: '{series.name}: <b>{point.y}</b>',
                            percentageDecimals: 1
                        },
                        plotOptions: {
                            pie: {
                                allowPointSelect: true,
                                cursor: 'pointer',
                                dataLabels: {
                                    enabled: true,
                                    color: '#000000',
                                    connectorColor: '#000000',
                                    formatter: function() {
                                        return '<b>'+ this.point.name +'</b>: '+ Highcharts.numberFormat(this.percentage,".00") +' %';
                                    }
                                }
                            }
                        },labels: {
                            items: [{
                                    html: 'CPU使用情况',
                                    style: {
                                        left: '70px',
                                        top: '8px',
                                        color: 'black'
                                    }
                                },{
                                    html: '内存使用情况',
                                    style: {
                                        left: '270px',
                                        top: '8px',
                                        color: 'black'
                                    }
                                },{
                                    html: '存储使用情况',
                                    style: {
                                        left: '470px',
                                        top: '8px',
                                        color: 'black'
                                    }
                                }]
                        },
                        series: [{
                                type: 'pie',
                                name: 'VCPU(单位：核)',
                                center: [100, 80],
                                size: 90,
                                showInLegend: false,
                                dataLabels: {
                                    enabled: false
                                }
                            },{
                                type: 'pie',
                                name: '内存(单位：MB)',
                                center: [300, 80],
                                size: 90,
                                showInLegend: false,
                                dataLabels: {
                                    enabled: false
                                }
                            },{
                                type: 'pie',
                                name: '存储内存(单位：GB)',
                                center: [500, 80],
                                size: 90,
                                showInLegend: false,
                                dataLabels: {
                                    enabled: false
                                }
                            }]
                    });
    				//chart.showLoading('Loading data from server...');
                    $.getJSON('charts_getMarginResourceData.do',{'time':new Date().toString()},function(data) {
                        chart.series[0].setData(eval(data.cpu));
                        chart.series[1].setData(eval(data.mem));
                        chart.series[2].setData(eval(data.storage));
                        chart.hideLoading();
                    });
            });
        </script>
    </head>

    <body>
        <div id="container" style="min-width: 200px; height: 200px; margin: -10 auto" align="left"></div>
    </body>
</html>
