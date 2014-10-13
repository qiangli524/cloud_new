//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

// 设置DIV显示隐藏属性
function setDivVisProp(divName) {
	pageOnLoad();
	$("#" + divName + "").show();
}
//搜索
function searchNode(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'searchNode',
			title:'搜索',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			content:'url:united_addDataCenter.do?parent_id='+treeNode.id+"&connect_id="+treeNode.connect_id
		});
	}
}
//更新数据
function updateData(){
	hideRMenu();
	var treeNode = rightSNode;
	var url = "unitedData_compareVmwareData.do";
	if(confirm("确认更新数据吗?")){
	bar(treeNode.id,"正在更新数据，请稍候...");
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.id,msg);
	          	zTree.reAsyncChildNodes(treeNode, "refresh",true);
             }
	});
  }
}
//添加地域，应安徽移动的要求，需要在安徽移动节点下再添加一层地域节点
function addArea(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addArea',
			title:'添加地域',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			content:'url:united_addArea.do?parent_id='+treeNode.id+"&connect_id="+treeNode.connect_id
		});
	}
}
//保存地域信息
function saveArea(name,parent_id){
	hideRMenu();
	var treeNode = rightSNode;
	var url = "united_saveArea.do?name="+encodeURI(encodeURI(name))+"&parent_id="+parent_id;
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
//----------------------------------------数据中心相关操作start----------------------------------------
//添加数据中心页面
function addDataCenter() {
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addDataCenter',
			title:'添加数据中心',
			max:false,
			min:false,
			height:'250px',
			width:'434px',
			lock:true,
			content:'url:united_addDataCenter.do?parent_id='+treeNode.id+"&connect_id="+treeNode.connect_id
		});
	}
}
//保存数据中心
function saveDataCenter(name,parent_id,vtype,connect_id){
	bar("dataCenter","正在添加数据中心，请稍候...");
	var url = "united_saveDataCenter.do?name="+encodeURI(encodeURI(name))+"&parent_id="+parent_id+"&vtype="+vtype+"&connect_id="+connect_id;
	$.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("dataCenter",data);
		var nodes = zTree.getNodeByParam("id",parent_id);
		zTree.reAsyncChildNodes(nodes, "refresh",true);
	});
}
//删除数据中心
function delDataCenter(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
	if(confirm("确认移除数据中心吗?")){
	bar("dataCenter","正在移除数据中心，请稍候...");
	var url = "united_delDataCenter.do?id="+treeNode.id+"&connect_id="+treeNode.connect_id+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid;
	$.getJSON(url,{"time":new Date().toString()},function(data){
		barEnd("dataCenter",data);
		zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}

//---------------------------------------数据中心相关操作end----------------------------------------
//---------------------------------------集群相关操作start-----------------------------------------
//添加集群页面
function addCluster() {
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addCluster',
			title:'添加集群',
			max:false,
			min:false,
			height:'200px',
			width:'384px',
			lock:true,
			content:'url:united_addCluster.do?parent_id='+treeNode.id+"&connect_id="+treeNode.connect_id+"&parent_uuid="+treeNode.uuid+"&vtype="+treeNode.vtype
		});
	}
}
//保存集群
function saveCluster(name,parent_id,parent_uuid,connect_id,vtype,str) {
	var treeNode = rightSNode;
	bar(parent_id,"正在添加集群，请稍候...");
	var url = "united_saveCluster.do?name="+encodeURI(encodeURI(name))+"&parent_id="+parent_id
	+"&parent_uuid="+parent_uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&"+str;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(parent_id,msg);
                var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
             }
	});
}
//删除集群
function deleteCluster() {
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
	if(confirm("确认移除集群吗?")){
	bar(treeNode.getParentNode().id,"正在移除集群，请稍候...");
	var url = "united_delCluster.do?parent_uuid="+treeNode.getParentNode().uuid+"&connect_id="+treeNode.connect_id
	+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid+"&id="+treeNode.id;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.getParentNode().id,msg);
				zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
	          	}
	 		});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//编辑设置vmware集群
