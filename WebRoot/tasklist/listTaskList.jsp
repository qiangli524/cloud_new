<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%
String excel_export_jsp = request.getContextPath() + "/excel/excel_export.jsp";
%>
<html:html locale="true">
<head>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
	<style type="text/css">
		div.hidden{
		width:150px;
		height:20px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
</style>
<style type="text/css">
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/css">
	div.red{
		background:red;
		
	}

</script>

<script type="text/javaScript">


$(function(){
 	$("#search").click(function(){
 	   $("#obj").submit();
 	});
 	//检测用户是否为admin用户
		$.ajax({
            type: "post",
            url: "tasklist_checkIsAdmin.do",
            dataType: "json",
			async:false,
			cache:false,
            success : function(msg){
					var adm=msg.result;
					if("yes"==adm){
						document.getElementById("addTaskList").style.display="none";
						document.getElementById("editTaskList").style.display="none";
						//document.getElementById("editTaskList").disabled="true";
					}
			}
          });
	$("#resert").click(function(){
	   $("input[type='text']").val("");
	   $("select").each(function(){
	      $(this).children("option:eq(0)").attr("selected",true);
	   });
	   $("#obj").submit();
	});
    $("[name='addTaskList']").unbind().live("click",function(){
					$.dialog({
						id:'add',
						title:'添加新任务',
						width: '510px',
						height: '300px',
					    lock:true,
						content: 'url:tasklist_addTaskList.do?oper=add'
			    	});	
   		 });
    $("[name='refresh']").click(function(){
    	$("#obj").submit();
    });
    
    
    $("[name='editTaskList']").unbind().live("click",function(){
    	var id ="";
    	var lenth = 0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
    	
    	if(id==null || id ==''){
			alert('请先选择一项进行修改');
			return false;
		}else if(lenth>1){
			alert('只能选择一项进行修改');
			return false;
		}
    	$.dialog({
			id:'vdi',
			title:'修改任务',
			width: '510px',
			height: '300px',
			max: true,
		    min: true,
		    lock:true,
			content: 'url:tasklist_updateTaskList.do?oper=edit&id='+id
		});
     });
    
    
    $("[name='del']").unbind().live("click",function(){
    	var id= "";
    	var lenth = 0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		id +=$(this).val();
    		lenth +=1;
    	 });
    	 if(lenth==0){
	 	    	alert("请勾选需要删除功能信息！");
	 	    	return false ;}
	 	    	else if(lenth>1){
    		alert("请选择一项删除");
    		return false;
    	}

      	 $.ajax({
		            type: "GET",
		            url: "tasklist_delTaskList.do?id="+id,
		            dataType: "json",
		            success : function(data){
			            $("#obj").submit();
		            }
		          });
      	 $("[name='refresh']").click(function(){
         	$("#obj").submit();
         });
    });
    
    $("[name='export']").unbind().live("click",function(){
    	
  		var url="<%=excel_export_jsp%>?key=task";
  		
  		
  		
  		exportForm.action =url;
  	  	$("#exportForm").submit();
    });
    
});


	function saveTaskList(theForm){
     	 $.ajax({
            type: "GET",
            url: "tasklist_saveTaskList.do?"+theForm,
            dataType: "json",
            success : function(data){
	            $("#obj").submit();
            }
          });
     }
	//查询
	function searchRequest() {
		obj.action = 'tasklist_queryTaskList.do';
		$("#obj").submit();
	}
	//重置 		
	function resetForm(){
		$("#responsible_persion").attr("value","");
		$("#cooperate_persion").attr("value","");
		$("#task_type").attr("value","");
		$("#plan_complete_date").attr("value","");
		$("#task_status").attr("value","");
		 
	}
	/****add by qism****/
	//查询该任务的具体记录
	function queryTaskRecords(id){
		$.dialog({
			id:'addRecords',
			title:'查看任务记录',
			width: '800px',
			height: '450px',
			max: true,
		    min: true,
		    lock:true,
			content: 'url:tasklist_queryTaskRecords.do?id='+id
		});
	}
	/****add end****/
</script>
</head>
<body onLoad="self.focus();document.obj.task_content.focus()" class="scrollbody">

