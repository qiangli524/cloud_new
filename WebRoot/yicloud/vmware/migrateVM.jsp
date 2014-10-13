<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	function submitRequest(theForm){
		var migType=$("input[name='theForm.migType']:checked").val();
		var hostname;
		var dsname;
		var HOST_ID;
		var id =<%=request.getAttribute("id")%>;
		
		if(migType=='1'){
			HOST_ID=theForm.HOST_ID.value;
			//hostname = theForm.HOST_ID.options[theForm.HOST_ID.selectedIndex].text;
			hostname =	$("#HOST_ID").find("option:selected").val();
			host =	$("#HOST_ID").find("option:selected").text();  
			//dsname = theForm.STORAGE.options[theForm.STORAGE.selectedIndex].text;
		} else if(migType=='2'){
			HOST_ID='<%=request.getAttribute("HOST_ID")%>';
		 	hostname = '<%=request.getAttribute("host_name")%>';
		 	dsname = theForm.STORAGE1.options[theForm.STORAGE1.selectedIndex].text;
		}
		var vmName = '<%=request.getAttribute("vmName")%>';
		var name = '<%=request.getAttribute("name")%>';
		if(confirm("确定要迁移虚拟机吗?")==true){
			//	theForm.action='vmw_migrateVM.do?vmName='+vmName+'&migType='+migType+'&hostname='+hostname+'&dsname='+dsname+'&HOST_ID='+HOST_ID+'&id='+id;
		var url = 'vmw_migrateVM.do?vmName='+encodeURI(encodeURI(vmName))+'&migType='+migType+'&hostname='+hostname+'&dsname='+dsname+'&HOST_ID='+HOST_ID+'&id='+id+'&host='+hostname+'&name='+name;
		bar(id,"正在迁移虚拟机"+name);
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data==1){
				barEnd(id,"已成功迁移虚拟机"+name);
				window.parent.asyncNode();
			}else{
				barEnd(id,"迁移虚拟机"+name+"失败");
			}
		});
		}
	    //theForm.submit();
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
	    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>虚拟机迁移中，请等待............';
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
	
	function getSelect() {
		createXmlHttp();
    	var HOST_ID=theForm.HOST_ID.value;
    	var migType=$("input[name='theForm.migType']:checked").val();
    	if(migType==1){
	    	xmlHttp.open("GET", "vmw_getStorage.do?HOST_ID="+HOST_ID, false);   
	    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
	    	xmlHttp.send(null);
	     	if (xmlHttp.readyState == 4) {
	    		var pageInfo = eval("("+xmlHttp.responseText+")");  
				
				var  SelectNode = document.all.STORAGE;
	     		$(SelectNode).empty();
	      		SelectNode.appendChild(createSelect("","-请选择-"));
	     		for(var o in pageInfo){
	      			SelectNode.appendChild(createSelect(pageInfo[o],pageInfo[o]));
	      		}
	      	}
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
		var migType=$("input[name='theForm.migType']:checked").val();
		var hostname;
		var dsname;
		var entityId ;
		var host_code;
		//主机名称
		
		if(migType=='1'){
			hostname = theForm.HOST_ID.options[theForm.HOST_ID.selectedIndex].text;
			dsname = theForm.STORAGE.options[theForm.STORAGE.selectedIndex].text;
		} else if(migType=='2'){
		 	hostname = '<%=request.getAttribute("host_name")%>';
		 	host_code = '<%=request.getAttribute("host_code")%>'
		 	dsname = theForm.STORAGE1.options[theForm.STORAGE1.selectedIndex].text;
		}
		//存储名称
		
		$.getJSON("vmw_getStoreInfo.do?hostname="+hostname+'&dsname='+dsname+"&host_code="+host_code,{'time':new Date().toString()},function(data){	
			$("#dsinfo").html('总量:'+data.capacity+'GB'+'     余量:'+data.freeSpace+'GB');
		});
	}
	//获取OS下拉框数据
	function getMigSelect(sel) {
		 if(sel=='1'){
		    document.getElementById('migHost1').style.display="";
			document.getElementById('migHost3').style.display="none";
			document.getElementById('resource').style.display="none";
		} else if(sel=='2'){
			document.getElementById('migHost1').style.display="none";
			document.getElementById('migHost3').style.display="";
			document.getElementById('resource').style.display="";
			createXmlHttp();
			var HOST_ID = '<%=request.getAttribute("HOST_ID")%>';
			xmlHttp.open("GET", "vmw_getStorage.do?HOST_ID="+HOST_ID, false);   
	    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
	    	xmlHttp.send(null);
	     	if (xmlHttp.readyState == 4) {
	    		var pageInfo = eval("("+xmlHttp.responseText+")");  
				
				var  SelectNode = document.all.STORAGE1;
	     		$(SelectNode).empty();
	      		SelectNode.appendChild(createSelect("","-请选择-"));
	     		for(var o in pageInfo){
	      			SelectNode.appendChild(createSelect(pageInfo[o],pageInfo[o]));
	      		}
	      	}      		
    	}
		/**
		createXmlHttp();
		var host_id = '<%=request.getAttribute("host_id")%>';
		var parent_id = '<%=request.getAttribute("parent_id")%>';
    	var migType=$("input[name='theForm.migType']:checked").val();
    	xmlHttp.open("GET", "vmw_getMigTypeInfo.do?migType="+migType+"&host_id="+host_id+"&parent_id="+parent_id, false);   
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
     	*/
	}
	//页面加载时获取OS下拉框
	function pageOnLoad() {
		getMigSelect('1');
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
<body style="width: 840px;height: 450px;margin: 10px;" onload="pageOnLoad()">
	<s:form action="vmw_cloneVirtualMac.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
		<s:hidden name="theForm.NAME_ZH" id="NAME_ZH"></s:hidden>
			<tr>
					<td class="til" width="20%">
						选择迁移类型:
					</td>
					<td>
						<input type="radio" name="theForm.migType" id="migType1" checked="checked" value="1" onclick="getMigSelect('1');"/><label for="migType1">迁移主机</label>
						<input type="radio" name="theForm.migType" id="migType2" value="2" onclick="getMigSelect('2');"/><label for="migType2">迁移存储</label>
					</td>
				</tr>
			<tr id="migHost1" style="display:">
				<td class="til">
					选择迁移的主机：
				</td>
				<td>
					<s:select headerKey="0" headerValue="-请选择-" list="theForm.hostList"
							name="theForm.HOST_ID" listKey="entityId" listValue="name"
							 id="HOST_ID"></s:select>
				</td>
			</tr>
			<!--  
			<tr id="migHost2" style="display:">
				<td class="til">
					存储资源:
				</td>
				<td>
					<s:select  list="#{'','-请选择-'}" headerKey="" headerValue="-请选择-" name="theForm.STORAGE" id="STORAGE" onchange="getDSInfo()"/>
				</td>
			</tr>
			-->
			<tr id="migHost3" style="display:">
				<td class="til">
					存储资源:
				</td>
				<td>
					<s:select  list="#{'','-请选择-'}" headerKey="" headerValue="-请选择-" name="theForm.STORAGE"  id="STORAGE1" onchange="getDSInfo()"/>
				</td>
			</tr>
			<tr id="resource" style="display:">
				<td class="til">
					资源信息:
				</td>
				<td>
					<div id="dsinfo"></div>
				</td>
			</tr>
		
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="迁移"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
