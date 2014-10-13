<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
<title></title>


<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />

<%--<script type="text/javascript" src="../cjs/ui2/njs/ui/ui.js"></script>--%>



<script type="text/javascript">
	/*
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
*/
	function resetForm(theForm){
		theForm.APPNAME.value = '';
		theForm.STATUS.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	function addRequest() {
 	    theForm.action = 'app_addApp.do?operType=list' 
		theForm.submit();
 	}
 	function modRequest(id) { 
 	    theForm.ID.value=id;
 	    $.getJSON("app_AppFlagStatus.do?ID="+theForm.ID.value+"&operType="+"list",{'time':new Date().toString()},function(data){
			if(data=='2' || data=='3' || data=='4' || data=='6' || data=='7'){
			  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被修改！");
			}else{
			  theForm.action = 'app_modApp.do?operType=list' 
		      theForm.submit();
			}
		});
 	}
 	function delRequest(id) {
 	    theForm.ID.value=id;
 	   $.getJSON("app_queryAppSonNum.do?appId="+id,{'time':new Date().toString()},function(data){
			if(data>0){
				alert("该基准应用下存在部署实例，不能删除！");
			}else{
				  $.getJSON("app_AppFlagStatus.do?ID="+id+"&operType="+"list",{'time':new Date().toString()},function(data){
						if(data=='2' || data=='3' || data=='4'|| data=='6' || data=='7'){
						  alert("应用状态为 '已注册' 或 '已注销' 的状态才能被删除！");
						}else{
						  theForm.action = 'app_delApp.do?operType=list'
						     if(confirm("确定要删除该应用吗！")==true)
					         {
					            theForm.submit();
						     }  
						}
					});
			}
		});
 	  
 	}
 	
 	
 	
 	//timer();
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
          $.getJSON("appStatus.do?ID="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){
			  $("#div"+data[j].ID).html(data[j].DEPLOY_FLAG_NAME);
			}
		   });          
        }
    }
</script>
</head>
<body>
<s:form action="app_listApp.do" method="post" styleId="theForm" id="theForm">
 <!-- <bean:define id="theForm" name="appForm"/> -->
 <s:hidden name="theForm.ID" id="ID"></s:hidden>
 <s:hidden name="theForm.SYS_ID" id="SYS_ID"></s:hidden>
<div class="scrollbody" >
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
        <div class="query-form">
                <table width="100%" class="querytable" border="0">
                  <tr>
                    <td class="til">应用名称:</td>
                    <td>
                        <s:textfield name="theForm.APPNAME" cssStyle="txt" id="APPNAME"/>
						<!-- <html:text name="theForm" property="APPNAME"  styleClass="txt" /> -->
                    </td>
                    <td class="til">状态:</td>
                    <td>
                        <s:select list="#{'1':'已注册','3':'已部署','5':'已注销'}" name="theForm.STATUS" id="STATUS"></s:select>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="8" class="btns">
                        <div>
							<input type = "button" class="thickbox btn-style02" value = "查询" onclick = "javascript:searchRequest()" />
							<input type = "button" class="btn-style02" value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" />
                        </div>
                    </td>
                  </tr>
                </table>
        </div><!--query-form end -->
	
	<div class="blue-wrap noborder" style="overflow: auto;height: 400px">
		<div class="table-head">
		    <ul class="btns">
				<li><input type="button" class="thickbox btn-style02" value="增加" onclick = "addRequest();return false;" /></li>
			</ul>
		</div>
		<div class="table-ct">
			 <ul class="compose-2">				
			      <s:iterator value="theForm.resultList" id="theBean">
				      <div style="display:none">
				      <input name="checkboxid" type="checkbox" value="<s:text name="#theBean.ID"/>"/>
				      </div>
					  <li>
					    <h1>
     				    <span class="tool_bar">
            			 <a href="javascript:delRequest(<s:text name="#theBean.ID"/>)"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" width="10" height="10" /></a> 
           				 <a href="javascript:modRequest(<s:text name="#theBean.ID"/>)"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" width="10" height="10" /></a>
     				    </span>
     				    <s:text name="#theBean.APPNAME"/>
     				 </h1>
		      			<div class="main">
		         			 <div class="left-c">
		              		 <img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/images/pic-yy.jpg" width="75" height="80" />
		          		</div>
		          		<div class="right-c">
		          			 <p align="left">        部署策略:
		          			              <s:if test="#theBean.STRATEGYTYPE==1">
											   录像部署
											</s:if>
											<s:elseif test="#theBean.STRATEGYTYPE==2">
											基准部署
										   </s:elseif>
		          			               </p>
		            		<p align="left">        应用状态：
		            				  <span id="div<s:text name="#theBean.ID"/>">
		            				    <s:if test="#theBean.STATUS==1">
										已注册
										</s:if>
										<s:elseif test="#theBean.STATUS==2">
										正在部署
										</s:elseif>
										<s:elseif test="#theBean.STATUS==3">
										已部署
										</s:elseif>
										<s:elseif test="#theBean.STATUS==4">
										正在注销
										</s:elseif>
										<s:elseif test="#theBean.STATUS==5">
										已注销
										</s:elseif>
		            				  </span></p>
		            		<p align="left">        基准主机用户：<s:text name="#theBean.HOSEUSERNAME"/> </p>
		            		<p align="left">        应用英文标识：<s:text name="#theBean.APP_IDENTIFY"/></p>
            		<p align="left">        应用编号：<s:text name="#theBean.ID"/></p>
<!--            		<p align="left">        业务名称：<a href="javascript:void(0);"><s:text name="#theBean.OPERATIONNAME"/></a></p>-->
<%--		            		<p align="left">        业务名称：<a href="/mainChartByBz.do?BZ_TYPE=11-20&"><s:text name="#theBean.OPERATIONNAME"/></a></p>--%>
		            		
		         		 </div>
		      			</div>
		    		</li>
				</s:iterator>
			</ul>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
</html:html>
