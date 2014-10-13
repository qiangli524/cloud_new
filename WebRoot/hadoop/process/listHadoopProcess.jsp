<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>
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
	    <style type="text/css">
  	    html,body,form
     {
      margin:0px;
      height:100%;
     }
  	</style>
<script type="text/javascript">
	$(function(){
		$(".query").click(function(){
			if($(".query-form").is(":visible")){
				$(".query-form").slideUp("slow");
			}else{
				$(".query-form").slideDown("slow");
			}
		});
		
		var example_id =$("#example_id").val();
		var hostIP = $("#hostIP").val();
		var type = $("#type").val();
		if(type==11){
			$("#addButton").show();
		}else{
			$("#addButton").hide();
		}
		
	 	$("#search").click(function(){
	 	   $("#theForm").submit();
	 	});
	
		$("#resert").click(function(){
		   $("input[type='text']").val("");
		   $("select").each(function(){
		      $(this).children("option:eq(0)").attr("selected",true);
		   });
		   $("#theForm").submit();
		});
		
		$(function(){
	      $("[name='all']").click(function(){
	   	 	var currentState=$(this).attr("checked");
	 	 	if(currentState){
	 	 		$("[name='checkboxid']").attr("checked",true);
	 	 	}else{
	 	 		$("[name='checkboxid']").attr("checked",false);
	      	}
	
	       });
		});
	
        $("[name='addprocess']").click(function(){
	  		$.dialog({
    			id:'add',
    			title:'添加新进程',
    			width: '510px',
    			height: '320px',
    		    lock:true,
    			content: 'url:treeprocess_addNewHadoopProcess.do?oper=add&example_id='+example_id+'&hostIP='+hostIP+'&type='+type
	    		});
        });
        
        $("[name='editProcess']").unbind().live("click",function(){
            var $td=$(this).parent();
            var processId=$td.siblings("[processid]").attr("processid");
	  		$.dialog({
    			id:'add',
    			title:'编辑进程',
    			width: '510px',
    			height: '320px',
    		    lock:true,
    			content: 'url:treeprocess_addNewHadoopProcess.do?oper=edit&processid='+processId
	    		});
        });
        
        $("[name='delProcess']").unbind().live("click",function(){
           if (confirm("确定要删除这个进程吗?") == true){
	           var $td=$(this).parent();
	           var processId=$td.siblings("[processid]").attr("processid");
	           mask('正在 删除进程....','0.5','0px');
	           $.ajax({
	            type: "POST",
	            url: "treeprocess_delProcess.do?processId="+processId,
	            dataType: "json",
	            data:{"processId":processId},
	            success : function(data){
		          removeMask();
	              if(data.result==0){
	            	  $("#theForm").submit();
	              }else{
	                 alert("删除失败!")
	              }
	              }
	          });
           }
        });
        
        $("[name='operprocess']").unbind().live("click",function(){
        	if($(":checkbox:checked").not("[name='all']").length==0){
        		alert("你好,请至少选择一项来进行操作.");
        		return false;
        	}
        	if (confirm("确定要进行这个操作吗?") == true){
	        	var processIds="";
	    		$(":checkbox:checked").not("[name='all']").each(function(index,data){
	    			processIds+=$(this).attr("scriptid")+",";
	    		});
	    		var oper=$(this).attr("oper");
	    		mask('正在发送请求,请稍后....','0.5','0px');
	            $.ajax({
		             type: "POST",
		             url: "treeprocess_operProcess.do",
		             dataType: "json",
		             data:{"processIds":processIds,"oper":oper},
		             success : function(data){
			           removeMask();
		               if(data.result==0){
			                $("#theForm").submit();
		                }else if(data.result==1){
		                   alert("启动进程已经在运行中,不能重复启动.");
		                }else if(data.result==2){
		                   alert("启动进程已经停止运行,不能重复停止.");
		                }else if(data.result==3){
		                   alert("启动进程正在启动中,不能重复启动.");
		                }else if(data.result==4){
		                   alert("启动进程正在停止运行中,不能重复停止.");
		                }else{
		            	    alert("启动失败.");
		               }
	               	 }
	           });
        	}
         });  
        
        
        $("[name='refresh']").click(function(){
        	$("#theForm").submit();
        });
	});
       
      function saveProcess(theForm,selectUsers,userips,oper,example_id2,type1){
    	  mask('正在编辑进程,请稍后....','0.5','0px');
       	 $.ajax({
            type: "GET",
            url: "treeprocess_saveHadoppProcess.do?"+theForm+"&selectUsers="+selectUsers+"&userips="+userips+"&oper="+oper+"&example_id="+example_id2+"&type1="+type1,
            dataType: "json",
            success : function(data){
	               removeMask();
	               $("#theForm").submit();
              }
          });
       	 
       }
      function searchRequest(){
    	  $("#theForm").submit();
      }
	  function resetForm(){
		  $("#PROCESS").attr("value","");
		  $("#PROCESS_KEY").attr("value","");
	  }
