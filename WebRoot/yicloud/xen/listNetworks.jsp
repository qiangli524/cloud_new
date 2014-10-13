<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
var poolUuid = '<%=request.getAttribute("pool_uuid")%>';
var hostUuid = '<%=request.getAttribute("host_uuid")%>';
 $(function(){
 	$("[name='addNetwork']").click(function(){
	   $.dialog({
				id:'addNetworkPage',
				title:'新建网络',
				resize:false,
				width: '800px',
				height: '400px',
				lock:true,
				content: 'url:xen_addNetworkPage.do?poolUuid='+poolUuid+"&hostUuid="+hostUuid+"&dialogName="+"addNetworkPage"});
	 });
		   
 	$("[name='configuretion']").click(function(){
		alert("您的操作，暂不支持");
		return false; 
	});
 	
 	$("[name='deleteInterface']").unbind().live("click",function(){
 		if(confirm("该操作将永久删除所选网络接口。是否继续？")){
 			bar("deleteInterface","正在删除网络，请稍等...");
	 		var networkUuid = $(this).attr("networkUuid");
	 		var url = "xen_deleteNetwork.do?poolUuid="+poolUuid+"&networkUuid="+networkUuid;
	 		$.getJSON(url,{"time":new Date().toString()},function(data){
	 			if(data.responseCode == 1){
	 				$("."+networkUuid).remove();
	 				barEnd("deleteInterface","删除网络成功！");
	   		 	}else if(data.responseCode == -1){
		    		barEnd("deleteInterface","删除网络失败！");
		    	}
	 		});
 		}
	});
	
	$("[name='adjustInterface']").unbind().live("click",function(){
		var networkUuid = $(this).attr("networkUuid");
		var networkName = $("."+networkUuid+" :eq(0)").text();
		var networkDesc = $("."+networkUuid+" :eq(1)").text();
		var nicName = $("."+networkUuid+" :eq(2)").text();
		var VLAN = $("."+networkUuid+" :eq(3)").text();
		var automatic = $("."+networkUuid+" :eq(4)").text();
		$.dialog({
			id:'goAdjustNetworkPage',
			title:'调整网络',
			resize:false,
			width: '400px',
			height: '200px',
			lock:true,
			content: 'url:xen_goAdjustNetworkPage.do?poolUuid='+poolUuid+"&hostUuid="+hostUuid
					+"&networkUuid="+networkUuid+"&nicName="+encodeURI(encodeURI(nicName))+"&networkName="+encodeURI(encodeURI(networkName))
					+"&networkDesc="+encodeURI(encodeURI(networkDesc))+"&VLAN="+VLAN+"&automatic="+encodeURI(encodeURI(automatic))
					+"&dialogName="+"goAdjustNetworkPage"
		});
	});
 });

	   
function addNetwork(url,dialogName){
	closeDialog(dialogName);
	bar("addNetworkbar","主机添加网络中，请稍等...");
	$.getJSON(url,{"time":new Date().toString()},function(data){
		var table = $(".blue-table:first");
		if(data.responseCode == 1){
			var $_tr = creatTableTr(data);
			table.prepend($_tr);
			barEnd("addNetworkbar","添加成功!");
		}else if(data.responseCode == -1){
			barEnd("addNetworkbar","添加失败!");
		}	
	});
}

function adjustNetwork(url,networkUuid){
	bar("adjustBar","主机调整网络中，请稍等...");
	$.getJSON(url,{"time":new Date().toString()},function(data){
		var table = $(".blue-table:first");
		if(data.responseCode == 1){
			var $_tr = creatTableTr(data);
			$("."+networkUuid).replaceWith($_tr);
			barEnd("adjustBar","调整成功!");
		}else if(data.responseCode == -1){
			barEnd("adjustBar","调整失败!");
		}	
	});
}

function closeDialog(dialogName){
	$.dialog.list[dialogName].close();
}

