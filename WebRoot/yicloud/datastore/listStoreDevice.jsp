<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
String excel_export_jsp = request.getContextPath() + "/excel/excel_export.jsp";
%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">
</style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript">

$(function(){
		$("a[name='usedStoreNum']").click(function(){
			var sDeviceId=$(this).parent().attr("sDeviceId");
			$.dialog({
				id:'listDateStoreBlock',
				title:'展示存储块',
				width: '1000px',
				height: '520px',
			    lock:true,
				content: 'url:datastore_liststorage.do?sDeviceId='+sDeviceId
				});
		});
		
		$("a[name='relevanceStoreDevice']").click(function(){
			var storeDeviceId=$(this).parent().attr("sDeviceId");
			$.dialog({
				id:'relevanceStoreDevice',
				title:'关联存储块',
				width: '1100px',
				height: '580px',
			    lock:true,
				content: 'url:datastore_relevanceStoreDevice.do?dialogId=relevanceStoreDevice'+"&storeDeviceId="+storeDeviceId+"&operType=relevance"
				});
		});
		
		$("a[name='cancleRelevance']").click(function(){
			var storeDeviceId=$(this).parent().attr("sDeviceId");
			$.dialog({
				id:'cancleRelevance',
				title:'取消存储块关联',
				width: '1100px',
				height: '580px',
			    lock:true,
				content: 'url:datastore_cancleRelevance.do?storeDeviceId='+storeDeviceId+"&dialogId=cancleRelevance"+"&operType=cancle"
				});
		});
		
		/*
		$("[name='addDiskGroup']").unbind().live("click",function(){
			var deviceId = $(this).parent().attr("deviceId");
			$.dialog({
				id:'adddiskgroup',
				title:'添加磁盘组',
				width:'600px',
				height:'400px',
				locK:true,
				content:'url:diskgroup_add.do?diskGroupObj.storeDeviceId='+deviceId
			});
		});
		*/
	});
	
	function resetForm(theForm){
		$("#queryName").attr("value","");
		$("#queryType").attr("value","");
	}
	
	function searchRequest(){
		theForm.submit();
	}
	
	function addRequest() {
		$.dialog({
			id:'addStoreDevice',
			title:'添加存储设备',
			width: '550px',
			height: '400px',
		    lock:true,
			content: 'url:datastore_addStoreDevice.do?dialogId=addStoreDevice'
			});
	}
	function modRequest() { 
		var id = null;
	    var couterNum = 0;
	    var checkboxids = document.getElementsByName("checkboxid");
	    if(checkboxids!=null&&checkboxids.length>0){
		    for(var i=0;i<checkboxids.length;i++){
		      if(checkboxids[i].checked){
		      couterNum = couterNum + 1 ;
		      id = checkboxids[i].value;
		      }
		    }
	    }
	    if(couterNum==0){
	    alert("请勾选需要修改信息！");
	    return false ;
	    }else if(couterNum>1){
	    alert("一次只能处理单条信息");
	    return false ;
	    }
	    $.dialog({
			id:'modStoreDevice',
			title:'修改存储设备',
			width: '450px',
			height: '400px',
		    lock:true,
			content: 'url:datastore_modStoreDevice.do?id='+id+"&dialogId=modStoreDevice"
			});
	}
	
	function delRequest() {
		var couterNum = 0;
		var id = null;
	    var checkboxids = document.getElementsByName("checkboxid");
	    if(checkboxids!=null&&checkboxids.length>0){
	    for(var i=0;i<checkboxids.length;i++){
	      if(checkboxids[i].checked){
	      couterNum = couterNum + 1 ;
	      id = checkboxids[i].value;
	      }
	    }
	    }
	    if(couterNum==0){
	    alert("请勾选需要删除功能信息！");
	    return false ;
	    }else if(couterNum>1){
	    alert("一次只能删除单条功能信息");
	    return false ;
	    }
	    if(confirm("确认要删除该存储设备么？")){
	    	theForm.action = 'datastore_delStoreDevice.do?id='+id
			theForm.submit();
	    }
	}
	
	function saveStoreDevice(dialogId,formData){
		var theForm = document.getElementById('theForm');
		////barEnd(dialogId);
		theForm.action = 'datastore_saveStoreDevice.do?'+formData  
		theForm.submit();
	}
	
	function saveRelevance(dialogId,operType,operIds,storeDeviceId){
		//barEnd(dialogId);
		theForm.action = 'datastore_saveRelevance.do?operType='+operType +"&operIds="+operIds +"&storeDeviceId="+storeDeviceId
		theForm.submit();
	}
	
	function barEnd(idstr){
		$.dialog.list[idstr].close();
	}
	//新建热备盘
	function addHotBackDisk(){
		var count=0;
		var id ='';
		$("input:checkbox[name='checkboxid']:checked").each(function(){
			count ++ ;
			id = $(this).val();
			if(count>1){
				alert('请先选择一条记录进行操作');
				return false;
			}
		});
		$.dialog({
			id:'addHotBackDisk',
			title:'新建热备份',
			width: '450px',
			height: '400px',
		    lock:true,
			content: 'url:datastore_addHotBackDiskPage.do?storeDeviceObj.id='+id
			});
	}
	//新建磁盘组
	function addDiskGroup(){
		var count=0;
		var id ='';
		$("input:checkbox[name='checkboxid']:checked").each(function(){
			count ++ ;
			id = $(this).val();
			if(count>1){
				alert('请先选择一条记录进行操作');
				return false;
			}
		});
		$.dialog({
			id:'addHotBackDisk',
			title:'新建磁盘组',
			width: '450px',
			height: '400px',
		    lock:true,
			content: 'url:datastore_addDiskGroupPage.do?storeDeviceObj.id='+id
			});
	}
	
	//新建LUN
	function addLUN(){
		var count=0;
		var id ='';
		$("input:checkbox[name='checkboxid']:checked").each(function(){
			count ++ ;
			id = $(this).val();
			if(count>1){
				alert('请先选择一条记录进行操作');
				return false;
			}
		});
		$.dialog({
			id:'addHotBackDisk',
			title:'新建LUN',
			width: '450px',
			height: '400px',
		    lock:true,
			content: 'url:datastore_addLUNPage.do?storeDeviceObj.id='+id
			});
	}
	//导出
	function listExp(){
		var name = theForm.queryName.value;
		var type = theForm.queryType.value;
		var url="<%=excel_export_jsp%>?key=storedeviceinfo";
		if (name!='') {
		    	url = url + "&name=" + name;
		}
	    if (type > 0) {
	    	url = url + "&type=" + type;
	    }
	    exportForm.action =url;
	    exportForm.submit();
	}
