<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
	var parent_nodeType = '<s:property value="obj.node_type"/>';
	var parent_serviceType = '<s:property value="obj.service_type"/>';
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:createTreeNode,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	});
	
	function createTreeNode(){
		var nodeName = $("#nodeName").val();//节点名称
		var cluster_id = $("#cluster_id").val();//标识
		var hostId="";  
		var labelName="";
		if(parent_nodeType == 0){
			if(cluster_id==""){
				alert("请填写标识");
				return false;
			}
		}
		if(parent_nodeType==10){
			$("input['name=checkboxId']:checked").each(function(){
				hostId+=$(this).val()+",";
				labelName+=$(this).attr("title")+",";
			});
			if(hostId==""){
				alert("主机不能为空");
				return false;
			}
		}else{
			if (nodeName.length == 0) {
				alert("请填写节点名称");
				return false;
			}	
		}
		w.saveChild($("#obj").serialize(),hostId,labelName,$("#parent_id").val(),parent_nodeType,parent_serviceType);
	}
	$(function(){
		if(parent_nodeType==10){
			$("#nodeId").hide();
		}
	});
</script>
</head>
<body style="overflow-y:auto;">
	<s:form action="" method="post" id="obj" cssStyle="theForm">
	<s:hidden name="obj.parent_id" id="parent_id"></s:hidden>
		<div>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" align="left">
						节点类型 <font color="red">*</font>
					</td>
					<td>
						<s:select list="#{'1':'大数据中心','2':'hadoop集群','3':'hbase集群','4':'hive集群','5':'zookeeper集群','6':'impala集群',
						'7':'storm集群','8':'hdfs文件系统','9':'mapReduce','10':'服务节点','11':'主机节点' }" name="obj.node_type" id="nodeType"></s:select>
					</td>
				</tr>
				<s:if test="obj.node_type==3||obj.node_type==4||obj.node_type==5||obj.node_type==6||obj.node_type==7||obj.node_type==8||obj.node_type==9">
					<tr>
						<td class="til" align="left">
							服务类型 <font color="red">*</font>
						</td>
						<td>
							<s:select list="#{'1':'namenode','2':'datanode','3':'journalnode','4':'nodemanager','5':'resourcemanager','6':'hmaster',
							'7':'regionserver','8':'thirftserver','9':'znode','10':'hivexx','11':'impalaxx'}" name="obj.service_type" id="serviceType"></s:select>
						</td>
					</tr>
				</s:if>
				<tr id="nodeId">
					<td class="til" align="left">
						节点名称 <font color="red">*</font>
					</td>
					<td>
						<s:textfield name="obj.name" id="nodeName"></s:textfield>
					</td> 
				</tr>
				<s:if test="obj.node_type==0||obj.node_type==2">
					<tr>
						<td class="til" align="left">
							标识 <font color="red">*</font>
						</td>
						<td>
							<s:textfield name="obj.cluster_id" id="cluster_id"></s:textfield>
						</td>
					</tr>
				</s:if>
				<s:if test="obj.node_type==10">
					<tr>
						<td class="til" align="left">
							主机名称 <font color="red">*</font>
						</td>
						<td>
							<s:iterator value="resultList" id="theBean" status="st">
								<s:if test="#st.getIndex()%4==0">
									<p>
								</s:if>
									<input name="checkboxId" id="id" type="checkbox" value="<s:property value="#theBean.id"/>" title="<s:property value="#theBean.label_name"/>"/>
									<s:property value="#theBean.label_name"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<s:if test="(#st.getIndex()+1)%4==0">
									</p>
								</s:if>
							</s:iterator>
						</td>
					</tr>
					<s:if test="obj.service_type==1">
						<tr>
							<td class="til" align="left">
								主/从 <font color="red">*</font>
							</td>
							<td>
								<s:select list="#{'1':'active','2':'standy'}" name="obj.user_defined" id="userDefined"></s:select>
							</td>
						</tr>
					</s:if>
				</s:if>
			</table>
		</div>
	</s:form>
</body>