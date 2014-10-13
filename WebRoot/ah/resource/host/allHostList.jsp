<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%
String excel_export_jsp = request.getContextPath() + "/excel/excel_export.jsp";
%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
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
		function resetForm(){
			theForm.eq_name.value = "";
			theForm.eq_ip.value = "";
			theForm.allocated.value = "";
			theForm.hasvertual.value = "-1";
			theForm.type.value="-1";
			theForm.STATUS.value="";
		}
		function searchRequest(){
			mask('正在查询,请稍后....','0.5','0px');
			var poolid=theForm.hostpoolid.value; 
		    theForm.action = "resource_ah_allHostList.do?hostID=" + poolid;
 		 	theForm.submit();
		} 
		function allhost(poolid,flag){ 
			theForm.action='resource_allHostList.do?hostID='+poolid+'&flag='+flag;
			theForm.submit();
		} 
 		 function saveAllhost(url,poolid,flag){
			 $.ajax({
				type:"POST",
	           url:url,
	           async: true,
	           cache: false,
		        success: function(msg){
		          theForm.action='resource_allHostList.do?hostID='+poolid+'&flag='+flag;
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
		          	theForm.action='resource_allHostList.do';
					theForm.submit();
		        }
			});
		}
 		 function configHost(){
 		 	$("[name=checkboxid]").each(function(){
 		 		if(this.checked){
 		 			theForm.action = "resource_listHostConfig.do?eq_id=" + this.value;
 		 			theForm.submit();
 		 		}
 		 	});
 		 }
 		 
 		$(function(){
 			$("#checkHostStatus").click(function(){
 				 var hostids = "";
 				 if ($(":checkbox:checked").length == 0) {
					alert("请至少选择一个主机来检测");
					return false;
				}
 				$(":checkbox:checked").each(function(){
 					hostids += $(this).attr("eqid") + ",";
 		 		});
 				mask('正在检测主机状态,请稍后....','0.7','0px');
 				$.ajax({
 		 			type:'post',
 		 			dataType:'json',
 		 			url:'resource_checkHostStatus.do?hostids='+hostids,
 		 			success:function(rs){
 		 				removeMask();
 		 				//正常
 		 		 		var countnor = 0;
 		 		 		//异常
 		 				var countunnor = 0;
 		 		 		//关闭
 		 				var countstop = 0;
 		 		 		//未采集到
 		 				var countunknow = 0;
 		 		 		
 		 				for ( var i = 0; i < rs.length; i++) {
 		 					if (rs[i].status == 0) {
 		 						countunknow += 1;
 							} else if (rs[i].status == 1) {
 								countnor += 1;
 							} else if (rs[i].status == 2) {
 			 					countstop += 1;
 							} else if (rs[i].status == 3) {
 								countunnor += 1;
 							}
 						}
 		 				alert("检测完成,正常： "+countnor+"个, 关闭： "+countstop+"个, 异常： "+countunnor+"个, 未采集到： "+countunknow+"个!");
 		 				$("#theForm").submit();
 		 			}
 				});
 			});
 			
 			$("a[name='vm_motion']").click(function(){
 				var eq_id=$(this).parent().parent().children("td[name='id']").attr("eqId");
 				var conn_id =$(this).parent().parent().children("td[name='id']").attr("connID");
 				var hasvertual = $(this).parent().parent().children("td[name='id']").attr("hasvertual");
 				if(hasvertual!=0){
 					eq_id = conn_id +"_" + eq_id;
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
	 					width: '1200px',
	 					height: '750px',
	 					lock:true,
	 					content: 'url:showvm_listvm.do?eq_id='+eq_id+'&flag=host'
	 	 			});
 	 			} else {
 	 				$.dialog({
	 					id:'vm_list',
	 					title:'虚拟机信息',
	 					width: '1200px',
	 					height: '750px',
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
	 					width: '1100px',
	 					height: '750px',
	 					lock:true,
	 					content: 'url:datastore_liststorage.do?eq_id='+eq_id
	 	 			});
	 	 		} else {
	 	 			$.dialog({
	 					id:'storage_list',
	 					title:'存储信息',
	 					width: '1100px',
	 					height: '750px',
	 					lock:true,
	 					content: 'url:datastore_liststorage.do?eq_id='+eq_id
	 	 			});
	 	 		}
 			});
  
 			$("[name='addhost']").unbind().live("click",function(){
 				var flag=theForm.flag.value;
 				var poolid=theForm.hostpoolid.value;
	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
		 			w.$.dialog({
		 				id:'addhost',
		 				title:'添加主机',
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_addHost.do?flag=allhost'+'&poolid='+poolid+'&flag_hostpool='+flag
		 	 		}); 
		 	 	} else {
		 	 		$.dialog({
		 				id:'addhost',
		 				title:'添加主机',
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_addHost.do?flag=allhost'
		 	 		}); 
		 	 	}
 		   }); 
 		   
 		   $("[name='modhost']").unbind().live("click",function(){ 
	 		   	var couterNum = 0;
	 		   	var eid="";
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
			 	    for(var i=0;i<checkboxids.length;i++){
			 	      if(checkboxids[i].checked){
				 	      couterNum = couterNum + 1 ;
				 	      eid = checkboxids[i].value;
			 	      }
			 	    } 
		 	    }
		 	    if(couterNum==0){
		 	    	alert("请勾选需要修改的主机信息！");
		 	    	return false ;
		 	    }else if(couterNum>1){
		 	    	alert("一次只能修改一条主机信息");
		 	    	return false ;
		 	    }   
		 	    var flag=theForm.flag.value;
		 	    var poolid=theForm.hostpoolid.value;
		 	    
	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener; 
		 			w.$.dialog({
		 				id:'modhost',
		 				title:'修改主机',
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_modHost.do?eq_id=' +eid+"&flag=allhost"+'&poolid='+poolid+'&flag_hostpool='+flag
		 	 		}); 
	 	 		} else {
		 	 		$.dialog({
		 				id:'modhost',
		 				title:'修改主机',
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_modHost.do?eq_id=' +eid+"&flag=allhost"
		 	 		});
	 	 		}
 		   });
 		   
 		   $("[name='confighost']").unbind().live("click",function(){ 
 		   		var couterNum = 0;
	 		   	var eid="";
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
			 	    for(var i=0;i<checkboxids.length;i++){
			 	      if(checkboxids[i].checked){
				 	      couterNum = couterNum + 1 ;
				 	      eid = checkboxids[i].value;
			 	      }
			 	    } 
		 	    }
		 	    if(couterNum==0){
		 	    	alert("请勾选需要配置用户的主机！");
		 	    	return false ;
		 	    }else if(couterNum>1){
		 	    	alert("一次只能选择一条主机进行用户配置！");
		 	    	return false ;
		 	    }  
		 	    var flag=theForm.flag.value;
	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener; 
		 			w.$.dialog({
		 				id:'listHostConfig',
		 				title:'主机用户管理',
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_listHostConfig.do?eq_id=' +eid
		 	 		}); 
	 	 		} else {
	 	 			$.dialog({
		 				id:'listHostConfig',
		 				title:'主机用户管理',
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_listHostConfig.do?eq_id=' +eid
		 	 		});  
	 	 		}
 		   });
 		   
 		   $("[name='del']").unbind().live("click",function(){
	    		var couterNum = 0;
	    		var eid="";
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
			 	    for(var i=0;i<checkboxids.length;i++){
			 	      if(checkboxids[i].checked){
			 	      	couterNum = couterNum + 1 ;
			 	      	eid = checkboxids[i].value;
			 	      }
			 	    }
		 	    }
		 	    if(couterNum==0){
		 	    	alert("请勾选需要删除功能信息！");
		 	    	return false ;
		 	    }else if(couterNum>1){
		 	    	alert("一次只能删除单条功能信息");
		 	    	return false ;
		 	    }
		 	    var poolid=theForm.hostpoolid.value;
				if(confirm("确定要删除该主机吗？") == true)
			    { 
					$.ajax({
						type:"GET",
						url:"resource_delHost.do?eq_id="+ eid +"&flag=allhost",
						dataType:"json",
						success :function(data){  
							alert("删除成功！");
			    			theForm.action = "resource_allHostList.do?hostID=" + poolid;
					        $("#theForm").submit();
						}
					});
				}
           }); 
           
           $("#export").click(function(){
           		listExp();
           
           })
           
            $("#searchForm").click(function(){
           		searchRequest();
           
           })
           
            $("#resetForm").click(function(){
           		resetForm();
           
           })
           
           
 		});
 		
 		function listExp(){  
		    var eq_name = theForm.eq_name.value;
		    var eq_ip = theForm.eq_ip.value;
		    var type = theForm.type.value;
		    var hasvertual = theForm.hasvertual.value;
		    var center_uuid = theForm.center_uuidd.value;
		    
<%-- 		    var url="<%=excel_export_jsp%>?key=hostinfo&eq_name=" + eq_name + "&eq_ip=" + eq_ip;--%>		    
			var url="<%=excel_export_jsp%>?key=hostInfo1&eq_name=" + eq_name + "&eq_ip=" + eq_ip;
			if (center_uuid!='') {
		    	url = url + "&center_uuid=" + center_uuid;
		    }
			if (type > -1) {
		    	url = url + "&type=" + type;
		    }
		    if (hasvertual > -1) {
		    	url = url + "&hasvertual=" + hasvertual;
		    } 
		    exportForm.action =url;
		    exportForm.submit();
		}
 		$(function(){
 			$("[name='viewDevice']").unbind().live("click",function(){
 				var deviceId = $(this).parent().attr("deviceid");
 				var flag=theForm.flag.value;
 				if (flag == 'hostpool') {
					var api = frameElement.api;
					var w = api.opener;
					w.$.dialog({
						id:'viewDeviceDetail',
						title:'存储设备详细信息',
						width:'900px',
						height:'500px',
						lock:true,
						content:'url:resource_viewDeviceDetail.do?deviceId='+deviceId
					});
				} else {
					$.dialog({
						id:'viewDeviceDetail',
						title:'存储设备详细信息',
						width:'900px',
						height:'500px',
						lock:true,
						content:'url:resource_viewDeviceDetail.do?deviceId='+deviceId
					});
				}
 			});
 		});
	</script>
