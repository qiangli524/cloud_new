<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<script type="text/javascript"> 
	var api = frameElement.api;
	var w = api.opener; 
	 	
	api.button({
		id:'OkAnd',
		name: '确定',
		callback:selecthost,
		focus: true
	},
	{
		id:'cancle',
		 name: '取消'
	});
	
	function selecthost() { 
		var lenth = 0;
	    var id = '';
	    $('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
	    if(id==null || id == ''){
	    	alert("请勾选一条信息！");
	 	    return false ;
	    }else if(lenth>1){
	   		alert("一次只能选择一条信息");
		    return false ;
		}
		var hostName = $("[name='checkboxid']:checked").attr("hostName");//主机名称
		var host_uuid = $("[name='checkboxid']:checked").attr("host_uuid");//主机标识
		var connect_uuid = $("[name='checkboxid']:checked").attr("connect_uuid");//主机链接
			api.get("migrateVM").getHost(hostName,host_uuid,connect_uuid);
		}
	function selecthostByName(){
		var uuid = '<s:property value="uuid" />';
		var connect_id = '<s:property value="connect_id" />';
		theForm.action = 'united_selectHostFormigrate.do?uuid='+uuid+'&connect_id='+connect_id;
		theForm.submit();
	}
	function resetForm(){
		$("#name").attr("value","");
	}
</script>
</head>
<body class="pop-body scrollbody">
<div class="mainbody">
<s:form action="" method="post" id="theForm">
<div>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">选择主机</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">主机名称：</label>
				<s:textfield name="name" id="name"  cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:selecthostByName()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			       <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">主机名称</th>
				   <th onclick="sort(theTable,2,'string')">主机IP</th>
                   <th onclick="sort(theTable,3,'string')">cpu使用率</th>
                   <th onclick="sort(theTable,4,'string')">内存使用率</th>
                   <th onclick="sort(theTable,5,'string')">存储使用率</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="resultList" >
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id" />" 
								hostName='<s:property value="#theBean.eq_name"/>' host_uuid='<s:property value="#theBean.host_uuid"/>' 
								connect_uuid='<s:property value="#theBean.connect_uuid"/>' 
							/></td>
							<td><s:property value="#theBean.eq_name" /></td>
							<td><s:property value="#theBean.eq_ip" /></td>
							<td><s:property value="#theBean.cpu_usage" /></td>
							<td><s:property value="#theBean.mem_useage" /></td>
							<td><s:property value="#theBean.store_usage" /></td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