<s:hidden name="wstat" id="wstat"></s:hidden>
<s:form action="tasklist_queryTaskList.do" method="post" cssStyle="theForm" id="obj" name="obj">
<div>
	<div class="pd-20 bgcolor-1">
		<h2 class="utt-1">工作清单</h2>
        <div class="bord-1 pd-10">
                <table width="100%"  border="0">
                  <tr>
                   <td class="til">任务类型</td>
                    <td>
                    	<s:select list="#{'':'--请选择--','0':'需求','1':'工单','2':'维护','3':'项目','4':'其他'}" name="obj.task_type" id="task_type" cssClass="select-1" ></s:select>
                    </td>
                     <td> 完成状态
                   	<s:select list="#{'':'--请选择--','0':'未完成','1':'已完成'}" name="obj.task_status" id="task_status" cssClass="select-1"></s:select>
                   
                   </td>
                    <td class="til">责任人:</td>
                    <td>
						<s:textfield name="obj.responsible_persion" id="responsible_persion" maxlength="100"></s:textfield>
                    </td>
                   
                   <td>任务内容
                   <s:textfield name="obj.task_content" id="task_content" maxlength="100"></s:textfield>
                   </td>
                   <td >计划完成时间
                   	<input id="planDateId" type="text" name="taskobj.plan_complete_date" size="20"  class="Wdate"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		/>	
                   </td>
                   
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                   <br/>
                        <div align="center">
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" id="search" /></span>
							<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id = "resert"/></span>
                        </div>
                    </td>
                  </tr>
                </table>
			<div class="utt-2 mgt-20">
					<a class="icon-add" href="javascript:void(0)" name="addTaskList" id="addTaskList"  >新增</a>
					<a class="icon-modify" href="javascript:void(0)" name="editTaskList" id="editTaskList"  >修改</a>
					<a class="icon-del" href="javascript:void(0)" name="del" >删除</a>
					<a class="" href="javascript:void(0)" name="export" >导出</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th>任务类型</th>
				   <th >任务内容</th>
				   <th>责任人</th>
				   <th>完成比例</th>
				   <th>任务状态</th>
				   <th>更新时间</th>
				   <th>计划完成时间</th>
				   <th>提交人</th>
				   <th>备注</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="tasklistObj" id="theBean">
			  	<tr>
			  	 	<td>
					<%-- <input type="checkbox" name="checkboxid" value='<s:property value="#theBean.id"/>'/> --%>
						<input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id"/>"/>
					</td>
						
			  		<td >
			  		<s:if test="#theBean.task_type==0">需求</s:if>
				  		<s:elseif test="#theBean.task_type==1">工单</s:elseif>
				  		<s:elseif test="#theBean.task_type==2">维护</s:elseif>
				  		<s:elseif test="#theBean.task_type==3">项目</s:elseif>
				  		<s:elseif test="#theBean.task_type==4">其他</s:elseif>
			  		</td>
			  		<td align="center">
					  <div class="hidden" title='<s:property value="#theBean.task_content"/>'>
					  <a href="#" onclick="queryTaskRecords('<s:property value="#theBean.id"/>');return false;"><s:property value="#theBean.task_content" /></a>
					  </div>
			  		</td>
			  		<td ><s:property value="#theBean.responsible_persion"/></td>
			  		<td ><s:property value="#theBean.complete_rate"/></td>
			  		<td ><div class="red">
				  		<s:if test="#theBean.task_status==0"><span style="color: red">未完成<span/></s:if></div>
					  		<s:elseif test="#theBean.task_status==1">完成</s:elseif>
			  		</td>
			  		<td ><s:property value="#theBean.submit_date"/></td>
			  		<td ><s:property value="#theBean.plan_complete_date"/></td>
			  		<td ><s:property value="#theBean.submit_persion"/></td>
			  		<td align="center">
			  			<div class="hidden" title='<s:property value="#theBean.note"/>' style="width: 15px;" >
					  		<s:property value="#theBean.note"/>
					  	</div>
			  		</td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			  
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>
		</div>
	</div>
		</s:form>
		<s:form target="hidden_frame" id="exportForm" method="post"></s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
		</body>
	</html:html>	
