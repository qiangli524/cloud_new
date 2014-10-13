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
					alert("����ѡ����־·��!");
					return false;
				}
				var line = $("#line").val();
				url += "&ajax_path="+ajax_path;
				if(url.indexOf("auto") != -1){
					var reg=new RegExp("&auto=","g"); //��������RegExp����   
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
					var reg=new RegExp("&auto=1","g"); //��������RegExp����   
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
				if(ajax_path.indexOf("/") == -1){//���Ǿ���·��
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
						if(menu.length > 0){//�鿴��ΪĿ¼�������ļ�
							html = '';
							for(i=1;i<menu.length;i++){
								html  += '<tr><td class="logs"><a href="javascript:;" name="logpath_href" visited="unvisited">' + menu[i] + '</a></td></tr>';
							}
							$("#logpath").empty().append(menu[0]);//��ʾĿ¼
							$("#logmenu").empty().html(html);//��ʾ�ļ��б�
							$("#back").show();//��ʾ����Ŀ¼��ť
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
		$("#back").hide();//Ĭ�����ط���Ŀ¼��ť
	})
	function goback(){//������һ��Ŀ¼ 
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
				if(dir == "1"){//�Ѿ����ص����õĸ�Ŀ¼
					for(i=0;i<menu.length;i++){
						html  += '<tr><td class="logs"><a href="javascript:;" name="logpath_href" visited="unvisited">' + menu[i] + '</a></td></tr>';
					}
					$("#logpath").empty().append('<font color="#CD0000">��־�б�</font>');
					$("#logmenu").empty().html(html);//��ʾ�ļ��б�
					$("#back").hide();//����ʾ����Ŀ¼��ť
				}else if(dir == "0"){//���ص�Ϊ��һ��Ŀ¼
					for(i=1;i<menu.length;i++){
						html  += '<tr><td class="logs"><a href="javascript:;" name="logpath_href" visited="unvisited">' + menu[i] + '</a></td></tr>';
					}
					$("#logpath").empty().append(menu[0]);//��ʾĿ¼
					$("#logmenu").empty().html(html);//��ʾ�ļ��б�
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
                  		<a href="javascript:;" onclick="refresh();"><font style="font-color:#ff0000;font-size:14px;">ˢ��</font> </a>
                  		��ȡ���
                  		<%
                  			if(tailline !=null && !"".equals("tailline")){
                  		%>
                  			<input type="text" id="line" size="6" value=<%=tailline%> />��
                  		<%
                  			}else{
                  		%>
                  			<input type="text" id="line" size="6" />��
                  		<% 	} %>
                  			&nbsp;&nbsp;&nbsp;<input type="button" id="confirm" value="ȷ��"/>
						<%
							if(auto !=null && !"".equals(auto)){
						%>   
							<input type="checkbox" id="auto" checked="checked"/>�Զ�ˢ��
						<%
							}else{
						%>               	
                  		<input type="checkbox" id="auto"/>�Զ�ˢ��
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
                  		<a href="javascript:;" onclick="searchword();">����</a>
                  		<font> (˵����֧��������ʽ,�õ�����,��ʽ���磺"'[a-z]\{5\}' aa")</font>
                  	</td>
                  </tr>
                 </table>
       <table class="width:100%;height:100%;"> 
        <tr>
        	<td>
		
		<textarea id="textareaContent" style="width:600px;height:700px;">
	<%-- 	���ٲ������ص�����Ȼ���ȡ�ķ�����Ĭ�ϻ�ȡ��500��
		
	<%
		//response.setHeader("refresh","300");//�Զ�ˢ��
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
				// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
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
				// ���ļ�����ʼλ��
				long beginIndex = (fileLength - 1000) < 0 ? 0
						: (fileLength - 1000);
				// �����ļ��Ŀ�ʼλ���Ƶ�beginIndexλ�á�
				randomFile.seek(beginIndex);
				String tempString = null;
				int line = 1;
				// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
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
												<a href="javascript:;" onclick="goback();"><font color="#CD0000">����Ŀ¼</font></a>
											</td>
										<tr>
										<tr>
											<td id="logpath">
												<font color="#CD0000">��־�б�</font>
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
