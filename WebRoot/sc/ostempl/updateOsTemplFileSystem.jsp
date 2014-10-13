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
	function submitRequest(thisForm){  
		var _filesys_name = $("#filesystem_name").val();
		if (_filesys_name == null|| _filesys_name == '') {
			alert('文件系统名称是必填项');
			$("#filesystem_name").focus();
			return false;
		}else if(_filesys_name == 'swap'){
			return true;
		}else if(!/^\/[a-zA-Z]*$/.test(_filesys_name)){
			alert('文件系统名称格式不规范');
			$("#filesystem_name").val("");
			$("#filesystem_name").focus();
			return false; 
		}
		
		var _filesystem_type = $("#filesystem_type").val();
		if (_filesystem_type == null|| _filesystem_type == '') {
			alert('文件系统类型是必选项');
			$("#filesystem_type").focus();
			return false;
		}
				
		var _volume_group = $("#volume_group").val();
		if (_volume_group == null|| _volume_group == '') {
			alert('卷组是必选项');
			$("#volume_group").focus();
			return false;
		}
		
		var _logical_volume = $("#logical_volume").val();
		if (_logical_volume == null|| _logical_volume == '') {
			alert('逻辑卷是必选项');
			$("#logical_volume").focus();
			return false;
		}
		
		var _filesys_size = $("#filesystem_size").val();
		if (_filesys_size == null|| _filesys_size == '') {
			alert('大小是必填项');
			$("#filesystem_size").focus();
			return false;
		}else if(!/^\d+$/.test(_filesys_size)){
			alert('大小必须是正整数');
			$("#filesystem_size").val("");
			$("#filesystem_size").focus();
			return false;
		}
		
		$.ajax({
			type : 'post',
			url : 'osTemplComp_validOsTemplateFileSystem.do?' + $("#theForm").serialize(),
			success : function(msg) {
				if (msg == -1) {
					alert("文件系" + _filesys_name + "已存在");
				} else {
					thisForm.submit();
				}
			}
		});	
	}
</script>
</head>
<body class="pop-body scrollbody">
	<div class="pd-20 bgcolor-1">
      <h2 class="utt-1">文件系统配置</h2>
         <div class="bord-1 pd-10">
			<s:form action="osTemplComp_modifyOsTemplateFileSystem.do" method="post" cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.id" id="id"></s:hidden>
				<s:hidden name="theForm.template_id" id="template_id"></s:hidden>
					<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
						<tr>
						    <td class="til">
								文件系统名称 <font color="red">*</font>
							</td>
							<td>
								<s:textfield name="theForm.filesystem_name" id="filesystem_name" maxlength="30" cssClass="inpt-2"/>            
								<font color="grey">（名称不能重复）</font>
							</td>
							<td class="til">
								类型<font color="red">*</font>
							</td>
							<td>
								<s:select name="theForm.filesystem_type" id="filesystem_type" cssClass="select-1"
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
								<s:textfield name="theForm.filesystem_size" id="filesystem_size" maxlength="30" cssClass="inpt-2"/> (单位：GB)
							</td>
							<td class="til">
								备注
							</td>
							<td>
								<s:textarea name="theForm.filesystem_desc" id="filesystem_desc" maxlength="30" cssClass="textarea-1"/>
							</td>
						</tr>
						<tr>
							<td colspan="4" class="btnCenter">
								<span class="ubtn-orange"><input type="button"  value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" /></span>
								<span class="ubtn-blue mgl-20"><input type="button"  value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"></span>
								<span class="ubtn-green mgl-20"><input type="button"  value="返回" onclick="window.history.back()"/></span>
							</td>
						</tr>
					</table>
			</s:form>
	    	</div>
        </div>
    </div>
</body>

</html:html>
