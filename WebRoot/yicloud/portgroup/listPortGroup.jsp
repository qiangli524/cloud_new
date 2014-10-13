<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" >
	var name = null;
	var hostName = null;
	var vssType = null;
 	function addRequest() {
 		if(vssType == 0){
 			theForm.flag.value = 0;
 			var vSwitchId = theForm.vSwitchId.value;
 			var url = 'portgroup_testVLAN.do';
 			$.getJSON(url,{"time":new Date().toString()},function(date){
 				if(date.result==0){
 					alert("全局变量中没有VLAN信息!\n请在全局变量中配置VLAN!");
 				}else{
 					theForm.action = 'portgroup_addPortGroup.do?vSwitchId='+vSwitchId+'&NAME='+name+'&hostName='+hostName+'&vssType='+vssType;
 					theForm.submit();
 				}
 			});
 		}else{
 			alert("分布式端口组暂不支持操作！");
 		}
	}
	function modRequest() { 
		if(vssType == 0){
			 var couterNum = 0;
		 	    var hostName = '<%=request.getAttribute("hostName")%>';
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      couterNum = couterNum + 1 ;
		 	      theForm.id.value = checkboxids[i].value;
		 	      }
		 	    }
		 	    theForm.flag.value = 1;
		 	    }
		 	    if(couterNum==0){
		 	    alert("请勾选需要配置的信息！");
		 	    return false ;
		 	    }else if(couterNum>1){
		 	    alert("一次只能处理单条信息");
		 	    return false ;
		 	    }
		 	    theForm.action = 'portgroup_modPortGroup.do?NAME='+name+'&hostName=' + hostName+'&vssType='+vssType;
				theForm.submit();
 		}else{
 			alert("分布式端口组暂不支持操作！");
 		}
 	}
 	function delRequest() {
 		var hostName = '<%=request.getAttribute("hostName")%>';
		if(vssType == 0){
			var pgname = '';
		 	var pguuid = '';
		 	var couterNum = 0;
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      couterNum = couterNum + 1 ;
		 	      theForm.id.value = checkboxids[i].value;
		 	      pgname = $(checkboxids[i]).attr("pgname");
		 	      pguuid = $(checkboxids[i]).attr("pguuid");
		 	      }
		 	    }
		 	    }
		 	    if(couterNum==0){
		 	    alert("请勾选需要删除信息！");
		 	    return false ;
		 	    }else if(couterNum>1){
		 	    alert("一次只能删除单条信息");
		 	    return false ;
		 	    }
		 	    var vSwitchId = theForm.vSwitchId.value;
		 	    if(confirm("确定要删除该端口组吗?")==true){
		 	    	theForm.action = 'portgroup_delPortGroup.do?NAME='+encodeURI(encodeURI(name))+"&vSwitchId="+vSwitchId+"&pgname="+encodeURI(encodeURI(pgname))+"&pguuid="+pguuid+"&vssType="+vssType+'&hostName=' + hostName;  
					theForm.submit();
		 	    }
 		}else{
 			alert("分布式端口组暂不支持操作！");
 		}
 	}
 	
 	$(function(){
           $("[name='listVm']").click(function(){
        	currentEdit=$(this);
        	var pguuid=$(this).attr("pguuid");
        	var vssName=$(this).attr("vssName");
        	var pgType = $(this).attr("pgType");
        	theForm.action = 'nic_listVm.do?pguuid='+pguuid+"&vssName="+vssName+"&pgType="+pgType;  
			theForm.submit();
              });
	      name = '<s:property value="#request.NAME"/>';
	      hostName = '<%=request.getAttribute("hostName")%>';
	      vssType = '<%=request.getAttribute("vssType")%>';
			});
</script>
</head>
<body>
<s:form action="#" method="post" id="theForm" cssClass="theForm">
<s:hidden name="thePGForm.flag" id="flag"></s:hidden>
<s:hidden name="thePGForm.vSwitchId" id="vSwitchId"></s:hidden>
<s:hidden name="thePGForm.id" id="id"></s:hidden>

<div class="tit-zzi">
		<div id="zi"><s:property value="#request.NAME"/>端口组</div>
</div>
 
 <div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="配置" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<%--  
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			--%>
		</div>
 <div class="scrollbody">
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>	<th>选择</th>
					<th onclick="sort(theTable,1,'string')">端口组名称</th> 
					<th onclick="sort(theTable,2,'string')">VLAN ID</th>
					<!-- 
					<th onclick="sort(theTable,3,'string')">端口组类型</th>
					 -->
					<th onclick="sort(theTable,4,'int')">连接虚拟机(个)</th>
					<!-- 
					<th>详情</th>
					 -->
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="thePGForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>" pgname="<s:property value="#theBean.name"/>" pguuid="<s:property value="#theBean.pguuid"/>"/></td>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.vlanId"/></td>
			  		<!-- 
			  		<td><s:property value="#theBean.type"/></td>
			  		 -->
			  		<s:if test="#theBean.numVm>0">
			  			<td><a href="javascript:;" pguuid='<s:property value="#theBean.pguuid"/>' pgType='<s:property value="#theBean.type"/>' vssName='<s:property value="#theBean.name"/>' name="listVm"><s:property value="#theBean.numVm"/></a></td>
			  		</s:if>
			  		<s:else>
			  			<td><s:property value="#theBean.numVm"/></td>
			  		</s:else>
			  		<!-- 
			  		<td><s:a href="portgroup_listPortGroupDetail.do"><s:property value="#theBean.name"/>详情</s:a></td>
			  		 -->
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>	
</s:form>
</body>
