<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
     <style type="text/css">
		.font-more{ width:150px;height:20px;line-height:20px;overflow: hidden;
					white-space: nowrap;
					display: block;
					-o-text-overflow: ellipsis; 
					text-overflow: ellipsis;}
	  </style>
    <link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script> 
   	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
   	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
	<script type="text/javascript">
    	$(function(){
              $("[name='add']").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'addtem',
    			title:'创建模板',
    			lock:true,
    			width: '700px',
    			height: '550px',
    			max: true,
    		    min: true,
    			content: 'url:temman_add.do'
    			});
              });
              
              
             $("[name='mod']").click(function(){
        	currentEdit=$(this);
        	var id ='';
        	var lenth =0;
        	$('[name=obj.id]:checkbox:checked').each(function(){
        		///id +=$(this).val();
        		lenth +=1;
        	 });
			if(lenth==0){
				alert('请先选择一项进行修改');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行修改');
				return false;
			}
			id = $('[name=obj.id]:checkbox:checked').val();
    		$.dialog({
    			id:'vdi',
    			lock:true,
    			title:'编辑模板',
    			width: '650px',
    			height: '400px',
    			max: true,
    		    min: true,
    			content: 'url:temman_modify.do?obj.id='+id
    			});
              });
              
            $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	var tem_code = currentEdit.attr("tem_code");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '650px',
    			height: '400px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:temman_listTemInfo.do?obj.id='+tem_code
    			});
              });
           });   
        
        function list(){
        	obj.submit();
        }
        
      function searchRequest(){
			obj.submit();
		}
		//删除模板
		function deleteTem(){
			var type =$('[name=obj.id]:checkbox:checked').attr("types");
			var id ='';
        	var lenth =0;
        	$('[name=obj.id]:checkbox:checked').each(function(){
        		lenth +=1;
        	 });
        	 if(lenth==0){
				alert('请先选择一项进行删除');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行删除');
				return false;
			}
			id = $('[name=obj.id]:checkbox:checked').val();
			var url="temman_deleteTem.do?obj.id="+id;
			if(confirm("确认要删除模板吗")){
			
			$.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	list();
                }
              }
		});
	}
} 
		
		function cleanup(){
			obj.name.value='';
			obj.type.value='';
		}
	
	function createTem(url){
	bar("12","正在创建模板，请稍后");
	 $.ajax({
			  type:"POST",
              url:url,
              dataType:"json",
              async: true,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                	bar("12",msg);
                }else{
                	bar("12",msg);
                	list();
                }
              }
		});
	}
	
	function updateTem(url){
		 $.ajax({
			  type:"POST",
              url:url,
              dataType:"json",
              async: true,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	obj.action="temman_list.do";
					obj.submit();
                }
              }
		});
	}
	
	function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    lock:true,
		    fixed: true,
		    max:false,
		    content:contents
		});
}

function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(25);
}
    </script>
    
</head>
<body onLoad="self.focus();document.obj.name.focus()">
	<div class="mainbody">
	    <s:form action="temman_list.do" method="GET" id="obj" >
	    <div class="pd-20 bgcolor-1">
			<h2 class="utt-1">模板管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
				<label class="vm">名称：</label>
				<s:textfield name="obj.name" cssClass="inpt-1 vm"
								id="name" maxlength="30"></s:textfield>
				<label class="mgl-20 vm">类型：</label>
				<s:select cssClass="select-1 vm" list="#{'1':'Vmware虚拟机模板','2':'Xen虚拟机模板','3':'OVF模板'}" name="obj.type" id="type" headerKey="" headerValue="--请选择--"></s:select>
				<span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:cleanup()" value="重置" /></span>
			</div>
			<div class="utt-2 mgt-20">
				<a class="icon-add" href="javascript:void(0)"  name="add">新增</a>
				<a class="icon-modify" href="javascript:void(0)"  name="mod">修改</a>
				<a class="icon-del" href="javascript:void(0)" onclick="javascript:deleteTem();return false;" >删除</a>
			</div>
	            <table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
	            	<thead>
						<tr>
							<th>选择</th>
							<th onclick="sort(theTable,1,'string')">名称</th>
							<th onclick="sort(theTable,2,'string')">类型</th>
							<th onclick="sort(theTable,3,'string')">数据中心</th>
							<th onclick="sort(theTable,3,'string')">是否公有</th>
							<th onclick="sort(theTable,4,'string')">是否为物理模板</th>
							<th onclick="sort(theTable,5,'int')">是否可用</th>
							<th onclick="sort(theTable,6,'string')">位置</th>
							<th onclick="sort(theTable,7,'string')">模板说明</th>
							<th onclick="sort(theTable,8,'string')">详细</th>
							
						</tr>
					</thead>
	                <tbody>
	                	<s:iterator value="resultList" id="theBean">
	                		<tr>
	                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>"  id="id" types= '<s:property value="#theBean.type"/>' name="obj.id"/></td>
	                			<td><s:property value="#theBean.name"/></td>
	                			<td><s:if test="#theBean.type==1">Vmware虚拟机模板</s:if>
	                				<s:elseif test="#theBean.type==2">Xen虚拟机模板</s:elseif>
	                				<s:else>OVF模板</s:else>
	                			</td>
	                			<td><s:property value="#theBean.dataCenterName"/></td>
	                			<td>
									<s:if test="#theBean.isPublic==0">公有</s:if>
									<s:else>私有</s:else>
	                			</td>
	                			<td><s:if test="#theBean.isPhysical==1" >是</s:if>
	                				<s:else>否</s:else>
	                			</td>
	                			<td>
	                				<s:if test="#theBean.usable==null || #theBean.usable==''||#theBean.usable==0">可用</s:if>
	                				<s:else>
	                				不可用
	                				</s:else>
	                			</td>
	                			<td>
	                			<s:if test="#theBean.position==null || #theBean.position==''">--</s:if>
	                			<s:else>
	                				<s:property value="#theBean.position"/>
	                			</s:else></td>
	                			<td align="center">
	                			<s:if test="#theBean.remark==null || #theBean.remark==''">--</s:if>
	                			<s:else>
	                				<span style="color: black;" class="font-more" title='<s:property value="#theBean.remark"/>'>
											<s:property value="#theBean.remark" />
								</span>
	                			</s:else>
								</td>
	                			<td><a href="javascript:;" name="detail" tem_code="<s:property value="#theBean.id"/>" >详细</a></td>
	                			
	                		</tr>
	                	</s:iterator>
	                </tbody>
	            </table>
		       <div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
						</div>	
			 </div>
	    </s:form>
    </div>
</body>