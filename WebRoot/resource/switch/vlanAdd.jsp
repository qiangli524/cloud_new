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
	<script type="text/javascript">
	var api = frameElement.api; 
	var w = api.opener;
	var flag = 0;//不能提交
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
    	var v_id = $("#id").val();  //更新时隐藏域id中存放vlanId
    	var id =$("#vlan_id").val();   //id
		var name =$("#vlan_name").val();  //名称
		var status =$("#vlan_status").val();  //状态
		var ip_old= "";
		var interf_id = ""; 
		var interf_name = "";
		var patrn = /^[1-9]+[0-9]*]*$/;
		$("input['name=checkboxId']:checked").each(function(){
			interf_id+=$(this).val()+","; 
			interf_name+=$(this).attr("title")+","; 
		});
		if(id==null || id ==''){
			alert('请填写vlan的id');
				return false;
		}
		if(!patrn.exec(id)){
			alert("VLAN ID是大于0的正整数");
			return false;
		}
		if(name==null || name==''){
			alert('请填写vlan名称');
				return false;
		}
		if(status==-1){
			alert('请填写vlan状态');
				return false;
		}
		if(v_id == null && v_id == ""){
			if(flag == 0){
				alert("id校检中，请稍等！");
				return false;
			}
		}
		if(flag == 1){
			alert("vlan的id已存在请重新输入！！");
			$("#vlan_id").attr("value","");
			return false;
		}
		var form = $("#theForm");
		api.get("vlan").saveOrUpdate(form,interf_id,interf_name,ip_old);
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
  	<s:form action="" method="post" name="theForm" id="theForm" cssStyle="theForm">
  	<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize" >
		  	<s:hidden type="hidden" id="id" name="vlanId"/>
		  	<s:hidden type="hidden" id="switch_id" name="switchId"/> 
			<tr>
				<td class="til" width="20%" align="left">
					VLAN ID<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.vlan_id" id="vlan_id" style="width:150px;   height:20px;" onblur="javascript:adjustHasVlanId();" ></s:textfield>
					<span id="checkResult"></span>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					VLAN名称<span style="color:red">*</span>
				</td>
				<td>
					<s:textfield name="theForm.vlan_name" id="vlan_name" style="width:150px;   height:20px;"></s:textfield>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					状态<span style="color:red">*</span>
				</td>
				<td>
				<s:select name="theForm.vlan_status" list="#{'0':'static','1':'dynamic'}"  id="vlan_status" headerKey="-1" headerValue="--请选择--" ></s:select>
				</td>
			</tr>
			<tr>
				<td class="til" align="left">
					IP地址
				</td>
				<td>
					<s:if test="ipList!=null">
						<s:select list="ipList" headerKey="" headerValue="-请选择-" listKey="IP_ID" listValue="IPADDRESS" id="IP_ID" name="theForm.IP_ID"></s:select>
					</s:if>
					<s:else>
						<select><option id="">-请选择-</option></select>
					</s:else>
				</td>
			</tr>
			<tr>
				<td class="til">
						端口名称
				</td>
				<td>
					<s:iterator value="interfaceList" id="theBean" status="st">
							<s:if test="#st.getIndex()%4==0">
								<p>
							</s:if>
								<input name="checkboxId" id="id" type="checkbox" value="<s:property value="#theBean.id"/>" title="<s:property value='#theBean.interf_name'/>"/>&nbsp;
								<s:property value="#theBean.interf_name"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<s:if test="(#st.getIndex()+1)%4==0">
								</p>
							</s:if>
					</s:iterator>
				</td>
			</tr>
		</table>
	</div>
	</s:form>
  </body>
</html>
