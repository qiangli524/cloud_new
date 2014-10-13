<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css"	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/hlj/report/js/customReport.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/underscore/underscore-1.4.4.js"></script>
<style>
	#but{
		margin-top: 4px;
		margin-bottom: 4px;
	}
	td{
		padding:2px 8px;
	}
</style>
<script type="text/javascript">
	$(function(){
		$("#query").click(function(){
			mask('正在查询,请稍后....','0.5','0px');
			$("#theForm").submit();
		});
		
		$("#resert").click(function(){
			    $('#dataCenterId').val('-1');
				$('#clusterId').val('-1');
				$('#vhName').val('');			   
		});
			
		//初始化数据中心
		initDataCenter();	
		//数据中心改变事件，加载集群
		$('#dataCenterId').live({
			change: function() {
				var val = $(this).val();
				renderCluster(val);
			},
		})	
		$('#dataCenterId').val('${obj.dataCenterId}');
		renderCluster('${obj.dataCenterId}');
		$('#clusterId').val('${obj.clusterId}');
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
<s:form action="customReport_hostlist.do" method="post" cssClass="theForm" id="theForm">
	<div class="pd-20 bgcolor-1">
		<div class="bord-1 pd-10">
            <table width="100%" border="0">
              <tr>
              	<td class="til">数据中心:</td>
				<td>
					<select id="dataCenterId" onchange="renderCluster()"  name="obj.dataCenterId" class="select-1 fl" >
							<option value="-1">所有</option>
					</select>
				</td>
				<td>集群: </td>
				<td>
					<select id="clusterId"  name="obj.clusterId" class="select-1 fl" >
							<option value="-1">所有</option>
					</select>
				</td>
			</tr>
             <tr> 
               	<td class="til">主机名称:</td>
                <td>
                   	<input type="text" name="obj.vhName" class="inpt-1 fl" value="${obj.vhName}"/>
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
					<th>主机名称</th>
					<th>IP地址</th>
		    	</tr>
			  </thead>
			  <tbody>
			  <s:iterator value="objList" id="theBean">
			  	<tr>
			  		<td>
			  			<input type="checkbox" class="inp-chb" value="<s:property value='#theBean.name'/>" name="vmButtName" title="<s:property value='#theBean.uuid'/>" />
			  			<span class="b-chb bon-chb"><b></b></span>
			  		</td>
			  		<td align="center"> 
			  			<s:property value="#theBean.name" default="-"/>
			  		</td>
			  		<td>
			  		    <s:property value="#theBean.ip" default="-"/>
                    </td>
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