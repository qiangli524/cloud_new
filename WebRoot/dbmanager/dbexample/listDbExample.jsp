<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<script type="text/javascript">
	$(function(){
		$check = $(":checkbox");
		$check.unbind().live("click",function(){
			$check.not(this).attr("checked",false);
		});
	})
	$(function(){
	
		$("#search").click(function(){
			$("#theForm").submit();
		});
		
		$("#reset").click(function(){
			$("#examplename").val("");
			$("#iscomplete").val("-1");
		});
		
        $("[name='addorder']").click(function(){
	  		$.dialog({
    			id:'editexample',
    			title:'创建数据库实例',
    			width: '500px',
    			height: '300px',
    			lock:true,
    			content: 'url:dbexample_addDBExample.do?oper=add'
	    	});
        });
        
        $("[name='orderdeploy']").click(function(){
        	if ($(":checkbox:checked").length == 0) {
				alert("请选择一个实例来发布!");
				return false;
			}
        	
        	 var eid = "";
        	 var iscomp = 0;
        	 $(":checkbox:checked").each(function(){
        		 eid += $(this).attr("exampleid");
        		 iscomp = $(this).attr("iscomplete");
             });
        	 if (iscomp == 1) {
				alert("该实例已经创建，不能重复创建");
				return false;
			}
        	mask('正在创建数据库实例,请稍后....','0.7','0px');
        	$.ajax({
        		type:'post',
        		dataType:'json',
        		url:'dbexample_deployExample.do?eid='+eid+'&iscomplete=1',
        		success:function(msg){
        			removeMask();
        			if (msg.result == 1) {
						alert("创建实例成功！");
						$("#theForm").submit();
					} else{
						alert("创建实例失败！");
					}
        		}
        	});
        });
        
        $("[name='qzcancel']").click(function(){
        	if ($(":checkbox:checked").length == 0) {
				alert("请选择一个实例来取消创建!");
				return false;
			}
        	var eid = "";
        	var iscomp = 0;
       	 	$(":checkbox:checked").each(function(){
       		 	eid += $(this).attr("exampleid");
       		 	iscomp = $(this).attr("iscomplete");
            });
       	 	if (iscomp == 0) {
				alert("该实例未创建，不能取消创建");
				return false;
			}
       	    mask('正在取消创建数据库实例,请稍后....','0.7','0px');
        	$.ajax({
        		type:'post',
        		dataType:'json',
        		url:'dbexample_deployExample.do?eid='+eid+'&iscomplete=0',
        		success:function(msg){
        			removeMask();
        			if (msg.result == 1) {
						alert("取消成功！");
						$("#theForm").submit();
					} else{
						alert("取消失败！");
					}
        		}
        	});
        });
        
        $("[name='shuaxin']").click(function(){
        	$("#theForm").submit();
        });
        
        $("[name='editexample']").unbind().live("click",function(){
        	var $td = $(this).parent();
        	var eid = $td.attr("exampleid");
        	$.dialog({
        		id:'editexample',
        		title:'编辑数据库实例',
        		width: '520px',
    			height: '300px',
    			lock:true,
    		    content:'url:dbexample_addDBExample.do?oper=edit&eid='+eid
        	});
        });
        
        $("[name='delexample']").unbind().live("click",function(){
        	var $td=$(this).parent();
        	var eid = $td.attr("exampleid");
        	mask('正在删除数据库实例,请稍后....','0.7','0px');
        	$.ajax({
        		type:'post',
        		dataType:'json',
        		url:'dbexample_delDBExample.do?eid='+eid,
        		success:function(msg){
        			removeMask();
        			if (msg.result == 1) {
        				$td.parent().remove();
					} else {
						alert("删除失败!");
		                return false;
					}
        		}
        	});
        });

        $("[name='table_space']").unbind().live("click",function(){
        	var $td=$(this).parent();
        	var exampleid=$td.attr("exampleid");
        	var iscomplete=$td.attr("iscomplete");
	        	$.dialog({
	    			id:'addtablespace',
	    			title:'表空间管理',
	    			width: '900px',
	    			height: '500px',
	    			content: 'url:dbexample_getTableSpaceList.do?dbExampleId='+exampleid + "&isComplete=" + iscomplete
		    	});
         });
        
          $("[name='example_user']").unbind().live("click",function(){
        		var id=$(this).attr("value");
        		var $td=$(this).parent();
        		var iscomplete=$td.attr("iscomplete");
	        	$.dialog({
	    			id:'addexampleuser',
	    			title:'实例用户管理',
	    			width: '900px',
	    			height: '500px',
	    			content: 'url:dbexample_getExampleUserList.do?id='+id+"&isComplete="+iscomplete
		    	});
         });        
        
	});
	
    function saveExample(theform,oper){
    	mask('正在保存数据库实例,请稍后....','0.7','0px');
       	 $.ajax({
            type: "GET",
            url: "dbexample_saveExample.do?"+theform+"&oper="+oper,
            dataType: "json",
            success : function(data){
				removeMask();
	            $("#theForm").submit();
              }
          });
       }