function editClusterPage(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'editCluster',
			title:'编辑设置集群',
			max:false,
			min:false,
			height:'450px',
			width:'801px',
			lock:true,
			content:'url:united_editClusterPage.do?&connect_id='+treeNode.connect_id+"&uuid="+treeNode.uuid+"&vtype="+treeNode.vtype+"&id="+treeNode.id
		});
	}
}
//执行编辑设置集群
function editCluster(uuid,connect_id,vtype,id,str){
	var url = "united_editCluster.do?uuid="+uuid+"&connect_id="+connect_id
	+"&vtype="+vtype+"&id="+id+"&"+str;
	bar(uuid,"正在编辑集群，请稍候...");
	 $.ajax({
		 type:"post",
         url:url,
         data:"text",
         async: true,
         cache: false,
         success: function(msg){
        	 barEnd(uuid,msg.result);
        }
	 });
}
//xen同步集群
function syncCluster(){
	hideRMenu();
	var treeNode = rightSNode;
	var url = "united_syncCluster.do?parent_id="+treeNode.getParentNode().id+"&connect_id="+treeNode.connect_id
	+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid+"&id="+treeNode.id;
	bar(treeNode.uuid,"正在同步集群，请稍候...");
	 $.ajax({
		 type:"post",
         url:url,
         data:"text",
         async: true,
         cache: false,
         success: function(msg){
        	 barEnd(treeNode.uuid,msg);
        	 zTree.reAsyncChildNodes(treeNode, "refresh",true);
        }
	 });
}

/**
 * xen添加集群(资源池)
 * xugang
 * 2023-11-26
 */
function addXenCluster(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addXenCluster',
			title:'添加集群',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			lock:true,
			content:'url:united_addXenCluster.do?parent_id='+treeNode.id+"&connect_id="+treeNode.connect_id+"&parent_uuid="+treeNode.uuid+"&vtype="+treeNode.vtype
		});
	}
}
//---------------------------------------------集群相关操作end-------------------------------------
//---------------------------------------------主机相关操作start-----------------------------------
//添加主机页面
function addHost(type) {
	hideRMenu();
	var treeNode = rightSNode;
	var vtype = treeNode.vtype;
	if (vtype==1 || vtype==2) {
		$.dialog({
			id:'addHost',
			title:'添加主机',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			lock:true,
			content:'url:united_addHost.do?parent_id='+treeNode.id+'&connect_id='+treeNode.connect_id+'&parent_uuid='+ treeNode.uuid+"&vtype="+treeNode.vtype
		});
	}else if(vtype==7){
		$.dialog({
			id:'addUnAllocatedHost',
			title:'添加主机',
			max:false,
			min:false,
			height:'400px',
			width:'780px',
			lock:true,
			content:'url:unitedDevice_addHostDevice.do?obj.vtype='+treeNode.vtype+"&obj.parent_id="+treeNode.id+"&obj.type="+type
		});
	}else if(vtype==8){
		$.dialog({
			id:'addUnvirtual',
			title:'添加主机',
			max:false,
			min:false,
			height:'400px',
			width:'850px',
			lock:true,
			content:'url:united_unAllocatedHostSC.do?vtype='+treeNode.vtype+'&connect_id='+treeNode.connect_id+'&parent_id='+treeNode.id
		});
	}
}
//保存主机
function saveHost(name,parent_id,parent_uuid,connect_id,vtype,str){
	bar(parent_id,"正在添加主机，请稍后...");
	var url = "united_saveHost.do?name="+name+"&parent_id="+parent_id
	+"&parent_uuid="+parent_uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&"+str;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(parent_id,msg);
                var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
             }
	});
}
//保存主机
function saveHostDevice(url,parent_id){
	var treeNode = rightSNode;
	bar(parent_id,"正在操作，请稍后...");
	 $.ajax({
			  type:"GET",
             url:url,
         	  data:"text",
         	  async: true,
          	  cache: false,
	          success: function(msg){
	          	barEnd(parent_id,"操作成功");
               var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
          }
	});
}
//修改主机
function updHost(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'updHost',
			title:'修改主机',
			max:false,
			min:false,
			height:'400px',
			width:'780px',
			lock:true,
			content:'url:unitedDevice_updHost.do?hostObj.eq_id='+treeNode.uuid+"&obj.parent_id="+treeNode.id+"&obj.pratentId="+treeNode.getParentNode().id
		});
	}
}
//移除主机
function delHost() {
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	var vtype = treeNode.vtype;
	var url ='';
	if(isParent==false){
	if(confirm("确认移除主机吗?")){
		bar(treeNode.getParentNode().id,"正在移除主机，请稍候...");
	if(vtype==7){//未分配主机的移除
		url = "unitedDevice_delHost.do?obj.uuid="+treeNode.uuid+"&obj.id="+treeNode.id;
	}else{//vmware、xen主机的移除等
		url = "united_deleteHost.do?vtype="+vtype+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&id="+treeNode.id;
	}
	$.ajax({
		  type:"GET",
		  url:url,
		  data:"text",
		  async: true,
		  cache: false,
		  success: function(msg){
			  barEnd(treeNode.getParentNode().id,msg);
			  zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
        		}
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//添加主机存储页面
function addStore() {
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addStore',
			title:'添加存储',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			lock:true,
			content:'url:united_addDataStore.do?id='+treeNode.id+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&vtype="+treeNode.vtype
		});
	}
}
//添加主机存储
function saveStore(id,uuid,connect_id,vtype,str){
	var url = "united_saveDataStore.do?id="+id+"&uuid="+uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&"+str;
	bar(uuid,"正在添加主机存储，请稍候...");
	 $.ajax({
		 type:"GET",
         url:url,
         data:"text",
         async: true,
         cache: false,
         success: function(msg){
		 	if (msg =="success") {
				barEnd(uuid,"添加存储成功");
			} else {
				barEnd(uuid,"添加存储失败，失败原因： " + msg);
			}
        }
	 });
}
//主机进入维护模式
function enterMaintenanceMode(){
	hideRMenu();
	var treeNode = rightSNode;
	if(treeNode){
		var parentNode = treeNode.getParentNode();
		if(confirm("处于维护模式下的主机不会执行任何与虚拟机相关的功能，包括虚拟机置备操作。若要进入维护模式，所有虚拟机必须关机或移至其他主机。可能需要人工干预。\u000d是否要将选定的主机置于维护模式?")==true){
			var url = "united_enterMaintenanceMode.do?vtype=" + treeNode.vtype + "&hostUnitedVO.hostCode=" + treeNode.uuid + "&hostUnitedVO.connectCode=" + treeNode.connect_id;
			$.getJSON(url,{'time':new Date().toString()},function(result){
				if(result=='success'){
					alert("进入维护模式成功!");
					zTree.reAsyncChildNodes(parentNode, "refresh",true);
				}else{
					alert("进入维护模式失败,可能的原因" + result);
				}
			});
		}
	}
}

//主机退出维护模式
function exitMaintenanceMode(){
	hideRMenu();
	var treeNode = rightSNode;
	if(treeNode){
		var parentNode = treeNode.getParentNode();
		var url = "united_exitMaintenanceMode.do?vtype=" + treeNode.vtype + "&hostUnitedVO.hostCode=" + treeNode.uuid + "&hostUnitedVO.connectCode=" + treeNode.connect_id;
		$.getJSON(url,{'time':new Date().toString()},function(result){
			if(result=='success'){
				alert("退出维护模式成功!");
				zTree.reAsyncChildNodes(parentNode, "refresh",true);
			}else{
				alert("退出维护模式失败,可能的原因" + result);
			}
		});
	}
}
//导入OVF模板页面
function importOvf(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'importOvf',
			title:'导入OVF模板',
			max:false,
			min:false,
			height:'120px',
			width:'630px',
			lock:true,
			content:'url:united_importOvfPage.do?parent_id='+treeNode.id+"&vtype="+treeNode.vtype+"&type="+treeNode.type
			+"&id="+treeNode.id+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id
		});
	}
}
//保存OVF模板
function saveOvf(parent_id,params){
	bar(parent_id,"正在导入OVF模板，请稍候...");
	var url = "united_importOvf.do?parent_id="+parent_id+"&"+params;
	 $.ajax({
			  type:"post",
	          url:url,
	          async: true,
	          cache: false,
	          success: function(msg){
	          	barEnd(parent_id,msg);
	        	var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);
	         }
	});
}
//连接主机
function connectHost(){
	hideRMenu();
	alert("待开发");
}

