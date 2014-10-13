$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}
	//虚拟机监控
function virtual_monitor() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		//if (treeNode && !treeNode.noR && !treeNode.isParent && (treeNode.getParentNode().id != 80)) {
		//	$("#right_iframe").attr("src", "http://172.21.0.117:8480/display/showMonitorInfo.action?type=uncomp&kbpClass=10-10-24:csapp$" + treeNode.name);
			var url = "vmw_queryUnitId.do?name=" + treeNode.name +"&type=" + treeNode.type +"&Date"+(new Date());
			 $.getJSON(url,function(data){
				if(data !=null){
					$("#right_iframe").attr("src", "http://172.21.0.117:8580/display/showMonitorInfo.action?type=uncomp&kbpClass=" + data);		
				}
			})
	//	}
	}
	setDivVisProp("iframe");
	hideRMenu();
}

function login(){
	$("#right_iframe").attr("src", "https://172.21.0.200:9443/vsphere-client/vmrc/vmrc.jsp?vm=F26CA86D-FDE9-4BC2-9C15-E65E47F4F346:VirtualMachine:vm-230");
			setDivVisProp("iframe");
		hideRMenu();
}
	//虚拟机添加
function virtual_add() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		//KVM虚拟镜像部署
		if (treeNode.type==22)  {
			oper = '1';
			$("#right_iframe").attr("src", "yvm_lookupImage.do?ENTITY_ID="+treeNode.entityId+"&TYPE="+treeNode.type+"&oper="+oper+"&parent_id="+parent_id);
		setDivVisProp("iframe");
		} else if (treeNode.type==1) {//VMware创建裸机
			oper = '2';
			$("#right_iframe").attr("src", "vmw_editDeployData.do?entity_id="+treeNode.entityId+"&TYPE="+treeNode.type+"&name="+ treeNode.name+"&id="+treeNode.id);
			setDivVisProp("iframe");
		}else if(treeNode.type==23){
			oper='3';
			$("#right_iframe").attr("src", "vmw_cloneVM.do?parent_id=" + parent_id+"&name="+treeNode.name+"&parent_id="+parent_id);
			setDivVisProp("iframe");
		}else if(treeNode.type==26){//xen创建裸机
			oper='4';
			$("#right_iframe").attr("src", "xen_goNakeMacPage.do?parent_id=" + treeNode.id+"&clusterId="+treeNode.getParentNode().id+"&pool_uuid="+treeNode.getParentNode().tree_uuid+"&host_uuid="+treeNode.tree_uuid);
			setDivVisProp("iframe");
		}
		//后续扩展Xen、IBM等虚拟化
	}
	hideRMenu();
}

//在主机节点使用，通过模板克隆虚拟机
function cloneVMByTem(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_code = treeNode.entityId;
		var parent_id = treeNode.id;
		if (treeNode.type==1)  {
			$("#right_iframe").attr("src", "vmw_cloneVMByTemPage.do?host_code="+treeNode.entityId+"&host_id="+parent_id);
		setDivVisProp("iframe");
		} 	
	}
}
	//添加主机
function host_add() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if ((treeNode && !treeNode.noR)) {
			var dcNode = treeNode.getParentNode();//数据中心节点
			if(dcNode.type!=8){
				dcNode = treeNode.getParentNode().getParentNode();//数据中心节点
			}
			$("#right_iframe").attr("src", "yvm_addHost.do?ID=" + treeNode.id + "&TYPE=" + treeNode.type + "&name=" + encodeURI(encodeURI(treeNode.name)) + "&dcName=" + dcNode.name + "&clId=" + treeNode.entityId+"&pool_uuid="+treeNode.tree_uuid + "&entityId=" + treeNode.entityId);
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}
	//虚拟机连接
function virtual_conn() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if ((treeNode && !treeNode.noR)) {
			$("#right_iframe").attr("src", "depvideo_loginHost.do?oper=yicloud" + "&vID=" + treeNode.id);
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}
	//主机监控
