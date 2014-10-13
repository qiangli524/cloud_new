<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=gb2312"%>
<%@ include file="../common/taglib.jsp" %>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<head>
<title></title>

<style type="text/css">
.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
white-space: nowrap;
display: block;
-o-text-overflow: ellipsis; 
text-overflow: ellipsis;}
</style>

<link href="../cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="../cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%--<script type="text/javascript" src="../cjs/ui2/njs/ui/ui.js"></script>--%>



<script type="text/javascript">
	/*
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
*/
	function resetForm(theForm){
		theForm.ID.value = '';
		theForm.APPID.value = '';
		theForm.CATCH_STATUS.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function modRequest(id) { 
 	   /*
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
 	    alert("请勾选需要修改应用！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条应用信息");
 	    return false ;
 	    }
 	    */
 	    theForm.ID.value=id;
 	    $.getJSON("app_AppFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='2' || data=='3' || data=='4' || data=='6' || data=='7'){
			  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被修改！");
			}else{
			  theForm.action = 'modApp.do' 
		      theForm.submit();
			}
		});
 	}
 	function delRequest(id) {
 		/*
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
 	    alert("请勾选需要删除应用！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条应用信息");
 	    return false ;
 	    }
 	   */
 	    theForm.ID.value=id;
 	    $.getJSON("app_AppFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='2' || data=='3' || data=='4'|| data=='6' || data=='7'){
			  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被删除！");
			}else{
			  theForm.action = 'delApp.do'
			     if(confirm("确定要删除该应用吗！")==true)
		         {
		            theForm.submit();
			     }  
			}
		});
 	}
 	
 	/*
 	function StartAndStopDeployExample(ID,PARAM) {  
	   theForm.action = "StartAndStopApp.do?ID="+ID+"&paran="+PARAM;
	   theForm.submit();
 	}
 	*/
 	 //  theForm.action = "app_CatchImage.do?APPID="+ID;
	   //	theForm.submit();
 	function CatchImage(ID) {
		var dialog = $.dialog({
			id:'remark',
			title:'填写备注',
			height:'120px',
			content:'url:app_remarkPage.do',
			button:[{name: '确定', callback: function () {
				var remarkValue = this.content.document.getElementById("remark").value;
				theForm.action = "app_CatchImage.do?APPID="+ID+"&remark="+escape(encodeURIComponent(remarkValue));
				if(confirm("确定开始捕获镜像吗?")){
					theForm.submit();
				}
			}}, {name: '取消'}]
		});
    }
 	timer();
 	function timer(){
        setStatus();
        setTimeout("timer()",20000);    
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
          $.getJSON("app_appStatus.do?ID="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
			  $("#div"+data[j].ID).html(data[j].DEPLOY_FLAG_NAME);
			}
		   });          
        }
    }
    function hisVersion(app_id){
    	var dialog = $.dialog({
			id:'version',
			title:'历史版本',
			max:false,
			min:false,
			content:'url:app_historyVersion.do?appId=' + app_id
		});
    }
</script>
</head>
<body>
<div class="mainbody">
<s:form action="app_listAppImage" method="post" id="theForm" cssStyle="theForm">
 <s:hidden name="theForm.ID" id="ID"/>
  <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">应用管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">所属应用：</label>
				 <s:select id="ID" cssClass="select-1 vm"  list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-">
						</s:select> 
				<label class="mgl-20 vm">状态：</label>
				 <s:select list="#{'':'-请选择-','0':'-未捕获-','1':'-正在捕获-','2':'-已捕获-'}" 
                     name="theForm.CATCH_STATUS" id="CATCH_STATUS"  cssClass="select-1 vm" ></s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)" onclick="addRequest();return false;">新增</a>
				<a class="icon-modify" href="javascript:void(0)" onclick="modRequest();return false;" >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="delRequest();return false;" >删除</a>
			</div>
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
			  <thead>
			  <tr>
				   <th onclick="sort(theTable,0,'string')">应用名称</th>
				   <th onclick="sort(theTable,1,'string')">部署策略</th>
				   <th onclick="sort(theTable,2,'string')">应用状态</th>
				   <th onclick="sort(theTable,3,'string')">部署主机用户</th>
<%--				   <th>应用英文标识</th>--%>
<%--				   <th>应用编号</th>--%>
<%--				   <th>业务名称</th>--%>
					<th onclick="sort(theTable,4,'string')">捕获路径</th>
					<th >捕获状态</th>
					<th onclick="sort(theTable,5,'string')">捕获时间</th>
					<th>历史版本</th>
				   <th>操作</th>
			  </tr>
			  </thead>
			  <tbody>
			  <s:iterator value="theForm.resultList" id="theBean">
						<tr>
						<td style="display: none"><input  name="checkboxid" type="checkbox" value="<bean:write name="theBean" property="ID"/>"/></td>
						    <td><s:property value="#theBean.APPNAME"/></td>
							<td>
							<s:if test="#theBean.STRATEGYTYPE==1">
							录像部署</s:if>
							<s:elseif test="#theBean.STRATEGYTYPE==2">
							基准部署</s:elseif></td>
							<td>
							  <s:if test="#theBean.STATUS==1">
						      已注册</s:if>
							   <s:elseif test="#theBean.STATUS==2">正在部署</s:elseif>
							  <s:elseif test="#theBean.STATUS==3">已部署</s:elseif>
							  <s:elseif test="#theBean.STATUS==4">正在注销</s:elseif>
							  <s:elseif test="#theBean.STATUS==5">已注销</s:elseif>
							  <s:elseif test="#theBean.STATUS==6">正在升级</s:elseif>
							  <s:elseif test="#theBean.STATUS==7">已升级</s:elseif></td>
							<td><s:property value="#theBean.HOSEUSERNAME"/></td>
<%--							<td><bean:write name="theBean" property="APP_IDENTIFY"/></td>--%>
<%--							<td>--%>
<%--								<bean:write name="theBean" property="ID"/>						</td>--%>
							
<%--							<td>--%>
<%--								<bean:write name="theBean" property="OPERATIONNAME"/>						</td>--%>
							<td style="width: 160px"><a style="color: black;" class="font-more" title='<s:property value="#theBean.DEPLOYPATH"/>'><s:property value="#theBean.DEPLOYPATH"/></a></td>
							
							<td>
							<span id='div<s:property value="#theBean.ID"/>'>
							<s:if test="#theBean.CATCH_STATUS==0">
							未捕获</s:if>
							  <s:elseif test="#theBean.CATCH_STATUS==1"><img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在捕获</s:elseif>
							  <s:elseif test="#theBean.CATCH_STATUS==2">已捕获</s:elseif>
							  </span>
							</td>
							<td>
							<span id='div_time<bean:write name="theBean" property="ID"/>'>
							<s:property value="#theBean.CATCH_TIME"/>
							</span>
							</td>
							<td>
								<a href="javascript:;" onclick="hisVersion('<s:property value="#theBean.ID"/>');">查看历史版本</a>
							</td>
							<td>
									<input type="button" class="thickbox btn-style02"
										value="捕获"
										onclick="CatchImage('<s:property value="#theBean.ID"/>');return false;" />
							</td>
						</tr>
				</s:iterator>
			  </tbody>
			</table>
			<div class="pages ">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
		 </div>
	</s:form>
</div>	
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
          $.getJSON("image_Deploy_Flag.do?ID="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
			  $("#div"+data[j].ID).html(data[j].CATCH_STATUS);
		      $("#div_time"+data[j].ID).html(data[j].CATCH_TIME);
			}
		   });          
        }
    }
</script>
</body>
