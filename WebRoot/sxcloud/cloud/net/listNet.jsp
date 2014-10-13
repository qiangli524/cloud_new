<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.QUERY_NAME.value = '';
		theForm.QUERY_NET_TYPE.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'net_addNetInfo.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.NET_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能修改一条信息");
 	    return false ;
 	    }
	    theForm.action = 'net_modNetInfo.do' 
		theForm.submit();
 	    
 	}
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.NET_ID.value = checkboxids[i].value;
 	      
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除一条信息");
 	    return false ;
 	    }
 	    //判断网络里的ip是否使用，使用中不能删除
 	    url = "net_adjustNetUseful.do?NET_ID="+theForm.NET_ID.value;
		$.getJSON(url,{"time":new Date().toString()},function(data){
			if(data.useing == 'no'){
				if(confirm("确定要删除?")){
					 theForm.action = 'net_delNetInfo.do'  
					 theForm.submit();
				}
			}else if(data.useing == 'yes'){
				alert("ip正在使用，不能删除该网络!");
			}
		});
 	}
 	
 	function showListIp(netid){
		$.dialog({
			id:'showListIp',
			title:'IP清单',
			resize:false,
			width: '1000px',
			height: '563px',
			lock : true,
			content: 'url:ip_listIpInfo.do?netId='+netid
		});
	}
</script>
</head>
<body class="scrollbody">
	<div class="mainbody">
		<s:form action="net_listNetInfo.do" method="post" cssStyle="theForm"
			id="theForm">
			<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>
			<%
					if (!((String) request.getAttribute("oper")).equals("1")) {
		%>
			<div class="pd-20 bgcolor-1">
				<h2 class="utt-1">网络管理</h2>
				<div class="bord-1 pd-10">
					<div class="clearfix mgt-10">
						<label class="vm">网络名称：</label>
						<s:textfield name="theForm.QUERY_NAME" cssClass="inpt-1 vm"
							id="QUERY_NAME" maxlength="30"></s:textfield>
						<span class="ubtn-1 mgl-20"><input type="button"
							onclick="javascript:searchRequest()" value="查询" />
						</span> <span class="ubtn-2 mgl-20"><input type="button"
							onclick="javascript:resetForm(document.getElementById('theForm'))"
							value="重置" />
						</span>
					</div>
					<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)"
							onclick="addRequest();return false;">新增</a> <a
							class="icon-modify" href="javascript:void(0)"
							onclick="modRequest();return false;">修改</a> <a class="icon-del"
							href="javascript:void(0)" onclick="delRequest();return false;">删除</a>
					</div>
					<%} %>
					<table id="theTable" width="100%" class="blue-table sorttable"
						border="0" cellspacing="0">
						<thead>
							<tr>
								<s:if test="#request.oper==0">
									<th>选择</th>
								</s:if>
								<th onclick="sort(theTable,1,'string')">网络名称</th>
								<th onclick="sort(theTable,2,'string')">网络类型</th>
								<th onclick="sort(theTable,3,'string')">安全域</th>
								<th onclick="sort(theTable,4,'string')">VLAN</th>
								<th onclick="sort(theTable,5,'string')">可用IP</th>
								<th onclick="sort(theTable,6,'string')">已分配IP</th>
								<th>查看IP地址</th>
								<th onclick="sort(theTable,3,'string')">网络描述</th>
								<!--  
									<th onclick="sort(theTable,5,'string')">
										是否缺省网络
									</th>
									<th onclick="sort(theTable,6,'string')">
										网络类型
									</th>
									
									
									<!--  
                   <th>连接地址:</th>
                   <th>重复加载模式:</th>
                   -->
							</tr>
						</thead>
						<tbody>
							<s:iterator value="theForm.resultList" id="theBean">
								<tr>
									<s:if test="#request.oper==0">
										<td><input name="checkboxid" type="checkbox"
											value="<s:text name="#theBean.NET_ID"/>" /></td>
									</s:if>
									<td><s:text name="#theBean.NAME" /></td>
									<td><s:if test="#theBean.NET_TYPE == 7">
												NAS网
											</s:if> <s:elseif test="#theBean.NET_TYPE == 6">
												管理网
											</s:elseif> <s:else>
												业务网
											</s:else></td>
									<td><s:property value="#theBean.DOMAIN" />
									</td>
									<td><s:property value="#theBean.VLAN_ID" />
									</td>
									<td><s:text name="#theBean.FREECOUNT" />个</td>
									<td><s:text name="#theBean.USEDCOUNT" />个</td>
									<td><a
										href="javascript:showListIp('<s:property value="#theBean.NET_ID"/>')">查看IP地址</a>
									</td>
									<td><s:if test="#theBean.DESCRIPTION==null">
											无
											</s:if> <s:if test="#theBean.DESCRIPTION!=null">
											<s:text name="#theBean.DESCRIPTION" />
										</s:if></td>
									<!-- 
										<td>
											<s:if test="#theBean.ISDEFAULT==0">
								缺省网络
								</s:if>
											<s:elseif test="#theBean.ISDEFAULT==1">
								非缺省网络
								</s:elseif>
										</td>
										<td>
											<s:if test="#theBean.NET_TYPE==1">
								Vmware
								</s:if>
											<s:elseif test="#theBean.NET_TYPE==2">
								Xen
								</s:elseif>
										</td>
								 -->
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
</html:html>