</head>
<body onLoad="self.focus();document.theForm.eq_name.focus()" class="pop-body scrollbody">
	<s:form action="resource_ah_allHostList.do" method="post" cssClass="theForm" id="theForm">
		<s:hidden id="hostUuids" name="theForm.hostUuids"></s:hidden>
		<s:hidden id="flag" name="theForm.flag"></s:hidden>
		<s:hidden id="hostpoolid" name="theForm.HOST_POOL_ID"></s:hidden>
	<div class="scrollbody">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">主机管理</h2>
	       	<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
        			<label class="vm">数据中心:</label>
					<s:select list="theForm.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
									name="theForm.center_uuid" value="theForm.center_uuid" cssClass="select-1 vm"></s:select>
        			<label class="vm">主机名称:</label>
					<s:textfield name="theForm.eq_name" cssClass="inpt-1 vm" id="eq_name"></s:textfield>
        			<label class="vm">主机IP地址:</label>
					<s:textfield name="theForm.eq_ip" cssClass="inpt-1 vm" id="eq_ip"></s:textfield>
        			<label class="vm">主机类型:</label>
					<s:if test="location == 'ah'">
						<s:select list="#{'-1':'---请选择--','4':'HPx86刀片','5':'机架服务器'}"
							name="theForm.eq_type" id="type" cssClass="select-1 vm"></s:select>
					</s:if>
					<s:else>
						<s:select
							list="#{'-1':'---请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器'}"
							name="theForm.eq_type" id="type" cssClass="select-1 vm"></s:select>
					</s:else>
        			<div>
        				<label class="vm">是否已分配:</label>
						<s:select list="#{'':'---请选择--','0':'未分配','1':'已分配'}" name="theForm.allocated" id="allocated" cssClass="select-1 vm"/>
	        			<label class="vm">主机状态:</label>
						<s:select list="#{'':'---请选择--','0':'未采集到','1':'正常启动','2':'关闭','3':'异常'}" name="theForm.STATUS" id="STATUS" cssClass="select-1 vm"></s:select>
	        			<label class="vm">虚拟化类型：</label>
						<s:select list="#{'-1':'---请选择--','3':'XEN','4':'VMWARE','1':'PowerVM','2':'KVM','0':'非虚拟化'}" name="theForm.hasvertual" id="hasvertual" cssClass="select-1 vm"></s:select>
	        			<span class="ubtn-1 mgl-20"><input type="button"  id="searchForm"   value="查询" /></span>
						<span class="ubtn-2 mgl-20"><input type="button" id="resetForm"  value="重置" /></span>
					</div>
				</div>
				<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)"  id="addhost" >新增</a>
						<a class="icon-modify" href="javascript:void(0)"  id="modhost" >修改</a>
						<a class="icon-del" href="javascript:void(0)" id="del" >删除</a>
						<a class="icon-set" href="javascript:void(0)" id="confighost" >配置</a>
					    <a class="icon-export" href="javascript:void(0)" id="export" >导出</a>
				</div>
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
							<th onclick="sort(theTable,3,'ip')">主机IP地址</th>
							<th onclick="sort(theTable,4,'float')">虚拟机(个)</th>
							<th onclick="sort(theTable,5,'float')">CPU(核)</th>
							<th onclick="sort(theTable,6,'float')">LUN(块)</th>
							<th onclick="sort(theTable,7,'string')">存储设备</th>
							<!-- <th onclick="sort(theTable,8,'string')">网卡</th> -->
							<th onclick="sort(theTable,8,'float')">内存(G)</th>
							<th onclick="sort(theTable,9,'string')">是否已分配</th>
							<th onclick="sort(theTable,10,'string')">虚拟化类型</th>
							<th onclick="sort(theTable,11,'string')">性能</th>
							<th onclick="sort(theTable,12,'string')">状态</th>
							<th onclick="sort(theTable,13,'date')">时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="theForm.resultList" id="theBean">
							<tr>
								<td eqId='<s:property value="#theBean.h_uuid" />' connID='<s:property value="#theBean.connectId" />' hasvertual='<s:property value="#theBean.hasvertual" />' name='id'>
									<input name="checkboxid" type="checkbox" eqid='<s:property value="#theBean.eq_id" />' 
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
									<a href='javascript:;' name='vm_list'><s:property value = "#theBean.vm_num"/></a>
								</s:if>
								<s:else>
									<s:property value = "#theBean.vm_num"/>
								</s:else>
								</td>
								<td>
									<s:property value="#theBean.cpu_cl"/>
								</td>
								<td eid='<s:property value="#theBean.eq_id" />'>
									<s:if test="#theBean.storage_num!=0">
										<a href='javascript:;' name='storage_list'><s:property value="#theBean.storage_num"/></a>
									</s:if>
									<s:else>
										<s:property value="#theBean.storage_num"/>
									</s:else>
								</td>
								<td deviceid='<s:property value="#theBean.deviceId" />'>
									<s:if test="#theBean.storage_num!=0">
										<a href="javascript:;" name="viewDevice">
											<s:property value="#theBean.deviceName"/>
										</a>
									</s:if>
									<s:else>
										--
									</s:else>
								</td>
								<%-- <td>
									<s:property value="#theBean.NIC_NUM"/>个
								</td> --%>
								<td width="50px;">
									<s:if test='#theBean.mem != null'>
									<%-- 原单位未统一到MB，为B --%>
<%--												<s:property value="@java.lang.Math@round(#theBean.mem/1024/1024*100) / 100.0"/>--%>
										<s:property value="@java.lang.Math@round(#theBean.mem/1024*100) / 100.0"/>
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
				<div class="pages">
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>	
			</div>
		</div>
	</div>
	</s:form>
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
