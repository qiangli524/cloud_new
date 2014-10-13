<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		     callback:confirm,
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
		$("#boot").click(function(){
			if(this.checked){
				buttonControl(false);
			}else{
				buttonControl(true);
			}
		});
	});
	
	function confirm(){
		theForm.action="unitedOper_rebootToBIOS.do";
		theForm.submit();
		return false;
		
	}
	function toBIOSCallBack(result){
		bar('bios',result,4);
		api.close();//关闭对话窗
	}
	//挂载ISO镜像
	function mount(){
		theForm.isoPath.value = $("#path").find('option:selected').text();
		theForm.action="unitedOper_mountISO.do";
		bar('mount','镜像挂载中,请稍后...',120);
		theForm.submit();
	}
	//挂载镜像回调函数
	function callBack(result){
		barEnd('mount',result);
	}
	
	function bar(idstr,contents,seconds){
		w.$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    time: seconds,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}
	
	function barEnd(idstr,contents){
		w.$.dialog.list[idstr].content(contents,false,false);
		w.$.dialog.list[idstr].time(4);
	}
</script>
</head>
<body class="pop-body scrollbody" >
	<s:form method="post" id="theForm" target="hidden_frame">
		<s:hidden name="virtualMachineUnitedVO.connectCode" value="%{connect_id}"></s:hidden>
		<s:hidden name="virtualMachineUnitedVO.vmCode" value="%{uuid}"></s:hidden>
		<s:hidden name="virtualMachineUnitedVO.vType" value="%{vtype}"></s:hidden>
		<s:hidden name="virtualMachineUnitedVO.isoPath" id="isoPath"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="20%">
					ISO路径：
				</td>
				<td>
					<s:select list="queryList" cssStyle="width:300px;" id="path"></s:select>
					<a href="javascript:;" onclick="mount();" style="text-decoration:underline;">挂载</a>
				</td>
			</tr>
			<tr>
				<td class="til">
					引导选项：
				</td>
				<td>
					<s:checkbox  name="bios" id="boot"></s:checkbox>虚拟机下次引导时，进入BIOS设置
				</td>
			</tr>
		</table>
	</s:form>
	<iframe  name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
</body>
