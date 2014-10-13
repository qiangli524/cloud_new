<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>

<link rel="stylesheet" href="sxcloud/cresources/ztree/zTree.css"
		type="text/css" />
	<link rel="stylesheet"
		href="sxcloud/cresources/ztree/zTreeStyle/zTreeStyle.css"
		type="text/css" />
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.core-3.1.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.excheck-3.1.js"></script>
	<script type="text/javascript"
		src="sxcloud/cjs/ztree/jquery.ztree.exedit-3.1.js"></script>
<script>


 function submitRequest(){
		if($("select[id=sendstyle] option:selected").val() ==-1){
		     alert("发送方式不能为空！");
		     return false  ;
		}
		
		if($("select[id=alarmlevel] option:selected").val()==-2){
			     alert("告警级别不能为空！");
			     return false  ;
		}
		if($("select[id=sendmode] option:selected").val()==-1){
					     alert("发送模式不能为空！");
					     return false  ;
		}
		if($("select[id=sendmode] option:selected").val()==2){
			if(document.getElementById("unsendtime1").value.length==0){
			     alert("告警禁止发送起始时间不能为空！");
			     return false  ;
			}
			if(document.getElementById("unsendtime2").value.length==0){
			     alert("告警禁止发送终止时间不能为空！");
			     return false  ;
			}
		}
		if($("select[id=sendmode] option:selected").val()==3){
			if(document.getElementById("sendtime1").value.length==0){
			     alert("告警发送起始时间不能为空！");
			     return false  ;
			}
			if(document.getElementById("sendtime2").value.length==0){
			     alert("告警发送终止时间不能为空！");
			     return false  ;
			}
		}
 	window.parent.submitRequest();
 }
	 function filePathDivIsShow() {
		 var sendmode = $("#sendmode").find("option:selected").val();
		 if(sendmode == 1) {
			 $("tr.sendmode2").hide();
			 $("tr.sendmode3").hide();
		 } else if(sendmode == 2){
			 $("tr.sendmode2").show();
			 $("tr.sendmode3").hide();
		 } else if(sendmode == 3){
			 $("tr.sendmode2").hide();
			 $("tr.sendmode3").show();
		 }
	 }
</script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.sendstyle.focus()">
	<s:form action="message_saveUserResources.do" method="post" id="theForm" >
	    <input type="hidden" name="FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>"/>
	    <s:hidden name="theForm.ID"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
		
					<td class="til">
						发送方式<font color="red">*</font>
					</td>
					<td>
						 <s:select headerKey="-1" headerValue="请选择" list="#{'0':'短信','1':'邮件'}" name="theForm.sendstyle" id="sendstyle">
	                    	<s:if test="#theForm.sendstyle==0">短信</s:if>
					    	<s:if test="#theForm.sendstyle==1">邮件</s:if>
						</s:select>   
					</td>
<%--					<td class="til">--%>
<%--						主机状态 <font color="red">*</font>--%>
<%--					</td>--%>
<%--					<td>--%>
<%--	                   <s:select headerKey="-1" headerValue="请选择" list="#{'1':'全部订阅','2':'按机房订阅','3':'按机柜订阅','4':'按主机订阅','5':'按虚拟机订阅','6':'按业务订阅'}" name="theForm.subscribe" id="subscribe">--%>
<%--	                    	<s:if test="#theForm.subscribe==1">全部订阅</s:if>--%>
<%--					    	<s:if test="#theForm.subscribe==2">按机房订阅</s:if>--%>
<%--					    	<s:if test="#theForm.subscribe==3">按机柜订阅</s:if>--%>
<%--					    	<s:if test="#theForm.subscribe==4">按主机订阅</s:if>--%>
<%--					    	<s:if test="#theForm.subscribe==5">按虚拟机订阅</s:if>--%>
<%--					    	<s:if test="#theForm.subscribe==6">按业务订阅</s:if>--%>
<%--						</s:select>         	--%>
<%--					</td>--%>
<td class="til">
						告警级别 <font color="red">*</font>
					</td>
					<td >
						<s:select headerKey="-2" headerValue="请选择" list="#{'0':'全部','-1':'Clear，恢复','1':'Minor，一般','2':'Major，重要','3':'Warning，警告','4':'Critical，严重'}" name="theForm.alarmlevel" id="alarmlevel">
	                    	<s:if test="#theForm.alarmlevel==0">全部</s:if>
					    	<s:if test="#theForm.alarmlevel==-1">Clear，恢复</s:if>
					    	<s:if test="#theForm.alarmlevel==1">Minor，一般</s:if>
					    	<s:if test="#theForm.alarmlevel==2">Major，重要</s:if>
					    	<s:if test="#theForm.alarmlevel==3">Warning，警告</s:if>
					    	<s:if test="#theForm.alarmlevel==4">Critical，严重</s:if>
						</s:select>
					</td>
				</tr>

				<tr >
