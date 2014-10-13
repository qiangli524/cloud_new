<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	var api = frameElement.api;
    var w = api.opener;

    api.button({
		id:'OkAnd',
		name: '确定',
		callback:submitRequest,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	}); 
	
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	
	function submitRequest(){
		var kpi_id = theForm.kpi_id.value;
		var weight = theForm.kpi_weight.value;
		var kpi_desc = theForm.kpi_desc.value;
		var flag = theForm.flag.value;
		if(kpi_id.length==0){
			alert("KPI名称不能为空");
			return false;
		}
		if(weight.length==0){
			alert("KPI权重不能为空!");
			return false;
		}else if(isNaN(weight)){
			alert("KPI权重必须是数字");
			return false;
		}
		if(kpi_desc.length==0){
			alert("KPI描述不能为空!");
			return false;
		} 
		if (flag != '1') {
			if(Trim(document.getElementById("span_key").innerHTML).length > 0) {
	   			alert("请重新输入KPI名称！");
	   			document.theForm.kpi_id.focus();
	   			return false;
	   		} 
   		}
		theForm.action='kpi_saveKPI.do';
	    theForm.submit();
	    w.searchRequest();
	}
	
	//校验KPI是否重复
	function checkKPI() {
		var kpiid=theForm.kpi_id.value;  
	    if (kpiid.length > 0) {
	        var url = 'kpi_checkKPI.do?kpiid='+ kpiid;
			document.getElementById("span_key").innerHTML="";
			$.getJSON(url,function(data){
				if(data.result=='1'){
					document.getElementById("span_key").innerHTML="KPI名称"+kpiid+"已生成！请请重新输入!"; 
				}
			});
	    }
	}
	

</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.kpi_id.focus()">
	<s:form action="kpi_saveKPI.do" method="post" id="theForm">
	<s:hidden id="flag" name="theForm.flag"></s:hidden>
		<div class="tit-zzi">
			<div id="zi">
				KPI信息
			</div>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						KPI名称<font color="red">*</font>
					</td>
					<td>
						<s:if test="theForm.flag==1">
							<s:property value="theForm.kpi_id"/>
							<s:hidden name="theForm.kpi_id" id="kpi_id"></s:hidden>
						<%--	<a href="#">##########</a>   --%>
						</s:if>
						<s:else>
							<s:textfield name="theForm.kpi_id" id="kpi_id" cssClass="txt" onblur="checkKPI()" />
							<span style="color:RED" id="span_key"/>    
						</s:else>
						
					</td>
				</tr>
				<tr>
					<td class="til">
						指标类型<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'0':'CPU','1':'内存','2':'存储','3':'网络','4':'其他'}" name="theForm.kpi_type" id="kpi_type"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						KPI权重<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.kpi_weight" id="kpi_weight" cssClass="txt" />
					</td>
				</tr>
				<tr>
					<td class="til">
						KPI描述<font color="red">*</font>
					</td>
					<td class="til">
						<s:textarea name="theForm.kpi_desc" id="kpi_desc" cssClass="txt" cols="77"
							rows="5"/>
					</td>
				</tr> 
			</table>
		</div>
		
	</s:form>
</body>