//断开主机
function disConnectHost(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		var parentNode = treeNode.getParentNode();
		var url = "united_disConnectHost.do?vtype="+treeNode.vtype + "&hostUnitedVO.hostCode=" + treeNode.uuid + "&hostUnitedVO.connectCode="+treeNode.connect_id;
		if (confirm("断开主机会有风险，你确定需要这么做吗？")) {
			bar(parentNode.id,"正在断开主机，请稍候...");
			$.ajax({
				type:"get",
				dataType:"text",
				url:url,
				success:function(msg){
					if (msg == "success") {
						barEnd(parentNode.id,"断开主机成功");
						zTree.reAsyncChildNodes(parentNode,"refresh",true);
					} else {
						barEnd(parentNode.id,"断开主机失败，失败原因： " + msg);
					}
				}
			});
		}
	}
}

//重新引导主机（重启）
function rebootHost(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		var parentNode = treeNode.getParentNode();
		var url = "united_changeHostPowerState.do?vtype="+treeNode.vtype + "&hostUnitedVO.hostCode="+treeNode.uuid
			+ "&hostUnitedVO.connectCode=" + treeNode.connect_id + "&hostUnitedVO.powerState=restart";
		if (confirm("重新启动主机会存在风险，你确定要这么做吗？")) {
			bar(parentNode.id,"正在重新引导主机，请稍候...");
			$.ajax({
				type:'get',
				dataType:'text',
				url:url,
				success:function(msg){
					if (msg == "success") {
						barEnd(parentNode.id, "重新引导主机成功");
						zTree.reAsyncChildNodes(parentNode,"refresh",true);
					} else {
						barEnd(parentNode.id,"重新引导主机失败，失败原因： " + msg);
					}
				}
			});
		}
	}
}

