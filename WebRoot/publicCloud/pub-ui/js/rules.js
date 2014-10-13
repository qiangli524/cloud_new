//icmp 协议
var icmp = [
            {
				icmpType:{value:"8",date_index:"0",description:"Echo"},
				icmpCode:[{value:"0",description:"Echo request"}]
            },
            {
				icmpType:{value:"0",date_index:"1",description:"Echo Reply"},
				icmpCode:[{value:"0",description:"Echo Reply"}]
             },
             {
                 icmpType:{value:"3",date_index:"2",description:"Destination Unreachable"},
                 icmpCode:[
	                           {value:"0",description:"Destination network unreachable"},
	                           {value:"1",description:"Destination host unreachable"},
	                           {value:"2",description:"Destination protocol unreachable"},
	                           {value:"3",description:"Destination port unreachable"}, 
	                           {value:"4",description:"Fragmentation required, and DF flag set"},
	                           {value:"5",description:"Source route failed"},
	                           {value:"6",description:"Destination network unknown"}, 
	                           {value:"7",description:"Destination host unknown"},
	                           {value:"8",description:"Source host isolated"},
	                           {value:"9",description:"Network administratively prohibited"},
	                           {value:"10",description:"Host administratively prohibited"},
	                           {value:"11",description:"Network unreachable for TOS"},
	                           {value:"12",description:"Host unreachable for TOS"},
	                           {value:"13",description:"Communication administratively prohibited"},
	                           {value:"14",description:"Host Precedence Violation"},
	                           {value:"15",description:"Precedence cutoff in effect"}
                           ]
              },
             {	
                 icmpType:{value:"4",date_index:"3",description:"Source Quench"},
                 icmpCode:[
	                           {value:"0",description:"Source quench (congestion control)"}
                           ]
              },
              {	
                  icmpType:{value:"5",date_index:"4",description:"Redirect Message"},
                  icmpCode:[
 	                           {value:"0",description:"Redirect Datagram for the Network"},
 	                           {value:"1",description:"Redirect Datagram for the Host"},
 	                           {value:"2",description:"Redirect Datagram for the TOS & network"},
 	                           {value:"3",description:"Redirect Datagram for the TOS & host"}
                            ]
               },
               {	
                   icmpType:{value:"9",date_index:"5",description:"Router Advertisement"},
                   icmpCode:[
  	                           {value:"0",description:"Router Advertisement"}
                             ]
                },
                {	
                    icmpType:{value:"10",date_index:"6",description:"	Router Solicitation"},
                    icmpCode:[
   	                           {value:"0",description:"Router discovery/selection/solicitation"}
                              ]
                 },
                 {	
	                icmpType:{value:"11",date_index:"7",description:"Time Exceeded"},
	                icmpCode:[
 	                           {value:"0",description:"TTL expired in transit"},
 	                           {value:"1",description:"Fragment reassembly time exceeded"}
	                         ]
                 },
	          	 {	
	                icmpType:{value:"12",date_index:"8",description:"Parameter Problem: Bad IP header"},
	                icmpCode:[
 	                           {value:"0",description:"Pointer indicates the error"},
 	                          {value:"1",description:"Missing a required option"},
 	                           {value:"2",description:"Bad length"}
	                         ]
	              },
                  {	
                     icmpType:{value:"13",date_index:"9",description:"Timestamp"},
                     icmpCode:[
    	                           {value:"0",description:"Timestamp"}
                               ]
                  },
	              {	
	                     icmpType:{value:"14",date_index:"10",description:"Timestamp Reply"},
	                     icmpCode:[
	    	                           {value:"0",description:"Timestamp reply"}
	                               ]
	              },
	              {	
	                     icmpType:{value:"15",date_index:"11",description:"Information Request"},
	                     icmpCode:[
	    	                           {value:"0",description:"Information request"}
	                               ]
	              },
	              
	              {	
	                     icmpType:{value:"16",date_index:"12",description:"Information Reply"},
	                     icmpCode:[
	    	                           {value:"0",description:"Information reply"}
	                               ]
	              },
	              {	
	                     icmpType:{value:"17",date_index:"13",description:"Address Mask Reques"},
	                     icmpCode:[
	    	                           {value:"0",description:"Address mask request"}
	                               ]
	              },
	              {	
	                     icmpType:{value:"18",date_index:"14",description:"Address Mask Reply"},
	                     icmpCode:[
	    	                           {value:"0",description:"Address mask reply"}
	                               ]
	              },
	              {	
	                     icmpType:{value:"30",date_index:"15",description:"Traceroute"},
	                     icmpCode:[
	    	                           {value:"0",description:"Information request"}
	                               ]
	              }
                 
            ];


//点击快速设置规则按钮
function quickRules(dataType){
	var protocal,val1;
	switch(dataType){
		case 'ping':
			protocal="icmp";
			break;
		case 'ssh':
			protocal="tcp";
			val1="22";
			break;
		case 'http':
			protocal="tcp";
			val1="80";
			break;
		case 'https':
			protocal="tcp";
			val1="443";
			break;
		case 'ftp':
			protocal="tcp";
			val1="21";
			break;
		case 'openvpn':
			protocal="udp";
			val1="1194";
			break;
		case 'remote':
			protocal="tcp";
			val1="3398";
			break;
		case 'pptp':
			protocal="tcp";
			val1="1723";
			break;
		case 'ipip':
			protocal="ipip";
			break;
		case 'gre':
			protocal="gre";
			break;
	}
	$("select[name='obj.protocol']").val(protocal);
	$("select[name='obj.protocol']").trigger('change');
	$("input[name='obj.val1']").val(val1);
}