<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<head>
<style type="text/css">
.pop-table tr td{text-align: left;}
.font-more-percent{ width:50px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<script type="text/javascript">
	function visitApp(appVisitPath){
		var dialog = $.dialog({
			id:'appVisit',
			title:'访问应用 : ' +appVisitPath,
			max:true,
			min:true,
			height:'520px',
			width:'820px',
			content:'url:'+appVisitPath
		});
	}
</script>
</head>
<body>
<s:form action="busitree_deployExampleInfo" method="post" id="theForm">
		<div class="table-ct">
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr>
					<td class="til" width="10%" align="left">
						启动用户：
					</td>
					<td>
						<s:property value="theForm.hostUserName"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="10%" align="left">
						实例名称：
					</td>
					<td>
						<s:property value="theForm.exampleName"/>
					</td>
				</tr>
				<tr>
					<td class="til" width="10%" align="left">
						部署机IP：
					</td>
					<td>
						<s:property value="theForm.deployHostIp"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						进程：
					</td>
					<td>
						<s:if test="theForm.process==''">
							无
						</s:if>
						<s:else>
							<s:property value="theForm.process"/>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						脚本：
					</td>
					<td>
						<s:if test="theForm.script==''">
							无
						</s:if>
						<s:else>
							<s:property value="theForm.script"/>
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						上线路径：
					</td>
					<td>
						<s:property value="theForm.deployPath"/>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						部署状态：
					</td>
					<td>
						<a  class="font-more-percent" title='<s:property value="theForm.deployPercent"/>' >
							<s:if test="theForm.deploy_flag==0">
								未部署
							</s:if>
							<s:elseif test="theForm.deploy_flag==1">
								正在部署
							</s:elseif>
							<s:elseif test="theForm.deploy_flag==2">
								已部署
							</s:elseif>
							<s:elseif test="theForm.deploy_flag==11">
								部署失败
							</s:elseif>
						</a>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						服务状态：
					</td>
					<td>
						 <s:if test="theForm.serveState==0">
							停止
						</s:if>
						<s:elseif test="theForm.serveState==1">
							运行
						</s:elseif>
						<s:else>
							异常
						</s:else>
					</td>
				</tr>
				<tr>
					<td class="til" align="left">
						部署时间：
					</td>
					<td>
						<s:if test="theForm.deployeEndtime!=null">
							<s:property value="theForm.deployeEndtime"/>
						</s:if>
						<s:else>
							未部署
						</s:else>
					</td>
				</tr>
				<s:if test="theForm.deployExampleType==0">
					<tr>
						<td class="til" align="left">
							应用访问地址：
						</td>
						<td>
							<s:if test="theForm.serveState==1">
								<s:if test="theForm.appAccessPath!=null">
									<a onclick="visitApp('<s:property value="theForm.appAccessPath"/>')" href="javascript:;"><font color="red">访问</font></a>
								</s:if>
								<s:else>
									未配置
								</s:else>
							</s:if>
							<s:else>
								<s:if test="theForm.appAccessPath!=null">
									<s:property value="theForm.appAccessPath"/>
								</s:if>
								<s:else>
									未配置
								</s:else>
							</s:else>
						</td>
					</tr>
				</s:if>
			</table>
		</div>
</s:form>
</body>
