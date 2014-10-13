//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}


//增加根节点-业务中心
function addBusiSysCenter(){
	var dialog = $.dialog({
		id:'datacenter',
		title:'添加业务中心',
		max:false,
		min:false,
		height:'120px',
		lock : true,
		content:'url:bmanager_addBusiSysCenter.do'
	});
	hideRMenu();
}

function saveBusiSysCenter(name){
  	 $.ajax({
            type: "POST",
            url: "bmanager_saveBusiSysCenter.do",
            data:{"name":name},
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	  //添加成功
	              }
              }
    });
}

// 设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}

//业务系统相关
//添加业务系统页面
function addBizsys() {
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'goAddBizsysPage',
			title:'添加业务系统',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			lock : true,
			content:'url:bizsystem_addBizSystem.do?createType=tree'+"&nodeId="+treeNode.id
		});
	}
}
function saveBizsys(busiSystemObj){
	if(busiSystemObj.flag==0){
		bar("addBizsys","添加业务系统中，请稍后...");
	}else{
		bar("modifyBizSysBar","修改业务系统中，请稍后...");
	}
	var formData = $.formDataFormat(busiSystemObj.theForm);
	var url = "bizsystem_saveBizSystem.do?" + formData + "&createType=tree" + "&nodeId=" +busiSystemObj.nodeId;
	$.getJSON(url,{"time":new Date().toString()},function(data){
		if(data.result == 1){
			if(busiSystemObj.flag==0){
				var nodes = zTree.getNodeByParam("id",busiSystemObj.nodeId);
				var isParent = nodes.isParent;
				if(!isParent){
					nodes.isParent=true;
					zTree.updateNode(nodes);
				}
				zTree.reAsyncChildNodes(nodes, "refresh",true);
				barEnd("addBizsys","添加业务系统成功!");
			}else{
				var nodes = zTree.getNodeByParam("id",busiSystemObj.nodeId).getParentNode();
				zTree.reAsyncChildNodes(nodes, "refresh",true);
				barEnd("modifyBizSysBar","修改业务系统成功!");
			}
		}else if(data.result == -1){
			if(busiSystemObj.flag==0){
				barEnd("addBizsys","添加业务系统失败!");
			}else{
				barEnd("modifyBizSysBar","修改业务系统失败!");
			}
		}
	});
}
//修改业务系统页面
function modifyBizsys() {
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'modifyBizsys',
			title:'修改业务系统',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			lock : true,
			content:'url:bizsystem_modBizSystem.do?createType=tree'+"&nodeId="+treeNode.id
		});
	}
}
//删除业务系统
function delBizsys(){
	if(confirm("确定要删除业务系统？")){
		hideRMenu();
		var treeNode = rightSNode;
		var nodeId = treeNode.id;
		var nodeType = treeNode.type;
		$.getJSON('busisys_validateDelete.do?id=' + nodeId+'&type='+nodeType,{'time':new Date().toString()}, function(data){
						if(data=='true'){
							var url = "bizsystem_delBizSystem.do?nodeId=" + nodeId + "&operType="+"tree";
							bar("delBizsys","删除业务系统中，请稍等...");
							$.getJSON(url,{"time":new Date().toString()},function(data){
								if(data.result == 1){
									zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
									barEnd("delBizsys","删除业务系统成功!");
								}else if(data.result == -1){
									barEnd("delBizsys","删除业务系统失败!");
								}
							});
						}else{
							alert("该节点不为空不能删除！");
						}
					});
	}
}

