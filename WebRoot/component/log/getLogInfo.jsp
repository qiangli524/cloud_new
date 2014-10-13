<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<head>
<title></title>
<script type="text/javascript">
	function searchword(){
		var word = $.trim($("#keyword").val());
		if(word==null || word==""){
			alert("请输入关键字!");
			return false;
		}
		id = '<s:property value="%{#request.id}"/>';
		path = '<s:property value="%{#request.path}"/>';
		$.ajax({
			type:"GET",
			cache:false,
			url:"hostLog_getSerchWord.do",
			data:"id="+id+"&path="+path+"&word="+word,
			dataType:"json",
			success:function(data){
				$("#logContent").empty().text(data.content);
			}
		});
	}
	function confirmLine(){
		var line = $.trim($("#line").val());
		if(line == null || line == ""){
			alert("请先填写要读取最后多少行!");
			return false;
		}else{
			id = '<s:property value="%{#request.id}"/>';
			path = '<s:property value="%{#request.path}"/>';
			$.ajax({
				type:"GET",
				cache:false,
				url:"hostLog_getLastLine.do",
				data:"id="+id+"&path="+path+"&line="+line,
				dataType:"json",
				success:function(data){
					$("#logContent").empty().text(data.content);
					setFocusLast(theForm.logContent);
				},
				error:function(){
					alert("查看日志错误!");
				}
			});
		}
	}
	function setFocusLast(obj){     
       obj.focus();      
       var r = obj.createTextRange();       
       r.moveStart("character",obj.value.length);      
       r.collapse(true);      
       r.select();        
   }
	$(function(){
		$("#auto").click(function(){
			if(this.checked){
				var line = $.trim($("#line").val());
				if(line == null || line == ""){
					alert("请先填写要读取最后多少行!");
					return false;
				}else{
					setInterval(confirmLine,3000);
				}
			}
		});
	});
</script>
<style type="text/css">
</style>
</head>
<body class="pop-body scrollbody">

<s:form action="hostLog_saveLogInfo.do" id="theForm" method="post">
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr>
				<td width="10%"><font color="red"><s:property value="#request.path"/></font>
					<label class="mgl-20">读取最后</label>
					<s:textfield id="line" cssStyle="width:50px; height:20px; line-height:20px; padding:0 3px; border:none; border:1px solid #999;text-align:right;"></s:textfield>&nbsp;&nbsp;行
					<span class="ubtn-1 mgl-10"><input type="button" value="确定"  onclick="confirmLine();"/></span>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" id="auto"/>自动刷新
				</td>
			</tr>
			<tr>
				<td  colspan="2">
					<input type="text" class="txt" id="keyword" />
					<a href="javascript:;" onclick="searchword();">搜索</a>
                  		<font> (说明：支持正则表达式,用单引号,例如："'[a-z]\{5\}' aa")</font>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:textarea value="%{#request.log}" cols="96" rows="21" readonly="true" id="logContent">
					</s:textarea>
				</td>
			</tr>
		</table>
	</s:form>
</body>
