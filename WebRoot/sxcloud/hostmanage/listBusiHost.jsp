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
<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/njs/changeColor.js"></script>
<%--<script type="text/javascript" src="../cjs/ui2/njs/ui/ui.js"></script>--%>
<script type="text/javascript">
    $(function(){
      $("input[name='resert']").click(function(){
            $("#ip").val("");
            $("#yingyong").get(0).selectedIndex=0;
            $("#zhuangtai").get(0).selectedIndex=0;
            $("#vlan").get(0).selectedIndex=0;
          });
        });
       
     function importxls(){
 		 $.dialog({
			id:"browse",
			title:'浏览文件',
			width: '400px',
			height: '160px',
			content: 'url:sxcloud/hostmanage/browse.jsp',
			button:[{name: '导入', callback: function () {
					//IE8 上传文件时会出现c:/fakepath/
					var file_upl = getPath(this.content.document.getElementById("browse").value);
					if(file_upl == '' || file_upl == null){
     						alert("请先选择要导入的文件!");
     					return false;
     				}
					//$(this.content.document.getElementById("browse")).val()
					var choosed = $.trim(realpath);
     				if(choosed.substring(choosed.lastIndexOf(".")+1) != "xls"){
     					alert("选择格式不正确!");
     					return false;
     				}
     				id = "xlsdata";
     				bar(id,"数据导入中,请稍后...");
     				$.post("hostmanage_importxls.do",{"location":choosed},
 						function(data){
 							if(data == 1){
 								barEnd(id,"数据导入成功!");
    							alert("导入成功!3秒后自动刷新!");
    							window.setTimeout("ok();",3000);
 							}else{
 								barEnd(id,"导入数据失败，请检查后重试!");
 							}
 					 },"json");
				}}, {name: '取消'}]
			});
     }  
     function ok(){
     	window.location.reload();
     }
     function exportxls(){
     	location.href = "hostmanage_exportxls.do";
     }
     function bar(idstr,contents){
		$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function getPath(obj) {
 		if (obj) {
  			if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
  				obj.select();
  				alert(document.selection.createRange().text);
   				return document.selection.createRange().text;
  			} else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
   				if (obj.files) {
    				return obj.files.item(0).getAsDataURL();
   				}
   				return obj.value;
  			}
  			return obj.value;
 		}
	}
	function barEnd(idstr,contents){
		$.dialog.list[idstr].content(contents,false,false);
		$.dialog.list[idstr].time(2);
	}
</script>
</head>
<body onLoad="self.focus();document.theForm.ip.focus()">
<div class="scrollbody">
<s:form action="hostmanage_listBusiHost.do" method="post" id="theForm">
<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">部署机注册</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
	<label class="mgl-20 vm">VLAN:</label>
		<s:select cssClass="select-1 vm" id="vlan" headerKey="" headerValue="请选择" list="#{'1':'Default','2':'www_web','3':'inside_web'}" name="theForm.VLAN" >
        </s:select> 
        <label class="vm">管理IP:</label>
        
        <s:textfield name="theForm.IP" id="ip"/></td>
     
     <label class="mgl-20 vm">应用:</label>
     	<s:select cssClass="select-1 vm" id="yingyong" headerKey="" headerValue="请选择"  list="theForm.resultList" listKey="ID" listValue="APPNAME" name="theForm.appid">
        </s:select>
     
     
      <label class="mgl-20 vm">主机状态:</label>
     	<s:select cssClass="select-1 vm" id="zhuangtai" headerKey="" headerValue="请选择"   list="#{'1':'空闲','2':'非空闲'}" name="theForm.STATUS">
     	</s:select>
     
     <span class="ubtn-1 mgl-20"><input id="searchForm" type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input name="reset" type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
				<div class="utt-2 mgt-20">
				<a name="add" class="icon-add" href="javascript:void(0)" onclick="javascript:addHost()">新增</a>
				<a id="import" name="mod" class="icon-release" href="javascript:void(0)" onclick="importxls();">导入数据</a>
				<a name="export" class="icon-export" href="javascript:void(0)" onclick="exportxls();" >导出数据</a>
				
			</div>
<!--主机列表 start -->

