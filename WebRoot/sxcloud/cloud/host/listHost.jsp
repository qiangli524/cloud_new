<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%> 
<%@ include file="../../common/view.jsp"%>

<html locale="true">
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" /> 
<link href="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/njs/changeColor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
  
   function searchRequest() {
   		var hostip=document.theForm.eq_ip.value; 
   		if(!isnumber(hostip)){
   			alert("管理IP输入不合法,只能为.和数字!");
   			return false;
   		}
   		theForm.action="resource_listHost.do";
		theForm.submit();
 	}
 	
 	function listhost() {  
		theForm.submit();
 	}
 	function savehost(url){
		 $.ajax({
			type:"POST",
           url:url,
           async: true,
           cache: false,
	        success: function(msg){
	          	listhost();
	        }
		});
	}
 	/* 检测输入的字符串是否符合要求 */
 	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	        {
	            if (number_chars.indexOf(str.charAt(i))==-1){
	            	
	            	document.theForm.eq_ip.value='';
	            	document.theForm.eq_ip.focus;
	            	return false;
	            }
	        }
	        return true;
	 }
	 $(function(){
	 	$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
      	 $("input[name='reset']").click(function(){
            $("[name='theForm.eq_id']").val("");
            $("#eq_ip").val("");
            $("#eq_type").get(0).selectedIndex=0;
            $("#hasvertual").get(0).selectedIndex=0;
          });
          
          $("a[name='addhost']").click(function(){
	 			$.dialog({
	 				id:'addhost',
	 				title:'添加主机',
	 				width: '800px',
	 				height: '400px',
	 				lock:true,
	 				content: 'url:resource_addHost.do?flag=listhost'
	 	 		}); 
 		   }); 
 		   
 		   $("a[name='modhost']").click(function(){ 
 		   		var eid= $(this).attr("eid"); 
	 			$.dialog({
	 				id:'modhost',
	 				title:'修改主机',
	 				width: '800px',
	 				height: '400px',
	 				lock:true,
	 				content: 'url:resource_modHost.do?eq_id=' +eid +"&flag=listhost" 
	 	 		}); 
 		   });
 		   $("a[name='listHostConfig']").click(function(){ 
 		   		var eid= $(this).attr("eid"); 
	 			$.dialog({
	 				id:'listHostConfig',
	 				title:'主机用户管理',
	 				width: '800px',
	 				height: '400px',
	 				lock:true,
	 				content: 'url:resource_listHostConfig.do?eq_id=' +eid
	 	 		}); 
 		   });
 		   $("a[name='delHost']").click(function(){ 
 		   		 var eid= $(this).attr("eid");  
			     if(confirm("确定要删除该主机吗！") == true)
			     { 
			     	var url = 'resource_checkHostCanDel.do?hostid='+eid;
			     	$.getJSON(url,function(data){
			     		if(data=="NO"){
			     			alert("请先删除该主机的配置信息");
			     			return false;
			     		}else if(data=="YES"){
			     			theForm.action = "resource_delHost.do?eq_id=" + eid+"&flag=listhost"; 
				    		theForm.submit();
			     		}
			     	})
			     }   
 		   });
      });
