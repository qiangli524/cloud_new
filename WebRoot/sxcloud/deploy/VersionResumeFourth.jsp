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
<script type="text/javascript">
	function resetForm(theForm) {
		theForm.APPID.value = '0';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';
	}

	function searchRequest() {
		var couterNum = 0;

		var checkboxids = document.getElementsByName("checkboxid");

		var exampleids = '';

		if (checkboxids != null && checkboxids.length > 0) {
			for ( var i = 0; i < checkboxids.length; i++) {
				if (checkboxids[i].checked) {
					couterNum = couterNum + 1;
					exampleids = exampleids + checkboxids[i].value + ',';
				}
			}
		}
		theForm.exampleids.value = exampleids;
		if (couterNum == 0) {
			alert("请勾选需要恢复的主机！");
			return false;
		}
		theForm.submit();
	}
	function selectAll() {
		var n = document.getElementsByName("checkboxid").length;
		var select = document.getElementsByName("checkboxid");
		if (select.length) {
			for ( var i = 0; i < n; i++) {
				if (select[i].value != "-1") {
					if (select[i].checked == true) {
						select[i].checked = false;
					} else {
						select[i].checked = true;
					}
				}
			}
		}
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
</script>
</head>
<body class="scrollbody">
	<s:form action="rollback_finish" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
		<s:hidden name="theForm.APPNAME" id="APPNAME"></s:hidden>
		<s:hidden name="theForm.fileids" id="fileids"></s:hidden>
		<s:hidden name="theForm.exampleids" id="exampleids"></s:hidden>
		<s:hidden name="theForm.day_version" id="day_version"></s:hidden>
		<s:hidden name="theForm.resume_type" id="resume_type"></s:hidden>
		<s:hidden name="theForm.tobkgfilepaths" id="tobkgfilepaths"></s:hidden>

		<div class="scrollbody">

			
			<ul class="stepStatusList">
				<li class="frist">选择业务应用</li>
				<li class="pass">选择恢复版本</li>
				<s:if test="theForm.resume_type==0">
					<li class="pass">选择恢复文件</li>
				</s:if>
				<li class="pass">选择要恢复的主机</li>
				<li>完成</li>
			</ul>

			<div class="box on">


				<div class="blue-wrap noborder">
					<div class="table-head">

						<%--			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />--%>
					</div>
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
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>日期版本</th>
									<th>恢复类型</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:property value="theForm.day_version" />
									</td>
									<s:if test="theForm.resume_type==0">
										<td>按文件恢复</td>
									</s:if>
									<s:elseif test="theForm.resume_type==1">
										<td>全版本恢复</td>
									</s:elseif>
								</tr>
							</tbody>
						</table>
						<br />
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>涉及文件路径</th>
<%--									<th>涉及文件版本</th>--%>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.tobkgfilepathsmap" id="theBean">
									<tr>
										<td><s:property value="#theBean.key" />
										</td>
<%--										<td><s:property value="#theBean.value" />--%>
<%--										</td>--%>
									</tr>
								</s:iterator>	
							</tbody>
						</table>
						<br />
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th><input name="allcheckbox" type="checkbox"
										onclick="selectAll()" /></th>
<%--									<th>应用id</th>--%>
									<th>主机IP</th>
<%--									<th>目前实例状态</th>--%>
<%--									<th>目前应用状态</th>--%>
<%--									<th>上次上线路径</th>--%>
<%--									<th>是否重启</th>--%>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.hostList" id="theBean">
									<tr>
										<td><input name="checkboxid" type="checkbox"
											value='<s:property value="#theBean.ID"/>' /></td>
<%--										<td><s:property value="#theBean.APPID" /></td>--%>
										<td><s:property value="#theBean.IP" /></td>
<%--										<td><span id="div<s:text name="#theBean.ID"/>"> <s:if--%>
<%--													test="#theBean.DEPLOY_FLAG==0">--%>
<%--								    未部署--%>
<%--								</s:if> <s:elseif test="#theBean.DEPLOY_FLAG==1">--%>
<%--													<img--%>
<%--														src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif"--%>
<%--														width="15" height="18" />正在部署--%>
<%--								</s:elseif> <s:elseif test="#theBean.DEPLOY_FLAG==2">--%>
<%--								   已部署--%>
<%--								</s:elseif> </span>--%>
<%--										</td>--%>
<%--										<td>--%>
<%--											<div id="divstartstop<s:text name="#theBean.ID"/>">--%>
<%--												<s:if test="#theBean.START_STOP_FLAG==0">--%>
<%--													<img--%>
<%--														src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif"--%>
<%--														width="15" height="18" />正在停止--%>
<%--								</s:if>--%>
<%--												<s:elseif test="#theBean.START_STOP_FLAG==1">--%>
<%--													<img--%>
<%--														src="<%=request.getContextPath()%>/sxcloud/images/downed.png"--%>
<%--														width="16" height="16" />已停止--%>
<%--								</s:elseif>--%>
<%--												<s:elseif test="#theBean.START_STOP_FLAG==2">--%>
<%--													<img--%>
<%--														src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif"--%>
<%--														width="15" height="18" />正在启动--%>
<%--								</s:elseif>--%>
<%--												<s:elseif test="#theBean.START_STOP_FLAG==3">--%>
<%--													<img--%>
<%--														src="<%=request.getContextPath()%>/sxcloud/images/uped.png"--%>
<%--														width="16" height="16" />已启动--%>
<%--								</s:elseif>--%>
<%--											</div>--%>
<%--										</td>--%>
<%--										<td style="width: 150px"><a style="color: black;"--%>
<%--											class="font-more"--%>
<%--											title='<s:property value="#theBean.DEPLOYPATH"/>'> <s:property--%>
<%--													value="#theBean.DEPLOYPATH" /> </a></td>--%>
<%--										<td><s:if test="#theBean.isrestart==0">不重启</s:if> <s:elseif--%>
<%--												test="#theBean.isrestart==1">重启</s:elseif>--%>
<%--										</td>--%>
									</tr>
								</s:iterator>
							</tbody>
						</table>
			<br/>
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
