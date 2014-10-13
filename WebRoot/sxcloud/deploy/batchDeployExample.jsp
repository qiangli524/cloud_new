<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>

<head>
<title></title>
<style type="text/css">
.font-more{ width:100px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<script type="text/javascript">
	var nodeId = '<%=request.getAttribute("nodeId") %>';
 	var api = frameElement.api;
	var w = api.opener;
	
	api.button({
	    id:'Ok',
	    name: '确定',
	    callback:deployRequestAll,
	    focus: true
	},
	{
	    id:'cancle',
	    name: '取消'
	});
		
 	function deployRequestAll(){
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    var count = 0;
 	    if(checkboxids.length>0){
 	   		for(var i=0;i<checkboxids.length;i++){
 	   			if(checkboxids[i].checked){
 	   				var flag = $(checkboxids[i]).attr("deployFlag");
 	   				if(flag != 0){
 	   					alert("请选择未部署的实例！");
 	   					return false;
 	   				}
 	   				count = count + 1;
 	   			}
 	   		}
			if(count>0){
				if(confirm("确定要部署吗?")==true){
					var ids = new Array();
			    	for(var i=0;i<checkboxids.length;i++){
				       if(checkboxids[i].checked){
				       		ids.push(checkboxids[i].value);
				       }
				    }
			    	w.batchDeployExampleRequest(ids,nodeId);
				}
			}else{
				alert("请选择要部署的实例");
			}
 	    }
 	}
	
 	</script>
</head>
<body>
<s:form action="dep_listDeployExample" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody" style="overflow: auto;height: 400px;" >
	<div class="blue-wrap noborder">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th></th>
				   <th>管理IP</th>
				   <th>应用</th>
				   <th>上线路径</th>
				   <th>部署状态</th>
                   <th>服务器状态</th>
                   <th>部署时间</th>
                   <th>当前版本</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						    <td><input name="checkboxid" type="checkbox" value='<s:property value="#theBean.ID"/>' deployFlag='<s:property value="#theBean.DEPLOY_FLAG"/>' /></td>
						    <td> <a href="javascript:;" onclick="sshchannel('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>','<s:property value="#theBean.IP"/>')"><s:property value="#theBean.IP"/></a></td>
							<td><s:property value="#theBean.APPNAME"/></td>
							<td  style="width: 100px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.DEPLOYPATH"/>' >
							<s:property value="#theBean.DEPLOYPATH"/>
							</a></td>
							<td>
							  <span id="div<s:text name="#theBean.ID"/>">
							    <s:if test="#theBean.DEPLOY_FLAG==0">
								    未部署
								</s:if>
								<s:elseif test="#theBean.DEPLOY_FLAG==1">
								  <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在部署
								</s:elseif>
								<s:elseif test="#theBean.DEPLOY_FLAG==2">
								   已部署
								</s:elseif>
							  </span>
							</td>
							<td>
							  <div id="divstartstop<s:text name="#theBean.ID"/>">
							     <s:if test="#theBean.START_STOP_FLAG==0">
								    <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在停止
								</s:if>
								<s:elseif test="#theBean.START_STOP_FLAG==1">
								   <img src="<%=request.getContextPath() %>/sxcloud/images/downed.png" width="16" height="16"/>已停止
								</s:elseif>
								<s:elseif test="#theBean.START_STOP_FLAG==2">
								  <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在启动
								</s:elseif>
								<s:elseif test="#theBean.START_STOP_FLAG==3">
								   <img src="<%=request.getContextPath() %>/sxcloud/images/uped.png" width="16" height="16"/>已启动
								</s:elseif>
							 </div>
							</td>
							<td id="deployetime<s:text name="#theBean.ID"/>">
							<s:property value="#theBean.DEPLOYESTARTTIME"/>--
							<s:if test="#theBean.DEPLOYESTARTTIME!=null">
							<s:if test="#theBean.DEPLOYEENDTIME==null">
							未结束
							</s:if>
							</s:if>
							<s:if test="#theBean.DEPLOYEENDTIME!=null">
							<s:property value="#theBean.DEPLOYEENDTIME"/>
							</s:if>
							</td>
							<td>
								<div id="div_version<s:text name="#theBean.ID"/>">
									<s:if test="#theBean.day_version==null">
										--
									</s:if>
									<s:if test="#theBean.day_version!=null">
										<s:property value="#theBean.day_version"/>
									</s:if>
								</div>
							</td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
</div>
</s:form>
</body>
