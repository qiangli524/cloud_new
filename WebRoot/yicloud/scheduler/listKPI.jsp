<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript">
	 
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.kpi_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选一条信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条KPI");
 	    return false ;
 	    }
 	   	$.dialog.confirm('你确定要删除吗？', function(){
 	    	theForm.action = 'kpi_deleteKPI.do';  
			theForm.submit();
 	    });
 	}
 	function resetForm(theForm){
		theForm.queryID.value = '';
		theForm.kpi_desc.value = '';
		theForm.kpi_type.value = '';
	}

	function searchRequest() {  
 	    theForm.action = 'kpi_listKPI.do';
		theForm.submit();
 	}
 	
 	
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
    		$.dialog({
    			id:'add',
    			title:'新增KPI',
    			width: '800px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:kpi_addKPI.do'
    		});
         });
         
         $("[name='mod']").unbind().live("click",function(){
         	var couterNum = 0;
	 	    var checkboxids = document.getElementsByName("checkboxid");
	 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
			 	      couterNum = couterNum + 1 ;
			 	      theForm.kpi_id.value = checkboxids[i].value;
		 	      }
		 	    }
	 	    }
	 	    if(couterNum==0){
		 	    alert("请勾选一条信息！");
		 	    return false ;
	 	    } 
	 	     
    		$.dialog({
    			id:'mod',
    			title:'修改KPI',
    			width: '800px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:kpi_updateKPI.do?' + $("#theForm").serialize()
    		});
         });
     });
</script>
</head>
<body onLoad="self.focus();document.theForm.queryID.focus()">
<s:form action="kpi_listKPI.do" method="post" id="theForm">
<s:hidden id="kpi_id" name="theForm.kpi_id"/>
<div class="scrollbody">
	<div class="box on">
	<div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">KPI名称:</td>
                    <td>
						<s:textfield name="theForm.query_id" id="queryID" cssClass="txt"></s:textfield>
                    </td>
                     <td class="til">指标类型:</td>
                    <td>
                    	<s:select list="#{'0':'CPU','1':'内存','2':'存储','3':'网络','4':'其他'}" name="theForm.kpi_type" headerKey="" headerValue="--请选择--" id="kpi_type"></s:select>
                    </td>
                    <td class="til">KPI描述:</td>
                    <td>
						<s:textfield name="theForm.kpi_desc" id="kpi_desc" cssClass="txt"></s:textfield>
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
		          
				<li><input type="button" class="thickbox btn-style02" value="增加" name = "add" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" name = "mod" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest();return false;" /></li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
			  	   <th>选择</th>
				   <th onclick="sort(theTable,1,'string')">KPI名称</th>
				   <th onclick="sort(theTable,2,'string')">指标类型</th>
				   <th onclick="sort(theTable,3,'string')">KPI权重</th>
				   <th onclick="sort(theTable,4,'string')">KPI描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.kpi_id"/>"/></td>
			  		<td> <s:property value="#theBean.kpi_id"/></td>
			  		<td> 
						<s:if test="#theBean.kpi_type==0">
			  				CPU
			  			</s:if>
			  			<s:elseif test="#theBean.kpi_type==1">
			  				内存
			  			</s:elseif>
			  			<s:elseif test="#theBean.kpi_type==2">
			  				存储
			  			</s:elseif>
			  			<s:elseif test="#theBean.kpi_type==3">
			  				网络
			  			</s:elseif>
			  			<s:elseif test="#theBean.kpi_type==4">
			  				其他
			  			</s:elseif>
			  		</td>
			  		<td> <s:property value="#theBean.kpi_weight"/></td>
			  		<td> <s:property value="#theBean.kpi_desc"/></td>
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
