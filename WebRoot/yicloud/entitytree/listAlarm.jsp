<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<head>
<title></title>
<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="inc/css.css" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" >
	
	function openNewWindows(eventId,eventType){
	
		window.open("tree_listSolution.do?eventId="+eventId+"&eventType="+eventType,"listSolution",'width=800, height=600, resizable=yes');
 	}
 	function cutString(text, canvasWidth) {
	 	var suffix = "...";
	    var tempText = text.substring(0, canvasWidth);
	   	if(canvasWidth >= text.length) return text;
	    return tempText+suffix
	}
 	
</script>
<!-- 
  <script type="text/javascript">
        var x1,y1;
    $(document).ready(function(){
     $("#Uc_headDiv").mousedown(
     function(event){
      var offset=$("#UC_selectDiv").offset();
      x1=event.clientX-offset.left;
      y1=event.clientY-offset.top;
      var witchButton=false;
      if(document.all&&event.button==1){witchButton=true;}
      else{if(event.button==0)witchButton=true;}
      if(witchButton)//按左键,FF是0，IE是1
      {
       $(document).mousemove(function(event){
        $("#UC_selectDiv").css("left",(event.clientX-x1)+"px"); 
        $("#UC_selectDiv").css("top",(event.clientY-y1)+"px"); 
       })
      }
     })
     $("#hiddenIframe").css("width",$("#UC_selectDiv").width()+"px");
     $("#hiddenIframe").css("height",$("#UC_selectDiv").height()+"px");
     $("#Uc_headDiv").mouseup(
     function(event){
      $(document).unbind("mousemove");
     })
     $("#Button1").click(
     function(event){
        $("#uc_body2").css("display","");
        $("#uc_body1").css("display","none");
     })
     $("#Button4").click(
     function(event){
        $("#uc_body1").css("display","");
        $("#uc_body2").css("display","none");
     })
     $("#Button2").click(
     function(event){
      $(".moveDiv").css("display","none");
     })
     $("#Button5").click(
     function(event){
      $(".moveDiv").css("display","none");
     })
     $("input.huojl").click(
     function(event){
        $("#UC_selectDiv").css("left",(screen.availWidth-$("#UC_selectDiv").width())/2+"px");
        $("#UC_selectDiv").css("top",(screen.availHeight-$("#UC_selectDiv").height())/3+"px");
      $(".moveDiv").css("display","block");
        if($.browser.mozilla||$.browser.msie)
      {
         $("#hiddenIframe").css("width",document.documentElement.clientWidth +"px");
            $("#hiddenIframe").css("height",document.documentElement.clientHeight +"px");
      }
      else
      {
         $("#hiddenIframe").css("width",document.body.clientWidth +"px");
            $("#hiddenIframe").css("height",document.body.clientHeight +"px");
      }
        $("#disable_oDiv").css("top","0px");
        $("#disable_oDiv").css("left","0px");
     })
    }
   );
    </script>
   -->
