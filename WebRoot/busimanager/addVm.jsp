<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ include file="../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<head>

<title></title>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;

	api.button({
		id : 'Ok',
		name : '保存',
		callback : getVmInfo,
		focus : true
	}, {
		id : 'cancle',
		name : '取消'
	});
	$(function() {
		$(".query").click(function() {
			if ($(".query-form").is(":visible")) {
				$(".query-form").slideUp("slow");
			} else {
				$(".query-form").slideDown("slow");
			}
		});

	});
	function resetForm(theForm) {
		$("#queryName").attr("value", "");
		$("#hostip").attr("value", "");
		$("#queryType").attr("value", '0');
		$("#queryState").attr("value", '');
		$("#queryVHIP").attr("value", "");
	}
	function searchRequest() {
		theForm.submit();
	}
	function changePageText(state, vh_uuid) {
		$("#" + vh_uuid + " td:eq(8)").text(state);
		var arr = $("#" + vh_uuid + " td:last-child a").attr("href").split(",");
		if (state == "正在运行") {
			arr[9] = "1";
		} else if (state == "已关闭") {
			arr[9] = "0";
		}
		$("#" + vh_uuid + " td:last-child a").attr("href", arr.toString());
	}

	//获取到主机IP，赋值查询框
	function getHostip(hostip) {
		theForm.hostip.value = hostip;
	}

	function changeHostIp() {
		var type = $("#queryType option:selected").val();
		if (type == 0) {
			$("#queryHostIp option").remove();
			$("#queryHostIp").append(createSelect("0", "--请选择--"));
		} else {
			var url = "showvm_queryHostIp.do?type=" + type;
			$.getJSON(url, function(data) {
				$("#queryHostIp option").remove();
				$("#queryHostIp").append(createSelect("0", "--请选择--"));
				if (data != null) {
					$.each(data, function(key, value) {
						$("#queryHostIp").append(createSelect(key, value));
					});
				}
			});
		}
	}

	function createSelect(value, text) {
		var opt = document.createElement("option");
		opt.setAttribute("value", value);
		opt.appendChild(document.createTextNode(text));
		return opt;
	}

	function getVmInfo() {
		var id = '';
		var con = '';
		var param = '';
		var length = 0;
		var parent_id = '<s:property value="parent_id"/>';
		$('[name=id]:checkbox:checked').each(function() {
			id = $(this).attr("value");
			con = $(this).attr("connID");
			param = param + ',' + id + '_' + con;
			length = length + 1;
		});
		if (length == 0) {
			alert("请先选择虚拟机再添加!");
			return false;
		}
		w.saveVm(param, parent_id);
	}