function host_monitor() {
		// var nodes = zTree.getSelectedNodes();
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if ((treeNode && !treeNode.noR && (treeNode.children == null) && (treeNode.getParentNode() == null)) || (treeNode && !treeNode.noR && treeNode.isParent)) {
			$("#right_iframe").attr("src", "http://172.21.0.117:8480/display/showMonitorInfo.action?type=uncomp&kbpClass=10-10-24:csapp");
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}
	//虚拟机查看
function virtual_view(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if(treeNode.type==0 && oper==1){//调整vmware虚拟机
			$("#right_iframe").attr("src", "vmw_adjustVmwPage.do?id=" + treeNode.entityId + "&name=" + encodeURI(encodeURI(treeNode.name)) + "&parentName=" + treeNode.getParentNode().entityId+ "&hostName=" + treeNode.getParentNode().name);
			setDivVisProp("iframe");
		}
		var hostId = treeNode.getParentNode().id;
		if(treeNode.type==24){//kvm虚拟机
			$("#right_iframe").attr("src", "yvm_checkKVMVirtualInfo.do?hostId=" + hostId + "&oper=" + oper + "&ENTITY_ID=" + treeNode.entityId);
			setDivVisProp("iframe");
	}
		if(treeNode.type==27){//xen虚拟机	
			if(treeNode.type==27){//xen虚拟机	
			$("#right_iframe").attr("src", "xen_listVMInfo.do?entity_id=" + treeNode.entityId + "&oper=" + oper +"&host_uuid="+treeNode.getParentNode().tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid);
			setDivVisProp("iframe");
		}
		}
	}
	hideRMenu();
}

//clone virtual
function  virtual_clone(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		var entityId = treeNode.entityId;
		if(treeNode){
			if (treeNode.type==0 || treeNode.type==23) {//从模板部署或从虚拟机克隆
				$("#right_iframe").attr("src", "vmw_cloneVM.do?parent_id=" + parent_id+"&name="+treeNode.name+"&type="+treeNode.type+"&entityId="+entityId);
				setDivVisProp("iframe");
			}
			if(treeNode.type==28) {//xen镜像
				$("#right_iframe").attr("src", "xen_goQueryCreateVMByTem.do?parent_id=" + parent_id+"&entity_id="+treeNode.entityId+"&host_uuid="+treeNode.getParentNode().tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid);
				setDivVisProp("iframe");
			}
			if(treeNode.type==27) {//xen虚拟机
				$("#right_iframe").attr("src", "xen_goCloneVM.do?parent_id=" + parent_id+"&entity_id="+treeNode.entityId +"&host_uuid="+treeNode.getParentNode().tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid);
				setDivVisProp("iframe");
			}
		}
		
	}
	hideRMenu();
}

//migrate virtual
function virtual_migrate(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var type = treeNode.type;
		var parent_id = treeNode.getParentNode().id;
		var cluster_id = treeNode.getParentNode().getParentNode().id;
		var pool_uuid =treeNode.getParentNode().getParentNode().tree_uuid; 
		var host_uuid =treeNode.getParentNode().tree_uuid; 
		var host_code = treeNode.getParentNode().entityId;
		if(type==27){//xen虚拟机
			$("#right_iframe").attr("src", "xen_goMigrate.do?cluster_id=" + cluster_id+"&id="+treeNode.getParentNode().id+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&vm_uuid="+treeNode.tree_uuid);
			setDivVisProp("iframe");
		}
		else{
		if (treeNode && !treeNode.noR && !treeNode.isParent && (treeNode.getParentNode().id != 80)) {
			$("#right_iframe").attr("src", "vmw_migratePage.do?parent_id=" + parent_id+"&vmName="+treeNode.entityId+"&id="+treeNode.id+"&host_code="+host_code+"&name="+encodeURI(encodeURI(treeNode.name)));
			setDivVisProp("iframe");
		}
	}
}
	hideRMenu();
}
	//修改虚拟机状态，暂停、恢复、启动、停止、删除等
