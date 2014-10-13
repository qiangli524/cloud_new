<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
	<script type="text/javascript"
		src="sxcloud/js/progressbar/js/jquery.progressbar.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
				$("#spaceused1").progressBar({boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_green.gif',showText:false,height:7,width:150} );
				$("#spaceused4").progressBar({ boxImage	:' <%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbar.gif', barImage: '<%=request.getContextPath()%>/sxcloud/js/progressbar/images/progressbg_green.gif',showText:false,height:7,width:150} );
	});
	
	</script>
	<!-- 部署时的进度条--jquery ui -->
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script
		src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/ui/jquery.ui.progressbar.js"></script>
	<link rel="stylesheet"
		href="<%=request.getContextPath()%>/sxcloud/js/jqueryui/demos.css" />
	<style>
	.ui-progressbar .ui-progressbar-value {
		background-image:
			url(sxcloud/js/jqueryui/themes/base/images/pbar-ani.gif);
	}
	</style>
		<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script>
	var val;
	$(function() {
		$( "#progressbar" ).progressbar({
			value: 0
		});
	});
	updateProgressbarValue();
	function updateProgressbarValue() {
		setTimeout(updateProgressbarValue, 1000);
		$.getJSON("xen_getProgressbarVal.do",{'time':new Date().toString()},function(data){
			val = data.val;
		});
		if (val == 100) {
			$("#progressbar").progressbar( "value", val);
			return;
		}
		var current = $("#progressbar").progressbar("value");
		if (current >= val) {
			return;
		}
		var value = $("#progressbar").progressbar("value")+20;
		$("#progressbar").progressbar( "value", value);
	}
	</script>
	<script type="text/javascript">
	function deployVMRequest(theForm) {
		var name = theForm.NAME_EN.value;
		var desc = theForm.DESC.value;
		var parent_id = <%=request.getAttribute("parent_id")%>;
		var entity_id = <%=request.getAttribute("entity_id")%>;
		var clusterId = <%=request.getAttribute("clusterId")%>;
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		var modeType = $("input[name='theForm.osType']:checked").val();
		var store_uuid;
		if(modeType==2){
			store_uuid = theForm.store_uuid.value;
		}else{
			store_uuid = '';
		}
		if (name=='') {
			alert('虚拟机名称不能为空');
			return;
		}
		url = "xen_cloneVMByvm.do?name="+ encodeURI(encodeURI(name))  + "&entity_id=" + entity_id+
		"&parent_id="+parent_id+"&clusterId="+clusterId+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&store_uuid="+store_uuid+"&desc="+encodeURI(encodeURI(desc));
		bar(entity_id,"正在复制虚拟机"+name);
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.responseCode == 1){
				barEnd(entity_id,"成功复制虚拟机"+name);
				window.parent.refreshParentNode();
				//window.location.reload();
			}else if(data.responseCode == -1){
				barEnd(entity_id,"无法复制虚拟机"+name);
				window.document.location.reload();
			}
		});
	}
	
	function show(){
        document.all.ly.style.display="block";  
     	document.all.ly.style.width=document.body.scrollWidth;//网页正文全文宽
     	document.all.ly.style.height=document.body.scrollHeight;//网页正文全文高
        document.all.divTest.style.display='block'; 
       
        document.getElementById("divTest").style.visibility="visible";
        /*var ss = document.getElementById("progressbar");
        ss.innerHTML= "<img src='sxcloud/js/progressbar/images/progressbg_yellow.gif'/>";*/
    }
    
    function modeSelect(){
    	var modeType = $("input[name='theForm.osType']:checked").val();
    	if(modeType==1){
    		document.getElementById('completeCopy').style.display="none";
    	}
    	else{
    		document.getElementById('completeCopy').style.display="";
    	}
    }
    
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

	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
	</script>
</head>
<body>
<s:form action="xen_hostInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
					<td class="til" width="15%"  align="left">
						选择复制模式
					</td>
					<td  align="left">
						<input type="radio" name="theForm.osType" id="osType1" checked="checked" value="1" onclick="modeSelect()"/><label for="osType1">快速复制</label>
						<input type="radio" name="theForm.osType" id="osType2" value="2" onclick="modeSelect()"/><label for="osType2">完整复制</label>
					</td>
				</tr> 
				<tr>
					<td class="til" width="15%" align="left"> 
						虚拟机名称
					</td>
					<td  align="left">
						<s:textfield name="theForm.NAME_EN"  id="NAME_EN"/>
					</td>
				</tr> 
				<tr>
					<td class="til" width="15%" align="left"> 
						描述
					</td>
					<td  align="left">
						<s:textfield name="theForm.DESC"  id="DESC"/>
					</td>
				</tr> 
				<tr id="completeCopy" style="display: none;">
					<td class="til" width="15%" align="left"> 
						选择存储
					</td>
					<td  align="left">
						<s:select list="theForm.resultList" name="theForm.store_uuid" listKey="store_uuid" listValue="NAME" headerKey="" headerValue="--请选择--" id="store_uuid"/>
					</td>
				</tr> 
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="提交"
							id="confirm"
							onclick="javascript:deployVMRequest(document.getElementById('theForm'));return false;" />
						<input type="button" class="thickbox btn-style02" value="重置"
							onclick="javascript:resetForm(document.getElementById('theForm'))" />
					</td>
				</tr>
			</table>
		</div>
		<!-- 创建虚拟机进度条 -->
		<div id="divTest"
			style="position: absolute; z-index: 10; width: 310; height: 30px; background-color: Yellow; display: none; top: 15%; left: 30%;">
			<table border="1" width="60%" cellspacing="0" cellpadding="0"
				style="border-collapse: collapse" bgcolor="#FFFFEC" height="50"
				align="center">
				<tr>
					<td bgcolor="#3399FF" style="font-size: 12px; color: #ffffff"
						height=5>
						虚拟机创建中...
					</td>
				</tr>
				<tr>
					<td style="" align="center">
						<div id="progressbar"></div>
					</td>
				</tr>
			</table>
		</div>
		<div id="ly"
			style="position: absolute; top: 0px; filter: alpha(opacity =   10); background-color: #777; z-index: 2; left: 0px; display: none;">

		</div>
		<div id="main" style="background-color: #FFFFFF; height: 500px;">
		</div>
</s:form>
</body>
