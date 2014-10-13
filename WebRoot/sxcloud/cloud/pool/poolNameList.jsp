<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>


  <head>
   
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest() { 
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
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = "resource_deleteHostPool.do"; 
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
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'resourcepool_modHostPool.do';
		theForm.submit();
 	}
 	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'resourcepool_addHostPool.do';
	  	theForm.submit();
 	}
 	
	</script>

  </head>
  
  <body>
  
  <s:form action="resourcepool_allHostPoolList.do" id="theForm" method="post"
		cssClass="theForm">
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
			
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
									服务器名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										主机IP
									</th>
									<th onclick="sort(theTable,3,'string')">
										主机名称
									</th>
									<th onclick="sort(theTable,4,'string')">
										服务IP
									</th>
									<th onclick="sort(theTable,5,'date')">
										创建时间
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.LIST_NAME" id="theBean">
									<tr >
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value='#theBean.EQ_ID'/>"/>
										</td>
										<td>
											<s:property value="#theBean.EQ_NAME" />
										</td>
										<td>
										<s:property value="#theBean.EQ_IP" />
										</td>
										<td>
											<s:property value="#theBean.EQ_HOSTNAME" />
										</td>
										<td>
											<s:property value="#theBean.VLANIP" />
										</td>
										<td>
											<s:property value="#theBean.INS_DATE" />
										</td>
										

									</tr>
								</s:iterator>
							</tbody>


						</table>
							<div style="height:1000px;"></div>
							<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
							</div>
						</div>
					</div>
				</div>
		</div>

	</s:form>
  
   
 </body>

