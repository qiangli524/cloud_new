<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<title></title>
	<style type="text/css">
		div.hidden{
		width:50px;
		height:30px;
		overflow:hidden;
		white-space:nowrap;
		text-overflow:ellipsis;
		text-overflow: ellipsis;/* IE/Safari */
		-ms-text-overflow: ellipsis;
		-o-text-overflow: ellipsis;/* Opera */
		-moz-binding: url("ellipsis.xml#ellipsis");/*FireFox*/
	}
</style>
<style type="text/css">
		.font-more{ width:80px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
  	
  		$(function(){
  			$check = $(":checkbox");
  			$check.unbind().live("click",function(){
  				$check.not(this).attr("checked",false);
  			});
  		});	
	
		$(function(){
			$(".query").click(function(){
    			if($(".query-form").is(":visible")){
    				$(".query-form").slideUp("slow");
    			}else{
    				$(".query-form").slideDown("slow");
    			}
    		});
			
			$("#searchForm").click(function(){
				$("#theForm").submit();
			});
			
			$("#resetForm").click(function(){
				$("#type").val("-1");
				$("#project").val("-1");
			});
			
			
			$("[name='viewResource']").unbind().live("click",function(){
				var $td = $(this).parent();
				var uuid = $td.attr("uuid");
				var state = $td.attr("states");
				var wstat = $td.attr("wstat");
				$.dialog({
					id:'viewResource',
					title:'查看工单任务',
					width: '1000px',
					height: '600px',
					lock:true,
					content: 'url:workorder_listResource.do?uuid='+uuid+'&state='+state+'&wstat='+wstat
				});
			});
		});
	  	
	</script>
</head>
<body>
	<s:form action="workorder_viewDealedWorkOrder.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">类型:</td>
		                  		<td>
		                  			<s:select list="#{'-1':'--请选择--','0':'申请','1':'调整','2':'回收'}" name="workOrderObj.TYPE" id="type"></s:select>
		                  		</td>
		                  		<td class="til">所属项目:</td>
		                  		<td>
		                  			<s:select list="projectList" listKey="ID" listValue="PROJECT_NAME" name="workOrderObj.PROJECT_ID" 
		                  				id="project" headerKey="-1" headerValue="请选择"></s:select>
		                  		</td>
		                  </tr>
		                  <tr>
		                    <td colspan="10" class="btns">
		                        <div>
									<input type = "button" class="thickbox btn-style02" value = "查询" id="searchForm" />
									<input type = "button" class="btn-style02" value = "重置" id="resetForm" />
		                        </div>
		                    </td>
		                  </tr>
	                </table>
				</div>
				
				<div class="blue-wrap noborder" style="bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
					<div class="table-head">
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">工单编号</th>
									<th onclick="sort(theTable,1,'string')">类型</th>
									<th onclick="sort(theTable,2,'string')">状态</th>
									<th onclick="sort(theTable,3,'string')">来源</th>
									<th onclick="sort(theTable,4,'string')">申请人</th>
									<th onclick="sort(theTable,5,'string')">所属项目</th>
									<th onclick="sort(theTable,6,'string')">任务总数</th>
									<th onclick="sort(theTable,7,'string')">成功</th>
									<th onclick="sort(theTable,8,'string')">失败</th>
									<th onclick="sort(theTable,9,'string')">待处理</th>
									<th onclick="sort(theTable,10,'string')">处理中</th>
									<th onclick="sort(theTable,11,'string')">查看任务</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
					  					<td>
					  						<s:property value="#theBean.BOMC_UUID"/>
					  					</td>
						  				<td>
						  					<s:if test="#theBean.TYPE == 0">申请</s:if>
						  					<s:elseif test="#theBean.TYPE == 1">调整</s:elseif>
						  					<s:elseif test="#theBean.TYPE == 2">回收</s:elseif>
										</td>
										<td>
						  					<s:if test="#theBean.WSTAT == 0">未处理</s:if>
						  					<s:elseif test="#theBean.WSTAT == 1">已处理</s:elseif>
										</td>
						  				<td>
						  					<s:if test="#theBean.CAMEFROM == 0">BOMC</s:if>
						  					<s:elseif test="#theBean.CAMEFROM == 1">云平台</s:elseif>
										</td>
										<td>
					  						<s:property value="#theBean.SHOWNAME"/>
					  					</td>
						  				<td>
						  					<s:property value="#theBean.PROJECT_NAME" default="无" />
										</td>
					  					<td>
					  						<s:property value="#theBean.ALLCOUNT"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.SUCCESS"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.FALUIRE"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.WAITDEAL"/>个
					  					</td>
					  					<td>
					  						<s:property value="#theBean.DEALING"/>个
					  					</td>
										<td uuid='<s:property value="#theBean.UUID"/>' states='<s:property value="#theBean.STATE"/>'
											wstat='<s:property value="#theBean.WSTAT"/>' >
											<a href="javascript:;" name="viewResource">查看</a>
										</td>
					  				</tr>
				  				</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</s:form>
</body>
