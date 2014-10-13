<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html:html locale="true">

<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/ui.datepicker.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/jqueryui/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	var api = frameElement.api;
    var w = api.opener;
	var icon_path="";
	$(function(){
		$("img").bind({
		mouseover:function(){
			$(this).animate({ 
    			width: "95px",
    			height: "90px", 
    			fontSize: "10em", 
    			borderWidth: 10
  		}, 500);
	},
		mouseout:function(){
			$(this).animate({ 
    			width: "80px",
    			height: "75px", 
    			fontSize: "10em", 
    			borderWidth: 10
  		}, 500);
	},
		click:function(){
			icon_path = $(this).attr("src");
			w.get_icon(icon_path);
			api.close();
		}
	})
	})
</script>
</head>
<body >
	
	<div id="picture" class="scrollbody" align="center">
		<ul>
			<li><img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_01.png" />
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_02.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_03.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_04.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_05.png"/>
			</li>
			<li>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_06.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_07.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_08.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_09.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_10.png"/>
			</li>
			<li>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_11.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_12.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_13.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_14.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_15.png"/>
			</li>
			<li><img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_host.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_store.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_vm.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/icon_xen.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/alarm.png"/>
			</li>
			<li>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/host.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/monitor.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/network.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/schedule.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/storage.png"/>
			</li>
			<li>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/template.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/virtual.png"/>
				<img src="<%=request.getContextPath()%>/newUI/newUI/images/business.png"/>
			</li>
		</ul>
	</div>
</body>

</html:html>
