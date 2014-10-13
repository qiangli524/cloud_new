$.dialog.setting.zIndex = 100000;
function pageOnLoad() {
	$("#iframe").hide();
}

function aa(){
	alert("aa");
}

//同步数据
function synchroData(){
	alert("dd");
	hideRMenu();
	var url = "huaweientitytree_synchroData.do";
	alert(confirm("确定要重新同步数据吗?"));
	if(confirm("确定要重新同步数据吗?")==true){
		alert("aa");
		mask('数据正在同步，请稍候.......');
		alert("bb");
		$.getJSON(url,{'time': new Date().toString()},function(data){
			if(data.result==1){
				removeMask();
				alert("同步数据成功!");
				zTree.reAsyncChildNodes(null, "refresh",true);
			}else{
				removeMask();
				alert("同步数据失败!");
			}
		});
	}
}

//虚拟机挂起添加遮罩层
function mask(message){
	var doc = window.document;
  	var w = doc.createElement("div");
    w.setAttribute("id","mybody")
    with(w.style){
        position = 'absolute';
        zIndex = '10000';
        width = Math.max(doc.documentElement.scrollWidth, doc.documentElement.clientWidth) + "px";
        height =Math.max(doc.documentElement.scrollHeight, doc.documentElement.clientHeight) + "px";
        position="absolute";
        left = '0';
        top = '0';
        background = '#FAFAFA';
        filter = 'Alpha(opacity=10)';
        opacity = '0.7';
    }
    doc.body.appendChild(w);
    //**********************************************//
    var ig=doc.createElement("div");
    ig.setAttribute("id","progressbar")
    ig.innerHTML='&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="sxcloud/images/ajax-loader.gif" /><td i="progressbar"/> <br/>'+message;
    doc.getElementById("mybody").appendChild(ig);
    with(ig.style){
        position = 'absolute';
        zIndex = '10001';
        left = '55%';
        top = '35%';
        marginLeft = - ig.offsetWidth / 2 + 'px';
        marginTop = - ig.offsetHeight / 2 + 'px';
    }
    doc.body.appendChild(ig);
}
//移除遮罩层
function removeMask(){
	var doc = window.document;
	var mybody = doc.getElementById('mybody');
	doc.body.removeChild(mybody);
	var progressbar = doc.getElementById('progressbar');
	doc.body.removeChild(progressbar);
}

//提示
//使用方法：barEnd是关闭弹出的bar的方法，注意，id必须唯一，并且bar的id和barEnd的id一样。

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

