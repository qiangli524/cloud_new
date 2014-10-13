<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<head>
<title></title>
<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css" type="text/css" />
<link rel="stylesheet" href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"src="sxcloud/cjs/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
		var baseappid = '<s:property value="baseappid"/>';

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
				url : "baseappfile_asyncForTree.do",
				autoParam : [ "id","name","file_url","parend_id","baseappid"],
				otherParam: ["baseappid",baseappid]
			}
		};
		//提交回滚请求
		function submitRequest(){
			
			var path = $("#path").val();
			if(path == ""){
				alert("请指定路径");
				return false;
			}
			
			//var serverid = $(".left").$("#serverid").val();
			//var path = $(".left#path").val();

			
			var nodes = zTree.getCheckedNodes(true);
			var file_url="";
			if(nodes){
				for (var i=0, l=nodes.length; i<l; i++) {
					if (!nodes[i].isParent) {
						var file_urls = nodes[i].file_url + ",";
						file_url +=file_urls;
					}
				}
			}
			if(file_url == ""){
				alert("请选择文件!");
				return false;
			}
			


			
			//alert("file_url:"+file_url);
			api.get("catchSoftware").listFilePath(file_url);
			
		} 
		
		var zTree;
		$(document).ready(function() {
			$.fn.zTree.init($("#treeDemo"), setting);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
		});
		
		$(function(){ 
			var _iframe = window.parent.document.getElementById("mainIframe");
			var iframeH = window.parent.document.documentElement.clientHeight-120;
			$(_iframe).attr("height",iframeH);
			$("div.js_toggleLeft").height(iframeH);
			$("#right_iframe").attr("height",iframeH);
			$("#treeDemo").css("height",iframeH-44);
			$(window).resize(function(){
				iframeH = window.parent.document.documentElement.clientHeight-120;
				$(_iframe).attr("height",iframeH);
				$("div.js_toggleLeft").height(iframeH-44);
				$("#right_iframe").attr("height",iframeH-54);	
				$("#treeDemo").css("height",iframeH-54);
			})
		})
		
		//控制隐藏div
		var flag = 0;
		function hide() {
			$(".left").toggle(100);
			if (flag == 0) {
				$("#img").attr("src",
						"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-right.png");
				$("#right_iframe").attr("width","100%");
				flag = 1;
			} else if (flag == 1) {
				$("#img").attr("src",
						"sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png");
				$("#right_iframe").attr("width","100%");
				flag = 0;
			}
		}

		function dealRecoverType(){
			var netType=$("input[name=recoverType]:checked").val();
			if(netType==1){
				$("#device").hide();
			}else{
				$("#device").show();
			}
		}
		
		function pageOnload() {
			dealRecoverType();
		}
</script>
</head>
<body class="pop-body" onload="pageOnload()">
<s:hidden id="backupid" name="backupid"></s:hidden>
<s:form action="" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<div class="toggle-collapse js_toggleLeft">
             <img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png" onclick="hide()">
        </div>
        <div class="right">
        <!--  
			<div id="iframe">
				<iframe id="right_iframe" src="swiftbackfile_backupFileRecoverPage.do" name="xxxx"  width="100%" frameborder="0"></iframe>
			</div>
		-->	
		</div>
	</div>
</s:form>
</body>
