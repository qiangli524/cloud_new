<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<style type="text/css">
		div.hidden{
		width:250px;
		height:20px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
  </style>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/common.js"></script>

<script type="text/javascript">
	var ip='<%=request.getAttribute("ip")%>';
	var name='<%=request.getAttribute("name")%>';
	var pwd='<%=request.getAttribute("pwd")%>';
	var port='<%=request.getAttribute("port")%>';

	$(function(){
		$("[name='view']").unbind().live("click",function(){
			var path = $(this).attr("scriptpath");
			$("#path").val(path);
			var url="dep_operConfigFile.do";
			mask('读取中,请稍后...','0.7','0');
			$.ajax({
				type:'post',
				url:url,
				dataType:'json',
				data:{"ip":ip,"name":name,"pwd":pwd,"path":path,"port":port},
				success:function(msg){
					var content = msg.content;
					$("#textareaContent").val(content);
					removeMask();
				}
			});
			
			/*
			$.getJSON(url,{'time':new Date().toString()},function(data){
				var content = data.content;
				$("#textareaContent").val(content);
				removeMask();
			});*/
		});
		
		$("#read").click(function(){
			var path = $("#path").val();
			if (path.length == 0) {
				alert("路径不能为空，请输入正确的脚本路径，路径包含脚本名称");
				return false;
			}
			var url = "dep_operConfigFile.do";
			mask('读取中,请稍后...','0.7','0');
			$.ajax({
				type:'post',
				url:url,
				dataType:'json',
				data:{"ip":ip,"name":name,"pwd":pwd,"path":path,"port":port},
				success:function(msg){
					var content = msg.content;
					$("#textareaContent").val(content);
					removeMask();
				}
			});
			/*
			$.getJSON(url,{'time':new Date().toString()},function(data){
				var content = data.content;
				$("#textareaContent").val(content);
				removeMask();
			});*/
		});
		
		$("#save").click(function(){
			var path = $("#path").val();
			var url = "dep_saveConfigFile.do";
			var content = $("#textareaContent").val();
			if (path.length == 0) {
				alert("路径不能为空，请输入正确的脚本路径，路径包含脚本名称");
				return false;
			}
			mask('正在保存修改后的脚本内容，请稍后','0.7','0');
			$.ajax({
				type:'post',
				url:url,
				dataType:'json',
				data:{"ip":ip,"name":name,"pwd":pwd,"path":path,"Content":content,"port":port},
				success:function(data){
					if (data.result == 1 || data.result == 2) {
						alert("保存成功");
					} else{
						alert("保存失败");
					}
					removeMask();
				}
			});
			/*
			$.post(url,{Action:"post",Content:content},function(data){
				if (data.result == 1 || data.result == 2) {
					alert("保存成功");
				} else{
					alert("保存失败");
				}
				removeMask();
			},"json");*/
		})
	});
</script>
<head>
	<title></title>
</head>
<body>
	<s:form action="" method="post" id="theForm">
		<div class="scrollbody">
			<div class="box on">
				<div class="query-form" style="width: 100%;height: 100%">
					<table style="width: 100%;height: 100%" border="0">
						<tr>
							<td colspan="2" align="left">
								路径：
								<input type="text" id="path" style="width: 629px;height: 20px"/>&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="read" value="读取" class="btn-style02"/>&nbsp;&nbsp;&nbsp;
								<input type="button" id="save" value="确定" class="btn-style02"/> &nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td align="left">
								<div style="width: 686px" class="blue-wrap noborder" id="content">
									<s:textarea rows="30" cols="100" id="textareaContent">
									</s:textarea>
								</div>
							</td>
							<td width="100%;" valign="top" align="left">
								<div style="width: 100%;height: 100%">
									<table width="100%" border="0" id="lishi" cellspacing="0" class="blue-table sorttable">
										<s:iterator value="theForm.resultList" id="theBean">
											<tr>
												<td>
													<div class="hidden" title="<s:property value="#theBean.path"/>">
														<a href="javascript:;" name="view" scriptpath='<s:property value="#theBean.path" />'>
															<s:property value="#theBean.path"/>
														</a>
													</div>
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