//关闭主机电源
function shutdownHost(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		var parentNode = treeNode.getParentNode();
		var url = "united_changeHostPowerState.do?vtype="+treeNode.vtype + "&hostUnitedVO.hostCode="+treeNode.uuid
			+ "&hostUnitedVO.connectCode=" + treeNode.connect_id + "&hostUnitedVO.powerState=powerOff";
		if (confirm("关闭主机电源会有风险，你确定要这么做吗？")) {
			bar(parentNode.id, "正在关闭主机电源，请稍候...");
			$.ajax({
				type:'get',
				dataType:'text',
				url:url,
				success:function(msg){
					if (msg == "success") {
						barEnd(parentNode.id, "关闭主机电源成功");
						zTree.reAsyncChildNodes(parentNode, "refresh",true);
					} else {
						barEnd(parentNode.id, "关闭主机电源失败，失败原因： " + msg);
					}
				}
			});
		}
	}
}

//远程开机
function bootHost(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		var parentNode = treeNode.getParentNode();
		var url = "united_changeHostPowerState.do?vtype="+treeNode.vtype + "&hostUnitedVO.hostCode="+treeNode.uuid
			+ "&hostUnitedVO.connectCode=" + treeNode.connect_id + "&hostUnitedVO.powerState=powerOn";
		if (confirm("你确定要打开主机吗？")) {
			bar(parentNode.id, "正在打开主机电源，请稍候...");
			$.ajax({
				type:'get',
				dataType:'text',
				url:url,
				success:function(msg){
					if (msg == "success") {
						barEnd(parentNode.id, "打开主机电源成功");
						zTree.reAsyncChildNodes(parentNode, "refresh",true);
					} else {
						barEnd(parentNode.id, "打开主机电源失败，失败原因： " + msg);
					}
				}
			});
		}
	}
}

//主机进入待机模式
function enterAwaitMode(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		var parentNode = treeNode.getParentNode();
		var url = "united_enterAwaitModeHost.do?vtype="+treeNode.vtype + "&hostUnitedVO.hostCode="+treeNode.uuid
			+ "&hostUnitedVO.connectCode=" + treeNode.connect_id;
		if (confirm("进入待机模式会带来风险，你确定要这么做吗？")) {
			bar(parentNode.id, "主机正在进入待机模式，请稍候...");
			$.ajax({
				type:'get',
				dataType:'text',
				url:url,
				success:function(msg){
					if (msg == "success") {
						barEnd(parentNode.id, "主机进入待机模式成功");
						zTree.reAsyncChildNodes(parentNode, "refresh",true);
					} else {
						barEnd(parentNode.id,"主机进入待机模式失败，原因： " + msg);
					}
				}
			});
		}
	}
}

//主机从待机模式恢复
function exitAwaitMode(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		var parentNode = treeNode.getParentNode();
		var url = "united_exitAwaitModeHost.do?vtype="+treeNode.vtype + "&hostUnitedVO.hostCode="+treeNode.uuid
			+ "&hostUnitedVO.connectCode=" + treeNode.connect_id;
		if (confirm("你确定要从待机模式恢复吗？")) {
			bar(parentNode.id, "主机正在从待机模式恢复，请稍候...");
			$.ajax({
				type:'get',
				dataType:'text',
				url:url,
				success:function(msg){
					if (msg == "success") {
						barEnd(parentNode.id, "主机从待机模式恢复成功");
						zTree.reAsyncChildNodes(parentNode, "refresh",true);
					} else {
						barEnd(parentNode.id, "主机从待机模式恢复失败，失败原因： " + msg);
					}
				}
			});
		}
	}
}