function put_virtual_state(state) {
	var stat = "";
	var nodes = rightSNode;
	if (state == "destroy") {
		stat = "\u5220\u9664";
	} else {
		if (state == "create") {
			stat = "\u542f\u52a8";
		} else {
			if (state == "shutdown") {
				stat = "\u5173\u95ed";
			} else {
				if (state == "suspend") {
					stat = "挂起";
				} else {
					if (state == "resume") {
						stat = "\u6062\u590d";
					} else {
						if (state == "reboot") {
							stat = "\u91cd\u65b0\u542f\u52a8";
						}
					}
				}
			}
		}
	}
	if (confirm("\u786e\u5b9a\u8981" + stat + "\u8be5\u865a\u62df\u673a\u5417\uff1f")) {
		var nodes = rightSNode;
		if (nodes) {
			var treeNode = nodes;
			if(treeNode.type==0){//vmware
				if(state == "create"){
					bar(treeNode.entityId,"正在启动虚拟机"+treeNode.name);
				}else if(state == "suspend"){
					bar(treeNode.entityId,"正在挂起虚拟机"+treeNode.name);
				}else if(state == "shutdown"){
					bar(treeNode.entityId,"正在关闭虚拟机"+treeNode.name);
				}else if(state == "resume"){
					bar(treeNode.entityId,"正在恢复虚拟机"+treeNode.name);
				}else if(state == "destroy"){
					bar(treeNode.entityId,"正在销毁虚拟机"+treeNode.name);
				}else if(state == "reboot"){
					bar(treeNode.entityId,"正在重启虚拟机"+treeNode.name);
				}
				$.getJSON("vmw_putVirtualStat.do?id=" + treeNode.id + "&op=" + state + "&name=" + encodeURI(encodeURI(treeNode.name))+ "&ENTITY_ID=" + treeNode.entityId,{"time:":new Date().toString()},function(data){
					var result = data.result;
					if(result !=null && result ==1){
						barEnd(treeNode.entityId,stat+"虚拟机"+treeNode.name + "成功");
						if(state == "create"){
							//treeNode.icon="sxcloud/images/virtual/running.png";
							//zTree.updateNode(treeNode);
							
							zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						}else if(state == "suspend"){
							//treeNode.icon="sxcloud/images/virtual/paused.png";
							//zTree.updateNode(treeNode);
							zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						}else if(state == "shutdown"){
							//treeNode.icon="sxcloud/images/virtual/VirtualMachine.png";
							//zTree.updateNode(treeNode);
							zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						}else if(state == "destroy"){
							var parentNode = treeNode.getParentNode();
							zTree.reAsyncChildNodes(parentNode, "refresh",true);
						}
					}else{
						barEnd(treeNode.entityId,stat+"虚拟机"+treeNode.name + "失败");
						if(result=="poweredOn"){
							alert(stat + "失败,可能的原因：虚拟机已开启");
						}else if(result=="poweredOff"){
							alert(stat + "失败,可能的原因：虚拟机已关闭");
						}else if(result=="suspended"){
							alert(stat + "失败,可能的原因：虚拟机已挂起");
						}else if(result!="" && result!= null){
							alert(stat + "失败,可能的原因："+result);
						}else{
							alert(stat + "失败");
						}
					}
				});
			}else if(treeNode.type==27){//xen虚拟机
				$.getJSON("xen_putVMState.do?entity_id=" + treeNode.entityId + "&state=" + state+"&id="+treeNode.id+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid,{"time:":new Date().toString()},function(data){
					var result = data.responseCode;
					if(result !=null && result ==1){
						alert(stat + "成功");
						if(state == "create"){
							var parentNode = treeNode.getParentNode().getParentNode();
							zTree.reAsyncChildNodes(parentNode, "refresh",true);
						}else if(state == "suspend"){
							//treeNode.icon="sxcloud/images/virtual/paused.png";
							//zTree.updateNode(treeNode);
							zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						}else if(state == "shutdown"){
							//treeNode.icon="sxcloud/images/virtual/VirtualMachine.png";
							//zTree.updateNode(treeNode);
							zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						}else if(state == "resume"){
							zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						}else if(state == "destroy"){
							var parentNode = treeNode.getParentNode();
							zTree.reAsyncChildNodes(parentNode, "refresh",true);
						}
					}else{
						alert(stat + "失败");
					}
				});
			}
		}
		hideRMenu();
	}
}
	//应用部署
