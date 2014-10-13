<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
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
//不要再这里面加弹出框的按钮，这样会导致此页面无法使用
	function getId(){
	      		var id='';
	     		var lenth=0;
				$('[name=dbExampleUserObj.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	 if(id==null || id ==''){
					alert('请先选择一项进行操作');
					return '0';
				}else if(lenth>1){
					alert('只能选择一项进行操作');
					return '0';
				}
	      	return id;
      }
    //请按照其他方法进行引入
	$(function(){
		if($("#iscomplete").val() == 0)
        {
			document.getElementById("executeButton").style.display="none";
			document.getElementById("lock").style.display="none";
			document.getElementById("unlock").style.display="none";
		}
		//其他用户类型请自己定义else 并请使用jquery控制页面元素的展示
		 var api = frameElement.api;
		 var w = api.opener;
		 
		 $("#search").click(function(){
			 $("#theForm").submit();
		 });
		 
		 $("#reset").click(function(){
			 $("#ename").val("");
			 $("#lockflag").val("-1");
		 });
		 
		 $("[name='viewpower']").click(function(){
			 var id =$(this).attr("value");
			 w.$.dialog({
			      id:'viewpower',
			      title:'查看用户权限',
			      width: '500px',
			      height: '300px',
			      max: true,
			      min: true,
			      content: 'url:dbexample_listExampleUserPower.do?exampleUserId='+id+'&caozuo=view',
	     	  });
		 });
		 
	      $("[name='addpower']").click(function(){
	          var id =$(this).attr("value");
	     	  w.$.dialog({
			      id:'vdi',
			      title:'添加用户权限',
			      width: '500px',
			      height: '300px',
			      max: true,
			      min: true,
			      content: 'url:dbexample_listExampleUserPower.do?exampleUserId='+id+'&caozuo=addp'
	     	  });
	       });
		 
		$("[name='add']").click(function(){
			var id = $("#example_id").attr("value");
	    		w.$.dialog({
	    			id:'add',
	    			title:'添加用户',
	    			width: '500px',
	    			height: '300px',
	    			max: true,
	    		    min: true,
	    			content: 'url:dbexample_addExampleUser.do?id='+id
	    			});
	           });
	     $("[name='mod']").click(function(){
	        var id =$(this).attr("value");
	    	w.$.dialog({
	    		id:'vdi',
	    		title:'修改用户',
	    		width: '500px',
	    		height: '300px',
	    		max: true,
	    		min: true,
	    		content: 'url:dbexample_modExampleUser.do?id='+id
	    	});
	      });
	       $("[name='unlock']").click(function(){
	      		var id='';
	     		var lenth=0;
				$('[name=dbExampleUserObj.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	 if(id==null || id ==''){
					alert('请先选择一项进行操作');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行操作');
					return false;
				}
        	var is_lock = 1;
        	if(!getId){
	       		return false;
	       	}
        	mask('正在解锁用户,请稍后....','0.7','0px');
	    	$.ajax({
		         type: "get",
		         url: "dbexample_saveExampleUser.do?id="+id+"&is_lock="+is_lock,
		         dataType: "json",
		         success : function(data){
			       list();
			       removeMask();
		        }
			});
	      });
	      $("[name='lock']").click(function(){
	       		var id='';
	     		var lenth=0;
				$('[name=dbExampleUserObj.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	 if(id==null || id ==''){
					alert('请先选择一项进行操作');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行操作');
					return false;
				}
        	 var is_lock = 2;
        	 mask('正在锁定用户,请稍后....','0.7','0px');
        	 $.ajax({
		         type: "get",
		         url: "dbexample_getARecord.do?id="+id,
		         dataType: "json",
				 cache: false,
		         success : function(data){
			       list();
			       removeMask();
			       if(data.result == 2){
			       		alert("用户未创建，不能锁定");
			       }else{
				       $.ajax({
				         type: "get",
				         url: "dbexample_saveExampleUser.do?id="+id+"&is_lock="+is_lock,
				         dataType: "json",
				         success : function(data){
					      removeMask();
					      alert("用户锁定成功");
			        	 }
					  });
			       }
		        }
			});
	      });
	      $("[name='is_submit']").click(function(){
	       	var id='';
	     		var lenth=0;
				$('[name=dbExampleUserObj.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	 if(id==null || id ==''){
					alert('请先选择一项进行操作');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行操作');
					return false;
				}
	       	var example_id = $("#exampleUserId").val();
        	var is_submit = 1;
        	 mask('正在创建用户,请稍后....','0.7','0px');
				       $.ajax({
				         type: "get",
				         url: "dbexample_saveExampleUser.do?id="+id+"&is_submit="+is_submit,
				         dataType: "json",
				         success : function(data){
					      removeMask(); 
					      alert("用户创建成功");  
					      list();
			        	 }
					  }); 
					     
	      });
	      $("[name='cancel']").click(function(){
	       		var id='';
	     		var lenth=0;
				$('[name=dbExampleUserObj.id]:checkbox:checked').each(function(){
	        		id +=$(this).val();
	        		lenth +=1;
	        	 });
	        	 if(id==null || id ==''){
					alert('请先选择一项进行操作');
					return false;
				}else if(lenth>1){
					alert('只能选择一项进行操作');
					return false;
				}
        	 var is_submit = 2;
	    	$.ajax({
		         type: "get",
		         url: "dbexample_saveExampleUser.do?id="+id+"&is_submit="+is_submit,
		         dataType: "json",
		         success : function(data){
			        list();
			        alert("取消成功!");
		        }
			});
	      });
	      $("[name='del']").click(function(){
	      	var id='';
	      	id = $(this).attr("value");
	      	mask('正在删除用户,请稍后....','0.7','0px');
			$.ajax({
		         type: "get",
		         url: "dbexample_delExampleUser.do?id="+id,
		         dataType: "json",
		         success : function(data){
			         removeMask();
			         list();
		        }
			});
	      });
	    });
      function searchRequest(){
			theForm.submit();
		}
		function clear(){
			theForm.dbExampleUserObj.example_username.value = '';
			theForm.dbExampleUserObj.is_lock.value = '';
		}
		
		function list(){
			$("#theForm").submit();
		}

		function addPower(powerid,exampleUserId){
			mask('正在添加用户权限,请稍后....','0.7','0px');
			  $.ajax({
			           type:"get",
			           url:"dbexample_addExampleUserPower.do?id="+powerid+"&exampleUserId="+exampleUserId,
			           dataType:"json",
			           success :function(data){
			        	   removeMask();
			        	   if (data.result == -1) {
								alert("添加权限失败");
						   } else{
							   alert("添加权限成功");
						   }
			           }
			  });
			}
		
