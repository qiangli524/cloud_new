<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
 	function addRequest() {
 		var flag = 0;
 	    theForm.action = 'strategy_addStrategy.do?flag='+flag; 
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
 	    alert("请勾选需要修改的策略！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条策略");
 	    return false ;
 	    }
 	    var flag = 1;
 	    theForm.action = 'strategy_saveStrategy.do?flag='+flag; 
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
 	    alert("请勾选需要删除的策略！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条策略");
 	    return false ;
 	    }
 	    theForm.action = 'strategy_deleteStrategy.do';  
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
<s:form action="strategy_listStrategy.do" method="post" id="theForm">
<div class="scrollbody">
	<div class="box on">
	<div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">策略名称:</td>
                    <td>
						<s:textfield name="theForm.name" id="name" cssClass="txt"></s:textfield>
                    </td>
                    <td class="til">策略状态:</td>
                    <td>
                    	<s:select list="#{'-1':'所有','1':'有效','0':'无效'}" name="theForm.state"></s:select>
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
				   <th onclick="sort(theTable,1,'string')">策略名称</th>
				   <th onclick="sort(theTable,2,'string')">状态</th>
				   <th onclick="sort(theTable,3,'date')">有效时间</th>
                   <th onclick="sort(theTable,4,'string')">对象</th>
                   <th onclick="sort(theTable,5,'string')">性能指标</th>
                   <th onclick="sort(theTable,6,'string')">条件</th>
                   <th onclick="sort(theTable,7,'string')">创建人</th>
                   <th onclick="sort(theTable,8,'date')">创建时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.id"/>"/></td>
			  		<td> <s:property value="#theBean.name"/></td>
			  		<td> 
			  			<s:if test="#theBean.state==1">
			  				有效
			  			</s:if>
			  			<s:elseif test="#theBean.state==0">
			  				无效
			  			</s:elseif>
			  		</td>
			  		<td> <s:property value="#theBean.effectiveTime"/></td>
			  		<td> <s:property value="#theBean.object"/></td>
			  		<td> <s:property value="#theBean.kpis"/></td>
			  		<td> <s:property value="#theBean.condition"/></td>
			  		<td> <s:property value="#theBean.creater"/></td>
			  		<td> <s:property value="#theBean.creatime"/></td>
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