function app_deploy(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var host_name = treeNode.getParentNode().name;
		$("#right_iframe").attr("src", "dep_addDeployExample.do?oper=" + oper + "&virtualId=" + treeNode.name+'&host_name='+host_name);
		
		setDivVisProp("iframe");
	}
	hideRMenu();
}
	
	//添加集群
function addCluster() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if (treeNode) {
			//var dcNode = treeNode.getParentNode();//得到集群所在的数据中心
			var dcNode = treeNode;
			if(dcNode.type!=8){
				dcNode = treeNode.getParentNode();
			}
			dcName =encodeURI(encodeURI(dcNode.name))
			$("#right_iframe").attr("src", "cluster_addCluster.do?id=" + treeNode.id  + "&dcName=" +dcName + "&entityId=" + dcNode.entityId);
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}
//开启，关闭集群HA,DRS状态等
function conCluster() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if (treeNode) {
			//var dcNode = treeNode.getParentNode().getParentNode();
			var dcNode = treeNode.getParentNode();
			if(dcNode.type!=8){
				dcNode = treeNode.getParentNode().getParentNode();
			}
			$("#right_iframe").attr("src", "cluster_conCluster.do?id=" + treeNode.entityId + "&name=" + treeNode.name + "&dcName=" + dcNode.entityId );
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}
	//删除集群
function delCluster(){
var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if (treeNode) {
			//var dcNode = treeNode.getParentNode().getParentNode();
			var dcNode = treeNode.getParentNode();
			if(treeNode.type==3){
				//dcNode = treeNode.getParentNode().getParentNode();
			if(dcNode.type!=8){
				dcNode = treeNode.getParentNode().getParentNode();
			}
			if(confirm("确定删除该集群吗?")==true){
				bar(treeNode.id,"正在删除集群:"+treeNode.name);
				var url = "cluster_delCluster.do?id=" + treeNode.id + "&name=" + treeNode.name + "&dcName=" + dcNode.name + "&entityId=" + treeNode.entityId;
				$.getJSON(url,{'time': new Date().toString()},function(data){
					if(data.result==1){
						barEnd(treeNode.id,"删除集群" + treeNode.name + "成功!");
						zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
					}else{
						if(data.resMsg !=null){
							alert(decodeURI(decodeURI(data.resMsg)));
						}
						barEnd(treeNode.id,"删除集群" + treeNode.name + "失败!");
					}
				});			
			}
		  }
		  if(treeNode.type==29){
			if(confirm("确定删除该集群吗?")==true){
				var url = "xen_delCluster.do?id=" + treeNode.id + "&pool_uuid=" + treeNode.tree_uuid  + "&entityId=" + treeNode.entityId;
				$.getJSON(url,{'time': new Date().toString()},function(data){
					if(data.result==1){
						alert("删除集群" + treeNode.name + "成功!");
						zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
					}else{
						if(data.resMsg !=null){
							alert(decodeURI(decodeURI(data.resMsg)));
						}
						alert("删除集群" + treeNode.name + "失败!");
					}
				});			
			}
		  }
		}
	}
	hideRMenu();
}

   //删除树节点
function delTreeNode() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var ID = treeNode.id;
		var type = treeNode.type;
		if(type==26){//xen主机
			$.getJSON("xen_delHost.do?ID=" + ID+"&entity_id="+treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().tree_uuid+"&host_uuid="+treeNode.tree_uuid, {"time":new Date().toString()}, function (data) {
			var result = data.responseCode;
			if (result == 1) {
				zTree.removeNode(treeNode);
				alert("成功删除");
			}else if (result == 'error') {
				alert("该节点是父节点,不能被删除");
			}
			else {
				alert("删除失败");
			}
		});
		}else{
		$.getJSON("tree_delTreeNode.do?ID=" + ID, {"time":new Date().toString()}, function (data) {
			var result = data.result;
			if (result == null) {
				zTree.removeNode(treeNode);
				alert("\u6210\u529f\u5220\u9664");
			} else {
				alert("\u5f53\u524d\u8282\u70b9\u5b58\u5728\u5b50\u8282\u70b9\uff0c\u4e0d\u5141\u8bb8\u5220\u9664");
			}
		});
     }
   }
	hideRMenu();
}

	//设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}