<table>
<%--
  <tr>
     <td>
       <div>
		<div class="tit-001">x86机箱</div>
		  <ul class="compose-1">
			   <logic:present name="theForm" property="resultList1">
			      <logic:iterate id="theBean" name="theForm" property="resultList1" >
			            <li>
					      <h1>
					         <span class="tool_bar">
				               <a href="/listBusiHostConfig.do?HOSTID=<bean:write name="theBean" property="ID"/>&FUNCSID=<%=request.getParameter("FUNCSID")%>"><img src="../cjs/ui2/nresources/common/icon/icon-user.gif" alt="主机用户管理" width="10" height="10" /></a> 
				               <a href="javascript:modBusihost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-search.gif" alt="主机用户查看" width="10" height="10" /></a> 
					           <a href="javascript:delHost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a> 
					           <a href="javascript:modBusihost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></a>
					         </span>
					         X86刀箱一(<bean:write name="theBean" property="HOSTNAME"/>)
					      </h1>
					      <div class="main">
					          <div class="left-c">
					              <img src="../cjs/ui2/nresources/common/images/pic-zj.jpg" width="30" height="80" />
					          </div>
					          <div class="right-c">
					          	<p>管理IP: <bean:write name="theBean" property="IP"/></p>
					            <p>服务IP：<bean:write name="theBean" property="VLANIP"/></p>
					            <p>所在VLAN：<!--<bean:write name="theBean" property="VLAN"/>-->
					            <logic:equal name="theBean" property="VLAN" value="1">Default VLAN</logic:equal>
							    <logic:equal name="theBean" property="VLAN" value="2">www_web</logic:equal>
							    <logic:equal name="theBean" property="VLAN" value="3">inside_web</logic:equal>
					            </p>
					            <p>CPU利用率: <bean:write name="theBean" property="CPU_KPI"/></p>
					            <p>内存利用率: <bean:write name="theBean" property="MEMORY_KPI"/></p>
					            <p>应用:<bean:write name="theBean" property="APPNAMES"/></p>
					          </div>
					      </div>
					    </li>
				</logic:iterate>
				</logic:present>		    
		  </ul>
		</div>
     </td>
  </tr>
  <tr>
     <td>
         <div>
			<div style="background:#82bbdc;border: 1px solid #d0d0d0;height: 26px;line-height: 28px;padding-left: 15px;margin-bottom: 8px;"> X86刀箱二 </div>
			  <ul class="compose-1">
				   <logic:present name="theForm" property="resultList2">
				      <logic:iterate id="theBean" name="theForm" property="resultList2" >
				            <li>
						      <h1>
						         <span class="tool_bar">
					               <a href="/listBusiHostConfig.do?HOSTID=<bean:write name="theBean" property="ID"/>&FUNCSID=<%=request.getParameter("FUNCSID")%>"><img src="../cjs/ui2/nresources/common/icon/icon-user.gif" alt="主机用户管理" width="10" height="10" /></a> 
					               <a href="javascript:modBusihost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-search.gif" alt="主机用户查看" width="10" height="10" /></a> 
						           <a href="javascript:delHost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a> 
						           <a href="javascript:modBusihost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></a>
						         </span>
						         X86刀箱二(<bean:write name="theBean" property="HOSTNAME"/>)
						      </h1>
						      <div class="main">
						          <div class="left-c">
						              <img src="../cjs/ui2/nresources/common/images/pic-zj.jpg" width="30" height="80" />
						          </div>
						          <div class="right-c">
						          	<p>管理IP: <bean:write name="theBean" property="IP"/></p>
						            <p>服务IP：<bean:write name="theBean" property="VLANIP"/></p>
						            <p>所在VLAN：<!--<bean:write name="theBean" property="VLAN"/>
						            --><logic:equal name="theBean" property="VLAN" value="1">Default VLAN</logic:equal>
							    	<logic:equal name="theBean" property="VLAN" value="2">www_web</logic:equal>
							    	<logic:equal name="theBean" property="VLAN" value="3">inside_web</logic:equal>
						            </p>
						            <p>CPU利用率: <bean:write name="theBean" property="CPU_KPI"/></p>
						            <p>内存利用率: <bean:write name="theBean" property="MEMORY_KPI"/></p>
						            <p>应用:<bean:write name="theBean" property="APPNAMES"/></p>
						          </div>
						      </div>
						    </li>
					</logic:iterate>
					</logic:present>		    
			  </ul>
			</div>
     </td>
  </tr>
  <tr>
     <td>
         <div>
			<div style="background:#82bbdc;border: 1px solid #d0d0d0;height: 26px;line-height: 28px;padding-left: 15px;margin-bottom: 8px;"> 小型机刀箱 </div>
			  <ul class="compose-1">
				   <logic:present name="theForm" property="resultList3">
				      <logic:iterate id="theBean" name="theForm" property="resultList3" >
				            <li>
						      <h1>
						         <span class="tool_bar">
					               <a href="/listBusiHostConfig.do?HOSTID=<bean:write name="theBean" property="ID"/>&FUNCSID=<%=request.getParameter("FUNCSID")%>"><img src="../cjs/ui2/nresources/common/icon/icon-user.gif" alt="主机用户管理" width="10" height="10" /></a> 
					               <a href="javascript:modBusihost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-search.gif" alt="主机用户查看" width="10" height="10" /></a> 
						           <a href="javascript:delHost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a> 
						           <a href="javascript:modBusihost(<bean:write name="theBean" property="ID"/>)"><img src="../cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></a>
						         </span>
						         小型机刀箱(<bean:write name="theBean" property="HOSTNAME"/>)
						      </h1>
						      <div class="main">
						          <div class="left-c">
						              <img src="../cjs/ui2/nresources/common/images/pic-zj.jpg" width="30" height="80" />
						          </div>
						          <div class="right-c">
						          	<p>管理IP: <bean:write name="theBean" property="IP"/></p>
						            <p>服务IP：<bean:write name="theBean" property="VLANIP"/></p>
						            <p>所在VLAN：<!--<bean:write name="theBean" property="VLAN"/>
						            --><logic:equal name="theBean" property="VLAN" value="1">Default VLAN</logic:equal>
							    	<logic:equal name="theBean" property="VLAN" value="2">www_web</logic:equal>
							    	<logic:equal name="theBean" property="VLAN" value="3">inside_web</logic:equal>
						            </p>
						            <p>CPU利用率: <bean:write name="theBean" property="CPU_KPI"/></p>
						            <p>内存利用率: <bean:write name="theBean" property="MEMORY_KPI"/></p>
						            <p>应用:<bean:write name="theBean" property="APPNAMES"/></p>
						          </div>
						      </div>
						    </li>
					</logic:iterate>
					</logic:present>		    
			  </ul>
			</div>
     </td>
  </tr>
  --%>
  <tr>
     <td>
        <div>
			<!--<div style="background:#82bbdc;border: 1px solid #d0d0d0;height: 26px;line-height: 28px;padding-left: 15px;margin-bottom: 8px;">IBM刀片 </div>-->
			<div class="tit-zzi">
									IBM刀片
			</div>
			  <ul class="compose-1">
				   
				      <s:iterator id="theBean" value="theForm.resultList3" >
				            <li>
						      <h1>
						         <span class="tool_bar">
					               <s:url var="theBean.ID" action="hostconfig_listBusiHostConfig">
						          	<s:param name="hostId" value="#theBean.ID"></s:param>
						          	<s:param name="hostIp" value="#theBean.IP"></s:param>
						          	<s:param name="FUNCSID" value="request.getParameter('FUNCSID')"></s:param>
						          	</s:url>
						          	<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif" alt="主机用户管理" width="10" height="10" /></s:a>
					               
					               <s:url var="theBean.ID" action="hostmanage_delBusiHost">
						          	<s:param name="ID" value="#theBean.ID"></s:param>
						          	</s:url>
						          	<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></s:a>
						           
						           <s:url var="theBean.ID"  action="hostmanage_modBusiHost">
						           	<s:param name="ID" value="#theBean.ID"></s:param>
						           </s:url>
						           <s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
						         </span>
						         IBM刀片(<s:text name="#theBean.HOSTNAME" />)
						      </h1>
						      <div class="main">
						          <div class="left-c">
						              <img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/host-icon-2.gif" width="30" height="80" />
						          </div>
						          <div class="right-c">
						          	<p>管理IP: <s:property value="#theBean.IP"/></p>
						            <p>服务IP：<s:property value="#theBean.VLANIP"/></p>
						            <p>所在VLAN：
						            <s:if test="#theBean.VLAN==1">Default VLAN</s:if>
						         	<s:if test="#theBean.VLAN==2">www_web</s:if>
						         	<s:if test="#theBean.VLAN==3">inside_web</s:if>
						            </p>
						            <p>CPU利用率: <s:property value="#theBean.CPU_KPI" /></p>
						            <p>内存利用率: <s:property value="#theBean.MEMORY_KPI"/></p>
						            <p>应用:<s:property value="#theBean.APPNAMES" /></p>
						          </div>
						      </div>
						    </li>
					</s:iterator>
					    
			  </ul>
			</div>
     </td>
  </tr>
  <tr>
     <td>
        <div>
			<!--<div style="background:#82bbdc;border: 1px solid #d0d0d0;height: 26px;line-height: 28px;padding-left: 15px;margin-bottom: 8px;">HP刀片 </div>-->
			<div class="tit-zzi">
					HP刀片
			</div>
			  <ul class="compose-1">
				   
				      <s:iterator id="theBean" value="theForm.resultList4" >
				            <li>
						      <h1>
						         <span class="tool_bar">
					               <s:url var="theBean.ID" action="hostconfig_listBusiHostConfig">
						          	<s:param name="hostId" value="#theBean.ID"></s:param>
						          	<s:param name="hostIp" value="#theBean.IP"></s:param>
						          	<s:param name="FUNCSID" value="request.getParameter('FUNCSID')"></s:param>
						          	</s:url>
						          	<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-user.gif" alt="主机用户管理" width="10" height="10" /></s:a>
						          	<s:url var="theBean.ID" action="hostmanage_delBusiHost">
						          	<s:param name="ID" value="#theBean.ID"></s:param>
						          	</s:url>
						          	<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></s:a>
						           <s:url var="theBean.ID"  action="hostmanage_modBusiHost">
						           	<s:param name="ID" value="#theBean.ID"></s:param>
						           </s:url>
						           <s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
						         </span>
						         HP刀片(<s:property value="#theBean.HOSTNAME" />)
						      </h1>
						      <div class="main">
						          <div class="left-c">
						              <img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/host-icon-4.gif" width="30" height="80" />
						          </div>
						          <div class="right-c">
						          	<p>管理IP: <s:property value="#theBean.IP" /></p>
						            <p>服务IP：<s:property value="#theBean.VLANIP"/></p>
						            <p>所在VLAN：
						            <s:if test="#theBean.VLAN==1">Default VLAN</s:if>
						            <s:if test="#theBean.VLAN==2">www_web</s:if>
						            <s:if test="#theBean.VLAN==3">inside_web</s:if>
						            </p>
						            <p>CPU利用率: <s:property value="#theBean.CPU_KPI"/></p>
						            <p>内存利用率:<s:property value="#theBean.MEMORY_KPI"/></p>
						            <p>应用:<s:property value="#theBean.APPNAMES"/></p>
						          </div>
						      </div>
						    </li>
					</s:iterator>
						    
			  </ul>
			</div>
     </td>
  </tr>
