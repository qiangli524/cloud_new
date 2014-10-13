<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" %>
<%@ include file="/sxcloud/common/link.jsp"%>
<%@ include file="/sxcloud/common/taglib.jsp"%>
<%@ include file="/sxcloud/common/view.jsp"%>

<head>
    <title></title>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
    
    <script type="text/javascript">
    	
      	function searchRequest(){
			theForm.submit();
		}
		function clear(){
			theForm.name.value = '';
		}
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
			$("[name='member']").click(function(){
	        	currentEdit=$(this);
	        	var group_id = currentEdit.attr("value");
	    		$.dialog({
	    			id:'vdi',
	    			title:'成员设置',
	    			width: '600px',
	    			height: '350px',
	    			max: true,
	    		    min: true,
	    		    lock:true,
	    			content: 'url:scriptgroup_member.do?group_id='+group_id
	    			});
	              });
	         $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'添加脚本组信息',
    			width: '500px',
    			height: '350px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:scriptgroup_edit.do'
    			});
              });
              
            $("[name='mod']").click(function(){
        	currentEdit=$(this);
        	var id='';
     		var lenth=0;
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
    			id:'mod',
    			title:'修改脚本组信息',
    			width: '500px',
    			height: '350px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:scriptgroup_edit.do?id='+id
    			});
              });
              
              $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	var group_id = currentEdit.attr("group_id");
    		$.dialog({
    			id:'vdi',
    			title:'脚本文件',
    			width: '500px',
    			height: '350px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:scriptgroup_listMember.do?group_id='+group_id
    			});
              });
		  });
		  
		 function deleteGroup(){
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
			if(confirm('确定删除?')){
				theForm.action="scriptgroup_delete.do?id="+id;
				theForm.submit();
			}
		}     
	function selectMember(group_id,str){
		  url='scriptgroup_saveGroupMember.do?str='+str+"&group_id="+group_id;
		  $.ajax({
				      type: "get",
				      url: url,
					  async: false,
				      dataType: "json",
				      success : function(data){
					  }
			});
			searchRequest();
	}
    </script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">
    <s:form action="scriptgroup_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="scrollbody">
		<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">脚本组</h2>
	       	<div class="bord-1 pd-10">
				<div class="clearfix mgt-10">
					<label class="vm">组名称:</label>
					<s:textfield name="theForm.name" id="name" cssClass="inpt-1 vm"></s:textfield>
					<span class="ubtn-1 mgl-20"><input type = "button"  value = "查询" onclick="javascript:searchRequest();" /></span>
					<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" id="reset" /></span>
				</div>
				<div class="utt-2 mgt-10">
					<a class="icon-add" href="javascript:void(0)" name="add" >增加</a>
					<a class="icon-modify" href="javascript:void(0)" name="mod" >修改</a>
					<a class="icon-del" href="javascript:void(0)" onclick="deleteGroup();">删除</a>
				</div>
	            <table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
	            	<thead>
						<tr>
							<th >选择</th>
							<th onclick="sort(theTable,1,'string')">组名称</th>
							<th onclick="sort(theTable,2,'int')">脚本文件个数</th>
							<th onclick="sort(theTable,3,'string')">组描述</th>
							<th onclick="sort(theTable,4,'date')">插入时间</th>
							<th onclick="sort(theTable,5,'date')">更新时间</th>
							<th>添加脚本文件</th>
							
						</tr>
					</thead>
	                <tbody>
	                	<s:iterator value="theForm.resultList" id="theBean">
	                		<tr>
	                			<td><input type="checkbox" name="theForm.id" value="<s:property value="#theBean.id"/>"/></td>
	                			<td><s:property value="#theBean.name"/></td>
	                			<td>
	                				<s:if test="#theBean.num !=null && #theBean.num>0">
	                					<a href="javascript:;" name="detail" group_id='<s:property value="#theBean.id"/>'><s:property value="#theBean.num"/></a>
	                				</s:if>
	                				<s:else>
	                					0
	                				</s:else>
	                			</td>
	                			<td><s:property value="#theBean.description"/></td>
	                			<td><s:property value="#theBean.create_time"/></td>
	                			<td><s:property value="#theBean.update_time"/></td>
	                			<td><a href="#" value='<s:property value="#theBean.id"/>' name="member">添加脚本文件</a></td>
	                		</tr>
	                	</s:iterator>
	                </tbody>
	            </table>
	        </div>
	    </div>
	</div>
    </s:form>
</body>