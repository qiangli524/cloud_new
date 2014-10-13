<%@ page  language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ include file="/common/taglib.jsp" %>
<html:html locale="true">
<head>  
<%@ include file="/common/link.jsp" %> 
<title><bean:message key="msg.jsp.title" arg0="�û�����"/></title>
<script language="JavaScript" src="/js/setday.js"></script>
<script language="javascript" src="/js/ClientFunc.js"></script>
<script language="javascript">
String.prototype.Trim  = function(){return this.replace(/^\s+|\s+$/g,"");}
	function BtnSave_OnClick(thisForm){
		if( confirm("ȷ��Ҫ������")  && checkValid(thisForm) ){
			  thisForm.action_type.value = "saveMyFeedBackInfo";
		 	  thisForm.submit();
		 
	      return true;
		}
		return false;
	}
	function BtnSelect_OnSelectAll(obj){
		if(obj != null && obj.length > 0 ){
           for(var i=0;i<obj.length;i++){
               obj[i].selected="true";
           }
        }
	}
	function BtnNew_OnClick(thisForm){
		thisForm.submit();
		return true;
	}

	function checkValid(obj){

		if (!checkEmpty(obj.TYPE_ID,'��������')) return false;
		if (!checkEmpty(obj.TITLE,'����')) return false;
	
		return true;
	}
	 function isdigit(s)
            {
            var r,re;
            re = /\d*/i;  
            r = s.match(re);
            return (r==s)?1:0;
            }
</script>

<script language="JavaScript">

	var xmlHttp;   
	var i=0         
	var a;
	var currentInfo = "";          
	var counter = 1;                
	var isReading = true;      
	var check;             
	function createXmlHttp() {
	    if (window.XMLHttpRequest) {
	       xmlHttp = new XMLHttpRequest();               
	    } else {
	       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); 
	    }
	}
	
	function getSelect(ID) {
	    createXmlHttp();
	 
	
	    xmlHttp.open("GET", "/common/GetLoginByDept.jsp?ID="+ID, false);     
	    xmlHttp.setRequestHeader("If-Modified-Since","0");  
	    xmlHttp.send(null);
	
	    if (xmlHttp.readyState == 4) {
	
	            var pageInfo = eval("("+xmlHttp.responseText+")");       
	            var SelectNode=document.getElementById("SENTTO_EMPLOYE");
	            SelectNode.length=0;
	            for(var o in pageInfo){
	              SelectNode.appendChild(createSelect(o,pageInfo[o]));
	              }
	              
	          }
	}
	
	function createSelect(value,text){
	  var opt=document.createElement("option");
	  opt.setAttribute("value",value);
	  opt.appendChild(document.createTextNode(text));
	
	  if(check == value){
	    opt.selected=true;
	  }
	  i=i+1;
	  return opt;
	}
</script>

<script language="JavaScript" src="/js/popcalendar.js"></script>
<script language="JavaScript">
	function ChoiceUser()
	{
	  if(document.theForm.TODOSTATUS.value == 1){
	    dept.style.display = 'block';
	    user.style.display = 'block';
	  }else{
	    dept.style.display = 'none';
	    user.style.display = 'none';
	  }
	  if(document.theForm.TODOSTATUS.value == 3){
	  finish.style.display = 'block';
	  }else{
	  finish.style.display = 'none';
	  }
	}
	function init(){
	   finish.style.display = 'none';
	}
	
	dateFormat='yyyy-mm-dd'
</script>

<%
   String stemploy=(String)request.getAttribute("stEmploy");
   String moduleId=(String)session.getAttribute("moduleId");
   String type=request.getParameter("type");
   type=String.valueOf(request.getAttribute("type"));
   if ( moduleId != null){
     if(!moduleId.equals("null"))
       naviURL=NaviDisplay.getNaviURLA(moduleId,false,"main");
}    

%>
</head>

<body>

<jsp:include page="/common/title.jsp" flush="true"/>
			<div id="frame">
		  <div class="tableheadbg"><img src="images/dot_tablehead.gif" width="13" height="13">������Ϣ�޸�<span class="darkblue"></span>
		  <font color="#FF0000">&nbsp; <logic:messagesPresent message="true">
		           <html:messages id="msg" message="true"> <%=msg%> </html:messages>
		   </logic:messagesPresent> </font>
  		</div>
	<table width="100%" height="83" border="0" cellpadding="0"
		cellspacing="0" class="input_bg">
