<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %> 
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

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<script type="text/javascript">
		var api = frameElement.api;
	    var w = api.opener;
	
	    api.button({
			id:'OkAnd',
			name: '确定',
			callback:commitScript,
			focus: true
		},
		{
	    	id:'cancle',
			name: '取消'
		}); 
		function resetForm(){
			theForm.eq_name.value = "";
			theForm.eq_ip.value = "";
			theForm.allocated.value = "";
			theForm.hasvertual.value = "-1";
			theForm.type.value="-1";
			theForm.STATUS.value="";
		}
		function searchRequest(){
			var poolid=theForm.hostpoolid.value; 
		    theForm.action = "convert_queryHostIpAddr.do?hostID=" + poolid;
 		 	theForm.submit();
		} 
		function allhost(poolid,flag){ 
			theForm.action='convert_queryHostIpAddr.do?hostID='+poolid+'&flag='+flag;
			theForm.submit();
		} 
 		 function saveAllhost(url,poolid,flag){
			 $.ajax({
				type:"POST",
	           url:url,
	           async: true,
	           cache: false,
		        success: function(msg){
		          theForm.action='convert_queryHostIpAddr.do?hostID='+poolid+'&flag='+flag;
				  theForm.submit();
		        }
			});
		}
		 function saveAllhost1(url){
			 $.ajax({
			   type:"POST",
	           url:url,
	           async: true,
	           cache: false,
		        success: function(msg){
		          	theForm.action='convert_queryHostIpAddr.do';
					theForm.submit();
		        }
			});
		}
 		 
 			
 			$("a[name='vm_motion']").click(function(){
 				var eq_id=$(this).parent().parent().children("td[name='id']").attr("eqId");
 				var conn_id =$(this).parent().parent().children("td[name='id']").attr("connID");
 				var hasvertual = $(this).parent().parent().children("td[name='id']").attr("hasvertual");
 				if(hasvertual!=0 ){
 					if(conn_id!=null && conn_id!=""){
 						eq_id = conn_id +"_" + eq_id;
 					}else{
 						eq_id = eq_id;//山西主机表中没有存connect_id @yanggl
 					}
 				}
 				var flag=theForm.flag.value;
 				if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
	 				w.$.dialog({
	 					id:'vm_motion',
	 					title:'性能监控',
	 					width: '800px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:hostMonitor_highChartsTabs.do?id='+eq_id
	 	 			});
 	 			} else {
 	 				$.dialog({
	 					id:'vm_motion',
	 					title:'性能监控',
	 					width: '800px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:hostMonitor_highChartsTabs.do?id='+eq_id
	 	 			});
 	 			}
 			});
 			
 			$("a[name='vm_list']").click(function(){
	 			var $td = $(this).parent();
	        	var eq_id = $td.attr("eid");
	        	var flag=theForm.flag.value;
 				if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
	 				w.$.dialog({
	 					id:'vm_list',
	 					title:'虚拟机信息',
	 					width: '1000px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:showvm_listvm.do?eq_id='+eq_id+'&flag=host'
	 	 			});
 	 			} else {
 	 				$.dialog({
	 					id:'vm_list',
	 					title:'虚拟机信息',
	 					width: '1000px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:showvm_listvm.do?eq_id='+eq_id+'&flag=host'
	 	 			});
 	 			}
 			});
 			
 			$("a[name='storage_list']").click(function(){
	 			var $td = $(this).parent();
	        	var eq_id = $td.attr("eid");
	        	var flag=theForm.flag.value;
	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
	 				w.$.dialog({
	 					id:'storage_list',
	 					title:'存储信息',
	 					width: '1000px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:datastore_liststorage.do?eq_id='+eq_id
	 	 			});
	 	 		} else {
	 	 			$.dialog({
	 					id:'storage_list',
	 					title:'存储信息',
	 					width: '1000px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:datastore_liststorage.do?eq_id='+eq_id
	 	 			});
	 	 		}
 			});
 			//点击选择一个主机时，把主机的IP返回赋值到父页面
 	        function commitScript(){
 	        	var eqId='';
 	        	var eqIp = '';
 	        	var vmNum = '';
 	        	var cpu = '';
 	        	var mem = '';
 	        	var storageNum = '';
 	        	var nicNum = '';
 	     		var lenth=0;
 				$('[name=checkboxid]:checkbox:checked').each(function(){
 					eqId +=$(this).val();
 					eqIp +=$(this).attr("eqIp");
 					vmNum +=$(this).attr("vmNum");
 					cpu +=$(this).attr("cpu");
 					mem +=$(this).attr("mem");
 					storageNum +=$(this).attr("storageNum");
 					nicNum +=$(this).attr("nicNum");
 	        		lenth +=1;
 	        	 });
 	        	if(eqId==null || eqId ==''){
 					alert('请先选择一项');
 					return false;
 				}else if(lenth>1){
 					alert('只能选择一项');
 					return false;
 				}
 	        	api.get("convertPage").getHostIpAddr(eqId,eqIp,vmNum,cpu,mem,storageNum,nicNum);
 	       }
	</script>
