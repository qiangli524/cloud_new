<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../common/taglib.jsp"%>
<%@ include file="../../../common/link.jsp"%>
<%@ include file="../../../common/view.jsp"%>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
		<script type="text/javascript">
			$(function(){
				$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
				$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
			})
			
			//提交
			function submitForm(status){
				
				//对页面表单进行数据校验
				if(validateForm()){
				
					//alert(status);
					document.getElementById("theForm").COMMAND.value = status;
					
					document.getElementById("theForm").submit();
				}
				
			}
		function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.NEED_NUMBERS.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要更改的应用部署申请！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能更改单条应用部署申请！");
 	    return false ;
 	    }
 	    theForm.action = 'appworkflow_alterAppOrder.do' 
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.NEED_NUMBERS.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要更改的应用部署申请！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能更改单条应用部署申请");
 	    return false ;
 	    }
 	    if(confirm("确定要删除该申请草稿吗?")==true){
 	    	theForm.action = 'appworkflow_deleteAppOrder.do'  
			theForm.submit();
 	    }
 	}
 	 function searchRequest() { 
		theForm.submit();
 	}
 	function resetForm(theForm){
			theForm.NEED_NUMBERS.value = '';
			theForm.datepicker2.value = '';
		}
	</script>
	</head>
	<body>
	<s:form action="appworkflow_listDraftAppDeploy.do" method="post" id="theForm" cssClass="theForm">
	<s:hidden name="theForm.COMMAND" id="COMMAND"></s:hidden>
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
                    	<s:textfield name="theForm.NEED_NUMBERS" cssClass="txt" id="NEED_NUMBERS"></s:textfield>
                    </td>
                    <td class="til">要求发起时间:</td>
                    <td>
                    	<s:textfield name="theForm.START_DATE" size="10" readonly="true" cssClass="txt" id="datepicker2"></s:textfield>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
				
	<div class="blue-wrap noborder">
	<div class="table-head">
						<ul class="btns">
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
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
				    <th onclick="sort(theTable,1,'string')">工单编号</th>
				    <th onclick="sort(theTable,2,'string')">需求内容</th>
				    <th onclick="sort(theTable,3,'date')">发起时间</th>
				    <th onclick="sort(theTable,4,'date')">要求结束时间</th>
				    <th onclick="sort(theTable,5,'int')">应用个数</th>
				    <th onclick="sort(theTable,6,'string')">应用名称</th> 
             </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="theForm.resultList" id="theBean">
			  		<tr>
			  			<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.NEED_NUMBERS"/>"/></td>
			  			<td> <s:property value="#theBean.NEED_NUMBERS"/> </td>
			  			<td> <s:property value="#theBean.NEED_CONT"/> </td>
			  			<td> <s:property value="#theBean.START_DATE"/> </td>
			  			<td> <s:property value="#theBean.END_DATE"/> </td>
			  			<td> <s:property value="#theBean.APP_SIZE"/> </td>
			  			<td> <s:property value="#theBean.APPNAME"/> </td>
			  		</tr>
			  	</s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->	
		</div>     	 
	</s:form>
	</body>
