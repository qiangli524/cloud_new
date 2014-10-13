<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<html:html locale="true">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	});	
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		$("#extEqId").attr("value","");
		$("#kpiName").attr("value","");
		$("#userId").attr("value","");
	}

   function searchRequest() { 
		theForm.submit();
 	}

   $(function(){
   	$("[name='add']").click(function(){
		$.dialog({
			id:'add',
			title:'添加监控配置',
			width: '700px',
			height: '400px',
			max: true,
		    min: true,
		    lock:true,
			content: 'url:monitorCfg_addInfo.do'
			});
         });
         
       $("[name='mod']").click(function(){
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
    	    alert("请勾选一条信息");
    	    return false ;
    	    }else if(couterNum>1){
    	    alert("一次只能修改一条信息");
    	    return false ;
    	    }
		$.dialog({
			id:'add',
			title:'修改监控配置',
			width: '700px',
			height: '400px',
			max: true,
		    min: true,
		    lock:true,
			content: 'url:monitorCfg_updateInfo.do?id=' + $("#id").val()
			});
         });
});
	
 	function addRequest() {
 	    theForm.action = 'monitorCfg_addInfo.do' 
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
 	    alert("请勾选一条信息");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除一条信息");
 	    return false ;
 	    }
 	   if(confirm("确定要删除?")){
 		  var url = 'monitorCfg_delInfo.do?id=' + $("#id").val(); 
 		  $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
	          if(msg == '1'){
		          alert('删除成功！');
	        	  theForm.action = 'monitorCfg_queryList.do';  
		 		  theForm.submit();
		 		  }else{
			 		  alert('删除失败！');}
              }
		});
 	 	   
 	    	
		}
 	}
</script>
</head>
<body>
<div class="mainbody">
<s:form action="monitorCfg_queryList.do" method="post" cssStyle="theForm" id="theForm">
	<s:hidden name="id" id="id"></s:hidden>
		 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">监控配置管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">主机名称：</label>
				<s:textfield name="obj.extEqName" id="extEqName" cssClass="inpt-1 vm" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">指标名称：</label>
				<s:textfield name="obj.kpiName" id="kpiName" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<label class="mgl-20 vm">用户名：</label>
				<s:textfield name="obj.userId" id="userId" maxlength="30" cssClass="inpt-1 vm" ></s:textfield>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)"
							value="增加" name="add" >增加</a>
						<a class="icon-modify" href="javascript:void(0)"
							value="修改" name="mod">修改</a> 
						<a class="icon-del" href="javascript:void(0)" 
							value="删除" onclick = "javascript:delRequest();">删除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th onclick="sort(theTable,0,'string')">主机名称</th>  
                   <th onclick="sort(theTable,1,'string')">主机IP</th>
                   <th onclick="sort(theTable,2,'string')">用户名</th>
                   <th onclick="sort(theTable,3,'string')">指标名称</th>
                   <th onclick="sort(theTable,4,'string')">指标配置值</th>
                   <th onclick="sort(theTable,5,'string')">业务描述</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean. id"/>"/></td>
							<td><s:property value="#theBean.extEqName"/></td>
							<td><s:property value="#theBean.hostIp"/></td>
							<td><s:property value="#theBean.userId"/></td>
							
							<td>
					  			<s:if test="#theBean.kpiId=='PM-01-01-001-01'">话务量监控</s:if>
					  			<s:elseif test="#theBean.kpiId=='PM-01-01-001-02'">目录文件积压量</s:elseif>
					  			<s:elseif test="#theBean.kpiId=='PM-01-01-001-03'">错误日志监控</s:elseif>
					  			<s:elseif test="#theBean.kpiId=='PM-01-01-001-04'">流量查询服务监控</s:elseif>
					  			<s:elseif test="#theBean.kpiId=='PM-01-01-001-05'">端口收发监控</s:elseif>
					  			<s:elseif test="#theBean.kpiId=='PM-01-01-001-06'">提醒服务监控</s:elseif>
					  		</td>	
							
							<td><s:property value="#theBean.kpiCfgValue"/></td>
							<td><s:property value="#theBean.busiDesc"/></td>
							
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
</s:form>
</div>
</body>
</html:html>
