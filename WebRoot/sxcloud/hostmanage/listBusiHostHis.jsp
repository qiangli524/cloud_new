<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.HOSTNAME.value = '';
		theForm.STATUS.value = -1;
		theForm.UPDATETYPE.value = -1;
	}

   function searchRequest() {
   		theForm.action="hosthis_listBusiHostHis.do"; 
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
 	    alert("请勾选需要删除的主机历史信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条主机历史信息");
 	    return false ;
 	    }
 	    theForm.action = 'hosthis_delBusiHostHis.do'  
		theForm.submit();
 	}
</script>
</head>
<body onLoad="self.focus();document.theForm.HOSTNAME.focus()">
<s:form action="hosthis_listBusiHostHis.do" method="post" id="theForm">
 <s:hidden name="theForm.ID" id="ID" />
 <s:hidden name="theForm.HOSTID" id="HOSTID" />
 		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">部署主机历史</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">主机名称：</label>
				<s:textfield name="theForm.HOSTNAME" id="HOSTNAME"  cssClass="inpt-1 vm"  maxlength="30" />
				<label class="mgl-20 vm">主机状态：</label>
				 <s:select cssClass="select-1 vm" headerKey="" headerValue="请选择"   list="#{'1':'空闲','2':'非空闲'}" name="theForm.STATUS" id="STATUS">
     				  </s:select>
     			<label class="mgl-20 vm">操作类型：</label>	  
     			<s:select headerKey="" cssClass="select-1 vm"  headerValue="请选择"   list="#{'0':'新增','1':'更新','2':'删除'}" name="theForm.UPDATETYPE" id="UPDATETYPE">
     					</s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>		
			<table width="100%" cellspacing="0" style="margin-top:10px" border="0" class="blue-table sorttable" id="theTable">
   			<thead>
			  <tr> 
				   <th onclick="sort(theTable,0,'string')">主机名称</th>
				   <th onclick="sort(theTable,1,'string')">状态</th>                
                   <th onclick="sort(theTable,2,'string')">主机IP</th>
                   <th onclick="sort(theTable,3,'string')">内存</th>
                   <th onclick="sort(theTable,4,'string')">操作类型</th>
                   <th onclick="sort(theTable,5,'string')">操作人</th>
                   <th onclick="sort(theTable,6,'date')">操作时间</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator id="theBean" value="theForm.resultList"  >
						<tr> 
							<td><s:property value="#theBean.HOSTNAME" /></td>
							<td>
	                    	<s:if test="#theBean.STATUS==1">空闲</s:if>
					    	<s:if test="#theBean.STATUS==2">非空闲</s:if>
							</td>
							<td><s:property value="#theBean.IP" /></td>				
							<td><s:property value="#theBean.MEMORY" /></td>
							<td>
								<s:if test="#theBean.UPDATETYPE==0">新增</s:if>
								<s:if test="#theBean.UPDATETYPE==1">更新</s:if>
							  	<s:if test="#theBean.UPDATETYPE==2">删除</s:if>
							</td>
							<td><s:property value="#theBean.UPDATEUSER" /></td>
							<td><s:property value="#theBean.INSERTTIME" /></td>
						
						</tr>
				</s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		 </div>
</s:form>
</body>
</html:html>
