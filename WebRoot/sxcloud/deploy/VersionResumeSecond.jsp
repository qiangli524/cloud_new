<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page
	import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil"%>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
<title></title>
<style type="text/css">
.font-more {
	width: 150px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
</style>
<style type="text/css">
/*恢复版本*/
.stepStatusList {
	color: #333;
	text-align: center;
	height: 40px;
	line-height: 37px;
	padding: 10px 0;
	border-bottom: 1px solid #f1f1f1;
}

.stepStatusList li {
	float: left;
	width: 155px;
	height: 37px;
	margin-left: -12px;
	background: url(<%=request.getContextPath()%>/sxcloud/images/version/process.gif
		) no-repeat 0 -76px;
}

.stepStatusList .frist {
	color: #fff;
	margin-left: 0;
	width: 140px;
	background-position: 0 0;
}

.stepStatusList .pass {
	color: #fff;
	background-position: 0 -36px;
}

.stepList {
	padding: 0 5px;
}

.stepList h3 {
	text-indent: 1em;
	line-height: 3em;
	font-size: 12px;
	font-weight: bold;
}

.stepList .radio,.stepList .checkbox {
	vertical-align: middle;
}

.stepList .step {
	display: ;
}

.stepList .step1 .stepRightBlock {
	background: url(<%=request.getContextPath()%>/sxcloud/images/application/hostBG.jpg
		) no-repeat 0 0;
}

.stepList .step1 .stepRightTitle {
	border: none;
	color: #fff;
}

.stepList .step1 .stepLeftList {
	
}

.stepList .step3 .stepRightBlock {
	height: 300px;
}

.stepList .step4 .stepLeftList {
	width: auto;
	height: auto;
	border-bottom: 0;
	margin: 0;
}

.stepList .btnLine {
	margin-bottom: 20px;
}

.stepRightBlock {
	float: right;
	width: 310px;
	height: 340px;
	padding: 0 20px;
	background-color: #f5f5f5;
}

.stepRightTitle {
	color: #000;
	margin-bottom: 1em;
	font-family: Tahoma;
	line-height: 2em;
	border-bottom: 1px solid #e1e1e1;
}

.stepRightTitle li {
	display: none;
}

.stepRightForm {
	
}

.stepRightForm li {
	display: none;
}

.stepRightForm td {
	padding-top: 3px;
}

.stepRightForm select {
	width: 100px;
	font-size: 12px;
}

.stepRightForm textarea {
	width: 160px;
}

.stepLeftList {
	width: 380px;
	height: 300px;
	line-height: 36px;
	margin-bottom: 10px;
	overflow: hidden;
	overflow-y: auto;
	border: 1px solid #f1f1f1;
	border-width: 1px 0;
}

.stepLeftList dt {
	cursor: pointer;
	border-bottom: 1px solid #e6e6e6;
	padding-left: 30px;
	background: url(../images/icon/nodes_add.gif) no-repeat 10px 50%;
}

.stepLeftList dt.extend {
	background-image: url(../images/icon/nodes_sub.gif);
}

.stepLeftList dd {
	border-bottom: 1px dotted #e6e6e6;
	padding-left: 60px;
	cursor: pointer;
}

.stepLeftList dd:hover,.stepLeftList dd.current {
	background-color: #fef0a5;
}

.stepHostInf dt {
	font-weight: bold;
}

.stepHostInf dd {
	line-height: 2em;
	cursor: default;
}
</style>
<script type="text/javascript">
	function resetForm(theForm) {
		theForm.APPID.value = '0';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';
	}

	function searchRequest() {
		var versionid;
		var count = 0;
		var versionids = document.getElementsByName("radioid");
		if (versionids != null) {
			var i;
			for (i = 0; i < versionids.length; i++) {
				if (versionids[i].checked) {
					count = count + 1;
					versionid = versionids[i].value;
				}
			}
		}
		if (count < 1) {
			alert("请勾选需要恢复的版本！");
			return false;
		}
		var id = versionid.split('&');
		var appid = id[0];
		var day_version = id[1];
		theForm.APPID.value = appid;
		theForm.day_version.value = day_version;
		theForm.submit();
	}

	function hisVersion(app_id) {
		var dialog = $.dialog({
			id : 'version',
			title : '历史版本',
			max : false,
			min : false,
			content : 'url:version_historyVersion.do?appId=' + app_id
		});
	}
	function changebar(resume_type) {
		if (resume_type == 1) {
			document.getElementById('fileresume').style.display = "none";
			document.getElementById('fullresume').style.display = "";
		} else if (resume_type == 0) {
			document.getElementById('fileresume').style.display = "";
			document.getElementById('fullresume').style.display = "none";
		}
	}
</script>
</head>
<body class="scrollbody">
	<s:form action="rollback_getThirdPath" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
		<s:hidden name="theForm.day_version" id="day_version"></s:hidden>
		<s:hidden name="theForm.APPNAME" id="APPNAME"></s:hidden>
		<div class="scrollbody">

			
			<ul class="stepStatusList" id="fileresume">
				<li class="frist">选择业务应用</li>
				<li class="pass">选择恢复版本</li>
				<li>选择恢复文件</li>
				<li>选择上线主机</li>
				<li>完成</li>
			</ul>
			<ul class="stepStatusList" id="fullresume" style="display: none">
				<li class="frist">选择业务应用</li>
				<li class="pass">选择恢复版本</li>
				<li>选择上线主机</li>
				<li>完成</li>
			</ul>

			<div class="box on">
				<div class="blue-wrap noborder">

					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>应用名称</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:property value="theForm.APPNAME" /></td>
								</tr>
							</tbody>
						</table>
						<br />
						<table width="100%" class="querytable" border="0">
							<tr>
								<td class="til">选择恢复类型: <%--                     <s:select id="ID" list="#{'':'-请选择-','0':'物理主机','1':'虚拟主机'}"    name="theForm.resume_type" id="resume_type"  >--%>
									<%--						</s:select> --%> <s:radio
										list="#{'0':'按文件恢复','1':'按版本恢复'}" name="theForm.resume_type"
										id="resume_type" onclick="changebar(this.value)"></s:radio></td>
							</tr>
						</table>
						<br />
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>选择版本</th>
									<%--									<th>应用名称</th>--%>
									<%--									<th>应用版本</th>--%>
									<th>日期版本</th>
									<th>版本时间</th>
									<th>版本描述</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.versionList" id="theBean">
									<tr>
										<td><input id="radioid" class="radio" name="radioid"
											type="radio"
											value='<s:property value="#theBean.appid"/>&<s:property value="#theBean.day_version"/>'>
										</td>
										<%--										<td><input name="checkboxid" type="checkbox"--%>
										<%--											value='<s:property value="#theBean.app_id"/>&<s:property value="#theBean.day_version"/>' />--%>
										<%--										</td>--%>
										<%--										<td><s:property value="#theBean.app_id" /></td>--%>
										<%--										<td><s:property value="#theBean.version" /></td>--%>
										<td><s:property value="#theBean.day_version" />
										</td>
										<td><s:property value="#theBean.insertTime" />
										</td>
										<td><s:property value="#theBean.versionDesc" />
										</td>

									</tr>
								</s:iterator>
							</tbody>
						</table>
						<br />
						<table width="100%" border="0" cellspacing="0"
							class="pop-table nosize">
							<tr>
								<td colspan="8" class="btns">
									<div>
										<input type="button" class="btn-style02" value="上一步"
											onclick="window.history.back()" /> <input type="button"
											class="btn-style02" value="下一步"
											onclick="javascript:searchRequest();" />
									</div></td>
							</tr>

						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>


</body>
