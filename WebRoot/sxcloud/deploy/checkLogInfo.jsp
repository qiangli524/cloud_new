<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>





<%@page import="java.io.*"%>
<%@page import="java.nio.MappedByteBuffer"%>
<%@page import="java.nio.channels.FileChannel"%>
<head>
<title></title>
<script type="text/javascript">
	<%
		String tailline = (String)request.getAttribute("line");
		String keyword = (String)request.getAttribute("keyword");
		String auto = (String)request.getAttribute("auto");
		if(auto !=null && !"".equals(auto)){
	%>
		setTimeout('refresh()',2000);			
	<%
		}
	%>
	var ip = '<%=request.getAttribute("ip")%>';
	var name = '<%=request.getAttribute("name")%>';
	var hostId = '<%=request.getAttribute("hostId")%>';
	var appId = '<%=request.getAttribute("appId")%>';
	function refresh(){
		window.location.reload();
	}
	$(function(){
		$("#confirm").click(function(){
			var url = location.href;
			var ajax_path =  $.trim($('a[visited="visited"]').html());
			var line = $("#line").val();
			url += "&ajax_path="+ajax_path;
			if(url.indexOf("line") != -1){
				var reg = new RegExp("&line="+ '<%=tailline%>',"g");
				var newStr = url.replace(reg,"&line=" + line);
				location.href = newStr;
			}else{
				url += "&line=" + line;
				$.ajax({
					type:"GET",
					async: false,
             		cache: false,
					url:url,
					success: function(data){
						$("#textareaContent").empty().text(data);
					} 					
				});
				//location = url;
			}
		});
		
		$("#auto").click(function(){
			if(this.checked){
				var url = location.href;
				var ajax_path =  $.trim($('a[visited="visited"]').html());
				if(ajax_path == null || ajax_path ==""){
					alert("请先选择日志路径!");
					return false;
				}
				var line = $("#line").val();
				url += "&ajax_path="+ajax_path;
				if(url.indexOf("auto") != -1){
					var reg=new RegExp("&auto=","g"); //创建正则RegExp对象   
					var newstr=url.replace(reg,"&auto=1");  
					location.href = newstr;
				}else{
					url += "&auto=1";
					$.ajax({
						type:"GET",
						async: false,
             			cache: false,
						url:url,
						success: function(data){
							$("#textareaContent").empty().text(data);
						} 					
					});
					//location = url;
				}
			}else{
				var url = location.href;
				var ajax_path =  $.trim($('a[visited="visited"]').html());
				var line = $("#line").val();
				url += "&ajax_path="+ajax_path;
				if(url.indexOf("auto") != -1){
					var reg=new RegExp("&auto=1","g"); //创建正则RegExp对象   
					var newstr=url.replace(reg,"&auto=");  
					location.href = newstr;
				}
			}
		});
		$("a[name='logpath_href']").live("click",function(){
				$(":checkbox").attr("checked",false);
				$(":text").attr("value","");
				var $clickeda = $(this);
				var ajax_path = $clickeda.html();
				if(ajax_path.indexOf("/") == -1){//不是绝对路径
					ajax_path = $("#logpath").html() + "/" + ajax_path;
				}
				$.ajax({
					type:"GET",
					async: false,
             		cache: false,
             		dataType: "json",
					url:"dep_getLogsInfo.do?ip="+ip+"&name="+name+"&hostId="+hostId+"&appId=" + appId+"&ajax_path=" + ajax_path,
					success: function(data){
						$("a[name='logpath_href']").not($clickeda).css('color','#000000');
						$clickeda.css('color', '#f00').attr("visited","visited");
						$("#textareaContent").empty().text(data.content);
						menu = data.logMenu;
						if(menu.length > 0){//查看的为目录，不是文件
							html = '';
							for(i=1;i<menu.length;i++){
								html  += '<tr><td class="logs"><a href="javascript:;" name="logpath_href" visited="unvisited">' + menu[i] + '</a></td></tr>';
							}
							$("#logpath").empty().append(menu[0]);//显示目录
							$("#logmenu").empty().html(html);//显示文件列表
							$("#back").show();//显示返回目录按钮
						}
					} 					
				});
		});
	});
	function searchword(){
		var word = $("#keyword").val();
		var url = location.href;
		var ajax_path =  $.trim($('a[visited="visited"]').html());
		var line = $("#line").val();
		url += "&ajax_path="+ajax_path;
		if(url.indexOf("keyword") != -1){
			var str ;
			if("<%=keyword%>" == "null"){
				str = "&keyword=";
			}else{
				str = "&keyword="+ "<%=keyword%>";
			}
			var reg = new RegExp(str,"g");
			var newStr = url.replace(reg,"&keyword=" + word);
			location.href = newStr;
		}else{
			url += "&keyword=" + word;
			$.ajax({
				type:"GET",
				async: false,
            	cache: false,
				url:url,
				success: function(data){
					$("#textareaContent").empty().text(data);
				} 					
			});
			//location = url;
		}
	}
	$(function(){
		$("#back").hide();//默认隐藏返回目录按钮
	})
	function goback(){//返回上一级目录 
		var path = $("#logpath").html();
		$.ajax({
			type:"GET",
			async:false,
			cache:false,
			dataType: "json",
			url:"dep_goBackPath.do?ip="+ip+"&name="+name+"&hostId="+hostId+"&appId=" + appId+"&path=" + path,
			success: function(data){
				menu = data.logMenu;
				dir = data.dir;
				html = '';
				if(dir == "1"){//已经返回到配置的根目录
					for(i=0;i<menu.length;i++){
						html  += '<tr><td class="logs"><a href="javascript:;" name="logpath_href" visited="unvisited">' + menu[i] + '</a></td></tr>';
					}
					$("#logpath").empty().append('<font color="#CD0000">日志列表</font>');
					$("#logmenu").empty().html(html);//显示文件列表
					$("#back").hide();//不显示返回目录按钮
				}else if(dir == "0"){//返回的为上一级目录
					for(i=1;i<menu.length;i++){
						html  += '<tr><td class="logs"><a href="javascript:;" name="logpath_href" visited="unvisited">' + menu[i] + '</a></td></tr>';
					}
					$("#logpath").empty().append(menu[0]);//显示目录
					$("#logmenu").empty().html(html);//显示文件列表
				}
			}
		})
	}
