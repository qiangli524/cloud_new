<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_wx.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/alai_tree_check.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/home/ui/njs/ui/ued_ui.js"></script>
<title></title>

	<style type="text/css">
		div.hidden{
		width:50px;
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

<script type="text/javascript">
//不要再这里面加弹出框的按钮，这样会导致此页面无法使用
//请按照其他方法进行引入
	var api="";
	var w="";
	$(function(){
	    //使用者根据用户类型自己指定参数
	    var userType = '<%=request.getAttribute("userType")%>';
	    var backupType = '<%=request.getAttribute("backupType")%>';
		//根据用户类型来初始化页面的展现方式
		if("orderAddTask"==userType){//订单中添加任务的界面
			api = frameElement.api;
			w = api.opener;
			$("[name='edit_delete']").show();
			$("#oper_task").show();
		} else{//树上直接调用
	        $.getScript("<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default");
			$(":checkbox").unbind().live("click",function(){
				$(":checkbox:checked").attr("checked",false);
				$(this).attr("checked",true);
			});
		}
		//显示 通过 backupType 显示回滚按钮
		if(backupType==1){//全量备份
			$("#all_rollBack").show();
			$("#part_rollBack").show();//部署实例全部删除
		}else if(backupType==2){//无备份
			$("#all_rollBack").hide();
			$("#part_rollBack").hide();
		}else{//增量备份
			$("#all_rollBack").hide();
			$("#part_rollBack").show();
		}
		//其他用户类型请自己定义else 并请使用jquery控制页面元素的展示
        $("[name='addtask']").click(function(){
        	if("orderAddTask"==userType){
            	w.$.dialog({
        			id:'createtask',
        			title:'创建任务',
        			width: '520px',
        			height: '300px',
        			lock:true,
        			content: 'url:treetask_addNewTask.do?oper=add'
    	    		});
        	}else{
            	$.dialog({
        			id:'createtask',
        			title:'创建任务',
        			width: '520px',
        			height: '300px',
        			lock:true,
        			content: 'url:treetask_addNewTask.do?oper=add'
    	    		});
        	}
        });
        
        $("[name='edittask']").unbind().live("click",function(){
            $td=$(this).parent();
            var taskid=$td.attr("taskid");
            if("orderAddTask"==userType){
    	  		w.$.dialog({
        			id:'createtask',
        			title:'编辑任务',
        			width: '520px',
        			height: '300px',
        			lock:true,
        			content: 'url:treetask_addNewTask.do?oper=edit&taskid='+taskid
    	    		});
            }else{
    	  		$.dialog({
        			id:'createtask',
        			title:'编辑任务',
        			width: '520px',
        			height: '300px',
        			lock:true,
        			content: 'url:treetask_addNewTask.do?oper=edit&taskid='+taskid
    	    		});
            }
        });
        
        $("[name='deltask']").unbind().live("click",function(){
           $td=$(this).parent();
           var taskid=$td.attr("taskid");
           mask('正在删除任务....','0.5','0px');
           $.ajax({
	            type: "GET",
	            url: "treetask_delTask.do?taskid="+taskid+"&time"+new Date().toString(),
	            dataType: "json",
	            success : function(data){
		          removeMask();
	              if(data.result==1){
	            	  $td.parent().remove();
	              }else if(data.result==2){
	            	  alert("任务在处理中,暂时无法删除.");
	              }else{
	                  alert("删除失败!");
	              }
              }
          });
        });
        
        $("[name='executetask']").unbind().live("click",function(){
            if($(":checkbox:checked").length==0){
                alert("请至少选择一项来执行!");
                return false;
             }
            var taskids="";
       		$(":checkbox:checked").each(function(){
       			taskids+=$(this).attr("taskid")+","; 
        	 });
            $.ajax({
             type: "GET",
             url: "treetask_executeTask.do?taskids="+taskids+"&time"+new Date().toString(),
             dataType: "json",
             success : function(data){
               if(data.result==1){
                  alert("执行成功!");
               }else if(data.result==2){
            	  alert("需要执行的任务有已经正在执行的任务，不能重复执行!")
               }else{
            	   alert("执行失败!")
               }
               }
           });
         });
     

        $("#example_count").unbind().live("click",function(){
        	var taskid=$(this).parents("td").attr("taskid");
        	if("orderAddTask"==userType){
            	w.$.dialog({
        			id:'exampleLook',
        			title:'部署实例查看',
        			width: '730px',
        			height: '500px',
        			lock:true,
        			content: 'url:dep_listExamplesInTreeByTaskId.do?app_id='+$("#appid").val()+"&task_id="+taskid+"&userType=tasklookexample"
    	    	});
        	}else{
            	$.dialog({
        			id:'exampleLook',
        			title:'部署实例查看',
        			width: '730px',
        			height: '500px',
        			lock:true,
        			content: 'url:dep_listExamplesInTreeByTaskId.do?app_id='+$("#appid").val()+"&task_id="+taskid+"&userType=tasklookexample"
    	    	});
        	}
        	return false;
         });
        
        $("[name='look']").click(function(){
        	var taskid=$(this).parent().attr("taskid");
        	var orderid = $("#orderid").attr("value");
        	var task_id = $(this).parent().attr("task_id");
        	if("orderAddTask"==userType){
            	w.$.dialog({
        			id:'look',
        			title:'任务报告',
        			width: '800px',
        			height: '400px',
        			lock:true,
        			/* content: 'url:treetask_taskLogList.do?taskid='+taskid+'&orderid='+orderid+'&task_id='+task_id */
        			content: 'url:treetask_taskLogList.do?task_id='+task_id+'&orderid='+orderid
    	    	});
        	}else{
            	$.dialog({
        			id:'look',
        			title:'任务报告',
        			width: '800px',
        			height: '400px',
        			lock:true,
        			content: 'url:treetask_taskLogList.do?taskid='+taskid+'&orderid='+orderid
    	    	});
        	}
        });
        
        $("[name='lookUp']").click(function(){
        	var orderid = $("#orderid").attr("value");
        	var task_id = $(this).parent().attr("task_id");
           	w.$.dialog({
       			id:'look',
       			title:'任务报告',
       			width: '1200px',
       			height: '510px',
       		    lock:true,
       			content: 'url:treetask_taskReportList.do?TASK_ID='+task_id+'&ORDER_ID='+orderid
   	    	});
        });
        $("[name='showtask']").click(function(){
        	var orderid = $("#orderid").attr("value");
        	var task_id = $(this).parent().attr("task_id");
        	var url ='treetask_showHtml.do?TASK_ID='+task_id+'&ORDER_ID='+orderid;
	   		 $.ajax({
	   			  type:"post",
	              url:url,
	   			  dataType : "html",
	              async: false,
	              cache: false,
	   	          success: function(data){
	   	        	$("#theForm").attr("action","treetask_executeCommandUDownload.do?TASK_ID="+task_id+"&ORDER_ID="+orderid);
	   	            $("#theForm").submit();
                 }
   			});
        });
        
        $("[name='down']").click(function(){
        	alert("暂时不支持的亲");
        });
        
        //验证规则
        $("[name='rule']").unbind().live("click",function(){
        	var task_id = $(this).parent().attr("taskid");
        	if("orderAddTask"==userType){
            	w.$.dialog({
        			id:'rule',
        			title:'验证规则',
        			width: '530px',
        			height: '300px',
        			lock:true,
        			content: 'url:rule_list.do?task_id='+task_id
    	    	});
        	}else{
            	$.dialog({
        			id:'rule',
        			title:'验证规则',
        			width: '530px',
        			height: '300px',
        			lock:true,
        			content: 'url:rule_list.do?task_id='+task_id
    	    	});
        	}

        });
        
       //任务策略
        $("[name='strategy']").unbind().live("click",function(){
        	var task_id = $(this).parent().attr("taskid");
        	if("orderAddTask"==userType){
            	w.$.dialog({
            		id:'strategy',
            		title:'任务策略',
            		width:'530px',
            		height:'300px',
            		content:'url:tactics_listTacticsObjMappings.do?taskId='+task_id
            	});
        	}else{
            	$.dialog({
            		id:'strategy',
            		title:'任务策略',
            		width:'530px',
            		height:'300px',
            		content:'url:tactics_listTacticsObjMappings.do?taskId='+task_id
            	});
        	}

        });
       
       $("[name='shuaxin']").click(function(){
    	   $("#theForm").attr("action","treetask_getTaskRelationOrder.do?userType=orderAddTask");
           $("#theForm").submit();
       });
	});
	
	 //回滚
    function rollBack(rollBackType){
 	    var task_id =  $('input[name="checkboxid"]:checked').attr("taskid");
    	var order_id = $("#orderid").val();
 	    var lenth = 0;
	    var id = '';
	    $('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
	    if(id==null || id == ''){
	    	alert("请勾选一条信息！");
	 	    return false ;
	    }else if(lenth>1){
	   		alert("只能选择一条信息！");
	    	return false ;
	    }
    	if(rollBackType==0){
     	   w.$.dialog({
          		id:'rollBack',
          		title:'文件',
          		width:'700px',
          		height:'550px',
          		lock:true,
          		content:'url:deployfile_listDeployFileTree.do?taskId='+task_id+'&orderId='+order_id+'&rollBackType='+rollBackType
          	});
    	}else{
    		$.dialog.confirm('需要删除部署实例中全部文件,确定要全部回滚？', function(){
    			$.ajax({
	             type: "GET",
	             url: "deployfile_sendSelectedNodes.do?taskId=" + task_id + "&orderId=" + order_id+"&rollBackType="+rollBackType,
				 async: false,
				 cache: false,
	             success : function(data){
	               }
	           });
    		});
   		 	
    	}
    }
	
	function addTaskForOrder(orderid){//为订单选择指定的任务，返回任务id
  		var taskids="";
    		$(":checkbox:checked").each(function(index,data){
    			taskids+=$(this).attr("taskid")+",";
    		});
    	 $.ajax({
  	            type: "GET",
  	            async:false,
  	            url: "treeorder_judgeTaskIsUser.do?taskids="+taskids+"&orderid="+orderid+"&time"+new Date().toString(),
  	            dataType: "json",
  	            success : function(data){
  		              if(data.result==-1){
						taskids=null;
  		              }
  	              }
	     });
    	 return taskids;
	}
       
      function saveTask(theform,oper){
    	  if("add"==oper){
    		  mask('正在 添加任务....','0.5','0px');
    	  }else if("edit"==oper){
    		  mask('正在 修改任务....','0.5','0px');
    	  }else{
    		  mask('正在 操作任务....','0.5','0px');
    	  }
       	 $.ajax({
            type: "GET",
            url: "treetask_saveTask.do?"+theform+"&oper="+oper+"&time"+new Date().toString(),
            dataType: "json",
            success : function(data){
	             removeMask();
	             if(data.result==1){
	            	 alert("订单已经失效,无法继续添加任务.");
	             }else if(data.result==2){
			  	    alert("任务在处理中,暂时无法编辑.");
			     }else{
	               $("#theForm").attr("action","treetask_getTaskRelationOrder.do?userType=orderAddTask");
	               $("#theForm").submit();
			     }
              }
          });
       }
</script>
</head>
<body>
<s:form action="treetask_listTask" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="appId" id="appid"></s:hidden>
<s:hidden name="exampleIds" id="exampleIds"></s:hidden>
<s:hidden name="orderid" id="orderid"></s:hidden>

<div class="scrollbody">

			<div class="utt-2">
				<a class="icon-add" href="javascript:void(0)" name="executetask">执行</a>
				<a class="icon-add" href="javascript:void(0)" name="addtask">添加</a>
				<a class="icon-modify" href="javascript:void(0)" name="shuaxin" >刷新</a>
			</div>
		
			<table width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th name="xuanze">
							选择
						</th>
						<th onclick="sort(theTable,1,'string')">
							任务编号
						</th>
						<th onclick="sort(theTable,2,'string')">
							任务状态
						</th>
						<th onclick="sort(theTable,3,'string')">
							任务类型
						</th>
						<th name="examplecount" onclick="sort(theTable,4,'int')">
							实例个数
						</th>
						<th onclick="sort(theTable,5,'int')">
							成功个数
						</th>
						<th onclick="sort(theTable,6,'int')">
							失败个数
						</th>
<%--						<th>--%>
<%--							执行时间--%>
<%--						</th>--%>
<%--						<th>--%>
<%--							完成时间--%>
<%--						</th>--%>
						<th name="taskName" onclick="sort(theTable,7,'string')" >
							任务报告
						</th>
						<th name="strategyRule" onclick="sort(theTable,9,'string')">
							策略与规则
						</th>
						<th name="edit_delete" style="display: none">
						          操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
	                  <tr>
					    <td name="xuanze"> 
							<input  taskid='<s:property value="#theBean.ID"/>' name="checkboxid" type="checkbox"
									value="<s:text name="#theBean.ID"/>" />
						</td>
						<td>
							<s:property value="#theBean.TASK_ID" default="-"/>
						</td>
						<td>
						    <s:if test="#theBean.STATUS==2">待处理</s:if>
						    <s:elseif test="#theBean.STATUS==0">处理中</s:elseif>
						    <s:elseif test="#theBean.STATUS==1">处理完成</s:elseif>
						</td>
						<td>
<%--						0部署任务，1升级任务，2启动任务，3停止任务，4重启任务，5、恢复任务，6卸载任务，7捕获任务--%>
						    <s:if test="#theBean.TASK_TYPE==0">部署任务</s:if>
						    <s:elseif test="#theBean.TASK_TYPE==1">升级任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==2">启动任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==3">停止任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==4">重启任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==5">恢复任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==6">卸载任务</s:elseif>
						    <s:elseif test="#theBean.TASK_TYPE==7">捕获任务</s:elseif>
						</td>
						<td name="examplecount" taskid='<s:property value="#theBean.ID"/>'>
						   <s:if test="#theBean.EXAMPLE_COUNT!=0">
						     <a href="javascript:;" id="example_count"><s:property value="#theBean.EXAMPLE_COUNT" default="-"/></a>
                           </s:if>
                           <s:else>
                             <s:property value="#theBean.EXAMPLE_COUNT" default="-"/>
                           </s:else>
						</td>
						<td>
						    <s:property value="#theBean.SUCCESS_COUNT" default="-"/>
						</td>
						<td>
						    <s:property value="#theBean.FAILURE_COUNT" default="-"/>
						</td>
<%--						<td>--%>
<%--						    <s:property value="#theBean.START_TIME" default="-"/>--%>
<%--						</td>--%>
<%--						<td>--%>
<%--						    <s:property value="#theBean.COMPLETE_TIME" default="-"/>--%>
<%--						</td>--%>
						<td name="taskName" task_id='<s:property value="#theBean.ID"/>'>
						    <a href="javascript:;" style="color: blue;" name="lookUp" >查看</a>&nbsp;<a href="javascript:;" style="color: blue;" name="showtask" >下载</a>
						</td>
						<td taskid='<s:property value="#theBean.ID"/>' name="strategyRule">
						   <a href="javascript:;" name="strategy">策略</a>&nbsp;&nbsp;<a href="javascript:;" name="rule">规则</a>
						</td>
						<td taskid='<s:property value="#theBean.ID"/>' name="edit_delete" style="display: none">
						    <a href="javascript:;" style="color: blue;" name="edittask" >编辑</a>&nbsp;&nbsp;<a href="javascript:;" style="color: blue;" name="deltask" >删除</a>
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
