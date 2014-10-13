<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
</style>
<style type="text/css">
		
</style>
<script type="text/javaScript">
  	//parent.removeMask();
  	//setInterval(reloadMonitorData, 20000);//30秒刷新一下数据
    //刷新监控数据
	function reloadMonitorData(){
		    $("#obj").submit();
	     }    
	 function chang(){
	 	var va=$("#sel").val();
	 	if(va == 0){
	 		$("[name='state']").each(function(){$(this).parent().show();});
	 	}else if(va == 1){//只显示异常或备
	 		$("[name='state']").each(function(){$(this).parent().show();});
	 		$("[name='state']").each(function(){
	    	if($(this).find("font").html() == '正常' || $(this).find("font").html()== '主'){
	    			$(this).parent().hide();//隐藏行
	    		}
	    	});
	 	}else{//只显示正常
	 		$("[name='state']").each(function(){$(this).parent().show();});
	 		$("[name='state']").each(function(){
	    	if($(this).find("font").html() == '异常' || $(this).find("font").html() == '备'){
	    			$(this).parent().hide();
	    		}
	    	});
	 	}
	 	/* //隐藏所有的当前行只有一列，且第一个兄弟行也只有一列的那一行
		$("#tab").find("tr").each(function(){
			if($(this).next().next().find("td").length == 1){
				$(this).hide();
			}
		});  */
	 }
	 /** 页面显示处理 */
	$(function(){
		var len = $("[name=yc]").length;
		/* if(len !=null && len != 0){
			$("#title").html("您好，当前有"+"<font color='red'>"+len+"</font>"+"个异常进程！");
		}else{
			$("#title").html("<font color='blue'>"+"您好，当前没有异常进程！"+"</font>");
		} */
		var ff = '<%=request.getAttribute("ff")%>';
		if(ff == "0"){
			$("#sel").find("option").each(function(){
				if($(this).val() == 0){
					$(this).attr("selected","selected");
				}
			});
			$("[name='state']").each(function(){
		    	if($(this).find("font").html() == '全部'){
		    			$(this).parent().hide();
		    		}
		    	});
		}else if(ff == "2"){
		$("#sel").find("option").each(function(){
				if($(this).val() == 2){
					$(this).attr("selected","selected");
				}
			});
			$("[name='state']").each(function(){
		    	if($(this).find("font").html() == '异常'|| $(this).find("font").html()=='备'){
		    			$(this).parent().hide();
		    		}
		    	});
		}else{
			//默认显示异常的
			$("[name='state']").each(function(){
		    	if($(this).find("font").html() == '正常'|| $(this).find("font").html()=='主'){
		    			$(this).parent().hide();
		    		}
		    	});
		}
		$("#time").html(new Date().toLocaleString());
		$("#search").click(function(){
			$("#obj").submit();
		});
	});	
	
