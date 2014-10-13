<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
			hostForm.sn.value = "";
			//hostForm.eq_name.value = "";
			hostForm.eq_ip.value = "";
			hostForm.allocated.value = "";
			//hostForm.hasvertual.value = "-1";
			hostForm.type.value="-1";
			hostForm.STATUS.value="";
		}
	
		function searchRequest(){
		    hostForm.action = "bmanager_addHostToBusi.do";
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
			w.saveHostToBusi(param,parent_id);
		}
	</script>
</head>
<body onLoad="self.focus()" class="mainbody">
	<s:form action="bmanager_addHostToBusi.do" method="post" cssClass="hostForm" id="hostForm">
	<s:hidden name="parent_id"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">主机列表</h2>
					<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="fl">编号:</label>
							<s:textfield name="hostForm.eq_id" cssClass="inpt-1 fl" id="eq_id"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="fl">SN</label>
							<s:textfield name="hostForm.sn" cssClass="inpt-1 fl" id="sn"></s:textfield>
						</div>
						<%-- 
						<div class="filtrate-field">
							<label class="fl">主机名称:</label>
							<s:textfield name="hostForm.eq_name" cssClass="inpt-1 fl" id="eq_name"></s:textfield>
						</div>--%>
						<div class="filtrate-field">
							<label class="fl">主机IP地址:</label>
							<s:textfield name="hostForm.eq_ip" cssClass="inpt-1 fl" id="eq_ip"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label>是否已分配:</label>
							<s:select list="#{'':'--请选择--','0':'未分配','1':'已分配'}" name="hostForm.allocated" id="allocated" />
						</div>
						<div class="filtrate-field">	
							<label>主机类型:</label>
							<s:select list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}" name="hostForm.eq_type" id="type"></s:select>
						</div>
						<div class="filtrate-field">
							<label >主机状态:</label>
							<s:select list="#{'':'--请选择--','0':'未采集到','1':'正常启动','2':'关闭','3':'异常'}" name="hostForm.STATUS" id="STATUS"></s:select>
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
					</div>
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th onclick="sort(theTable,9,'string')">编号</th>
									<th onclick="sort(theTable,11,'string')">SN</th>
									<%--<th onclick="sort(theTable,12,'string')">物理位置</th>--%>
									<th onclick="sort(theTable,1,'string')">名称</th>
									<th onclick="sort(theTable,2,'string')">类型</th>
									<th onclick="sort(theTable,3,'string')">IP地址</th>									
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
										<td><s:property value="#theBean.eq_id" /></td>
										<td><s:property value="#theBean.sn" /></td>
										<%--TB_CLOUD2_HOST_INFO表中没有记录物理信息，可以通过TB_HOST_INFO关联到。
											是否可以考虑在TB_CLOUD2_HOST_INFO表中存储资产信息 
										<td><s:property value="#theBean.eq_hostname" /></td>--%>
										<td>
											<s:property value="#theBean.eq_hostname" />
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
											<s:else>
												其它
											</s:else>
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
