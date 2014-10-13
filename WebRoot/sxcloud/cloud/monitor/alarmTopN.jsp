<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>告警topN</title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
		<style>
		.aalink{
			border-bottom: #000000 dashed 1px;
			text-decoration: none;
			width: 90%;
			height: 14px;
			font-size:12px; 
		}
        </style>
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
		<script type="text/javascript">
			var chart;
			$(function () {
				 var num = $("#num").val();
                 $.ajax({
	                    type:"POST",
	                    dataType:"json",
	                    cache:false,
	                    data:"top_num="+num,
	                    url:'top_queryTopAlarm.do',
	                    success:function(data){
	                    	 var yval = [];
	                    	 for ( var i = 0; i < data.y.length; i++) {
	                    		 if (data.y[i] == 0.0) {
	                         		yval.push({'color':'red','y':4});
	     						} else if(data.y[i] == 1.0){
	     							yval.push({'color':'yellow','y':3});
	     						} else if(data.y[i] == 2.0){
	     							yval.push({'color':'blue','y':2});
	     						} else if(data.y[i] == 3.0){
	     							yval.push({'color':'green','y':1});
	     						} else {
	     							yval.push({'color':'white','y':0});
	     						}
							}
	                    	 
	                        chart.series[0].setData(yval);  
	                        chart.xAxis[0].setCategories(data.categories);
	                    },
	                    error:function(e){
	                    }
              });
				
				Highcharts.setOptions({
					global:{
						useUTC:false
					}
				});
				chart = new Highcharts.Chart({
					chart:{
						renderTo: 'container',       
                        plotBackgroundColor: null,    
                        plotBorderWidth: null,       
                        plotShadow: false
					},
					navigator : {
	                     adaptToUpdatedData: false,
	                     series : {
	                         data : []
	                     }
	                 },
					title:{
						text:'告警'
					},
					xAxis:{
						categories:[]
					},
					yAxis:{
						title:{
							text:'告警级别'
						},
						min:0
					},
					tooltip:{
						formatter:function(){
							if (this.y == 4) {
								return '告警位置：'+this.x+',告警级别：'+'严重告警'
							} else if(this.y == 3){
								return '告警位置：'+this.x+',告警级别：'+'主要告警'
							} else if(this.y == 2){
								return '告警位置：'+this.x+',告警级别：'+'次要告警'
							} else if(this.y == 1){
								return '告警位置：'+this.x+',告警级别：'+'不确定告警'
							}
						}
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
	                     },
	                     column: {
	             			pointPadding: 0.2,
	            			borderWidth: 0,
	            			pointWidth:50,
	            			cursor:'pointer',
	            			point:{
	            				events:{
	            					click:function(){
	            						//alert(chart.xAxis[0].categories[this.x]);
	            						//alert(this.y);
	            						$.dialog({
	            							id:'viewTop',
	            			    			title:'查看',
	            			    			width: '900px',
	            			    			height: '450px',
	            			    			content: 'url:alarm_viewMonitorAlarm.do?location='+chart.xAxis[0].categories[this.x]+'&level='+this.y
	            						});
	            					}
	            				}
	            			}
	         			}
	                 },
					series:[{
						type:'column',
						name:'告警'
					}]
				});
				
	                  
			});
			  
	        
            function getTops(){
        		var num = $("#num").val();
        		 $.ajax({
                     type:"POST",
                     dataType:"json",
                     cache:false,
                     data:"top_num="+num,
                     url:'top_queryTopAlarm.do',
                     success:function(data){
                    	 var yval = [];
                    	 for ( var i = 0; i < data.y.length; i++) {
                    		 if (data.y[i] == 0.0) {
                         		yval.push({'color':'red','y':4});
     						} else if(data.y[i] == 1.0){
     							yval.push({'color':'yellow','y':3});
     						} else if(data.y[i] == 2.0){
     							yval.push({'color':'blue','y':2});
     						} else if(data.y[i] == 3.0){
     							yval.push({'color':'green','y':1});
     						} else {
     							yval.push({'color':'white','y':0});
     						}
						}
                    	 
                        chart.series[0].setData(yval);  
                        chart.xAxis[0].setCategories(data.categories);
                     },
                     error:function(e){
                     }
                 });
        	}
        </script>
       
    </head>

	<body>
		<div id="time">
			<table style="width: 100%;height: 20px;border: 0" >
				<tr>
				    <td style="width: 64%"></td>
					<td style="font-size:15px;" align="right">TOP
						<input type="text" style="width:30px;color: red" id="num" value="5"/>
						<input type="button" value="GO" onclick="getTops()" />
					</td>
				</tr>
			</table>
		</div>
		<div id='container' style="min-width: 400px; height: 300px; margin: 0 auto" align="center"></div>
	</body>
</html>

