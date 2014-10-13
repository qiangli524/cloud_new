<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<%@ include file="../../sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>

<title></title>
<script type="text/javascript">
	function resetForm(){
		$("#type").attr("value","");
		$("#name").attr("value","");
	}
	function searchRequest(){
		obj.submit();
	}
	
	var api = frameElement.api;
	var w = api.opener;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectVM,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
	
	function selectVM(){
		var lenth =0;
    	$('[name=obj.id]:checkbox:checked').each(function(){
    		lenth +=1;
    	 });
    	if(lenth==0){
    		alert('请选择一个虚拟机，然后点击确认按钮');
    		return false;
    	}
    	var vmCode = $('[name=obj.id]:checkbox:checked').attr("vmCode");
    	var connectId = $('[name=obj.id]:checkbox:checked').attr("connectId");
    	api.get("addtem").listVMInfo(vmCode,connectId);
	}
</script>
</head>
  <style type="text/css">
		div.hidden{
		width:200px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="obj">
<div class="pd-20 bgcolor-1">
<div class="bord-1 pd-10">			
	<div class="clearfix filtrate-area">
        	<div class="filtrate-field">
        		<label class="mgl-20 vm">虚拟机名称:</label>
        		<s:textfield name="obj.name" id="name" cssClass="inpt-1 vm"></s:textfield>
        	</div>
        	<div class="filtrate-field">
        		<label class="mgl-20 vm">虚拟化类型:</label>
        		<s:select id="type" name="obj.type" cssClass="select-1 vm" list="#{'':'--请选择--','1':'Vmware','3':'Xen','9':'其他'}" ></s:select>
        	</div>
        	<div class="filtrate-field">
        		<span class="ubtn-1 mgl-20"><input type = "button" value = "查询" onclick = "javascript:searchRequest()" /></span>
				<span class="ubtn-2 mgl-20"><input type = "button" value = "重置" onclick = "javascript:resetForm()" /></span>
        	</div>
        </div><!--query-form end -->
        <div class="mgt-20" />
	<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-ct">
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>	
			  		<th>选择</th>
					<th onclick="sort(theTable,0,'string')">虚拟机名称</th>
					<th onclick="sort(theTable,1,'string')">IP地址</th>
					<th onclick="sort(theTable,2,'string')">虚拟化类型</th>
					<th onclick="sort(theTable,4,'string')">操作系统</th>
					<th onclick="sort(theTable,5,'string')">CPU</th>
					<th onclick="sort(theTable,6,'string')">内存</th>
					<th onclick="sort(theTable,7,'string')">存储</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr id="<s:property value="#theBean.VH_UUID"/>" vhid='<s:property value="#theBean.VH_UUID"/>'>
			  		<td><input type="checkbox" vmCode="<s:property value="#theBean.VH_UUID"/>" connectId="<s:property value="#theBean.connectId"/>" name="obj.id"/></td>
			  		<td> 
			  			<div class="hidden" title='<s:property value="#theBean.VH_NAME"/>'>
			  				<s:property value="#theBean.VH_NAME"/></a>
			  			</div> 
			  		</td>
			  		<td><s:property value="#theBean.VH_IP"/></td>
			  		<td><s:if test="#theBean.VH_TYPE==1">Vmware</s:if>
			  			<s:else>Xen</s:else>
			  		</td>
			  		<td width="200"> 
			  			<div class="hidden" title='<s:property value="#theBean.VH_SYSTEM"/>'>
			  				<s:property value="#theBean.VH_SYSTEM"/></a>
			  			</div> 
			  		</td>
			  		<td><s:property value="#theBean.VH_CPU"/>核</td>
			  		<td><s:property value="#theBean.VH_MEM"/>M</td>
			  		<td><s:property value="#theBean.VH_STORAGE"/>M</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
</div>
</div>
</s:form>
</body>
