<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
	$(function(){
      $("[name='all']").click(function(){
   	 	var currentState=$(this).attr("checked");
 	 	if(currentState){
 	 		$("[name='checkboxid']").attr("checked",true);
 	 	}else{
 	 		$("[name='checkboxid']").attr("checked",false);
      	}

       });
	
        $("[name='addorder']").click(function(){
        	var appid = $("#entityId").val();
		  		$.dialog({
	    			id:'editorder',
	    			title:'创建订单',
	    			width: '520px',
	    			height: '300px',
	    		    lock:true,
	    			content: 'url:treeorder_addNewOrder.do?oper=add&appid='+appid
		    	});
        });
        
        
        $("[name='quickAddorder']").click(function(){
        	var appid = $("#entityId").val();
		  		$.dialog({
	    			id:'quickcreateorder',
	    			title:'快速创建订单与任务',
	    			width: '920px',
	    			height: '500px',
	    			lock:true,
	    			content: 'url:treeorder_quickAddNewOrder.do?oper=add&appid='+appid
		    	});
        });
        
        $("[name='editorder']").unbind().live("click",function(){
        	var $td=$(this).parent();
        	var orderid=$td.attr("orderid");
		  		$.dialog({
	    			id:'editorder',
	    			title:'编辑订单',
	    			width: '520px',
	    			height: '300px',
	    		    lock:true,
	    			content: 'url:treeorder_addNewOrder.do?oper=edit&orderid='+orderid
		    	});
        });
        
        $("[name='delorder']").unbind().live("click",function(){
        	var $td=$(this).parent();
        	var orderid=$td.attr("orderid");
           mask('正在删除订单....','0.5','0px');
           $.ajax({
            type: "GET",
            url: "treeorder_delOrder.do?orderid="+orderid+"&time"+new Date().toString(),
            dataType: "json",
            success : function(data){
	          removeMask();
              if(data.result==1){
            	  $td.parent().remove();
              }else if(data.result==2){
            	  alert("订单下面有任务，请先删除任务在操作订单!");
            	  return false;
              }else{
                 alert("订单删除失败!");
                 return false;
              }
              }
          });
        });

        $("#file_list").unbind().live("click",function(){
        	var orderid=$(this).attr("orderid");
	        	$.dialog({
	    			id:'look',
	    			title:'查看',
	    			width: '530px',
	    			height: '300px',
	    		    lock:true,
	    			content: 'url:treeorder_queryFileList.do?orderid='+orderid
		    	});
         });
        
        $("[name='add_task']").unbind().live("click",function(){
        	var $td=$(this).parent();
        	var orderid=$td.attr("orderid");
        	var order_id=$td.attr("order_id");
	        	$.dialog({
	    			id:'addtask11',
	    			title:'管理任务',
	    			width: '900px',
	    			height: '500px',
	    		    button: [
	    		             {
	    		                 name: '确定',
	    		                 callback:function(){
	    		                	 $("#theForm").attr("action","treeorder_listOrder.do?");
	    					         $("#theForm").submit();
	    		                 },
	    		                 force:true
	    		             }
	        	      ],
	    			content: 'url:treetask_getTaskRelationOrder.do?userType=orderAddTask&orderid='+orderid+"&entityId="+$("#entityId").val()+"&id="+$("#id").val()+"&ORDER_ID="+order_id,
	    			close:function(){
	                	 $("#theForm").attr("action","treeorder_listOrder.do?");
				         $("#theForm").submit();
	                 }	
		    	});
         });
        
        $("[name='orderdeploy']").click(function(){
       	  if($(":checkbox:checked").length==0){
             alert("请选择一个订单来部署!");
             return false;
          }
     	  var orderids ="";//订单编号
     	  var flag=true;
       	  $(":checkbox:checked").each(function(){
       		  orderids+=$(this).attr("orderid")+",";
       		  if($(this).attr("taskcount")==0){
       			flag=false;
       		  }
          });
       	  if(flag){//判断订单下是否有任务，没有任务的不允许提交
       		mask('正在上线订单....','0.5','0px');
		      $.ajax({
		            type: "GET",
		            url: "treeorder_submitOrderToDeploy.do?orderids="+orderids+"&time"+new Date().toString(),
		            dataType: "json",
		            success : function(data){
			           removeMask();
			           if(data.result==1){
			        	  $("#theForm").attr("action","treeorder_listOrder.do?");
				          $("#theForm").submit();
			        	  alert("订单提交成功");
			           }else if(data.result==2){
			              alert("订单已经完成,或已提交,或者下面有处理中的任务,无法继续提交.");
			           }else{
			        	   alert("提交失败!");
			           }
		           }
		        });
       	  }else{
       		  alert("订单下无可上线任务,无法提交.");
       	  }
        });
        
        $("[name='shuaxin']").click(function(){
        	$("#theForm").submit();
        });
        
        $("[name='qzcancel']").click(function(){
            if($(":checkbox:checked").length==0){
                alert("请选择一项编辑.");
                return false;
             }else if($(":checkbox:checked").length>1){
             	alert("只能选择一项编辑.");
                 return false;
             }
	       	  var orderids ="";//订单编号
	       	  $(":checkbox:checked").each(function(){
	       		  orderids+=$(this).attr("orderid")+",";
	          });
        	if (confirm("强制取消会重置所选订单状态为初始状态,确定次此操作吗?") == true){
        		mask('强制重置订单状态中....','0.5','0px');
  		       $.ajax({
  		            type: "GET",
  		            url: "treeorder_qzCancelOrderStatus.do?orderids="+orderids+"&time"+new Date().toString(),
  		            dataType: "json",
  		            success : function(data){
  			           removeMask();
  			           if(data.result==1){
  			        	  $("#theForm").attr("action","treeorder_listOrder.do?");
  				          $("#theForm").submit();
  			        	  alert("订单状态重置成功.");
  			           }else{
  			        	   alert("提交失败!");
  			           }
		  		     }
		  		  });
        		
        	}
        });
        
	});
	
    function saveOrder(theform,oper){
    	if("add"==oper){
    	   mask('正在添加订单....','0.5','0px');
    	}else if("edit"==oper){
    	   mask('正在修改订单....','0.5','0px');
    	}else{
    		mask('正在操作订单....','0.5','0px');
    	}
       	 $.ajax({
            type: "GET",
            url: "treeorder_saveOrder.do?"+theform+"&oper="+oper,
            dataType: "json",
           // data:{"effecttime":effect_time,"filelist":file_list,"authorization":authorization,"oper":oper,"appid",appId},
            success : function(data){
				removeMask();
				$("#theForm").submit();
              }
          });
       }
       
       
       //快速创建订单和任务
      // quickSaveOrder(effect_time,file_list,versionTarPath,versionids);
       function quickSaveOrder(effect_time,versionTarPath,versionids,taskTactiStrs){
    	  mask('正在创建订单....','0.5','0px');
       	 $.ajax({
            type: "POST",
            url: "treeorder_quickSaveOrder.do",
            dataType: "json",
            data:{"orderObj.EFFECT_TIME":effect_time,"orderObj.VERSIONID":versionids,"orderObj.versionPath":versionTarPath,"taskTactiStrs":taskTactiStrs},
            success : function(data){
				removeMask();
				$("#theForm").submit();
              }
          });
       }
	