//定时获取状态
//timer();
function timer() {
	var tree = $.fn.zTree.getZTreeObj("treeDemo");
	if (tree != null) {
		setStatus(tree);
	}
    setTimeout("timer()",3000);    
}
function setStatus(tree) {
	var nodes = zTree.getNodesByParam("type",25, null);
	if(nodes && nodes.length > 0){
	for(j=0; j< nodes.length;j++){
	
	var childNodes = nodes[j].children;
	
	if (childNodes && childNodes.length > 0) {
		for (i = 0; i < childNodes.length; i++) {
			var treeNode = childNodes[i];
			if (treeNode.type == 0 ) {
				$.getJSON("yvm_virtual_state.do?ID=" + treeNode.id, {"time":new Date().toString()}, function (data) {
					for (k = 0; k < data.length; k++) {
						var id = data[k].ID;
						var node = zTree.getNodeByParam("id", id, null);
						zTree.setting.view.fontCss = {};
						if (data[k].state == "VIR_DOMAIN_SHUTOFF") {
							node.icon = "sxcloud/images/virtual/shutdown.png";
						} else {
							if (data[k].state == "VIR_DOMAIN_RUNNING") {
								node.icon = "sxcloud/images/virtual/running.png";
							} else {
								if (data[k].state == "VIR_DOMAIN_PAUSED") {
									node.icon = "sxcloud/images/virtual/paused.png";
								} else {
									if (data[k].state == "error") {
										node.icon = "sxcloud/images/virtual/cancel.png";
									}
								}
							}
						}
						zTree.updateNode(node);
					}
				});
			}
		}
		}
	}
  }
}

function snapshotManager(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if(treeNode.type==0){//vmware虚拟机
		$("#right_iframe").attr("src", "snapshot_snapshotManager.do?name=" + treeNode.name+"&entityId="+treeNode.entityId);
		setDivVisProp("iframe");
		}
		if(treeNode.type==27){//xen虚拟机
		$("#right_iframe").attr("src", "xen_snapshotManager.do?entity_id=" + treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
		setDivVisProp("iframe");
		}
	}
	hideRMenu();
}

