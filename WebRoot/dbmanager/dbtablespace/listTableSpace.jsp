<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
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
<script type="text/javascript">
 
 
	function resetForm(theForm){
		theForm.space_name.value =''; 
		theForm.is_execute.value ='-1';
	}
    
    function searchRequest() {
		theForm.submit();
 	} 
 	

 	var api = frameElement.api;
    var w = api.opener;
	$(function(){
        if($("#iscomplete").val() == 0)
        {
			document.getElementById("executeButton").style.display="none";
		}
	 
		$("[name='addtablespace']").unbind().live("click",function(){
		    var example_id = $("#example_id").val();
			w.$.dialog({
    			id:'add_tablespace',
    			title:'添加表空间',
    			width: '520px',
    			height: '300px',
    			lock:true,
    			content: 'url:dbexample_editTableSpace.do?oper=add&example_id='+example_id
	    	});
        });
        
        $("[name='edittablespace']").unbind().live("click",function(){
            $td=$(this).parent();
            var tablespaceid=$td.attr("tablespaceid");
   	  		w.$.dialog({
       			id:'edittablespace',
       			title:'编辑表空间',
       			width: '520px',
       			height: '300px',
       			lock:true,
       			content: 'url:dbexample_editTableSpace.do?oper=edit&tableSpaceId='+tablespaceid
   	    	});
        }); 
        
        $("[name='deltablespace']").unbind().live("click",function(){
            	$td=$(this).parent();
                var tablespaceid=$td.attr("tablespaceid"); 
                mask('正在删除表空间,请稍候....','0.7','0px');
				$.ajax({
					type:"GET",
					url:"dbexample_delTableSpace.do?tablespaceid="+ tablespaceid,
					dataType:"json",
					success :function(data){ 
					   removeMask();
					   if (data.result == 1)
					   {
					   	  alert("删除成功！");
					  	  $("#theForm").submit();
					   } else {
					       alert("删除失败！");
					   }
					}
				});
        });
        $("[name='excute']").unbind().live("click",function(){
                var couterNum = 0;
                var tablespaceid="";
		 	    var checkboxids = document.getElementsByName("checkboxid");
		 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      	couterNum = couterNum + 1 ;
		 	      	tablespaceid = checkboxids[i].value;
		 	      }
		 	    }
		 	    }
		 	    if(couterNum==0){
		 	    	alert("请勾选需要执行创建操作的表空间！");
		 	    	return false ;
		 	    }else if(couterNum>1){
		 	    	alert("一次只能选择一条信息");
		 	    	return false ;
		 	    }
            	var example_id = $("#example_id").val();
            	mask('正在创建表空间,请稍候....','0.7','0px');
				$.ajax({
					type:"POST",
					url:"dbexample_excuteCreateSpace.do?example_id="+ example_id + "&tablespaceid=" + tablespaceid,
					dataType:"json",
					success :function(data){
						removeMask();
						if (data.result == 1)
					   {
					   	  alert("创建成功！");
					  	  $("#theForm").submit();
					   }else if(data.result==11){
						   alert("已经执行完成,无法再次执行！");
						   return false;
					   }else {
					   	  alert("创建失败！");
					   }
					}
				});
        });
        
        $("[name='qzcancel']").click(function(){
       		 var couterNum = 0;
             var tablespaceid="";
		 	 var checkboxids = document.getElementsByName("checkboxid");
		 	 if(checkboxids!=null&&checkboxids.length>0){
		 	 	for(var i=0;i<checkboxids.length;i++){
		 	   		if(checkboxids[i].checked){
		 	      		couterNum = couterNum + 1 ;
		 	       		tablespaceid = checkboxids[i].value;
		 	    	}
		 	    }
		 	 }
		     
		     if(couterNum==0){
		 	    alert("请选择一条要取消创建状态的表空间信息！");
		 	    return false ;
		 	 } 
 
        	 $.ajax({
        		type:'post',
        		dataType:'json',
        		url:'dbexample_updateTableSpaceExecute.do?tableSpaceId='+tablespaceid+'&is_execute=2',
        		success:function(msg){
        			if (msg.result == 1) {
						alert("取消成功！");
						$("#theForm").submit();
					} else{
						alert("取消失败！");
					}
        		}
        	});
        });
     });
     
     function saveTableSpace(theform,oper,example_id,tablespaceid){
       	 $.ajax({
            type: "GET",
            url: "dbexample_saveTableSpace.do?oper=" + oper + "&dbExampleId=" + example_id + "&" + theform + "&tablespaceid=" + tablespaceid,
            dataType: "json",
            success : function(data){
				if (data.result >= 0)
				{
					alert("保存成功！");
	            	$("#theForm").submit();
	            } else {
	           		alert("保存失败！");
	            }
              }
          });
       }
