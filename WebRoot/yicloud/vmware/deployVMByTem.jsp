<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.config.Constant"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />
<meta http-equiv="expires" content="0" />
<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script
	src="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.js"></script>
<link
	href="<%=request.getContextPath()%>/sxcloud/js/alert/jquery.alerts.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//校验虚拟机英文名称的唯一性
	
	function validate_NAME_EN(){
		var name = $("#name").val();
		if (name!='') {
			$.getJSON("vmw_validateUnique.do?NAME_EN="+name,{'time':new Date().toString()},function(data){
				if (data == null) {
					document.getElementById("NAME_EN_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"虚拟机名称已经存在，请更换名称!";
					//document.getElementById("theForm").NAME_EN;
					//alert('虚拟机名称已经存在，请更换名称!');
					theForm.name.value='';
					theForm.name.focus();
				}
				else{
					document.getElementById("NAME_EN_SPAN").innerHTML = "<img src='sxcloud/images/virtual/open.png'/>"
				}
			});
		}
	}
	var hostId = '<%=request.getAttribute("hostId")%>';
	//部署虚拟机测试
	
	//部署虚拟机
	function cloneVMByTem() {
		var NET = theForm.NET.value;
		IP = $("#IP").val();
		var name = $("#name").val();
		var cpu = $("#cpu").val();
		var mem = $("#mem").val();
		var unit = $("#unit").val();
		var store = $("#dataSize").val();
		var unit = $("#UNIT").find("option:selected").text();
		var ID = '<%=request.getAttribute("host_id")%>';
		var netType=$("input[name='theForm.netType']:checked").val();
		var tem_code = $("#tem").find("option:selected").val();
		var store_uuid = $("#STORAGE").find("option:selected").text();
		var host_code = '<%=request.getAttribute("host_code")%>';
		if (name=='') {
			alert('虚拟机名称不能为空');
			return false;
		}
		if (cpu=='') {
			alert('CPU值不能为空');
			return false;
		}
			alert();
				bar(ID,"虚拟机创建中...");
				url = "vmw_cloneVMByTem.do?ID="+ID+'&name='+encodeURI(encodeURI(name))+'&cpu='+cpu+'&unit='+unit+'&mem='+mem
				+'&store='+store+'&IP=' + IP+"&tem_code="+tem_code+"&store_code="+store_uuid+"&host_code="+host_code;
				alert(url);
				$.getJSON(url,{'time':new Date().toString()},function(data){		
					//IP =  data;
					var result = data.result;
					if (result == 1) {
						barEnd(ID,"虚拟机创建成功");
						window.parent.refreshParentNode();
						//window.document.location.reload();
					} else {
						barEnd(ID,"虚拟机创建失败");
						window.document.location.reload();
					}
				});
	}
	function resetForm(theForm){
		//theForm.NAME_ZH.value = '';
		//theForm.NAME_EN.value = '';
		//theForm.CPU.value = '';
		//theForm.MEMORY.value = '';
		//theForm.IP.value = '';
		//theForm.CURRENTCPU.value = '';
		//theForm.CURRENTMEMORY.value = '';
	}
	
	//获取存储信息
	function getDSInfo() {
		var entityId = '<%=request.getAttribute("host_code")%>';
		//存储名称
		var dsname = $("#STORAGE").find("option:selected").text();
		$.getJSON("vmw_getStoreInfo.do?host_code="+entityId+'&dsname='+dsname+"&entityId="+entityId,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'&nbsp;&nbsp;&nbsp;'+'余量:'+data.freeSpace+'GB'+'<br/>'+'  存储使用率:'+data.storePer);
		});
	}
	
	function getNETSelect(){
		var netType=$("input[name='theForm.netType']:checked").val();
		if(netType == 1){
			$("#ip").show();
			$("#net").hide();
			$("#NET").attr("value","0");
		}else{
			$("#net").show();
			$("#ip").hide();
			$("#IP").attr("value","");
		} 
	}
	
	
	//获取模板信息
	function getTemInfo(){
		var tem_id =  $("#tem").find("option:selected").val();
		$.getJSON("temman_listTemInfo.do?tem_id="+tem_id,{'time':new Date().toString()},function(data){	
			$("#cpu").attr("value",data.VH_CPU);
			$("#mem").attr("value",data.VH_MEM);
			$("#dataSize").attr("value",data.VH_STORAGE);
		});
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
	
	function pageOnLoad(){
		getDSInfo();
		getNETSelect();
		getTemInfo();
	}
	</script>
</head>
<body  style="overflow-y:auto;" onload="pageOnLoad();">
	<s:form action="" method="post" cssStyle="theForm"
		id="theForm">
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr id="temList">
					<td class="til" align="center">
						选择模板
					</td>
					<td>
						<s:select list="theForm.resultList" listKey="id" listValue="name"
							cssStyle="width:150px;   height:25px;" onchange="getTemInfo();" id="tem"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						虚拟机名称
					</td>
					<td>
						<s:textfield name="theForm.NAME_EN" id="name"
							onblur="validate_NAME_EN()" value="newVirtual" cssClass="txt"></s:textfield>
						<span id="NAME_EN_SPAN" style="color: RED"></span>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						CPU个数
					</td>
					<td>
						<s:select
							list="#{'8':'8','7':'7','6':'6','5':'5','4':'4','3':'3','2':'2','1':'1'}"
							name="theForm.CURRENTCPU" id="cpu"
							cssStyle="width:40px;   height:25px;" value="2"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						内存
					</td>
						<td>
						<s:textfield name="theForm.mem" id="mem" cssClass="txt" cssStyle="width:50px;"></s:textfield>
						<s:select list="#{'1':'M','2':'G'}" name="theForm.UNIT"
							cssStyle="width:40px;   height:25px;" id="UNIT"></s:select>
						</td>
				</tr>
				<tr>
					<td class="til" align="center">存储</td>
					<td>
						<s:select list="theForm.storageList" listKey="store_uuid" listValue="NAME"  id="STORAGE" onchange="getDSInfo()"></s:select>
					</td>
				</tr>
				<tr>
					<td class="til" align="center">
						存储使用情况:
					</td>
					<td>
						<div id="dsinfo"></div>
					</td>
				</tr>
				<tr >
					<td class="til" align="center">存储大小</td>
					<td>
						<s:textfield name="theForm.dataSize" id="dataSize" value="10" cssClass="txt" cssStyle="width:50px;"></s:textfield>G
					</td>
				</tr>
				<tr>
					<td class="til" width="15%" align="center">
						网络分配方式
					</td>
					<td>
						<input type="radio" name="theForm.netType" id="netType1" checked="checked" value="1" onclick="getNETSelect();"/><label for="netType1">手动</label>
						<input type="radio" name="theForm.netType" id="netType2" value="2" onclick="getNETSelect();"/><label for="netType2">自动</label>
					</td>
				</tr> 
				<tr id="net">
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
				</tr>
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="提交"
							id="confirm"
							onclick="javascript:cloneVMByTem();" />
						<input type="button" class="thickbox btn-style02" value="重置"
							id="resetvalue" onclick="javascript:resetForm(document.getElementById('theForm'))" />
					</td>

				</tr>
			</table>
		</div>
	</s:form>
</body>
</html:html>


