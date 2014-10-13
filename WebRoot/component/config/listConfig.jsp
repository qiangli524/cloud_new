<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>

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
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    <script type="text/javascript">
    	$(function(){
	    		$("#query").click(function(){
	    			if($("#query-form").is(":visible")){
	    				$("#query-form").slideUp("slow");
	    			}else{
	    				$("#query-form").slideDown("slow");
	    			}
	    		});
	    		$("#reset").click(function(){
	    			clear();
	    		});
              $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'添加配置文件',
    			width: '500px',
    			height: '400px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:configfile_add.do'
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
    			content: 'url:configfile_listConfigContent.do?id='+id
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
    			width: '500px',
    			height: '400px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:configfile_mod.do?id='+id
    			});
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
			if(confirm('确定删除？')){
				theForm.action="configfile_delete.do?id="+id;
				theForm.submit();	
			}
		}
		
		function clear(){
			$("#name").attr("value","");
			$("#type").attr("value","-1");
		}
		
		function list(){
			theForm.submit();
		}
		
    </script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();">
    <s:form action="configfile_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="scrollbody">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">配置文件列表</h2>
	       	<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
					<label class="vm">配置文件名称:</label>
					<s:textfield name="theForm.name" id="name" cssClass="inpt-1 vm"></s:textfield>
					<label class="vm">类型:</label>
					<s:select list="#{'0':'xml','1':'properties','2':'其他'}" name="theForm.type" id="type" headerKey="-1" headerValue="--请选择--"  cssClass="select-1 vm"></s:select>
					<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" onclick="javascript:searchRequest();" /></span>
					<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="reset" /></span>
				</div>
                <div class="utt-2 mgt-10">
					<a class="icon-add" href="javascript:void(0)" name="add" >增加</a>
					<a class="icon-modify" href="javascript:void(0)" name="mod" >修改</a>
					<a class="icon-del" href="javascript:void(0)" onclick="javascript:deleteConfig();">删除</a>
				</div>
	            <table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	            	<thead>
						<tr>
							<th>选择</th>
							<th onclick="sort(theTable,1,'string')">配置文件名称</th>
							<th onclick="sort(theTable,2,'string')">主机/用户名</th>
							<!-- 
							<th>用户名</th>
							 -->
							
							<th onclick="sort(theTable,3,'string')">类型</th>
							<th onclick="sort(theTable,4,'string')">类别</th>
							<th onclick="sort(theTable,5,'string')" width="10%">路径</th>
							<th onclick="sort(theTable,6,'string')">描述</th>
							<th onclick="sort(theTable,7,'string')">上传人</th>
							<th onclick="sort(theTable,8,'string')">更新人</th>
							<th onclick="sort(theTable,9,'date')" width="10%">插入时间</th>
							<th onclick="sort(theTable,10,'date')" width="10%">更新时间</th>
							
						</tr>
					</thead>
	                <tbody>
	                	<s:iterator value="theForm.resultList" id="theBean">
	                		<tr>
	                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>" name="theForm.id" id="id"/></td>
	                			<td><s:property value="#theBean.name"/></td>
	                			<td><s:property value="#theBean.ip"/>/<s:property value="#theBean.username"/></td>
	                			<!--  
	                			<td><s:property value="#theBean.username"/></td>
	                			-->
	                			
	                			<td><s:if test="#theBean.type==0">xml</s:if>
	                				<s:elseif test="#theBean.type==1">properties</s:elseif>
	                				<s:else>其他</s:else>
	                			</td>
	                			<td>
									<s:if test="#theBean.category==0">通用</s:if>
									<s:elseif test="#theBean.category==1">部署使用</s:elseif>
									<s:else>其他</s:else>
	                			</td> 
	                			<td align="center"><div class="hidden" title='<s:property value="#theBean.path"/>'>
	                				<a name="detail" href="#" value='<s:property value="#theBean.id"/>'><s:property value="#theBean.path"/></a></div></td>
	                			<td><s:property value="#theBean.description"/></td>
	                			<td><s:property value="#theBean.upload_user"/></td>
	                			<td><s:property value="#theBean.mod_user"/></td>
	                			<td><s:property value="#theBean.insert_time"/></td>
	                			<td><s:property value="#theBean.update_time"/></td>
	                		</tr>
	                	</s:iterator>
	                </tbody>
	            </table>
            	<div class="pages mgb-10"><!-- 分页 -->
					<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
				</div>
            	</div>
			</div>
		</div>
    </s:form>
</body>