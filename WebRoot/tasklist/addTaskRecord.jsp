<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script>
  	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
	<title></title>
<script type="text/javascript">
$(function(){
	 var api = frameElement.api;
	 var w = api.opener;
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
		
	 function Trim(str){
		return str.replace(/^\s+|\s+$/g,"");
		}
	//添加
	 function addprocess(){
		var workcontent=$("#workcontent").val();
		var partner=$("#partner").val();
		var task_id = '<%=request.getAttribute("task_id")%>';
		var complete_date = $("#complete_date").val();
		var complete_rate=$("#complete_rate").val();
	    if(workcontent==null||Trim(workcontent)==""){
	       alert("记录内容不能为空！");
	       return false  ;
	     }
	    if(partner==null||Trim(partner)==""){
	       alert("请填写配合人！");
	       return false  ;
		 }
		if(complete_date == null||Trim(complete_date)==""){
		   alert("请填写完成时间！");
	       return false  ;
		 }
		if(isNaN(complete_rate)){
		  	alert("完成比例请填写数字");
		  	return false;
		  	}
	    if(complete_rate==null || Trim(complete_rate)=="" || complete_rate<0 || complete_rate>100){
			alert("请正确填写完成比例");
			return false;
		   }
	    //w.saveRecordList($("#theForm").serialize());
	     api.get("addRecords").saveRecordList($("#theForm").serialize());
	 }
});
</script>
</head>
<body class="pop-body ">
	<s:form action="" method="post" styleId="theForm" id="theForm">
		<s:hidden name="task_id" id="task_id"></s:hidden>
		<s:hidden name="oper" id="oper"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">记录内容:<font color="red">*</font></td>
				<td >
				<s:textarea  name="taskRecordObj.WORKCONTENT" cssClass="txt" id="workcontent" cols="38" rows="5" ></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">配合人:</td>
				<td ><s:textfield
						name="taskRecordObj.PARTNER" cssClass="txt"
						id="partner" style="width:250px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr  >
				<td class="til">计划完成时间:</td>
				<td >
				<input id="complete_date" type="text" name="taskRecordObj.COMPLETE_DATE" size="32"  class="Wdate" value="${taskobj.plan_complete_date }"
					onFocus="WdatePicker({minDate:'2013-10-01',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd'})"/>		
				</td>
			</tr>		
			<tr >
				<td  class="til">完成比例:</td>
				<td><s:textfield name="taskRecordObj.COMPLETE_RATE"
						cssClass="txt" id="complete_rate" style="width:250px;   height:20px;"></s:textfield><font color="red">请输入1-100之间的数字
				</td>
			</tr>
			<tr >
				<td class="til">备注:</td>
				<td><s:textarea name="taskRecordObj.NOTE"
						cssClass="txt" id="note" cols="30" rows="4"></s:textarea>
				</td>
			</tr>
			</table>
			</s:form>
			</body>
</html>