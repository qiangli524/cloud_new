//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

// 设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}
//添加子节点
function addChild(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addChild',
			title:'添加子节点',
			max:false,
			min:false,
			height:'450px',
			width:'650px',
			content:'url:hadoop_addNode.do?obj.parent_id='+treeNode.id+'&obj.node_type='+treeNode.node_type+'&obj.service_type='+treeNode.service_type
		});
	}
}

///保存添加的子节点
function saveChild(urls,hostId,labelName,parent_id,parent_nodeType,parent_serviceType){
	hideRMenu();
	var treeNode = rightSNode;
	var url = "hadoop_saveNode.do?"+urls+"&obj.parent_nodeType="+parent_nodeType+"&obj.parent_serviceType="+parent_serviceType+"&hostId="+hostId+"&labelName="+labelName;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	        	  	var nodes = zTree.getNodeByParam("id",parent_id);
	      			zTree.reAsyncChildNodes(nodes, "refresh",true);
             }
	});
}
//远程访问主机
function remoteVisit(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'remoteVisit',
			title:'远程访问',
			max:false,
			min:false,
			height:'450px',
			width:'600px',
			content:'url:hadoop_remoteVisit.do'
		});
	}
}

function listTopo(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'listTopo',
			title:'拓扑图',
			max:false,
			min:false,
			height:'600px',
			width:'1066px',
			content:'url:topo_newTopo.do?parentId='+treeNode.id
		});
	}
}
