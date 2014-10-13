<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link_export.jsp"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/FusionCharts/FusionCharts.jqueryplugin.js"></script>
<%@ include file="/sxcloud/common/view.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<style>
	#but{
		margin-top: 4px;
		margin-bottom: 4px;
	}
</style>
<head>
<title></title>
<script type="text/javascript">
	$(function(){
		 var api = frameElement.api;
		 var w = api.opener;
			api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addVmHosts,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		$("#query").click(function(){
			mask('正在查询,请稍后....','0.5','0px');
			$("#theForm").submit();
		});
		
		$("#resert").click(function(){
			   $("input[flag='text']").val("");
			});
		function addVmHosts(){
			var lenth=0;
			var server_type = $("#server_type").val();
	 		var parentId = $("#uuid").val();
	 		var vm_ids = "";
	 		var vm_names = "";
		 	$('[name=checkboxid]:checkbox:checked').each(function(){
	    		vm_ids = vm_ids+$(this).val()+",";
	    		vm_names = vm_names+$(this).attr("title")+",";
	    		lenth +=1;
	    	});
	    	if(lenth == 0){
	    		alert("请勾选一项！");
	    		return false;
	    	}
	    	w.saveVmHosts(parentId,vm_ids,vm_names,server_type);
		return true;
		}
	})
    	
</script>
</head>
<body  class="pop-body scrollbody">
<s:form action="paasBusiTree_addVmHosts.do" method="post" cssClass="theForm" id="theForm">
	<s:hidden name="uuid" id="uuid"></s:hidden>
	<s:hidden name="server_type" id="server_type"></s:hidden>
	<div >
                <table width="100%" class="querytable" border="0">
                  <tr>
                  	<td class="til">
								数据中心:
					</td>
					<td>
						<s:select list="vf.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
							name="vf.center_uuid" value="vf.center_uuid"></s:select>
					</td>
                    <td class="til">虚拟机名称:</td>
                    <td>
                    	<s:textfield name="vf.vmName" cssStyle="width:100px;"  flag="text"></s:textfield>
                    </td>
                    <td class="til">IP地址:</td>
                    <td>
                    	<s:textfield name="vf.ip" cssStyle="width:100px;" flag="text"></s:textfield>
                    </td>                    
                  </tr>
                  <tr>
                    <td colspan="10" class="btns" >
                        <div align="center" id="but">
							<input type = "button" class="thickbox btn-style02" value = "查询" id="query"/>
							<input type = "button" class="btn-style02" value = "重置" id="resert"/>
                        </div>
                    </td>
                  </tr>
                </table>
       <!--query-form end -->
	
	<div style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
					<th>虚拟机名称</th>
					<th>IP地址</th>
					<th>业务子系统</th>
					<th>承载业务</th>
					<th>虚拟化类型</th>				
		    </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="vmReportFormList" id="theBean">
			  	<tr>
			  		<td>
			  			<input type="checkbox" value="<s:property value='#theBean.vmId'/>" name="checkboxid" title="<s:property value='#theBean.vmName'/>" />
			  		</td>
			  		<td> 
			  			<s:property value="#theBean.vmName" default="-"/>
			  		</td>
			  		<td> 
			  			<s:property value="#theBean.ip" default="-"/>
			  		</td>
			  		<td>
			  		    <s:property value="#theBean.busiParent" default="-"/>
                    </td>
			  		<td>
			  		    <s:property value="#theBean.busi" default="-"/>
                    </td>
			  		<td>
			  			<s:if test="#theBean.vmType == 1">
							VMWARE
						</s:if>
						<s:elseif test="#theBean.vmType == 3">
							XEN	
						</s:elseif>
						<s:else>
							其它
						</s:else>
                    </td>
			  	</tr>
			  	</s:iterator>
			  </tbody>
			</table>
			<div style="height:10px;"></div>
			<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
