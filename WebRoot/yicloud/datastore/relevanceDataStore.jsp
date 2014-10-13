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
	var api = frameElement.api;
	var w = api.opener;
	
	api.button({
	    id:'Ok',
	    callback:submitRequest,
	    name: '确定',
	    focus: true
	},
	{
	    id:'cancle',
	    name: '取消'
	});
	
	$(window).load(function() {
		var operType = $("#operType").val();
		if(operType=='relevance'){
			$("#theForm").attr("action","datastore_relevanceStoreDevice.do");
		}else{
			$("#theForm").attr("action","datastore_cancleRelevance.do");
		}
	});

	
	function submitRequest() { 
 	    var couterNum = 0;
 	    var operIds = "";
 	    var storeId = null;
 	    var storeType = null;
 	    var operType = $("#operType").val();
 	  	var dialogId = $("#dialogId").val();
 	  	var storeDeviceId = $("#storeDeviceId").val();
 	    var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids!=null&&checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1 ;
					storeId = $(checkboxids[i]).attr("value");
					storeType = $(checkboxids[i]).attr("dataStoreType");
					operIds = operIds+storeId+"@"+storeType+"~";
				}
			}
		}
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	   	 return false ;
 	    }
 	    w.saveRelevance(dialogId,operType,operIds,storeDeviceId);
 	}
	function resetForm(theForm){
		$("#queryName").attr("value","");
		$("#queryState").attr("value",'');
		$("#queryType").attr("value",'');
	}
	function searchRequest(){
		theForm.submit();
	}
</script>
</head>
<body class="mainbody">
<div class="mainbody">
<s:form action="" method="post" id="theForm">
<s:hidden id="dialogId" name="dialogId"></s:hidden>
<s:hidden id="storeDeviceId" name="storeDeviceId"></s:hidden>
<s:hidden id="operType" name="operType"></s:hidden>
<div class="pd-20 bgcolor-1">
<div class="bord-1 pd-10">			
	<div class="clearfix filtrate-area">
		<div class="filtrate-field">
		<label class="vm">存储块名称：</label>
		<s:textfield name="theForm.queryName" id="queryName" cssClass="inpt-1 vm"></s:textfield>
		</div>
		<div class="filtrate-field">
		<label class="mgl-20 vm">状态：</label>
		<s:select cssClass="select-1 vm" id="queryState" name="theForm.queryState" list="#{'':'--请选择--','connection':'正常','detach':'异常','destroy':'损坏'}"></s:select>
		</div>
		<div class="filtrate-field">
		<label class="mgl-20 vm">存储块格式：</label>
		<s:select cssClass="select-1 vm"  id="queryType" name="theForm.queryType" list="#{'':'--请选择--','NFS':'NFS','LVM':'LVM','ISO':'ISO','VMFS':'VMFS','UDEV':'UDEV','LVMOHBA':'LVMOHBA'}"></s:select>
		</div>
		<div class="filtrate-field">
		<span class="ubtn-1 mgl-20"><input type="button" onclick = "javascript:searchRequest()"  value="查询" /></span>
		<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm()" value="重置" /></span>
		</div>
	</div>
	<div class="mgt-20" />
	<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
		<thead>
		  <tr>
	  		<th>选择</th>
			<th>存储块名称</th>
			<th>存储块路径</th>
			<th>状态</th>
			<th>存储块格式</th>
			<th>总容量(G)</th>
			<th>可用容量(G)</th>
		  </tr>
		</thead>
	  <tbody>
	  <s:iterator value="resultList" id="theBean">
	  	<tr>
	  		<td><input name="checkboxid" type="checkbox"
				value="<s:property value='#theBean.store_uuid'/>" dataStoreType="<s:property value='#theBean.dataStoreType'/>"/></td>
	  		<td><s:property value="#theBean.NAME"/></td>
	  		<td align="center">
	  			<s:if test="#theBean.STORAGE_URL==null">
	  				-
	  			</s:if>
	  			<s:elseif test="#theBean.STORAGE_URL==''">
	  				-
	  			</s:elseif>
	  			<s:else>
	  				<a style="color: black;text-decoration: none;" class="font-more" title='<s:property value="#theBean.STORAGE_URL"/>' >
						<s:property value="#theBean.STORAGE_URL"/>
					</a>
	  			</s:else>
	  			
	  		</td>
	  		<td align="center">
	  			<s:if test="#theBean.STATE=='connection'">
	  				<img src="sxcloud/images/virtual/accept.png" style="margin-top: 6px"/>正常
	  			</s:if>
	  			<s:elseif test="#theBean.STATE=='detach'">
	  				异常
	  			</s:elseif>
	  			<s:elseif test="#theBean.STATE=='destroy'">
	  				损坏
	  			</s:elseif>
	  			<s:elseif test="#theBean.STATE==''">
	  				无
	  			</s:elseif>
	  			<s:else>
	  				<img src="sxcloud/images/virtual/accept.png" style="margin-top: 6px"/>正常
	  			</s:else>
	  		</td>
	  		<td><s:property value="#theBean.TYPE"/></td>
	  		<td><s:property value="@java.lang.Math@round(#theBean.CAPACITY/1024)"/></td>
	  		<td><s:property value="@java.lang.Math@round(#theBean.FREE_SPACE/1024)"/></td>
	  	</tr>
	  </s:iterator>
	  </tbody>
	</table>
	<div class="pages mgb-10">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
	</div>	
	
		</div>
</s:form>
</div>
</body>
