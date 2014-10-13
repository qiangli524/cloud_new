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
	pageNum = 10;//页码显示10个
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
	//new start
	
	//拼装fenye html
	var fenyehtml = '';
	fenyehtml += '<div class="page-l">共&nbsp;<span class="blue">'+totalCount+'</span>&nbsp;条记录'+curPage+'/'+pageCount+'页<span>|</span>';
	
	
	//每页记录数pageSize
	fenyehtml += '每页<font> <select id="pageSize" onchange="changePageSize(this.value)">';
		
	if(pageSize == 10){
		fenyehtml += '<option value="10" selected="selected">10</option>';
	}else{
		fenyehtml += '<option value="10" >10</option>';
	}
	if(pageSize == 30){
		fenyehtml += '<option value="30" selected="selected">30</option>';
	}else{
		fenyehtml += '<option value="30" >30</option>';
	}
	if(pageSize == 50){
		fenyehtml += '<option value="50" selected="selected">50</option>';
	}else{
		fenyehtml += '<option value="50" >50</option>';
	}
		
	fenyehtml += '</select> </font>条记录</div>';

	
	//首页
	fenyehtml += '<div class="page-r"><a class="a-1" href="javascript:void(0);" onclick="javascript:gotoPage(1);return false;">首页</a>&nbsp;';
	
	//上一页
	if ( prePage > 0 ){
		fenyehtml +='<a href="javascript:void(0);" class="a-1" onClick="javascript:gotoPage('+prePage+');return false;">上一页</a>&nbsp;';
	}

	//页码
	for (p in pageNums) {
		if(pageNums[p] == curPage){
			fenyehtml += '<a href="javascript:gotoPage('+pageNums[p]+');return false;" class="a-1 on">'+pageNums[p]+'</a>&nbsp;';
		}else{
			fenyehtml += '<a href="javascript:void(0);"  class="a-1" onclick="javascript:gotoPage('+pageNums[p]+');return false;">'+pageNums[p]+'</a>&nbsp;';
		}
	}

	//下一页
	if ( nextPage > 0 ){
		fenyehtml += '<a href="javascript:void(0);" class="a-1" onClick="javascript:gotoPage('+nextPage+');return false;">下一页</a>&nbsp;';
	}
	
	
	//尾页	
	fenyehtml += '<a href="javascript:void(0);" onclick="javascript:gotoPage(1);return false;">尾页</a>&nbsp';
		
	//跳转到	
	fenyehtml += '跳转到<select id="PAGINATION_CURRENT_PAGE_NO" name="PAGINATION_CURRENT_PAGE_NO" onchange="gotoPage(this.value);">';
	for(var i=1;i <= pageCount;i++){
		if( i == curPage){
			fenyehtml +='<option value="'+i+'" selected="">'+i+'</option>';
		}else{
			fenyehtml +='<option value="'+i+'">'+i+'</option>';
		}
	}		
	fenyehtml += '</select>页</div>';

	
	//显示行数
	fenyehtml += '<input type="hidden" name="PGSZIE" id="PGSZIE"/>';
	
	//new end
 	$("#ajaxpage").html(fenyehtml);
 }