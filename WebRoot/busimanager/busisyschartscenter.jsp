<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
    <head>
        <script type="text/javascript">
        $(function () {
        	Highcharts.setOptions({global:{useUTC : false}});
        	 window.chart = new Highcharts.Chart({
                chart: {
                	renderTo: 'container',
                    type: 'bar'
                },
                title: {
                    text: '业务资源统计分析视图'
                },
                subtitle: {
                    text: ''
                },
                xAxis: { 
                    categories: ['子系统', '服务器总量'],
                    title: {
                        text: null
                    }
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '单位  (个)',
                        align: 'high'
                    },
                    labels: {
                        overflow: 'justify'
                    }
                },
                tooltip: {
                    valueSuffix: ' 个'
                },
                plotOptions: {
                    bar: {
                        dataLabels: {
                            enabled: true
                        }
                    }
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'top',
                    x: -10,
                    y: 50,
                    floating: true,
                    borderWidth: 1,
                    backgroundColor: '#FFFFFF',
                    shadow: true
                },
                credits: {
                    enabled: false
                },
                series: []
            });
        	chart.showLoading('Loading data from server...');
        	var id=$("#id").val();
            $.ajax({
		             type: "POST",
		             url: "abstractinfo_getChartInfoSyncByCenter.do",
		             data:{"id":id},
		             dataType: "json",
		             success : function(data){
					 $.each(data, function(i, n){
						    chart.hideLoading();
				            chart.addSeries({name:n.name,data:n.data});
						});
	              	 }
   	       	});        		

        });

        </script>
    </head>
    <body>
        <div id="container"></div>
        <s:hidden name="id" id="id"></s:hidden>
    </body>
</html>
