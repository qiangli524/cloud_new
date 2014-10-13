<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <%@include file="import-mxgraph.jsp" %>
        <title>运维资源配置平台</title>
        <s:set id="backurl" value="#parameters.backurl[0]"/>
        <script type="text/javascript">
            mxConstants.DEFAULT_HOTSPOT = 1;
            // Enables guides
            mxGraphHandler.prototype.guidesEnabled = true;
            // Alt disables guides
            mxGuide.prototype.isEnabledForEvent = function(evt){return !mxEvent.isAltDown(evt);};
            // Enables snapping waypoints to terminals
            mxEdgeHandler.prototype.snapToTerminals = true;
            /*
            window.onbeforeunload = function(){
                if(window.opener){
                    setTimeout("opener.location.reload()", 1);
                }else if($(document).data("saved")){
                    return;
                }
                return mxResources.get('changesLost'); 
            };
            */
            $(function(){
                //var oMxGraph=<s:property default="{}" value="#toJSON(mxGraph)" escape="false"/>;
                //由Struts配置文件进行JSON转换
                var oMxGraph=<s:property default="{}" value="mxGraph" escape="false"/>;
                var graphType='<s:property value="#parameters.graphType[0]" escape="false"/>'||oMxGraph.graphType;
                var editor;
                var hideSplash = function(){
                    var splash = document.getElementById('splash');
                    if (splash != null){
                        try{
                            mxEvent.release(splash);
                            mxEffects.fadeOut(splash, 100, true);
                        }catch (e) {
                            splash.parentNode.removeChild(splash);
                        }
                    }
                };
                try{
                    if (!mxClient.isBrowserSupported()){
                        mxUtils.error('您的浏览器不支持此功能！', 200, false);
                    }else{
                        var node = mxUtils.load('config/workfloweditor.xml').getDocumentElement();
                        editor = new mxEditor(node);
                        $(document).data("editor", editor);
                        function getPosition(cell){
                            var isCell=cell!=null;
                            cell = cell || editor.graph.getSelectionCell();
                            if (!isCell) {
                                cell = editor.graph.getCurrentRoot();
                                if (cell == null) {
                                    cell = editor.graph.getModel().getRoot();
                                }
                            }
                            editor.graph.stopEditing(true);
                            var offset = mxUtils.getOffset(editor.graph.container);
                            var x = offset.x + 10;
                            var y = offset.y;
                            if (editor.properties != null && !this.movePropertiesDialog) {
                                x = editor.properties.getX();
                                y = editor.properties.getY();
                            } else {
                                var bounds = editor.graph.getCellBounds(cell);
                                if (bounds != null) {
                                    x += bounds.x + Math.min(200, bounds.width);
                                    y += bounds.y;
                                }
                            }
                            return [x,y];
                        }
                        editor.showProperties=function(cell){//重写属性修改对话框
                            if(cell){
                                if(cell.edge){//网络连线
                                    var $form=$("#edgeForm");
                                    $form.dialog({
                                        position:getPosition(cell),
                                        title:"网络连接",
                                        open:function(){
                                            var ipleft=cell.source.getAttribute("data-ipaddr"),
                                            ipright=cell.target.getAttribute("data-ipaddr");
                                            $form.find(":text[name=ipleft]").val(ipleft);
                                            $form.find(":text[name=ipright]").val(ipright);
                                            $("#submitEdgeForm").removeAttr("disabled").one("click",{edge:cell}, function(event){
                                                var times=0,intervalID=setInterval(function(){//连线闪烁
                                                    var color,width;
                                                    if(++times%2==1){
                                                        color='#00EE00';
                                                        width=2;
                                                    }else{
                                                        if(times>=10){
                                                            clearInterval(intervalID);
                                                            if(intervalID){
                                                                intervalID=false;
                                                                alert("成功接通网络！");
                                                            }
                                                            return;
                                                        }
                                                        color='#999999';
                                                        width=1;
                                                    }
                                                    editor.graph.setCellStyles(mxConstants.STYLE_STROKECOLOR, color, [event.data.edge]);
                                                    editor.graph.setCellStyles(mxConstants.STYLE_STROKEWIDTH, width, [event.data.edge]);
                                                }, 500);
                                                $form.dialog("close");
                                            });
                                        },
                                        close:function(){
                                            $form.dialog("distroy");
                                        }
                                    });
                                }else if(cell.getAttribute("data-type")=="VMware"){
                                    $("#VMwareForm").dialog({
                                        title:'虚拟机属性',
                                        position:getPosition(cell),
                                        open:function(){
                                          //  $("#VMwareForm").find("[name=label]").text(cell.getAttribute("data-vhName")||"");
                                          	$("#VMwareForm").find("[name=label]").text(cell.getAttribute("label")||"");
                                            $("#VMwareForm").find("[name=ipaddr]").text(cell.getAttribute("data-vhIp")||"");
                                            $("#VMwareForm").find("[name=hostName]").text(cell.getAttribute("data-hostName")||"");
                                        },
                                        close:function(){
                                            $("#VMwareForm").dialog("distroy")
                                        }
                                    });
                                }else if(cell.getAttribute("data-type")=="X86Computer"){
                                    var $form=$("#X86ComputerForm");
                                    $form.dialog({
                                        position:getPosition(cell),
                                        title:"设置主机",
                                        open:function(){
                                            var $label=$form.find(":text[name=label]");
                                            var $ipaddr=$form.find(":text[name=ipaddr]");
                                            $label.val(cell.getAttribute("label"));
                                            $ipaddr.val(cell.getAttribute("data-ipaddr")||"");
                                            $form.find(".btn-submit").one("click", {cell:cell,$label:$label,$ipaddr:$ipaddr}, function(e){
                                                var graph=editor.graph;
                                                graph.getModel().beginUpdate();
                                                try{
                                                    e.data.cell.setAttribute("label",e.data.$label.val());
                                                    e.data.cell.setAttribute("data-ipaddr",e.data.$ipaddr.val());
                                                    var image=graph.getCellStyle(e.data.cell)[mxConstants.STYLE_IMAGE]||'';
                                                    graph.setCellStyles(mxConstants.STYLE_IMAGE,image.replace(/\.gray\.png$/,".png"),[e.data.cell]);
                                                }finally{
                                                    graph.getModel().endUpdate();
                                                }
                                                $form.dialog("close");
                                            });
                                        },
                                        close:function(){
                                            $form.dialog("distroy");
                                        }
                                    });
                                }
                            }
                        }
                        if(oMxGraph&&oMxGraph.graphId){
                            var doc = mxUtils.parseXml(oMxGraph.graphContent);
                            var codec = new mxCodec(doc);
                            codec.decode(doc.documentElement, editor.graph.getModel());
                        }
                        editor.addAction('save', function(editor, cell){
                            $("#graphForm").dialog({
                                title:'设置拓扑图名称',
                                modal:true,
                                open:function(){
                                    var $form=$("#graphForm"), cell=editor.graph.model.getRoot();
                                    var $label=$form.find("[name='label']");
                                    var $description=$form.find("[name='description']");
                                    $label.val(cell.getAttribute('label')||"");
                                    $description.val(cell.getAttribute('description')||"");
                                    $("#submitGraphForm").one('click',{cell:cell,$label:$label,$description:$description},function(e){
                                        editor.graph.getModel().beginUpdate();
                                        try{
                                            e.data.cell.setAttribute("label",e.data.$label.val());
                                            e.data.cell.setAttribute("description",e.data.$description.val());
                                        }finally{
                                            editor.graph.getModel().endUpdate();
                                        }
                                        saveMxGraph();
                                    });
                                },
                                close:function(){
                                    this.reset();
                                }
                            });
                        });
                        
                       //create vmware vm -----duangh
                         editor.addAction('createVmwareVM', function(editor, cell){
                            $("#vminfo").dialog({
                                title:'创建虚拟机',
                                modal:true,
                                open:function(){
                                    $("#createsubmit").one('click',function(e){
	                                  if(typeof(create(editor, cell)) == "undefined"){//调用创建虚拟机函数并判断是否调用后台创建虚拟机
	                                  	editor.graph.addCellOverlay(cell, new mxCellOverlay(new mxImage("../images/loading.gif",20,20), '正在创建...', 'right', 'top'));
	                                  }
                                       $("#vminfo").dialog("close");
                                    });
                                },
                                close:function(){
                                    $("#vminfo").dialog("distroy");
                                }
                            });
                        });
                        function create(editor, cell){
                        	var url = '<s:url action="create"/>';
                        	var url_param = '?net_id=0fa87701bf024f24b3ec119c4b77ca23';
                        	url = url + url_param;
              				var vmname = $("#vminfo").find('[name=vmname]').val();
              				if(vmname==''){
              					alert("请填写虚拟机名称!");
              					return false;
              				}
              				var vmcpu = $("#vminfo").find('[name=vmcpu]').val();
              				var vmmem = $("#vminfo").find('[name=vmmem]').val();
              				var storage = $("#vmstorage").val();
                   			$.ajax({
                        		url:url,
                        		type:'post',
                        		data:{vmname:vmname,vmcpu:vmcpu,vmmem:vmmem,storage:storage},
                        		dataType:"json",
                        		success:function(data){
                        			if(data.result==1){
                        				alert("创建虚拟机" + vmname + "成功！");
                        				editor.graph.getModel().beginUpdate();
                                      	try{
                                      		var image=editor.graph.getCellStyle(cell)[mxConstants.STYLE_IMAGE]||'';
                                      		cell.setAttribute("label",$("#vminfo").find('[name=vmname]').val());
                                      		editor.graph.setCellStyles(mxConstants.STYLE_IMAGE,image.replace(/\.gray\.png$/,".png"),[cell]);
                                     	}finally{
                                      		editor.graph.getModel().endUpdate();
                                      	}
                        			}else{
                        				alert("创建虚拟机失败!");
                        			}
                            		editor.graph.removeCellOverlays(cell);
                            	},
                        		error:function(xhr){
                            		if(window.confirm("创建虚拟机失败，是否查看失败信息？")){
                                		window.open().document.write(xhr.responseText);
                            		}
                        		}
                   			});
                        }
                        
                        
                        
                        editor.addAction('createVM', function(editor, cell){
                            var funcName=new Date().getTime().toString(36)+"_";
                            window[funcName]=function(win,url){
                                var graph = editor.graph;
                                graph.addCellOverlay(cell, new mxCellOverlay(new mxImage("../images/loading.gif",20,20), '正在创建...', 'right', 'top'));
                                $.getJSON(url, {timestamp:funcName}, function(data){
                                    editor.graph.getModel().beginUpdate();
                                    try{
                                        var image=graph.getCellStyle(cell)[mxConstants.STYLE_IMAGE]||'';
                                        graph.setCellStyles(mxConstants.STYLE_IMAGE,image.replace(/\.gray\.png$/,".png"),[cell]);
                                        for(var p in data){
                                            cell.setAttribute("data-"+p, data[p]);
                                        }
                                        //data-vhId="1_01_001_0029_02_109" data-vhName="bomc1" data-vhIp="172.21.1.102" data-hostName="172.21.1.105"
                                        cell.setAttribute("label",data.vhName);
                                        cell.setAttribute("data-ipaddr",data.vhIp);
                                        editor.graph.removeCellOverlays(cell);
                                    }finally{
                                        editor.graph.getModel().endUpdate();
                                    }
                                });
                                win.close();
                            }
                            var iWidth = 1000;
                            var iHeight = 600;
                            var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                            var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                            var url="<s:url action="tree_listVMService" namespace="/"/>?callbackurl=<%=request.getContextPath()%>/graph/interface/"+funcName+".do";
                            window.open(url,"_blank",'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
                        });
                        editor.addAction('addDeploy', function(editor, cell){
                            var vhName=cell.getAttribute("data-vhName"),hostName=cell.getAttribute("data-hostName");
                            if(!vhName){
                                alert("请先创建虚拟机!");
                                return;
                            }
                            var funcName=new Date().getTime().toString(36)+"_";
                            window[funcName]=function(win){
                                editor.graph.addCellOverlay(cell, new mxCellOverlay(new mxImage("../images/loading.gif",20,20), '正在部署应用...', 'right', 'top'));
                                setTimeout(function(){
                                    alert("应用部署成功！");
                                    editor.graph.removeCellOverlays(cell);
                                }, 9999);
                                win.close();
                            }
                            var url="<s:url action="dep_addDeployExample" namespace="/"/>?oper=1&virtualId="+vhName+"&host_name="+hostName+"&callbackurl=<%=request.getContextPath()%>/graph/interface/"+funcName+".do";;
                            var iWidth = 800;
                            var iHeight = 600;
                            var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
                            var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
                            window.open(url,"_blank",'height='+iHeight+',width='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
                        });
                        //返回
                        editor.addAction('back', function(editor, cell){
                            if('${backurl}'){
                                window.location.href='${backurl}';
                            }else{
                                alert("已经是第一页！");
                            }
                        });
                        
                        var title = document.title;
                        var funct = function(sender){
                            document.title = title + ' - ' + sender.getTitle();
                        };
                        editor.addListener(mxEvent.OPEN, funct);
                        editor.addListener(mxEvent.ROOT, funct);
                        funct(editor);
                        editor.setStatus('Version '+mxClient.VERSION)
                        hideSplash();
                    }
                }catch (e){
                    hideSplash();
                    mxUtils.alert('无法开启功能: '+e.message);
                }
                function saveMxGraph(){
                    var url='<s:url action="save"/>';
                    var root=editor.graph.model.getRoot();
                    var label=root.getAttribute("label")||'',desc=root.getAttribute("description");
                    $.ajax({
                        url:url,
                        type:'post',
                        data:{mxGraph:{graphId:oMxGraph.graphId,graphName:label,graphType:graphType,graphDesc:desc,graphContent:editor.writeGraphModel()}},
                        success:function(data){
                            oMxGraph = data;
                            editor.fireEvent(new mxEventObject(mxEvent.SAVE, 'url', url));
                            alert("保存成功！");
                            $(document).data("saved",true)
                            location.href="<s:url/>?g="+data.graphId;
                        },
                        error:function(xhr){
                            if(window.confirm("保存失败，是否查看失败信息？")){
                                window.open().document.write(xhr.responseText);
                            }
                        }
                    });
                }
                $("img.mxToolbarMode,img.mxToolbarItem").parent().find("img").each(function(){$(this).attr("width",16).attr("height",16)});
            });
        </script>
    </head>
    <body>
        <table id="splash" width="100%" height="100%"
               style="background:white;position:absolute;top:0px;left:0px;z-index:4;">
            <tr>
                <td align="center" valign="middle">
                    <img src="images/loading.gif">
                </td>
            </tr>
        </table>
        <div id="graph" class="base">
            <!-- Graph Here -->
        </div>
        <div id="status" class="base" style="text-align:right;">
            <!-- Status Here -->
        </div>
        <div id="nodeForm" style="display:none"></div>
        <form id="graphForm" action="#" style="display: none">
            <table class="form-table" width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td style="text-align:right;white-space: nowrap">拓扑图名称:</td>
                    <td style="width: 250px"><input type="text" name="label"/></td>
                </tr>
                <tr>
                    <td style="text-align:right;white-space: nowrap">描述:</td>
                    <td style="width: 250px">
                        <textarea name="description" rows="3"></textarea>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;">
                        <input type="button" id="submitGraphForm" value="确定"/>&nbsp;&nbsp;
                        <input type="button" class="btn-dialog-cancel" value="取消"/>
                    </td>
                </tr>
            </table>
        </form>
        <div id="edgeForm" style="display:none">
            <table class="form-table" width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>IP1:</td>
                    <td><input type="text" name="ipleft"/></td>
                </tr>
                <tr>
                    <td>IP2:</td>
                    <td><input type="text" name="ipright"/></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;">
                        <input type="button" id="submitEdgeForm" value="接通网络"/>&nbsp;&nbsp;
                        <input type="button" class="btn-dialog-cancel" value="取消"/>
                    </td>
                </tr>
            </table>
        </div>
        <div id="X86ComputerForm" style="display:none">
            <table class="form-table" width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>主机名:</td>
                    <td><input type="text" name="label"/></td>
                </tr>
                <tr>
                    <td>IP地址:</td>
                    <td><input type="text" name="ipaddr"/></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;">
                        <input type="button" class="btn-submit" value="绑定IP"/>&nbsp;&nbsp;
                        <input type="button" class="btn-dialog-cancel" value="取消"/>
                    </td>
                </tr>
            </table>
        </div>
        <div id="VMwareForm" style="display:none">
            <table class="form-table" width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td>虚拟机名称:</td>
                    <td><span name="label"/></td>
                </tr>
                <tr>
                    <td>IP地址:</td>
                    <td><span name="ipaddr"/></td>
                </tr>
                <tr>
                    <td>所在物理机:</td>
                    <td><span name="hostName"/></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center;">
                        <input type="button" class="btn-dialog-cancel" value="关闭"/>
                    </td>
                </tr>
            </table>
        </div>
        <!-- duangh -->
        <div id="vminfo" style="display:none">
        	<table class="form-table" width="100%" border="0" cellpadding="0" cellspacing="0">
        		<tr>
        			<td>虚拟机名称：<font color="red">*</font>
        			</td>
        			<td><input type="text" name="vmname"/></td>
        		</tr>
        		<tr>
        			<td>CPU个数:</td>
        			<td><input type="text" name="vmcpu"/>个</td>
        		</tr>
        		<tr>
        			<td>内存:</td>
        			<td><input type="text" name="vmmem"/>(MB)</td>
        		</tr>
        		<tr>
        			<td>存储:</td>
        			<td><s:textfield id="vmstorage"></s:textfield>(GB)</td>
        		</tr>
        		<tr>
        			<td colspan="2" style="text-align:center;">
        				<input type="button" class="btn-submit" id="createsubmit" value="创建"/>&nbsp;&nbsp;
        				<input type="button" class="btn-dialog-cancel" value="取消" id="cancel"/>
        			</td>
        		</tr>
        	</table>
        </div>
    </body>
</html>
