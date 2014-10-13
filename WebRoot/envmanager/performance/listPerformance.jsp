<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
  <head>
   
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript">
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})
	function searchRequest() { 
		theForm.submit();
 	}
 	function resetForm(){
			document.getElementById("CF_IP").value="";
			document.getElementById("MEM_USED").value="";
			document.getElementById("MEM_FREE").value="";
			document.getElementById("CPU_IDLE").value="";
		}
 	
	</script>

  </head>
  
  <body>
  
   <s:form action="performance_queryPerformanceList.do" id="theForm" method="post"
		>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								IP地址:
							</td>
							<td>
								<s:textfield name="theForm_CF_IP" cssClass="txt"
									id="CF_IP"></s:textfield>
							</td>
							<td class="til">
								内存使用:
							</td>
							<td>
								<s:textfield name="theForm_MEM_USED" cssClass="txt"
									id="MEM_USED"></s:textfield>
							</td>
							<td class="til">
								内存空闲:
							</td>
							<td>
								<s:textfield name="MEM_FREE" cssClass="txt"
									id="MEM_FREE"></s:textfield>
							</td>
							<td class="til">
								CPU空闲:
							</td>
							<td>
								<s:textfield name="CPU_IDLE" cssClass="txt"
									id="CPU_IDLE"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick = "javascript:searchRequest()"/>
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
						<!--<ul class="btns">
							<li>
								<input type="button" class="thickbox btn-style02" value="添加"
									onclick = "addRequest();return false;"  />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;"  />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;"/>
							</li>
						</ul>-->
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										IP地址
									</th>
									<th>
										内存使用(M)
									</th>
									<th>
										内存空闲(M)
									</th>
									<th>
										CPU空闲
									</th>
									<th>
										存储剩余(G)
									</th>
									<th>
										存储总量(G)
									</th>
									
									<th>
										检查时间
									</th>
									
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.Perfor_List" id="theBean">
									<tr >
										<td>
											<s:property value="#theBean.CF_IP" />
										</td>
										<td>
											<s:property value="#theBean.MEM_USED" />
										</td>
										<td>
											<s:property value="#theBean.MEM_FREE" />
										</td>
											<td>
											<s:property value="#theBean.CPU_IDLE" />
										</td>
											<td>
											<s:property value="#theBean.STOR_FREE" />
										</td>
										<td>
											<s:property value="#theBean.STOR_ALL" />
										</td>
											<td>
											<s:property value="#theBean.CHE_TIME" />
										</td>
										

									</tr>
								</s:iterator>
							</tbody>


						</table>
							<div style="height:1000px;"></div>
							<div style="position:absolute; display:none; background-color:#eee; width:200px; height:300px;" id="mdiv" ></div>
							</div>
						</div>
					</div>
				</div>
		

	</s:form>
   
 </body>

