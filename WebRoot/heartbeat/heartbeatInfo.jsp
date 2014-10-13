<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/thickbox.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/thickbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/contextmenu.r2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<%@ include file="../sxcloud/common/taglib.jsp"%>
<head>
<script type="text/javascript">
</script>
</head>
<body>
<s:form>
<div class="scrollbody">
	<div class="query">
		<div class="title"></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder">
		<div class="table-head">
		    <ul class="btns">
			</ul>
		</div>
					<div class="table-ct">
						<table  width="100%" class="blue-table sorttable"
							border="0" cellspacing="0">
							<thead>
								<tr>
									<th>
										程序
									</th>
									<th>
										监控状况
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										云管理平台接口程序
									</td>
									<td>
										<s:if test="%{beatMap['rest']==null || beatMap['rest']==''}">
											已断开连接
											<img src="<%=request.getContextPath()%>/heartbeat/ico/noheart.gif" width="40" height="40"/>
										</s:if>
										<s:else>
											<img src="<%=request.getContextPath()%>/heartbeat/ico/beat.gif" width="40" height="40"/>
											正常运行中
										</s:else>
									</td>
								</tr>
								<tr>
									<td>
										云管理平台后台程序
									</td>
									<td>
										<s:if test="%{beatMap['daemon']==null || beatMap['daemon']==''}">
											<img src="<%=request.getContextPath()%>/heartbeat/ico/noheart.gif" width="40" height="40"/>
											已断开连接
										</s:if>
										<s:else>
											<img src="<%=request.getContextPath()%>/heartbeat/ico/beat.gif" width="40" height="40" />
											正常运行中
											</s:else>
									</td>
								</tr>
								<tr>
									<td>
										云管理平台监控程序
									</td>
									<td>
										<s:if test="%{beatMap['monitor']==null || beatMap['monitor']==''}">
											<img src="<%=request.getContextPath()%>/heartbeat/ico/noheart.gif" width="40" height="40"/>
											已断开连接
										</s:if>
										<s:else>
											<img src="<%=request.getContextPath()%>/heartbeat/ico/beat.gif" width="40" height="40"/>
											正常运行中
										</s:else>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
</div>
</s:form>
</body>
