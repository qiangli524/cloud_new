<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="/sxcloud/common/taglib.jsp" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<style type="text/css">
html,body{width:100%;height:100%}
#overDiv{
	background-color: #000;
	width: 100%;
	height: 100%;
	left:0;
	top:0;/*FF IE7*/
	filter:alpha(opacity=40);/*IE*/
	opacity:0.4;/*FF*/
	z-index:1;
	
	position:fixed!important;/*FF IE7*/
	position:absolute;/*IE6*/
	
	_top:       expression(eval(document.compatMode &&
				document.compatMode=='CSS1Compat') ?
				documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
				document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
	
}

.dlDiv{
	background-color:white;
	background-image:url(dlbj.jpg);
	background-repeat:repeat-x;
	border:2px solid #06F;
	z-index:2;
	left:37%;/*FF IE7*/
	top:29%;/*FF IE7*/
	
	margin-left:-150px!important;/*FF IE7 该值为本身宽的一半 */
	margin-top:-60px!important;/*FF IE7 该值为本身高的一半*/
	
	margin-top:0px;
	
	position:fixed!important;/*FF IE7*/
	position:absolute;/*IE6*/
	
	_top:       expression(eval(document.compatMode &&
				document.compatMode=='CSS1Compat') ?
				documentElement.scrollTop + (document.documentElement.clientHeight-this.offsetHeight)/2 :/*IE6*/
				document.body.scrollTop + (document.body.clientHeight - this.clientHeight)/2);/*IE5 IE5.5*/
}

.lobu{
	width:65px;
	height:25px;
	background:blue;
	font-size:13px;
	border:#0099CC solid 1px;
}

</style>
<script type="text/javascript">
	function show(id){
		document.getElementById("overDiv").style.display = "block" ;
		document.getElementById(id).style.display = "block" ;
	}
	function closeDiv(id){
		document.getElementById("overDiv").style.display = "none" ;
		document.getElementById(id).style.display = "none" ;
	}
</script>
</head>
<body>
<s:form action="healthy_getWarnInfo.do" method="post" id="theForm" cssClass="theForm"> 
			 <div class="blue-wrap noborder">
		<input type="hidden" name= "id" value="<%=(String)request.getAttribute("id")%>"/>
		<input type="hidden" name= "tag" value="<%=(String)request.getAttribute("tag")%>"/>
		<div class="table-head">
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>  
			  		<th onclick="sort(theTable,0,'string')">告警编号</th>
					<th onclick="sort(theTable,1,'string')">告警标题</th> 
					<th onclick="sort(theTable,2,'int')">告警类型</th> 
					<th onclick="sort(theTable,3,'int')">当前状态</th>
					<th onclick="sort(theTable,4,'string')">告警位置</th>
					<th onclick="sort(theTable,5,'date')">告警产生时间</th>
					<th>操作</th>
			  </tr>
			  </thead>
			  <tbody id = "ajaxjquery">
			  <s:if test="theForm.resultList.size>0">
			  <s:iterator value="theForm.resultList" id="theBean">
			  	<tr > 
			  		<td ><s:property value="#theBean.EVENT_ID"/></td>
			  		<td ><s:property value="#theBean.TITILE"/></td>
			  		<td >
			  			<s:if test="#theBean.EVENT_TYPE==0">应用告警</s:if>
			  			<s:elseif test="#theBean.EVENT_TYPE==1">虚拟机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==2">物理主机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==3">机房告警</s:elseif>
			  		</td>
			  		<td >
			  			<s:if test="#theBean.EVENT_STAT==0">未处理</s:if>
			  			<s:elseif test="#theBean.EVENT_STAT==1">已处理</s:elseif>
			  		</td>
			  		<td ><s:property value="#theBean.EVENT_LOCATION"/></td>
			  		<td ><s:property value="#theBean.ALARM_TIME"/></td>
			  		<td ><input type="button" onclick="show('aa<s:property value="#theBean.EVENT_ID"/>')" value="详细>>" /></td>
			  		
			  	</tr>
			  </s:iterator>
			  </s:if>
			  <s:else>
			  <tr > <td colspan="11" style="font-weight:400;font-size: 20px;color: blue">无告警记录 </td> </tr>
			  </s:else>
			  </tbody>
              </table>
			<s:if test="theForm.resultList.size>0">
			<s:iterator value="theForm.resultList" id="theBean">
			<div id='aa<s:property value="#theBean.EVENT_ID"/>' style="display:none;" class="dlDiv">
				<table class="blue-table" >
			  <thead>
			  <tr>  
			  		<th colspan="2">
					告警编号:<s:property value="#theBean.EVENT_ID"/>的告警详细信息
					</th>
			  </tr>
			  </thead>
			  <tbody >
			  	<tr > 
			  		<td>告警标题:</td>
			  		<td><s:property value="#theBean.TITILE"/>
			  		</td>
			  	</tr>
			  	<tr > 
			  		<td >告警类型:</td>
			  		<td >
			  			<s:if test="#theBean.EVENT_TYPE==0">应用告警</s:if>
			  			<s:elseif test="#theBean.EVENT_TYPE==1">虚拟机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==2">物理主机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==3">机房告警</s:elseif>
			  		</td>
			  	</tr>
			  	<tr > 
			  		<td >告警级别:</td>
			  		<td >
               			<s:if test="#theBean.EVENT_LEVEL==0">严重告警</s:if>
			  			<s:elseif test="#theBean.EVENT_LEVEL==1">主要告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==2">次要告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==3">不确定告警</s:elseif>
			  		</td>
			  	</tr>
			  	<tr > 
			  		<td >告警内容:</td>
			  		<td style="word-wrap:break-word;word-break:break-all ;white-space:normal">
						<s:property value="#theBean.CONTENT"/>
			  		</td>
			  	</tr>
			  	<tr > 
			  		<td >当前状态:</td>
			  		<td >
                		<s:if test="#theBean.EVENT_STAT==0">未处理</s:if>
			  			<s:elseif test="#theBean.EVENT_STAT==1">已处理</s:elseif>
			  		</td>
			  	</tr>
			  	<tr > 
			  		<td >告警位置:</td>
			  		<td ><s:property value="#theBean.EVENT_LOCATION"/>
			  		</td>
			  	</tr>
			  	<tr > 
			  		<td>告警产生时间:</td>
			  		<td ><s:property value="#theBean.ALARM_TIME"/>
			  		</td>
			  	</tr>
              <tr>
                <td colspan="2" align="center">　
                    <input type="button" id="redo" class="lobu" onclick="closeDiv('aa<s:property value="#theBean.EVENT_ID"/>');" value="返 回" />
                </td>
              </tr>
			  </tbody>
			</table> 
			</div>
          	</s:iterator>
          	</s:if>
<div id="overDiv" style="display:none;"></div>

              </div>
              </div>
</s:form>
</body>
