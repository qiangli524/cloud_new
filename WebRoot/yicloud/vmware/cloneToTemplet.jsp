<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	function submitRequest(theForm){
		var name = theForm.NAME_ZH.value;
		var NAME_EN = theForm.NAME_EN.value;
	//	var parent_id = <%=request.getAttribute("parent_id")%>
		var type = '<%=request.getAttribute("type")%>';
		var entityId = '<%=request.getAttribute("entity_id")%>';
		if(NAME_EN==null){
			alert("镜像名称不能为空");
			return false;
		}
		var hostName = $("#HOST_ID").find('option:selected').val();
		if($("#HOST_ID").find('option:selected').val() == 0){
			alert("请选择主机");
			return false;
		}
		var parent_id = $("#CONTENTS").find('option:selected').val();
		if($("#CONTENTS").find('option:selected').val() == 0){
			alert("请选择目录");
			return false;
		}
		if(type==0){//从虚拟机克隆虚拟机
			var dsname = theForm.STORAGE.options[theForm.STORAGE.selectedIndex].text;
			var url = "vmw_cloneVirtualMacFirStep.do?vmName="+entityId + "&hostName=" + hostName + "&name=" + encodeURI(encodeURI(NAME_EN))+"&entity_id="+entityId+"&parent_id="+parent_id+"&dsname="+dsname;
			bar(entityId,"正在将虚拟机克隆为模板"+NAME_EN);
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(data.result==1){
					barEnd(entityId,"已成功虚拟机克隆为模板"+NAME_EN);
					window.parent.refreshParentNode();
				}else{
					barEnd(entityId,"无法将虚拟机克隆为模板"+NAME_EN);
				}	
			});
		}
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
    	//var HOST_ID=theForm.HOST_ID.value;
    	var HOST_ID = $("#HOST_ID").find('option:selected').val();
    	xmlHttp.open("GET", "vmw_getStorage.do?HOST_ID="+HOST_ID, false);   
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
     		if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("(" +xmlHttp.responseText+ ")");  
			
			var  SelectNode = document.all.STORAGE;
     		SelectNode.length=0;
      		SelectNode.appendChild(createSelect("","-请选择-"));
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(pageInfo[o],pageInfo[o]));
      		}
      		
    	}
    	/*if(HOST_ID!=0){
    		document.getElementById('').style.display="";
    	}else{
    		document.getElementById('ip').style.display="none";
    	}*/
      	
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
	
		//主机名称
		//var hostname = theForm.HOST_ID.options[theForm.HOST_ID.selectedIndex].text;
		var hostname = $("#HOST_ID").find('option:selected').val();
		//存储名称
		var dsname = theForm.STORAGE.options[theForm.STORAGE.selectedIndex].text;
		$.getJSON("vmw_getStoreInfo.do?host_code="+hostname+'&dsname='+dsname+'&entityId='+hostname,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'     余量:'+data.freeSpace+'GB');
		});
	}
	
	function validate_NAME_EN(){
		var NAME_EN = document.getElementById("theForm").NAME_EN.value;
		if (theForm.NAME_EN.value!='') {
			$.getJSON("yvm_validateUnique.do?NAME_EN="+NAME_EN,{'time':new Date().toString()},function(data){
				if (data == null) {
					document.getElementById("NAME_EN_SPAN").innerHTML = "<img src='sxcloud/images/virtual/close.png'/>"+"镜像名称已经存在，请更换名称!";
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
	
	//添加遮罩层
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
	    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>正在从虚拟机克隆为模板，请等待............';
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
	
	
</script>
<script type="text/javascript">
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
</head>
<body style="width: 840px;height: 450px;margin: 10px;">
	<s:form action="vmw_cloneVirtualMac.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize" >
<s:hidden name="theForm.NAME_ZH" id="NAME_ZH"></s:hidden>
			<tr>
				<td class="til" width="20%" >
					模板名称：
				</td>
				<td>
					<s:textfield name="theForm.NAME_EN" id="NAME_EN" onblur="validate_NAME_EN()"/>
					<span id="NAME_EN_SPAN" style="color: RED"></span>
				</td>
			</tr>
			<tr>
				<td class="til">
					所属主机：
				</td>
				<td>
					<s:select headerKey="0" headerValue="-请选择-" list="theForm.hostList"
							name="theForm.HOST_ID" listKey="entityId" listValue="name"
							onchange="getSelect()" id="HOST_ID"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					存储资源:
				</td>
				<td>
					<s:select  list="#{'','-请选择-'}" headerKey="" headerValue="-请选择-" name="theForm.STORAGE" id="STORAGE" onchange="getDSInfo()"/>
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
				<td class="til">
					模板目录:
				</td>
				<td>
					<s:select  headerKey="" headerValue="-请选择-" list="theForm.resultList" listKey="parent_id" listValue="name" id="CONTENTS"/>
				</td>
			</tr>
			<!-- 描述信息 -->
			<tr>
				<td class="til" width="20%" >
					CPU(个)：
				</td>
				<td>
					<s:property value="theForm.CPU" />(个)
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" >
					内存(MB)：
				</td>
				<td>
					<s:property value="theForm.MEMORY" />(MB)
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" >
					磁盘大小(G)：
				</td>
				<td >
					<s:property value="theForm.STORAGE" />(G)
				</td>
			</tr>
			<tr>
				<td class="til" width="20%" >
					操作系统：
				</td>
				<td>
					<s:property value="theForm.osType" />
				</td>
			</tr>		
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="克隆"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
