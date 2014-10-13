<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.APPID.value = '';
		theForm.IP.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	
 	function resumeRequest(ID) {
 	    if(confirm("确定要恢复吗！")==true)
	    {
	      $.getJSON("resume_resumeRequest.do?ID="+ID,{'time':new Date().toString()},function(data){
			 setStatus();
		  });     
	    }
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
 	    alert("请勾选需要删除升级实例恢复信息!");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能删除单条升级实例恢复信息!");
 	    return false ;
 	    }
 	    $.getJSON("resume_ResumeFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='1'){
			  alert("对不起，该升级实例正在进行恢复，不能删除!!!");
			}else{
			  if(confirm("确定要删除吗！")==true)
	          {
			   theForm.action = 'resume_delUpgradeExampleResume.do';  
		       theForm.submit();
		      }
			}
		});
 	}
 	
 	timer();
 	function timer(){
        setStatus();
        setTimeout("timer()",12000);    
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
          $.getJSON("resume_Example_RESUME_Flag.do?ID="+str,{'time':new Date().toString()},function(data){
			for(j=0;j<data.length;j++){

			  $("#div"+data[j].ID).html(data[j].RESUME_FLAG_NAME);
		      $("#div_an"+data[j].ID).html(data[j].RESUME_FLAG_AN);
			}
		   });          
        }
    }
    
</script>
</head>
<body>
	<s:form action="resume_upgradeExampleResumeList.do" method="post" id="theForm">
		<s:hidden name="theForm.ID" id="ID"/>
		<div class="scrollbody">
			<div align="left">
				<s:if test="theFormerror_msg!=null">
					<font color="red"> 
						<s:text name="theForm.error_msg" /> </font></s:if>
			</div>
			<div class="query">
				<div class="title">
					<%=getImageTag(request, "query-icon.gif")%>
				</div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
						    <td class="til">所属应用:</td>
						    <td>	
						    	<s:select list="theForm.appList" headerKey="" headerValue="-请选择-" name="theForm.APPID" listKey="ID" listValue="APPNAME" id="APPID"></s:select>
						    </td>
							<td class="til">
								主机IP
							</td>
							<td>
								<s:textfield name="theForm.IP"  id="IP"/>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->
				<div class="blue-wrap noborder">
					<div class="table-head">
						<ul class="btns">
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;" />
							</li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										恢复版本名称
									</th>
									<th  onclick="sort(theTable,2,'string')">
										主机IP
									</th>
									<th  onclick="sort(theTable,3,'string')">
									   	 应用名称
									</th>
									<th  onclick="sort(theTable,4,'date')">
										创建时间
									</th>
									<th  onclick="sort(theTable,5,'string')">
										恢复状态
									</th>
									<th>
										操作指令
									</th>
								</tr>
							</thead>
							<tbody>
									<s:iterator id="theBean" value="theForm.resultList">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value="<s:property value="#theBean.ID"/>" />
											</td>
											<td>
												<s:text name="#theBean.NAME" />
											</td>
											<td>
												<s:text name="#theBean.IP" />
											</td>
											<td>
												<s:text name="#theBean.APPNAME"  />
											</td>
											<td>
												<s:text name="#theBean.CREATETIME"  />
											</td>
											<td>
											    <div id="div<s:property value="#theBean.ID" />">
											    <s:if test="#theBean.RESUME_FLAG==0">未恢复</s:if>
											    <s:if test="#theBean.RESUME_FLAG==1"><img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在恢复</s:if>
												 <s:if test="#theBean.RESUME_FLAG==2">已恢复</s:if>
												<div id="div<s:property value="#theBean.ID" />">	
											</td>
											<td>
											    <div id="div_an<s:property value="#theBean.ID"/>">
											    <s:if test="#theBean.RESUME_FLAG==2"><input type="button" class="thickbox btn-style02"
														value="恢复"
														onclick="resumeRequest('<s:property value="#theBean.ID" />');return false;" /></s:if>
												<s:if test="#theBean.RESUME_FLAG==0"><input type="button" class="thickbox btn-style02"
														value="恢复"
														onclick="resumeRequest('<s:property value="#theBean.ID" />');return false;" /></s:if>

												</div>
											</td>
										</tr>
									</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
</html:html>