//-----------------------------------------主机相关操作end---------------------------------------
//-----------------------------------------虚拟机相关操作----------------------------------------
//修改虚拟机电源状态
function putVMState(state){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确认修改虚拟机电源状态吗?")){
	bar(treeNode.getParentNode().id,"正在修改虚拟机电源状态，请稍候...");
	var url = "united_putVMPowerState.do?connect_id="+treeNode.connect_id
	+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid+"&oper="+state+"&parent_uuid="+treeNode.getParentNode().uuid;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.getParentNode().id,msg);
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
				//zTree.expandNode(treeNode.getParentNode(),false,true,false,false);//不展开父类节点
             }
	});
   }
}
//将虚拟机从磁盘移除
function destroyVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确认从磁盘删除虚拟机吗?")){
	bar('destroyVM'+treeNode.getParentNode().id,"正在移除虚拟机，请稍候...");
	var url = "united_destoryVM.do?connect_id="+treeNode.connect_id
	+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid+"&id="+treeNode.id;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd('destroyVM'+treeNode.getParentNode().id,msg);
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
             }
	});
 }
}
//创建虚拟机(通过模板创建)
function addVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addVM',
			title:'新建虚拟机',
			max:false,
			min:false,
			height:'420px',
			width:'630px',
			lock:true,
			content:'url:united_addVM.do?parent_id='+treeNode.id+"&vtype="+treeNode.vtype+"&type="+treeNode.type
			+"&parent_uuid="+treeNode.uuid+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id
		});
	}
}
//保存通过模板克隆的虚拟机
function saveVM(parent_id,parent_uuid,vtype,connect_id,str){
	bar(parent_id,"正在创建虚拟机，请稍候...");
	var url = "united_createVMByTem.do?parent_id="+parent_id
	+"&parent_uuid="+parent_uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&"+str;
	 $.ajax({
			  type:"post",
              url:url,
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(parent_id,msg);
	        	var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(nodes.getParentNode(), "refresh",true);
             }
	});
}

//通过虚拟机克隆虚拟机
function cloneVMByVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'cloneVM',
			title:'克隆虚拟机',
			max:false,
			min:false,
			height:'420px',
			width:'630px',
			lock:true,
			content:'url:united_addVMByVMPage.do?parent_id='+treeNode.getParentNode().id+"&vtype="+treeNode.vtype+"&type="+treeNode.type
			+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+'&parent_uuid='+treeNode.getParentNode().uuid
		});
	}
}

//保存通过虚拟机克隆的虚拟机
function cloneVM(parent_uuid,vtype,connect_id,str){
	bar(parent_uuid,"正在创建虚拟机，请稍候...");
	var url = "united_createVMByVM.do?parent_uuid="+parent_uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&"+str;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(parent_uuid,msg);
                var nodes = zTree.getNodeByParam("id",parent_uuid);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
             }
	});
}
//将虚拟机转化为模板（虚拟机节点删除，相应模板表里增加数据）
function markAsTemplate(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确认将虚拟机转化为模板吗？")){
	if (treeNode) {
		bar(treeNode.uuid,"正在将虚拟机转化为模板");
		var url = 'united_markAsTemplate.do?uuid='+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&vtype="+treeNode.vtype+"&id="+treeNode.id;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			barEnd(treeNode.uuid,data);
		});
	}
  }
}
//进入迁移虚拟机页面
function migrateVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'migrateVM',
			title:'迁移虚拟机',
			max:false,
			min:false,
			height:'200px',
			width:'500px',
			lock:true,
			content:'url:united_gomigrateVMPage.do?connect_id='+treeNode.connect_id+"&type="+treeNode.type+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid
				+'&parent_id='+treeNode.getParentNode().id+'&parent_uuid='+treeNode.getParentNode().uuid+'&state='+treeNode.state
		});
	}
}

//进入重定位虚拟机页面
function relocateVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'relocateVM',
			title:'重定位虚拟机',
			max:false,
			min:false,
			height:'200px',
			width:'500px',
			lock:true,
			content:'url:united_gorelocateVMPage.do?parent_id='+treeNode.getParentNode().id+'&connect_id='+treeNode.connect_id+'&uuid='+treeNode.uuid
		});
	}
}

function exeMigrateVM(vtype,connect_id,uuid,parent_uuid,str){
	bar(uuid,"正在迁移虚拟机，请稍后");
	var treeNode = rightSNode;
	var url = "united_executeMigrateVM.do?vtype="+vtype+"&connect_id="+connect_id
		 +"&uuid="+uuid+"&parent_uuid="+parent_uuid+"&"+str;
	$.ajax({
		type:"get",
		url:url,
		async:true,
		cache:false,
		success:function(msg){
			barEnd(uuid,msg);
			zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(),"refresh",true);
		}
	});
}

//执行迁移虚拟机操作
function exeRelocateVM(vtype,hostCode,datastoreCode,connectCode,vmCode){
	bar(vmCode,"正在迁移虚拟机，请稍后");
	var treeNode = rightSNode;
	var url='united_executeMigrateVM.do?vtype='+vtype+'&connect_id='+connectCode+'&uuid='+vmCode+'&parent_uuid='+hostCode
		+'&dataStoreCode='+datastoreCode;
	$.ajax({
		type:'post',
		url:url,
		async:true,
		cache:false,
		success:function(msg){
			barEnd(vmCode,msg);
			zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(),"refresh",true);
		}
	});
}

