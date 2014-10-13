<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
	<title></title>

	<script type="text/javascript">
		<%-- var id= '<%=request.getAttribute("id")%>'; --%>
   		var api = frameElement.api;
		w = api.opener;
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:submitRequest,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '返回'
		 });
		 
	function submitRequest(){ 
		var serviceName = $("#serviceName").val();
		var old_kpiId=$("#kpiId").val();
		var new_kpiId="";  
		$("input['name=checkboxId']:checked").each(function(){
			new_kpiId+=$(this).val()+","; 
		});
		if(new_kpiId==""){
			alert("KPI不能为空");
			return false;
		}
		w.editServiceKpiRelation(serviceName,old_kpiId,new_kpiId);
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" cssClass="obj" id="obj">
<s:hidden name="relationObj.service_name" id="serviceName"></s:hidden>
<s:hidden name="relationObj.kpi_id" id="kpiId"></s:hidden>
<s:hidden name="obj.id" id="id"></s:hidden> 
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr id="net">
					<td class="til" style="width: 60px;">
						服务名称
					</td>
					<td>
						<s:textfield name="relationObj.service_name" disabled="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til" style="width: 60px;">
							KPI名称
					</td>
					<td>
						<s:iterator value="kpiList" id="theBean" status="st">
							<s:if test="#theBean.kpi_id==relationObj.kpi_id">
								<s:if test="#st.getIndex()%1==0">
									<p>
								</s:if>
									<input name="checkboxId" id="id" type="checkbox" value="<s:property value="#theBean.kpi_id"/>" checked="checked"/>
									<s:property value="#theBean.description"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="(#st.getIndex()+1)%1==0">
									</p>
								</s:if>
							</s:if>
							<s:else>
								<s:if test="#st.getIndex()%1==0">
									<p>
								</s:if>
									<input name="checkboxId" id="id" type="checkbox" value="<s:property value="#theBean.kpi_id"/>"/>
									<s:property value="#theBean.description"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="(#st.getIndex()+1)%1==0">
									</p>
								</s:if>
							</s:else>
						</s:iterator>
					</td>
				</tr>
			</table>
</s:form>
</body>