<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.rsmu.domain.system.FunctionTreeObj" %>
<%@ include file="../common/taglib.jsp"%>
<%@ include file="../common/link.jsp"%>
<html:html locale="true">
<head>
	<title></title>
<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})
/*提交数据*/
function submitRequest(){
  document.getElementById("theForm").submit();
}
</script>
</head>
<body leftmargin="0" topmargin="0" class="pop-body scrollbody">
</body>
<s:form action="user_listUserInfo" method="post" cssStyle="theForm" id="theForm">
<%--原版本有，未发现其作用
<s:hidden name="theForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>" id="FUNCID"></s:hidden>--%>
<s:hidden name="theForm.FUNCIDS" id="FUNCIDS"></s:hidden>
<div nowrap="true" id="divTree1"></div>
<script language="JavaScript">
<%
	FunctionTreeObj [] funcTreeObjArray = (FunctionTreeObj[])request.getAttribute("functionTreeObjArray");
%>
/**
 *开始构建树
 */
var tree1=new alai_tree_wx(divTree1)

var root=tree1.root
var n1=root.add("模块展现")
<%
if (funcTreeObjArray != null) {
for (int i = 0;i < funcTreeObjArray.length; i ++){
  String mId = funcTreeObjArray[i].getMId();
  String mName = funcTreeObjArray[i].getMName();
  String strChecked = funcTreeObjArray[i].getStrChecked();
  int level = funcTreeObjArray[i].getLevel();
  if(level==1){
%>
          var n11 = tree1.addChkNode(n1,"<%=mName%>")
          n11.checkBox.value = "<%=mId%>"
          n11.checkBox.checked = <%=strChecked%>
<%
  }
  if(level==2){
%>
          var n111 = tree1.addChkNode(n11,"<%=mName%>")
          n111.checkBox.value = "<%=mId%>"
          n111.checkBox.checked = <%=strChecked%>
<%
  }
  if(level==3){
%>
          var n1111 = tree1.addChkNode(n111,"<%=mName%>")
          n1111.checkBox.value = "<%=mId%>"
          n1111.checkBox.checked = <%=strChecked%>
<%
  }
  if(level==4){
%>
          var n11111 = tree1.addChkNode(n1111,"<%=mName%>")
          n11111.checkBox.value = "<%=mId%>"
          n11111.checkBox.checked = <%=strChecked%>
<%
  }
  if(level==5){
%>
          var n111111 = tree1.addChkNode(n11111,"<%=mName%>")
          n111111.checkBox.value = "<%=mId%>"
          n111111.checkBox.checked = <%=strChecked%>
<%
  }
}
%>
<%}%>

tree1.target="mainMessage"
/*收缩目录树*/
tree1.expandToTier(2)
var isRun = false;
tree1.oncheck=function changeCheck(srcNode)
{
   if (isRun) return;
       isRun = true;
       changeChildrenCheck(srcNode);
       isRun =false;
}
</script>
<script language = "JavaScript">
//让所有父节点与当前节点的选择状态一致
function changeParentCheck(srcNode){
 nodeParent = srcNode.parent;
 if (nodeParent.isCheck){
   nodeParent.checkBox.checked = srcNode.checkBox.checked;
   changeParentCheck(nodeParent);
 }
}
//让所有子节点与当前节点的选择状态一致
function changeChildrenCheck(srcNode){
  if (srcNode.hasChild){
    var nodes = srcNode.children;
    alert
    var i=0;
    for (i=0;i<nodes.length;i++){
      var node = nodes[i];
      if (node.isCheck){
            node.checkBox.checked = srcNode.checkBox.checked;
            changeChildrenCheck(node);
      }
    }
  }else{
	 nodeParent = srcNode.parent;
//	 alert(nodeParent.isCheck);
	 if (nodeParent.isCheck && srcNode.checkBox.checked){
	   nodeParent.checkBox.checked = srcNode.checkBox.checked;
	   changeParentCheck(nodeParent);
	 } 
  }
}
</script>
<br/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  align="center">
    	<ul class="btns">		     
            <li><input type="button" class="btn-style02" value="返回" onclick="submitRequest();return false;" /></li>
     	</ul>
	</td>
</tr>
</table>              
</s:form>

</html:html>
