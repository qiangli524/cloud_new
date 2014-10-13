<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
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
		var KPI_ID = $("#KPI_ID").val();
		var FLAG = $("#FLAG").val();
		var HOST_IP = $("#HOST_IP").val();
		var SUB_ENTITY = $("#SUB_ENTITY").val();
		
		var SERIOUS_ALARM_EXPRESSION = $("#SERIOUS_ALARM_EXPRESSION").val();
		var SERIOUS_ALARM_INFO_EXPRESSION = $("#SERIOUS_ALARM_INFO_EXPRESSION").val();
		var COMMON_ALARM_EXPRESSION = $("#COMMON_ALARM_EXPRESSION").val();
		var COMMON_ALARM_INFO_EXPRESSION = $("#COMMON_ALARM_INFO_EXPRESSION").val();
		
		var STATE = $("#STATE").val();
		
		var START_TIME = $("#START_TIME").val();
		var END_TIME = $("#END_TIME").val();
		
		if(KPI_ID==""){
			alert("请选择告警指标");
			$("#KPI_ID").focus();
			return false;
		}
		
		if(KPI_ID == "PM-01-01-001-02" || KPI_ID == "PM-01-01-001-03"){
			if(FLAG==""){
				alert("请选择是否配置到子实体");
				$("#FLAG").focus();
				return false;
			}
		}
		
		if(KPI_ID == "PM-01-01-001-02" && FLAG == "0"){
			if(HOST_IP==""){
				alert("请选择主机IP");
				$("#HOST_IP").focus();
				return false;
			}
			if(SUB_ENTITY==""){
				alert("请输入子实体配置值");
				$("#SUB_ENTITY").focus();
				return false;
			}
		}
		
		if(KPI_ID == "PM-01-01-001-03" && FLAG == "0"){
			if(SUB_ENTITY==""){
				alert("请输入子实体配置值");
				$("#SUB_ENTITY").focus();
				return false;
			}
		}
		
		if(SERIOUS_ALARM_EXPRESSION==""&& COMMON_ALARM_EXPRESSION==""){
			alert("严重告警和一般告警必须配置一项");
			return false;
		}
		
		if(SERIOUS_ALARM_EXPRESSION!=""&& SERIOUS_ALARM_INFO_EXPRESSION==""){
			alert("请输入严重告警信息表达式");
			$("#SERIOUS_ALARM_INFO_EXPRESSION").focus();
			return false;
		}
		
		if(COMMON_ALARM_EXPRESSION!=""&& COMMON_ALARM_INFO_EXPRESSION==""){
			alert("请输入一般告警信息表达式");
			$("#COMMON_ALARM_INFO_EXPRESSION").focus();
			return false;
		}
		
		if(SERIOUS_ALARM_EXPRESSION==""&& SERIOUS_ALARM_INFO_EXPRESSION!=""){
			alert("请输入严重告警表达式");
			$("#SERIOUS_ALARM_EXPRESSION").focus();
			return false;
		}
		
		if(COMMON_ALARM_EXPRESSION==""&& COMMON_ALARM_INFO_EXPRESSION!=""){
			alert("请输入一般告警表达式");
			$("#COMMON_ALARM_EXPRESSION").focus();
			return false;
		}
		
		if(STATE==""){
			alert("请选择是否启用");
			$("#STATE").focus();
			return false;
		}
		
		if(START_TIME != ""){
			if(END_TIME == ""){
				alert("不告警结束时间不能为空");
				return false;
			}
		}
		if(END_TIME != ""){
			if(START_TIME == ""){
				alert("不告警开始时间不能为空");
				return false;
			}
		}
		
		var id = $("#id").val();
		w.saveAlarmThreshold($("#obj").serialize(),id);
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
	
	function flagChange() {
		var flag = $("#FLAG").val();
		var kpiId = $("#KPI_ID").val();
		if(kpiId == ""){
			$("#KPI_ID").focus();
			return;
		}
		if(flag == "0"){
			if(kpiId == "PM-01-01-001-02"){
				$("#SUB_ENTITY_DIV").show();
				$("#HOST_IP_DIV").show();
				subEntityClear();
			}else if(kpiId == "PM-01-01-001-03"){
				$("#SUB_ENTITY_DIV").show();
				$("#HOST_IP_DIV").hide();
				$("#HOST_IP").val("");
				queryEntityErrorLog();
			}else if(kpiId == "PM-01-01-001-01"){
				$("#SUB_ENTITY_DIV").show();
				$("#HOST_IP_DIV").hide();
				$("#HOST_IP").val("");
				queryEntityCall();
			}else{
				$("#SUB_ENTITY_DIV").hide();
				$("#HOST_IP_DIV").hide();
				$("#SUB_ENTITY").val("");
				$("#HOST_IP").val("");
			}
		}else if(flag == "1"){
			$("#SUB_ENTITY_DIV").hide();
			$("#HOST_IP_DIV").hide();
			$("#SUB_ENTITY").val("");
			$("#HOST_IP").val("");
		}else{
			$("#SUB_ENTITY_DIV").hide();
			$("#HOST_IP_DIV").hide();
			$("#SUB_ENTITY").val("");
			$("#HOST_IP").val("");
		}
	}
	
	function kpiIdChange() {
		var kpiId = $("#KPI_ID").val();
		
		if(kpiId == "PM-01-01-001-01"){
			$("#shouRemindInfo").html("<font color='red'>提示：<br>可用变量：<br>in_count(入口流量)、out_count(出口流量)、error_count(错单量)、no_master_count(无主量)、<br>before_in_count(前一条采集信息入口流量)、before_out_count(前一条采集信息出口流量)、<br>before_error_count(前一条采集信息错单量)、before_no_master_count(前一条采集信息无主量)<br>判断表达式示例：{in_count}==0&&{out_count==0}<br>信息表达式示例：入口流量{in_count}和出口流量{out_count}为0，告警</font>");
		}else if(kpiId == "PM-01-01-001-02"){
			$("#shouRemindInfo").html("<font color='red'>提示：<br>可用变量：<br>in_count(目录积压量)、before_in_count(前一条采集信息目录积压量)<br>判断表达式示例：{in_count}>3<br>信息表达式示例：文件目录积压量{in_count}大于3,告警</font>");
		}else if(kpiId == "PM-01-01-001-03"){
			$("#shouRemindInfo").html("<font color='red'>提示：<br>可用变量：<br>in_count(错误日志数量)、before_in_count(前一条采集信息错误日志数量)<br>判断表达式示例：{in_count}>0<br>信息表达式示例：错误日志数量{in_count}大于0,告警</font>");
		}else if(kpiId == "PM-01-01-001-04"){
			$("#shouRemindInfo").html("<font color='red'>提示：<br>可用变量：<br>in_count(入口业务量)、out_count(出口业务量)、error_count(查询失败量)、<br>before_in_count(前一条采集信息入口业务量)、before_out_count(前一条采集信息出口业务量)、<br>before_error_count(前一条采集信息查询失败量)<br>判断表达式示例：{in_count}==0&&{out_count}==0<br>信息表达式示例：入口业务量{in_count}和出口业务量{out_count}为0，告警</font>");
		}else if(kpiId == "PM-01-01-001-05"){
			$("#shouRemindInfo").html("<font color='red'>提示：<br>可用变量：<br>in_count(入口业务量)、out_count(出口业务量)、before_in_count(前一条采集信息入口业务量)、<br>before_out_count(前一条采集信息出口业务量)<br>判断表达式示例：{in_count}==0&&{out_count==0}<br>信息表达式示例：入口业务量{in_count}和出口业务量{out_count}为0，告警</font>");
		}else if(kpiId == "PM-01-01-001-06"){
			$("#shouRemindInfo").html("<font color='red'>提示：<br>可用变量：<br>in_count(提醒量)、before_in_count(前一条采集信息提醒量)<br>判断表达式示例：{in_count}>0<br>信息表达式示例：提醒量{in_count}大于0,告警</font>");
		}else{
			$("#shouRemindInfo").html("")
		}
		
		if(kpiId == "PM-01-01-001-02" || kpiId == "PM-01-01-001-03" || kpiId == "PM-01-01-001-01"){
			$("#FLAG_DIV").show();
			$("#HOST_IP_DIV").hide();
			$("#SUB_ENTITY_DIV").hide();
			$("#FLAG").val("");
			$("#HOST_IP").val("");
			$("#SUB_ENTITY").val("");
			
		}else{
			$("#FLAG_DIV").hide();
			$("#HOST_IP_DIV").hide();
			$("#SUB_ENTITY_DIV").hide();
			$("#FLAG").val("");
			$("#HOST_IP").val("");
			$("#SUB_ENTITY").val("");
		}
	}
	
	$(function(){
    	var api = frameElement.api;
		w = api.opener;
      $("[type='selectHost']").unbind().live("click",function(){
	  		w.$.dialog({
    			id:'addHost',
    			title:'选择主机',
    			width: '1000px',
    			height: '500px',
    		    lock:true,
    			content: 'url:monitorCfg_queryHostList.do?flag=1'
	    		});
		 });
		 
		
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
		
	});
	
	 function addHost(eq_ip,sn,eq_name){
		 $("#HOST_IP").attr("value",eq_ip + "_" + sn);
		 queryPathByIp(eq_ip);
	 }
	 
	 function addVmHost(connect_id,vh_uuid,vh_ip,vm_name){
		 $("#HOST_IP").attr("value",connect_id + "_" + vh_uuid);
		 queryPathByIp(vh_ip);
	 }
	 
	 
	 function queryPathByIp(ip){
		 if(Trim(ip)!= ''){
			 var url = "bossBusiMonAlarmAction_queryPathByIp.do?hostIp=" + ip;
			 $.ajax({
				  type:"GET",
	              url:url,
	              data:"text",
	              async: false,
	              cache: false,
		          success: function(data){
					if(data!=null && data.length>0){
						var SUB_ENTITY=document.getElementById("SUB_ENTITY");
						SUB_ENTITY.options.length=0;
						for(i=0;i<data.length;i++){
							SUB_ENTITY.add(new Option(data[i].kpiCfgValue,data[i].kpiCfgValue));
							}
				}
			}
			});
			 }else{
					subEntityClear();
				 }
		
	}
	
	function subEntityClear(){
		var SUB_ENTITY=document.getElementById("SUB_ENTITY");
		SUB_ENTITY.options.length=0; 
		SUB_ENTITY.add(new Option('',''));
	}
	
	function queryEntityCall(){
		var ENTITY=document.getElementById("SUB_ENTITY");
		ENTITY.options.length=0;
		ENTITY.add(new Option('TOFEDX','TOFEDX'));
		ENTITY.add(new Option('NCT','NCT'));
		ENTITY.add(new Option('RF','RF'));
	}
	
	function queryEntityErrorLog(){
		var ENTITY=document.getElementById("SUB_ENTITY");
		ENTITY.options.length=0;
		ENTITY.add(new Option('业务处理失败','ABM_001'));
		ENTITY.add(new Option('获取用户信息失败','AM_001'));
		ENTITY.add(new Option('从session池获取session_id错误','CF_006'));
	}
	
	 
	 function expressionSeriousCheck(){
	 	var obj = $("#obj").serialize();
	 	$.ajax({
            type: "GET",
            url: "bossBusiMonAlarmAction_expressionSeriousCheck.do?"+obj,
            dataType: "text",
			cache:false,
            success : function(data){
				var flag = data;
				if(flag == 0 || flag == 1){
					alert("严重报警表达式正确");
				}else if(flag == 2){
					alert("严重报警表达式不正确，请检查");
				}else if(flag == 3){
					alert("严重报警表达式为空，无法检查");
				}
            }
          });
	 }
	 
	 function expressionCommonCheck(){
	 	var obj = $("#obj").serialize();
	 	$.ajax({
            type: "GET",
            url: "bossBusiMonAlarmAction_expressionCommonCheck.do?"+obj,
            dataType: "text",
			cache:false,
            success : function(data){
				var flag = data;
				if(flag == 0 || flag == 1){
					alert("一般报警表达式正确");
				}else if(flag == 2){
					alert("一般报警表达式不正确，请检查");
				}else if(flag == 3){
					alert("一般报警表达式为空，无法检查");
				}
            }
          });
	 }
	 
	 $(document).ready(function(){
	 	var kpiId = $("#KPI_ID").val();
	 	var FLAG = $("#FLAG").val();
	 	var SUB_ENTITY_HIDDEN = $("#SUB_ENTITY_HIDDEN").val();
	 	
	 	if(kpiId == "PM-01-01-001-01"){
			$("#FLAG_DIV").show();
			if(FLAG == "0"){
				$("#SUB_ENTITY_DIV").show();
				queryEntityCall();
				$("#SUB_ENTITY").val(SUB_ENTITY_HIDDEN);
			}else{
				$("#SUB_ENTITY_DIV").hide();
			}
			
			//$("#FLAG").val("");
			//$("#HOST_IP").val("");
			//$("#SUB_ENTITY").val("");
			
		}else if(kpiId == "PM-01-01-001-02"){
			$("#FLAG_DIV").show();
			if(FLAG == "0"){
				$("#HOST_IP_DIV").show();
				$("#SUB_ENTITY_DIV").show();
				
				var hostIp = SUB_ENTITY_HIDDEN.substr(0,SUB_ENTITY_HIDDEN.indexOf("/")-1);
				$("#HOST_IP").val(hostIp);
				var queryIp = SUB_ENTITY_HIDDEN.substr(0,SUB_ENTITY_HIDDEN.indexOf("_"));
				queryPathByIp(queryIp)
				
				
			}else{
				$("#HOST_IP_DIV").hide();
				$("#SUB_ENTITY_DIV").hide();
			}
		}else if(kpiId == "PM-01-01-001-03"){
			$("#FLAG_DIV").show();
			if(FLAG == "0"){
				$("#SUB_ENTITY_DIV").show();
				queryEntityErrorLog();
				$("#SUB_ENTITY").val(SUB_ENTITY_HIDDEN);
			}else{
				$("#SUB_ENTITY_DIV").hide();
			}
		}
	}); 
	 
	 
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" cssClass="obj" id="obj" >
			<s:hidden name="tbBossBusiMonAlarmCfg.ID" id="id"></s:hidden> 
			<table width="100%" border="0" cellspacing="0" class="pop-table">
				<tr>
				    <td class="til">
						告警指标<font color="red">*</font>
					</td>
					<td colspan="3">
                   		<s:select list="#{'':'--请选择--',
                   						'PM-01-01-001-01':'话务量监控指标',
                   						'PM-01-01-001-02':'目录文件积压量指标',
                   						'PM-01-01-001-03':'错误日志监控指标',
                   						'PM-01-01-001-04':'流量查询服务监控指标',
                   						'PM-01-01-001-05':'端口收发监控指标',
                   						'PM-01-01-001-06':'提醒服务指标'
                   						}"
                   		 name="tbBossBusiMonAlarmCfg.KPI_ID" id="KPI_ID" 
                   		 cssClass="required" onchange="kpiIdChange()"></s:select>            
					</td>
				</tr>
				<tr id="FLAG_DIV" style="display:none;">
					<td class="til">
						是否配置到子实体<font color="red">*</font>
					</td>
					<td colspan="3">
						<s:select list="#{'':'--请选择--','0':'是','1':'否'}" name="tbBossBusiMonAlarmCfg.FLAG" id="FLAG" cssClass="required" onchange="flagChange()"></s:select>            
					</td>
				</tr>
				<tr id="HOST_IP_DIV" style="display:none;">
				    <td class="til">
						  主机IP<font color="red">*</font>
					</td>
					<td colspan="3"> 
						<s:textfield id="HOST_IP" name="tbBossBusiMonAlarmCfg.HOST_IP" value="" cssClass="required" readonly="true"></s:textfield>
						<a href="javascript:;" type="selectHost" id="start" style="color: blue;text-decoration: underline;">选择</a>
					</td>
				</tr>
				<tr id="SUB_ENTITY_DIV" style="display:none;">
				    <td class="til">
						  子实体配置值<font color="red">*</font>
					</td>
					<td colspan="3"> 
					<!--  
						<s:textfield id="SUB_ENTITY" name="tbBossBusiMonAlarmCfg.SUB_ENTITY" value="" cssClass="required"></s:textfield>
						-->
						<input id="SUB_ENTITY_HIDDEN" name="SUB_ENTITY_HIDDEN" value="<s:property value='tbBossBusiMonAlarmCfg.SUB_ENTITY'/>" type="hidden">
						<s:select list="#{'':'--请选择--'}" name="tbBossBusiMonAlarmCfg.SUB_ENTITY" id="SUB_ENTITY"></s:select> 
					</td>
				</tr>
				<tr>
					<td class="til">
					</td>
					<td colspan="3" id="shouRemindInfo">
					</td>
				</tr>
				<tr>
					<td class="til">
						严重告警判断表达式<font color="red">*</font>
					</td>
					<td colspan="3">
						<s:textarea name="tbBossBusiMonAlarmCfg.SERIOUS_ALARM_EXPRESSION" id="SERIOUS_ALARM_EXPRESSION" rows="2" cols="50" cssClass="required"/>
						<a href="javascript:;" onclick="expressionSeriousCheck()" style="color: blue;text-decoration: underline;">校验</a>
					</td>
				</tr>
				<tr>
					<td class="til">
						严重告警信息表达式<font color="red">*</font>
					</td>
					<td colspan="3"><s:textarea name="tbBossBusiMonAlarmCfg.SERIOUS_ALARM_INFO_EXPRESSION" id="SERIOUS_ALARM_INFO_EXPRESSION" rows="2" cols="50" cssClass="required"/></td>
				</tr>
				<tr>
					<td class="til">
						一般告警判断表达式<font color="red">*</font>
					</td>
					<td colspan="3">
						<s:textarea name="tbBossBusiMonAlarmCfg.COMMON_ALARM_EXPRESSION" id="COMMON_ALARM_EXPRESSION" rows="2" cols="50" cssClass="required"/>
						<a href="javascript:;" onclick="expressionCommonCheck()" style="color: blue;text-decoration: underline;">校验</a>
					</td>
				</tr>
				<tr>
					<td class="til">
						一般告警信息表达式<font color="red">*</font>
					</td>
					<td colspan="3"><s:textarea name="tbBossBusiMonAlarmCfg.COMMON_ALARM_INFO_EXPRESSION" id="COMMON_ALARM_INFO_EXPRESSION" rows="2" cols="50" cssClass="required"/></td>
				</tr>
				<tr>
					<td class="til">不告警开始时间</td>
					<td colspan="3">
						<input id="START_TIME" type="text" name="tbBossBusiMonAlarmCfg.START_TIME"  size="23" class="Wdate" value="${tbBossBusiMonAlarmCfg.START_TIME}"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>	
					</td>
				</tr>
				<tr>
					<td class="til">不告警结束时间</td>
					<td colspan="3">
						<input id="END_TIME" type="text" name="tbBossBusiMonAlarmCfg.END_TIME" size="23" class="Wdate" value="${tbBossBusiMonAlarmCfg.END_TIME}"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>	
					</td>
				</tr>
				<tr>
					<td class="til">
						是否启用<font color="red">*</font>
					</td>
					<td colspan="3">
						<s:select list="#{'':'--请选择--','0':'启用','1':'停用'}" name="tbBossBusiMonAlarmCfg.STATE" id="STATE" cssClass="required" ></s:select>            
					</td>
				</tr>
				
			</table>
</s:form>
</body>