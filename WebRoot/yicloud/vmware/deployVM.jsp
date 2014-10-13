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
				$("#spaceused4").progressBar({ boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_green.gif',showText:false,height:7,width:150} );
	});
	
	</script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
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
		setTimeout(updateProgressbarValue, 2000);
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
			$.getJSON("vmw_validateUnique.do?NAME_EN="+NAME_EN,{'time':new Date().toString()},function(data){
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
		var osType = $("input[name='theForm.osType']:checked").val();
		var osId = theForm.osId.value;
		//var NET = theForm.NET.value;
		//IP = theForm.IP.value.trim();
		var osName = $("#osId").find("option:selected").text();
		var name_en = $.trim(theForm.NAME_EN.value);
		var currentmemory = $.trim(theForm.CURRENTMEMORY.value);
		var memory = theForm.MEMORY.value;
		var UNIT = $("#UNIT").find("option:selected").text();
		var currentcpu = $.trim(theForm.CURRENTCPU.value);
		var ID = '<%=request.getAttribute("id")%>';
		var TYPE = <%=request.getAttribute("type")%>;
		var dataName = $("#STORAGE").find("option:selected").text();
		//var netType=$("input[name='theForm.netType']:checked").val();
		var dataSize = theForm.dataSize.value;
		if(memory!=0&&UNIT=='G'){
			currentmemory=memory;
		}
		if (name_en=='') {
			alert('虚拟机名称不能为空');
			return;
		}
		if (currentcpu=='') {
			alert('CPU期望值不能为空');
			return;
		}
		if (!num(currentcpu)) {
			alert('CPU期望值必须为正整数');
			return;
		}
		var os = null;
		if(osType==1){
			os = 'Windows';
		}else if(osType==2){
			os = 'Linux';
		}else{
			os = '其他';
		}
	//	$.ajaxSetup({async: false});  
	//	$.getJSON("vmw_getIPAddress.do?NET="+NET + '&netType=' + netType,{'time':new Date().toString()},function(data){		
	//		IP =  data;
	//	});	
	/**
		$.ajax({
			  type:"GET",
              url:"vmw_getIPAddress.do?NET="+NET + '&netType=' + netType,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                 IP =msg;
              }
		});
		if(NET==0){
			NET = '';
		}
		*/
		content = '虚拟机名称：' + name_en + '<br>' +
		'操作系统：' + os + '<br>' + 
		'版本：' + osName + '<br>' +
		'CPU期望值：' + currentcpu+ '<br>' +
		'内存期望值：' + currentmemory+UNIT+ '<br>' +
		'存储：' + dataName + '<br>' +
		'存储大小：' +dataSize+'G';
		//+'<br>'+
		//'网络：'+NET+'<br>'+
		//'IP:'+IP;
		IP = "";
		var NET = "";
		var netType = 1;
		jConfirm(content,'确认信息', function(r) {
			if(r==false){
				return false;
			}
			if(r==true){
				bar(ID,"虚拟机创建中...");
				$.getJSON("vmw_deployVirtualMachine.do?ID="+ID+'&name_en='+encodeURI(encodeURI(name_en))+'&currentmemory='+currentmemory+'&UNIT='+UNIT+'&currentcpu='+currentcpu+'&TYPE='+TYPE +'&osId='+osId +'&dataName='+dataName+'&dataSize='+dataSize + '&osName=' + osName + '&NET=' + NET + '&IP=' + IP + '&netType' + netType,{'time':new Date().toString()},function(data){		
					IP =  data;
					var result = data.result;
					if (result == 1) {
						barEnd(ID,"虚拟机创建成功");
						window.parent.refreshParentNode();
						//window.document.location.reload();
					} else {
						barEnd(ID,"虚拟机创建失败");
						alert('创建虚拟机失败！可能的原因:' + result);
						window.document.location.reload();
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
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		/**
  		if(check == value){
    		opt.selected=true;
  		}
  		*/
  		return opt;
	}
	
	//获取OS下拉框数据
	function getOSSelect() {
		createXmlHttp();
    	var osType=$("input[name='theForm.osType']:checked").val();
    	xmlHttp.open("GET", "vmw_getOsTypeList.do?osType="+osType, false);   
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
     		if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.all.osId;
     		SelectNode.length=0;
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
    	}
	}
	//页面加载时获取OS下拉框
	function pageOnLoad() {
		getOSSelect();
		getDSInfo();
		getMemSelect();
	}
	//获取存储信息
	function getDSInfo() {
		//主机名称
		var hostname = '<%=request.getAttribute("name")%>';
		var entityId = '<%=request.getAttribute("id")%>'
		//存储名称
		var dsname = theForm.STORAGE.options[theForm.STORAGE.selectedIndex].text;
		$.getJSON("vmw_getStoreInfo.do?host_code="+entityId+'&dsname='+dsname+"&entityId="+entityId,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'&nbsp;&nbsp;&nbsp;'+'余量:'+data.freeSpace+'GB'+'<br/>'+'  存储使用率:'+data.storePer);
		});
	}
	
	function getNETSelect(){
		var netType=$("input[name='theForm.netType']:checked").val();
		if(netType == 1){
			document.getElementById('ip').style.display="";
			document.getElementById('net').style.display="none";
			$("#NET").attr("value","0");
		}else{
			document.getElementById('ip').style.display="none";
			document.getElementById('net').style.display="";
			$("#IP").attr("value","");
		} 
	}
	
	function getMemSelect(){
		var unit= $("#UNIT").find("option:selected").val();
		if(unit==1){
			document.getElementById('msize').style.display="" ; 
			document.getElementById('gsize').style.display="none" ; 
		}else{
			document.getElementById('gsize').style.display="" ; 
			document.getElementById('msize').style.display="none" ; 
		}
	}
	
	</script>
	
	<script type="text/javascript">
	$.dialog.setting.zIndex = 100000;
		function bar(idstr,contents){
		$("#confirm").attr("disabled","disabled");
		$("#resetvalue").attr("disabled","disabled");
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
	</script>
</head>
<body onload="pageOnLoad()" style="overflow-y:auto;">
	<s:form action="" method="post" cssStyle="theForm"
		id="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til" width="15%" align="center">
						客户机操作系统
					</td>
					<td>
						<input type="radio" name="theForm.osType" id="osType1" checked="checked" value="1" onclick="getOSSelect();"/><label for="osType1">Windows</label>
						<input type="radio" name="theForm.osType" id="osType2" value="2" onclick="getOSSelect();"/><label for="osType2">Linux</label>
						<input type="radio" name="theForm.osType" id="osType3" value="3" onclick="getOSSelect();"/><label for="osType3">其他</label>
					</td>
				</tr> 
				<tr>
					<td class="til" align="center">
						版本
					</td>
					<td>
						<s:select list="#{'':''}"
							name="theForm.osId" id="osId" cssStyle="width:350px;   height:25px;" ></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						虚拟机名称
					</td>
					<td>
						<s:textfield name="theForm.NAME_EN" id="NAME_EN"
							onblur="validate_NAME_EN()" value="newVirtual" cssClass="txt"></s:textfield>
						<span id="NAME_EN_SPAN" style="color: RED"></span>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						物理CPU使用情况
					</td>
					<td>
						物理CPU使用率:<s:property value="theForm.cpuPer"/>%<br/>
						已使用的物理CPU频率: <s:property value="theForm.cpuUsage"/>MHZ
						物理CPU总数:<s:property value="theForm.resource_cpu"/>MHZ<br/>
						<span class="progressBar" id="spaceused1"><s:property value="theForm.cpuPer"/></span></td>
				</tr>
				<tr>
					<td class="til" align="center">
						CPU期望个数
					</td>
					<td>
						<s:select
							list="#{'8':'8','7':'7','6':'6','5':'5','4':'4','3':'3','2':'2','1':'1'}"
							name="theForm.CURRENTCPU" id="CURRENTCPU"
							cssStyle="width:40px;   height:25px;" value="2"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						内存使用情况
					</td>
					<td>
						内存使用率:<s:property value="theForm.memPer"/>%<br/>
						已使用的内存：<s:property value="theForm.memUsage"/>MB  
						总内存：<s:property value="theForm.resource_mem"/>MB<br/>
						<span class="progressBar" id="spaceused4"><s:property value="theForm.memPer"/></span>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						期望内存
					</td>
						<td>
						<tt id="msize" style="display: none;">
						<s:select name="theForm.CURRENTMEMORY"
							list="#{'1024':'1024','512':'512','128':'128','64':'64','32':'32','16':'16','8':'8','4':'4'}"
							id="CURRENTMEMORY"  cssStyle="width:60px;   height:25px;" ></s:select>
						</tt>
						<tt id="gsize" style="display: none;">
						<s:select name="theForm.MEMORY"
							list="#{'32':'32','16':'16','8':'8','7':'7','6':'6','5':'5','4':'4','3':'3','2':'2','1':'1'}"
							  cssStyle="width:60px;   height:25px;" id="MEMORY" ></s:select>
						</tt>
						<s:select list="#{'1':'M','2':'G'}" name="theForm.UNIT"
							cssStyle="width:40px;   height:25px;" id="UNIT" onclick="getMemSelect();"></s:select>
						</td>
					
				</tr>
				<tr>
					<td class="til" align="center">存储</td>
					<td>
						<s:select list="theForm.resultList" listKey="ID" listValue="NAME" name="theForm.dataId" id="STORAGE" onchange="getDSInfo()"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						存储使用情况:
					</td>
					<td>
						<div id="dsinfo"></div>
						<%--总量：余量：<span class="progressBar" id="spaceused4">85%</span>280G--%>
					</td>
				</tr>
				<tr >
					<td class="til" align="center">存储大小</td>
					<td>
						<s:textfield name="theForm.dataSize" id="dataSize" value="10" cssClass="txt" cssStyle="width:43px;"></s:textfield>G
					</td>
				</tr>
				<%--<tr>
					<td class="til" width="15%" align="center">
						网络分配方式
					</td>
					<td>
						<input type="radio" name="theForm.netType" id="netType1" checked="checked" value="1" onclick="getNETSelect();"/><label for="netType1">手动</label>
						<input type="radio" name="theForm.netType" id="netType2" value="2" onclick="getNETSelect();"/><label for="netType2">自动</label>
					</td>
				</tr>
				<tr id="net" style="display: none">
					<td class="til" align="center">
						网络
					</td>
					<td>
						<s:select headerKey="0" headerValue="-请选择-" list="theForm.netList"
							name="theForm.NET" listKey="NAME" listValue="NAME"
							 id="NET"></s:select>
					</td>
				</tr>
				<tr id="ip">
					<td class="til" align="center">
						IP地址
					</td>
					<td>
						<s:textfield name="theForm.IP" id="IP" cssClass="txt"></s:textfield>
					</td>
				</tr>--%>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="提交"
							id="confirm"
							onclick="javascript:deployVMRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							id="resetvalue" onclick="javascript:resetForm(document.getElementById('theForm'))" />
					</td>

				</tr>
			</table>
		</div>
		<!--  
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
	</s:form>
</body>
</html:html>
<script
	src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.js"></script>
<link
	href="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.css"
	rel="stylesheet" type="text/css" />

