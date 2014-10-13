<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ include file="../../common/taglib.jsp"%> 
<%@ include file="../../common/view.jsp"%>
<html locale="true">
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/default.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/css/location_tj.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/njs/changeColor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/common.js"></script>

<script>
			function searchHost(c_id){
				theForm.c_id.value=c_id;
			    theForm.action = 'checkHostList.do' 
				theForm.submit();
			}
			function resetForm(theForm){
				theForm.r_id.value ='';
				theForm.c_type.value ='';
				theForm.c_name.value = '';
			}
			function delCubinet(c_id){
		     if(confirm("确定要删除该机柜吗！")==true){
		     	var url = "cubinet_adjustCubinet.do?c_id="+c_id+"&date="+new Date();
		     	mask('删除中...','0.7','0');
		     	$.ajax({
			         type: "get",
			         url: url,
			         dataType: "json",
			         success : function(data){
						if(data.flag == 1){//1代表存在主机关联，不能随便删除
							alert("存在关联主机,不能删除!");
		     				return false;
						}else{
						    var theForm = $("#theForm");
						    $.ajax({
								type:'get',
								url:"cubinet_delCubinet.do?c_id="+c_id+"&time="+new Date(),
								success:function(msg){
						  			list();
									removeMask();
						  		}
							});
						}
			        }
				});
		     }  
		  }
		  
		  function list() {
		   	  theForm.action="cubinet_getCubinetHostList.do";
		      theForm.submit();
		  } 
		  
		  $(function(){  
	          $("a[name='listHost']").click(function(){
	          		var cid= $(this).attr("cid");  
		 			$.dialog({
		 				id:'listHost',
		 				title:'查看主机',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
		 				content: 'url:cubinet_checkHostList.do?c_id=' + cid
		 	 		}); 
	 		   }); 
	 		   
	 		   $("a[name='modCubinet']").click(function(){
	          		var cid= $(this).attr("cid");  
		 			$.dialog({
		 				id:'modCubinet',
		 				title:'修改机柜',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
		 				content: 'url:cubinet_modCubinet.do?c_id=' + cid
		 	 		}); 
	 		   }); 
	 		  $("#addCubinet").click(function(){
	 		       $.dialog({
		 				id:'addCubinet',
		 				title:'增加机柜',
		 				width: '800px',
		 				height: '400px',
		 				lock:true,
		 				content: 'url:cubinet_addCubinet.do'
		 	 		}); 
	 		  })
 		   });
 		   function save(theForm){
	 		   	mask('保存中...','0.7','0');
	 		   	$.ajax({
						type:'post',
						url:"cubinet_saveCubinet.do",
						data:theForm,
						success:function(msg){
							list();
							removeMask();
						}
				});
 		   }
		 </script>
	</head>
	<body style="height: 1000px">
			<s:form action="cubinet_getCubinetHostList.do" method="post"
				 cssStyle="theForm" id="theForm">
				<s:hidden name="theForm.c_id" />
					<table width="100%" class="blue-table sorttable" border="0">
						<tr>
							<td>
								<a href="javascript:void(0);" id="addCubinet">增加</a>
							</td>
							<td width="20%">
								机柜类型：
								<s:select cssClass="select-1" headerKey="" headerValue="请选择" name="theForm.c_type" id="c_type" list="#{'1':'IBM机柜(Power)','2':'IBM机柜(小刀)','3':'HP机柜(小刀)','4':'磁带库机柜','5':'网络机柜','0':'其他机柜'}">
								</s:select>
							</td>
							<td width="">
								机柜名称:
								<s:textfield name="theForm.c_name" id="c_name"/>
							</td>
							<td width="">
								机柜所在房间:
								<s:select cssClass="select-1" headerKey="" headerValue="请选择" list="theForm.roomOptionList" listKey="r_id" listValue="r_name" name="theForm.r_id" id="r_id">
								</s:select>
							</td>
							<td width="">
								<span class="ubtn-1 mgl-20"><input type="button"  value="查询" onclick="javascript:searchRequest()" /></span>
								<span class="ubtn-2 mgl-20"><input type = "button"  value = "重置" onclick = "javascript:resetForm(document.getElementById('theForm'))" /></span>
								
							</td>
						</tr>
					</table>
				<!--主机列表 start -->

				<table>
					<tr>
						<td>
							<div>
								<div
									style="background: #82bbdc; border: 1px solid #d0d0d0; height: 26px; line-height: 28px; padding-left: 15px; margin-bottom: 8px;">
									IBM机柜 (Power)
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.ibm_powerlist">
											<li>
												<h1>
													<span class="tool_bar"> 
														<a cid='<s:property value="#theBean.c_id" />' title="查看主机" href="javascript:;" name="listHost"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/search.gif" width="10" height="10"/></a>
														<a  title="主机删除" href="javascript:delCubinet('<s:property value="#theBean.c_id"/>')"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a>
														<a cid='<s:property value="#theBean.c_id" />' href="javascript:;" name="modCubinet"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" width="10" height="10"/></a>
													</span> IBM Power(
													<s:text name="#theBean.c_id"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/images/ibm-power.jpg"
															width="70" height="75" />
													</div>
													<div class="right-c">
														<p>
															机柜名称:
															<s:property value="#theBean.c_name"/>
														</p>
														<p>
															机柜位置：
															<s:property value="#theBean.c_addr"/>
														</p>
														<p>
															机柜所在房间名称：
															<s:property value="#theBean.r_name"/>
														</p>
														<p>
															机柜描述:
															<s:property value="#theBean.c_desc"/>
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div
									style="background: #82bbdc; border: 1px solid #d0d0d0; height: 26px; line-height: 28px; padding-left: 15px; margin-bottom: 8px;">
									IBM机柜(小刀)
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.ibm_knifelist">
											<li>
												<h1>
													<span class="tool_bar"> 
														<a title="查看主机" cid='<s:property value="#theBean.c_id" />' href="javascript:;" name="listHost"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/search.gif" width="10" height="10"/></a>
														<a title="主机删除" href="javascript:delCubinet('<s:property value="#theBean.c_id"/>')"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a>
														<s:url var="theBean.ID" action="cubinet_modCubinet">
															<s:param name="c_id" value="#theBean.c_id"></s:param>
														</s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
													</span> IBM 小刀(
													<s:text name="#theBean.c_id"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/images/ibm-blade.jpg"
															width="70" height="75" />
													</div>
													<div class="right-c">
														<p>
															机柜名称:
															<s:text name="#theBean.c_name" />
														</p>
														<p>
															机柜位置：
															<s:text name="#theBean.c_addr"  />
														</p>
														<p>
															机柜所在房间名称：
															<s:text name="#theBean.r_name"  />
														</p>
														<p>
															机柜描述:
															<s:text name="#theBean.c_desc"  />
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div
									style="background: #82bbdc; border: 1px solid #d0d0d0; height: 26px; line-height: 28px; padding-left: 15px; margin-bottom: 8px;">
									HP机柜(小刀)
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.hp_knifelist">
											<li>
												<h1>
													<span class="tool_bar">
														<a title="查看主机" cid='<s:property value="#theBean.c_id" />' href="javascript:;" name="listHost"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/search.gif" width="10" height="10"/></a>
														<a title="主机删除"  href="javascript:delCubinet('<s:property value="#theBean.c_id"/>')"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a>
														<s:url var="theBean.ID" action="cubinet_modCubinet">
															<s:param name="c_id" value="#theBean.c_id"></s:param>
														</s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
													</span> HP 小刀(
													<s:text name="#theBean.c_id"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/images/hp.jpg"
															width="70" height="75" />
													</div>
													<div class="right-c">
														<p>
															机柜名称:
															<s:text name="#theBean.c_name" />
														</p>
														<p>
															机柜位置：
															<s:text name="#theBean.c_addr" />
														</p>
														<p>
															机柜所在房间名称：
															<s:text name="#theBean.r_name" />
														</p>
														<p>
															机柜描述:
															<s:text name="#theBean.c_desc" />
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div
									style="background: #82bbdc; border: 1px solid #d0d0d0; height: 26px; line-height: 28px; padding-left: 15px; margin-bottom: 8px;">
									磁带库机柜
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.tape_librarylist">
											<li>
												<h1>
													<span class="tool_bar">
														<a title="查看主机" cid='<s:property value="#theBean.c_id" />' href="javascript:;" name="listHost"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/search.gif" width="10" height="10"/></a>
														<a title="主机删除"  href="javascript:delCubinet('<s:property value="#theBean.c_id"/>')"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a>
														<s:url var="theBean.ID" action="cubinet_modCubinet">
															<s:param name="c_id" value="#theBean.c_id"></s:param>
														</s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
													</span> 磁带库(
													<s:text name="#theBean.c_id"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/images/icon-tapelib.jpg"
															width="70" height="75" />
													</div>
													<div class="right-c">
														<p>
															机柜名称:
															<s:text name="#theBean.c_name" />
														</p>
														<p>
															机柜位置：
															<s:text name="#theBean.c_addr" />
														</p>
														<p>
															机柜所在房间名称：
															<s:text name="#theBean.r_name"  />
														</p>
														<p>
															机柜描述:
															<s:text name="#theBean.c_desc"/>
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div
									style="background: #82bbdc; border: 1px solid #d0d0d0; height: 26px; line-height: 28px; padding-left: 15px; margin-bottom: 8px;">
									网络机柜
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.network_equlist">
											<li>
												<h1>
													<span class="tool_bar"> 
														<a title="查看主机"  cid='<s:property value="#theBean.c_id" />' href="javascript:;" name="listHost"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/search.gif" width="10" height="10"/></a>
														<a title="主机删除"  href="javascript:delCubinet('<s:property value="#theBean.c_id"/>')"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a>
														<s:url var="theBean.ID" action="cubinet_modCubinet">
															<s:param name="c_id" value="#theBean.c_id"></s:param>
														</s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
													</span> 网络机柜(
													<s:text name="#theBean.c_id"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/images/network.jpg"
															width="70" height="75" />
													</div>
													<div class="right-c">
														<p>
															机柜名称:
															<s:text name="#theBean.c_name" />
														</p>
														<p>
															机柜位置：
															<s:text name="#theBean.c_addr" />
														</p>
														<p>
															机柜所在房间名称：
															<s:text name="#theBean.r_name"  />
														</p>
														<p>
															机柜描述:
															<s:text name="#theBean.c_desc"  />
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div>
								<div
									style="background: #82bbdc; border: 1px solid #d0d0d0; height: 26px; line-height: 28px; padding-left: 15px; margin-bottom: 8px;">
									其他机柜
								</div>
								<ul class="compose-1">
										<s:iterator id="theBean" value="theForm.others_equlist">
											<li>
												<h1>
													<span class="tool_bar"> 
														<a title="查看主机"  cid='<s:property value="#theBean.c_id" />' href="javascript:;" name="listHost"><img src="<%=request.getContextPath() %>/sxcloud/images/alarm/search.gif" width="10" height="10"/></a>
														<a title="主机删除" href="javascript:delCubinet('<s:property value="#theBean.c_id"/>')"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-del.gif" alt="主机删除" width="10" height="10" /></a>
														<s:url var="theBean.ID" action="cubinet_modCubinet">
															<s:param name="c_id" value="#theBean.c_id"></s:param>
														</s:url>
														<s:a href="%{theBean.ID}"><img src="<%=request.getContextPath() %>/sxcloud/cjs/ui2/nresources/common/icon/icon-set.gif" alt="主机修改" width="10" height="10" /></s:a>
													</span> 其他设备(
													<s:text name="#theBean.c_id"  />
													)
												</h1>
												<div class="main">
													<div class="left-c">
														<img
															src="<%=request.getContextPath()%>/sxcloud/cjs/ui2/nresources/common/images/other.jpg"
															width="70" height="75" />
													</div>
													<div class="right-c">
														<p>
															机柜名称:
															<s:text name="#theBean.c_name" />
														</p>
														<p>
															机柜位置：
															<s:text name="#theBean.c_addr" />
														</p>
														<p>
															机柜所在房间名称：
															<s:text name="#theBean.r_name" />
														</p>
														<p>
															机柜描述:
															<s:text name="#theBean.c_desc" />
														</p>
													</div>
												</div>
											</li>
										</s:iterator>
								</ul>
							</div>
						</td>
					</tr>
				</table>







				<div class="clear"></div>
			</s:form>
			<script type="text/javascript">
   function searchRequest() {
   	  theForm.action="cubinet_getCubinetHostList.do";
      theForm.submit();
 	}

  function modCubinet(c_id)
  {
    theForm.c_id.value=c_id;
    theForm.action = 'modCubinet.do' 
	theForm.submit();
  }
  
  
</script>
	</body>
</html>

