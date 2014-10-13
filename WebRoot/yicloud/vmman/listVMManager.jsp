<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/link.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html:html locale="true">
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">
	
	$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});

	})

	function resetForm(theForm){
		theForm.NAME_ZH.value = '';
		theForm.NAME_EN.value = '';
		theForm.CODE.value = '';
		theForm.IP.value = '';
		theForm.TYPE.value = '0';
		theForm.SUB_TYPE.value = '0';
		theForm.VH_HOST.value = '0';
		theForm.BRAND.value = '0';
		theForm.MODEL.value = '';
		theForm.MEMORY.value = '';
		theForm.STORAGE.value = '';
		theForm.INTERFACE.value = '';
		theForm.AUXILIARY.value = '';
		theForm.REMARK.value = '';
	}

	function searchRequest() { 
		theForm.submit();
 	}
 	
	function addRequest() {
 	    theForm.action = 'addDevice.do' 
		theForm.submit();
	}
	function modRequest() { 
 	    var couterNum = 0;
 	    var checkboxids = document.getElementsByName("checkboxid");
 	    if(checkboxids!=null&&checkboxids.length>0){
 	    for(var i=0;i<checkboxids.length;i++){
 	      if(checkboxids[i].checked){
 	      couterNum = couterNum + 1 ;
 	      theForm.ID.value = checkboxids[i].value;
 	      }
 	    }
 	    theForm.flag.value = 1;
 	    }
 	    if(couterNum==0){
 	    alert("请勾选需要修改信息！");
 	    return false ;
 	    }else if(couterNum>1){
 	    alert("一次只能处理单条信息");
 	    return false ;
 	    }
 	    theForm.action = 'modDevice.do';
		theForm.submit();
 	}
 	function delRequest() {
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
 	    theForm.action = 'delDevice.do'  
		theForm.submit();
 	}
