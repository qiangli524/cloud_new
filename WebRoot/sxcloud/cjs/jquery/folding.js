// folding //
$(document).ready(function () {
		
		$('div.itemContent').slideUp(300);
		
		$('.set').bind('click',hiden);
		
		$('img.closeEl').bind('click', toggleContent);

		$('img.hideEl').bind('click', toggleContent);
		
		//	$("#mydiv").load("getData.jsp"); 
	});

var hiden = function()
{
	//被选中
	if(this.checked)
	{
		if(this.id=="div1")
		{
			$("#div1_show").show();
		}
		else if(this.id=="div2")
		{
			$("#div2_show").show();
		}else if(this.id=="div3")
		{
			$("#div3_show").show();
		}else if(this.id=="div4")
		{
			$("#div4_show").show();
		}else if(this.id=="div5")
		{
			$("#div5_show").show();
		}else if(this.id=="div6")
		{
			$("#div6_show").show();
		}
	}
	else//取消选中
	{
		if(this.id=="div1")
		{
			$("#div1_show").hide();
		}else if(this.id=="div2")
		{
			$("#div2_show").hide();
		}else if(this.id=="div3")
		{
			$("#div3_show").hide();
		}else if(this.id=="div4")
		{
			$("#div4_show").hide();
		}else if(this.id=="div5")
		{
			$("#div5_show").hide();
		}else if(this.id=="div6")
		{
			$("#div6_show").hide();
		}
	}
};



var toggleContent = function(e)
{
	var targetContent = $('div.itemContent', this.parentNode.parentNode.parentNode.parentNode.parentNode);
	if (targetContent.css('display') == 'none') {
		targetContent.slideDown(300);
		$(this).attr({ src: "cresources/default/images/jian.gif"}); 
	} else {
		targetContent.slideUp(300);
		$(this).attr({ src: "cresources/default/images/jia.gif"}); 
	}
	return false;
};
