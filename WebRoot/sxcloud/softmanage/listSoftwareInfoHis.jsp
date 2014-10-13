<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.NAME.value = '';
		theForm.UPDATETYPE.value='';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	/*
 	function addRequest() {
 	    theForm.action = 'addSoftwareInfo.do' 
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
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改软件！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条软件信息");
 	    return false ;
 	    }
 	    theForm.action = 'modSoftwareInfo.do' 
		theForm.submit();
 	}
 	*/
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
 	    alert("请勾选需要删除软件历史信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条软件历史信息！");
 	    return false ;
 	    }
 	    theForm.action = 'softwarehis_delSoftwareInfohis.do'  
		theForm.submit();
 	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="softwarehis_listSoftwareInfohis.do" method="post" cssClass="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">软件历史</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">软件名称：</label>
				<s:textfield name="tbBusiSoftwareInfoObj.NAME" id="NAME" cssClass="inpt-1 vm"></s:textfield>
				<label class="mgl-20 vm">操作类型：</label>
				 <s:select list="#{'':'-请选择-','1':'-删除-','2':'-更新-'}" name="theForm.UPDATETYPE" cssClass="select-1 vm" id="UPDATETYPE"></s:select>           
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>			
		<div class="table-head">
		    <ul class="btns">
		        <!--  
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
				-->
			</ul>
		</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">软件名称</th>                
                   <th onclick="sort(theTable,1,'int')">软件大小</th>
                   <th onclick="sort(theTable,2,'float')">软件版本</th>
                   <th onclick="sort(theTable,3,'string')">软件提供者</th>
                   <th onclick="sort(theTable,4,'string')">操作类型</th>
                   <th onclick="sort(theTable,5,'string')">操作人</th>
                   <th onclick="sort(theTable,6,'date')">操作时间</th>    
			  </tr>
			  </thead>
			  <tbody>
			  	<s:iterator value="theForm.resultList" id="theBean">
			  		<tr>
			  			<td><s:property value="#theBean.NAME"/></td>
			  			<td><s:property value="#theBean.SOFTWARE_SIZE"/></td>
			  			<td><s:property value="#theBean.VERSION"/></td>
			  			<td><s:property value="#theBean.PROVIDERS"/></td>
			  			<td><s:if test="#theBean.UPDATETYPE==1">删除</s:if>
			  				<s:elseif test="#theBean.UPDATETYPE==2">更新</s:elseif>
			  			</td>
			  			<td><s:property value="#theBean.UPDATEUSER"/></td>
			  			<td><s:property value="#theBean.INSERTTIME"/></td>
			  		</tr>
			  	</s:iterator>
			  </tbody>
			</table>
			<div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
	</s:form>
</div>	
</body>
