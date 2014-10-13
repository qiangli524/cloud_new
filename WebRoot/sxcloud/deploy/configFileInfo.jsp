<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<head>
<title></title>
<script type="text/javascript">
	var ip='<%=request.getAttribute("ip")%>';
	var name='<%=request.getAttribute("name")%>';
	var pwd='<%=request.getAttribute("pwd")%>';

	$(function(){

		$("#confirm").click(function(){
			   var path = $("#path").attr("value");
			   var url = "dep_saveConfigFile.do?ip="+ip+"&name="+name+"&pwd="+pwd+"&path="+path;;
			   var content = $("#textareaContent").attr("value");
			   mask('保存中...','0.7','0');
			   $.post(url, { Action: "post", Content:content },function(data){
			   		if(data.result == 1){
			   			removeMask();
			   			$("#msg").text("保存"+ip+"  "+path+"成功!");
			   		}else if(data.result == 2){
			   			removeMask();
			   			$("#msg").text("保存"+ip+"  "+path+"成功!");
			   			var tempPath="<tr><td><a href='javascript:;' name='his'>"+path+"</a>&nbsp;<a href='javascript:;' name='delhis' style='color: red'>删除</a></td></tr>";
			   		    $("#lishi tbody").append(tempPath);
			   		}else{
			   			removeMask();
			   			alert("保存失败!");
			   	    }
				   },"json");
		});

		$("#open").click(function(){
			   var path = $("#path").attr("value");
			   var url = "dep_operConfigFile.do?ip="+ip+"&name="+name+"&pwd="+pwd+"&path="+path;
			   alert(url);
			   mask('读取中...','0.7','0');
				$.getJSON(url,{'time':new Date().toString()},function(data){
					var content = data.content;
					$("#textareaContent").text(content);
					removeMask();
					$("#msg").text("读取"+ip+"  "+path+"成功!");
				});	
		});


		$("a[name='his']").unbind().live("click",function(){
			$("#path").empty().attr("value",$(this).text());
			var path = $(this).text();
			var url = "dep_operConfigFile.do?ip="+ip+"&name="+name+"&pwd="+pwd+"&path="+path;
		    mask('读取中...','0.7','0');
			$.getJSON(url,{'time':new Date().toString()},function(data){
				var content = data.content;
				$("#textareaContent").text(content);
				removeMask();
				$("#msg").text("读取"+ip+"  "+path+"成功!");
			});	
		});

		$("a[name='delhis']").unbind().live("click",function(){
		   var $temp=$(this);
		   var path=$temp.parent().children(":first").text();
		   var url = "dep_deleteConfigFileHis.do?ip="+ip+"&path="+path;
			$.getJSON(url,{'time':new Date().toString()},function(data){
		   		if(data.result == 1){
			   		$temp.parent().parent().empty().remove();
		   			$("#msg").text("删除"+ip+"  "+path+"成功!");
		   		}else{
		   			alert("删除失败!");
		   		}
			});	
		});

		$("#back").click(function(){
           alert("暂未实现");
			});
	});

</script>
</head>
<body>
<s:form action="" method="post" id="theForm">
<div class="scrollbody">
	<div class="box on">
        <div class="query-form" style="width: 100%;height: 100%">
                <table style="width: 100%;height: 100%" border="0">
                  <tr>
                  	<td colspan="2" align="left">
                  	    &nbsp;&nbsp;&nbsp;&nbsp;<label style="font-size:medium;margin-bottom:15px">路径：<label/><input type="text" id="path" style="width: 65%;height: 20px" value='/'/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="open" value="读取" class="btn-style02"/>&nbsp;&nbsp;&nbsp;<input type="button" id="confirm" value="确定" class="btn-style02"/> &nbsp;&nbsp;&nbsp;<input type="button" id="back" value="恢复" class="btn-style02"/>
                  	</td>
                  </tr>
                  <tr>
                    <td align="left" >	
                      <div style="width:686px" class="blue-wrap noborder" id="content">
                            <textarea rows="30" cols="100" id="textareaContent"></textarea>
	                  </div>
	                 </td>
	                 <td width="100%;" valign="top">
	                   <div style="width: 100%;height: 100%" >
								<table width="100%" border="0" id="lishi" cellspacing="0" class="blue-table sorttable" >
									<thead>
										<tr>
										<td>配置文件列表</td>
										</tr>
									</thead>
									<tbody align="left">
									<s:iterator value="theForm.resultList" id="theBean" >
									  <tr>
										<td>
											<a href="javascript:;" name="his"><s:property value="#theBean.PATH"/></a>&nbsp;<a href="javascript:;" name="delhis" style="color: red">删除</a>
										</td>
									  </tr>
								    </s:iterator>
									</tbody>
								</table>
							</div>
	                  </td>
                  </tr>
                  <tr><td id="msg" align="left" style="color: blue;"></td></tr>
                </table>
        </div>
	</div>
</div>
</s:form>
</body>
