<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
	function submitRequest(theForm){
		if(theForm.REMOTE_HOST.value==""){
			alert("服务器不能为空!");
			return false;
		} 
		if(theForm.REMOTE_PATH.value==""){
			alert("文件夹不能为空!");
			return false;
		}
		if(theForm.name.value==""){
			alert("存储名称不能为空!");
			return false;
		}
		var id = '<%=request.getAttribute("id")%>';
		var remotehost = theForm.REMOTE_HOST.value;
		var remotepath = theForm.REMOTE_PATH.value;
		var name = theForm.name.value;
		var readOnly = "readWrite";
		if(document.getElementById("readonly").checked){
			readOnly = "readOnly";
		}
		var url = "datastore_saveNFS.do?id=" + id + "&remotehost=" + remotehost + "&remotepath=" + remotepath + "&name=" + name + "&readOnly=" + readOnly;
		mask();
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.result==1){
				removeMask();
				alert("添加网络存储成功!");
				window.close();
			}else{
				removeMask();
				alert("添加网络存储失败!可能的原因：" + data.reason);
			}
		});
	}
	function mask(){
		var doc = window.document;
	  	var w = doc.createElement("div");
	    w.setAttribute("id","mybody")
	    with(w.style){
	        position = 'absolute';
	        zIndex = '10000';
	        width = Math.max(doc.documentElement.scrollWidth, doc.documentElement.clientWidth) + "px";
	        height =Math.max(doc.documentElement.scrollHeight, doc.documentElement.clientHeight) + "px";
	        position="absolute";
	        left = '0';
	        top = '0';
	        background = '#FAFAFA';
	        filter = 'Alpha(opacity=10)';
	        opacity = '0.7';
	    }
	    doc.body.appendChild(w);
	    //**********************************************//
	    var ig=doc.createElement("div");
	    ig.setAttribute("id","progressbar")
	    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>添加存储中，请等待............';
	    doc.getElementById("mybody").appendChild(ig);
	    with(ig.style){
	        position = 'absolute';
	        zIndex = '10001';
	        left = '55%';
	        top = '35%';
	        marginLeft = - ig.offsetWidth / 2 + 'px';
	        marginTop = - ig.offsetHeight / 2 + 'px';
	    }
	    doc.body.appendChild(ig);
	}
	//移除mask
	function removeMask() {
		var doc = window.document;
		var mybody = doc.getElementById('mybody');
		doc.body.removeChild(mybody);
		var progressbar = doc.getElementById('progressbar');
		doc.body.removeChild(progressbar);
	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
	<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
		<tr>
			<td class="til">服务器</td>
			<td><s:textfield name="theForm.REMOTE_HOST" id="REMOTE_HOST" cssClass="txt"/></td>
		</tr>
		<tr>
			<td class="til">文件夹</td>
			<td><s:textfield name="theForm.REMOTE_PATH" id="REMOTE_PATH" cssClass="txt"/></br>
			例如：  /vols/vol0/datastore-001</td>
		</tr>
		<tr>
			<td class="til">是否挂载只读NFS</td>
			<td><input type="checkbox" id="readonly"/>挂载只读NFS </td>
		</tr>
		<tr>
			<td class="til">数据存储名称</td>
			<td><s:textfield name="theForm.NAME" id="name" cssClass="txt"/></td>
		</tr>
		
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
		</table>
		</div>
	</s:form>
</body>
