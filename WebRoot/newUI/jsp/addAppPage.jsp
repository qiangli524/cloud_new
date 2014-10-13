<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="../../sxcloud/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
        <title>安徽移动私有云管理平台</title>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/reset.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/jquery.contextMenu.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/compons.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/lhgdialog.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/base/jquery.ui.all.css" />
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/newUI/newUI/css/pages.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/jquery.cookie.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/correctPNG.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/modernizr-min.2.6.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/jquery.hotkeys.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/jquery.contextMenu.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/plugin/jquery-lhgdialog.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/index.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/newUI/newUI/js/function.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/yicloud/xen/js/dialog/lhgdialog.min.js?skin=default"></script>
        <script type="text/javascript">
        var api = frameElement.api;
        var w = api.opener;

        api.button({
            id:'Ok',
            name: '添加到桌面',
            callback: addIconToIndex,
            focus: true
        },
        {
            id:'cancle',
            name: '取消'
        });
        
            //获取数据到页面
            function getPageData() {
                var module_name=$("#module_name").val();
                
                //module_name=encodeURI(module_name); 
                module_name=encodeURI(encodeURI(module_name));  
                $.ajax({
                    type: "POST",
                    cache: "false",
                    async: true,
                    dataType: "text",
                    url: "<%=request.getContextPath()%>/newui_getUserAllowAddName.do?module_name=" + module_name,
                    success: function(data) {
                        var moduleName_li = "";
                        var page_num=0;//页数
                        var jsonObject = eval(data);
                        $.each(jsonObject, function(i) {
                            var moduleName = jsonObject[i].FUNNAME;
                            if(moduleName.length>8) {moduleNameAbbr = moduleName.substring(0,7)+"..";}
                            else {moduleNameAbbr = moduleName;} 
                            var moduleId = jsonObject[i].FUNCID;
                            var module_path = jsonObject[i].FUNCREQUEST;
                            var pic_path = jsonObject[i].PIC_PATH;
                            moduleName_li = moduleName_li+"<li onClick='UID_addApp(this)' moduleId='"+moduleId+"' module_path='"+module_path+"' moduleName='"+moduleName+"' pic_path='"+pic_path+"'><a href='javascript:void(0)' title='"+moduleName+"'>"+moduleNameAbbr+"</a></li>";
                            page_num=page_num+1;
                           
                        });
                       $("#page_num").html(page_num);
                       $("#appmain").html(moduleName_li);
                    }
                });
                
            }
            //页面实例时添加数据到页面
            function initPageData() {
                getPageData();
            }
            //添加图标数据到首页
            function addIconToIndex() {
                var moduleId='';
                var module_paths='';
                var moduleNames='';
                var pic_paths='';
                $("#applist-1 li").each(function(){
                    if($(this).hasClass("on")){
                        moduleId=moduleId+ $(this).attr("moduleId")+",";
                        module_paths=module_paths+ $(this).attr("module_path")+",";
                        moduleNames=moduleNames+ $(this).attr("moduleName")+",";
                        pic_paths=pic_paths+ $(this).attr("pic_path")+",";
                    } 
                });
                $.ajax({
                    type: "POST",
                    cache: "false",
                    async: true,
                    dataType: "text",
                    url: "<%=request.getContextPath()%>/newui_addMoudleToDesk.do?moduleId=" + moduleId,
                    success: function(data) {
                        /*   var moduleName_array=moduleNames.split(',');
                                           var module_path_array=module_paths.split(',');
                                           var pic_path_array=pic_paths.split(',');
                                           var currentPaneAdd = $("#main div.pane[currentPane='true']").find("a.indexAdd");
                                           for(var i=0;i<moduleName_array.length;i++){
                                             if(moduleName_array[i]!=''&& module_path_array[i] !=''){
                                                 currentPaneAdd.before("<a href="+module_path_array[i]+" class='indexIcon' ><img src="+<%=request.getContextPath()%>pic_path_array[i]+" alt="+moduleName_array[i]+"/><span>"+moduleName_array[i]+"<s></s></span></a>");

                                                         UID_iconArray.init(indexGlobal.main, indexGlobal.mainChild, indexGlobal.iconH,indexGlobal.iconW);
							 
                                             }
                                           }*/
                    }
                });
                //top.location.reload();
                var moduleName_array=moduleNames.split(',');
                var moduleId_array=moduleId.split(',');
                var module_path_array=module_paths.split(',');
                var pic_path_array=pic_paths.split(',');
                var topMain = $(top.document.getElementById("main"));
                var currentPane = $(top.document.getElementById("main")).find("div.pane[currentpane='true']");
                var currentPaneAdd = $(top.document.getElementById("main")).find("div.pane[currentpane='true']>a.indexAdd");
                topMain.fadeOut(500);
                for(var i=0;i<moduleName_array.length;i++){
                    if(moduleName_array[i]!=''&& module_path_array[i] != ''){
                        var iconSrc = pic_path_array[i]=="undefined"? '/newUI/images/icon_15.png' : pic_path_array[i];
                        var iconName = moduleName_array[i].length>6?moduleName_array[i].substring(0,5)+"..." : moduleName_array[i];
                        //alert(iconSrc);
                        //alert("<a href="+module_path_array[i]+" class='indexIcon' id="+moduleId_array[i]+"><img src="+"<%=request.getContextPath()%>" + iconSrc+" alt='"+moduleName_array[i]+"'/><span>"+iconName+"<s></s></span></a>");
                        currentPaneAdd.before("<a href=javascript:openHref('"+module_path_array[i] +"','"+moduleName_array[i] + "')"+" class='indexIcon' id="+moduleId_array[i]+"><img src="+"<%=request.getContextPath()%>" + iconSrc+" alt='"+moduleName_array[i]+"'/><span>"+iconName+"<s></s></span></a>");
                    }
                }
                top.UID_iconArray.init(topMain, currentPane, top.indexGlobal.iconH, top.indexGlobal.iconW);
                top.UID_iconArray.init(topMain, currentPane, top.indexGlobal.iconHNew, top.indexGlobal.iconWNew);
            }
            
            
        </script>
        <!--[if IE 6]>
        <script type="text/javascript">
            window.attachEvent("onload", correctPNG);
        </script>
        <![endif]-->
    </head>

    <body onload="initPageData()">
        <div class="toolbar-1 toolbar-2 mb15 clearfix">
            <form action="newui_getUserAllowAddName.do" id="queryForm">
                <div class="toolbar-1_left">
                    <input type="button" id="refreshBtn" title="刷新" class="refresh-btn-01" value="刷新" onclick="getPageData();"/>
                </div>
                <div class="toolbar-1_right">
                
            		<s:form action="newui_index.do" method="post">
            			<input type="text"  name="module_name" id="module_name" /><input type="button"  onclick="getPageData();" value="搜索">
           			 </s:form>
                </div>
            </form>
        </div>
        <div class="appCategory">
            <div class="tabBox-2" id="tabBox-2">
            </div>
            <div>
                <div class="tabBox-2_con">
                    <div class="toolbar-1 toolbar-3 mt10 clearfix">
<%--                        <div class="toolbar-1_left">--%>
<%--                            <input type="button" id="addToDesk" title="添加到桌面" class="color690 pl10 pr10" onclick="addIconToIndex()" value="添加到桌面"/>--%>
<%--                        </div>--%>
<%--                        <div class="toolbar-1_right">--%>
<%--                        </div>--%>
                        <div class="appList-1 clear pt20" id="applist-1">
                            <ul class="clearfix" id="appmain">
                            </ul>
                            <div class="clear pt20"></div>
                        </div>
                    </div>
                </div>
                <div class="tabBox-2_con none">2</div>
                <div class="tabBox-2_con none">3</div>
            </div>
        </div>
    </body>
</html>