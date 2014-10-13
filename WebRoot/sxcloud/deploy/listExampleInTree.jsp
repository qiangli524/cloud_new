<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<%@ page
	import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil"%>
<%!public String getImageTag(HttpServletRequest request, String path) {
		return ImageUtil.getImageTag(request, path);
	}%>
<head>
	<title></title>
	<style type="text/css">
.font-more {
	width: 100px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}

.font-more-deploytime {
	width: 130px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}

.font-more-percent {
	width: 50px;
	height: 20px;
	line-height: 20px;
	overflow: hidden;
	white-space: nowrap;
	display: block;
	-o-text-overflow: ellipsis;
	text-overflow: ellipsis;
}
</style>
	<script type="text/javascript">
 //使用者根据用户类型自己指定参数
		var userType = '<%=request.getAttribute("userType")%>';

		//任务管理中展示部署实例的界面
		$(function() {
			if ("tasklookexample" == userType) {
				$("#buttonoper").css({
					"display" : "none"
				});
			}
			
			$("[name='refresh']").click(function(){
	        	$("#theForm").submit();
	        });
		});

		function selectAll() {
			var n = document.getElementsByName("checkboxid").length;
			var select = document.getElementsByName("checkboxid");
			if (select.length) {
				for ( var i = 0; i < n; i++) {
					if (select[i].value != "-1") {
						if (select[i].checked == true) {
							select[i].checked = false;
						} else {
							select[i].checked = true;
						}
					}
				}
			}
		}
		function sshchannel(hostId, appId, ip) {
			if (confirm("该动作会ssh连接至主机，是否继续?")) {
				var url = "hostconfig_ssh2Host.do?hostip=" + ip;
				$.dialog({
					id : ip + "",
					title : '连接主机 ' + ip,
					width : '470px',
					height : '230px',
					content : 'url:hostconfig_ssh2Host.do?hostip=' + ip,
					button : [
							{
								name : '连接',
								callback : function() {
									var ip = this.content.document
											.getElementById("ip").value;
									var port = this.content.document
											.getElementById("port").value;
									var user = this.content.document
											.getElementById("user").value;
									var pwd = this.content.document
											.getElementById("pwd").value;
									if ($.trim(port) == "") {
										alert("端口号不能为空!");
										return false;
									}
									if ($.trim(user) == "") {
										alert("用户名不能为空!");
										return false;
									}
									if ($.trim(pwd) == "") {
										alert("密码不能为空!");
										return false;
									}
									window.open(
											"depvideo_makeDeployVideo.do?user="
													+ user + "&port=" + port
													+ "&pwd=" + pwd + "&ip="
													+ ip, "ssh");
								}
							}, {
								name : '取消'
							} ]
				});
			}
		}

		//批量操作实例
		function batchOperator(oper) {
			//启动数组
			var startArray = new Array();
			//停止数据
			var stopArray = new Array();
			var operString = '';
			if (oper == '1') {
				operString = '启动';
			} else if (oper == '0'){
				operString = '停止';
			}
			//操作前是否符合要求
			var ifCorrect = false;
			var checkboxids = document.getElementsByName("checkboxid");
			var count = 0;
			if (checkboxids.length > 0) {
				for ( var i = 0; i < checkboxids.length; i++) {
					if (checkboxids[i].checked) {
						count = count + 1;
					}
				}
				if (count > 0) {
					var checked_num = 0;
					var names = '<br/>';
					var ids = '';
					//首先检查是否符合要求
					for ( var i = 0; i < checkboxids.length; i++) {
						if (checkboxids[i].checked) {
							var appId = checkboxids[i].value.split('|')[0];
							var appName = checkboxids[i].value.split('|')[1];
							//0 启动 1 停止
							var appStatus = checkboxids[i].value.split('|')[2];
							if (appStatus == '1') {
								startArray.push(appName);
							} else {
								stopArray.push(appName);
							}
							checked_num += 1;
							if (checked_num == count) {
								ids += appId;
								names += appName + "<br/>";
							} else {
								ids += appId + ",";
								names += appName + ",<br/>";
							}
						}
					}
					//判断批量数据是否符合要求
					if (oper == '1' && startArray.length === 0) {//启动
						ifCorrect = true;
					} else if (oper == '0' && stopArray.length === 0) {//停止
							ifCorrect = true;
					}
					if (ifCorrect) {
						$.dialog.confirm("确定要" + operString + "实例  ：" + names
								+ "吗?", function dealOperationData() {
									$.ajax({
									       url: "appexample_batchExampleOper.do",
									       dataType: "json",
									       type: "POST",
									       data : {'appExampleVO.operation':oper,'appExampleVO.exampleIds':ids},
									       async: false,
									       success: function (data) {
									    	   alert(data.result);
									 	   }
									  });
								});
					} else {
						var tempName = '';
						var tempArray = new Array();
						if (oper === '1') {
							tempArray = startArray;
						} else {
							tempArray = stopArray;
						}
						for ( var i = 0; i < tempArray.length; i++) {
							tempName += tempArray[i] + "<br/>";
						}
						$.dialog.alert("您选择的实例<br/>" + tempName + "状态有误，不能重复"
									+ operString + "!", null, null);
					}
				} else {
					$.dialog.alert("请选择要" + operString + "的实例 !", null, null);
				}
			}
		}
		
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
<body class="pop-body scrollbody">
	<s:form action="dep_listExamplesInTree" method="post" cssStyle="theForm"
		id="theForm">
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.HOSTID" id="HOSTID"></s:hidden>
		<s:hidden name="theForm.APPID" id="APPID"></s:hidden>
		<s:hidden name="theForm.TASK_ID" id="TASK_ID"></s:hidden>
		<div class="scrollbody">
					<div class="utt-2">
						<a class="icon-add" href="javascript:void(0)" onclick="batchOperator('1');return false;">批量启动</a>
						<a class="icon-modify" href="javascript:void(0)" onclick="batchOperator('0');return false;" >批量停止</a>
						<a class="icon-del" href="javascript:void(0)" oper="refresh" name="refresh" >刷新</a>
					</div>
						<table id="theTable" width="100%" class="blue-table sorttable"
							border="0" cellspacing="0">
							<thead>
								<tr>
				   					<th><input name="allcheckbox" type="checkbox" onclick="selectAll()"/></th>
									<th onclick="sort(theTable,0,'string')">
										实例名称
									</th>
									<th onclick="sort(theTable,1,'string')">
										当前版本
									</th>
									<th onclick="sort(theTable,2,'string')">
										部署状态
									</th>
									<th onclick="sort(theTable,3,'string')">
										管理IP
									</th>
									<th onclick="sort(theTable,4,'string')">
										服务状态
									</th>
									<th onclick="sort(theTable,5,'string')">
										访问应用
									</th>
									<th onclick="sort(theTable,6,'string')">
										上线路径
									</th>
									<th onclick="sort(theTable,7,'date')">
										部署时间
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.resultList" id="theBean">
									<tr>
						    			<td><input name="checkboxid" type="checkbox" value='<s:property value="#theBean.ID"/>|<s:property value="#theBean.exampleName" />|<s:property value="#theBean.serveState" />'/></td>
										<td>
											<s:property value="#theBean.exampleName" />
										</td>

										<td>
											<div id="div_version<s:text name="#theBean.ID"/>">
												<s:if test="#theBean.day_version==null">
										--
									</s:if>
												<s:if test="#theBean.day_version!=null">
													<s:property value="#theBean.day_version" />
												</s:if>
											</div>
										</td>

										<td align="center">
											<span id="div<s:text name="#theBean.ID"/>"> <a
												class="font-more-percent"
												title='<s:property value="#theBean.DEPLOY_PERCENT"/>'>
													<s:if test="#theBean.DEPLOY_FLAG==0">
								    未部署
								</s:if> <s:elseif test="#theBean.DEPLOY_FLAG==1">
														<img
															src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif"
															width="15" height="18" />正在部署
								</s:elseif> <s:elseif test="#theBean.DEPLOY_FLAG==2">
								   已部署
								</s:elseif> <s:elseif test="#theBean.DEPLOY_FLAG==11">
								   部署失败
								</s:elseif> <%--								<s:elseif test="#theBean.DEPLOY_FLAG==3">--%> <%--								  <img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在卸载--%>
													<%--								</s:elseif>--%> <%--								<s:elseif test="#theBean.DEPLOY_FLAG==4">--%>
													<%--								  已卸载--%> <%--								</s:elseif>--%> </a> </span>
										</td>

										<td>
											<a href="javascript:;"
												onclick="sshchannel('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>','<s:property value="#theBean.IP"/>')"><s:property
													value="#theBean.IP" />
											</a>
										</td>

										<td>
											<s:if test="#theBean.serveState==0">
												停止
											</s:if>
											<s:elseif test="#theBean.serveState==1">
												运行
											</s:elseif>
											<s:else>
												异常
											</s:else>
										</td>

										<td>
											<s:if test="#theBean.appVisitPath!=null">
												<s:if test="#theBean.serveState==1">
													<a
														onclick="visitApp('<s:property value="#theBean.appVisitPath"/>')"
														href="javascript:;"><font color="red">访问</font>
													</a>
												</s:if>
												<s:else>
										--
									</s:else>
											</s:if>
											<s:else>
									未配置
								</s:else>
										</td>

										<td style="width: 100px">
											<div class="font-more"
												title='<s:property value="#theBean.DEPLOYPATH"/>'>
												<s:property value="#theBean.DEPLOYPATH" />
											</div>
										</td>

										<td id="deployetime<s:text name="#theBean.ID"/>"
											style="width: 130px">
											<div class="font-more-deploytime"
												title='<s:property value="#theBean.deployTime"/>'>
												<s:if
													test="#theBean.DEPLOYESTARTTIME!=null && #theBean.DEPLOYESTARTTIME !=''">
													<s:property value="#theBean.DEPLOYESTARTTIME" />
												</s:if>
												<s:else>
									未部署
								</s:else>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="pages mgb-10">
						<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
					</div>
				<!--blue-wrap end -->
			<!--box end -->
		</div>
	</s:form>
	<script>
 	
 	//timer();
 	function timer(){
        setStatus();
        setTimeout("timer()",10000);    
    }
   
    function setStatus(){
        var str="";
        var checkboxids = document.getElementsByName("checkboxid");
        if(checkboxids!=null&&checkboxids.length>0){
          for(var i=0;i<checkboxids.length;i++){
            if(i==checkboxids.length-1){
              str+=checkboxids[i].value.split("|")[0];
            }else{
              str+=checkboxids[i].value.split("|")[0]+',';
            }
          }
          $.getJSON("dep_Example_Deploy_Flag.do?ID="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
			  $("#div"+data[j].ID).html(data[j].DEPLOY_FLAG_NAME);
		      $("#div_an"+data[j].ID).html(data[j].DEPLOY_FLAG_AN);
			  $("#divstartstop"+data[j].ID).html(data[j].START_STOP_FLAG_NAME);
			  $("#div_version"+data[j].ID).html(data[j].day_version);
<%--			  if(data[j].DEPLOY_FLAG !=1){--%>
			  $("#deployetime"+data[j].ID).html(data[j].DEPLOYEENDTIME);		
<%--			  }--%>
			}
		   });          
        }
    }
</script>

</body>