//基准应用相关
//跳转添加基准应用页面
function addSysApp(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'goAddSysAppPage',
			title:'添加基准应用',
			max:false,
			min:false,
			height:'400px',
			width:'740px',
			lock : true,
			button: [
    	              {
    	              	  id:'OK',
    	                  name: '保存',
    	                  callback:addSysAppOperateFromTree,
    	                  focus: true
    	              },
    	              {
    	                  name: '取消'
    	              }
        	      ],
			content:'url:app_addApp.do?operType=tree'+"&nodeId="+treeNode.id+"&dialogName="+"goAddSysAppPage"
		});
	}
}
function addSysAppOperateFromTree(){
	$.dialog.list["goAddSysAppPage"].content.addSysAppOperateFromTree();
	return false;
}
//修改基准应用
function modifySysApp() {
	hideRMenu();
	var treeNode = rightSNode;
	$.getJSON("app_AppFlagStatus.do?nodeId="+treeNode.id+"&operType="+"tree",{'time':new Date().toString()},function(data){
		if(data=='2' || data=='3' || data=='4' || data=='6' || data=='7'){
		  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被修改！");
		}else{
			if (treeNode) {
				$.dialog({
					id:'goModifyBizsysPage',
					title:'修改基准应用',
					max:false,
					min:false,
					height:'400px',
					width:'740px',
					lock : true,
					button: [
	    	              {
	    	              	  id:'OK',
	    	                  name: '保存',
	    	                  callback:modSysAppOperateFromTree,
	    	                  focus: true
	    	              },
	    	              {
	    	                  name: '取消'
	    	              }
	        	      ],
					content:'url:app_modApp.do?operType=tree'+"&nodeId="+treeNode.id+"&dialogName="+"goModifyBizsysPage"
				});
			}
		}
	});
}
function modSysAppOperateFromTree(){
	$.dialog.list["goModifyBizsysPage"].content.addSysAppOperateFromTree();
	return false;
}
//保存基准应用
function saveApp(busiAppObj){
	closeDialog(busiAppObj.dialogName);
	var ID = busiAppObj.ID;
	if(ID==0){
		bar("addApp","添加基准应用中，请稍候...");
	}else{
		bar("modifyApp","修改基准应用中，请稍候...");
	}
	var formData = $.formDataFormat(busiAppObj.theFormData);
	var url = "app_saveApp.do?" + formData + "&nodeId=" + busiAppObj.nodeId + "&operType=tree";
	$.getJSON(url,{"time":new Date().toString()},function(data){
		if(data.result == 1){
			if(ID==0){
				var nodes = zTree.getNodeByParam("id",busiAppObj.nodeId);
				var isParent = nodes.isParent;
				if(!isParent){
					nodes.isParent=true;
					zTree.updateNode(nodes);
				} 
				zTree.reAsyncChildNodes(nodes, "refresh",true);
				barEnd("addApp","添加基准应用成功!");
			}else{
				var nodes = zTree.getNodeByParam("id",busiAppObj.nodeId).getParentNode();
				zTree.reAsyncChildNodes(nodes, "refresh",true);
				barEnd("modifyApp","修改基准应用成功!");
			}
		}else if(data.result == -1){
			if(ID==0){
				barEnd("addApp","添加基准应用失败!");
			}else{
				barEnd("modifyApp","修改基准应用失败!");
			}
		}
	});
}

//删除基准应用
function delSysApp(){
	hideRMenu();
	var treeNode = rightSNode;
	var nodeId = treeNode.id;
	var nodeType = treeNode.type;
	$.getJSON('busisys_validateDelete.do?id=' + nodeId+'&type='+nodeType,{'time':new Date().toString()}, function(data){
				if(data=='true'){
					 $.getJSON("app_AppFlagStatus.do?nodeId="+treeNode.id+ "&operType="+"tree",{'time':new Date().toString()},function(data){
							if(data=='2' || data=='3' || data=='4'|| data=='6' || data=='7'){
							  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被删除！");
							  return "false";
							}else{
								if(confirm("确定要删除基准应用？")){
									var url = "app_delApp.do?nodeId=" + treeNode.id + "&operType="+"tree";
									bar("delSysApp","删除基准应用中，请稍等...");
									$.getJSON(url,{"time":new Date().toString()},function(data){
										if(data.result == 1){
											zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
											barEnd("delSysApp","删除基准应用成功!");
										}else if(data.result == -1){
											barEnd("delSysApp","删除基准应用失败!");
										}
									});
								}
							}
						});
				}else{
					alert("该节点不为空不能删除！");
					return "false";
				}
			});
}

