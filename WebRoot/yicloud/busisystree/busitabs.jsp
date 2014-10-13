<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String id = (String)request.getAttribute("id");
	String type= (String)request.getAttribute("type");
	String  entityId = (String)request.getAttribute("entityId");
	String hostIP =(String)request.getAttribute("hostIP");
%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Cache-Control" content="max-age=7200" />  
<meta http-equiv="expires" content="0" />
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/themes/base/jquery.ui.all.css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.core.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.widget.js"></script>
	<script src="<%=request.getContextPath() %>/sxcloud/js/jqueryui/ui/jquery.ui.tabs.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/sxcloud/js/jqueryui/demos.css" />
		<style> 
            html { 
                font-size:10px; 
            } 
 
            .iframetab { 
                width:100%; 
                height:auto; 
                border:none;
                margin:0px; 
                position:relative; 
                top:0px;
                background：none;
            } 
 
            .ui-tabs-panel { 
                padding:5px !important; 
            } 
 
            .openout { 
                float:right; 
                position:relative; 
                top:-28px; 
                left:-5px; 
            } 
        </style> 
	<script>
	 $(document).ready(function() { 
		var $tabs = $('#tabs').tabs({
			ajaxOptions: {
				cache:false,	
				error: function( xhr, status, index, anchor ) {
					$( anchor.hash ).html(
						"数据加载失败,请重试！");
				}
			}
		}); 
 
        //get selected tab 
	// function getSelectedTabIndex() { 
     //   	return $tabs.tabs('option', 'selected'); 
       // } 
 
      	//get tab contents 
       // beginTab = $("#tabs ul li:eq(" + getSelectedTabIndex() + ")").find("a"); 
 
	   // loadTabFrame($(beginTab).attr("href"),$(beginTab).attr("rel")); 
 
        $("a.tabref").click(function() { 
        	loadTabFrame($(this).attr("href"),$(this).attr("rel")); 
        }); 
 
       //tab switching function 
        function loadTabFrame(tab, url) { 
        	if ($(tab).find("iframe").length == 0) { 
            	var html = []; 
            	html.push('<div class="tabIframeWrapper">'); 
            	html.push('<div class="openout"><a href="' + url + '">  </a></div><iframe class="iframetab" frameborder="0" allowtransparency="true" src="' + url + '">Load Failed?</iframe>'); 
            	html.push('</div>'); 
            	$(tab).append(html.join("")); 
            	$(tab).find("iframe").height($(window).height()-80); 
        	} 
            return false; 
        } 
	});
	</script>
</head>
<body>
	<div class="demo">

<div id="tabs" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
	<ul>
		<s:if test="#request.type==0"> <!-- 根节点 -->
			<li><a href="busiareainfo_listBusiArea.do" >摘要</a></li>
<%--		<li><a href="busitree_busiCenterInfo.do?id=<%=id%>" >告警</a></li>--%>
		</s:if>
		<s:elseif test="#request.type==1"><!-- 业务系统 -->
			<li><a href="busitree_bizsysInfo.do?id=<%=id%>" >业务介绍</a></li>
			<li><a class="tabref" href="#tabs-4" rel="busitree_bizsysView.do?id=<%=id%>" >业务视图</a></li>
			<li><a class="tabref" href="#tabs-2" rel="busitree_listSysDeployHost.do?id=<%=id%>" >部署主机</a></li>
			<li><a class="tabref" href="#tabs-3" rel="software_listSoftwareInfo.do?bizid=<%=id%>" >软件包</a></li>
			<li><a class="tabref" href="#tabs-5" rel="fileversion_queryFileVersionList.do?sysid=<%=entityId%>" >版本</a></li>
			<li><a class="tabref" href="#tabs-1" rel="busitree_listDataBase.do" >数据库</a></li>			
<%--			<li><a class="tabref" href="#tabs-2" rel="busitree_listDeployAlarm.do?id=<%=id%>" >部署异常</a></li>			--%>
<%--			<li><a href="rule_list.do" >验证规则</a></li>--%>
		</s:elseif>
		<s:elseif test="#request.type==2"><!-- 基准应用-->
			<li><a href="busitree_bizsysAppInfo.do?id=<%=id%>" >应用介绍</a></li>
			<li><a class="tabref" href="#tabs-7" rel="dep_listExamplesInTree.do?app_id=<%=entityId%>" >实例</a></li>
			<li><a class="tabref" href="#tabs-6" rel="treeorder_listOrder.do?id=<%=id%>&entityId=<%=entityId%>" >订单</a></li>
	<%-- 	<li><a class="tabref" href="#tabs-5" rel="treetask_listTask.do?id=<%=id%>&entityId=<%=entityId%>">任务</a></li> --%>
			<li><a class="tabref" href="#tabs-3" rel="treeprocess_listAllProcess.do?example_id=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="deployscript_list.do?id=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>" >脚本</a></li>
			<li><a class="tabref" href="#tabs-8" rel="busitree_listAppDeployHost.do?id=<%=id%>" >部署主机</a></li>
			<li><a class="tabref" href="#tabs-2" rel="deployconfig_list.do?id=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>">配置文件</a></li>
<%--		<li><a href="busitree_bizsysView.do?id=<%=id%>" >上线报告</a></li>--%>
			<li><a class="tabref" href="#tabs-1" rel="logDeploy_listLogDeploy.do?id=<%=id%>&hostIP=<%=hostIP%>&type=<%=type%>&entityId=<%=entityId%>" >部署日志</a></li>
		</s:elseif>
		<s:elseif test="#request.type==3"><!-- 部署实例 -->
			<li><a href="busitree_deployExampleInfo.do?id=<%=id%>" >实例介绍</a></li>
			<li><a class="tabref" href="#tabs-1" rel="logDeploy_listLogDeploy.do?entityId=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>" >部署日志</a></li>
			<li><a class="tabref" href="#tabs-2" rel="deployconfig_list.do?id=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>" >配置文件</a></li>
			<li><a class="tabref" href="#tabs-3" rel="treeprocess_listAllProcess.do?example_id=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>">进程</a></li>
			<li><a class="tabref" href="#tabs-4" rel="deployscript_list.do?id=<%=entityId%>&hostIP=<%=hostIP%>&type=<%=type%>" >脚本</a></li>
			<li><a class="tabref" href="#tabs-5" rel="operationLog_listOperationLog.do?entityId=<%=entityId%>">操作日志</a></li>
		</s:elseif>
		<s:elseif test="#request.type==4"><!-- 业务系统 -->
			<li><a href="busitree_bizsysInfo.do?id=<%=id%>" >摘要</a></li>
			<li><a href="busitree_bizsysView.do?id=<%=id%>" >应用</a></li>
		</s:elseif>
	</ul>
	<!-- tab switching -->
	<div id="tabs-1">
    </div>
    <div id="tabs-2"> 
    </div> 
    <div id="tabs-3"> 
    </div> 
    <div id="tabs-4"> 
    </div>
    <div id="tabs-5"> 
    </div> 
    <div id="tabs-6"> 
    </div>
    <div id="tabs-7"> 
    </div> 
    <div id="tabs-8"> 
    </div>
</div>

</div><!-- End demo -->
</body>
