<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
var vm_uuid = '<%=request.getAttribute("vmUuid") %>';
var pool_uuid = '<%=request.getAttribute("pool_uuid") %>';
var host_uuid = '<%=request.getAttribute("host_uuid") %>';

$(function(){
		$("[name='addInterface']").click(function(){
			$.dialog({
    			id:'addInterface',
    			title:'添加虚拟接口',
    			width: '380px',
    			height: '200px',
    			content: 'url:xen_goAddInterfacePage.do?pool_uuid='+pool_uuid+"&host_uuid="+host_uuid+"&vm_uuid="+vm_uuid
    			});
		}); 
		
		$("[name='deleteInterface']").unbind().live("click",function(){
			if (confirm("该操作将永久删除所选网络接口。是否继续？")) {
				bar("deleteInterface","正在网络接口，请稍等...");
				var vifUuid = $(this).attr("vifUuid");
				var url = "xen_deleteVmVirtualNic.do?vifUuid="+vifUuid+"&poolUuid="+pool_uuid;
				$.getJSON(url,{"time":new Date().toString()},function(data){
	 	    		if(data.responseCode == 1){
	 	    			$("."+vifUuid).remove();
	 	    			barEnd("deleteInterface","删除网络接口成功！");
	 	   		 	}else if(data.responseCode == -1){
	 		    		barEnd("deleteInterface","删除网络接口失败！");
	 		    	}
	 		  	});
			}
			
		}); 
		
		$("[name='adjustInterface']").unbind().live("click",function(){
			var networkUuid = $(this).attr("networkUuid");
			var mac = $(this).attr("mac");
			var vifUuid = $(this).attr("vifUuid");
			$.dialog({
    			id:'adjustInterface',
    			title:'调整虚拟接口',
    			width: '380px',
    			height: '200px',
    			content: 'url:xen_goAdjustInterfacePage.do?pool_uuid='+pool_uuid+"&host_uuid="+host_uuid+"&vm_uuid="+vm_uuid + "&networkUuid=" + networkUuid + "&mac=" + mac +"&vifUuid=" +vifUuid
    			});
		});
		
   });


function addXenVirtualNic(url){
	bar("addaddXenVirtualNic","正在添加网络接口，请稍等...");
	$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == 1){
					var table = $(".blue-table");
					var $_tr = $("<tr></tr>");
					$_tr.attr("class",data.vifUuid);
					var $_td = $("<td></td>");
					var $_td_adjust = $("<a>调整</a>");
					$_td_adjust.attr("href","javascript:;").attr("name","adjustInterface")
								.attr("style","color: blue;margin-right: 5px").attr("vifUuid",data.vifUuid)
								.attr("networkUuid",data.networkUuid).attr("mac",data.mac);
					var $_td_delete = $("<a>删除</a>");
					$_td_delete.attr("href","javascript:;").attr("name","deleteInterface")
								.attr("style","color: blue;margin-right: 5px").attr("vifUuid",data.vifUuid);
					$_td.append($_td_adjust).append($_td_delete);
					$_tr.append("<td>"+data.networkName+"</td>").append("<td>"+data.mac+"</td>")
					.append("<td>-</td>").append("<td>"+data.ip+"</td>").append("<td>"+data.currentlyAttached+"</td>")
					.append($_td);
					table.prepend($_tr);
					barEnd("addaddXenVirtualNic","添加网络接口成功！");
				}else{
					barEnd("addaddXenVirtualNic","添加网络接口失败！");
				}
			});
}

function adjustXenVirtualNic(url,vifUuid){
	bar("adjustVirtualNic","正在调整网络接口，请稍等...");
	$.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == 1){
					var $_tr = $("<tr></tr>");
					$_tr.attr("class",data.vifUuid);
					var $_td = $("<td></td>");
					var $_td_adjust = $("<a>调整</a>");
					$_td_adjust.attr("href","javascript:;").attr("name","adjustInterface")
								.attr("style","color: blue;margin-right: 5px").attr("vifUuid",data.vifUuid)
								.attr("networkUuid",data.networkUuid).attr("mac",data.mac);
					var $_td_delete = $("<a>删除</a>");
					$_td_delete.attr("href","javascript:;").attr("name","deleteInterface")
								.attr("style","color: blue;margin-right: 5px").attr("vifUuid",data.vifUuid);
					$_td.append($_td_adjust).append($_td_delete);
					$_tr.append("<td>"+data.networkName+"</td>").append("<td>"+data.mac+"</td>")
					.append("<td>-</td>").append("<td>"+data.ip+"</td>").append("<td>"+data.currentlyAttached+"</td>")
					.append($_td);
					$("."+vifUuid).replaceWith($_tr);
					barEnd("adjustVirtualNic","调整网络接口成功！");
				}else{
					barEnd("adjustVirtualNic","调整网络接口失败！");
				}
			});
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
	<div class="blue-wrap noborder" style="overflow: auto;width: 800px;height: ">
		<div class="table-head">
			 	 <ul class="btns" style="height: 25px;margin-top: 0px" >
					<li><input type="button" class="thickbox btn-style02" value="添加" name="addInterface"/></li>
				</ul>
		</div>
			<table width="100%" border="0" cellspacing="0" class="blue-table" id="theTable" >
				<thead>
					<tr>
					<!--  
						<th>设备</th>
					-->
						<th onclick="sort(theTable,0,'string')">网络</th>
						<th onclick="sort(theTable,1,'string')">MAC</th>
						<th onclick="sort(theTable,2,'string')">限制</th>
						<th onclick="sort(theTable,3,'string')">IP地址</th>
						<th onclick="sort(theTable,4,'string')">活动</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="theBean" value="theForm.resultList">
						<tr class="<s:property value="#theBean.vifUuid"/>">
						<!-- 
							<td>
							<s:property value="#theBean.device"/>
							</td>
						 -->
							 <td>
	                        <s:property value="#theBean.networkName"/>
                            </td>
							<td>
							<s:property value="#theBean.mac"/>
                            </td>
                            <td>
                            -
                            </td>
							
							<td>
							<s:if test="#theBean.ip==null">
							-
							</s:if>
							<s:else>
							<s:property value="#theBean.ip"/>
							</s:else>
							</td>
							<td>
							<s:if test="#theBean.currentlyAttached==true">
							激活
							</s:if>
							<s:else>
							未激活
							</s:else>
                            </td>
                            <td>
	                            <a href="javascript:;" name="adjustInterface"  style="color: blue;margin-right: 5px" mac="<s:property value="#theBean.mac"/>" networkUuid="<s:property value="#theBean.networkUuid"/>"  vifUuid="<s:property value="#theBean.vifUuid"/>" >调整</a>
	                            <a href="javascript:;" name="deleteInterface"  style="color: blue;margin-right: 5px"  vifUuid="<s:property value="#theBean.vifUuid"/>" >删除</a>
                            </td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
	</div>
</s:form>
</body>
