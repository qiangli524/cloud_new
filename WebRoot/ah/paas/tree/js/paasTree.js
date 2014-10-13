//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

// 设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}

//---------------------------------------Caas相关操作start----------------------------------------
//添加缓存
function addCache(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addCache',
		title:'添加缓存',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addCache.do?treeObj.parent_id='+treeNode.id
	});
}
//保存缓存
function saveCache(parentId,treeObj){
	 bar("saveCache","正在添加缓存，请稍候...");
	 var url = 'paasTree_saveCache.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveCache",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除缓存
function delCache(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delCache","正在移除缓存，请稍候...");
			var url = "paasTree_delCache.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delCache",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//添加业务
function addCacheBusiness(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addCacheBusiness',
		title:'添加业务',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addCacheBusiness.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存业务
function saveCacheBusiness(parentId,treeObj){
	 bar("saveCacheBusiness","正在添加业务，请稍候...");
	 var url = 'paasTree_saveCacheBusiness.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveCacheBusiness",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除业务
function delCacheBusiness(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delCacheBusiness","正在移除业务，请稍候...");
			var url = "paasTree_delCacheBusiness.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delCacheBusiness",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
} 

//添加数据库实例
function addCacheExamples(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addCacheExample',
		title:'添加实例',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addCacheExample.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存数据库实例
function saveCacheExample(parentId,treeObj){
	bar("saveCacheExample","正在添加实例，请稍候...");
	 var url = 'paasTree_saveCacheExample.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveCacheExample",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除数据库实例
function delCacheExamples(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delCacheExample","正在移除实例，请稍候...");
			var url = "paasTree_delCacheExample.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delCacheExample",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}

//添加主机
function addCacheHost(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addCacheHost',
		title:'添加主机',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addCacheHost.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存主机
function saveCacheHost(parentId,treeObj){
	bar("saveCacheHost","正在添加实例，请稍候...");
	 var url = 'paasTree_saveCacheHost.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveCacheHost",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除主机
function delCacheHost(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delCacheHost","正在移除实例，请稍候...");
			var url = "paasTree_delCacheHost.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delCacheHost",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//---------------------------------------Caas相关操作end ----------------------------------------


//---------------------------------------Daas相关操作start----------------------------------------
//添加数据库类型
function addDBType(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addDBType',
		title:'添加数据库类型',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addDBType.do?treeObj.parent_id='+treeNode.id
	});
}
//保存数据库类型
function saveDBType(parentId,treeObj){
	 bar("saveDBType","正在添加数据库类型，请稍候...");
	 var url = 'paasTree_saveDBType.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveDBType",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除数据库类型
function delDBType(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delDBType","正在移除数据库类型，请稍候...");
			var url = "paasTree_delDBType.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delDBType",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//添加业务
function addDBBusiness(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addDBBusiness',
		title:'添加业务',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addDBBusiness.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存业务
function saveDBBusiness(parentId,treeObj){
	 bar("saveDBBusiness","正在添加业务，请稍候...");
	 var url = 'paasTree_saveDBBusiness.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveDBBusiness",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}

//生成节点
function autoGeneration(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	bar("autoGeneration","正在生成节点,请稍候...");
	var url = "paasTree_autoGeneration.do?treeObj.id="+treeNode.id;
	$.getJSON(url,{"time":new Date().toString()},function(data){
	barEnd("autoGeneration",data);
	var nodes = treeNode.getParentNode().getParentNode();
	zTree.reAsyncChildNodes(nodes, "refresh",true);
	});
} 

//删除业务
function delDBBusiness(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delDBBusiness","正在移除业务，请稍候...");
			var url = "paasTree_delDBBusiness.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delDBBusiness",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
} 

//添加数据库
function addDB(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addDB',
		title:'添加',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addDB.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存数据库
function saveDB(parentId,treeObj,server_type){
	bar("saveDB","正在添加，请稍候...");
	 var url = 'paasTree_saveDB.do?'+treeObj+'&server_type='+server_type;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveDB",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除数据库
function delDB(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delDB","正在移除，请稍候...");
			var url = "paasTree_delDB.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delDB",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}

//添加数据库实体
function addDBEntity(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addDBEntity',
		title:'添加',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addDBEntity.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type+'&treeObj.node_type='+treeNode.node_type
	});
}
//保存数据库实体
function saveDBEntity(parentId,treeObj){
	bar("saveDBEntity","正在添加，请稍候...");
	 var url = 'paasTree_saveDBEntity.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveDBEntity",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除数据库实体
function delDBEntity(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delDBEntity","正在移除，请稍候...");
			var url = "paasTree_delDBEntity.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delDBEntity",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}

/**
 * 用于执行重命名操作，重置节点为可以编辑状态
 * @returns
 */
function renameBusi(){
	hideRMenu();
	var treeNode = rightSNode;
	//重置名称为可编辑状态
	zTree.editName(treeNode);
}
/**
 * 执行重命名的保存操作
 * @param
 * @returns ture 
 */
function saveRenameBusi(currentTreeNode,newName,oldName,type){
	var treeNode = currentTreeNode;
	newName = newName.trim();
	if(newName == oldName){
		//用户没有修改名称
		zTree.cancelEditName(oldName);
		return false;
	}else{
		if(newName == ""){
			alert("名称不能为空");
			zTree.cancelEditName(oldName);
			return false;
		}
		bar("saveRenameBusi","正在重新命名，请稍候...");
		var url = "paasTree_renameBusi.do?treeObj.id="+treeNode.id+"&treeObj.name="+newName;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("saveRenameBusi",data);
			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
		 });
	}
}
//---------------------------------------Daas相关操作end ----------------------------------------

//---------------------------------------Maas相关操作start----------------------------------------
//添加中间件类型
function addMiddlewareType(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addMiddlewareType',
		title:'添加中间件类型',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addMiddlewareType.do?treeObj.parent_id='+treeNode.id
	});
}
//保存中间件类型
function saveMiddlewareType(parentId,treeObj){
	 bar("saveMiddlewareType","正在添加中间件类型，请稍候...");
	 var url = 'paasTree_saveMiddlewareType.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveMiddlewareType",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除中间件类型
function delMiddlewareType(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delMiddlewareType","正在移除中间件类型，请稍候...");
			var url = "paasTree_delMiddlewareType.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delMiddlewareType",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//添加业务
function addMiddlewareBusiness(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addMiddlewareBusiness',
		title:'添加业务',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addMiddlewareBusiness.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存业务
function saveMiddlewareBusiness(parentId,treeObj){
	 bar("saveMiddlewareBusiness","正在添加业务，请稍候...");
	 var url = 'paasTree_saveMiddlewareBusiness.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveMiddlewareBusiness",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除业务
function delMiddlewareBusiness(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delMiddlewareBusiness","正在移除业务，请稍候...");
			var url = "paasTree_delMiddlewareBusiness.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delMiddlewareBusiness",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
} 

//添加中间件
function addMiddleware(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addMiddleware',
		title:'添加中间件',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addMiddleware.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type
	});
}
//保存中间件
function saveMiddleware(parentId,treeObj){
	bar("saveMiddleware","正在添加中间件，请稍候...");
	 var url = 'paasTree_saveMiddleware.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveMiddleware",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除中间件
function delMiddleware(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delMiddleware","正在移除中间件，请稍候...");
			var url = "paasTree_delMiddleware.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delMiddleware",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}

//添加实体
function addMiddlewareEntity(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
		id:'addMiddlewareEntity',
		title:'添加',
		max:false,
		min:false,
		height:'225px',
		width:'400px',
		lock:true,
		content:'url:paasTree_addMiddlewareEntity.do?treeObj.parent_id='+treeNode.id+'&treeObj.server_type='+treeNode.server_type+'&treeObj.node_type='+treeNode.node_type
	});
}
//保存实体
function saveMiddlewareEntity(parentId,treeObj){
	bar("saveMiddlewareEntity","正在添加，请稍候...");
	 var url = 'paasTree_saveMiddlewareEntity.do?'+treeObj;
	 $.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("saveMiddlewareEntity",data);
		var nodes = zTree.getNodeByParam("id",parentId);
		if(!nodes.isParent){
			nodes.isParent=true;
			zTree.updateNode(nodes);
		}
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	 });
}
//删除实体
function delMiddlewareEntity(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delMiddlewareEntity","正在移除，请稍候...");
			var url = "paasTree_delMiddlewareEntity.do?treeObj.id="+treeNode.id;
			$.getJSON(url,{"time":new Date().toString()},function(data){
			barEnd("delMiddlewareEntity",data);
			var nodes = treeNode.getParentNode().getParentNode();
			zTree.reAsyncChildNodes(nodes, "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//---------------------------------------Maas相关操作end ----------------------------------------

//---------------------------------------Hadoop相关操作strat ----------------------------------------
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
			lock:true,
			height:'300px',
			width:'450px',
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
			lock:true,
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
			lock:true,
			height:'600px',
			width:'1066px',
			content:'url:topo_newTopo.do?parentId='+treeNode.id
		});
	}
}
//---------------------------------------Hadoop相关操作end ----------------------------------------

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
	$.dialog.list[idstr].time(5);
}
function closeBar(idstr){
	$.dialog.list[idstr].close();
}

function closeDialog(idstr){
	$.dialog.list[idstr].close();
}
