<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript">
	  
 	function delRequest() {
 		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.trigger_id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除的记录！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条记录");
 	    return false ;
 	    }
 	    theForm.action = 'trigger_delTriggerDefinition.do';  
		theForm.submit();
 	}
 	function resetForm(theForm){
		theForm.name.value = '';
		theForm.type.value = '-1';
		theForm.level.value='-1';
	}

	function searchRequest() { 
 	    theForm.action = 'trigger_listTriggerDefinition.do';
		theForm.submit();
 	}
 	
 	function list() {
		theForm.submit();
 	}
 	
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
    		$.dialog({
    			id:'add',
    			title:'新增触发器',
    			width: '800px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:trigger_addTriggerDefinition.do'
    		});
        });
        
        $("[name='mod']").unbind().live("click",function(){
        	var couterNum = 0;
	 	    var checkboxids = document.getElementsByName("checkboxid");
	 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      	couterNum = couterNum + 1 ;
		 	      	theForm.trigger_id.value = checkboxids[i].value;
		 	      }
		 	    }
	 	    }
	 	    if(couterNum==0){
	 	   	 	alert("请勾选需要修改的记录！");
	 	    	return false ;
	 	    } 
	 	    
    		$.dialog({
    			id:'mod',
    			title:'修改触发器',
    			width: '800px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:trigger_updateTriggerDefinition.do?' + $("#theForm").serialize()
    		});
        });
     });

</script>
 <style type="text/css">
		div.hidden{
		width:400px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
</head>
<body onLoad="self.focus();document.theForm.name.focus()">
<s:form action="trigger_listTriggerDefinition.do" method="post" id="theForm">
<s:hidden name="theForm.trigger_id" id="trigger_id"></s:hidden>
<div class="scrollbody">
	<div class="box on">
	<div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">触发器名称:</td>
                    <td>
						<s:textfield name="theForm.name" id="name" cssClass="txt"></s:textfield>
                    </td>
                     <td class="til">指标:</td>
                    <td>
                    	<s:select list="#{'0':'CPU','1':'内存','2':'存储','3':'网络','4':'其他'}" name="theForm.type" headerKey="-1" headerValue="--请选择--" id="type"></s:select>
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
				   <th onclick="sort(theTable,1,'string')">触发器名称</th>
				   <th onclick="sort(theTable,2,'string')">指标</th>
				   <th onclick="sort(theTable,3,'string')">触发器内容</th>
				   <th onclick="sort(theTable,4,'string')">描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value='<s:property value="#theBean.trigger_id"/>'/></td>
			  		<td> <s:property value="#theBean.name"/> </td>
			  		<td> 
						<s:if test="#theBean.type==0">
			  				CPU
			  			</s:if>
			  			<s:elseif test="#theBean.type==1">
			  				内存
			  			</s:elseif>
			  			<s:elseif test="#theBean.type==2">
			  				存储
			  			</s:elseif>
			  			<s:elseif test="#theBean.type==3">
			  				网络
			  			</s:elseif>
			  			<s:elseif test="#theBean.type==4">
			  				其他
			  			</s:elseif>
			  		</td>  
			  		<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.content"/>'>
			  				<s:property value="#theBean.content"/></a>
			  			</div> 
			  		</td>
			  		<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.description"/>'>
			  				<s:property value="#theBean.description"/></a>
			  			</div> 
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
