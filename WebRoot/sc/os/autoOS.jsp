<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
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
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function() {
		$(".query").click(function() {
			if ($(".query-form").is(":visible")) {
				$(".query-form").slideUp("slow");
			} else {
				$(".query-form").slideDown("slow");
			}
		});

		$("#addForm").click(function() {
			$.dialog({
				id : 'addHost',
				title : '添加设备信息',
				width : '650px',
				height : '400px',
				max : false,
				min : false,
				lock : true,
				content : 'url:autoos_addOsHost.do?oper=add'
			});
		});

		$("[name='mod']").click(function() {

			currentEdit = $(this);
			var id = '';
			var lenth = 0;
			$('[name=theForm.id]:checkbox:checked').each(function() {
				id += $(this).val();
				lenth += 1;
			});
			if (id == null || id == '') {
				alert("请勾选一条信息");
				return false;
			} else if (lenth > 1) {
				alert('只能选择一项进行修改');
				return false;
			}
			$.dialog({
				id : 'vdi',
				title : '查看主机信息',
				width : '800px',
				height : '600px',
				max : true,
				min : true,
				lock : true,
				content : 'url:autoos_modifyOsHost.do?id=' + id
			});

			//alert('该功能在维护中...');
		});

		$("#installForm").click(function() {
			currentEdit = $(this);
			var ids = '';
			var lenth = 0;
			$('[name=theForm.id]:checkbox:checked').each(function() {
				ids += $(this).val() + ',';
				lenth += 1;
			});
			if (ids == null || ids == '') {
				alert("请选择主机");
				return false;
			}
			if (confirm("你选择了" + lenth + "台主机，请确认")) {
				$.ajax({
					type : 'post',
					url : 'autoos_batchCustomedInstall.do?ids=' + ids,
					success : function(msg) {
						if (msg == 0) {
							alert("系统检测到没有选择安装项目。")
						} else if (msg == -1) {
							alert("保存失败");
						} else {
							$("#theForm").submit();
						}
					}
				});
			}
		});

		$("#importexcel").click(function() {
			$.dialog({
				id : "browse",
				title : '浏览文件',
				width : '400px',
				height : '225px',
				content : 'url:hostexcel_excel_browse.do'
			});
		});
	});

	function searchRequest() {
		theForm.submit();
	}

	function deleteConfig() {
		alert("删除按钮正在优化....");
		/*
		var couterNum = 0;
		var checkboxids = document.getElementsByName("theFormid");
		if (checkboxids != null && checkboxids.length > 0) {
			for ( var i = 0; i < checkboxids.length; i++) {
				if (checkboxids[i].checked) {
					couterNum = couterNum + 1;
				}
			}
		}
		if (couterNum == 0) {
			alert("请勾选一条信息");
			return false;
		}

		var id = '';
		$('[name=theForm.id]:checkbox:checked').each(function() {
			id += $(this).val() + ",";
		});
		if (confirm("确定要删除?")) {
			theForm.action = "autoos_deleteOsHost.do?id=" + id;
			theForm.submit();
		}
		 */
	}

	function resetForm(){
		$("#serial_num").val("");
		$("#mge_console_ip").val("");
		$("#eq_type").val("");
		$("#install_state").val("");
		$("#stay_machroom").val("");
	}

	//响应弹出窗口中，保存按钮
	function _save_button_click_event(argForm) {
		$.ajax({
			type : 'post',
			url : 'autoos_updateCustomedInstall.do?' + argForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
	//响应弹出窗口中，保存并安装按钮
	function _install_button_click_event(argForm) {
		$.ajax({
			type : 'post',
			url : 'autoos_customedInstall.do?' + argForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
	function _clear_button_click_event(argForm) {
		$.ajax({
			type : 'post',
			url : 'autoos_clearOsHostConfig.do?' + argForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
	//配置和安装OS页面前，密码验证
	function validUserInfo(id){
		$.dialog({
			id : 'customed_install_v',
			title : '授权',
			width : '400px',
			height : '350px',
			max : false,
			min : false,
			lock : true,
			content : 'url:autoos_validUserInfo.do?theForm.id=' + id
		});
	}
	//配置 超链接使用
	function _config_a_click_event(id) {
		//2014年9月16日  Marked By JamTau
		//configAndInstallOs(id);
		validUserInfo(id);
	}
	//配置 超链接使用
	function _install_a_click_event(id) {
		//2014年9月16日  Marked By JamTau
		//configAndInstallOs(id);
		validUserInfo(id);
	}
	function configAndInstallOs(id) {		
		theForm.action = "autoos_configInstall.do?theForm.id=" + id;
		theForm.submit();
	}
	//展现资产明细
	function _show_detail_a_click_event(id) {
		$.dialog({
			id : 'customed_install_v',
			title : '资产明细',
			width : '1000px',
			height : '550px',
			max : false,
			min : false,
			lock : true,
			content : 'url:autoos_showHostDetail.do?id=' + id
		});
	}

	function customedInstall(theForm) {
		$.ajax({
			type : 'post',
			url : 'autoos_updateCustomedInstall.do?' + theForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}

	function saveHostInfo(theForm) {
		$.ajax({
			type : 'post',
			url : 'autoos_insertOsHost.do?' + theForm,
			success : function(msg) {
				if (msg == -1) {
					alert("保存失败");
				} else {
					$("#theForm").submit();
				}
			}
		});
	}
	
	function _show_a_click_event(id){
		$.dialog({
			id : 'customed_install_v',
			title : '资产明细',
			width : '1000px',
			height : '550px',
			max : false,
			min : false,
			lock : true,
			content : 'url:autoos_osConfigDetail.do?theForm.id=' + id
		});
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="autoos_listOsHost.do" method="post" id="theForm"
		cssStyle="theForm">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">物理机安装</h2>
			<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
					<label class="vm">设备序列号：</label>
					<s:textfield name="theForm.serial_num" id="serial_num"></s:textfield>
					<label class="vm">管理IP：</label>
					<s:textfield name="theForm.mge_console_ip" id="mge_console_ip"></s:textfield>
					<label class="vm">主机类型：</label>
					<s:select 
						list="#{'':'请选择','2':'IBMx86刀片','3':'IBM PC服务器','4':'HPx86刀片','6':'HP PC服务器','7':'HUAWEI PC服务器'}"
						name="theForm.eq_type" id="eq_type"/>
					<label class="vm">OS安装状态：</label>
					<s:select 
					    list="#{'':'请选择','0':'待安装','1':'安装中','2':'已安装','3':'安装失败','4':'已配置'}"
						name="theForm.install_state" id="install_state"/>
					<label class="vm">所属机房：</label>
					<s:select list="#{'':'请选择','1':'府青机房','2':'西区机房','3':'上海电信'}" name="theForm.stay_machroom" id="stay_machroom"/>
					<span class="ubtn-1 mgl-20">
						<input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" />
					</span>
					<span class="ubtn-2 mgl-20">
						<input id="resetForm_button" type="button" onclick="javascript:resetForm()" value="重置" />
					</span>
				</div>
				<div class="utt-2 mgt-20">
					<a name="add" class="icon-add" id="addForm">新增</a> <a name="del"
						class="icon-del" href="javascript:void(0)"
						onclick="javascript:deleteConfig();">删除</a> <a
						name="template_install" class="icon-check" id="installForm">批量安装</a>
					<a name="customed_install" class="icon-occupy" id="importexcel">EXCEL导入</a>
				</div>
				<div class="table-ct">
					<table id="theTable" width="100%" border="0" cellspacing="0"
						class="blue-table sorttable">
						<thead id="table">
							<tr>
								<th>选择</th>
								<th onclick="sort(theTable,18,'string')">设备编号</th>
								<th onclick="sort(theTable,1,'string')">序列号</th>
								<th onclick="sort(theTable,2,'string')">主机类型</th>
								<!--<th onclick="sort(theTable,17,'string')">操作系统版本</th>  -->
								<th onclick="sort(theTable,3,'string')">主机型号</th>
								<th onclick="sort(theTable,4,'string')">CPU个数</th>
								<th onclick="sort(theTable,5,'string')">内存</th>
<%--								<th onclick="sort(theTable,6,'string')">存储块数</th>--%>
								<th onclick="sort(theTable,7,'string')">存储大小</th>
<%--								<th onclick="sort(theTable,8,'string')">网卡</th>--%>
								<th onclick="sort(theTable,9,'string')">所属机房</th>
								<th onclick="sort(theTable,10,'string')">物理位置</th>
								<th onclick="sort(theTable,11,'string')">IPMI管理IP</th>
								<!--<th onclick="sort(theTable,12,'string')">管理用户</th>  -->
								<th onclick="sort(theTable,13,'string')">OS安装状态</th>
								<th onclick="sort(theTable,14,'string')">入网时间</th>
								<!-- 
								<th onclick="sort(theTable,15,'string')">OS安装时间</th>								 
								<th onclick="sort(theTable,16,'string')">描述</th>-->

								<!--								<th onclick="sort(theTable,18,'string')">操作系统位数</th>-->
								<!--								<th onclick="sort(theTable,20,'string')">raid信息</th>-->
								<!--								<th onclick="sort(theTable,21,'string')">bond信息</th>-->
								<!--								<th onclick="sort(theTable,22,'string')">光纤卡</th>-->
								<!--								<th onclick="sort(theTable,23,'string')">共享存储</th>-->
							</tr>
						</thead>
						<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<td>
										<input type="checkbox" id="id" value="<s:property value="#theBean.id"/>" name="theFormid"
										<s:if test="#theBean.install_state==0">
	                					</s:if>
										<s:else>
	                						disabled
	                					</s:else> />
									</td>
									<td><s:property value="#theBean.eq_id" />
									</td>
									<td><a name="a_instal" hrer="javascript:void(0);"
										onclick="_show_detail_a_click_event('<s:property value="#theBean.id"/>')"><s:property
												value="#theBean.serial_num" /> </a></td>
									<td><s:if test="#theBean.eq_type == 1">
									IBM小型机
								</s:if> <s:elseif test="#theBean.eq_type == 2">
									IBMx86刀片
								</s:elseif> <s:elseif test="#theBean.eq_type == 3">
									IBM PC服务器
								</s:elseif> <s:elseif test="#theBean.eq_type == 4">
									HPx86刀片
								</s:elseif> <s:elseif test="#theBean.eq_type == 5">
									机架服务器
								</s:elseif> <s:elseif test="#theBean.eq_type == 6">
									HP PC服务器
								</s:elseif>
								<s:elseif test="#theBean.eq_type == 7">
									HUAWEI PC服务器
								</s:elseif></td>
									<!--<td><s:property value="#theBean.os_versions" />
									</td>  -->
									<td><s:property value="#theBean.host_type_num" />
									</td>
									<td><s:property value="#theBean.cpu_cl" />个</td>
									<td><s:if test='#theBean.memory !=0'>
											<%-- 原单位未统一到MB，为B --%>
											<%--												<s:property value="@java.lang.Math@round(#theBean.mem/1024/1024*100) / 100.0"/>--%>
											<s:property
												value="@java.lang.Math@round(#theBean.memory/1024*100) / 100" />
												G
											</s:if>
									</td>
<%--									<td><s:property value="#theBean.storage_num" />块</td>--%>
									<td><s:if test="#theBean.store!=0">
											<s:property
												value="@java.lang.Math@round(#theBean.store/1024)" />G			
										    </s:if> <s:else>
												0G
											</s:else></td>
<%--									<td><s:property value="#theBean.nic_num" />个</td>--%>
									<td><s:if test="#theBean.stay_machroom == 1">
									府青机房
								</s:if> <s:elseif test="#theBean.stay_machroom == 2">
									西区机房
								</s:elseif>
								<s:elseif test="#theBean.stay_machroom == 3">
									上海电信
								</s:elseif>
								</td>
									<td><s:property value="#theBean.host_physical_position" />
									</td>
									<td><s:property value="#theBean.mge_console_ip" />
									</td>
									<!--<td><s:property value="#theBean.mge_console_username" />
									</td>  -->
									<td><s:if test="#theBean.install_state==0">
											<a name="a_config" href="javascript:void(0);" onclick="_config_a_click_event('<s:property value="#theBean.id"/>')">待安装</a>
										</s:if>
										<s:elseif test="#theBean.install_state==1">
                							<a name="a_instal" hrer="javascript:void(0);" onclick="_show_a_click_event('<s:property value="#theBean.id"/>')">安装中</a>
                						</s:elseif>
                						<s:elseif test="#theBean.install_state==2">
                							<a name="a_instal" hrer="javascript:void(0);" onclick="_show_a_click_event('<s:property value="#theBean.id"/>')">已完成</a>
                						</s:elseif>
                						<s:elseif test="#theBean.install_state==3">
                							<a name="a_config" href="javascript:void(0);" onclick="_config_a_click_event('<s:property value="#theBean.id"/>')">安装失败</a>
                						</s:elseif>
                						<s:elseif test="#theBean.install_state==4">
											<a name="a_instal" hrer="javascript:void(0);" onclick="_install_a_click_event('<s:property value="#theBean.id"/>')">已配置</a>
										</s:elseif>
									</td>
									<td><s:property value="#theBean.insert_date" />
									</td>
									<!--
									<td><s:property value="#theBean.install_date" />
									</td>
										<td><s:property value="#theBean.install_desc" /></td>-->
									<!--									-->
									<!--									<td><s:property value="#theBean.os_digits" />-->
									<!--									</td>-->
									<!--									<td><s:property value="#theBean.raid_information" />-->
									<!--									</td>-->
									<!--									<td><s:property value="#theBean.bond_information" />-->
									<!--									</td>-->
									<!--									<td><s:property value="#theBean.fibercard" />-->
									<!--									</td>-->
									<!--									<td><s:property value="#theBean.shared_storage" />-->
									<!--									</td>-->
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
				</div>
			</div>
	</s:form>
</body>