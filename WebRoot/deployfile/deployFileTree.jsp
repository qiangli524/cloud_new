<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<head>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/cresources/ztree/zTree.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/unitedTree.js"></script>
<script type="text/javascript">
		var orderID = '<s:property value="orderId"/>';

		var api = frameElement.api;
		var w = api.opener;
		api.button({
			id:'OkAnd',
			name: '确定',
			callback:submitRequest,
			focus: true
		},
		{
			id:'cancle',
			name: '取消'
		}); 
		var setting = {
			check: {
					enable: true,
					chkboxType: { "Y": "s", "N": "s" }
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			async : {
				enable : true,
				dataType: "json",
				url : "deployfile_asyncForTree.do",
				autoParam : [ "id","name","file_url","parend_id","state","type","order_id"],
				otherParam: ["orderId",orderID]
			}
		};
		
		function submitRequest(){
			var taskId = $("#taskId").val();
			var orderId = $("#orderId").val();
			var rollBackType = $("#rollBackType").val();
			var nodes = zTree.getCheckedNodes(true);
			var file_url="";
			if(nodes){
				for (var i=0, l=nodes.length; i<l; i++) {
					var file_urls = nodes[i].file_url+",";
					file_url +=  file_urls;
				}
			}
			if(file_url == ""){
				alert("请选择文件!");
				return false;
			}
			var url = "deployfile_sendSelectedNodes.do?file_url=" + file_url + "&taskId=" + taskId + "&orderId=" + orderId+"&rollBackType="+rollBackType;
			if(confirm("确定回滚吗?")==true){
				$.ajax({
					url:url,
					async: false,
	      			cache: false,
	      			contentType:"application/x-www-form-urlencoded",
	      			success: function(data) {
	      			}
			   });
			}
		} 
		
		var zTree;
		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
		});
	
		//控制隐藏div
		var flag = 0;
		function hide() {
			$(".left").toggle();
			if (flag == 0) {
				$("#img").attr("src",
						"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon2.gif");
				flag = 1;
			} else if (flag == 1) {
				$("#img").attr("src",
						"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif");
				flag = 0;
			}
		}
</script>
</head>
<body>
<s:hidden id="taskId" name="taskId"></s:hidden>
<s:hidden id="orderId" name="orderId"></s:hidden>
<s:hidden id="rollBackType" name="rollBackType"></s:hidden>
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div style="float: left; margin-top: 200px">
			<img id="img"
				src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon.gif"
				onclick="hide()" />
		</div>
		<%--<div class="right">
			<div id="iframe">
				<iframe id="right_iframe" src="ibmtab_showFirstPage.do" name="xxxx" height="100%" width="76%" frameborder="0" style="position:fixed;left:259px;right:0px;top:0px;bottom:0px;overflow-x:auto;border:none;background：#fff;"></iframe>
			</div>
		</div>
	--%></div>
</s:form>
</body>