</script>
</head>
<style type="text/css">
div.hidden {
	width: 200px;
	height: 30px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	text-overflow: ellipsis; /* IE/Safari */
	-ms-text-overflow: ellipsis;
	-o-text-overflow: ellipsis; /* Opera */
	-moz-binding: url("ellipsis.xml#ellipsis"); /*FireFox*/
}
</style>
<body onLoad="self.focus();document.theForm.queryName.focus()"
	class="mainbody">
	<s:form action="bmanager_addVm.do" method="post" cssClass="theForm"
		id="theForm">
		<s:hidden id="parent_id" name="parent_id"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<div class="clearfix filtrate-area">
					<div class="filtrate-field">
						<label class="fl">虚拟机名称:</label>
						<s:textfield name="vmForm.queryName" id="queryName"
							cssClass="inpt-1 fl"></s:textfield>
					</div>
					<div class="filtrate-field">
						<label class="fl">IP:</label>
						<s:textfield name="vmForm.queryVHIP" id="queryVHIP"
							cssClass="inpt-1 fl"></s:textfield>
					</div>
					<div class="filtrate-field">
						<label class="fl">虚拟化类型:</label>
						<s:select id="queryType" cssClass="select-1 vm"
							name="vmForm.queryType"
							list="#{'0':'--请选择--','1':'VMWARE','3':'XEN','9':'其他'}"
							onchange="changeHostIp();"></s:select>
					</div>
					<div class="filtrate-field">
						<span class="ubtn-1 mgl-20"><input type="button" value="查询"
							onclick="javascript:searchRequest()" /> </span> <span
							class="ubtn-2 mgl-20"><input type="button" value="重置"
							onclick="javascript:resetForm()" /> </span>
					</div>
				</div>
				<div class="pd-10">
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th onclick="sort(theTable,0,'string')">选择</th>
								<th onclick="sort(theTable,1,'string')">虚拟机名称</th>
								<th onclick="sort(theTable,2,'string')">IP</th>
								<th onclick="sort(theTable,3,'string')">虚拟化类型</th>
								<!-- <th onclick="sort(theTable,4,'string')">应用个数</th> -->
								<th onclick="sort(theTable,5,'string')">操作系统</th>
								<th onclick="sort(theTable,6,'string')">CPU</th>
								<th onclick="sort(theTable,7,'string')">内存</th>
								<!-- <th>性能</th> -->
								<th onclick="sort(theTable,8,'string')">状态</th>
								<!-- <th>明细</th> -->
							</tr>
						</thead>
						<tbody>
							<s:iterator value="vmForm.resultList" id="theBean">
								<tr id="<s:property value="#theBean.VH_UUID"/>"
									vhid='<s:property value="#theBean.VH_UUID"/>'
									connID='<s:property value="#theBean.connectId"/>'>
									<td><input type="checkbox"
										value="<s:property value="#theBean.VH_UUID"/>"
										connID='<s:property value="#theBean.connectId"/>' name="id" />
									</td>
									<td width="200">
										<div class="hidden"
											title='<s:property value="#theBean.VH_NAME"/>'>
											<s:property value="#theBean.VH_NAME" />
											</a>
										</div></td>
									<td><s:property value="#theBean.VH_IP" />
									</td>
									<td><s:if test="#theBean.VH_TYPE ==1">
			  				VMWARE
			  			</s:if> <s:elseif test="#theBean.VH_TYPE ==2">
			  				POWERVM
			  			</s:elseif> <s:elseif test="#theBean.VH_TYPE ==3">
			  				XEN
			  			</s:elseif> <s:else>-</s:else> <%-- <td hostid='<s:property value="#theBean.H_ID" />'>
			  			<s:if test="#theBean.APPNUM !=0">
			  				<a href='javascript:;' name='app_list'>
			  					<s:property value="#theBean.APPNUM"/>个
			  				</a>
			  			</s:if>
			  			<s:else>
			  				<s:property value="#theBean.APPNUM"/>个
			  			</s:else>
			  		</td> --%>
										<td width="200">
											<div class="hidden"
												title='<s:property value="#theBean.VH_SYSTEM"/>'>
												<s:property value="#theBean.VH_SYSTEM" />
												</a>
											</div></td>
										<td><s:property value="#theBean.VH_CPU" />核</td>
										<td><s:property value="#theBean.VH_MEM" />MB</td> <!-- <td><a href='javascript:;' name='vm_motion'>性能</a></td> -->
										<td id="stateText"><s:if test="#theBean.VH_STAT==1">
							正在运行
						</s:if> <s:if test="#theBean.VH_STAT==0">
							已关闭
						</s:if> <s:if test="#theBean.VH_STAT==2">
							挂起
						</s:if></td> <%-- <td ><a href="javascript:showVMDetail('<s:property value="#theBean.VH_NAME"/>','<s:property value="#theBean.EQ_NAME"/>','<s:property value="#theBean.VH_TYPE"/>',
			  		'<s:property value="#theBean.EQ_ID"/>','<s:property value="#theBean.VH_SYSTEM"/>','<s:property value="#theBean.VH_CPU"/>','<s:property value="#theBean.VH_MEM"/>',
			  		'<s:property value="#theBean.VH_STORAGE"/>','<s:property value="#theBean.VH_NETWORK"/>','<s:property value="#theBean.VH_STAT"/>','<s:property value="#theBean.VH_UUID"/>',
			  		'<s:property value="#theBean.VH_IP"/>','<s:property value="#theBean.connectId"/>')">查看</a></td> --%>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
			</div>
	</s:form>
	</div>
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
