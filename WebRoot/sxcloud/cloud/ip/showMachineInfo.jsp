<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link.jsp"%>
<%@ include file="../../common/view.jsp"%>
<head>
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

</head>
<body>
<s:form action="" method="post" cssStyle="theForm" id="tempForm">

<div class="scrollbody">
	<div class="box on">
	<div class="blue-wrap nobtask">
		<div class="table-ct">
			<table width="100%" class="blue-table sorttable" btask="0" cellspacing="0" name="tasktable">
				<s:if test="tempForm.type==1">
					<tbody>
						<tr>
							<td>
								主机名称：
							</td>
							<td>
								<s:property value="tempForm.name"/>
							</td>
						</tr>
						<tr>
							<td>
								主机类型：
							</td>
							<td>
								<s:if test="tempForm.hosttype==1">
									IBM小型机
								</s:if>
								<s:elseif test="tempForm.hosttype==2">
									刀片
								</s:elseif>
							</td>
						</tr>
						<tr>
							<td>
								虚拟机个数：
							</td>
							<td>
                           		<s:if test="tempForm.num!=0">
									<a href="showvm_listvm.do?eq_id=<s:property value="tempForm.eq_id" />"><s:property value = "tempForm.num"/> 个</a>
								</s:if>
								<s:else>
									<s:property value = "#theBean.vm_num"/> 个
								</s:else>
										
							</td>
						</tr>
						<tr>
							<td>
								虚拟化类型：
							</td>
							<td>
								<s:if test="tempForm.vmtype == 3">
												XEN
								</s:if>
								<s:elseif test="tempForm.vmtype == 4">
												VMWARE	
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 1">
												PowerVM
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 0">
												无
								</s:elseif>
							</td>
						</tr>
					</tbody>
				</s:if>
				<s:else>
					<tbody>
						<tr>
							<td>
								虚拟机名称：
							</td>
							<td>
								<s:property value="tempForm.name"/>
							</td>
						</tr>
						<tr>
							<td>
								虚拟化类型：
							</td>
							<td>
								<s:if test="tempForm.vmtype == 0">
												kvm
								</s:if>
								<s:elseif test="tempForm.vmtype == 1">
												VMWARE	
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 2">
												IBM
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 3">
												xen
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 4">
												template
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 5">
												vmwareImage
								</s:elseif>
								<s:elseif test="tempForm.vmtype == 6">
												xen模板
								</s:elseif>
							</td>
						</tr>
						<tr>
							<td>
								应用个数：
							</td>
							<td>
								<s:property value="tempForm.num"/>
							</td>
						</tr>
						<tr>
							<td>
								操作系统：
							</td>
							<td>
								<s:property value="tempForm.system"/>
							</td>
						</tr>
					</tbody>
				</s:else>
			</table>
		</div>
	</div>
    </div>
</div>
</s:form>
</body>
