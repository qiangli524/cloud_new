<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<head>
<title></title>
<style type="text/css">
.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<script type="text/javascript">
	function resetForm(theForm){
	    theForm.APPID.value= '';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';		
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'dep_addDeployExample.do' 
		theForm.submit();
 	}
 	
 	function StartAndStopDeployExample(ID,PARAM) {
 	        if(PARAM=='2'){
 	          if(confirm("确定要启动该主机吗?")==true)
		      {
		       $.getJSON("dep_StartAndStopDeployExample.do?ID="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       }); 
		      }
 	        }else{
 	          if(confirm("确定要停止该主机吗?")==true)
		      {
		       $.getJSON("dep_StartAndStopDeployExample.do?ID="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       });
		      }
 	        }
 	}
 	function deployRequest(ID,PARAM) {
 	        if(PARAM=='1'){
 	          if(confirm("确定要部署吗?")==true)
		      {
		       $.getJSON("dep_deployRequest.do?ID="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		       }); 
		      }
 	        }else{
 	          if(confirm("确定要卸载吗?")==true)
		      {
		        $.getJSON("dep_deployRequest.do?ID="+ID+"&paran="+PARAM,{'time':new Date().toString()},function(data){
		         setStatus();
		        });  
		      }
 	        }
 	}
 	function deployRequestAll(){
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    var count = 0;
 	    if(checkboxids.length>0){
 	   		for(var i=0;i<checkboxids.length;i++){
 	   			if(checkboxids[i].checked){
 	   				count = count + 1;
 	   			}
 	   		}
			if(count>0){
			       if(confirm("确定要部署吗?")==true){
			         for(var i=0;i<checkboxids.length;i++){
				       if(checkboxids[i].checked){
				         $.getJSON("dep_deployRequest.do?ID="+checkboxids[i].value+"&paran=1",{'time':new Date().toString()},function(data){
			        	setStatus();
			        }); 
				       }
				       if(i==(checkboxids.length-1)){
			            setStatus();
			          }  
				     }
			       }
			}else{
				alert("请选择要部署的实例");
			}
 	    }
 	}
 	function uninstallRequestAll(){
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids.length>0){
 	       if(confirm("确定要卸载吗?")==true){
 	         for(var i=0;i<checkboxids.length;i++){
	 	       if(checkboxids[i].checked){
	 	        $.getJSON("dep_deployRequest.do?ID="+checkboxids[i].value+"&paran="+3,{'time':new Date().toString()},function(data){
		        });
	 	       }
	 	       if(i==(checkboxids.length-1)){
	             setStatus();
	           }  
	 	     }
 	       }
 	    }else{
 	       alert('请选择要卸载的记录!');
 	    }
 	}
 	function modRequest() {
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改的部署实例信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条部署实例信息！");
 	    return false ;
 	    }
 	    $.getJSON("dep_DeployFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='1' || data=='2' || data=='3'){
			  alert("部署实例状态为 '未部署' 或 '已卸载' 的状态才能被修改！");
			}else{
			  theForm.action = 'dep_modDeployExample.do' 
		      theForm.submit();
			}
		});
 	    
 	}
 	function delRequest() {
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要删除的部署实例信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条部署实例信息");
 	    return false ;
 	    }
 	    $.getJSON("dep_DeployFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='1' || data=='2' || data=='3'){
			  alert("部署实例状态为 '未部署' 或 '已卸载' 的状态才能被删除！");
			}else{
			  if(confirm("确定要删除该主机吗?")==true)
		      {
		        theForm.action = 'dep_delDeployExample.do'  
		        theForm.submit();
		      }
			}
		});
 	   
 	}
 	function selectAll()
	{
		var n=document.getElementsByName("checkboxid").length;
		var select=document.getElementsByName("checkboxid");
		if(select.length)
		{
			for(var i=0;i<n;i++)
			{
				if(select[i].value!="-1")
				{
					if(select[i].checked==true){
						select[i].checked=false;
					} else {
						select[i].checked=true;
					}    
			    } 
			}
		}
	}
	function sshchannel(hostId,appId,ip){
		if(confirm("该动作会ssh连接至主机，是否继续?")){
			var url  = "hostconfig_getUserInfo.do?hostId=" + hostId + "&appId=" + appId ;
			$.getJSON(url,{'time':new Date().toString()},function(data){
				var name = data.name;
				var port = data.port;
				var pwd = data.pwd;
				if(name == null || port==null || pwd == null){
					alert("查询到的主机用户ssh配置错误！");
				}else{
					window.open("depvideo_makeDeployVideo.do?user="+name+"&port="+port+"&pwd="+pwd+"&ip="+ip,"ssh");
				}				
			});
		}
	}
	function visit(hostId,appId){
		var url = "hostconfig_getUserInfo.do?hostId=" + hostId + "&appId=" + appId ;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			var apppath = data.apppath;
			if(apppath == null){
				alert("未配置应用访问地址路径!");				
			}else{
				window.open(apppath,"应用",'scrollbars=yes,resizable=yes');
			}
		});
	}
	function startlog(hostId,appId,ip){
		var url = "hostconfig_getUserInfo.do?hostId=" + hostId + "&appId=" + appId ;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			var logpath = data.logpath;
			var name = data.name;
			var pwd = data.pwd;
			if(name ==null){
				alert("未查询到连接至主机的用户!");
			}else if(pwd == null){
				alert("未查询到用户密码!");
			}else if(logpath == null){
				alert("未查询到配置的日志文件路径!");				
			}else{
				window.open("dep_getStartLogInfo.do?ip="+ip+"&name="+name+"&pwd="+pwd+"&path="+logpath,"日志",'scrollbars=yes,resizable=yes');
			}
		});	
	}
	function modifyConfigFile(hostId,appId,ip){
		
	   	var url = "hostconfig_getUserInfo.do?hostId=" + hostId + "&appId=" + appId ;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			var name = data.name;
			var pwd = data.pwd;
			if(name =="null"){
				alert("未查询到连接至主机的用户!");
			}else if(pwd == "null"){
				alert("未查询到用户密码!");
			}else{
			
				openwindow("dep_getConfigFileInfo.do?ip="+ip+"&name="+name+"&pwd="+pwd,'日志',700,500);
		 	}
		});	
	}
	function openwindow(url,name,iWidth,iHeight)
	{
		var url; //转向网页的地址;
		var name; //网页名称，可为空;
		var iWidth; //弹出窗口的宽度;
		var iHeight; //弹出窗口的高度;
		var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
		window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=yes,location=no,status=no');
	}
	function upgrade(){
		var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    var checkedIds = "";
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    	for(var i=0;i<checkboxids.length;i++){
 	      		if(checkboxids[i].checked){
 	      			couterNum = couterNum + 1 ;
 	      			checkedIds += checkboxids[i].value + ",";
 	      		}
 	    	}
 	    }
 	    if(couterNum==0){
 	    	alert("请勾选需要升级的部署实例信息！");
 	    	return false ;
 	    }
 	    //判断选择的部署实例是否为同一基准应用部署的
 	    var url = "dep_sameBaseApp.do?ids=" + checkedIds;
 	    $.getJSON(url,{'time':new Date().toString()},function(data){
 	    	if(data.result == -1){
 	    		alert("选择的应用必须为同一基准应用部署!");
 	    	}else{
 	    		theForm.action = 'dep_upgradeDeployExample.do?ids='+ checkedIds; 
				theForm.submit();
 	    	}
 	    });
	}
	
 	</script>
