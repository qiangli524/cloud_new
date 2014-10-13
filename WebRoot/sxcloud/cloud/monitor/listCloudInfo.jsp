<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<title></title>
<link href="sxcloud/cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="sxcloud/cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
</script>
</head>
<style>
a 
{
text-decoration: none;
}

a:hover 
{
text-decoration: underline;
color: #5500FF;
}
</style>
<body>
<s:form action="monitor_listCloudInfo" method="post" id="theForm" cssClass="theForm">
<div class="scrollbody">

<div class="table-ct"><%--class="table-ct"--%>

	<div>
		<ul class="compose-3">			      
			   <li>
     			<h1>云状态  
     				    <span class="tool_bar">   
     				    </span>
     				 </h1>
      			<div class="main">
         			 <div class="left-c">
         			 <% String str = (String)request.getAttribute("cloud");
         			 	if(str !=null && !"".equals(str)){
         			 	if(str.equals("OK")){ %>
              			 <img src="sxcloud/cjs/ui2/nresources/common/images/cloud_normal.jpg" width="75" height="80" />
              			 <% }else { %>
              			 <img src="sxcloud/cjs/ui2/nresources/common/images/cloud_notnormal.jpg" width="75" height="80" />
              			 <% } %>
              		  <% } %>
          		</div>
          		
          		<div class="right-c">
          			<p align="left">        云状态:<s:property value="theForm.state"/>
							  	  </p>
          			<p align="left">        主机名:<s:property value="theForm.hostname"/>
							  	  </p>
            		<p align="left">        端口：<s:property value="theForm.port"/>
            				  </p>
            		<p align="left">        类型： <s:property value="theForm.cloudType"/> </p>
            		<p align="left">        版本：<s:property value="theForm.version"/> </p>
            		<p align="left">        管理员标识：<s:property value="theForm.username"/> </p>
           		 </div>
           		 </div>
           		 </li>
    	</ul>

  </div>
  <div>
		<ul class="compose-3">	
    			<li>
     				 <h1>工作负载摘要  
     				    <span class="tool_bar">   
     				    </span>
     				 </h1>
      			<div class="main">
         			 <div class="left-c">
              		<img src="sxcloud/cjs/ui2/nresources/common/images/workload.jpg" width="75" height="80" />
          		</div>
          		<div class="right-c">

      					<p align="left">	确定：<font color="blue"><s:property value="theForm.count1"/> </font> 
			      		</p> 	
			      		<p align="left">     错误： <font color="blue">	<s:property value="theForm.count2"/></font> 
            			</p>
            			<p align="left">      转换中：<font color="blue">	<s:property value="theForm.count3"/></font> 
            			</p>
            			<p align="left">      已停止：<font color="blue"><s:property value="theForm.count4"/></font> 
            			</p>
            			<p align="left">      未知：<font color="blue"><s:property value="theForm.count5"/></font> 
            			</p>		      		

           		 </div>
           		 </div>
    			</li>
    	</ul>
  </div>
   <div>
    <ul class="compose-3">			      
			   <li>
     				 <h1>资源使用情况 
     				    <span class="tool_bar">   
     				    </span>
     				 </h1>
      			<div class="main">
         			 <div class="left-c">
              		 <img src="sxcloud/cjs/ui2/nresources/common/images/resource.jpg" width="75" height="80" />
          		</div>
          		<div class="right-c">
          		<s:iterator value="theForm.resourceList" id="theBean">
			      		<p align="left">       <s:property value="#theBean.label"/>:<s:property value="#theBean.value"/>
						</p>
            			<p align="left">      
            			</p>
            			<p align="left">       
            			</p>
            		</s:iterator>
           		 </div>
           		 </div>
    			</li>
    	</ul>
    	<ul class="compose-3">			      
			   <li>
     				 <h1>最新事件 
     				    <span class="tool_bar">   
     				    </span>
     				 </h1>
      			<div class="main">
         			 <div class="left-c">
              		 <img src="sxcloud/cjs/ui2/nresources/common/images/event.jpg" width="75" height="80" />
          		</div>
          		<div class="right-c" >
			      	<s:iterator value="theForm.eventManageList" id="theBean">
			      	
			      		<table style="table-layout:fixed;" width="90%" >
			      		<tr>
			      			<td style= "white-space:nowrap;overflow:hidden;text-overflow:ellipsis;" width="100%" title="<s:property value="#theBean.EVENT_INFO"/>">
			      			<div align="left"><s:property value="#theBean.EVENT_INFO"/> </div></td>
			      		</tr>
			      		</table>
			      	</s:iterator>
					<p align="left">       <a href="event_listEventManage.do"><font color="blue"> 转至事件 </font></a></p>					
           		 </div>
           		 </div>
    			</li>
    	</ul>
    </div>
 </div>
<div class="clear"></div>
</div>
</s:form>
</body>
