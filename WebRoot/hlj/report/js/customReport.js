function initQuery(){
	var resourceType = $("input[class=resourceType]:checked").val();
	var tml = $('#temp_query').html();
	var tmp = _.template(tml, {'resourceType':resourceType} );
	$('#tbody').html(tmp);
	//初始化默认kpi
	initDefalutKpi(resourceType);
	//初始化业务系统
	initBusiness();
	//初始化数据中心
	initDataCenter();
	//设置默认时间
	defaultTime();

	//时间维度改变事件,根据不同维度设置不同的默认时间
	$('.dateType').live({
		click: function() {
			defaultTime();
		}
	})	
}
/**
 * 初始化默认kpi
 * 
 */
function initDefalutKpi(resourceType){
	var ks = kpis[resourceType];
	if(ks.length!=0){
		var k = ks[0];
		$('#kpiName').val(k.name);
		$('#kpiunit').val(k.unit);
		$('#kpiId').val(k.id);
	}
}

/**
 * 初始化导出设置
 * 
 */
function initFcExport(){
	var myExportComponent = new  FusionChartsExportObject("fcExporter1","FusionCharts/FCExporter.swf"); 
	 myExportComponent.componentAttributes.btnColor = 'EAF4FD'; 
	 myExportComponent.componentAttributes.btnBorderColor = '0372AB'; 
	 myExportComponent.componentAttributes.btnFontFace = 'Verdana'; 
	 myExportComponent.componentAttributes.btnFontColor = '0372AB'; 
	 myExportComponent.componentAttributes.btnFontSize = '12'; 
	 //Title of button  
	 myExportComponent.componentAttributes.btnsavetitle = '另存为';
	 //myExportComponent.componentAttributes.btndisabledtitle = '右键生成图片';
	 myExportComponent.Render("exproterDiv");
}

/**
 * 默认时间  
 * 实时维度默认区间为六小时   
 * 小时维度默认区间为一周
 */
function defaultTime(){
	var dateType = $('input[class="dateType"]:checked').val();
	var now = new Date();
	$('#endDateId').val(now.format("yyyy-MM-dd hh:mm:00"));
	if(dateType==1){
		now.setTime(now.getTime()-2*3600*1000);
		$('#startDateId').val(now.format("yyyy-MM-dd hh:mm:00"));
	}else if(dateType==2){
		now.setTime(now.getTime()-3*24*3600*1000);
		$('#startDateId').val(now.format("yyyy-MM-dd hh:mm:00"));		
	}else if(dateType==3){
		now.setTime(now.getTime()-30*24*3600*1000);
		$('#startDateId').val(now.format("yyyy-MM-dd hh:mm:00"));		
	}
}

/**
 * 验证时间  
 *实时维度时间区间不能大于1个月
 *小时维度时间区间不能大于一年
 */
function verifyDate(){
	var isOK = true;
	var dateType = $('input[class="dateType"]:checked').val();
	var startDate = new Date($('#startDateId').val().replace(/-/g,"/")).getTime();
	var endDate = new Date($('#endDateId').val().replace(/-/g,"/")).getTime();
	if(startDate>endDate){
		alert("以下原因导致提交失败：\t\t\t\t \n1:开始时间不能大于结束时间");
		return false;
	}
	if(dateType==1){
		if((endDate-startDate)>(30*24*3600*1000)){
			alert("以下原因导致提交失败：\t\t\t\t \n1:实时维度时间区间不能超过一个月");
			isOK = false;
		}
	}else if(dateType==2){
		if((endDate-startDate)>(356*24*3600*1000)){
			alert("以下原因导致提交失败：\t\t\t\t \n1:小时维度时间区间不能超过一年");
			isOK = false;
		}
	}
	return isOK;
}


/**
 * 初始化数据中心
 * 
 */
 var dataCenterList = [];
function initDataCenter(){
	if(dataCenterList.length==0){		
		getDataCneter();
	}
	var html = '<option value="-1">所有</option>';
	$(dataCenterList).each(function (i, dataCenter) { 
		html = html + '<option value="'+dataCenter.id+'">'+dataCenter.name+'</option>';							
	})
	$('#dataCenterId').html(html);	
}

function getDataCneter(){
	$.ajax({
		type : 'post',
		async : false,
		url : "customReport_getDataCenterList.do",
		data:{},
		dataType: 'json',
		success : function(data){
			dataCenterList = data;			
		},
		error : function(data,textStatus){
			console.log('error:' + data);
		}
	});			
}

/**
 * 渲染集群下拉框
 * @param val
 */
 var clusterObj = {};
function renderCluster(){
	var val = $('#dataCenterId').val();
	var html = '<option value="-1">所有</option>';
	var data = clusterObj['cluster'+val];
	if(val!=-1 && data==undefined){
		getCluster(val);
	}
	data = clusterObj['cluster'+val];
	$(data).each(function (i, cluster) { 
		html = html + '<option value="'+cluster.id+'">'+cluster.name+'</option>';							
	})
	$('#clusterId').html(html);
}

function getCluster(val){
		$.ajax({
			type : 'post',
			async : false,
			url : "customReport_getClusterByDataCenter.do",
			data:{"obj.dataCenterId":val},
			dataType: 'json',
			success : function(data){
				clusterObj['cluster'+val]=data;
			},
			error : function(data,textStatus){
				console.log('error:' + data);
			}
		});				
}

/**
 * 渲染子业务系统下拉框
 * @param val
 */
