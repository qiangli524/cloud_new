<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@	taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'createSnapShot.jsp' starting page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/publicCloud/pub-ui/css/rules.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript">
var api = frameElement.api;
var w = api.opener;

$(function(){
	 api.button({
	     id:'OkAnd',
	     name: '确定',
	     callback:joinNetwork,
	     focus: true
	 },
	 {
	     id:'cancle',
	     name: '取消'
	 });

	 var v = $("#obj").validate({
			//debug:true,
			rules : {
				'vlanObj.vlan_type' : {
					required : true
				},
				'vlanObj.id' : {
					required : true
				}
			},
			messages : {
				'vlanObj.vlan_type' : "请选择子网类型",
				'vlanObj.id' : "请选择子网"
			},
	  		errorPlacement : function(error, element) { 
				if (element.is(":radio")) 
					error.appendTo(element.parent()); 
				else if (element.is("select"))
					error.appendTo(element.parent().parent()); 
				else
					error.appendTo(element.parent());
			} 
	 });
	 
	 //异步获得可用的vlan。
	 var vlans =null;
	 $.ajax({
	   		type : 'post',
	   		async : false,
	   		url : 'physicalVlan_ajaxGetVlanListForCustomer.do',
			//data:{"obj.vm_uuid":$('#vm_uuid').val()},
			dataType: 'json',
			success : function(data){
				if(data.length > 0){
					vlans = data;
				}
			}
	 });	
	 
	 //根据子网类型渲染页面
		 $("#vlan_type").change(function (){
			 var vlan_type = $(this).children("option:selected").val();
			 if(vlan_type == ""){
				 $("#vlan_id").html("");
			 }else if(vlans.length != null){
				 renderVlan(vlan_type);
			 }else{
				 $("#info").removeClass("alert-info").addClass("alert-error").html("您没有可以用子网，请购买后加入网络");
			 }
		 })
	
	
	 //渲染子网
	function renderVlan(vlan_type){
		var vlan_select_html = '<div class="control-label">子网名称</div>'+
									'<div class="controls">'+
										'<div class="select-con">'+
											'<select class="dropdown-select" name="vlanObj.vlan_id">';
											
		var outer_vlan_num = 0;
		var inner_vlan_num = 0;
		
		$.each(vlans,function(i,vlan){
			if(vlan_type == '1' && vlan.vlan_type=='1'){
				vlan_select_html += '<option value="'+vlan.vlan_id+'">'+vlan.name+'</option>';
				outer_vlan_num ++;
			}
			if(vlan_type == '0' && vlan.vlan_type=='0'){
				if(inner_vlan_num == 0){
					vlan_select_html += '<option value="" selected>请选择</option>';
				}
				vlan_select_html += '<option value="'+vlan.vlan_id+'">'+vlan.name+'</option>';
				
				inner_vlan_num ++;
			}
		})
		vlan_select_html += '</select></div></div></div>';
		
		if((vlan_type == '1' && outer_vlan_num > 0) || (vlan_type == '0' && inner_vlan_num > 0)){
			$("#vlan_id").html(vlan_select_html);
			$("#info").removeClass("alert-error").addClass("alert-info").html("提示：请选择子网类型，再选择要加入的子网。");
		}else{
			$("#vlan_id").html("");
			if(vlan_type == '1'){
				$("#info").removeClass("alert-info").addClass("alert-error").html("您没有可以用公网子网，请购买后加入网络");
			}else{
				$("#info").removeClass("alert-info").addClass("alert-error").html("您没有可以用内网子网，请购买后加入网络");
			}
		}
	}
	
	 //加入网络
	 function joinNetwork(){
	 	var check = v.form();
		if (!check)	return false;
		
		if($(".alert-error").size())return false;
		
		var params = $("#obj").serialize();
		alert(params);
		var url = "vm_joinNetwork.do";
		w.joinNetwork(url,params);
	}

});
$(document).ready(function(){
	 $("#vlan_type").trigger("change");
 });

</script>
  </head>
  <body>
     <div class="modal" style="width: 500px;height: auto;margin-left: -250px;margin-top: -76px;top: 50%;">
		<p class="alert alert-info" style="margin-left: 40px;margin-top: -20px;" id="info">
			提示：请选择子网类型，再选择要加入的子网。
		</p>
		<div class="modal-content" >
			<form id="obj" class="form form-horizontal">
				<s:hidden name="obj.VH_UUID"></s:hidden>
				<s:hidden name="obj.connectId"></s:hidden>
				<fieldset>
					<legend>连接路由器</legend>
					<div class="item">
						<div class="control-label">
							虚拟网络
						</div>
						<div class="controls">
							<div class="select-con">
								<select name="vlanObj.vlan_type" id = "vlan_type" class="dropdown-select">
									<!-- <option value="">请选择</option>  -->
									<option value="1">内网</option>
									<option value="2">公网</option>
								</select>
							</div>
						</div>
					</div>
					<div class="item" id = "vlan_id" >
					<!-- 
					<div class="control-label">
							虚拟网络
						</div>
						<div class="controls">
						<s:select list="resultList" listKey="vlan_id" listValue="name"></s:select>
						</div>
						 -->
					</div>
				</fieldset>
			</form>
		</div>
	</div>
  </body>
</html>
