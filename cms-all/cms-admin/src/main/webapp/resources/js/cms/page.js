/*page.js*/

var imagepath = "http://localhost:10953/cms-rest/static/";

var imageuploadurl = "/cms-admin/admin/page/fileupload";

var templets = null;
var myIndex = 0;
var controlcnt = 0;
var btncnt = 0;

$(document)
		.ready(
				function() {

					$(document)
							.on(
									'click',
									'.apply',
									function(event) {
										
										var id = event.target.id;
										var admindiv = $('#' + id).parent().attr('id');
										var userdiv = $('#' + id).parent().attr('id').replace('admin','user');
										var selectedelement = id.replace("adminsave", "h");
										var sequencenumber = admindiv.substring(12, admindiv.length);

										$('#' + admindiv).css("display", "none");
										$('#' + userdiv).css("display", "block");
										if ($('#' + selectedelement).val() == 'textarea') {
											tinyMCE.triggerSave();
											$('#'+userdiv).empty().append($('#textarea_'+ sequencenumber).val());
										}
										if ($('#' + selectedelement).val() == 'quote') {
											tinyMCE.triggerSave();
											$('#'+userdiv).empty().append($('#quote'+ sequencenumber).val());
										} else if ($('#' + selectedelement).val() == 'file') {	
											
											var formData = new FormData();
                                            var image = document.getElementById('img_'+sequencenumber).files[0];
                                            formData.append('file', image);
                                            var hostId = $("#host-id").val();
                                            var status = upload(formData,hostId);
                                            
                                            var imgurl = $('#img_'+ sequencenumber).val();
											imgurl = imgurl.substring(imgurl.lastIndexOf('\\') + 1,imgurl.length);
											 
											$('#'+userdiv).find('img').attr('src',imagepath+'/images/'+hostId+'/'+imgurl);
										} else if ($('#' + selectedelement).val() == 'video') {
											var formData = new FormData();
                                            var videofile = document.getElementById('video_'+sequencenumber).files[0];
                                            formData.append('file', videofile);
                                            
                                            var hostId = $("#host-id").val();
                                            var status = upload(formData,hostId);
                                           
											var value = $('#'+admindiv).find('input').val();
											var vediourl = value.substring(value.lastIndexOf('\\') + 1,value.length);
											
											$('#'+userdiv).find('video').find('source').attr('src',imagepath+'/vedio/'+hostId+'/'+vediourl);
										} else if ($('#' + selectedelement).val() == 'linkbutton') {
											var linkbuttonselect = $('#linkbuttonselect'+ sequencenumber).val();
											var linkbtntext = $('#linkbuttontext_'+ sequencenumber).val();
											var linkbtnurl = $('#linkbuttonurl_'+ sequencenumber).val();
											
											if(linkbuttonselect == 'BUTTON'){
												$('#'+userdiv).empty().append("<a id='linkbutton_"+ btncnt+ "' class='btn btn-default'  href="+linkbtnurl+" >"+linkbtntext+"</a>");	
											}else if(linkbuttonselect == 'LINK'){
												$('#'+userdiv).empty().append("<a id='linkbutton_"+ btncnt+ "'  href="+linkbtnurl+" >"+linkbtntext+"</a>");
											}
										} else if ($('#' + selectedelement).val() == 'imagewithtext') {
											tinyMCE.triggerSave();
											var formData = new FormData();
											var imagefile = document.getElementById('imagewithtext_img_'+sequencenumber).files[0];
                                            formData.append('file', imagefile);
                                            var hostId = $("#host-id").val();
											var status = upload(formData,hostId);
											var imgurl = $('#imagewithtext_img_'+ sequencenumber).val();
											imgurl = imgurl.substring(imgurl.lastIndexOf('\\') + 1,imgurl.length)
											var imgtext = $('#imagewithtext_'+ sequencenumber).val();
											$('#' + userdiv).find('img').attr('src',imagepath + '/images/'+ hostId + '/'+ imgurl);
											$('#'+userdiv).find('p').empty().append(imgtext);
										} else if ($('#' + selectedelement).val() == 'fileupload') {
											var formData = new FormData();
                                            var pdffile = document.getElementById('fileupload_file_'+sequencenumber).files[0];
                                            formData.append('file', pdffile);
                                            
                                            var hostId = $("#host-id").val();
                                            var status = upload(formData,hostId);
											var fileurl = $('#fileupload_file_'+ sequencenumber).val();
											fileurl = fileurl.substring(fileurl.lastIndexOf('\\') + 1,fileurl.length)
											var filetext = $('#fileuploadtext_'+ sequencenumber).val();
											$('#'+userdiv).empty().append("<a href='"+imagepath+'pdfs/'+hostId+'/'+fileurl+"' download> "+filetext+"</a>"); 	
										
										} else if ($('#' + selectedelement).val() == 'Menu') {
											var fileurl = $('.menu').val();
                                            $('#'+userdiv).append("<div class='menu'></div>");
										}else {
											
										}
										return false;
									});

					$(document)
							.on(
									'click',
									'.addelementselect',
									function(event) {

										var option = this;
										var res = option.id;
										res = res.substring(7, res.length);
										var control = "controldiv" + res;

										var addbtnDiv = document
												.createElement('DIV');
										addbtnDiv.className = 'add';
										addbtnDiv.id = "addbtn" + ++controlcnt;
										addbtnDiv.innerHTML = "<button class='btn btn-info btn-sm addelement' id='add"
												+ ++btncnt
												+ "' onclick='return addelement(this.id);'>Add Element</button> <input id='h"
												+ btncnt
												+ "' type='hidden' value="
												+ option.value + ">";

										var adminDiv = document
												.createElement('DIV');
										adminDiv.className = 'fragment';
										adminDiv.style.cssText = 'display: none;border: 1px solid #9AB3CE;';
										adminDiv.id = "adminelement"+ controlcnt;
										adminDiv.innerHTML = "<button class='btn btn-info btn-sm apply' id='adminsave"+ btncnt+ "' onclick=''>apply</button>"; 
										var userDiv = document.createElement('DIV');
										userDiv.className = 'fragment';
										userDiv.style.cssText = 'display: none; border: 1px solid #9AB3CE;overflow:hidden';
										userDiv.id = "userelement" + controlcnt;
						
										var p = document.createElement('p');
										p.innerHTML = 'No Content ';

										if (option.value == 'textarea') {
											var textarea = document.createElement("textarea");
											textarea.id = "textarea_" + btncnt;
											textarea.className = "text";
											textarea.name = "textarea";
											textarea.maxLength = "5000";
											textarea.cols = "50";
											textarea.rows = "5";
											
											adminDiv.appendChild(textarea);
											userDiv.appendChild(p);
										} else if (option.value == 'none') {
										} else if (option.value == 'video') {
											var myform = document.createElement("form");
											myform.setAttribute("method","POST");
											myform.setAttribute("id","vedioForm_adminsave" + btncnt);

											myform.setAttribute("enctype","multipart/form-data");
											
											var vediofilechooser = document.createElement("input");
											vediofilechooser.setAttribute("type", "file");
											vediofilechooser.setAttribute("accept", "video/*");
											vediofilechooser.setAttribute("id","video_" + btncnt);
											vediofilechooser.setAttribute("class","filestyle adminvideo_"+ btncnt);
											myform.appendChild(vediofilechooser);
											
											var vedio = document.createElement("video");
											vedio.setAttribute("width", "100%");
											vedio.setAttribute("height","200px");
											vedio.setAttribute("controls","controls");
											vedio.setAttribute("preload","true");
											vedio.setAttribute("id", "video_"+ btncnt);
											vedio.setAttribute("class","filestyle uservideo_"+ btncnt);

											var source = document.createElement("source");
											source.setAttribute("type","video/mp4");

											adminDiv.appendChild(myform);
											vedio.appendChild(source);
											userDiv.appendChild(vedio);
										} else if (option.value == 'quote') {
											var quotetextarea = document.createElement("textarea");
											quotetextarea.id = "quote" + btncnt;
											quotetextarea.className = "quotetext";
											quotetextarea.name = "quote";
											quotetextarea.maxLength = "500";
											quotetextarea.cols = "50";
											quotetextarea.rows = "5";

											adminDiv.appendChild(quotetextarea);
											userDiv.appendChild(p);
										} else if (option.value == 'linkbutton') {
											var lnkbtnDiv = document.createElement('DIV');
											lnkbtnDiv.className = 'lnkbtndiv';
											lnkbtnDiv.id = "lnkbtndiv" + btncnt;

											var select = document.createElement("select");
							                select.setAttribute("name", "linkbuttonselect");
							                select.setAttribute("id", "linkbuttonselect"+btncnt);
							                var option = document.createElement("option");
							                option.setAttribute("value", "LINK");
							                option.innerHTML = "LINK";
							                select.appendChild(option);
							                
							                var option = document.createElement("option");
							                option.setAttribute("value", "BUTTON");
							                option.innerHTML = "BUTTON";
							                select.appendChild(option);
							                 
											var linkbuttontext = document.createElement("input");
											linkbuttontext.setAttribute("id","linkbuttontext_" + btncnt);
											linkbuttontext.className = "form-control";
											linkbuttontext.name = "linkbuttontext"+ btncnt;
											linkbuttontext.placeholder = "Button text";
											linkbuttontext.type = "text";
											lnkbtnDiv.appendChild(linkbuttontext);

											var linkbuttonurl = document.createElement("input");
											linkbuttonurl.setAttribute("id","linkbuttonurl_" + btncnt);
											linkbuttonurl.className = "form-control ";
											linkbuttonurl.name = "linkbuttonurl"+ btncnt;
											linkbuttonurl.placeholder = "http://www.google.com";
											linkbuttonurl.type = "text";
											lnkbtnDiv.appendChild(linkbuttonurl);

											adminDiv.appendChild(lnkbtnDiv);
											adminDiv.appendChild(select);
											
										} else if (option.value == 'imagewithtext') {

											var myform = document.createElement("form");
											myform.setAttribute("method","POST");
											myform.setAttribute("id","imagewithtextForm_" + btncnt);

											myform.setAttribute("enctype","multipart/form-data");
											
											var imagewithtextimgchooser = document.createElement("input");
											imagewithtextimgchooser.setAttribute("type", "file");
											imagewithtextimgchooser.setAttribute("id", "imagewithtext_img_"+ btncnt);
											imagewithtextimgchooser.setAttribute("class", "filestyle adminimg_"+ btncnt);
											myform.appendChild(imagewithtextimgchooser);

											var imagewithtext = document.createElement("textarea");
											imagewithtext.setAttribute("id","imagewithtext_" + btncnt);
											imagewithtext.className = "imagewithtext";
											imagewithtext.name = "imagewithtext";
											imagewithtext.maxLength = "500";
											imagewithtext.cols = "50";
											imagewithtext.rows = "5";

											var imagewithtextimg = document.createElement('img');
											imagewithtextimg.setAttribute('width','100%');
											imagewithtextimg.setAttribute("class","userimg_" + btncnt);
											
											adminDiv.appendChild(myform);
											adminDiv.appendChild(imagewithtext);
											userDiv.appendChild(imagewithtextimg);
											userDiv.appendChild(p);
										} else if (option.value == 'fileupload') {
											var filechooser = document.createElement("input");
											filechooser.setAttribute("type", "file");
											filechooser.setAttribute("id", "fileupload_file_"+ btncnt);
											filechooser.setAttribute("class", "filestyle adminfile_"+ btncnt);
											
											var fileuploadtext = document.createElement("input");
											fileuploadtext.setAttribute("id","fileuploadtext_" + btncnt);
											fileuploadtext.className = "form-control";
											fileuploadtext.name = "fileuploadtext"+ btncnt;
											fileuploadtext.placeholder = "Button text";
											fileuploadtext.type = "text";
											
											adminDiv.appendChild(filechooser);
									        adminDiv.appendChild(fileuploadtext);
											}else if(option.value =='Menu'){

											var menu= document.createElement('div');
											menu.setAttribute('class',  'menu');

										} else {
											var myform = document.createElement("form");
											myform.setAttribute("method","POST");
											myform.setAttribute("id","imgForm_adminsave" + btncnt);

											myform.setAttribute("enctype","multipart/form-data");
											
											var imagechooser = document.createElement("input");
											imagechooser.setAttribute("type",option.value);
											imagechooser.setAttribute("id","img_" + btncnt);
											imagechooser.setAttribute("name","file");
											imagechooser.setAttribute("class","filestyle adminimg_"+ btncnt);
											myform.appendChild(imagechooser);

											var image = document.createElement('img');
											image.setAttribute('width', '100%');
											image.setAttribute("class","userimg_" + btncnt);
											image.setAttribute("class","userimg_" + btncnt);

											adminDiv.appendChild(myform);
											userDiv.appendChild(image);
										}
										var x = document.getElementById(control);
										x.appendChild(addbtnDiv);
										x.appendChild(adminDiv);
										x.appendChild(userDiv);
										applyMCE();
										applyQuoteMCE();
										applyImageWithTetMCE();
									});

				});

