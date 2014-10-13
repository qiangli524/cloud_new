<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=iblue"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
    //创建配置文件
     api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:updateConfig,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
    function updateConfig(){
    	var name =$("#switch_name").val();   //名称
		var type =$("#switch_type").val();  //型号
		var ip =$("#switch_ip").val();  //ip
		var status = $("#switch_status").val(); //状态
		var oem = $("#switch_oem").val();   //厂商
		var vision = $("#switch_vision").val();   //版本
		var des = $("#switch_des").val();   //描述
		if(name==null || name==''){
			alert('请填写交换机名称');
				return false;
		}
		/*if(type==null || type ==''){
			alert('请填写交换机型号');
				return false;
		}
		if(!isIp(ip.toString())){
			alert('ip地址有误，请重新输入');
			return false;
		}
		if(oem==null || oem ==''){
			alert('请填写交换机厂商');
				return false;
		}
		if(vision==null || vision ==''){
			alert('请填写交换机版本');
				return false;
		}
		if(des==null || des ==''){
			alert('请填写交换机描述');
				return false;
		}*/
		var id = $("#id").val();
		if (id == null || id == ''){
			$.ajax({
				type:"POST",
	            url:"switchAction_insert.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
			});
		}else{
			$.ajax({
				type:"POST",
	            url:"switchAction_update.do",
	            data:$("#theForm").serialize(),
	            async: false,
	            cache: false,
		        success: function(){
	           		w.searchRequest();
	           	}
			});
		}
		w.searchRequest();
	}
	</script>
	</script>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="" method="post" name="theForm" id="theForm" cssStyle="theForm">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<input type="hidden" id="id" name="switchId" value="<s:property value='switchId'/>"/>
			<tr>
				<td class="til" width="20%" align="left">
					交换机名称<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.switch_name" id="switch_name" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					型号
				</td>
				<td>
					<s:textfield name="theForm.switch_type" id="switch_type" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					状态
				</td>
				<td>
				<s:select name="theForm.switch_status" list="#{'0':'unlock','1':'lock'}"  id="switch_status" style="width:150px;   height:20px;"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					管理IP
				</td>
				<td>
					<s:textfield name="theForm.switch_ip" id="switch_ip" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					交换机厂商
				</td>
				<td>
					<s:textfield  name="theForm.switch_oem"  id="switch_oem" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					交换机版本
				</td>
				<td>
					<s:textfield name="theForm.switch_vision" id="switch_vision" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					交换机描述
				</td>
				<td>
					<s:textarea name="theForm.switch_des" id="switch_des" cols="20" rows="5" style="width:150px;   height:50px;">
					</s:textarea>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
  </body>
</html>
