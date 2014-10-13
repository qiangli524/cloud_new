<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
var allauthorityId = "";
		//得到页面的所有节点，节点authorityId以逗号分割
	
		function getAllNodes(treeNode){
			if(treeNode){
				for (var i=0, l=treeNode.length; i<l; i++) {
					if(treeNode[i].authorityId != -1){
						allauthorityId += treeNode[i].authorityId + ",";
					}
					
					if(treeNode [i].isParent){
						getAllNodes(treeNode[i].children);
					}
				}
			}
		}
		
		//获取所有勾选状态被改变的节点，(包括原来已勾选的节点，现取消勾选;原来未被勾选的节点，现已勾选)
		var change = "";
		function getChange(){
			var rootNodes = zTree.getNodes();
			var nodes = zTree.getChangeCheckedNodes();
			
			if(nodes){
				for (var i=0, l=nodes.length; i<l; i++) {
					var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].entityId+ ":";
					change +=  nameandtype;
				}
			}
		}
		function submitRequest(){
			var rootNodes = zTree.getNodes();
			getChange();
			var nodes = zTree.getCheckedNodes(true);
			var names="";
			if(nodes){
				for (var i=0, l=nodes.length; i<l; i++) {
					var nameandtype = nodes[i].name + "," + nodes[i].type+"," +nodes[i].entityId+ ":";
					names +=  nameandtype;
				}
			}
			var userId = '<%=request.getAttribute("userId")%>';
			 var childHtml = document.getElementById("right_iframe").contentWindow; 
	 			var sendstyle = childHtml.$("select[id=sendstyle] option:selected").val();
	 			var subscribe = childHtml.$("select[id=subscribe] option:selected").val();
	 			var alarmlevel = childHtml.$("select[id=alarmlevel] option:selected").val();
	 			var sendmode = childHtml.$("select[id=sendmode] option:selected").val();
	 			var sendtime1 = childHtml.document.getElementById("sendtime1").value;
	 			var sendtime2 = childHtml.document.getElementById("sendtime2").value;
	 			var unsendtime1 = childHtml.document.getElementById("unsendtime1").value; 
	 			var unsendtime2 = childHtml.document.getElementById("unsendtime2").value; 
			var url = "message_saveUserResources.do?names=" + encodeURI(encodeURI(names)) + "&userId=" + userId + "&sendstyle=" + sendstyle + "&alarmlevel=" + alarmlevel + "&sendmode="  + sendmode + "&sendtime1=" + sendtime1 + "&sendtime2=" + sendtime2 + "&unsendtime1=" + unsendtime1 + "&unsendtime2=" + unsendtime2;
			if(confirm("确定保存吗?")==true){
				$.ajax({
					url:url,
					async: false,
      				cache: false,
      				contentType:"application/x-www-form-urlencoded",
      				success: function(data) {
      					var json = eval("("+ data+")");
        				if(json.result==-1){
							alert("保存失败!");
							
							//zTree.reAsyncChildNodes(null, "refresh",true);
						}else{
<%--							alert("保存成功!");--%>
window.location.href='message_listUserSubscribe.do?USERID=' + userId; 
							//zTree.reAsyncChildNodes(null, "refresh",true);
						}
      				}
				});
			}
			//if(confirm("确定保存吗?")==true){
			//	$.getJSON(url,{'time':new Date().toString()},function(data){
			//		if(data.result==-1){
			//			alert("保存失败!");
			//			zTree.reAsyncChildNodes(null, "refresh",true);
			//		}else{
			//			alert("保存成功!");
			//			zTree.reAsyncChildNodes(null, "refresh",true);
			//		}
			//	});
			//}else{
			//	allauthorityId="";
			//}
		}
</script>
<body>
	<s:form action="vmauth_editUserVirAuthority" method="post" id="theForm">
		<div class="content_wrap">
			<jsp:include page="listVmTree.jsp"></jsp:include>
			<div class="right">
				<div id="iframe">
					<iframe id="right_iframe" src="message_WarningInfoPage.do"  name="xxxx" height="100%" width="76%;" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;"></iframe>
				</div>
			</div>
		</div>
	</s:form>
</body>
