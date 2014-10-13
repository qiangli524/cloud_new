<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	var basePath  = '<%=request.getScheme()%>' +"://" + '<%=request.getServerName()%>' +":" 
				+ '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>' +"/";
	
	$.dialog.setting.zIndex = 100000;
		function bar(idstr,contents){
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
	<script type="text/javascript">
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
	function submitRequest(theForm){
		//克隆后虚拟机名称
		var NAME_EN = theForm.NAME_EN.value;
		//原虚拟机名称
		var name = '<%=request.getParameter("name")%>';
		var entityId = '<%=request.getParameter("entityId")%>';
		if(NAME_EN.length==0){
			alert("虚拟机机名称不能为空");
			return false;
		}
		var clusterName = $("#CLUSTER_ID").find('option:selected').text();
		var clusterId = $("#CLUSTER_ID").find('option:selected').val();
		if($("#CLUSTER_ID").find('option:selected').val() == 0){
			alert("请选择集群");
			return false;
		}
		var dns = $("#dns").val();
		if(dns.length==0){
			alert("HOST_NAME不能为空!");
			return false;
		}
		var numCPUs = $("#numCPUs").val();
		if(numCPUs.length==0){
			alert("CPU个数不能为空！");
			return false;
		}
		var memoryMB = $("#memoryMB").val();
		if(memoryMB.length==0){
			alert("内存不能为空！");
			return false;
		}
		var unit = $("#unit").val();
	 	if(unit!=2){
	 		if(memoryMB%4!=0){
	 			alert('内存大小必须为4的倍数');
	 			return false;
	 		}
	 	}else{
	 		memoryMB = memoryMB*1024;
	 		if(memoryMB%4!=0){
	 			alert('内存大小必须为4的倍数');
	 			return false;
	 		}
	 	}
		var storeName = $("#DATASTORE_ID").find('option:selected').text();
		if($("#DATASTORE_ID").find('option:selected').val() == 0){
			alert("请选择存储");
			return false;
		}
		var storageSizeInMb = $("#storageSizeInMb").val();
		if(storageSizeInMb.length==0){
			alert("存储大小不能为空！");
			return false;
		}
		var net_id = $("#net_id").find('option:selected').val();
		if($("#net_id").find('option:selected').val() == "-1"){
			alert("请选择VLAN!");
			return false;
		}
		var netType = $("[name='theForm.netType']:checked").val();
		if(netType==1){
			var ip = $("#ip").val();
			if(ip.length==0){
				alert("IP地址不能为空！");
			}
		}
		//从虚拟机克隆虚拟机
		var theFormDate = $("#theForm").serialize(); 
		var url = "vmw_cloneVirtualToCluster.do?"+ theFormDate + "&vmCode=" + entityId;
		bar(entityId,"正在克隆虚拟机"+NAME_EN);
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				barEnd(entityId,"克隆虚拟机"+NAME_EN+"到集群成功");
				window.parent.asyncNode();
			}else{
				barEnd(entityId,"克隆虚拟机"+NAME_EN+"到集群失败");
			}	
		});
	}
	function mask(){
		var doc = window.document;
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
	        background = '#FAFAFA';
	        filter = 'Alpha(opacity=10)';
	        opacity = '0.7';
	    }
	    doc.body.appendChild(w);
	    //**********************************************//
	    var ig=doc.createElement("div");
	    ig.setAttribute("id","progressbar")
	    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>虚拟机创建中，请等待............';
	    doc.getElementById("mybody").appendChild(ig);
	    with(ig.style){
	        position = 'absolute';
	        zIndex = '10001';
	        left = '55%';
	        top = '35%';
	        marginLeft = - ig.offsetWidth / 2 + 'px';
	        marginTop = - ig.offsetHeight / 2 + 'px';
	    }
	    doc.body.appendChild(ig);
	}
	//移除mask
	function removeMask() {
		var doc = window.document;
		var mybody = doc.getElementById('mybody');
		doc.body.removeChild(mybody);
		var progressbar = doc.getElementById('progressbar');
		doc.body.removeChild(progressbar);
	}
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
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
	//获取存储信息
	function getDSInfo() {
		//存储ID
		var dsId = $("#DATASTORE_ID").find('option:selected').val();
		$.getJSON("vmw_getDataStoreMess.do?dsId="+dsId,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'     余量:'+data.freeSpace+'GB');
		});
	}
	
	//查询集群下的存储信息
	function getSelect(clusterId) {
		if(clusterId==0){
			$("#DATASTORE_ID").empty();
			return false;	
		}
    	$.getJSON("vmw_getClusterDs.do?clusterId="+clusterId,{'time':new Date().toString()},function(data){
    		var  SelectNode = document.all.DATASTORE_ID;
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in data){
      			SelectNode.appendChild(createSelect(data[o].ID,data[o].NAME));
      		}
    	});
	}
	//判断集群是否开启drs
	function getClusterDRS(){
		var clusterId = $("#CLUSTER_ID").find('option:selected').val();
		getSelect(clusterId);
		var clusterName = $("#CLUSTER_ID").find('option:selected').text();
		$.getJSON("vmw_getClusterDRS.do?clusterName="+clusterId,{'time':new Date().toString()},function(data){	
			//通过返回值做判断 是否该集群可用
			if(data.result==1){
				$("#CLUSTER_SPAN").html("集群已开启DRS");
				$("#submit").show();
			}else{
				$("#CLUSTER_SPAN").html("集群未开启DRS,请更改集群配置或者选择主机!");
				$("#submit").hide();
			}
		});
	}
	
	///通过vlan获取对应的IP地址列表
	function selectIPByVlan(){
		var vlan_id = $("#net_id option:selected").val();
		if(vlan_id == -1){
			alert("请先选择VLAN!");
			return false;
		}
		$.dialog({
			id:'cloneVm',
  			title:'选择IP',
  			width: '750px',
  			height: '470px',
  		    lock:true,
  			content: 'url:'+basePath+'united_selectIpByVlan.do?uuid='+vlan_id+"&name="+'cloneVm-nm'
		});
	}
	
	//选择IP地址
	function selectip(ip){
		$("#ip").attr("value",ip);
	}
	
	function getNETSelect(){
		var netType=$("input[name='theForm.netType']:checked").val();
		if(netType==1){
			$("#IP").show();
			$("#net").show();
			$("#vlan").show();
		}else{
			$("#net").show();
			$("#vlan").show();
			$("#IP").hide();
		}
	}
