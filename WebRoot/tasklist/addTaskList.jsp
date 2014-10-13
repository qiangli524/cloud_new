<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
  	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
<script type="text/javascript">

$(function(){
  	 var oper =$("#oper").val();
	 var api = frameElement.api;
	 var w = api.opener;
		if("add"==oper){
			api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addprocess,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		
		}else{
			 api.button({
			     id:'OkAnd',
			     name: '修改',
			     callback:addprocess,
			     focus: true
			 },
			 {
			     id:'cancle',
			     name: '取消'
			 });
		}

   function Trim(str){
	return str.replace(/^\s+|\s+$/g,"");
	}

	 function addprocess(){
		var task_content=$("#task_content").val();
	    if(task_content==null||Trim(task_content)==""){
	       alert("名称不能为空！");
	       return false  ;
	     }
		var responsible_persion=$("#responsible_persion").val();
	    if(responsible_persion==null||Trim(responsible_persion)==""){
	       alert("责任人不能为空！");
	       return false  ;
		 }
	    w.saveTaskList($("#theForm").serialize());
	 }
});



</script>
</head>
<body class="pop-body">
	<s:form action="" method="post" styleId="theForm" id="theForm">
		<s:hidden name="oper" id="oper"></s:hidden>
		<s:hidden name="taskobj.id" id="id"></s:hidden>
		<s:hidden name="theForm.user_id" id="user_id"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr align="left">
				<td class="til">任务类型:</td>
				<td>
					<s:select list="#{'0':'需求','1':'工单','2':'维护','3':'项目','4':'其他'}"
						name="taskobj.task_type" id="task_type" cssClass="select-1"></s:select>
				</td>
			<tr>
				<td class="til">任务内容:<font color="red">*</font></td>
				<td >
				<s:textarea  name="taskobj.task_content"
						cssClass="txt" id="task_content" cols="38" rows="4" ></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">责任人:<font color="red">*</font></td>
				<td >
					<s:select cssClass="select-1" list="taskobj.responPersonList" id="responsible_persion" headerKey="" headerValue="---请选择---" 
						listKey="USERNAME" listValue="USERNAME"    name="taskobj.responsible_persion" value="taskobj.responsible_persion" >
					</s:select>
				</td>
			</tr>
			<tr>
				<td class="til">计划完成时间:
				<td >
				<input id="planDateId" type="text" name="taskobj.plan_complete_date" size="32"  class="Wdate" value="${taskobj.plan_complete_date }"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})"
						   		/>		
						</td>
				
			</tr>
			<tr>
				<%--<td class="til">实际完成时间:
				<td >
				<input id="realDateId" type="text" name="taskobj.complete_date" size="20"  class="Wdate" value="${taskobj.complete_date }"
						   		onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
						   		/>	
						</td>
			--%></tr>
			<tr>
				<td class="til">备注:</td>
				<td><s:textarea name="taskobj.note"
						cssClass="txt" id="note" cols="30" rows="4"></s:textarea></td>
			</tr>
			</table>
			</s:form>
			</body>
			