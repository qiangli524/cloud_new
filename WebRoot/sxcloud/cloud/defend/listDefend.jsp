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
		theForm.ID.value = 0;
		theForm.DEFEND_DIR.value = '';
		theForm.ENABLE.value = '-1';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'defend_addDefend.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.DEFEND_ID.value = checkboxids[i].value;
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
 	    theForm.action = 'defend_modDefend.do';
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.DEFEND_ID.value = checkboxids[i].value;
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
 	    if(confirm("确定要删除此条防篡改吗?")==true){
 	    	theForm.action = 'defend_delDefend.do'  
			theForm.submit();
 	    }
 	   
 	}
</script>
</head>
<body>
<s:form action="defend_listDefend" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.flag" id="flag"></s:hidden>
<s:hidden name="theForm.DEFEND_ID" id="DEFEND_ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">所属主机名称:</td>
                    <td>
                    	<s:select list="theForm.hphostList" listKey="ID" listValue="HOSTNAME" name="theForm.ID" id="ID" headerKey="0" headerValue="-请选择-"></s:select>
                    </td>
                    <td class="til">受控目录:</td>
                    <td>
						<s:textfield name="theForm.DEFEND_DIR" cssClass="txt" id="DEFEND_DIR"></s:textfield>
                    </td>
                    <td class="til">是否生效:</td>
                    <td>
                      <s:select list="#{'-1':'请选择','0':'失效','1':'生效'}" name="theForm.ENABLE" id="ENABLE"></s:select>
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
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
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
					<th onclick="sort(theTable,1,'string')">防篡改编号</th>
					<th onclick="sort(theTable,2,'string')">所属主机名称</th> 
					<th onclick="sort(theTable,3,'string')">受控目录</th>
					<th onclick="sort(theTable,4,'string')">是否生效</th>  
					<th onclick="sort(theTable,5,'string')">状态</th>
					<th onclick="sort(theTable,6,'string')">操作类型</th>                 
					<th onclick="sort(theTable,7,'date')">更新时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.DEFEND_ID"/>"/></td>
			  		<td> <s:property value="#theBean.DEFEND_ID"/> </td>
			  		<td> <s:property value="#theBean.HOSTNAME"/> </td>
			  		<td><s:property value="#theBean.DEFEND_DIR"/></td>
			  		<td>
			  			<s:if test="#theBean.ENABLE==0">失效</s:if>
			  			<s:elseif test="#theBean.ENABLE==1">生效</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.EXCUTE_FLAG==0">未添加</s:if>
			  			<s:else>已添加</s:else>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.DEFEND_TYPE==0">添加防篡改</s:if>
			  			<s:elseif test="#theBean.DEFEND_TYPE==1">修改防篡改</s:elseif>
			  			<s:elseif test="#theBean.DEFEND_TYPE==2">删除防篡改</s:elseif>
			  		</td>
			  		<td>
			  			<s:property value="#theBean.INS_DATE"/>
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