function addelement(id) {
	var id = id.replace(/add/g, "adminelement");
	$('#' + id).css("display", "block");
	return false;
}

function applyMCE() {
	tinyMCE
			.init({
				plugins : [ 'autolink lists link image save emoticons textcolor colorpicker textpattern' ],
				mode : "textareas",
				theme : 'modern',
				skin : 'light',
				menubar : false,
				statusbar : false,
				toolbar1 : 'bold italic | alignleft aligncenter alignright alignjustify | bullist | link | forecolor backcolor emoticons',
				editor_selector : "text",
				setup : function(ed) {
					ed.on('focus', function() {
						$(this.contentAreaContainer.parentElement).find(
								"div.mce-toolbar-grp").show();
					});
					ed.on('blur', function() {
						$(this.contentAreaContainer.parentElement).find(
								"div.mce-toolbar-grp").hide();
					});
					ed.on("init", function() {
						$(this.contentAreaContainer.parentElement).find(
								"div.mce-toolbar-grp").hide();
					});
				}
			});
}

function applyQuoteMCE() {
	tinyMCE.init({
		plugins : [ 'textpattern' ],
		mode : "textareas",
		theme : 'modern',
		menubar : false,
		statusbar : false,
		toolbar1 : 'bold',
		editor_selector : "quotetext",
		setup : function(ed) {
			ed.on('focus', function() {
				$(this.contentAreaContainer.parentElement).find(
						"div.mce-toolbar-grp").show();
			});
			ed.on('blur', function() {
				$(this.contentAreaContainer.parentElement).find(
						"div.mce-toolbar-grp").hide();
			});
			ed.on("init", function() {
				$(this.contentAreaContainer.parentElement).find(
						"div.mce-toolbar-grp").hide();
			});
		}
	});
}

