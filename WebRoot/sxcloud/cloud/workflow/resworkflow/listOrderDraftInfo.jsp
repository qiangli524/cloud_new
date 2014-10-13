<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
<html:html locale="true">
	<head>
		<title></title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		
			})
			//提交表单
			function submitForm(){
				document.theForm.submit();
			}
			//重置查询条件
			function resetForm(){
				theForm.NEED_NUMBERS.value = '';
				theForm.datepicker1.value = '';
			}
			//新建上线工单详细信息查看
			function OrderDraftInfo(NEED_NUMBERS){
			}
			//新建上线工单 
			function addOrder(){
			var  NEED_NUMBERS = theForm.NEED_NUMBERS.value;
				document.theForm.action = 'resworkflow_addOrderInfo.do';
				document.theForm.submit();
			}
			//删除修改新建上线工单
			function manageOrder(COMMAND){
				var form = document.getElementById("theForm");
				if(COMMAND == 'delete' && !confirm("确认删除吗？")){
					return;
				}
				var j=0;
				var obj = form.chk;
				if(typeof(obj) == "undefined"){
					alert("请选择删除或修改的项目！");
					return;
				}
				if(typeof(obj.length) == "undefined"){
					if(!form.chk.checked){
						alert("请选择删除或修改的项目！");
						return;
					}else{
						j += 1;
					}
				}else{
					for(var i=0;i<obj.length;i++){
						if(obj[i].checked){
						   j++
						}
					}
				}
				var couterNum = 0;
		 	    var checkboxids = document.getElementsByName("theForm.chk");
		 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      couterNum = couterNum + 1 ;
		 	      theForm.NEED_NUMBERS.value = checkboxids[i].value;
		 	      }
		 	    }
		 	    }
		 	    if(couterNum==0){
		 	    alert("请勾选需要修改的信息！");
		 	    return false ;
		 	    }else if(couterNum>1){
		 	    alert("一次只能处理单条信息！");
		 	    return false ;
		 	    }
				if(j==0){
					alert("请选择删除或修改的项目！");
					return;
				}else if(COMMAND == 'update' && j > 1){
					alert("一次只能修改一条项目！");
					return;
				}else if(COMMAND == 'update' && j == 1){
					var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
					form.action = 'resworkflow_alterOrderInfo.do?NEED_NUMBERS='+NEED_NUMBERS;
					form.submit();
					return;
				}else if(COMMAND == 'delete'){
					var NEED_NUMBERS = theForm.NEED_NUMBERS.value;
					form.action = 'resworkflow_deleteOrderInfo.do?NEED_NUMBERS='+NEED_NUMBERS;
					form.submit();
				}
			}
		</script>
	</head>
	<body>
		<s:form action="resworkflow_getOrderDraftInfo.do" method="post" id="theForm">
			<s:hidden name="theForm.COMMAND" id="COMMAND" />
			<div class="scrollbody">
				<div class="query">
					<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
				</div>
				<div class="box on">
					<div class="query-form">
						<table width="100%" class="querytable" border="0">
						    <tr>
                    <td class="til">工单编号:</td>
                    <td>
						<s:textfield name="theForm.NEED_NUMBERS" id="NEED_NUMBERS"/>
                    </td>
                    <td class="til">要求完成时间:</td>
                    <td>
						<s:textfield name="theForm.end_time"  id="datepicker1" />
                    </td>
                  </tr>
							<tr>
								<td colspan="6" class="btns">
									<div>
										<input type="button" class="thickbox btn-style02" value="查询"
											onclick="javascript:submitForm();" />
										<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
									</div>
								</td>
							</tr>
						</table>
					</div>
					<!--query-form end -->
	
					<div class="blue-wrap noborder">
						<div class="table-head">
							<ul class="btns">
								<li><input type="button" class="thickbox btn-style02" value="新建" onclick = addOrder();return false;" /></li>
								<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "manageOrder('delete');return false;" /></li>
								<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "manageOrder('update');return false;" /></li>
							</ul>
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
						<div class="table-ct">
							<table id="theTable" width="100%" class="blue-table sorttable" border="0"
								cellspacing="0">
							   <thead>
									<tr>
										<th>选择</th>
										<th onclick="sort(theTable,1,'string')">工单编号</th>
										<th onclick="sort(theTable,2,'string')">工单发起人</th>
										<th onclick="sort(theTable,3,'date')">要求完成时间</th>
										<th onclick="sort(theTable,4,'string')">工单状态</th>
									</tr>
								</thead>
								<tbody>
										<s:iterator id="theBean" value="theForm.resultList">
											<tr>
												<td><input type="checkbox" name="theForm.chk" value="<s:property value="#theBean.NEED_NUMBERS" />" id="chk"/></td>
												<td><s:text name="#theBean.NEED_NUMBERS" /></td>	
												<td><s:text name="#theBean.NEED_SPONSOR" /></td>
												<td><s:text name="#theBean.NEED_END" /></td>
												<td>
													<s:if test="#theBean.NEED_STATUS==0">草稿</s:if>
													<s:if test="#theBean.NEED_STATUS==1 %>">正在处理</s:if>
													<s:if test="#theBean.NEED_STATUS==2">处理结束</s:if>
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
