<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function() {
		api.button({
			id : 'OkAnd',
			name : '确定',
			callback : createWorkOrder,
			focus : true
		}, {
			id : 'cancle',
			name : '取消'
		});
	});

	function createWorkOrder() {
		/**
		var projectid = $("#projectname").val();
		if (projectid.length == 0) {
			alert("请选择一个项目");
			return false;
		}
		 **/
		var title = $("#title").attr("value");
		var content = $("#title").attr("value");
		if (title == null || title == "") {
			alert("请填写工单标题");
			return false;
		}

		var typer = $("#typer").val();
		if (typer == 0) {
			/**
			var busisystemname = $("#busisystemname").val();
			if (busisystemname.length == 0) {
				alert("请指定一个业务系统");
				return false;
			}
			var subbusisystemname = $("#subbusisystemname").val();
			if (subbusisystemname.length == 0) {
				alert("请指定一个业务子系统");
				return false;
			}
			 **/
		}
		w.saveWorkOrder($("#workOrderObj").serialize());
	}

	$(function() {
		/**
		$("#choosePRO").click(function(){
			w.$.dialog({
				id:'addpro',
				title:'选择项目',
				width: '900px',
				height: '470px',
			    lock:true,
				content: 'url:workorder/workorder_selectProject.do'
			});
		});
		
		$("#chooseBusi").click(function(){
			w.$.dialog({
				id:'addbusi',
				title:'选择业务系统',
				width: '900px',
				height: '470px',
			    lock:true,
				content: 'url:workorder/workorder_selectBusiSystem.do?operFrom=addWorkOrder'
			});
		});
		
		$("#chooseSubBusi").click(function(){
			var busisystemid = $("#busisystemid").val();
			if (busisystemid.length == 0) {
				alert("请先选择一个业务系统");
				return false;
			}
			w.$.dialog({
				id:'addSubBusi',
				title:'选择业务子系统',
				width:'900px',
				height:'470px',
				lock:true,
				content:'url:workorder/workorder_selectBusiSystem.do?busisystemid='+busisystemid+'&operFrom=addWorkOrder'
			});
		});
		 **/
	});

	function selectPro(proid, proleader, projectname, projectusername) {
		$("#projectid").val(proid);
		$("#projectuserid").val(proleader);
		$("#projectname").val(projectname);
		$("#projectusername").val(projectusername);
		$("#showname").val(projectusername);
		$("#username").val(proleader);
	}

	function selectBusi(busiid, businame, busisystemid) {
		if (busisystemid.length == 0) {//业务系统
			$("#busisystemid").val(busiid);
			$("#busisystemname").val(businame);
		} else {//子系统
			$("#subbusisystemid").val(busiid);
			$("#subbusisystemname").val(businame);
		}
	}

	function showBusi() {
		/**
		var typer = $("#typer").val();
		if (typer == 2) {
			document.getElementById("showBusi").style.display="none";
			document.getElementById("showSubBusi").style.display="none";
		} else if(typer == 0){
			document.getElementById("showBusi").style.display="";
			document.getElementById("showSubBusi").style.display="";
		}
		 **/
	}
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="workorder_saveWorkOrder" method="post"
		id="workOrderObj" cssStyle="theForm" name="workOrderObj">
		<s:hidden name="oper"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" align="left">工单类型：</td>
					<td><s:select list="#{'0':'申请','2':'回收' }" name="type"
							id="typer" onchange="showBusi()"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">资源类型：</td>
					<td>
						<!--<s:select list="#{'1':'物理资源','2':'虚拟资源' }" name="resource_type" id="resource_type" onchange="showBusi()"></s:select>  -->
						<s:select list="#{'1':'物理资源'}" name="resource_type"
							id="resource_type" onchange="showBusi()"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">工单标题： <font color="red" size="1">*</font>
					</td>
					<td><s:textfield name="workOrderObj.WORK_ORDER_TITLE"
							id="title" size="40"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">备注：</td>
					<td><s:textarea cols="32" rows="5"
							name="workOrderObj.WORK_ORDER_COMM_MSG" id="content">
						</s:textarea>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>