function applyImageWithTetMCE() {
	tinyMCE
			.init({
				plugins : [ 'autolink textpattern textcolor colorpicker' ],
				mode : "textareas",
				theme : 'modern',
				menubar : false,
				statusbar : false,
				toolbar1 : 'bold italic | alignleft aligncenter alignright alignjustify | bullist | link | forecolor backcolor emoticons ',
				editor_selector : "imagewithtext",
				setup : function(ed) {
					ed.on('focus', function() {
						$(this.contentAreaContainer.parentElement).find(
								"div.mce-toolbar-grp").show();
					});
					ed.on('blur', function() {
						$(this.contentAreaContainer.parentElement).find(
								"div.mce-toolbar-grp").hide();
					});
					ed.on("init", function() {
						$(this.contentAreaContainer.parentElement).find(
								"div.mce-toolbar-grp").hide();
					});
				}
			});
}

function addcontainer(id) {
	if (id == 'lsb-add-container') {
		$("#lsb_container").css("display", "block");
	} else if (id == 'rsb-add-container') {
		$("#rsb_container").css("display", "block");
	}

	$(".addbtn-model").attr("id", id);
	$('#myModal').modal('show');
}

function showcontainer(id) {

	var ele = $('#' + id).parent().attr('id');
	var mainDiv = document.getElementById(ele);
	var x = mainDiv.getElementsByTagName('div')[0].id;

	var iDiv = document.createElement('div');
	iDiv.id = 'container' + count;
	iDiv.className = 'subdivs';

	var jDiv = document.createElement('div');
	jDiv.className = 'addremoveDiv';
	jDiv.innerHTML = "<button id='container"
			+ count
			+ "' type='button' class='btn btn-info btn-sm' onclick='removeDrawedContainer(this.id);return false'>Remove</button>";
	iDiv.appendChild(jDiv);

	var cDiv = document.createElement('div');
	cDiv.id = 'content';
	cDiv.className = 'content';

	var hTag = document.createElement('h5');
	hTag.innerHTML = "Sometimes there might be a group of elements that you want to stylize in a certain way. For example, let's say you're creating an entertainment web page that includes several movie reviews, in addition to other content. The entire content of each review (a heading with the movie title, plus several paragraphs) is wrapped in a <div> in order to keep it all together in one box. The first paragraph of each review is a short summary describing the movie, then all remaining paragraphs contain the content of your critique of the movie. You may want to stylize the";
	cDiv.appendChild(hTag);
	iDiv.appendChild(cDiv);

	var container = document.getElementById(x);
	container.appendChild(iDiv);

	count++;
}

function checkpagename() {
	$('#namealert').text();
	var name = $('#page-name').val();
	var id = $('#host-id').val();

	var url = "/cms-admin/admin/page/checkpagename/" + name + "/hostId/" + id;
	var sumMenuBody = "";
	$.ajax({
		type : "GET",
		url : url,
		async : false,
		success : function(response) {
			if (response == 'FOUND') {
				$('#namealert').text('Name is Already in use !! ');
				$('#page-name').val("");
			} else if (response == 'NOT-FOUND') {
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

function upload(data ,hostId) {

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
         	
         }
     });
} 
