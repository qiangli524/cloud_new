<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>

<head>
<script type="text/javascript">

	var api = frameElement.api;
	var w = api.opener;
	
	api.button({
		id : 'Ok',
		name : '引用',
		callback : saveHost,
		focus : true
	}, {
		id : 'cancle',
		name : '取消'
	});
	
	function resetForm(){
		hostForm.eq_id.value = "";
		hostForm.eq_ip.value = "";
		//hostForm.allocated.value = "";
		hostForm.type.value="-1";
		hostForm.STATUS.value="";
	}
	
	function searchRequest(){
	    hostForm.action = "united_unAllocatedHostSC.do";
		hostForm.submit();
	} 
	
	function saveHost() {
		var eq_id = '';		
		var parent_id = '<s:property value="parent_id"/>';		
		var param = '';
		var length = 0;
		$('[name=checkboxid]:checkbox:checked').each(function() {
			eq_id = $(this).attr("eqid");
			param = param + ',' + eq_id;
			length = length + 1;
		});
		
		if (length == 0) {
			alert("请先选择物理主机再添加!");
			return false;
		}
		
		w.saveHostNotVir(param,parent_id);
	}
		
</script>
</head>
<body onLoad="self.focus();document.hostForm.eq_name.focus()" class="mainbody">
	<s:form action="united_unAllocatedHostSC.do" method="post" cssClass="hostForm" id="hostForm">
		<s:hidden name="vtype" id="vtype"></s:hidden>
		<s:hidden name="connect_id" id="connect_id"></s:hidden>
		<s:hidden name="parent_id" id="parent_id"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">主机列表</h2>
					<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="fl">主机编号</label>
							<s:textfield name="hostForm.eq_id" cssClass="inpt-1 fl" id="eq_id"></s:textfield>
						 </div>
						<div class="filtrate-field">
							<label class="fl">主机IP地址:</label>
							<s:textfield name="hostForm.eq_ip" cssClass="inpt-1 fl" id="eq_ip"></s:textfield>
						</div>
						<div class="filtrate-field">	
							<label class="fl">主机类型:</label>
							<s:select cssClass="select-1 vm" list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}" name="hostForm.eq_type" id="type"></s:select>
						</div>
						<div class="filtrate-field">
							<label class="fl">主机状态:</label>
							<s:select cssClass="select-1 vm"  list="#{'':'--请选择--','0':'未采集到','1':'正常启动','2':'关闭','3':'异常'}" name="hostForm.STATUS" id="STATUS"></s:select>
						</div>
						<div class="filtrate-field">
							<span class="ubtn-1 mgl-20"><input type="button" value="查询"
										onclick="javascript:searchRequest()" />
							</span>
							<span class="ubtn-2 mgl-20"><input type="button" value="重置"
										onclick="javascript:resetForm(document.getElementById('hostForm'))" />
							</span>
						</div>
					</div>
					
					<div  class="utt-2 mgt-20">
					<%-- 
							<a class="icon-add" href="javascript:void(0)"  name="addhost" />增加</a>
							<a class="icon-modify" href="javascript:void(0)" name="modhost" />修改</a>
							<a class="icon-del" href="javascript:void(0)" name = "del" />删除</a>
							<a class="icon-set" href="javascript:void(0)"  name = "confighost" />配置</a>
							<a class="icon-check" href="javascript:void(0)"  id="checkHostStatus" />状态检测</a>
							<a class="icon-export" href="javascript:void(0)" onclick = "listExp();" />导出</a>--%>
					</div>
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,9,'string')">
										主机编号
									</th>
									<th onclick="sort(theTable,1,'string')">
										主机型号
									</th>
									<th onclick="sort(theTable,2,'string')">
										设备类型
									</th>
									<th onclick="sort(theTable,3,'string')">主机IP地址</th>
									<th onclick="sort(theTable,4,'string')">CPU</th>
									<th onclick="sort(theTable,5,'string')">存储</th>
									<th onclick="sort(theTable,6,'string')">网卡</th>
									<th onclick="sort(theTable,7,'string')">内存</th>
									<th onclick="sort(theTable,8,'string')">是否已分配</th>
									<th onclick="sort(theTable,10,'string')">状态</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="hostForm.resultList" id="theBean">
									<tr>
										<td eqId='<s:property value="#theBean.h_uuid" />' connID='<s:property value="#theBean.connectId" />' hasvertual='<s:property value="#theBean.hasvertual" />' name='id'>
											<input name="checkboxid" type="checkbox" eqid='<s:property value="#theBean.eq_id" />' 
												value="<s:property value="#theBean.eq_id"/>" />
										</td>
										<td>
											<s:property value="#theBean.eq_id" />
										</td>
										<td>
											<s:property value="#theBean.eq_name" />
										</td>
										<td>
											<s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if>
											<s:elseif test="#theBean.eq_type == 2">
												IBM刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 3">
												IBM普通刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 4">
												HPx86刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 5">
												机架服务器
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
										</td>
										<td>
											<s:property value="#theBean.cpu_cl"/> 核
										</td>
										<td eid='<s:property value="#theBean.eq_id" />'>
											<s:if test="#theBean.storage_num!=0">
												<a href='javascript:;' name='storage_list'><s:property value="#theBean.storage_num"/>块</a>
											</s:if>
											<s:else>
												<s:property value="#theBean.storage_num"/>块
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.NIC_NUM"/>个
										</td>
										<td width="50px;">
											<s:if test='#theBean.mem != null'>
												<s:property value="@java.lang.Math@round(#theBean.mem/1024*100) / 100.0"/>
												G
											</s:if>
										</td>
										<td>
											<s:if test="#theBean.allocated == 0">
												未分配
											</s:if>
											<s:elseif test="#theBean.allocated==1">
												已分配	
											</s:elseif>
										</td>
										<td>
											<s:if test="#theBean.STATUS == 0">
												未采集到
											</s:if>
											<s:elseif test="#theBean.STATUS == 1">
												正常启动
											</s:elseif>
											<s:elseif test="#theBean.STATUS == 2">
												关闭
											</s:elseif>
											<s:else>
												异常
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=hostForm" />
						</div>
				</div>
			</div>
	</s:form>
</div>
</body>
