<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ page import="com.sitech.basd.yicloud.domain.opersystem.OperSystemObj" %>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.queryName.value = '';
		theForm.QUERYTYPE.value = '';
		theForm.QUERYOPERTYPE.value = '';
	}

   function searchRequest() { 
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
 	    alert("请勾选需要修改操作系统信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条操作系统信息");
 	    return false ;
 	    }
 	    theForm.action = 'opersystem_modOperSystem.do' 
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
 	    alert("请勾选需要删除操作系统信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条操作系统信息");
 	    return false ;
 	    }
 	    theForm.action = 'opersystem_delOperSystem.do'  
		theForm.submit();
 	}
 
	$(function(){
		$("[name=link]").unbind().live("click",function(){
				var opersysid = $(this).attr("opersysid");
				location.href = "opersystem_downLoadFile.do?opersysid="+opersysid;
		});
	});	
 	
</script>
</head>
<body>
<s:form action="opersystem_listOperSystemInfo.do" method="post" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
		<div class="query">
			</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">操作系统文件名称:</td>
                    <td>
                    	<s:textfield name="theForm.queryName" id="queryName" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">类别</td>
                   	<td>
                   		<s:select list="#{'':'请选择','0':'物理主机','1':'虚拟机'}" name="theForm.QUERYTYPE" id="QUERYTYPE"></s:select>
                   	</td>
                   	<td class="til">
						操作系统类型
					</td>
					<td>
						<s:select list="#{'':'请选择','0':'UNIX','1':'LINUX','2':'HP LINUX','3':'AIX','4':'WINDOWS'}" name="theForm.QUERYOPERTYPE" id="QUERYOPERTYPE"></s:select>
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
		          
				<!--<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>-->
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
				   <th onclick="sort(theTable,1,'string')">操作系统编号</th>
				   <th onclick="sort(theTable,2,'string')">操作系统名称</th>      
				   <th onclick="sort(theTable,3,'string')">类别</th> 
				   <th onclick="sort(theTable,4,'string')">操作系统类型</th>          
                   <th onclick="sort(theTable,5,'string')">操作系统版本</th>
                   <th onclick="sort(theTable,6,'date')">上传时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.ID"/>"/></td>
			  		<td><s:property value="#theBean.ID"/></td>
			  		<td>
			  			<a href="javascript:;" opersysid='<s:property value="#theBean.ID"/>' name="link">
			  				<s:property value="#theBean.NAME"/>
			  			</a>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.TYPE==0">物理主机</s:if>
			  			<s:elseif test="#theBean.TYPE==1">虚拟机</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.OPERTYPE==0">UNIX</s:if>
			  			<s:elseif test="#theBean.OPERTYPE==1">LINUX</s:elseif>
			  			<s:elseif test="#theBean.OPERTYPE==2">HP LINUX</s:elseif>
			  			<s:elseif test="#theBean.OPERTYPE==3">AIX</s:elseif>
			  			<s:elseif test="#theBean.OPERTYPE==4">WINDOWS</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.VERSION"/></td>
			  		<td><s:property value="#theBean.UPDATETIME"/></td>
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