</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="yvm_listVMService" method="post" id="theForm" cssStyle="theForm">
	<s:hidden name="theForm.ID" id="ID"></s:hidden>
	<s:hidden name="theForm.flag" id="flag"></s:hidden>
		<div class="scrollbody">
			<div class="query">
				<div class="title"><%=getImageTag(request, "query-icon.gif")%></div>
			</div>
			<div class="box on">
				<div class="query-form">
					<table width="100%" class="querytable" border="0">
						<tr>
							<td class="til">
								中文名:
							</td>
							<td>
								<html:text name="theForm" property="NAME_ZH" styleClass="txt" />
							</td>
							<td class="til">
								英文名:
							</td>
							<td>
								<html:text name="theForm" property="NAME_EN" styleClass="txt" />
							</td>
							<td class="til">
								SN编号:
							</td>
							<td>
								<html:text name="theForm" property="CODE" styleClass="txt" />
							</td>
							<td class="til">
								内网IP:
							</td>
							<td>
								<html:text name="theForm" property="IP" styleClass="txt" />
							</td>
						</tr>
						<tr>
							<td class="til">
								设备类型:
							</td>
							<td>
								<html:select name="theForm" property="TYPE">
									<html:option value="0">-请选择-</html:option>
									<html:optionsCollection name="theForm" property="typeList"
										label="NAME" value="CODE" />
								</html:select>
							</td>
							<td class="til">
								设备子类型:
							</td>
							<td>
								<html:select name="theForm" property="SUB_TYPE">
									<html:option value="0">-请选择-</html:option>
									<html:optionsCollection name="theForm" property="subTypeList"
										label="NAME" value="CODE" />
								</html:select>
							</td>
							<td class="til">
								虚拟主机的宿主机ID:
							</td>
							<td>
								<html:select name="theForm" property="VH_HOST">
									<html:option value="0">-请选择-</html:option>
									<html:optionsCollection name="theForm" property="vhHostList"
										label="NAME_ZH" value="ID" />
								</html:select>
							</td>
							<td class="til">
								品牌:
							</td>
							<td>
								<html:select name="theForm" property="BRAND">
									<html:option value="0">-请选择-</html:option>
									<html:optionsCollection name="theForm" property="brandList"
										label="NAME" value="CODE" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td class="til">
								型号:
							</td>
							<td>
								<html:text name="theForm" property="MODEL" styleClass="txt" />
							</td>
							<td class="til">
								内存:
							</td>
							<td>
								<html:text name="theForm" property="MEMORY" styleClass="txt" />
							</td>
							<td class="til">
								存储空间:
							</td>
							<td>
								<html:text name="theForm" property="STORAGE" styleClass="txt" />
							</td>
							<td class="til">
								外围接口:
							</td>
							<td>
								<html:text name="theForm" property="INTERFACE" styleClass="txt" />
							</td>
						</tr>

						<tr>
							<td class="til">
								附属设备:
							</td>
							<td>
								<html:text name="theForm" property="AUXILIARY" styleClass="txt" />
							</td>
							<td class="til">
								备注:
							</td>
							<td>
								<html:text name="theForm" property="REMARK" styleClass="txt" />
							</td>
						</tr>
						<tr>
							<td colspan="8" class="btns">
								<div>
									<input type="button" class="thickbox btn-style02" value="查询"
										onclick="javascript:searchRequest()" />
									<input type="button" class="btn-style02" value="重置"
										onclick="javascript:resetForm(document.getElementById('theForm'))" />
								</div>
							</td>
						</tr>
					</table>
				</div>
				<!--query-form end -->

				<div class="blue-wrap noborder">
					<div class="table-head">
						<ul class="btns">
							<li>
								<input type="button" class="thickbox btn-style02" value="添加"
									onclick="addRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="修改"
									onclick="modRequest();return false;" />
							</li>
							<li>
								<input type="button" class="thickbox btn-style02" value="删除"
									onclick="delRequest();return false;" />
							</li>
						</ul>
						<jsp:include page="/inc/Pagination.jsp?formId=theForm" />
					</div>
					<div class="table-ct">
						<table width="100%" class="blue-table sorttable" border="0" id="theTable"
							cellspacing="0">
							<thead>
								<tr>
									<th>
										选择
									</th>
									<th onclick="sort(theTable,1,'string')">
										序号
									</th>
									<th onclick="sort(theTable,2,'string')">
										中文名
									</th>
									<th onclick="sort(theTable,3,'string')">
										英文名
									</th>
									<th onclick="sort(theTable,4,'string')">
										SN编号
									</th>
									<th onclick="sort(theTable,5,'string')">
										内网IP
									</th>
									<th onclick="sort(theTable,6,'string')">
										设备类型
									</th>
									<th onclick="sort(theTable,7,'string')">
										设备子类型
									</th>
									<th onclick="sort(theTable,8,'string')">
										虚拟主机的宿主机ID
									</th>
									<th onclick="sort(theTable,9,'string')">
										品牌
									</th>
									<th onclick="sort(theTable,10,'string')">
										型号
									</th>
									<th onclick="sort(theTable,11,'string')">
										内存
									</th>
									<th onclick="sort(theTable,12,'string')">
										存储空间
									</th>
									<th onclick="sort(theTable,13,'string')">
										外围接口
									</th>
									<th onclick="sort(theTable,14,'string')">
										附属设备
									</th>
									<th onclick="sort(theTable,15,'string')">
										备注
									</th>
								</tr>
							</thead>
							<tbody>
								<logic:present name="theForm" property="resultList">
									<logic:iterate id="theBean" name="theForm"
										property="resultList">
										<tr>
											<td>
												<input name="checkboxid" type="checkbox"
													value="<bean:write name="theBean" property="ID"/>" />
											</td>
											<td>
												<bean:write name="theBean" property="ID" />
											</td>
											<td>
												<bean:write name="theBean" property="NAME_ZH" />
											</td>
											<td>
												<bean:write name="theBean" property="NAME_EN" />
											</td>
											<td>
												<bean:write name="theBean" property="CODE" />
											</td>
											<td>
												<bean:write name="theBean" property="IP" />
											</td>

											<td>
												<bean:write name="theBean" property="TYPENAME" />
											</td>
											<td>
												<bean:write name="theBean" property="SUB_TYPENAME" />
											</td>
											<td>
												<bean:write name="theBean" property="VH_HOSTNAME" />
											</td>
											<td>
												<bean:write name="theBean" property="BRANDNAME" />
											</td>
											<td>
												<bean:write name="theBean" property="MODEL" />
											</td>

											<td>
												<bean:write name="theBean" property="MEMORY" />
											</td>
											<td>
												<bean:write name="theBean" property="STORAGE" />
											</td>
											<td>
												<bean:write name="theBean" property="INTERFACE" />
											</td>
											<td>
												<bean:write name="theBean" property="AUXILIARY" />
											</td>
											<td>
												<bean:write name="theBean" property="REMARK" />
											</td>

										</tr>
									</logic:iterate>
								</logic:present>
							</tbody>
						</table>
					</div>
				</div>
				<!--blue-wrap end -->
			</div>
			<!--box end -->
		</div>
	</s:form>
</body>
</html:html>
