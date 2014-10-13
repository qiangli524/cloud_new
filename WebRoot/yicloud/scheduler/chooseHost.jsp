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
	var sig = 0; //主机id
	var eName = '';//主机name
	var checkboxids = document.getElementsByName("checkboxid");
	/* var connectId = ''; */
	var sId = '<s:property value="hostId"/>';//主机id
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
				eName = checkboxids[i].getAttribute("hostName");
				/* connectId = checkboxids[i].getAttribute("connectId"); */
			}
		}
		if (eId == '') {
			alert("请选择主机");
			return false;
		}
		if (flag != '' && flag != null) {
			if(flag == 1){
				sig = 1;
			}
		}
		if (sig == '1') {
			api.get("mod").showHost(eId, eName);
		} else {
			api.get("add").showHost(eId, eName);
		}
	}
</script>
</head>
<body class="scrollbody">
	<s:form action="" method="post" id="theForm">
	<s:hidden name="entityId" id="entity_id"/>
	<s:hidden name="type" id="type"/>
	<s:hidden name="flag" id="flag"/>
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
										主机名
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
											<input name="checkboxid" type="checkbox" hostName="<s:property value='#theBean.name'/>"  value="<s:property value='#theBean.uuid'/>" connectId="<s:property value='#theBean.connect_id'/>"/>
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
