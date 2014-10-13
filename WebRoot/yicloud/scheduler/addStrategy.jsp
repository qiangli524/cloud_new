<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/ui16rc5.packed.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/portal.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
	<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
	});
	var sign = 0;//是否选择触发条件的标记，从子窗口获得 
	var trigger_id = "";
	function submitRequest(){ 
		var excute = document.getElementById("excute").value;
/* 		var HOST_ID = document.getElementById("HOST_ID").value;
 */		var name = theForm.name.value;
		var datepicker1 = $("#effect_time").val();
		var excute = theForm.excute.value;
		var temp_id = $("#temp_id").attr("value");
		var vh_num =  $("#vh_num").val(); 
		var vh_cpu =  $("#cpu").val(); 
		var vh_mem =  $("#mem").val(); 
		var entity_name  = $("#entityName").val();
		var vh_storage =  $("#storage").val();
		var network = $("#nId").val();
		var monitor_name = $("#monitor_name").val();
		if(name.length==0 || name.replace(/(^\s*)|(\s*$)/g,"")==""){
			alert("策略名称不能为空");
			return false;
		}
		if(sign==0){
			alert("请选择策略条件!");
			return false;
		}
		if(datepicker1.length==0){
			alert("请选择有效时间!");
			return false;
		}
		if(excute==-1){
			alert("请选择执行动作!");
			return false;
		}
		if(monitor_name == null || monitor_name == ""){
			alert("请选择监控对象");
			return false;
		}
	    if(entity_name == null || entity_name=="" ){
			alert("请选择实体对象!");
			return false;
		}
		var reg_1 = /^[1-9][0-9]*$/;
		var reg_2 = /^[0-9]*\.[0-9]*$/;
		if(excute=="0"){ 
			if(temp_id==-1){
				alert("请选择模板!");
				return false;
			}
		
			if($.trim(vh_cpu) == ""){
				alert("CPU不能为空");
				return false;
			}else{
				if(vh_cpu.search(reg_1) == -1){
				alert("CPU个数必须为大于0的数字");
				return false;
				}
			}
			if($.trim(vh_mem) == ""){
				alert("内存值不能为空");
				return false;
			}else{
				if(vh_mem.search(reg_1) == -1){
				alert("内存必须为大于0的数字");
				return false;
				}
			}
			if($.trim(vh_storage) == ""){
				alert("存储值不能为空");
				return false;
			} else{
				if(vh_storage.search(reg_2) == -1){
				alert("存储值必须为大于0的小数,如：12.0");
				return false;
				}
			}
			if($.trim(vh_num) == ""){
				alert("虚拟机个数不能为空");
				return false;
			}else if(network ==""){
				alert("请选择网络域");
				return false;
			} else{
				if(vh_num.search(reg_1) == -1){
				alert("虚拟机个数必须为大于0的数字");
				return false;
				}
			}
		}
		var name = $("#name").val();
		var url = 'strategy_saveStrategy.do?trigger_id='+trigger_id+'&vh_cpu='+vh_cpu+'&vh_mem='+vh_mem+'&vh_num='+vh_num+'&vh_storage='+vh_storage+'&entity_name='+encodeURI(encodeURI(entity_name)) +'&'+$("#theForm").serialize()+"&name="+encodeURI(encodeURI(name));
		w.saveStrategy(url);
	}
	
	
	function queryCondition(){
		window.open("strategy_queryCondition.do","chooseConditon","height=500,width=450");
	}
	
	$(function(){
	 $("[name='queryCondition']").unbind().live("click",function(){
        	currentEdit=$(this);
        	var aFlag = '<s:property value="flag"/>';
        	var sid = $("#strategy_id").val();
    		w.$.dialog({
    			id:'queryCondition',
    			title:'选择策略条件',
    			width: '800px',
    			height: '450px',
    			lock:true,
    			content: 'url:strategy_queryCondition.do?sid='+sid+'&flag='+aFlag
    			});
              });
      $("#network").unbind().live("click",function(){
      		var aFlag = '<s:property value="flag"/>';
        	currentEdit=$(this);
        	var sid = $("#nId").val();
    		w.$.dialog({
    			id:'queryNetWork',
    			title:'选择网络域',
    			width: '800px',
    			height: '450px',
    			lock:true,
    			content: 'url:strategy_queryNetWork.do?netWorkId='+sid+'&flag='+aFlag
    			});
              });
       $("#chooseHost").unbind().live("click",function(){
      		var aFlag = '<s:property value="flag"/>';
        	currentEdit=$(this);
        	var connectId = $("#connectId").attr("value");
        	var sid = $("#entity_id").val();
        	var hostId = $("#host_id").val();
        	if(sid==null||sid==''){
        		alert("请先选择需要迁移的虚拟机");
        		return false;
        	}
    		w.$.dialog({
    			id:'queryHost',
    			title:'选择主机',
    			width: '800px',
    			height: '450px',
    			lock:true,
    			content: 'url:strategy_queryHost.do?entityId='+sid+'&flag='+aFlag+'&connectId='+connectId+"&hostId="+hostId
    			});
              });       
	});
	
	$(function(){
		var flag = <%=request.getAttribute("flag")%>;
		 
		if(flag==1){
			//对象展现
		    $(".entity_id").show();
			var type = <%=request.getAttribute("type")%>;
			var strateay_level = <%=request.getAttribute("strateay_level")%>;
			
			//实体ID 
			var entityId = '<%=request.getAttribute("entityId")%>';
			//实体名称 
			var enName='<%=request.getAttribute("entityName")%>';  
			var content = '<%=request.getAttribute("content")%>';
			//策略动作
			var execute = '<%=request.getAttribute("execute")%>';
			var migtype = '<%=request.getAttribute("migtype")%>';
			$('input[name="theForm.type"]').get(type).checked = true; 
			
			//实体对象非虚拟机
			if (strateay_level != '4') { 
			 	 $(".templet").show(); 	  
  				 $(".strateay_level").show();
  				 $('input[name="theForm.strateay_level"]').get(strateay_level-1).checked = true; 
			}
			//创建、调整虚拟机
			if (execute == '0' || execute == '1') {
				 $(".templetInfo").show();
				 $(".network").show();
				 if(execute == '1'){
					 $(".network").hide();
				 }
				 if (execute == '1') {
					$("#vhNum").hide();
				 }
				 if (execute == '0' && strateay_level != '3') {
				   $(".hostInfo").hide();
				 }
				 if (migtype == 1) {
				 	 $("#hosttarget").hide();  
				 } 
			}
			
			//迁移虚拟机
			if (execute == '3') {
				 $(".hostInfo").show();
				 if (migtype == 1) {
				 	 $("#hosttarget").hide();  
				 }
			}
			
			/* $("#enId").show(); */
			$("#enId").empty();
 			$("#enId").append("<td class='til' >已选择</td>");
 			$("#enId").append("<td id='entity' name='theForm.entity_name'></td>");
	 		$("#entity").append(enName);
			
			$("#tr").empty();
			$("#tr").append("<td class='til' >已选择</td>");
			$("#tr").append("<td id='td'></td>");
			$("#td").append(content + ";");
			sign = 1;//已选择策略条件
			
			var netWorkName = $("#netName").val();
			$(".net").show();
			$("#net").empty();
			$("#net").append("网络域:"+netWorkName);
		}
	});
	
	$(function(){
		$(":radio[name='theForm.strateay_level']").each(function(){
			$(this).click(function(){
				if(this.checked){ 
				    if (this.value != "3") {   //3代表级别为主机
					    $(".hostInfo").hide();  //待修改
				    } else {
				    	$(".hostInfo").hide(); 
				    }			
					//queryAllEntities(this.value);
				}
			});
		});
		$(":radio[name='theForm.migtype']").each(function(){
			$(this).click(function(){
				if(this.checked){  
					if(this.value==1){ 
						/* $("#HOST_ID").attr("value",""); */
						$("#hosttarget").hide();
					}else{ 
						$("#hosttarget").show();
					}
				}
			});
		});
	});
	
	$(function(){
			var flag = $("#flag").val();
			$("[name='selectObj_id']").unbind().live("click",function(){
				var monitorObj = $("#monitorObj").val();
				if(monitorObj == '0'){
					w.$.dialog({
		    			id:'addtem',
		    			title:'选择虚拟机',
		    			width: '800px',
		    			height: '500px',
		    		    lock:true,
		    			content: 'url:strategy_queryVmHost.do?flag='+flag
			    		});
				}else if(monitorObj == '1'){
					w.$.dialog({
		    			id:'addtem',
		    			title:'选择主机',
		    			width: '750px',
		    			height: '470px',
		    		    lock:true,
		    			content: 'url:strategy_queryVmHost.do?flag='+flag
			    		});
					}
			 });
		
		
	});
	
	function listVMInfo(vm_id,vm_name,vmCode,connectId){
		$("#monitor_id").attr("value",vmCode);
		$("#monitor_name").attr("value",vm_name);
	}
	
	
	
	function queryAllEntities(){
			var aFlag = '<s:property value="flag"/>';
			var eId = $("#entity_id").val();
			var type = '';
			$(":radio[name='theForm.strateay_level']").each(function(){
						if(this.checked){ 
							type = this.value;
						}
				});
			var excute = document.getElementById("excute").value;
			if(excute != '0'){
				type = 4;
			}
			w.$.dialog({
    			id:'queryCondition',
    			title:'选择实体对象',
    			width: '800px',
    			height: '500px',
    			content: 'url:strategy_queryAllEntity.do?type=' + type +'&entityId='+eId+'&flag='+aFlag+'&etype='+excute
    			});
	}
	function showEitity(entityId,entityName,connectId){
		var type = '';
		$("#connectId").attr("value",connectId);
		$(":radio[name='theForm.strateay_level']").each(function(){
			if(this.checked){ 
				type = this.value;
			}
		});
		if(entityId != null){
			$("#entity_id").attr("value",entityId);
		}
		$("#entityName").attr("value",entityName);
		$("#enId").empty();
 		$("#enId").append("<td class='til' >已选择</td>");
 		$("#enId").append("<td id='entity' name='theForm.entity_name'></td>");
 		/* if(type =='3'){
		 	$("#entity").append("主机:"+entityName);
 		}else{
 			$("#entity").append("虚拟机:"+entityName);
 		} */
 		$("#entity").append("实体对象:"+entityName);
 		getTemplate();
	}
	
	var check;
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    		opt.selected=true;
  		}
  		return opt;
	}
	
	$(document).ready(function(){
		var flag = <%=request.getAttribute("flag")%>;
			if(flag!=1){
			/* 	queryAllEntities(3);//默认加载虚拟机列表 */
		}
	});
	
	function getTrigger(trigger_ids,sig){
 		sign=1;
 		trigger_id = trigger_ids;
 		$.post("strategy_choosedTrigger.do",{trigger_ids:trigger_ids},
 			function(data){
    			$("#tr").empty();
 				$("#tr").append("<td class='til' >已选择</td>");
 				$("#tr").append("<td id='td'></td>");
 				for(var o in data){
	 				$("#td").append(data[o].content + ";");
 				}
 		 },"json");
 	} 
 	
	function getSelect(){
		var TEM_ID = $("#temp_id").val(); 
		if(TEM_ID == "-1"){
			$(".templetInfo").hide();
    	  	return false;
    	}
    	$(".templetInfo").show();
    	var url = "strategy_getTemplateInfo.do?temp_id=" + TEM_ID;
    	$("#vh_num").attr("value","");
    	$("#cpu").attr("value","");
    	$("#mem").attr("value","");
    	$("#storage").attr("value","");
    	
    	$.getJSON(url,{'time': new Date().toString()},function(data){
    		$("#vh_num").val(data[0].vmnum);
    		$("#cpu").val(data[0].cpu);
    		$("#mem").val(data[0].mem);
    		$("#storage").val(data[0].store);
    	});
	}
	
	//设置显示与隐藏的列表 
	function getExcute(){
		var excute = document.getElementById("excute").value;
		var TEM_ID = document.getElementById("temp_id").value;
		
		if(excute == "3"){//迁移虚拟机
			$(".templetInfo").hide();
			$(".templet").hide();
			$(".entity_id").show();
			$(".strateay_level").hide();
			$(".hostInfo").show(); 
			//queryAllEntities(4);
    	  	return false;
    	}
    	if(excute == "2"){//删除虚拟机
    		$(".templetInfo").hide();
			$(".templet").hide();
			$(".entity_id").show();
			$(".strateay_level").hide();
			$(".hostInfo").hide();
			//queryAllEntities(4);
    	}
    	if(excute == "1"){//调整虚拟机
    		$(".templetInfo").show();
			$(".templet").hide();
			$(".entity_id").show();
			$("#vhNum").hide();
			$(".strateay_level").hide();
			$(".hostInfo").hide();
			//queryAllEntities(4);
    	}
    	if(excute == "0"){//创建虚拟机
    		
    		$(".strateay_level").show();
    		$(".entity_id").show();
    		$(".network").show();
    		//queryAllEntities(3);
    		if(TEM_ID=="-1")
    		{	
	    		$(".templetInfo").hide();
	    		$(".templet").show();
	      		return false;
    		}
    		else{
    			$(".templetInfo").show();
				$(".templet").show(); 
				return false;
			}
			
    	}
	}
	
	//根据所选实体的虚拟化类型查询获取虚拟机模板
	function getTemplate() {  
		var eid = theForm.entity_id.value; 
		var url = "strategy_queryTemplate.do?eid=" + eid;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			var SelectNode = document.all.temp_id;
	     	SelectNode.length=0;
	     	SelectNode.appendChild(createSelect("-1","--请选择--"));
		    for(var o in data){ 
		     	SelectNode.appendChild(createSelect(data[o].id,data[o].name)); 
		     }
		}); 
	}
	//选择网络域
	function showNetWork(netWorkId,netWorkName){
		$("#nId").attr("value",netWorkId);
		$(".net").show();
		$("#net").empty();
		$("#net").append("网络域:"+netWorkName);
	}
	function showHost(hostId,hostName){
		$("#host_id").attr("value",hostId);
		$("#host").empty();
		$(".host").show();
		$("#host").append("迁移主机:"+hostName);
	}
	function adjustStrategyName(){
		var flag = '<s:property value="flag"/>';
		if(flag == 1){
			if(!confirm("请确定更新策略名称！")){
				return false;
			}
		}
		 mask('正在校验策略名称是否重复，请稍等....','0.5','0px');
		var name = $("#name").attr("value");
		$.ajax({
			 type: "post",
			 url: "strategy_adjustStrategy.do",
			 data: "name="+name,
			 dataType: "json",
			 success : function(data){
			 	removeMask();
				if(data.flag == 0){
					alert("策略名称已存在或者为空，请重新填写！");
					var name = $("#name").attr("value","");
				}
			 }
		});
	}
