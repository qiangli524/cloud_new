<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.NAME.value = '';
		theForm.NET_TYPE.value = '';
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
 	    alert("请勾选需要修改功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条功能信息");
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
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'net_delNetInfo.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
	<s:form action="net_listNetInfo.do" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="theForm.NET_ID" id="NET_ID"></s:hidden>

				<div class="blue-wrap noborder">
					<div class="table-head">
						<jsp:include page="../../inc/Pagination.jsp?formId=theForm" />
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">
										虚拟机名称
									</th>
									<th onclick="sort(theTable,1,'int')">
										内存(MB)
									</th>
									<th onclick="sort(theTable,2,'int')">
										CPU
									</th>
									<th onclick="sort(theTable,3,'int')">
										存储(MB)
									</th>
									<th onclick="sort(theTable,4,'string')">
										操作系统
									</th>
									<th onclick="sort(theTable,5,'string')">
										IP地址
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td>
											<s:if test="#theBean.VH_NAME==null">暂无</s:if>
											<s:else>
											<s:text name="#theBean.VH_NAME" />
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.VH_MEM==null">暂无</s:if>
											<s:else>
											<s:text name="#theBean.VH_MEM" />
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.VH_CPU==null">暂无</s:if>
											<s:else>
											<s:text name="#theBean.VH_CPU" />
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.VH_STORAGE==null">暂无</s:if>
											<s:else>
											<s:text name="#theBean.VH_STORAGE" />
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.VH_SYSTEM==null">暂无</s:if>
											<s:else>
											<s:text name="#theBean.VH_SYSTEM"/>
											</s:else>
										</td>
										
										<td>
										<s:if test="#theBean.VH_IP==null">暂无</s:if>
										<s:else>
											<s:text name="#theBean.VH_IP"/>
										</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
	</s:form>
</body>
