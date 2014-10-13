<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<head>
	<script type="text/javascript">
	var api = frameElement.api;
	var w = api.opener;
    api.button({
		id:'OkAnd',
		name: '确定',
		type: 'submit',
		callback:choosed,
		focus: true
	},
	{
    	id:'cancle',
		name: '取消'
	}); 
	function choosed(){
		var ips = '';
		var num = 0;
		$(':checkbox').each(function(){
			if(this.checked){
				ips += $(this).val()+',';
				num++;
			}
		});
		if(ips == ''){
			alert('请选择主机!');
			return false;
		}
		api.get('management').choosedHosts(ips,num);
	}
	</script>
</head>
<body>
	<s:form action="phyHost_physicalHost.do" method="post" cssClass="theForm" id="theForm">
		<div class="scrollbody">
			<div class="box on">
				<div class="blue-wrap noborder" style="position:fixed;bottom:2px;top:10px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>选择</th>
									<th>主机</th>
									<th>IPMI用户</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="top" id="theBean">
									<tr>
										<td>
											<input type="checkbox" value="<s:property value="#theBean.ip"/>"/>
										</td>
										<td>
											<s:property value="#theBean.ip"/>
										</td>
										<td>
											<s:property value="#theBean.user"/>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	<iframe id="hidden_frame" name="hidden_frame" style="display:none;"></iframe>
</body>
