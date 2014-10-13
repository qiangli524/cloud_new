<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
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
		 
		 $("[name='selectVM']").click(function(){
	        	currentEdit=$(this);
	    		w.$.dialog({
	    			id:'selectVM',
	    			title:'选择虚拟机',
	    			width: '950px',
	    			height: '500px',
	    		    lock:true,
	    			content: 'url:temman_getVMList.do'
	    			});
	          });
	})
 function createTem(){
	var vmName = $("#vmName").val();
	var name = $("#name").val();
	var remark = $("#remark").val();
	if(vmName=="" || vmName==null){
		alert('请先选择虚拟机');
		return false;
	}
	if(name=="" || name==null){
		alert("请填写模板名称");
		$("#name").focus();
		return false;
	}
	if(remark.length == 0){
		alert("请填写模板说明");
		$("#remark").focus();
		return false;
	}
 	var type=$("input[name='type']:checked").val();
 	var system = $("#system").text();
 	var str = $("#obj").serialize();
 	var url = "temman_createTem.do?obj.type="+type+"&"+str+"&obj.system="+system;
 	w.createTem(url);
 }
	/*
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
	*/
	function pageOnLoad(){
		///getVMList();
		//listVMInfo();
	}
	//列示虚拟机的信息
	function listVMInfo(vmCode,connectId){
		var url = "temman_listVMInfo.do?obj.templateCode="+vmCode+"&obj.connectId="+connectId;
		$.getJSON(url,{"time":new Date().toString()},function(data){
				$("#cpu").html(data.VH_CPU+" 个");
				$("#mem").html(data.VH_MEM + " M");
				$("#store").html(data.VH_STORAGE+" G");
				$("#system").html(data.VH_SYSTEM);
				$("#hostCode").attr("value",data.hostCode);
				$("#connectId").attr("value",data.connectId);
				$("#templateCode").attr("value",vmCode);
				$("#vmName").attr("value",data.VH_NAME);
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
	
	///选择虚拟机列表
	$(function(){
		
	})
</script>
</head>
<body onload="pageOnLoad()" class="pop-body scrollbody">
<div class="pd-20 bgcolor-1">
         <div class="bord-1 pd-10">
		<s:form action="" method="post" id="obj" >
		<s:hidden name="obj.hostCode" id="hostCode"></s:hidden>
		<s:hidden name="obj.connectId" id="connectId"></s:hidden>
		<s:hidden name="obj.templateCode" id="templateCode"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<tr align="left">
					<td class="til">模板类型</td>
					<td>
						<input type="radio" name="type" checked="checked" value="1" /><label>Vmware模板</label>
						<input type="radio" name="type" value="3" /><label>Xen模板</label>
						<!--  
						<input type="radio" name="type" value="" /><label>OVF模板</label>
						-->
					</td>
				</tr> 
				<tr align="left">
					<td class="til">虚拟机<font color="red">*</font></td>
					<!-- 
					<td>
						<s:select list="#{'':''}" id="VH_UUID"   cssStyle="width:220px;   height:25px;" onchange="listVMInfo();"></s:select>
					</td>
					 -->
					 <td><s:textfield name="" id="vmName" disabled="true" cssClass="inpt-2"></s:textfield>
					 <input type="button" value="选择" name="selectVM" class="ubtn-3 vm mgl-3"/>
					 </td>
				</tr> 
				
				<tr align="left">
					<td class="til">CPU</td>
					<td>
						<div name="obj.cpu" id="cpu"/>
<%--						<s:textfield id="cpu" cssStyle="width:50px;   height:20px;" name="obj.cpu" cssClass="inpt-2"></s:textfield>个--%>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">内存</td>
					<td>
						<div name="obj.mem" id="mem"/>
<%--						<s:textfield id="mem" cssStyle="width:50px;   height:20px;" name="obj.mem" cssClass="inpt-2"></s:textfield>M--%>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">存储</td>
					<td>
						<div name="obj.store" id="store"/>
<%--						<s:textfield id="store" cssStyle="width:50px;   height:20px;" name="obj.store" cssClass="inpt-2"></s:textfield>G--%>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">操作系统</td>
					<td>
						<div name="obj.system" id="system"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">模板名称<font color="red">*</font></td>
					<td>
					<s:textfield name="obj.name" id="name" cssClass="required inpt-2" value="新建模板"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">是否公有</td>
					<td>
					<input type="radio" name="obj.isPublic" checked="checked" value="0"/><label>公有</label>
					<input type="radio" name="obj.isPublic" value="1"/><label>私有</label>
					</td>
				</tr>
<%--				<tr align="left">--%>
<%--					<td class="til">是否为物理模板</td>--%>
<%--					<td>--%>
<%--					<input type="radio" name="obj.isPhysical"  value="1"/><label>是</label>--%>
<%--					<input type="radio" name="obj.isPhysical" value="0" checked="checked"/><label>否</label>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr align="left">--%>
<%--					<td class="til">位置</td>--%>
<%--					<td>--%>
<%--					<s:textfield name="obj.position" id="position" cssClass="inpt-2"></s:textfield>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr align="left">--%>
<%--					<td class="til">用户名</td>--%>
<%--					<td>--%>
<%--					<s:textfield name="obj.username" id="username" cssClass="inpt-2"></s:textfield>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr align="left">--%>
<%--					<td class="til">密码</td>--%>
<%--					<td>--%>
<%--					<s:password name="obj.password" id="password" cssClass="inpt-2"></s:password>--%>
<%--					</td>--%>
<%--				</tr>--%>
				<tr align="left">
					<td class="til">模板说明<font color="red">*</font></td>
					<td>
					<s:textarea name="obj.remark" id="remark" cssStyle="box" cols="40" rows="4" cssClass="textarea-1"></s:textarea>
					<br />
					<span>
						<font color="red">
							格式： 操作系统类型:操作系统版本,软件描述  ，如：
							<br/>RedHat Enterprise Linux:RHEL 6.1,mysql 5.5.20
							<br/>
							请严格遵守该格式！
						</font>
					</span>
					</td>
				</tr>  
			</table>
</s:form>
</div>
    </div>
</body>
