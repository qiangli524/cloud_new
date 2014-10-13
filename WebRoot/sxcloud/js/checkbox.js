$(function(){
    $(":checkbox").click(function(){
          $(":checked").not(this).attr("checked",false);
    });
});