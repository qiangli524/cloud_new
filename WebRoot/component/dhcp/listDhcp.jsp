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
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
  <script type="text/javascript">
	  	$(function(){
			$check = $(":checkbox");
			$check.unbind().live("click",function(){
				$check.not(this).attr("checked",false);
			});
		})
	  
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
				$("#ip").val("");
			});
			
			$("#addForm").click(function(){
				$.dialog({
					id:'adddhcp',
					title:'增加dhcp信息',
					width: '500px',
					height: '320px',
	    		    lock:true,
					content: 'url:dhcp_insertDhcp.do?oper=add',
				});
			});
			
			$("#editForm").unbind().live("click",function(){

				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行修改");
					return false;
				}
				var dhcpid = "";
				$(":checkbox:checked").each(function(){
					dhcpid+=$(this).attr("dhcpid");
				});

				$.dialog({
	        		id:'editdhcp',
	        		title:'编辑dhcp',
	        		width: '520px',
	    			height: '300px',
	    		    lock:true,
	    		    content:'url:dhcp_updateDhcp.do?id='+dhcpid+'&oper=edit'
	        	});
			});
			
			$("#deleteForm").unbind().live("click",function(){
				
				if ($(":checkbox:checked").length == 0) {
					alert("你好,请至少选择一项来进行删除");
					return false;
				}
				
				var dhcpid = "";
				$(":checkbox:checked").each(function(){
					dhcpid+=$(this).attr("dhcpid");
				});
				if(confirm("确定要删除?")){
					var url = "dhcp_deleteDhcp.do?id="+dhcpid;
					$.ajax({
						type:'post',
						url:url,
						success:function(msg){
							if (msg == 0) {
								alert("删除失败");
							} else{
								alert("删除成功");
		            			$("#theForm").submit();
		            		}
						}
					});
				}
			});
		});
	  	
	  	function saveDhcp(theform){
	       	 $.ajax({
	            type: "post",
	            url: "dhcp_saveDhcp.do?"+theform,
	            dataType: "json",
	            success : function(data){
		            $("#theForm").submit();
	              }
	          });
	       }
	</script>
</head>
<body>
	<s:form action="dhcp_queryDhcpList.do" method="post" id="theForm" cssStyle="theForm">
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					 <table width="100%" class="querytable" border="0">
		                  <tr>
		                  		 <td class="til">IP地址:</td>
		                  		<td><s:textfield name="dhcpObj.IP" id="ip" cssClass="txt"></s:textfield></td>
		                  </tr>
		                  <tr>
		                    <td colspan="8" class="btns">
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
									<th>选择</th>
									<th onclick="sort(theTable,1,'string')">IP</th>
									<th onclick="sort(theTable,2,'string')">MAC</th>
									<th onclick="sort(theTable,3,'string')">IP状态</th>
									<th onclick="sort(theTable,4,'string')">描述</th>
									<th onclick="sort(theTable,5,'date')">插入时间</th>
									<th onclick="sort(theTable,6,'date')">更新时间</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="resultList" id="theBean">
					  				<tr>
						  				<td><input name="checkboxid" type="checkbox" dhcpid='<s:property value="#theBean.ID"/>'/>
													</td>
						  				<td><s:property value="#theBean.IP" />
													</td>
						  				<td><s:property value="#theBean.MACADDRESS" />
													</td>
										<td><s:if test="#theBean.IPSTATUS==1">已使用</s:if> <s:else>未使用</s:else>
													</td>
						  				<td><s:property value="#theBean.DESCR" />
													</td>
										<td><s:property value="#theBean.INSERTDATE" />
													</td>
										<td><s:property value="#theBean.UPDATEDATE" />
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
