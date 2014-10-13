<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>

<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/ui/ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>

<%@ page import="com.sitech.basd.sxcloud.rsmu.web.util.page.image.ImageUtil" %>
<%!
public String getImageTag(HttpServletRequest request, String path) {
	return ImageUtil.getImageTag(request, path);
}%>



  <head>
   
    <title></title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/js/checkbox.js"></script>
	<script type="text/javascript">
	function searchRequest() { 
		theForm.submit();
 	}
 	function list() { 
		theForm.submit();
 	}
 	function saveHostPool(url){
		 $.ajax({
			type:"POST",
            url:url,
            async: true,
            cache: false,
	        success: function(msg){
	          	list();
	        }
		});
	}
 	$(function(){ 
	    $("[name='add']").unbind().live("click",function(){
    		$.dialog({
    			id:'add',
    			title:'新增主机池',
    			width: '600px',
    			height: '200px',
    			max: true,
    		    min: true,
    			content: 'url:resourcepool_addHostPool.do'
    		});
         });
         
         $("[name='mod']").unbind().live("click",function(){
	    	var couterNum = 0;
	 	    var checkboxids = document.getElementsByName("checkboxid");
	 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      couterNum = couterNum + 1 ;
		 	      theForm.ID.value = checkboxids[i].value;
		 	      }
		 	    }
	 	    }
	 	    if(couterNum==0){
	 	    	alert("请勾选需要修改信息！");
	 	    	return false ;
	 	    }else if(couterNum>1){
	 	    	alert("一次只能处理单条信息");
	 	    	return false ;
	 	    }
    		$.dialog({
    			id:'mod',
    			title:'修改主机池',
    			width: '600px',
    			height: '200px',
    			max: true,
    		    min: true,
    			content: 'url:resourcepool_modHostPool.do?'+$("#theForm").serialize()
    		});
         });
         
         $("[name='del']").unbind().live("click",function(){
    		var couterNum = 0;
	 	    var checkboxids = document.getElementsByName("checkboxid");
	 	    if(checkboxids!=null&&checkboxids.length>0){
		 	    for(var i=0;i<checkboxids.length;i++){
		 	      if(checkboxids[i].checked){
		 	      	couterNum = couterNum + 1 ;
		 	      	theForm.ID.value = checkboxids[i].value;
		 	      }
		 	    }
	 	    }
	 	    if(couterNum==0){
	 	    	alert("请勾选需要删除功能信息！");
	 	    	return false ;
	 	    }else if(couterNum>1){
	 	    	alert("一次只能删除单条功能信息");
	 	    	return false ;
	 	    }
			if(confirm("确定要删除吗?")){
				$.ajax({
					type:"GET",
					url:"resourcepool_deleteHostPool.do?"+ $("#theForm").serialize(),
					dataType:"json",
					success :function(data){
						if (data.result == 1)
						{
							alert("删除主机池和主机的关联关系出错！"); 
							return false;
						} else if (data.result == 2) {
							alert("删除主机池出错！"); 
							return false;
						} else {
							alert("删除成功！");
				            $("#theForm").submit();
						}
					}
				});
			}
         });
         
     });          	
 	
 	function forPassId(ID){
 		$.dialog({
    			id:'listallhost',
    			title:'主机管理',
    			width: '900px',
    			height: '506px',
    			max: true,
    		    min: true,
    			content: 'url:resource_allHostList.do?hostID='+ID +'&flag=hostpool'
    	});
 	}
 	function addhost(id,pool_type){
 		$.dialog({
			id:id,
			title:'添加主机',
			width: '970px',
			height: '515px',
			content: 'url:resourcepool_host2pool.do?id='+id+'&flag=0'+'&pool_type='+ pool_type,
			button:[{name: '添加', focus: true, callback: function () {
					var ids = this.content.document.getElementsByName("checkboxid");
					var count = 0;
					var host_ids = '';
					var flag = 0;
					//page_ids = '';
					for(var i=0;i<ids.length;i++){
						if(ids[i].checked){
							count += 1;
							host_ids += ids[i].value + ',';
						}
						//page_ids += ids[i].value + "','";
					}
					if(count==0){
						alert("请选择要添加的主机!");
						return false;
					}
					$.getJSON('resourcepool_updatePoolId.do',{'time':new Date().toString(),'host_ids':host_ids,'pool_id':id,'flag':flag}, function(data){
						if(data.result ==1){
							alert("添加成功");
							theForm.submit();
						}else{
							alert("添加失败");
							theForm.submit();
						}
					});
				}}, {name: '取消'}]
			});
 	}
 	function delhost(id){
 		$.dialog({
			id:id,
			title:'删除主机',
			width: '970px',
			height: '515px',
			content: 'url:resourcepool_host2pool.do?id='+id+'&flag=1',
			button:[{name: '删除', focus: true, callback: function () {
					var ids = this.content.document.getElementsByName("checkboxid");
					var count = 0;
					var host_ids = '';
					var flag = 1;
					//page_ids = '';
					for(var i=0;i<ids.length;i++){
						if(ids[i].checked){
							count += 1;
							host_ids += ids[i].value + "','";
						}
						//page_ids += ids[i].value + "','";
					}
					if(count==0){
						alert("请选择要删除的主机!");
						return false;
					}
					$.getJSON('resourcepool_updatePoolId.do',{'time':new Date().toString(),'host_ids':host_ids,'pool_id':id,'flag':flag}, function(data){
						if(data.result ==1){
							alert("删除成功");
							theForm.submit();
						}else{
							alert("删除失败");
							theForm.submit();
						}
					});
				}}, {name: '取消'}]
			});
 	}
 	function resetForm() {
 	  theForm.NAME.value = "";
 	  theForm.POOL_TYPE.value = "";
 	}
	</script>

  </head>
	<style type="text/css">
		div.hidden{
		width:170px;
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
  <body  onLoad="self.focus();document.theForm.NAME.focus()" class="mainbody">
  <s:form action="resourcepool_allHostPoolList.do" id="theForm" method="post"
		cssClass="theForm">
		<s:hidden name="theForm.ID" id="ID"></s:hidden>
		<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<div class="pd-20 bgcolor-1">
			<div class="box on">
			<h2 class="utt-1">主机池</h2>
			<div class="bord-1 pd-10">
			<div class="clearfix mgt-10">
				<label class="vm">主机池名称:</label>
				<s:textfield name="theForm_NAME" cssClass="inpt-1" id="NAME"></s:textfield>
				<label class="mgl-20 vm">主机池类型:</label>
				<s:select id="POOL_TYPE" name="theForm.POOL_TYPE" list="#{'':'--请选择--','1':'物理池','2':'业务池'}" cssClass="select-1" ></s:select>
									<span class="ubtn-1 mgl-20"> <input type="button" value="查询"
										onclick = "javascript:searchRequest()"/>
									</span>
									<span class="ubtn-1 mgl-20"><input type="button"  value="重置"
										onclick="javascript:resetForm()" />
									</span>
 			</div>
				<div class="utt-2 mgt-20">
						<a class="icon-add" href="javascript:void(0)" name="add" />添加</a>
						<a class="icon-modify" href="javascript:void(0)" name="mod" />修改</a>
						<a class="icon-del" href="javascript:void(0)" name="del" />删除</a>
				</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										主机池名称
									</th>
									<th onclick="sort(theTable,2,'string')">
										主机池类型
									</th>
									<th onclick="sort(theTable,3,'int')">
										主机的个数
									</th>
									<th onclick="sort(theTable,4,'string')">
										主机池描述
									</th>
									<th onclick="sort(theTable,5,'date')">
										创建时间
									</th>
									<th>
										关联取消主机
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="theForm.LIST_POOL" id="theBean">
									<tr >
										<td>
											<input name="checkboxid" type="checkbox"
												value="<s:property value='#theBean.ID'/>"/>
										</td>
										<td>
											<s:property value="#theBean.NAME" />
										</td>
										<td>
											<s:if test="#theBean.POOL_TYPE==1">
								  				物理池
								  			</s:if>
								  			<s:elseif test="#theBean.POOL_TYPE==2">
								  				业务池
								  			</s:elseif>
										</td>
										<td>
										<s:if test="#theBean.shu!=0">
										<a href="#" onclick="return forPassId('<s:property value="#theBean.ID"/>');">
										<s:property value="#theBean.shu" />
										</a>
										</s:if>
										<s:else>
										<s:property value="#theBean.shu" />
										</s:else>
										</td>
										<td width="170"> 
								  			<div class="hidden" title='<s:property value="#theBean.POOL_DESC"/>'>
								  				<s:property value="#theBean.POOL_DESC"/>
								  			</div> 
								  		</td>
										<td>
											<s:property value="#theBean.INS_DATE" />
										</td>
										<td>
											<s:a href="javascript:;"  onclick="addhost('%{#theBean.ID}','%{#theBean.POOL_TYPE}');">关联主机</s:a>
											<s:a href="javascript:;"  onclick="delhost('%{#theBean.ID}');">取消关联主机</s:a>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<div class="pages mgb-10">
							<jsp:include page="/sxcloud/inc/Pagination.jsp?formId=theForm" />
						</div>
					</div>
				</div>
		</div>
	</s:form>
 </body>

