<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<head>
    <title></title>
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
    			id:'vdi',
    			title:'创建模板',
    			width: '600px',
    			height: '350px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:temman_add.do'
    			});
              });
              
              
             $("[name='mod']").click(function(){
        	currentEdit=$(this);
        	var id ='';
        	var lenth =0;
        	$('[name=theForm.id]:checkbox:checked').each(function(){
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
			id = $('[name=theForm.id]:checkbox:checked').val();
    		$.dialog({
    			id:'vdi',
    			title:'编辑模板',
    			width: '600px',
    			height: '350px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:temman_modify.do?obj.id='+id
    			});
              });
              
            $("[name='detail']").click(function(){
        	currentEdit=$(this);
        	var tem_code = currentEdit.attr("tem_code");
    		$.dialog({
    			id:'vdi',
    			title:'详细信息',
    			width: '600px',
    			height: '350px',
    			max: true,
    		    min: true,
    		    lock:true,
    			content: 'url:temman_listTemInfo.do?obj.id='+tem_code
    			});
              });
           });   
        
        function list(){
        	theForm.submit();
        }
        
      function searchRequest(){
			theForm.submit();
		}
	/*
		function modify(){
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
			theForm.action="temman_modify.do?id="+id;
			theForm.submit();
		}
	*/		
		function deleteTem(){
			var type =$('[name=theForm.id]:checkbox:checked').attr("types");
			var id ='';
        	var lenth =0;
        	$('[name=theForm.id]:checkbox:checked').each(function(){
        		lenth +=1;
        	 });
        	 if(lenth==0){
				alert('请先选择一项进行修改');
				return false;
			}else if(lenth>1){
				alert('只能选择一项进行修改');
				return false;
			}
			id = $('[name=theForm.id]:checkbox:checked').val();
			//alert(id);
			//alert(type);
			theForm.action="temman_deleteTem.do?obj.id="+id;
			theForm.submit();
		} 
		
		function cleanup(){
			theForm.name.value='';
			theForm.type.value='';
		}
	
	function createTem(url){
	///bar("1","正在创建模板")
	 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	theForm.action="temman_list.do";
					theForm.submit();
					///barEnd("1",msg);
                }
              }
		});
	}
	
	function updateTem(url){
		 $.ajax({
			  type:"GET",
              url:url,
              data:"text",
              async: true,
              cache: false,
	          success: function(msg){
                if(msg==-1){
                }else{
                	theForm.action="temman_list.do";
					theForm.submit();
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
<body class="pop-body scrollbody" onLoad="self.focus();document.theForm.name.focus()">
    <s:form action="temman_list.do" method="post" id="theForm" cssStyle="theForm">
  	<div class="query">
	<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
	</div>
	<div class="box on">
	<div class="query-form">
	<table width="100%" class="querytable" border="0">
		<tr>
			<td class="til">名称:</td>
			<td>
				<s:textfield name="obj.name" id="name"></s:textfield>
			</td>
			<td class="til">类型:</td>
			<td>
				<s:select list="#{'1':'Vmware模板','2':'Xen模板','3':'OVF模板'}" name="obj.type" id="type" headerKey="" headerValue="--请选择--"></s:select>
			</td>
		</tr>
		<tr>
			<td colspan="8" class="btns">
			<div><input type="button" class="thickbox btn-style02"
				value="查询" onclick="javascript:searchRequest();" />
				 <input type="button" class="btn-style02" value="重置"
				onclick = "javascript:cleanup();" />
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
                           <input type="button"  value="创建"  class="btn-style02" name="add"/>
                         <input type="button"  value="修改" name="mod" class="btn-style02"/>
                        <input type="button"  value="删除" name="" class="btn-style02"  onclick="javascript:deleteTem();"/>
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
						<th onclick="sort(theTable,3,'string')">是否公有</th>
						<th onclick="sort(theTable,4,'string')">位置</th>
						<th onclick="sort(theTable,5,'string')">备注</th>
						<th>详细</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>"  id="id" types= '<s:property value="#theBean.type"/>' /></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td><s:if test="#theBean.type==1">Vmware模板</s:if>
                				<s:elseif test="#theBean.type==2">Xen模板</s:elseif>
                				<s:else>OVF模板</s:else>
                			</td>
                			<td>
								<s:if test="#theBean.isPublic==0">公有</s:if>
								<s:else>私有</s:else>
                			</td>
                			<td>
                			<s:if test="#theBean.position==null || #theBean.position==''">--</s:if>
                			<s:else>
                				<s:property value="#theBean.position"/>
                			</s:else></td>
                			<td>
                			<s:if test="#theBean.remark==null || #theBean.remark==''">--</s:if>
                			<s:else>
                				<s:property value="#theBean.remark"/>
                			</s:else></td>
                			<td><a href="javascript:;" name="detail" tem_code="<s:property value="#theBean.id"/>" >详细</a></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
        </div>
        </div>
    </s:form>
</body>