function executeSnapshot(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if(treeNode.type==0){//vmware虚拟机
			$("#right_iframe").attr("src", "snapshot_executeSnapshot.do?name=" + encodeURI(encodeURI(treeNode.name))+"&entityId="+treeNode.entityId);
			setDivVisProp("iframe");
		}
		if(treeNode.type==27){//xen虚拟机
			$("#right_iframe").attr("src", "xen_goSnapshotPage.do?entity_id=" + treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}

function virtualToTem(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var name = encodeURI(encodeURI(treeNode.name));
		if(treeNode.type==0){//vmware虚拟机
			var parentNode = treeNode.getParentNode();
		//	$("#right_iframe").attr("src", "tree_markAsTem.do?id=" + treeNode.id + "&name=" + treeNode.name + "&parentId=" + parentNode.id);
			var url = "tree_turnToMarkPage.do?id=" + treeNode.id + "&name=" + name + "&parentId=" + parentNode.id;
			$("#right_iframe").attr("src",url);
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}
function cloneToTem(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		if(treeNode){
			if (treeNode.type==0 || treeNode.type==23) {//从模板部署或从虚拟机克隆
				$("#right_iframe").attr("src", "vmw_cloneToTem.do?parent_id=" + parent_id+"&name="+treeNode.name+"&type="+treeNode.type+"&entity_id="+treeNode.entityId);
				setDivVisProp("iframe");
			}
			if(treeNode.type==27){//xen虚拟机
			$("#right_iframe").attr("src", "xen_goCreateTemPage.do?entity_id=" + treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
			setDivVisProp("iframe");
		}
		}
		
	}
	hideRMenu();
}
function put_xenVmstate(){
	
}
//删除数据中心
function delDataCenter(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		if(confirm("确定要删除该数据中心吗?")==true){
		bar(treeNode.id,"正在删除数据中心:"+treeNode.name);
			$.getJSON("datacenter_deleteDataCenter.do?id=" + treeNode.id + "&name=" + treeNode.name + "&entityId=" + treeNode.entityId,{'time': new Date().toString()},function(data){
				if(data.result==1){
					barEnd(treeNode.id,"成功删除数据中心:"+treeNode.name);
					zTree.removeNode(treeNode);
				}else{
					barEnd(treeNode.id,"无法删除数据中心:"+treeNode.name);
				}
			})
		}
	}
	hideRMenu();
}
//删除xen数据中心
function delXenDataCenter(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		if(confirm("确定要删除该数据中心吗?")==true){
			bar(treeNode.id,"正在删除数据中心:"+treeNode.name);
			$.getJSON("xen_deleteXenDataCenter.do?id=" + treeNode.id ,{'time': new Date().toString()},function(data){
				if(data.result==1){
					barEnd(treeNode.id,"成功删除数据中心:"+treeNode.name);
					zTree.removeNode(treeNode);
				}else{
					barEnd(treeNode.id,"无法删除数据中心:"+treeNode.name);
				}
			})
		}
	}
	hideRMenu();
}
//删除vmware模板
function templet_remove(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		var parentNode = treeNode.getParentNode();
		var type = treeNode.type;
		var pool_uuid = treeNode.getParentNode().getParentNode().tree_uuid;
		var host_uuid = treeNode.getParentNode().tree_uuid;
		var vm_uuid = treeNode.tree_uuid;
		if(type==23){//vmware模板
		if(confirm("确定删除该模板吗?")==true){
			hideRMenu();
			var url = "vmw_removeTemplet.do?id=" + treeNode.id + "&name=" + treeNode.name + "&entityId=" + treeNode.entityId;
			bar(treeNode.id ,"正在删除模板:"+treeNode.name);
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(data.result==1){
					barEnd(treeNode.id ,"成功删除模板:"+treeNode.name);
					zTree.reAsyncChildNodes(parentNode, "refresh",true);
				}else{
					barEnd(treeNode.id ,"无法删除模板:"+treeNode.name);
				}
			});
		}
		}else if(type==28){//xen模板
			if(confirm("确定删除该模板吗?")==true){
			hideRMenu();
			var url = "xen_delTem.do?id=" + treeNode.id + "&vm_uuid=" + vm_uuid + "&entityId=" + treeNode.entityId+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid;
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(data.responseCode==1){
					alert("删除模板成功");
					zTree.reAsyncChildNodes(parentNode, "refresh",true);
				}else{
					alert("删除模板失败");
				}
			});
		}
	  }
	}
}
function moveToDateCenter(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		var parentNode = treeNode.getParentNode().getParentNode();//数据中心节点
		if(parentNode.type!=8){
			parentNode = treeNode.getParentNode().getParentNode().getParentNode();
		}
		var target_id = parentNode.id;
		var dcCode = parentNode.entityId;
		var dcName = parentNode.name;
		var clCode = treeNode.getParentNode().entityId;//集群名称
		var parentEntityId = parentNode.entityId;
		if(confirm("移到数据中心需要将主机置于维护模式。\u000d确定要移到数据中心下吗?")==true){
			hideRMenu();
			var url = "vmw_moveToDateCenter.do?id=" + treeNode.id + "&name=" + treeNode.name+"&type="+treeNode.type+"&targetId="+target_id+"&dcCode="+dcCode+"&clCode="+clCode+"&entityId="+treeNode.entityId+"&dcName="+dcName;
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(data.result==1){
					alert("移动主机成功!\r可以将主机拖回集群");
					zTree.reAsyncChildNodes(parentNode, "refresh",true);
				}else{
					alert("移动主机失败,可能的原因" + data.result);
				}
			});
		}
	}
}
//主机进入维护模式
function enterMaintenanceMode(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		var parentNode = treeNode.getParentNode();
		if(confirm("处于维护模式下的主机不会执行任何与虚拟机相关的功能，包括虚拟机置备操作。若要进入维护模式，所有虚拟机必须关机或移至其他主机。可能需要人工干预。\u000d是否要将选定的主机置于维护模式?")==true){
			hideRMenu();
			var url = "tree_enterMaintenanceMode.do?hostName=" + treeNode.name + "&entityId=" + treeNode.entityId;
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(data.result==1){
					alert("进入维护模式成功!");
					zTree.reAsyncChildNodes(parentNode, "refresh",true);
				}else{
					alert("进入维护模式失败,可能的原因" + data.result);
				}
			});
		}
	}
}

