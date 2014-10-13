<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

	<script type="text/javascript">
    $(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	
    function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}
	function submitRequest(theForm){
		var ID = <%=request.getAttribute("ID")%>;
		var TYPE=<%=request.getAttribute("TYPE")%>
		var ip = theForm.IP.value;
	//	var name = theForm.NAME_ZH.value
		var username = theForm.USERNAME.value;   
		var password = theForm.PASSWORD.value;
		if(ip==null){
			alert("IP地址不能为空，例如：1.2.3.4");
		}
	//	if(name==null){
	//		alert("主机名称不能为空");
	//	}
		if(username==null){
			alert("用户名称不能为空");
		}
		if(password==null){
			alert("密码不能为空");
		}
		var dcName = '<%=request.getAttribute("dcName")%>';
		var clName = '<%=request.getAttribute("clName")%>';
		var clId = '<%=request.getAttribute("clId")%>';
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var entityId = '<%=request.getParameter("entityId")%>';
		var url;
		//theForm.action='yvm_saveHost.do?ID='+ID+'&TYPE='+TYPE;
		if(TYPE=='29'){//xen集群
			url = 'xen_saveHost.do?ID='+ID+'&TYPE='+TYPE+'&ip='+ip 
			+ '&username=' + username + '&password=' + password+
			'&pool_uuid='+pool_uuid;
			$.getJSON(url,{'time': new Date().toString()},function(data){
			if(data.responseCode==1){
				alert("添加主机成功!");
				window.parent.refreshParentNode();
			}else{
				alert("添加主机失败!");
			}
		});
		}else{
		url = 'yvm_saveHost.do?ID='+ID+'&TYPE='+TYPE+'&ip='+ip + '&username=' + username + '&password=' + password + '&dcName=' + dcName + '&clName=' + clName + '&clId=' + clId + '&entityId=' + entityId;
		mask();
		$.getJSON(url,{'time': new Date().toString()},function(data){
			if(data.result==1){
				removeMask();
				alert("添加主机成功!");
				window.parent.refreshParentNode();
			}else{
				removeMask();
				alert("添加主机失败!");
			}
		});
		}
	   // theForm.submit();
	}
	//添加遮罩层
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
	    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>添加主机中，请等待............';
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
	<s:form action="yvm_saveHost.do" method="post" id="theForm">
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til" width="20%">
					集群名称：
				</td>
				<td>
					<s:property value="#request.clName"/>
				</td>
			</tr>
			<tr>
				<td class="til" width="20%">
					IP地址：
				</td>
				<td>
					<s:textfield name="theForm.IP" id="IP" cssClass="txt"/>
				</td>
			</tr>
			<%-- 
			<tr>
				<td class="til">
					主机名称:
				</td>
				<td>
					<s:textfield name="theForm.NAME_ZH" id="NAME_ZH" cssClass="txt"/>
				</td>
			</tr>
			--%>
			<tr>
				<td class="til" width="20%">
					主机用户名：
				</td>
				<td>
					<s:textfield name="theForm.USERNAME" id="USERNAME" cssClass="txt"/>
				</td>
			</tr>
			
			<tr>
				<td class="til" width="20%">
					密码：
				</td>
				<td>
					<s:password name="theForm.PASSWORD" id="PASSWORD" cssClass="txt"/>
				</td>
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
	</s:form>
</body>
