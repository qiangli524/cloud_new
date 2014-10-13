var resourceType;
function initQuery(){
	resourceType = $("input[class=resourceType]:checked").val();
	var tml = $('#temp_query_'+resourceType).html();
	var tmp = _.template(tml, {} );
	$('#tbody').html(tmp);

	if(resourceType=='host'){		
		//初始化业务系统
		initBusiness();
		//初始化数据中心
		initDataCenter();
		//初始化机房
		initRoom();
	}else if(resourceType=='vm'){
		//初始化业务系统
		initBusiness();
		//初始化数据中心
		initDataCenter();
	}else if(resourceType=='ip'){
		//初始化VLAN
		initVlan();
		//初始化网络域
		initSubNet();
	}
}


/**
 * 查询
 */
function query(){	
	var form = document.forms['theForm']; 
	 var options = {
		success:function(xml){
			queryFinish(xml);
		},
		resetForm:false,						
		converterTz:false,
		url:"assetReport_queryAssetData"+".do"
	};
	$("#theForm").ajaxSubmit(options);
}

/**
 * 查询成功
 */
 var charId = 1;
function queryFinish(xml){
	removeMask();
	$('.contentDiv').hide();	
	$('#contentTable').show();
	page = {
		pageSize:10,
		curPage:1,
		prePage:0,
		nextPage:0,
		list:[]
	}
	page.list = JSON.parse(xml);
	loadDataAndPage();
}

/**
 * 导出excel
 */
function reportExcelExport(){
	var exportObj = {};
	var heads = [];
	var keys = [];
	var exports = $('.export');
	exports.each(function(i,e){
		heads[i]=$(e).html();
		keys[i]=$(e).attr('key');
	});
	exportObj.heads = heads;
	exportObj.keys = keys;
	$('#exportjsonId').val(JSON.stringify(exportObj));
	var url = "assetReport_exportExcel.do";
	var form1= $('#theForm');
	form1.attr("action",url);
	form1.submit();
}

/**
 * 重置
 */
function resert(){
	//$("#radio_rt_host").attr("checked","checked");
	initQuery();
}

//---------------------------------------------------------------------

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
 	var tml = $('#temp_list_'+resourceType).html();
	var tmp = _.template(tml, {'datas':objs} );
	$('#listContent').html(tmp);
	//加载分页
	var tmll = $('#temp_page').html();
	var tmpp = _.template(tmll, page );
	$('#pageContent').html(tmpp);
}
//---------------------------------------------------------------------

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

//---------------------------------------------------------------------
var vlanList = [];
function initVlan(){
	if(vlanList.length==0){		
		getVlans();
	}
	var html = '<option value="-1">所有</option>';
	$(vlanList).each(function (i, vlan) { 
		html = html + '<option value="'+vlan.id+'">'+vlan.name+'</option>';							
	})
	$('#vlanId').html(html);	
}

function getVlans(){
	$.ajax({
		type : 'post',
		async : false,
		url : "assetReport_getVlanList.do",
		data:{},
		dataType: 'json',
		success : function(data){
			vlanList=data;
		},
		error : function(data,textStatus){
			console.log('error:' + data);
		}
	});				
}

var subNetList = [];
function initSubNet(){
	if(subNetList.length==0){		
		getSubNet();
	}
	var html = '<option value="-1">所有</option>';
	$(subNetList).each(function (i, vlan) { 
		html = html + '<option value="'+vlan.id+'">'+vlan.name+'</option>';							
	})
	$('#subnetId').html(html);	
}

function getSubNet(){
	$.ajax({
		type : 'post',
		async : false,
		url : "assetReport_getSubNetList.do",
		data:{},
		dataType: 'json',
		success : function(data){
			subNetList=data;
		},
		error : function(data,textStatus){
			console.log('error:' + data);
		}
	});				
}

//初始化机房
var roomList = [];
function initRoom(){
	if(roomList.length==0){		
		getRoom();
	}
	var html = '<option value="-1">所有</option>';
	$(roomList).each(function (i, room) { 
		html = html + '<option value="'+room.id+'">'+room.name+'</option>';							
	})
	$('#roomId').html(html);	
}
function getRoom(){
	$.ajax({
		type : 'post',
		async : false,
		url : "assetReport_getRoomList.do",
		data:{},
		dataType: 'json',
		success : function(data){
			roomList=data;
		},
		error : function(data,textStatus){
			console.log('error:' + data);
		}
	});				
}