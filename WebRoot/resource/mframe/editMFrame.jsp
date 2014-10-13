<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
    <script type="text/javascript">
  	var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
     api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:insertConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		function insertConfig(){
    	var num =$("#frame_num").val();   //编号
		var name =$("#frame_name").val();  //名称
		var ip =$("#ip").val();  //ip
		var oem = $("#oem").val();
		var position = $("#position").val();
   	 	if(num==null || num==''){
			alert('请填写机框编号');
				return false;
		}
		if(name==null || name==''){
			alert('请填写机框名称');
				return false;
		}
		if(ip==null || ip ==''){
			alert('请填写IP地址');
				return false;
		}
		if(oem==null || oem ==''){
			alert('请填写所属厂商');
				return false;
		}
		if(position==null || position ==''){
			alert('请填写所在位置');
				return false;
		}
		if(!isIp(ip.toString())){
			alert('ip格式有误，请重新输入');
			return false;
		}
		$.ajax({
				type:"POST",
	            url:"mFrameAction_save.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
		});
    }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form method="post" id="theForm" cssStyle="theForm">
  	<div class="table-ct" >
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<input type="hidden" name="theForm.id" value="<s:property value='theForm.id'/>" />
				<tr align="left">
				    <td class="til">机框编号<span style="color:red">*</span></td>
				    <td colspan="5" name="ips">
				    	<s:textfield name="theForm.frame_num" id="frame_num" style="width:150px;   height:20px;" />
				    </td>
				</tr>
				<tr align="left">
					<td class="til">机框名称&nbsp;<span style="color:red">*</span></td>
					<td>
						<s:textfield name="theForm.frame_name" id="frame_name" style="width:150px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">机框状态&nbsp;<span style="color:red">*</span></td>
					<td>
						<s:select list="#{'0':'0','1':'1','2':'2','3':'3'}" name="theForm.frame_status" id="frame_status" ></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">IP地址&nbsp;<span style="color:red">*</span></td>
					<td>
					<s:textfield name="theForm.ip" id="ip" style="width:150px;   height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">所属厂商&nbsp;<span style="color:red">*</span></td>
					<td>
					<s:textfield name="theForm.oem" id="oem" style="width:150px;height:20px;"></s:textfield>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">机框位置&nbsp;<span style="color:red">*</span></td>
					<td>
					<s:textarea name="theForm.position" id="position" cols="23" rows="4"></s:textarea>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">描述</td>
					<td>
					<s:textarea name="theForm.frame_desc" id="frame_desc" cols="23" rows="4"></s:textarea>
					</td>
				</tr> 
			</table>
		</div>
    </s:form>
</body>