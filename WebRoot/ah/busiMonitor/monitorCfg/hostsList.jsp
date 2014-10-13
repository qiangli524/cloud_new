<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<head>
<title></title>
<script type="text/javascript">

var api = frameElement.api;
api.button({
    id:'OkAnd',
    name: '确定',
    callback:selectHost,
    focus: true
},
{
    id:'cancle',
    name: '取消'
});

function selectHost(){
	var couterNum = 0;
	var ids;
	    var checkboxids = document.getElementsByName("checked");
	    if(checkboxids!=null&&checkboxids.length>0){
	    for(var i=0;i<checkboxids.length;i++){
	      if(checkboxids[i].checked){
	      couterNum = couterNum + 1 ;
	      ids = checkboxids[i].value;
	      }
	    }
	    }
	    if(couterNum==0){
	    alert("请勾选一条信息");
	    return false ;
	    }else if(couterNum>1){
	    alert("一次只能勾选一条信息");
	    return false ;
	    }
	    var id = ids.split('_');
		api.get("add").addHost(id[0],id[1],id[2]);
}
 	$(function(){
	 	//查询		
	   	$("#search").click(function(){
	   		mask('正在查询,请稍后....','0.5','0px');
			theForm.submit();
	   	});
	   	//重置
	   	$("#resert").click(function(){
	   		theForm.eq_name.value = "";
			theForm.eq_ip.value = "";
	   	});
 	});
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="monitorCfg_queryHostList.do" method="post" cssClass="theForm" id="theForm">
	 <div class="pd-10 bgcolor-1">
				<div class="clearfix mgt-10">
					<table width="100%"  border="0">
						<tr>
							<td class="til">主机类型:</td>
						    <td><s:select cssClass="select-1 vm"  list="#{1:'物理主机',2:'虚拟主机'}" name="flag" id="flag"></s:select></td>
							<td class="til">主机名称:</td>
							<td><s:textfield name="eq_name" cssClass="txt" id="eq_name"></s:textfield></td>
							<td class="til">主机IP地址:</td>
							<td><s:textfield name="eq_ip" cssClass="txt" id="eq_ip"></s:textfield></td>
							<td>
								<span class="ubtn-1 mgl-20"><input type="button" id="search" value="查询" /></span>
								<span class="ubtn-2 mgl-20"><input type="button" id = "resert" value="重置" /></span>
							</td>
						</tr>
					</table>
				</div>
				<br/>
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th>选择</th>
					<th>主机名称</th>
					<th>IP地址</th>
					<th>主机类型</th>
		    </tr>
			  </thead>
			  <tbody>
				  <s:iterator value="hostList" id="theBean">
					<tr>
						<td><input type="checkbox" value="<s:property value="#theBean.eq_ip"/>_<s:property value="#theBean.sn"/>_<s:property value="#theBean.eq_ip"/>_<s:property value="#theBean.eq_name" />" name="checked" id="id"/></td>
						<td>
							<s:property value="#theBean.eq_name" /></a>
						</td>	
						<td>
							<s:property value="#theBean.eq_ip" />
						</td>										
						<td>
							<s:if test="#theBean.eq_type == 1">
								IBM小型机
							</s:if>
							<s:elseif test="#theBean.eq_type == 2">
								IBM刀片
							</s:elseif>
							<s:elseif test="#theBean.eq_type == 3">
								IBM普通刀片
							</s:elseif>
							<s:elseif test="#theBean.eq_type == 4">
								HPx86刀片
							</s:elseif>
							<s:elseif test="#theBean.eq_type == 5">
								机架服务器
							</s:elseif>
						</td>
					</tr>					
				  </s:iterator>
			  </tbody>
			</table>
			<div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
</s:form>
</body>