var subBusinessObj = {};
function renderSubBusiness(){
	var val = $('#businessId').val();
	var html = '<option value="-1">所有</option>';
	var data = subBusinessObj['business'+val];
	if(val!=-1 && data==undefined){
		getSubBusiness(val);
	}
	data = subBusinessObj['business'+val];
	$(data).each(function (i, cluster) { 
		html = html + '<option value="'+cluster.id+'">'+cluster.name+'</option>';							
	})
	$('#subBusinessId').html(html);
}

function getSubBusiness(val){
	$.ajax({
		type : 'post',
		async : false,
		url : "customReport_getSubBusinessList.do",
		data:{"obj.businessId":val},
		dataType: 'json',
		success : function(data){
			subBusinessObj['business'+val]=data;
		},
		error : function(data,textStatus){
			console.log('error:' + data);
		}
	});				
}

function addResource(){
		var resourceType = $("input[class=resourceType]:checked").val();
		var dataCenterId = $('#dataCenterId').val();
		var clusterId =  $('#clusterId').val();
		var businessId = $('#businessId').val();
		var subBusinessId = $('#subBusinessId').val();
		$.dialog({
			id : 'add',
			title : '选择资源',
			width : '950px',
			height : '500px',
			lock:true,
			max : true,
			min : true,
			content : 'url:customReport_showDetailAdd.do?obj.resourceType='+resourceType+'&obj.dataCenterId='+dataCenterId+'&obj.clusterId='+clusterId+'&obj.businessId='+businessId+'&obj.subBusinessId='+subBusinessId
		});
}

function addValue(vmNames2,vmIds2) {
	$("#resourceNames").val(vmNames2);
	$("#resourceIds").val(vmIds2);
}

function addKpi(vmNames2,vmIds2,unit) {
	$("#kpiName").val(vmNames2);
	$("#kpiId").val(vmIds2);
	$("#kpiunit").val(unit);
}

/**
 * 初始化业务系统
 */
var businessList = [];
function initBusiness(){
	if(businessList.length==0){
		getBusiness();
	}
	var html = '<option value="-1">所有</option>';
	$(businessList).each(function (i, business) { 
		html = html + '<option value="'+business.id+'">'+business.name+'</option>';							
	})
	$('#businessId').html(html);
}

function getBusiness(){
	$.ajax({
		type : 'post',
		async : false,
		url : "customReport_getBusinessList.do",
		data:{},
		dataType: 'json',
		success : function(data){
			businessList = data;
		},
		error : function(data,textStatus){
			console.log('error:' + data);
		}
	});			
}

/**
 * 选择指标
 */
function queryKpis(){
		var resourceType = $("input[class=resourceType]:checked").val();
		$.dialog({
			id : 'add',
			title : '指标列表',
			width : '400px',
			height : '500px',
			lock:true,
			max : true,
			min : true,
			content : 'url:customReport_showKpis.do?obj.resourceType='+resourceType
		});
}

/**
 * 重置
 */
function resert(){
	$("#radio_rt_host").attr("checked","checked");
	initQuery();
}

/**
 * 查询
 */
function query(){	
	var form = document.forms['theForm']; 
	if(Validator.Validate(form,2) && verifyDate()){
		 //mask('正在查询,请稍后....','0.5','0px');
		 var options = {
			success:function(xml){
				queryFinish(xml);
			},
			resetForm:false,						
			converterTz:false,
			url:"customReport_query.do"
		};
		$("#theForm").ajaxSubmit(options);
	} 
}

//分页对象
var page={
};

function loadDataAndPage(){
	page.totalCount = page.list.length;
	page.pageCount=Math.ceil(page.list.length/page.pageSize);
	page.prePage=page.curPage-1;
	page.nextPage=page.curPage+1;
	
	var start = (page.curPage-1)*page.pageSize;
	var end = page.curPage*page.pageSize;
	if(end>=page.totalCount){
		end = page.totalCount;
	}else{
		end = end-1;
	}
	var tmpList= page.list;
    var objs = []; 
   	for(var i = start;i<end;i++ ){
   		objs.push(page.list[i]);
   	}
 	var tml = $('#temp_list').html();
	var tmp = _.template(tml, {'datas':objs} );
	$('#listTbody').html(tmp);
	//加载分页
	var tmll = $('#temp_page').html();
	var tmpp = _.template(tmll, page );
	$('#pageContent').html(tmpp);
}
/**
 * 查询成功
 */
 var charId = 1;
function queryFinish(xml){
	removeMask();
	$('.contentDiv').hide();
	
	var msg = JSON.parse(xml);
	var showType = $('#showType').val();
	if(showType==1){	
		$('#contentChart').show();
		var chartXml = msg.chartXml;	
		chartXml.chart.showAboutMenuItem='0';
		var chart1 = new FusionCharts("FusionCharts/ZoomLine.swf", "ChartId"+charId, "100%","500", "0", "0");
		chart1.setJSONData(chartXml);
		chart1.render("chartarea");
		charId++;
	}else{
		$('#contentTable').show();
		//修改指标值列的单位
		var kpith = '指标值('+$('#kpiunit').val()+')';
		$('#kpivalueTh').html(kpith);
		page = {
			pageSize:10,
			curPage:1,
			prePage:0,
			nextPage:0,
			list:[]
		}
		page.list = msg.resultList;
		loadDataAndPage();
	}	
}

/**
 * 跳页
 */
function gotoPage(p){
	page.curPage = p;
	loadDataAndPage();
}

/**
 * 更改每页条数
 */
function changePageSize(pageSize){
	page.pageSize = pageSize;
	page.curPage = 1;
	loadDataAndPage();
}

/**
 * 导出excel
 */
function reportExcelExport(){
	var url = "customReport_exportExcel.do";
	var form1= $('#theForm');
	form1.attr("action",url);
	form1.submit();
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
