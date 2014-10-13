<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript">
		var basePath  = '<%=request.getScheme()%>' +"://" + '<%=request.getServerName()%>' +":" 
				+ '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>' +"/";
		$(function(){
		 $("[name='add']").click(function(){
        	currentEdit=$(this);
        	var hostCode = '<%=request.getAttribute("hostCode")%>';
    		$.dialog({
    			id:'addNetWork',
    			title:'添加网络',
    			width: '700px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:'+basePath+'portgroup_addNetWork.do?hostCode='+hostCode
    			});
              });
              
           $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	 var ID=$(this).attr("ID");
        	 var vssUuid=$(this).attr("vssUuid");
        	 var NAME=$(this).attr("sName");
        	 var hostName=$(this).attr("hostName");
        	 var vssType=$(this).attr("vssType"); 
    		$.dialog({
    			id:'vdi',
    			title:'端口组信息',
    			width: '890px',
    			height: '480px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:'+basePath+'portgroup_listPortGroup.do?vssUuid='+vssUuid+"&NAME="+NAME+"&hostName="+hostName+"&ID="+ID+"&vssType="+vssType
    			});
              });
                  
           $("[name='listNic']").click(function(){
        	currentEdit=$(this);
        	 var vssUuid=$(this).attr("vssUuid");
        	 var vssName=$(this).attr("vssName");
        	 var vssType=$(this).attr("vssType"); 
    		$.dialog({
    			id:'vdi',
    			title:'物理适配器',
    			width: '890px',
    			height: '480px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:'+basePath+'nic_listNic.do?vssUuid='+vssUuid+"&vssName="+vssName+"&vssType="+vssType
    			});
              });    
              
			});
			
		function searchRequest(){
			var name = $("#name").val();
			theForm.action= "portgroup_listVirtualSwitch.do?name="+name;
			theForm.submit();
		}
		
	function resetForm(theForm){
		theForm.name.value = '';
	}
	
	function list(){
		theForm.action="portgroup_listVirtualSwitch.do";
		theForm.submit();
	}
	$(function(){
		$(":checkbox[name='check']").click(function(){
			if(this.checked){
				$(":checkbox[name='check']").not(this).attr("checked",false);
			} 
		});
	});
	function del(){
		var $checked = $("[name='check']:checked");
		if($checked.length == 0){
			alert("请选择要删除的端口组");
			return false;
		}
		vsName = $checked.val();
		if(vsName == "vSwitch0"){
			alert("不能删除vSwitch0的虚拟交换机!");
			return false;
		}
		url =  "portgroup_removeVirtualSwitch.do?name=" + vsName + "&hostCode=" + $checked.attr("hostCode") +"&vsId="+ $checked.attr("vsId");
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result == "1"){
				alert("删除虚拟交换机" +vsName+ "成功!");
				window.location.reload();
			}else if(data.result == "-1"){
				alert("删除虚拟交换机" + vsName + "失败!可能的原因：" +data.reason);
				window.location.reload();
			}
		})
	}
	
	function saveNetWork(url,hostCode){
		closeDialog("addNetWork");
		bar("saveNetWork","正在添加网络");
		var theForm = $("#theForm")[0];
		$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == 1){
					barEnd("saveNetWork","成功添加");
					theForm.action="portgroup_listVirtualSwitch.do?host_code="+ hostCode + "&oper=1";
					theForm.submit();
				}else{
					barEnd("saveNetWork","添加失败");
				}
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
	
	function closeDialog(idstr){
		$.dialog.list[idstr].close();
	}
	</script>
</head>
<body>
<s:form action="#" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<div class="scrollbody">
	
	<!--query-form end -->

	<div class="blue-wrap noborder">
	<div class="table-head">
	<ul class="btns">
		<li><input type="button" class="thickbox btn-style02" value="添加" name="add"/></li>
		<li><input type="button" class="thickbox btn-style02" value="删除" name="delete" onclick="del()"/></li>
	</ul>
	
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
			  		<th>选择</th>
					<th onclick="sort(theTable,1,'string')">虚拟交换机名称</th> 
					<th onclick="sort(theTable,2,'int')">端口数</th>
					<th onclick="sort(theTable,3,'int')">可用端口数</th>
					<th onclick="sort(theTable,4,'string')">主机</th>
					<th onclick="sort(theTable,5,'int')">物理适配器(个)</th>
					<th onclick="sort(theTable,6,'string')">端口组</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theVSForm.resultList" id="theBean">
			  	<tr>
			  		<td><input type="checkbox" name="check" value="<s:property value="#theBean.name"/>" hostCode="<s:property value="#theBean.hostCode"/>"  vsId="<s:property value="#theBean.id"/>"></input></td>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.numPorts"/></td>
			  		<td><s:property value="#theBean.numPortsAvailable"/></td>
			  		<td><s:property value="#theBean.hostName"/></td>
			  		<s:if test="#theBean.numNic>0">
						<td><a href="javascript:;" vssUuid='<s:property value="#theBean.vssuuid"/>' vssType='<s:property value="#theBean.type"/>' vssName='<s:property value="#theBean.name"/>'  name="listNic"><s:property value="#theBean.numNic"/></a></td>			  			
			  		</s:if>
			  		<s:else>
			  			<td><s:property value="#theBean.numNic"/></td>
			  		</s:else>
			  		<td><a href="javascript:;" ID='<s:property value="#theBean.id"/>' vssUuid='<s:property value="#theBean.vssuuid"/>' vssType='<s:property value="#theBean.type"/>'  sName='<s:property value="#theBean.name"/>' hostName='<s:property value="#theBean.hostName"/>' name="detail">查看所有端口组</a></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
 </div>
	</div>	
</div>
</s:form>
</body>