</script>
</head>
<body  class="pop-body scrollbody">
<s:form action="treeorder_listOrder" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="entityId" id="entityId"></s:hidden>
<s:hidden name="id" id="id"></s:hidden>
		<div class="scrollbody">
					<div class="utt-2">
					    <a class="icon-add" href="javascript:void(0)"  name="quickAddorder">快速添加</a>
						<a class="icon-add" href="javascript:void(0)"  name="addorder">添加</a>
						<a class="icon-modify" href="javascript:void(0)" name="orderdeploy" >发布</a>
						<a class="icon-del" href="javascript:void(0)" name="qzcancel" >强制取消</a>
						<a class="icon-del" href="javascript:void(0)" name="shuaxin" >刷新</a>
					</div>
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0" name="ordertable">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										订单编号
									</th>
									<th onclick="sort(theTable,2,'string')">
										文件清单
									</th>
									<th onclick="sort(theTable,3,'date')">
										订单有效时间
									</th>
									<th onclick="sort(theTable,4,'int')">
										任务总数
									</th>
									<th onclick="sort(theTable,5,'string')">
										待处理
									</th>
									<th onclick="sort(theTable,6,'string')">
										处理中
									</th>
									<th onclick="sort(theTable,7,'string')">
										处理完成
									</th>
									<th onclick="sort(theTable,8,'date')">
										创建时间
									</th>
									<th onclick="sort(theTable,9,'string')">
										任务
									</th>
									<th>
										操作
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
				                  <tr>
								    <td>
										<input type="checkbox" name="checkboxid" orderid='<s:property value="#theBean.ID"/>' taskcount='<s:property value="#theBean.TASK_COUNT"/>'/>
									</td>
									<td>
										<s:property value="#theBean.ORDER_ID" default="-"/>
									</td>
									<td>
									    <a href="javascript:;" id="file_list" orderid='<s:property value="#theBean.ID"/>'>查看</a>
									</td>
									<td>
									   <s:property value="#theBean.EFFECT_TIME" default="-"/>
									</td>
									<td name="taskcount" orderid='<s:property value="#theBean.ID"/>'>
									  <s:property value="#theBean.TASK_COUNT" default="-"/>
									</td>
									<td>
									  <s:property value="#theBean.TASK_WAIT_COUNT" default="-"/>
									</td>
									<td>
									  <s:property value="#theBean.TASK_DEAL_COUNT" default="-"/>
									</td>
									<td>
									  <s:property value="#theBean.TASK_COMPLETE_COUNT" default="-"/>
									</td>
									<td>
									    <s:property value="#theBean.ADD_TIME" default="-"/>
									</td>
									<td orderid='<s:property value="#theBean.ID"/>' order_id='<s:property value="#theBean.ORDER_ID"/>'>
									   <a href="javascript:;" name="add_task" >管理</a>
									</td>
									<td orderid='<s:property value="#theBean.ID"/>'>
									   <a href="javascript:;" name="editorder" >编辑</a> <a href="javascript:;" name="delorder" >删除</a>
									</td>
								</tr>
								</s:iterator>
							</tbody>
						</table>
					<div class="pages mgb-10"><!-- 分页 -->
						<jsp:include page="../../../sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
		</div>
</s:form>
</body>
