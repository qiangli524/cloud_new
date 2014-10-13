<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link_export.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
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
		    theForm.action = "resource_allHostList.do?hostID=" + poolid;
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
 				if(hasvertual!=0 ){
 					if(conn_id!=null && conn_id!=""){
 						eq_id = conn_id +"_" + eq_id;
 					}else{
 						eq_id = eq_id;//山西主机表中没有存connect_id @yanggl
 					}
 				}else{
 					eq_id = eq_id;//@xugang
 				}
 				
 				var flag=theForm.flag.value;
 				if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
	 				w.$.dialog({
	 					id:'vm_motion',
	 					title:'性能监控',
	 					lock:true,
	 					width: '800px',
	 					height: '500px',
	 					content: 'url:hostMonitor_highChartsTabs.do?id='+eq_id
	 	 			});
 	 			} else {
 	 				$.dialog({
	 					id:'vm_motion',
	 					title:'性能监控',
	 					lock:true,
	 					width: '800px',
	 					height: '500px',
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
	 					lock:true,
	 					width: '1000px',
	 					height: '530px',
	 					content: 'url:showvm_listvm.do?eq_id='+eq_id+'&flag=host'
	 	 			});
 	 			} else {
 	 				$.dialog({
	 					id:'vm_list',
	 					title:'虚拟机信息',
	 					lock:true,
	 					width: '1000px',
	 					height: '530px',
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
	 					lock:true,
	 					width: '1000px',
	 					height: '530px',
	 					content: 'url:datastore_liststorage.do?eq_id='+eq_id
	 	 			});
	 	 		} else {
	 	 			$.dialog({
	 					id:'storage_list',
	 					title:'存储信息',
	 					lock:true,
	 					width: '1000px',
	 					height: '530px',
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
		 				lock:true,
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_addHost.do?flag=allhost'+'&poolid='+poolid+'&flag_hostpool='+flag
		 	 		}); 
		 	 	} else {
		 	 		$.dialog({
		 				id:'addhost',
		 				title:'添加主机',
		 				lock:true,
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
		 				lock:true,
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_modHost.do?eq_id=' +eid+"&flag=allhost"+'&poolid='+poolid+'&flag_hostpool='+flag
		 	 		}); 
	 	 		} else {
		 	 		$.dialog({
		 				id:'modhost',
		 				title:'修改主机',
		 				lock:true,
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
		 				lock:true,
		 				width: '800px',
		 				height: '400px',
		 				content: 'url:resource_listHostConfig.do?eq_id=' +eid
		 	 		}); 
	 	 		} else {
	 	 			$.dialog({
		 				id:'listHostConfig',
		 				title:'主机用户管理',
		 				lock:true,
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
 		});
 		
 		function listExp(){  
		    var eq_name = theForm.eq_name.value;
		    var eq_ip = theForm.eq_ip.value;
		    var type = theForm.type.value;
		    var hasvertual = theForm.hasvertual.value;
<%-- 		    var url="<%=excel_export_jsp%>?key=hostinfo&eq_name=" + eq_name + "&eq_ip=" + eq_ip;--%>		    
			var url="<%=excel_export_jsp%>?key=hostInfo1&eq_name=" + eq_name + "&eq_ip=" + eq_ip;
			if (type > -1) {
		    	url = url + "&type=" + type;
		    }
		    if (hasvertual > -1) {
		    	url = url + "&hasvertual=" + hasvertual;
		    } 
		    exportForm.action =url;
		    exportForm.submit();
		}
 		
 		/*
 		function addHostInfo(oper){
 			var $td = $("#hostState").parent();
			var eq_id = $td.attr("eq_id");
 			$.dialog({
 				id:'updateState',
 				title:'添加主机信息',
 				lock:true,
 				width: '500px',
 				height: '300px',
 				content: 'url:resource_addHostInfo.do?oper='+oper+'&theForm.eq_id='+eq_id
 	 		}); 
 		}
 		*/
 		function addHostInfo(oper,eqid){
 			//var $td = $("#hostState").parent();
			//var eq_id = $td.attr("eq_id");
			//此处为共用页面，之前titile均为“添加主机信息”,故修改之
			var _title = "添加主机信息";
			if(oper == 'stop'){
				_title = "主机下电";
			}else if(oper == 'start'){
				_title = "主机上电";
			}else if(oper == 'restart'){
				_title = "主机重启";
			}
 			$.dialog({
 				id:'updateState',
 				title:_title,
 				lock:true,
 				width: '500px',
 				height: '300px',
 				content: 'url:resource_addHostInfo.do?oper='+oper+'&theForm.eq_id='+eqid
 	 		}); 
 		}
 		
 		function updateHostState(url){
 			$.ajax({
				type:"post",
				url:url,
				async:false,
	            cache:false,
				success :function(data){  
					if(data == '-1'){
						alert("操作失败，请检查原因")
					}else if(data == '1'){
						$("#theForm").submit();
					}
				}
			});
 		}
 		
 		//进入控制台页面(中央动漫福建移动投标演示)
 		function console(){
 			$.dialog({
 				id:'console',
 				title:'控制台',
 				lock:true,
 				width: '500px',
 				height: '300px',
 				content: 'url:resource_showConsole.do'
 	 		}); 
 		}
 		//打开控制台
 		function showConsole(url){
 			window.open(url,"控制台");
 		}
 		
	</script>
</head>
<body onLoad="self.focus();document.theForm.eq_name.focus()" class="mainbody">
	<s:form action="resource_allHostList.do" method="post" cssClass="theForm" id="theForm">
		<s:hidden id="hostUuids" name="theForm.hostUuids"></s:hidden>
		<s:hidden id="flag" name="theForm.flag"></s:hidden>
		<s:hidden id="hostpoolid" name="theForm.HOST_POOL_ID"></s:hidden>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">主机列表</h2>
					<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="fl">主机名称:</label>
							<s:textfield name="theForm.eq_name" cssClass="inpt-1 fl" id="eq_name"></s:textfield>
						 </div>
						<div class="filtrate-field">
							<label class="fl">主机IP地址:</label>
							<s:textfield name="theForm.eq_ip" cssClass="inpt-1 fl" id="eq_ip"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="fl">是否已分配:</label>
							<s:select cssClass="select-1 vm" list="#{'':'--请选择--','0':'未分配','1':'已分配'}" name="theForm.allocated" id="allocated" />
						</div>
						<div class="filtrate-field">	
							<label class="fl">主机类型:</label>
								<!-- 
								<s:if test="location == 'ah'">
									<s:select list="#{'-1':'--请选择--','4':'HPx86刀片','5':'机架服务器'}" name="theForm.eq_type" id="type"></s:select>
								</s:if>
								 -->
							<s:select cssClass="select-1 vm" list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片','5':'机架服务器','6':'华为服务器'}" name="theForm.eq_type" id="type"></s:select>
						</div>
						<div class="filtrate-field">
							<label class="fl">主机状态:</label>
							<s:select cssClass="select-1 vm"  list="#{'':'--请选择--','0':'未采集到','1':'正常启动','2':'关闭','3':'异常'}" name="theForm.STATUS" id="STATUS"></s:select>
						</div>
						<div class="filtrate-field">
							<label class="fl">虚拟化类型:</label>
							<s:select cssClass="select-1 vm"  list="#{'-1':'--请选择--','3':'XEN','4':'VMWARE','1':'PowerVM','2':'KVM','0':'非虚拟化'}" name="theForm.hasvertual" id="hasvertual"></s:select>
						</div>
						<div class="filtrate-field">
							<span class="ubtn-1 mgl-20"><input type="button" value="查询"
										onclick="javascript:searchRequest()" />
							</span>
							<span class="ubtn-2 mgl-20"><input type="button" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
							</span>
						</div>
					</div>
					<div  class="utt-2 mgt-20">
							<a class="icon-add" href="javascript:void(0)"  name="addhost" />增加</a>
							<a class="icon-modify" href="javascript:void(0)" name="modhost" />修改</a>
							<a class="icon-del" href="javascript:void(0)" name = "del" />删除</a>
							<a class="icon-set" href="javascript:void(0)"  name = "confighost" />配置</a>
							<a class="icon-check" href="javascript:void(0)"  id="checkHostStatus" />状态检测</a>
							<a class="icon-export" href="javascript:void(0)" onclick = "listExp();" />导出</a>
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
									<th onclick="sort(theTable,3,'string')">虚拟化类型</th>
									<th onclick="sort(theTable,4,'string')">状态</th>
									<th onclick="sort(theTable,5,'string')">是否已分配</th>
									<th onclick="sort(theTable,6,'string')">主机IP地址</th>
									<th onclick="sort(theTable,7,'string')">虚拟机</th>
									<th onclick="sort(theTable,8,'string')">CPU</th>
									<th onclick="sort(theTable,9,'string')">内存</th>
									<th onclick="sort(theTable,10,'string')">存储</th>
									<th onclick="sort(theTable,11,'string')">网卡</th>
									<th onclick="sort(theTable,12,'string')">性能</th>
									<th onclick="sort(theTable,13,'date')">接入时间</th>
<%--									<th onclick="sort(theTable,14,'date')">操作</th>--%>
<%--									<th onclick="sort(theTable,15,'date')">控制台</th>--%>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td eqId='<s:property value="#theBean.h_uuid" />' connID='<s:property value="#theBean.connectId" />' hasvertual='<s:property value="#theBean.hasvertual" />' name='id'>
											<input name="checkboxid" type="checkbox" eqid='<s:property value="#theBean.eq_id"  />' 
												value="<s:property value="#theBean.eq_id"/>" />
										</td>
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
											<s:elseif test="#theBean.eq_type == 6">
												华为服务器
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 7">
												华为 pc服务器
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
											<s:if test="#theBean.allocated == 0">
												未分配
											</s:if>
											<s:elseif test="#theBean.allocated==1">
												已分配	
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
										</td>
										<td eid='<s:property value="#theBean.eq_id" />'>
										<s:if test="#theBean.vm_num!=0">
											<a href='javascript:;' name='vm_list' style="text-decoration:underline"><s:property value = "#theBean.vm_num"/> 个</a>
										</s:if>
										<s:else>
											<s:property value = "#theBean.vm_num"/> 个
										</s:else>
										</td>
										<td>
											<s:property value="#theBean.cpu_cl"/> 核
										</td>
										<td width="50px;">
											<s:if test='#theBean.mem != null'>
											<%-- 原单位未统一到MB，为B --%>
<%--												<s:property value="@java.lang.Math@round(#theBean.mem/1024/1024*100) / 100.0"/>--%>
<!-- 												<s:property value="@java.lang.Math@round(#theBean.mem/1024*100) / 100.0"/> -->
												<fmt:formatNumber value="${(theBean.mem)/1024}" pattern="#,###" type="number"/>
												G
											</s:if>
										</td>
										<td eid='<s:property value="#theBean.eq_id" />'>
											<s:if test="#theBean.storage_num!=0">
												<a href='javascript:;' name='storage_list' style="text-decoration:underline"><s:property value="#theBean.storage_num"/>块</a>
											</s:if>
											<s:else>
												<s:property value="#theBean.storage_num"/>块
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.NIC_NUM"/>个
										</td>
										
										
										<td>
											<a href='javascript:;' name='vm_motion' style="text-decoration:underline">性能</a>
										</td>
										
										<td>
											<s:property value="#theBean.ins_date" />
										</td>
<%--										<td eq_id='<s:property value="#theBean.eq_id"/>'>--%>
<%--											<s:if test="#theBean.STATUS == 2">--%>
<%--												<a href="javascript:;"  style="text-decoration:underline" name="hostState" id="hostState" onclick="addHostInfo('start','<s:property value="#theBean.eq_id"/>')">--%>
<%--													上电--%>
<%--												</a>--%>
<%--											</s:if>--%>
<%--											<s:if test="#theBean.STATUS == 1">--%>
<%--												<a href="javascript:;"  style="text-decoration:underline" name="hostState" id="hostState" onclick="addHostInfo('stop','<s:property value="#theBean.eq_id"/>')">--%>
<%--													下电--%>
<%--												</a>--%>
<%--											</s:if>&nbsp;&nbsp;--%>
<%--											<a href="javascript:;" style="text-decoration:underline" name="hostState" id="hostState" onclick="addHostInfo('restart','<s:property value="#theBean.eq_id"/>')">--%>
<%--												重启--%>
<%--											</a>--%>
<%--										</td>--%>
<%--										<td><a href="javascript:console();" style="text-decoration:underline">控制台</a></td>--%>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
				</div>
			</div>
	</s:form>
</div>
	<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
