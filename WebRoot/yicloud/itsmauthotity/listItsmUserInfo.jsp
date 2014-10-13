<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	var basePath  = '<%=request.getScheme()%>' +"://" + '<%=request.getServerName()%>' +":"+ '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>' +"/";

	function resetForm(theForm){
		theForm.ACCOUNT.value = '';
		theForm.NAME.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	
 	$(function(){
 		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
         $("[name='hostauth']").click(function(){
         	$td=$(this).parent();
            var USERID=$td.attr("USERID");
            var ACCOUNT=$td.attr("ACCOUNT");
    	 	$.dialog({
    			id:'add',
    			title:'权限设置',
    			width: '800px',
    			height: '550px',
    			max: true,
    			min: true,
    			content: 'url:'+basePath+'vmauth_editItsmUserVirAuthority.do?USERID='+USERID+'&ACCOUNT='+ACCOUNT+'&flag=host'
    	 	});
         });
         $("[name='vmauth']").click(function(){
         	$td=$(this).parent();
            var USERID=$td.attr("USERID");
            var ACCOUNT=$td.attr("ACCOUNT");
    	 	$.dialog({
    			id:'add',
    			title:'权限设置',
    			width: '800px',
    			height: '550px',
    			max: true,
    			min: true,
    			content: 'url:'+basePath+'vmauth_editItsmUserVirAuthority.do?USERID='+USERID+'&ACCOUNT='+ACCOUNT+'&flag=vm'
    	 	});
         });
    });
</script>
</head>
<body onLoad="self.focus();document.theForm.ACCOUNT.focus()" class="pop-body scrollbody">
<s:form action="vmauth_listITSMUserInfo" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">账号:</td>
                    <td>
                    	<s:textfield name="userForm.ACCOUNT" id="ACCOUNT" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">姓名:</td>
                    <td>
                  	  <s:textfield name="userForm.NAME" id="NAME" cssClass="txt"></s:textfield>
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
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">账号</th>                
                   <th onclick="sort(theTable,1,'string')">密码</th>
                   <th onclick="sort(theTable,2,'string')">姓名</th>
                   <th onclick="sort(theTable,3,'string')">状态</th>
                   <th>主机虚拟机权限分配</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="userForm.resultList" id="theBean">
						<tr>
							<td><s:text name="#theBean.ACCOUNT"/></td>
							<td>
							<s:text name="#theBean.PASSWORD"/></td>
							<td><s:text name="#theBean.NAME"/></td>
							<td>
							<s:if test="#theBean.STATUS==0">
							冻结
							</s:if>
							<s:elseif test="#theBean.STATUS==1">
							活跃
							</s:elseif>
							<s:elseif test="#theBean.STATUS==2">
							锁定
							</s:elseif>
							</td>
							<td USERID='<s:text name="#theBean.ID"/>'  ACCOUNT='<s:text name="#theBean.ACCOUNT"/>'>
							<a href="javascript:;" name="hostauth">
								主机权限配置
							</a>
							<a href="javascript:;" name="vmauth">
								虚拟机权限配置
							</a>
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
