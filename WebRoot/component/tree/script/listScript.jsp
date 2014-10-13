<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../../sxcloud/common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>


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
  	<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
  	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    var example_id = '<%=request.getAttribute("example_id")%>';
    var hostIP = '<%=request.getAttribute("hostIP")%>';
    var type = '<%=request.getAttribute("type")%>';
    
    	$(function(){
              $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'添加脚本',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    			content: 'url:deployscript_add.do?example_id='+example_id+'&hostIP='+hostIP+'&type='+type
    			});
              });
              
              
            $("[name='detail']").unbind().live("click",function(){
        	currentEdit=$(this);
        	var id = currentEdit.attr("value");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '950px',
    			height: '520px',
    			max: true,
    		    min: true,
    			content: 'url:deployscript_listContent.do?id='+id
    			});
              });
             
             $("[name='mod']").unbind().click(function(){
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
    			title:'修改脚本',
    			width: '500px',
    			height: '300px',
    			max: true,
    		    min: true,
    			content: 'url:deployscript_mod.do?id='+id+'&example_id='+example_id+'&type='+type
    			});
              });
              
              
             $("[name='execute']").unbind().live("click",function(){
        		currentEdit=$(this);
        		var script_id = $(this).attr("script");
        		var script_type = $(this).attr("script_type");
        		var script_grade= $(this).attr("script_grade");
        		var deploy_example= $(this).attr("deploy_example");
        		var url = 'deployscript_executeScript.do?id='+script_id+'&example_id='+
        		example_id+'&type='+type+'&script_type='+script_type+'&script_grade='+script_grade+"&deploy_example="+deploy_example;
        		 $.ajax({
			  		type:"GET",
              		url:url,
              		data:"text",
              		async: false,
             		 cache: false,
	          		success: function(msg){
                	if(msg==null){
                		alert("执行失败");
                	}else{
                		list();
                	}
             	 }
			});
         });
      });   
      function searchRequest(){
			theForm.submit();
		}
		function deleteScript(){
			$('[name=theForm.id]:checkbox:checked').each(function(){
			var  $aa = $(this);
			var id = $(this).val();
			var url = "deployscript_delete.do?id="+id+'&example_id='+example_id+'&type='+type;
			 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: false,
              cache: false,
	          success: function(msg){
                if(msg==null){
                }else{
                	list();
                	}
             	 }
			});
         });
	}
		
		function clear(){
			theForm.name.value = '';
			theForm.type.value = '';
		}
		
		function list(){
			theForm.submit();
		}
		
		
		function updatePage(){
			theForm.action="deployscript_list.do?id="+example_id+"&hostIP="+hostIP+"&type="+type;
			theForm.submit();
			
		}
    </script>
</head>
<body class="pop-body scrollbody" >
    <s:form action="" method="post" id="theForm" cssStyle="theForm">
    		<div class="utt-2">
				<a class="icon-add" href="javascript:void(0)"  name="add">新增</a>
				<a class="icon-modify" href="javascript:void(0)" name="mod"  >修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="javascript:deleteScript();" >删除</a>
			</div>
            <table id="theTable" width="100%" border="0" cellspacing="0" class="blue-table sorttable">
            	<thead>
					<tr>
						<th width="10%">选择</th>
						<th onclick="sort(theTable,1,'string')">脚本名称</th>
						<th onclick="sort(theTable,2,'string')">主机/用户名</th>
						<th onclick="sort(theTable,3,'string')">类型</th>
						<th onclick="sort(theTable,4,'string')">路径</th>
						<th onclick="sort(theTable,5,'string')">参数</th>
						<th onclick="sort(theTable,6,'string')">级别</th>
						<th onclick="sort(theTable,7,'int')">执行次数</th>
						<th onclick="sort(theTable,8,'string')" width="7%">执行脚本</th>
					</tr>
				</thead>
                <tbody id="table">
                	<s:iterator value="theForm.resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td><s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
                			<td>
			  					<s:if test="#theBean.type==0">通用</s:if>
			  					<s:elseif test="#theBean.type==1">启动</s:elseif>
			  					<s:elseif test="#theBean.type==2">停止</s:elseif>
			  					<s:elseif test="#theBean.type==3">备份</s:elseif>
			  					<s:else>其他</s:else>
			  				</td>
                			<td align="center"><div class="hidden" title='<s:property value="#theBean.path"/>'>
                			<a name="detail" href="javascript:;" value='<s:property value="#theBean.id"/>'>
                				<s:property value="#theBean.path"/></a></div></td>
                			<td align="center">
                				<div class="hidden" title='<s:property value="#theBean.params"/>'>
                					<s:property value="#theBean.params"/>
                				</div>
                			</td>
                			<td id="desc"><s:property value="#theBean.grade"/></td>
                			<td><s:property value="#theBean.count"/></td>
                			<td>
                				<a href="javascript:void(0)" script='<s:property value="#theBean.id" />' name="execute" script_type='<s:property value="#theBean.type" />' script_grade='<s:property value="#theBean.grade"/>' deploy_example='<s:property value="#theBean.example_id"/>'>
                					执行 
                				</a>
                			</td>
                			<!--  
                			<td><s:property value="#theBean.upload_user"/></td>
                			<td><s:property value="#theBean.mod_user"/></td>
                			<td><s:property value="#theBean.insert_time"/></td>
                			<td><s:property value="#theBean.update_time"/></td>
                			-->
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        <div class="pages mgb-10"><!-- 分页 -->
		  <jsp:include page="../../../sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
       </div>
    </s:form>
</body>