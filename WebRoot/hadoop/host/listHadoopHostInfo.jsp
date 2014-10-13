<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/link4a.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	    
		function resetForm(){
			$("#host_name").attr("value","");
			$("#ip").attr("value","");
		}
		function searchRequest(){
			hostInfoObj.submit();
		} 
 		 function saveHadoophost(url){
			 $.ajax({
			    type:"POST",
	            url:url,
	            async: false,
	            cache: false,
		        success: function(msg){
		        	hostInfoObj.submit();
		        }
			});
		}
 		$(function(){
 			$("[name='addhost']").unbind().live("click",function(){
	 	 		$.dialog({
	 				id:'addhost',
	 				title:'添加主机',
	 				width: '500px',
	 				height: '400px',
	 				lock:true,
	 				content: 'url:hadoopHostInfo_addHostInfo.do'
	 	 		}); 
 		   }); 
 		   
 		   $("[name='modhost']").unbind().live("click",function(){ 
 			   var hostId = $("input['name=checkboxId']:checked").val();
 			   if(hostId == undefined){
 				   alert("请勾选一条信息");
 				   return false;
 			   }
	 	 		$.dialog({
	 				id:'modhost',
	 				title:'修改主机',
	 				width: '500px',
	 				height: '400px',
	 				lock:true,
	 				content: 'url:hadoopHostInfo_addHostInfo.do?hostInfoObj.id='+hostId
	 	 		});
 		   });
 		   
 		   $("[name='delhost']").unbind().live("click",function(){
 			  var hostId = $("input['name=checkboxId']:checked").val();
			   if(hostId == undefined){
				   alert("请勾选一条信息");
				   return false;
			   }
				if(confirm("确定要删除该主机吗？") == true)
			    { 
					$.ajax({
						type:"GET",
						url:"hadoopHostInfo_deleteByObj.do?hostInfoObj.id="+hostId,
						async: false,
			            cache: false,
						success :function(data){  
							hostInfoObj.submit();
						}
					});
				}
           }); 
 		});
 		
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="hadoopHostInfo_listHadoopHost.do" method="post" id="hostInfoObj">
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">主机管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">主机名称：</label>
				<s:textfield name="theForm.ACCOUNT" id="host_name" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">主机IP地址：</label>
				<s:textfield name="theForm.NAME" id="ip" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			</div>
			
			<div class="utt-2 mgt-20">
				<a name="addhost" class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a name="modhost" class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a name="delhost" class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
			
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">主机名称</th>
									<th onclick="sort(theTable,2,'string')">主机IP地址</th>
									<th onclick="sort(theTable,3,'string')">主机类型</th>
									<th onclick="sort(theTable,4,'string')">CPU</th>
									<th onclick="sort(theTable,5,'string')">内存</th>
									<th onclick="sort(theTable,6,'string')">本地存储</th>
									<th onclick="sort(theTable,7,'string')">HDFS存储</th>
									<th onclick="sort(theTable,8,'string')">操作系统</th>
<!-- 									<th onclick="sort(theTable,9,'string')">服务器类型</th> -->
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxId" type="checkbox" value="<s:property value="#theBean.id"/>" />
										</td>
										<td>
											<s:property value="#theBean.host_name" />
										</td>
										<td>
											<s:property value="#theBean.ip"/>
										</td>
										<td>
											<s:if test="#theBean.host_type == 0">
												物理机
											</s:if>
											<s:else>
												虚拟机
											</s:else>
										</td>
										<td>
											<s:property value="#theBean.cpu_num"/>核
										</td>
										<td>
											<s:property value="@java.lang.Math@round(#theBean.mem_size/1024*100) / 100.0"/>G
										</td>
										<td>
											<s:property value="@java.lang.Math@round(#theBean.local_disk_size/1024*100) / 100.0"/>G
										</td>
										<td>
											<s:property value="@java.lang.Math@round(#theBean.swap_size/1024*100) / 100.0"/>G
										</td>
										<td>
											<s:property value="#theBean.os" />
										</td>
<!-- 										<td> -->
<!-- 											<s:if test="#theBean.service_type == 0"> -->
<!-- 												x86服务器 -->
<!-- 											</s:if> -->
<!-- 											<s:elseif test="#theBean.service_type==1"> -->
<!-- 												机架服务器  -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service_type==2"> -->
<!-- 												vmware虚拟机 -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service_type==3"> -->
<!-- 												xen虚拟机 -->
<!-- 											</s:elseif> -->
<!-- 											<s:elseif test="#theBean.service_type==4"> -->
<!-- 												kvm虚拟机 -->
<!-- 											</s:elseif> -->
<!-- 											<s:else> -->
<!-- 												其他 -->
<!-- 											</s:else> -->
<!-- 										</td> -->
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				    </div>
				
			</div>
			</div>
			
			
		</div>
	</s:form>
</body>
