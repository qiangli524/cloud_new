<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">

	function resetForm(theForm){
		//theForm.IP_ID.value = '';
		theForm.QUERYIPADDRESS.value = '';
		theForm.queryNetId.value = '';
		$("#ISUSED").attr("value","-1");
		$("#ISBLOCKED").attr("value","-1");
	}

   function searchRequest() {
   		theForm.action="ip_listIpInfo.do"; 
		theForm.submit();
 	}
 	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'ip_addIpInfo.do?netId=<%=request.getAttribute("net_id")%>'
 	    
 	    
<%-- 	    theForm.NET_ID.value='listIpInfo.do?netId';--%>
<%-- 	    alert(theForm.NET_ID.value);--%>
	   theForm.submit();
 	}
 	function modRequest() {
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IPADDRESS.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    if(confirm('确认要修改'+theForm.IPADDRESS.value+'吗？')) { 
	 	    theForm.action = 'ip_modIpInfo.do' 
			theForm.submit();
		}
 	}
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	   		 for(var i=0;i<checkboxids.length;i++){
 	      			if(checkboxids[i].checked){
 	      				couterNum = couterNum + 1 ;
 	      			 	theForm.IPADDRESS.value = $(checkboxids[i]).attr("inetaddress");
 	      			}
 	    	 }
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选需要删除IP信息！");
 	    	return false ;
 	    }else if(couterNum>1){
 	    	var address='';
 	    	for(var j=0;j<checkboxids.length;j++){
 	    		if(checkboxids[j].checked){
 	    			 var ip = $(checkboxids[j]).attr("inetaddress");
 	    			 address =ip+','+address;
 	    		}
 	    	}
 	    	if(confirm('确认要删除'+address+'吗？')) { 
 	    		theForm.action = 'ip_delIpInfo.do?ip='+address;
 	 	    	theForm.submit();
 			}
 	    }else{
 	    	if(confirm('确认要删除'+theForm.IPADDRESS.value+'吗？')) { 
 	    		theForm.action = 'ip_delIpInfo.do?ip='+theForm.IPADDRESS.value;
 	 	    	theForm.submit();
 			}
 	    }
 	}
 	
	 function checkIp(){
	 		var address = "";
	 		if ($(":checkbox:checked").length == 0) {
				alert("请至少选择一个ip地址来检测!");
				return false;
			}
	 		if(confirm("确定要检测吗?")==true){
	 			$(":checkbox:checked").each(function(){
		 			address += $(this).attr("inetaddress") + ",";
		 		});
		 		mask('正在检测ip状态,请稍后....','0.7','0px');
		 		$.ajax({
		 			data:'ip='+address,
		 			type:'post',
		 			dataType:'json',
		 			url:'ip_checkStatus.do',
		 			success:function(rs){
		 				removeMask();
		 				//未使用
		 		 		var countun = 0;
		 		 		//使用
		 				var countuy = 0;
		 		 		//未阻塞
		 				var countbn = 0;
		 		 		//阻塞
		 				var countby = 0;
		 		 		
		 				for ( var i = 0; i < rs.length; i++) {
		 					if (rs[i].isused == 0) {
								countun += 1;
							} else if (rs[i].isused == 1) {
								countuy += 1;
							}
			 				if (rs[i].isblock == 0) {
								countbn += 1;
							} else if (rs[i].isblock == 1) {
								countby += 1;
							}
						}
		 				alert("检测完成,占用： "+countuy+"个,未占用： "+countun+"个,阻塞： "+countby+"个,未阻塞： "+countbn+"个!");
		 				$("#theForm").submit();
		 			}
		 		});
	 		}
	 	}
 	
 	function backupRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      	couterNum = couterNum + 1 ;
 	     	theForm.IPADDRESS.value = checkboxids[i].getAttribute("inetaddress");
 	     //theForm.IPADDRESS.value = checkboxids[i].value;
 	     // theForm.NET_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要占用的IP地址！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    var ip = theForm.IPADDRESS.value;
 	    if(confirm('确认要占用'+theForm.IPADDRESS.value+'吗？')) { 
	 	    theForm.action = 'ip_backupIpInfo.do?ip='+ip 
			theForm.submit();
		}
 	}
 	function issuanceRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      //theForm.IPADDRESS.value = checkboxids[i].value;
 	      theForm.IPADDRESS.value = checkboxids[i].getAttribute("inetaddress"); 
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要释放的IP地址！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    var ip = theForm.IPADDRESS.value;
 	    if(confirm('确认要释放'+theForm.IPADDRESS.value+'吗？')) { 
	 	    theForm.action = 'ip_issuanceIpInfo.do?ip='+ip  
			theForm.submit();
		}
 	}
 	function goback(){
		theForm.action = 'net_listNetInfo.do' 
		theForm.submit();
	}
 	
 	/*
 	$("#hostipaddress").unbind().live("click",function(){
    	var ip=$(this).parents("td").attr("ipaddress");
    	$.dialog({
			id:'showHost',
			title:'部署机信息查看',
			width: '900px',
			height: '500px',
			max: true,
		    min: true,
			content: 'url:ip_showMachineInfo.do?ipaddress='+ip
    	});
    	return false;
     });
 	*/
 	
 	$("[name='hostipaddress']").unbind().live("click",function(){
 		var ip = $(this).attr("ipaddress");
 		$.dialog({
			id:'showHost',
			title:'信息查看',
			width: '900px',
			height: '500px',
			max: true,
		    min: true,
		    lock:true,
			content: 'url:ip_showMachineInfo.do?ipaddress='+ip
    	});
 	});
 	
 	
 	<%-- 排序IP --%>
 	function sortIP(tableId, sortColumn, nodeType) {
 		var table = document.getElementById("theTable");
 		var tableBody = table.tBodies[0];
 		var tableRows = tableBody.rows;
 		var rowArray = new Array();
 		for (var i = 0; i < tableRows.length; i++) {
 			var reg = /^<a/;
 			if(reg.test(tableRows[i].cells[sortColumn].innerHTML.trim())){
 				$(tableRows[i]).attr("change","true");
 				tableRows[i].cells[sortColumn].innerHTML=tableRows[i].cells[sortColumn].innerHTML.trim().split("<")[1].split(">")[1];
 			}else{
 				$(tableRows[i]).attr("change","false");
 				tableRows[i].cells[sortColumn].innerHTML=tableRows[i].cells[sortColumn].innerHTML.trim();
 			}
 			rowArray[i] = tableRows[i];
 		}
 		if (table.sortColumn == sortColumn) {
 			rowArray.reverse();
 		} else {
 			rowArray.sort(generateCompareTR(sortColumn, nodeType));
 		}
 		for (var i = 0; i < rowArray.length; i++) {
 			if($(rowArray[i]).attr("change")=="true"){
 				rowArray[i].cells[sortColumn].innerHTML= "<a href='javascript:;' name='hostipaddress' ipaddress='"+rowArray[i].cells[sortColumn].innerHTML+"'>"+rowArray[i].cells[sortColumn].innerHTML+"</a>";
 			}
 			$(rowArray[i]).removeAttr("change");
 		}
 		var tbodyFragment = document.createDocumentFragment();
 		for (var i = 0; i < rowArray.length; i++) {
 			tbodyFragment.appendChild(rowArray[i]);
 		}
 		tableBody.appendChild(tbodyFragment);
 		table.sortColumn = sortColumn;
 	}
 	 	
 	$(function(){	
 		$("#checkboxAllid").click(function(){
 		 if($(this).get(0).checked==true) {
 			$(":checkbox").attr("checked",true);
 		 }else{
 			$(":checkbox").attr("checked",false);
 		 }
 		});
 	});
