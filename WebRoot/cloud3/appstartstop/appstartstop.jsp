<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />


<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<head>
<title>应用批量启停执行页面</title>
<script type="text/javascript">
	function submitRequest() {

		var choose_type = $("input[name='theForm.choose_type']:checked").val();
		if ('1' == choose_type) {
			var name = $("#username").val();
			var password = $("#passwd").val();
			//var password = document.getElementById("passwd").value;
			var exepath = $("#exepath").val();
			var ip = $("#ip").val();
			if (name == null || name == '') {
				alert('请填写主机用户');
				return false;
			}
			if (password == null || password == '') {
				alert('请填写用户密码');
				return false;
			}
			if (exepath == null || exepath == '') {
				alert('请填写执行命令或脚本');
				return false;
			}
			if (ip == null || ip == '') {
				alert('请填写主机ip');
				return false;
			}
		} else {
			var upload_file = document.getElementById("upload_file").value;
			if (upload_file == '' || upload_file == null) {
				alert("请先选择要导入的文件!");
				return false;
			}
			var choosed = $.trim(upload_file);
			if (choosed.substring(choosed.lastIndexOf(".") + 1) != "xls") {
				alert("选择格式不正确!");
				return false;
			}
		}
		theForm.submit();
	}

	$(function() {
		$(".filetype").change(function() {
			var val = $("input[name='theForm.choose_type']:checked").val();//获得选中的radio的值  
			if (val == '1') {
				document.getElementById("file").style.display = "none";
				document.getElementById("name").style.display = "";
				document.getElementById("password").style.display = "";
				document.getElementById("path").style.display = "";
				document.getElementById("type").style.display = "";
				document.getElementById("ips").style.display = "";
			} else {
				document.getElementById("file").style.display = "";
				document.getElementById("name").style.display = "none";
				document.getElementById("password").style.display = "none";
				document.getElementById("path").style.display = "none";
				document.getElementById("type").style.display = "none";
				document.getElementById("ips").style.display = "none";
			}
		});
	});
</script>
<style>
.path {
	width: 335px;
	height: 20px;
	vertical-align: middle;
	overflow-y: hidden;
	line-height: 20px;
	padding: 0px;
	border: none;
	border: 1px solid #999;
}
</style>
</head>
<body class="pop-body scrollbody">

	<s:form action="appStartStop_exe.do" id="theForm"
		enctype="multipart/form-data" method="post">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til"><font color="red">选择方式：</font>
				</td>
				<td><s:radio list="#{1:'批量模式',2:'附件模式'}"
						name="theForm.choose_type" value="1" id="choose_type"
						cssClass="filetype"></s:radio><font color="red">批量模式支持环境资源(用户，密码，路径等)相同情况；附件模式都支持(环境资源相同和不同)</font>
				</td>
			</tr>
			<tr id="name">
				<td class="til">统一登陆用户名<font color="red">*</font>
				</td>
				<td><s:textfield name="theForm.username" cssClass="txt"
						id="username"></s:textfield>
				</td>
			</tr>
			<tr id="password">
				<td class="til">统一登陆密码<font color="red">*</font>
				</td>
				<td><s:password name="theForm.password" cssClass="txt"
						id="passwd">
					</s:password>
				</td>
			</tr>
			<tr id="path">
				<td class="til">执行命令或脚本(shell脚本带全路径)<font color="red">*</font>
				</td>
				<td><s:textarea cols="40" rows="2" name="theForm.exepath"
						id="exepath"></s:textarea>
				</td>
			</tr>
			<tr id="type">
				<td class="til">执行方式</td>
				<td><s:select list="#{1:'shell脚本',2:'命令'}"
						name="theForm.exetype" id="exetype"></s:select>
				</td>
			</tr>
			<tr id="ips">
				<td class="til">主机ip(多ip之间以,分割)<font color="red">*</font>
				</td>
				<td><s:textarea cols="40" rows="5" name="theForm.ip" id="ip"></s:textarea>
				</td>
			</tr>
			<tr id="file" style="display:none">
				<td class="til">上传附件<font color="red">*</font>
				</td>
				<td><s:file name="file" id="upload_file"></s:file><a
					href="<%=request.getContextPath()%>/cloud3/appstartstop/template.xls">下载模板</a>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter"><input type="button"
					class="thickbox btn-style02" value="执行"
					onclick="javascript:submitRequest()" /> <input type="button"
					class="thickbox btn-style02" value="重置"
					onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
			<s:if test="theForm.returnMsg!=''">
				<tr>
					<td class="til"><font color="red">执行结果：</font></td>
					<td><s:textarea cols="40" rows="4" name="theForm.returnMsg"></s:textarea>
					</td>
				</tr>
			</s:if>
		</table>
	</s:form>
</body>
