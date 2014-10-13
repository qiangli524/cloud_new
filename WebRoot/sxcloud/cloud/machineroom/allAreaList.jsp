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
 	var theForm = document.getElementById("theForm");
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.AREA_ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    
 	    theForm.action = 'area_deleteArea.do'; 
		theForm.submit();
		
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.AREA_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'area_updateArea.do';
		theForm.submit();
 	}
 	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'area_modifyArea.do';
	  	theForm.submit();
 	}
 	function forPassId(ID){
 	  theForm.action = '';
	  theForm.submit();
 	}
 	function resetForm(){
			theForm.NAME.value = "";
		}
 	
	</script>

  </head>
  
  <body>
  
   <s:form action="area_queryAreaList.do" id="theForm" method="post"
		>
		<s:hidden name="theForm.AREA_ID" id="AREA_ID"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								机房区域名称:
							</td>
							<td>
								<s:textfield name="theForm.AREA_NAME" cssClass="txt"
									id="NAME"></s:textfield>
							</td>
						
						
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick = "javascript:searchRequest()"/>
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
								<input type="button" class="thickbox btn-style02" value="添加"
									onclick = "addRequest();return false;"  />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;"  />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;"/>
							</li>
						</ul>
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
										机房区域名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										区域编码
									</th>
									<th onclick="sort(theTable,3,'string')">
										所在机房
									</th>
									<th onclick="sort(theTable,4,'string')">
										联系人
									</th>
									<th onclick="sort(theTable,5,'string')">
										联系人电话
									</th>
									
									<th onclick="sort(theTable,6,'int')">
										面积(平方米)
									</th>
									<th onclick="sort(theTable,7,'date')">
										创建时间
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.AREA_LIST" id="theBean">
									<tr >
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value='#theBean.AREA_ID'/>"/>
										</td>
										<td>
											<s:property value="#theBean.AREA_NAME" />
										</td>
										<!--<td>
										 <s:if test="#theBean.shu!=0">
										<a href="#" onclick="return forPassId('<s:property value="#theBean.ID"/>');">
										<s:property value="#theBean.shu" />
										</a>
										</s:if>
										<s:else>
										<s:property value="#theBean.shu" />
										</s:else>
										</td> -->
										<td>
											<s:property value="#theBean.AREA_CODE" />
										</td>
										<td>
											<s:property value="#theBean.ROOM_NAME" />
										</td>
											<td>
											<s:property value="#theBean.LINK_MAN" />
										</td>
											<td>
											<s:property value="#theBean.PHONE" />
										</td>
										<td>
											<s:property value="#theBean.AREA_SIZE" />
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
		

	</s:form>
   
 </body>

