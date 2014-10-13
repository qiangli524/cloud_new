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
 	    alert("�빴ѡ��Ҫ�޸ĵ��������ԣ�");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ�ܴ������������ԣ�");
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
 	    alert("�빴ѡ��Ҫɾ���������ԣ�");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("һ��ֻ��ɾ��������������");
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
								������������
							</td>
							<td>
								<s:textfield name="theForm.STRATEGYNAME" id="STRATEGYNAME" />
							</td>
							<td class="til">
								��������
							</td>
							<td>
								<s:textfield name="theForm.HOSTNAME" id="HOSTNAME"/>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="��ѯ"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="����"
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
								<input type="button" class="thickbox btn-style02" value="����"
									onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="�޸�"
									onclick="modRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="ɾ��"
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
										ѡ��
									</th>
									<th onclick="sort(theTable,1,'string')">
										������������
									</th>
									<th onclick="sort(theTable,2,'string')">
										��������
									</th>
									<th onclick="sort(theTable,3,'string')">
										��׼��������
									</th>
									<th onclick="sort(theTable,4,'date')">
										����ʱ��
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
												<s:if test="#theBean.TYPE==2" >��׼����</s:if>
											</td>
											<td>
												<s:text name="#theBean.HOSTNAME" />
											</td>
											<td>
											<s:if test="#theBean.UPDATETIME==null">��</s:if>
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
