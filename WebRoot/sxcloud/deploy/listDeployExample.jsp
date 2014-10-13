<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<head>
<title></title>
<style type="text/css">
.font-more{ width:100px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
.font-more-percent{ width:50px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>
<script type="text/javascript">
	$(function(){
		$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    	});
	});
	function resetForm(theForm){
	    theForm.APPID.value= '0';
		theForm.DEPLOY_FLAG.value = '';
		theForm.START_STOP_FLAG.value = '';		
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'dep_addDeployExample.do?operType=list' 
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
 	        	var content = "确定要部署吗?";
 	        	$.ajax({
 	        		type : "get",
 	        		url : "dep_getDeployPath.do" ,
 	        		data : "id="+ ID,
 	        		dataType : "json",
 	        		async: false,
 					cache : false,
 	        		success : function(data){
 	        			content = "确定要部署到" + data.path + "吗?";
 	        		}
 	        	});
 	          if(confirm(content)==true)
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
				         $.getJSON("dep_deployRequest.do?ID="+checkboxids[i].value+"&paran=1"+"&operType=list",{'time':new Date().toString()},function(data){
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
 	    $.getJSON("dep_DeployFlagStatus.do?ID="+theForm.ID.value+"&operType=list",{'time':new Date().toString()},function(data){
			if(data=='1' || data=='2' || data=='3'){
			  alert("部署实例状态为 '未部署' 或 '已卸载' 的状态才能被修改！");
			}else{
			  theForm.action = 'dep_modDeployExample.do?operType=list' 
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
 	    $.getJSON("dep_DeployFlagStatus.do?ID="+theForm.ID.value+"&operType="+"list",{'time':new Date().toString()},function(data){
			if(data=='1' || data=='2' || data=='3'){
			  alert("部署实例状态为 '未部署' 或 '已卸载' 的状态才能被删除！");
			}else{
			  if(confirm("确定要删除该部署实例吗?")==true)
		      {
		        theForm.action = 'dep_delDeployExample.do?operType=list'  
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
			/**
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
			*/
			var url = "hostconfig_ssh2Host.do?hostip=" + ip;
			$.dialog({
					id:ip+"",
					title:'连接主机 '+ip,
					width: '370px',
					height: '130px',
					content: 'url:hostconfig_ssh2Host.do?hostip='+ip,
					button:[{name: '连接', callback: function () {
						var ip = this.content.document.getElementById("ip").value;
						var port = this.content.document.getElementById("port").value;
						var user = this.content.document.getElementById("user").value;
						var pwd = this.content.document.getElementById("pwd").value;
						if($.trim(port) == ""){
							alert("端口号不能为空!");
							return false;
						}
						if($.trim(user) == ""){
							alert("用户名不能为空!");
							return false;
						}
						if($.trim(pwd) == ""){
							alert("密码不能为空!");
							return false;
						}
					window.open("depvideo_makeDeployVideo.do?user="+user+"&port="+port+"&pwd="+pwd+"&ip="+ip,"ssh");	
					}}, {name: '取消'}]
				});
		}
	}
	function visit(hostId,appId){
		var url = "hostconfig_getDepExampleAppPath.do?appId=" + appId ;
		$.getJSON(url,{'time':new Date().toString()},function(data){
			var apppath = data.apppath;
			if(apppath == null || apppath=='' ||apppath=='null' ){
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
				$.dialog({
					id : ip  +"",
					title : "查看日志 " +ip,
					width: '970px',
					height: '480px',
					content: 'url:dep_getStartLogInfo.do?ip='+ip+"&name="+name+"&hostId="+hostId+"&appId=" + appId
				});
				//window.open("dep_getStartLogInfo.do?ip="+ip+"&name="+name+"&pwd="+pwd+"&path="+logpath,"日志",'scrollbars=yes,resizable=yes');
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
				$.dialog({
					id:ip+"",
					title:'修改配置文件  '+ip,
					width: '970px',
					height: '515px',
					content: 'url:dep_getConfigFileInfo.do?ip='+ip+'&name='+name+'&pwd='+pwd});
		 	}
		});	
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
 	    		theForm.action = 'dep_upgradeDeployExample.do?ids='+ checkedIds+"&operType=list"; 
				theForm.submit();
 	    	}
 	    });
	}
	
 	</script>
</head>
<body>
<s:form action="dep_listDeployExample" method="post" cssStyle="theForm" id="theForm">
<!--  <s:hidden name="theForm.ID" id="ID"></s:hidden>  -->
<s:hidden name="theForm.SYS_ID" id="SYS_ID"></s:hidden>
<s:hidden name="theForm.HOSTID" id="HOSTID"></s:hidden>
<s:hidden name="theForm.ID" id="ID"/>
	 <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">部署实例管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                <label class="mgl-20 vm">所属应用:</label>
				        <s:select  cssClass="select-1 vm" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-">
						</s:select>    
				    <label class="mgl-20 vm">部署状态:</label>
                        <s:select cssClass="select-1 vm" list="#{'':'请选择','0':'-未部署-','1':'-正在部署-','2':'-已部署-'}" name="theForm.DEPLOY_FLAG" id="DEPLOY_FLAG"></s:select>
<%--                        ,'3':'-正在卸载-','4':'-已卸载-'--%>
                   <label class="mgl-20 vm">启用状态:</label>
                         <s:select cssClass="select-1 vm" list="#{'':'-请选择-','1':'-已停止-','2':'-正在启动-','3':'-已启动-'}" name="theForm.START_STOP_FLAG" id="START_STOP_FLAG"></s:select>
                   
                   
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
	<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
	
			
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th><input name="allcheckbox" type="checkbox" onclick="selectAll()" /></th>
				   <th onclick="sort(theTable,1,'string')">实例名称</th>
				   <th onclick="sort(theTable,2,'string')">管理IP</th>
<%--				   <th>服务IP</th>--%>
				   <th onclick="sort(theTable,3,'string')">基准应用</th>
				   <th onclick="sort(theTable,4,'string')">上线路径</th>
	<!--		   <th>备份</th>
				   <th>重启</th>   -->	
				   <th onclick="sort(theTable,5,'string')">部署状态</th>
                   <th onclick="sort(theTable,6,'string')">服务器状态</th>
                   <th onclick="sort(theTable,7,'date')">部署时间</th>
                   <th onclick="sort(theTable,8,'string')">当前版本</th>
<%--                   <th>操作</th>--%>
                   <th onclick="sort(theTable,9,'string')">访问应用</th>
<%--                   <th>启动日志</th>--%>
<%--                   <th>配置文件</th>--%>
			  </tr>
			  </thead>
			  <tbody>
			      <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						    <td><input name="checkboxid" type="checkbox" value='<s:property value="#theBean.ID"/>'/></td>
							<td><s:property value="#theBean.exampleName"/></td>
						    <td> <a href="javascript:;" onclick="sshchannel('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>','<s:property value="#theBean.IP"/>')"><s:property value="#theBean.IP"/></a></td>
<%--							<td>--%>
<%--							  <s:if test="#theBean.VLANIP!=null">--%>
<%--									<s:text name="#theBean.VLANIP" />--%>
<%--							   </s:if>--%>
<%--							</td>--%>
							<td><s:property value="#theBean.APPNAME"/></td>
							<td  style="width: 100px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.DEPLOYPATH"/>' >
							<s:property value="#theBean.DEPLOYPATH"/>
							</a></td>
					<%-- 		
							<td>
								<s:if test="#theBean.isbackup==0">不备份</s:if>
								<s:elseif test="#theBean.isbackup==1">备份</s:elseif>					
							</td>
							<td>
								<s:if test="#theBean.isrestart==0">不重启</s:if>
								<s:elseif test="#theBean.isrestart==1">重启</s:elseif>							
							</td>  --%>
							<td>
							  <span id="div<s:text name="#theBean.ID"/>">
							  	<a  class="font-more-percent" title='<s:property value="#theBean.DEPLOY_PERCENT"/>' >
								    <s:if test="#theBean.DEPLOY_FLAG==0">
									    未部署
									</s:if>
									<s:elseif test="#theBean.DEPLOY_FLAG==1">
									  <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在部署
									</s:elseif>
									<s:elseif test="#theBean.DEPLOY_FLAG==2">
									   已部署
									</s:elseif>
									<s:elseif test="#theBean.DEPLOY_FLAG==11">
									   	 部署失败
									</s:elseif>
	<%--								<s:elseif test="#theBean.DEPLOY_FLAG==3">--%>
	<%--								  <img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在卸载--%>
	<%--								</s:elseif>--%>
	<%--								<s:elseif test="#theBean.DEPLOY_FLAG==4">--%>
	<%--								  已卸载--%>
	<%--								</s:elseif>--%>
									</a>
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
<%--							<td>-->
<%--							    <div id="div_an<s:text name="#theBean.ID"/>">--%>
<%--							    <s:if test="#theBean.DEPLOY_FLAG==0">--%>
<%--								    <input type="button" class="thickbox btn-style02"--%>
<%--										value="部署"--%>
<%--										onclick="deployRequest('<s:text name="#theBean.ID"/>','1');return false;" />--%>
<%--								</s:if>--%>
<%--								<s:if test="#theBean.DEPLOY_FLAG==2">--%>
<%--								   <input type="button" class="thickbox btn-style02"--%>
<%--										value="卸载"--%>
<%--										onclick="deployRequest('<s:text name="#theBean.ID"/>','3');return false;" />--%>
<%--								</s:if>--%>
<%--								<s:if test="#theBean.DEPLOY_FLAG==4">--%>
<%--								   <input type="button" class="thickbox btn-style02"--%>
<%--										value="部署"--%>
<%--										onclick="deployRequest('<s:text name="#theBean.ID"/>','1');return false;" />--%>
<%--								</s:if>--%>
<%--								<s:if test="#theBean.START_STOP_FLAG==1">--%>
<%--								   <input type="button" class="thickbox btn-style02"--%>
<%--										value="启动"--%>
<%--										onclick="StartAndStopDeployExample('<s:text name="#theBean.ID"/>','2');return false;" />--%>
<%--								</s:if>--%>
<%--								<s:if test="#theBean.START_STOP_FLAG==3">--%>
<%--								  <input type="button" class="thickbox btn-style02"--%>
<%--										value="停止"--%>
<%--										onclick="StartAndStopDeployExample('<s:text name="#theBean.ID"/>','0');return false;" />--%>
<%--								</s:if>--%>
<%--								</div>--%>
<%--							</td>--%>
							<td>
								<a href="javascript:;" onclick="visit('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>');">访问</a> 
							</td>
<%--							<td>--%>
<%--								<a href="javascript:;" onclick="startlog('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>','<s:property value="#theBean.IP"/>');">查看</a>--%>
<%--							</td>--%>
<%--							<td>--%>
<%--								<a href="javascript:;" onclick="modifyConfigFile('<s:property value="#theBean.HOSTID"/>','<s:property value="#theBean.APPID"/>','<s:property value="#theBean.IP"/>');">修改</a>--%>
<%--							</td>--%>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
			<div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
<script>
 	
<%-- 	timer();--%>
<%-- 	function timer(){--%>
<%--        setStatus();--%>
<%--        setTimeout("timer()",10000);    --%>
<%--    }--%>
<%--   --%>
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

