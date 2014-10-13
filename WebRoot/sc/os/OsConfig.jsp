<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
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
<script type="text/javascript" src="yicloud/busisystree/js/rMenu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<script type="text/javascript" src="sxcloud/js/uuid.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/rMenu.js?d1"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resource/united/js/unitedTree.js?e1"></script>

<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 0 1px 1px 1px;
}

div#rMenu ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
    width: 100px;
}
div#div_snapshot {
	position: absolute;
	visibility: hidden;
	background-color: #9dbcd9;
	text-align: left;
	padding: 1px;
}
div#div_snapshot ul li {
	background-color: white;
    cursor: pointer;
    list-style: none outside none;
    margin: 1px 0 0 1px;
    padding: 2px 5px 2px 12px;
}

div#rMenu ul li:hover {
    background-color: #dfecf9;
}
<%--下拉框--%>
<!--
body {
	background: #fff
}

.Menu {
	position: relative;
	width: 210px;
	height:550px;
	z-index: 1;
	background: #FFF;
	border: 1px solid #000;
	margin-top: -10px;
	display: none;
	overflow-y:auto;
}

.Menu2 {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: auto;
	overflow: hidden;
	z-index: 1;
}

.Menu2 ul {
	margin: 0;
	padding: 0
}

.Menu2 ul li {
	width: 100%;
	height: 25px;
	line-height: 25px;
	text-indent: 15px;
	border-bottom: 1px dashed #ccc;
	color: #0A0A0A;
	cursor: pointer;
	change: expression(
		this.onmouseover = function(){ 
    		this.style.background="#F2F5EF";
		}
		,
		this.onmouseout =function(){
			this.style.background="";
		}
	)
}
input {
	width: 200px
}

.form {
	width: 200px;
	height: auto;
}

.form div {
	position: relative;
	top: 0;
	left: 0;
	margin-bottom: 5px
}

#List1,#List2,#List3 {
	left: 0px;
	top: 0px;
}
-->
<%--下拉框--%>
</style>
<script type="text/javascript">
	//控制隐藏div
	var flag = 0;
	function hide() {
		$(".left").toggle(100);
		if (flag == 0) {
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-right.png");
			$("#right_iframe").attr("width","100%");
			flag = 1;
		} else if (flag == 1) {
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png");
			$("#right_iframe").attr("width","100%");
			flag = 0;
		}
	}

	$(document).ready(function() {
		$("#treeDemo")
	});

	//控制隐藏div
	var flag = 0;
	function hide() {
		$(".left").toggle(100);
		if (flag == 0) {
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-right.png");
			$("#right_iframe").attr("width","100%");
			flag = 1;
		} else if (flag == 1) {
			$("#img").attr("src","sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png");
			$("#right_iframe").attr("width","100%");
			flag = 0;
		}
	}

	$(function(){ 
		var _iframe = window.parent.document.getElementById("mainIframe");
		var iframeH = window.parent.document.documentElement.clientHeight-120;
		$(_iframe).attr("height",iframeH);
		$("div.js_toggleLeft").height(iframeH);
		$("#right_iframe").attr("height",iframeH);
		$("#configComp").css("height",iframeH-44);
		$(window).resize(function(){
			iframeH = window.parent.document.documentElement.clientHeight-120;
			$(_iframe).attr("height",iframeH);
			$("div.js_toggleLeft").height(iframeH);
			$("#right_iframe").attr("height",iframeH);	
			$("#configComp").css("height",iframeH-44);
		})
	})
	
	function comp_config(id,type){
		var url = "";
		if(type == '1'){//基本信息
			url = "autoos_configOsBase.do?theForm.id=" + id;
		}else if(type == '2'){//文件系统
			url = "osFileSystem_queryFileSystem.do?theForm.os_host_id=" + id;
		}else if(type == '3'){//组
			url = "osGroup_queryGroup.do?theForm.os_host_id=" + id;
		}else if(type == '4'){//用户
			url = "osUser_queryUser.do?theForm.os_host_id=" + id;
		}else if(type == '5'){//附加组
			url = "osGroupUser_queryGroupUser.do?theForm.os_host_id=" + id;
		}else if(type == '6'){//详细信息
			url = "autoos_osConfigDetail.do?theForm.id=" + id;
		}else if(type == '7'){//网络
			url = "hostAdapter_queryHostAdapter.do?theForm.os_host_id=" + id;
		}
		if(url == ''){
			alert("未使用该项配置");
			return false;
		}
		$("#right_iframe").attr( "src", url);
	}	
	function returnFunction(){
		var _iframe = window.parent.parent.document.getElementById("mainIframe");
		$(_iframe).attr( "src", "autoos_listOsHost.do");
	}		
</script>
</head>
<body >
<s:form action="osTemplate_showOsTemplate.do" method="post" id="theForm">
	<div class="content_wrap">
		<div class="left">
			<!-- 
			<div>
				<div>
					<input type="button"  value="返回" onclick="returnOs()"/>
				</div>
			</div>
			 -->
			<ul id="configComp" class="ztree">
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','1');">基本信息</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','7');">网络配置</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','2');">文件系统配置</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','3');">组配置</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','4');">用户配置</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','5');">附加组配置</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:comp_config('<s:property value="theForm.id"/>','6');">详细信息</a></li>
				<li><a href="javascript:void(0);return false;" onclick="javascript:returnFunction();">返回列表</a></li>
			</ul>
		</div>
		
		<%--
		<div class="toggle-collapse js_toggleLeft">
			<img id="img" src="sxcloud/cresources/ztree/zTreeStyle/img/diy/icon-left.png" onclick="hide()">
		</div>
		 --%>	
		<div class="right">
			<div id="iframe">
				<%-- 
				<iframe id="right_iframe" src="resourcestatistics_showFirstPage.do" name="xxxx"  width="100%" frameborder="0"></iframe>
				--%>
				<iframe id="right_iframe" src="autoos_configOsBase.do?theForm.id=<s:property value="theForm.id"/>" name="xxxx"  width="100%" frameborder="0"></iframe>
			</div>
		</div>
	</div>
</s:form>
</body>

