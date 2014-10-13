<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
</style>
<style type="text/css">
		
</style>
<script type="text/javaScript">
	$(function(){
	 var oper = "<s:property value='oper'/>";
	 var api = frameElement.api;
	 var w = api.opener;
	 if("register"==oper){
			api.button({
			     id:'OkAnd',
			     name: '注册',
			     callback:saveZKCluster,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		
		}else{
			 api.button({
			     id:'OkAnd',
			     name: '修改',
			     callback:saveZKCluster,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		}
			
	//添加保存操作
	function saveZKCluster(){
		var oper = $("#oper").val();
		w.save($("#theForm").serialize(),oper);
	}
});
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="clusterId" id="clusterId"></s:hidden>
			<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
				 <tr>
				 	<!-- td><b>配置项</b></td>
				 	<td><b>设置参数</b></td> -->
				 	<td class="til" >ZooKeeper集群名称:</td>
				 	<td><s:textfield   name="obj.clusterName" id="clusterName" /></td>
				 	<td class="til" >端口:</td>
				 	<td><s:textfield   name="obj.ssh_port"  id="ssh_port"  /></td>
				 </tr>
				 <tr>
				 	<td class="til">用户名:</td>
				 	<td><s:textfield   name="obj.ssh_username"  id="ssh_username"  /></td>
				 	<td class="til">密码:</td>
				 	<td><s:textfield   name="obj.ssh_passwd"  id="ssh_passwd"  /></td>
				 </tr>
				 <tr>
				 	<td class="til">机器列表:</td>
				 	<td colspan="4"><s:textarea cols="50" rows="4"  name="obj.serverListStr"  id="serverListStr" /></td>
				 </tr>
				<%--  <tr >
				 	<td class="til" >Cpu 峰值报警:</td>
				 	<td><s:textfield  name="aobj.maxCpuUsage"  id="maxCpuUsage" />% </td>
				 	<td class="til" >Memory 峰值报警:</td>
				 	<td><s:textfield  name="aobj.maxMemoryUsage" id="maxMemoryUsage" />%</td>
				 </tr>
				 <tr >
				 	<td class="til">Load 峰值报警:</td>
				 	<td><s:textfield  name="aobj.maxLoad" id="maxLoad"/>%</td>
				 	<td class="til">Data目录:</td>
				 	<td><s:textfield  name="aobj.dataDir" id="dataDir"/></td>
				 </tr>
				 <tr >
				 	<td class="til">Log目录:</td>
				 	<td><s:textfield  name="aobj.dataLogDir" id="dataLogDir"/></td>
				 	<td class="til">磁盘使用率 峰值报警:</td>
				 	<td><s:textfield  name="aobj.maxDiskUsage" id="maxDiskUsage"/>%</td>
				 </tr>
				 <tr >
				 	<td class="til">单机 连接数 峰值报警:</td>
				 	<td><s:textfield  name="aobj.maxConnectionPerIp" id="maxConnectionPerIp"/>%</td>
				 	<td class="til">单机 Watcher数 峰值报警:</td>
				 	<td><s:textfield  name="aobj.maxWatchPerIp" id="maxWatchPerIp"/>%</td>
				 </tr>
				 <tr >
				 	<td class="til">Node Check Rule:</td>
				 	<td colspan="4"><s:textfield  name="aobj.nodePathCheckRule" id="nodePathCheckRule" size="50"/>
					 	<br>
					 	<font color="blue">样例</font>：<b>|只能出现|^|不能出现| </b>
					 	<br>
					 	<font color="blue">例子</font>：
					 	 <b>|/</b>:nileader,yinshi;<b>/nileader</b>:test<b>|</b>^<b>|/</b>:test<b>|</b><br>
				 		 <font color="blue">表示</font>：
				  		"<b>/</b>"这个path下，只能够出现nileader和yinshi这两个节点，"<b>/nileader</b>" 这个path下，只能够出现test节点, "<b>/</b>" 这个path下，不能够出现test节点
				 	</td>
				 </tr> --%>
				 <tr>
				 	<td class="til" >描述:</td>
				 	<td colspan="4"><s:textarea cols="50" rows="5" name="obj.description" id="description" /></td>
				 </tr>
			</table>
	</s:form>
</html:html>	