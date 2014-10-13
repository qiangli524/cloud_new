<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
	var api = frameElement.api;
	 var w = api.opener;
	$(function(){
		 $("[id='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'添加网络',
    			width: '700px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:portgroup_addNetWork.do'
    			});
              });
            });
	var check;
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
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
  		if(check == value){
    	opt.selected=true;
  		}
  		return opt;
	}

	
	 function getNicList(){
	 	createXmlHttp();
    	var host_code=$("#host").val();
    	xmlHttp.open("GET", "portgroup_getNicList.do?host_code="+host_code, false);   
    	xmlHttp.setRequestHeader("If-Modified-Since","0");     
    	xmlHttp.send(null);
     		if (xmlHttp.readyState == 4) {
     		
    		var pageInfo = eval("("+xmlHttp.responseText+")");     
			var  SelectNode = document.all.nic;
			
     		SelectNode.length=0;
     		for(var o in pageInfo){
      			SelectNode.appendChild(createSelect(o,pageInfo[o]));
      		}
      		
    	}
	 }
	 
	function getIpSelect(){
		var type = $("#type").val();
		if(type==2){
			document.getElementById('ip').style.display="" ; 
			document.getElementById('ip_address').style.display="" ; 
			document.getElementById('sub_net').style.display="" ; 
			document.getElementById('vm_kernel').style.display="" ; 
		}
		else{
			document.getElementById('ip').style.display="none" ; 
			document.getElementById('ip_address').style.display="none" ; 
			document.getElementById('sub_net').style.display="none" ; 
			document.getElementById('vm_kernel').style.display="none" ; 
		}
	}
	
	function getContraSelect(a){
		if(a==1){
			theVSForm.ip_address.disabled = "true";
			theVSForm.sub_net.disabled = "true";
			theVSForm.vm_kernel.disabled = "true";
			$("#ip_address").attr("disabled","disabled"); 
			$("#sub_net").attr("disabled","disabled"); 
			$("#vm_kernel").attr("disabled","disabled"); 
		}else{
			theVSForm.ip_address.disabled = "";
			theVSForm.sub_net.disabled = "";
			theVSForm.vm_kernel.disabled = "";
			$("#ip_address").removeAttr("disabled");
			$("#sub_net").removeAttr("disabled");
			$("#vm_kernel").removeAttr("disabled");
		}
		
	}
	
	function pageOnLoad(){
		getNicList();
		getContraSelect(1);
	}
	
	function submitRequest(theVSForm){
		var ip_type='';
		var ip ='';
		var sub_net='';
		var vm_kernel ='';
		var type = $("#type").val();
		var host_code = theVSForm.host.value;
		var nic = $("#nic").find("option:selected").text();;
		var name = theVSForm.name.value; 
		var vlan = theVSForm.vlan.value; 
		if(type==2){
		ip_type=$("input[name='theVSForm.ip_type']:checked").val();
		if(ip_type=2){
		ip = theVSForm.ip_address.value;
		sub_net = theVSForm.sub_net.value;
		vm_kernel = theVSForm.vm_kernel.value;
		}
		}
		if(name==null ||name ==''){ 
			alert("网络标签不能为空");
			return ;
		}
		var hostCode = '<%=request.getAttribute("hostCode")%>';
		var url = "portgroup_createNetWork.do?type="+type+"&host_code="+host_code+"&nic="+encodeURI(encodeURI(nic))
		+"&name="+encodeURI(encodeURI(name))+"&vlan="+vlan+"&ip_type="+ip_type+"&ip="+ip+"&sub_net="+sub_net
		+"&vm_kernel="+vm_kernel;
		w.saveNetWork(url,hostCode);
	}
	
</script>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad();" >
	<s:form action="" method="post" id="theVSForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="15%">
					连接类型：
				</td>
				<td>
					<s:select list="#{'1':'虚拟机','2':'VMkernel'}"  id="type" onclick="getIpSelect();"/>
				</td>
			</tr>
			<tr>
			<tr>
				<td class="til" width="20%">
					选择主机：
				</td>
				<td>
					<s:select list="theVSForm.hostList" listKey="entityId" listValue="name" onchange="getNicList();" name="theVSForm.host" id="host"/>
				</td>
			</tr>
			<tr>
				<td class="til">
					网络访问：
				</td>
				<td>
					<s:select list="#{'':''}"  name="theVSForm.nic"  id="nic" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					网络标签：
				</td>
				<td >
					<s:textfield name="theVSForm.name" cssStyle="width:150px;   height:20px;" id="name"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					VLAN ID：
				</td>
				<td >
					<s:textfield name="theVSForm.vlan" cssStyle="width:150px;   height:20px;" id="vlan"></s:textfield>
				</td>
			</tr>
			<tr style="display: none;" id="ip">
				<td class="til">
					IP设置：
				</td>
				<td >
					<input type="radio" checked="checked" value="1" onclick="getContraSelect(1);" name="theVSForm.ip_type"/><label>自动获得IP设置</label>
					<input type="radio"  value="2" onclick="getContraSelect(2);" name="theVSForm.ip_type"/><label>使用以下IP设置</label>
				</td>
			</tr>
			<tr style="display: none;" id="ip_address">
				<td class="til">
					IP地址：
				</td>
				<td >
					<s:textfield  name="theVSForm.ip" cssStyle="width:150px;   height:20px;" id="ip_address"></s:textfield>
				</td>
			</tr>
			<tr style="display: none;" id="sub_net">
				<td class="til" >
					子网掩码：
				</td>
				<td >
					<s:textfield name="theVSForm.sub_net" cssStyle="width:150px;   height:20px;" id="sub_net"></s:textfield>
				</td>
			</tr>	
			<tr style="display: none;" id="vm_kernel">
				<td class="til">
					VMkernel默认网关：
				</td>
				<td >
					<s:textfield name="theVSForm.vm_kernel" cssStyle="width:150px;   height:20px;" id="vm_kernel"></s:textfield>
				</td>
			</tr>		
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theVSForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
	</s:form>
</body>
