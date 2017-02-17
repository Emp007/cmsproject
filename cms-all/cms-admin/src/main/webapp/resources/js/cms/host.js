
var hostAddress="http://localhost:8989/cms-admin/admin/host/checkhostname/";

$(document).ready(function(){

	$("#searchByHostName").blur(function(event){
		var searchtext = event.target.value;
        var tool = ajaxCallGet(hostAddress+event.target.value,"GET");
    })
});


function checkhostname(){
		$('#namealert').text();
		var name = $('#hostName').val(); 
		var url = "/cms-admin/admin/host/checkhostname/" + name;
		
		$.ajax({
					type : "GET",
					url : url,
					async : false,
					success : function(response) {
						if(response == 'FOUND'){
							$('#namealert').text('Name is Already in use !! ');	
						}else if(response == 'NOT-FOUND'){
							$('#namealert').text("");
						}
					},
					failure : function() {
						$('#namealert').text("");
						return;
					},
					error : function() {
						$('#namealert').text("");
						return;
					}
				});
		}


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
		  alert( "Request failed: " + textStatus );
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
		  alert( "Request failed: " + textStatus );
		});
	return resultData;
}
