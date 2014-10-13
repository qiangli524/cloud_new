<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../sxcloud/common/link.jsp"%>
<%@ include file="../sxcloud/common/taglib.jsp"%>
<%@ include file="../sxcloud/common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		<%-- var id= '<%=request.getAttribute("id")%>'; --%>
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
		     name: '返回'
		 });
		 
	//调用父页面方法，保存并刷新
	function submitRequest(){ 
		var alarm_kpi = $("#alarm_kpi").val();
		var alarm_threshold = $("#alarm_threshold").val();
		var len = alarm_threshold.length;
		var alarm_type = $("#alarm_type").val();
		var alarm_level = $("#alarm_level").val();
		var alarm_unit = $("#alarm_unit").val();
		if(alarm_kpi==""){
			$("#alarm_kpi").focus();
			return false;
		}
		if(alarm_threshold=="" || isNaN(Trim(alarm_threshold))){
			$("#alarm_threshold").focus();
			$("#span_threshold").html("请填入数字格式!");
			return false;
		}
		if(alarm_threshold.charAt(0)==" "){
			alert("请检查填入数字是否以空格开始!");
			return false;
		}
		if(alarm_threshold.charAt(len-1)==" "){
			alert("请检查填入数字是否以空格结尾!");
			return false;
		}
		if(alarm_type==""){
			$("#alarm_type").focus();
			return false;
		}
		if(alarm_level==""){
			$("#alarm_level").focus();
			return false;
		}
		if(alarm_unit==""){
			$("#alarm_unit").focus();
			return false;
		}
		w.saveAlarmThreshold($("#obj").serialize());
	}
	
	function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}
	/** 验证指标英文名字是否重复 **/
	function validateName_En(){
		var alarm_kpi = $("#alarm_kpi").val();
		var param ="obj.alarm_kpi="+alarm_kpi;
		$.ajax({
			type : "post",
			url  : 'alarmAction_validateNameEn.do',
			data : param,
			dataType : "json",
			cache:false,
			success : function(data){
				var res= data.result;
				if(res != ""){
					$("#span_kpi").html(res);
				}else{
					$("#span_kpi").html("");
				}
			}
		});	
	}
	/** 验证指标中文名称是否重复 **/
	function validateName_Zh(){
		var alarm_name = $("#alarm_name").val();
		var param ="obj.alarm_name="+alarm_name;
		$.ajax({
			type : "post",
			url  : 'alarmAction_validateNameZh.do',
			data : param,
			dataType : "json",
			cache:false,
			success : function(data){
				var res= data.result;
				if(res != ""){
					$("#span_name").html(res);
				}else{
					$("#span_name").html("");
				}
			}
		});	
	}
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" cssClass="obj" id="obj" >
			<s:hidden name="obj.id" id="id"></s:hidden> 
			<table width="100%" border="0" cellspacing="0" class="pop-table">
				<tr>
				    <td class="til">
						告警指标<font color="red">*</font>
					</td>
					<td colspan="3">
					    <s:textfield name="obj.alarm_kpi" cssClass="required" id="alarm_kpi" onblur="validateName_En()" maxlength="50"></s:textfield>
					    <span style="color:RED" id="span_kpi" />                
					</td>
				</tr>
				<tr>
				    <td class="til">
						指标名称<font color="red">*</font>
					</td>
					<td colspan="3">
					    <s:textfield name="obj.alarm_name" cssClass="required" id="alarm_name" onblur="validateName_Zh()" maxlength="50"></s:textfield>
					    <span style="color:RED" id="span_name" />                
					</td>
				</tr>
				<tr>
				    <td class="til">
						告警阀值<font color="red">*</font>
					</td>
					<td>
					    <s:textfield name="obj.alarm_threshold" cssClass="required" id="alarm_threshold" ></s:textfield>
					    <span style="color:RED" id="span_threshold"/>
					</td>
					<td width="35px;" bgcolor="#e8eff7">单位<font color="red">*</font></td>
					<td>
						<s:select list="#{'':'--请选择--','0':'%','1':'次','5':'天','2':'小时','3':'分钟','4':'秒'}" name="obj.alarm_unit" id="alarm_unit" cssClass="required" ></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						告警实体类型<font color="red">*</font>
					</td>
					<td colspan="3">
						<s:select list="#{'':'--请选择--','1':'虚拟机告警','2':'物理主机告警','6':'Hadoop告警'}" name="obj.alarm_type" id="alarm_type" cssClass="required" ></s:select>            
					</td>
				</tr>
				<tr>
				    <td class="til">
						  告警级别 <font color="red">*</font>
					</td>
					<td colspan="3"> 
                   		<s:select list="#{'':'--请选择--','0':'严重告警','1':'主要告警','2':'次要告警','3':'不确定告警'}" name="obj.alarm_level" id="alarm_level" cssClass="required"></s:select>            
					</td>
				</tr>
				<tr>
					<td class="til">告警阀值判断符号</td>
					<td colspan="3">
						<s:select list="#{'':'--请选择--','0':'小于','1':'等于','2':'大于'}" name="obj.alarm_trigger" id="alarm_trigger" ></s:select>            
					</td>
				</tr>
				<tr>
					<td class="til">不可告警时间开始值</td>
					<td colspan="3">
						<input id="start_time" type="text" name="obj.start_time"  size="23" class="Wdate" value="${obj.start_time}"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>	
					</td>
				</tr>
				<tr>
					<td class="til">不可告警时间结束值 </td>
					<td colspan="3">
						<input id="end_time" type="text" name="obj.end_time" size="23" class="Wdate" value="${obj.end_time}"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>	
					</td>
				</tr>
				<tr>
					<td class="til">告警描述</td>
					<td colspan="3"><s:textarea name="obj.alarm_desc" id="alarm_desc" rows="5" cols="30"/></td>
				</tr>
			</table>
</s:form>
</body>