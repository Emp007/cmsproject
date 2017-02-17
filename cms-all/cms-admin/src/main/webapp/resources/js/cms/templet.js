
/*templet.js*/
var imagepath = "http://localhost:8989/cms-rest/static/";

$(document)
		.ready(
				function() {

					$(document)
							.on(
									'click',
									'.addcontainer',
									function(event) {
										var id = event.target.id;
										if (id == 'lsb-add-container') {
											$("#lsb_container").css("display", "block");
										} else if (id == 'rsb-add-container') {
											$("#rsb_container").css("display", "block");
										}
										showcontainer(id);
									});
				});


function readURL(input) {
			var ext = input.files[0]['name'].substring(
					input.files[0]['name'].lastIndexOf('.') + 1).toLowerCase();
			if (input.files
					&& input.files[0]
					&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
				var reader = new FileReader();
				reader.onload = function(e) {

					$('#img').attr('src', e.target.result);
					$('#preview-img').attr('src', e.target.result);
				}

				reader.readAsDataURL(input.files[0]);
			} else {
				$('#img').attr('src', '/assets/no_preview.png');
			}
		}
		var seperator ="";
		function readURLs(input) {
			var ext = input.files[0]['name'].substring(
					input.files[0]['name'].lastIndexOf('.') + 1).toLowerCase();
			if (input.files
					&& input.files[0]
					&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) {
				var reader = new FileReader();
				reader.onload = function(e) {
					images.push(e.target.result);
				}
				 var imageId = input.id;
			     var formData = new FormData();
                 var image = document.getElementById(imageId).files[0];
                 formData.append('file', image);
                 var hostId = $("#host-id").val();
                 var status = upload(formData,hostId);
                 var URL = imagepath+'images/'+hostId+'/'+input.files[0]['name'];
                 var textPath = $("#herosURL").val();
                 textPath+=seperator+URL;
                 seperator="~";
                 $("#herosURL").val(textPath);
				reader.readAsDataURL(input.files[0]);
			} else {
				$('#img').attr('src', '/assets/no_preview.png');
			}
		}
		
		function upload(data ,hostId) {
			   // hostId=hostId.trim();
			     $.ajax({
			         url : "/cms-admin/admin/page/fileupload?hostId="+hostId,
			         data : data,
			         processData : false,
			         contentType : false,
			         async: false,
			         type : 'POST',
			         success : function(data) {
			             return data;
			         },
			         error : function(err) {
			             //alert(err);
			         }
			     });
			} 
		
		function getImageType(sel) {

			var mainDiv = document.getElementById('imageuploadpane');
			$(mainDiv).empty();

			var lDiv = document.createElement('div');
			lDiv.className = 'media';
			lDiv.style.cssText = 'padding: 10px';

			if (sel.value == '0') {
				$("#imageno-select").css("display", "none");
				$("#imageuploadpane").css("display", "none");
			} else if (sel.value == '1') {
				$("#singleimg").css("display", "block");$("#preview-singleimg").css("display", "block");
				$("#preview-sliderimg").css("display", "none");
				$("#imageno-select").css("display", "none");
				$("#imageuploadpane").css("display", "block");
				lDiv.innerHTML = "<input id='image' type='file' class='form-control' name='image' onChange='readURL(this);'>";
				mainDiv.appendChild(lDiv);
			} else if (sel.value == '2') {
				$("#singleimg").css("display", "none");$("#preview-singleimg").css("display", "none");
				$("#preview-sliderimg").css("display", "block");
				$("#imageuploadpane").css("display", "block");
				$("#imageno-select").css("display", "block");
			}
		}

		function getImageNo(sel) {
			var mainDiv = document.getElementById('imageuploadpane');
			$(mainDiv).empty();
			for (var i = 0; i < sel.value; i++) {

				var rowDiv = document.createElement('div');
				rowDiv.className = 'row';

				var lDiv = document.createElement('div');
				lDiv.className = 'col-sm-2';
				rowDiv.appendChild(lDiv);

				var rDiv = document.createElement('div');
				rDiv.className = 'col-sm-8';
				lDiv.id = 'imagelist';
				rDiv.innerHTML = "<input id='image"+i+ "' type='file' class='form-control' name='image_'"+i+ "' onChange='readURLs(this);'>";
				rowDiv.appendChild(rDiv);

				mainDiv.appendChild(rowDiv);
			}
		}

		function gettemplettype(sel) {
			var mainDiv = document.getElementById('body-content');
			
			
			var row = document.createElement('div');
			row.className = 'row';
			
			var lrow = document.createElement('div');
			lrow.className = 'whitebg-min-height';
			lrow.id = 'leftcontent';
			
			var rrow = document.createElement('div');
			rrow.className = 'whitebg-min-height';
			rrow.id = 'rightcontent';
			
			var lsb_add_container = document.createElement('a');
			lsb_add_container.setAttribute('id','lsb-add-container');
			lsb_add_container.setAttribute("class","btn btn-xs addcontainer");
			lsb_add_container.style.cssText = 'text-decoration: none;';
			lsb_add_container.innerHTML = "<i class='fa fa-plus-square-o' aria-hidden='true'></i> Add Container";
			lrow.appendChild(lsb_add_container);
			
			var lcDiv = document.createElement('div');
			lcDiv.id = 'lsb_container';
			lcDiv.style.cssText = 'background-color: white; height: 90%; display: none;';
			lrow.appendChild(lcDiv);
			
			var lDiv = document.createElement('div');
			lDiv.id = 'leftside';
			lDiv.style.cssText = 'margin: 0px;';
			lDiv.className = 'div_style templet_style';
			lDiv.appendChild(lrow);
			
			var rsb_add_container = document.createElement('a');
			rsb_add_container.setAttribute('id','rsb-add-container');
			rsb_add_container.setAttribute("class","btn btn-xs addcontainer");
			rsb_add_container.style.cssText = 'text-decoration: none;';
			rsb_add_container.innerHTML = "<i class='fa fa-plus-square-o' aria-hidden='true'></i> Add Container";
			rrow.appendChild(rsb_add_container);
			
			var rcDiv = document.createElement('div');
			rcDiv.id = 'rsb_container';
			rcDiv.style.cssText = 'background-color: white; height: 90%; display: none;';
			rrow.appendChild(rcDiv);
			
			var rDiv = document.createElement('div');
			rDiv.id = 'rightside';
			rDiv.style.cssText = 'margin: 0px;';
			rDiv.className = 'div_style templet_style';
			rDiv.appendChild(rrow); 

			if (sel.value == '1') {
				$(mainDiv).empty();
				lDiv.className = '';
				lDiv.removeChild(lrow);
				lrow.className = 'min-height-100';
				lDiv.appendChild(lrow);

				mainDiv.appendChild(lDiv);
			} else if (sel.value == '2') {
				$(mainDiv).empty();
								
				lDiv.className = 'col-sm-6';
				rDiv.className = 'col-sm-6';
				
				row.appendChild(lDiv);
				row.appendChild(rDiv);
				mainDiv.appendChild(row);
			} else if (sel.value == '3') {
				$(mainDiv).empty();				
				lDiv.className = 'col-sm-8';
				rDiv.className = 'col-sm-4';
				
				row.appendChild(lDiv);
				row.appendChild(rDiv);
				mainDiv.appendChild(row);
			} else if (sel.value == '4') {
				$(mainDiv).empty();
				lDiv.className = 'col-sm-4';
				rDiv.className = 'col-sm-8';
				
				row.appendChild(lDiv);
				row.appendChild(rDiv);
				mainDiv.appendChild(row);
			}
		}

		
		function showcontainer(id) {
            
			var ele = $('#' + id).parent().attr('id');
			var mainDiv = document.getElementById(ele);
			var x = mainDiv.getElementsByTagName('div')[0].id;
			
			var iDiv = document.createElement('div');
			iDiv.id = 'container' + count;
			iDiv.className = 'greybg sub-container';

			var jDiv = document.createElement('div');
			jDiv.className = 'addremoveDiv text-left';
			jDiv.innerHTML = "<a id='container"
				+ count
				+ "' class='btn btn-xs addcontainer' onclick='removeDrawedContainer(this.id);return false'><i class='fa fa-minus-square-o' aria-hidden='true'></i> Remove Container</a>";
			
			var kDiv = document.createElement('div');
			kDiv.className = 'control col-md-4';
			kDiv.style.cssText = 'display: none;';
			kDiv.innerHTML = "<SELECT name='element' id='control"+ count+"' class='ctrlselect selectpicker form-control addelementselect' ><OPTION value='none'>SELECT</OPTION><OPTION value='textarea'>PARAGRAPH</OPTION><option value='file'>IMAGE</option><option value='video'>VIDEO</option><option value='quote'>QUOTE</option><option value='imagewithtext'>Image With Text</option><option value='linkbutton'>Link Button</option><option value='fileupload'>File Upload</option><option value='Menu'>menu</option></SELECT>";
			jDiv.appendChild(kDiv);

			var cDiv = document.createElement('div');
			cDiv.id = 'content';
			cDiv.className = 'content';
			
			var controleDiv = document.createElement('div');
			controleDiv.id = 'controldiv' + count;
			controleDiv.className = 'control'; 

			var hTag = document.createElement('h5');
			hTag.innerHTML = "";
			cDiv.appendChild(hTag);
			iDiv.appendChild(cDiv);
			
			var zDiv = document.createElement('div');
			zDiv.id = 'subpart' + count;

			zDiv.appendChild(jDiv);
			zDiv.appendChild(iDiv);
			zDiv.appendChild(controleDiv);

			var container = document.getElementById(x);
			container.appendChild(zDiv);

			count++;
		}
		
		function removeDrawedContainer(id) {
			var btnid = id.substr(9);
			$("#subpart" + btnid).remove();
		}
		
		function checktempletname(){
			$('#namealert').text();
			var name = $('#templet-name').val();
			var id = $('#host-id').val();
			
			var url = "/cms-admin/admin/templet/checktempletname/"+name+"/hostId/"+id;
			var sumMenuBody="";
			$.ajax({
						type : "GET",
						url : url,
						async : false,
						success : function(response) {
							if(response == 'FOUND'){
								$('#namealert').text('Name is Already in use !! ');	
								$('#templet-name').val("");
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