//$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

//更新数据
function updateData(){
	hideRMenu();
	var treeNode = rightSNode;
	var url = "update_compareIBMData.do";
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
	        	var nodes = zTree.getNodeByParam("id",0);
	          	zTree.reAsyncChildNodes(nodes, "refresh",true);
             }
	});
  }
}

function addIBMManager(){
	$.dialog({
		id:'ibmManager',
		title:'添加小型机',
		max:false,
		min:false,
		width:'400px',
		height:'220px',
		content:'url:ibmmanager_addIBMManager.do'
	});
	hideRMenu();
}
function updateIBMManager(){
	var treeNode = rightSNode;
	$.dialog({
		id:'ibmManager',
		title:'修改小型机',
		max:false,
		min:false,
		width:'300px',
		height:'120px',
		content:'url:ibmmanager_updateIBMManager.do?obj.id='+treeNode.id
	});
	hideRMenu();
}
function deleteIBMManager(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确定移除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "ibmmanager_deleteIBMManager.do?id="+treeNode.id,
			dataType: "json",
			success : function(data){
				if(data.result==1){
					   alert("下面有数据不能移除");
				}else{
					   var nodes = zTree.getNodeByParam("id",0);
		  			   zTree.reAsyncChildNodes(nodes, "refresh",true);
				 }
			}
	   });
	}
	
}
function saveIBMManager(data){
  	 $.ajax({
            type: "POST",
            url: "ibmmanager_saveIBMManager.do",
            data: data,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 //var nodes = zTree.getNodeByParam("id",0);
	  				 zTree.reAsyncChildNodes(null, "refresh",true);
	              }
              }
    });
}
 
/***************IBM整机*******************/
function addPower(){
	var treeNode = rightSNode;
	$.dialog({
		id:'power',
		title:'添加整机',
		max:false,
		min:false,
		width:'300px',
		height:'120px',
		content:'url:ibmmanager_addPower.do?obj.parent_id='+treeNode.id
	});
	hideRMenu();
}
function updatePower(){
	var treeNode = rightSNode;
	$.dialog({
		id:'power',
		title:'修改整机',
		max:false,
		min:false,
		width:'300px',
		height:'120px',
		content:'url:ibmmanager_updatePower.do?obj.id='+treeNode.id
	});
	hideRMenu();
}
function savePower(form){
	var treeNode = rightSNode;
 	 $.ajax({
            type: "post",
            url: "ibmmanager_savePower.do",
            data: form,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 //var nodes = zTree.getNodeByParam("id",data.parent_id);
	  				 zTree.reAsyncChildNodes(treeNode.getParentNode().getParentNode(), "refresh",true);
	              }
              }
    });
}
function delPower(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "ibmmanager_delPower.do?id="+treeNode.id+"&parent_id="+treeNode.parent_id,
			dataType: "json",
			success : function(data){
				if(data.result==1){
					   alert("下面有数据不能移除");
				}else{
					   //var nodes = zTree.getNodeByParam("id",data.parent_id);
	 				   zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
				 }
			}
	   });
	}
}
/******************逻辑分区**************************/
function addLogicPartition(){
	var treeNode = rightSNode;
	$.dialog({
		id:'logicPartition',
		title:'添加逻辑分区',
		max:false,
		min:false,
		width:'300px',
		height:'120px',
		content:'url:ibmmanager_addLogicPartition.do?obj.parent_id='+treeNode.id
	});
	hideRMenu();
}
function updateLogicPartition(){
	var treeNode = rightSNode;
	$.dialog({
		id:'logicPartition',
		title:'修改逻辑分区',
		max:false,
		min:false,
		width:'300px',
		height:'120px',
		content:'url:ibmmanager_updateLogicPartition.do?obj.id='+treeNode.id
	});
	hideRMenu();
}
function saveLogicPartition(form){
	var treeNode = rightSNode;
 	 $.ajax({
            type: "post",
            url: "ibmmanager_saveLogicPartition.do",
            data: form,
            dataType: "json",
            success : function(data){
	              if(data.result==1){
	            	  alert("名称已经存在请重新添加.");
	              }else{
	            	 //var nodes = zTree.getNodeByParam("id",data.parent_id);
	  				 zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
	              }
              }
    });
}
function delLogicPartition(){
	hideRMenu();
	var treeNode = rightSNode;
	if(confirm("确定删除?")){
		var treeNode = rightSNode;
		$.ajax({
			type: "get",
			url: "ibmmanager_delLogicPartition.do?id="+treeNode.id+"&parent_id="+treeNode.parent_id,
			dataType: "json",
			success : function(data){
				if(data.result==1){
					   alert("下面有数据不能删除");
				}else{
					   //var nodes = zTree.getNodeByParam("id",data.parent_id);
	 				   zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh",true);
				 }
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