</script>
	</head>
	<body onLoad="self.focus();document.theForm.eq_id.focus()">
		<div class="scrollbody">
			<s:form action="resource_listHost.do" method="post" id="theForm">
				<%
				 	String FUNID = request.getParameter("FUNID"); 
				 	if (!"report".equals(FUNID)) {
				 %>
				<div class="gray-area">
					<table width="100%" class="" border="0">
						<tr>
							<td>
								<a href="javascript:;" name="addhost">增加</a>
							</td>
							<td>
								主机编号:
								<s:textfield name="theForm.eq_id" id="eq_id"/>
							</td>
							<td>
								管理IP:
								<s:textfield name="theForm.eq_ip"  id="eq_ip"/>
							</td>
							<td>
								主机类型:
								<s:select name="theForm.eq_type" id="eq_type" headerKey="" headerValue="请选择" list="#{'1':'IBM小机','2':'IBM刀片','3':'IBM普通刀片','4':'HPx86刀片'}">
								</s:select>
							</td> 
							<td>
								虚拟化类型:
								<s:select name="theForm.hasvertual" id="hasvertual" headerKey="" headerValue="请选择" list="#{'3':'XEN','4':'VMWARE','1':'PowerVM','2':'KVM','0':'非虚拟化'}">
								</s:select>
							</td>
							<td>
								<input type="button" class="thickbox btn-style02" value="查询"
									onclick="javascript:searchRequest()" />
								<input type="button" class="thickbox btn-style02" value="重置" name="reset"/>
							</td>
						</tr>
					</table>
				</div>
				<%
					}
				%>
				<!--主机列表 start -->

				<table>
					<tr>
						<td>
							<div>
								<div class="tit-zzi">
									IBM小机
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.hostList1">

											<li>
												<h1>

													<span class="tool_bar"> 
													<%
													 	if ("report".equals(FUNID)) {
													 %> 
													 <s:url var="theBean.ID" action="getHostCurrentReportView">
															<s:param name="eq_id" value="#theBean.eq_id"></s:param>
													 </s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="查看报表" width="10" height="10" /></s:a>
													 <%
														 	} else {
													 %>  
													 	<a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="listHostConfig"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif"/></a>
													    <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="delHost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif"/></a>
														<a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="modhost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif"/></a>
														<%
														 	}
														 %> </span> IBM小机(
													<s:text name="#theBean.eq_name"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="sxcloud/cjs/ui2/nresources/common/images/host-icon-1.gif"
															width="30" height="80" />
													</div>
													<div class="right-c">
														<p>
															主机编号:
															<s:text name="#theBean.eq_id"  />
														</p>
														<p>
															主机IP：
															<s:if test="#theBean.eq_ip!=null">
															 	<s:text name="#theBean.eq_ip" />
															</s:if>
															
														</p>
														<p>
															虚拟化类型:
															<s:if test="#theBean.hasvertual == 3">XEN</s:if>
															<s:elseif test="#theBean.hasvertual==4">VMWARE</s:elseif>
															<s:elseif test="#theBean.hasvertual==1">PowerVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==2">KVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==0">非虚拟化</s:elseif>
														</p>
														<p>
															注册时间:
															<s:if test="#theBean.ins_date!=null"><s:text name="#theBean.ins_date" /></s:if>
															
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div class="tit-zzi">
									IBM刀片
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.hostList2">
											<li>
												<h1>
													<span class="tool_bar"> 
													<%
													 	if ("report".equals(FUNID)) {
													 %> 
													 <s:url var="theBean.ID" action="getHostCurrentReportView">
															<s:param name="eq_id" value="#theBean.eq_id"></s:param>
													 </s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="查看报表" width="10" height="10" /></s:a>
													 <%
														 	} else {
													 %> 
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="listHostConfig"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="delHost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="modhost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif"/></a>
													 <%
														 	}
														 %> </span> IBM刀片(
													<s:text name="#theBean.eq_name"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="sxcloud/cjs/ui2/nresources/common/images/host-icon-2.gif"
															width="30" height="80" />
													</div>
													<div class="right-c">
														<p>
															主机编号:
															<s:text name="#theBean.eq_id"  />
														</p>
														<p>
															主机IP：
															<s:text name="#theBean.eq_ip" />
														</p>
														<p>
															虚拟化类型:
															<s:if test="#theBean.hasvertual == 3">XEN</s:if>
															<s:elseif test="#theBean.hasvertual==4">VMWARE</s:elseif>
															<s:elseif test="#theBean.hasvertual==1">PowerVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==2">KVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==0">非虚拟化</s:elseif>
														</p>
														<p>
															注册时间:
															<s:if test="#theBean.ins_date!=null"><s:text name="#theBean.ins_date" /></s:if>
															
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div class="tit-zzi">
									IBM普通刀片
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.hostList3">
											<li>
												<h1>
													<span class="tool_bar"> 
													<%
													 	if ("report".equals(FUNID)) {
													 %> 
													 <s:url var="theBean.ID" action="getHostCurrentReportView">
															<s:param name="eq_id" value="#theBean.eq_id"></s:param>
													 </s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="查看报表" width="10" height="10" /></s:a>
													 <%
														 	} else {
													 %> 
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="listHostConfig"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="delHost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="modhost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif"/></a>
													<%
														 	}
														 %> </span> IBM普通刀片(
													<s:text name="#theBean.eq_name"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="sxcloud/cjs/ui2/nresources/common/images/host-icon-1.gif"
															width="30" height="80" />
													</div>
													<div class="right-c">
														<p>
															主机编号:
															<s:text name="#theBean.eq_id"  />
														</p>
														<p>
															主机IP：
															<s:text name="#theBean.eq_ip" />
														</p>
														<p>
															虚拟化类型:
															<s:if test="#theBean.hasvertual == 3">XEN</s:if>
															<s:elseif test="#theBean.hasvertual==4">VMWARE</s:elseif>
															<s:elseif test="#theBean.hasvertual==1">PowerVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==2">KVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==0">非虚拟化</s:elseif>
														</p>
														<p>
															注册时间:
															<s:if test="#theBean.ins_date!=null"><s:text name="#theBean.ins_date" /></s:if>
															
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div class="tit-zzi">
									HPx86刀片
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.hostList4">
											<li>
												<h1>
													<span class="tool_bar"> 
													<%
													 	if ("report".equals(FUNID)) {
													 %> 
													 <s:url var="theBean.ID" action="getHostCurrentReportView">
															<s:param name="eq_id" value="#theBean.eq_id"></s:param>
													 </s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="查看报表" width="10" height="10" /></s:a>
													 <%
														 	} else {
													 %> 
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="listHostConfig"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="delHost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="modhost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif"/></a>
													<%
														 	}
														 %> </span> HPx86刀片(
													<s:text name="#theBean.eq_name"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="sxcloud/cjs/ui2/nresources/common/images/host-icon-1.gif"
															width="30" height="80" />
													</div>
													<div class="right-c">
														<p>
															主机编号:
															<s:text name="#theBean.eq_id"  />
														</p>
														<p>
															主机类型：
															<s:text name="#theBean.eq_type"  />
														</p>
														<p>
															虚拟化类型:
															<s:if test="#theBean.hasvertual == 3">XEN</s:if>
															<s:elseif test="#theBean.hasvertual==4">VMWARE</s:elseif>
															<s:elseif test="#theBean.hasvertual==1">PowerVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==2">KVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==0">非虚拟化</s:elseif>
														</p>
														<p>
															注册时间:
															<s:if test="#theBean.ins_date!=null"><s:text name="#theBean.ins_date" /></s:if>
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div class="tit-zzi">
									机架服务器
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.hostList5">
											<li>
												<h1>
													<span class="tool_bar"> 
													<%
													 	if ("report".equals(FUNID)) {
													 %> 
													 <s:url var="theBean.ID" action="getHostCurrentReportView">
															<s:param name="eq_id" value="#theBean.eq_id"></s:param>
													 </s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="查看报表" width="10" height="10" /></s:a>
													 <%
														 	} else {
													 %> 
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="listHostConfig"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="delHost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif"/></a>
													 <a eid='<s:property value="#theBean.eq_id" />' href="javascript:;" name="modhost"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif"/></a>
													<%
														 	}
														 %> </span> 机架服务器(
													<s:text name="#theBean.eq_name"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="sxcloud/cjs/ui2/nresources/common/images/host-icon-1.gif"
															width="30" height="80" />
													</div>
													<div class="right-c">
														<p>
															主机编号:
															<s:text name="#theBean.eq_id"  />
														</p>
														<p>
															主机类型：
															<s:text name="#theBean.eq_type"  />
														</p>
														<p>
															虚拟化类型:
															<s:if test="#theBean.hasvertual == 3">XEN</s:if>
															<s:elseif test="#theBean.hasvertual==4">VMWARE</s:elseif>
															<s:elseif test="#theBean.hasvertual==1">PowerVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==2">KVM</s:elseif>
															<s:elseif test="#theBean.hasvertual==0">非虚拟化</s:elseif>
														</p>
														<p>
															注册时间:
															<s:if test="#theBean.ins_date!=null"><s:text name="#theBean.ins_date" /></s:if>
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						
						</td>
					</tr>
				</table>
				<div class="clear"></div>
			</s:form>
	</body>
</html>

