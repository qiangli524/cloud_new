<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.TEM_ID.value = '';
		theForm.TEM_NAME.value = '';
		theForm.TYPE.value = 0;
	}
	
   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	//	theForm.flag.value = 0;
 	    theForm.action = 'templet_addTempletInfo.do' 
		theForm.submit();
 	}
 	function lookRequest(){
 	    theForm.action = 'lookTempletInfo.do'  
		theForm.submit();
 	}
 	function modRequest() { 
 	var tem_id ='';
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      //theForm.TEM_ID.value = checkboxids[i].value;
 	     tem_id=checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'templet_modTempletInfo.do?TEM_ID='+tem_id; 
		theForm.submit();
 	}
 	function delRequest() {
 	var tem_id ='';
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      //theForm.TEM_ID.value = checkboxids[i].value;
 	      tem_id = checkboxids[i].value;
 	      if( document.getElementsByName("release_flag")[i].value==1){
 	      	alert("不能删除已发布服务！");
 	      	return false;
 	      }
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
 	    if(confirm("确定要删除此服务吗?")==true){
 	    	theForm.action = 'templet_delTempletInfo.do?TEM_ID='+tem_id;
			theForm.submit();
 	    }
 	}
</script>
</head>
<body>
<s:form action="templet_listTempletInfo.do" id="theForm" method="post">
 <s:hidden name="theForm.flag" id="flag"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">服务编号:</td>
                    <td>
						<s:textfield name="theForm.TEM_ID" cssClass="txt" id="TEM_ID"></s:textfield>
                    </td>
                    <td class="til">服务名称:</td>
                    <td>
						<s:textfield name="theForm.TEM_NAME" cssClass="txt" id="TEM_NAME"></s:textfield>
                    </td>
                    <td class="til">服务类型:</td>
                    <td>
					<s:select list="theForm.typeList" listKey="TYPE" listValue="TYPE_NAME" headerKey="0" headerValue="-请选择-" id="TYPE" name="theForm.TYPE"></s:select> 
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
				<li><input type="button" class="thickbox btn-style02" value="添加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>

		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">服务编号</th>
				   <th onclick="sort(theTable,2,'string')">服务名称</th>
				   <th onclick="sort(theTable,3,'string')">服务类型</th>
				   <th onclick="sort(theTable,4,'string')">服务状态</th>
				   <th onclick="sort(theTable,5,'string')">服务描述</th>
				   <th>查看服务详情</th>
             </tr>
			  </thead>
			  <tbody>
			<s:iterator value="theForm.resultList" id="theBean">
				<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.TEM_ID'/>"/></td>
							<td><s:property value="#theBean.TEM_ID"/></td>
							<td><s:property value="#theBean.TEM_NAME" /></td>
							<td><s:property value="#theBean.TYPE_NAME" /></td>
							<td>
								<s:if test="#theBean.RELEASE_FLAG==0">未发布</s:if>
								<s:elseif test="#theBean.RELEASE_FLAG==1">已发布</s:elseif>
								<s:else>废弃</s:else>
								<input name="release_flag" type="hidden" value="<s:property value='#theBean.RELEASE_FLAG'/>"/>
							</td>
							<td><s:property value="#theBean.TEM_DESC" /></td>
							<td>
							<a href="templet_lookTempletInfo.do?TEM_ID=<s:property value="#theBean.TEM_ID"/>">
							查看服务详情
							</a>
							</td>
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
