<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-ui.min.js"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath }/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>


	<script type="text/javascript">
	
    var oper = '<%=request.getAttribute("oper")%>';
    var appid = '<%=request.getAttribute("appid")%>';
   	var api = frameElement.api;
	var w = api.opener;
    //创建配置文件
	$(function(){
		 
		 if("add"==oper){
			 api.button({
			     id:'OkAnd',
			     name: '添加',
			     callback:addorder,
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
			     callback:addorder,
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

		 function addorder(){
			var effect_time=$("#effect_time").val();
		    if(effect_time==null||Trim(effect_time)==""){
			       alert("有效时间不能为空！");
			       return false  ;
			}
		    var file_list=$("#file_list").val();
		    if(file_list==null||Trim(file_list)==""){
			       alert("上线清单不能为空！");
			       return false  ;
			}
		    var authorization=$("#authorization").val();
		    if(authorization==null||Trim(authorization)==""){
			       alert("许可证编号不能为空！");
			       return false  ;
			     }
		    var versionTarPath=$("#versionTarPath").val();
		    if (versionTarPath==null ||Trim(versionTarPath)=="") {
				alert("上线包版本不能为空");
				return false;
			}
     		w.saveOrder($("#theForm").serialize(),oper);
		 }
		 
		 
		 $("#versionTarPath").click(function(){
			 w.$.dialog({
      			id:'add1',
      			title:'从版本中选取',
      			width: '750px',
      			height: '470px',
      		    lock:true,
      			content: 'url:fileversion_queryFileVersionByAppid.do?appid='+appid
  	    	});
		 });
	});
	
    function addFile(versionids,path){
    	$("#versionTarPath").val(path);
    	$("#versionid").val(versionids);
    }
	
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="" method="post" styleId="theForm" id="theForm">
	    <s:hidden name="orderObj.ID" id="orderid"></s:hidden>
	    <s:hidden name="orderObj.VERSIONID" id="versionid"></s:hidden>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
		    <tr>
			    <td class="til">
			       有效时间:<font color="red">*</font>
			    </td>
			    <td>
			  	 <input id="effect_time" style="txt" type="text" name="orderObj.EFFECT_TIME" size="20"  class="Wdate"
			   		onFocus="WdatePicker({minDate:'new Date()',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
			   		value="${orderObj.EFFECT_TIME }"/>
			   		<!-- 
			   			 <s:textfield name="orderObj.EFFECT_TIME" id="effect_time" size="20" readonly="true" cssStyle="txt" class="Wdate" 
			   			 onFocus="WdatePicker({minDate:'new Date()',maxDate:'2100-10-01',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></s:textfield>
			   		 -->
			    </td>
			</tr>
		
			<tr align="left">
				<td class="til">上线包版本<font color="red">*</font></td>
				<td>
<%--					<s:select list="#{'0':'手动输入','1':'从版本选取'}" name="orderObj.FILE_LIST_NUM" id="file_list_num" ></s:select>--%>
					<s:textfield name="orderObj.versionPath" id="versionTarPath" style="border: 1px solid #9DBCD9;width:335px;height:20px;" readonly="readonly"></s:textfield>
				</td>
			</tr>
				<!--  -->
			<tr>
				<td class="til">
					上线文件清单<font color="red">*</font></td>
				</td>
				<td>
					<s:textarea cols="40" rows="4" name="orderObj.FILE_LIST" id="file_list"
					 cssStyle="border: 1px solid #9DBCD9;overflow-y:auto;"></s:textarea>
				</td>
			</tr>
			<tr>
				<td class="til">
					文件备份 <font color="red">*</font></td>
				</td>
				<td>
					<s:if test="orderObj.backupType==1">
						<input type="radio" name="orderObj.backupType" value="0"> 增量备份 </input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="1" checked="checked"> 全部备份</input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="2"> 无备份</input> &nbsp;&nbsp;
					</s:if>
					<s:elseif test="orderObj.backupType==2">
						<input type="radio" name="orderObj.backupType" value="0"> 增量备份 </input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="1"> 全部备份</input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="2" checked="checked"> 无备份</input> &nbsp;&nbsp;
					</s:elseif>
					<s:else>
						<input type="radio" name="orderObj.backupType" value="0" checked="checked"> 增量备份 </input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="1"> 全部备份</input>&nbsp;&nbsp;
						<input type="radio" name="orderObj.backupType" value="2"> 无备份</input> &nbsp;&nbsp;
					</s:else>
				</td>
			</tr>
			<tr>
			  <td class="til">
			       许可证编号:<font color="red">*</font>
				</td>
				<td>
				<s:textfield name="orderObj.AUTHORIZATION" id="authorization" style="border: 1px solid #9DBCD9;width:335px;height:20px;"></s:textfield>
				</td>
			</tr>
		</table>
	</s:form>
</body>

</html:html>
