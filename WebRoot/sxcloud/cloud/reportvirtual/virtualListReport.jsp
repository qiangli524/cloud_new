<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.sitech.basd.sxcloud.cloud.domain.reportvirtual.ReportTreeObj" %>
<%@ page import="com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualInfoObj" %>
<%@ page import="com.sitech.basd.sxcloud.cloud.domain.virtual.TbCloud2VirtualPoolObj" %>
<%@ include file="../../common/taglib.jsp"%>
<%@ include file="../../common/link_report.jsp"%>
<head>
	<title>虚拟服务器报表</title>
	<script type="text/javascript" src="sxcloud/js/FusionCharts.js">    </script>
<script type="text/javascript">
$(function(){
		$("#datepicker1").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
		$("#datepicker2").datepicker({showOn: 'button', buttonImage: '../cresources/default/images/date-icon.gif', buttonImageOnly: true});
	})
</script>
</head>
<body leftmargin="0" topmargin="0" class="pop-body scrollbody">
<s:form action="reportvir_saveGroupAuthority.do" method="post" cssClass="theForm" id="theForm">
<div nowrap="true" id="divTree1" style = "float:left;width: 17%"></div>

<script language="JavaScript">
<%
	ReportTreeObj [] reportTreeObjArray = (ReportTreeObj[])request.getAttribute("reportTreeObjArray");
%>
/**
 *开始构建树
 */
var tree1=new alai_tree_wx(divTree1)

var root=tree1.root
var n1=root.add("虚拟机报表","report.gif")
<%
int jj = 0 ;
String hy_id = "";
String tag = "";
if (reportTreeObjArray != null) {
for (int i = 0;i < reportTreeObjArray.length; i ++){
  String C_addr = reportTreeObjArray[i].getC_addr() ;
  String mName = reportTreeObjArray[i].getMName();
  String name =  mName + "(" + C_addr + ")";
%>
		  var n11 = n1.add("<%=name%>","host.gif")	
<%
 // TbCloud2VirtualInfoObj[] virs = reportTreeObjArray[i].getVirtualObjs();
		  TbCloud2VirtualPoolObj[] virs = reportTreeObjArray[i].getVirPoolObjs();
  int virHostCount = 0;
  if(virs != null && virs.length > 0){
    virHostCount = virs.length;
  }
  if(virHostCount > 0){
  for(int j = 0 ; j < virs.length ; j ++ ){
 // TbCloud2VirtualInfoObj tempvObj =  virs[j];
  TbCloud2VirtualPoolObj tempvObj = virs[j];
  String v_name = tempvObj.getVH_NAME() ;
  String tag1 = name + ">>" + v_name;
  if(jj==0){
   hy_id = tempvObj.getVH_ID();
   tag = name + ">>" + v_name;
  jj++;
  }
%>
var n111 = n11.add("<a href = 'reportvir_getCurrentReportView.do?hy_id=<%=tempvObj.getVH_ID()%>&tag=<%=tag1%>"+"' target='xxxx'>" + "<%=v_name%>" + "</a>","v_host.gif")
<%--          var n111 = n11.addLink("getCurrentReportView.do" + "?hy_id =<%=tempvObj.getVH_ID()%>","<%=v_name%>","","server.gif");--%>
<%
	}
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
<div style = "float:left;height: 100%;width: 80%; ">
<iframe src="reportvir_listVirtualReport.do?FUNID=report" name = "xxxx" width="840" height="800" frameborder="0"></iframe>
</div>             
</s:form>
</body>
