/**
 * 
 */

   /*------------- HTML EDITOR(TINIMCE) INITIALIZATION FOR ADD AND EDIT CONTENT------------------------- */

   tinymce.init({
	selector : '#headercontent, #footercontent,#contaner-content',
	height : 500,
	plugins : [
           'advlist autolink lists link image charmap print preview hr anchor pagebreak',
           'searchreplace wordcount visualblocks visualchars code fullscreen',
           'insertdatetime media nonbreaking save table contextmenu directionality',
           'emoticons template paste textcolor colorpicker textpattern imagetools codesample toc' ],
	toolbar1: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
    toolbar2: 'print preview media | forecolor backcolor emoticons | codesample',
	image_advtab: true
});
   
/*---------------------------------------------------------------------------------------------------------*/
   
   
	$(document).ready(function() {
		// header style  select change 
		$('#header-select').change(function() {
			if ($(this).val() == '1') {
				$('#header-percentage').css('display', 'none')
			} else {
				$('#header-percentage').css('display', 'block')
			}
		});

		// footer style select change 
		$('#footer-select').change(function() {
			if ($(this).val() == '1') {
				$('#footer-percentage').css('display', 'none')
			} else {
				$('#footer-percentage').css('display', 'block')
			}
		});
		
		// header check box change 
		$('#header-check').change(function() {
			if($(this).is(":checked")) {
				$('#header-detail').css('display', 'block')
		    } else {
		    	$('#headerheader-detail').css('display', 'none')
		    }
	    });
		
		// footer check box change
		$('#footer-check').change(function() {
			if($(this).is(":checked")) {
				$('#footer-detail').css('display', 'block')
		    } else {
		    	$('#footer-detail').css('display', 'none')
		    }
	    });
	});
	
	
	
/*======================= TEMPLET PREVIEW====================================  */
	
var count = 0;
function preview() {

	var headerType = $('#header-select').val();
	var footerType = $('#footer-select').val();
	var bodyColumn = $('#body-select').val();
	var header = '';

	// header conditions
	if (headerType != null) {
		$("#header").css("display", "block");
		if (headerType == 1) {
			$("#leftsideheader").css("width", "100%");
			$("#rightsideheader").css("width", "0%");
		} else if (headerType == 2) {
			$("#leftsideheader").css("width", "20%");
			$("#rightsideheader").css("width", (100 - 20) - 1 + '%');
		} else if (headerType == 3) {
			$("#leftsideheader").css("width", "30%");
			$("#rightsideheader").css("width", (100 - 30) - 1 + '%');
		} else if (headerType == 4) {
			$("#leftsideheader").css("width", "40%");
			$("#rightsideheader").css("width", (100 - 40) - 1 + '%');
		} else if (headerType == 5) {
			$("#leftsideheader").css("width", (100 - 20) - 1 + '%');
			$("#rightsideheader").css("width", "20%");
		} else if (headerType == 6) {
			$("#leftsideheader").css("width", (100 - 30) - 1 + '%');
			$("#rightsideheader").css("width", "30%");
		} else if (headerType == 7) {
			$("#leftsideheader").css("width", (100 - 40) - 1 + '%');
			$("#rightsideheader").css("width", "40%");
		}
	}

	// main body content conditions
	if (bodyColumn != null) {
		$("#body-content").css("display", "block");
		if (bodyColumn == 1) {
			$("#leftside").css("width", "100%");
			$("#rightside").css("width", "0%");
		} else if (bodyColumn == 2) {
			$("#leftside").css("width", "50%");
			$("#rightside").css("width", "50%");
		} else if (bodyColumn == 3) {
			$("#leftside").css("width", "66%");
			$("#rightside").css("width", "33%");
		} else if (bodyColumn == 4) {
			$("#leftside").css("width", "33%");
			$("#rightside").css("width", "66%");
		}
	}

	// footer conditions
	if (footerType != null) {
		$("#footer").css("display", "block");
		if (footerType == 1) {
			$("#leftsidefooter").css("width", "100%");
			$("#rightsidefooter").css("width", "0%");
		} else if (footerType == 2) {
			$("#leftsidefooter").css("width", "20%");
			$("#rightsidefooter").css("width", (100 - 20) - 1 + '%');
		} else if (footerType == 3) {
			$("#leftsidefooter").css("width", "30%");
			$("#rightsidefooter").css("width", (100 - 30) - 1 + '%');
		} else if (footerType == 4) {
			$("#leftsidefooter").css("width", "40%");
			$("#rightsidefooter").css("width", (100 - 40) - 1 + '%');
		} else if (footerType == 5) {
			$("#leftsidefooter").css("width", (100 - 20) - 1 + '%');
			$("#rightsidefooter").css("width", "20%");
		} else if (footerType == 6) {
			$("#leftsidefooter").css("width", (100 - 30) - 1 + '%');
			$("#rightsidefooter").css("width", "30%");
		} else if (footerType == 7) {
			$("#leftsidefooter").css("width", (100 - 40) - 1 + '%');
			$("#rightsidefooter").css("width", "40%");
		}
	}

	var html = document.getElementById('templettext').innerHTML;
	document.getElementById('templetontent').value = html;

}
/* ========================================================================================= */

