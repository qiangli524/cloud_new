$( function(){  
        $("#system").change(  
            function()  
            {  
        		var checkText = $("#system").find("option:selected").text();
        		if (checkText!="请选择操作系统")
       			{
        			$.ajax({
            			url : 'workflow_getTemListBySystem.do?systemName='+checkText,
            			dataType : 'json',
            			success : function(data){
            				var myOptions="<option value='-1'>请选择预装软件</option>"; 
            				for(var i =0 ; i<data.length;i++)
            				{
            					myOptions+='<option value="' + data[i].id + '">' + data[i].soft_name + '</option>'; 
                                $("#soft").html(myOptions);  
            				}
            			}
            		});
       			}
            }  
        );  
        }     
    );  