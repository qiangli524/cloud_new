<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<%@ include file="../common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.APPID.value = '';
		theForm.IP.value = '';
	//	theForm.RESUME_FLAG.value = '';
	}

   function searchRequest() { 
		theForm.submit();
 	}
 	
 	function resumeRequest(ID) {
 	    if(confirm("确定要恢复吗！")==true)
	    {
	      $.getJSON("example_resumeRequest.do?ID="+ID,{'time':new Date().toString()},function(data){
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
 	    $.getJSON("example_ResumeFlagStatus.do?ID="+theForm.ID.value,{'time':new Date().toString()},function(data){
			if(data=='1'){
			  alert("对不起，该升级实例正在进行恢复，不能删除!!!");
			}else{
			  if(confirm("确定要删除吗！")==true)
	          {
			   theForm.action = 'example_delUpgradeExampleResume.do';  
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
          $.getJSON("example_Example_RESUME_Flag.do?ID="+str,{'time':new Date().toString()},function(data){
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
    <s:form action="example_ExampleResumeList" method="post" cssStyle="theForm" id="theForm">
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
						    <td class="til">所属应用:</td>
						    <td>
						       <s:select id="ID" list="theForm.appList" listKey="ID" name="theForm.APPID" id="APPID" listValue="APPNAME" headerKey="0" headerValue="-请选择-" >
						       </s:select>
						    </td>
							<td class="til">
								主机名称
							</td>
							<td>
							    <s:textfield name="theForm.IP" cssStyle="txt" id="IP"/>
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
						<jsp:include page="../inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th>
										恢复版本名称
									</th>
									<th>
										主机IP
									</th>
									<th>
									   	 应用名称
									</th>
									<th>
										创建时间
									</th>
									<th>
										恢复状态
									</th>
									<th>
										操作指令
									</th>
								</tr>
							</thead>
							<tbody>
							        <s:iterator value="theForm.resultList" id="theBean">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value="<s:text name="#theBean.ID" />" />
											</td>
											<td>
											    <s:text name="#theBean.NAME" />
											</td>
											<td>
											    <s:text name="#theBean.IP" />
											</td>
											<td>
											    <s:text name="#theBean.APPNAME" />
											</td>
											<td>
											    <s:text name="#theBean.CREATETIME" />
											</td>
											<td>
											    <div id="div<s:text name="#theBean.ID" />">
												    <s:if test="#theBean.RESUME_FLAG==0">
													    未恢复
													</s:if>
													<s:elseif test="#theBean.RESUME_FLAG==1">
													  <img src="<%=request.getContextPath() %>/sxcloud/images/ajax-loader.gif" width="15" height="18"/>正在恢复
													</s:elseif>
													<s:elseif test="#theBean.RESUME_FLAG==2">
													   已恢复
													</s:elseif>
												</div>	
											</td>
											<td>
											    <div id="div_an<s:text name="#theBean.ID" />">
											    <s:if test="#theBean.RESUME_FLAG==2">
													    <input type="button" class="thickbox btn-style02"
														value="恢复"
														onclick="resumeRequest('<s:text name="#theBean.ID" />');return false;" />
												</s:if>
												<s:elseif test="#theBean.RESUME_FLAG==0">
												  <input type="button" class="thickbox btn-style02"
														value="恢复"
														onclick="resumeRequest('<s:text name="#theBean.ID" />');return false;" />
												</s:elseif>
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
