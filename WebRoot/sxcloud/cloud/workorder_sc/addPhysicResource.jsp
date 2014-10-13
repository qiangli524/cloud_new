<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../common/taglib.jsp"%>
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
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function() {
		api.button({
			id : 'OkAnd',
			name : '确定',
			callback : createResource,
			focus : true
		}, {
			id : 'cancle',
			name : '取消'
		});
	});

	function createResource() {
		/*
		var vmname = $("#vmname").val();
		if (vmname.length == 0) {
			alert("请指定主机名称");
			return false;
		}
		var temid = $("#temid").val();*/
		var maxCounts = $("#maxCounts").val();
		var re_num = $("#re_num").val();
		if (re_num == 0) {
			alert("申请数量不能为0");
			return false;
		}
		if (re_num > maxCounts) {
			alert("申请数量不能为大于剩余可分配数量");
			return false;
		}
		var srsize = $("#srsize").val();
		var cpu = $("#cpu").val();
		var memsize = $("#memsize").val();
		var flag = true;
		/*
		mask('正在检测存储大小是否满足模板要求，请稍后....','0.7','0px');
		$.ajax({
			type:'post',
			url:'workorder_checkStore.do?srsize='+srsize+'&temid='+temid,
			async:false,
			success:function(msg){
				removeMask();
				if (msg == 1) {
					flag = true;
				} else {
					alert("抱歉，存储太小，无法提交任务单");
				}
			}
		});
		 */
		//if (flag) {
		var oper = $("#oper").val();
		var wrid = "";
		if ('edit' == oper) {
			wrid = $("#wrid").val();
		}
		var projectid = $("#projectid").val();
		/*
		mask('正在检测资源余量是否充足，请稍后....', '0.7', '0px');
		$.ajax({
			type : 'post',
			url : 'workorder_checkResourceLeft.do?srsize=' + srsize + '&cpu='
					+ cpu + '&memsize=' + memsize + '&projectid=' + projectid
					+ '&wrid=' + wrid,
			async : false,
			success : function(msg) {
				removeMask();
				if (msg == 1) {
					flag = true;
				} else {
					flag = false;
					alert("很遗憾，资源余量不足，无法提交任务单");
				}
			}
		});*/
		//}
		if (flag) {
			api.get("viewResource").saveResource($("#theForm").serialize());
		} else {
			alert("数据不合法,无法提交任务单");
			return false;
		}
	}

	$(function() {
		$("#chooseResourcePool").click(function() {
			var oper = $("#oper").val();
			w.$.dialog({
				id : 'addpool',
				title : '选择划入的业务资源池',
				width : '450px',
				height : '300px',
				max : false,
				min : false,
				lock : true,
				content : 'url:workorder_listResourcePool.do?oper=' + oper
			});
		});

		$("#chooseTem").click(function() {
			var oper = $("#oper").val();
			w.$.dialog({
				id : 'addtem',
				title : '选择可分配资源类型',
				width : '680px',
				height : '450px',
				max : false,
				min : false,
				lock : true,
				content : 'url:workorder_listResourceType.do?oper=' + oper
			});
		});

		$("#selectClu").click(function() {
			var oper = $("#oper").val();
			w.$.dialog({
				id : 'addclu',
				title : '选择集群',
				width : '1100px',
				height : '550px',
				max : false,
				min : false,
				lock : true,
				content : 'url:workorder_selectCluster.do?oper=' + oper
			});
		});

		$("#selectHost").click(
				function() {
					var cluid = $("#clusterid").val();
					var connectid = $("#connectid").val();
					if (cluid.length == 0) {
						alert("请先选择集群");
						return false;
					}
					var oper = $("#oper").val();
					w.$.dialog({
						id : 'addhost',
						title : '选择主机',
						width : '1100px',
						height : '550px',
						max : false,
						min : false,
						lock : true,
						content : 'url:workorder_selectHost.do?oper=' + oper
								+ '&domainid=' + cluid + '&vlainid='
								+ connectid
					});
				});

		$("#domainid").change(
				function() {
					$("#netid").empty();
					$("#ipaddress").val(null);
					var domainid = $("#domainid").val();
					$
							.ajax({
								type : 'post',
								dataType : "json",
								url : 'workorder_selectVlan.do?domainid='
										+ domainid,
								async : false,
								success : function(msg) {
									if (msg == -1) {
										alert("该网络域下没有VLAN,请重新选择");
									} else {
										for ( var i = 0; i < msg.length; i++) {
											$(
													"<option value="+msg[i].NET_ID+">"
															+ msg[i].NAME
															+ "</option>")
													.appendTo("#netid");
										}
									}
								}
							});
				});

		$("#netid").change(function() {
			$("#ipaddress").val(null);
		});

		$("#chooseIP").click(
				function() {
					var netid = $("#netid").val();
					if (netid == -1) {
						alert("请先选择一个VLAN");
						return false;
					}
					var oper = $("#oper").val();
					w.$.dialog({
						id : 'addIP',
						title : '选择IP',
						width : '750px',
						height : '470px',
						max : false,
						min : false,
						lock : true,
						content : 'url:workorder_selectIp.do?net_id=' + netid
								+ '&oper=' + oper
					});
				});

		$("#choosePRO").click(function() {
			var openerId = $("#openerId").val();
			w.$.dialog({
				id : 'addpro',
				title : '选择项目',
				width : '900px',
				height : '470px',
				max : false,
				min : false,
				lock : true,
				content : 'url:workorder_selectProject.do?operFrom=' + openerId
			});
		});

		$("#chooseBusi").click(
				function() {

					var openerId = $("#openerId").val();
					w.$.dialog({
						id : 'addbusi',
						title : '选择业务系统',
						width : '900px',
						height : '470px',
						max : false,
						min : false,
						lock : true,
						content : 'url:workorder_selectBusiSystem.do?operFrom='
								+ openerId
					});
				});

		$("#chooseSubBusi")
				.click(
						function() {
							var resParentBusiSystemiId = $(
									"#resParentBusiSystemiId").val();
							if (resParentBusiSystemiId.length == 0) {
								alert("请先选择一个业务系统");
								return false;
							}
							var openerId = $("#openerId").val();

							w.$
									.dialog({
										id : 'addSubBusi',
										title : '选择业务子系统',
										width : '900px',
										height : '470px',
										max : false,
										min : false,
										lock : true,
										content : 'url:workorder_selectBusiSystem.do?busisystemid='
												+ resParentBusiSystemiId
												+ '&operFrom=' + openerId
									});
						});

	});

	function randomVmName() {

		var vmname = $("#vmname").val();
		if (vmname.length == 0) {
			var temid = $("#temid").val();
			if (temid.length == 0) {
				alert("请选择一个模板");
				return false;
			}
			var cpu = $("#cpu").val();
			if (cpu.length == 0) {
				alert("cpu个数不能为空");
				return false;
			}
			var memsize = $("#memsize").val();
			if (memsize.length == 0) {
				alert("内存大小不能为空");
				return false;
			}
			var srsize = $("#srsize").val();
			if (srsize.length == 0) {
				alert("存储大小不能为空");
				return false;
			}
			var netid = $("#netid").val();
			if (netid == -1) {
				alert("请选择一个VLAN");
				return false;
			}
			var selectip = $("#selectip").val();
			var ipaddress = "";
			if (selectip == 1) {
				ipaddress = $("#ipaddress").val();
				if (ipaddress.length == 0) {
					alert("请指定一个ip地址");
					return false;
				}
			}

			////////
			var projectid = $("#projectname").val();
			if (projectid.length == 0) {
				alert("请选择一个项目");
				return false;
			}
			var resBusiSytemId = $("#resBusiSytemId").val();
			if (resBusiSytemId.length == 0) {
				alert("请选择业务系统");
				return false;
			}

			var resAppDir = $("#resAppDir").val();
			if (resAppDir.length == 0) {
				alert("请录入业务资源目录");
				return false;
			}
			var resAppSize = $("#resAppSize").val();
			if (resAppSize.length == 0) {
				alert("请输入业务资源目录空间大小");
				return false;
			}
			///////

			var busisystemid = $("#resBusiSytemId").val();
			var domainid = $("#domainid").val();
			var uuid = $("#uuid").val();
			mask('正在生成资源名称，请稍后....', '0.7', '0px');
			$
					.ajax({
						type : 'post',
						url : 'workorder_randomVmName.do?temid=' + temid
								+ '&busisystemid=' + busisystemid
								+ '&ipaddress=' + ipaddress + '&domainid='
								+ domainid + '&uuid=' + uuid,
						success : function(msg) {
							removeMask();
							$("#vmname").val(msg);
						}
					})
		}
	}

	function selectTem(eq_type, cpu_cl, mem, store, maxCounts) {
		$("#eq_type").val(eq_type);
		$("#eq_type_id").val(eq_type);
		$("#cpu").val(cpu_cl);
		$("#memsize").val(mem / 1024);
		$("#srsize").val(store / 1024);
		$("#maxCounts").val(maxCounts);
	}

	function choosePool(id, name) {
		$("#busi_pool_id").val(id);
		$("#busi_pool_name").val(name);
	}

	function selectip(ipaddress) {
		$("#ipaddress").val(ipaddress);
	}

	function showIP() {
		var selectip = $("#selectip").val();
		if (selectip == 0) {
			document.getElementById("ipshow").style.display = "none";
			$("#ipaddress").val("");
		} else if (selectip == 1) {
			document.getElementById("ipshow").style.display = "";
		}
	}

	function referHost() {
		var selecthost = $("#referhost").val();
		if (selecthost == 0) {
			document.getElementById("clushow").style.display = "none";
			document.getElementById("hostshow").style.display = "none";
			$("#host_id").val(null);
			$("#host_name").val(null);
			$("#connectid").val(null);
			$("#clusterid").val(null);
			$("#clustername").val(null);
		} else if (selecthost == 1) {
			document.getElementById("clushow").style.display = "";
			document.getElementById("hostshow").style.display = "";
		}
	}

	function selectBusi(busiid, businame, busisystemid) {
		if (busisystemid.length == 0) {//业务系统
			$("#resParentBusiSystemiId").val(busiid);
			$("#resParentBusiSystemName").val(businame);
			$("#resBusiSytemId").val("");
			$("#resBusiSystemName").val("");
		} else {//子系统
			$("#resBusiSytemId").val(busiid);
			$("#resBusiSystemName").val(businame);
		}
	}

	function chooseCluster(cluname, connectid, cluid) {
		$("#clustername").val(cluname);
		$("#connectid").val(connectid);
		$("#clusterid").val(cluid);
	}

	function chooseHost(host_id, host_name, connectid) {
		$("#host_id").val(host_id + "_" + connectid);
		$("#host_name").val(host_name);
	}

	function showBusi() {
		var typer = $("#typer").val();
		if (typer == 2) {
			document.getElementById("showBusi").style.display = "none";
			document.getElementById("showSubBusi").style.display = "none";
		} else if (typer == 0) {
			document.getElementById("showBusi").style.display = "";
			document.getElementById("showSubBusi").style.display = "";
		}
	}
	function selectPro(proid, proleader, projectname, projectusername) {
		$("#projectid").val(proid);
		$("#projectuserid").val(proleader);
		$("#projectname").val(projectname);
		$("#projectusername").val(projectusername);
	}
	$(function() {
		var domainid = $("#domainid").val();
		var vlainid = $("#vlainid").val();
		if (domainid.length > 0 && domainid != -1) {
			$.ajax({
				type : 'post',
				dataType : 'json',
				url : 'workorder_selectVlan.do?domainid=' + domainid,
				async : false,
				success : function(msg) {
					for ( var i = 0; i < msg.length; i++) {
						if (msg[i].NET_ID == vlainid) {
							$(
									"<option value="+msg[i].NET_ID+" selected='selected'>"
											+ msg[i].NAME + "</option>")
									.appendTo("#netid");
						} else {
							$(
									"<option value="+msg[i].NET_ID+">"
											+ msg[i].NAME + "</option>")
									.appendTo("#netid");
						}
					}
				}
			});
		}
	});

	$(function() {
		var selectip = $("#selectip").val();
		if (selectip == 0) {
			document.getElementById("ipshow").style.display = "none";
		} else if (selectip == 1) {
			document.getElementById("ipshow").style.display = "";
		}

		var selecthost = $("#referhost").val();
		if (selecthost == 0) {
			document.getElementById("clushow").style.display = "none";
			document.getElementById("hostshow").style.display = "none";
		} else if (selecthost == 1) {
			document.getElementById("clushow").style.display = "";
			document.getElementById("hostshow").style.display = "";
		}
	});
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="workOrderObj.ID" id="wrid"></s:hidden>
		<s:hidden name="type" id="type"></s:hidden>
		<s:hidden name="uuid" id="uuid"></s:hidden>
		<s:hidden name="vlainid" id="vlainid"></s:hidden>
		<s:hidden name="workOrderObj.TEMPLATE_ID" id="temid"></s:hidden>
		<s:hidden name="operFrom" id="openerId"></s:hidden>
		<s:hidden name="resource_type" id="resource_type"></s:hidden>

		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<%--<tr>
					<td>资源名称<font color="red">*</font></td>
					<td>
						<s:textfield name="workOrderObj.VM_NAME" id="vmname"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>主机名称<font color="red">*</font></td>
					<td>
						<s:textfield name="workOrderObj.HOST_NAME" id="hostname"></s:textfield>
					</td>
				</tr>
				
				<tr>
					<td>选择可分配资源类型<font color="red">*</font>
					</td>
					<td><input type="button"
						class="thickbox btn-style02" value="选择" id="chooseTem" /></td>
				</tr>--%>
				<!-- <tr>
					<td>模板类型</td>
					<td>
						<s:textfield name="workOrderObj.TEMPLATE_TYPE" id="temtype" readonly="true"></s:textfield>
					</td>
				</tr> -->
				<tr>
					<td>选择可分配资源类型及配置<font color="red">*</font>
					</td>
					<td><s:select
							list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器','6':'HP PC服务器'}"
							name="workOrderObj.EQ_TYPE" id="eq_type" disabled="true"></s:select>
						<s:hidden name="workOrderObj.EQ_TYPE_ID" id="eq_type_id"></s:hidden>
						<input type="button" class="thickbox btn-style02" value="选择"
						id="chooseTem" />
					</td>
				</tr>
				<tr>
					<td>CPU个数<font color="red">*</font>
					</td>
					<td><s:textfield name="workOrderObj.CPU_NUM" id="cpu"
							readonly="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>内存大小<font color="red">*</font>
					</td>
					<td><s:textfield name="workOrderObj.MEM_SIZE" id="memsize"
							readonly="true"></s:textfield>G</td>
				</tr>
				<tr>
					<td>存储大小<font color="red">*</font>
					</td>
					<td><s:textfield name="workOrderObj.SR_SIZE" id="srsize"
							readonly="true"></s:textfield>G</td>
				</tr>
				<tr>
					<td>剩余可申请数量<font color="red">*</font>
					</td>
					<td><s:textfield name="workOrderObj.maxCounts" id="maxCounts"
							readonly="true"></s:textfield>台</td>
				</tr>
				<tr>
					<td>申请数量<font color="red">*</font>
					</td>
					<td><s:textfield name="workOrderObj.RESOURCE_NUM" id="re_num"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>选择所属资源池：<font color="red">*</font></td>
					<td><s:hidden name="workOrderObj.busi_pool_id"
							id="busi_pool_id"></s:hidden> <s:textfield
							name="workOrderObj.busi_pool_name" readonly="true"
							id="busi_pool_name"></s:textfield> <input type="button"
						class="thickbox btn-style02" value="选择" id="chooseResourcePool" />
					</td>
				</tr>
				<!-- 
				<tr>
					<td>
						是否指定主机
					</td>
					<td>
						<s:select list="#{'0':'否','1':'是'}" id="referhost" onchange="referHost()" name="workOrderObj.ISREFERHOST"></s:select>
					</td>
				</tr>
				<tr id="clushow">
					<td>集群</td>
					<td>
						<s:hidden name="workOrderObj.CLUSTERID" id="clusterid"></s:hidden>
						<s:hidden name="workOrderObj.CONNECT_ID" id="connectid"></s:hidden>
						<s:textfield readonly="true" name="workOrderObj.CLUSTERNAME" id="clustername"></s:textfield>
						<input type="button" class="thickbox btn-style02" value="选择" id="selectClu"/>
					</td>
				</tr>
				<tr id="hostshow">
					<td>主机</td>
					<td>
						<s:hidden name="workOrderObj.HOST_ID" id="host_id"></s:hidden>
						<s:textfield readonly="true" name="workOrderObj.HOST_NAME" id="host_name"></s:textfield>
						<input type="button" class="thickbox btn-style02" value="选择" id="selectHost"/>
					</td>
				</tr> 
				<tr>
					<td>网络子域<font color="red">*</font>
					</td>
					<td><s:select list="domainList" listKey="id" listValue="name"
							id="domainid" name="domainid" headerKey="-1" headerValue="请选择"></s:select>
					</td>
				</tr>
				<tr>
					<td>VLAN<font color="red">*</font>
					</td>
					<td><select id="netid" name="workOrderObj.NETWORK_ID">
							<option value="-1">请选择</option>
					</select></td>
				</tr>-->
				<!--  
				<tr>
					<td>是否指定IP</td>
					<td>
						<s:select list="#{'1':'是','0':'否' }" id="selectip" onchange="showIP()" name="flag"></s:select>
					</td>
				</tr>
				<tr id="ipshow">
					<td>IP地址<font color="red">*</font></td>
					<td>
						<s:textfield name="workOrderObj.IPADDRESS" id="ipaddress" readonly="true"></s:textfield>
						<input type="button" class="thickbox btn-style02" value="选择" id="chooseIP"/>
					</td>
				</tr>-->
				<!-- ----------------------------------------- -->
				<tr>
					<td>选择项目：<font color="red">*</font></td>
					<td><s:hidden name="workOrderObj.PROJECT_ID" id="projectid"></s:hidden>
						<s:textfield name="workOrderObj.PROJECT_NAME" readonly="true"
							id="projectname"></s:textfield> <input type="button"
						class="thickbox btn-style02" value="选择" id="choosePRO" /></td>
				</tr>
				<tr>
					<td>项目责任人:</td>
					<td><s:hidden name="workOrderObj.PROJECT_USER_ID"
							id="projectuserid"></s:hidden> <s:textfield
							name="workOrderObj.PROJECT_USER_NAME" readonly="true"
							id="projectusername"></s:textfield></td>
				</tr>

				<tr id="showBusi">
					<td>业务系统：<font color="red">*</font>
					</td>
					<td><s:hidden name="workOrderObj.resParentBusiSystemiId"
							id="resParentBusiSystemiId"></s:hidden> <s:textfield
							name="workOrderObj.resParentBusiSystemName"
							id="resParentBusiSystemName" disabled="true"></s:textfield> <input
						type="button" class="thickbox btn-style02" value="选择"
						id="chooseBusi" />
					</td>
				</tr>
				<tr id="showSubBusi">
					<td>子业务系统：<font color="red">*</font>
					</td>
					<td><s:hidden name="workOrderObj.resBusiSytemId"
							id="resBusiSytemId"></s:hidden> <s:textfield
							name="workOrderObj.resBusiSystemName" id="resBusiSystemName"
							disabled="true"></s:textfield> <input type="button"
						class="thickbox btn-style02" value="选择" id="chooseSubBusi" />
					</td>
				</tr>
				<!--  
				<tr>
					<td>资源目录：<font color="red">*</font></td>
					<td><s:textfield name="workOrderObj.resAppDir" id="resAppDir"></s:textfield>
					</td>
				</tr>
				<tr>
					<td>资源目录大小(G)：<font color="red">*</font>
					</td>
					<td><s:textfield name="workOrderObj.resAppSize"
							id="resAppSize"></s:textfield></td>
				</tr>
				<tr>
					<td>主机名称：<font color="red">*</font></td>
					<td>
						<s:textfield name="workOrderObj.VM_NAME" id="vmname"></s:textfield>
						<br /> <font color="red">主机名称须符合规范性要求，请慎重命名</font></td>
				</tr>-->
			</table>
		</div>
	</s:form>
</body>