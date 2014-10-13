<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../sxcloud/common/view.jsp"%>
<head>
	<title></title>
<link href="/cloud/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="/cloud/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/cloud/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
$(function() {
	 $("[name='add']").unbind().live("click",function(){
		 var height = screen.height*0.521;
		 var width = screen.width*0.585;
		 $.dialog({
				id:'convertPage',
				title:'迁移',
				resize:false,
				width: width+'px',
				height: height+'px',
				content: 'url:convert_goConvertPage.do?dialogName=convertPage'});
		});
	 
	 $("[name='delete']").unbind().live("click",function(){
		 var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	   var convertId= "";  
 	    if(checkboxids!=null&&checkboxids.length>0){
 	   		 for(var i=0;i<checkboxids.length;i++){
 	      			if(checkboxids[i].checked){
 	      				couterNum = couterNum + 1 ;
 	      				convertId = checkboxids[i].getAttribute("convertId"); 
 	      			}
 	    	 }
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选需要删除功能信息！");
 	    	return false ;
 	    }else if(couterNum>1){
 	    	alert("只能删除单条数据！");
 	    	return false ;
 	    }else{
 		     if(confirm("确定要删除吗！")) { 
 		    	 var theForm = document.getElementById("theForm");
 				 theForm.action = "convert_deleteConvert.do?convertObj.id="+convertId;
 				 theForm.submit();
 		     }
 	    }
	});
	 
	 $("[name='update']").unbind().live("click",function(){
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	   var convertId= "";  
 	    if(checkboxids!=null&&checkboxids.length>0){
 	   		 for(var i=0;i<checkboxids.length;i++){
      			if(checkboxids[i].checked){
      				couterNum = couterNum + 1 ;
      				convertId = checkboxids[i].getAttribute("convertId"); 
      			}
 	    	 }
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选需要修改功能信息！");
 	    	return false ;
 	    }else if(couterNum>1){
 	    	alert("只能修改单条数据！");
 	    	return false ;
 	    }else{
	    	$.dialog({
				id:'updateConvertPage',
				title:'迁移',
				resize:false,
				width: '350px',
				height: '170px',
				content: 'url:convert_goUpdateConvert.do?convertObj.id='+convertId});
 	    }
	});
});

function searchRequest(){
	var theForm = document.getElementById("theForm");
	var converType=theForm.converType.value;
	var sourceIp=theForm.sourceIp.value; 
	var destiIp=theForm.destiIp.value; 
	theForm.action = "convert_queryConvertData.do?convertObj.convertType=" + converType+"&convertObj.sourceIp="+sourceIp+"&convertObj.destiIp="+destiIp;
	theForm.submit();
}

function resetForm(theForm){
	theForm.converType.value = "-1";
}


function closeDialog(dialogName){
	 $.dialog.list[dialogName].close();
}

function saveConvert(convertType,destiIp,sourceIp){
	 var theForm = document.getElementById("theForm");
	 theForm.action = "convert_saveConvert.do?convertObj.convertType="+convertType+"&convertObj.sourceIp="+sourceIp+"&convertObj.destiIp="+destiIp;
	 theForm.submit();
}

function updateConvert(id,state){
	closeDialog("updateConvertPage");
	var theForm = document.getElementById("theForm");
	theForm.action = "convert_updateConvert.do?convertObj.id="+id+"&convertObj.state="+state;
	theForm.submit();
}

