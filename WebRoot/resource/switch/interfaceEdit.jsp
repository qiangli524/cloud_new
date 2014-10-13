<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
<style type="text/css">
.pop-table tr td{text-align: left;}
</style>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/formvalidate/validate.js"></script> 
	<script type="text/javascript">
	var flag = 0;//不能提交
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
		var name =$("#interf_name").val();  //名称
    	var interf_status =$("#interf_status").val();   //状态
		var workModle =$("#workModle").val();  //模式
		var interf_speed =$("#interf_speed").val();  //速率
		var auto_negotiation =$("#auto_negotiation").val();  //自协商
		var vlanId_old = $("#vlan_id_old").val();//选择之前的vlanID
		if(name==null || name==''){
			alert('请填写端口名称');
				return false;
		}
		if(interf_status == '-1' || interf_status ==''){
			alert('请填写端口状态');
				return false;
		}
		if(workModle == -1){
			alert('请填写端口工作模式');
				return false;
		}
		if(interf_speed==null || interf_speed==''){
			alert('请填写端口速率');
			return false;
		}
		if(name == null && name == ""){
			if(flag == 0){
				alert("端口名称校检中，请稍等！");
				return false;
			}
		}
		if(flag == 1){
			alert("端口名称已存在请重新输入！！");
			$("#name").attr("value","");
			return false;
		}
		var number = /^[0-9]*$/;
		if(!number.test(interf_speed)){
			return false;
		}
		if(auto_negotiation=='-1'){
			alert('请填写自协商状态');
				return false;
		}
		var form = $("#theForm");
		api.get("interface").saveOrUpdate(form,vlanId_old);
	}
	function adjustHasVlanId(){
		var interid =$("#interf_name").val();
		var sid =$("#switch_id").val();
		$("#checkResult").html("");
		if($("#interf_name").attr("value") != "" && $("#interf_name").attr("value") != null){
			$("#checkResult").html("校验中，请等待....");
			$.ajax({
			         type: "get",
			         url: "switchInterfaceAction_adjustInterfaceName.do?interName="+interid+"&switchId="+sid,
			         dataType: "json",
					 async: false,
      				 cache: false,
			         success : function(data){
						if(data == 1){
							$("#checkResult").css("color","red");
							$("#checkResult").html("该名称已存在请重新输入");
							$("#interf_name").focus();
							flag = 1; //用户已存在，不可提交
						}else{
							$("#checkResult").css("color","green");
							$("#checkResult").html("该名称可用");
							flag = 2 ;//可以提交
						}
			        }
			});
		}else{
			$("#checkResult").css("color","red");
			$("#checkResult").html("请填写端口名称");
			$("#vlan_id").focus();
		}
	}
	</script>
  </head>
  <body  class="pop-body scrollbody">
  	<s:hidden type="hidden" id="vlan_id_old" name="theForm.vlan_id"/> 
  	<s:form action="" method="post" name="theForm" id="theForm" cssStyle="theForm">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<s:hidden id="id" name="interfaceId" />
		  	<s:hidden id="switch_id" name="switchId"/> 
			<tr>
				<td class="til" width="20%" align="left">
					端口名称<span style="color:red">*</span>
				</td>
				<td>
					<s:if test="theForm.id != null">
						<s:textfield name="theForm.interf_name" id="interf_name" style="width:150px;   height:20px;" readonly="true"></s:textfield>
					</s:if>
					<s:else>
						<s:textfield name="theForm.interf_name" id="interf_name" style="width:150px;   height:20px;" onblur="javascript:adjustHasVlanId();"></s:textfield>
					</s:else>
					<span id="checkResult"></span>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					端口状态<span style="color:red">*</span>
				</td>
				<td>
					<s:select name="theForm.interf_status" list="#{'0':'up','1':'down'}"  id="interf_status" headerKey="-1" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					工作模式<span style="color:red">*</span>
				</td>
				<td>
					<%--<s:select name="theForm.workModle" list="#{'0':'FULL','1':'HALF','2':'access','3':'trunk','4':'router'}"  id="workModle" headerKey="-1" headerValue="--请选择--"></s:select>--%>
				<s:select name="theForm.workModle" list="#{'0':'FULL','2':'access','3':'trunk'}"  id="workModle" headerKey="-1" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					端口速率<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.interf_speed" id="interf_speed" style="width:150px;   height:20px;" cssClass="for0_9"></s:textfield>(Mbps)
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					自协商<span style="color:red">*</span>
				</td>
				<td>
					<s:select name="theForm.auto_negotiation" list="#{'0':'10M半双工','1':'10M全双工','2':'100M半双工','3':'100M全双工'}"  id="auto_negotiation" headerKey="-1" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					Vlan名称
				</td>
				<td>
					<s:if test="vlanList!=null">
						<s:select list="vlanList" headerKey="" headerValue="-请选择-" listKey="id" listValue="vlan_name" id="vlan_id" name="theForm.vlan_id"></s:select>
					</s:if>
					<s:else>
						<select><option id="">-请选择-</option></select>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					描述
				</td>
				<td>
					<s:textarea name ="theForm.descript" id="descript" cssClass="txt" cols="15" rows="5"></s:textarea>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
  </body>
</html>
