<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link_export.jsp"%>
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

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<script type="text/javascript">
	    
		function resetForm(){
			hostForm.eq_name.value = "";
			hostForm.eq_ip.value = "";
			hostForm.hasvertual.value = "-1";
			hostForm.type.value="-1";
		}
		function searchRequest(){
			var poolid=hostForm.hostpoolid.value; 
		    hostForm.action = "resource_allHostList.do?hostID=" + poolid;
 		 	hostForm.submit();
		} 
		function allhost(poolid,flag){ 
			hostForm.action='resource_allHostList.do?hostID='+poolid+'&flag='+flag;
			hostForm.submit();
		} 
 		 
 		 function configHost(){
 		 	$("[name=checkboxid]").each(function(){
 		 		if(this.checked){
 		 			hostForm.action = "resource_listHostConfig.do?eq_id=" + this.value;
 		 			hostForm.submit();
 		 		}
 		 	});
 		 }
 		 
 		 function checkHost(){
 			var couterNum = 0;
 	 		var hostids = "";
 	 		var temp = "";
 	 		 var count0 = 0;
			 var count1 = 0;
			 var count2 = 0;
			 var count3 = 0;
 	 	    var checkboxids = document.getElementsByName("checkboxid");
 	 	    if(checkboxids!=null&&checkboxids.length>0){
 	 	   		 for(var i=0;i<checkboxids.length;i++){
 	 	      			if(checkboxids[i].checked){
 	 	      				couterNum = couterNum + 1 ;
 	 	      			 	temp = checkboxids[i].value;
 	 	      			}
 	 	    	 }
 	 	    }
 	 	    if(couterNum==0){
 	 	    	alert("请勾选需要检测的主机！");
 	 	    	return false ;
 	 	    }else if(couterNum>1){
 	 	    	for(var j=0;j<checkboxids.length;j++){
 	 	    		if(checkboxids[j].checked){
 	 	    			 var host_id = checkboxids[j].value;
 	 	    			 if (hostids != "") {
 	 	    				hostids =host_id+','+hostids;
 						} else {
 							hostids = host_id;
 						}
 	 	    		}
 	 	    	}
 	 	    }else{
 	 	    	hostids = temp;
 	 	    }
 	 	    $.ajax({
 	 	    	data:'hostid='+hostids,
				 async:false,
				 type:'post',
				 dataType:'json',
				 url:'resource_checkHostStatus.do',
				 success:function(rs){
					 for(var i = 0; i < rs.length; i++){
						 var htmlstr = "<td>";
						 if (rs[i].status == 0) {
								htmlstr += "未采集到";
								count0 += 1;
						 } else if (rs[i].status == 1) {
								htmlstr += "正常启动";
								count1 += 1;
						 } else if(rs[i].status == 2){
								htmlstr += "关闭";
								count2 += 1;
						 } else{
								htmlstr += "异常";
								count3 += 1;
						 }
						 htmlstr += "</td>";
						 for(var k=0;k<checkboxids.length;k++){
					 	      if(checkboxids[k].value == rs[i].hostid){
					 	    	 var $checked = $(checkboxids[k]);
					 	    	 $checked.parent().parent().children(":eq(11)").replaceWith(htmlstr);
					 	      }
					 	 }
					 }
				 }
 	 	    });
	 	 	alert("检测完成,未采集到: "+count0+"个，正常启动: "+count1+"个，关闭: "+count2+"个，异常: "+count3+"个！");
 		 }
 		 

 		$(function(){
 			$("a[name='vm_motion']").click(function(){
 				var eq_id=$(this).parent().parent().children("td[name='id']").attr("eqId");
 				var flag=hostForm.flag.value;
 				if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
	 				w.$.dialog({
	 					id:'vm_motion',
	 					title:'性能监控',
	 					width: '1000px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:hostMonitor_highChartsTabs.do?id='+eq_id
	 	 			});
 	 			} else {
 	 				$.dialog({
	 					id:'vm_motion',
	 					title:'性能监控',
	 					width: '1000px',
	 					height: '500px',
	 					lock:true,
	 					content: 'url:hostMonitor_highChartsTabs.do?id='+eq_id
	 	 			});
 	 			}
 			});
 			
 			$("a[name='vm_list']").click(function(){
	 			var $td = $(this).parent();
	        	var eq_id = $td.attr("eid");
	        	var flag=hostForm.flag.value;
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
	        	var flag=hostForm.flag.value;
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
  
 			$("[name='addhost']").unbind().live("click",function(){
 				var flag=hostForm.flag.value;
 				var poolid=hostForm.hostpoolid.value;

	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener;
		 			w.$.dialog({
		 				id:'addhost',
		 				title:'添加主机',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
		 				content: 'url:resource_addHost.do?flag=allhost'+'&poolid='+poolid
		 	 		}); 
		 	 	} else {
		 	 		$.dialog({
		 				id:'addhost',
		 				title:'添加主机',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
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
		 	    var flag=hostForm.flag.value;
		 	    var poolid=hostForm.hostpoolid.value;
		 	    
	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener; 
		 			w.$.dialog({
		 				id:'modhost',
		 				title:'修改主机',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
		 				content: 'url:resource_modHost.do?eq_id=' +eid+"&flag=allhost"+'&poolid='+poolid
		 	 		}); 
	 	 		} else {
		 	 		$.dialog({
		 				id:'modhost',
		 				title:'修改主机',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
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
		 	    var flag=hostForm.flag.value;
	        	if (flag == 'hostpool') { 
					var api = frameElement.api;
				    var w = api.opener; 
		 			w.$.dialog({
		 				id:'listHostConfig',
		 				title:'主机用户管理',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
		 				content: 'url:resource_listHostConfig.do?eq_id=' +eid
		 	 		}); 
	 	 		} else {
	 	 			$.dialog({
		 				id:'listHostConfig',
		 				title:'主机用户管理',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
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
		 	    var poolid=hostForm.hostpoolid.value;
				if(confirm("确定要删除该主机吗？") == true)
			    { 
					$.ajax({
						type:"GET",
						url:"resource_delHost.do?eq_id="+ eid +"&flag=allhost",
						dataType:"json",
						success :function(data){  
							alert("删除成功！");
			    			hostForm.action = "resource_allHostList.do?hostID=" + poolid;
					        $("#hostForm").submit();
						}
					});
				}
           }); 
 		});
 		
 		function listExp(){  
		    var eq_name = hostForm.eq_name.value;
		    var eq_ip = hostForm.eq_ip.value;
		    var type = hostForm.type.value;
		    var hasvertual = hostForm.hasvertual.value;
		    var url="<%=excel_export_jsp%>?key=hostinfo&eq_name=" + eq_name + "&eq_ip=" + eq_ip;
		    if (type > -1) {
		    	url = url + "&type=" + type;
		    }
		    if (hasvertual > -1) {
		    	url = url + "&hasvertual=" + hasvertual;
		    } 
		    document.forms[0].action =url;
		    document.forms[0].submit();
		}
	</script>
</head>
<body onLoad="self.focus();document.hostForm.eq_name.focus()">
	<s:form action="cubinet_checkHostList.do" method="post" cssClass="hostForm" id="hostForm">
		<s:hidden id="hostUuids" name="hostForm.hostUuids"></s:hidden>
		<s:hidden id="flag" name="hostForm.flag"></s:hidden>
		<s:hidden id="hostpoolid" name="hostForm.HOST_POOL_ID"></s:hidden>
		<div class="scrollbody">
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
								<s:textfield name="hostForm.eq_name" cssClass="txt" id="eq_name"></s:textfield>
							</td>
							<td class="til">
								主机IP地址:
							</td>
							<td>
								<s:textfield name="hostForm.eq_ip" cssClass="txt" id="eq_ip"></s:textfield>
							</td>
							<td class="til">
								主机类型:
							</td>
							<td>
								<s:select list="#{'-1':'--请选择--','1':'IBM小型机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片'}" name="hostForm.eq_type" id="type"></s:select>
							</td>
							<td class="til">
								虚拟化类型:
							</td>
							<td>
								<s:select list="#{'-1':'--请选择--','3':'XEN','4':'VMWARE','1':'PowerVM','2':'KVM','0':'非虚拟化'}" name="hostForm.hasvertual" id="hasvertual"></s:select>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('hostForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=hostForm" />
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
									<th onclick="sort(theTable,9,'string')">虚拟化类型</th>
									<th onclick="sort(theTable,11,'string')">状态</th>
									<th onclick="sort(theTable,12,'date')">时间</th>
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
												IBM刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 3">
												IBM普通刀片
											</s:elseif>
											<s:elseif test="#theBean.eq_type == 4">
												HPx86刀片
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.eq_ip" />
										</td>
										<td>
											<s:property value = "#theBean.vm_num"/> 个
										</td>
										<td>
											<s:property value="#theBean.cpu_cl"/> 核
										</td>
										<td> 
											<s:property value="#theBean.storage_num"/>块 
										</td>
										<td>
											<s:property value="#theBean.NIC_NUM"/>个
										</td>
										<td width="50px;">
											<s:if test='#theBean.mem != null'>
											<%-- 原单位未统一到MB，为B --%>
<%--												<s:property value="@java.lang.Math@round(#theBean.mem/1024/1024*100) / 100.0"/>--%>
												<s:property value="@java.lang.Math@round(#theBean.mem*100) / 100.0"/>
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
</body>
