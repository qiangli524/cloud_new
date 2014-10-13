<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
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
	
	function submitRequest(){
		var name = theForm.name.value;
		var type = theForm.type.value;
		var kpi_id = theForm.kpi_id.value;
		var tigger_n = theForm.tigger_n.value;
		var trigger_m = theForm.trigger_m.value;
		var interval = theForm.interval.value; 
		var operator = theForm.operator.value;
		var threshold = theForm.threshold.value;
		var min = theForm.min.value;
		var max = theForm.max.value;
		if(name.length==0 || name.replace(/(^\s*)|(\s*$)/g,"")==""){
			alert("名称不能为空!");
			return false;
		} 
		if(tigger_n.length==0 || tigger_n.replace(/(^\s*)|(\s*$)/g,"")==""){
			alert("请填写采集次数!");
			return false;
		}else if(isNaN(tigger_n)){
			alert("采集次数必须是数字!");
			return false;
		}
		if(trigger_m.length==0 || trigger_m.replace(/(^\s*)|(\s*$)/g,"")==""){
			alert("请填写告警次数!");
			return false;
		}else if(isNaN(trigger_m)){
			alert("告警次数必须是数字!");
			return false;
		}
		if (parseInt(trigger_m) > parseInt(tigger_n)) {
			alert("告警次数不得大于采集次数!");
			return false;
		}
		if(interval.length==0 || interval.replace(/(^\s*)|(\s*$)/g,"")==""){
			alert("请填写采集间隔时间!");
			return false;
		} 
		if(operator==-1){
			alert("请选择计算公式!");
			return false;
		}

		if (operator != 'in') {
			if(threshold.length==0 || threshold.replace(/(^\s*)|(\s*$)/g,"")==""){
				alert("请填写临界值!");
				return false;
			}
		} else {
			if(min.length==0 || min.replace(/(^\s*)|(\s*$)/g,"")==""){
				alert("请填写最小值!");
				return false;
			}
			
			if(max.length==0 || max.replace(/(^\s*)|(\s*$)/g,"")==""){
				alert("请填写最大值!");
				return false;
			}
		}
		theForm.action='trigger_saveTriggerDefinition.do';
	    theForm.submit();
	    w.list();
	}
	$(function(){
		$("#section").hide();
		$("[name='theForm.operator']").each(function(){
			$(this).click(function(){
				if(this.value=="in"){
					$("#section").show();
					$("#thresholdtr").hide();
					$("#threshold").attr("value","");
				}else{
					$("#section").hide();
					$("#thresholdtr").show();
					$("#min").attr("value","");
					$("#max").attr("value","");
				}
			});
		});
	});
	
	function onSelectChange() {
	   	var type = $("#type").find('option:selected').val(); 
	   	
	   	var url = "trigger_queryKpi.do?type=" + type;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			var  SelectNode = document.all.kpi_id;
     		SelectNode.length=0;
     		for(var o in data){ 
     			SelectNode.appendChild(createSelect(data[o].kpi_id,data[o].kpi_id));
      		}
		});
	 }
	 
	 function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		 
  		return opt;
	}
	
	function setDesc() {
		var name = theForm.name.value;
		var type = theForm.type.value;
		var kpi_id = theForm.kpi_id.value;
		var tigger_n = theForm.tigger_n.value;
		var trigger_m = theForm.trigger_m.value;
		var interval = theForm.interval.value; 
		var operator = theForm.operator.value;
		var threshold = theForm.threshold.value;
		if (name.length>0 && tigger_n.length>0 && trigger_m.length>0 && interval.length>0
		    && operator.length > 0 && threshold.length > 0) {
			theForm.des.value = "每" + interval + "分钟内采集一次，共采集" + tigger_n + "次，若采集指标:" 
				+ kpi_id + operator + threshold + "达到" + trigger_m + "次则触发";
		}
	}
	
	function setSectionDesc() {
		var name = theForm.name.value;
		var type = theForm.type.value;
		var kpi_id = theForm.kpi_id.value;
		var tigger_n = theForm.tigger_n.value;
		var trigger_m = theForm.trigger_m.value;
		var interval = theForm.interval.value; 
		var operator = theForm.operator.value;
		var min = theForm.min.value;
		var max = theForm.max.value;
		if (name.length>0 && tigger_n.length>0 && trigger_m.length>0 && interval.length>0
		    && operator.length > 0 && min.length > 0 && max.length > 0) {
			theForm.des.value = "每" + interval + "分钟内采集一次，共采集" + tigger_n + "次，若采集指标:" 
				+ kpi_id + "在" + min + "--" + max + "区间内则触发";
		}
	}
</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">
	<s:form action="trigger_saveTriggerDefinition.do" method="post"
		id="theForm">
	<s:hidden name="theForm.flag"></s:hidden>
	<s:hidden name="theForm.trigger_id"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						触发器名称<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.name" id="name" cssClass="required txt"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						指标<font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'0':'CPU','1':'内存','2':'存储','3':'网络','4':'其他'}"
							name="theForm.type" id="type" cssClass="required" onChange="onSelectChange()"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						KPI<font color="red">*</font>
					</td>
					<td>						
						<s:select cssClass="required" list="#request.kpiList" name="theForm.kpi_id" id="kpi_id" listKey="kpi_id" listValue="kpi_id">
						</s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						采集次数<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.tigger_n" id="tigger_n" cssClass="forInt required txt"></s:textfield>
						次
					</td>
				</tr>
				<tr>
					<td class="til">
						告警次数<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.trigger_m" id="trigger_m" cssClass="forInt required txt"></s:textfield>
						次
					</td>
				</tr>
				<tr>
					<td class="til">
						间隔时间<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.interval" id="interval" cssClass="forInt required txt"></s:textfield>
						分钟
					</td>
				</tr>
				<!-- 
				<tr>
					<td class="til">
						级别<font color="red">*</font>
					</td>
					<td>
						<s:select
							list="#{'0':'vcenter','1':'数据中心','2':'集群','3':'主机','4':'虚拟机'}"
							name="theForm.level" id="level" headerKey="-1" headerValue="--请选择--" cssClass="required"></s:select>
					</td>
				</tr>
				 -->
				<tr>
					<td class="til">
						规则计算公式<font color="red">*</font>
					</td>
					<td>
						<s:select
							list="#{'>':'大于(>)','>=':'大于等于(>=)','<':'小于(<)','<=':'小于等于(<=)','!=':'不等于(!=)','in':'区间'}"
							name="theForm.operator" headerKey="-1" headerValue="--请选择--" id="operator" cssClass="required"></s:select>
					</td>
				</tr>
				<tr id="thresholdtr">
					<td class="til">
						临界值<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.threshold" id="threshold"
							cssClass="required txt" onblur="setDesc()" ></s:textfield>
					</td>
				</tr>
				<tr id="section">
					<td class="til">
						区间
					</td>
					<td>
						<s:textfield name="theForm.min" id="min" cssClass="txt"></s:textfield>
						--
						<s:textfield name="theForm.max" id="max" cssClass="txt" onblur="setSectionDesc()" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="til">
						描述
					</td>
					<td colspan="3">
						<s:textarea name="theForm.description" id="des" cssClass="txt" cols="77"
							rows="5"></s:textarea>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
