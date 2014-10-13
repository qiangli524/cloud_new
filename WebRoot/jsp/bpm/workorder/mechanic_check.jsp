<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>运维处理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath%>jsp/css/alicss.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/dateformat.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/workorder_back.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/jsp/js/json.util.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		var entryId = "${requestScope.obj.entryId}";
		//渲染页面
		renderUser();
		$("#zhuanfa").hide();
		getWorkFlowByEntryId(entryId);
		getStepListByEntryId(entryId);
		
		//是否选中转发
		$("#toOther").change(function() { 
			if($("#toOther").attr("checked")){
				$("#zhuanfa").show();
			}else{
				$("#zhuanfa").hide();
			}
		}); 
		
		$('.configType').click(function(){
				var type = $('input:radio[name="config.exeType"]:checked').val();
				if(type==0){
					$('.selectUser').show();
					$('.selectGroup').show();
				}else if(type==1){
					$('.selectUser').hide();
					$('.selectGroup').show();					
				}
			});	
		//根据组改变时间
		$('#groupName').change(function(){
			renderUser();
		});
		
		//保存流程
		$("#apply").click(function(){
			var params = $("form").serialize();
			//把表单的数据进行序列化
			mask('正在处理,请稍后....','0.5','50px');
			if($("#toOther").attr("checked")){
				//转发
				forword();
				
				<%-- $.ajax({
						url:"<%=request.getContextPath() %>/bpm/workorder_addProxy.action",
						type:"POST",
						data:params,
						dataType:"json",
						success:function(data){
							if(data.result == "success"){
							    alert("转发成功！");	
							}
						}
				});		 --%>			
			}else{	
				$.ajax({
					url:"<%=request.getContextPath() %>/bpm/workorder_ajaxSave.action",
					type:"POST",
					data:params,
					dataType:"json",
					success:function(data){
						if(data.result == "success"){
							removeMask();
						    alert("处理成功！");	
						    history.back();
						}
					}
				});			
			}
	    });
})

//加载人员
 	function renderUser(){
			$('#userName').html("");
			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=request.getContextPath()%>/bpm/config_getUsersList.do',
       			data:{"groupId":$('#groupName').val()},
       			dataType: 'json',
       			success : function(data){
       				$(data).each(function(i,e){
       					$('#userName').append('<option value="'+e.account+'">'+e.userName+'</option>');
       				});
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});	
		}
		
/*
 * 转发
 */
function forword(){
	var params = $("form").serializeObject(); 
	params['proxy.entryId']= $("#entryId").val();	
	params['obj.entryId']= $("#entryId").val();
	
	var type = $('input:radio[name="config.exeType"]:checked').val();
	if(type==0){
		params['proxy.userName'] = $('#userName').val();
	}else if(type==1){
		params['proxy.groupName']= $('#groupName').val();
	}
	
	params['proxy.callType']= 0;
	//params['obj.account']= '${obj.account}';
	
	$.ajax({
       		type : 'post',
       		async : false,
       		url : '<%=path%>/bpm/workorder_addProxy.action',
      			data: params,
      			dataType: 'json',
      			success : function(data){
      				if(data.result=='success'){
      					removeMask();
      					alert("转发成功");
      					history.back();
      				}
      			},
      			error : function(data,textStatus){
      				console.log('error:' + data);
      			}
      	});				
}
</script>
</head>

<body>
	<div id="main" class="mt20 clearfix">
		<div sytle="padding:20px;">
			<div class="uitopg">
				<!--新工单透明化 start-->
				<div class="record-review">
					<div class="record-hd cc">
						<span class="fr"> <a href="javascript:history.back()">返回列表</a>
						</span>
					</div>
					<div class="record-bd">
						<div class="question-title">问题标题:<div id="orderTitle"></div>
						<div class="question-status">
							<span class="record-id"></span> 
							<span class="record-status"></span> 
							<span class="submitTime"></span>
						</div>
					</div>
				</div>
			</div>
			<!--新工单透明化 end-->

			<!--沟通记录start-->
			<div class="module-wrap">
				<h2 class="section-title">沟通记录</h2>
				<div class="bordersection mb20">
					<ul class="bubble-wrap">
						<li id="before_me"></li>
					</ul>
				</div>
				<!--沟通记录end-->

				<!--留言反馈表单start-->

				<h2 class="section-title">处理意见：</h2>
				<form id="faq_reply_form" target="_self" action="/workorder/workorder_updateMalfunctionInfo.action" method="post"	enctype="multipart/form-data">
					<input type="hidden" name="obj.id"  value="${obj.id }">
					<input type="hidden" name="obj.entryId" id="entryId" value="${obj.entryId }">
					<input type="hidden" name="obj.resourceName" id="resourceName" value="workorder">
					<div class="textarea-autofit">
						<textarea id="reply_content" name="step.objData.answer"></textarea>
					</div>

					<p class="clearfix pt20 pb20" id="satisfy_block">
					<label>故障类型</label>
					<select name="step.objData.malfunctionType">
						<option>--请选择--</option>
						<option>云平台故障</option>
						<option>骨干网、互联互通网络故障</option>
						<option>FTP服务故障</option>
						<option>iSCSI挂载服务故障</option>
						<option>备份软件无法登陆/功能无法使用</option>
						<option>备份数据无法恢复</option>
						<option>趋势安全服务端软件故障</option>
						<option>趋势安全客户端软件故障</option>
						<option>客户咨询或客户原因</option>
					</select>
					
					<label>停机时间</label><input type="text" name="step.objData.dateRange">
					<select name="step.objData.dateUnit">					
						<option value="分钟">分钟</option>
						<option value="时">时</option>
						<option value="天">天</option>
					</select>
					
                    <label><input type="checkbox" name="toOther" id="toOther">是否转发</label>
                    
                    <table id="zhuanfa">
                           	 <tr>
					        	<th><span class="font-red pdr-5">*</span>类型：</th>
					            <td>
					            	<div class="mgb-10">
					                    <span class="check-s" style="width:80px"><input type="radio" class="configType"  name="config.exeType" checked value="0"/> 执行人</span>
					                    <span class="check-s" style="width:80px"><input type="radio" class="configType"  name="config.exeType" value="1" /> 执行组</span>
					                </div>        
					            </td>
					        </tr>
					        <tr class="selectGroup selectUser">
					        	<th width="15%"><span class="font-red pdr-5">*</span>选择执组：</th>
					            <td>
					            	<select id="groupName" name="config.groupName" style="width:150px">
					            		<s:iterator value="groupList" id="group">
											<option value="${group.groupId }">${group.groupName }</option>
										 </s:iterator>
					            	</seletc>
					            </td>
					        </tr>
					        <tr class="selectUser">
					        	<th width="15%"><span class="font-red pdr-5">*</span>选择执行人：</th>
					            <td>
					            	<select id="userName" name="config.userName" style="width:150px">
					            		<!--
					            		<s:iterator value="userList" id="user">
											<option value="${user.account }">${user.userName }</option>
										 </s:iterator>
										 -->
					            	</seletc>
					            </td>
					        </tr>
					        </table>

					<div class="toolbar mt10">
						<div class="right">
							<input class="btn-fmsubmiti y-btn y-btn-mini left" style="margin:0;min-width:100px;" type="button" id="apply" value="提交">
						</div>
					</div>
				</form>
				<!--留言反馈表单end-->
			</div>
		</div>
	</div>
	</div>
</body>
</html>
