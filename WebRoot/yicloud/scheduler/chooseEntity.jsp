<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>

<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
    var w = api.opener;
	 api.button({
	     id:'Ok',
	     callback:add,
	     name: '确定',
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });
	var couterNum = 0;
	var eId = '';
	var sig = 0;
	var eName = '';
	var checkboxids = document.getElementsByName("checkboxid");
	var connectId = '';
	var sId = '<s:property value="entityId"/>';
	var flag = '<s:property value="flag"/>';
	/****让其出来就可以勾选********/
	$(function(){
		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    	});
		for ( var i = 0; i < checkboxids.length; i++) {
			if (checkboxids[i].value == sId) {
				checkboxids[i].checked = true;
			}
		}
	});
	
	function add() {
		for ( var i = 0; i < checkboxids.length; i++) {
			if (checkboxids[i].checked) {
				couterNum = couterNum + 1;
				eId = checkboxids[i].value;
				eName = checkboxids[i].getAttribute("trigger_name");
				connectId = checkboxids[i].getAttribute("connectId");
			}
		}
		if (eId == '') {
			alert("请选择实体对象");
			return false;
		}
		if (flag != '' && flag != null) {
			if(flag == 1){
				sig = 1;
			}
		}
		if (sig == '1') {
			api.get("mod").showEitity(eId, eName ,connectId);
		} else {
			api.get("add").showEitity(eId, eName ,connectId);
		}
	}
</script>
</head>
<body class="scrollbody">
	<s:form action="strategy_queryAllEntity.do" method="post" id="theForm">
	<s:hidden name="entityId" id="entity_id"/>
	<s:hidden name="type" id="type"/>
	<s:hidden name="flag" id="flag"/>
	<div class="query" id="query">
	<div class="title" id="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form" id="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">实体名称：</td>
			<td>
				<s:textfield name="theForm.entity_name"></s:textfield>
			</td>
			<td colspan="8" class="btns">
			<div><input type="submit" class="thickbox btn-style02"
				value="查询" />
				 <input type="reset" class="btn-style02" value="重置"/>
			</div>
			</td>
		</tr>
	</table>
	</div>
			<div class="box on">
				<div class="blue-wrap noborder">
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										实体对象
									</th>
									<th>
										虚拟化类型
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" trigger_name="<s:property value='#theBean.name'/>"  value="<s:property value='#theBean.uuid'/>" connectId="<s:property value='#theBean.connect_id'/>"/>
										</td>
										<td>
											<s:property value="#theBean.name" />
										</td>
										<td>
											<s:if test="#theBean.vtype==1">
												VMWARE
											</s:if>
											<s:elseif test="#theBean.vtype==2">
			  									XEN
			  								</s:elseif>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
	</s:form>
</body>
