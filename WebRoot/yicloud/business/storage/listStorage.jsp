<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../../sxcloud/common/link.jsp"%>
<%@ include file="../../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.SELECT_APP_ID.value ='';
		theForm.SELECT_BIND_DEVICE.value = '';
		theForm.USER_NAME.value='';
		theForm.USER_ID.value='';
		theForm.GROUP_ID.value='';
		theForm.FILE_PATH.value='';
		theForm.STORAGE_SIZE.value='';
		theForm.AVAILABLE.value='';
		
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	theForm.flag.value=0;
 	    theForm.action = 'storage_addStorage.do'
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
 	    theForm.action = 'storage_modStorage.do' 
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
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条功能信息");
 	    return false ;
 	    }
 	    theForm.action = 'storage_delStorage.do'  
		theForm.submit();
 	}
 	function backupRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IPADDRESS.value = checkboxids[i].value;
 	     // theForm.NET_ID.value = checkboxids[i].value;
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
 	   // theForm.action = 'backupIpInfo.do' 
		theForm.submit();
 	}
 	function issuanceRequest(){
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.IPADDRESS.value = checkboxids[i].value; 
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
 	    theForm.action = 'issuanceIpInfo.do' 
		theForm.submit();
 	}
 	function checkSelect(){
		theForm.SELECT_APP_ID.value;
		theForm.SELECT_BIND_DEVICE.value;
		theForm.SELECT_TYPE.value;
	}
</script>
</head>
<body>
<s:form action="storage_listStorage.do" id="theForm" method="post"
	cssClass="theForm">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                    <tr>
                    <td class="til">业务应用ID:</td>
                    <td>
                        <s:select list="theForm.appIdList" listKey="ID" listValue="NAME" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_APP_ID" id="SELECT_APP_ID"></s:select>
                    </td>
                    <td class="til">绑定设备主机ID:</td>
                  	 <td>
                  	    <s:select list="theForm.bindDeviceList" listKey="ID" listValue="NAME_ZH" headerKey="0" headerValue="-请选择-" name="theForm.SELECT_BIND_DEVICE" id="SELECT_BIND_DEVICE"></s:select>
                    </td>
                    <td class="til">用户:</td>
                   <td>
                        <s:textfield name="theForm.USER_NAME" cssClass="txt" id="USER_NAME"></s:textfield>
                    </td>
                    <td class="til">用户ID:</td>
                   <td>
                        <s:textfield name="theForm.USER_ID" cssClass="txt" id="USER_ID"></s:textfield>
                    </td>
                    </tr>
                    <tr>
                     <td class="til">组ID:</td>
                   <td>
                        <s:textfield name="theForm.GROUP_ID" cssClass="txt" id="GROUP_ID"></s:textfield>
                    </td>
                     <td class="til">文件系统:</td>
                   <td>
                        <s:textfield name="theForm.FILE_PATH" cssClass="txt" id="FILE_PATH"></s:textfield>
                    </td>
                     <td class="til">大小:</td>
                   <td>
                        <s:textfield name="theForm.STORAGE_SIZE" cssClass="txt" id="STORAGE_SIZE"></s:textfield>
                    </td>
                     <td class="til">剩余存储:</td>
                   <td>
                        <s:textfield name="theForm.AVAILABLE" cssClass="txt" id="AVAILABLE"></s:textfield>
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
			<ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="添加" onclick = "addRequest();return false;" /></li>
			    <li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
		
			</ul>
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'int')">业务应用ID</th>
				   <th onclick="sort(theTable,2,'int')">绑定设备主机ID</th>
				   <th onclick="sort(theTable,3,'string')">用户</th>                
                   <th onclick="sort(theTable,4,'string')">用户ID</th>
                   <th onclick="sort(theTable,5,'string')">组ID</th>
                   <th onclick="sort(theTable,6,'string')">文件系统</th>
                   <th onclick="sort(theTable,7,'string')">大小</th>
                   <th onclick="sort(theTable,8,'string')">剩余存储</th>
             </tr>
			  </thead>
			  <tbody>
			   <s:iterator value="theForm.resultList" id="theBean">
				<tr>
					<td><input name="checkboxid" type="checkbox" value="<s:property value='#theBean.ID'/>" /></td>
					<td><s:property value="#theBean.APP_ID" /></td>
					<td><s:property value="#theBean.BIND_DEVICE" /></td>
					<td><s:property value="#theBean.USER_NAME" /></td>
					<td><s:property value="#theBean.USER_ID" /></td>
					<td><s:property value="#theBean.GROUP_ID" /></td>
					<td><s:property value="#theBean.FILE_PATH" /></td>
					<td><s:property value="#theBean.STORAGE_SIZE" /></td>
					<td><s:property value="#theBean.AVAILABLE" /></td>
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
