//将form表单序列化成json对象
// var jsonuserinfo = $('#form1').serializeObject();  
// alert(JSON.stringify(jsonuserinfo));  
// 结果：{"name":"wanglei_bj","sex":"man","phone":"18600613050"}

$.fn.serializeObject = function()    
{    
   var o = {};    
   var a = this.serializeArray();    
   $.each(a, function() {    
       if (o[this.name]) {    
           if (!o[this.name].push) {    
               o[this.name] = [o[this.name]];    
           }    
           o[this.name].push(this.value || '');    
       } else {    
           o[this.name] = this.value || '';    
       }    
   });    
   return o;    
};