</script>
</head>
<body>
<div class="scrollbody">
	<div class="box on">
        <div class="query-form">
                <table style="width: 100%;height: 100%" border="0">
                  <tr>
                  	<td class="align:left;">
                  		<a href="javascript:;" onclick="refresh();"><font style="font-color:#ff0000;font-size:14px;">刷新</font> </a>
                  		读取最后
                  		<%
                  			if(tailline !=null && !"".equals("tailline")){
                  		%>
                  			<input type="text" id="line" size="6" value=<%=tailline%> />行
                  		<%
                  			}else{
                  		%>
                  			<input type="text" id="line" size="6" />行
                  		<% 	} %>
                  			&nbsp;&nbsp;&nbsp;<input type="button" id="confirm" value="确定"/>
						<%
							if(auto !=null && !"".equals(auto)){
						%>   
							<input type="checkbox" id="auto" checked="checked"/>自动刷新
						<%
							}else{
						%>               	
                  		<input type="checkbox" id="auto"/>自动刷新
                  		<% } %>
                  		</td>
                  		<td>
                  		<%
                  			if(keyword !=null && !"".equals(keyword)){
                  		%>
                  			<input type="text" class="txt" id="keyword" value="<%=keyword%>" />
                  		<%
                  			}else{
                  		%>
                  			<input type="text" class="txt" id="keyword" />
                  		<%
                  			}
                  		%>
                  		<a href="javascript:;" onclick="searchword();">搜索</a>
                  		<font> (说明：支持正则表达式,用单引号,格式例如："'[a-z]\{5\}' aa")</font>
                  	</td>
                  </tr>
                 </table>
       <table class="width:100%;height:100%;"> 
        <tr>
        	<td>
		
		<textarea id="textareaContent" style="width:600px;height:700px;">
	<%-- 	不再采用下载到本地然后读取的方法，默认获取后500行
		
	<%
		//response.setHeader("refresh","300");//自动刷新
		String temppath = (String) request.getAttribute("temppath");
		String filepath = temppath;
		File file = new File(filepath);
	//	if (finalline == null || "".equals(finalline)) {
			BufferedReader reader = null;
			try {
				//RandomAccessFile raf=new RandomAccessFile(filepath,"r");

				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				int line = 1;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					out.println(tempString);
				//	out.println("<br>");
					line++;
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e1) {
					}
				}
			}
		/* *
		} else {
			RandomAccessFile randomFile = null;
			try {
				randomFile = new RandomAccessFile(filepath, "r");
				long fileLength = randomFile.length();
				// 读文件的起始位置
				long beginIndex = (fileLength - 1000) < 0 ? 0
						: (fileLength - 1000);
				// 将读文件的开始位置移到beginIndex位置。
				randomFile.seek(beginIndex);
				String tempString = null;
				int line = 1;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = randomFile.readLine()) != null) {
					out.println(tempString);
					out.println("<br>");
					line++;
				}
				randomFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (randomFile != null) {
					try {
						randomFile.close();
					} catch (IOException e1) {
					}
				}
			}

		}
	*/
		if (file.exists()) {
			file.delete();
		}
	%>
	
	--%>
			</textarea>
		</td>
					<td valign="top">
							<div>
								<table width="100%" border="0" cellspacing="0"
									class="blue-table sorttable">
									<thead>
										<tr>
											<td id="back">
												<a href="javascript:;" onclick="goback();"><font color="#CD0000">返回目录</font></a>
											</td>
										<tr>
										<tr>
											<td id="logpath">
												<font color="#CD0000">日志列表</font>
											</td>
										</tr>
									</thead>
									<tbody id="logmenu">
										<s:iterator value="#request.resultList" id="theBean">
											<tr>
												<td class="logs">
													<a href="javascript:;" name="logpath_href" visited="unvisited"><s:property value="#theBean" />
													</a>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						<td>
				</tr>
			</table>
		</div>
	</div>
</div>
</body>
