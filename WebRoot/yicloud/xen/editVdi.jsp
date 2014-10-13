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
var srUuid='<%=request.getAttribute("srUuid")%>';
var vdiUuid='<%=request.getAttribute("vdiUuid")%>';


var vdiSizeOj;//GB
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
		vdiSizeOj=$("#size").val();
	 });
 var api = frameElement.api;
 var w = api.opener;

 api.button({
     id:'Ok1',
     name: '调整',
     callback:ok,
     focus: true
 },
 {
     id:'cancle1',
     name: '取消'
 });
 function ok(){
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
		if("GB"==mate){
           if(vdiSize<vdiSizeOj){
               alert("磁盘只能扩大，不支持缩小!");
               $("#size").val(vdiSizeOj);
               return false;
              }
			}else{
               var temp=vdiSizeOj*1024;
               if(vdiSize<temp){
                   alert("磁盘只能扩大，不支持缩小!");
                   $("#size").val(temp);
                   return false;
                  }
			}
	    w.editVdi(vdiName,vdiDesc,vdiSize,mate,vdiUuid);
	  }

 $(function(){
	$("#select option").each(function(){
          var temp=$(this).attr("value");
          if(srUuid==temp){
               this.selected=true;
          }
		});
	 });
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="" method="post" id="theForm">
		<div class="table-ct">
			<table border="0" cellspacing="0" class="pop-table nosize"  style="width: 95%;margin-top: 2px" align="center">
				<tr>
					<td align="left" colspan="2">
						<span>可以增加磁盘大小，以便为VM提供更多的空间，不支持减小磁盘大小。</span>
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
					<s:textfield name="theForm.vdiSize" cssStyle="width:60px;height:20px;" id="size"></s:textfield>
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
					<s:select id="select" disabled="true" name="theForm.srUuid" multiple="multiple" size="100" list="theForm.srs" listKey="key" listValue="value" cssStyle="width:470px;height:150px;"></s:select>
					</td>
				</tr> 
			</table>
		</div>
</s:form>
</body>