function creatTableTr(data){
	var $_tr = $("<tr></tr>");
	$_tr.attr("class",data.networkUuid);
	
	if(data.vlanNum==-1){
		data.vlanNum = '-';
	}
	if(data.autoMatic==true){
		data.autoMatic = '是';
	}else{
		data.autoMatic = '否';
	}
	if(data.connState==true){
		data.connState =  '已链接';
	}else {
		data.connState =  '已断开链接';
	}
	
	var link1 = $("<a>调整</a>");
	link1.attr("href","javascript:;").attr("style","color: blue;margin-right: 5px").attr("name","adjustInterface").attr("networkUuid",data.networkUuid);
	var link2 = $("<a>删除</a>");
	link2.attr("href","javascript:;").attr("style","color: blue;margin-right: 5px").attr("name","deleteInterface").attr("networkUuid",data.networkUuid);
	var $_td_oper = $("<td></td>");
	$_td_oper.append(link1).append(link2);
	
	$_tr.append("<td>"+data.networkName+"</td>").append("<td>"+data.networkDesc+"</td>")
	.append("<td>"+data.nicName+"</td>").append("<td>"+data.vlanNum+"</td>").append("<td>"+data.autoMatic+"</td>")
	.append("<td>"+data.connState+"</td>").append("<td>"+data.mac+"</td>").append($_td_oper);
	return $_tr;
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
<s:form action="" method="post" id="theForm">
	<s:hidden name="#theBean.store_uuid" id="store_uuid"/>
<!-- 	<div class="blue-wrap noborder"> -->
	<div class="blue-wrap noborder" style="overflow: auto;width: 800px;height: ">
		 	<div class="table-head">
			 	 <ul class="btns" style="height: 30px;" >
					<li><input type="button" class="thickbox btn-style02" value="添加网络" name="addNetwork"/></li>
					<li><input type="button" class="thickbox btn-style02" value="配置" name="configuretion"/></li>
				</ul>
		    </div>   
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
						<th onclick="sort(theTable,1,'string')">说明</th>
						<th onclick="sort(theTable,2,'string')">NIC</th>
						<th onclick="sort(theTable,3,'string')">VLAN</th>
						<th onclick="sort(theTable,4,'string')">自动</th>
						<th onclick="sort(theTable,5,'string')">链接状态</th>
						<th onclick="sort(theTable,6,'string')">MAC</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr class="<s:property value="#theBean.networkUuid"/>">
							<td><s:property value="#theBean.networkName"/></td>
							<td><s:property value="#theBean.networkDesc"/></td>
                            <td><s:if test="theBean.nicName=='' ||　theBean.nicName==undefined"></s:if><s:else><s:property value="#theBean.nicName"/></s:else></td>
							<td ><s:if test="#theBean.vlanNum==-1 || #theBean.vlanNum== 0">-</s:if><s:else><s:property value="#theBean.vlanNum"/></s:else></td>
							<td><s:if test="#theBean.autoMatic==true">是</s:if><s:else>否</s:else></td>
							<td>
							<s:if test="#theBean.connState==true">
							  已链接
							</s:if>
							<s:else>
							 已断开链接
							</s:else>
                            </td>
							<td>
							<s:property value="#theBean.mac"/>
							</td>
							<td>
							 	<a href="javascript:;" name="adjustInterface" networkUuid="<s:property value="#theBean.networkUuid"/>"  style="color: blue;margin-right: 5px">调整</a>
	                            <a href="javascript:;" name="deleteInterface" networkUuid="<s:property value="#theBean.networkUuid"/>" style="color: blue;margin-right: 5px">删除</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
	</div>

	<div style="padding-top: 15px">
		<div class="blue-wrap noborder" style="overflow: auto;width: 800px;">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable">
				<thead>
					<tr>
						<th>服务器</th>
						<th>接口</th>
						<th>网络</th>
						<th>IP地址</th>
						<th>子网掩码</th>
						<th>网关</th>
						<th>DNS</th>
					</tr>
				</thead>
				<tbody>
                  <s:iterator value="theForm.hostResultList" id="theBean">
						<tr>
							<td>
							<s:property value="#theBean.hostName"/>
							</td>
							<td>
							主
                            </td>
                            <td>
							<s:property value="#theBean.nicName"/>
                            </td>
							<td >
							 <s:property value="#theBean.ip"/>
                            </td>
							<td>
				             <s:property value="#theBean.netmask"/>
							</td>
							<td>
		                     <s:property value="#theBean.gateway"/>
                            </td>
							<td>
							<s:property value="#theBean.DNS"/>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
<!-- 			<div class="table-head"> -->
<!-- 		 	 <ul class="btns" style="height: 30px;" > -->
				
<!-- 			</ul> -->
<!-- 	</div> -->
<!-- 	</div> -->
	</div>
</s:form>
</body>
