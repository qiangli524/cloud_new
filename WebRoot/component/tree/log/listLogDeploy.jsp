<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
<title></title>
<script type="text/javascript">
	var treeId = '<s:property value="#request.id"/>',hostIP='<s:property value="#request.hostIP"/>',type='<s:property value="#request.type"/>';
	function addRequest(){
		$.dialog({
					id:"add",
					title:'增加日志信息',
					width: '500px',
					height: '320px',
					lock:true,
					content: 'url:logDeploy_addLog.do?hostIP='+hostIP+'&treeId='+treeId+'&type='+type,
				});
	}
	function modRequest(){
		var id = $(":checkbox[checked='checked']").val();
		if(id == null || id == ''){
			alert("请选择要修改的日志对象!");
			return false;
		}
		$.dialog({
			id:"update",
			title:'修改日志信息',
			width: '500px',
			height: '320px',
			lock:true,
			content: 'url:logDeploy_updateLog.do?id='+id,
		});
	}
	function delRequest(){
		var id = $(":checkbox[checked='checked']").val();
		if(id == null || id == ''){
			alert("请选择要删除的日志对象!");
			return false;
		}
		logForm.logId.value = id;
		logForm.appId.value = treeId;
		logForm.action = "logDeploy_deleteLogDeploy.do"
		if(confirm('你确定要删除该日志对象吗？')){
			$.ajax({
				type:"POST",
				url:"logDeploy_deleteLogDeploy.do",
				data:$("#logForm").serialize(),
				dataType:"text",
				cache:false,
				async: false,
				success:function(data){
					if(data != -1){
						$(":checkbox[checked='checked']").parent().parent().remove();
					}else{
						alert("删除失败!");
					}
				}
			});
			//$("#tabs").tabs('destroy');
			//$('#tabs').tabs('load', 0);
			//$(ui.panel).load("www.baidu.com");
			//var tab = $('#tabs').tabs('getSelected');
			//tab.panel('refresh');
		}
	}
	$(function(){
		$check = $(":checkbox");
		$check.live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
	$(function(){
		$("a[name='path']").live("click",function(data){
			if(confirm("连接主机查看日志?")){
				id = $(this).attr("logid");	
				type = $(this).attr("logtype");
				if(type === "1"){//文件
					$.dialog({
						title:'查看日志',
						width: '800px',
						height: '450px',
						lock:true,
						content: 'url:hostLog_getLogInfo.do?id='+id,
					});
				}else if(type === "2"){//文件夹
					$.dialog({
						title:'查看日志',
						width: '800px',
						height: '450px',
						lock:true,
						content: 'url:hostLog_getLogMenu.do?id='+id,
					});
				}
			}
		});
	});
	//保存后
	function afterSave(data){
		if(data){
			var html = '';
			for(i=0;i<data.length;i++){
				 	html += '<tr><td><input type="checkbox" value="' + data[i].id + '"/>' + '</td><td>'+data[i].name+'</td><td>';
				if(data[i].type=="1"){
					html += '文件</td><td>';
				}else if(data[i].type=="2"){
					html += '文件夹</td><td>';
				}
				html += '<a href="javascript:;" name="path" logid="'+data[i].id+'" logtype="'+ data[i].type+'">' + data[i].path+'</a></td><td>';
				html += data[i].description+'</td><td>'+data[i].insDate+'</td></tr>';
			}
			$("#tbody_list").prepend(html);
		}
	}
	function afterUpdate(data){
		var html = '<tr><td><input type="checkbox" value="' + data[0].id + '"/>' + '</td><td>'+data[0].name+'</td><td>';
		if(data[0].type=="1"){
			html += '文件</td><td>';
		}else if(data[0].type=="2"){
			html += '文件夹</td><td>';
		}
		html += '<a href="javascript:;" name="path" logid="'+data[0].id+'" logtype="'+ data[0].type+'">' + data[0].path+'</a></td><td>';
		html += data[0].description+'</td><td>'+data[0].insDate+'</td></tr>';
		$(":checkbox:checked").parent().parent().replaceWith(html);
	}
</script>
</head>
<body>
<s:form action="logDeploy_listLogDeploy.do" method="post" id="logForm">
<s:hidden id="logId" name="theForm.id"></s:hidden>
<s:hidden id="appId" name="theForm.treeId"></s:hidden>
<div class="scrollbody">
			 <div class="utt-2">
				<a class="icon-add" href="javascript:void(0)"  onclick = "addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick = "modRequest();return false;"  >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick = "delRequest();return false;" >删除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  		<tr>
			  			<th>选择</th>
						<th onclick="sort(theTable,1,'string')">日志文件名称</th>
						<th onclick="sort(theTable,2,'string')">文件/文件夹</th>
						<th onclick="sort(theTable,3,'string')">路径</th>
						<th onclick="sort(theTable,4,'string')" >描述</th>
						<th onclick="sort(theTable,5,'date')">修改时间</th>			  			
			  		</tr>
			  </thead>
			  <tbody id="tbody_list">
			  		<s:iterator value="#request.resultList" id="theBean">
			  			<tr>
			  				<td> <input type="checkbox" value="<s:property value='#theBean.id'/>"/> </td>
			  				<td> <s:property value="#theBean.name"/> </td>
			  				<td>
			  					<s:if test="#theBean.type==1">文件</s:if>
			  					<s:elseif test="#theBean.type==2">文件夹</s:elseif>
			  				</td>
			  				<td> 
			  					<a href="javascript:;" name="path" logid="<s:property value='#theBean.id'/>" logtype="<s:property value='#theBean.type'/>"><s:property value="#theBean.path"/></a> 
			  				</td>
			  				<td> <s:property value="#theBean.description"/> </td>
			  				<td> <s:property value="#theBean.insDate"/> </td>
			  			</tr>
			  		</s:iterator>
			  </tbody>
			</table>
</div>
</s:form>
</body>