//主机退出维护模式
function exitMaintenanceMode(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		var parentNode = treeNode.getParentNode();
		hideRMenu();
		var url = "tree_exitMaintenanceMode.do?hostName=" + treeNode.name + "&entityId=" + treeNode.entityId;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				alert("退出维护模式成功!");
				zTree.reAsyncChildNodes(parentNode, "refresh",true);
			}else{
				alert("退出维护模式失败,可能的原因" + data.result);
			}
		});
	}
}
//克隆到集群
function cloneToCluster(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		var entityId = treeNode.entityId;
		if(treeNode){
			if (treeNode.type==0 || treeNode.type==23) {//从虚拟机或模板克隆
				$("#right_iframe").attr("src", "vmw_cloneVMToCluster.do?name=" +treeNode.name+"&entityId="+entityId);
				setDivVisProp("iframe");
			}
		}
	}
	hideRMenu();
}

//从集群创建虚拟机
function creatVirtualMachine(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var url = "vmw_getClusterDRS.do?clusterName="+treeNode.entityId;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				var parent_id = treeNode.getParentNode().id;
				if (treeNode.type == 3)  {
					$("#right_iframe").attr("src", "vmw_deployVirtualMachineData.do?TYPE="+treeNode.type+"&name="+ treeNode.name+"&id="+treeNode.id+"&parent_id="+parent_id+"&entityId="+treeNode.entityId);
					setDivVisProp("iframe");
				}
			}else{
				alert("集群未开启DRS,请修改集群配置或者选择主机创建虚拟机！");
			}
		});
		hideRMenu();
	}
}
//打开控制台
function console() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		//$("#right_iframe").attr("src", "vmw_openconsole.do?name=" + treeNode.name);
		window.open("vmw_openconsole.do?name=" + treeNode.entityId,"控制台");
		setDivVisProp("iframe");
	}
	hideRMenu();
}
//删除无效数据
function deleteData(){
	
}
//远程唤醒主机
function wakeup(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var url = "tree_wakeupHost.do?entityId=" + treeNode.entityId + "&name=" + treeNode.name;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.result==1){
				alert("唤醒命令执行成功，请等待主机启动！");
			}else{
				alert("唤醒命令执行失败");
			}
		});
		setDivVisProp("iframe");
	}
	hideRMenu();
}




//重新启动操作系统
function restartSysttem(){
	var treeNode = rightSNode;
	var url = "vmw_restartSysttem.do?name=" + treeNode.name+"&entityId="+treeNode.entityId;
	$.getJSON(url,{'time': new Date().toString()},function(data){
		if(data.result==1){
			alert("重启操作系统成功，请等待操作系统重启!");
			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
		}else{
			if(data.resMsg !=null){
				alert(decodeURI(decodeURI(data.resMsg)));
			}
			alert("重启操作系统失败!");
		}
	});			
	hideRMenu();
	
}
//关闭操作系统
function closeSystem(){
	var treeNode = rightSNode;
	var url = "vmw_closeSystem.do?name=" + treeNode.name + "&entityId=" + treeNode.entityId;
	$.getJSON(url,{'time': new Date().toString()},function(data){
		if(data.result==1){
			alert("关闭操作系统成功");
			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
		}else{
			if(data.resMsg !=null){
				alert(decodeURI(decodeURI(data.resMsg)));
			}
			alert("关闭操作系统失败!");
		}
	});			
	hideRMenu();
	
}
//重启主机
function host_reboot(){
	var treeNode = rightSNode;
	var url = "vmw_rebootHost.do?name="  + treeNode.entityId + "&hostName=" + treeNode.name;
	if(confirm("确定要重新引导主机吗?")==true){
		$.getJSON(url,{'time': new Date().toString()},function(data){
			if(data.result==1){
				alert("主机重新启动成功，请等待主机重启!");
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
			}else{
				alert("主机重启失败!可能的原因:" + data.reason);
			}
		});
	}
	hideRMenu();
}

