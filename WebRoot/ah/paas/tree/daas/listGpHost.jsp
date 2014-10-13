<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<head>
	<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectEntity,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		 $("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		});
	});
	
	function selectEntity(){
		var lenth = 0;
		var entity_id = "";
		var entity_name = "";
		$("input:checkbox[name='checkboxid']:checked").each(function(){
			entity_id += $(this).attr("entity_id")+",";
			entity_name += $(this).attr("entity_name")+",";
			lenth +=1;
		});
    	if(lenth==0){
    		alert('请选择一个实体');
    		return false;
    	}
    	api.get("addDBEntity").listDBEntity(entity_id,entity_name);
	}
	
		function resetForm(){
			$("#host_name").attr("value","");
			$("#ip").attr("value","");
		}
		
		function searchRequest(){
			hostInfoObj.submit();
		} 
		
 		
 		
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="paasHostInfo_listPaasHostInfo.do" method="post" id="hostInfoObj">
		<div>
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
					 	<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" /></div>
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择<input type="checkbox" id="checkAll"/>
									</th>
									<th onclick="sort(theTable,1,'string')">主机名称</th>
									<th onclick="sort(theTable,2,'string')">主机IP地址</th>
									<th onclick="sort(theTable,3,'string')">主机类型</th>
									<th onclick="sort(theTable,4,'string')">CPU</th>
									<th onclick="sort(theTable,5,'string')">内存</th>
									<th onclick="sort(theTable,6,'string')">本地存储</th>
									<th onclick="sort(theTable,8,'string')">操作系统</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
									<tr>
										<td>
											<input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>" 
											entity_id="<s:property value='#theBean.id'/>" entity_name="<s:property value='#theBean.host_name'/>"/>
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