</script>
</head>
<body>
<s:form action="dbexample_listDBExample.do" method="post" cssStyle="theForm" id="theForm">
<div class="scrollbody">
	<div class="query">
		<div class="title"><%=getImageTag(request,"query-icon.gif")%></div>
	</div>
	<div class="box on">
	    <div class="query-form">
			<table width="100%" class="querytable" border="0">
				<tr>
					<td class="til">
						实例名称:
					</td>
					<td>
					    <s:textfield name="dbExampleObj.example_name" id="examplename"></s:textfield>
					</td>
					<td class="til">
						是否创建:
					</td>
					<td>
						<s:select list="#{'-1':'--请选择--','0':'否','1':'是'}" name="dbExampleObj.iscomplete" id="iscomplete"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="btns">
						<div>
							<input type="button" class="thickbox btn-style02" value="查询" id="search"/>
							<input type="button" class="btn-style02" value="重置" id="reset"/>
						</div>
					</td>
				</tr>
			</table>
        </div><!--query-form end -->
        
	<div class="blue-wrap noborder" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
			<ul class="btns">
				<li>
					<input type="button" class="thickbox btn-style02" value="添加"  name="addorder"/>
				</li>
			    <li>
					<input type="button" class="thickbox btn-style02" value="执行"  name="orderdeploy"/>
				</li>
				<li>
					<input type="button" class="btn-style02-100" value="强制取消" name="qzcancel"/>
				</li>
				<!-- 
				<li>
					<input type="button" class="thickbox btn-style02" value="刷新" name="shuaxin"/>
				</li>
				
				<li>
					<input type="button" class="btn-style02-100" value="同步实例" name="syncexample"/>
				</li>
				 -->
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0" name="ordertable">
				<thead>
					<tr>
						<th>
							选择
						</th>
						<th onclick="sort(theTable,1,'string')">
							实例名称
						</th>
						<th onclick="sort(theTable,2,'string')">
							是否已创建
						</th>
						<th onclick="sort(theTable,3,'string')">
							IP地址
						</th>
						<th onclick="sort(theTable,4,'string')">
						 	数据库文件路径
						</th>
						<th onclick="sort(theTable,5,'string')">
							sys口令
						</th>
						<th onclick="sort(theTable,6,'string')">
							管理
						</th>
						<th>
							操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
	                  <tr>
					    <td>
							<input type="checkbox" name="checkboxid" exampleid='<s:property value="#theBean.id"/>' iscomplete='<s:property value="#theBean.iscomplete" />'/>
						</td>
						<td>
						   <s:property value="#theBean.example_name" default="-"/>
						</td>
						<td>
							<s:if test="#theBean.iscomplete==1">是</s:if>
							<s:else>
								否
							</s:else>
						</td>
						<td>
						   <s:property value="#theBean.example_ip" default="-"/>
						</td>
						<td>
							<s:property value="#theBean.data_file_path"/>
						</td>
						<td>
							<s:property value="#theBean.sys_pass"/>
						</td>
						<td exampleid='<s:property value="#theBean.id"/>' iscomplete='<s:property value="#theBean.iscomplete"/>'>
						   <a href="javascript:;" name="table_space" >表空间</a> <a href="javascript:;" name="example_user" value="<s:property value="#theBean.id"/>">用户</a>
						</td>
						<td exampleid='<s:property value="#theBean.id"/>'>
							<s:if test="#theBean.iscomplete==1">
								<a href="javascript:;" name="delexample" >删除</a>
							</s:if>
							<s:else>
								<a href="javascript:;" name="editexample" >编辑</a>&nbsp<a href="javascript:;" name="delexample" >删除</a>
							</s:else>
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
