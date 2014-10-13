<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	function buttonControl(disabled){
	 	 api.button({
		     id:'Ok',
		     name: '确定',
		     callback:submitRequest,
		     focus: true,
		     disabled: disabled 
			 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	 }
	$(function(){
		buttonControl(true);
	});
	function submitRequest(){
   		theForm.action ="phyHost_installOS.do";
		theForm.submit();
		return false;
	}
	//主机重启
	function reboot(){
		if(theForm.host.value==null || theForm.host.value==''){
			alert('请选择主机！');
			return false;
		}
		theForm.action = 'phyHost_rebootHost.do';
		theForm.submit();
	}
	//选择主机
	function chooseHosts(){
		w.$.dialog({
	 		id:'hosts',
	 		lock: true,
	 		title:'主机',
	 		width: '700px',
	 		height: '350px',
	 		content: 'url:phyHost_chooseHosts.do'
	 	 });
	}
	//以选择的主机处理
	function choosedHosts(ips,num){
		api.get('management').focus();
		theForm.host.value = ips;
		buttonControl(false);
		var html = '';
		html += '<td class="til">已选择</td><td>已选择'+num +'台主机</td>';
		$("#choosedtr").empty().html(html);
	}
	//设置主机从PXE启动
	function bootPXE(){
		if(theForm.host.value==null || theForm.host.value==''){
			alert('请选择主机！');
			return false;
		}
		theForm.action = 'phyHost_bootFromPXE.do';
		theForm.submit();
	}
	//执行impi命令返回结果调用函数
	function callBack(result){
		alert(result);
	}
	function callBackClose(result){
		alert(result);
		api.close();
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form method="post" id="theForm" target="hidden_form">
	<s:hidden name="host" id="host"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="200px;">
						选择主机 <font color="red">*</font>
					</td>
					<td>
						<a href="javascript:;" onclick="chooseHosts();" style="text-decoration:underline;color:#CC3300;">选择主机</a>
					</td>
				</tr>
				<tr id="choosedtr">
				</tr>
				<tr>
					<td class="til">
						（第一步）启动:
					</td>
					<td>
						<a href="javascript:;" style="text-decoration:underline;color:#CC3300;" onclick="bootPXE();">PXE启动</a>
					</td>
				</tr>
				<tr>
					<td class="til">
						（第二步）操作系统:
					</td>
					<td>
		                 <s:select list="#{'rhel6.1':'rhel6.1','esx5.1':'esx5.1','xen6.0':'xen6.0','xcp1.6':'xcp1.6'}" name="os"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">（第三步）操作：</td>
					<td>
						<a href="javascript:;" style="text-decoration:underline;color:#CC3300;" onclick="reboot();">重启</a>
					</td>
				</tr>
			</table>
	</s:form>
	<iframe id="hidden_form" name="hidden_form" style="display:none"></iframe>
</body>
