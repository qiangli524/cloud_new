<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>

<head>
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
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		$("#query").click(function(){
    			if($("#query-form").is(":visible")){
    				$("#query-form").slideUp("slow");
    			}else{
    				$("#query-form").slideDown("slow");
    			}
    		});
            $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'添加机框',
    			width: '600px',
    			height: '400px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:resource/mframe/addMFrame.jsp'       
    			});
              });
            $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("value");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '1000px',
    			height: '550px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:#.do?id='+id
    			});
              });
             
             $("[name='mod']").click(function(){
	        	currentEdit=$(this);
	        	var id ='';
	        	var lenth = 0;
	        	$('[name=theForm.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	if(id==null || id ==''){
					alert('请先选择一项进行修改');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行修改');
					return false;
				}
	    		$.dialog({
	    			id:'vdi',
	    			title:'修改配置文件',
	    			width: '600px',
	    			height: '400px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:mFrameAction_mod.do?id='+id
	    		});
	         });
	         $("#reset").click(function(){
	         	clear();
	         });
           });
      function searchRequest(){
			theForm.submit();
		}
		function deleteConfig(){
			var id='';
     		var lenth=0;
			$('[name=theForm.id]:checkbox:checked').each(function(){
        		id +=$(this).val();
        		lenth +=1;
        	 });
        	if(id==null || id ==''){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			theForm.action="mFrameAction_delete.do?id="+id;
			theForm.submit();
		}
		function clear(){
			$("#name").attr("value","");
			$("#status").attr("value","-1");
		}
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="mFrameAction_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="query" id="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form" id="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">机框名称:</td>
			<td>
				<s:textfield name="theForm.frame_name" id="name"></s:textfield>
			</td>
			<td class="til">机框电源状态:</td>
			<td>
				<s:select list="#{'0':'打开','1':'关闭'}" name="theForm.frame_status" id="status" headerKey="-1" headerValue="--请选择--"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02" value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" value="重置" id="reset" />
			</div>
			</td>
		</tr>
	</table>
	</div>
	<!--query-form end -->
    <div class="blue-wrap noborder">
	<div class="table-head">
				<ul class="btns">
                    <li colspan="4" class="btnCenter">
                           <input type="button"  value="添加"  class="btn-style02" name="add"/>
                         <input type="button"  value="修改" name="mod" class="btn-style02"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:if(confirm('确定删除？'))deleteConfig();"/>
                    </li>
                </ul>
     <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
       <div class="table-ct">
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">机框编号</th>
						<th onclick="sort(theTable,2,'string')">机框名称</th>
						<th onclick="sort(theTable,3,'string')">机框状态</th>
						<th onclick="sort(theTable,4,'string')">ip地址</th>
						<th onclick="sort(theTable,5,'string')">所属厂商</th>
						<th onclick="sort(theTable,6,'string')" width="10%">机框位置</th>
						<th onclick="sort(theTable,7,'string')">描述</th>
						<th onclick="sort(theTable,8,'string')">上传者</th>
						<th onclick="sort(theTable,9,'string')">更新者</th>
						<th onclick="sort(theTable,10,'date')" width="10%">插入时间</th>
						<th onclick="sort(theTable,11,'date')" width="10%">更新时间</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="frameList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.frame_num"/></td>
                			<td><s:property value="#theBean.frame_name"/></td>
                			<td><s:if test="#theBean.frame_status==0">打开</s:if>
                				<s:elseif test="#theBean.frame_status==1">关闭</s:elseif>
                				<s:else>-</s:else>
                				<%-- <s:elseif test="#theBean.frame_status==2">2</s:elseif>
                				<s:elseif test="#theBean.frame_status==3">3</s:elseif> --%>
                			</td>
                			<td><s:property value="#theBean.ip"/></td>
                			<td align="center"><div class="hidden" title='<s:property value="#theBean.oem"/>'>
                				<s:property value="#theBean.oem" default="-"/></div></td>
                			<td><s:property value="#theBean.position" default="-"/></td>
                			<td><s:property value="#theBean.frame_desc" default="-"/></td>
                			<td><s:property value="#theBean.insert_user" default="-"/></td>
                			<td><s:property value="#theBean.update_user" default="-"/></td>
                			<td><s:property value="#theBean.insert_time" default="-"/></td>
                			<td><s:property value="#theBean.update_time" default="-"/></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>