<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<head>
<script type="text/javascript">
	var api = frameElement.api;
	w = api.opener;
	$(function(){
    	api.button({
		     id:'OkAnd',
		     name: '关闭',
		 });
    });
   function submitRequest(){
   		var file_upl = document.getElementById("browse").value;
		if(file_upl == '' || file_upl == null){
     		alert("请先选择要导入的文件!");
     		return false;
   		}
		var choosed = $.trim(file_upl);
		var suffix = choosed.substring(choosed.lastIndexOf(".")+1);
     	if(!(suffix == "xls" || suffix =="xlsx")){
     		alert("选择格式不正确!");
     		return false;
   		}
   		bar('importtips','数据导入中!请稍后');
   		theForm.submit();
    }
   function bar(idstr,contents){
		w.$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}
	function barEnd(idstr,contents){
		w.$.dialog.list[idstr].content(contents,false,false);
		w.$.dialog.list[idstr].time(6);
	}
	function importCallBack(data){
		if(data=='success'){
			barEnd('importtips','数据导入成功!');
		}else{
			barEnd('importtips','数据导入失败，请检查后重试!');
		}
		w.searchRequest();
		api.close();
		
	}
</script>
</head>
<body style="background-color:#f7f7f7;">
<s:form id="theForm" method="post" enctype="multipart/form-data" target="hidden_frame" action="relation_importFromXls">
	<table  style="position:absolute;top:20px;">
		<tr>
			<td>
				<font style="color:#6495ED;"> 请选择要导入的Excel文件</font>
			</td>
		</tr>
		<tr>
			<td>
				<s:file name="file" accept="application/vnd.ms-excel" id="browse" cssStyle="background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:280px;"></s:file>
				&nbsp;&nbsp;
				<a href="javascript:;" onclick="submitRequest();">导入</a>
			</td>
		</tr>
		<tr>
			<td>
			   <a href="<%=request.getContextPath()%>/hadoop/relation/import_template.xls">下载模板</a>
			</td>
		</tr>
		<tr style="height: 10px;"></tr>
		<tr>
			<td>
				<font style="color:red" size="2"> 说明： </font>
			</td>
		</tr>
		<tr>
			<td>
				<font style="color:red" size="2">附件中的红色title部分是必填项;</font>
			</td>
		</tr>
		<tr>
			<td>
				<font style="color:red" size="2">导入数据时不要关闭此窗口;</font>
			</td>
		</tr>
	</table>
	<iframe  name="hidden_frame" id="hidden_frame" style="display:none"></iframe>
</s:form>
</body>
