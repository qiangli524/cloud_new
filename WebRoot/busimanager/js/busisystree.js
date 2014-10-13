//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}


//增加根节点-业务中心
function addBusiSysCenter(){
	$.dialog({
		id:'datacenter',
		title:'添加业务中心',
		max:false,
		min:false,
		width:'500px',
		height:'220px',
		content:'url:bmanager_addBusiSysCenter.do'
	});
	hideRMenu();
}
function updateSystemCenter(){
	var treeNode = rightSNode;
	$.dialog({
		id:'datacenter',
		title:'修改业务中心',
		max:false,
		min:false,
		lock:true,
		width:'500px',
		height:'220px',
		content:'url:bmanager_updateBusiSysCenter.do?theForm.id='+treeNode.id
	});
	hideRMenu();
}
function saveBusiSystemCenter(form){
  	 $.ajax({
            type: "POST",
            url: "bmanager_saveBusiSysCenter.do",
            data: form,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 var nodes = zTree.getNodeByParam("id",0);
	  				 zTree.reAsyncChildNodes(null, "refresh",true);
	              }
              }
    });
}
function deleteBusiSysCenter(){
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "bmanager_delBusiSysCenter.do?id="+treeNode.id+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				if(data.result==1){
					   alert("该中心下还有数据不能删除");
				}else{
					   var nodes = zTree.getNodeByParam("id",0);
		  			   zTree.reAsyncChildNodes(nodes, "refresh",true);
				 }
			}
	   });
	}
	
}

/***************业务系统*******************/
function addBizsys(){
	var treeNode = rightSNode;
	$.dialog({
		id:'bizsys',
		title:'添加业务系统',
		lock:true,
		max:false,
		min:false,
		height:'350px',
		width:'500px',
		content:'url:bmanager_addBizSys.do?parent_id='+treeNode.id+"&date="+new Date()
	});
	hideRMenu();
}
function updateBizsys(){
	var treeNode = rightSNode;
	$.dialog({
		id:'bizsys',
		title:'更新业务系统',
		lock:true,
		max:false,
		min:false,
		width:'500px',
		height:'260px',
		content:'url:bmanager_updateBizSys.do?id='+treeNode.id+"&date="+new Date()
	});
	hideRMenu();
}
function saveBizsys(form){
 	 $.ajax({
            type: "POST",
            url: "bmanager_saveBizSys.do",
            data: form,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 var nodes = zTree.getNodeByParam("id",data.parent_id);
	  				 zTree.reAsyncChildNodes(nodes, "refresh",true);
	              }
              }
    });
}
function delBizsys(){
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "bmanager_delBizSys.do?id="+treeNode.id+"&parent_id="+treeNode.pId+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				hideRMenu();
				if(data.result==1){
					   alert("该中心下还有数据不能删除");
				}else{
					   var nodes = zTree.getNodeByParam("id",data.parent_id);
	 				   zTree.reAsyncChildNodes(nodes, "refresh",true);
				 }
			}
	   });
	}
}
/******************子系统**************************/
function addSysApp(){
	var treeNode = rightSNode;
	$.dialog({
		id:'addbizsys',
		title:'添加子系统',
		lock:true,
		max:false,
		min:false,
		width:'600px',
		height:'300px',
		content:'url:bmanager_addSysApp.do?parent_id='+treeNode.id+"&date="+new Date()
	});
	hideRMenu();
}
function modifySysApp(){
	var treeNode = rightSNode;
	$.dialog({
		id:'updatebizsys',
		title:'更新子系统',
		max:false,
		min:false,
		width:'600px',
		height:'300px',
		content:'url:bmanager_modifySysApp.do?id='+treeNode.id+"&date="+new Date()
	});
	hideRMenu();
}
function saveSysApp(form){
 	 $.ajax({
            type: "POST",
            url: "bmanager_saveSysApp.do",
            data: form,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 var nodes = zTree.getNodeByParam("id",data.parent_id);
	  				 zTree.reAsyncChildNodes(nodes, "refresh",true);
	              }
              }
    });
}
function delSysApp(){
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "bmanager_delSysApp.do?id="+treeNode.id+"&parent_id="+treeNode.pId+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				hideRMenu();
				if(data.result==1){
					   alert("该子系统下还有数据不能删除");
				}else{
					  var nodes = zTree.getNodeByParam("id",data.parent_id);
	  				  zTree.reAsyncChildNodes(nodes, "refresh",true);
				 }
			}
	   });
	}
}
/*****************虚拟机*****************************/
function addDeployExample(){
	var treeNode = rightSNode;
	hideRMenu();
	$.dialog({
		id:'addVm',
		title:'添加虚拟机',
		max:false,
		lock:true,
		min:false,
		width:'900px',
		height:'500px',
		content:'url:bmanager_addVm.do?parent_id='+treeNode.id+"&date="+new Date()
	});
	
}
function saveVm(param,parent_id){
	var url = 'bmanager_saveVm.do?entity_id='+param+'&date='+new Date()+'&parent_id='+parent_id;
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
		 		var nodes = zTree.getNodeByParam("id",data.parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
		 	   }else{
		 	   	var nodes = zTree.getNodeByParam("id",data.parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
		 		   alert("请选择虚拟机");
		 	   }
	   }
	 });
}
function delDeployExample(){
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "bmanager_delVm.do?id="+treeNode.id+"&parent_id="+treeNode.pId+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				hideRMenu();
				if(data.result==2){
					var nodes = zTree.getNodeByParam("id",data.parent_id);
					zTree.reAsyncChildNodes(nodes, "refresh",true);
				}else{
				    alert("删除失败");
				 }
			}
	   });
	}
}
/*****************承载业务*****************************/
function addbusiness(){
	var treeNode = rightSNode;
	hideRMenu();
	$.dialog({
		id:'addbusiness',
		title:'添加承载业务',
		max:false,
		lock:true,
		min:false,
		width:'450px',
		height:'150px',
		content:'url:bmanager_addBusiness.do?parent_id='+treeNode.id+"&date="+new Date()
	});
}
function modBusiness(){
	var treeNode = rightSNode;
	$.dialog({
		id:'bizsys',
		title:'更新承载业务',
		max:false,
		min:false,
		width:'300px',
		height:'120px',
		content:'url:bmanager_modBusiness.do?id='+treeNode.id+"&date="+new Date()
	});
	hideRMenu();
}
function saveBuiness(form){
	 $.ajax({
            type: "POST",
            url: "bmanager_saveBusiness.do",
           	data: form,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 var nodes = zTree.getNodeByParam("id",data.parent_id);
	  				 zTree.reAsyncChildNodes(nodes, "refresh",true);
	              }
              }
    });
}
/*function saveBuiness(param,parent_id){
	var url = 'bmanager_saveBusiness.do?entity_id='+param+'&date='+new Date()+'&parent_id='+parent_id;
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
		 		var nodes = zTree.getNodeByParam("id",data.parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
		 	   }else{
		 	   	var nodes = zTree.getNodeByParam("id",data.parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
		 		   alert("请选择虚拟机");
		 	   }
	       }
	 });
}*/
function delBusiness(){
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "bmanager_delBusiness.do?id="+treeNode.id+"&parent_id="+treeNode.pId+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				hideRMenu();
				if(data.result==2){
					var nodes = zTree.getNodeByParam("id",data.parent_id);
					zTree.reAsyncChildNodes(nodes, "refresh",true);
				}else if(data.result==1){
				    alert("请先删除子节点！");
				 }else{
					 alert("删除失败");
				 }
			}
	   });
	}
}
function exportExcel(){
	var treeNode = rightSNode;
	var url = "bmanager_exportExcel.do?id="+treeNode.id+"&type="+treeNode.type;
	var form1= $('#excelForm');
	hideRMenu();
	form1.attr("action",url);
	form1.submit();
}