</script>
</head>
<body>
<s:form action="treeprocess_listHadoopProcess.do" method="post" cssStyle="theForm" id="theForm">
<s:hidden name="example_id" id="example_id"></s:hidden>
<s:hidden name="hostIP" id="hostIP"></s:hidden>
<s:hidden name="type" id="type"></s:hidden>
<div class="pd-20 bgcolor-1">
			<h2 class="utt-1">进程管理</h2>
			<div class="bord-1 pd-10">			
			<div class="clearfix mgt-10">
                 <label class="vm">进程名：</label>
						<input type="text" name="processObj.PROCESS" id="PROCESS" maxlength="100"></input>
                 <label class="vm">标识：</label>
						<input type="text" name="processObj.PROCESS_KEY" id="PROCESS_KEY" maxlength="100"></input>
                  <span class="ubtn-1 mgl-20"><input type="button" onclick="javascript:searchRequest()" value="查询" /></span>
				<span class="ubtn-2 mgl-20"><input type="button" onclick="javascript:resetForm(document.getElementById('theForm'))" value="重置" /></span>
			</div><!--query-form end -->
			
	<div class="utt-2 mgt-20">
				<a name="addprocess" class="icon-add" href="javascript:void(0)" >添加</a>
				<a name="operprocess" class="icon-set" href="javascript:void(0)">启动</a>
				<a name="operprocess" class="icon-del" href="javascript:void(0)" >停止</a>
				<a name="refresh" class="icon-release" href="javascript:void(0)">刷新</a>
			</div>
			
			
		<div class="table-ct">
			<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0" name="processtable">
				<thead>
					<tr>
						<th>选择</th>
						<th onclick="sort(theTable,1,'string')">
							进程名
						</th>
						<th onclick="sort(theTable,2,'string')">
							标识
						</th>
						<th onclick="sort(theTable,3,'int')">
							配置个数
						</th>
						<th onclick="sort(theTable,4,'int')">
							实际个数
						</th>
						<th onclick="sort(theTable,5,'string')">
							主机 / 用户
						</th>
						<th onclick="sort(theTable,6,'string')">
							运行状态
						</th>
						<th onclick="sort(theTable,7,'string')">
							启停状态
						</th>
						<th onclick="sort(theTable,8,'string')">
							启动  / 停止
						</th>
						<th>
						     操作
						</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="resultList" id="theBean">
	                  <tr>
					    <td processid='<s:property value="#theBean.ID"/>'>
							<input type="checkbox" name="checkboxid" scriptid='<s:property value="#theBean.ID"/>' />
						</td>
						<td>
							<s:property value="#theBean.PROCESS" default="无"/>
						</td>
						<td align="center">
						   <div class="hidden" title='<s:property value="#theBean.PROCESS_KEY" default="无"/>'>
               				   <s:property value="#theBean.PROCESS_KEY" default="无"/>
               			   </div>
						</td>
						<td>
							<s:property value="#theBean.PROCESS_COUNT" default="1"/>
						</td>
						<td>
						    <s:if test="#theBean.PROCESS_COUNT_ACTUAL==-1">
									<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />检测中
							</s:if>
							<s:else>
							      <s:property value="#theBean.PROCESS_COUNT_ACTUAL" default="1"/> 
							</s:else>
						</td>
						<td>
						    <s:property value="#theBean.IP" default="-"/> / <s:property value="#theBean.USERNAME" default="-"/>
						</td>
						<td>
					        <s:if test="#theBean.PROCESS_STATE==1"><span style="color: red">异常停止<span/></s:if>
						    <s:elseif test="#theBean.PROCESS_STATE==2"><span style="color: blue">运行良好<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==0">无</s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==3"><span style="color: red">启动失败<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==4"><span style="color: red">停止失败<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==5"><span style="color: red">非通过平台启动<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==6"><span style="color: red">实际个数与配置个数不符<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==7"><span style="color: red">服务器无法ping通<span/></s:elseif>
						    <s:elseif test="#theBean.PROCESS_STATE==8"><span style="color: red">服务器登录失败<span/></s:elseif>
						</td>
						<td>
							<span id="div<s:property value="#theBean.ID"/>" name="state">
							    <s:if test="#theBean.OPERATION==1">
							        <s:if test="#theBean.TAST_TYPE==0">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1"/>正在启动</s:if>
									<s:if test="#theBean.TAST_TYPE==1">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />正在启动</s:if>
								</s:if>
								<s:elseif test="#theBean.OPERATION==0">
								    <s:if test="#theBean.TAST_TYPE==0">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1"/>正在停止</s:if>
									<s:if test="#theBean.TAST_TYPE==1">
										<img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />正在停止</s:if>
								</s:elseif>
								<s:elseif test="#theBean.OPERATION==2">
								  <s:if test="#theBean.ISRUNNING==0">
								    <s:if test="#theBean.TAST_TYPE==2"><span state="2">已停止</span></s:if>
								  </s:if>
								  <s:elseif test="#theBean.ISRUNNING==1">
								    <s:if test="#theBean.TAST_TYPE==2"><span state="2">运行中</span></s:if>
								  </s:elseif>
								  <s:elseif test="#theBean.ISRUNNING==2">
								    <s:if test="#theBean.TAST_TYPE==2"><span state="4">部分启动</span></s:if>
								  </s:elseif>
								  <s:elseif test="#theBean.ISRUNNING==3">
								    <s:if test="#theBean.TAST_TYPE==2">
								      <img src="<%=request.getContextPath()%>/sxcloud/images/ajax-loader.gif" width="15" height="18" state="1" />检测中
								    </s:if>
								  </s:elseif>
								</s:elseif>
							</span>
						</td>
						<td align="center">
               			   <div class="hidden" title='<s:property value="#theBean.START_SCRIPT" default="-"/> / <s:property value="#theBean.STOP_SCRIPT" default="-"/>'>
               				  <s:property value="#theBean.START_SCRIPT" default="-"/> / <s:property value="#theBean.STOP_SCRIPT" default="-"/>
               			   </div>
						</td>
                        <td>
							<a href="javascript:;" name="editProcess">编辑</a>&nbsp;&nbsp;&nbsp;<a href="javascript:;" name="delProcess">删除</a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
             <div class="pages">
		<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
			</div>	
			
		</div>
		
	</div>
    </div>

</s:form>
</body>
