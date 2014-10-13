<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript">
	<%---
	function addRequest() {
		$.dialog({
   			id:'addSecurityConfig',
   			title:'添加安全配置',
   			width: '535px',
   			height: '300px',
   			content: 'url:security_addSecurityConfig.do'
   			});
	}
	--%>
	function resetForm(theForm){
		theForm.queryStatus.value='';
		theForm.queryType.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	
 	function modRequest() { 
 	    var couterNum = 0;
 	    var secType = "";
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	     secType = $(checkboxids[i]).attr("secType");
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息");
 	   		return false ;
 	    }else if(couterNum>1){
 	    alert("只能选择一项进行修改");
 	    	return false ;
 	    }
 	   $.dialog({
  			id:'modSecurityConfig',
  			title:'添加安全配置',
  			width: '535px',
  			height: '300px',
  			content: 'url:security_modSecurityConfig.do?secType='+secType
  			});
 	}
 	
	function delRequest() {
		var couterNum = 0;
		var secType = "";
		var checkboxids = document.getElementsByName("checkboxid");
		if(checkboxids!=null&&checkboxids.length>0){
			for(var i=0;i<checkboxids.length;i++){
				if(checkboxids[i].checked){
					couterNum = couterNum + 1 ;
					secType = $(checkboxids[i]).attr("secType");
				}
			}
		}
		if(couterNum==0){
			alert("请勾选一条信息");
			return false ;
		}else if(couterNum>1){
			alert("只能选择一项进行删除");
			return false ;
		}
		if(confirm("确定要删除?")){
			theForm.action = 'security_delSecurityConfig.do?secType='+secType  
			theForm.submit();
		}
	}
 	
 	function saveSecurityConfigInParent(secObj){
 		barEnd();
 		theForm.action="security_saveSecurityConfig.do?"+secObj;
 		theForm.submit();
 	}
 	
 	function barEnd(){
 		$.dialog.list['modSecurityConfig'].close();
 	}
 	
</script>
</head>
<body>
<s:form action="security_listSecurityConfig.do" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">类型:</td>
                    <td>
                    	<s:select list="#{'':'-请选择-','0':'-IP鉴权-','2':'-错误登陆次数-'}" name="securityConfigObj.queryType" id="queryType">
                    	</s:select>
                    </td>
                    <td class="til">状态:</td>
                    <td>
                    	<s:select list="#{'':'-请选择-','0':'-失效-','1':'-生效-'}" name="securityConfigObj.queryStatus" id="queryStatus">
                    	</s:select>
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
		    	<%-- 
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
				--%>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">安全配置值</th>
				   <th onclick="sort(theTable,2,'string')">类型</th>                
                   <th onclick="sort(theTable,3,'string')">状态</th>
                   <th onclick="sort(theTable,4,'string')">描述</th>
                   <%-- 
                   <th onclick="sort(theTable,5,'string')">更新时间</th>
			  		--%>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
					<td><input name="checkboxid" type="checkbox" secType="<s:text name="#theBean.type"/>"/></td>
					<td><s:text name="#theBean.value"/></td>
					<td>
						<s:if test="#theBean.type==0">
							IP鉴权
						</s:if>
						<s:elseif test="#theBean.type==2">
							错误登陆次数
						</s:elseif>
						<%-- 
						<s:text name="#theBean.type"/>
						 --%>
					</td>
					<td><s:if test="#theBean.status==1">
						生效
					</s:if>
					<s:elseif test="#theBean.status==0">
						失效
					</s:elseif>
					</td>
					<td><s:text name="#theBean.desc"/></td>
					<%-- 
					<td><s:text name="#theBean.upd_date"/></td>
					--%>
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
</html:html>
