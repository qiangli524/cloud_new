<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/view.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
    <title></title>
 <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/ipCheck/ip.js"></script>
	<script type="text/javascript">
	
	var api = frameElement.api;
	var w = api.opener;
	var flag = 0;
	$(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:savePhysicalVlan,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
	})
 function savePhysicalVlan(){
    	var id =$("#vlan_id").val();   //id
		var name =$("#vlan_name").val();  //名称
		var area_id =$("#area_id").val();  //地域
	
		$("input['name=checkboxId']:checked").each(function(){
			interf_id+=$(this).val()+","; 
			interf_name+=$(this).attr("title")+","; 
		});
		if(id==null || id ==''){
			alert('请填写vlan的id');
				return false;
		}
		if(name==null || name==''){
			alert('请填写vlan名称');
				return false;
		}
		if(area_id==-1){
			alert('请选择Vlan地域');
				return false;
		}
		if(flag == 1){
			alert("vlan的id已存在请重新输入！！");
			$("#vlan_id").attr("value","");
			return false;
		}
 
 
 	var str = $("#addForm").serialize();
 	var url = "physicalVlan_savePhysicalVlan.do?"+str;
 	w.savePhysicalVlan(url);
 }
	
	function adjustHasVlanId(){
		var vid =$("#vlan_id").val();
		var sid =$("#switch_id").val();
		$("#checkResult").html("");
		if($("#vlan_id").attr("value") != "" && $("#vlan_id").attr("value") != null){
			$("#checkResult").html("校验中，请等待....");
			$.ajax({
			         type: "get",
			         url: "vlanAction_adjustHasVlanId.do?vlanId="+vid+"&switchId="+sid,
			         dataType: "json",
			         success : function(data){
						if(data == 1){
							$("#checkResult").css("color","red");
							$("#checkResult").html("id已存在请重新输入");
							$("#vlan_id").focus();
							flag = 1; //用户已存在，不可提交
						}else{
							$("#checkResult").css("color","green");
							$("#checkResult").html("该id可用");
							flag = 2 ;//可以提交
						}
			        }
			});
		}else{
			$("#checkResult").css("color","red");
			$("#checkResult").html("请填写Vlan Id");
			$("#vlan_id").focus();
		}
	}
	</script>
  </head>
  <body  class="pop-body scrollbody">
  	<s:form action="physicalVlan_savePhysicalVlan.do" method="post" name="addForm" id="addForm" cssStyle="theForm">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
			<tr>
				<td class="til" width="20%" align="left">
					VLAN ID<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield cssClass="inpt-1 vm" name="obj.vlan_id" id="vlan_id"   onblur="javascript:adjustHasVlanId();" ></s:textfield>
					<span id="checkResult"></span>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					VLAN名称<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield cssClass="inpt-1 vm" name="obj.name" id="vlan_name" ></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					地域<span style="color:red">*</span>
				</td>
				<td>
				<s:select cssClass="select-1 vm" name="obj.area_id" list="#{'1':'北京东部','2':'北京西部'}"  id="area_id" headerKey="-1" headerValue="--请选择--"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					IP地址
				</td>
				<td>
					<s:textfield cssClass="inpt-1 vm" name="obj.ip" id="ip"  ></s:textfield>
				</td>
			</tr>
			
			<tr>
				<td class="til" align="left">
					子网掩码
				</td>
				<td>
					<s:textfield cssClass="inpt-1 vm" name="obj.subnet_mask" id="subnet_mask"  ></s:textfield>
				</td>
			</tr>
			
			<tr>
				<td class="til" align="left">
					网关
				</td>
				<td>
					<s:textfield cssClass="inpt-1 vm" name="obj.gateway" id="gateway"  ></s:textfield>
				</td>
			</tr>
		</table>
		<br>
	</div>
		
	</s:form>
  </body>
</html>
