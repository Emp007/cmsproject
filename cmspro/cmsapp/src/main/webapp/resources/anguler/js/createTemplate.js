//var hostAddress="http://209.200.39.254:8989/cmsapp";
var hostAddress="http://localhost:8989/cmsapp";
var getTemplateByName ="/admin/templates/gettemplate/";
var getTemplates = "/admin/templates/gettemplates";
var getTemplateView = "/admin/templates/view/"
var getTemplateUpdate = "/admin/templates/edit/"	

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




var count = 1;
var rowsCount = 0;
$(document).ready(function(){
	
	
	$("#templateName").blur(function(event){
		
		var yourTemplate = event.target.value;
        var tool = ajaxCallGet(hostAddress+getTemplateByName+event.target.value,"GET");
        if(tool == 'FOUND'){
        	$( "#templateName" ).val('');
        	$("#templateName").attr("placeholder", yourTemplate+" not available..! ");
        	$('#templateName').addClass('placeholder-color');
        }
    });
	
	
	
	$( "#addCols").hide();
	$( "#act").hide();
	
	/*Column Select-Box*/
	$( document ).on( 'click', '#addRows', function(event) {
		   $( "#addCols").show();
		});
	
	
	/*Column Select-Box Apply*/
	$( document ).on( 'click', '#addRowsCols', function(event) {
		rowsCount++;   
		
		$( "#addCols").hide();
		$( "#act").hide();
		
		if($("#colId").val() == '100%'){
		
			var counter = rowsCount;
			$("#tpb").append('<div id="'+counter+'removebtn" class="row  rmadmin text-center">' +
								'<br/><div id="'+counter+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
							 '</div>'+
								'<div id="'+counter+'container" class="row templateborder templateborder full-border text-center" >' +
								'<div id="'+counter+'template" class="col-sm-12 text-center " >'+
								  		'<div id="'+counter+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+counter+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+counter+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  '</div>');
		}
		
		if($("#colId").val() == '33%-66%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
								'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
							 '</div>'+
							 '<div id="'+colCounter1+'container" class="row templateborder full-border text-center" >' +
								'<div id="'+colCounter1+'template" class="col-sm-4 text-center templateborder full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-8 text-center " >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  '</div>');
		}
		
		if($("#colId").val() == '66%-33%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
								'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
							 '</div>'+
								'<div id="'+colCounter1+'container" class="row templateborder full-border text-center" >' +
								'<div id="'+colCounter1+'template" class="col-sm-8 text-center templateborder full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-4 text-center " >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  '</div>');
			
		}
		
		if($("#colId").val() == '33%-33%-33%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			rowsCount++;
			var colCounter3 = rowsCount;
			
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
							'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
						     '</div>'+
								'<div id="'+colCounter1+'container" class="row templateborder full-border text-center" >' +
								'<div id="'+colCounter1+'template" class="col-sm-4 text-center templateborder full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-4 text-center templateborder full-border" >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  		'<div id="'+colCounter3+'template" class="col-sm-4 text-center" >'+
						  				'<div id="'+colCounter3+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
						  				'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter3+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
						  				'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter3+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
						  			'</div>'+
							  '</div>');
			
		}
		
		if($("#colId").val() == '25-75%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
								'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
							     '</div>'+
								'<div id="'+colCounter1+'container" class="row templateborder full-border text-center" >' +
								'<div id="'+colCounter1+'template" class="col-sm-3 text-center templateborder full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-9 text-center " >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  '</div>');
		}
		
		if($("#colId").val() == '75%-25%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
							'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
						     '</div>'+
								'<div id="'+colCounter1+'container" class="row templateborder full-border text-center" >' +
								'<div id="'+colCounter1+'template" class="col-sm-9 text-center templateborder full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-3 text-center " >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  '</div>');
		}
		
		if($("#colId").val() == '50%-50%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
								'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
						     '</div>'+
							 '<div id="'+colCounter1+'container" class="row templateborder full-border text-center" >' +
								  	'<div id="'+colCounter1+'template" class="col-sm-6 text-center templateborder full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-6 text-center " >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  '</div>');
			
		}
		
		if($("#colId").val() == '25%-25%-25%-25%'){
			
			var colCounter1 = rowsCount;
			rowsCount++;
			var colCounter2 = rowsCount;
			rowsCount++;
			var colCounter3 = rowsCount;
			rowsCount++;
			var colCounter4 = rowsCount;
			
			$("#tpb").append('<div id="'+colCounter1+'removebtn" class="row  rmadmin text-center">' +
								'<br/><div id="'+colCounter1+'remove" class="removeclass btn btn-sm btn-danger rmadmin ">Remove-Row</div>'+
					          '</div>'+
							  '<div id="'+colCounter1+'container" class="row templateborder full-border text-center " >' +
								  	'<div id="'+colCounter1+'template" class="col-sm-3 templateborder text-center full-border" >'+
								  		'<div id="'+colCounter1+'btn" class="btn btn-sm btn-info  rmadmin addtemplateContainer">Add-container</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
								  		'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter1+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
								  	'</div>'+
								  	'<div id="'+colCounter2+'template" class="col-sm-3 templateborder text-center full-border" >'+
							  			'<div id="'+colCounter2+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
							  			'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter2+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
							  		'</div>'+
							  		'<div id="'+colCounter3+'template" class="col-sm-3 templateborder text-center full-border" >'+
						  				'<div id="'+colCounter3+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
						  				'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter3+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
						  				'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter3+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
						  			'</div>'+
						  			'<div id="'+colCounter4+'template" class="col-sm-3 templateborder text-center full-border " >'+
					  					'<div id="'+colCounter4+'btn" class="btn btn-sm btn-info rmadmin addtemplateContainer">Add-container</div>'+
					  					'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter4+'btnmenu" class="addMenu btn btn-sm btn-warning rmadmin">Menu</div>'+
					  					'&nbsp;&nbsp;&nbsp;&nbsp;<div id="'+colCounter4+'btnrmenu" class="removeMenu btn btn-sm btn-danger rmadmin">R-Menu</div>'+
					  				'</div>'+
							  '</div>');	
		}
		}); 
	
	/*Make view in adminMode*/
	$( document ).on( 'click', '.removeclass', function(event) {
		var makeId = ((event.target.id).substring(0,(event.target.id).indexOf("remove")))+"container";
		$("#"+makeId).remove();
		
		makeId = ((event.target.id).substring(0,(event.target.id).indexOf("remove")))+"removebtn";
		$("#"+makeId).remove();
		});
	
	/*Make view in userMode*/
	$( document ).on( 'click', '#userModeAction', function(event) {
		   $( ".rmadmin").hide();
		});
	
	/*Get Pure Html*/
	$( document ).on( 'click', '#getHtmlAction', function(event) {
		  
		   $('#myHtmlView').text($('#templateBody').html());
		});
	
	/*Make view in adminMode*/
	$( document ).on( 'click', '#adminModeAction', function(event) {
		   $( ".rmadmin").show();
		});
	
	/*copy snippet html code*/
	$( document ).on( 'click', '#btncopy', function(event) {
		   
		var $temp = $("<input>");
		  $("body").append($temp);
		  $temp.val($("#myHtmlView").text()).select();
		  document.execCommand("copy");
		  $temp.remove();
	});
	
	/*Save Template*/
	$( document ).on( 'click', '#savetemplate', function(event) {
		
		$('#myHtml').val($('#templateBody').html());
		$("#myTemplateForm").submit();
			
			
		});
	
	$('#example').DataTable( {
        "ajax": hostAddress+getTemplates,
        "pageLength": 8,
        "columns": [
            { "data": "id" },
            { "data": "templateName" },
            { "data": "editableInfo.createdBy" },
            { "data": "editableInfo.createdAt",
              "render": function ( data, type, full, meta ) {
            	      return new Date(data).toLocaleString();
            	    }
            },
            { "data": "templateName",
                "render": function ( data, type, full, meta ) {
              	      return '<a title="Edit Site" href="'+hostAddress+getTemplateUpdate+data+'" class="glyphicon glyphicon-pencil"></a>&nbsp;&nbsp;&nbsp;&nbsp;'+
              	      		 '<a title="View Site" href="'+hostAddress+getTemplateView+data+'" class="glyphicon glyphicon-eye-open"></a>&nbsp;&nbsp;&nbsp;&nbsp;';
              	    } 	
              },
        ]
    } );
	
	
});