//编辑设置虚拟机
function editVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.getJSON("united_getAdjustState.do?uuid="+treeNode.uuid+"&state="+treeNode.state+'&vtype='+treeNode.vtype, {"time":new Date().toString()}, function (data) {
			if(data){
				$.dialog({
					id:'editVM',
					title:'编辑设置虚拟机',
					max:false,
					min:false,
					height:'300px',
					width:'550px',
					lock:true,
					content:'url:united_goEditVMPage.do?connect_id='+treeNode.connect_id+'&vtype='+treeNode.vtype+'&uuid='+treeNode.uuid
							+'&parent_uuid='+treeNode.getParentNode().uuid+'&parent_id='+treeNode.getParentNode().id+'&type='+treeNode.type
							+"&state="+treeNode.state
				});
			}else{
				alert("XEN虚拟机暂仅支持关机状态编辑设置！");
			}
    	});
	}
}

//修改虚拟机信息后保存
function saveVMInfo(parent_id,parent_uuid,vtype,connect_id,type,uuid,state,str,labels,capacitys){
	bar(parent_id,"正在保存虚拟机设置，请稍后...");
	var url = "united_saveVMInfo.do?parent_id="+parent_id+"&parent_uuid="+parent_uuid+"&connect_id="+connect_id+"&vtype="+vtype
				+"&type="+type+"&uuid="+uuid+"&state="+state+"&"+str+"&labels="+encodeURI(encodeURI(labels))+"&capacitys="+encodeURI(encodeURI(capacitys));
	$.ajax({
		type:"post",
		url:url,
		async:true,
		cache:false,
		success:function(msg){
			barEnd(parent_id,msg);
		}
	});
}
//执行快照
function executeSnapshot(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addSnapshot',
			title:'添加快照',
			max:false,
			min:false,
			height:'300px',
			width:'534px',
			lock:true,
			button: [
	    	              {
	    	              	  id:'OK',
	    	                  name: '保存',
	    	                  callback:goAddSnapshot,
	    	                  focus: true
	    	              },
	    	              {
	    	                  name: '取消'
	    	              }
	        	      ],
			content:'url:united_goCreateSnapshotVM.do?vtype='+treeNode.vtype+"&entity_id="+treeNode.uuid
					+"&host_id="+treeNode.getParentNode().uuid+"&pool_id="+treeNode.getParentNode().getParentNode().uuid
		});
	}
}
//跳转到添加快照页面
function goAddSnapshot(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addsnapshot',
			title:'创建快照',
			max:false,
			min:false,
			height:'400px',
			width:'634px',
			lock:true,
			content:'url:united_goCreateSnapshotVM.do?vtype='+treeNode.vtype+"&parent_uuid="+treeNode.getParentNode().uuid+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id
		});
	}
}
//添加快照
function addSnapshot(uuid,connect_id,vtype,str){
	var url ="united_addSnapshot.do?uuid="+uuid+"&connect_id="+connect_id+"&vtype="+vtype+"&"+str;
	bar(uuid,"正在创建快照，请稍候...");
	$.ajax({
		type: "get",
		dataType: "json" ,
		url:url,
		success: function(msg){
			barEnd(uuid,msg);
		},
		error: function(msg){
			barEnd(uuid,msg);
		}
	});
}

//展示快照列表
function snapshotManage(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addsnapshot',
			title:'快照管理',
			max:false,
			min:false,
			height:'400px',
			width:'634px',
			lock:true,
			content:'url:united_snapShotManage.do?vtype='+treeNode.vtype+"&parent_uuid="+treeNode.getParentNode().uuid+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id
		});
	}
}

//恢复或者删除快照
function operSnapshot(url,uuid,oper){
	var urls = url+"&oper="+oper+"&uuid="+uuid;
	//alert(urls);
	if(oper=="recover"){
		bar(uuid,"正在恢复快照");
	}else{
		bar(uuid,"正在删除快照");
	}
	$.ajax({
		type: "get",
		dataType: "json" ,
		timeout: 30000,
		url:urls,
		success: function(msg){
			barEnd(uuid,msg);
		},
		error: function(msg){
			barEnd(uuid,msg);
		}
	});
}
//虚拟机控制台
function console(){
	hideRMenu();
	var treeNode = rightSNode;
	var temp=treeNode.vtype;
	if("1"==temp){//vmware
		if (treeNode) {
			$.dialog({
				id:'console',
				title:'控制台',
				max:true,
				min:true,
				height:'400px',
				width:'634px',
				lock:true,
				content:'url:united_console.do?vtype='+treeNode.vtype+"&parent_uuid="+treeNode.getParentNode().uuid+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id
			});
		}
	}else if("2"==temp){//xen
		var state=treeNode.state;
		var url = "united_vmConsole.do?connect_id="+treeNode.connect_id+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid+"&id="+treeNode.id+'&state='+state;
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
				lock:true,
				content: 'url:'+url});
		}else{
			bar("控制台出错，无法弹出，请尝试其他方式！");
		}
	}else{
		alert("你好：暂时不支持此类型的控制台.");
	}
}
/** 创建裸机 */
function addnakedVM(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addnakedVM',
			title:'新建裸机',
			max:false,
			min:false,
			height:'400px',
			width:'700px',
			lock:true,
			content:'url:unitedOper_addnakedVM.do?parent_id='+treeNode.getParentNode().id+"&vtype="+treeNode.vtype+"&type="+treeNode.type
			+"&parent_uuid="+treeNode.id+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id
		});
	}
}

