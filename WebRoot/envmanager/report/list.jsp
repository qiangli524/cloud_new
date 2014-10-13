<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8"); 
	String contextPath=request.getContextPath();
	String domain = request.getParameter("domain");
	String showDo = domain;
	if(domain!=null&&domain.equals("2"))	
		domain="测试中心";
	else if(domain!=null&&domain.equals("3"))
		domain="演示环境";
	else if(domain!=null&&domain.equals("4"))
		domain="4";
	else
		domain="研发中心(含商务项目的研发)";

	if(showDo==null)
		showDo="1";

	String hostip = (String)session.getAttribute("ssd_hostip");
	String dbport = (String)session.getAttribute("ssd_dbport");
	String dbname = (String)session.getAttribute("ssd_dbname");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<title>研发生产环境数据列表</title>

<link rel="stylesheet" type="text/css" media="all" href="<%=contextPath%>/js/grid/calendar/calendar-blue.css"  />
<script type="text/javascript" src="<%=contextPath%>/js/grid/calendar/calendar.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/grid/calendar/calendar-cn-utf8.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/grid/calendar/calendar-setup.js"></script>

<link rel="stylesheet" type="text/css" href="<%=contextPath%>/js/grid/gt_grid.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/js/grid/skin/vista/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/js/grid/skin/pink/skinstyle.css" />
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/js/grid/skin/mac/skinstyle.css" />

<script type="text/javascript" src="<%=contextPath%>/js/grid/gt_msg_cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/grid/gt_const.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/grid/gt_grid_all.js"></script>

<link rel="stylesheet" href="<%=contextPath%>/js/menu/js/main.css">
<link rel="stylesheet" href="<%=contextPath%>/js/menu/js/xbreadcrumbs.css">
<script type="text/javascript" src="<%=contextPath%>/js/menu/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/menu/js/xbreadcrumbs.js"></script>


<style type="text/css">
 .mybutton-cls { 
		background : url('<%=contextPath%>/js/grid/skin/default/images/mybutton.png') no-repeat center center; 
}
</style>
    
<script type="text/javascript" >

var APP_DOMAIN='<%=domain%>';

var APP_PATH='<%=contextPath%>';

var grid_demo_id = "myGrid1" ;


var dsOption= {

	fields :[
		{name : 'ROWID' },
		{name : 'ENV',type:'int' },
		{name : 'DOMAIN' },
		{name : 'RESOURCE' },
		{name : 'CLASS' },  
		{name : 'HOSTTYPE' },
		{name : 'HOSTNUM' },
		{name : 'DESCRIPTION' }, 
		{name : 'SYSTEM' }, 
		{name : 'HOSTNAME' },
		{name : 'IP' },  
		{name : 'PRODUCT' },
		{name : 'DEVEPROD' },
		{name : 'SID' }, 
		{name : 'TABLESPACE' },  
		{name : 'FILESYSNAM' },  
		{name : 'FILEAPPNUM' },  
		{name : 'CPUUSED' },
		{name : 'MEM' }, 
		{name : 'FILEUSERD' },   
		{name : 'FILEUSEPER' },  
		{name : 'SID1' },
		{name : 'TABSPAUSED' },  
		{name : 'TABSPAUSEPER' },
		{name : 'SGA' }, 
		{name : 'CPULEFT' },
		{name : 'MEMLEFT' },
		{name : 'STORAGE' },
		{name : 'CFDATE' }
		
	],

	uniqueField : 'ROWID'
}



