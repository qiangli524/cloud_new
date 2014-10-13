<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>

<script type="text/javascript"> 
	var api = frameElement.api;
	var w = api.opener; 
	 	
	api.button({
		id:'OkAnd',
		name: '确定',
		callback:selecthost,
		focus: true
	},
	{
		id:'cancle',
		 name: '取消'
	});
	
	function selecthost() { 
		var lenth = 0;
	    var id = '';
	    $('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
	    if(id==null || id == ''){
	    	alert("请勾选一条信息！");
	 	    return false ;
	    }else if(lenth>1){
	   		alert("一次只能选择一条信息");
		    return false ;
		}
		var hostName = $("[name='checkboxid']:checked").attr("hostName");//主机名称
		var ip = $("[name='checkboxid']:checked").attr("ip");//主机标识
			api.get("addHost").getHostInfo(ip,hostName);
		}
	function selecthostByName(){
		host.submit();
	}
	function resetForm(){
		$("#eq_name").attr("value","");
	}
</script>
</head>
<body class="mainbody">
<s:form action="united_selectUnAllocatedHost.do" method="post" id="host">
<s:hidden name="vtype" id="vtype"></s:hidden>
<s:hidden name="connect_id" id="connect_id"></s:hidden>
<div class="pd-20 bgcolor-1">
					<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="fl">主机名称:</label>
							<s:textfield cssClass="inpt-1 fl"  name="host.eq_name" id="eq_name" />
						 </div>
						 <div class="filtrate-field">
						<span class="ubtn-1 mgl-20"><input type="button" value="查询"
									onclick="javascript:selecthostByName()" />
						</span>
						<span class="ubtn-2 mgl-20"><input type="button" value="重置"
									onclick="javascript:resetForm(document.getElementById('theForm'))" />
						</span>
					</div>
					</div>
					
					<div class="pd-20">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			       <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">主机名称</th>
				   <th onclick="sort(theTable,2,'string')">主机IP</th>
                   <th onclick="sort(theTable,3,'string')">用户名</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="resultList" >
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id" />" 
								hostName='<s:property value="#theBean.eq_hostname"/>' ip='<s:property value="#theBean.eq_ip"/>' 
							/></td>
							<td><s:property value="#theBean.eq_name" /></td>
							<td><s:property value="#theBean.eq_ip" /></td>
							<td><s:property value="#theBean.eq_hostname" /></td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
				</div>
			</div>
	</s:form>
</div>
</body>
</html:html>