//升级或安装VMware Tool
function installOrUpdateTool(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确认安装/升级VMware Tools吗？")){
	bar(treeNode.id,"正在安装/升级VMware Tools，请稍候...");
	var url = "united_installOrUpdateTool.do?connect_id="+treeNode.connect_id
	+"&vtype="+treeNode.vtype+"&uuid="+treeNode.uuid;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.id,msg);
             }
	 	});
   }
}
//---------------------------------------网络相关操作start-----------------------------------------
//添加网络域
function addDomain(type){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addDomain',
			title:'添加网络域',
			max:false,
			min:false,
			height:'400px',
			width:'634px',
			lock:true,
			content:'url:unitedNetwork_addDomain.do?obj.vtype='+treeNode.vtype+"&obj.parent_id="+treeNode.id+"&obj.type="+type
		});
	}
}
//添加子网络域
function addSubDomain(type){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addSubDomain',
			title:'添加子网络域',
			max:false,
			min:false,
			height:'400px',
			width:'634px',
			lock:true,
			content:'url:unitedNetwork_addDomain.do?obj.vtype='+treeNode.vtype+"&obj.parent_id="+treeNode.id+"&obj.type="+type
		});
	}
}
//保存网络域
function saveDomain(url,parent_id){
	var treeNode = rightSNode;
	bar(parent_id,"正在操作，请稍候...");
	 $.ajax({
			  type:"GET",
              url:url,
          	  data:"text",
          	  async: true,
           	  cache: false,
	          success: function(msg){
	          	barEnd(parent_id,"操作成功");
                var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
           }
	});
}
//添加VLan
function addVlan(type){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addVlan',
			title:'添加Vlan',
			max:false,
			min:false,
			height:'500px',
			width:'734px',
			lock:true,
			content:'url:unitedNetwork_addVlan.do?obj.vtype='+treeNode.vtype+"&obj.parent_id="+treeNode.id+"&obj.type="+type
		});
	}
}
//保存Vlan
function saveVlan(url,parent_id){
	var treeNode = rightSNode;
	bar(parent_id,"正在操作，请稍候...");
	 $.ajax({
			  type:"GET",
              url:url,
              data:"json",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(parent_id,"操作成功");
                var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode().getParentNode(), "refresh",true);
           }
	});
}
//修改VLan
function editVlan(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'editVlan',
			title:'修改Vlan',
			max:false,
			min:false,
			height:'500px',
			width:'734px',
			lock:true,
			content:'url:unitedNetwork_editVlan.do?netObj.NET_ID='+treeNode.uuid+"&obj.parent_id="+treeNode.id+"&obj.pratentId="+treeNode.getParentNode().id+"&obj.domPratentId="+treeNode.getParentNode().getParentNode().id   
		});
	}
}
//移除Vlan
function delVlan() {
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
	if(confirm("确认移除Vlan吗?")){
	bar(treeNode.getParentNode().id,"正在移除Vlan，请稍候...");
	var url = "unitedNetwork_delVlan.do?obj.uuid="+treeNode.uuid+"&obj.id="+treeNode.id;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.getParentNode().id,"操作成功");
				zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
	          	}
	 		});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//移除数据中心网络域和子网络域操作
function delOperate() {
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
	if(confirm("确认移除吗?")){
	bar(treeNode.getParentNode().id,"正在移除，请稍候...");
	var url = "unitedNetwork_delOperate.do?obj.id="+treeNode.id;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.getParentNode().id,"操作成功");
				zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
	          	}
	 		});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
