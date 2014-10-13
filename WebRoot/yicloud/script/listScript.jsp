<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

 	function addRequest() {
 		var flag = 0;
 	    theForm.action = 'templettree_addScript.do?flag='+flag; 
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改的脚本！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条脚本");
 	    return false ;
 	    }
 	    var flag = 1;
 	    theForm.action = 'templettree_updateScript.do?flag='+flag; 
		theForm.submit();
 	}
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除的脚本！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条脚本");
 	    return false ;
 	    }
 	    theForm.action = 'templettree_deleteScript.do';  
		theForm.submit();
 	}
 	function resetForm(theForm){
		theForm.name.value = '';
	}

	function searchRequest() { 
 	    theForm.action = 'templettree_listScript.do';
		theForm.submit();
 	}
</script>
</head>
<body>
<s:form action="templettree_listScript.do" method="post" id="theForm">
<s:hidden name="theScriptForm.id" id="id"></s:hidden>
<s:hidden name="theScriptForm.chks" id="chks"></s:hidden>
<div class="scrollbody">
	<div class="box on">
	<div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">脚本名称:</td>
                    <td>
						<s:textfield name="theScriptForm.name" id="name" cssClass="txt"></s:textfield>
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
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
			  	   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">脚本名称</th>
				   <th onclick="sort(theTable,2,'string')">脚本内容</th>                
                   <th onclick="sort(theTable,3,'string')">脚本描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theScriptForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
			  		<td> <s:property value="#theBean.name"/></td>
			  		<td> <s:property value="#theBean.content"/></td>
			  		<td> <s:property value="#theBean.des"/></td>
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
