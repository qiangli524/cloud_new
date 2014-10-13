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
		theForm.NAME.value='';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
		theForm.flag.value = 0;
 	    theForm.action = 'templet_addTempletType.do' 
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
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'templet_modTempletType.do'
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
 	    alert("请勾选需要删除信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条信息");
 	    return false ;
 	    }
 	    if(confirm("确定要删除该镜像类型吗?")==true){
 	    	theForm.action = 'templet_delTempletType.do'  
			theForm.submit();
 	    }
 	}
 	function bindingImage(id){
 		theForm.action = 'templet_listAllTemplet.do?ID='+id  
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="templet_listTempletType.do" method="post" cssClass="theForm" id="theForm">
<s:hidden id="flag" name="theTypeForm.flag"></s:hidden>
<s:hidden id="ID" name="theTypeForm.ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">镜像名称:</td>
                    <td>
						<s:textfield name="theTypeForm.NAME" id="NAME" cssClass="txt"></s:textfield>
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
					<th onclick="sort(theTable,1,'string')">镜像类型名称</th>
					<th onclick="sort(theTable,2,'string')">镜像类型描述</th>
					<th>查看</th>
					<th>绑定</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theTypeForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/></td>
			  		<td><s:property value="#theBean.NAME"/></td>
			  		<td><s:property value="#theBean.TYPE_DESC"/></td>
			  		<td><a href="templet_listAllTempletOfType.do?ID=<s:property value="#theBean.ID"/>&NAME=<s:property value="#theBean.NAME"/>"> 查看所有<s:property value="#theBean.NAME"/>镜像 </a></td>
			  		<td><input type="button" class="thickbox btn-style03" value="绑定镜像" onclick = "bindingImage(<s:property value="#theBean.ID"/>);return false;" /></td>
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