//---------------------------------------网络相关操作end-----------------------------------------
//---------------------------------------存储相关操作start---------------------------------------
//添加存储设备
function addStorageDevice(type){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'addStorageDevice',
			title:'添加存储设备',
			max:false,
			min:false,
			height:'400px',
			width:'634px',
			lock:true,
			content:'url:unitedDevice_addStorageDevice.do?obj.vtype='+treeNode.vtype+"&obj.parent_id="+treeNode.id+"&obj.type="+type
		});
	}
}
//修改存储设备
function editStorageDevice(){
	hideRMenu();
	var treeNode = rightSNode;
	if (treeNode) {
		$.dialog({
			id:'editStorageDevice',
			title:'修改存储设备',
			max:false,
			min:false,
			height:'400px',
			width:'634px',
			lock:true,
			content:'url:unitedDevice_editStorageDevice.do?storeObj.id='+treeNode.uuid+"&obj.parent_id="+treeNode.id+"&obj.pratentId="+treeNode.getParentNode().id
		});
	}
}
//保存存储设备
function saveStorageDevice(url,parent_id){
	var treeNode = rightSNode;
	bar(parent_id,"正在操作，请稍候...");
	 $.ajax({
			  type:"GET",
             url:url,
             data:"json",
             async: true,
             cache: false,
	          success: function(msg){
	          	barEnd(parent_id,"操作成功");
                var nodes = zTree.getNodeByParam("id",parent_id);
				zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
         }
	});
}
//移除存储设备
function delStorageDevice() {
	hideRMenu();
	var treeNode = rightSNode;
	var isParent=treeNode.isParent;
	if(isParent==false){
	if(confirm("确认移除存储设备吗?")){
	bar(treeNode.getParentNode().id,"正在移除存储设备，请稍候...");
	var url = "unitedDevice_delStorageDevice.do?obj.uuid="+treeNode.uuid+"&obj.id="+treeNode.id;
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
	          	barEnd(treeNode.getParentNode().id,"操作成功");
				zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
	          	}
	 		});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}
/**
 * 用于执行虚拟机的重命名操作，重置节点为可以编辑状态
 * @returns
 */
function renameVM(){
	hideRMenu();
	var treeNode = rightSNode;
	//重置虚拟机名称为可编辑状态
	zTree.editName(treeNode);
}
/**
 * 执行重命名的保存操作
 * @param
 * @returns ture 
 */
function saveRenameVM(currentTreeNode,newName,oldName,type){
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
			var url = "united_renameVM.do?vtype="+treeNode.vtype+"&uuid="+treeNode.uuid+"&connect_id="+treeNode.connect_id+"&name="+encodeURI(encodeURI(newName))+"&type="+treeNode.type;
			 $.ajax({
					  type:"POST",
		              url:url,
		              data:"text",
		              async: true,
		              cache: false,
			          success: function(result){
			          		if(result.flag == "1"){
			          			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
			          		}else{
			          			zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
			          		}
			          	}
			});
	}
}

//---------------------------------------存储相关操作end-----------------------------------------
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
	if (contents.indexOf('Failed') < 0) {//说明执行成功
		$.dialog.list[idstr].time(5);
	} else {
		alert();
	}
}
function closeBar(idstr){
	$.dialog.list[idstr].close();
}

function closeDialog(idstr){
	$.dialog.list[idstr].close();
}
//---------------------------------------非虚拟化 begin-----------------------------------------
//回收主机
function recycleHostNotVir(){
	hideRMenu();
	var treeNode = rightSNode;
	var isParent = treeNode.isParent;
	if(isParent==false){
		if(confirm("确认回收主机吗?")){			
			bar(treeNode.getParentNode().id,"正在回收，请稍候...");
			
			var url = "united_recycleHostNotVir.do?id="+treeNode.id+"&connect_id="+treeNode.connect_id;
			$.ajax({
				type:"GET",
				url:url,
				data:"text",
				async: true,
				cache: false,
				success: function(msg){
					barEnd(treeNode.getParentNode().id,"操作成功");
					zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
					//zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
				}
			});
		}
	}else{
		alert('当前节点存在子节点,不允许删除');
	}
}

function saveHostNotVir(eq_id,parent_id){//添加物理机	
	//alert("eq_id:" + eq_id + ",parent_id:" + parent_id);
	var url = 'united_saveAllocatedHostSC.do?id=id&connect_id='+eq_id+'&date='+new Date()+'&parent_id='+parent_id;
	$.ajax({
		type: "GET",
		url: url,
		dataType: "json",
		success : function(data){
			hideRMenu();
		 	 if(data.result==1){
		 	     alert("名称已经存在请重新添加.");
		 	     var nodes = zTree.getNodeByParam("id",data.parent_id);
				 zTree.reAsyncChildNodes(nodes, "refresh",true);
		 	  }else if(data.result == 2){
		 		 alert("分配成功");
		 		 var nodes = zTree.getNodeByParam("id",data.parent_id);
				 zTree.reAsyncChildNodes(nodes, "refresh",true);
		 	   }else if(data.result ==3){
		 		   alert("该资源可能被他人分配使用");
		 	   }else{
		 			var nodes = zTree.getNodeByParam("id",data.parent_id);
					zTree.reAsyncChildNodes(nodes, "refresh",true);
			 		alert("请选择物理机");
		 	   }
	   }
	 });
}
//---------------------------------------非虚拟化 end-----------------------------------------
