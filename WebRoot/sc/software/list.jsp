<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
	<title></title>
<link href="/cloud/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="/cloud/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/cloud/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
$(function() {
	 $("[name='add']").unbind().live("click",function(){
		 var height = screen.height*0.521;
		 var width = screen.width*0.585;
		 $.dialog({
				id:'convertPage',
				title:'软件安装',
				resize:false,
				width: width+'px',
				height: height+'px',
				lock:true,
				content: 'url:software_add.do?dialogName=convertPage'});
		});
	 
	 $("[name='delete']").unbind().live("click",function(){
		 var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	   var convertId= "";  
 	    if(checkboxids!=null&&checkboxids.length>0){
 	   		 for(var i=0;i<checkboxids.length;i++){
 	      			if(checkboxids[i].checked){
 	      				couterNum = couterNum + 1 ;
 	      				convertId = checkboxids[i].getAttribute("convertId"); 
 	      			}
 	    	 }
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选需要删除功能信息！");
 	    	return false ;
 	    }else if(couterNum>1){
 	    	alert("只能删除单条数据！");
 	    	return false ;
 	    }else{
 		     if(confirm("确定要删除吗！")) { 
 		    	 var theForm = document.getElementById("theForm");
 				 theForm.action = "convert_deleteConvert.do?convertObj.id="+convertId;
 				 theForm.submit();
 		     }
 	    }
	});
	 
	 $("[name='update']").unbind().live("click",function(){
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	   var convertId= "";  
 	    if(checkboxids!=null&&checkboxids.length>0){
 	   		 for(var i=0;i<checkboxids.length;i++){
      			if(checkboxids[i].checked){
      				couterNum = couterNum + 1 ;
      				convertId = checkboxids[i].getAttribute("convertId"); 
      			}
 	    	 }
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选需要修改功能信息！");
 	    	return false ;
 	    }else if(couterNum>1){
 	    	alert("只能修改单条数据！");
 	    	return false ;
 	    }else{
	    	$.dialog({
				id:'updateConvertPage',
				title:'迁移',
				max:false,
				min:false,
				resize:false,
				width: '350px',
				height: '170px',
				content: 'url:convert_goUpdateConvert.do?convertObj.id='+convertId});
 	    }
	});
});

function searchRequest(){
	obj.action = "software_list.do";
	obj.submit();
}

function resetForm(theForm){
	$("#ip").attr("value","");
	$("#description").attr("value","");
}


function closeDialog(dialogName){
	 $.dialog.list[dialogName].close();
}

function startInstall(url){
	$.ajax({
			type:"post",
			url:url,
			dataType:"json",
			async:false,
			cache:false,
			success:function(data){
				searchRequest();
			}
		});
}

function updateConvert(id,state){
	closeDialog("updateConvertPage");
	var theForm = document.getElementById("theForm");
	theForm.action = "convert_updateConvert.do?convertObj.id="+id+"&convertObj.state="+state;
	theForm.submit();
}


</script>
<style type="text/css">
</style>
</head>
<body>
<s:form action="software_list.do" method="post" id="obj">
	<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">软件安装</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
		 <label  class="vm">IP地址：</label>
						<s:textfield id="ip" name="obj.ip"></s:textfield>
			 <label  class="vm">描述：</label>
						<s:textfield  id="description" name="obj.description"></s:textfield>
					<span class="ubtn-1 mgl-20"><input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input id="resetForm" type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
		<div class="utt-2 mgt-20">
				<a name="add" class="icon-add" href="javascript:void(0)" >安装</a>
	 	 	
		
		</div>
			<table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
				<thead>
					<tr>
						<th onclick="sort(theTable,0,'string')">选项</th>
						<!-- 
						<th onclick="sort(theTable,1,'string')">名称</th>
						 -->
						<th onclick="sort(theTable,1,'string')">IP地址</th>
						<th onclick="sort(theTable,2,'string')">状态</th>
						<th onclick="sort(theTable,3,'string')">安装软件</th>
						<th onclick="sort(theTable,4,'string')">开始时间</th>
						<th onclick="sort(theTable,5,'string')">完成时间</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" convertId='<s:property value="#theBean.id"/>'/></td>
							<!-- 
							<td>
								<s:property value="#theBean.name"/>
							</td>
							 -->
							<td>
								<s:property value="#theBean.ip"/>
							</td>
							<td>
								<s:if test="#theBean.status==1">
										完成
								</s:if>
								<s:else>
										<img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18" style="margin-right: -60px;"/>进行中
								</s:else>
							</td>
							<td>
								<s:property value="#theBean.description"/>
	                        </td>
                            <td><s:property value="#theBean.start_time"/></td>
                            <td><s:property value="#theBean.end_time"/></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
	</div>
</s:form>
</body>