</script>
</head>
<body class="scrollbody" >
<s:form action="zookeeperMonitor_monitorTree.do" method="post" cssClass="obj" id="obj" name="obj">
<s:hidden name="currentTreeObj.fullPath" id="fullPath"></s:hidden>
<div style="padding: 10px;">
	<s:if test='pp == "S"'>
		<div style="border-bottom: 1px solid blue;padding-bottom: 5px;margin-bottom: 10px;" >
			<b class="mgr-10">请选择参照树：</b><s:select onchange="reloadMonitorData()" cssClass="select-1 vm" style="width: 100px;" list="#{'S':'静态树','D':'动态树'}" name="pp" id="isCheck" ></s:select>
			<b class="mgl-20">集群：</b>
				<s:select cssStyle="width: 100px;" list="clusterList" cssClass="select-1" id="cluster_id"  
						listKey="nodeId" listValue="nodeName" name="cluster_id"  >
				</s:select>
			<b class="mgl-20">程序池：</b>
				<s:select cssStyle="width: 100px;" list="poolList"  cssClass="select-1 vm" id="pool_name"
					headerKey="" headerValue="--请选择--"	listKey="nodeId" listValue="nodeName" name="pool_name" >
				</s:select>
			<b class="mgl-20">数据类型:</b>
			<select class="select-1 vm" id="sel" onchange="chang();" style="width: 100px;" name="ff">
				<option value="0">全部</option>
				<option value="1" selected="selected">异常</option>
				<option value="2">正常</option>
			</select>
			<span class="ubtn-1 mgl-20"><input type="button" id="search" value="刷新" /></span> 
			<b class="mgl-20">检测时间：</b><span id="time"></span>
			<!-- <marquee align=left scrollamount=3 direction=right behavior="alternate" Height="15px" width="400px">
			<font id="title"  ></font></marquee> -->
		</div>
	</s:if>
	<s:else>
		<div style="border-bottom: 1px solid blue;padding-bottom: 5px;margin-bottom: 10px;" >
			<b class="mgr-10">请选择参照树：</b><s:select onchange="reloadMonitorData()" cssClass="select-1 vm" style="width: 100px;" list="#{'S':'静态','D':'动态'}" name="pp" id="isCheck" ></s:select>
			<b class="mgl-20">集群：</b>
				<s:select cssStyle="width: 100px;" list="clusterList" cssClass="select-1" id="cluster_id"  
						listKey="nodeId" listValue="nodeName" name="cluster_id"  >
				</s:select>
			<b class="mgl-20">程序池：</b>
				<s:select cssStyle="width: 100px;" list="poolList"  cssClass="select-1 vm" id="pool_name"
					headerKey="" headerValue="--请选择--"	listKey="nodeId" listValue="nodeName" name="pool_name" >
				</s:select>
			<b class="mgl-20">数据类型:</b>
			<select class="select-1 vm" id="sel" onchange="chang();" style="width: 100px;" name="ff">
				<option value="0">全部</option>
				<option value="1" selected="selected">备</option>
				<option value="2">主</option>
			</select>
			<span class="ubtn-1 mgl-20"><input type="button" id="search" value="刷新" /></span>
			<b class="mgl-20">检测时间：</b><span id="time"></span>
			<!-- <marquee align=left scrollamount=3 direction=right behavior="alternate" Height="15px" width="400px">
			<font id="title"  ></font></marquee>  -->
		</div>
	</s:else>
	<div class="table-ct">
	<table  width="100%" border="0" cellspacing="0" id="tab" class="blue-table sorttable">
		 <s:iterator value="mapResult">
		 		 <s:if test="!value.isEmpty()">
		 			 <tr>
						<td colspan="10" align="left" name="total">
							节点路径: <s:property value="key"/>
						</td>
					 </tr>
		 		 </s:if>	
				 <s:iterator value="value" id="st">	
					 <tr>
					 	<td width="10%">节点名称：</td>
					 	<td  bgcolor="#F5F5DC"><s:property value="#st.nodeName"/></td>
					 	<td>状态:</td>
					 	<td name="state" width="10%;">
						 	<s:if test='pp == "S"'>
							 	<s:if test="#st.flag == false">
							 		<img style="margin-top: 4px;margin-right: -20px;margin-left: 15px;" src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/Danger.png" /><font color="red" name="yc">异常</font>
							 	</s:if>
							 	<s:else><img style="margin-top: 4px;margin-right: -20px;margin-left: 15px;" src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/Ok.png" /><font color="blue">正常</font></s:else>
							</s:if>
						 	<s:else>
						 		<s:if test="#st.flag == false">
							 			<font color="red">备</font>
							 	</s:if>
							 	<s:else><font color="blue">主</font></s:else>
						 	</s:else>	
					 	</td>
					 	<s:if test="#st.flag == true">
						 	<td width="10%">节点信息：</td>	
						 	<td width="50%" bgcolor="#F5F5DC"><b><s:property value="#st.dataValue"/></b></td>
					 	</s:if>
					 	<s:else><td colspan="4"></td></s:else>
					 </tr>
				 </s:iterator>	
		 </s:iterator>
	</table>
	</div>
</div>
</s:form>
</html:html>	