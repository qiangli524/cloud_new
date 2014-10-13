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
			width: 90%;
			height: 14px;
			font-size:12px; 
		}
        </style>
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
   		<script type="text/javascript" src="<%=request.getContextPath()%>/cloud3/charts/highcharts/highcharts.js"></script>
		<script type="text/javascript">
			var types = '';
			var chart;
			var topNames = 'aaa';
            $(function () {
                    Highcharts.setOptions({global:{useUTC : false}});
                    chart = new Highcharts.Chart({
                        chart : {
                            renderTo: 'container',        //在哪个区域呈现，对应HTML中的一个元素ID
                            plotBackgroundColor: null,    //绘图区的背景颜色
                            plotBorderWidth: null,        //绘图区边框宽度
                            plotShadow: false  
                        },
                        navigator : {
                            adaptToUpdatedData: false,
                            series : {
                                data : []
                            }
                        },
                        credits : {
                            href:'',
                            text:''
                        },
                         //导航
                         legend: {
				            enabled: false
				        },
                        title: {
                            text: '主机资源使用率',
                            margin: 20,
                            style :{
                            	color: '#3E576F',
                    			fontSize: '13px'
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
                    			pointPadding: 0.3,
                   				borderWidth: 0,
                   				pointWidth:40
                   				
                			}
                        },
                        xAxis: {
                           categories:[]
                        },
                        yAxis: {
                            title: {
                                	text: '资源使用率'
                            },
                            min: 0
                        },
                        tooltip: {
                            formatter: function() {
                            	if(types=="cpu"){
                            		return this.x+'CPU使用率:'+''+this.y+'%'
                            	}else if(types="mem"){
                            		return this.x+'内存使用率:'+''+this.y+'%'
                            	}else{
                            		return this.x+'存储使用率:'+''+this.y+'%'
                            	}
                             }
                        },
            
                        series: [{
                        	type:'column'
                        }]
                    });
                   $("#time a").click(function(){
                   var type =$(this).attr("name");
                   var topName = $(this).children("span").text();
                   var num = $("#num").val();
                   var uuid = $("#uuid").val();
           			var connect_id = $("#connect_id").val();
                   topNames = topName;
                   types=type;
                    $.ajax({
                    type:"POST",
                    dataType:"json",
                    cache:false,
                    data:"topName="+topName+"&top_num="+num+"&type="+type+"&uuid="+uuid+"&connect_id="+connect_id,
                    url:'top_hostTop.do',//提供数据的Servlet
                    success:function(data){
                        //设置数据
                       chart.xAxis[0].setCategories(data.categories);
                       chart.series[0].setData(data.y);
                       //拼接表格
                       $("#tab tr:not(:first)").remove();   //首先删除，除第一行之外的所有表格
                       for(var i = 0; i < data.resultList.length ;i++){
                         $("<tr><td>"+data.resultList[i].name+"</td><td>"
                         			 +data.resultList[i].ip+"</td><td>"
                         			 +data.resultList[i].cpu_usage+"%</td><td>"
                         			 +data.resultList[i].mem_usage+"%</td><td>"
                         			 +data.resultList[i].store_usage+"%</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
                       }
                    }
                });
             })
             
              $("#time a:eq(0)").trigger("click");
            });
            
            
            function getTops(){
        		var num = $("#num").val();
        		var uuid = $("#uuid").val();
        		var connect_id = $("#connect_id").val();
        		 $.ajax({
                    type:"POST",
                    dataType:"json",
                    cache:false,
                    data:"top_num="+num+"&type="+types+"&uuid="+uuid+"&connect_id="+connect_id,
                    url:'top_hostTop.do',//提供数据的Servlet
                    success:function(data){
                        //设置数据
                        var obj = data.topTargetObj;
                       chart.xAxis[0].setCategories(data.categories);
                       chart.series[0].setData(data.y); 
                      //拼接表格
                       $("#tab tr:not(:first)").remove();   //首先删除，除第一行之外的所有表格
                       for(var i = 0; i < data.resultList.length ;i++){
                        $("<tr><td>"+data.resultList[i].name+"</td><td>"
                         			 +data.resultList[i].ip+"</td><td>"
                         			 +data.resultList[i].cpu_usage+"%</td><td>"
                         			 +data.resultList[i].mem_usage+"%</td><td>"
                         			 +data.resultList[i].store_usage+"%</td></tr>").insertAfter($("#tab tr:eq("+i+")"));
                       }
                    }
                });
        		
        	}
        </script>
       
    </head>

	<body class="pop-body scrollbody">
	<s:hidden name="uuid"></s:hidden>
	<s:hidden name="connect_id"></s:hidden>
		<div id="time">
			<table style="width: 100%;height: 20px;border: 0" >
				<tr>
					<s:if test=""></s:if>
					
				     <td style="width: 64%"></td>
					<td><a href="javascript:;" name="cpu" class="aalink" ><span style="color: blue">CPU使用率TOPN</span></a></td>
					<td><a href="javascript:;" name="mem" class="aalink"><span style="color: blue">内存使用率TOPN</span></a></td>
					<td><a href="javascript:;" name="store" class="aalink"><span style="color: blue">存储使用率TOPN</span></a></td>
					<td style="font-size:15px;">TOP<input type="text" style="width:20px;color: red" id="num" value="6"/><input type="button" value="GO" id="set" onclick="getTops()"/></td>
				</tr>
			</table>
		</div>
		<div id='container' style="min-width: 700px; height: 350px; margin: 0 auto" align="center"></div>
			<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="tab">
				<thead>	
					<tr>
						<th>主机名称</th>
						<th>IP地址</th>
						<th>CPU利用率</th>
						<th>内存利用率</th>
						<th>存储利用率</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="list" value="resultList">
						<tr>
							<td><s:property value="#list.name"/></td>
							<td><s:property value="#list.ip"/></td>
							<td><s:property value="#list.cpu_uage"/>%</td>
							<td><s:property value="#list.mem_usage"/>%</td>
							<td><s:property value="#list.store_usage"/>%</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</body>
</html>
