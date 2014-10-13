<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
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
		width:100px;
		height:20px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
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
<script type="text/javaScript">
	$(function(){
	 var api = frameElement.api;
	 var w = api.opener;
	 api.button(
			 {
			     id:'cancle',
			     name: '取消'
			 });
 	  /* $("#search").click(function(){
 	  $("#obj").submit();
 		}); */

	/* $("#resert").click(function(){
	   $("input[type='text']").val("");
	   $("select").each(function(){
	      $(this).children("option:eq(0)").attr("selected",true);
	   });
	   $("#obj").submit();
	}); */
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
						document.getElementById("addRecordList").style.display="none";
						document.getElementById("editRecordList").style.display="none";
						//document.getElementById("editTaskList").disabled="true";
					}
			}
          });
    $("[name='addRecordList']").unbind().live("click",function(){
   		var task_id = '<%=request.getAttribute("id")%>';
  		w.$.dialog({
			id:'add1',
			title:'添加任务记录',
			width: '550px',
			height: '350px',
			lock:true,
			content: 'url:tasklist_addRecordList.do?oper=add&task_id='+task_id
    		});
    });
    $("[name='refresh']").click(function(){
    	$("#obj").submit();
    });
    
    
    $("[name='editRecordList']").unbind().live("click",function(){
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
    	w.$.dialog({
			id:'edit',
			title:'修改RecordList',
			width: '550px',
			height: '350px',
			max: true,
		    min: true,
			content: 'url:tasklist_updateRecordList.do?oper=edit&id='+id
		});
     });
    
    
    $("[name='del']").unbind().live("click",function(){
    	var ids= "";
    	var lenth = 0;
    	$('[name=checkboxid]:checkbox:checked').each(function(){
    		ids =ids+$(this).val()+",";
    		lenth +=1;
    	 });
    	 if(lenth==0){
	 	    alert("请勾选需要删除功能信息！");
	 	    return false ;
	 	    }
	 	    	
      	 $.ajax({
		            type: "GET",
		            url: "tasklist_delRecordList.do?id="+ids,
		            dataType: "json",
		            success : function(data){
			            $("#obj").submit();
		            }
		          });
    });
    
	    $("#exportT").click(function(){
	  		var url="<%=excel_export_jsp%>?key=taskRecord";
	  		exportForm.action =url;
	  	  	$("#exportForm").submit();
	    });
	    
	});
     //保存并刷新
	     function saveRecordList(theForm){
	     	 $.ajax({
	            type: "GET",
	            url: "tasklist_saveRecordList.do?"+theForm,
	            dataType: "json",
	            success : function(data){
		            $("#obj").submit();
	            }
	          });
	     } 
    //修改并刷新
	     function updateRecordList(theForm){
	     	 $.ajax({
	            type: "GET",
	            url: "tasklist_updateToDo.do?"+theForm,
	            dataType: "json",
	            success : function(data){
		            $("#obj").submit();
	            }
	          });
	     } 
</script>
</head>
<body class="scrollbody">
<s:form action="tasklist_queryTaskRecords.do" method="post" cssClass="obj" id="obj" name="obj">
<!-- 此处id存的是对应的task_id -->
<s:hidden name="id" id="id"></s:hidden>
<div class="pd-20 bgcolor-1">
        <h2 class="utt-1">任务记录</h2>
        <div class="bord-1 pd-10">
		<!-- <div class="table-head">
			<ul class="btns">
				<li>
					<input type="button" class="thickbox btn-style02" value="添加"  name="addRecordList" id="addRecordList"/>
					<input type="button" class="thickbox btn-style02" value="修改"  name="editRecordList" id="editRecordList"/>
					<input type="button" class="thickbox btn-style02" value="删除"  name="del"/>
				</li>
			</ul>		
		</div> -->
			<div class="utt-2 mgt-20">
					<a class="icon-add" href="javascript:void(0)" name="addRecordList" id="addRecordList"  >新增</a>
					<a class="icon-modify" href="javascript:void(0)" name="editRecordList" id="editRecordList"  >修改</a>
					<a class="icon-del" href="javascript:void(0)" name="del" >删除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th>选择</th>
				   <th>记录内容</th>
				   <th>配合人</th>
				   <th>完成比例</th>
				   <th>任务状态</th>
				   <th>提交时间</th>
				   <th>计划完成时间</th>
				   <th>提交人</th>
				   <th>备注</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="taskRecordListObj" id="theBean">
			  	<tr>
			  	 	<td>
					<input type="checkbox" name="checkboxid" value='<s:property value="#theBean.ID"/>'/>
					</td>
			  		<td align="center">
					  <div class="hidden" title='<s:property value="#theBean.WORKCONTENT"/>'>
					  	<s:property value="#theBean.WORKCONTENT" />
					  </div>
			  		</td>
			  		<td><s:property value="#theBean.PARTNER"/></td>
			  		
			  		<td ><s:property value="#theBean.COMPLETE_RATE"/></td>
			  		<td >
				  		<s:if test="#theBean.TASK_STATUS==0">
				  			<span style="color: red">未完成<span/>
				  		</s:if>
					  	<s:else>
					  		完成
					  	</s:else>
			  		</td>
					<td ><s:property value="#theBean.SUBMIT_DATE"/></td>
					<td ><s:property value="#theBean.COMPLETE_DATE"/></td>
			  		<td ><s:property value="#theBean.SUBMIT_PERSION"/></td>
			  		<td align="center">
			  			<div class="hidden" title='<s:property value="note"/>' style="width: 10px;">
					  		<s:property value="#theBean.NOTE"/>
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
		<s:form target="hidden_frameR" id="exportForm" method="post"></s:form>
		<iframe id="hidden_frameR" name="hidden_frameR" style="display:none;"></iframe>
		</body>
	</html:html>	