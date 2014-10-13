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
 	      theForm.strategy_id.value = checkboxids[i].value;
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
 	    if(confirm("确认删除?")){
	 	    theForm.action = 'strategy_deleteStrategy.do';  
			theForm.submit();
 	    }
 	}
 	function resetForm(theForm){
		theForm.name.value = '';
		theForm.enable.value= "-1";
	}

	function searchRequest() {  
 	    theForm.action = 'strategy_listStrategy.do';
		theForm.submit();
 	}
 	function list() {
		theForm.submit();
 	}
 	
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
    		$.dialog({
    			id:'add',
    			title:'新增策略',
    			width: '800px',
    			height: '500px',
    			max: true,
    		    min: true,
    			content: 'url:strategy_addStrategy.do'
    		});
         });
         
         $("[name='mod']").unbind().live("click",function(){
         	var couterNum = 0;
	 	    var checkboxids = document.getElementsByName("checkboxid");
	 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
			 	      couterNum = couterNum + 1 ;
			 	      theForm.strategy_id.value = checkboxids[i].value;
		 	      }
		 	    }
	 	    }
	 	    if(couterNum==0){
		 	    alert("请勾选需要修改的策略！");
		 	    return false ;
	 	    } 
    		$.dialog({
    			id:'mod',
    			title:'修改策略',
    			width: '800px',
    			height: '500px',
    			max: true,
    		    min: true,
    			content: 'url:strategy_updateStrategy.do?' + $("#theForm").serialize()
    		});
         });
     });
 	
 	function saveStrategy(url){
 		 $.ajax({
			 type:"POST",
             url:url,
             dataType:"json",
             async: true,
             cache: false,
	          success: function(msg){
               list();
	          }
		});
 	}
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
<s:form action="strategy_listStrategy.do" method="post" id="theForm">
<s:hidden id="strategy_id" name="theForm.strategy_id"></s:hidden>
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
                    	<s:select list="#{'-1':'所有','1':'有效','0':'无效'}" name="theForm.enable" id="enable"></s:select>
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
				   <th onclick="sort(theTable,1,'string')">策略名称</th>
				   <th onclick="sort(theTable,2,'string')">动作</th>
				   <th onclick="sort(theTable,3,'string')">对象</th>
                   <th onclick="sort(theTable,4,'string')">性能指标</th>
                   <th onclick="sort(theTable,5,'string')">触发条件</th>
				   <th onclick="sort(theTable,6,'string')">状态</th>
				   <th onclick="sort(theTable,7,'date')">有效时间</th>
                   <th onclick="sort(theTable,8,'string')">创建人</th>
                   <th onclick="sort(theTable,9,'date')">创建时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr>
			  		<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.strategy_id"/>"/></td>
			  		<td> <s:property value="#theBean.strategy_name"/></td>
			  		<td> 
			  		<s:if test="#theBean.excute==0">
			  				创建虚拟机
			  			</s:if>
			  			<s:elseif test="#theBean.excute==1">
			  				调整虚拟机
			  			</s:elseif>
			  			<s:elseif test="#theBean.excute==2">
			  				删除虚拟机
			  			</s:elseif>
			  			<s:elseif test="#theBean.excute==3">
			  				迁移虚拟机
			  			</s:elseif>
			  		</td>
			  		<td> <s:property value="#theBean.entity_name"/></td>
			  		<td> <s:property value="#theBean.kpi_id"/></td>
			  		<td width="400"> 
			  			<div class="hidden" title='<s:property value="#theBean.content"/>'>
			  				<s:property value="#theBean.content"/></a>
			  			</div> 
			  		</td>
			  		<td> 
			  			<s:if test="#theBean.enable==1">
			  				有效
			  			</s:if>
			  			<s:elseif test="#theBean.enable==0">
			  				无效
			  			</s:elseif>
			  		</td>
			  		<td> <s:property value="#theBean.effect_date"/></td>
			  		<td> <s:property value="#theBean.creater"/></td>
			  		<td> <s:property value="#theBean.ins_date"/></td>
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
