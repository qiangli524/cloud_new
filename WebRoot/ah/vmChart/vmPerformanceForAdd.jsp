<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<style>
	#but{
		margin-top: 4px;
		margin-bottom: 4px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#query").click(function(){
			mask('正在查询,请稍后....','0.5','0px');
			$("#theForm").submit();
		});
		
		$("#resert").click(function(){
			   $("input[flag='text']").val("");
			});
		
	})
    function chanageRq(obj){
      	$("#dateBoxId").hide();
      	if(obj.value == 'selfDate'){
      		$("#dateBoxId").show();
      	}
      }
/* 	function selAll(a){
		if(!a.checked){
			$("input[name='vmButtName']").each(function(){
			$(this).attr("checked",true);
			});
		}else{
			$("input[name='vmButtName']").each(function(){
			$(this).attr("checked",false);
			});
		}
	} */
   	
</script>
</head>
<body >
<div class="mainbody">
<s:form action="vmReportForm_vmPerformanceForAdd.do" method="post" cssClass="theForm" id="theForm">
	<div class="pd-20 bgcolor-1">
		<div class="bord-1 pd-10">
            <table width="100%" border="0">
              <tr>
              	<td class="til">数据中心:</td>
				<td>
						<s:select list="vf.dataCenterList" id="center_uuidd" headerKey="" headerValue="---请选择---" listKey="uuid" listValue="name"
							name="vf.center_uuid" value="vf.center_uuid" cssClass="select-1 fl"></s:select>
				</td>
                <td class="til">虚拟机名称:</td>
                <td>
                   	<s:textfield name="vf.vmName" cssStyle="width:100px;"  flag="text" cssClass="inpt-1 vm"></s:textfield>
                </td>
                <td class="til">业务名称:</td>
                <td>
                    <s:textfield name="vf.busi" cssStyle="width:100px;" flag="text"></s:textfield>
                 </td>		 	 	    	                  
              </tr>
              <tr>
                 <td colspan="10">
                    <div align="center" id="but" >
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="query"/></span>
							<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="resert"/></span>
                        </div>    
                 </td>   
              </tr>    
            </table>    
			<table id = "theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  	<tr>
			  		<th>选择</th>
					<th>虚拟机名称</th>
					<th style="width: 10px;">业务子系统</th>
					<th >承载业务</th>
				<!-- 	<th>网络域</th> -->				
		    	</tr>
			  </thead>
			  <tbody>
			  <s:iterator value="vmReportFormList" id="theBean">
			  	<tr>
			  		<td>
			  			<input type="checkbox" value="<s:property value='#theBean.vmName'/>" name="vmButtName" title="<s:property value='#theBean.vmId'/>" />
			  		</td>
			  		<td align="center"> 
			  			<s:property value="#theBean.vmName" default="-"/>
			  		</td>
			  		<td >
			  		    <s:property value="#theBean.busiParent" default="-"/>
                    </td>
			  		<td>
			  		    <s:property value="#theBean.busi" default="-"/>
                    </td>
			  		<%-- <td>
			  		    <s:property value="#theBean.network" default="-"/>
                    </td> --%>
			  	</tr>
			  	</s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
	</div>
</s:form>
</div>
</body>
</html:html>