//关闭主机
function host_shutdown(){
	var treeNode = rightSNode;
	var url = "vmw_shutdownHost.do?name="  + treeNode.entityId + "&hostName=" + treeNode.name;
	if(confirm("确定要关闭主机吗?")==true){
		$.getJSON(url,{'time': new Date().toString()},function(data){
			mask('主机正在关闭，请稍候......');
			if(data.result==1){
				alert("主机关闭成功!");
				removeMask();
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
			}else{
				alert("主机关闭失败!可能的原因:" + data.reason);
				removeMask();
			}
		});
	}
	hideRMenu();
}

//新建sr(xen)
function addNFS(){
		var nodes = rightSNode;
		if (nodes) {
		var treeNode = nodes;
		$("#right_iframe").attr("src", "xen_addNFSPage.do?pool_uuid="+ treeNode.getParentNode().tree_uuid+"&host_uuid="+treeNode.tree_uuid+"&host_id="+treeNode.entityId);
		setDivVisProp("iframe");
	}
	hideRMenu();
}
//同步数据
function synchroData(){
	var url = "tree_synchroData.do";
	if(confirm("确定要重新同步数据吗?")==true){
		mask('数据正在同步，请稍候.......');
		$.getJSON(url,{'time': new Date().toString()},function(data){
			if(data.result==1){
				removeMask();
				alert("同步数据成功!");
				zTree.reAsyncChildNodes(null, "refresh",true);
			}else{
				removeMask();
				alert("同步数据失败!");
			}
		});
	}
	hideRMenu();
}
//虚拟机添加网卡
function addnic(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var hostCode = treeNode.getParentNode().entityId;
		if(treeNode){
			$("#right_iframe").attr("src", "vmw_addnic.do?name=" +encodeURI(encodeURI(treeNode.name))+"&entityId="+treeNode.entityId + "&hostCode=" + hostCode);
			setDivVisProp("iframe");
		}
	}
	hideRMenu();
}

//虚拟机挂起添加遮罩层
function mask(message){
	var doc = window.document;
  	var w = doc.createElement("div");
    w.setAttribute("id","mybody")
    with(w.style){
        position = 'absolute';
        zIndex = '10000';
        width = Math.max(doc.documentElement.scrollWidth, doc.documentElement.clientWidth) + "px";
        height =Math.max(doc.documentElement.scrollHeight, doc.documentElement.clientHeight) + "px";
        position="absolute";
        left = '0';
        top = '0';
        background = '#FAFAFA';
        filter = 'Alpha(opacity=10)';
        opacity = '0.7';
    }
    doc.body.appendChild(w);
    //**********************************************//
    var ig=doc.createElement("div");
    ig.setAttribute("id","progressbar")
    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>'+message;
    doc.getElementById("mybody").appendChild(ig);
    with(ig.style){
        position = 'absolute';
        zIndex = '10001';
        left = '55%';
        top = '35%';
        marginLeft = - ig.offsetWidth / 2 + 'px';
        marginTop = - ig.offsetHeight / 2 + 'px';
    }
    doc.body.appendChild(ig);
}
//移除遮罩层
function removeMask(){
	var doc = window.document;
	var mybody = doc.getElementById('mybody');
	doc.body.removeChild(mybody);
	var progressbar = doc.getElementById('progressbar');
	doc.body.removeChild(progressbar);
}

//提示
//使用方法：barEnd是关闭弹出的bar的方法，注意，id必须唯一，并且bar的id和barEnd的id一样。

function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
}

function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
}

