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
		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
		});
	})
	function resetForm(thisForm){
		thisForm.HOSTID.value='';
		thisForm.HOSTUSERNAME.value='';
		
		
	}
	
   function searchRequest() {
	   vhid = document.getElementById("HOSTID").value;
	   thisForm.action = 'virpool_listVirPoolConfig.do?VH_ID='+vhid;
	   thisForm.submit();
 	}
 	function addRequest(vhid) {
 		thisForm.action = 'virpool_addVirPoolConfig.do?VH_ID='+vhid;
 		thisForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var id=0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      id = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改虚拟机机配置！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条虚拟机配置信息");
 	    return false ;
 	    }
 	   thisForm.action = 'virpool_modVirPoolConfig.do?id='+id; 
 	 thisForm.submit();
 	}
 	function delRequest() {
 	var couterNum = 0;
 	var id=0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      id = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除虚拟机配置信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条虚拟机配置信息");
 	    return false ;
 	    }
 	  	thisForm.action = 'virpool_delVirPoolConfig.do?id='+id;  
 	  	thisForm.submit();
 	}
</script>
</head>
<body>
<s:form action="virpool_listVirPoolConfig.do" method="post" cssClass="theForm" id="thisForm">
  <s:hidden name="theForm.VH_ID" id="VH_ID"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">虚拟机编号:</td>
                    <td>
						<s:textfield name="thisForm.HOSTID" cssClass="txt" id="HOSTID"></s:textfield>
                    </td>
                    <td class="til">用户名:</td>
                    <td>
						<s:textfield name="thisForm.HOSTUSERNAME" cssClass="txt" id="HOSTUSERNAME"></s:textfield>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('thisForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
                
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="添加" onclick = "addRequest('<s:property value="thisForm.HOSTID"/>');return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=thisForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
			       <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">虚拟机编号</th>
				   <th onclick="sort(theTable,2,'string')">用户类型</th>
				   <th onclick="sort(theTable,3,'string')">用户名</th>
				   <th onclick="sort(theTable,4,'string')">虚拟机密码</th>                
                   <th onclick="sort(theTable,5,'date')">更新时间</th>
                   <th onclick="sort(theTable,6,'string')">磁盘空间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="thisForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/>
			  			<s:hidden name="#theBean.ID" id="ID"></s:hidden>
			  		</td>
			  		<td><s:property value="#theBean.HOSTID"/></td>
			  		<td>
			  			<s:if test="#theBean.TYPE==1">超级管理员</s:if>
			  			<s:elseif test="#theBean.TYPE==2">普通用户</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.HOSTUSERNAME"/></td>
			  		<td><s:property value="#theBean.HOSRPWD"/></td>
			  		<td><s:property value="#theBean.UPDATETTIME"/></td>
			  		<td><s:property value="#theBean.SPACE"/></td>
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
