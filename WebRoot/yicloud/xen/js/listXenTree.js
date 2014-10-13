$.dialog.setting.zIndex = 100000;

	//虚拟机添加
function virtual_add() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		if(treeNode.type==26){//xen创建裸机
			oper='4';
			$("#right_iframe").attr("src", "xen_goNakeMacPage.do?parent_id=" + treeNode.id+"&clusterId="+treeNode.getParentNode().id+"&pool_uuid="+treeNode.getParentNode().tree_uuid+"&host_uuid="+treeNode.tree_uuid);
			setDivVisProp("iframe");
		}
		//后续扩展Xen、IBM等虚拟化
	}
	hideRMenu();
}

//在主机节点上通过模板来克隆虚拟机
function cloneVMByTemOnHost(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		if(treeNode.type==26){//在主机节点上克隆虚拟机
			var host_id = treeNode.id;
			var host_uuid = treeNode.tree_uuid;
			var clusterId = treeNode.getParentNode.id;
			var pool_uuid = treeNode.getParentNode.tree_uuid;
			var url = "xen_cloneVMByTemOnHostPage.do?host_id="+host_id+"&host_uuid="+host_uuid+
			"&clusterId="+clusterId+"&pool_uuid="+pool_uuid
			$("#right_iframe").attr("src",url);
			setDivVisProp("iframe");
		}
		//后续扩展Xen、IBM等虚拟化
	}
	hideRMenu();
}
	//添加主机
function host_add1(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		$.dialog({
			id:'addHost',
			title:'添加服务器',
			max:false,
			min:false,
			resize:false,
			lock:true,
			width: '430px',
			height: '100px',
			content: 'url:xen_saveHost1.do?ID='+treeNode.getParentNode().id +'&pool_uuid='+treeNode.tree_uuid});
	}
   hideRMenu();
}

function host_add2(ID,pool_uuid,name,username,password){
	$.getJSON("xen_saveHost.do?ID=" + ID+"&ip="+name+"&pool_uuid="+pool_uuid+"&username="+username+"&password="+password, {"time":new Date().toString()}, function (data) {
		var result = data.responseCode;
		if (result == 1) {
			var nodes = zTree.getNodeByParam("id",ID);
			//zTree.removeNode(nodes);
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			$.dialog.list['addHost'].close();
			alert("添加主机成功!");
		}else {
			$.dialog.list['delHost'].close();
			alert("添加主机失败,请查看日志!");
		}
    });
}


	//虚拟机查看
function virtual_view(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var hostId = treeNode.getParentNode().id;
			if(treeNode.type==27){//xen虚拟机	
			$("#right_iframe").attr("src", "xen_listVMInfo.do?entity_id=" + treeNode.entityId + "&oper=" + oper +"&host_uuid="+treeNode.getParentNode().tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid);
			setDivVisProp("iframe");
	  }
	}
	hideRMenu();
}

//调整虚拟机
function virtual_edit(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var hostId = treeNode.getParentNode().id;
			if(treeNode.type==27){//xen虚拟机	
			$("#right_iframe").attr("src", "xen_editListVMInfo.do?entity_id=" + treeNode.entityId + "&oper=" + oper +"&host_uuid="+treeNode.getParentNode().tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid);
			setDivVisProp("iframe");
	  }
	}
	hideRMenu();
}
//虚拟机控制台
function virtual_console(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var pool_uuid =treeNode.getParentNode().getParentNode().tree_uuid; 
		var host_uuid =treeNode.getParentNode().tree_uuid; 
		var entityId = treeNode.entityId;
		var state=treeNode.state;
		$.dialog({
			title:'控制台',
			width: '700px',
			height: 500,
			content: 'url:xen_vmConsole.do?pool_uuid='+pool_uuid+'&host_uuid='+host_uuid+'&entityId='+entityId+'&state='+state});
	}else{
		bar("控制台出错，无法弹出，请尝试其他方式！");
	}
	hideRMenu();
}
//虚拟机控制台
function virtual_console2(pool_uuid,host_uuid,entityId,state) {
		$.dialog({
			title:'控制台',
			width: '700px',
			height: 500,
			content: 'url:xen_vmConsole.do?pool_uuid='+pool_uuid+'&host_uuid='+host_uuid+'&entityId='+entityId+'&state='+state});
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
			if(treeNode.type==27) {//xen虚拟机
				$("#right_iframe").attr("src", "xen_goCloneVM.do?parent_id=" + parent_id+"&entity_id="+treeNode.entityId +"&host_uuid="+treeNode.getParentNode().tree_uuid+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid);
				setDivVisProp("iframe");
			}
		}
		
	}
	hideRMenu();
}

