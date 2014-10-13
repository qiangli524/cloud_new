<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<%@ include file="../../sxcloud/common/view.jsp"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/common.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/sxcloud/cresources/default/css/framework.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/sxcloud/cjs/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

  function resetForm(){
  	theForm.name.value = '';
  }
  var api = frameElement.api;
	var w = api.opener;
  $(function(){
		 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:saveTem,
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		 
	
})
  
  function saveTem(){
	  var remark = $("#remark").val();
	  if(remark.length == 0){
			alert("请填写模板说明");
			$("#remark").focus();
			return false;
		}
  	var url ='temman_saveTem.do?'+$("#obj").serialize();
  	w.updateTem(url);
  }
</script>
</head>
<body>
<div class="pd-20 bgcolor-1">
         <div class="bord-1 pd-10">
<s:form action="" method="post" id="obj">
	<s:hidden name="obj.id" id="id"></s:hidden>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr align="left">
					<td class="til">模板名称</td>
			 		<td>
					<s:property value="obj.name"/>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">类型</td>
					<td>
					<s:select list="#{'1':'vmware模板','2':'xen模板','3':'OVF模板'}" name="obj.type" id="type" disabled="true" cssClass="selece-1"></s:select>
					</td>
				</tr> 
				<tr align="left">
					<td class="til">是否公有</td>
					<td>
					<!-- 
					<s:select list="#{'0':'公有','1':'私有'}" name="theForm.isPublic" id="isPublic"></s:select>
					 -->
					 <s:if test="obj.isPublic==0">
					 	<input type="radio" name="obj.isPublic"  value="0" checked="checked"/><label>公有</label>
					 	<input type="radio" name="obj.isPublic" value="1"/><label>私有</label>
					 </s:if>
					<s:else>
						<input type="radio" name="obj.isPublic"  value="0" /><label>公有</label>
					 	<input type="radio" name="obj.isPublic" value="1" checked="checked"/><label>私有</label>
					</s:else>
					</td>
				</tr>
				<tr align="left">
					<td class="til">是否可用</td>
					<td>
						<s:if test="obj.usable==0 || obj.usable==null || obj.usable=='' ">
						 	<input type="radio" name="obj.usable"  value="0" checked="checked"/><label>可用</label>
						 	<input type="radio" name="obj.usable" value="1"/><label>不可用</label>
						 </s:if>
						<s:else>
							<input type="radio" name="obj.usable"  value="0" /><label>可用</label>
						 	<input type="radio" name="obj.usable" value="1" checked="checked"/><label>不可用</label>
						</s:else>
					</td>
				</tr>
				<s:if test="obj.isPhysical==1">
				<tr align="left">
					<td class="til">CPU</td>
					<td>
						<s:textfield name="obj.cpu"  id="cpu"  cssClass="inpt-2"/>个
					</td>
				</tr> 
				<tr align="left">
					<td class="til">内存</td>
					<td>
						<s:textfield name="obj.mem" id="mem" cssClass="inpt-2"/>M
					</td>
				</tr> 
				<tr align="left">
					<td class="til">存储</td>
					<td>
						<s:textfield name="obj.store" id="store" cssClass="inpt-2"/>M
					</td>
				</tr> 
				<tr align="left">
					<td class="til">操作系统</td>
					<td>
						<s:property value="obj.system"/>
					</td>
				</tr> 
				</s:if>
				<tr align="left">
					<td class="til">用户名</td>
					<td>
					<s:textfield name="obj.username" id="username"  cssStyle="width:220px;   height:20px;" cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">密码</td>
					<td>
					<s:password name="obj.password" id="password"  cssStyle="width:220px;   height:20px;" showPassword="true"  cssClass="inpt-2"></s:password>
					</td>
				</tr>
				<tr align="left">
					<td class="til">位置</td>
					<td>
					<s:textfield name="obj.position" id="position"  cssStyle="width:220px;   height:20px;"  cssClass="inpt-2"></s:textfield>
					</td>
				</tr>
				<tr align="left">
					<td class="til">模板说明</td>
					<td>
					<s:textarea name="obj.remark" id="remark" cssStyle="box" cols="40" rows="4"  cssClass="textarea-1"></s:textarea>
					<br />
					<span>
						<font color="red">
							格式： 操作系统类型:操作系统版本,软件描述  ，如：
							<br/>RedHat Enterprise Linux:RHEL 6.1,mysql 5.5.20
							<br/>
							请严格遵守该格式！
						</font>
					</span>
					</td>
				</tr>  
			</table>
</s:form>
</div>
    </div>
</body>
</html>