</head>
<body>
<s:form action="dep_listDeployExample" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="theForm.ID" id="ID"></s:hidden>
<div class="scrollbody">
	<div class="box on">
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">管理IP</th>
<%--				   <th>服务IP</th>--%>
				   <th onclick="sort(theTable,1,'string')">应用</th>
				   <th onclick="sort(theTable,2,'string')">上线路径</th>
				   <th onclick="sort(theTable,3,'string')">备份</th>
				   <th onclick="sort(theTable,4,'string')">重启</th>
				   <th onclick="sort(theTable,5,'string')">部署状态</th>
                   <th onclick="sort(theTable,6,'string')">服务器状态</th>
                   <th onclick="sort(theTable,7,'date')">部署时间</th>
                   <th onclick="sort(theTable,8,'string')">访问应用</th>
                   <th onclick="sort(theTable,9,'string')">查看启动日志</th>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						    <td><s:property value="#theBean.IP"/></td>
							<td><s:property value="#theBean.APPNAME"/></td>
							<td ><s:property value="#theBean.DEPLOYPATH"/></td>
							
							<td>
								<s:if test="#theBean.isbackup==0">不备份</s:if>
								<s:elseif test="#theBean.isbackup==1">备份</s:elseif>					
							</td>
							<td>
								<s:if test="#theBean.isrestart==0">不重启</s:if>
								<s:elseif test="#theBean.isrestart==1">重启</s:elseif>							
							</td>
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
<%--								<s:elseif test="#theBean.DEPLOY_FLAG==3">--%>
<%--								  <img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在卸载--%>
<%--								</s:elseif>--%>
<%--								<s:elseif test="#theBean.DEPLOY_FLAG==4">--%>
<%--								  已卸载--%>
<%--								</s:elseif>--%>
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
								<a href="javascript:;" onclick="visit('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>');">访问</a> 
							</td>
							<td>
								<a href="javascript:;" onclick="startlog('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>','<s:property value="#theBean.IP"/>');">查看启动日志</a>
							</td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
<script>
 	
 	timer();
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
<%--			  if(data[j].DEPLOY_FLAG !=1){--%>
			  	$("#deployetime"+data[j].ID).html(data[j].DEPLOYEENDTIME);		
<%--			  }--%>
			}
		   });          
        }
    }
</script>

</body>
