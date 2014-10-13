<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/uploadify-v3.1/jquery.uploadify.js"></script>
 <style type="text/css">
	div.yincang{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest(){
		theForm.submit();
	}
	function resetForm(theForm){
	document.getElementById("SCRIPT_IP").value="";
	document.getElementById("SCRIPT_USERNAME").value="";
	}
	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'scriptmanage_addScriptmanageObj.do';
	  	theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.s_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'scriptmanage_editScriptmanageObj.do';
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.s_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    
 	    theForm.action = 'scriptmanage_deletecriptmanageObj.do'; 
		theForm.submit();
		
 	}
 	function actionRequest(s_id){
 		alert(s_id);
 	    theForm.action = 'scriptmanage_actionBackup.do?s_id='+s_id;
		theForm.submit();
 	
 	
 	}
 	function actionPlat(){
	 	window.open("depvideo_makeDeployVideo.do?user="+HOSTUSERNAME+"&port="+port+"&pwd="+HOSRPWD+"&ip="+EQ_IP,"ssh");
	 }
 	function init(){
			if('${msg }'!=''){alert('${msg }');}
			}
	function sshchannel(ip){
		if(confirm("该动作会ssh连接至主机，是否继续?")){
			var url = "hostconfig_ssh2Host.do?hostip=" + ip;
			$.dialog({
					id:ip+"",
					title:'连接主机 '+ip,
					width: '370px',
					height: '130px',
					content: 'url:hostconfig_ssh2Host.do?hostip='+ip,
					button:[{name: '连接', callback: function () {
						var ip = this.content.document.getElementById("ip").value;
						var port = this.content.document.getElementById("port").value;
						var user = this.content.document.getElementById("user").value;
						var pwd = this.content.document.getElementById("pwd").value;
						if($.trim(port) == ""){
							alert("端口号不能为空!");
							return false;
						}
						if($.trim(user) == ""){
							alert("用户名不能为空!");
							return false;
						}
						if($.trim(pwd) == ""){
							alert("密码不能为空!");
							return false;
						}
					window.open("depvideo_makeDeployVideo.do?user="+user+"&port="+port+"&pwd="+pwd+"&ip="+ip,"ssh");	
					}}, {name: '取消'}]
				});
		}
	}
			
</script>
</head>
<body onload="init()">
<s:form action="scriptmanage_queryScriptmanageList.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.s_id" id="s_id"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
 <div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%"  border="0">
                  <tr>
                	 <td class="til">服务器IP</td>
					<td>
						<s:textfield name="theForm.SCRIPT_IP" cssClass="txt" id="SCRIPT_IP"></s:textfield>
					</td>
                 
					<td class="til">用户名</td>
					<td>
						<s:textfield name="theForm.SCRIPT_USERNAME" cssClass="txt" id="SCRIPT_USERNAME"></s:textfield>
					</td>
				</tr>
			
				<tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                 </tr>
                </table>
        </div>
  	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li>
				<input type="button" class="thickbox btn-style02" value="添加"
									onclick = "addRequest();return false;"  />
				</li>
				<li>
				<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;"  />
				</li>
				<li>
				<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;"/>
				</li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			<tr>
				<th>选择</th>
				<th>服务器IP</th>
				<th>用户名</th>
				
				<th>脚本路径</th>
				<th>描述</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
			  
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.scriptmanageList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.s_id"/>" /></td>
			  		<!-- <td><s:property value="#theBean.host_id"/></td> -->
			  		<td><a href="javascript:;" onclick="sshchannel('<s:property value="#theBean.SCRIPT_IP"/>') "><s:property value="#theBean.SCRIPT_IP"/></a></td>
			  		<td><s:property value="#theBean.SCRIPT_USERNAME"/></td>
			  		
			  		<td align="center"><div class="yincang" title='<s:property value="#theBean.SCRIPT_CONTENT"/>'><s:property value="#theBean.SCRIPT_CONTENT"/></div></td>
			  		<td align="center"><div class="yincang" title='<s:property value="#theBean.SCRIPT_USE"/>'><s:property value="#theBean.SCRIPT_USE"/></div></td>
			  		<td><s:property value="#theBean.CREATE_TIME"/></td>
			  		<td><input type="button" value="执行脚本"
									onclick="actionRequest('<s:property value="#theBean.s_id"/>');return false;"/></td>
			  		
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>
		</div>
		</div>
</s:form>
</body>
