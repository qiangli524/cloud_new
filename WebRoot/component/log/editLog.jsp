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
	var userids='',userips='';
	var api = frameElement.api;
	W = api.opener;
	function submitRequest(){
		if(theForm.flag.value == 0){// 增加时
			if(userips == null || userips == ""){
				alert("请选择关联用户!");
				return false;
			}
		}
		var type = $(":radio:checked").val(),path = $.trim($("#path").val());
		if(type==null || type==''){
			alert("请选择是文件还是文件夹!");
			return false;
		}
		if(path == null || path == ''){
			alert("请选择路径!");
			return false;
		}
		 $.ajax({
			type:"POST",
            url:"hostLog_saveLogInfo.do",
            data:'hostids='+userids+"&"+$("#theForm").serialize(),
            async: false,
            cache: false,
	        success: function(){
           		W.searchRequest();
           	}
		});
	}
	function selectHost(){
		W.$.dialog({
			lock:true,
			id:'addHostIp',
			title:'选择主机',
			width: '800px',
			height: '450px',
			content: 'url:usermanage_list.do?userids='+userids
		});
	}

	function addUser(ids,ips){
    	userids=ids;
    	userips=ips;
        var strIds=ids.split(",");
        var strIps=ips.split(",");
        var temp="已选择:<label name=\"ip\" key="+userids+">"+(strIps.length-1)+"</label>个";
        $("[name='ips']").empty();
        $("[name='ips']").append(temp);
    }
    $(function(){
    	api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:submitRequest,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
    })
</script>
<style>
.path {
	width: 332px;
	height: 20px;
	vertical-align: middle;
	line-height: 20px;
	padding: 0px;
	border: none;
	border: 1px solid #9DBCD9;
}
</style>
</head>
<body class="pop-body scrollbody">

<s:form action="hostLog_saveLogInfo.do" id="theForm" method="post">
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
	<s:hidden name="theForm.id"></s:hidden>
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<s:if test="theForm==null">	<!-- 增加 -->
				<tr>
					<td class="til">
				       <a href="javascript:;"  style="text-decoration: underline;" onclick="selectHost();">关联用户:</a><font color="red">*</font>
				    </td>
				    <td colspan="5" name="ips">
				    </td>
				</tr>
			</s:if>
			<s:elseif test="theForm.flag==1"> <!-- 修改 -->
				<tr>
				<td class="til">
					主机IP<font color="red">*</font>
				</td>
				<td>
					<s:property value="theForm.hostIP"/>
				</td>
				</tr>
				<tr>
				<td class="til">
					主机用户<font color="red">*</font>
				</td>
				<td>
					<s:property value="theForm.hostUser"/>
				</td>
			</tr>
			</s:elseif>
			<tr>
				<%-- 
				<td class="til">
					日志名称<font color="red">*</font>
				</td>
				<td>
					<s:textfield name="theForm.name" id="name" cssClass="txt"></s:textfield>					
				</td>
				--%>
				<td class="til">
					过滤后缀名
				</td>
				<td>
					<s:textfield name="theForm.extension" id="extension" cssClass="txt"></s:textfield>	
					(要过滤的后缀名,如log,不填写则不过滤)				
				</td>
			</tr>
			<tr>
				<td class="til">
					类型<font color="red">*</font>
				</td>
				<td>
					<s:radio list="#{'1':'文件','2':'文件夹'}" name="theForm.type" id="type"></s:radio>
				</td>
			</tr>
			<tr>
				<td class="til">
					 日志路径<font color="red">*</font>
				</td>
				<td>
					<s:textfield id="path"  cssClass="path" name="theForm.path"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til">
					描述
				</td>
				<td>
					<s:textarea cols="41" rows="4" name="theForm.description" cssStyle="border: 1px solid #9DBCD9;overflow-y:auto;"></s:textarea>
				</td>
			</tr>
			<%-- 
			<tr>
				<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="确定"
						onclick="javascript:submitRequest(document.getElementById('theForm'));return false;" />
					<input type="button" class="thickbox btn-style02" value="重置"
						onclick="javascript:void(document.getElementById('theForm').reset());return false;" />
				</td>
			</tr>
			--%>
		</table>
	</s:form>
</body>
