<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<title></title>
<style type="text/css">

.divmatnrdesc{
width: 200px;
height:100%;
overflow: hidden;
white-space: nowrap;
text-overflow:ellipsis;
}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	
	function resetForm(){
		$("#event_level").attr("value","");
		$("#event_location").attr("value","");
		$("#content").attr("value","");
	}
	
	function searchRequest(){
		obj.submit();
	}
</script>
</head>
<body  class="pop-body scrollbody">
<s:form action="paasAlarm_listPaasAlarm.do" method="post" id="obj">
<s:hidden name="id" id="id"></s:hidden>
<s:hidden name="node_type" id="node_type"></s:hidden>
<div class="scrollbody">
	<div class="pd-20 bgcolor-1">
       	<div class="bord-1 pd-10">
			<table width="100%"  border="0">
				<tr>
					<td class="til">告警级别:</td>
					<td>
						<s:select list="#{'':'-请选择-','0':'严重告警','1':'主要告警','2':'次要告警','3':'不确定告警'}" name="obj.event_level" id="event_level" cssClass="select-1"></s:select>
					</td>
					<td class="til">告警位置:</td>
					<td>
						<s:textfield name="obj.event_location" id="event_location"></s:textfield>
					</td>
					<td class="til">告警内容:</td>
					<td>
						<s:textfield name="obj.content" id="content" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td colspan="10" class="pd-10">
						<div align="center">
							<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" onclick = "javascript:searchRequest()"  /></span>
							<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" onclick = "javascript:resetForm()" /></span>
						</div>
					</td>
				</tr>
			</table>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
					<!-- <th>选择</th> -->
					<th>级别</th>
					<th onclick="sort(theTable,1,'string')">告警编号</th>
					<th onclick="sort(theTable,2,'string')">告警类型</th> 
					<th onclick="sort(theTable,3,'string')">告警内容</th>
					<th onclick="sort(theTable,4,'string')">告警级别</th>
					<!-- <th onclick="sort(theTable,5,'string')">当前状态</th>
					<th onclick="sort(theTable,6,'String')">告警次数</th> -->
					<th onclick="sort(theTable,7,'string')">告警位置</th>
					<th onclick="sort(theTable,8,'date')">告警产生时间</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="resultList" id="theBean">
			  	<tr>
			  	<%-- 	<td><input name="checkboxid" type="checkbox" value="<s:property value="#theBean.EVENT_ID"/>" stu="<s:property value='#theBean.event_stat' default='0'/>"/></td> --%>
			  		<td>
			  		    <s:if test="#theBean.event_level==0">
			  		       <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm1.png" width="16" height="16" state="1" />
			  		    </s:if>
			  			<s:elseif test="#theBean.event_level==1">
                           <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm2.png" width="16" height="16" state="1" />
                        </s:elseif>
			  			<s:elseif test="#theBean.event_level==2">
			  			   <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm3.png" width="16" height="16" state="1" />
			  			</s:elseif>
			  			<s:elseif test="#theBean.event_level==3">
			  			   <img src="<%=request.getContextPath()%>/sxcloud/js/alarm/images/alarm4.png" width="16" height="16" state="1" />
			  			</s:elseif>
                    </td>
			  		<td><s:property value="#theBean.event_num"/></td>
			  		<td><s:property value="#theBean.event_type"/></td>
			  		<td title="<s:property value="#theBean.content"/>">
			  			<div class="divmatnrdesc"><s:property value="#theBean.content"/></div>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.event_level==0"><span style="color:red;">严重告警<span/></s:if>
			  			<s:elseif test="#theBean.event_level==1"><span style="color:blue;">主要告警<span/></s:elseif>
			  			<s:elseif test="#theBean.event_level==2"><span style="color:green;">次要告警<span/></s:elseif>
			  			<s:elseif test="#theBean.event_level==3"><span style="color:yellow;">不确定告警<span/></s:elseif>
			  		</td>
			  		<%-- <td>
			  			未处理
			  			<s:if test="#theBean.event_stat==0">未处理</s:if>
			  			<s:elseif test="#theBean.event_stat==1">已处理</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.event_count" default="1"/></td> --%>
			  		<td><s:property value="#theBean.event_location"/></td>
			  		<td><s:property value="#theBean.alarm_time" default="无"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
			<div class="pages mgb-10"><!-- 分页 -->
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>
		</div>
	</div>
</div>
</s:form>
</body>
