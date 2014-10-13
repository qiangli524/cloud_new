<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<script type="text/javascript">
	    
		function resetForm(){
			$("#host_name").attr("value","");
			$("#ip").attr("value","");
		}
		function searchRequest(){
			hostInfoObj.submit();
		} 
 		 function savePaashost(url){
			 $.ajax({
			    type:"POST",
	            url:url,
	            async: false,
	            cache: false,
		        success: function(msg){
		        	searchRequest();
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
	 				content: 'url:paasHostInfo_addHostInfo.do'
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
	 				content: 'url:paasHostInfo_addHostInfo.do?hostInfoObj.id='+hostId
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
						url:"paasHostInfo_deleteByObj.do?hostInfoObj.id="+hostId,
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
	<s:form action="paasHostInfo_listPaasHostInfo.do" method="post" id="hostInfoObj">
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
								<s:textfield name="hostInfoObj.host_name" cssClass="txt" id="host_name"></s:textfield>
							</td>
							<td class="til">
								主机IP地址:
							</td>
							<td>
								<s:textfield name="hostInfoObj.ip" cssClass="txt" id="ip"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm()" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" name="addhost" /></li>
							<li><input type="button" class="thickbox btn-style02" value="修改" name="modhost" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" name="delhost" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
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
				</div>
			</div>
		</div>
	</s:form>
</body>