</script>
</head>
<body class="pop-body scrollbody">
<div class="mainbody">
<s:form action="datastore_listStoreDevice.do" method="post" id="theForm">
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">存储设备管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">存储设备名称：</label>
				<s:textfield name="storeDeviceObj.queryName" cssClass="inpt-1 vm"
								id="queryName" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">存储设备类型：</label>
				<s:select cssClass="select-1 vm" id="queryType" name="storeDeviceObj.queryType" list="#{'':'--请选择--','0':'本地存储','1':'SAN','2':'NAS'}"></s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
				<a class="icon-export" href="javascript:void(0)" onclick="listExp();return false;" >导出</a>
			</div>

			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
			  		<th>选择</th>
					<th>设备编号</th>
					<th>设备名称</th>
					<th>IP地址</th>
					<th>设备状态</th>
					<th>类型</th>
					<th>总量(G)</th>
					<th>可用空间(G)</th>
					<th>关联存储块</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
			  		<td><s:property value="#theBean.device_num"/></td>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.ip"/></td>
			  		<td>
			  			<s:if test="#theBean.status==1">可用</s:if>
			  			<s:else>故障</s:else>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.type==0">
			  				本地存储
			  			</s:if>
			  			<s:elseif test="#theBean.type==1">
			  				SAN
			  			</s:elseif>
			  			<s:elseif test="#theBean.type==2">
			  				NAS
			  			</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.capacity"/></td>
			  		<td><s:property value="#theBean.freeSpace"/></td>
			  		<td sDeviceId="<s:property value="#theBean.id"/>">
			  			<a href='javascript:;' name='relevanceStoreDevice'>关联存储块</a>
			  			<a href='javascript:;' name='cancleRelevance'>取消关联</a>	
			  		</td>
			  		
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages">
			</div>	
		 </div>
</s:form>
</div>
<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
</body>
