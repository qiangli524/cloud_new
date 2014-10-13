<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>

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
	var trigger_ids = '';
	var types = '';
	var type = '';
	var sig = 0;
	var checkboxids = document.getElementsByName("checkboxid");
	var flag = '<s:property value="flag"/>';
	function add() {
		for ( var i = 0; i < checkboxids.length; i++) {
			if (checkboxids[i].checked) {
				couterNum = couterNum + 1;
				type = checkboxids[i].getAttribute("type1");
				if(types.indexOf(type)>=0){
					alert("不能选择同一类型的两种策略");
					types = '';
					return false;
				}
				types += type;
				trigger_ids += checkboxids[i].value + ",";
			}
		}
		//alert(trigger_ids);
		//trigger_ids +=$("[name='checkboxid']:checked").attr("trigger_id")+',';
		// alert(trigger_ids);
		//theForm.action="strategy_choosedTrigger.do?trigger_ids="+trigger_ids;
		//theForm.submit();
		//theForm.trigger_ids.value = trigger_ids;
		if (trigger_ids == '') {
			alert("请选择策略条件!");
			return false;
		}
		if (flag != '' && flag != null) {
			if(flag == 1){ //表示更新
				sig = 1;
			}
		}
		if (sig == '1') {
			api.get("mod").getTrigger(trigger_ids, sig);
		} else {
			api.get("add").getTrigger(trigger_ids, sig);
		}
	}
</script>
</head>
<body onunload="">
	<s:form action="strategy_listStrategy.do" method="post" id="theForm">
		<s:hidden name="strategyId" id="strategyId"></s:hidden>
		<s:hidden name="flag" id="flag"/>
		<input type="hidden" value="<s:property  value='strategyId'/>"/>
		<div class="scrollbody">
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
										类型
									</th>
									<th>
										kpi
									</th>
									<th>
										触发条件
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="#request.resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" trigger_id="<s:property value="#theBean.trigger_id"/>"  value="<s:property value="#theBean.trigger_id"/>" type1="<s:property value="#theBean.type"/>"/>
										</td>
										<td>
											<s:if test="#theBean.type==0">
			  									CPU
			  								</s:if>
											<s:elseif test="#theBean.type==1">
			  									内存
			  								</s:elseif>
											<s:elseif test="#theBean.type==2">
			  									存储
			  								</s:elseif>
											<s:elseif test="#theBean.type==3">
			  									网络
			  								</s:elseif>
											<s:elseif test="#theBean.type==4">
			  									其他
			  								</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.kpi_name" />
										</td>
										<td>
											<s:property value="#theBean.content" />
										</td>
									</tr>
								</s:iterator>
							</tbody>
							<!-- 
							<tr>
								<td colspan="4" class="btnCenter">
									<input type="button" class="thickbox btn-style02" value="确定"
										onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
									<input type="button" class="thickbox btn-style02" value="返回"
										onclick="window.close();" />
								</td>
							</tr>
						 -->
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