</script>
</head>
<body class="pop-body scrollbody" onload="self.focus();">
	<s:form action="strategy_saveStrategy.do" method="post" id="theForm">
	<s:hidden id="trigger_ids" name="theForm.trigger_ids"></s:hidden>
	<s:hidden  id="strategy_id" name="theForm.strategy_id"></s:hidden>
	<s:hidden id="entity_id" name="theForm.entity_id"/>
	<s:hidden id="entityName" name="theForm.entity_name"/>
	<s:hidden name="theForm.flag" id="flag"></s:hidden>   
	<s:hidden id="connectId" name="theForm.connect_id"/>
	<s:hidden id="nId" name="theForm.netWork_id"></s:hidden>
	<s:hidden id="netName" name="theForm.netWork_name"></s:hidden>
	<s:hidden id="ajustFlag" name="flag"></s:hidden>
	<s:hidden id="host_id" name="theForm.host_id"></s:hidden>
		<div class="tit-zzi">
			<div id="zi">
				策略基本信息
			</div>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						策略名称<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.strategy_name" id="name" cssClass="required txt"  onblur="adjustStrategyName()"/>
					</td>
				</tr>
				<tr>
					<td class="til">
						策略类型
					</td>
					<td>
						<s:radio list="#{'0':'通用','1':'专用'}" name="theForm.type" value="0"></s:radio>
					</td>
				</tr>
				<tr>
					<td class="til">
						描述：
					</td>
					<td colspan="3">
						<s:textarea name="theForm.strategy_desc" id="strategy_desc" cssClass="txt" cols="77"
							rows="5"></s:textarea>
					</td>
				</tr>

			</table>
		</div>
		<div class="tit-zzi">
			<div id="zi">
				策略详细信息
			</div>
		</div>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					执行动作<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'0':'创建虚拟机','1':'调整虚拟机','2':'删除虚拟机','3':'迁移虚拟机'}" headerKey="-1" headerValue="--请选择执行动作--" name="theForm.excute" id="excute" onchange="getExcute()"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					监控对象<font color="red">*</font>
				</td>
				<td>
					<s:select list="#{'0':'虚拟机','1':'主机'}" name="monitorObj" id="monitorObj"></s:select>
					<s:hidden name="theForm.monitor_id" id="monitor_id"></s:hidden>
					<s:textfield name="theForm.monitor_name" id="monitor_name" disabled="true"></s:textfield>
					<input type="button" class="btn-style02" value="选择" name="selectObj_id"/>
				</td>
			</tr>
			<tr class="strateay_level">
				<td class="til">
					目标级别<font color="red">*</font>
				</td>	
				<td>
					<s:radio list="#{'1':'数据中心','2':'集群','3':'主机'}" name="theForm.strateay_level" id="strateay_level" value="3" ></s:radio>
				</td>
			</tr>
			<tr class="entity_id">
				<td class="til">
					目标对象<font color="red">*</font>
				</td>
				<td>
					<a href="javascript:queryAllEntities();" >选择实体对象</a>
				</td>
			</tr>
			<tr id="enId">
			</tr>
			<tr>
				<td class="til">
					触发条件<font color="red">*</font>
				</td>
				<td>
					<a href="javascript:;" name="queryCondition" style="text-decoration:underline;">选择策略条件</a>
				</td>
			</tr> 
			<tr id="tr">
				
			</tr>
			<tr>
				<td class="til">
					有效时间<font color="red">*</font>
				</td>
				<td>
					<input id="effect_time" style="txt" type="text" name="theForm.effect_date" size="20"  class="Wdate"
			   		onFocus="WdatePicker({minDate:'new Date()',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			   		value="${theForm.effect_date }"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					是否有效
				</td>
				<td>
					<s:select list="#{'1':'有效','0':'无效'}" name="theForm.enable"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					执行类型
				</td>
				<td>
					<s:select list="#{'1':'自动执行','0':'手动执行'}" name="theForm.excute_type"></s:select>
				</td>
			</tr> 
			<tr class="network" style="display:none">
				<td class="til">
					NetWork<font color="red">*</font>
				</td>
				<td>
					<a id="network" href="javascript:void(0);">选择网络域</a>
				</td>
			</tr>
			<tr class="net" style="display:none">
				<td class="til">
					已选择
				</td>
				<td id="net">
				</td>
			</tr>
			<tr class="templet" style="display:none">
				<td class="til">
					选择模板<font color="red">*</font>
				</td>
				<td>
					<s:select list="theForm.templateList" headerKey="-1"
						headerValue="--选择模板--" name="theForm.temp_id" id="temp_id" listKey="templateCode" listValue="name"  onchange="getSelect()"></s:select>
				</td>
			</tr>
			<!-- 开始设值 -->
			<tr class="templetInfo" id="vhNum" style="display:none">
					<td class="til">
						虚拟机个数
						<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="theForm.vh_num"  id= "vh_num" cssClass="forInt txt"></s:textfield>
					</td>
				</tr>
			<tr class="templetInfo" style="display:none">
				<td class="til">
					CPU
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield cssClass="txt" id= "cpu" name="theForm.cpu"></s:textfield>
				</td>
			</tr>
			<tr class="templetInfo" style="display:none">
				<td class="til">
					内存
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield cssClass="txt" id= "mem" name="theForm.mem"></s:textfield>&nbsp;&nbsp;MB
				</td>
			</tr>
			<tr class="templetInfo" style="display:none">
				<td class="til">
					存储
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield cssClass="txt" id= "storage" name="theForm.storage"></s:textfield>&nbsp;&nbsp;MB
				</td>
			</tr>
			<tr class="hostInfo" id="ifHost" style="display:none">
				<td class="til">
					是否指定主机
				</td>
				<td>
					<s:radio list="#{'0':'是','1':'否'}" name="theForm.migtype" value="0" ></s:radio>
				</td>
			</tr>
			<tr class="hostInfo" id="hosttarget" style="display:none">
				<td class="til">
					选择主机
				</td>
				<td>	
					<%-- <s:select headerKey="" headerValue="-请选择-" list="theForm.hostList" 
						name="theForm.host_id" listKey="uuid" listValue="name" id="HOST_ID"></s:select> --%>
					<a id="chooseHost" href="javascript:void(0);">选择主机</a>
				</td>
			</tr>
			<tr class="host" style="display:none">
				<td class="til">
					已选择
				</td>
				<td id="host">
				</td>
			</tr>
		</table>
	</s:form>
</body>
