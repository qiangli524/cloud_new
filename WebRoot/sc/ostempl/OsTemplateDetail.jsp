<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<head>
	<title></title>
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/tableSort/tableSort.js"></script>
	<script type="text/javascript">	
		function returnOsTemplate(){
			theForm.action = "osTemplate_showOsTemplate.do";
			theForm.submit();
		}
		function copyOsTemplate(id){
			theForm.action = "osTemplate_copyOsTemplate.do?theForm.id=" + id;
			theForm.submit();
		}
	</script>
</head>
<body class="pop-body scrollbody">
	<s:form action="osTemplate_showOsTemplate.do" method="post" id="theForm" cssStyle="theForm">
		<div class="pd-20 bgcolor-1">
			<div class="utt-1">模版明细</div>
			<div class="bord-1 pd-10">
				<div class="utt-2">基本信息</div>
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<tr align="left">
						<td class="til">模版名称</td>
						<td>
							<s:textfield name="theForm.templ_name" id="templ_name" 
								cssClass="inpt-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
						<td class="til">系统镜像</td>
						<td>
							<s:textfield name="theForm.os_version" id="os_version"
								cssClass="inpt-2" maxlength="30" disabled="true" ></s:textfield>
						</td>
					</tr>
					<tr align="left">
						<td class="til">备注</td>
						<td>
							<s:textarea name="theForm.templ_desc" id="templ_desc" cssClass="textarea-1"
								disabled="true" ></s:textarea>
						</td>
						<td  class="til">
						</td>
						<td>
						</td>
					</tr>
				</table>
			</div>
		</div>
		
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">Part</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">类型</th>
							<th width="20%" onclick="sort(theTable,2,'string')">DISK</th>   
							<th width="10%" onclick="sort(theTable,3,'string')">大小</th>
							<th width="10%" onclick="sort(theTable,4,'string')">自增长</th>
							<th width="20%" onclick="sort(theTable,5,'string')">备注</th>
							
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.partList" id="theBean">
						<tr>
							<td><s:property value="#theBean.part_name" /></td>
							<td><s:property value="#theBean.part_fstype" /></td>
							<td><s:property value="#theBean.part_ondisk" /></td>
							<td><s:property value="#theBean.part_size" />GB</td>
							<td><s:if test="#theBean.part_grow==1">是</s:if><s:else>否</s:else></td>
							<td><s:property value="#theBean.part_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="80%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">卷组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">VG名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">PV编号</th>   
							<th width="20%" onclick="sort(theTable,2,'string')">大小</th>
							<th width="20%" onclick="sort(theTable,3,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.vgList" id="theBean">
						<tr>
							<td><s:property value="#theBean.vg_name" /></td>
							<td><s:property value="#theBean.vg_pvno" /></td>
							<td><s:property value="#theBean.vg_pesize" />GB</td>
							<td><s:property value="#theBean.vg_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>				

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">文件系统</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">卷组</th>
							<th width="20%" onclick="sort(theTable,1,'string')">逻辑卷</th>
							<th width="20%" onclick="sort(theTable,2,'string')">文件系统</th>   
							<th width="20%" onclick="sort(theTable,3,'string')">大小</th>
							<th width="10%" onclick="sort(theTable,4,'string')">类型</th>
							<th width="10%" onclick="sort(theTable,5,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.otfsList" id="theBean">
						<tr>
							<td><s:property value="#theBean.volume_group" /></td>
							<td><s:property value="#theBean.logical_volume" /></td>
							<td><s:property value="#theBean.filesystem_name" /></td>
							<td><s:property value="#theBean.filesystem_size" />GB</td>
							<td><s:property value="#theBean.filesystem_type" /></td>
							<td><s:property value="#theBean.filesystem_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>		


		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="60%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="75%">用户组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">组ID</th>   
							<th width="20%" onclick="sort(theTable,1,'string')">组名</th>
							<th width="20%" onclick="sort(theTable,2,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.groupList" id="theBean">
						<tr>
							<td><s:property value="#theBean.group_id" /></td>
							<td><s:property value="#theBean.group_name" /></td>
							<td><s:property value="#theBean.group_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="100%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="100%">用户</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">ID</th>
							<th width="20%" onclick="sort(theTable,5,'string')">用户名</th>    
							<th width="20%" onclick="sort(theTable,2,'string')">主组</th>
							<th width="20%" onclick="sort(theTable,3,'string')">家目录</th>
							<th width="20%" onclick="sort(theTable,4,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.userList" id="theBean">
						<tr>
							<td><s:property value="#theBean.user_id" /></td>
							<td><s:property value="#theBean.user_name" /></td>
							<td><s:property value="#theBean.primary_group" /></td>
							<td><s:property value="#theBean.home_dir" /></td>
							<td><s:property value="#theBean.user_desc" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="60%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="50%">附加组</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">用户名</th>
							<th width="20%" onclick="sort(theTable,1,'string')">附加组</th>
							<th width="20%" onclick="sort(theTable,2,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.guList" id="theBean">
						<tr>
							<td><s:property value="#theBean.user_id" /></td>
							<td><s:property value="#theBean.group_id" /></td>
							<td><s:property value="#theBean.mask" /></td>
						</tr>
					</s:iterator>		  
					</tbody>
				</table>
			</div>
		</div>

		<div class="pd-20 bgcolor-1">
			<div class="bord-1 pd-10">
				<table id="theTable" width="60%" class="blue-table sorttable" border="0" cellspacing="0">
					<div class="utt-2" align="left" width="50%">软件包</div>
					<thead>
						<tr>
							<th width="20%" onclick="sort(theTable,0,'string')">软件名称</th>
							<th width="20%" onclick="sort(theTable,1,'string')">类别（区分不同类型的软件包）</th>
							<th width="20%" onclick="sort(theTable,2,'string')">备注</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="theForm.softList" id="item">
						<tr>
							<td><s:property value="#item.soft_name" /></td>
							<td><s:property value="#item.soft_type" /></td>
							<td><s:property value="#item.soft_desc" /></td>
						</tr>
					</s:iterator>							  
					</tbody>
				</table>
			</div>
		</div>
		
		<div class="btnCenter">
			<span class="ubtn-orange mgl-20"><input type="button"  value="复制模版" onclick="javascript:copyOsTemplate('<s:property value="theForm.id" />');"/></span>
			<span class="ubtn-green mgl-20"><input type="button"  value="返回" onclick="javascript:returnOsTemplate();"/></span>
		</div>
	</s:form>
</body>