<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$("a#operator").click(function(){
	    var oper=$(this).attr("name");
	    var storeUuid=$(this).parent().parent().attr("store_uuid");
	    var name=$(this).parent().parent().attr("store_name");
	    operateStore(oper,storeUuid,name);
	});
	function operateStore(oper,store_uuid,name){
		var pool_uuid = '<%=request.getAttribute("pool_uuid")%>';
		var host_uuid = '<%=request.getAttribute("host_uuid")%>';
		if(oper == 4){
			alert("修复功能暂不支持!");
			return false;
		}
 	   	var url = "xen_operateStore.do?sr_uuid=" + store_uuid+"&pool_uuid="+pool_uuid+"&host_uuid="+host_uuid+"&oper="+oper+"&name="+encodeURI(encodeURI(name));
 	   	if(confirm("确定要进行当前操作吗?")==true){
 	   	    mask('正在处理....','0.5','50px');
 	   		$.getJSON(url,{"time":new Date().toString()},function(data){
 	    		if(data.responseCode == 1){
 	    			removeMask();
 	    			if(oper==1){
 	    				var $td_state = $("tr#"+store_uuid+" td:eq(4)").text("否");
 	    				var $td_state = $("tr#"+store_uuid+" td:eq(5)").text("已分离");
 	    				var $td_operate = $("tr#"+store_uuid+" td:eq(6)").empty();
 	    				var $td_a1 = $("<a>忘记</a>");
 	    				$td_operate.append($td_a1);
 	    				$("tr#"+store_uuid+" td:eq(6) a:eq(0)").attr("href","javascript:operateStore(2,"+"'"+data.srUuid+"','"+data.srName+"')");
 	    				$("tr#"+store_uuid+" td:eq(6) a:eq(0)").css("color","blue");
 	    				var $tr = $("tr#"+store_uuid);
 	    				var $tr_clone = $tr.clone(true);
 	    				$tr_clone.attr("class",store_uuid);
 	    				$tr.remove();
 	    				var $tbody = $("tbody");
 	    				$tbody.append($tr_clone);
 	    			}else{
 	    				//不明白为什么要删除两遍，但一遍删除不了
 	    				$("#"+store_uuid).remove();
 	    				$("#"+store_uuid).remove();
 	    			}
 	    			//window.location.reload();
 	   		 	}else if(data.responseCode == -1){
 		    		alert("操作失败!");
 		    		removeMask();
 		    		//window.location.reload();
 		    	}
 		  	});
 	   	}
	}
	$(function(){
		$("tbody a").css("color","blue");
	});
</script>
</head>
<body>
<s:form action="xen_listStorage.do" method="post" id="theForm">
	<s:hidden name="#theBean.store_uuid" id="store_uuid"/>
	<div class="blue-wrap noborder">
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">名称</th>
						<th onclick="sort(theTable,1,'string')">容量(GB)</th>
						<th onclick="sort(theTable,2,'string')">使用情况</th>
						<th onclick="sort(theTable,3,'string')">类型</th>
						<th onclick="sort(theTable,4,'string')">共享</th>
						<th onclick="sort(theTable,5,'string')">状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="theForm.resultList" id="theBean">
						<tr id="<s:property value="#theBean.store_uuid"/>" store_uuid="<s:property value="#theBean.store_uuid"/>" store_name="<s:property value="#theBean.name"/>">
							<td>
								<s:if test="#theBean.name=='Local storage'">本地存储</s:if>
								<s:elseif test="#theBean.name=='DVD drives'">DVD驱动器</s:elseif>
								<s:elseif test="#theBean.name=='Removable storage'">可移动存储</s:elseif>
								<s:else><s:property value="#theBean.name"/></s:else>
							</td>
							<td><span><s:property value = "#theBean.sr_size"/></span> GB</td>
							<td><s:property value="#theBean.use_per"/>%(<s:property value = "#theBean.free_size"/>GB已用)</td>
							<td ><s:property value = "#theBean.type"/></td>
							<td>
								<s:if test="#theBean.shared=='true'">是</s:if>
								<s:elseif test="#theBean.shared=='false'">否</s:elseif>
								<s:else>否</s:else>
							</td>
							<td>
								<s:if test="#theBean.state=='connect'">已连接</s:if>
								<s:if test="#theBean.state=='destroy'">已损坏</s:if>
								<s:if test="#theBean.state==2">已分离</s:if>
								<s:if test="#theBean.state==3">已忘记</s:if>
							</td>
							<td>
								<s:if test="#theBean.state==2">
									<a name="2" href="javascript:;" id="operator">忘记</a>
								</s:if>
								<s:elseif test="#theBean.state==3">
									<a name="3" href="javascript:;" id="operator">销毁</a>
								</s:elseif>
								<s:elseif test="#theBean.state=='destroy'">
								    <a name="4" href="javascript:;">修复</a>
								</s:elseif>
								<s:elseif test="#theBean.type=='iso'">
									<a name="1" href="javascript:;" id="operator">分离</a>
									<a name="2" href="javascript:;" id="operator">忘记</a>
								</s:elseif>
								<s:elseif test="#theBean.type=='udev' || #theBean.type=='lvm' || #theBean.contraoper=='Running'">
									无
								</s:elseif>
								<s:else>
									<a name="1" href="javascript:;" id="operator">分离</a>
									<a name="2" href="javascript:;" id="operator">忘记</a>
									<a name="3" href="javascript:;" id="operator">销毁</a>
								</s:else>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
	</div>
</s:form>
</body>