function sshchannel(ip) {
	if (confirm("该动作会ssh连接至主机，是否继续?")) {
		var url = "hostconfig_ssh2Host.do?hostip=" + ip;
		$.dialog({
			id : ip + "",
			title : '连接主机 ' + ip,
			width : '470px',
			height : '230px',
			content : 'url:hostconfig_ssh2Host.do?hostip=' + ip,
			button : [
					{
						name : '连接',
						callback : function() {
							var ip = this.content.document
									.getElementById("ip").value;
							var port = this.content.document
									.getElementById("port").value;
							var user = this.content.document
									.getElementById("user").value;
							var pwd = this.content.document
									.getElementById("pwd").value;
							if ($.trim(port) == "") {
								alert("端口号不能为空!");
								return false;
							}
							if ($.trim(user) == "") {
								alert("用户名不能为空!");
								return false;
							}
							if ($.trim(pwd) == "") {
								alert("密码不能为空!");
								return false;
							}
							window.open(
									"depvideo_makeDeployVideo.do?user="
											+ user + "&port=" + port
											+ "&pwd=" + pwd + "&ip="
											+ ip, "ssh");
						}
					}, {
						name : '取消'
					} ]
		});
	}
}
</script>
<style type="text/css">
</style>
</head>
<body>
<s:form action="" method="post" id="theForm">
	<div class="blue-wrap noborder">
		<div class="query">
			<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
		</div>
		<div class="query-form">
			<table width="100%" class="querytable" border="0">
				<tr>
					<td  class="til">
						源主机：
					</td>
					<td>
						<s:textfield id="sourceIp" cssClass="txt" cssStyle="width:150px;"></s:textfield>
					</td>
					<td  class="til">
						目标主机：
					</td>
					<td>
						<s:textfield id="destiIp" cssClass="txt" cssStyle="width:150px;"></s:textfield>
					</td>
					<td  class="til">
						迁移类型：
					</td>
					<td>
						<s:select list="#{'-1':'请选择','1':'V2P','2':'V2V','3':'P2V','4':'P2P'}" id="converType" cssStyle="width:80px;" value="0"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="btns">
						<div>
							<input type="button" class="thickbox btn-style02" value="查询"
								onclick="javascript:searchRequest()" />
							<input type="button" class="btn-style02" value="重置"
								onclick="javascript:resetForm(document.getElementById('theForm'))" />
						</div>
					</td>
				</tr>
			</table>
		</div>
		<!--query-form end -->
		<div class="table-head">
	 	 <ul class="btns" style="height: 30px;" >
			<li><input type="button" class="thickbox btn-style02" value="添加" name="add"/></li>
			<li><input type="button" class="thickbox btn-style02" value="修改" name="update"/></li>
			<li><input type="button" class="thickbox btn-style02" value="删除" name="delete"/></li>
		</ul>
		</div>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">选项</th>
						<th onclick="sort(theTable,1,'string')">源主机</th>
						<th onclick="sort(theTable,2,'string')">目标主机</th>
						<th onclick="sort(theTable,3,'string')">迁移类型</th>
						<th onclick="sort(theTable,4,'string')">状态</th>
						<th onclick="sort(theTable,5,'string')">开始时间</th>
						<th onclick="sort(theTable,6,'string')">使用时间</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" convertId='<s:property value="#theBean.id"/>'/></td>
							<td>
								<s:if test="#theBean.state==1">
									<a href="javascript:;" onclick="sshchannel('<s:property value="#theBean.sourceIp"/>')"><s:property value="#theBean.sourceIp"/></a>
								</s:if>
								<s:elseif test="#theBean.state==2">
									<s:property value="#theBean.sourceIp"/>
								</s:elseif>
							</td>
							<td>
								<s:if test="#theBean.state==1">
										<s:property value="#theBean.destiIp"/>
								</s:if>
								<s:elseif test="#theBean.state==2">
										<a href="javascript:;" onclick="sshchannel('<s:property value="#theBean.sourceIp"/>')"><s:property value="#theBean.destiIp"/> : <s:property value="#theBean.sourceIp"/></a>
								</s:elseif>
							</td>
							<td>
								<s:if test="#theBean.convertType==1">
							  		V2P
								</s:if>
								<s:elseif test="#theBean.convertType==2">
									V2V
								</s:elseif>
								<s:elseif test="#theBean.convertType==3">
									P2V
								</s:elseif>
								<s:elseif test="#theBean.convertType==4">
								    P2P
								</s:elseif>
	                        </td>
							<td>
								<s:if test="#theBean.state==1">
									<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>进行中
								</s:if>
								<s:elseif test="#theBean.state==2">
									完成
								</s:elseif>
                            </td>
                            <td><s:property value="#theBean.insertTime"/></td>
                            <td><s:property value="#theBean.usedTime"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
	</div>
</s:form>
</body>