</head>
<body>
<s:form action="alarm_listMonitorAlarm.do" method="post" id="theForm" cssClass="theForm">
<s:hidden name="theForm.ENTITY_ID" id="ENTITY_ID"></s:hidden>
 
 <div class="scrollbody">
 <div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0" id="theTable">
			  <thead>
			  <tr>
					<th onclick="sort(theTable,0,'string')">告警类型</th> 
					<th onclick="sort(theTable,1,'string')">告警内容</th>
					<th onclick="sort(theTable,2,'string')">告警级别</th>
					<th onclick="sort(theTable,3,'string')">当前状态</th>
					<th onclick="sort(theTable,4,'string')">告警位置</th>
					<th onclick="sort(theTable,5,'date')">告警产生时间</th>
					<th>告警处理</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.alarmList" id="theBean">
			  	<tr>
			  		
			  		<td>
			  		
			  			<s:if test="#theBean.EVENT_TYPE==0">应用告警</s:if>
			  			<s:elseif test="#theBean.EVENT_TYPE==1">虚拟机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==2">物理主机告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_TYPE==3">机房告警</s:elseif>
			  		</td>
			  		<td class="tabletd" title = "<s:property value="#theBean.CONTENT" 
						/>" nowrap>
      					<div id="Alcontent${theBean.EVENT_ID}"></div>
      				 <script  language="javaScript">
      				 	$("#Alcontent${theBean.EVENT_ID}").html(cutString('<s:property value="#theBean.CONTENT" />', 10));
      				 </script>
    						 </td>
			  		<td>
			  			<s:if test="#theBean.EVENT_LEVEL==0">严重告警</s:if>
			  			<s:elseif test="#theBean.EVENT_LEVEL==1">主要告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==2">次要告警</s:elseif>
			  			<s:elseif test="#theBean.EVENT_LEVEL==3">不确定告警</s:elseif>
			  		</td>
			  		<td>
			  			<s:if test="#theBean.EVENT_STAT==0">未处理</s:if>
			  			<s:elseif test="#theBean.EVENT_STAT==1">已处理</s:elseif>
			  		</td>
			  		<td><s:property value="#theBean.EVENT_LOCATION"/></td>
			  		<td><s:property value="#theBean.ALARM_TIME"/></td>
			  		<td colspan="4" class="btnCenter">
					<input type="button" class="thickbox btn-style02" value="处理"
							 onclick="javascript:openNewWindows('${theBean.EVENT_ID}','${theBean.EVENT_TYPE}');"/></td>
			  	</tr>
			  </s:iterator>
			  </tbody>
			</table>
		</div>
		</div>
	<!-- 
		<div id="UC_selectDiv" class="moveDiv" style="display:none; width:500px;position:absolute; background-color:white;z-index:9999; height: 400px">
        <div id="Uc_headDiv" style="height:25px; font-size:10px; width:100%; cursor:move;background-color:gray;"></div>
        <div id="uc_body1">
        <ul>
        <!--  
            <li>方案一<input type="radio" checked="checked"/>方案详细描述</li>
            <li>方案二<input type="radio" checked="none"/> 方案详细描述</li>
            <li>方案三<input type="radio" checked="checked"/> 方案详细描述</li>
            <li>方案四<input type="radio" checked="checked"/> 方案详细描述</li>
        -->
        <!-- 
            <s:iterator value="theForm.temList" id="tem">
            <tr>
            	<td><input type="radio" checked="checked"/></td>
            	<td>方案名称：<s:property value="#tem.TEM_NAME"/><br></td>
            	<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方案描述：<s:property value="#tem.TEM_DESC"/><br></td>
            </tr>
            </s:iterator>
            <li><input type="button" value="上一步"><input id="Button1" type="button" value="下一步"><input id="Button2" type="button" value="关 闭" /></li>
        </ul>
        </div>
        <div id="uc_body2" style="display:none;">
        <ul>
        	<s:iterator value="theForm.temList" id="list">
        	<td>CPU:</td><td><s:textfield name="#list.CPU" size="10"/><br/></td>
        	<td>内存:</td><td><s:textfield name="#list.MEM" size="10"/><br/></td>
        	<td>存储:</td><td><s:textfield name="#list.STORAGE" size="10"/><br/></td>
        	</s:iterator>
            <li><input id="Button4" type="button" value="上一步"><input id="Button5" type="button" value="关 闭" /></li>
        </ul>
        </div>
    </div>
    
    <div id="disable_oDiv" class="moveDiv" style="display:none;position:absolute;z-index:9998;background-color:White; z-index:9998;FILTER:alpha(opacity=40);-moz-opacity:.4;opacity:0.4;";>
   <iframe src="about:blank" id="hiddenIframe" width="100%" frameborder="0" height="100%"></iframe>
</div>
 -->	
</s:form>
</body>
