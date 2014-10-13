<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>资源总量趋势图</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
		<style>
		.aalink{
			border-bottom: #000000 dashed 1px;
			text-decoration: none;
		}
        </style>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	    <script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
		<script type="text/javascript">
            var kpi = '<s:property value="kpi"/>';
            var hyId = '<s:property value="hyId"/>';
           var motionName = '<s:property value="motionName"/>';//性能名称，比如cpu 内存，磁盘，网络等
            $(function () {
                    Highcharts.setOptions({global:{useUTC : false}});
                    window.chart = new Highcharts.Chart({
                        chart: {
                            renderTo: '<s:property value="motionName"/>',
                            type: 'spline',
                            zoomType : 'x'
                        },
                        credits : {
                            href:'',
                            text:''
                        },
                        title: {
                            text: ''
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
                            	minute: '%H:%M',
                            	hour: '%e. %b.%H:%M'
                            }
                        },
                        yAxis: {
                            title: {
                        		<s:if test='motionName=="CPU"'>
                                	text: '资源使用百分比'
                                </s:if>
                                <s:elseif test='motionName=="MEMORY"'>
                                	text: '资源使用百分比'
                                </s:elseif>
                                <s:elseif test='motionName=="DISK"'>
                                	text: '读写速率'
                                </s:elseif>
                                 <s:elseif test='motionName=="NETWORK"'>
                                	text: 'IO吞吐量'
                                </s:elseif>
                            },
                            min: 0
                        },
                        tooltip: {
                            formatter: function() {
                            if(kpi=='PM-V-01-010-13' || kpi=='PM-V-01-010-15'){
                            	return '<b>'+ this.series.name +'</b><br/>'+
                                    Highcharts.dateFormat('%Y-%m-%e %H:%M:%S', this.x) + '  ' +  this.series.name + '使用情况:'+ (this.y+"").substring(0,7) + 'KBps';
                            }else
                            	{
                                	return '<b>'+ this.series.name +'</b><br/>'+
                                    Highcharts.dateFormat('%Y-%m-%e %H:%M:%S', this.x) + '  ' +  this.series.name + '使用率:'+ (this.y+"").substring(0,4) + '%';
                                  } 
                            }
                        },
            
                        series: [{
                               <s:if test='motionName=="CPU"'>
                                	name: 'CPU'
                                </s:if>
                                <s:elseif test='motionName=="MEMORY"'>
                                	name: '内存'
                                </s:elseif>
                                <s:elseif test='motionName=="DISK"'>
                                	name: '读取'
                                </s:elseif>
                                 <s:elseif test='motionName=="NETWORK"'>
                                	name: 'I'
                                </s:elseif>
                            }
                            	<s:if test='motionName=="DISK"'>
                            		,{
                            			name:'写入'
                            		}
                            	</s:if>
                            	<s:if test='motionName=="NETWORK"'>
                            		,{
                            			name:'O'
                            		}
                            	</s:if>
                            ]
                    });

            });
            $(function(){
            	$("#time a").click(function(){
                      	var time = '';
                        var minute=$(this).attr("name");
                        var minuteName=$(this).children("span").text();
                        time=minute;
                        var param = {
                        	kpi:kpi,
                        	hyId:hyId,
                        	time:new Date().toString(),
                        	pointTime:time
                        };
                        if(kpi=='PM-V-01-010-11' || kpi=='PM-V-01-010-12'){//cpu and memory
	                        $.ajax({
	    			  			type:"POST",
	                  			url:"hyMonitor_queryHyDNData.do",
	                  			async: false,
	                  			cache:false,
	                  			param:param,
	    	          			success: function(data){
	    	          				if(data.length==0){
		                        		 chart.showLoading(minuteName+' 此时间段无数据...');
		                        		 chart.series[0].setData();
		                            }else{
		                                 chart.hideLoading();
		                                 chart.series[0].setData(data);
		                            }
	                  			}
	    					});
                        }else if(kpi=='PM-V-01-010-13' || kpi=='PM-V-01-010-15'){ //disk and net I/O
                        	  $.ajax({
  	    			  			type:"POST",
  	                  			url:"hyMonitor_queryHyDNData.do",
  	                  			async: false,
  	                  			cache:false,
  	                  			param:param,
  	    	          			success: function(data){
	  	    	          			if(data.length==0){
		                        		 chart.showLoading(minuteName+' 此时间段无数据...');
		                        		 chart.series[0].setData();
		                            }else{
		                                 chart.hideLoading();
		                                 chart.series[0].setData(data.resultList);
		                                 chart.series[1].setData(data.resultList2);
		                            }
  	                  			}
  	    					});
                        }
                    });
                    $("#time a:eq(0)").trigger("click");
            });
        </script>
    </head>

		<body>
		<div id="time">
			<table style="width: 100%;height: 20px;border: 0" >
				<tr>
				     <td style="width: 64%"></td>
				    <td><span style="font-weight: bold">周期：</span></td>
					<td><a href="javascript:;" name="30" class="aalink"><span style="color: blue">30 分钟</span></a></td>
					<td><a href="javascript:;" name="60" class="aalink"><span style="color: blue">1 小时</span></a></td>
					<td><a href="javascript:;" name="1440" class="aalink"><span style="color: blue">1 天</span></a></td>
					<td><a href="javascript:;" name="10080" class="aalink"><span style="color: blue">1 周</span></a></td>
					<td><a href="javascript:;" name="43200" class="aalink"><span style="color: blue">1 个月</span></a></td>
					<td><a href="javascript:;" name="518400" class="aalink"><span style="color: blue">1 年</span></a></td>
				</tr>
			</table>
		</div>
		<div id='<s:property value="motionName"/>'
			style="min-width: 700px; height: 380px; margin: 0 auto" align="center"></div>
	</body>
</html>
