<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	function bindingImageRequest(){
		var id = '<%=request.getParameter("ID")%>';
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
 	    alert("请勾选需要绑定的信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'templet_bindingImage.do?ID='+id;
		theForm.submit();
	}
	function goBackRequest(){
		theForm.action = 'templet_listTempletType.do'
		theForm.submit();
	}
</script>
</head>
<body>
<s:form action="templet_bindingImage.do" cssClass="theForm" id="theForm">
<s:hidden name="theTempletForm.ID" id="ID"></s:hidden>
<div class="tit-zzi">
		<div id="zi">备绑定的镜像</div>
		<div id="zi"></div>
</div>
<div class="scrollbody">
	<div class="blue-wrap noborder">
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			  		<th>选择</th>
			  		<th onclick="sort(theTable,1,'string')">镜像名称</th>
					<th onclick="sort(theTable,2,'string')">镜像描述</th>
					<th onclick="sort(theTable,3,'string')">镜像状态</th>
					<th onclick="sort(theTable,4,'int')">CPU(个)</th>
					<th onclick="sort(theTable,5,'int')">内存(MB)</th>
					<th onclick="sort(theTable,6,'int')">存储</th>
					<th onclick="sort(theTable,7,'string')">操作系统</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theTempletForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.ID'/>"/></td>
			  		<td><s:property value="#theBean.VH_NAME"/></td>
			  		<td><s:property value="#theBean.VH_DESC"/></td>
			  		<td><s:property value="#theBean.VH_STAT"/></td>
			  		<td><s:property value="#theBean.VH_CPU"/></td>
			  		<td><s:property value="#theBean.VH_MEM"/></td>
			  		<td><s:property value="#theBean.VH_STORAGE"/></td>
			  		<td><s:property value="#theBean.VH_SYSTEM"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">	
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="绑定"
						onclick="javascript:bindingImageRequest();return false;" />
					<input type="button" class="thickbox btn-style02" value="返回"
						onclick="javascript:goBackRequest();return false;" />
				</td>
			</tr>
		</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
