<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp" %>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    	$(function(){
		 $("[name='add_vm']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'添加虚拟机DRS组',
    			width: '800px',
    			height: '480px',
    			max: true,
    		    min: true,
    			content: 'url:cluster_addDRSGroup.do?type='+0
    			});
              });
              
            $("[name='add_host']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'vdi',
    			title:'添加主机DRS组',
    			width: '800px',
    			height: '480px',
    			max: true,
    		    min: true,
    			content: 'url:cluster_addDRSGroup.do?type='+1
    			});
              });
              
            $("[name='edit']").click(function(){
        	currentEdit=$(this);
        	var group_id =$('[name=theForm.group_id]:checkbox:checked').val();
        	if(group_id==null || group_id=="" || group_id ==undefined){
        		alert("请先选择一项进行编辑");
        		return false;
        	}
        	var type = $('[name=theForm.group_id]:checkbox:checked').attr("types");
    		$.dialog({
    			id:'vdi',
    			title:'编辑DRS组成员',
    			width: '800px',
    			height: '480px',
    			max: true,
    		    min: true,
    			content: 'url:cluster_listGroupMember.do?group_id='+group_id+"&type="+type
    			});
              });
              
          });
          
        function delGroup(){
        		var str = "";
        		var name="";
        		var host_type = "";
        		var vm_type = "";
        		var type = "";
        		var group_id =$('[name=theForm.group_id]:checkbox:checked').val();
        		if(group_id==null || group_id=="" || group_id ==undefined){
        			alert("请先选择一项或多项，再进行删除");
        			return false;
        		}
        	 $('[name=theForm.group_id]:checkbox:checked').each(function(){
        	 	str +=$(this).val()+",";
        	 	name +=$(this).attr("names")+",";
        	 	code=$(this).attr("code");
        	 	type=$(this).attr("types");
        	 	if(type==0){
        	 		vm_type=type;
        	 	}else{
        	 		host_type= type;
        	 	}
        	 });
        	 if(host_type !="" && vm_type!=""){
        	 	alert("只能选择一种类型的DRS组进行操作");
        	 	return false;
        	 }
        	 if (confirm("确定删除DRS组吗?")) {
        	 bar(name,"正在删除DRS组");
         	url = "cluster_delGroup.do?group_id="+str+"&code="+code+"&type="+type+"&name="+encodeURI(encodeURI(name));
		  	$.getJSON(url,{"time":new Date().toString()},function(data){
		  		if(data.responseCode==1){
		  			 barEnd(name,"成功删除DRS组");
		  			list();
		  		}else{
		  			 barEnd(name,"无法删除DRS组");
		  		}
			});
        }
        }
        
        function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
	}
        
        
        function list(){
        	theForm.action="cluster_listGroup.do";
        	theForm.submit();
        }
    </script>
</head>
<body class="pop-body scrollbody">
    <s:form action="cluster_listGroup.do" method="post" id="theForm" cssStyle="theForm">
  
    <div class="blue-wrap noborder">
	<div class="table-head">
				<ul class="btns">
                    <li colspan="4" class="btnCenter">
                        <input type="button" class="btn-style02-100"  value="添加虚拟机组" name="add_vm"/>
                        <input type="button"  value="添加主机组" name="add_host" class="btn-style02-100" />
                        <input type="button"  value="删除" name="del_host" class="btn-style02"  onclick="javascript:delGroup();"/>
                        <input type="button"  value="编辑" name="edit" class="btn-style02"/>
                    </li>
                </ul>
     <jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
       <div class="table-ct">
            <table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
            	<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">名称</th>
						<th onclick="sort(theTable,2,'string')">类型</th>
						<th onclick="sort(theTable,3,'string')">所属集群</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="theForm.groupList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.group_id"/>" name="theForm.group_id" types="<s:property value="#theBean.type"/>" code="<s:property value="#theBean.c_uuid"/>" names="<s:property value="#theBean.group_name"/>"/></td>
                			<td><s:property value="#theBean.group_name"/></td>
                			<td><s:if test="#theBean.type==0">虚拟机组</s:if>
                				<s:else>主机组</s:else>
                			</td>
                			<td><s:property value="#theBean.name"/>
                			</td>
                			<!--  
                			<td><a href="javascript:" name="showDetail" group_id='<s:property value="#theBean.group_id"/>' type='<s:property value="#theBean.type"/>'/>查看组成员</td>
                			-->
                		</tr>
                	</s:iterator>
                </tbody>
                
            </table>
        </div>
        </div>
    </s:form>
</body>