var colsOption = [
	 {id: 'ROWID' , header: "ROWID" , width :50, hidden:true },
	 {id: 'ENV' , header: "序号" , width :50, editor:{type:"text",defaultText : '', validRule : ['N']}},
     {id: 'DOMAIN' , header: "系统域" , width :60, editor:{type:"select",options : {"":"","研发中心(含商务项目的研发)":"研发中心(含商务项目的研发)","测试中心":"测试中心","演示环境":"演示环境"},defaultText : ''}},
     {id: 'RESOURCE' , header: "资源域" , width :60, editor:{type:"select",options : {"":"","数据池":"数据池","应用池":"应用池"},defaultText : ''} },
	 {id: 'CLASS' , header: "分类" , width :70, editor:{type:"select",options : {"":"","研发数据库":"研发数据库","研发应用":"研发应用","测试数据库":"测试数据库","测试应用":"测试应用","演示应用":"演示应用"},defaultText : ''} },
	 {id: 'HOSTTYPE' , header: "主机类型" , width :80, editor:{type:"select",options : {"":"","PC Server":"PC Server","小型机":"小型机","兼容机":"兼容机"},defaultText : ''} },
	 {id: 'HOSTNUM' , header: "型号" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'DESCRIPTION' , header: "配置" , width :60,  editor:{type:"textarea",width:"200px",height:"150px"}, toolTip : true ,toolTipWidth : 150 },
	 {id: 'SYSTEM' , header: "操作系统" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'HOSTNAME' , header: "主机名" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'IP' , header: "IP地址" , width :100, editor:{type:"text",defaultText : ''} },
	 {id: 'PRODUCT' , header: "使用产品线" , width :100, editor:{type:"text",defaultText : ''} },
	 {id: 'DEVEPROD' , header: "研发产品" , width :100, editor:{type:"text",defaultText : ''} },
	 {id: 'SID' , header: "实例" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'TABLESPACE' , header: "表空间（G）" , width :80, editor:{type:"text",defaultText : '', validRule : ['F']} },
	 {id: 'FILESYSNAM' , header: "文件系统" , width :80, editor:{type:"text",defaultText : ''} },
	 {id: 'FILEAPPNUM' , header: "文件系统大小（G）" , width :120, editor:{type:"text",defaultText : '', validRule : ['F']} },
	 {id: 'CPUUSED' , header: "CPU" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'MEM' , header: "内存（M）" , width :80, editor:{type:"text",defaultText : '', validRule : ['F']} },
	 {id: 'FILEUSERD' , header: "文件系统（G）" , width :100, editor:{type:"text",defaultText : '', validRule : ['F']} },
	 {id: 'FILEUSEPER' , header: "文件系统使用率" , width :120, editor:{type:"text",defaultText : ''} },
	 {id: 'SID1' , header: "实例" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'TABSPAUSED' , header: "表空间（G）" , width :80, editor:{type:"text",defaultText : '', validRule : ['F']} },
	 {id: 'TABSPAUSEPER' , header: "表空间使用率" , width :100, editor:{type:"text",defaultText : ''} },
	 {id: 'SGA' , header: "内存（G）" , width :80, editor:{type:"text",defaultText : ''} },
	 {id: 'CPULEFT' , header: "CPU" , width :60, editor:{type:"text",defaultText : ''} },
	 {id: 'MEMLEFT' , header: "内存（M）" , width :80, editor:{type:"text",defaultText : ''} },
	 {id: 'STORAGE' , header: "存储（G）" , width :80, editor:{type:"text",defaultText : ''} },
	 {id: 'CFDATE' , header: "更新日期" , width :80, editor:{type:"date",format:'%Y%m%d',defaultText : ''} }
 
];
 
Sigma.ToolFactroy.register(
	'mybutton',  
	{
		cls : 'mybutton-cls',  
		toolTip : '显示/隐藏列',
		action : function(event,grid) {  
			var gridId=grid.id;
			var hideColDialog = Sigma.createColumnDialog('show' , {gridId : gridId ,
							checkValid : function(r){ return !r.hidden; }, checkFn :'show' , uncheckFn:'hide',
					canCheck : function(col){ return col.hideable!==false&&!col.frozen; } });
			showColDialog(hideColDialog,grid.getMsg('MENU_SHOW_COL') ); }
	}
);

function showColDialog(columnDialog,title){
	if (!columnDialog) { return; }
	columnDialog.show();
	columnDialog.setTitle(title);
	grid.gridMenuButton.closeMenu();
}