</head>
<body onLoad="self.focus();document.theForm.eq_name.focus()" class="scrollbody">
	<s:form action="convert_queryHostIpAddr.do" method="post" cssClass="theForm" id="theForm">
		<s:hidden id="hostUuids" name="theForm.hostUuids"></s:hidden>
		<s:hidden id="flag" name="theForm.flag"></s:hidden>
		<s:hidden id="hostpoolid" name="theForm.HOST_POOL_ID"></s:hidden>
		<div>
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								主机名称:
							</td>
							<td>
								<s:textfield name="theForm.eq_name" cssClass="txt" id="eq_name"></s:textfield>
							</td>
							<td class="til">
								主机IP地址:
							</td>
							<td>
								<s:textfield name="theForm.eq_ip" cssClass="txt" id="eq_ip"></s:textfield>
							</td>
							<td class="til">
								是否已分配:
							</td>
							<td>
								<s:select list="#{'':'--请选择--','0':'未分配','1':'已分配'}" name="theForm.allocated" id="allocated" />
							</td>
							<td class="til">
								主机类型:
							</td>
							<td>
								<s:if test="location == 'ah'">
									<s:select list="#{'-1':'--请选择--','4':'HPx86刀片','5':'机架服务器'}" name="theForm.eq_type" id="type"></s:select>
								</s:if>
								<s:else>
									<s:select list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}" name="theForm.eq_type" id="type"></s:select>
								</s:else>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
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
									<th onclick="sort(theTable,9,'string')">是否已分配</th>
									<th onclick="sort(theTable,10,'string')">虚拟化类型</th>
									<th onclick="sort(theTable,12,'string')">状态</th>
									<th onclick="sort(theTable,13,'date')">时间</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td eqId='<s:property value="#theBean.h_uuid" />' connID='<s:property value="#theBean.connectId" />' hasvertual='<s:property value="#theBean.hasvertual" />' name='id'>
											<input name="checkboxid" type="checkbox" eqid='<s:property value="#theBean.eq_id" />' eqIp='<s:property value="#theBean.eq_ip" />'
												value="<s:property value="#theBean.eq_id"/>" 
												vmNum='<s:property value = "#theBean.vm_num"/>' cpu='<s:property value="#theBean.cpu_cl"/>' 
												storageNum='<s:property value="#theBean.storage_num"/>' nicNum='<s:property value="#theBean.NIC_NUM"/>' 
												mem='<s:property value="@java.lang.Math@round(#theBean.mem/1024*100) / 100.0"/>' />
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
										<td eid='<s:property value="#theBean.eq_id" />'>
										<s:if test="#theBean.vm_num!=0">
											<s:property value = "#theBean.vm_num"/> 个
										</s:if>
										<s:else>
											<s:property value = "#theBean.vm_num"/> 个
										</s:else>
										</td>
										<td>
											<s:property value="#theBean.cpu_cl"/> 核
										</td>
										<td eid='<s:property value="#theBean.eq_id" />'>
											<s:if test="#theBean.storage_num!=0">
												<s:property value="#theBean.storage_num"/>块
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
											<%-- 原单位未统一到MB，为B --%>
<%--												<s:property value="@java.lang.Math@round(#theBean.mem/1024/1024*100) / 100.0"/>--%>
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
											<s:if test="#theBean.hasvertual == 3">
												XEN
											</s:if>
											<s:elseif test="#theBean.hasvertual==4">
												VMWARE	
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==1">
												PowerVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==2">
												KVM
											</s:elseif>
											<s:elseif test="#theBean.hasvertual==0">
												非虚拟化
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
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
