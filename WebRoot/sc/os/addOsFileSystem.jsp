<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var api = frameElement.api;
		w = api.opener;
		
		$("#saveButton").click(function() {
			var _filesys_name = $("#filesys_name").val();
			if (_filesys_name == null|| _filesys_name == '') {
				alert('文件系统名称是必填项');
				$("#filesys_name").focus();
				return false;
			}else if(!/^\/[a-zA-Z]+$/.test(_filesys_name)){
				alert('文件系统名称格式不规范');
				$("#filesys_name").val("");
				$("#filesys_name").focus();
				return false; 
			}
			var _filesys_type = $("#filesys_type").val();
			if (_filesys_type == null|| _filesys_type == '') {
				alert('类型是必填项');
				$("#filesys_type").focus();
				return false;
			}
			
			var _volume_group = $("#volume_group").val();
			if (_volume_group == null|| _volume_group == '') {
				alert('卷组是必填项');
				$("#volume_group").focus();
				return false;
			}
			
			var _logical_volume = $("#logical_volume").val();
			if (_logical_volume == null|| _logical_volume == '') {
				alert('逻辑卷是必填项');
				$("#logical_volume").focus();
				return false;
			}
			
			var _filesys_size = $("#filesys_size").val();
			if (_filesys_size == null|| _filesys_size == '') {
				alert('大小是必填项');
				$("#filesys_size").focus();
				return false;
			}else if(!/^\d+$/.test(_filesys_size)){
				alert('大小必须是正整数');
				$("#filesys_size").val("");
				$("#filesys_size").focus();
				return false;
			}
			w._save_button_click_event($("#theForm").serialize());
		});
		
		$("#resetButton").click(function (){
			$("#filesys_name").val("");
			$("#filesys_type").val("");
			$("#volume_group").val("");
			$("#logical_volume").val("");
			$("#filesys_size").val("");
			$("#filesys_desc").val("");
		});	
		
		$("#filesys_name").change(function(){
			var _filesys_name = $("#filesys_name").val();
			if (_filesys_name != null|| _filesys_name != '') {
				$.ajax({
					type : 'post',
					url : 'osFileSystem_validFileSystem.do?' + $("#theForm").serialize(),
					success : function(msg) {
						if (msg == -1) {
							$("#filesys_name").val("文件系统" + _filesys_name + "已存在");
							$("#filesys_name").css("color","red");
						}else if(msg ==-2){
							$("#filesys_name").val("文件系统" + _filesys_name + "已存在");
							$("#filesys_name").css("color","red");
						}
					}
				});
			}
		});			
	});	
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">文件系统配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osFileSystem_saveFileSystem.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
				<s:hidden name="theForm.os_host_id" id="os_host_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">						
						<tr>
						    <td class="til">
								文件系统名称 <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.filesys_name" id="filesys_name" maxlength="30" cssClass="inpt-2"/>           
							</td>
							<td class="til">
								类型<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.filesys_type" id="filesys_type" cssClass="select-1"
									listValue="dictname " listKey="dictcode " list="theForm.sdList" headerKey="" headerValue="---请选择---"/>
							</td>
						</tr>
						<tr>
						    <td class="til">
								卷组<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.volume_group" id="volume_group" cssClass="select-1"
									listValue="vg_name " listKey="vg_name " list="theForm.vgList" headerKey="" headerValue="---请选择---"/>
							</td>
							<td class="til">
								逻辑卷<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.logical_volume" id="logical_volume" maxlength="30" cssClass="inpt-2"/>
							</td>
						</tr>						
						<tr>
							<td class="til">
								大小<font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.filesys_size" id="filesys_size" maxlength="30" cssClass="inpt-2"/> GB
							</td>
							<td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.filesys_desc" id="filesys_desc" maxlength="30" cssClass="textarea-1"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="btnCenter">
								<span class="ubtn-green"><input type="button" id="saveButton"  value="确定"/></span>
								<span class="ubtn-orange mgl-20"><input type="button" id="resetButton" value="重置" /></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
