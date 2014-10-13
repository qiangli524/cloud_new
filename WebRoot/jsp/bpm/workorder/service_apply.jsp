<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>提交工单</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/jsp/css/validation.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.form.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/jattachment.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workorder.constant.js"></script>
<script type="text/javascript">
//初始加载页面时
$(document).ready(function(){
	
	//初始化问题类型
	renderQuestion();
	
	//手机验证规则
	$.validator.addMethod("mobile",function(value, element, params){  
		var length = value.length;
		var mobile =  /^0?(13[0-9]|15[012356789]|18[0236789]|14[57])[0-9]{8}$/
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请填入正确的手机号码！");  
  
     var v = $("#frm").validate({
        //debug:true,
        rules: {
           'obj.account':{required:true},
           'step.objData.type': {required:true},
		   'step.objData.phone': {required:true,mobile:true},
		   'step.objData.email': {required: true,email: true},
		   'step.objData.content': {required: true}
		},
        messages: {
           'obj.account':"请选择客户",
           'step.objData.type':  "请选择问题分类",
		   'step.objData.phone': "请输入手机号码",
		   'step.objData.email': {required: "请输入Email地址",email: "请输入正确的email地址"},
		   'step.objData.content': {required: "请输入问题描述"}										
  		}
    });

     
     //选择客户页面
     $("#selectCustomer").click(function(){
    	 var url = "url:workorder_queryCustomerList.do?customer.ACCOUNT=nothing";
         $.dialog({
             id: 'selectCustomer',
             title: '选择客户',
             width: document.body.scrollWidth-20,
             height: document.body.scrollHeight-20,
             lock: true,
             content: url
         });
     })  
     
     //提交工单
     $("#apply").click(function(){
		var check = v.form();
		if(!check)return false;
		//把表单的数据进行序列化
		var params = $("form").serialize();
		mask('正在添加,请稍后....','0.5','50px');
		$.ajax({
			url:"<%=request.getContextPath() %>/bpm/workorder_ajaxSave.action",
			type:"POST",
			data:params,
			dataType:"json",
			success:function(data){
				if(data.result == "success"){
				    $("#workflowId").val(data.entryId);
				    save();
				}
			}
		});
	});
	
	//重置表单数据
	$("#reset").click(function(){
		$("#name").val("");
		$("#phone").val("");
		$("#email").val("");
		$("#account").val("");
		$("#content").val("");
	});
     
	//问题类型选择事件
	$("#questionType").change(function(){
		var qType = $(this).children('option:selected').val()
		var content = "";
		if(qType != ""){
			content += '<td class="til"><font color="red">*&nbsp;</font>选择常见问题：</td><td>';
			$.each(question,function(i,qt){
				if(qType == qt.questionType){
					$.each(qt.questions,function(i,q){
					
						if(i%2 == 0){
							content += '<div class="mgb-10">';
						}
						if(i==0){
							content += '<span class="check-s" style="width:200px"><input type="radio"	name="step.objData.question" value=' +q+' checked />'+ q +'</span>';
						}else{
							content += '<span class="check-s" style="width:200px"><input type="radio"	name="step.objData.question" value=' +q+' />'+ q +'</span>';
						}
							
						if(i%2 != 0){
							content += '</div>';
						}
					})	
				}
			})
			content += '</td>';
			$("#questionContent").html(content);
			$("#questionContent").show();
		}else{
			$("#questionContent").html("");
			//$("#questionContent").hidden();
		}
	});
	
	//附件上传例子
	var ops1 = {"operator":"upload"}
	$("#bt_upload").attachment(ops1);
});

	//初始化问题类型
	function renderQuestion(){
		var content="";
		$.each(question,function(index,qt){
		 content +=	'<option value='+qt.questionType+'>'+qt.questionType+'</option>';
		})
		$("#questionType").append(content);
	}

	function save(){
		document.frm.action ="workorder_helpSave.action";
		document.frm.submit(); 
	}
</script>
</head>
<body>
<div class="mainbody">
    	<div class="pd-20 bgcolor-1">
            <h2 class="utt-1">代填工单</h2>
            <div class="bord-1 pd-10">
            	<form name='frm' id="frm" action="" method="post">
					<input type="hidden" name="obj.id" value="${obj.id }"/> 
					<input type="hidden" name="workflowId" id="workflowId"/> 
					<input type="hidden" name="obj.resourceName" value="workorder"/>
					<input type="hidden" name="obj.orderTitle" value="代填工单"/>
					<!-- 保存后重定向标记 --> 
					<input type="hidden" name="position"	value="${position}"/>
                    <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
                    	 <tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>客户名称：
                            </td>
                            <td>
                            	<input type="hidden" name="obj.account" id="account"/>
                           		<input type="text" class="inpt-2" id="name" readonly="readonly"/>
                           		 <span class="ubtn-1 mgl-20">
			                    	<input type="button" id="selectCustomer" value="选择" />
			                    </span>
							</td>
                       	</tr>
                    
                        <tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>选择问题分类：
                            </td>
                            <td>
                            	<select class="select_box"	name="step.objData.type" id="questionType" class="select-1">
										<option value="">请选择</option>
								</select>
							</td>
                       	</tr>
                       	<tr id="questionContent">
						</tr>
                       	<tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>问题描述：
                            </td>
                            <td>
                            	<textarea name="step.objData.content" class="textarea-1" id="content" style="width: 50%;height: 80px;"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td class="til">
                               	 <font color="red">*&nbsp;</font>手机号码：
                            </td>
                           <td><input type="text" name="step.objData.phone"
														class="inpt-2" id="phone"/>
								</td>                            
                        </tr>
                        <tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>邮箱：
                            </td>
                           <td><input type="text" name="step.objData.email"
														class="inpt-2" id="email"/>
						   </td>
                           
                        </tr>                       
                    </table>
                    <p class="tc mgt-20 mgb-20">
	                    <span class="ubtn-green">
	                    	<input type="button" id="apply" value="提交" />
	                    </span>
	                    <span class="ubtn-orange mgl-20">
	                    	<input type="button" id="reset" value="重置" />   
	                    </span>
                	</p>
            	</form>
            </div>
        </div>
</div>
</body>
</html>
