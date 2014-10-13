//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

// 设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}
//添加子节点动作，并刷新节点
function addChildDoAndLoadtree(currPath,newName,newDataValue,aliasName){
	var treeNode = rightSNode;
	var url = "zookeeperTree_saveChildNode.do?treeObj.name="+newName+"&currentTreeObj.fullPath="+currPath+"&treeObj.dataValue="+newDataValue+"&treeObj.aliasName="+aliasName;
	$.getJSON(url,
				{"time":new Date().toString()},
				function(msg){
					bar("addC","");
					barEnd("addC",msg);;//调用iframe子页面方法
					var nodes = treeNode.getParentNode();//只需要刷新父节点
					zTree.reAsyncChildNodes(nodes, "refresh",true);
				});
}
//添加子节点页面
function addChild(){
	hideRMenu();
	var treeNode = rightSNode;
	$("#right_iframe").attr(
			"src",
			"zookeeperTree_tabs.do?currentTreeObj.name="+treeNode.name+"&currentTreeObj.fullPath="+treeNode.fullPath+"&currentTreeObj.dataValue="+treeNode.dataValue+"&currentTreeObj.flag=add");
	setDivVisProp('iframe');
	hideRMenu();
}

//编辑当前节点页面
function editSelfNode(){
	hideRMenu();
	var treeNode = rightSNode;
	$("#right_iframe").attr(
			"src",
			"zookeeperTree_tabs.do?currentTreeObj.name="+treeNode.name+"&currentTreeObj.fullPath="+treeNode.fullPath+"&currentTreeObj.dataValue="+treeNode.dataValue+"&currentTreeObj.isParent="+treeNode.isParent+"&currentTreeObj.hiddName="+treeNode.hiddName+"&currentTreeObj.flag=edit");
	setDivVisProp('iframe');
	hideRMenu();
}
//编辑操作
function saveAfterEdit(currPath,newName,newDataValue,isPare){
	var treeNode = rightSNode;
/*	$.ajax({
		 type: "post",
		 url: "zookeeperTree_saveAfterEdit.do?newDirName="+newName+"&currPath="+currPath+"&newDirValue="+newDataValue+"&isPare="+isPare,
		 dataType: "json",
		 async:true,//同步的话，弹出框显示有问题
		 cache:false,
		 success : function(msg){
				xxxx.barEnd("editChild",msg);//调用iframe子页面方法
				var nodes = treeNode.getParentNode();
				zTree.reAsyncChildNodes(nodes, "refresh",true);
			}
	 	});*/
	var url = "zookeeperTree_saveAfterEdit.do?newDirName="+newName+"&currPath="+currPath+"&newDirValue="+newDataValue+"&isPare="+isPare;
	$.getJSON(url,
				{"time":new Date().toString()},
				function(msg){
					bar("editC","");
					barEnd("editC",msg);//调用iframe子页面方法
					var nodes = treeNode.getParentNode();//只需要刷新父节点
					zTree.reAsyncChildNodes(nodes, "refresh",true);
				});
}
//删除当前节点
function delSelfNode(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
		if(confirm("确认移除吗?")){
			bar("delSelf","正在移除节点，请稍候...");
			var url = "zookeeperTree_delSelfNode.do?currPath="+treeNode.fullPath;
			$.getJSON(url,
					{"time":new Date().toString()},
					function(data){
						barEnd("delSelf",data);
						//对于删除时需要对父节点的父节点进行刷新，显示才更合理
						var nodes = treeNode.getParentNode().getParentNode();
						zTree.reAsyncChildNodes(nodes, "refresh",true);
					});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
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
	$.dialog.list[idstr].time(5);
}
function closeBar(idstr){
	$.dialog.list[idstr].close();
}

function closeDialog(idstr){
	$.dialog.list[idstr].close();
}
