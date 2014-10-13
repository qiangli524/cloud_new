<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/portalet.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<head>
	<title></title>
	<script type="text/javascript">
		var operType = '<%=request.getAttribute("operType") %>';
		var nodeId = '<%=request.getAttribute("nodeId") %>';
		var dialogName = '<%=request.getAttribute("dialogName") %>';
	
		function submitForm(){
			var ids = "";
			if(theForm.versionDesc.value == null ||theForm.versionDesc.value == ''){
				alert("请填写版本备注");
				return false;
			}
			$("[name=checkboxid]").each(function(){
					ids += this.value + ",";
			});
			if(operType == 'list'){
				theForm.action = "dep_startUpgrade.do?ids=" + ids+"&operType=list";
				if(confirm("确定开始升级吗?")){
					theForm.submit();
				}
			}else if(operType == 'tree'){
				var api = frameElement.api;
				var w = api.opener;
				
				var APPID = theForm.APPID.value;
				var onlinePath = theForm.onlinePath.value;
				var versionDesc = theForm.versionDesc.value;
				var isrestart = theForm.isrestart.value;
				var isbackup = theForm.isbackup.value;
				
				w.startUpgrade(dialogName,nodeId,ids,APPID,onlinePath,versionDesc,isrestart,isbackup);
			}
		}
	</script>
</head>
<body class="pop-body scrollbody">
    <s:form action="dep_queryAppIp" method="post" id="theForm">
    	<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
			<div class="tit-zzi">
			<div id="zi">
				基准应用信息
			</div>
			<div id="zi"></div>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td class="til">
						选择基准应用
					</td>
					<td>
						<s:text name="theForm.APPNAME" />
					</td>
				</tr>
				<s:if test="theForm.standardHostIP!=null">
					<tr>
						<td class="til">
							基准机IP
						</td>
						<td>
							<s:text name="theForm.standardHostIP" />
						</td>
					</tr>
				</s:if>
				<s:if test="theForm.standardPath!=null">
					<tr>
						<td class="til">
							基准机应用基准路径
						</td>
						<td>
							<s:text name="theForm.standardPath" />
						</td>
					</tr>
				</s:if>
<%--				<s:if test="theForm.onlinePath!=null">--%>
					<tr>
						<td class="til">
							部署机上线文件路径
						</td>
						<td>
							<s:textarea name="theForm.onlinePath" id="onlinePath" cssStyle="txt"  cols="100" rows="5"/>
						</td>
					</tr>
<%--				</s:if>--%>
				<s:if test="theForm.startsh!=null">
					<tr>
						<td class="til">
							启动脚本
						</td>
						<td>
							<s:text name="theForm.startsh" />
						</td>
					</tr>
				</s:if>
				<s:if test="theForm.stopsh!=null">
					<tr>
						<td class="til">
							停止脚本
						</td>
						<td>
							<s:text name="theForm.stopsh" />
						</td>
					</tr>
				</s:if>
<%--				<s:if test="theForm.versionDesc!=null">--%>
					<tr>
						<td class="til">
							版本备注
						</td>
						<td>
							<s:textarea name="theForm.versionDesc" id="versionDesc" cssStyle="txt"  cols="100" rows="2"/>
						</td>
					</tr>
<%--				</s:if>--%>
			</table>
		</div>
		<div class="tit-zzi">
			<div id="zi">
				部署操作选项
			</div>
			<div id="zi"></div>
		</div>
		<table width="100%" border="0" cellspacing="0"
			class="pop-table nosize">
			<tr>
				<td class="til">
					是否重启:
					<font color="red">*</font>
				</td>
				<td>
				<s:select list="#{'0':'-不重启-','1':'-重启-'}" 
				name="theForm.isrestart" id="isrestart"></s:select>
				</td>
			</tr>
			<tr>
				<td class="til">
					是否备份:
					<font color="red">*</font>
				</td>
				<td>
				<s:select list="#{'0':'-不备份-','1':'-备份-'}" 
				name="theForm.isbackup" id="isbackup"></s:select>
				</td>
			</tr>
		</table>
		
			<div class="tit-zzi">
				<div id="zi">
					主机列表
				</div>
				<div id="zi"></div>
			</div>
			<div>
				<table width="100%" class="blue-table sorttable" border="0"
					cellspacing="0">
					<thead>
						<tr>
							<th>
								已选择
							</th>
							<th>
								管理IP
							</th>
							<th>
								服务IP
							</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.hostList" id="theBean">
							<tr>
								<td>
									<input name="checkboxid" type="checkbox"
										value='<s:property value="#theBean.APPID" />' checked="checked" disabled="disabled"/>
								</td>
								<td>
									<s:property value="#theBean.IP" />
								</td>
								<td>
									<s:property value="#theBean.VLANIP" />
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		<div class="blue-wrap noborder">
			<table width="100%" border="0" cellspacing="0"
				class="pop-table nosize">
				<tr>
					<td colspan="4" class="btnCenter">
						<input type="button" class="btn-style02" value="升级"
							onclick="javascript:submitForm();" />
						<input type="button" class="btn-style02" value="返回"
							onclick=" window.history.back();return false;" />
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</body>