//部署实例相关
//跳转到添加部署实例页面
function addDeployExample(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'goAddDeployExamplePage',
			title:'添加部署实例',
			max:false,
			min:false,
			height:'400px',
			width:'740px',
			lock : true,
			button: [
	    	              {
	    	              	  id:'OK',
	    	                  name: '保存',
	    	                  callback:addDeployExampleOperateFromTree,
	    	                  focus: true
	    	              },
	    	              {
	    	                  name: '取消'
	    	              }
	        	      ],
			content:'url:dep_addDeployExample.do?operType=tree'+"&nodeId="+treeNode.id+"&dialogName="+"goAddDeployExamplePage"
		});
	}
}
function addDeployExampleOperateFromTree(){
	$.dialog.list["goAddDeployExamplePage"].content.submitForm();
	return false;
}
//修改部署实例
function modifyDeployExample(){
	 hideRMenu();
	 var treeNode = rightSNode;
	 $.getJSON("dep_DeployFlagStatus.do?nodeId="+treeNode.id+"&operType="+"tree",{'time':new Date().toString()},function(data){
			if(data=='1' || data=='2' || data=='3'){
			  alert("部署实例状态为 '未部署' 或 '已卸载' 的状态才能被修改！");
			}else{
		      $.dialog({
			  	id:'goModifyDeployExamplePage',
				title:'修改部署实例',
				max:false,
				min:false,
				height:'400px',
				width:'740px',
				lock : true,
				button: [
	    	              {
	    	              	  id:'OK',
	    	                  name: '保存',
	    	                  callback:modifyDeployExampleOperateFromTree,
	    	                  focus: true
	    	              },
	    	              {
	    	                  name: '取消'
	    	              }
	        	      ],
				content:'url:dep_modDeployExample.do?operType=tree'+"&nodeId="+treeNode.id+"&dialogName="+"goModifyDeployExamplePage"
			  });
			}
		});
}
function modifyDeployExampleOperateFromTree(){
	$.dialog.list["goModifyDeployExamplePage"].content.submitForm();
	return false;
}
//保存部署实例
function saveDeployExample(deployExampleObj){
	closeDialog(deployExampleObj.dialogName);
	var ID = deployExampleObj.ID;
	if(ID==0){
		bar("addDeployExample","添加部署实例中，请稍后...");
	}else{
		bar("modifyDeployExample","修改部署实例中，请稍后...");
	}
	var theFormData = $.formDataFormat(deployExampleObj.theFormData);
	var url = "dep_saveDeployExample.do?" + theFormData + "&nodeId=" + deployExampleObj.nodeId + "&operType="+"tree";
	$.getJSON(url,{"time":new Date().toString()},function(data){
		if(data.result == 1){
			if(ID==0){
				var nodes = zTree.getNodeByParam("id",deployExampleObj.nodeId);
				var isParent = nodes.isParent;
				if(!isParent){
					nodes.isParent=true;
					zTree.updateNode(nodes);
				}
				zTree.reAsyncChildNodes(nodes, "refresh",true);
				barEnd("addDeployExample","添加部署实例成功!");
			}else{
				var nodes = zTree.getNodeByParam("id",deployExampleObj.nodeId).getParentNode();
				zTree.reAsyncChildNodes(nodes, "refresh",true);
				barEnd("modifyDeployExample","修改部署实例成功!");
			}
		}else if(data.result == -1){
			if(ID==0){
				barEnd("addDeployExample","添加部署实例失败!");
			}else{
				barEnd("modifyDeployExample","修改部署实例失败!");
			}
		}
	});
}
//删除部署实例
function delDeployExample(){
	 hideRMenu();
	 var treeNode = rightSNode;
	 bar("delDeployExample","校验中，请稍等...");
	 $.getJSON("dep_DeployFlagStatus.do?nodeId="+treeNode.id+"&operType="+"tree",{'time':new Date().toString()},function(data){
			if(data=='1' || data=='2' || data=='3'){
			  closeBar("delDeployExample");
			  alert("部署实例状态为 '未部署' 或 '已卸载' 的状态才能被删除！");
			}else{
			  if(confirm("确定要删除该部署实例吗?")==true){
			  	barChangeContent("delDeployExample","删除部署实例中，请稍等...");
		        var url = "dep_delDeployExample.do?nodeId=" + treeNode.id + "&operType="+"tree";
				$.getJSON(url,{"time":new Date().toString()},function(data){
					if(data.result == 1){
						zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
						barEnd("delDeployExample","删除部署实例成功!");
					}else if(data.result == -1){
						barEnd("delDeployExample","删除部署实例失败!");
					}
				});
		      }
			}
	});
}

//验证名称是否可用
function validateName(selfObj,name,type){
	$.getJSON('busitree_validateName.do?name=' + encodeURI(encodeURI(name))+"&id=0",{'time':new Date().toString()}, function(data){
		if(data.nameEnabled){
			if(type == 'busiCenter'){
				$(selfObj.content.document.getElementById("showValidateMess")).hide();
				saveBusiSysCenter(name);
			}
		}else{
			if(type == 'busiCenter'){
				$(selfObj.content.document.getElementById("showValidateMess")).children().empty().append($('<font color="red">名称重复，请重新输入!</font>'));
				$(selfObj.content.document.getElementById("showValidateMess")).show();
				selfObj.button({name: '保存',disabled: false});
				return false;
			}
		}
	});
}

function saveBusiSysCenter(name){
		closeDialog("datacenter");
		$.getJSON('busisys_saveBusiSysCenter.do?name=' + encodeURI(encodeURI(name)),{'time':new Date().toString()}, function(data){
			if(data.result ==1){
				alert("添加业务中心成功");
				receiveValue(name);
			}else{
				alert("添加业务中心失败");
			}
		});
	}
	