/*==================ADD NEW CONTAINER IN PROVIDED BODY ELEMENT ID===========================*/
	

/*function addcontainer(id) {

	var ele = $('#' + id).parent().parent().attr('id');
	var mainDiv = document.getElementById(ele);
	var x = mainDiv.getElementsByTagName('div')[1].id;
	
	var jDiv = document.createElement('div');
	jDiv.className = 'addremoveDiv';

	jDiv.innerHTML = "<button id='container"+ count+ "' type='button' class='btn btn-info btn-sm' onclick='removeDrawedContainer(this.id);return false'>Remove</button>";
	
	var iDiv = document.createElement('div');
	iDiv.id = 'container' + count;
	iDiv.className = 'subdivs';

	var cDiv = document.createElement('div');
	cDiv.id = 'content';
	cDiv.className = 'content';

	var hTag = document.createElement('h5');
	hTag.innerHTML = "Add content here";
	cDiv.appendChild(hTag);
	iDiv.appendChild(cDiv);

	var container = document.getElementById(x);
	container.appendChild(jDiv);
	container.appendChild(iDiv);

	count++;
}*/

/* ===================================================== */
	
/* ==================REMOVE CONTAINER=================== */
	
/*	function removeDrawedContainer(id) {
		var btnid = id.substr(9);
		$("#subpart" + btnid).remove();
	}*/

/* ==================EDIT CONTENT======================== */
	
	function editContainer(id) {
		
	}
	
/* ==================ADD CONTENT TO CONTAINER============= */
	
	function addcontent(id){
		tinyMCE.triggerSave();
		var content = $('#contaner-content').val();
		$('#'+id).removeClass("subdivs");
		$('div#'+id).html("");
		$('div#'+id).append(content);	
	}
		
/* ==========CHANGE CONTENT MODEL BUTTON ID=============== */
	
	function changeContentModelButtonId(id){
		$(".contentmodel-btn").attr("id", id)
		$('#contentModal').modal('show');
	}
	
/* ==================IDENTIFY CONTAINER==================== */
	
	function containerIdentification(id) {
		if (id == 'lsb-add-container') {
			$("#lsb_container").css("display", "block");
		} else if (id == 'rsb_add_container') {
			$("#rsb_container").css("display", "block");
		}

		$(".addbtn-model").attr("id", id)
		$('#myModal').modal('show');
	}
/* ======================================================= */
	
	
	
/* ==================ON CLICK OF TABLE ROW ==================== */
	
	function rowClicked(value) {
	    /* location.href = "/myurl?param=" + value; */
	    //alert("rowClicked : "+value);
	}
/* ======================================================= */
		
	
/* ==================HERO PANEL SLIDER ==================== */
/*	var images = [];
	var i = 0;
    var path = new Array();
    function swapImage()
    {
        $("#img_preview").empty();
        $("<img>", {
              "src": images[i],
              "width": "100%", "height": "100%"})
            .appendTo("#img_preview");
            if(i < images.length - 1) i++; else i = 0;
       setTimeout("swapImage()",3000);
    }*/
	
	/*var myIndex = 0;
	function carousel() {
		var i;
		var x = document.getElementsByClassName("mySlides");
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none";
		}
		myIndex++;
		if (myIndex > x.length) {
			myIndex = 1
		}
		x[myIndex - 1].style.display = "block";
		setTimeout(carousel, 2000);
	}*/