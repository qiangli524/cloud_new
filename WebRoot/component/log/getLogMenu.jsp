<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<head>
<title></title>
<script type="text/javascript">
	var path = '<s:property value="#request.path"/>';
	var id = '<s:property value="#request.id"/>';
	$(function(){
		$("a[name='logs']").live("click",function(){
			$clicked_a = $(this);
			ajax_path = path+'/'+$clicked_a.html();
			if($clicked_a.attr("flag") === "back"){
				//单击返回按钮时$clicked_a.html()获得值为“返回上层”
				ajax_path = path.substring(0,path.lastIndexOf("/"));
			}
			$.ajax({
				type:"GET",
             	cache: false,
             	dataType: "json",
				url:"hostLog_getMenuLog.do?id="+id+"&path="+ajax_path,
				success: function(data){
					//$("a[name='logpath_href']").not($clickeda).css('color','#000000');
					menu = data.logMenu;//文件菜单
					content = data.content;//如果为文件，文件内容
					path = data.path;//当前目录
					if(menu.length > 0){//查看的为目录，不是文件
						html = '';
						for(i=0;i<menu.length;i++){
							temp = menu[i];
							firstStr = temp.substring(0,1);
							html += '<tr><td>';
							if(firstStr==="d"){//目录
								html += '<img src="component/log/ico/folder.png" />';
							}else if(firstStr==="-"){//文件 
								html += '<img src="component/log/ico/page_white.png" />';
							}
							html  += '<a href="javascript:;" name="logs">' + temp.substring(1,temp.length) + '</a></td></tr>';
						}
						if(path === '<s:property value="#request.path"/>'){//返回至配置的根路径，不显示返回按钮
							$("#logpath").empty().append('<font color="red">'+path+'</font>');//显示当前目录
						}else{
							$("#logpath").empty().append('<font color="red">'+path+'</font>'+'&nbsp;&nbsp;&nbsp;<a href="javascript:;" name="logs" flag="back">返回上层</a>');//显示当前目录
						}
						$("#logmenu").empty().html(html);//显示菜单
					}else if(menu.length==0 && content != ""){ //查看的是文件
						$clicked_a.css('color', '#f00');
						var api = frameElement.api, W = api.opener; 
						W.$.dialog({
							title:'查看日志',
							left: '40%',
							top: '35%',
							width: '800px',
							height: '450px',
							content: 'url:hostLog_getLogInfo.do?id='+data.logId+"&ajax_path="+ajax_path,
						});
					}
				},
				error:function(){
					alert("查看日志错误!");
				} 					
			});
		});
	})
</script>
</head>
<body class="pop-body scrollbody">

<s:form action="hostLog_saveLogInfo.do" id="theForm" method="post">
		<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
			<tr id="logpath">
				<td><font color="red"><s:property value="#request.path"/></font>
				</td>
			</tr>
			<tbody id="logmenu">
				<s:iterator value="#request.resultList" id="menu">
				<tr>
					<td>
						<s:if test='#menu.substring(0,1)=="-"'>
							<img src="component/log/ico/page_white.png" />
						</s:if>
						<s:elseif test='#menu.substring(0,1)=="d"'>
							<img src="component/log/ico/folder.png" />
						</s:elseif>
						<a href="javascript:;" name="logs"><s:property value="#menu.substring(1,#menu.length())"/></a>
					</td>
					
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</s:form>
</body>