<html:form action="/saveMyFeedBackInfo" method="post" styleId="theForm">
  <bean:define  id="theForm" name="publishFeedBackForm" />
  <html:hidden property="action_type" value=""/>
  <html:hidden name="theForm" property="ID" /> 
  
  <tr>
    <td width="15%" class="input_bg">��������: <font color="#FF0000"></font> ��</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="TYPE_ID"  styleClass="input"/></td> 
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">����: <font color="#FF0000"></font> ��</td>
    <td width="85%" class="input_bg" colspan="3"><html:text property="TITLE"  styleClass="input"/></td> 
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">������Ϣ: <font color="#FF0000"></font> ��</td>
    <td width="85%" class="input_bg" colspan="3"><html:textarea property="DF_INFO"  cols="30" rows="2"  styleClass="input"/></td> 
  </tr>
  
  <tr>
    <td width="15%" class="input_bg">����ʱ��: <font color="#FF0000"></font> ��</td>
    <td width="85%" class="input_bg" colspan="3">
      <html:text styleClass="button" name ="theForm" property="SUBMIT_TIME"  readonly="true" size="10"/>
	 </td> 
  </tr>
  <%if( "null".equals(type) || type==null||type.equals("")){ %>
  <tr id="dept">
		<td class="input_bg">
			<font color="#FF0000">&nbsp;</font>ѡ������˲���:
		</td>
		<td class="input_bg" colspan="4">
			<div id="mastab11">
				<div class="masborder">
					<div id="mas_bg5">
						<div nowrap="true" id="divTree1"></div>
					</div>
				</div>
			</div>
		</td>
  </tr>
  <%} %>
  <tr>
		<td class="input_bg">
			<font color="#FF0000">&nbsp;</font>������Ϣ�����ˣ�
		</td>
		<td class="input_bg">
			<html:select name="theForm" property="SENTTO_EMPLOYE">
				<html:option value="<%=stemploy%>"></html:option>
			</html:select>
		</td>
  </tr>

  
 <tr>
    <td class="input_bg">��Ч��</td>
    <td colspan="3" class="input_bg">
    <html:select name="theForm" property="ENABLE"> 
      <html:option value="1">��Ч</html:option>
     	<html:option value="0">��Ч</html:option>
      
    </html:select>
    </td>
    </tr>
</table>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr  >
    <td  >
      <div align="center">
  <input type="image" src="images/sc_button_9_11.gif"   onclick="BtnSave_OnClick(document.theForm);return false;"/>
&nbsp;&nbsp;
  <input type="image" src="images/sc_button_9_2.gif"   onclick="void(document.theForm.reset());return false;"/>
   
    </div></td></tr>
</table>

</div>

<script language="JavaScript">
<%
	List treeList = (List)request.getAttribute("treeList");
%>
var tree1=new alai_tree_wx(divTree1)

var root=tree1.root
var n1=root.add("��֯�ṹ")
<%if (treeList != null) {%>
<%

for (int i = 0;i < treeList.size(); i ++){

  String mId = (String)((HashMap)(treeList.get(i))).get("TREE_ID");
  String mName = (String)((HashMap)(treeList.get(i))).get("TREE_NAME");
  String strChecked = "false";
   int level = Integer.parseInt((String)((HashMap)(treeList.get(i))).get("TREE_LEVEL"));
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
tree1.expandToTier(2)
var isRun = false;
tree1.oncheck=function changeCheck(srcNode)
{
   if (isRun) return;
       isRun = true;
       selectOnlyOne(srcNode);
       isRun =false;
}
</script>
<script language="JavaScript">
function changeParentCheck(srcNode){
 nodeParent = srcNode.parent;
 if (nodeParent.isCheck){
   nodeParent.checkBox.checked = srcNode.checkBox.checked;
   changeParentCheck(nodeParent);
 }
}
function changeChildrenCheck(srcNode){
  if (srcNode.hasChild){
    var nodes = srcNode.children;
    var i=0;
    for (i=0;i<nodes.length;i++){
      var node = nodes[i];
      if (node.isCheck){
            node.checkBox.checked = srcNode.checkBox.checked;
            changeChildrenCheck(node);
      }
    }
  }
}

function selectOnlyOne(srcNode){

for (i=0;i<tree1.colChkNode.length;i++){
    node = tree1.colChkNode[i];
    node.checkBox.checked=false;
    }
srcNode.checkBox.checked=true;
getSelect(srcNode.checkBox.value);
}

</script>
</html:form>
</body>
</html:html>
