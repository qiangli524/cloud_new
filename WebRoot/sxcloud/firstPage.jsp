<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/view.jsp"%>
<html:html locale="true">
<head>
<%=getCssTag(request, "login.css")%>
<%--<title>思特奇云管理平台</title>--%>
<title>重庆移动云资源池管理</title>
</head>
<body>
<html:form action="listCloudInfo" method="post" styleId="theForm">
 <bean:define id="theForm" name="monitorForm"/>
<div class="index-panel">
	<div class="logo">
    	<img src="cresources/default/images/logo.png" width="382" height="33" alt="logo" />
    </div>
<div class="main clearfix">
    	<div class="tit-yun">
            <span class="icon"><img src="cresources/default/images/icon-normal.gif" width="14" height="14" /></span>
            云处于正常状态，可准备开始接受部署
        </div>
        <!---云状态 start--->
<dl class="index-single">
            <dt>主机信息</dt>
            <div class="content clearfix">
            	<div class="icon-01">
                </div>
                <div class="text">
           	  <ul>
                    	<li>云状态:<bean:write name="theForm" property="state"/></li>
                        <li>主机名:<bean:write name="theForm" property="hostname"/></li>
                        <li>端&nbsp;&nbsp;口：<bean:write name="theForm" property="port"/></li>
                        <li>类&nbsp;&nbsp;型：<bean:write name="theForm" property="cloudType"/></li>
                        <li>版&nbsp;&nbsp;本：<bean:write name="theForm" property="version"/></li>
                        <li>管理员标识：<bean:write name="theForm" property="username"/></li>
                  </ul>
              </div>
    </div>
        </dl>
        <!---云状态 end--->
        <!---工作负载 start--->
<dl class="index-single">
            <dt>工作负载摘要</dt>
            <div class="content clearfix">
            	<div class="icon-02"></div>
                <div class="text">
                	<ul>
                    	<li>确&nbsp;&nbsp;定：<font color="blue">	<bean:write name="theForm" property="count1"/></font> </li>
                        <li>错&nbsp;&nbsp;误： <font color="blue">	<bean:write name="theForm" property="count2"/></font></li>
                        <li>转换中：<font color="blue">	<bean:write name="theForm" property="count3"/></li>
                        <li>已停止：<font color="blue">	<bean:write name="theForm" property="count4"/></li>
                        <li>未&nbsp;&nbsp;知：<font color="blue">	<bean:write name="theForm" property="count5"/></li>
                    </ul>
                 </div>
            </div>
        </dl>
        <!---工作负载 end--->
        <!---资源使用情况 start--->
		<dl class="index-single">
            <dt>资源使用情况</dt>
            <div class="content clearfix">
            	<div class="icon-03"></div>
                <div class="text">
                	<ul>
                	<logic:present name="theForm" property="resourceList">
                		<logic:iterate id="theBean" name="theForm" property="resourceList" >
                    	<li><bean:write name="theBean" property="label"/>:<bean:write name="theBean" property="value"/></li>
                       </logic:iterate>
					</logic:present>
                    </ul>
                 </div>
            </div>
        </dl>
        <!---资源使用情况 end--->
        <!---最新事件 start--->
		<dl class="index-single">
            <dt>最新事件</dt>
            <div class="content clearfix">
            	<div class="icon-04"></div>
                <div class="text">
                	<ul>
	                	<logic:present name="theForm" property="eventManageList" >
				      		<logic:iterate id="theBean" name="theForm" property="eventManageList" >
	                    		<li><bean:write name="theBean" property="EVENT_INFO"/></li>
	                    	</logic:iterate>
	                    	<li><a href="/listEventManage.do"><font color="blue"> 转至事件 </font></a></li>
						</logic:present>
                    </ul>
                 </div>
            </div>
        </dl>
      <div class="clr"></div>
        <div class="gray-panel clearfix">
        	<ul class="icon-list">
                <li><p><img src="cresources/default/images/icon-06.gif" width="60" height="60" /></p><p><a href="#">监控管理</a></p></li>
                <li><p><img src="cresources/default/images/icon-07.gif" width="60" height="60" /></p><p><a href="#">部署管理</a></p></li>
                <li><p><img src="cresources/default/images/icon-08.gif" width="60" height="60" /></p><p><a href="#">自助服务</a></p></li>
                <li><p><img src="cresources/default/images/icon-09.gif" width="60" height="60" /></p><p><a href="#">系统管理</a></p></li>
                <li><p><img src="cresources/default/images/icon-10.gif" width="60" height="60" /></p><p><a href="#">报表管理</a></p></li>
            </ul>
      </div>
    </div>
</div>
</html:form>
</body>
</html:html>