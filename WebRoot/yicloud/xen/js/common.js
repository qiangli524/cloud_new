	//msg 需要显示的信息 opac透明度 to距离顶端的距离
    function mask(msg,opac,to){
		var doc = window.document;
	  	var w = doc.createElement("div");
	    w.setAttribute("id","mybody")
	    if(opac==""||opac==null){
	    	opac='0.0000001'
	    }
	    if(to==""||to==null){
	    	to='0';
	    }
	    with(w.style){
	        position = 'absolute';
	        zIndex = '10000';
	        width = Math.max(doc.documentElement.scrollWidth, doc.documentElement.clientWidth) + "px";
	        height =Math.max(doc.documentElement.scrollHeight, doc.documentElement.clientHeight) + "px";
	        position="absolute";
	        left = '0';
	        top = to;
	        background = '#FAFAFA';
	        filter = 'Alpha(opacity=10)';
	        opacity = opac;
	    }
	    doc.body.appendChild(w);
	    //**********************************************//
	    
	    var ig=$("<div id='progressbar' style='width:200px;'></div>")[0];
	    if(msg==''||msg==null){
	    	msg="数据加载中..."
		}
	    ig.innerHTML='<img src="yicloud/xen/images/loading.gif" /><br/><span style="font-size:12px;font-weight:650;position:absolute;left:20%;top:33%;width:400px">'+msg+'</span>';
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
	//移除mask
	function removeMask() {
		$("#mybody").remove();
		$("#progressbar").remove();
	}
	
	/* 去掉两段空格*/
	function trim(str){
		return str.replace(/^\s+|\s+$/g,"");
	}

	/* 检测输入的字符串是否符合要求 */
	function isnumber(str)
	 {
	  var number_chars = "1234567890.";
	        var i;
	        for (i=0;i<str.length;i++)
	   {
	            if (number_chars.indexOf(str.charAt(i))==-1) return false;
	        }
	        return true;
	}