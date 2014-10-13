<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
	<title></title>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript">
var poolUuid='<%=request.getAttribute("poolUuid")%>';
var vmUuid='<%=request.getAttribute("vmUuid")%>';

 $(function(){
		$("#sizeSelect").change(function(){
			var temp=$(this).val();
			if("GB"==temp){
				var temp1=$("#size").val();
				$("#size").val(temp1/1024);
			}else{
				var temp1=$("#size").val();
				$("#size").val(temp1*1024);
			}
		});
	 });
 var api = frameElement.api;
 var w = api.opener;

 api.button({
     id:'Ok',
     name: '添加',
     callback:add,
     focus: true
 },
 {
     id:'cancle',
     name: '取消'
 });
 function add(){
	var vdiName=$("#name").val();
	var vdiDesc=$("#desc").val();
	var vdiSize=$("#size").val();
	if(vdiName==null||trim(vdiName)==""){
       alert("名称不能为空!");
       return false;
	}

	if(!isnumber(vdiSize)){
		alert("虚拟磁盘大小格式非法!");
		return false;
	}
	var mate=$("#sizeSelect option:selected").val();
	var srUuid=$("#select option:selected").val();
	if(srUuid==null||trim(srUuid)==""||srUuid=='undefined'){
		alert("请选择存储所在位置!");
		return false;
	}
    w.addVdi(vdiName,vdiDesc,vdiSize,mate,srUuid);
  }
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="theForm">
		<div class="table-ct">
			<table border="0" cellspacing="0" class="pop-table nosize"  style="width: 95%;margin-top: 2px" align="center">
				<tr>
					<td align="left" colspan="2">
						<span>输入虚拟磁盘的名称、说明和大小。磁盘大小及磁盘所属任何VM的主服务器设置将影响可用发的存储位置。</span>
					</td>
				</tr>
				<tr>
					<td class="til" width="20%" align="left"> 
						名称
					</td>
					<td  align="left">
					<s:textfield name="theForm.NAME" cssStyle="width:400px;height:20px;" id="name"></s:textfield>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						说明
					</td>
					<td align="left">
					<s:textarea name="theForm.DESC" cssStyle="width:400px;height:60px;"  id="desc"></s:textarea>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						大小
					</td>
					<td align="left">
					<s:textfield name="theForm.vdiSize" cssStyle="width:60px;height:20px;" id="size" value="1"></s:textfield>
					<select name="mate" style="width:50px;height:20px;" id="sizeSelect">
					 <option value="GB">GB</option>
					 <option value="MB">MB</option>
					</select>
					</td>
				</tr> 
				<tr>
					<td class="til" align="left">
						位置
					</td>
					<td align="left">
					<s:select id="select" name="theForm.srUuid" multiple="multiple" size="100" list="theForm.srs" listKey="key" listValue="value" cssStyle="width:470px;height:150px;"></s:select>
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>
