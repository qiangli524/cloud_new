<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!--<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"> -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<!--<html xmlns="http://www.w3.org/1999/xhtml"> -->
<!-- <head>  -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=path%>/home/ui/nresources/css/framework.css" rel="stylesheet" />
<link href="<%=path%>/home/ui/nresources/css/home.css" rel="stylesheet" />
<style>
.onli{
	background-color:#347ca6;
}
.nav li a.nav1100000000{background-position:10px -33px;}
.nav li a.nav1200000000{background-position:10px -85px;}
.nav li a.nav1500000000{background-position:10px -185px;}
.nav li a.nav1700000000{background-position:10px -133px;}
.nav li a.nav1800000000{background-position:10px -133px;}
.nav li a.nav1900000000{background-position:10px -133px;}
.nav li a.nav3100000000{background-position:10px -133px;}
.nav li a.nav2100000000{background-position:10px -233px;}
.nav li a.nav2400000000{background-position:10px -235px;}
.nav li a.nav2300000000{background-position:10px -85px;}
.nav li a.nav1400000000{background-position:10px -33px;}
.nav li a.nav2600000000{background-position:10px -133px;}
.nav li a.nav2000000000{background-position:10px -133px;}
.nav li a.nav2900000000{background-position:10px -133px;}

</style>
<!--</head>-->
<script type="text/javascript" src="<%=path%>/home/ui/njs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/home/ui/njs/ui/ui.js"></script>
<script type="text/javascript">
	function setFrameHeight(obj) { 
		var frame = obj;
		frame.onload = frame.onreadystatechange = function(){ 
			if(!frame.readyState || frame.readyState == "complete"){
				frame.height = 480;
				try{
					frame.height = frame.contentWindow.document.body.scrollHeight;
				}catch(e){
					frame.height = 750;
				}
			} 
		} 
	}
	$(function(){setFrameHeight($('#mainIframe')[0])})
	function navChange(url,obj,id){
		$('.onli').removeClass('onli');
		$(obj).parents('.firstMeun').addClass('onli');
		
				
		//////////////////
		if(url.substr(0,4) == "http"){//boss云化功能需要
			if(obj.innerHTML.substr(0,4) != 'BOSS'){
				window.open(url,'popUpWin');
    			return;
			}
		}
		///////
		
		
		if(url!='#'){
			if(id!=-1){
				currPosition(id);				
			}else{
				$('.position').html('当前位置：首页');
			}
			var _mainIframe = $('#mainIframe');
			var reUrl = '';
			if (url.indexOf("http://") != -1) {
				reUrl = url;
			} else {
			    reUrl = '<%=path%>/'+url;
			}
			_mainIframe.attr('src',reUrl);
			//当存在树形结构时不设置IFRAME高度
			if('/cloud/united_listUnitedTree.do' != reUrl && '/cloud/busimanager_listBusiSysTree.do' != reUrl){
				setFrameHeight(_mainIframe[0]);
			}else{
				_mainIframe[0].height = 0;
			}
		}		
	}
	//当前时间
	setInterval("webjx.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
	//退出
	function login_out(){
    	  var localurl = 'http://'+'<%=request.getServerName()%>'+':' + '<%=request.getServerPort()%>' + '<%=request.getContextPath()%>';
    	  $.ajax({
			type: "POST",
			cache: "false",
			async: true,
			dataType: "text",
			url: "<%=request.getContextPath()%>/login_loginout.do",
			success: function(data) {
				//实现单点登出
				 $.ajax({
						type: "GET",
						cache: "false",
						async: true,
						dataType: "text",
						url: '<s:property value="casurl" />' + '/logout?service='+localurl,
						success: function(data) {
							
						}
				 });
				window.location=localurl;
			}
		  });
    	}
      //当前位置
      function currPosition(funcid){
      	$.ajax({
			type: "POST",
			cache: "false",
			async: true,
			dataType: "text",
			url: "<%=request.getContextPath()%>/login_getTitle.do?FUNCID="+funcid,
			success: function(data) {
				$('.position').html(data);
			}
		  });
      }
</script>
 <!-- <body> -->
	<div class="header-wrap">
		<div class="hwrap-inner">
			<div class="top-panel">
		    	<span class="login-info">欢迎您，${name}&nbsp;|&nbsp;&nbsp;<a href="javascript:void(0)" onclick="login_out()">退出</a></span>
		        <span class="login-date">当前时间：<span id="webjx"></span></span>
		    </div>
			<div class="header clearfix">
		    	<a class="logo" href="javascript:void(0)"></a>
		        <div class="nav">
		            <ul class="root_ul">
		            	<li class="firstMeun onli"><a class="home" onclick="navChange('home_home.do',this,'-1')" href="javascript:void(0)">首页</a></li>
		 				<s:iterator value="list" id="firstObj">
		                <li class="firstMeun"><a class="nav${firstObj.id}"  onclick="navChange('${firstObj.url}',this,'${firstObj.id}')"  href="javascript:void(0)">${firstObj.name}</a>
		                    <div class="subNav">
		                        <ul class="m_ul">
		                        	<s:iterator value="#firstObj.list" id="secondObj">
		                            <li>
		                                <s:if test="#secondObj.list.size()!=0">
		                                	<a  href="javascript:void(0)"><span>${secondObj.name}</span></a>
		                                	<div class="thrNav">
		                                   		 <ul class="m_ul">
		                                    		<s:iterator value="#secondObj.list" id="thirdObj">
		                                        	<li><a onclick="navChange('${thirdObj.url}',this,'${thirdObj.id}')" href="javascript:void(0)"><span>${thirdObj.name}</span></a></li>
		                                        	</s:iterator>
		                                    	</ul>
		                                	</div>
		                                </s:if>
		                                <s:else>
		                                	<a  href="javascript:void(0)" onclick="navChange('${secondObj.url}',this,'${secondObj.id}')"><span>${secondObj.name}</span></a>
		                                </s:else>
		                            </li>
		                           </s:iterator>
		                        </ul>
		                    </div>
		                </li>
		                </s:iterator>
		            </ul>
		    	</div>
	    	</div>
	    </div>
    </div>
    <div class="position">当前位置：首页</div>
<!-- </body>
//</html>
-->
