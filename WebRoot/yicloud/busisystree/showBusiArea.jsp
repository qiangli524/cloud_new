<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>

<link href="<%=request.getContextPath()%>/sxcloud/images/nresources/bizarea-css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/images/nresources/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'busiareainfo_showBusiAreaInfoBiz.do',
			success:function(rs){
				$("#bizcount").text(rs[0].bizcount);
				$("#appcount").text(rs[0].appcount);
				$("#examplecount").text(rs[0].examplecount);
			}
		});
		
		$.ajax({
			type:'post',
			dataType:'json',
			url:'busiareainfo_showBusiAreaInfoBusiHost.do',
			success:function(rs){
				$("#busihostcount").text(rs[0].countBusiHost)
				$("#runcount").text(rs[0].countWorkBusiHost)
				$("#unruncount").text(rs[0].countUnworkBusiHost)
			}
		});
		
		$.ajax({
			type:'post',
			dataType:'json',
			url:'busiareainfo_showBusiAreaInfoProcess.do',
			success:function(rs){
				$("#processcount").text(rs[0].processNum);
				$("#normalcount").text(rs[0].rprocessNum);
				$("#unnormalcount").text(rs[0].urprocessNum);
			}
		});
		
		$.ajax({
			type:'post',
			dataType:'json',
			url:'busiareainfo_showBusiAreaInfoTask.do',
			success:function(rs){
				$("#ordernum").text(rs[0].orderNum);
				$("#tasknum").text(rs[0].taskNum);
				$("#runnum").text(rs[0].exeNum);
			}
		});
	});
	
	$(function(){
		$("[name='viewbusihost']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewHost',
				title:'部署主机明细',
				width:'800px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listBusiHost.do'
			});
		});
		$("[name='viewrunhost']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewRunHost',
				title:'正在运行的部署主机',
				width:'800px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listBusiHost.do?workstatus=1'
			});
		});
		$("[name='viewstophost']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewStopHost',
				title:'已停止的部署主机',
				width:'800px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listBusiHost.do?workstatus=0'
			});
		});
		$("[name='viewprocess']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewProcess',
				title:'进程总数',
				width:'900px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listProcess.do'
			});
		});
		$("[name='viewnormal']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewNor',
				title:'正常进程数',
				width:'900px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listProcess.do?isnor=normal'
			});
		});
		$("[name='viewunnormal']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewUnnor',
				title:'异常进程数',
				width:'900px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listProcess.do?isnor=unnormal'
			});
		});
		$("[name='vieworder']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewOrder',
				title:'订单总数',
				width:'800px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listOrder.do'
			});
		});
		$("[name='viewtask']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewTask',
				title:'任务总数',
				width:'900px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listTask.do'
			});
		});
		$("[name='viewruntask']").unbind().live("click",function(){
			currentEdit = $(this);
			$.dialog({
				id:'viewRunTask',
				title:'正在运行的任务数',
				width:'900px',
				height:'400px',
				max:true,
				min:true,
				content:'url:busiareainfo_listTask.do?status=1'
			});
		});
	});
</script>
</head>
<body>
<s:form action="" method="post">
<div>
<table width="80%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
	<td align="left" valign="top" class="panel-gj">
    	<h2 class="biz"></h2>
        <table width="370" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;">
	          <tr>
	            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/business.png" width="16" height="16" /></td>
	            <td width="60">业务系统个数：</td>
	            <td width="120" class="orange-20">
					<span id="bizcount"></span>
	            </td>
	          </tr>
	          <tr>
	            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/sysapp.png" width="16" height="16" /></td>
	            <td width="60">应用个数：</td> 
	            <td width="120" class="orange-20">
		            <span id="appcount"></span>
				</td>
	          </tr>
	          <tr>
	            <td width="25" height="50"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/example.png" width="16" height="16" /></td>
	            <td width="60">实例个数：</td>
	            <td width="120" class="orange-20">
	            	<span id="examplecount"></span>
	            </td>
	          </tr>
        </table>
        
        
        
        <div class="clr" style=" height: 57px;"></div>
        <h2 class="hosts"></h2>
       <table width="370" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;">
	          <tr>
		            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/Host.png" width="16" height="16" /></td>
		            <td width="60">部署机总数：</td>
		            <td width="120" class="orange-20">
		            	<a href="javascript:;" name="viewbusihost" class="orange"><span id="busihostcount"></span></a>
		            </td>
	          </tr>
	          <tr>
		            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/accept.png" width="16" height="16" /></td>
		            <td width="60">正在运行数：</td> 
		            <td width="120" class="orange-20">
		            	<a href="javascript:;" name="viewrunhost" class="orange"><span id="runcount"></span></a>
		            </td>
	          </tr>
	          <tr>
		            <td width="25" height="50"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/shutdown.png" width="16" height="16" /></td>
		            <td width="60">已停止数：</td>
		            <td width="120" class="orange-20">
		            	<a href="javascript:;" name="viewstophost" class="orange"><span id="unruncount"></span></a>
		            </td>
	          </tr>
        </table>
    </td>
    
    
    <td align="left" valign="top" class="panel-gj">
    	<h2 class="process"></h2>
        <table width="370" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;">
	          <tr>
	            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/process.png" width="16" height="16" /></td>
	            <td width="60">进程总数：</td>
	            <td width="120" class="orange-20">
	            	<a href="javascript:;" name="viewprocess" class="orange"><span id="processcount"></span></a>
	            </td>
	          </tr>
	          <tr>
	            <td width="32"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/blue.gif" width="16" height="16" /></td>
	            <td width="74">正常个数：</td> 
	            <td width="164" class="orange-20">
	            	<a href="javascript:;" name="viewnormal" class="orange"><span id="normalcount"></span>
	            </td>
	          </tr>
	          <tr>
	            <td width="25" height="50"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/red.gif" width="16" height="16" /></td>
	            <td width="60">异常个数：</td>
	            <td width="120" class="orange-20">
	            	<a href="javascript:;" name="viewunnormal" class="orange"><span id="unnormalcount"></span></a>
	            </td>
	          </tr>
        </table>
        
        
        <div class="clr" style=" height: 57px;"></div>
        <h2 class="order"></h2>
        <table width="370" border="0" cellspacing="0" cellpadding="0" class="font-14 mgt-10" style="height: 40px;">
	          <tr>
	            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/order.png" width="16" height="16" /></td>
	            <td width="80">订单总数：</td>
	            <td width="100" class="orange-20">
	            	<a href="javascript:;" name="vieworder" class="orange"><span id="ordernum"></span></a>
	            </td>
	          </tr>
	          <tr>
	            <td width="25"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/task.png" width="16" height="16" /></td>
	            <td width="80">任务总数：</td> 
	            <td width="100" class="orange-20">
	            	<a href="javascript:;" name="viewtask" class="orange"><span id="tasknum"></span></a>
	            </td>
	          </tr>
	          <tr>
	            <td width="25" height="50"><img src="<%=request.getContextPath()%>/sxcloud/images/virtual/accept.png" width="16" height="16" /></td>
	            <td width="80">正在运行任务个数：</td>
	            <td width="100" class="orange-20">
	            	<a href="javascript:;" name="viewruntask" class="orange"><span id="runnum"></span></a>
	            </td>
	          </tr>
        </table>
    </td>
  </tr>
</table>
</div>
</s:form>
</body>
