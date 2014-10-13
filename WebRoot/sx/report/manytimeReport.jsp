<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
        <title></title>
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">    
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
		<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
    	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jqueryform/jquery.form.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){
        	//渲染kpi
        	randerKpi();
        	//加载主机
        	randerHost();
        	//类型改变事件
        	$('#search_type').change(function(){
        		randerKpi();
        		randerHost();
        		$('#tb_cardId').html('');
        		$('#tb_cardKpi').html('');
        	});
        	//指标改变事件
        	$('#search_kpi').change(function(){
        		changeKpi();
        	});
        	//时间段按钮事件
        	$('#addTime').click(function(){
        		addTime();
        	});
        	//查询按钮事件
        	$('#queryReports').click(function(){
        		search();
        	});
        	//主机改变事件      	
        	$('#search_hostId').change(function(){
        		changeKpi();
        	});
        	$('.starttime').live('blur', function() {
  				//计算时间差
  				var val = $(this).val();
  				if(val!=''){
  				var starttime1 = new Date('20'+$('#starttime1').val());
  				var endtime1 = new Date('20'+$('#endtime1').val());
  				var miss = endtime1.getTime()-starttime1.getTime();
  				var id = $(this).attr('id');
  				id=id.split('_')[1];
  				var endtime  = new Date('20'+val).getTime()+miss;
  				var d = new Date(endtime).format('yy/MM/dd hh:mm');
  				$('#endtime_'+id).val(d);
  				}
			});
        })
        
        function randerKpi(){
        	$('#search_kpi').html("");
        	var type = $('#search_type').val();
        	var html='';
        	if(type=='power'){
        		html='<option value="1">cpu</option>'+
        			 '<option value="2">内存</option>'+
        			 '<option value="3">光纤卡读写</option>'+
        			 '<option value="4">网卡读写</option>';
        			 
        	}else{
        		html='<option value="1">cpu</option>'+
        			 '<option value="2">内存</option>'+
        			 '<option value="3">光纤卡读写</option>'+
        			 '<option value="4">网卡读写</option>'+
        			 '<option value="5">磁盘读写</option>';
        	}
        	$('#search_kpi').html(html);
        }
        
        function randerHost(){
        	if($('#search_type').val()=='power'){
        		$('#search_hostId').attr('name','search.powerId')
        	}else{
        		$('#search_hostId').attr('name','search.lparId')
        	}
        	$('#search_hostId').html('');
        	 $.ajax({
		        async:false,
		        type:'post',
		        url:'sxreport_getHostList.action',
		        data:{"search.type": $('#search_type').val()},
		        dataType:'json',
		        success:function (data) {
		        	$.each(data,function(i,e){		
						$('#search_hostId').append('<option value="'+e.id+'">'+e.name+'</option>');
					});
		        },
		        error:function (data, textStatus) {}
		     });
        }
        
        function addTime(){
        	var starttime1 = $('#starttime1').val();
        	var endime1 = $('#endime1').val();
        	if(starttime1!='' && starttime1!=''){
        		$('#descSpan').html('');
        		var size = $('.startTime').size();
        		var index = size+2;
        		if(index<=5){
        		var html='<div>'+
  	 				'时间段'+index+':'+
					'<input id="starttime_'+index+'" style="txt" type="text" name="search.timeMap.startTime" size="20" class="Wdate starttime"'+
					'onFocus="WdatePicker({minDate:\'1970-10-01\',dateFmt:\'yy/MM/dd HH:mm\'})"/>'+
					'-<input id="endtime_'+index+'" style="txt" type="text" readonly name="search.timeMap.endTime" size="20" class="Wdate endtime"'+
  	 				'</div>';
        		$('#search_time').append(html);
        		}else{
        			$('#descSpan').html("*时间段最多五个");
        		}
        	}else{        	
        		$('#descSpan').html("*请先填写时间段1");
        	}
        }
        
        function changeKpi(){
        	var kpi = $('#search_kpi').val();
        	if(kpi==3 || kpi==4 || kpi==5){        		
        		getCardList();
        		//加载网卡指标
        		var html_kpi = '子指标:'+
        					'<select id="search_cardKpi" name="search.cardKpi">'+
        						'<option value="1">读速度</option>'+
        						'<option value="2">写速度</option>'+
        						'<option value="3">读包数速度</option>'+
        						'<option value="4">写包数速度</option>'+
        					'</select>';
        		$('#tb_cardKpi').html(html_kpi);
        	}else{
        		$('#tb_cardKpi').html('');
        		$('#tb_cardId').html('');
        	}
        }
        
        function getCardList(){
        	var data = {};
        	data['search.kpi'] = $('#search_kpi').val();
        	data['search.type'] = $('#search_type').val();
        	if($('#search_type').val()=='power'){
        		data['search.powerId']=$('#search_hostId').val();
        	}else{
        		data['search.lparId']=$('#search_hostId').val();
        	}
        	$.ajax({
		        async:false,
		        type:'post',
		        url:'sxreport_getCardList.action',
		        data:data,
		        dataType:'json',
		        success:function (data) {
		        	var html_cardId = '';
		        	if($('#search_kpi').val()==3){
		        		html_cardId = '光纤卡:';
		        	}else if($('#search_kpi').val()==4){
		        		html_cardId = '网卡:';
		        	}else if($('#search_kpi').val()==5){
		        		html_cardId = '磁盘:';
		        	}
		       		html_cardId = html_cardId+'<select name="search.cardId">';
		        	$.each(data,function(i,e){		
		        		html_cardId = html_cardId+'<option value="'+e.name+'">'+e.name+'</option>';
					});
					html_cardId=html_cardId+'</select>';
					$('#tb_cardId').html(html_cardId);
		        },
		        error:function (data, textStatus) {}
		     });
        }
        
		function search(){
			 var options = {
					success:function(xml){
						randerChar(xml);
					},

					url:"<%=request.getContextPath()%>/sxreport_getManytimeReportData.do"
			};
			 $("#frm").ajaxSubmit(options); 
		}
		var index = 0;
		function randerChar(xml){
			index++;
			$('#chartContainer').html('');
			var obj = eval('(' + xml + ')');
			FusionCharts.setCurrentRenderer('javascript');
			var myChart = new FusionCharts("FusionCharts/ZoomLine.swf","myChartId"+index, "1000", "550", "0", "1" );
			var chart = {
    				"caption":"同比报表",
    				"xaxisname":"时间"	
  				  };
 			var unit='';
        		var kpi = $('#search_kpi').val();
        		if(kpi==1 || kpi==2){
        			unit='(%)';
        			chart.yAxisMinValue=0;
        			chart.yAxisMaxValue=100;
        		}else if(kpi==3 || kpi==4){
        			var cardkpi = $('#search_cardKpi').val();
        			if(cardkpi==1 || cardkpi==2){
        				unit='(kb/s)';
        			}else{
        				unit='(个/s)';
        			}
        		}else if(kpi==5){
        			unit='(kb/s)';
        		}
        		chart.yaxisname = "指标"+unit;
			myChart.setJSONData({
				 "chart":chart,
				   "categories":[{
				      "category":obj.category
				    }
				  ],
				  "dataset":obj.dataset
				});
	 		myChart.render("chartContainer");
		}
		
		Date.prototype.format = function(format){ 
var o = { 
"M+" : this.getMonth()+1, //month 
"d+" : this.getDate(), //day 
"h+" : this.getHours(), //hour 
"m+" : this.getMinutes(), //minute 
"s+" : this.getSeconds(), //second 
"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
"S" : this.getMilliseconds() //millisecond 
} 

if(/(y+)/.test(format)) { 
format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
} 

for(var k in o) { 
if(new RegExp("("+ k +")").test(format)) { 
format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
} 
} 
return format; 
} 
</script>
    </head>
