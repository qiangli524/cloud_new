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
		 
	 	$(function(){
			 $("[name='selectKpi_id']").unbind().live("click",function(){
			  		w.$.dialog({
		    			id:'kpiId',
		    			title:'选择KPI',
		    			width: '750px',
		    			height: '470px',
		    		    lock:true,
		    			content: 'url:relation_queryKpi_id.do'
			    		});
				 });
		});
	function submitRequest(){ 
		var serviceName = $("#serviceId").val();
		var kpi_id=$("#kpiId").val();
		if(serviceName==""){
			alert("服务不能为空");
			return false;
		}
		if(kpi_id==""){
			alert("KPI不能为空");
			return false;
		}
		w.saveServiceKpiRelation(serviceName,kpi_id);
	}
	
	function saveKpi_id(data){
		$("#kpiId").attr("value",data);
		var kpi_id_format = "";
		var kpi_id = data.substring(0,data.length-1);
		var arr = kpi_id.split(',');
		for(var i=0;i<arr.length;i++){
			kpi_id_format += i+1+"."+arr[i]+","+"\n";
		}
		kpi_id_format = kpi_id_format.substring(0,kpi_id_format.length-2);
		$("#kpi_id").attr("value",kpi_id_format);
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" cssClass="obj" id="obj">
<s:hidden name="obj.id" id="id"></s:hidden> 
<s:hidden name="kpiId" id="kpiId"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr style="height: 40px;">
					<td class="til" style="width: 60px;">
						服务名称
					</td>
					<td>
						<s:if test="serviceNameList!=null">
							<s:select  list="serviceNameList"  listKey="service_name" listValue="service_name" id="serviceId"></s:select>
						</s:if>
						<s:else>
							<select id="serviceId">
								<option value="">---请选择---</option>
							</select>
						</s:else>
					</td>
				</tr>
				<tr style="height: 40px;">
					<td class="til" style="width: 60px;">
							KPI名称
					</td>
					<td>
						<s:textarea id="kpi_id" rows="6" cols="55" disabled="true"></s:textarea>
						<input type="button" class="btn-style02" value="选择" name="selectKpi_id"/>
					</td>
				</tr>
				
			</table>
</s:form>
</body>