</script>
</head>
<body onLoad="self.focus();document.theForm.space_name.focus()">
<s:form action="dbexample_getTableSpaceList.do" method="post" cssStyle="theForm" id="theForm"> 
<s:hidden name='dbExampleId' id='example_id'/>
<s:hidden name='isComplete' id='iscomplete'/>
<div class="scrollbody">
	<div class="box on">
		<div class="query-form">
			<table width="100%" class="querytable" border="0">
				<tr>
					<td class="til">
						表空间名称:
					</td>
					<td>
					    <s:textfield name="dbTableSpaceObj.space_name" id="space_name"></s:textfield>
					</td>
					<td class="til">
						是否创建:
					</td>
					<td>
						<s:select list="#{'-1':'--请选择--','1':'是','2':'否'}" name="dbTableSpaceObj.is_execute" id="is_execute"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="8" class="btns">
						<div>
							<input type="button" class="thickbox btn-style02" value="查询" onclick="javascript:searchRequest()"/>
							<input type="button" class="btn-style02" value="重置" onclick="javascript:resetForm(document.getElementById('theForm'))"/>
						</div>
					</td>
				</tr>
			</table>
        </div><!--query-form end -->	
	<div class="blue-wrap nobtask" style="position:absolute;bottom:2px;top:100px;left:5px;right:5px;overflow-y:auto;">
		<div class="table-head" id="oper_space"">
			<ul class="btns">
				<li>
					<input type="button" class="thickbox btn-style02" value="添加"  name="addtablespace"/>
				</li>
				<li> 
					<input type="button" class="thickbox btn-style02" id="executeButton" value="执行" name="excute"/> 
				</li>
				<li>
					<input type="button" class="btn-style02-100" value="强制取消" name="qzcancel"/>
				</li>
			</ul>
			<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
		</div>
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<thead>
					<tr>
						<th>
							选择
						</th>
						<th onclick="sort(theTable,1,'string')">
							表空间名称
						</th>
						<th onclick="sort(theTable,2,'string')">
							存储大小
						</th>
						<th onclick="sort(theTable,3,'string')">
							数据文件目录
						</th>
						<th onclick="sort(theTable,4,'string')">
							是否自动扩展
						</th>
						<th onclick="sort(theTable,5,'string')">
							是否已创建
						</th>
						<th onclick="sort(theTable,6,'string')">
						          操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="dbTableSpaceList" id="theBean">
	                  <tr>
					    <td> 
							<input type="checkbox" name="checkboxid" value="<s:property value="#theBean.id"/>"/>
						</td>
						<td>
							<s:property value="#theBean.space_name" default="-"/>
						</td>
						<td>
						    <s:property value="#theBean.space_size" default="-"/>M
						</td>
						<td>
						    <s:property value="#theBean.data_file_path" default="-"/>
						</td>
						<td>
							<s:if test="#theBean.is_expand==1">
				  				是
				  			</s:if>
				  			<s:elseif test="#theBean.is_expand==2">
				  				否
				  			</s:elseif>
						</td>
						<td>
						    <s:if test="#theBean.is_execute==1">
				  				是
				  			</s:if>
				  			<s:elseif test="#theBean.is_execute==2">
				  				否
				  			</s:elseif>
						</td>
						<td tablespaceid='<s:property value="#theBean.id"/>'>
							<s:if test="#theBean.is_expand==2 && #theBean.is_execute==1">
						    	<a href="javascript:;" style="color: blue;" name="deltablespace" >删除</a>
						    </s:if>
						    <s:else>
						    	<a href="javascript:;" style="color: blue;" name="edittablespace" >编辑</a>&nbsp;&nbsp;<a href="javascript:;" style="color: blue;" name="deltablespace" >删除</a>
							</s:else>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div><!--blue-wrap end -->
    </div><!--box end -->
</div>
</s:form>
</body>