<body class="pop-body scrollbody" style="overflow-x:hidden">
<form name='frm' id="frm" action="" method="post"> 
 <div>
 	<table>
 		<tr>
 			<td>
		 	 	主机类型:
		 	 	<select id="search_type" name="search.type">
		 	 		<option value="power">power</option>
		 	 		<option value="lpar">lpar</option>
		 	 	</select>
	 	 	</td>
	 	 	<td>
		 	 	主机:
		 	 	<select id="search_hostId" name="search.hostId">
		 	 		
		 	 	</select>
		 	</td>
		 	<td>
		 	 	指标:
		 	 	<select id="search_kpi" name="search.kpi">
		 	 		<option value="1">cpu</option>
		 	 		<option value="2">内存</option>
		 	 		<option value="3">光纤卡读写</option>
		 	 		<option value="4">网卡读写</option>
		 	 	</select>
		 	</td>
		 	<td id="tb_cardId">
						 	
		 	</td>
		 	<td id="tb_cardKpi">
						 	
		 	</td>	 	
		    <td>
		    	<input type = "button" class="thickbox btn-style02" value = "查询" id="queryReports" />
		    </td>
  	 	</tr>
  	 	<tr>
  	 		<td colspan="10" id="search_time">
  	 			<div>
  	 				时间段1:<input id="starttime1" style="txt" value="${search.startTime}" type="text" name="search.timeMap.startTime" size="20" class="Wdate starttime1"
					onFocus="WdatePicker({minDate:'1970-10-01',maxDate:'#F{$dp.$D(\'endtime1\')||\'new Date()\'}',dateFmt:'yy/MM/dd HH:mm'})"/>-<input id="endtime1" style="txt" type="text" name="search.timeMap.endTime" size="20" class="Wdate endtime1"
					onFocus="WdatePicker({minDate:'#F{$dp.$D(\'starttime1\')}',maxDate:'new Date()',dateFmt:'yy/MM/dd HH:mm'})" value="${search.endTime}"/>
					<input type="button" id="addTime" value="添加时间段" name="">
					<span id="descSpan" style="margin-left: 10px;font-size: 12px;color: red;"></span>
  	 			</div> 
  	 				 			
			</td>			    	
  	 	</tr>
  	</table>
</div>
</form>
<div id="chartContainer" style="margin-top:20px"></div>
</body>
</html>
