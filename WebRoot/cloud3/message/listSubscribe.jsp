<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.subscribe.value = -1;
		theForm.sendstyle.value = -1;
		theForm.sendmode.value = -1;
		theForm.alarmlevel.value = -2;
	}

   function searchRequest(theForm) { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'message_addAlarmUser.do' ;
		theForm.submit();
 	}
 	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改账户！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条账户信息");
 	    return false ;
 	    }
 	    theForm.action = 'message_editUserSubscribe.do' 
		theForm.submit();
 	}
 	function delRequest(theForm) {
 	var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.id.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除账户！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条账户信息");
 	    return false ;
 	    }
 	    theForm.action = 'message_delUserSubscribe.do';  
		theForm.submit();
 	}
</script>
</head>
<body onLoad="self.focus();document.theSubscribeForm.sendstyle.focus()">
<s:form action="message_listUserSubscribe" method="post" cssStyle="theForm" id="theSubscribeForm">
<s:hidden name="theSubscribeForm.id" id="id"></s:hidden>
<s:hidden name="theSubscribeForm.alarmuserid" id="alarmuserid"></s:hidden>
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                   <tr>
<%--                    <td class="til">订阅用户:</td>--%>
<%--                   <td>--%>
<%--						<s:select headerKey="-1" list="theSubscribeForm.userList"--%>
<%--						name="theSubscribeForm.alarmuserid" id="alarmuserid" listKey="ID"--%>
<%--						listValue="NAME"  headerValue="请选择"></s:select>--%>
<%--                    </td>--%>
                    <td class="til">发送方式:</td>
                    <td>
                    	<s:select headerKey="-1" headerValue="请选择"
						list="#{'0':'短信','1':'邮件'}" name="theSubscribeForm.sendstyle"
						id="sendstyle">
						</s:select>
                    </td>
                    <td class="til">订阅模式:</td>
                    <td>
                  	  <s:select headerKey="-1" headerValue="请选择"
						list="#{'1':'全部订阅','2':'按机房订阅','3':'按机柜订阅','4':'按主机订阅','5':'按虚拟机订阅','6':'按业务订阅'}" name="theSubscribeForm.subscribe"
						id="subscribe">
						</s:select>
                    </td>
                    <td class="til">告警级别 :</td>
                    <td>
                    	<s:select headerKey="-2" headerValue="请选择"
						list="#{'0':'全部','-1':'Clear，恢复','1':'Minor，一般','2':'Major，重要','3':'Warning，警告','4':'Critical，严重'}"
						name="theSubscribeForm.alarmlevel" id="alarmlevel">
						</s:select>
                    </td>
                    <td class="til">发送模式:</td>
                    <td>
                  	  <s:select headerKey="-1" headerValue="请选择"
						list="#{'1':'产生立即发送','2':'在禁止发送段外发送','3':'在固定时间段发送'}"
						name="theSubscribeForm.sendmode" id="sendmode">
						</s:select>
                    </td>
                  </tr>
                  
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest(document.getElementById('theSubscribeForm'))" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theSubscribeForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="返回" onclick = "javascript:window.location.href='message_listAlarmUser.do'; return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="修改" onclick = "modRequest();return false;" /></li>
				<li><input type="button" class="thickbox btn-style02" value="删除" onclick = "delRequest(document.getElementById('theSubscribeForm'));return false;" /></li>
			</ul>
			<jsp:include page="../../sxcloud/inc/Pagination.jsp?formId=theSubscribeForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">编号</th>
				   <th onclick="sort(theTable,1,'string')">发送方式</th>  
                   <th onclick="sort(theTable,2,'string')">订阅模式</th>
<%--                   <th>订阅对象id</th>   --%>
                   <th onclick="sort(theTable,3,'string')">订阅对象名称</th>            
                   <th onclick="sort(theTable,4,'string')">告警级别</th>
                   <th onclick="sort(theTable,5,'string')">发送模式</th>
                   <th onclick="sort(theTable,6,'date')">发送开始时间</th>
                   <th onclick="sort(theTable,7,'date')">发送结束时间</th>
                   <th onclick="sort(theTable,8,'date')">禁止发送开始时间</th>
                   <th onclick="sort(theTable,9,'date')">禁止发送结束时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:property value="#theBean.subscribeList"/>
			  <s:iterator value="theSubscribeForm.subscribeList" id="theBean">
						<tr>
							<td><input name="checkboxid" type="checkbox" value="<s:text name="#theBean.id"/>"/></td>
							<td>
								<s:if test="#theBean.sendstyle==0">短信</s:if>
								<s:if test="#theBean.sendstyle==1">邮件</s:if>
							</td>
							<td>
								<s:if test="#theBean.subscribe==1">全部订阅</s:if>
								<s:if test="#theBean.subscribe==2">按机房订阅</s:if>
								<s:if test="#theBean.subscribe==3">按机柜订阅</s:if>
								<s:if test="#theBean.subscribe==4">按主机订阅</s:if>
								<s:if test="#theBean.subscribe==5">按虚拟机订阅</s:if>
								<s:if test="#theBean.subscribe==6">按业务订阅</s:if>
							</td>
<%--							<td><s:property value="#theBean.objid"/></td>--%>
							<td><s:property value="#theBean.objname"/></td>
							<td>
								<s:if test="#theBean.alarmlevel==0">全部</s:if>
								<s:if test="#theBean.alarmlevel==-1">Clear，恢复</s:if>
								<s:if test="#theBean.alarmlevel==1">Minor，一般</s:if>
								<s:if test="#theBean.alarmlevel==2">Major，重要</s:if>
								<s:if test="#theBean.alarmlevel==3">Warning，警告</s:if>
								<s:if test="#theBean.alarmlevel==4">Critical，严重</s:if>
							</td>
							<td>
								<s:if test="#theBean.sendmode==1">产生立即发送</s:if>
								<s:if test="#theBean.sendmode==2">在禁止发送段外发送</s:if>
								<s:if test="#theBean.sendmode==3">在固定时间段发送</s:if>
							</td>
							<td>
								<s:property value="#theBean.sendtime1"/>
							</td>
							<td>
								<s:property value="#theBean.sendtime2"/>
							</td>
							<td>
								<s:property value="#theBean.unsendtime1"/>
							</td>
							<td>
								<s:property value="#theBean.unsendtime2"/>
							</td>
						</tr>
				</s:iterator>		  
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>
