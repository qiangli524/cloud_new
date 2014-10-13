<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
<title></title>
  <style type="text/css">
		div.hidden{
		width:170px;
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
				$("#appName").val(null);
				$("#appStatus").val("-1");
				$("#appUrl").val(null);
			});
			
			$("#addForm").click(function(){
				$.dialog({
					id:'addApplication',
					title:'添加应用',
					width: '500px',
					height: '320px',
	    		    lock:true,
					content: 'url:tbcloud2application_add.do?oper=add',
				});
			});
			
			$("#editForm").click(function(){
				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行修改");
					return false;
				}
				if ($(":checkbox:checked").length > 1) {
					alert("每次只能修改一条记录");
					return false;
				}
				var appId = "";
				$(":checkbox:checked").each(function(){
					appId+=$(this).attr("appId");
				});
				$.dialog({
	        		id:'updateApp',
	        		title:'修改',
	        		width: '520px',
	    			height: '300px',
	    		    lock:true,
	    		    content:'url:tbcloud2application_update.do?applicationObj.id='+appId+'&oper=edit'
	        	});
			});
			
			$("#deleteForm").click(function(){
				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行删除");
					return false;
				}
				var appId = "";
				$(":checkbox:checked").each(function(){
					appId+=$(this).attr("appId")+",";
				});
				if(confirm("你确定要进行删除操作吗?")){
					var url = "tbcloud2application_delete.do?applicationObj.id="+appId;
					$.ajax({
						type:'post',
						url:url,
						dataType:'text',
						success:function(msg){
							if (msg == -1) {
								alert("删除失败");
							} else {
								$("#theForm").submit();
							}
						}
					});
				}
			});
		});
	  	
	  	function saveApp(theForm){
	  		$.ajax({
	  			type:'post',
	  			url:'tbcloud2application_save.do?'+theForm,
	  			dataType:'text',
	  			success:function(data){
	  				if (data == -1) {
						alert("保存失败");
					} else {
						$("#theForm").submit();
					}
	  			}
	  		});
	     }
	  	
	</script>
</head>
<body style="overflow-y:auto">
	<s:form action="tbcloud2application_list.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		<td class="til">应用名：</td>
		                  		<td>
		                  			<s:textfield name="applicationObj.appname" id="appName"></s:textfield> 
		                  		</td>
		                  		<td class="til">应用状态：</td>
		                  		<td>
									<s:select list="#{'-1':'请选择','0':'异常','1':'正常'}" name="applicationObj.status" id="appStatus"></s:select>
		                  		</td>
		                  		<td class="til">应用地址：</td>
		                  		<td>
									<s:textfield name="applicationObj.url" id="appUrl"></s:textfield>
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
						<ul class="btns">
							<li><input type="button" class="thickbox btn-style02" value="增加" id="addForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="修改" id="editForm" /></li>
							<li><input type="button" class="thickbox btn-style02" value="删除" id="deleteForm" /></li>
						</ul>
						<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
							<thead>
								<tr>
									<th onclick="sort(theTable,0,'string')">选择</th>
									<th onclick="sort(theTable,1,'string')">应用名</th>
									<th onclick="sort(theTable,2,'int')">应用状态</th>
									<th onclick="sort(theTable,3,'string')">应用地址</th>
									<th onclick="sort(theTable,4,'int')">应用响应码</th>
									<th onclick="sort(theTable,5,'')">应用描述</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
					  					<td>
					  						<input type="checkbox" name="checkboxid" appId='<s:property value="#theBean.id"/>'/>
					  					</td>
					  					<td>
					  						<s:property value="#theBean.appname"/>
					  					</td>
					  					<td>
					  						<s:if test="#theBean.status==0">异常</s:if>
					  						<s:else>正常</s:else>
					  					</td>
						  				<td align="center">
											<span style="color: black;" class="font-more" title='<s:property value="#theBean.url"/>'>
												<s:property value="#theBean.url" default="--"/>
											</span>
										</td>
										<td>
											<s:property value="#theBean.resultcode" default="--"/>
										</td>
										<td align="center">
											<span style="color:black;" class="font-more" title='<s:property value="#theBean.description"/>'>
												<s:property value="#theBean.description" default="--"/>
											</span>
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
