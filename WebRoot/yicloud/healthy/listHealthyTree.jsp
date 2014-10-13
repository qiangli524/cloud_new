<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css"
		type="text/css" />
	<link rel="stylesheet"
		href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
		type="text/css" />
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.core-3.1.js"></script> 
</head>
<body onload="pageOnLoad()">
	<s:form action="healthy_listHealthyService" method="post" id="theForm" cssStyle="theForm">
		<s:hidden name="theForm.VH_HOST" id="VH_HOST"></s:hidden>
		<script type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: false,
				selectedMulti: false
			},
			callback: { 
				onAsyncSuccess: onAsyncSuccess,
				onClick: onClick
			},
			data: {
				simpleData: {
					enable: true
				}				
			},
			async: {
				enable: true,
				url:"healthy_listHealthyServicetest.do",
			//	autoParam:["id"]
				autoParam:["id", "name=n", "level=lv"],
				//otherParam:{"otherParam":"zTreeAsyncTest"},
				dataFilter: filter
			}
		};
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
<%--			alert(msg);--%>
		}
		
		
		var zNodes =[
			<%=request.getAttribute("treeStr")%>
  	 	];
		
		
		
		var zTree;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//	zTree.reAsyncChildNodes(null, "refresh");
		//	var nodes = zTree.getSelectedNodes();
		//	if (nodes.length>0) {
		//		zTree.reAsyncChildNodes(nodes[0], "refresh");
	//		}
			
		});
		
		
		function onClick(event, treeId, treeNode, clickFlag) {
			var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length>0){
			var treeNode = nodes[0]; 
			var hostId = treeNode.id;
			var tag = '';
			if(nodes[0].type==0){
				tag ='0';
			}else if(nodes[0].type==1){
				tag ='1';
			}else{
				tag ='2';	
				return false;	
			}
			$("#health_state_iframe").attr("src","healthy_HealthyTabs.do?id="+treeNode.id+"&tag="+tag);
		}
		
		}
		//定时获取虚拟机状态
		
		
			//控制隐藏
			var flag = 0;
	function test(){
		$(".left").toggle('slide',{direction: 'left'},100);
		if(flag==0){
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon2.gif");
			flag = 1;
		}else if(flag==1){
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif");
			flag =0;
		}
	}
	</script>
		<div class="content_wrap">
			<div class="left">
				<ul id="treeDemo" class="ztree"></ul>
			</div>
			<div  style="float:left; margin-top:200px"><img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif" onclick="test()"/></div>
			<div class="right">
				<div id="health_state">
					<iframe id="health_state_iframe" src="" name = "xxxx" width="840" height="520" frameborder="0"></iframe>
				</div>
			</div>
		</div>
		
	</s:form>
</body>
</html:html>