</table>







<div class="clear"></div>
</s:form>
<script type="text/javascript">
  /*
  function hostuserlist(hostid,funcsid)
  {
    alert(hostid,funcsid);
	var url = "listBusiHostConfig.do?HOSTID="+hostid+"&FUNCSID="+funcsid;
    openCenterWin(url,600,340);
  }
  function save_host_config(form2)
  {
    var obj = document.forms[0];
	form2.action="saveBusiHostConfig.do";
	form2.submit();
  }*/
  function modBusihost(hostid)
  {		
	  theForm.ID = hostid;
	  theForm.action = "hostmanage_modBusiHost.do";
	  theForm.submit();
  }
  function delHost(hostid){
     theForm.ID.value=hostid;
     if(confirm("确定要删除该主机吗！")==true)
     {
     	var url = "/checkBusiHostDel.do?ID=" + hostid +"&Date"+(new Date());
     	$.getJSON(url,function(data){
     		if(data=="NO"){
     			alert("请先删除该主机的配置信息！");
     		}else if(data=="YES"){
     			 theForm.action = "delBusiHost.do"; 
	   			 theForm.submit();
     		}
     	})
     }  
  }
  function addHost(){
	  theForm.action = 'hostmanage_addBusiHost.do';
	  theForm.submit();
  }
   function searchRequest() {
	   var hostip = theForm.IP;
   		/*if(!isnumber(hostip)){
   			alert("管理IP输入不合法,只能为.和数字!");
   			return false;
   		}*/
   		theForm.action='hostmanage_listBusiHost.do';
		theForm.submit();
 	}
 	/* 检测输入的字符串是否符合要求 */
 	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	        {
	            if (number_chars.indexOf(str.charAt(i))==-1){
	            	
	            	document.theForm.IP.value='';
	            	document.theForm.IP.focus;
	            	return false;
	            }
	        }
	        return true;
	 }
</script>

</body>
