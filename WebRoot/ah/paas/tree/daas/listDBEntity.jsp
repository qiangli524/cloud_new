<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
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
	})
	
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
	
   	//查询
   	function searchRequest() {
   		entityObj.submit();
   	}

	$(function(){
		$(".query").click(function(){
			if($(".query-form").is(":visible")){
				$(".query-form").slideUp("slow");
			}else{
				$(".query-form").slideDown("slow");
			}
		});
		
		$("#searchForm").click(function(){
			searchRequest();
		});
		
		$("#resetForm").click(function(){
			$("#entity_name").attr("value","");
			$("#host_address").attr("value","");
		});
		
		$("#checkAll").click(function(){
			var queuebox = document.getElementsByName("checkboxid");
			var checkall = document.getElementById("checkAll");
			for ( var i = 0; i < queuebox.length; i++) {
				queuebox[i].checked = checkall.checked;
			}
		})
		
	});
	
</script>
</head>
<body>
	<s:form action="paasTree_listDBEntity.do" method="post" id="entityObj" cssStyle="entityObj">
	<s:hidden name="flag" id="flag"></s:hidden>
		<div class="scrollbody">
			<div class="pd-20 bgcolor-1">
	        	<div class="bord-1 pd-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">实体名字：</td>
							<td>
								<s:textfield name="entityObj.entity_name" id="entity_name" size="15"></s:textfield>
							</td>
							<td class="til">服务器地址：</td>
							<td>
								<s:textfield name="entityObj.host_address" id="host_address" size="15"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="10" class="pd-10">
								<div align="center">
									<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="searchForm" /></span>
									<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resetForm" /></span>
								</div>
							</td>
						</tr>
					</table>
					<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
						<thead>
							<tr>
								<th><input type="checkbox" id="checkAll"/>选择</th> 
								<th onclick="sort(theTable,1,'string')">实体名字</th>
								<th onclick="sort(theTable,2,'string')">实体类型</th>
								<th onclick="sort(theTable,3,'string')">服务器地址</th>
								<th onclick="sort(theTable,4,'string')">端口</th>
								<th onclick="sort(theTable,5,'string')">插入时间</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="resultList" id="theBean">
								 <tr>
									<td>
										<input type="checkbox" name="checkboxid" id="<s:property value='#theBean.id'/>" 
										entity_id="<s:property value='#theBean.entity_id'/>" entity_name="<s:property value='#theBean.entity_name'/>" />
									</td>
									<td>
										<s:property value="#theBean.entity_name"/>
									</td>
									<td>
										<s:if test="#theBean.entity_type==31">
											Oracle DB
										</s:if>
										<s:elseif test="#theBean.entity_type==32">
											Oracle Instance
										</s:elseif>
										<s:elseif test="#theBean.entity_type==33">
											Oracle Server
										</s:elseif>
										<s:elseif test="#theBean.entity_type==34">
											Oracle监听器
										</s:elseif>
										<s:elseif test="#theBean.entity_type==35">
											业务服务
										</s:elseif>
										<s:elseif test="#theBean.entity_type==4">
											Mysql
										</s:elseif>
									</td>
									<td>
										<s:property value="#theBean.host_address"/>
									</td>
									<td>
										<s:property value="#theBean.host_port"/>
									</td>
									<td>
										<s:property value="#theBean.insert_time"/>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
					<div class="pages mgb-10"><!-- 分页 -->
						<jsp:include page="../../../../sxcloud/inc/Pagination.jsp?formId=entityObj" />
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>