//通过模版创建虚拟机
function  create_virtual_clone_by_tem(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var parent_id = treeNode.getParentNode().id;
		var entityId = treeNode.entityId;
		if(treeNode){
			$.dialog({
				id:'createVmByTem',
				title:'创建虚拟机',
				width: '650px',
				height: '235px',
				max:false,
				resize:false,
				min:false,
				content: 'url:xen_goCreateVMByTem.do?parent_id=' + parent_id+'&entity_id='+treeNode.entityId+'&host_uuid='+treeNode.getParentNode().tree_uuid+'&pool_uuid='+treeNode.getParentNode().getParentNode().tree_uuid+'&parent_type='+treeNode.getParentNode().type+"&nodeId="+treeNode.id});
			setDivVisProp("iframe");
		}
		
	}
	hideRMenu();
}
//通过模版创建虚拟机2
function createVmByTem(url,nodeId){
	bar(url,"通过模版创建虚拟机中...");
	$.getJSON(url,{"time":new Date().toString()},function(data){
		if(data.responseCode == 1){
			barEnd(url,"虚拟机创建成功!");
			var nodes = zTree.getNodeByParam("id",nodeId);
			zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);
		}else if(data.responseCode == -1){
			alert("创建失败,详细信息请查看日志！");
			barEnd2(url);
		}
	});
}

//虚拟机迁移
function virtual_migrate(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var type = treeNode.type;
		var parent_id = treeNode.getParentNode().id;
		var cluster_id = treeNode.getParentNode().getParentNode().id;
		var pool_uuid =treeNode.getParentNode().getParentNode().tree_uuid; 
		var host_uuid =treeNode.getParentNode().tree_uuid; 
		if(type==27){//xen虚拟机
    		$.dialog({
    			id:'migrate',
    			title:'在线迁移',
    			width: '430px',
    			height: '70px',
    			max: false,
    		    min: false,
    			content: 'url:xen_goMigrate.do?cluster_id=' + cluster_id+'&id='+treeNode.getParentNode().id+'&pool_uuid='+pool_uuid+'&host_uuid='+host_uuid+'&vm_uuid='+treeNode.tree_uuid+'&nodeId='+treeNode.id
    			});
		}
}
	hideRMenu();
}
//迁移虚拟机
function migrateVm(vm_uuid,pool_uuid,host_uuid,newHostEntityID,nodeId){
	bar(nodeId,"开始迁移...");
	var url = 'xen_migrateVM.do?vm_uuid='+vm_uuid+'&pool_uuid='+pool_uuid+'&host_uuid='+host_uuid+'&newHostEntityID='+newHostEntityID;
	$.getJSON(url,{'time':new Date().toString()},function(data){
		if(data.responseCode==1){
			barEnd(nodeId,"迁移至主机成功!");
			var nodes = zTree.getNodeByParam("id",nodeId);
			zTree.reAsyncChildNodes(nodes.getParentNode().getParentNode(), "refresh",true);
		}else{
			barEnd(nodeId,"迁移至主机失败!");
		}
	});
}


//虚拟机移动，离线迁移
function virtual_move(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var type = treeNode.type;
		var parent_id = treeNode.getParentNode().id;
		var cluster_id = treeNode.getParentNode().getParentNode().id;
		var pool_uuid =treeNode.getParentNode().getParentNode().tree_uuid; 
		var host_uuid =treeNode.getParentNode().tree_uuid; 
		if(type==27){//xen虚拟机
    		$.dialog({
    			id:'move',
    			title:'在服务器上启动',
    			width: '430px',
    			height: '70px',
    			max: false,
    		    min: false,
    			content: 'url:xen_goMove.do?cluster_id=' + cluster_id+'&id='+treeNode.getParentNode().id+'&pool_uuid='+pool_uuid+'&host_uuid='+host_uuid+'&vm_uuid='+treeNode.tree_uuid+'&nodeId='+treeNode.id
    			});
		}
}
	hideRMenu();
}
//离线迁移虚拟机
function moveVm(vm_uuid,pool_uuid,host_uuid,newHostEntityID,nodeId){
	bar(nodeId,"启动虚拟机...");
	var url = 'xen_moveVM.do?vm_uuid='+vm_uuid+'&pool_uuid='+pool_uuid+'&host_uuid='+host_uuid+'&newHostEntityID='+newHostEntityID;
	$.getJSON(url,{'time':new Date().toString()},function(data){
		if(data.responseCode==1){
			barEnd(nodeId,"启动成功!");
			var nodes = zTree.getNodeByParam("id",nodeId);
			zTree.reAsyncChildNodes(nodes.getParentNode().getParentNode(), "refresh",true);
		}else{
			barEnd(nodeId,"启动失败!");
		}
	});
}


	//修改虚拟机状态，暂停、恢复、启动、停止、删除等
