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