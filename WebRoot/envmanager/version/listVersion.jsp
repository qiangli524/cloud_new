<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: 'sxcloud/cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest(){
		theForm.submit();
	}
	function resetForm(theForm){
	document.getElementById("SOFTNAME").value="";
	document.getElementById("SOFTEDITION").value="";
	//document.getElementById("SUPLINUX").value="";
	//document.getElementById("SUPUNIX").value="";
	}
	function addRequest() {
 		theForm.flag.value = 0;
 	    theForm.action = 'version_insertVersionObj.do';
	  	theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.s_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'version_updateVersionObj.do';
		theForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
  	var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.s_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除功能信息！");
 	    return false ;
 	    }
 	    
 	    theForm.action = 'version_deleteVersionObj.do'; 
		theForm.submit();
		
 	}
</script>
</head>
<body>
<s:form action="version_queryVersionList.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.s_id" id="s_id"></s:hidden>
<s:hidden name="theForm.flag" id="flag"></s:hidden>
 <div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%"  border="0">
                  <tr>
                 
					<td class="til">软件名称</td>
					<td>
						<s:textfield name="theForm.SOFTNAME" cssClass="txt" id="SOFTNAME"></s:textfield>
					</td>
					<td class="til">版本</td>
					<td>
						<s:textfield name="theForm.SOFTEDITION" cssClass="txt" id="SOFTEDITION"></s:textfield>
					</td>
					<!-- 
					<td class="til">Linux系统</td>
					<td>
						<s:textfield name="theForm.SUPLINUX" cssClass="txt" id="SUPLINUX"></s:textfield>
					</td>
					<td class="til">Unix系统</td>
					<td>
						<s:textfield name="theForm.SUPUNIX" cssClass="txt" id="SUPUNIX"></s:textfield>
					</td>
					 -->
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
        </div>
  <div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li>
				<input type="button" class="thickbox btn-style02" value="添加"
									onclick = "addRequest();return false;"  />
				</li>
				<li>
				<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;"  />
				</li>
				<li>
				<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;"/>
				</li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
				<!-- <th>选择</th>
				<th>软件名称</th>
				<th>版本</th>
				<th>Linux</th>
				<th>Unix</th>
				<th>SunOS</th>
				<th>操作系统补丁</th>
				<th>操作系统服务要求</th>
				<th>依赖软件包</th>
				<th>编译器要求</th>
				<th>依赖第三方软件</th>
				<th>软件补丁</th>
				<th>是否需要license</th> -->
			  
			<tr>
				<th rowspan="2">选择</th>
				<th rowspan="2">软件名称</th>
				<th rowspan="2">版本</th>
				<th colspan="3">支持操作系统</th>
				<th rowspan="2">操作系统补丁</th>
				<th rowspan="2">操作系统服务要求</th>
				<th rowspan="2">依赖软件包</th>
				<th rowspan="2">编译器要求</th>
				<th rowspan="2">依赖第三方软件</th>
				<th rowspan="2">软件补丁</th>
				<th rowspan="2">是否需要license</th>
			</tr>
			<tr>
				<th style="background:#4682B4" >Linux</th>
				<th style="background:#4682B4" columnId='SUPUNIX'>Unix</th>
				<th style="background:#4682B4" columnId='SUPSUNOS'>SunOS</th>
			</tr>
			  
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.version_List" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.s_id"/>" /></td>
			  		<td><s:property value="#theBean.SOFTNAME"/></td>
			  		<td><s:property value="#theBean.SOFTEDITION"/></td>
			  		
			  		<td><s:property value="#theBean.SUPLINUX"/></td>
			  		<td><s:property value="#theBean.SUPUNIX"/></td>
			  		
			  		<td><s:property value="#theBean.SUPSUNOS"/></td>
			  		<td><s:property value="#theBean.SYSTEMPATCH"/></td>
			  		<td><s:property value="#theBean.SYSTEMSERVICE"/></td>
			  		<td><s:property value="#theBean.DEPENDPACK"/></td>
			  		<td><s:property value="#theBean.COMPILER"/></td>
			  		<td><s:property value="#theBean.OTHERPACK"/></td>
			  		<td><s:property value="#theBean.SOFTPACK"/></td>
			  		<td><s:property value="#theBean.SOFTLICENSE"/></td>
			  		
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>
		</div>
		</div>
</s:form>
</body>