function put_virtual_state(state) {
	var stat = "";
	var nodes = rightSNode;
	if (state == "destroy") {
		stat = "\u5220\u9664";
	} else if (state == "create") {
			stat = "\u542f\u52a8";
		} else if (state == "shutdown") {
				stat = "\u5173\u95ed";
			} else if(state == "suspend") {
					stat = "挂起";
				} else if (state == "resume") {
						stat = "\u6062\u590d";
					} else if(state == "reboot") {
							stat = "\u91cd\u65b0\u542f\u52a8";
						}
					
	if (confirm("\u786e\u5b9a\u8981" + stat + "\u8be5\u865a\u62df\u673a\u5417\uff1f")) {
		var nodes = rightSNode;
		if (nodes) {
			var treeNode = nodes;
			 if(treeNode.type==27){//xen虚拟机
				if(state == "create"){
					bar(treeNode.entityId,"虚拟机启动中...");
				}else if(state == "suspend"){
					bar(treeNode.entityId,"虚拟机暂停中...");
				}else if(state == "shutdown"){
					bar(treeNode.entityId,"虚拟机关机中...");
				}else if(state == "resume"){
					bar(treeNode.entityId,"虚拟机恢复中...");
				}else if(state == "destroy"){
					bar(treeNode.entityId,"虚拟机销毁中...");
				}
				var treeNode = nodes;
				if(state == "create" || state == "suspend" || state == "shutdown"||state == "destroy"){
							treeNode.icon="sxcloud/images/virtual/opering.png";
							treeNode.state=3;
							zTree.updateNode(treeNode);
				}
				$.getJSON("xen_putVMState.do?entity_id=" + treeNode.entityId + "&state=" + state+"&id="+treeNode.id+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid,{"time:":new Date().toString()},function(data){
					var result = data.responseCode;
					if(result !=null && result ==1){
						barEnd(treeNode.entityId,stat + "成功");
						if(state == "create"){
							treeNode.icon="sxcloud/images/virtual/running.png";
							treeNode.state=0;
							zTree.updateNode(treeNode);
							//var parentNode = treeNode.getParentNode();
							//zTree.reAsyncChildNodes(parentNode, "refresh",false);
						}else if(state == "suspend"){
							treeNode.icon="sxcloud/images/virtual/paused.png";
							zTree.updateNode(treeNode);
							//zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",false);
						}else if(state == "shutdown"){
							treeNode.icon="sxcloud/images/virtual/VirtualMachine.png";
							treeNode.state=1;
							zTree.updateNode(treeNode);
						}else if(state == "resume"){
						    treeNode.icon="sxcloud/images/virtual/running.png";
							treeNode.state=0;
							zTree.updateNode(treeNode);
							//zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",false);
						}else if(state == "destroy"){
							//var parentNode = treeNode.getParentNode();
							zTree.removeNode(treeNode,false);
							//zTree.reAsyncChildNodes(parentNode, "refresh",false);
						}
					}else{
						if(state == "shutdown"){
							//treeNode.icon="sxcloud/images/virtual/running.png";
							//treeNode.state=1;
							//zTree.updateNode(treeNode);
							var parentNode = treeNode.getParentNode();
							zTree.reAsyncChildNodes(parentNode, "refresh",false);
						}else if(state == "create"){
							//treeNode.icon="sxcloud/images/virtual/VirtualMachine.png";
							//treeNode.state=0;
							//zTree.updateNode(treeNode);
							var parentNode = treeNode.getParentNode();
							zTree.reAsyncChildNodes(parentNode, "refresh",false);
						}else if(state == "suspend"){
							//treeNode.icon="sxcloud/images/virtual/running.png";
							//treeNode.state=0;
							//zTree.updateNode(treeNode);
							var parentNode = treeNode.getParentNode();
							zTree.reAsyncChildNodes(parentNode, "refresh",false);
						}
						barEnd(treeNode.entityId,stat + "失败");
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
			$("#right_iframe").attr("src", "cluster_addCluster.do?id=" + treeNode.id  + "&dcName=" + dcNode.name + "&entityId=" + dcNode.entityId);
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
			$("#right_iframe").attr("src", "cluster_conCluster.do?id=" + treeNode.entityId + "&name=" + treeNode.name + "&dcName=" + dcNode.name );
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

//删除主机
function delTreeNode() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var ID = treeNode.id;
		var type = treeNode.type;
		if(type==26){//主机
    		$.dialog({
    			id:'delHost',
    			title:'移除主机',
    			width: '430px',
    			height: '45px',
    			lock:true,
    			max: false,
    		    min: false,
    			content: 'url:xen_isCanDelHost.do?ID=' + ID+'&entity_id='+treeNode.entityId+'&pool_uuid='+treeNode.getParentNode().tree_uuid+'&host_uuid='+treeNode.tree_uuid
    			});
		}
   }
	hideRMenu();
}


function delHost(ID,entity_id,pool_uuid,host_uuid){
	$.getJSON("xen_delHost.do?ID=" + ID+"&entity_id="+entity_id+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid, {"time":new Date().toString()}, function (data) {
		var result = data.responseCode;
		if (result == 1) {
			var nodes = zTree.getNodeByParam("id",ID);
			zTree.removeNode(nodes);
			//zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);
			$.dialog.list['delHost'].close();
			alert("删除主机成功!");
		}else if (result == 'error') {
			$.dialog.list['delHost'].close();
			alert("该节点是父节点,不能被删除!");
		}else {
			$.dialog.list['delHost'].close();
			alert("删除主机失败!");
		}
    });
}

	//设置DIV显示隐藏属性
function setDivVisProp(divName) {
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
		
		if(treeNode.type==27){//xen虚拟机
		$("#right_iframe").attr("src", "xen_snapshotManager.do?entity_id=" + treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid+"&vm_uuid="+treeNode.tree_uuid);
		setDivVisProp("iframe");
		}
	}
	hideRMenu();
}

function executeSnapshot(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		
		if(treeNode.type==27){//xen虚拟机
			$("#right_iframe").attr("src", "xen_goSnapshotPage.do?entity_id=" + treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
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
			
			if(treeNode.type==27){//xen虚拟机
			$("#right_iframe").attr("src", "xen_goCreateTemPage.do?entity_id=" + treeNode.entityId+"&pool_uuid="+treeNode.getParentNode().getParentNode().tree_uuid+"&host_uuid="+treeNode.getParentNode().tree_uuid);
			setDivVisProp("iframe");
		}
		}
		
	}
	hideRMenu();
}
//删除xen数据中心
function delXenDataCenter(){
	alert();
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		if(confirm("确定要删除该数据中心吗?")==true){
		bar(treeNode.id,"正在删除数据中心:"+treeNode.name);
			$.getJSON("xen_deleteXenDataCenter.do?id=" + treeNode.id ,{'time': new Date().toString()},function(data){
				if(data.result==1){
					barEnd(treeNode.id,"删除数据中心成功!");
					zTree.removeNode(treeNode);
				}else if(data.result==2){
					barEnd(treeNode.id,"数据中心下存在集群，不能删除！");
				}else{
					barEnd(treeNode.id,"删除数据中心失败!");
				}
			})
		}
	}
	hideRMenu();
}
//删除Xen模板
function templet_remove(){
	var nodes = rightSNode;
	if(nodes){
		var treeNode = nodes;
		var parentNode = treeNode.getParentNode();
		var type = treeNode.type;
		var pool_uuid = treeNode.getParentNode().getParentNode().tree_uuid;
		var host_uuid = treeNode.getParentNode().tree_uuid;
		var vm_uuid = treeNode.tree_uuid;
		if(type==28){//xen模板
			if(confirm("确定删除该模板吗?")==true){
			bar(treeNode.entityId,"开始删除模版...");
			hideRMenu();
			var url = "xen_delTem.do?id=" + treeNode.id + "&vm_uuid=" + vm_uuid + "&entityId=" + treeNode.entityId+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid;
			$.getJSON(url,{'time':new Date().toString()},function(data){
				if(data.responseCode==1){
					barEnd(treeNode.entityId,"删除模板成功!");
					zTree.reAsyncChildNodes(parentNode, "refresh",true);
				}else{
					barEnd(treeNode.entityId,"删除模板失败!");
				}
			});
		}
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


//同步到集群（xen）
function conXenCluster(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
	    $.dialog({
			id:'sysXcpCenter',
			title:'添加新服务器',
			max:false,
			min:false,
			resize:false,
			width: '450px',
			height: '250px',
			content: 'url:xen_goConXenCluster.do?parent_id='+treeNode.id+'&pool_uuid='+treeNode.tree_uuid});
	}
	hideRMenu();
}

function conXenCluster2(name,username,password,parent_id){
	hideRMenu();
	bar("conXenCluster2","添加服务器，请稍后....");
	$.getJSON('xen_synConXenCluster.do?name=' + encodeURI(encodeURI(name))+"&username="+username+"&password="+encodeURIComponent(password)+"&parent_id="+parent_id,{'time':new Date().toString()}, function(data){
		if(data.responseCode ==1){
		    barEnd("conXenCluster2","添加服务器成功");
	    	refreshParentNode();
			window.location.reload();
		}else{
			alert("添加服务器失败");
		}
	});
}
//连接xen集群
function connectCluster(){
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		$.dialog({
			id:'conn',
			title:'连接xen集群',
			max:false,
			min:false,
			resize:false,
			width: '500px',
			height: '110px',
			content: 'url:xen_goConnectCluster.do?cluster_uuid='+treeNode.tree_uuid});
		//$("#right_iframe").attr("src", "xen_goConnectCluster.do?cluster_uuid="+treeNode.tree_uuid);
		//setDivVisProp("iframe");
    }
	hideRMenu();
}
//数据比对，资源重新同步
function  xenDataCompare(){
	if(confirm("资源池同步会重新更新本地数据,更新速度依资源池大小而定,确定同步吗?")==true){
		var nodes = rightSNode;
		if (nodes) {
			var treeNode = nodes;
			bar(treeNode.id,"开始资源池更新，请稍后...");
			$.getJSON("xendata_xenDataCompare.do?pool_uuid=" + treeNode.tree_uuid ,{'time': new Date().toString()},function(data){
				if(data.result==1){
					barEnd(treeNode.id,"资源池更新成功!");
					zTree.reAsyncChildNodes(treeNode, "refresh",true);
				}else{
					barEnd(treeNode.id,"资源池更新失败!");
					zTree.reAsyncChildNodes(treeNode, "refresh",true);
				}
			})
		}
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
	//var url = "vmw_rebootHost.do?name="  + treeNode.entityId + "&hostName=" + treeNode.name;
	if(confirm("重启过程中会丢失主机的链接,确定要重新启动主机吗?")==true){
	//	$.getJSON(url,{'time': new Date().toString()},function(data){
	//		if(data.result==1){
	//			alert("主机重新启动成功，请等待主机重启!");
	//			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
	//		}else{
	//			alert("主机重启失败!可能的原因:" + data.reason);
	//		}
	//	});
	}
	hideRMenu();
}

//关闭主机
function host_shutdown(){
	var treeNode = rightSNode;
	//var url = "vmw_shutdownHost.do?name="  + treeNode.entityId + "&hostName=" + treeNode.name;
	if(confirm("关闭主机后,会丢失主机的链接,确定要关闭主机吗?")==true){
	//	$.getJSON(url,{'time': new Date().toString()},function(data){
	//		mask('主机正在关闭，请稍候......');
	//		if(data.result==1){
	//			alert("主机关闭成功!");
	//			removeMask();
	//			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
	//		}else{
	//			alert("主机关闭失败!可能的原因:" + data.reason);
	//			removeMask();
	//		}
	//	});
	}
	hideRMenu();
}

//新建存储
function addNFS(){
		var nodes = rightSNode;
		if (nodes) {
		var treeNode = nodes;
		$.dialog({
			id:'createNewStore',
			title:'新建存储',
			max:false,
			min:false,
			resize:false,
			width: '600px',
			height: '250px',
			content: 'url:xen_addNFSPage.do?pool_uuid='+ treeNode.getParentNode().tree_uuid+'&host_uuid='+treeNode.tree_uuid+'&host_id='+treeNode.entityId+'&nodeId='+treeNode.id});
	}
	hideRMenu();
}

//主机新建存储
function addNewStore(url,nodeId){
	hideRMenu();
	mask("正在创建存储，请稍等....",0.5,0);
	$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.responseCode == 1){
				removeMask();
				var nodes = zTree.getNodeByParam("id",nodeId);
				zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);
				alert("创建SR成功!");
			}else if(data.responseCode == -1){
				removeMask();
				alert("创建SR失败!");
			}
		});
}

//对xen存储操作，分离、忘记、销毁
function changeStoreState(oper){
		hideRMenu();
		var nodes = rightSNode;
 	   	var url = "xen_operateStore.do?sr_uuid=" + nodes.tree_uuid +"&pool_uuid="+nodes.getParentNode().getParentNode().tree_uuid+"&host_uuid="+nodes.getParentNode().tree_uuid+"&oper="+oper+"&name="+encodeURI(encodeURI(nodes.name));
 	   	if(confirm("确定要进行当前操作吗?")==true){
 	   	   bar("changeStoreState","存储操作中，请稍后...");
 	   		$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.responseCode == 1){
 	    			if(oper==1){
 	    				barEnd("changeStoreState","分离存储成功!");
 	    			}else if(oper==2){
 	    				barEnd("changeStoreState","忘记存储成功!");
 	    			}else if(oper==3){
 	    				barEnd("changeStoreState","销毁存储成功!");
 	    			}
 	    			zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);
 	   		 	}else if(data.responseCode == -1){
 	   		 		if(oper==3){
 	    				nodes.icon="sxcloud/images/virtual/damagedStore.png";
						nodes.state="destroy";
						zTree.updateNode(nodes);
 	    			}
					barEnd("changeStoreState","存储操作失败!");
 		    	}
 		  	});
 	   	}
}
//跳转到xen存储重新连接页面
function reConnectSrPage(){
	hideRMenu();
	var nodes = rightSNode;
		if (nodes) {
		var treeNode = nodes;
		$.dialog({
			id:'reConnSR',
			title:'重新连接存储',
			max:false,
			min:false,
			resize:false,
			width: '600px',
			content: 'url:xen_reConnectSrPage.do?pool_uuid='+ treeNode.getParentNode().getParentNode().tree_uuid+'&sr_uuid='+treeNode.tree_uuid+'&host_uuid='+treeNode.getParentNode().tree_uuid});
	}
}
//xen存储重新连接
function reConnSr(url){
	hideRMenu();
	var nodes = rightSNode;
 	   	if(confirm("确定要进行当前操作吗?")==true){
 	   	   bar("reConnSr","存储重新连接中，请稍后...");
 	   		$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.responseCode == 1){
 	    			nodes.icon="sxcloud/images/virtual/storage.png";
 	    			nodes.state='connection';
					nodes.name=data.name;
					zTree.updateNode(nodes);
 	    			barEnd("reConnSr","重新连接成功!");
 	   		 	}else if(data.responseCode == -1){
 		    		barEnd("reConnSr","重新连接失败!");
 		    	}
 		  	});
 	   	}
}

//修复存储
function fixStore(){
		hideRMenu();
		var nodes = rightSNode;
 	   	if(confirm("确定要进行当前操作吗?")==true){
 	   	   bar("fixStore","存储修复中，请稍后...");
 	   	   	var url = "xen_fixStore.do?sr_uuid=" + nodes.tree_uuid +"&pool_uuid="+nodes.getParentNode().getParentNode().tree_uuid+"&host_uuid="+nodes.getParentNode().tree_uuid;
 	   		$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.responseCode == 1){
 	    			nodes.icon="sxcloud/images/virtual/storage.png";
 	    			nodes.state='connection';
					zTree.updateNode(nodes);
 	    			barEnd("fixStore","重新连接成功!");
 	   		 	}else if(data.responseCode == -1){
 		    		barEnd("fixStore","重新连接失败!");
 		    	}
 		  	});
 	   	}
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

function barEnd2(idstr){
	$.dialog.list[idstr].close();
}


