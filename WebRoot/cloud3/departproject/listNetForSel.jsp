<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%> 
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
		var api = frameElement.api;
     	var w = api.opener; 
     	
		api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:selectNetDomain,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });

	 function resetForm(netObj){
		netObj.NAME.value = '';
		netObj.NET_TYPE.value = '';
	 }

    function searchRequest() { 
		netObj.submit();
 	} 
 	
 	function selectNetDomain() {
 
 		var count = 0;
 		var checkboxids = $("[name='checkboxid']");
		if(checkboxids!=null&&checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					count++;
				}
			}
		}
		if(count<1){
			alert("请选择网络域！");
			return false;
		}else if(count>1){
			alert("一次只能选择一个网络域！");
			return false;
		} 	 
		var netId = $("[name='checkboxid']:checked").attr("value");
		var domainName = $("[name='checkboxid']:checked").attr("domainname");
 		var id =  '<s:property value="obj.ID"/>';
 		if(id == null || id =='') {
			api.get("add").getNetDomain(netId,domainName);
		} else {
			api.get("mod").getNetDomain(netId,domainName);
		}
 	}
</script>
</head>
<body onLoad="self.focus();document.netObj.NAME.focus()" class="pop-body scrollbody">
	<s:form action="departproject_queryNetDomainList.do" method="post" cssStyle="netObj"
		id="netObj" name="netObj">
		<s:hidden name="netObj.NET_ID" id="NET_ID"></s:hidden> 
		<s:hidden name="netObj.flag" id="flag"></s:hidden> 
		<s:hidden name="obj.ID"></s:hidden>
		<div>
				<%-- <div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								网络名称:
							</td>
							<td>
								<s:textfield name="netObj.NAME" id="NAME" cssStyle="txt"></s:textfield>
							</td> --%>
							<%-- <td class="til">
								网络类型:
							</td>
							<td>
								<s:select list="#{'':'-请选择-','2':'KVM','1':'IBM'}"
									name="netObj.NET_TYPE" id="NET_TYPE"></s:select>
							</td> --%>
						<%-- </tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('netObj'))" />
								</div>
							</td>
						</tr>
					</table>
				</div> --%>
				
				<div class="clearfix mgt-10">
					<label class="vm">网络名称:</label>		
					<s:textfield  name="netObj.NAME" id="NAME" cssClass="txt"></s:textfield>
					<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('netObj'))" value="重置" /></span>	
				</div>
				<!--query-form end -->
				<div class="blue-wrap noborder"  style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;"> 
					<%-- <div class="table-head">
						 <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=netObj" />
					</div>
					--%>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th> 
									<th onclick="sort(theTable,1,'string')">
										网络名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										网络描述
									</th>
									<th onclick="sort(theTable,3,'string')" >
										可用IP地址
									</th>
									<th onclick="sort(theTable,4,'string')">
										已分配IP地址
									</th>
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
								<s:iterator value="resultList" id="theBean">
									<tr> 
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:text name="#theBean.NET_ID"/>" domainname='<s:property value="#theBean.NAME"/>'/>
										</td>
										<td>
											<s:text name="#theBean.NAME" />
										</td>
										<td>
											<s:if test="#theBean.DESCRIPTION==null">
							无
							</s:if>
											<s:if test="#theBean.DESCRIPTION!=null">
												<s:text name="#theBean.DESCRIPTION" />
											</s:if>
										</td>
										<td>
											<s:text name="#theBean.FREECOUNT" />
										</td>
										<td>
											<s:text name="#theBean.USEDCOUNT" />
										</td>
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
								IBM
								</s:if>
											<s:elseif test="#theBean.NET_TYPE==2">
								KVM
								</s:elseif>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=netObj" />
						</div>
					</div>
				</div>
			</div>
	</s:form>
</body>
</html:html>
