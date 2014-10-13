 //ajax分页
 var pageSize,totalCount,pageCount,curPage,prePage,nextPage;
 var pageNums = new Array();
 function ajaxFenye(thePage){
 
 	$("#ajaxpage").append("");
 
 	pageSize = thePage.pageSize;
	totalCount = thePage.totalCount;
	pageCount = thePage.pageCount;
	curPage = thePage.currentPageNo;
	prePage = 0;
	nextPage = 0;
	pageNum = 10;
	prePage = curPage - 1;
	nextPage = curPage + 1;
	prePage = prePage <= 0 ? -1 : prePage;
	nextPage = nextPage > pageCount ? -1 : nextPage;
	
	//计算要显示的页码列表，默认最多显示10个页码
	var start = 1;
	if(curPage >= pageNum){
	     start = pageCount/pageNum * pageNum;
	}
	var num = start;
	for(var i=0;!(num > pageCount || num > start + pageNum);i++){
	     pageNums[i]=num;
	     ++num;
	}
	//alert(pageNums);
	
//拼装fenye html
	var fenyehtml = '';
	
	if ( prePage > 0 ){
		fenyehtml +='<a href="javascript:void(0);" onClick="javascript:gotoPage('+prePage+');return false;">上一页</a>';
	} else {
		fenyehtml +='<a href="javascript:void(0);" >上一页</a>';
	}

	for (p in pageNums) {
		if(pageNums[p] == curPage){
			fenyehtml += '<a href="javascript:gotoPage('+pageNums[p]+');return false;" class="on1">'+pageNums[p]+'</a>';
		}else{
			fenyehtml += '<a href="javascript:void(0);" onclick="javascript:gotoPage('+pageNums[p]+');return false;">'+pageNums[p]+'</a>';
		}
	}

	if ( nextPage > 0 ){
		fenyehtml += '<a href="javascript:void(0);" onClick="javascript:gotoPage('+nextPage+');return false;">下一页</a>';
	} else {
		fenyehtml += '<a href="javascript:void(0);">下一页</a>';
	}

	fenyehtml += '共'+pageCount+'页 到第<input type="text" id="gopage" name="gopage" onkeyup="this.value=this.value.replace(/\D/g,\'\')">页'
			  +'<a href="javascript:void(0);" onClick="javascript:go();return false;">GO</a>';

 	$("#ajaxpage").html(fenyehtml);
 }
//各种事件	 
 
//跳转到指定页面 	
function go(){
	var gopage = document.getElementById("gopage").value;
	if(null == gopage || "" == gopage){
		alert("您要去哪一页？请填写页码！");
		return;
	}
 	if(gopage > pageCount){
		alert("没有这一页，请重新输入！");
		return;
	}
	gotoPage(gopage);
}

		
		