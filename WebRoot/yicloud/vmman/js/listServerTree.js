
function pageOnLoad() {
	$("#report_virtual").hide();
	$("#report_host").hide();
	$("#image_list").hide();
	$("#host_add_page").hide();
	$("#virtual_lookup").hide();
	$("#app_deploy_div").hide();
	$("#virtual_conn_div").hide();
	$("#cluster_add").hide();
	$("#health_state").hide();
	$("#datacenterinfo").hide();
	$("#clusterinfo").hide();
	$("#host_info").hide();
	$("#vm_info").hide();
	$("#imageinfo").hide();
	$("#datastoreinfo").hide();
}
	//虚拟机监控
function virtual_monitor() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if (treeNode && !treeNode.noR && !treeNode.isParent && (treeNode.getParentNode().id != 80)) {
			$("#virtual_monitor_iframe").attr("src", "http://172.21.0.117:8480/display/showMonitorInfo.action?type=uncomp&kbpClass=10-10-24:csapp$" + treeNode.name);
			setDivVisProp("report_virtual");
		}
	}
	hideRMenu();
}
	//虚拟机添加
function virtual_add() {
alert();
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		alert(treeNode.entityId);
		return;
		//KVM虚拟镜像部署
		if (treeNode.type==22)  {
			oper = '1';
		} else if (treeNode.type==1) {//VMware创建裸机
			oper = '2';
		}
		//后续扩展Xen、IBM等虚拟化
		$("#image_list_iframe").attr("src", "yvm_lookupImage.do?ENTITY_ID="+treeNode.entityId+"&TYPE="+treeNode.type+"&oper="+oper);
		setDivVisProp("image_list");
	}
	hideRMenu();
}
	//添加主机
function host_add() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		if ((treeNode && !treeNode.noR)) {
			$("#host_add_iframe").attr("src", "yvm_addHost.do?ID=" + treeNode.id + "&TYPE=" + treeNode.type);
			setDivVisProp("host_add_page");
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
			$("#virtual_conn_iframe").attr("src", "depvideo_loginHost.do?oper=yicloud" + "&vID=" + treeNode.id);
			setDivVisProp("virtual_conn_div");
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
			$("#host_monitor_iframe").attr("src", "http://172.21.0.117:8480/display/showMonitorInfo.action?type=uncomp&kbpClass=10-10-24:csapp");
			setDivVisProp("report_host");
		}
	}
	hideRMenu();
}
	//虚拟机查看
function virtual_view(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		var hostId = treeNode.getParentNode().id;
		if (treeNode && !treeNode.noR && !treeNode.isParent && (treeNode.getParentNode().id != 80)) {
			$("#virtual_lookup_iframe").attr("src", "yvm_checkKVMVirtualInfo.do?hostId=" + hostId + "&oper=" + oper + "&ID=" + treeNode.id);
			setDivVisProp("virtual_lookup");
		}
	}
	hideRMenu();
}
	//修改虚拟机状态，暂停、恢复、启动、停止、删除等
function put_virtual_state(state) {
	var stat = "";
	if (state == "destroy") {
		stat = "\u5220\u9664";
	} else {
		if (state == "start") {
			stat = "\u542f\u52a8";
		} else {
			if (state == "shutdown") {
				stat = "\u5173\u95ed";
			} else {
				if (state == "suspend") {
					stat = "\u6682\u505c";
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
			var hostId = treeNode.getParentNode().id;
			$.getJSON("yvm_putVirtualStat.do?hostId=" + hostId + "&state=" + state + "&ID=" + treeNode.id, {"time":new Date().toString()}, function (data) {
				var result = data.result;
				if (result == null) {
					if (state == "destroy") {
						zTree.removeNode(treeNode);
					}
					alert("\u64cd\u4f5c\u6210\u529f!");
				} else {
					alert("\u64cd\u4f5c\u5931\u8d25");
				}
			});
		}
		hideRMenu();
	}
}
	//应用部署
function app_deploy(oper) {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		$("#app_deploy_iframe").attr("src", "addDeployExample.do?oper=" + oper + "&virtualId=" + treeNode.id);
		setDivVisProp("app_deploy_div");
	}
	hideRMenu();
}
	
	//添加集群
function addCluster() {
	var nodes = rightSNode;
	if (nodes) {
		var treeNode = nodes;
		alert(treeNode.type);
		if ((treeNode && !treeNode.noR)) {
			$("#cluster_add_iframe").attr("src", "yvm_addCluster.do?id=" + treeNode.id + "&TYPE="+treeNode.type);
			setDivVisProp("cluster_add");
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
		$.getJSON("yvm_delTreeNode.do?ID=" + ID, {"time":new Date().toString()}, function (data) {
			var result = data.result;
			if (result == null) {
				zTree.removeNode(treeNode);
				alert("\u6210\u529f\u5220\u9664");
			} else {
				alert("\u5f53\u524d\u8282\u70b9\u5b58\u5728\u5b50\u8282\u70b9\uff0c\u4e0d\u5141\u8bb8\u5220\u9664");
			}
		});
	}
	hideRMenu();
}

	//设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}
//定时获取状态
timer();
function timer() {
	alert();
	var tree = $.fn.zTree.getZTreeObj("treeDemo");
	if (tree != null) {
		setStatus(tree);
	}
    setTimeout("timer()",3000);    
}
function setStatus(tree) {
	alert();
	var nodes = zTree.getNodesByParam("type",25, null);
	if(nodes && nodes.length > 0){
	for(j=0; j< nodes.length;j++){
	
	var childNodes = nodes[j].children;
	if (childNodes && childNodes.length > 0) {
		for (i = 0; i < childNodes.length; i++) {
			var treeNode = childNodes[i];
			if (treeNode.type == 0 ) {
				$.getJSON("yvm_virtual_state.do?ENTITY_ID=" + treeNode.entityId+"&PARENT_ID="+treeNode.parentId, {"time":new Date().toString()}, function (data) {
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

