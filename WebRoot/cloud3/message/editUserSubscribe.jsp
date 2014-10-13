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

/*
	function getOptions(){
	alert();
		var select = $("input[name='theSubscribeForm.vm_authority']:checked").val();
		alert(select);
		if(select==0){
			document.getElementById('alls').style.display="";
			document.getElementById('operates').style.display="none";
			document.getElementById('views').style.display="none";
		}else if(select==1){
			document.getElementById('alls').style.display="none";
			document.getElementById('operates').style.display="";
			document.getElementById('views').style.display="none";
		}else{
			document.getElementById('alls').style.display="none";
			document.getElementById('operates').style.display="none";
			document.getElementById('views').style.display="";
		}
	}
	function pageOnLoad(){
		getOptions();
	}
	*/
	


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
	 function pageOnLoad() {
		 filePathDivIsShow();
	 }
 function submitRequest(thisForm){
		if(thisForm.sendstyle.value ==-1){
		     alert("发送方式不能为空！");
		     return false  ;
		}
		if(thisForm.alarmlevel.value==-2){
			     alert("告警级别不能为空！");
			     return false  ;
		}
		if(thisForm.sendmode.value==-1){
					     alert("发送模式不能为空！");
					     return false  ;
		}
		if(thisForm.sendmode.value==2){
			if(thisForm.unsendtime1.value.length==0){
			     alert("告警禁止发送起始时间不能为空！");
			     return false  ;
			}else{
				if(!checktime(thisForm.unsendtime1.value)){
				     return false  ;
				}
			}
			if(thisForm.unsendtime2.value.length==0){
			     alert("告警禁止发送终止时间不能为空！");
			     return false  ;
			}else{
				if(!checktime(thisForm.unsendtime2.value)){
				     return false  ;
				}
			}
			if(thisForm.unsendtime2.value < thisForm.unsendtime1.value){
				alert("终止时间不能小于开始时间！");
			}
		}
		if(thisForm.sendmode.value==3){
			if(thisForm.sendtime1.value.length==0){
			     alert("告警发送起始时间不能为空！");
			     return false  ;
			}else{
				if(!checktime(thisForm.sendtime1.value)){
				     return false  ;
				}
			}
			if(thisForm.sendtime2.value.length==0){
			     alert("告警发送终止时间不能为空！");
			     return false  ;
			}else{
				if(!checktime(thisForm.sendtime2.value)){
				     return false  ;
				}
			}
			if(thisForm.sendtime2.value < thisForm.sendtime1.value){
				alert("终止时间不能小于开始时间！");
			}
			
		}
		if(thisForm.subscribe.value==6){
			if(thisForm.objid.value==-1){
			     alert("业务系统不能为空！");
			     return false  ;
			}
			var selectIndex = document.getElementById("objid").selectedIndex;//获得是第几个被选中了
			var selectText = document.getElementById("objid").options[selectIndex].text //获得被选中的项目的文本
			thisForm.objname.value = selectText;
		}
		thisForm.submit();
	}
 
 	function checktime(str){
 		if(!isNumber(str)){
 			alert("请按格式输入合法数字");
		     return false  ;
 		}
 		if(str.length!=4){
 			alert("请按格式输入合法四位数字");
		     return false  ;
 		}
 		if(str.substring(0,2) < 00 ||str.substring(0,2) > 23){
 			alert("请按格式输入合法四位时间数字（HH24：mm）");
		     return false  ;
 		}
 		if(str.substring(2,4) < 00 ||str.substring(2,4) > 59){
 			alert("请按格式输入合法四位时间数字（HH24：mm）");
		     return false  ;
 		}
 		return true;
 	}
 	/* 
 	用途：检查输入字符串是否符合正整数格式 
 	输入： 
 	s：字符串 
 	返回： 
 	如果通过验证返回true,否则返回false 

 	*/ 
 	function isNumber( s ){   
 	var regu = "^[0-9]+$"; 
 	var re = new RegExp(regu); 
 	if (s.search(re) != -1) { 
 	return true; 
 	} else { 
 	return false; 
 	} 
 	} 

