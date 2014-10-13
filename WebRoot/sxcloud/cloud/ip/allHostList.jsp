<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
	<script type="text/javascript">
		var api = frameElement.api; 
		var w = api.opener;
 		$(function(){
 			$("a[name='vm_motion']").click(function(){
 				var eq_id=$(this).parent().parent().children("td[name='id']").attr("eqId");
 				w.$.dialog({
 					id:eq_id,
 					title:'性能监控',
 					width: '1000px',
 					height: '500px',
 					lock:true,
 					content: 'url:charts_tabHostMonitorData.do?eqid='+eq_id
 	 				});
 			});
 			$("a[name='vm_store']").click(function(){
 				var eq_id=$(this).parent().parent().children("td[name='id']").attr("eqId");
 				w.$.dialog({
 					id:'store',
 					title:'存储详情',
 					width: '1000px',
 					height: '500px',
 					lock:true,
 					content: 'url:datastore_liststorage.do?eqid='+eq_id
 	 				});
 			});
 			$("a[name='vm_detail']").click(function(){
 				var eq_id=$(this).parent().parent().children("td[name='id']").attr("eqId");
 				w.$.dialog({
 					id:'VM',
 					title:'虚拟机详情',
 					width: '1000px',
 					height: '500px',
 					lock:true,
 					content: 'url:showvm_listvm.do?eqid='+eq_id
 	 				});
 			});
 		});
	</script>
</head>
<body>
	<s:form action="resource_allHostList.do" method="post" cssClass="hostForm" id="hostForm">
		<s:hidden id="hostUuids" name="hostForm.hostUuids"></s:hidden>
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										主机名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										主机类型
									</th>
									<th onclick="sort(theTable,3,'string')">主机IP地址</th>
									<th onclick="sort(theTable,4,'string')">虚拟机</th>
									<th onclick="sort(theTable,5,'string')">CPU</th>
									<th onclick="sort(theTable,6,'string')">存储</th>
									<th onclick="sort(theTable,7,'string')">网卡</th>
									<th onclick="sort(theTable,8,'string')">内存</th>
									<th onclick="sort(theTable,9,'string')">虚拟化类型</th>
									<th>性能</th>
									<th onclick="sort(theTable,10,'string')">状态</th>
									<th onclick="sort(theTable,11,'date')">时间</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="hostForm.resultList" id="theBean">
									<tr>
										<td eqId='<s:property value="#theBean.h_uuid" />' name='id'>
											<input name="checkboxid" type="checkbox"
												value="<s:property value="#theBean.eq_id"/>" />
										</td>
										<td>
											<s:property value="#theBean.eq_name" />
										</td>
										<td>
											<s:if test="#theBean.eq_type == 1">
												IBM小型机
											</s:if>
											<s:elseif test="#theBean.eq_type == 2">
												刀片
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
										</td>
										<td>
										<s:if test="#theBean.vm_num!=0">
											<a href="javascript:void(0)" name="vm_detail"><s:property value = "#theBean.vm_num"/> 个</a>
										</s:if>
										<s:else>
											<s:property value = "#theBean.vm_num"/> 个
										</s:else>
										</td>
										<td>
											<s:property value="#theBean.cpu_cl"/> 核
										</td>
										<td>
											<s:if test="#theBean.storage_num!=0">
												<a href="javascript:void(0);" name="vm_store"><s:property value="#theBean.storage_num"/>块</a>
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
												<s:property value="@java.lang.Math@round(#theBean.mem/1024/1024*100) / 100.0"/>
												(MB)
											</s:if>
										</td>
										<td>
											<s:if test="#theBean.hasvertual == 3">
												XEN
											</s:if>
											<s:elseif test="#theBean.hasvertual==4">
												VMWARE	
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==1">
												PowerVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==0">
												无
											</s:elseif>
										</td>
										<td>
											<a href='javascript:;' name='vm_motion'>性能</a>
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
										<td>
											<s:property value="#theBean.ins_date"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
