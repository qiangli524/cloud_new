<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.STRATEGYNAME.value = '';
		theForm.HOSTNAME.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	
 	 	function addRequest() {
 	    theForm.action = 'strategy_addUpgradeStrategy.do' 
		theForm.submit();
 	}
 	
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改的升级策略！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条升级策略！");
 	    return false ;
 	    }
 	    theForm.action = 'strategy_modUpgradeStrategy.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除升级策略！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条升级策略");
 	    return false ;
 	    }
 	    theForm.action = 'strategy_delUpgradeStrategy.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
	<s:form action="strategy_upgradeStrategyList.do" method="post" id="theForm">
		<s:hidden name="theForm.ID" id="ID" />
		<div class="scrollbody">
			<div class="query">
				<div class="title">
					<%=getImageTag(request, "query-icon.gif")%>
				</div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>

							<td class="til">
								升级策略名称
							</td>
							<td>
								<s:textfield name="theForm.STRATEGYNAME" id="STRATEGYNAME" />
							</td>
							<td class="til">
								主机名称
							</td>
							<td>
								<s:textfield name="theForm.HOSTNAME" id="HOSTNAME"/>
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
				<div class="blue-wrap noborder">
					<div class="table-head">
						<ul class="btns">
							<li>
								<input type="button" class="thickbox btn-style02" value="增加"
									onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;" />
							</li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										升级策略名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										升级类型
									</th>
									<th onclick="sort(theTable,3,'string')">
										基准主机名称
									</th>
									<th onclick="sort(theTable,4,'date')">
										更新时间
									</th>
								</tr>
							</thead>
							<tbody>
									<s:iterator id="theBean" value="theForm.resultList">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value='<s:property value="#theBean.ID"/>'/>
											</td>
											<td>
												<s:text name="#theBean.STRATEGYNAME"/>
											</td>
											<td>
												<s:if test="#theBean.TYPE==2" >基准部署</s:if>
											</td>
											<td>
												<s:text name="#theBean.HOSTNAME" />
											</td>
											<td>
											<s:if test="#theBean.UPDATETIME==null">无</s:if>
											<s:else>
												<s:text name="#theBean.UPDATETIME"/>
											</s:else>
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
</html:html>
