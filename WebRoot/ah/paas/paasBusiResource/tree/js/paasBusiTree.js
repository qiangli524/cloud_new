//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

// 设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}

//添加子系统
function addChildSys(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addChildSys',
		title:'添加子系统',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		content:'url:paasBusiTree_addChildSys.do?uuid='+treeNode.uuid
	});
}
//保存子系统
function saveChildSys(parentId,paasBusiTreeObj){
	 var treeNode = rightSNode;
	 bar("saveChildSys","正在添加，请稍候...");
	 var url = 'paasBusiTree_saveChildSys.do?'+paasBusiTreeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveChildSys",data);
		var nodes = treeNode.getParentNode();//只需要刷新父节点
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除子系统
function delChildSys(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delChildSys","正在移除，请稍候...");
			var url = "paasBusiTree_delNode.do?paasBusiTreeObj.uuid="+treeNode.uuid;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delChildSys",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
} 
//添加业务(页面)
function addBusi(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addBusi',
		title:'添加业务',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		content:'url:paasBusiTree_addBusi.do?uuid='+treeNode.uuid
	});
}
//保存业务
function saveBusi(parentId,paasBusiTreeObj){
	var treeNode = rightSNode;
	 bar("saveBusi","正在添加，请稍候...");
	 var url = 'paasBusiTree_saveBusi.do?'+paasBusiTreeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveBusi",data);
		var nodes = treeNode.getParentNode();//只需要刷新父节点
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//移除业务
function delBusi(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delBusi","正在移除，请稍候...");
			var url = "paasBusiTree_delNode.do?paasBusiTreeObj.uuid="+treeNode.uuid;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delBusi",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//添加应用(页面)
function addApp(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addApp',
		title:'添加应用',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		content:'url:paasBusiTree_addApp.do?uuid='+treeNode.uuid
	});
}
//保存 应用
function saveApp(parentId,paasBusiTreeObj){
	var treeNode = rightSNode;
	 bar("saveApp","正在添加，请稍候...");
	 var url = 'paasBusiTree_saveApp.do?'+paasBusiTreeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveApp",data);
		var nodes = treeNode.getParentNode();//只需要刷新父节点
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//移除应用
function delApp(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delApp","正在移除，请稍候...");
			var url = "paasBusiTree_delNode.do?paasBusiTreeObj.uuid="+treeNode.uuid;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delApp",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//添加物理主机
function addHosts(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addHosts',
		title:'添加主机',
		max:false,
		min:false,
		height:'500px',
		width:'650px',
		content:'url:paasBusiTree_addHosts.do?uuid='+treeNode.uuid+"&server_type="+treeNode.server_type
	});
}
//保存物理主机
function saveHosts(parentId,eq_ids,host_names,server_type){
	var treeNode = rightSNode;
	 bar("saveHosts","正在添加，请稍候...");
	 var url = 'paasBusiTree_saveHosts.do?parent_id='+parentId+"&eq_ids="+eq_ids+"&h_names="+host_names+"&server_type="+server_type;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveHosts",data);
		var nodes = treeNode.getParentNode();//只需要刷新父节点
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//添加虚拟机
function addVmHosts(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addVmHosts',
		title:'添加主机',
		max:false,
		min:false,
		height:'500px',
		width:'650px',
		content:'url:paasBusiTree_addVmHosts.do?uuid='+treeNode.uuid+"&server_type="+treeNode.server_type
	});
}
//保存虚拟机
function saveVmHosts(parentId,vm_ids,vm_names,server_type){
	var treeNode = rightSNode;
	 bar("saveVmHosts","正在添加，请稍候...");
	 var url = 'paasBusiTree_saveVmHosts.do?parent_id='+parentId+"&vm_ids="+vm_ids+"&vm_names="+vm_names+"&server_type="+server_type;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveVmHosts",data);
		var nodes = treeNode.getParentNode();//只需要刷新父节点
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
// 提示
// 使用方法：barEnd是关闭弹出的bar的方法，注意，id必须唯一，并且bar的id和barEnd的id一样。
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
function messagebar(idstr,contents){
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
	$.dialog.list[idstr].time(5);
}
function barChangeContent(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
}
function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(4);
}
function closeBar(idstr){
	$.dialog.list[idstr].close();
}

function closeDialog(idstr){
	$.dialog.list[idstr].close();
}
