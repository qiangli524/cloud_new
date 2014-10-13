<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<html:html locale="true">
<%@ include file="../../sxcloud/common/link.jsp"%>
<head>
	<title></title>
	<script type="text/javascript" src="<%=request.getContextPath()%>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
	<script type="text/javascript">
	 var api = frameElement.api;
	 var w = api.opener;
	 /*
	 	 api.button({
		     id:'OkAnd',
		     name: '确定',
		     callback:submitRequest($("#group_id")),
		     focus: true
		 },
		 {
		     id:'cancle',
		     name: '取消'
		 });
		 */
	
   /**
    *check object
    */
    function cf_isNotObject(obj)
    {
      if(typeof(obj) != "object")
        {
          alert(obj+"\nis not a object");
          return true;
        }
        return false;
    }
    //按添加按钮时触发的函数
    function addselect(forselectForm,selectedForm) {
    var forForm = eval(forselectForm);
	var selectedForm = eval(selectedForm);
    if (cf_isNotObject(forForm))
       return false;
	if (cf_isNotObject(selectedForm))
       return false;
    var k;
    var len;
    len = selectedForm.length;
    for (k = 0; k < forForm.length; k++)
	{
       if (forForm.options[k].selected)
	   {
          olength = selectedForm.length;
     	  otext = forForm.options[k].text;
          ovalue = forForm.options[k].value;
          tag = 0
          for (i = 0; i < olength; i++)
		  {
            if (ovalue == selectedForm.options[i].value)
			{
               tag = 1;
            }
          }
          if (tag == 0)
		  {
             selectedForm.options[len] = new Option(otext, olength);
             selectedForm.options[len].value = ovalue;
             selectedForm.options[len].selected = true;
             len = len + 1;
          }
       }
    }
        deleteselected(forselectForm); 
  }
  //按删除按钮实现的功能
  function deleteselected(selectedForm){
   var objForm = eval(selectedForm);
   if(cf_isNotObject(objForm)) return false;
   var olength=objForm.length;
   var s=olength - 1;
   var j=0;
   for(i=s; i>=0; i--)
   {
	 if (objForm.options[i].selected)
	 {
       objForm.options[i]=null;
	   j=j+1;
 	 }
   }
   objForm.length=olength-j;
 }
 function submitRequest(thisForm){
 	   var theForm = document.getElementById("theForm");
 	   var name = theForm.name.value;
 	   if(name == ''){
	 	   alert("请输入名称！");
	 	   return false;
 	   }
 	   var type = '<%=request.getAttribute("type")%>'   
	   var selectedForm = eval(thisForm);
	   var   str = "" ;
	   len = selectedForm.length;
       for (k = 0; k < len; k++)
	   {
	      if(k>0)
	      str = str + ",";
          str = str + selectedForm.options[k].value;
	   }
	   	var name = theForm.name.value;
	   	var cluster_code = theForm.cluster_code.value;
	    //$("input[name='USERIDS']").attr("value", str);	    
	    //var groupid = theForm.GROUPID.value;
	    url='cluster_saveDRSGroup.do?str='+str+"&name="+encodeURI(encodeURI(name))+"&type="+type+"&cluster_code="+cluster_code;
	   /// document.getElementById('theForm').submit();
	   bar(cluster_code,"正在创建DRS组");
	   $.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == 1){
					barEnd(cluster_code,"成功创建DRS组");
					w.list();
				}else {
					barEnd(cluster_code,"创建DRS组失败");
				}
			});
	}
	
	function validateName(){
		var name = $("#name").val();
		url = "cluster_validateGroupName.do?name="+name;
		  $.getJSON(url,{"time":new Date().toString()},function(data){
				if(data.responseCode == -1){
					$("#name_validate").append("已经存在，请修改");
				}
			});
	}
	function bar(idstr,contents){
	$.dialog({
			id:idstr,
		    title: '提示',
		    width: 200,
		    height: 100,
		    left: '100%',
		    top: '100%',
		    fixed: true,
		    max:false,
		    content:contents
		});
	}

	function barEnd(idstr,contents){
	$.dialog.list[idstr].content(contents,false,false);
	$.dialog.list[idstr].time(2);
	}
	
</script>
</head>
<body class="pop-body scrollbody">
<s:form action="ugroup_saveGrpmember" method="post" cssStyle="theForm"
		id="theForm">
		<input type="hidden" name="grpForm.FUNCSID" value="<%=(String)request.getParameter("FUNCSID") %>" />
		<input type="hidden" name="grpForm.GROUPID" id="GROUPID" value="<%=(String)request.getParameter("GROUPID") %>"/>
		<input type="hidden" id="grpForm.USERIDS" name="USERIDS" value=""/>
			<table width="100%" border="0" cellspacing="0" class="pop-table nosize">
				<tr >
					<td class="til" align="center">
					名称：<span id="name_validate" style="color: RED">*</span>
					</td>
					<td >
					<s:textfield name="theForm.name" cssStyle="width:220px;   height:20px;" id="name" onblur="javascript:validateName();"></s:textfield>
					</td>
					<td class="til" width="15%" align="center">
                        选择集群
                    </td>
                    <td>
                       	<s:select list="theForm.resultList" listKey="entityId" listValue="name" name="theForm.cluster_code" id="cluster_code" cssStyle="width:220px;   height:20px;" ></s:select>
                    </td>
				</tr>
				<tr>
				    <td class="til" align="center">
						不在此组中的
					</td>
					<td>
					<s:select list="theForm.vmList" id="user_id" name="" listKey="entityId" listValue="name" multiple="true" ondblclick="addselect('document.forms[0].user_id','document.forms[0].group_id');return false;"
					size="10" cssStyle="width:220px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
					<td class="til" align="center">
						在此组中的
					</td>
					<td>
					<s:select list="theForm.forSelectList" id="group_id" name="" listKey="entityId" listValue="name"  multiple="true" ondblclick="addselect('document.forms[0].group_id','document.forms[0].user_id');return false;"
					size="10" cssStyle="width:220px; height:224px; line-height:22px; border:none; border:1px solid #9dbcd9">
					</s:select>
					</td>
				</tr>
				<tr>
					<td colspan="10" class="btnCenter">
						<input type="button" class="thickbox btn-style02" value="确定"
							onclick="javascript:submitRequest(document.forms[0].group_id);return false;" />
					</td>
				</tr>
			</table>
	</s:form>
</body>

</html:html>
