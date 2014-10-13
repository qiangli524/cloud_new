<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
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
		theForm.DEFEND_DIR.value = '';
		theForm.TYPE.value = '0';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
</script>
</head>
<body>
<s:form action="defend_listHisDefend" method="post" cssClass="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                   <td class="til">受控目录:</td>
                    <td>
						<s:textfield name="theForm.DEFEND_DIR" cssClass="txt" id="DEFEND_DIR"></s:textfield>
					</td>
                  	<td class="til">操作
                  	</td>
                  	
                  	 <td>
                     <s:select list="#{'0':'请选择','1':'添加操作','2':'修改操作','3':'删除操作'}" name="theForm.TYPE" id="TYPE"></s:select>
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
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">防篡改编号</th>
					<th onclick="sort(theTable,1,'string')">所属主机名称</th> 
					<th onclick="sort(theTable,2,'string')">受控目录</th>
					<th onclick="sort(theTable,3,'string')">是否生效</th>  
					<th onclick="sort(theTable,4,'string')">状态</th>
					<th onclick="sort(theTable,5,'string')">操作类型</th>                 
					<th onclick="sort(theTable,6,'date')">防篡改更新时间</th>
					<th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><s:property value="#theBean.DEFEND_ID"/></td>
			  		<td><s:property value="#theBean.HOSTNAME"/></td>
			  		<td><s:property value="#theBean.DEFEND_DIR"/></td>
			  		<td>
			  			<s:if test="#theBean.ENABLE==0">失效</s:if>
			  			<s:elseif test="#theBean.ENABLE==1">生效</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.EXCUTE_FLAG==0">未添加</s:if>
			  			<s:elseif test="#theBean.EXCUTE_FLAG==1">已添加</s:elseif>
			  			<s:elseif test="#theBean.EXCUTE_FLAG==2">已添加</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.DEFEND_TYPE==0">添加防篡改</s:if>
			  			<s:elseif test="#theBean.DEFEND_TYPE==1">修改防篡改</s:elseif>
			  			<s:elseif test="#theBean.DEFEND_TYPE==2">删除防篡改</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.INS_DATE"/></td>
			  		<td>
			  			<s:if test="#theBean.TYPE==1">添加操作</s:if>
			  			<s:elseif test="#theBean.TYPE==2">修改操作</s:elseif>
			  			<s:elseif test="#theBean.TYPE==3">删除操作</s:elseif>
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