</script>
</head>
<body style="width: 840px;height: 450px;margin: 10px;">
	<s:form action="vmw_cloneVirtualMac.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
			<tr>
				<td class="til" width="20%" >
					虚拟机名称：
				</td>
				<td>
					<s:textfield name="theForm.NAME_EN" id="NAME_EN" value="newVirtual" onblur="validate_NAME_EN()"/>
					<span id="NAME_EN_SPAN" style="color: RED"></span>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" >
					集群名称：
				</td>
				<td>
					<s:select  headerKey="0" headerValue="-请选择-" list="theForm.clusterList"
							name="theForm.CLUSTER_ID" listKey="entityId" listValue="name"
							onchange="getClusterDRS()" id="CLUSTER_ID"></s:select>
					<span id="CLUSTER_SPAN" style="color: RED"></span>
				</td>
			</tr>
			<tr>
					<td class="til" >
						HOST_NAME
					</td>
					<td>
						<s:textfield name="theForm.hostname" id="dns" ></s:textfield>
						<span style="color:RED" id="span_vmName1"/>
					</td>
				</tr>
			<tr>
				<td class="til" >
					CPU个数
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield
						 id="numCPUs" name="theForm.CPU"
						cssStyle="width:50px;" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" >
					内存
					<font color="red">*</font>
				</td>
					<td>
					<s:textfield name="theForm.MEMORY" id="memoryMB" cssClass="txt" cssStyle="width:50px;"></s:textfield>
					<s:select list="#{'1':'M','2':'G'}" 
						cssStyle="width:40px;   height:25px;" name="theForm.memUnit" id="unit"></s:select>
					</td>
			</tr>
			<tr>
				<td class="til">
					存储资源:
				</td>
				<td>
					<s:select list="#{'','-请选择-'}"  headerKey="0" headerValue="-请选择-"
							name="theForm.DATASTORE_ID" listKey="ID" listValue="NAME"
							onchange="getDSInfo()" id="DATASTORE_ID"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					资源信息:
				</td>
				<td>
					<div id="dsinfo"></div>
					<%--总量：余量：<span class="progressBar" id="spaceused4">85%</span>280G--%>
				</td>
			</tr>
			<tr>
				<td class="til">存储大小
					<font color="red">*</font>
				</td>
				<td>
					<s:textfield  id="storageSizeInMb" cssStyle="width:50px;" name="theForm.STORAGE"></s:textfield>G
					<font color="red">存储只能增加，不能减少</font>
				</td>
			</tr>
			<tr>
				<td class="til" width="15%">
					网络分配方式
				</td>
				<td>
					<input type="radio" name="theForm.netType" id="netType1" checked="checked" value="1" onclick="getNETSelect();"/><label for="netType1">手动</label>
					<input type="radio" name="theForm.netType" id="netType2" value="2" onclick="getNETSelect();"/><label for="netType2">自动</label>
				</td>
			</tr>
			<tr id="vlan">
				<td class="til">
					VLAN
				</td>
				<td>
					<s:select id="net_id" name="theForm.NET" list="theForm.netList" listKey="NET_ID" listValue="NAME" headerKey="-1" headerValue="-- 请选择 --">
					</s:select>
				</td>
			</tr>
			<tr id="IP">
				<td class="til">
					IP地址
				</td>
				<td>
					<s:textfield id="ip" name="theForm.IP" cssClass="required ip" readonly="true"></s:textfield><input type="button" value="选择" onclick="selectIPByVlan();"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					磁盘格式:
				</td>
				<td>
					<s:select list="#{'1':'与源格式相同','2':'厚置备延迟置零','3':'厚置备置零','4':'精简置备'}">
					</s:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="克隆"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" id="submit"/>
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
