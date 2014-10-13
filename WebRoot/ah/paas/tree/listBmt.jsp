<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../../sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<%@ include file="../../../sxcloud/common/view.jsp"%>
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
		     callback:selectBmt,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
	
	function selectBmt(){
		var lenth =0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		lenth +=1;
    	 });
    	if(lenth==0){
    		alert('请选择一个业务，然后点击确认按钮');
    		return false;
    	}
    	var id   = $('[name=obj.id]:checkbox:checked').attr("bmtid");
    	var name = $('[name=obj.id]:checkbox:checked').attr("bmtname");
    	var jspName = $("#jspName").val();
    	api.get(jspName).listBmt(id,name);
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
<s:hidden name="jspName" id="jspName"></s:hidden>
<div class="pd-20 bgcolor-1">
<div class="bord-1 pd-10">		
	<div class="clearfix filtrate-area">
        	<div class="filtrate-field">
        		<label class="mgl-20 vm">业务名称:</label>
        		<s:textfield name="bmtObj.name" id="name" cssClass="inpt-1 vm"></s:textfield>
        	</div>
        	<div class="filtrate-field">
        		<label class="mgl-20 vm">业务类型:</label>
        		<s:select id="type" name="bmtObj.type" cssClass="select-1 vm" list="#{'':'--请选择--','0':'业务中心','1':'业务系统','2':'业务子系统','4':'承载业务'}" ></s:select>
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
				<th onclick="sort(theTable,0,'string')">名称</th>
				<th onclick="sort(theTable,1,'string')">简称</th>
				<th onclick="sort(theTable,2,'string')">类型</th>
				<th onclick="sort(theTable,3,'string')">上级名称</th>
				<th onclick="sort(theTable,4,'string')">创建时间</th>
				<th onclick="sort(theTable,5,'string')">描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  		<td>
			  			<input type="checkbox" name="checkboxid" bmtid='<s:property value="#theBean.id"/>' bmtname="<s:property value="#theBean.name"/>" />
			  		</td>
			  		<td><s:property value="#theBean.name"/></td>
			  		<td><s:property value="#theBean.number"/></td>
			  		<td><s:if test="#theBean.type==0">业务中心</s:if>
			  			<s:elseif test="#theBean.type==1">业务系统</s:elseif>
			  			<s:elseif test="#theBean.type==2">业务子系统</s:elseif>
			  			<s:elseif test="#theBean.type==4">承载业务</s:elseif>
			  			<s:else>--</s:else>
			  		</td>
			  		<td><s:property value="#theBean.name1"/></td>
			  		<td><s:property value="#theBean.insertdate"/></td>
			  		<td width="200"> 
			  			<div class="hidden" title='<s:property value="#theBean.desc"/>'>
			  				<s:property value="#theBean.desc"/></a>
			  			</div> 
			  		</td>
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