</script>
</head>
<body class="scrollbody">
	<div class="mainbody">
		<s:form action="ip_listIpInfo.do" method="post" cssStyle="theForm"
			id="theForm">
			<s:hidden name="theForm.flag" id="flag"></s:hidden>
			<s:hidden name="theForm.IP_ID" id="IP_ID"></s:hidden>
			<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>
			<s:hidden name="theForm.IPADDRESS" id="IPADDRESS"></s:hidden>

			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">IP管理</h2>
				<div class="bord-1 pd-10">
					<div class="clearfix filtrate-area">
						<div class="filtrate-field">
							<label class="fl">IP地址：</label>
							<s:textfield name="theForm.QUERYIPADDRESS" cssClass="inpt-1 fl"
								id="QUERYIPADDRESS" maxlength="30"></s:textfield>
						</div>
						<div class="filtrate-field">
							<label class="fl">所属VLAN：</label>
							<s:select cssClass="select-1 fl" list="theForm.netList"
								headerKey="" headerValue="--请选择--" listKey="NET_ID"
								listValue="NAME" name="theForm.queryNetId" id="queryNetId"></s:select>
						</div>
						<div class="filtrate-field">
							<label class="fl">是否使用：</label>
							<s:select cssClass="select-1 fl"
								list="#{'-1':'--请选择--','0':'否','1':'是'}" name="theForm.ISUSED"
								id="ISUSED"></s:select>
						</div>
						<div class="filtrate-field">
							<label class="fl">是否阻塞：</label>
							<s:select cssClass="select-1 fl"
								list="#{'-1':'--请选择--','0':'否','1':'是'}"
								name="theForm.ISBLOCKED" id="ISBLOCKED"></s:select>
						</div>
						<div class="filtrate-field">
						<span class="ubtn-1 mgl-20"><input type="button"
							onclick="javascript:searchRequest()" value="查询" /> </span> <span
							class="ubtn-2 mgl-20"><input type="button"
							onclick="javascript:resetForm(document.getElementById('theForm'))"
							value="重置" /> </span>
					</div>
				</div>
				<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)"
							onclick="addRequest();return false;">添加</a> <a class="icon-del"
							href="javascript:void(0)" onclick="delRequest();return false;">删除</a>
						<a class="icon-occupy" href="javascript:void(0)"
							onclick="backupRequest();return false;">占用</a> <a
							class="icon-release" href="javascript:void(0)"
							onclick="issuanceRequest();return false;">释放</a> <a
							class="icon-check" href="javascript:void(0)"
							onclick="checkIp();return false;" id="checkip">检测</a>
					</div>
					<table id="theTable" width="100%" class="blue-table sorttable imgTableCenter"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<th><input name="checkboxAllid" type="checkbox" id="checkboxAllid"/></th>
								<!-- <th>编号</th> -->

								<th onclick="sortIP(theTable,1,'string')">IP地址</th>
								<th onclick="sortIP(theTable,2,'string')">所属VLAN</th>
								<th onclick="sortIP(theTable,3,'string')">类型</th>
								<!--   <th>所属网络名称</th>-->
								<th onclick="sort(theTable,4,'string')">是否使用</th>
								<th onclick="sort(theTable,5,'string')">是否被阻塞</th>
								<%--                   <th>入库时间</th>--%>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>

									<td><input name="checkboxid" type="checkbox"
										inetaddress='<s:property value="#theBean.IPADDRESS"/>' /></td>
									<!-- 
										<s:if test="#theBean.ISUSED==1">
											<td>
												<a
													href="yvm_listMacInfo.do?IP=<s:text name="#theBean.IPADDRESS"/>"><s:text
														name="#theBean.IPADDRESS" /> </a>
											</td>
										</s:if>
										 
										<s:elseif test="#theBean.ISUSED==0">
										</s:elseif>
										-->
									<td ipaddress='<s:property value="#theBean.IPADDRESS"/>'
										name="hostip"><s:if test="#theBean.ISUSED==1">
											<a href="javascript:;" name="hostipaddress"
												ipaddress='<s:property value="#theBean.IPADDRESS"/>'> <s:property
													value="#theBean.IPADDRESS" /> </a>
										</s:if> <s:else>
											<s:property value="#theBean.IPADDRESS" />
										</s:else></td>
									<td><s:if test="#theBean.NAME=='' || #theBean.NAME==null">--</s:if>
										<s:else>
											<s:property value="#theBean.NAME" />
										</s:else></td>
									<td><s:if test="#theBean.IP_TYPE==1">公网IP</s:if> <s:else>内网IP</s:else>
									</td>
									<!--  <td><bean:write name="theBean" property="NAME"/></td>-->
									<td>
										<s:if test="#theBean.ISUSED==1">
											<img src="<%=request.getContextPath() %>/sxcloud/cresources/default/images/dot_1.gif"/>
											</s:if> <s:elseif test="#theBean.ISUSED==0">
													<img src="<%=request.getContextPath() %>/sxcloud/cresources/default/images/dot_0.gif"/>
											</s:elseif></td>
									<td><s:if test="#theBean.ISBLOCKED==1">
													<img src="<%=request.getContextPath() %>/sxcloud/cresources/default/images/dot_1.gif"/>
											</s:if> <s:elseif test="#theBean.ISBLOCKED==0">
													<img src="<%=request.getContextPath() %>/sxcloud/cresources/default/images/dot_0.gif"/>
											</s:elseif></td>
									<!--<td><bean:write name="theBean" property="INS_DATE"/></td>-->
								</tr>
							</s:iterator>
						</tbody>
					</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
				</div>
		</s:form>
	</div>
</body>
