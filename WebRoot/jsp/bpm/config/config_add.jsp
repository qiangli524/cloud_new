<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java"  contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加配置</title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/jsp/js/workflow.constant.js"></script>
  </head>
  <script type="text/javascript">
		$(document).ready(function(){
			//渲染步骤
			renderStep();
			//根据组加载人
			renderUser();
			//类型点击事件	
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
			//流程改变事件
			$('#resourceName').change(function(){
				renderStep();
			});
			//流程改变事件
			$('#groupName').change(function(){
				renderUser();
			});
		});
		function renderStep(){
			$('#stepId').html("");
			$.ajax({
        		type : 'post',
        		async : false,
        		url : '<%=request.getContextPath()%>/bpm/workorder_getAllStepInfo.do',
       			data:{"obj.resourceName":$('#resourceName').val()},
       			dataType: 'json',
       			success : function(data){
       				$(data).each(function(i,e){
       					if(e.stepName!='开始' && e.stepName!='结束' ){
       						$('#stepId').append('<option value="'+e.stepId+'">'+e.stepName+'('+e.stepId+')</option>');
       					}
       				});
       			},
       			error : function(data,textStatus){
       				console.log('error:' + data);
       			}
       		});	
		}
		function save(){
			 var theForm = $('#theForm');
			 theForm.action = "<%=path%>/bpm/config_add.do";
	 		 theForm.submit();
		}
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
  </script>
  <body>
  <div class="mainbody">
    	<div class="pd-20 bgcolor-1">
            <h2 class="utt-1">添加配置</h2>
            <div class="bord-1 pd-10">
                <form action="<%=request.getContextPath() %>/bpm/config_add.do" method="post" class="table-f1" id="theForm">  
                    <table width="100%" border="0" cellspacing="0" class="pop-table nosize">
                        <tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>流程名称：
                            </td>
                            <td>
                                <select id="resourceName" style="width: 150px;" name="config.resourceName" class="select-1">
									 <s:iterator value="pmbs" id="pmb">
										<option value="${pmb.ppName }" <s:if test="config.resourceName.equals(#pmb.ppName)">selected</s:if> >${pmb.ppName }</option>
									 </s:iterator>
								</select>            
                            </td>
                       	</tr>
                       	<tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>环节名称：
                            </td>
                            <td>
                            	<select name="config.stepId" id="stepId" class="select-1">
            					</seletc>
                            </td>
                        </tr>
                        <tr>
                            <td class="til">
                               	 <font color="red">*&nbsp;</font>执行类型：
                            </td>
                            <td >
                            	 <span class="check-s" style="width:80px"><input type="radio" class="configType"  name="config.exeType" checked value="0"/> 执行人</span>
                    			 <span class="check-s" style="width:80px"><input type="radio" class="configType"  name="config.exeType" value="1" /> 执行组</span>
                            </td>                             
                        </tr>
                        <tr>
                            <td class="til">
                                <font color="red">*&nbsp;</font>选择执行组：
                            </td>
                            <td >
                            	<select id="groupName" name="config.groupName" class="select-1">
				            		<s:iterator value="groupList" id="group">
										<option value="${group.groupId }">${group.groupName }</option>
									 </s:iterator>
				            	</seletc>
                            </td>
                           
                        </tr>
                        <tr class="selectUser">
					        	<td class="til"> <font color="red">*&nbsp;</font>选择执行人： </td>
					            <td >
					            	<select id="userName" name="config.userName" style="width:150px">
					            	</seletc>
					            </td>
				        </tr>
				        
				        
				        <tr class="callTypeTr">
				        	<td class="til"><font color="red">*&nbsp;</font>派发方式： </td>
				            <td>
				            	<select name="config.callType"  class="select-1">
				            		<option value="0">智能</option>
				            		<option value="1">共享</option>
				            	</seletc>     
				            </td>
				        </tr>
                    </table>
                    <p class="tc mgt-20 mgb-20">
	                    <span class="ubtn-green">
	                    	<input type="button" onclick="save()" value="确定" />
	                    </span>
	                    <span class="ubtn-blue mgl-20">
	                    	<input type="button" onclick="history.back(-1)" value="返回" />   
	                    </span>
                	</p>
            	</form>
            </div>
        </div>
</div>
</html>
