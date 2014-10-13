<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript">
        function submitRequest(theForm){ 
        	if(theForm.type.value==0){
        		alert("集群类型不能为空");
				return;
        	}
        	if(theForm.name.value==0){
        		alert("集群名称不能为空");
				return;
        	}
        	var ID = "<%=request.getAttribute("ID")%>";
        	var dcName = "<%=request.getAttribute("dcName")%>";
        	var entityId = "<%=request.getAttribute("entityId")%>";
        	var clusterName = theForm.name.value;
        	var clustertype = $("#type").find('option:selected').val();
        	dcName = encodeURI(encodeURI(dcName)) ;
        	clusterName = encodeURI(encodeURI(clusterName));
        	var url;
        	if(clustertype==1){
        		url = 'cluster_saveCluster.do?ID='+ID+'&dcName='+dcName+'&clusterName=' + clusterName + '&entityId=' + entityId;
        	}else if(clustertype==3){
				//Xen主服务器信息
				var username = theForm.username.value;
				var hostName = theForm.hostName.value;
				var password = theForm.password.value;
        		url = 'xen_saveCluster.do?ID='+ID+'&clusterName=' + encodeURI(encodeURI(clusterName)) + '&clustertype=' + clustertype
				+ '&username=' + username+ '&hostName=' + hostName+ '&password=' + password;
        	}
        	$.getJSON(url,{'time':new Date().toString()},function(data){
        		if(data.result==1){
        			alert("保存集群成功!");
        			window.parent.refreshParentNode();
        		}else{
        			alert("保存集群失败!");
        		} 
        	});  
       }
	   
	   function onSelectChange() {
	   		var clustertype = $("#type").find('option:selected').val();
			if(clustertype!=3){
				document.getElementById("hostName").style.display="none";
				document.getElementById("hostUserName").style.display="none";
				document.getElementById("hostPassword").style.display="none";
        	}else if(clustertype==3){
        		document.getElementById("hostName").style.display="";
				document.getElementById("hostUserName").style.display="";
				document.getElementById("hostPassword").style.display="";
        	}
	   }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="cluster_saveCluster.do" method="post" id="theForm" cssStyle="theForm">
        <div>
            <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
                <tr>
                    <td class="til">
                        集群类型
                    </td>
                    <td>
                        <s:select list="#{'1':'VMWARE','2':'KVM','3':'XEN'}" headerKey="0" headerValue="--请选择--" name="theForm.type" id="type" width="60%" onChange="onSelectChange()">
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td class="til">
                        集群名称
                    </td>
                    <td>
                        <s:textfield name="theForm.name" id="name" cssClass="txt"/>
                    </td>
                </tr>
				<tr id=hostName style="display:none">
					<td class="til" width="20%">
						主服务器名称：
					</td>
					<td>
						<s:textfield name="theForm.HostName" id="hostName" cssClass="txt"/>
					</td>
				</tr>
				<tr id=hostUserName style="display:none">
					<td class="til"  width="20%">
						用户名：
					</td>
					<td>
						<s:textfield name="theForm.username" id="username" cssClass="txt"/>
					</td>
				</tr>
				<tr id=hostPassword style="display:none">
					<td class="til"  width="20%">
						密码：
					</td>
					<td>
						<s:password name="theForm.password" id="password" cssClass="txt"/>
					</td>
				</tr>
                <tr>
                    <td colspan="4" class="btnCenter">
                        <input type="button" class="thickbox btn-style02" value="确定" onclick="javascript:submitRequest(document.getElementById('theForm'));return false;"/><input type="button" class="thickbox btn-style02" value="重置" onclick="javascript:void(document.getElementById('theForm').reset());return false;"/>
                    </td>
                </tr>
            </table>
        </div>
    </s:form>
</body>