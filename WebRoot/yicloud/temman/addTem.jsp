<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createTem,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
 function createTem(){
 	var type=$("input[name='type']:checked").val();
 	var vm_code = $("#VH_UUID").find("option:selected").val();
 	var str = $("#obj").serialize();
 	var url = "temman_createTem.do?obj.type="+type+"&obj.templateCode="+vm_code+"&"+str;
 	//alert(url);
 	w.createTem(url);
 }
 function getVMList() {
		createXmlHttp();
    	var type=$("input[name='type']:checked").val();
    	xmlHttp.open("GET", "temman_getVMList.do?obj.type="+type, false);   
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
     		if (xmlHttp.readyState == 4) {
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.all.VH_UUID;
     		SelectNode.length=0;
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
      		
    	}
	}
	function createXmlHttp(){
    	if (window.XMLHttpRequest) {
       		xmlHttp = new XMLHttpRequest();               
    	} else {
       		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
    	}
	}
	var check='';
	function createSelect(value,text){
  		var opt=document.createElement("option");
  		opt.setAttribute("value",value);
  		opt.appendChild(document.createTextNode(text));
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}
	
	function pageOnLoad(){
		getVMList();
		listVMInfo();
	}
	//列示虚拟机的信息
	function listVMInfo(){
		var vm_code = $("#VH_UUID").find("option:selected").val();
		var url = "temman_listVMInfo.do?vm_code="+vm_code;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			
				//$("#cpu").html('<input type="text" />'+data.VH_CPU+'个');
				//$("#mem").html(data.VH_MEM+'M');
				//$("#store").html(data.VH_STORAGE+'M');
				//$("#system").html(data.VH_SYSTEM);
				$("#cpu").attr("value",data.VH_CPU);
				$("#mem").attr("value",data.VH_MEM);
				$("#store").attr("value",data.VH_STORAGE);
				$("#system").html(data.VH_SYSTEM);
				$("#hostCode").attr("value",data.hostCode);
				$("#connectId").attr("value",data.connectId);
			});
	}

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
<body onload="pageOnLoad()">
<s:form action="" method="post" id="obj" >
<s:hidden name="obj.hostCode" id="hostCode"></s:hidden>
<s:hidden name="obj.connectId" id="connectId"></s:hidden>
		<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">模板类型</td>
					<td>
						<input type="radio" name="type" checked="checked" value="1" onclick="getVMList()"/><label>Vmware模板</label>
						<input type="radio" name="type" value="3" onclick="getVMList()"/><label>Xen模板</label>
						<input type="radio" name="type" value="" onclick="getVMList()"/><label>OVF模板</label>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">虚拟机</td>
					<td>
						<s:select list="#{'':''}" id="VH_UUID"   cssStyle="width:220px;   height:25px;" onchange="listVMInfo();"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">CPU</td>
					<td>
						<s:textfield id="cpu" cssStyle="width:50px;   height:20px;" name="obj.cpu"></s:textfield>个
					</td>
				</tr> 
				<tr align="left">
					<td class="til">内存</td>
					<td>
						<s:textfield id="mem" cssStyle="width:50px;   height:20px;" name="obj.mem"></s:textfield>M
					</td>
				</tr> 
				<tr align="left">
					<td class="til">存储</td>
					<td>
						<s:textfield id="store" cssStyle="width:50px;   height:20px;" name="obj.store"></s:textfield>G
					</td>
				</tr> 
				<tr align="left">
					<td class="til">操作系统</td>
					<td>
						<div name="obj.system" id="system"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">模板名称</td>
					<td>
					<s:textfield name="obj.name" id="name" cssStyle="width:220px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">是否公有</td>
					<td>
					<input type="radio" name="obj.isPublic" checked="checked" value="0"/><label>公有</label>
					<input type="radio" name="obj.isPublic" value="1"/><label>私有</label>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">位置</td>
					<td>
					<s:textfield name="obj.position" id="position" cssStyle="width:220px;   height:20px;"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">备注</td>
					<td>
					<s:textfield name="obj.remark" id="remark" cssStyle="width:220px;   height:20px;"></s:textfield>
					</td>
				</tr>  
			</table>
		</div>
</s:form>
</body>
