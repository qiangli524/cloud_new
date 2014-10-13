<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
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
		theForm.IM_NAME.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	  var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IM_ID.value = checkboxids[i].value;
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
 		theForm.action = 'image_basicDeploy.do'
 		theForm.submit();
 	}
 	function advancedRequest() {
 	  var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IM_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'image_advancedDeploy.do' 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IM_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'modFunctions.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IM_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'delImageInfo.do'  
		theForm.submit();
 	}
 	 function configRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
	      theForm.IM_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'image_virtualImagesConfig.do' 
		theForm.submit();
 	}
 	function moveRequest(){
 	
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
	    if(checkboxids!=null&&checkboxids.length>0){
	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	     
	      theForm.IM_ID.value = checkboxids[i].value;
	      
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
 	    theForm.action = 'image_moveImageInfo.do' 
		theForm.submit();
 	}
</script>
</head>
<body>
	<s:form action="image_listImageInfo.do" method="post" id="theForm">
		<s:hidden name="theForm.IM_ID" id="IM_ID"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								映像名称:
							</td>
							<td>
								<s:textfield name="theForm.IM_NAME" id="IM_NAME" cssClass="txt"></s:textfield>
							</td>
							<%--<td class="til">功能状态:</td>
                    <td>
                        <html:select property="STATUS" name="theForm">
	                        <html:option value="">-请选择-</html:option>
	                        <html:option value="0">-失效-</html:option>
	                        <html:option value="1">-生效-</html:option>
                        </html:select>
                    </td>
                  --%>
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
								<input type="button" class="thickbox btn-style02-75"
									value="基本部署" onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02-75"
									value="高级部署" onclick="advancedRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="配置"
									onclick="configRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02-75"
									value="移至项目" onclick="moveRequest();return false;" />
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
										映像名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										映像描述
									</th>
									<th onclick="sort(theTable,3,'string')">
										映像状态
									</th>
									<th onclick="sort(theTable,4,'float')">
										映像版本
									</th>
									<!--  
                   <th>类型:</th>
                   <th>连接地址:</th>
                   <th>重复加载模式:</th>
                   -->
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
										<td>
										<input name="checkboxid" type="checkbox"
													value="<s:property value='#theBean.IM_ID'/>" />
										</td>
										<td>
											<s:property value="#theBean.IM_NAME"/>
										</td>
										<td>
											<s:if test="#theBean.IM_DESC==null">
												无
											</s:if>
											<s:else>
												<s:property value="#theBean.IM_DESC"/>
											</s:else>
										</td>
										<td>
											<s:if test="#theBean.IM_STATE==0">
												发生故障
											</s:if>
											<s:elseif test="#theBean.IM_STATE==1">
												未知
											</s:elseif>
											<s:elseif test="#theBean.IM_STATE==2">
												确定
											</s:elseif>
										</td>
										<td>
											<s:property value="#theBean.IM_VERSION"/>
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
