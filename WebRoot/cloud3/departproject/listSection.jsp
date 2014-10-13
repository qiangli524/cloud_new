<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp" %>
<%@ include file="../../sxcloud/common/view.jsp" %>
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
    	$(function(){
            $("#addForm").click(function(){
        	currentEdit=$(this);
    		$.dialog({
    			id:'add',
    			title:'新增部门',
    			width: '450px',
    			height: '150px',
    			max: true,
    		    min: true,
    		    lock : true,
				content: 'url:section_editSection.do'
 				});
            });
              
              
            $("#modForm").click(function(){
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
	    			id:'edit',
	    			title:'修改部门信息',
	    			width: '450px',
	    			height: '150px',
	    			max: true,
	    		    min: true,
	    		    lock : true,
	    			//content: 'url:depart_editDepart.do?obj.id='+id
	    			content: 'url:section_editSection.do?obj.id='+id
	    		});
             });
              
            $("[name='detail']").click(function(){
	        	currentEdit=$(this);
	        	var section_id = currentEdit.attr("depart_id");
	    		$.dialog({
	    			id:'depart',
	    			title:'科室信息',
	    			width: '1000px',
	    			height: '500px',
	    			max: true,
	    		    min: true,
	    		    lock : true,
	    			//content: 'url:departproject_listProject.do?obj.DEPART_ID='+depart_id
	    			content: 'url:depart_listDepart.do?obj.parent_id='+section_id
	    			});
            });
            
            $("[name='use']").click(function(){
	        	currentEdit=$(this);
	        	var id = currentEdit.attr("depart_id");
	    		$.dialog({
	    			id:'sectionVm',
	    			title:'资源分配率',
	    			width: '375px',
	    			height: '390px',
	    			max: true,
	    		    min: true,
	    		    lock : true,
	    			//content: 'url:departproject_projectResourceRate.do?departId='+id
	    			content: 'url:section_sectionResourceRate.do?sectionId='+id
	    			});
	              });
	              
	          $("#deleteForm").click(function(){
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
					var url="section_deleteSection.do?obj.id="+id;
					if(confirm("确认要删除吗")){
					
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
	          });
	          $("#exportForm").click(function(){
	          		expertExcel();
	          });
	           $("#searchForm").click(function(){
	          		list();
	          });
	           $("#resetForm").click(function(){
	          		cleanup();
	          });
	          
           });   
		function expertExcel(){
			/* var url = "sectionAction_exportSectionExcel.do?"+$("#obj").serialize() */
			var url = "section_exportSectionExcel.do?obj.name=" + encodeURI(encodeURI($("#name").attr("value")))+"&obj.leaderName="+encodeURI(encodeURI($("#leader").attr("value")));
			obj.action = url;
			obj.submit();
		}        
        function list(){
        	obj.submit();
        }
        
		//删除部门
		function deleteTem(){
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
			var url="section_deleteSection.do?obj.id="+id;
			if(confirm("确认要删除吗")){
			
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
			obj.leaderName.value='';
		}
	//添加部门
	function createDepart(url){
	 $.ajax({
			  type:"POST",
              url:url,
              dataType:"json",
              async: true,
              cache: false,
	          success: function(msg){
                list();
              }
		});
	}
	//更新部门
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
                	list();
                }
              }
		});
	}
    </script>
</head>
<body class="pop-body scrollbody" onLoad="self.focus();document.obj.name.focus()">
    <s:form action="section_listSection.do" method="GET" id="obj" >
  			<h2 class="utt-1">部门管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix filtrate-area">
				<div class="filtrate-field">
        			<label class="vm">部门名称:</label>
					<s:textfield name="obj.name" id="name" cssClass="inpt-1 vm"></s:textfield> 
				</div>
				<div class="filtrate-field">
        			<label class="vm">部门负责人名称:</label>
        			<s:textfield name="obj.leaderName" id="leaderName"></s:textfield>
				</div>
				<div class="filtrate-field">
        			<span class="ubtn-1 mgl-20"><input type="button"  id="searchForm" onclick="javascript:list();"  value="查询" /></span>
					<span class="ubtn-2 mgl-20"><input type="button" id="resetForm" onclick = "javascript:cleanup();" value="重置" /></span>
				</div>
			</div>
			<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)"  id="addForm" >新增</a>
						<a class="icon-modify" href="javascript:void(0)"  id="modForm" >修改</a>
						<a class="icon-del" href="javascript:void(0)" id="deleteForm" >删除</a>
						<a class="icon-export" href="javascript:void(0)" id="exportForm" >导出</a>
			</div>
  	
	<!--query-form end -->
            <table width="100%" border="0" cellspacing="0" class="blue-table sorttable" id="theTable">
            	<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">部门名称</th>
						<th onclick="sort(theTable,2,'string')">部门负责人</th>
						<th onclick="sort(theTable,3,'string')">预分配CPU</th>
						<th onclick="sort(theTable,4,'string')">预分配内存</th>
						<th onclick="sort(theTable,5,'string')">预分配存储</th>
						<th onclick="sort(theTable,6,'string')">预分配IP</th>
						<th onclick="sort(theTable,7,'string')">分配率</th>
						<th onclick="sort(theTable,8,'string')">详细</th>
					</tr>
				</thead>
                <tbody>
                	<s:iterator value="resultList" id="theBean">
                		<tr>
                			<td><input type="checkbox" value="<s:property value="#theBean.id"/>"  id="id"  name="obj.id"/></td>
                			<td><s:property value="#theBean.name"/></td>
                			<td>
                			<s:if test="#theBean.leader==null || #theBean.leader==''">--</s:if>
                			<s:else>
                				<s:property value="#theBean.leaderName"/>
                			</s:else></td>
                			<td><s:property value="#theBean.cpu"/>核</td>
                			<td><fmt:formatNumber value="${(theBean.mem)/1024}" pattern="#,###.##" type="number"/>G</td>
                			<td><fmt:formatNumber value="${(theBean.store)/1024/1024}" pattern="#,###.##" type="number"/>T</td>
                			<td><s:property value="#theBean.ip_num"/>个</td>
                			<td><a name="use" href="javascript:void(0);" depart_id="<s:property value="#theBean.id"/>">分配率</a></td>
                			<td><a href="javascript:;" name="detail" depart_id="<s:property value="#theBean.id"/>" >详细</a></td>
                		</tr>
                	</s:iterator>
                </tbody>
            </table>
            <div class="pages">
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=obj" />
			</div>	
    </s:form>
</body>