var gridOption={
    id : grid_demo_id,
	loadURL : APP_PATH+'/report/controller.jsp?actionMethod=list&domain='+APP_DOMAIN,
	saveURL : APP_PATH+'/report/controller.jsp?actionMethod=save' ,
	remotePaging : false ,
	width: "100%",  //"100%", // 700,
	height: "450",  //"100%", // 330,
	container : 'gridbox', 
	replaceContainer : true, 
	customHead : 'myHead1',
//	showGridMenu : true,
//	allowCustomSkin	: true ,
//	allowFreeze	: true ,
//	allowHide	: true ,
//	allowGroup	: true ,
	dataset : dsOption ,
	columns : colsOption,
	pageSize : 15,
	pageSizeList : [15,30,50,100,200],
	toolbarContent : 'nav | pagesize | reload | add del save | filter | mybutton | state',
	onMouseOver : function(value,  record,  cell,  row,  colNo, rowNo,  columnObj,  grid){
		
		
		if (columnObj && columnObj.toolTip) {
			grid.showCellToolTip(cell,columnObj.toolTipWidth);
		}else{
			grid.hideCellToolTip();
		}
	},
	onMouseOut : function(value,  record,  cell,  row,  colNo, rowNo,  columnObj,  grid){
		grid.hideCellToolTip();
	}
}; 


var mygrid=new Sigma.Grid( gridOption );  
Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );


//////////////////////////////////////////////////////////

</script>
<script type="text/javascript">
	$(document).ready(function(){
        //  Initialize xBreadcrumbs
        $('#breadcrumbs-1').xBreadcrumbs();
	});
</script>
</head>
<body>
	  <table id="myHead1" style="display:none">
		<tr>
			<td rowspan="3" columnId='ROWID' resizable='false' style="vertical-align:middle;">ROWID</td>
			<td rowspan="3" columnId='ENV' style="vertical-align:middle;">序号</td>
			<td rowspan="3" columnId='DOMAIN' style="vertical-align:middle;">系统域</td>
			<td rowspan="3" columnId='RESOURCE' style="vertical-align:middle;">资源域</td>
			<td rowspan="3" columnId='CLASS' style="vertical-align:middle;">分类</td>
			<td rowspan="3" columnId='HOSTTYPE' style="vertical-align:middle;">主机类型</td>
			<td rowspan="3" columnId='HOSTNUM' style="vertical-align:middle;">型号</td>
			<td rowspan="3" columnId='DESCRIPTION' style="vertical-align:middle;">配置</td>
			<td rowspan="3" columnId='SYSTEM' style="vertical-align:middle;">操作系统</td>
			<td rowspan="3" columnId='HOSTNAME' style="vertical-align:middle;">主机名</td>
			<td rowspan="3" columnId='IP' style="vertical-align:middle;">IP地址</td>
			<td rowspan="3" columnId='PRODUCT' style="vertical-align:middle;">使用产品线</td>
			<td rowspan="3" columnId='DEVEPROD' style="vertical-align:middle;">研发产品</td>
			<td colspan="4" align="center">申请资源情况</td>
			<td colspan="8" align="center">使用情况</td>
			<td rowspan="2" colspan="3" align="center">剩余资源情况</td>
			<td rowspan="3" columnId='CFDATE' style="vertical-align:middle;">更新日期</td>
		</tr>
		<tr>
			<td colspan="2" align="center">数据库</td>
			<td colspan="2" align="center">应用</td>
			<td colspan="4" align="center">应用</td>
			<td colspan="4" align="center">数据库</td>
		</tr>
		<tr>
			<td columnId='SID'>实例</td>
			<td columnId='TABLESPACE'>表空间（G）</td>
			<td columnId='FILESYSNAM'>文件系统</td>
			<td columnId='FILEAPPNUM'>文件系统大小（G）</td>
			<td columnId='CPUUSED'>CPU</td>
			<td columnId='MEM'>内存（M）</td>
			<td columnId='FILEUSERD'>文件系统（G）</td>
			<td columnId='FILEUSEPER'>文件系统使用率</td>
			<td columnId='SID1'>实例</td>
			<td columnId='TABSPAUSED'>表空间（G）</td>
			<td columnId='TABSPAUSEPER'>表空间使用率</td>
			<td columnId='SGA'>内存（G）</td>
			<td columnId='CPULEFT'>CPU</td>
			<td columnId='MEMLEFT'>内存（M）</td>
			<td columnId='STORAGE'>存储（G）</td>
		</tr>
	</table>
	
    <div id="bigbox" style="margin:15px;display:!none;">
      <div id="gridbox" style="border:0px solid #cccccc;background-color:#f3f3f3;padding:5px;height:500px;width:100%;" ></div>
    </div>

</body>
</html>