</script>
</head>
<body class="pop-body scrollbody" onload="pageOnLoad()">
	<s:form action="message_saveBusiUserWarning.do" method="post"
		id="theSubscribeForm">
		<input type="hidden" name="FUNCSID"
			value="<%=(String) request.getParameter("FUNCSID")%>" />
		<s:hidden name="theSubscribeForm.id" id="id"></s:hidden>
		<s:hidden name="theSubscribeForm.alarmuserid" id="alarmuserid"></s:hidden>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			
			<tr>

				<td class="til">订阅模式<font color="red">*</font>
				</td>
				<td colspan="3"> <s:select headerKey="-1" headerValue="请选择"
						list="#{'1':'全部订阅','2':'按机房订阅','3':'按机柜订阅','4':'按主机订阅','5':'按虚拟机订阅','6':'按业务订阅'}" name="theSubscribeForm.subscribe"
						id="subscribe" disabled="true">
						</s:select>
				</td>
			</tr>
			<tr>

				<td class="til">发送方式<font color="red">*</font>
				</td>
				<td><s:select headerKey="-1" headerValue="请选择"
						list="#{'0':'短信','1':'邮件'}" name="theSubscribeForm.sendstyle"
						id="sendstyle">
						<s:if test="#theSubscribeForm.sendstyle==0">短信</s:if>
						<s:if test="#theSubscribeForm.sendstyle==1">邮件</s:if>
					</s:select>
				</td><td class="til">
				<s:if test="theSubscribeForm.subscribe==6">
					业务系统
				</s:if>
				<s:if test="theSubscribeForm.subscribe==5">
					主机名称集合
				</s:if>
				<s:if test="theSubscribeForm.subscribe==4">
					虚机名称集合
				</s:if>
				<font color="red">*</font></td>
				<td>
					<%--					<s:select list="theForm.busiCenterList" name="theForm.busiCenterId" id="busiCenterId" listKey="id" listValue="name" headerKey="" headerValue="-请选择-"></s:select>--%>
					<s:if test="theSubscribeForm.subscribe==6">
					<s:select headerKey="-1" list="theSubscribeForm.busiList"
						name="theSubscribeForm.objid" id="objid" listKey="SYS_ID"
						listValue="SYS_NAME"  headerValue="请选择"></s:select>
						<s:hidden name="theSubscribeForm.objname" id="objname"></s:hidden>
					</s:if>
					<s:else>
						<s:property value="theSubscribeForm.objname"/>
						<s:hidden name="theSubscribeForm.objid" id="objid"></s:hidden>
						<s:hidden name="theSubscribeForm.objname" id="objname"></s:hidden>
					</s:else>
				</td>

				<%--					<td class="til">--%>
				<%--						主机状态 <font color="red">*</font>--%>
				<%--					</td>--%>
				<%--					<td>--%>
				<%--	                   <s:select headerKey="-1" headerValue="请选择" list="#{'1':'全部订阅','2':'按机房订阅','3':'按机柜订阅','4':'按主机订阅','5':'按虚拟机订阅','6':'按业务订阅'}" name="theSubscribeForm.subscribe" id="subscribe">--%>
				<%--	                    	<s:if test="#theSubscribeForm.subscribe==1">全部订阅</s:if>--%>
				<%--					    	<s:if test="#theSubscribeForm.subscribe==2">按机房订阅</s:if>--%>
				<%--					    	<s:if test="#theSubscribeForm.subscribe==3">按机柜订阅</s:if>--%>
				<%--					    	<s:if test="#theSubscribeForm.subscribe==4">按主机订阅</s:if>--%>
				<%--					    	<s:if test="#theSubscribeForm.subscribe==5">按虚拟机订阅</s:if>--%>
				<%--					    	<s:if test="#theSubscribeForm.subscribe==6">按业务订阅</s:if>--%>
				<%--						</s:select>         	--%>
				<%--					</td>--%>
			</tr>

			<tr>
				<td class="til">告警级别 <font color="red">*</font>
				</td>
				<td><s:select headerKey="-2" headerValue="请选择"
						list="#{'0':'全部','-1':'Clear，恢复','1':'Minor，一般','2':'Major，重要','3':'Warning，警告','4':'Critical，严重'}"
						name="theSubscribeForm.alarmlevel" id="alarmlevel">
						<s:if test="#theSubscribeForm.alarmlevel==0">全部</s:if>
						<s:if test="#theSubscribeForm.alarmlevel==-1">Clear，恢复</s:if>
						<s:if test="#theSubscribeForm.alarmlevel==1">Minor，一般</s:if>
						<s:if test="#theSubscribeForm.alarmlevel==2">Major，重要</s:if>
						<s:if test="#theSubscribeForm.alarmlevel==3">Warning，警告</s:if>
						<s:if test="#theSubscribeForm.alarmlevel==4">Critical，严重</s:if>
					</s:select>
				</td>
				<td class="til">发送模式</td>
				<td><s:select headerKey="-1" headerValue="请选择"
						list="#{'1':'产生立即发送','2':'在禁止发送段外发送','3':'在固定时间段发送'}"
						name="theSubscribeForm.sendmode" id="sendmode" onchange="filePathDivIsShow()">
						<s:if test="#theSubscribeForm.sendmode==1">产生立即发送</s:if>
						<s:if test="#theSubscribeForm.sendmode==2">在禁止发送段外发送</s:if>
						<s:if test="#theSubscribeForm.sendmode==3">在固定时间段发送</s:if>
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
				<td colspan="4" class="btnCenter"><input type="button"
					class="btn-style02" value="确定"
					onclick="javascript:submitRequest(document.getElementById('theSubscribeForm'));return false;" />
				<input type="button" class="thickbox btn-style02" value="返回"
							onclick="window.history.back()"/>
				</td>
			</tr>

		</table>
	</s:form>
</body>

</html:html>