</script>
</head>
<body>
<s:form action="dbexample_getExampleUserList.do" method="post" cssStyle="theForm" id="theForm" name="theForm">
<s:hidden name="exampleUserId" id="exampleUserId"></s:hidden>
<s:hidden name="dbExampleId" id="dbExampleId"/>
<s:hidden name='isComplete' id='iscomplete'/>
<div class="scrollbody">
	<div class="box on">
<%--		<div class="query-form">--%>
<%--			<table width="100%" class="querytable" border="0">--%>
<%--				<tr>--%>
<%--					<td class="til">--%>
<%--						用户名称:--%>
<%--					</td>--%>
<%--					<td>--%>
<%--					    <s:textfield name="dbExampleUserObj.example_username" id="ename"></s:textfield>--%>
<%--					</td>--%>
<%--					<td class="til">--%>
<%--						用户状态:--%>
<%--					</td>--%>
<%--					<td>--%>
<%--					  <s:select name="dbExampleUserObj.is_lock" list="#{'1':'未锁定','2':'锁定'}"  headerKey="-1" headerValue="-请选择-" id="lockflag"></s:select>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				<tr>--%>
<%--					<td colspan="8" class="btns">--%>
<%--						<div>--%>
<%--							<input type="button" class="thickbox btn-style02" value="查询" id="search"/>--%>
<%--							<input type="button" class="btn-style02" value="重置" id="reset"/>--%>
<%--						</div>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--			</table>--%>
<%--        </div>--%>
	<div class="blue-wrap nobtask" style="position:absolute;bottom:2px;top:2px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head">
				<ul class="btns">
					<li><input type="button" value="添加" class="btn-style02"  name="add" /></li>
					<li><input type="button" value="锁定" name="lock" class="btn-style02" id="lock" /></li>
					<li><input type="button" value="解锁" name="unlock" class="btn-style02" id="unlock"/></li>
					<li><input type="button" value="执行" id="executeButton" name="is_submit" class="btn-style02" /></li>
					<li><input type="button" class="btn-style02-100" value="强制取消" name="cancel" /></li>
				</ul>
				<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" /></div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th>
							选择
						</th>
						<th onclick="sort(theTable,1,'string')">
							用户名称
						</th>
						<th onclick="sort(theTable,2,'string')">
							密码
						</th>
						<th onclick="sort(theTable,3,'string')">
							是否锁定
						</th>
						<th onclick="sort(theTable,4,'string')">
							是否已创建
						</th>
						<th onclick="sort(theTable,5,'string')">
						          操作
						</th>
						<th onclick="sort(theTable,6,'string')">
							权限
						</th>
					</tr>
				</thead>
				<tbody>
				<input type="hidden" id="example_id" name="dbExampleUserObj.example_id" value="<s:property value='dbExampleUserObj.example_id'/>"/>
					<s:iterator value="dbExampleUserList" id="theBean">
	                  <tr>
					    <td is_lock="<s:property value='#theBean.is_lock'/>" is_submit="<s:property value='#theBean.is_submit'/>"> 
							<input type="checkbox" name="dbExampleUserObj.id" value="<s:property value='#theBean.id'/>"/>
						</td>
						<td>
							<s:property value="#theBean.example_username" default="-"/>
						</td>
						<td>
                            <s:property value="#theBean.example_password" default="-"/>
						</td>
						<td>
						    <s:if test="#theBean.is_lock==1">未锁定</s:if>
						    <s:elseif test="#theBean.is_lock==2">已锁定</s:elseif>
						    <s:else>-</s:else>
						</td>
						<td>
						    <s:if test="#theBean.is_submit==1">是</s:if>
						    <s:elseif test="#theBean.is_submit==2">否</s:elseif>
						    <s:else>-</s:else>
						</td>
						<td>
						    <a href="javascript:;" style="color: blue;" name="mod" value="<s:property value='#theBean.id'/>" >编辑</a>&nbsp;&nbsp;<a href="javascript:;" style="color: blue;" name="del" value="<s:property value='#theBean.id'/>">删除</a>
						</td>
						<td>
							<s:if test="#theBean.is_submit==2">
								---- ----
							</s:if>
							<s:elseif test="#theBean.is_submit==1">
								<s:if test="#theBean.is_lock==1">
		    						<a href="javascript:;" style="color: blue;" name="addpower" value="<s:property value='#theBean.id'/>" >添加</a>
		    						<a href="javascript:;" style="color: blue;" name="viewpower" value="<s:property value='#theBean.id'/>" >查看</a>
								</s:if>
								<s:elseif test="#theBean.is_lock==2">
		    						<a href="javascript:;" style="color: blue;" name="viewpower" value="<s:property value='#theBean.id'/>" >查看</a>
								</s:elseif>
							</s:elseif>
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
