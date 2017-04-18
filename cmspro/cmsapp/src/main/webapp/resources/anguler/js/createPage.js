//var hostAddress="http://209.200.39.254:8989/cmsapp";
var hostAddress="http://localhost:8989/cmsapp";
var getPages = "/admin/page/getpages/";
var pageDesign="/admin/page/page-design/";
var pagePreview = "/admin/page/preview/";	

function ajaxCallGet(url,method){
	
	var resultData;
	var request = $.ajax({
		  url: url,
		  type: method,
		  async: false,
		  cache: false,
		  timeout: 30000,
		  dataType: "html"
		});

		request.done(function(msg) {
		  resultData = msg;
		});
		
		request.fail(function(jqXHR, textStatus) {
		  
		});
	return resultData;
}

function ajaxCallPost(url,method,data){
	
	var resultData;
	var request = $.ajax({
		  url: url,
		  type: method,
		  data : data,
		  async: false,
		  cache: false,
		  timeout: 30000,
		  dataType: "html"
		});

		request.done(function(msg) {
		  resultData = msg;
		});
		
		request.fail(function(jqXHR, textStatus) {
		  
		});
	return resultData;
}	

$(document).ready(function(){
	
	$('#example').DataTable( {
        "ajax": hostAddress+getPages+$("#myHost").val(),
        "columns": [
            { "data": "hostName",
              "visible": false },
            { "data": "pageName" },
            { "data": "templateName" },
            { "data": "editableInfo.createdBy" },
            { "data": "editableInfo.createdAt",
              "render": function ( data, type, full, meta ) {
            	      return new Date(data).toLocaleString();
            	    }
            },
            { "data": "hostName",
              "render": function ( data, type, full, meta ) {
          	      return '<a title="Page Design" href="'+hostAddress+pageDesign+full.pageName+'/host/'+full.hostName+'" class="">'+full.pageName+'</a>&nbsp;&nbsp;&nbsp;&nbsp;';
              	    } 	
              },
            { "data": "pageName",
                "render": function ( data, type, full, meta ) {
              	      return '<a title="View Site" href="'+hostAddress+pagePreview+full.pageName+'/host/'+full.hostName+'"  class="glyphicon glyphicon-eye-open"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
              	    } 	
              }
        ]
    } );
});