/*****************物理机 begin*****************************/
function addHostToBusi(){//添加物理机
	var treeNode = rightSNode;
	hideRMenu();
	$.dialog({
		id:'addHost',
		title:'添加物理机',
		max:false,
		lock:true,
		min:false,
		width:'900px',
		height:'500px',
		content:'url:bmanager_addHostToBusi.do?parent_id='+treeNode.id+"&date="+new Date()
	});
}

function saveHostToBusi(eq_id,parent_id){//添加物理机
	var url = 'bmanager_saveHostToBusi.do?eq_id='+eq_id+'&date='+new Date()+'&parent_id='+parent_id;
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
		 		var nodes = zTree.getNodeByParam("id",data.parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
		 	   }else{
		 	   	var nodes = zTree.getNodeByParam("id",data.parent_id);
				zTree.reAsyncChildNodes(nodes, "refresh",true);
		 		   alert("请选择物理机");
		 	   }
	   }
	 });
}

function deleteHostToBusi(){//删除物理机
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "bmanager_deleteHostToBusi.do?id="+treeNode.id+"&parent_id="+treeNode.pId+"&date="+new Date(),
			dataType: "json",
			success : function(data){
				hideRMenu();
				if(data.result==2){
					var nodes = zTree.getNodeByParam("id",data.parent_id);
					zTree.reAsyncChildNodes(nodes, "refresh",true);
				}else if(data.result==1){
				    alert("请先删除子节点！");
				 }else{
					 alert("删除失败");
				 }
			}
	   });
	}
}
/*****************物理机 end*****************************/
