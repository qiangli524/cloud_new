<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.config.Constant"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />
<meta http-equiv="expires" content="0" />
<head>
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
		rel="stylesheet" type="text/css" />
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
		rel="stylesheet" type="text/css" />
	<link
		href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css"
		rel="stylesheet" type="text/css" />
	<!-- 进度条 -->
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script type="text/javascript"
		src="sxcloud/js/progressbar/js/jquery.progressbar.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
				$("#spaceused1").progressBar({boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_green.gif',showText:false,height:7,width:150} );
				$("#spaceused4").progressBar({ boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_red.gif',showText:false,height:7,width:150} );
	});
	
	</script>

	<!-- 部署时的进度条--jquery ui -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.progressbar.js"></script>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/demos.css" />
	<style>
	.ui-progressbar .ui-progressbar-value {
		background-image:
			url(sxcloud/js/jqueryui/themes/base/images/pbar-ani.gif);
	}
	</style>
	<script>
	var val;
	$(function() {
		$( "#progressbar" ).progressbar({
			value: 0
		});
	});
	updateProgressbarValue();
	function updateProgressbarValue() {
		setTimeout(updateProgressbarValue, 1000);
		$.getJSON("yvm_getProgressbarVal.do",{'time':new Date().toString()},function(data){
			val = data.val;
		});
		if (val == 100) {
			$("#progressbar").progressbar( "value", val);
			return;
		}
		var current = $("#progressbar").progressbar("value");
		if (current >= val) {
			return;
		}
		var value = $("#progressbar").progressbar("value")+3;
		$("#progressbar").progressbar( "value", value);
	}
	</script>
	<script type="text/javascript"
		src="sxcloud/cjs/validate/validate_class.js"></script>
	<script type="text/javascript">
	
		var flag= true;
		function checkSelect(){
		var str= document.theForm.SELECT_TYPE.value;
		if(str=='IBM' || str=='KVM'){
			document.getElementById('type').style.display="";
			theForm.action = 'queryImageList.do'  
		    theForm.submit();
		}else {
			document.getElementById('type').style.display="none";
		}
	}
	//校验虚拟机英文名称的唯一性
	function validate_NAME_EN(){
		var NAME_EN = document.getElementById("theForm").NAME_EN.value;
		if (theForm.NAME_EN.value!='') {
			$.getJSON("yvm_validateUnique.do?NAME_EN="+NAME_EN,{'time':new Date().toString()},function(data){
				if (data == null) {
					document.getElementById("NAME_EN_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"虚拟机名称已经存在，请更换名称!";
					//document.getElementById("theForm").NAME_EN;
					//alert('虚拟机名称已经存在，请更换名称!');
					theForm.NAME_EN.value='';
					theForm.NAME_EN.focus();
				}
				else{
					document.getElementById("NAME_EN_SPAN").innerHTML = "<img src='sxcloud/images/virtual/open.png'/>"
				}
			});
		}
	}
	function validate_CPU(){
		var CPU = document.getElementById("theForm").CPU.value;
		//校验是否是正整数
		if (!num(CPU)) {
			document.getElementById("CPU_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"必须为正整数";
			theForm.CPU.value='';
			//theForm.CPU.focus();
			return;
		}
		if (theForm.CPU.value!='') {
			$.getJSON("yvm_validateCPU.do?CPU="+CPU,{'time':new Date().toString()},function(data){
				if (data == null) {
					document.getElementById("CPU_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"CPU个数不能多于剩余资源量!";
					theForm.CPU.value='';
					theForm.CPU.focus();
				} else{
					document.getElementById("CPU_SPAN").innerHTML = "<img src='sxcloud/images/virtual/open.png'/>"
				}
			});
		}
	}
	function validate_MEMORY(){
		var MEMORY = document.getElementById("theForm").MEMORY.value;
		//校验是否是正整数
		if (!num(MEMORY)) {
			document.getElementById("MEMORY_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"必须为正整数";
			theForm.MEMORY.value='';
			//theForm.MEMORY.focus();
			return;
		}
		if (theForm.MEMORY.value!='') {
			$.getJSON("yvm_validateMEMORY.do?MEMORY="+MEMORY,{'time':new Date().toString()},function(data){
				if (data == null) {
					document.getElementById("MEMORY_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"内存数不能多于剩余资源量!";
					theForm.MEMORY.value='';
					theForm.MEMORY.focus();
				}
				else{
					document.getElementById("MEMORY_SPAN").innerHTML = "<img src='sxcloud/images/virtual/open.png'/>"
				}
			});
		}
	}
	var hostId = <%=request.getAttribute("hostId")%>;
	var IM_ID = <%=request.getAttribute("IM_ID")%>;
	//部署虚拟机测试
	
	
	//部署虚拟机
	function deployVMRequest(theForm) {
		var ip = theForm.IP.value.trim();
		var NET = theForm.NET.value;
		var name_en = theForm.NAME_EN.value.trim();
		var cpu = theForm.CPU.value.trim();
		var memory = theForm.MEMORY.value.trim();
		var currentmemory = theForm.CURRENTMEMORY.value.trim();
		//var currentmemory = 1024;
		var currentcpu = theForm.CURRENTCPU.value.trim();
		var unit = theForm.UNIT.value;
		var ENTITY_ID = <%=request.getAttribute("ENTITY_ID")%>;
		var TYPE = <%=request.getAttribute("TYPE")%>;
		if (name_en=='') {
			alert('虚拟机名称不能为空');
			return;
		}
		if (!num_letter(name_en)) {
			alert('虚拟机名称必须为数字,英文和下划线!');
			return;
		}
		if (cpu=='') {
			alert('CPU最大值不能为空');
			return;
		}
		if (!num(cpu)) {
			alert('CPU最大值必须为正整数');
			return;
		}
		/*if (currentcpu=='') {
			alert('CPU期望值不能为空');
			return;
		}
		if (!num(currentcpu)) {
			alert('CPU期望值必须为正整数');
			return;
		}
		if (parseInt(currentcpu) > parseInt(cpu)) {
			alert('期望CPU不能大于最大CPU！');
			return;
		}
		if (memory=='') {
			alert('最大内存不能为空');
			return;
		}
		if (!num(memory)) {
			alert('最大内存必须为正整数');
			return;
		}
		if (currentmemory=='') {
			alert('期望内存不能为空');
			return;
		}
		if (!num(currentmemory)) {
			alert('期望内存必须为正整数');
			return;
		}
		if (parseInt(currentmemory) > parseInt(memory)) {
			alert('期望内存不能大于最大内存！');
			return;
		}
		if (NET==0) {
			alert('请选择网络！');
			return;
		}
		if (ip==0) {
			alert('请选择IP！');
			return;
		}*/
		content = '虚拟机名称：' + name_en + '<br>' +
		'CPU最大值：' + cpu + '<br>' + 
		'CPU期望值：' + currentcpu+ '<br>' +
		'内存最大值：'  + memory+ '<br>' +
		'内存期望值：' + currentmemory+ '<br>' + 
		'网络：' + $("#NET").find("option:selected").text() + '<br>' +  
		'IP地址：' + ip;
		jConfirm(content,'确认信息', function(r) {
			if(r==false){
				return false;
			}
			if(r==true){
				show();
				$.getJSON("yvm_deployVirtualMachine.do?ENTITY_ID="+ENTITY_ID+'&name_en='+name_en+'&cpu='+cpu+'&memory='+memory+'&ip='+ip+'&currentmemory='+currentmemory+'&currentcpu='+currentcpu+'&unit='+unit+'&TYPE='+TYPE,{'time':new Date().toString()},function(data){		
					var result = data.result;
					if (result == null) {
						alert('创建虚拟机成功！');
						window.document.location.reload();
					} else {
						alert('创建虚拟机失败！');
						//window.parent.parent.document.location.reload();
					}
				});
			}
		})
		
	}
	function show()
    {
        document.all.ly.style.display="block";  
     	document.all.ly.style.width=document.body.scrollWidth;//网页正文全文宽
     	document.all.ly.style.height=document.body.scrollHeight;//网页正文全文高
        document.all.divTest.style.display='block'; 
       
        document.getElementById("divTest").style.visibility="visible";
        /*var ss = document.getElementById("progressbar");
        ss.innerHTML= "<img src='sxcloud/js/progressbar/images/progressbg_yellow.gif'/>";*/
    }
    
   
	//判断必须为数字,英文和下划线
	function num_letter(obj) {
      var patrn = /^\w+$/;
      var sInput = obj;
      if (sInput.search(patrn) == -1) {
        return false;
      }
      return true;
    }
    //判断必须为数字
	function num(obj) {
      var patrn = /^[0-9]+$/;
      var sInput = obj;
      if (sInput.search(patrn) == -1) {
        return false;
      }
      return true;
    }
    
	function pageOnLoad() {
		if (document.getElementById("theForm").NAME_EN!=null){
			document.getElementById("theForm").NAME_EN.focus();
		}
	}
	function resetForm(theForm){
		//theForm.NAME_ZH.value = '';
		theForm.NAME_EN.value = '';
		theForm.CPU.value = '';
		theForm.MEMORY.value = '';
		theForm.IP.value = '';
		theForm.CURRENTCPU.value = '';
		theForm.CURRENTMEMORY.value = '';
	}
	
	function mask(){
		var doc = window.parent.parent.document;
	  	var w = doc.createElement("div");
	    w.setAttribute("id","mybody")
	    with(w.style){
	        position = 'absolute';
	        zIndex = '10000';
	        width = Math.max(doc.documentElement.scrollWidth, doc.documentElement.clientWidth) + "px";
	        height =Math.max(doc.documentElement.scrollHeight, doc.documentElement.clientHeight) + "px";
	        position="absolute";
	        left = '0';
	        top = '0';
	        background = '#000000';
	        filter = 'Alpha(opacity=70)';
	        opacity = '0.7';
	    }
	    doc.body.appendChild(w);
	    //**********************************************//
	    var ig=doc.createElement("div");
	    ig.setAttribute("id","progressbar")
	    ig.innerHTML='<td i="progressbar"/> <br/>虚拟机创建中，请等待............';
	    doc.getElementById("mybody").appendChild(ig);
	    with(ig.style){
	        position = 'absolute';
	        zIndex = '10001';
	        left = '50%';
	        top = '50%';
	        marginLeft = - ig.offsetWidth / 2 + 'px';
	        marginTop = - ig.offsetHeight / 2 + 'px';
	    }
	    doc.body.appendChild(ig);
	}
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	function getSelect() {
		createXmlHttp();
    	var NET_ID=document.theForm.NET.value;
    	xmlHttp.open("GET", "yvm_getIpAddress.do?NET_ID="+NET_ID, false);   
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
     		if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.all.IP;
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(pageInfo[o],pageInfo[o]));
      		}
      		
    	}
    	/*if(NET_ID!=0){
    		document.getElementById('ip').style.display="";
    	}else{
    		document.getElementById('ip').style.display="none";
    	}*/
      	
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}
	
	</script>
</head>
<%
	String oper = (String) request.getAttribute("oper");
%>
<body onload="pageOnLoad()">
	<s:form action="yvm_deployKVMImage" method="post" cssStyle="theForm"
		id="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<%
					if (!oper.equals("2")) {
				%>
				<tr>
					<td class="til" width="30%">
						映像名称
					</td>
					<td width="70%">
						<s:property value="theForm.IM_NAME" />
					</td>
				</tr>
				<tr>
					<td class="til">
						映像描述
					</td>
					<td>
						<s:property value="theForm.IM_DESC" />
					</td>
				</tr>
				<tr>
					<td class="til">
						映像状态
					</td>
					<td>
						<s:property value="theForm.IM_STATE" />
					</td>
				</tr>
				<tr>
					<td class="til">
						映像版本
					</td>
					<td>
						<s:property value="theForm.IM_VERSION" />
					</td>
				</tr>
				<tr>
					<td class="til">
						映像类型
					</td>
					<td>
						<s:property value="theForm.IM_TYPE" />
					</td>
				</tr>
				<%} %>
				<%
					if (oper.equals("1") || oper.equals("2")) {
				%>
				<%--<tr>
                   <td class="til">
						虚拟机中文名称
					</td>
					<td>
					    <html:text property="NAME_ZH" styleClass="txt notnull"/>                
					</td>
                   </tr>--%>
				<tr>
					<td class="til">
						虚拟机名称
					</td>
					<td>
						<s:textfield name="theForm.NAME_EN" id="NAME_EN"
							onblur="validate_NAME_EN()"></s:textfield>
						<span id="NAME_EN_SPAN" style="color: RED"></span>
				</tr>
				<tr>
					<td class="til">
						剩余cpu个数
					</td>
					<td>
						<span class="progressBar" id="spaceused1">25%</span>20个
					</td>
				</tr>
				<tr>
					<td class="til">
						CPU最大个数
					</td>
					<td>
						<s:textfield name="theForm.CPU" id="CPU" onblur="validate_CPU()"></s:textfield>
						<span id="CPU_SPAN" style="color: RED"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						CPU期望个数
					</td>
					<td>
						<s:select
							list="#{'8':'8','7':'7','6':'6','5':'5','4':'4','3':'3','2':'2','1':'1'}"
							name="theForm.CURRENTCPU" id="CURRENTCPU"
							cssStyle="width:45px;   height:25px;"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til">
						剩余内存
					</td>
					<td>
						<span class="progressBar" id="spaceused4">85%</span>280G
					</td>
				</tr>
				<tr>
					<td class="til">
						最大内存(M)
					</td>
					<td>
						<s:textfield name="theForm.MEMORY" id="MEMORY"
							onblur="validate_MEMORY()"></s:textfield>
						<span id="MEMORY_SPAN" style="color: RED"></span>
					</td>
				</tr>
				<tr>
					<td class="til">
						期望内存
					</td>
					<td>
						<s:select name="theForm.CURRENTMEMORY"
							list="#{'1024':'1024','64':'64','16':'16','8':'8','4':'4'}"
							id="CURRENTMEMORY" size="2" cssStyle="width:60px;   height:25px;"></s:select>
						<s:select list="#{'1':'MB','2':'GB'}" name="theForm.UNIT"
							cssStyle="width:45px;   height:25px;" id="UNIT"></s:select>

					</td>
				</tr>
				<tr>
					<td class="til">
						网络
					</td>
					<td>
						<s:select headerKey="0" headerValue="-请选择-" list="theForm.netList"
							name="theForm.NET" listKey="NET_ID" listValue="NAME"
							onchange="getSelect()" id="NET"></s:select>
					</td>
				</tr>

				<tr id="ip">
					<td class="til">
						IP地址
					</td>
					<td>
						<s:select list="#{'0','-请选择-'}" headerKey="0" headerValue="-请选择-"
							name="theForm.IP" id="IP"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="部署"
							id="confirm"
							onclick="javascript:deployVMRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:resetForm(document.getElementById('theForm'))" />
					</td>

				</tr>


				<%
					}
				%>
			</table>
		</div>
		<!-- 创建虚拟机进度条 -->
		<div id="divTest"
			style="position: absolute; z-index: 10; width: 310; height: 30px; background-color: Yellow; display: none; top: 15%; left: 30%;">
			<table border="1" width="50%" cellspacing="0" cellpadding="0"
				style="border-collapse: collapse" bgcolor="#FFFFEC" height="50"
				align="center">
				<tr>
					<td bgcolor="#3399FF" style="font-size: 12px; color: #ffffff"
						height=5>
						虚拟机创建中...
					</td>
				</tr>
				<tr>
					<td style="" align="center">
						<div id="progressbar"></div>
					</td>
				</tr>
			</table>
		</div>
		<div id="ly"
			style="position: absolute; top: 0px; filter: alpha(opacity =   10); background-color: #777; z-index: 2; left: 0px; display: none;">

		</div>
		<div id="main" style="background-color: Azure; height: 500px;">
		</div>
	</s:form>
</body>
</html:html>
<script
	src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.js"></script>
<link
	href="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.css"
	rel="stylesheet" type="text/css" />