//部署
function deploy(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确定要部署吗?")==true){
		bar("deploy","部署中，请稍等...");
		$.getJSON("dep_deployRequest.do?nodeId=" + treeNode.id +"&paran=1"+"&operType=tree",{'time':new Date().toString()},function(data){
			if(data!=0){
				//成功后树的操作
				//....
				barEnd("deploy","部署任务已加入！");
			}else{
				barEnd("deploy","部署失败！");
			}
		}); 
	}
}
 
//跳转到升级页面
function upgradeDeployExample(){
	hideRMenu();
	var treeNode = rightSNode;
	$.dialog({
			  	id:'goupgradeDeployExamplePage',
				title:'升级部署实例',
				max:false,
				min:false,
				height:'500px',
				width:'840px',
				lock : true,
				content:'url:dep_upgradeDeployExample.do?operType=tree'+"&nodeId="+treeNode.id+"&dialogName="+"goupgradeDeployExamplePage"
	});
}
//升级
function startUpgrade(dialogName,nodeId,ids,APPID,onlinePath,versionDesc,isrestart,isbackup){
	closeDialog(dialogName);
	bar("startUpgrade","升级中，请稍等...");
	$.getJSON("dep_startUpgrade.do?ids=" + ids +"&operType=tree" + "&APPID=" +APPID + "&onlinePath=" + onlinePath
				+ "&versionDesc=" + versionDesc + "&isrestart=" + isrestart + "&isbackup=" + isbackup
				,{'time':new Date().toString()},function(data){
		if(data.result==1){
			//成功后树的操作
			//....
			barEnd("startUpgrade","升级成功！");
		}else{
			barEnd("startUpgrade","升级失败！");
		}
	}); 
}

//跳转到批量部署页面
function batchDeployExample(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'goBatchDeployExample',
			title:'批量部署',
			max:false,
			min:false,
			height:'380px',
			width:'700px',
			lock : true,
			content:'url:dep_batchDeployExample.do?nodeId=' + treeNode.id
		});
	}
}

//批量部署
function batchDeployExampleRequest(ids,nodeId){
	bar("batchDeployExampleRequest","部署中，请稍等...");
	for(var i=0;i<ids.length;i++){
		$.getJSON("dep_deployRequest.do?ID="+ids[i]+"&paran=1"+"&operType=list",{'time':new Date().toString()},function(data){
			idstr = "batchDeployExampleRequest"+i;
			if(data.result == 1){
				if(i != ids.length-1){
					messagebar(idstr,"主机"+data.name+"已添加到部署任务！");
					//更新树节点
				}else{
					barEnd("batchDeployExampleRequest","主机"+data.name+"已添加到部署任务！");
					//更新树节点
				}
			}else{
				if(i != ids.length-1){
					messagebar(idstr,"主机"+data.name+"部署失败！");
					//更新树节点
				}else{
					barEnd("batchDeployExampleRequest","主机"+data.name+"部署失败！");
					//更新树节点
				}
			}
		});
	}
}
//生成文件实例树
function createFileObjTree(){
	bar("createFileObjTree","正在生成文件树，请稍等...");

	$.ajax({
		type:"GET",
		url:"baseappfile_CreateFileTreeByAppid.do?appid="+rightSNode.entityId,
		dataType:"json",
		success:function(data){
					if(data==1){
						barEnd("createFileObjTree","文件树生成成功");
					}else{
						barEnd("createFileObjTree","文件树生成失败");
					}
					
		        }
	
	});
}
//批量操作进程
function batchDealProcessData() {
	var x = document.body.scrollWidth;
	var y = document.body.scrollHeight - 45;
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'batchDealProcessData',
			title:'批量操作进程及脚本',
			max:false,
			min:false,
			height:y,
			width:x,
			lock : true,
			content:'url:appexcel_initAppProcessExcel.do?appId='+treeNode.entityId
		});
	}
}

//刷新状态
function refreshState(){
	var uuid = new UUID().createUUID();
	var url = '';
	var uuid = setInterval(function(){
		$.getJSON(url,{'time':new Date().toString()},function(data){
			if(data.state==1){
				//成功后树的操作
				//....
				clearInterval(uuid);
			}
		}); 
	},5000);
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
		    lock : true,
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
		    lock : true,
		    content:contents
		});
	$.dialog.list[idstr].time(2);
}
function barChangeContent(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
}

function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
}
function closeBar(idstr){
	$.dialog.list[idstr].close();
}

function closeDialog(idstr){
	$.dialog.list[idstr].close();
}


