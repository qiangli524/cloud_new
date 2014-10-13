<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
  <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
  <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
 	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
  	<script type="text/javascript">
  		$(function(){
			var chart;
			var topNames = 'aaa';
			var number = new Array();
			number.splice(0, number.length);
			$.ajax({
                    type:"POST",
                    dataType:"json",
                    async:false,
                    cache:false,
                    url:'alarm_count.do',//提供数据的Servlet
                    success:function(data){
                        //设置数据
                       number = data.number;
                    },
                    error:function(e){
                    }
                });
            $(function () {
                    Highcharts.setOptions({global:{useUTC : false}});
                    chart = new Highcharts.Chart({
                        chart : {
                            renderTo: 'container',        //在哪个区域呈现，对应HTML中的一个元素ID
                            plotBackgroundColor: null,    //绘图区的背景颜色
                            plotBorderWidth: null,        //绘图区边框宽度
                            plotShadow: false  
                        },
                        /* navigator : {
                            adaptToUpdatedData: false,
                            series : {
                                data : [ ]
                            }
                        }, */
                        //导航
                         legend: {
				            enabled: false
				        },
				        //所有者
                        credits : {
                        	 enabled: false  //不显示high的所有者
                        },
                        title: {
                            text: '当前告警次数统计',
                            margin: 20,
                            style :{
                            	fontSize: '20',
                            	fontWeight: 'bold'
                            }
                        },
                        //副标题
                        /* subtitle: {
                            text: ''
                        }, */
                        plotOptions: {
                            column: {
                            	pointWidth: 80,
                    			pointPadding: 0.3,
                   				borderWidth: 0
                			},
                			series: {
				                cursor: 'pointer',
				                 point: {
				                    events: {
				                        click: function() {
				                            var category = this.category;   //告警级别具体文字
				                            var cateNum  = '';              //告警级别对应数字
				                            if(category == '严重告警'){
				                            	cateNum = 0;
				                            }else if(category == '主要告警'){
				                            	cateNum = 1;
				                            }else if(category == '次要告警'){
				                            	cateNum = 2;
				                            }else{
				                            	cateNum = 3;
				                            }
				                     //       $.dialog({
								    	//		id:'editorder',
								    	//		title: category,
								    	//		width: '1000px',
								    	//		height: '500px',
								    	//		max: false,
								    	//	    min: false,
								    	//		content: 'url:alarm_listMonitorAlarm.do?cateNum='+cateNum
									   // 	});
				                        }
				                    }
				                }
				            }
                        },
                        xAxis: {
                            categories:['严重告警','主要告警','次要告警','不确定告警'],
                        	labels: {
                                y: 25,
				                style: {
				                    fontSize: '10'
				                }
				            }
                        },
                        yAxis: {
                            title: {
                                	text: '告警次数(次)'
                            },
                            min: 0
                        },
                        tooltip: {
                            formatter: function() {
                            		return '告警次数:'+''+this.y
                             }
                        },
                        series: [{
                        	type:'column',
                        	data: [{
				                name: '严重告警',
				                color: '#FF0000',
				                y: number[0]
				            }, {
				                name: '主要告警',
				                color: '#FF9600',
				                y: number[1]
				            },{
				                name: '次要告警',
				                color: '#F7DE05',
				                y: number[2]
				            },{
				                name: '不确定告警',
				                color: '#CCE8CF',
				                y: number[3]
				            }]
                        }]
                    });
            });
  		});
  		
  	</script>
  </head>
  
  <body class="scrollbody">
		<div id='container' style="min-width: 700px; height: 450px; margin: 0 auto" align="center" class="scrollbody">
		</div>
  </body>
</html>