<%--				    <td class="til">--%>
<%--						告警级别 <font color="red">*</font>--%>
<%--					</td>--%>
<%--					<td >--%>
<%--						<s:select headerKey="-1" headerValue="请选择" list="#{'0':'全部','-1':'Clear，恢复','1':'Minor，一般','2':'Major，重要','3':'Warning，警告','4':'Critical，严重'}" name="theForm.alarmlevel" id="alarmlevel">--%>
<%--	                    	<s:if test="#theForm.alarmlevel==0">全部</s:if>--%>
<%--					    	<s:if test="#theForm.alarmlevel==-1">Clear，恢复</s:if>--%>
<%--					    	<s:if test="#theForm.alarmlevel==1">Minor，一般</s:if>--%>
<%--					    	<s:if test="#theForm.alarmlevel==2">Major，重要</s:if>--%>
<%--					    	<s:if test="#theForm.alarmlevel==3">Warning，警告</s:if>--%>
<%--					    	<s:if test="#theForm.alarmlevel==4">Critical，严重</s:if>--%>
<%--						</s:select>--%>
<%--					</td>--%>
					<td class="til" colspan="1">
						发送模式
					</td>
					<td colspan="3">
                        <s:select headerKey="-1" headerValue="请选择" list="#{'1':'产生立即发送','2':'在禁止发送段外发送','3':'在固定时间段发送'}" name="theForm.sendmode" id="sendmode" onchange="filePathDivIsShow()">
	                    	<s:if test="#theForm.sendmode==1">产生立即发送</s:if>
					    	<s:if test="#theForm.sendmode==2">在禁止发送段外发送</s:if>
					    	<s:if test="#theForm.sendmode==3">在固定时间段发送</s:if>
						</s:select>         	
					</td>
				</tr>
				<tr class="sendmode3" style="display: none">
				<td class="til">告警发送起始时间</td>
				<td><s:textfield cssClass="txt"
						name="theSubscribeForm.sendtime1" id="sendtime1" /><font
					color="red">*</font>(例如："0900"、"0905"、"1800"、"1805" ；)</td>
				<td class="til">告警发送终止时间</td>
				<td><s:textfield cssClass="txt"
						name="theSubscribeForm.sendtime2" id="sendtime2" /><font
					color="red">*</font>(格式同"告警发送起始时间")</td>
			</tr>
			<tr class="sendmode2" style="display: none">
				<td class="til">告警禁止发送起始时间</td>
				<td><s:textfield cssClass="txt"
						name="theSubscribeForm.unsendtime1" id="unsendtime1" /><font
					color="red">*</font>(例如："0900"、"0905"、"1800"、"1805" ；)</td>
				<td class="til">告警禁止发送终止时间</td>
				<td><s:textfield cssClass="txt"
						name="theSubscribeForm.unsendtime2" id="unsendtime2" /><font
					color="red">*</font>(格式同"告警禁止发送起始时间")</td>
			</tr>
				
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="btn-style02" value="确定" onclick="submitRequest();" />
					<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
					</td>
				</tr>

			</table>
	</s:form>
</body>

</html:html>
